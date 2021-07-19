package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.BaseInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.MasterUserAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.BaseInfoAdvanceSearch;
import com.regitiny.catiny.advance.service.BaseInfoAdvanceService;
import com.regitiny.catiny.advance.service.mapper.BaseInfoAdvanceMapper;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.HistoryUpdate;
import com.regitiny.catiny.domain.enumeration.ProcessStatus;
import com.regitiny.catiny.quick.PermissionQuick;
import com.regitiny.catiny.service.BaseInfoQueryService;
import com.regitiny.catiny.service.BaseInfoService;
import com.regitiny.catiny.util.MasterUserUtil;
import io.vavr.control.Option;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Log4j2
@Service
@Transactional
public class BaseInfoAdvanceServiceImpl extends LocalServiceImpl<BaseInfoService, BaseInfoQueryService> implements BaseInfoAdvanceService
{
  private final BaseInfoAdvanceRepository baseInfoAdvanceRepository;

  private final BaseInfoAdvanceSearch baseInfoAdvanceSearch;

  private final BaseInfoAdvanceMapper baseInfoAdvanceMapper;

  public BaseInfoAdvanceServiceImpl(BaseInfoAdvanceRepository baseInfoAdvanceRepository,
    BaseInfoAdvanceSearch baseInfoAdvanceSearch, BaseInfoAdvanceMapper baseInfoAdvanceMapper)
  {
    this.baseInfoAdvanceRepository = baseInfoAdvanceRepository;
    this.baseInfoAdvanceSearch = baseInfoAdvanceSearch;
    this.baseInfoAdvanceMapper = baseInfoAdvanceMapper;
  }

  private MasterUserAdvanceRepository masterUserAdvanceRepository;

//  public Option<BaseInfo> findByUuid(UUID uuid)
//  {
//    return baseInfoAdvanceRepository.findOneByUuid(uuid)
//      .map(Option::of)
//      .orElseGet(() ->
//      {
//        log.info("not found BaseInfo by id = {}", uuid);
//        return Option.none();
//      });
//  }

  public static BaseInfo beforeCreate()
  {
    var baseInfo = new BaseInfo();
    var now = Instant.now();
    var currentMasterUser = MasterUserUtil.getCurrentMasterUser().get();
    var currentPermissions = new PermissionQuick()
      .allTrue()
      .uuid(UUID.randomUUID())
      .masterUser(currentMasterUser)
      .level(0);
    var anonymousMasterUser = MasterUserUtil.anonymousMasterUser().get();
    var anonymousPermission = new PermissionQuick()
      .allFalse()
      .uuid(UUID.randomUUID())
      .masterUser(anonymousMasterUser)
      .level(Integer.MAX_VALUE);

    var historyUpdate = new HistoryUpdate();
    return baseInfo.uuid(UUID.randomUUID())
      .processStatus(ProcessStatus.NOT_PROCESSED)
//      .modifiedClass(null)
      .createdDate(now)
      .modifiedDate(now)
      .owner(currentMasterUser)
      .addPermission(currentPermissions)
      .addPermission(anonymousPermission)
      .createdBy(currentMasterUser)
      .modifiedBy(currentMasterUser)
      .priorityIndex(0L)
      .countUse(0L)
      .addHistoryUpdate(historyUpdate);
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

//  public static BaseInfo beforeUpdate(BaseInfo baseInfo)
//  {
//    var currentMasterUser = MasterUserUtil.getCurrentMasterUser().get();
//    var historyUpdate = new HistoryUpdate().uuid(UUID.randomUUID())
//      .version();
//    baseInfo.addHistoryUpdate()
//    baseInfo.modifiedDate(Instant.now())
//      .modifiedBy(currentMasterUser);
//  }
}
