package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.MessageContentAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.MessageContentAdvanceSearch;
import com.regitiny.catiny.advance.service.MessageContentAdvanceService;
import com.regitiny.catiny.advance.service.MessageGroupAdvanceService;
import com.regitiny.catiny.advance.service.mapper.MessageContentAdvanceMapper;
import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.service.MessageContentQueryService;
import com.regitiny.catiny.service.MessageContentService;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.tools.utils.EntityDefaultPropertiesServiceUtils;
import com.regitiny.catiny.tools.utils.UserUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MessageContent}.
 */
@Log4j2
@Service
@Transactional
public class MessageContentAdvanceServiceImpl extends LocalServiceImpl<MessageContentService, MessageContentQueryService> implements MessageContentAdvanceService
{
  private final MessageContentAdvanceRepository messageContentAdvanceRepository;

  private final MessageContentAdvanceMapper messageContentAdvanceMapper;

  private final MessageContentAdvanceSearch messageContentAdvanceSearch;

  private final MessageGroupAdvanceService messageGroupAdvanceService;

  public MessageContentAdvanceServiceImpl(MessageContentAdvanceRepository messageContentAdvanceRepository, MessageContentAdvanceMapper messageContentAdvanceMapper, MessageContentAdvanceSearch messageContentAdvanceSearch, MessageGroupAdvanceService messageGroupAdvanceService)
  {
    this.messageContentAdvanceRepository = messageContentAdvanceRepository;
    this.messageContentAdvanceMapper = messageContentAdvanceMapper;
    this.messageContentAdvanceSearch = messageContentAdvanceSearch;
    this.messageGroupAdvanceService = messageGroupAdvanceService;
  }


  @Override
  public Page<MessageContentDTO> getContentInGroup(String groupId, Pageable pageable)
  {

//    var messageGroup = messageGroupAdvanceService.getMessageGroupByGroupId(groupId);
//    var result = Objects.isNull(messageGroup) ? Page.empty() : messageContentAdvanceRepository.findAllByGroupIdAndCreatedDateGreaterThanEqualOrderByCreatedDateDesc(groupId, messageGroup.getCreatedDate(), pageable).map(messageContentAdvanceMapper::toDto);
//    var resultx = result.toList().stream()
//      .map(MessageContentDTO.class::cast)
//      .sorted(Comparator.comparing(MessageContentDTO::getCreatedDate))
//      .collect(Collectors.toList());
//    resultx.forEach(x -> System.out.println(x.getId()));
//    return new PageImpl<>(resultx);
    return null;
  }

  @Override
  public MessageContentDTO saveMessage(String content, String groupId)
  {
//    var messageGroup = messageGroupAdvanceService.getMessageGroupByGroupId(groupId);
//    if (Objects.nonNull(messageGroup))
//    {
//      var messageContent = new MessageContent();
//      EntityDefaultPropertiesServiceUtils.setPropertiesBeforeCreate(messageContent);
//      messageContent.groupId(messageGroup.getGroupId())
//        .content(content)
//        .sender(UserUtils.thisUser().getLogin())
//        .status("Đã gửi");
//      return messageContentAdvanceMapper.toDto(messageContentAdvanceRepository.save(messageContent));
//    }
    return null;

  }

}
