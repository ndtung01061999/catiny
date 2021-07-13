package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.UserProfileModel;
import com.regitiny.catiny.domain.UserProfile;
import com.regitiny.catiny.service.dto.UserProfileDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface UserProfileAdvanceMapper extends EntityAdvanceMapper<UserProfileModel, UserProfileDTO>, EntityMapper<UserProfileDTO, UserProfile>
{
  UserProfileDTO requestToDto(UserProfileModel.Request request);


  List<UserProfileDTO> requestToDto(List<UserProfileModel.Request> request);


  UserProfileModel.Response dtoToResponse(UserProfileDTO dto);


  List<UserProfileModel.Response> dtoToResponse(List<UserProfileDTO> dto);
}
