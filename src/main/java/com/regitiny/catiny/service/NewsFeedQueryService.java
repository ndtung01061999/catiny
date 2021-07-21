package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.NewsFeed;
import com.regitiny.catiny.repository.NewsFeedRepository;
import com.regitiny.catiny.repository.search.NewsFeedSearchRepository;
import com.regitiny.catiny.service.criteria.NewsFeedCriteria;
import com.regitiny.catiny.service.dto.NewsFeedDTO;
import com.regitiny.catiny.service.mapper.NewsFeedMapper;
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
 * Service for executing complex queries for {@link NewsFeed} entities in the database.
 * The main input is a {@link NewsFeedCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NewsFeedDTO} or a {@link Page} of {@link NewsFeedDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class NewsFeedQueryService extends QueryService<NewsFeed> {

  private final Logger log = LoggerFactory.getLogger(NewsFeedQueryService.class);

  private final NewsFeedRepository newsFeedRepository;

  private final NewsFeedMapper newsFeedMapper;

  private final NewsFeedSearchRepository newsFeedSearchRepository;

  public NewsFeedQueryService(
    NewsFeedRepository newsFeedRepository,
    NewsFeedMapper newsFeedMapper,
    NewsFeedSearchRepository newsFeedSearchRepository
  ) {
    this.newsFeedRepository = newsFeedRepository;
    this.newsFeedMapper = newsFeedMapper;
    this.newsFeedSearchRepository = newsFeedSearchRepository;
  }

  /**
   * Return a {@link List} of {@link NewsFeedDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<NewsFeedDTO> findByCriteria(NewsFeedCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<NewsFeed> specification = createSpecification(criteria);
    return newsFeedMapper.toDto(newsFeedRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link NewsFeedDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<NewsFeedDTO> findByCriteria(NewsFeedCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<NewsFeed> specification = createSpecification(criteria);
    return newsFeedRepository.findAll(specification, page).map(newsFeedMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(NewsFeedCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<NewsFeed> specification = createSpecification(criteria);
    return newsFeedRepository.count(specification);
  }

  /**
   * Function to convert {@link NewsFeedCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<NewsFeed> createSpecification(NewsFeedCriteria criteria) {
    Specification<NewsFeed> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), NewsFeed_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), NewsFeed_.uuid));
      }
      if (criteria.getPriorityIndex() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getPriorityIndex(), NewsFeed_.priorityIndex));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(NewsFeed_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getPostId() != null) {
        specification =
          specification.and(buildSpecification(criteria.getPostId(), root -> root.join(NewsFeed_.post, JoinType.LEFT).get(Post_.id)));
      }
    }
    return specification;
  }
}
