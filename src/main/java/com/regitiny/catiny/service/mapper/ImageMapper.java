package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.ImageDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Image} and its DTO {@link ImageDTO}.
 */
@Mapper(componentModel = "spring", uses = { FileInfoMapper.class, BaseInfoMapper.class, EventMapper.class })
@GeneratedByJHipster
public interface ImageMapper extends EntityMapper<ImageDTO, Image> {
  @Mapping(target = "fileInfo", source = "fileInfo", qualifiedByName = "id")
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "imageOriginal", source = "imageOriginal", qualifiedByName = "id")
  @Mapping(target = "event", source = "event", qualifiedByName = "id")
  ImageDTO toDto(Image s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  ImageDTO toDtoId(Image image);

  @Named("idSet")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  Set<ImageDTO> toDtoIdSet(Set<Image> image);
}
