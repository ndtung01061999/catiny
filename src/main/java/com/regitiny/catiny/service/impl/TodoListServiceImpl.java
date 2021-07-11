package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.TodoList;
import com.regitiny.catiny.repository.TodoListRepository;
import com.regitiny.catiny.repository.search.TodoListSearchRepository;
import com.regitiny.catiny.service.TodoListService;
import com.regitiny.catiny.service.dto.TodoListDTO;
import com.regitiny.catiny.service.mapper.TodoListMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TodoList}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class TodoListServiceImpl implements TodoListService {

  private final Logger log = LoggerFactory.getLogger(TodoListServiceImpl.class);

  private final TodoListRepository todoListRepository;

  private final TodoListMapper todoListMapper;

  private final TodoListSearchRepository todoListSearchRepository;

  public TodoListServiceImpl(
    TodoListRepository todoListRepository,
    TodoListMapper todoListMapper,
    TodoListSearchRepository todoListSearchRepository
  ) {
    this.todoListRepository = todoListRepository;
    this.todoListMapper = todoListMapper;
    this.todoListSearchRepository = todoListSearchRepository;
  }

  @Override
  public TodoListDTO save(TodoListDTO todoListDTO) {
    log.debug("Request to save TodoList : {}", todoListDTO);
    TodoList todoList = todoListMapper.toEntity(todoListDTO);
    todoList = todoListRepository.save(todoList);
    TodoListDTO result = todoListMapper.toDto(todoList);
    todoListSearchRepository.save(todoList);
    return result;
  }

  @Override
  public Optional<TodoListDTO> partialUpdate(TodoListDTO todoListDTO) {
    log.debug("Request to partially update TodoList : {}", todoListDTO);

    return todoListRepository
      .findById(todoListDTO.getId())
      .map(
        existingTodoList -> {
          todoListMapper.partialUpdate(existingTodoList, todoListDTO);

          return existingTodoList;
        }
      )
      .map(todoListRepository::save)
      .map(
        savedTodoList -> {
          todoListSearchRepository.save(savedTodoList);

          return savedTodoList;
        }
      )
      .map(todoListMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<TodoListDTO> findAll(Pageable pageable) {
    log.debug("Request to get all TodoLists");
    return todoListRepository.findAll(pageable).map(todoListMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<TodoListDTO> findOne(Long id) {
    log.debug("Request to get TodoList : {}", id);
    return todoListRepository.findById(id).map(todoListMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete TodoList : {}", id);
    todoListRepository.deleteById(id);
    todoListSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<TodoListDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of TodoLists for query {}", query);
    return todoListSearchRepository.search(queryStringQuery(query), pageable).map(todoListMapper::toDto);
  }
}
