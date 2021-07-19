package com.regitiny.catiny.util;

import com.regitiny.catiny.advance.repository.MasterUserAdvanceRepository;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.security.SecurityUtils;
import io.vavr.control.Option;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MasterUserUtil
{
  private static final Logger log = LogManager.getLogger(MasterUserUtil.class);
  private static MasterUserAdvanceRepository masterUserAdvanceRepository;

  private MasterUserUtil()
  {
    throw new IllegalStateException("MasterUserUtil is Util class");
  }

  public static Option<MasterUser> getCurrentMasterUser()
  {
    return SecurityUtils.getCurrentUserLogin()
      .map(login -> masterUserAdvanceRepository.findOneByUserLogin(login))
      .orElseGet(() ->
      {
        log.warn("current user login not exists");
        return Option.none();
      });
  }

  public static Option<MasterUser> getAnonymousMasterUser()
  {
    return masterUserAdvanceRepository.findById(0L)
      .map(Option::of)
      .orElseGet(() ->
      {
        log.warn("anonymous user not exists");
        return Option.none();
      });
  }

  @Autowired
  private void setMasterUserRepository(MasterUserAdvanceRepository masterUserAdvanceRepository)
  {
    MasterUserUtil.masterUserAdvanceRepository = masterUserAdvanceRepository;
  }

}
