package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.GroupProfileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GroupProfile} and its DTO {@link GroupProfileDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class })
@GeneratedByJHipster
public interface GroupProfileMapper extends EntityMapper<GroupProfileDTO, GroupProfile> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  GroupProfileDTO toDto(GroupProfile s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  GroupProfileDTO toDtoId(GroupProfile groupProfile);
}
