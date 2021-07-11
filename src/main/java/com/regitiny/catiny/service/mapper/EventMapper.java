package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.EventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Event} and its DTO {@link EventDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class })
@GeneratedByJHipster
public interface EventMapper extends EntityMapper<EventDTO, Event> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  EventDTO toDto(Event s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  EventDTO toDtoId(Event event);
}
