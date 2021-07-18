package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.ClassInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.ClassInfoAdvanceSearch;
import com.regitiny.catiny.advance.service.ClassInfoAdvanceService;
import com.regitiny.catiny.advance.service.mapper.ClassInfoAdvanceMapper;
import com.regitiny.catiny.service.ClassInfoQueryService;
import com.regitiny.catiny.service.ClassInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class ClassInfoAdvanceServiceImpl extends LocalServiceImpl<ClassInfoService, ClassInfoQueryService> implements ClassInfoAdvanceService
{
  private final ClassInfoAdvanceRepository classInfoAdvanceRepository;

  private final ClassInfoAdvanceSearch classInfoAdvanceSearch;

  private final ClassInfoAdvanceMapper classInfoAdvanceMapper;

  public ClassInfoAdvanceServiceImpl(ClassInfoAdvanceRepository classInfoAdvanceRepository,
    ClassInfoAdvanceSearch classInfoAdvanceSearch,
    ClassInfoAdvanceMapper classInfoAdvanceMapper)
  {
    this.classInfoAdvanceRepository = classInfoAdvanceRepository;
    this.classInfoAdvanceSearch = classInfoAdvanceSearch;
    this.classInfoAdvanceMapper = classInfoAdvanceMapper;
  }
}
