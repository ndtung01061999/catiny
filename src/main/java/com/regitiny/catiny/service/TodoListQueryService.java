package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.TodoList;
import com.regitiny.catiny.repository.TodoListRepository;
import com.regitiny.catiny.repository.search.TodoListSearchRepository;
import com.regitiny.catiny.service.criteria.TodoListCriteria;
import com.regitiny.catiny.service.dto.TodoListDTO;
import com.regitiny.catiny.service.mapper.TodoListMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link TodoList} entities in the database.
 * The main input is a {@link TodoListCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TodoListDTO} or a {@link Page} of {@link TodoListDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class TodoListQueryService extends QueryService<TodoList> {

  private final Logger log = LoggerFactory.getLogger(TodoListQueryService.class);

  private final TodoListRepository todoListRepository;

  private final TodoListMapper todoListMapper;

  private final TodoListSearchRepository todoListSearchRepository;

  public TodoListQueryService(
    TodoListRepository todoListRepository,
    TodoListMapper todoListMapper,
    TodoListSearchRepository todoListSearchRepository
  ) {
    this.todoListRepository = todoListRepository;
    this.todoListMapper = todoListMapper;
    this.todoListSearchRepository = todoListSearchRepository;
  }

  /**
   * Return a {@link List} of {@link TodoListDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<TodoListDTO> findByCriteria(TodoListCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<TodoList> specification = createSpecification(criteria);
    return todoListMapper.toDto(todoListRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link TodoListDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<TodoListDTO> findByCriteria(TodoListCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<TodoList> specification = createSpecification(criteria);
    return todoListRepository.findAll(specification, page).map(todoListMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(TodoListCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<TodoList> specification = createSpecification(criteria);
    return todoListRepository.count(specification);
  }

  /**
   * Function to convert {@link TodoListCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<TodoList> createSpecification(TodoListCriteria criteria) {
    Specification<TodoList> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), TodoList_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), TodoList_.uuid));
      }
      if (criteria.getTitle() != null) {
        specification = specification.and(buildStringSpecification(criteria.getTitle(), TodoList_.title));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(TodoList_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getMasterUserId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMasterUserId(), root -> root.join(TodoList_.masterUser, JoinType.LEFT).get(MasterUser_.id))
          );
      }
    }
    return specification;
  }
}
