package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.HistoryUpdateAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.HistoryUpdateAdvanceSearch;
import com.regitiny.catiny.advance.service.HistoryUpdateAdvanceService;
import com.regitiny.catiny.advance.service.mapper.HistoryUpdateAdvanceMapper;
import com.regitiny.catiny.domain.HistoryUpdate;
import com.regitiny.catiny.service.HistoryUpdateQueryService;
import com.regitiny.catiny.service.HistoryUpdateService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Override
  public HistoryUpdate createHistoryUpdate()
  {
    var result = historyUpdateAdvanceRepository.save(new HistoryUpdate().uuid(UUID.randomUUID()).version(0).content(null));
    historyUpdateAdvanceSearch.save(result);
    return result;
  }
//  public HistoryUpdate updateHistoryUpdate(Long baseInfoId ,Object content)
//  {
//    historyUpdateAdvanceRepository.
//    var result = historyUpdateAdvanceRepository.save(new HistoryUpdate().uuid(UUID.randomUUID()).version(0).content(null));
//    historyUpdateAdvanceSearch.save(result);
//    return result;
//  }
}
