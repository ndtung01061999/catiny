package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.BaseInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.BaseInfoAdvanceSearch;
import com.regitiny.catiny.advance.service.BaseInfoAdvanceService;
import com.regitiny.catiny.advance.service.mapper.BaseInfoAdvanceMapper;
import com.regitiny.catiny.service.BaseInfoQueryService;
import com.regitiny.catiny.service.BaseInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class BaseInfoAdvanceServiceImpl extends LocalServiceImpl<BaseInfoService, BaseInfoQueryService> implements BaseInfoAdvanceService
{
  private final BaseInfoAdvanceRepository baseInfoAdvanceRepository;

  private final BaseInfoAdvanceSearch baseInfoAdvanceSearch;

  private final BaseInfoAdvanceMapper baseInfoAdvanceMapper;

  public BaseInfoAdvanceServiceImpl(BaseInfoAdvanceRepository baseInfoAdvanceRepository,
    BaseInfoAdvanceSearch baseInfoAdvanceSearch, BaseInfoAdvanceMapper baseInfoAdvanceMapper)
  {
    this.baseInfoAdvanceRepository = baseInfoAdvanceRepository;
    this.baseInfoAdvanceSearch = baseInfoAdvanceSearch;
    this.baseInfoAdvanceMapper = baseInfoAdvanceMapper;
  }
}
