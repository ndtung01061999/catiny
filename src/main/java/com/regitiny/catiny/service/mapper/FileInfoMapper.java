package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.FileInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FileInfo} and its DTO {@link FileInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, MasterUserMapper.class })
@GeneratedByJHipster
public interface FileInfoMapper extends EntityMapper<FileInfoDTO, FileInfo> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "masterUser", source = "masterUser", qualifiedByName = "id")
  FileInfoDTO toDto(FileInfo s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  FileInfoDTO toDtoId(FileInfo fileInfo);
}
