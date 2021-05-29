package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.controller.rest.MessageContentManagement;
import com.regitiny.catiny.advance.service.MessageContentAdvanceService;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing {@link com.regitiny.catiny.domain.MessageContent}.
 */
@Log4j2
@RestController
@RequestMapping("/api")
public class MessageContentManagementImpl implements MessageContentManagement
{

  private static final String ENTITY_NAME = "messageContent";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final MessageContentAdvanceService messageContentAdvanceService;

  public MessageContentManagementImpl(MessageContentAdvanceService messageContentAdvanceService)
  {
    this.messageContentAdvanceService = messageContentAdvanceService;
  }


  @Override
  @GetMapping("/message-contents/message-groups/{groupId}")
  public ResponseEntity<List<MessageContentDTO>> getContentInGroup(@PathVariable String groupId, Pageable pageable) throws URISyntaxException
  {
    log.debug("REST request get all groups user joined");
    var result = messageContentAdvanceService.getContentInGroup(groupId, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), result);
    return ResponseEntity.ok().headers(headers).body(result.getContent());
  }

}
