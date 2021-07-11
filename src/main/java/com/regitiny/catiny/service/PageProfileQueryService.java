package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.PageProfile;
import com.regitiny.catiny.repository.PageProfileRepository;
import com.regitiny.catiny.repository.search.PageProfileSearchRepository;
import com.regitiny.catiny.service.criteria.PageProfileCriteria;
import com.regitiny.catiny.service.dto.PageProfileDTO;
import com.regitiny.catiny.service.mapper.PageProfileMapper;
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
 * Service for executing complex queries for {@link PageProfile} entities in the database.
 * The main input is a {@link PageProfileCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PageProfileDTO} or a {@link Page} of {@link PageProfileDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class PageProfileQueryService extends QueryService<PageProfile> {

  private final Logger log = LoggerFactory.getLogger(PageProfileQueryService.class);

  private final PageProfileRepository pageProfileRepository;

  private final PageProfileMapper pageProfileMapper;

  private final PageProfileSearchRepository pageProfileSearchRepository;

  public PageProfileQueryService(
    PageProfileRepository pageProfileRepository,
    PageProfileMapper pageProfileMapper,
    PageProfileSearchRepository pageProfileSearchRepository
  ) {
    this.pageProfileRepository = pageProfileRepository;
    this.pageProfileMapper = pageProfileMapper;
    this.pageProfileSearchRepository = pageProfileSearchRepository;
  }

  /**
   * Return a {@link List} of {@link PageProfileDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<PageProfileDTO> findByCriteria(PageProfileCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<PageProfile> specification = createSpecification(criteria);
    return pageProfileMapper.toDto(pageProfileRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link PageProfileDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<PageProfileDTO> findByCriteria(PageProfileCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<PageProfile> specification = createSpecification(criteria);
    return pageProfileRepository.findAll(specification, page).map(pageProfileMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(PageProfileCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<PageProfile> specification = createSpecification(criteria);
    return pageProfileRepository.count(specification);
  }

  /**
   * Function to convert {@link PageProfileCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<PageProfile> createSpecification(PageProfileCriteria criteria) {
    Specification<PageProfile> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), PageProfile_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), PageProfile_.uuid));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(PageProfile_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getPageId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPageId(), root -> root.join(PageProfile_.page, JoinType.LEFT).get(PagePost_.id))
          );
      }
    }
    return specification;
  }
}
