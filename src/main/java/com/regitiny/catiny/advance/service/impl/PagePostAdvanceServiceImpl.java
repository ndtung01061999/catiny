package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.PagePostAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PagePostAdvanceSearch;
import com.regitiny.catiny.advance.service.PagePostAdvanceService;
import com.regitiny.catiny.advance.service.mapper.PagePostAdvanceMapper;
import com.regitiny.catiny.service.PagePostQueryService;
import com.regitiny.catiny.service.PagePostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class PagePostAdvanceServiceImpl extends LocalServiceImpl<PagePostService, PagePostQueryService> implements PagePostAdvanceService
{
  private final PagePostAdvanceRepository pagePostAdvanceRepository;

  private final PagePostAdvanceSearch pagePostAdvanceSearch;

  private final PagePostAdvanceMapper pagePostAdvanceMapper;

  public PagePostAdvanceServiceImpl(PagePostAdvanceRepository pagePostAdvanceRepository,
    PagePostAdvanceSearch pagePostAdvanceSearch, PagePostAdvanceMapper pagePostAdvanceMapper)
  {
    this.pagePostAdvanceRepository = pagePostAdvanceRepository;
    this.pagePostAdvanceSearch = pagePostAdvanceSearch;
    this.pagePostAdvanceMapper = pagePostAdvanceMapper;
  }
}
