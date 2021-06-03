package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.MessageGroupModel;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity {@link MessageGroup} and its DTO {@link MessageGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MessageGroupAdvanceMapper extends EntityAdvanceMapper<MessageGroupModel, MessageGroupDTO>, EntityMapper<MessageGroupDTO, MessageGroup>
{
  MessageGroupModel.OutputModel dto2OutputModel(MessageGroupDTO messageGroupDTO);


  List<MessageGroupModel.OutputModel> dto2OutputModel(List<MessageGroupDTO> messageGroupDTO);


  MessageGroupDTO inputModel2DTO(MessageGroupModel.InputModel inputModel);


  List<MessageGroupDTO> inputModel2DTO(List<MessageGroupModel.InputModel> inputModel);
}
