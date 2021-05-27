package com.regitiny.catiny.service.impl;

import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.repository.MessageContentRepository;
import com.regitiny.catiny.repository.search.MessageContentSearchRepository;
import com.regitiny.catiny.service.MessageContentService;
import com.regitiny.catiny.service.MessageGroupService;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.service.mapper.MessageContentMapper;
import com.regitiny.catiny.tools.utils.EntityDefaultPropertiesServiceUtils;
import com.regitiny.catiny.tools.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing {@link MessageContent}.
 */
@Service
@Transactional
public class MessageContentServiceImpl implements MessageContentService {

  private final Logger log = LoggerFactory.getLogger(MessageContentServiceImpl.class);

  private final MessageContentRepository messageContentRepository;
  private final MessageContentMapper messageContentMapper;
  private final MessageContentSearchRepository messageContentSearchRepository;
  private final MessageGroupService messageGroupService;

  public MessageContentServiceImpl(
    MessageContentRepository messageContentRepository,
    MessageContentMapper messageContentMapper,
    MessageContentSearchRepository messageContentSearchRepository,
    MessageGroupService messageGroupService)
  {
    this.messageContentRepository = messageContentRepository;
    this.messageContentMapper = messageContentMapper;
    this.messageContentSearchRepository = messageContentSearchRepository;
    this.messageGroupService = messageGroupService;
  }

  @Override
  public MessageContentDTO save(MessageContentDTO messageContentDTO) {
    log.debug("Request to save MessageContent : {}", messageContentDTO);
    MessageContent messageContent = messageContentMapper.toEntity(messageContentDTO);
    messageContent = messageContentRepository.save(messageContent);
    MessageContentDTO result = messageContentMapper.toDto(messageContent);
    messageContentSearchRepository.save(messageContent);
    return result;
  }

  @Override
  public Optional<MessageContentDTO> partialUpdate(MessageContentDTO messageContentDTO) {
    log.debug("Request to partially update MessageContent : {}", messageContentDTO);

    return messageContentRepository
      .findById(messageContentDTO.getId())
      .map(
        existingMessageContent -> {
          messageContentMapper.partialUpdate(existingMessageContent, messageContentDTO);
          return existingMessageContent;
        }
      )
      .map(messageContentRepository::save)
      .map(
        savedMessageContent -> {
          messageContentSearchRepository.save(savedMessageContent);

          return savedMessageContent;
        }
      )
      .map(messageContentMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<MessageContentDTO> findAll(Pageable pageable) {
    log.debug("Request to get all MessageContents");
    return messageContentRepository.findAll(pageable).map(messageContentMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<MessageContentDTO> findOne(Long id) {
    log.debug("Request to get MessageContent : {}", id);
    return messageContentRepository.findById(id).map(messageContentMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete MessageContent : {}", id);
    messageContentRepository.deleteById(id);
    messageContentSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<MessageContentDTO> search(String query, Pageable pageable)
  {
    log.debug("Request to search for a page of MessageContents for query {}", query);
    return messageContentSearchRepository.search(queryStringQuery(query), pageable).map(messageContentMapper::toDto);
  }

  @Override
  public Page<MessageContentDTO> getContentInGroup(String groupId, Pageable pageable)
  {

    var messageGroup = messageGroupService.getMessageGroupByGroupId(groupId);
    var result = Objects.isNull(messageGroup) ? Page.empty() : messageContentRepository.findAllByGroupIdAndCreatedDateGreaterThanEqualOrderByCreatedDateDesc(groupId, messageGroup.getCreatedDate(), pageable).map(messageContentMapper::toDto);
    var resultx = result.toList().stream()
      .map(o -> (MessageContentDTO) o)
      .sorted(Comparator.comparing(MessageContentDTO::getCreatedDate))
      .collect(Collectors.toList());
    resultx.forEach(x -> System.out.println(x.getId()));
    return new PageImpl<>(resultx);
  }

  @Override
  public MessageContentDTO saveMessage(String content, String groupId)
  {
    var messageGroup = messageGroupService.getMessageGroupByGroupId(groupId);
    if (Objects.nonNull(messageGroup))
    {
      var messageContent = new MessageContent();
      EntityDefaultPropertiesServiceUtils.setPropertiesBeforeCreate(messageContent);
      messageContent.groupId(messageGroup.getGroupId())
        .content(content)
        .sender(UserUtils.thisUser().getLogin())
        .status("Đã gửi");
      return messageContentMapper.toDto(messageContentRepository.save(messageContent));
    }
    return null;

  }
}
