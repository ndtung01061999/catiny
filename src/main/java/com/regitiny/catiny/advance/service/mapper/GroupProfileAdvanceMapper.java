package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.GroupProfileModel;
import com.regitiny.catiny.domain.GroupProfile;
import com.regitiny.catiny.service.dto.GroupProfileDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface GroupProfileAdvanceMapper extends EntityAdvanceMapper<GroupProfileModel, GroupProfileDTO>, EntityMapper<GroupProfileDTO, GroupProfile>
{
  GroupProfileDTO requestToDto(GroupProfileModel.Request request);


  List<GroupProfileDTO> requestToDto(List<GroupProfileModel.Request> request);


  GroupProfileModel.Response dtoToResponse(GroupProfileDTO dto);


  List<GroupProfileModel.Response> dtoToResponse(List<GroupProfileDTO> dto);
}
