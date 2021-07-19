package com.regitiny.catiny.util;

import com.regitiny.catiny.advance.service.MasterUserAdvanceService;
import com.regitiny.catiny.domain.MasterUser;
import io.vavr.control.Option;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MasterUserUtil
{
  private static final Logger log = LogManager.getLogger(MasterUserUtil.class);
  private static MasterUserAdvanceService masterUserAdvanceService;

  public static Option<MasterUser> getCurrentMasterUser()
  {
    return masterUserAdvanceService.currentMasterUser();
  }

  public static Option<MasterUser> anonymousMasterUser()
  {
    return masterUserAdvanceService.anonymousMasterUser();
  }

  @Autowired
  private void setMasterUserRepository(MasterUserAdvanceService masterUserAdvanceService)
  {
    MasterUserUtil.masterUserAdvanceService = masterUserAdvanceService;
  }

}
