package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PostModel;
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.service.dto.PostDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface PostAdvanceMapper extends EntityAdvanceMapper<PostModel, PostDTO>, EntityMapper<PostDTO, Post>
{
  PostDTO requestToDto(PostModel.Request request);


  List<PostDTO> requestToDto(List<PostModel.Request> request);


  PostModel.Response dtoToResponse(PostDTO dto);


  List<PostModel.Response> dtoToResponse(List<PostDTO> dto);
}
