package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.RankGroupDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RankGroup} and its DTO {@link RankGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class })
@GeneratedByJHipster
public interface RankGroupMapper extends EntityMapper<RankGroupDTO, RankGroup> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  RankGroupDTO toDto(RankGroup s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  RankGroupDTO toDtoId(RankGroup rankGroup);
}
