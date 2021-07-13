package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.TopicInterestAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.TopicInterestAdvanceSearch;
import com.regitiny.catiny.advance.service.TopicInterestAdvanceService;
import com.regitiny.catiny.advance.service.mapper.TopicInterestAdvanceMapper;
import com.regitiny.catiny.service.TopicInterestQueryService;
import com.regitiny.catiny.service.TopicInterestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class TopicInterestAdvanceServiceImpl extends LocalServiceImpl<TopicInterestService, TopicInterestQueryService> implements TopicInterestAdvanceService
{
  private final TopicInterestAdvanceRepository topicInterestAdvanceRepository;

  private final TopicInterestAdvanceSearch topicInterestAdvanceSearch;

  private final TopicInterestAdvanceMapper topicInterestAdvanceMapper;

  public TopicInterestAdvanceServiceImpl(
    TopicInterestAdvanceRepository topicInterestAdvanceRepository,
    TopicInterestAdvanceSearch topicInterestAdvanceSearch,
    TopicInterestAdvanceMapper topicInterestAdvanceMapper)
  {
    this.topicInterestAdvanceRepository = topicInterestAdvanceRepository;
    this.topicInterestAdvanceSearch = topicInterestAdvanceSearch;
    this.topicInterestAdvanceMapper = topicInterestAdvanceMapper;
  }
}
