package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.RankUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RankUser} and its DTO {@link RankUserDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, RankGroupMapper.class })
@GeneratedByJHipster
public interface RankUserMapper extends EntityMapper<RankUserDTO, RankUser> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "rankGroup", source = "rankGroup", qualifiedByName = "id")
  RankUserDTO toDto(RankUser s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  RankUserDTO toDtoId(RankUser rankUser);
}
