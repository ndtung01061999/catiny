package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.service.TodoListQueryService;
import com.regitiny.catiny.service.TodoListService;

/**
 * Spring Data Elasticsearch advance-repository extends jhipster-search-repository for the {@link com.regitiny.catiny.domain.TodoList} entityDomain.
 *
 * @see TodoListService is base repository generate by jhipster
 */
public interface TodoListAdvanceService extends LocalService<TodoListService, TodoListQueryService>
{
}
