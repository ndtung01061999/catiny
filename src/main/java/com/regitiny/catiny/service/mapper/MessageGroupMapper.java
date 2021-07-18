package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MessageGroup} and its DTO {@link MessageGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class })
@GeneratedByJHipster
public interface MessageGroupMapper extends EntityMapper<MessageGroupDTO, MessageGroup> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  MessageGroupDTO toDto(MessageGroup s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  MessageGroupDTO toDtoId(MessageGroup messageGroup);
}
