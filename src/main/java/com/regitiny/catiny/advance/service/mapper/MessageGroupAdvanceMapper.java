package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.MessageGroupModel;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface MessageGroupAdvanceMapper extends EntityAdvanceMapper<MessageGroupModel, MessageGroupDTO>, EntityMapper<MessageGroupDTO, MessageGroup>
{
  MessageGroupDTO requestToDto(MessageGroupModel.Request request);


  List<MessageGroupDTO> requestToDto(List<MessageGroupModel.Request> request);


  MessageGroupModel.Response dtoToResponse(MessageGroupDTO dto);


  List<MessageGroupModel.Response> dtoToResponse(List<MessageGroupDTO> dto);
}
