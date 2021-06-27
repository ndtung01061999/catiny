package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MessageGroup} and its DTO {@link MessageGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
@GeneratedByJHipster
public interface MessageGroupMapper extends EntityMapper<MessageGroupDTO, MessageGroup> {}
