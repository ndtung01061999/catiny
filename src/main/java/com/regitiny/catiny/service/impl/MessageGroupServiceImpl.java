package com.regitiny.catiny.service.impl;

import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.repository.MessageGroupRepository;
import com.regitiny.catiny.repository.UserRepository;
import com.regitiny.catiny.repository.search.MessageGroupSearchRepository;
import com.regitiny.catiny.service.MessageGroupService;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import com.regitiny.catiny.service.mapper.MessageGroupMapper;
import com.regitiny.catiny.tools.exception.constant.StringPool;
import com.regitiny.catiny.tools.utils.EntityDefaultPropertiesServiceUtils;
import com.regitiny.catiny.tools.utils.UserUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing {@link MessageGroup}.
 */
@Service
@Transactional
public class MessageGroupServiceImpl implements MessageGroupService
{

  private final Logger log = LoggerFactory.getLogger(MessageGroupServiceImpl.class);

  private final MessageGroupRepository messageGroupRepository;

  private final MessageGroupMapper messageGroupMapper;

  private final MessageGroupSearchRepository messageGroupSearchRepository;

  private final UserRepository userRepository;

  public MessageGroupServiceImpl(
    MessageGroupRepository messageGroupRepository,
    MessageGroupMapper messageGroupMapper,
    MessageGroupSearchRepository messageGroupSearchRepository,
    UserRepository userRepository
  )
  {
    this.messageGroupRepository = messageGroupRepository;
    this.messageGroupMapper = messageGroupMapper;
    this.messageGroupSearchRepository = messageGroupSearchRepository;
    this.userRepository = userRepository;
  }

  @Override
  public MessageGroupDTO save(MessageGroupDTO messageGroupDTO)
  {
    log.debug("Request to save MessageGroup : {}", messageGroupDTO);
    MessageGroup messageGroup = messageGroupMapper.toEntity(messageGroupDTO);
    messageGroup = messageGroupRepository.save(messageGroup);
    MessageGroupDTO result = messageGroupMapper.toDto(messageGroup);
    messageGroupSearchRepository.save(messageGroup);
    return result;
  }

