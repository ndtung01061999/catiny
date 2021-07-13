package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.MasterUserModel;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface MasterUserAdvanceMapper extends EntityAdvanceMapper<MasterUserModel, MasterUserDTO>, EntityMapper<MasterUserDTO, MasterUser>
{
  MasterUserDTO requestToDto(MasterUserModel.Request request);


  List<MasterUserDTO> requestToDto(List<MasterUserModel.Request> request);


  MasterUserModel.Response dtoToResponse(MasterUserDTO dto);


  List<MasterUserModel.Response> dtoToResponse(List<MasterUserDTO> dto);
}
