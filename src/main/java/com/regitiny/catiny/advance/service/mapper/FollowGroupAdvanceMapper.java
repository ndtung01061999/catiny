package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FollowGroupModel;
import com.regitiny.catiny.domain.FollowGroup;
import com.regitiny.catiny.service.dto.FollowGroupDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface FollowGroupAdvanceMapper extends EntityAdvanceMapper<FollowGroupModel, FollowGroupDTO>, EntityMapper<FollowGroupDTO, FollowGroup>
{
  FollowGroupDTO requestToDto(FollowGroupModel.Request request);


  List<FollowGroupDTO> requestToDto(List<FollowGroupModel.Request> request);


  FollowGroupModel.Response dtoToResponse(FollowGroupDTO dto);


  List<FollowGroupModel.Response> dtoToResponse(List<FollowGroupDTO> dto);
}
