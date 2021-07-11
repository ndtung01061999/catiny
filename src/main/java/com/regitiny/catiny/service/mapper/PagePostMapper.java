package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.PagePostDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PagePost} and its DTO {@link PagePostDTO}.
 */
@Mapper(componentModel = "spring", uses = { PageProfileMapper.class, BaseInfoMapper.class, MasterUserMapper.class })
@GeneratedByJHipster
public interface PagePostMapper extends EntityMapper<PagePostDTO, PagePost> {
  @Mapping(target = "profile", source = "profile", qualifiedByName = "id")
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "masterUser", source = "masterUser", qualifiedByName = "id")
  PagePostDTO toDto(PagePost s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  PagePostDTO toDtoId(PagePost pagePost);

  @Named("idSet")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  Set<PagePostDTO> toDtoIdSet(Set<PagePost> pagePost);
}
