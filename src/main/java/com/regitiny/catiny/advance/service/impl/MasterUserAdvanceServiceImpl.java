package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.MasterUserAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.MasterUserAdvanceSearch;
import com.regitiny.catiny.advance.service.MasterUserAdvanceService;
import com.regitiny.catiny.advance.service.mapper.MasterUserAdvanceMapper;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.security.SecurityUtils;
import com.regitiny.catiny.service.MasterUserQueryService;
import com.regitiny.catiny.service.MasterUserService;
import com.regitiny.catiny.service.UserService;
import com.regitiny.catiny.tools.utils.StringPool;
import io.vavr.control.Option;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Log4j2
@Service
@Transactional
public class MasterUserAdvanceServiceImpl extends LocalServiceImpl<MasterUserService, MasterUserQueryService> implements MasterUserAdvanceService
{
  private final MasterUserAdvanceRepository masterUserAdvanceRepository;

  private final MasterUserAdvanceSearch masterUserAdvanceSearch;

  private final MasterUserAdvanceMapper masterUserAdvanceMapper;

  private final UserService userService;

  public MasterUserAdvanceServiceImpl(MasterUserAdvanceRepository masterUserAdvanceRepository,
    MasterUserAdvanceSearch masterUserAdvanceSearch,
    MasterUserAdvanceMapper masterUserAdvanceMapper, UserService userService)
  {
    this.masterUserAdvanceRepository = masterUserAdvanceRepository;
    this.masterUserAdvanceSearch = masterUserAdvanceSearch;
    this.masterUserAdvanceMapper = masterUserAdvanceMapper;
    this.userService = userService;
  }

  @Override
  public Option<MasterUser> currentMasterUser()
  {
    return SecurityUtils.getCurrentUserLogin()
      .map(masterUserAdvanceRepository::findOneByUserLogin)
      .orElseGet(() ->
      {
        log.warn("current user login not exists");
        return Option.none();
      });
  }

  @Override
  public Option<MasterUser> anonymousMasterUser()
  {
    return masterUserAdvanceRepository.findById(0L)
      .map(Option::of)
      .orElseGet(() ->
      {
        log.warn("anonymous user not exists");
        return Option.none();
      });
  }

  @Override
  public Option<MasterUser> createMasterUser(String login, String nickName)
  {
    return userService.getUserWithAuthoritiesByLogin(login)
//      .filter(User::isActivated)
      .map(user -> masterUserAdvanceRepository.findOneByUserLogin(user.getLogin())
        .map(masterUser ->
        {
          log.warn("MasterUeser really exist! masterUser :{}", masterUser);
          return Option.of(masterUser);
        })
        .getOrElse(() ->
        {
          var masterUser = new MasterUser().uuid(UUID.randomUUID())
            .user(user)
            .fullName(user.getLastName() + StringPool.SPACE + user.getFirstName())
            .nickname(nickName);
          return Option.of(masterUser);
        }))
      .orElseGet(() ->
      {
        log.warn("user not exist");
        return Option.none();
      });
  }
}
