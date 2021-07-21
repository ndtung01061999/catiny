package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BaseInfo} and its DTO {@link BaseInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = { ClassInfoMapper.class, MasterUserMapper.class })
@GeneratedByJHipster
public interface BaseInfoMapper extends EntityMapper<BaseInfoDTO, BaseInfo> {
  @Mapping(target = "classInfo", source = "classInfo", qualifiedByName = "id")
  @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "id")
  @Mapping(target = "modifiedBy", source = "modifiedBy", qualifiedByName = "id")
  @Mapping(target = "owner", source = "owner", qualifiedByName = "id")
  BaseInfoDTO toDto(BaseInfo s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  BaseInfoDTO toDtoId(BaseInfo baseInfo);
}
