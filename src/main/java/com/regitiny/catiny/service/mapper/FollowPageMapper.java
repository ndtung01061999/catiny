package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.FollowPageDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FollowPage} and its DTO {@link FollowPageDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, PagePostMapper.class, MasterUserMapper.class })
@GeneratedByJHipster
public interface FollowPageMapper extends EntityMapper<FollowPageDTO, FollowPage> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "followPageDetails", source = "followPageDetails", qualifiedByName = "id")
  @Mapping(target = "masterUser", source = "masterUser", qualifiedByName = "id")
  FollowPageDTO toDto(FollowPage s);
}
