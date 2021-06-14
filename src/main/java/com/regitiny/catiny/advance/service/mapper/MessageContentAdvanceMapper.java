package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.MessageContentModel;
import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface MessageContentAdvanceMapper extends EntityAdvanceMapper<MessageContentModel, MessageContentDTO>, EntityMapper<MessageContentDTO, MessageContent>
{
  MessageContentDTO requestToDto(MessageContentModel.Request request);


  List<MessageContentDTO> requestToDto(List<MessageContentModel.Request> request);


  MessageContentModel.Response dtoToResponse(MessageContentDTO dto);


  List<MessageContentModel.Response> dtoToResponse(List<MessageContentDTO> dto);
}
