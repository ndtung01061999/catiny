package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.MessageContentAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.MessageContentAdvanceSearch;
import com.regitiny.catiny.advance.service.MessageContentAdvanceService;
import com.regitiny.catiny.advance.service.mapper.MessageContentAdvanceMapper;
import com.regitiny.catiny.service.MessageContentQueryService;
import com.regitiny.catiny.service.MessageContentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class MessageContentAdvanceServiceImpl extends LocalServiceImpl<MessageContentService, MessageContentQueryService> implements MessageContentAdvanceService
{
  private final MessageContentAdvanceRepository messageContentAdvanceRepository;

  private final MessageContentAdvanceSearch messageContentAdvanceSearch;

  private final MessageContentAdvanceMapper messageContentAdvanceMapper;

  public MessageContentAdvanceServiceImpl(
    MessageContentAdvanceRepository messageContentAdvanceRepository,
    MessageContentAdvanceSearch messageContentAdvanceSearch,
    MessageContentAdvanceMapper messageContentAdvanceMapper)
  {
    this.messageContentAdvanceRepository = messageContentAdvanceRepository;
    this.messageContentAdvanceSearch = messageContentAdvanceSearch;
    this.messageContentAdvanceMapper = messageContentAdvanceMapper;
  }
}
