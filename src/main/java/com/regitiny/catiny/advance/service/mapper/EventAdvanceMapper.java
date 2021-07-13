package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.EventModel;
import com.regitiny.catiny.domain.Event;
import com.regitiny.catiny.service.dto.EventDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface EventAdvanceMapper extends EntityAdvanceMapper<EventModel, EventDTO>, EntityMapper<EventDTO, Event>
{
  EventDTO requestToDto(EventModel.Request request);


  List<EventDTO> requestToDto(List<EventModel.Request> request);


  EventModel.Response dtoToResponse(EventDTO dto);


  List<EventModel.Response> dtoToResponse(List<EventDTO> dto);
}
