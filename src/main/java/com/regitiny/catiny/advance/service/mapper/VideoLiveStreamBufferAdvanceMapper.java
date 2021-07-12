package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.VideoLiveStreamBufferModel;
import com.regitiny.catiny.domain.VideoLiveStreamBuffer;
import com.regitiny.catiny.service.dto.VideoLiveStreamBufferDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface VideoLiveStreamBufferAdvanceMapper extends EntityAdvanceMapper<VideoLiveStreamBufferModel, VideoLiveStreamBufferDTO>, EntityMapper<VideoLiveStreamBufferDTO, VideoLiveStreamBuffer>
{
  VideoLiveStreamBufferDTO requestToDto(VideoLiveStreamBufferModel.Request request);


  List<VideoLiveStreamBufferDTO> requestToDto(List<VideoLiveStreamBufferModel.Request> request);


  VideoLiveStreamBufferModel.Response dtoToResponse(VideoLiveStreamBufferDTO dto);


  List<VideoLiveStreamBufferModel.Response> dtoToResponse(List<VideoLiveStreamBufferDTO> dto);
}
