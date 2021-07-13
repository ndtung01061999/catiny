package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.MasterUserAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.MasterUserAdvanceSearch;
import com.regitiny.catiny.advance.service.MasterUserAdvanceService;
import com.regitiny.catiny.advance.service.mapper.MasterUserAdvanceMapper;
import com.regitiny.catiny.service.MasterUserQueryService;
import com.regitiny.catiny.service.MasterUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class MasterUserAdvanceServiceImpl extends LocalServiceImpl<MasterUserService, MasterUserQueryService> implements MasterUserAdvanceService
{
  private final MasterUserAdvanceRepository masterUserAdvanceRepository;

  private final MasterUserAdvanceSearch masterUserAdvanceSearch;

  private final MasterUserAdvanceMapper masterUserAdvanceMapper;

  public MasterUserAdvanceServiceImpl(MasterUserAdvanceRepository masterUserAdvanceRepository,
    MasterUserAdvanceSearch masterUserAdvanceSearch,
    MasterUserAdvanceMapper masterUserAdvanceMapper)
  {
    this.masterUserAdvanceRepository = masterUserAdvanceRepository;
    this.masterUserAdvanceSearch = masterUserAdvanceSearch;
    this.masterUserAdvanceMapper = masterUserAdvanceMapper;
  }
}
