package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FileInfoModel;
import com.regitiny.catiny.domain.FileInfo;
import com.regitiny.catiny.service.dto.FileInfoDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface FileInfoAdvanceMapper extends EntityAdvanceMapper<FileInfoModel, FileInfoDTO>, EntityMapper<FileInfoDTO, FileInfo>
{
  FileInfoDTO requestToDto(FileInfoModel.Request request);


  List<FileInfoDTO> requestToDto(List<FileInfoModel.Request> request);


  FileInfoModel.Response dtoToResponse(FileInfoDTO dto);


  List<FileInfoModel.Response> dtoToResponse(List<FileInfoDTO> dto);
}
