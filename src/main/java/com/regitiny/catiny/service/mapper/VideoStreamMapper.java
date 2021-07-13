package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.VideoStreamDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VideoStream} and its DTO {@link VideoStreamDTO}.
 */
@Mapper(componentModel = "spring", uses = { VideoMapper.class, BaseInfoMapper.class })
@GeneratedByJHipster
public interface VideoStreamMapper extends EntityMapper<VideoStreamDTO, VideoStream> {
  @Mapping(target = "video", source = "video", qualifiedByName = "id")
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  VideoStreamDTO toDto(VideoStream s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  VideoStreamDTO toDtoId(VideoStream videoStream);
}
