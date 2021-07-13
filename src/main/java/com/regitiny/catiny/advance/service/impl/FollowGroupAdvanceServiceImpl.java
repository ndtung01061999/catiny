package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.FollowGroupAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.FollowGroupAdvanceSearch;
import com.regitiny.catiny.advance.service.FollowGroupAdvanceService;
import com.regitiny.catiny.advance.service.mapper.FollowGroupAdvanceMapper;
import com.regitiny.catiny.service.FollowGroupQueryService;
import com.regitiny.catiny.service.FollowGroupService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class FollowGroupAdvanceServiceImpl extends LocalServiceImpl<FollowGroupService, FollowGroupQueryService> implements FollowGroupAdvanceService
{
  private final FollowGroupAdvanceRepository followGroupAdvanceRepository;

  private final FollowGroupAdvanceSearch followGroupAdvanceSearch;

  private final FollowGroupAdvanceMapper followGroupAdvanceMapper;

  public FollowGroupAdvanceServiceImpl(FollowGroupAdvanceRepository followGroupAdvanceRepository,
    FollowGroupAdvanceSearch followGroupAdvanceSearch,
    FollowGroupAdvanceMapper followGroupAdvanceMapper)
  {
    this.followGroupAdvanceRepository = followGroupAdvanceRepository;
    this.followGroupAdvanceSearch = followGroupAdvanceSearch;
    this.followGroupAdvanceMapper = followGroupAdvanceMapper;
  }
}
