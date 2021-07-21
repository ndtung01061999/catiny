package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.NewsFeedDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NewsFeed} and its DTO {@link NewsFeedDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, PostMapper.class })
@GeneratedByJHipster
public interface NewsFeedMapper extends EntityMapper<NewsFeedDTO, NewsFeed> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "post", source = "post", qualifiedByName = "id")
  NewsFeedDTO toDto(NewsFeed s);
}
