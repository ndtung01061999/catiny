package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.controller.rest.MessageGroupManagement;
import com.regitiny.catiny.advance.repository.MessageGroupAdvanceRepository;
import com.regitiny.catiny.advance.service.MessageGroupAdvanceService;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import com.regitiny.catiny.web.rest.errors.BadRequestAlertException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * REST controller for managing {@link com.regitiny.catiny.domain.MessageGroup}.
 */
@Log4j2
@RestController
@RequestMapping("/api")
public class MessageGroupManagementImpl implements MessageGroupManagement
{

  private static final String ENTITY_NAME = "messageGroup";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final MessageGroupAdvanceService messageGroupAdvanceService;

  public MessageGroupManagementImpl(MessageGroupAdvanceService messageGroupAdvanceService, MessageGroupAdvanceRepository messageGroupAdvanceRepository)
  {
    this.messageGroupAdvanceService = messageGroupAdvanceService;
  }

  @Override
  public ResponseEntity<List<MessageGroupDTO>> createNewMessageGroup(
    @RequestParam("groupName") String groupName,
    @RequestParam("lastContent") String lastContent,
    @RequestParam("userIds") List<Long> userIds
  ) throws URISyntaxException {
    log.debug("REST request to save MessageGroup . groupName : {} , lastContent : {}", groupName, lastContent);
    if (userIds.isEmpty()) throw new BadRequestAlertException("create group but not add any user to the group , so what you want?", ENTITY_NAME, "idexists");
    var result = messageGroupAdvanceService.createMessageGroup(groupName, lastContent, userIds);
    if (Objects.isNull(result) || result.isEmpty()) throw new BadRequestAlertException("not create new messageGroup ", ENTITY_NAME, "err");
    return ResponseEntity.ok(result);
  }

  @Override
  public ResponseEntity<List<MessageGroupDTO>> addUserToGroup(@RequestParam List<Long> userIds, @PathVariable String groupId)
    throws URISyntaxException
  {
    log.debug("REST request to save userId : {} , groupId : {}", userIds, groupId);
    var result = messageGroupAdvanceService.addUserToGroup(userIds, groupId);
    if (result.size() == 0) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("si đa quá bạn ơi không có thằng user nào đc add vào");
    return ResponseEntity.ok(result);
  }

  @Override
  public ResponseEntity<List<MessageGroupDTO>> getAllGroupsJoined(Pageable pageable) throws URISyntaxException
  {
    log.debug("REST request get all groups user joined");
    var result = messageGroupAdvanceService.getAllGroupsJoined(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), result);
    return ResponseEntity.ok().headers(headers).body(result.getContent());
  }
}
