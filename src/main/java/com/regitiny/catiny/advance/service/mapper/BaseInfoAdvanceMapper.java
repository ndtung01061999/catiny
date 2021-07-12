package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.BaseInfoModel;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface BaseInfoAdvanceMapper extends EntityAdvanceMapper<BaseInfoModel, BaseInfoDTO>, EntityMapper<BaseInfoDTO, BaseInfo>
{
  BaseInfoDTO requestToDto(BaseInfoModel.Request request);


  List<BaseInfoDTO> requestToDto(List<BaseInfoModel.Request> request);


  BaseInfoModel.Response dtoToResponse(BaseInfoDTO dto);


  List<BaseInfoModel.Response> dtoToResponse(List<BaseInfoDTO> dto);
}
