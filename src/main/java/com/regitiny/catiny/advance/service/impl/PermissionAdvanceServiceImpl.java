package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.PermissionAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PermissionAdvanceSearch;
import com.regitiny.catiny.advance.service.PermissionAdvanceService;
import com.regitiny.catiny.advance.service.mapper.PermissionAdvanceMapper;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.service.PermissionQueryService;
import com.regitiny.catiny.service.PermissionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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

  /**
   * .read(true)
   * .write(true)
   * .share(true)
   * .add(true)
   * .delete(true)
   * .level(0))
   *
   * @return permission
   */
  public Permission createForOwner()
  {
    return permissionAdvanceRepository.save(new Permission().uuid(UUID.randomUUID())
      .read(true)
      .write(true)
      .share(true)
      .add(true)
      .delete(true)
      .level(0));
  }

  /**
   * .read(false)
   * .write(false)
   * .share(false)
   * .add(false)
   * .delete(false)
   * .level(Integer.MAX_VALUE))
   *
   * @return permission
   */
  public Permission createForAnonymous()
  {
    return permissionAdvanceRepository.save(new Permission().uuid(UUID.randomUUID())
      .read(false)
      .write(false)
      .share(false)
      .add(false)
      .delete(false)
      .level(Integer.MAX_VALUE));
  }

  /**
   * .read(true)
   * .write(false)
   * .share(false)
   * .add(false)
   * .delete(false)
   * .level(Integer.MAX_VALUE))
   *
   * @return permission
   */
  public Permission createReadOnly()
  {
    return permissionAdvanceRepository.save(createForAnonymous().uuid(UUID.randomUUID())
      .read(true));
  }


}
