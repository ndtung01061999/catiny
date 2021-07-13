package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FollowUserModel;
import com.regitiny.catiny.domain.FollowUser;
import com.regitiny.catiny.service.dto.FollowUserDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface FollowUserAdvanceMapper extends EntityAdvanceMapper<FollowUserModel, FollowUserDTO>, EntityMapper<FollowUserDTO, FollowUser>
{
  FollowUserDTO requestToDto(FollowUserModel.Request request);


  List<FollowUserDTO> requestToDto(List<FollowUserModel.Request> request);


  FollowUserModel.Response dtoToResponse(FollowUserDTO dto);


  List<FollowUserModel.Response> dtoToResponse(List<FollowUserDTO> dto);
}
