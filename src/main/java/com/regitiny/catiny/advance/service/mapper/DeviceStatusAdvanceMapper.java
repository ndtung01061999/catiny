package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.DeviceStatusModel;
import com.regitiny.catiny.domain.DeviceStatus;
import com.regitiny.catiny.service.dto.DeviceStatusDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface DeviceStatusAdvanceMapper extends EntityAdvanceMapper<DeviceStatusModel, DeviceStatusDTO>, EntityMapper<DeviceStatusDTO, DeviceStatus>
{
  DeviceStatusDTO requestToDto(DeviceStatusModel.Request request);


  List<DeviceStatusDTO> requestToDto(List<DeviceStatusModel.Request> request);


  DeviceStatusModel.Response dtoToResponse(DeviceStatusDTO dto);


  List<DeviceStatusModel.Response> dtoToResponse(List<DeviceStatusDTO> dto);
}
