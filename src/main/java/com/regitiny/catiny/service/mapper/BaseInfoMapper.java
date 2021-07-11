package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BaseInfo} and its DTO {@link BaseInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
@GeneratedByJHipster
public interface BaseInfoMapper extends EntityMapper<BaseInfoDTO, BaseInfo> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  BaseInfoDTO toDtoId(BaseInfo baseInfo);
}
