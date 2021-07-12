package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.HanhChinhVNAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.HanhChinhVNAdvanceSearch;
import com.regitiny.catiny.advance.service.HanhChinhVNAdvanceService;
import com.regitiny.catiny.advance.service.mapper.HanhChinhVNAdvanceMapper;
import com.regitiny.catiny.service.HanhChinhVNQueryService;
import com.regitiny.catiny.service.HanhChinhVNService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class HanhChinhVNAdvanceServiceImpl extends LocalServiceImpl<HanhChinhVNService, HanhChinhVNQueryService> implements HanhChinhVNAdvanceService
{
  private final HanhChinhVNAdvanceRepository hanhChinhVNAdvanceRepository;

  private final HanhChinhVNAdvanceSearch hanhChinhVNAdvanceSearch;

  private final HanhChinhVNAdvanceMapper hanhChinhVNAdvanceMapper;

  public HanhChinhVNAdvanceServiceImpl(HanhChinhVNAdvanceRepository hanhChinhVNAdvanceRepository,
    HanhChinhVNAdvanceSearch hanhChinhVNAdvanceSearch,
    HanhChinhVNAdvanceMapper hanhChinhVNAdvanceMapper)
  {
    this.hanhChinhVNAdvanceRepository = hanhChinhVNAdvanceRepository;
    this.hanhChinhVNAdvanceSearch = hanhChinhVNAdvanceSearch;
    this.hanhChinhVNAdvanceMapper = hanhChinhVNAdvanceMapper;
  }
}
