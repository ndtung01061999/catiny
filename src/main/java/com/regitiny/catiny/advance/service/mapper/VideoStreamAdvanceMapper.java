package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.VideoStreamModel;
import com.regitiny.catiny.domain.VideoStream;
import com.regitiny.catiny.service.dto.VideoStreamDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface VideoStreamAdvanceMapper extends EntityAdvanceMapper<VideoStreamModel, VideoStreamDTO>, EntityMapper<VideoStreamDTO, VideoStream>
{
  VideoStreamDTO requestToDto(VideoStreamModel.Request request);


  List<VideoStreamDTO> requestToDto(List<VideoStreamModel.Request> request);


  VideoStreamModel.Response dtoToResponse(VideoStreamDTO dto);


  List<VideoStreamModel.Response> dtoToResponse(List<VideoStreamDTO> dto);
}
