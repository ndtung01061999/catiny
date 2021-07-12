package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PageProfileModel;
import com.regitiny.catiny.domain.PageProfile;
import com.regitiny.catiny.service.dto.PageProfileDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface PageProfileAdvanceMapper extends EntityAdvanceMapper<PageProfileModel, PageProfileDTO>, EntityMapper<PageProfileDTO, PageProfile>
{
  PageProfileDTO requestToDto(PageProfileModel.Request request);


  List<PageProfileDTO> requestToDto(List<PageProfileModel.Request> request);


  PageProfileModel.Response dtoToResponse(PageProfileDTO dto);


  List<PageProfileModel.Response> dtoToResponse(List<PageProfileDTO> dto);
}
