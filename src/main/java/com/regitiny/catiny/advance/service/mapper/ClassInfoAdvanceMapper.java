package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.ClassInfoModel;
import com.regitiny.catiny.domain.ClassInfo;
import com.regitiny.catiny.service.dto.ClassInfoDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface ClassInfoAdvanceMapper extends EntityAdvanceMapper<ClassInfoModel, ClassInfoDTO>, EntityMapper<ClassInfoDTO, ClassInfo>
{
  ClassInfoDTO requestToDto(ClassInfoModel.Request request);


  List<ClassInfoDTO> requestToDto(List<ClassInfoModel.Request> request);


  ClassInfoModel.Response dtoToResponse(ClassInfoDTO dto);


  List<ClassInfoModel.Response> dtoToResponse(List<ClassInfoDTO> dto);
}
