package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.UserProfileAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.UserProfileAdvanceSearch;
import com.regitiny.catiny.advance.service.UserProfileAdvanceService;
import com.regitiny.catiny.advance.service.mapper.UserProfileAdvanceMapper;
import com.regitiny.catiny.service.UserProfileQueryService;
import com.regitiny.catiny.service.UserProfileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class UserProfileAdvanceServiceImpl extends LocalServiceImpl<UserProfileService, UserProfileQueryService> implements UserProfileAdvanceService
{
  private final UserProfileAdvanceRepository userProfileAdvanceRepository;

  private final UserProfileAdvanceSearch userProfileAdvanceSearch;

  private final UserProfileAdvanceMapper userProfileAdvanceMapper;

  public UserProfileAdvanceServiceImpl(UserProfileAdvanceRepository userProfileAdvanceRepository,
    UserProfileAdvanceSearch userProfileAdvanceSearch,
    UserProfileAdvanceMapper userProfileAdvanceMapper)
  {
    this.userProfileAdvanceRepository = userProfileAdvanceRepository;
    this.userProfileAdvanceSearch = userProfileAdvanceSearch;
    this.userProfileAdvanceMapper = userProfileAdvanceMapper;
  }
}
