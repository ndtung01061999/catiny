package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.MessageGroupAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.MessageGroupAdvanceSearch;
import com.regitiny.catiny.advance.service.MessageGroupAdvanceService;
import com.regitiny.catiny.advance.service.mapper.MessageGroupAdvanceMapper;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.repository.UserRepository;
import com.regitiny.catiny.service.MessageGroupQueryService;
import com.regitiny.catiny.service.MessageGroupService;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import com.regitiny.catiny.tools.utils.EntityDefaultPropertiesServiceUtils;
import com.regitiny.catiny.tools.utils.StringPool;
import com.regitiny.catiny.tools.utils.UserUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * Service Implementation for managing {@link MessageGroup}.
 */
@Log4j2
@Service
@Transactional
public class MessageGroupAdvanceServiceImpl extends LocalServiceImpl<MessageGroupService, MessageGroupQueryService> implements MessageGroupAdvanceService
{


  private final MessageGroupAdvanceRepository messageGroupAdvanceRepository;

  private final MessageGroupAdvanceMapper messageGroupAdvanceMapper;

  private final MessageGroupAdvanceSearch messageGroupAdvanceSearch;

  private final UserRepository userRepository;

  private final MessageGroupService messageGroupService;

  public MessageGroupAdvanceServiceImpl(MessageGroupAdvanceRepository messageGroupAdvanceRepository, MessageGroupAdvanceMapper messageGroupAdvanceMapper, MessageGroupAdvanceSearch messageGroupAdvanceSearch, UserRepository userRepository, MessageGroupService messageGroupService)
  {
    this.messageGroupAdvanceRepository = messageGroupAdvanceRepository;
    this.messageGroupAdvanceMapper = messageGroupAdvanceMapper;
    this.messageGroupAdvanceSearch = messageGroupAdvanceSearch;
    this.userRepository = userRepository;
    this.messageGroupService = messageGroupService;
  }


  @Override
  public List<MessageGroupDTO> createMessageGroup(String groupName, String lastContent, List<Long> userIds)
  {
//    log.debug(
//      "Request create message group . groupName : {} , lastContent  : {} , userIds.size() : {} ",
//      groupName,
//      lastContent,
//      userIds.size()
//    );
//    var thisUser = UserUtils.thisUser();
//    if (Objects.isNull(thisUser))
//    {
//      log.debug("user not exists");
//      return new ArrayList<>();
//    }
//
//    userIds = userIds.stream().filter(userId -> !userId.equals(thisUser.getId())).collect(toList());
//
//    var messageGroup = new MessageGroup();
//    EntityDefaultPropertiesServiceUtils.setPropertiesBeforeCreate(messageGroup);
//
//    String groupId;
//    if (userIds.size() == 1 && !thisUser.getId().equals(userIds.get(0)))
//      groupId = userRepository.findById(userIds.get(0))
//        .map(user ->
//        {
//          var sumLoginSorted = (user.getLogin().compareTo(thisUser.getLogin()) < 0)
//            ? user.getLogin() + StringPool.SPACE + thisUser.getLogin()
//            : thisUser.getLogin() + StringPool.SPACE + user.getLogin();
//          return DigestUtils.md5Hex(sumLoginSorted);
//        })
//        .orElse(null);
//    else if (userIds.size() == 1 && thisUser.getId().equals(userIds.get(0)))
//      groupId = DigestUtils.md5Hex(thisUser.getLogin());
//    else
//      groupId = UUID.randomUUID().toString();
//
//    messageGroup.groupId(groupId).userId(thisUser.getId()).groupName(groupName).lastContent(lastContent).addBy(thisUser.getLogin());
//    if (messageGroupAdvanceRepository.findByGroupIdAndUserId(groupId, thisUser.getId()).isEmpty())
//    {
//      var resultAfterSave = messageGroupAdvanceRepository.save(messageGroup);
//      resultAfterSave = messageGroupAdvanceSearch.save(resultAfterSave);
//      var result = addUserToGroup(userIds, resultAfterSave.getGroupId());
//      result.add(messageGroupAdvanceMapper.toDto(resultAfterSave));
//      return result;
//    }
//    log.debug("user has been added to the group");
    return new ArrayList<>();
  }

  @Override
  @Transactional
  public List<MessageGroupDTO> addUserToGroup(List<Long> userIds, final String groupId)
  {
//    log.debug("Request create message group . groupId : {} , userIds.size() : {} ", groupId, userIds.size());
//
//    var thisUser = UserUtils.thisUser();
//    if (Objects.isNull(thisUser))
//    {
//      log.debug("user not exists");
//      return new ArrayList<>();
//    }
//    if (userIds.isEmpty() || (userIds.size() == 1 && userIds.get(0).equals(thisUser.getId())))
//      return new ArrayList<>();
//    var resultList = new ArrayList<MessageGroupDTO>();
//    messageGroupAdvanceRepository.findByGroupIdAndUserId(groupId, thisUser.getId())
//      .ifPresentOrElse(messageGroupThisUser ->
//      {
//        var userIdsSet = new HashSet<>(userIds);
//        userIdsSet.stream()
//          .filter(userId -> !userId.equals(thisUser.getId()))
//          .filter(userId -> userRepository.findById(userId).isPresent())
//          .map(userId ->
//          {
//            var messageGroup = new MessageGroup();
//            EntityDefaultPropertiesServiceUtils.setPropertiesBeforeCreate(messageGroup);
//            messageGroup.groupId(messageGroupThisUser.getGroupId())
//              .addBy(thisUser.getLogin())
//              .userId(userId)
//              .groupName(messageGroupThisUser.getGroupName())
//              .lastContent(messageGroupThisUser.getLastContent());
//            return messageGroupAdvanceRepository.save(messageGroup);
//          })
//          .map(messageGroupAdvanceSearch::save)
//          .map(messageGroupAdvanceMapper::toDto)
//          .forEach(resultList::add);
//      }, () ->
//      {
//        log.debug("group not exists or you not in this group");
//      });
//    return resultList;
    return null;
  }

  @Override
  public Page<MessageGroupDTO> getAllGroupsJoined(Pageable pageable)
  {
    var thisUser = UserUtils.thisUser();
    return messageGroupAdvanceRepository.findAllByUserId(thisUser.getId(), pageable)
      .map(messageGroupAdvanceMapper::toDto);
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
    return messageGroupAdvanceRepository.findByGroupIdAndUserId(groupId, UserUtils.thisUser().getId()).orElse(null);
  }

  @Override
  public List<MessageGroupDTO> getAllUserIdInGroupByGroupId(String groupId)
  {
    return messageGroupAdvanceRepository.findAllByGroupId(groupId).map(messageGroupAdvanceMapper::toDto).orElse(new ArrayList<>());
  }


}
