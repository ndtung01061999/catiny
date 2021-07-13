package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.PostDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Post} and its DTO {@link PostDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, GroupPostMapper.class, PagePostMapper.class, MasterUserMapper.class })
@GeneratedByJHipster
public interface PostMapper extends EntityMapper<PostDTO, Post> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "groupPost", source = "groupPost", qualifiedByName = "id")
  @Mapping(target = "pagePost", source = "pagePost", qualifiedByName = "id")
  @Mapping(target = "postShareParent", source = "postShareParent", qualifiedByName = "id")
  @Mapping(target = "poster", source = "poster", qualifiedByName = "id")
  PostDTO toDto(Post s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  PostDTO toDtoId(Post post);

  @Named("idSet")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  Set<PostDTO> toDtoIdSet(Set<Post> post);
}
