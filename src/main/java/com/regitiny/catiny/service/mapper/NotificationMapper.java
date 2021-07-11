package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.NotificationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Notification} and its DTO {@link NotificationDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, MasterUserMapper.class })
@GeneratedByJHipster
public interface NotificationMapper extends EntityMapper<NotificationDTO, Notification> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "masterUser", source = "masterUser", qualifiedByName = "id")
  NotificationDTO toDto(Notification s);
}
