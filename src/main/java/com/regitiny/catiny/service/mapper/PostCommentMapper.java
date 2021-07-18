package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.PostCommentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PostComment} and its DTO {@link PostCommentDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, PostMapper.class })
@GeneratedByJHipster
public interface PostCommentMapper extends EntityMapper<PostCommentDTO, PostComment> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "post", source = "post", qualifiedByName = "id")
  @Mapping(target = "commentParent", source = "commentParent", qualifiedByName = "id")
  PostCommentDTO toDto(PostComment s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  PostCommentDTO toDtoId(PostComment postComment);
}
