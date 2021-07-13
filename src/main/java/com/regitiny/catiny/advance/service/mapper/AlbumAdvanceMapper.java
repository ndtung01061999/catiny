package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.AlbumModel;
import com.regitiny.catiny.domain.Album;
import com.regitiny.catiny.service.dto.AlbumDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface AlbumAdvanceMapper extends EntityAdvanceMapper<AlbumModel, AlbumDTO>, EntityMapper<AlbumDTO, Album>
{
  AlbumDTO requestToDto(AlbumModel.Request request);


  List<AlbumDTO> requestToDto(List<AlbumModel.Request> request);


  AlbumModel.Response dtoToResponse(AlbumDTO dto);


  List<AlbumModel.Response> dtoToResponse(List<AlbumDTO> dto);
}
