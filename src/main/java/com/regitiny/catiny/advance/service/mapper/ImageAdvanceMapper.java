package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.ImageModel;
import com.regitiny.catiny.domain.Image;
import com.regitiny.catiny.service.dto.ImageDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface ImageAdvanceMapper extends EntityAdvanceMapper<ImageModel, ImageDTO>, EntityMapper<ImageDTO, Image>
{
  ImageDTO requestToDto(ImageModel.Request request);


  List<ImageDTO> requestToDto(List<ImageModel.Request> request);


  ImageModel.Response dtoToResponse(ImageDTO dto);


  List<ImageModel.Response> dtoToResponse(List<ImageDTO> dto);
}
