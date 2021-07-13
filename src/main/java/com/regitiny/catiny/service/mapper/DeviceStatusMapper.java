package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.DeviceStatusDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeviceStatus} and its DTO {@link DeviceStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, AccountStatusMapper.class })
@GeneratedByJHipster
public interface DeviceStatusMapper extends EntityMapper<DeviceStatusDTO, DeviceStatus> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "accountStatus", source = "accountStatus", qualifiedByName = "id")
  DeviceStatusDTO toDto(DeviceStatus s);
}
