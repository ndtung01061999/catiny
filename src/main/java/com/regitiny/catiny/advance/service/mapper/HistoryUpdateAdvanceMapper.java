package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.HistoryUpdateModel;
import com.regitiny.catiny.domain.HistoryUpdate;
import com.regitiny.catiny.service.dto.HistoryUpdateDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface HistoryUpdateAdvanceMapper extends EntityAdvanceMapper<HistoryUpdateModel, HistoryUpdateDTO>, EntityMapper<HistoryUpdateDTO, HistoryUpdate>
{
  HistoryUpdateDTO requestToDto(HistoryUpdateModel.Request request);


  List<HistoryUpdateDTO> requestToDto(List<HistoryUpdateModel.Request> request);


  HistoryUpdateModel.Response dtoToResponse(HistoryUpdateDTO dto);


  List<HistoryUpdateModel.Response> dtoToResponse(List<HistoryUpdateDTO> dto);
}
