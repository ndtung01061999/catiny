package com.regitiny.catiny.web.websocket;

import com.regitiny.catiny.domain.User;
import com.regitiny.catiny.service.MessageContentService;
import com.regitiny.catiny.service.MessageGroupService;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.tools.exception.constant.StringPool;
import com.regitiny.catiny.tools.utils.UserUtils;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;

@Log4j2
@Controller
public class MessageWS implements ApplicationListener<SessionDisconnectEvent>
{

  private final SimpMessageSendingOperations messagingTemplate;
  private final MessageContentService messageContentService;
  private final MessageGroupService messageGroupService;

  public MessageWS(SimpMessageSendingOperations messagingTemplate, MessageContentService messageContentService, MessageGroupService messageGroupService)
  {
    this.messagingTemplate = messagingTemplate;
    this.messageContentService = messageContentService;
    this.messageGroupService = messageGroupService;
  }

  @MessageMapping("/topic/message/send")
  public void receiveMessages(@Payload String body, StompHeaderAccessor stompHeaderAccessor, Principal principal)
  {
    var bodyJson = new JSONObject(body);
    var messageContent = bodyJson.has("content") ? bodyJson.getString("content") : StringPool.BLANK;
    var groupId = bodyJson.has("groupId") ? bodyJson.getString("groupId") : StringPool.BLANK;
    var result = messageContentService.saveMessage(messageContent, groupId);
    sendMessagesToUserInGroup(result);
  }

  private void sendMessagesToUserInGroup(MessageContentDTO messageContentDTO)
  {
    messageGroupService.getAllUserIdInGroupByGroupId(messageContentDTO.getGroupId()).stream()
      .map(messageGroupDTO -> UserUtils.getUserById(messageGroupDTO.getUserId()))
      .map(User::getLogin)
      .map(login -> "/topic/message/user/${user}/group/new-message".replace("${user}", login))
      .forEach(topic -> messagingTemplate.convertAndSend(topic, messageContentDTO));
  }

//  @SubscribeMapping("/topic/video-call/group2/*")
//  public Object s() {
//    log.debug("SecurityUtils.getCurrentUserLogin().get()");
//
//    return null;
//  }

  @Override
  public void onApplicationEvent(SessionDisconnectEvent event)
  {
//    log.debug(SecurityUtils.getCurrentUserLogin().get());
  }
}
