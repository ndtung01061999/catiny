package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.NotificationModel;
import com.regitiny.catiny.domain.Notification;
import com.regitiny.catiny.service.dto.NotificationDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface NotificationAdvanceMapper extends EntityAdvanceMapper<NotificationModel, NotificationDTO>, EntityMapper<NotificationDTO, Notification>
{
  NotificationDTO requestToDto(NotificationModel.Request request);


  List<NotificationDTO> requestToDto(List<NotificationModel.Request> request);


  NotificationModel.Response dtoToResponse(NotificationDTO dto);


  List<NotificationModel.Response> dtoToResponse(List<NotificationDTO> dto);
}
