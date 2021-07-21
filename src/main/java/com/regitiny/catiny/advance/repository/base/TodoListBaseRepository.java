package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.TodoList;
import com.regitiny.catiny.repository.TodoListRepository;

/**
 * Spring Data SQL repository for the {@link TodoList} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.TodoListAdvanceRepository}
 */
public interface TodoListBaseRepository extends BaseRepository<TodoList>, CommonRepository<TodoList>, TodoListRepository
{
}
