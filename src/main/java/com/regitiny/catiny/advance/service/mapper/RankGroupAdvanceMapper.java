package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.RankGroupModel;
import com.regitiny.catiny.domain.RankGroup;
import com.regitiny.catiny.service.dto.RankGroupDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface RankGroupAdvanceMapper extends EntityAdvanceMapper<RankGroupModel, RankGroupDTO>, EntityMapper<RankGroupDTO, RankGroup>
{
  RankGroupDTO requestToDto(RankGroupModel.Request request);


  List<RankGroupDTO> requestToDto(List<RankGroupModel.Request> request);


  RankGroupModel.Response dtoToResponse(RankGroupDTO dto);


  List<RankGroupModel.Response> dtoToResponse(List<RankGroupDTO> dto);
}
