package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FriendModel;
import com.regitiny.catiny.domain.Friend;
import com.regitiny.catiny.service.dto.FriendDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface FriendAdvanceMapper extends EntityAdvanceMapper<FriendModel, FriendDTO>, EntityMapper<FriendDTO, Friend>
{
  FriendDTO requestToDto(FriendModel.Request request);


  List<FriendDTO> requestToDto(List<FriendModel.Request> request);


  FriendModel.Response dtoToResponse(FriendDTO dto);


  List<FriendModel.Response> dtoToResponse(List<FriendDTO> dto);
}
