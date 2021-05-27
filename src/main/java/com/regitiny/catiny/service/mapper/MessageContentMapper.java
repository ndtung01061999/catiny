package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link MessageContent} and its DTO {@link MessageContentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MessageContentMapper extends EntityMapper<MessageContentDTO, MessageContent> {}
