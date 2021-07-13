package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.GroupPostModel;
import com.regitiny.catiny.domain.GroupPost;
import com.regitiny.catiny.service.dto.GroupPostDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface GroupPostAdvanceMapper extends EntityAdvanceMapper<GroupPostModel, GroupPostDTO>, EntityMapper<GroupPostDTO, GroupPost>
{
  GroupPostDTO requestToDto(GroupPostModel.Request request);


  List<GroupPostDTO> requestToDto(List<GroupPostModel.Request> request);


  GroupPostModel.Response dtoToResponse(GroupPostDTO dto);


  List<GroupPostModel.Response> dtoToResponse(List<GroupPostDTO> dto);
}
