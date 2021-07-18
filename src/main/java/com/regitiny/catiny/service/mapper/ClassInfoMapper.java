package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.ClassInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClassInfo} and its DTO {@link ClassInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
@GeneratedByJHipster
public interface ClassInfoMapper extends EntityMapper<ClassInfoDTO, ClassInfo> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  ClassInfoDTO toDtoId(ClassInfo classInfo);
}
