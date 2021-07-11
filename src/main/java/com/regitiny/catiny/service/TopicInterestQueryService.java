package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.TopicInterest;
import com.regitiny.catiny.repository.TopicInterestRepository;
import com.regitiny.catiny.repository.search.TopicInterestSearchRepository;
import com.regitiny.catiny.service.criteria.TopicInterestCriteria;
import com.regitiny.catiny.service.dto.TopicInterestDTO;
import com.regitiny.catiny.service.mapper.TopicInterestMapper;
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
 * Service for executing complex queries for {@link TopicInterest} entities in the database.
 * The main input is a {@link TopicInterestCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TopicInterestDTO} or a {@link Page} of {@link TopicInterestDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class TopicInterestQueryService extends QueryService<TopicInterest> {

  private final Logger log = LoggerFactory.getLogger(TopicInterestQueryService.class);

  private final TopicInterestRepository topicInterestRepository;

  private final TopicInterestMapper topicInterestMapper;

  private final TopicInterestSearchRepository topicInterestSearchRepository;

  public TopicInterestQueryService(
    TopicInterestRepository topicInterestRepository,
    TopicInterestMapper topicInterestMapper,
    TopicInterestSearchRepository topicInterestSearchRepository
  ) {
    this.topicInterestRepository = topicInterestRepository;
    this.topicInterestMapper = topicInterestMapper;
    this.topicInterestSearchRepository = topicInterestSearchRepository;
  }

  /**
   * Return a {@link List} of {@link TopicInterestDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<TopicInterestDTO> findByCriteria(TopicInterestCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<TopicInterest> specification = createSpecification(criteria);
    return topicInterestMapper.toDto(topicInterestRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link TopicInterestDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<TopicInterestDTO> findByCriteria(TopicInterestCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<TopicInterest> specification = createSpecification(criteria);
    return topicInterestRepository.findAll(specification, page).map(topicInterestMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(TopicInterestCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<TopicInterest> specification = createSpecification(criteria);
    return topicInterestRepository.count(specification);
  }

  /**
   * Function to convert {@link TopicInterestCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<TopicInterest> createSpecification(TopicInterestCriteria criteria) {
    Specification<TopicInterest> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), TopicInterest_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), TopicInterest_.uuid));
      }
      if (criteria.getTitle() != null) {
        specification = specification.and(buildStringSpecification(criteria.getTitle(), TopicInterest_.title));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(TopicInterest_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getPostId() != null) {
        specification =
          specification.and(buildSpecification(criteria.getPostId(), root -> root.join(TopicInterest_.posts, JoinType.LEFT).get(Post_.id)));
      }
      if (criteria.getPagePostId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPagePostId(), root -> root.join(TopicInterest_.pagePosts, JoinType.LEFT).get(PagePost_.id))
          );
      }
      if (criteria.getGroupPostId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getGroupPostId(), root -> root.join(TopicInterest_.groupPosts, JoinType.LEFT).get(GroupPost_.id))
          );
      }
      if (criteria.getMasterUserId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMasterUserId(), root -> root.join(TopicInterest_.masterUsers, JoinType.LEFT).get(MasterUser_.id))
          );
      }
    }
    return specification;
  }
}
