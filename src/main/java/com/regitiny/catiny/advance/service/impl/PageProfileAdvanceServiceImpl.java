package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.PageProfileAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PageProfileAdvanceSearch;
import com.regitiny.catiny.advance.service.PageProfileAdvanceService;
import com.regitiny.catiny.advance.service.mapper.PageProfileAdvanceMapper;
import com.regitiny.catiny.service.PageProfileQueryService;
import com.regitiny.catiny.service.PageProfileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class PageProfileAdvanceServiceImpl extends LocalServiceImpl<PageProfileService, PageProfileQueryService> implements PageProfileAdvanceService
{
  private final PageProfileAdvanceRepository pageProfileAdvanceRepository;

  private final PageProfileAdvanceSearch pageProfileAdvanceSearch;

  private final PageProfileAdvanceMapper pageProfileAdvanceMapper;

  public PageProfileAdvanceServiceImpl(PageProfileAdvanceRepository pageProfileAdvanceRepository,
    PageProfileAdvanceSearch pageProfileAdvanceSearch,
    PageProfileAdvanceMapper pageProfileAdvanceMapper)
  {
    this.pageProfileAdvanceRepository = pageProfileAdvanceRepository;
    this.pageProfileAdvanceSearch = pageProfileAdvanceSearch;
    this.pageProfileAdvanceMapper = pageProfileAdvanceMapper;
  }
}
