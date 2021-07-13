package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.GroupProfileAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.GroupProfileAdvanceSearch;
import com.regitiny.catiny.advance.service.GroupProfileAdvanceService;
import com.regitiny.catiny.advance.service.mapper.GroupProfileAdvanceMapper;
import com.regitiny.catiny.service.GroupProfileQueryService;
import com.regitiny.catiny.service.GroupProfileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class GroupProfileAdvanceServiceImpl extends LocalServiceImpl<GroupProfileService, GroupProfileQueryService> implements GroupProfileAdvanceService
{
  private final GroupProfileAdvanceRepository groupProfileAdvanceRepository;

  private final GroupProfileAdvanceSearch groupProfileAdvanceSearch;

  private final GroupProfileAdvanceMapper groupProfileAdvanceMapper;

  public GroupProfileAdvanceServiceImpl(GroupProfileAdvanceRepository groupProfileAdvanceRepository,
    GroupProfileAdvanceSearch groupProfileAdvanceSearch,
    GroupProfileAdvanceMapper groupProfileAdvanceMapper)
  {
    this.groupProfileAdvanceRepository = groupProfileAdvanceRepository;
    this.groupProfileAdvanceSearch = groupProfileAdvanceSearch;
    this.groupProfileAdvanceMapper = groupProfileAdvanceMapper;
  }
}
