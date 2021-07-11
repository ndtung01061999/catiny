package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.TopicInterestDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TopicInterest} and its DTO {@link TopicInterestDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, PostMapper.class, PagePostMapper.class, GroupPostMapper.class })
@GeneratedByJHipster
public interface TopicInterestMapper extends EntityMapper<TopicInterestDTO, TopicInterest> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "posts", source = "posts", qualifiedByName = "idSet")
  @Mapping(target = "pagePosts", source = "pagePosts", qualifiedByName = "idSet")
  @Mapping(target = "groupPosts", source = "groupPosts", qualifiedByName = "idSet")
  TopicInterestDTO toDto(TopicInterest s);

  @Named("idSet")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  Set<TopicInterestDTO> toDtoIdSet(Set<TopicInterest> topicInterest);

  @Mapping(target = "removePost", ignore = true)
  @Mapping(target = "removePagePost", ignore = true)
  @Mapping(target = "removeGroupPost", ignore = true)
  TopicInterest toEntity(TopicInterestDTO topicInterestDTO);
}
