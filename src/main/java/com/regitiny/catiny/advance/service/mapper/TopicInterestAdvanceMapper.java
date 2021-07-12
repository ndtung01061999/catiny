package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.TopicInterestModel;
import com.regitiny.catiny.domain.TopicInterest;
import com.regitiny.catiny.service.dto.TopicInterestDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface TopicInterestAdvanceMapper extends EntityAdvanceMapper<TopicInterestModel, TopicInterestDTO>, EntityMapper<TopicInterestDTO, TopicInterest>
{
  TopicInterestDTO requestToDto(TopicInterestModel.Request request);


  List<TopicInterestDTO> requestToDto(List<TopicInterestModel.Request> request);


  TopicInterestModel.Response dtoToResponse(TopicInterestDTO dto);


  List<TopicInterestModel.Response> dtoToResponse(List<TopicInterestDTO> dto);
}
