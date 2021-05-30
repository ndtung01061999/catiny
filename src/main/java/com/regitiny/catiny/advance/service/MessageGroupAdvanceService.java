package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.service.MessageGroupQueryService;
import com.regitiny.catiny.service.MessageGroupService;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing {@link MessageGroup}.
 */
public interface MessageGroupAdvanceService extends LocalService<MessageGroupService, MessageGroupQueryService>
{
  // custom by yuvytung
  List<MessageGroupDTO> createMessageGroup(String groupName, String lastContent, List<Long> userIds);


  List<MessageGroupDTO> addUserToGroup(List<Long> userIds, String groupId);


  Page<MessageGroupDTO> getAllGroupsJoined(Pageable pageable);


  MessageGroup getMessageGroupByGroupId(String groupId);


  List<MessageGroupDTO> getAllUserIdInGroupByGroupId(String groupId);
}
