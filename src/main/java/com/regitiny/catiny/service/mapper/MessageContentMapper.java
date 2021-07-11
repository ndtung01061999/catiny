package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MessageContent} and its DTO {@link MessageContentDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, MasterUserMapper.class, MessageGroupMapper.class })
@GeneratedByJHipster
public interface MessageContentMapper extends EntityMapper<MessageContentDTO, MessageContent> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "messageSender", source = "messageSender", qualifiedByName = "id")
  @Mapping(target = "messageGroup", source = "messageGroup", qualifiedByName = "id")
  MessageContentDTO toDto(MessageContent s);
}
