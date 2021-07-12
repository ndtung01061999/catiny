package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.FollowUserAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.FollowUserAdvanceSearch;
import com.regitiny.catiny.advance.service.FollowUserAdvanceService;
import com.regitiny.catiny.advance.service.mapper.FollowUserAdvanceMapper;
import com.regitiny.catiny.service.FollowUserQueryService;
import com.regitiny.catiny.service.FollowUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class FollowUserAdvanceServiceImpl extends LocalServiceImpl<FollowUserService, FollowUserQueryService> implements FollowUserAdvanceService
{
  private final FollowUserAdvanceRepository followUserAdvanceRepository;

  private final FollowUserAdvanceSearch followUserAdvanceSearch;

  private final FollowUserAdvanceMapper followUserAdvanceMapper;

  public FollowUserAdvanceServiceImpl(FollowUserAdvanceRepository followUserAdvanceRepository,
    FollowUserAdvanceSearch followUserAdvanceSearch,
    FollowUserAdvanceMapper followUserAdvanceMapper)
  {
    this.followUserAdvanceRepository = followUserAdvanceRepository;
    this.followUserAdvanceSearch = followUserAdvanceSearch;
    this.followUserAdvanceMapper = followUserAdvanceMapper;
  }
}
