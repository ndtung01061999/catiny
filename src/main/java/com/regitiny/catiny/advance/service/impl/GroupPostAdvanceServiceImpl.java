package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.GroupPostAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.GroupPostAdvanceSearch;
import com.regitiny.catiny.advance.service.GroupPostAdvanceService;
import com.regitiny.catiny.advance.service.mapper.GroupPostAdvanceMapper;
import com.regitiny.catiny.service.GroupPostQueryService;
import com.regitiny.catiny.service.GroupPostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class GroupPostAdvanceServiceImpl extends LocalServiceImpl<GroupPostService, GroupPostQueryService> implements GroupPostAdvanceService
{
  private final GroupPostAdvanceRepository groupPostAdvanceRepository;

  private final GroupPostAdvanceSearch groupPostAdvanceSearch;

  private final GroupPostAdvanceMapper groupPostAdvanceMapper;

  public GroupPostAdvanceServiceImpl(GroupPostAdvanceRepository groupPostAdvanceRepository,
    GroupPostAdvanceSearch groupPostAdvanceSearch,
    GroupPostAdvanceMapper groupPostAdvanceMapper)
  {
    this.groupPostAdvanceRepository = groupPostAdvanceRepository;
    this.groupPostAdvanceSearch = groupPostAdvanceSearch;
    this.groupPostAdvanceMapper = groupPostAdvanceMapper;
  }
}
