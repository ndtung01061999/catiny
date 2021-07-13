package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.TodoListAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.TodoListAdvanceSearch;
import com.regitiny.catiny.advance.service.TodoListAdvanceService;
import com.regitiny.catiny.advance.service.mapper.TodoListAdvanceMapper;
import com.regitiny.catiny.service.TodoListQueryService;
import com.regitiny.catiny.service.TodoListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class TodoListAdvanceServiceImpl extends LocalServiceImpl<TodoListService, TodoListQueryService> implements TodoListAdvanceService
{
  private final TodoListAdvanceRepository todoListAdvanceRepository;

  private final TodoListAdvanceSearch todoListAdvanceSearch;

  private final TodoListAdvanceMapper todoListAdvanceMapper;

  public TodoListAdvanceServiceImpl(TodoListAdvanceRepository todoListAdvanceRepository,
    TodoListAdvanceSearch todoListAdvanceSearch, TodoListAdvanceMapper todoListAdvanceMapper)
  {
    this.todoListAdvanceRepository = todoListAdvanceRepository;
    this.todoListAdvanceSearch = todoListAdvanceSearch;
    this.todoListAdvanceMapper = todoListAdvanceMapper;
  }
}
