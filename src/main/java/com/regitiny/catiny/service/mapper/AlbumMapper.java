package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.AlbumDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Album} and its DTO {@link AlbumDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, ImageMapper.class })
@GeneratedByJHipster
public interface AlbumMapper extends EntityMapper<AlbumDTO, Album> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "images", source = "images", qualifiedByName = "idSet")
  AlbumDTO toDto(Album s);

  @Mapping(target = "removeImage", ignore = true)
  Album toEntity(AlbumDTO albumDTO);
}
