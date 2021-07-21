package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.BaseInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.BaseInfoAdvanceSearch;
import com.regitiny.catiny.advance.service.BaseInfoAdvanceService;
import com.regitiny.catiny.advance.service.mapper.BaseInfoAdvanceMapper;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.enumeration.ProcessStatus;
import com.regitiny.catiny.service.BaseInfoQueryService;
import com.regitiny.catiny.service.BaseInfoService;
import com.regitiny.catiny.util.MasterUserUtil;
import io.vavr.control.Option;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@Service
@Transactional
public class BaseInfoAdvanceServiceImpl extends LocalServiceImpl<BaseInfoService, BaseInfoQueryService> implements BaseInfoAdvanceService
{
  private final BaseInfoAdvanceRepository baseInfoAdvanceRepository;
  private final BaseInfoAdvanceSearch baseInfoAdvanceSearch;
  private final BaseInfoAdvanceMapper baseInfoAdvanceMapper;
  private final PermissionAdvanceServiceImpl permissionAdvanceService;
  private final HistoryUpdateAdvanceServiceImpl historyUpdateAdvanceService;

  public BaseInfoAdvanceServiceImpl(BaseInfoAdvanceRepository baseInfoAdvanceRepository, BaseInfoAdvanceSearch baseInfoAdvanceSearch, BaseInfoAdvanceMapper baseInfoAdvanceMapper, PermissionAdvanceServiceImpl permissionAdvanceService, @Lazy HistoryUpdateAdvanceServiceImpl historyUpdateAdvanceService)
  {
    this.baseInfoAdvanceRepository = baseInfoAdvanceRepository;
    this.baseInfoAdvanceSearch = baseInfoAdvanceSearch;
    this.baseInfoAdvanceMapper = baseInfoAdvanceMapper;
    this.permissionAdvanceService = permissionAdvanceService;
    this.historyUpdateAdvanceService = historyUpdateAdvanceService;
  }


  public Option<BaseInfo> findByUuid(UUID uuid)
  {
    return baseInfoAdvanceRepository.findOneByUuid(uuid)
      .map(Option::of)
      .getOrElse(() ->
      {
        log.info("not found BaseInfo by id = {}", uuid);
        return Option.none();
      });
  }

  public Option<BaseInfo> findById(Long id)
  {
    return baseInfoAdvanceRepository.findById(id)
      .map(Option::of)
      .orElseGet(() ->
      {
        log.info("not found BaseInfo by id = {}", id);
        return Option.none();
      });
  }

  public Option<BaseInfo> findOne(BaseInfo baseInfo)
  {
    return Option
      .when(Objects.nonNull(baseInfo),
        findById(baseInfo.getId())
          .orElse(findByUuid(baseInfo.getUuid()))
          .onEmpty(() -> log.warn("not found this BaseInfo : {}", baseInfo)))
      .onEmpty(() -> log.warn("BaseInfo input is empty")).get();
  }

  public BaseInfo createForOwner()
  {
    var now = Instant.now();
    var currentMasterUser = MasterUserUtil.getCurrentMasterUser().get();
    var ownerPermission = permissionAdvanceService.createForOwner().masterUser(currentMasterUser);
    var anonymousMasterUser = MasterUserUtil.anonymousMasterUser().get();
    var anonymousPermission = permissionAdvanceService.createForAnonymous().masterUser(anonymousMasterUser);

    var historyUpdate = historyUpdateAdvanceService.createFirstVersion();
    var baseInfo = new BaseInfo().uuid(UUID.randomUUID())
      .processStatus(ProcessStatus.NOT_PROCESSED)
//      .modifiedClass(null)
      .createdDate(now)
      .modifiedDate(now)
      .owner(currentMasterUser)
      .addPermission(ownerPermission)
      .addPermission(anonymousPermission)
      .createdBy(currentMasterUser)
      .modifiedBy(currentMasterUser)
      .priorityIndex(0L)
      .countUse(0L)
      .addHistoryUpdate(historyUpdate);
    return baseInfoAdvanceRepository.save(baseInfo);
  }


}
