package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MasterUser} and its DTO {@link MasterUserDTO}.
 */
@Mapper(componentModel = "spring", uses = { UserMapper.class, RankUserMapper.class, BaseInfoMapper.class, TopicInterestMapper.class })
@GeneratedByJHipster
public interface MasterUserMapper extends EntityMapper<MasterUserDTO, MasterUser> {
  @Mapping(target = "user", source = "user", qualifiedByName = "id")
  @Mapping(target = "myRank", source = "myRank", qualifiedByName = "id")
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "topicInterests", source = "topicInterests", qualifiedByName = "idSet")
  MasterUserDTO toDto(MasterUser s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "user", source = "user")
  MasterUserDTO toDtoId(MasterUser masterUser);

  @Mapping(target = "removeTopicInterest", ignore = true)
  MasterUser toEntity(MasterUserDTO masterUserDTO);
}
