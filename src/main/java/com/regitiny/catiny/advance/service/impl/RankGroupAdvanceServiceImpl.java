package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.RankGroupAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.RankGroupAdvanceSearch;
import com.regitiny.catiny.advance.service.RankGroupAdvanceService;
import com.regitiny.catiny.advance.service.mapper.RankGroupAdvanceMapper;
import com.regitiny.catiny.service.RankGroupQueryService;
import com.regitiny.catiny.service.RankGroupService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class RankGroupAdvanceServiceImpl extends LocalServiceImpl<RankGroupService, RankGroupQueryService> implements RankGroupAdvanceService
{
  private final RankGroupAdvanceRepository rankGroupAdvanceRepository;

  private final RankGroupAdvanceSearch rankGroupAdvanceSearch;

  private final RankGroupAdvanceMapper rankGroupAdvanceMapper;

  public RankGroupAdvanceServiceImpl(RankGroupAdvanceRepository rankGroupAdvanceRepository,
    RankGroupAdvanceSearch rankGroupAdvanceSearch,
    RankGroupAdvanceMapper rankGroupAdvanceMapper)
  {
    this.rankGroupAdvanceRepository = rankGroupAdvanceRepository;
    this.rankGroupAdvanceSearch = rankGroupAdvanceSearch;
    this.rankGroupAdvanceMapper = rankGroupAdvanceMapper;
  }
}
