package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.HistoryUpdateAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.HistoryUpdateAdvanceSearch;
import com.regitiny.catiny.advance.service.HistoryUpdateAdvanceService;
import com.regitiny.catiny.advance.service.mapper.HistoryUpdateAdvanceMapper;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.HistoryUpdate;
import com.regitiny.catiny.service.HistoryUpdateQueryService;
import com.regitiny.catiny.service.HistoryUpdateService;
import io.vavr.control.Option;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Log4j2
@Service
@Transactional
public class HistoryUpdateAdvanceServiceImpl extends LocalServiceImpl<HistoryUpdateService, HistoryUpdateQueryService> implements HistoryUpdateAdvanceService
{
  private final HistoryUpdateAdvanceRepository historyUpdateAdvanceRepository;

  private final HistoryUpdateAdvanceSearch historyUpdateAdvanceSearch;

  private final HistoryUpdateAdvanceMapper historyUpdateAdvanceMapper;

  private final BaseInfoAdvanceServiceImpl baseInfoAdvanceServiceImpl;

  public HistoryUpdateAdvanceServiceImpl(
    HistoryUpdateAdvanceRepository historyUpdateAdvanceRepository,
    HistoryUpdateAdvanceSearch historyUpdateAdvanceSearch,
    HistoryUpdateAdvanceMapper historyUpdateAdvanceMapper, BaseInfoAdvanceServiceImpl baseInfoAdvanceServiceImpl)
  {
    this.historyUpdateAdvanceRepository = historyUpdateAdvanceRepository;
    this.historyUpdateAdvanceSearch = historyUpdateAdvanceSearch;
    this.historyUpdateAdvanceMapper = historyUpdateAdvanceMapper;
    this.baseInfoAdvanceServiceImpl = baseInfoAdvanceServiceImpl;
  }

  public HistoryUpdate createFirstVersion()
  {
    var result = historyUpdateAdvanceRepository.save(new HistoryUpdate().uuid(UUID.randomUUID()).version(0).content(null));
    historyUpdateAdvanceSearch.save(result);
    return result;
  }

  public Option<HistoryUpdate> updateChangeLog(BaseInfo baseInfoInput, Object content)
  {
    if (Objects.isNull(content))
    {
      log.warn("content is null then update is meaningless.");
      return Option.none();
    }

    return baseInfoAdvanceServiceImpl.findOne(baseInfoInput)
      .map(baseInfo ->
      {
        var countVersion = historyUpdateAdvanceRepository.lastVersion(baseInfo).getOrElse(0);
        var historyUpdate = createFirstVersion()
          .baseInfo(baseInfo)
          .version(++countVersion)
          .content(new JSONObject(content).toString());
        return historyUpdateAdvanceRepository.save(historyUpdate);
      });
  }
}
