package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.FriendDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Friend} and its DTO {@link FriendDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, MasterUserMapper.class })
@GeneratedByJHipster
public interface FriendMapper extends EntityMapper<FriendDTO, Friend> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "friendDetails", source = "friendDetails", qualifiedByName = "id")
  @Mapping(target = "masterUser", source = "masterUser", qualifiedByName = "id")
  FriendDTO toDto(Friend s);
}
