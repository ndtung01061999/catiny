package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.VideoModel;
import com.regitiny.catiny.domain.Video;
import com.regitiny.catiny.service.dto.VideoDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface VideoAdvanceMapper extends EntityAdvanceMapper<VideoModel, VideoDTO>, EntityMapper<VideoDTO, Video>
{
  VideoDTO requestToDto(VideoModel.Request request);


  List<VideoDTO> requestToDto(List<VideoModel.Request> request);


  VideoModel.Response dtoToResponse(VideoDTO dto);


  List<VideoModel.Response> dtoToResponse(List<VideoDTO> dto);
}
