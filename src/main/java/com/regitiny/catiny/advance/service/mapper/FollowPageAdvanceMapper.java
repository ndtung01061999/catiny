package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FollowPageModel;
import com.regitiny.catiny.domain.FollowPage;
import com.regitiny.catiny.service.dto.FollowPageDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface FollowPageAdvanceMapper extends EntityAdvanceMapper<FollowPageModel, FollowPageDTO>, EntityMapper<FollowPageDTO, FollowPage>
{
  FollowPageDTO requestToDto(FollowPageModel.Request request);


  List<FollowPageDTO> requestToDto(List<FollowPageModel.Request> request);


  FollowPageModel.Response dtoToResponse(FollowPageDTO dto);


  List<FollowPageModel.Response> dtoToResponse(List<FollowPageDTO> dto);
}
