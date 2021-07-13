package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.PageProfileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PageProfile} and its DTO {@link PageProfileDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class })
@GeneratedByJHipster
public interface PageProfileMapper extends EntityMapper<PageProfileDTO, PageProfile> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  PageProfileDTO toDto(PageProfile s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  PageProfileDTO toDtoId(PageProfile pageProfile);
}
