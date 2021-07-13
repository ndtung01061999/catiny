package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.VideoLiveStreamBufferDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VideoLiveStreamBuffer} and its DTO {@link VideoLiveStreamBufferDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, VideoStreamMapper.class })
@GeneratedByJHipster
public interface VideoLiveStreamBufferMapper extends EntityMapper<VideoLiveStreamBufferDTO, VideoLiveStreamBuffer> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "videoStream", source = "videoStream", qualifiedByName = "id")
  VideoLiveStreamBufferDTO toDto(VideoLiveStreamBuffer s);
}
