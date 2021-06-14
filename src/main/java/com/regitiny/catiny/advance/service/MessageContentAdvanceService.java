package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.service.MessageContentQueryService;
import com.regitiny.catiny.service.MessageContentService;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.MessageContent}.
 */
public interface MessageContentAdvanceService extends LocalService<MessageContentService, MessageContentQueryService>
{
  Page<MessageContentDTO> getContentInGroup(String groupId, Pageable pageable);


  MessageContentDTO saveMessage(String content, String groupId);
}
