package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.VideoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Video} and its DTO {@link VideoDTO}.
 */
@Mapper(componentModel = "spring", uses = { FileInfoMapper.class, BaseInfoMapper.class })
@GeneratedByJHipster
public interface VideoMapper extends EntityMapper<VideoDTO, Video> {
  @Mapping(target = "fileInfo", source = "fileInfo", qualifiedByName = "id")
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "videoOriginal", source = "videoOriginal", qualifiedByName = "id")
  VideoDTO toDto(Video s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  VideoDTO toDtoId(Video video);
}
