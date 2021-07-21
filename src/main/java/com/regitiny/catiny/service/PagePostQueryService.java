package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.PagePost;
import com.regitiny.catiny.repository.PagePostRepository;
import com.regitiny.catiny.repository.search.PagePostSearchRepository;
import com.regitiny.catiny.service.criteria.PagePostCriteria;
import com.regitiny.catiny.service.dto.PagePostDTO;
import com.regitiny.catiny.service.mapper.PagePostMapper;
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
 * Service for executing complex queries for {@link PagePost} entities in the database.
 * The main input is a {@link PagePostCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PagePostDTO} or a {@link Page} of {@link PagePostDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class PagePostQueryService extends QueryService<PagePost> {

  private final Logger log = LoggerFactory.getLogger(PagePostQueryService.class);

  private final PagePostRepository pagePostRepository;

  private final PagePostMapper pagePostMapper;

  private final PagePostSearchRepository pagePostSearchRepository;

  public PagePostQueryService(
    PagePostRepository pagePostRepository,
    PagePostMapper pagePostMapper,
    PagePostSearchRepository pagePostSearchRepository
  ) {
    this.pagePostRepository = pagePostRepository;
    this.pagePostMapper = pagePostMapper;
    this.pagePostSearchRepository = pagePostSearchRepository;
  }

  /**
   * Return a {@link List} of {@link PagePostDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<PagePostDTO> findByCriteria(PagePostCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<PagePost> specification = createSpecification(criteria);
    return pagePostMapper.toDto(pagePostRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link PagePostDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<PagePostDTO> findByCriteria(PagePostCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<PagePost> specification = createSpecification(criteria);
    return pagePostRepository.findAll(specification, page).map(pagePostMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(PagePostCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<PagePost> specification = createSpecification(criteria);
    return pagePostRepository.count(specification);
  }

  /**
   * Function to convert {@link PagePostCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<PagePost> createSpecification(PagePostCriteria criteria) {
    Specification<PagePost> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), PagePost_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), PagePost_.uuid));
      }
      if (criteria.getName() != null) {
        specification = specification.and(buildStringSpecification(criteria.getName(), PagePost_.name));
      }
      if (criteria.getProfileId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getProfileId(), root -> root.join(PagePost_.profile, JoinType.LEFT).get(PageProfile_.id))
          );
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(PagePost_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getMyPostInPageId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMyPostInPageId(), root -> root.join(PagePost_.myPostInPages, JoinType.LEFT).get(Post_.id))
          );
      }
      if (criteria.getTopicInterestId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getTopicInterestId(),
              root -> root.join(PagePost_.topicInterests, JoinType.LEFT).get(TopicInterest_.id)
            )
          );
      }
    }
    return specification;
  }
}
