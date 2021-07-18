package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.PermissionAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PermissionAdvanceSearch;
import com.regitiny.catiny.advance.service.PermissionAdvanceService;
import com.regitiny.catiny.advance.service.mapper.PermissionAdvanceMapper;
import com.regitiny.catiny.service.PermissionQueryService;
import com.regitiny.catiny.service.PermissionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class PermissionAdvanceServiceImpl extends LocalServiceImpl<PermissionService, PermissionQueryService> implements PermissionAdvanceService
{
  private final PermissionAdvanceRepository permissionAdvanceRepository;

  private final PermissionAdvanceSearch permissionAdvanceSearch;

  private final PermissionAdvanceMapper permissionAdvanceMapper;

  public PermissionAdvanceServiceImpl(PermissionAdvanceRepository permissionAdvanceRepository,
    PermissionAdvanceSearch permissionAdvanceSearch,
    PermissionAdvanceMapper permissionAdvanceMapper)
  {
    this.permissionAdvanceRepository = permissionAdvanceRepository;
    this.permissionAdvanceSearch = permissionAdvanceSearch;
    this.permissionAdvanceMapper = permissionAdvanceMapper;
  }
}
