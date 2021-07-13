package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.EventAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.EventAdvanceSearch;
import com.regitiny.catiny.advance.service.EventAdvanceService;
import com.regitiny.catiny.advance.service.mapper.EventAdvanceMapper;
import com.regitiny.catiny.service.EventQueryService;
import com.regitiny.catiny.service.EventService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class EventAdvanceServiceImpl extends LocalServiceImpl<EventService, EventQueryService> implements EventAdvanceService
{
  private final EventAdvanceRepository eventAdvanceRepository;

  private final EventAdvanceSearch eventAdvanceSearch;

  private final EventAdvanceMapper eventAdvanceMapper;

  public EventAdvanceServiceImpl(EventAdvanceRepository eventAdvanceRepository,
    EventAdvanceSearch eventAdvanceSearch, EventAdvanceMapper eventAdvanceMapper)
  {
    this.eventAdvanceRepository = eventAdvanceRepository;
    this.eventAdvanceSearch = eventAdvanceSearch;
    this.eventAdvanceMapper = eventAdvanceMapper;
  }
}
