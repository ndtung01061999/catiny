package com.regitiny.catiny.advance.controller.rest;

import com.regitiny.catiny.service.dto.MessageContentDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URISyntaxException;
import java.util.List;

public interface MessageContentManagement
{
  @GetMapping("/message-contents/message-groups/{groupId}")
  ResponseEntity<List<MessageContentDTO>> getContentInGroup(@PathVariable String groupId, Pageable pageable) throws URISyntaxException;
}
