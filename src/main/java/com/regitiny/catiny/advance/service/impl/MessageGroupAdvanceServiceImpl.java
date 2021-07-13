package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.MessageGroupAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.MessageGroupAdvanceSearch;
import com.regitiny.catiny.advance.service.MessageGroupAdvanceService;
import com.regitiny.catiny.advance.service.mapper.MessageGroupAdvanceMapper;
import com.regitiny.catiny.service.MessageGroupQueryService;
import com.regitiny.catiny.service.MessageGroupService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class MessageGroupAdvanceServiceImpl extends LocalServiceImpl<MessageGroupService, MessageGroupQueryService> implements MessageGroupAdvanceService
{
  private final MessageGroupAdvanceRepository messageGroupAdvanceRepository;

  private final MessageGroupAdvanceSearch messageGroupAdvanceSearch;

  private final MessageGroupAdvanceMapper messageGroupAdvanceMapper;

  public MessageGroupAdvanceServiceImpl(MessageGroupAdvanceRepository messageGroupAdvanceRepository,
    MessageGroupAdvanceSearch messageGroupAdvanceSearch,
    MessageGroupAdvanceMapper messageGroupAdvanceMapper)
  {
    this.messageGroupAdvanceRepository = messageGroupAdvanceRepository;
    this.messageGroupAdvanceSearch = messageGroupAdvanceSearch;
    this.messageGroupAdvanceMapper = messageGroupAdvanceMapper;
  }
}
