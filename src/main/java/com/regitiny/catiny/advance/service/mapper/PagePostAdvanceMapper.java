package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PagePostModel;
import com.regitiny.catiny.domain.PagePost;
import com.regitiny.catiny.service.dto.PagePostDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface PagePostAdvanceMapper extends EntityAdvanceMapper<PagePostModel, PagePostDTO>, EntityMapper<PagePostDTO, PagePost>
{
  PagePostDTO requestToDto(PagePostModel.Request request);


  List<PagePostDTO> requestToDto(List<PagePostModel.Request> request);


  PagePostModel.Response dtoToResponse(PagePostDTO dto);


  List<PagePostModel.Response> dtoToResponse(List<PagePostDTO> dto);
}
