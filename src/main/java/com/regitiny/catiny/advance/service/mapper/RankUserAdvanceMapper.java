package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.RankUserModel;
import com.regitiny.catiny.domain.RankUser;
import com.regitiny.catiny.service.dto.RankUserDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface RankUserAdvanceMapper extends EntityAdvanceMapper<RankUserModel, RankUserDTO>, EntityMapper<RankUserDTO, RankUser>
{
  RankUserDTO requestToDto(RankUserModel.Request request);


  List<RankUserDTO> requestToDto(List<RankUserModel.Request> request);


  RankUserModel.Response dtoToResponse(RankUserDTO dto);


  List<RankUserModel.Response> dtoToResponse(List<RankUserDTO> dto);
}
