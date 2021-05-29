package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.MessageContentModel;
import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link MessageContent} and its DTO {@link MessageContentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MessageContentAdvanceMapper extends EntityAdvanceMapper<MessageContentModel, MessageContent> , EntityMapper<MessageContentDTO, MessageContent>
{}
