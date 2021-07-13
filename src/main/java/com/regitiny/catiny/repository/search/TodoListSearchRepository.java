package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.TodoList;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link TodoList} entity.
 */
@GeneratedByJHipster
public interface TodoListSearchRepository extends ElasticsearchRepository<TodoList, Long> {}
