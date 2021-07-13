package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.PostLikeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PostLike} and its DTO {@link PostLikeDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, MasterUserMapper.class, PostMapper.class })
@GeneratedByJHipster
public interface PostLikeMapper extends EntityMapper<PostLikeDTO, PostLike> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "userLike", source = "userLike", qualifiedByName = "id")
  @Mapping(target = "post", source = "post", qualifiedByName = "id")
  PostLikeDTO toDto(PostLike s);
}
