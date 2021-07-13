package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PostCommentModel;
import com.regitiny.catiny.domain.PostComment;
import com.regitiny.catiny.service.dto.PostCommentDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface PostCommentAdvanceMapper extends EntityAdvanceMapper<PostCommentModel, PostCommentDTO>, EntityMapper<PostCommentDTO, PostComment>
{
  PostCommentDTO requestToDto(PostCommentModel.Request request);


  List<PostCommentDTO> requestToDto(List<PostCommentModel.Request> request);


  PostCommentModel.Response dtoToResponse(PostCommentDTO dto);


  List<PostCommentModel.Response> dtoToResponse(List<PostCommentDTO> dto);
}
