package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MessageContent} and its DTO {@link MessageContentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
@GeneratedByJHipster
public interface MessageContentMapper extends EntityMapper<MessageContentDTO, MessageContent> {}