  @Override
  public Optional<MessageGroupDTO> partialUpdate(MessageGroupDTO messageGroupDTO)
  {
    log.debug("Request to partially update MessageGroup : {}", messageGroupDTO);

    return messageGroupRepository
      .findById(messageGroupDTO.getId())
      .map(
        existingMessageGroup ->
        {
          messageGroupMapper.partialUpdate(existingMessageGroup, messageGroupDTO);
          return existingMessageGroup;
        }
      )
      .map(messageGroupRepository::save)
      .map(
        savedMessageGroup ->
        {
          messageGroupSearchRepository.save(savedMessageGroup);

          return savedMessageGroup;
        }
      )
      .map(messageGroupMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<MessageGroupDTO> findAll(Pageable pageable)
  {
    log.debug("Request to get all MessageGroups");
    return messageGroupRepository.findAll(pageable).map(messageGroupMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<MessageGroupDTO> findOne(Long id)
  {
    log.debug("Request to get MessageGroup : {}", id);
    return messageGroupRepository.findById(id).map(messageGroupMapper::toDto);
  }

  @Override
  public void delete(Long id)
  {
    log.debug("Request to delete MessageGroup : {}", id);
    messageGroupRepository.deleteById(id);
    messageGroupSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<MessageGroupDTO> search(String query, Pageable pageable)
  {
    log.debug("Request to search for a page of MessageGroups for query {}", query);
    return messageGroupSearchRepository.search(queryStringQuery(query), pageable).map(messageGroupMapper::toDto);
  }

  @Override
  public List<MessageGroupDTO> createMessageGroup(String groupName, String lastContent, List<Long> userIds)
  {
    log.debug(
      "Request create message group . groupName : {} , lastContent  : {} , userIds.size() : {} ",
      groupName,
      lastContent,
      userIds.size()
    );
    var thisUser = UserUtils.thisUser();
    if (Objects.isNull(thisUser))
    {
      log.debug("user not exists");
      return new ArrayList<>();
    }

    userIds = userIds.stream().filter(userId -> !userId.equals(thisUser.getId())).collect(toList());

    var messageGroup = new MessageGroup();
    EntityDefaultPropertiesServiceUtils.setPropertiesBeforeCreate(messageGroup);

    String groupId;
    if (userIds.size() == 1 && !thisUser.getId().equals(userIds.get(0)))
      groupId = userRepository.findById(userIds.get(0))
        .map(user ->
        {
          var sumLoginSorted = (user.getLogin().compareTo(thisUser.getLogin()) < 0)
            ? user.getLogin() + StringPool.SPACE + thisUser.getLogin()
            : thisUser.getLogin() + StringPool.SPACE + user.getLogin();
          return DigestUtils.md5Hex(sumLoginSorted);
        })
        .orElse(null);
    else if (userIds.size() == 1 && thisUser.getId().equals(userIds.get(0)))
      groupId = DigestUtils.md5Hex(thisUser.getLogin());
    else
      groupId = UUID.randomUUID().toString();

    messageGroup.groupId(groupId).userId(thisUser.getId()).groupName(groupName).lastContent(lastContent).addBy(thisUser.getLogin());
    if (messageGroupRepository.findByGroupIdAndUserId(groupId, thisUser.getId()).isEmpty())
    {
      var resultAfterSave = messageGroupRepository.save(messageGroup);
      resultAfterSave = messageGroupSearchRepository.save(resultAfterSave);
      var result = addUserToGroup(userIds, resultAfterSave.getGroupId());
      result.add(messageGroupMapper.toDto(resultAfterSave));
      return result;
    }
    log.debug("user has been added to the group");
    return new ArrayList<>();
  }

  @Override
  @Transactional
  public List<MessageGroupDTO> addUserToGroup(List<Long> userIds, final String groupId)
  {
    log.debug("Request create message group . groupId : {} , userIds.size() : {} ", groupId, userIds.size());

    var thisUser = UserUtils.thisUser();
    if (Objects.isNull(thisUser))
    {
      log.debug("user not exists");
      return new ArrayList<>();
    }
    if (userIds.isEmpty() || (userIds.size() == 1 && userIds.get(0).equals(thisUser.getId())))
      return new ArrayList<>();
    var resultList = new ArrayList<MessageGroupDTO>();
    messageGroupRepository.findByGroupIdAndUserId(groupId, thisUser.getId())
      .ifPresentOrElse(messageGroupThisUser ->
      {
        var userIdsSet = new HashSet<>(userIds);
        userIdsSet.stream()
          .filter(userId -> !userId.equals(thisUser.getId()))
          .filter(userId -> userRepository.findById(userId).isPresent())
          .map(userId ->
          {
            var messageGroup = new MessageGroup();
            EntityDefaultPropertiesServiceUtils.setPropertiesBeforeCreate(messageGroup);
            messageGroup.groupId(messageGroupThisUser.getGroupId())
              .addBy(thisUser.getLogin())
              .userId(userId)
              .groupName(messageGroupThisUser.getGroupName())
              .lastContent(messageGroupThisUser.getLastContent());
            return messageGroupRepository.save(messageGroup);
          })
          .map(messageGroupSearchRepository::save)
          .map(messageGroupMapper::toDto)
          .forEach(resultList::add);
      }, () ->
      {
        log.debug("group not exists or you not in this group");
      });
    return resultList;
  }

  @Override
  public Page<MessageGroupDTO> getAllGroupsJoined(Pageable pageable)
  {
    var thisUser = UserUtils.thisUser();
    return messageGroupRepository.findAllByUserId(thisUser.getId(), pageable)
      .map(messageGroupMapper::toDto);
  }

  /**
   * used to check if the user has in this group.
   *
   * @param groupId id of the MessageGroup
   * @return MessageGroup
   */
  @Override
  public MessageGroup getMessageGroupByGroupId(String groupId)
  {
    return messageGroupRepository.findByGroupIdAndUserId(groupId, UserUtils.thisUser().getId()).orElse(null);
  }
}
