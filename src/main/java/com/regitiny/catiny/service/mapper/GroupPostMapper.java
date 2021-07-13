package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.GroupPostDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GroupPost} and its DTO {@link GroupPostDTO}.
 */
@Mapper(componentModel = "spring", uses = { GroupProfileMapper.class, BaseInfoMapper.class })
@GeneratedByJHipster
public interface GroupPostMapper extends EntityMapper<GroupPostDTO, GroupPost> {
  @Mapping(target = "profile", source = "profile", qualifiedByName = "id")
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  GroupPostDTO toDto(GroupPost s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  GroupPostDTO toDtoId(GroupPost groupPost);

  @Named("idSet")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  Set<GroupPostDTO> toDtoIdSet(Set<GroupPost> groupPost);
}
