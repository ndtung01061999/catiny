package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.HanhChinhVNModel;
import com.regitiny.catiny.domain.HanhChinhVN;
import com.regitiny.catiny.service.dto.HanhChinhVNDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface HanhChinhVNAdvanceMapper extends EntityAdvanceMapper<HanhChinhVNModel, HanhChinhVNDTO>, EntityMapper<HanhChinhVNDTO, HanhChinhVN>
{
  HanhChinhVNDTO requestToDto(HanhChinhVNModel.Request request);


  List<HanhChinhVNDTO> requestToDto(List<HanhChinhVNModel.Request> request);


  HanhChinhVNModel.Response dtoToResponse(HanhChinhVNDTO dto);


  List<HanhChinhVNModel.Response> dtoToResponse(List<HanhChinhVNDTO> dto);
}
