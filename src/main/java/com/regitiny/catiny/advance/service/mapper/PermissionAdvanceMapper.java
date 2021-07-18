package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PermissionModel;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.service.dto.PermissionDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface PermissionAdvanceMapper extends EntityAdvanceMapper<PermissionModel, PermissionDTO>, EntityMapper<PermissionDTO, Permission>
{
  PermissionDTO requestToDto(PermissionModel.Request request);


  List<PermissionDTO> requestToDto(List<PermissionModel.Request> request);


  PermissionModel.Response dtoToResponse(PermissionDTO dto);


  List<PermissionModel.Response> dtoToResponse(List<PermissionDTO> dto);
}
