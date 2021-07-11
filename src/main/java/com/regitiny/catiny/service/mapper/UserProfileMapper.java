package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.UserProfileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserProfile} and its DTO {@link UserProfileDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class })
@GeneratedByJHipster
public interface UserProfileMapper extends EntityMapper<UserProfileDTO, UserProfile> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  UserProfileDTO toDto(UserProfile s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  UserProfileDTO toDtoId(UserProfile userProfile);
}
