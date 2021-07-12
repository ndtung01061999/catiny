package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.TodoListModel;
import com.regitiny.catiny.domain.TodoList;
import com.regitiny.catiny.service.dto.TodoListDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface TodoListAdvanceMapper extends EntityAdvanceMapper<TodoListModel, TodoListDTO>, EntityMapper<TodoListDTO, TodoList>
{
  TodoListDTO requestToDto(TodoListModel.Request request);


  List<TodoListDTO> requestToDto(List<TodoListModel.Request> request);


  TodoListModel.Response dtoToResponse(TodoListDTO dto);


  List<TodoListModel.Response> dtoToResponse(List<TodoListDTO> dto);
}
