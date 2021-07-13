package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.TodoListDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TodoList} and its DTO {@link TodoListDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, MasterUserMapper.class })
@GeneratedByJHipster
public interface TodoListMapper extends EntityMapper<TodoListDTO, TodoList> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  @Mapping(target = "masterUser", source = "masterUser", qualifiedByName = "id")
  TodoListDTO toDto(TodoList s);
}
