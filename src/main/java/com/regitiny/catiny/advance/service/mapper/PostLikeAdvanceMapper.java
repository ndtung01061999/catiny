package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PostLikeModel;
import com.regitiny.catiny.domain.PostLike;
import com.regitiny.catiny.service.dto.PostLikeDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface PostLikeAdvanceMapper extends EntityAdvanceMapper<PostLikeModel, PostLikeDTO>, EntityMapper<PostLikeDTO, PostLike>
{
  PostLikeDTO requestToDto(PostLikeModel.Request request);


  List<PostLikeDTO> requestToDto(List<PostLikeModel.Request> request);


  PostLikeModel.Response dtoToResponse(PostLikeDTO dto);


  List<PostLikeModel.Response> dtoToResponse(List<PostLikeDTO> dto);
}
