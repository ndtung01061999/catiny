package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.PostAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PostAdvanceSearch;
import com.regitiny.catiny.advance.service.PostAdvanceService;
import com.regitiny.catiny.advance.service.mapper.PostAdvanceMapper;
import com.regitiny.catiny.service.PostQueryService;
import com.regitiny.catiny.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class PostAdvanceServiceImpl extends LocalServiceImpl<PostService, PostQueryService> implements PostAdvanceService
{
  private final PostAdvanceRepository postAdvanceRepository;

  private final PostAdvanceSearch postAdvanceSearch;

  private final PostAdvanceMapper postAdvanceMapper;

  public PostAdvanceServiceImpl(PostAdvanceRepository postAdvanceRepository,
    PostAdvanceSearch postAdvanceSearch, PostAdvanceMapper postAdvanceMapper)
  {
    this.postAdvanceRepository = postAdvanceRepository;
    this.postAdvanceSearch = postAdvanceSearch;
    this.postAdvanceMapper = postAdvanceMapper;
  }
}
