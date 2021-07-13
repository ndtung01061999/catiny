package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.NewsFeedModel;
import com.regitiny.catiny.domain.NewsFeed;
import com.regitiny.catiny.service.dto.NewsFeedDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface NewsFeedAdvanceMapper extends EntityAdvanceMapper<NewsFeedModel, NewsFeedDTO>, EntityMapper<NewsFeedDTO, NewsFeed>
{
  NewsFeedDTO requestToDto(NewsFeedModel.Request request);


  List<NewsFeedDTO> requestToDto(List<NewsFeedModel.Request> request);


  NewsFeedModel.Response dtoToResponse(NewsFeedDTO dto);


  List<NewsFeedModel.Response> dtoToResponse(List<NewsFeedDTO> dto);
}
