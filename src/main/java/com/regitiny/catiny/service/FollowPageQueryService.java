package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.FollowPage;
import com.regitiny.catiny.repository.FollowPageRepository;
import com.regitiny.catiny.repository.search.FollowPageSearchRepository;
import com.regitiny.catiny.service.criteria.FollowPageCriteria;
import com.regitiny.catiny.service.dto.FollowPageDTO;
import com.regitiny.catiny.service.mapper.FollowPageMapper;
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
 * Service for executing complex queries for {@link FollowPage} entities in the database.
 * The main input is a {@link FollowPageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FollowPageDTO} or a {@link Page} of {@link FollowPageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class FollowPageQueryService extends QueryService<FollowPage> {

  private final Logger log = LoggerFactory.getLogger(FollowPageQueryService.class);

  private final FollowPageRepository followPageRepository;

  private final FollowPageMapper followPageMapper;

  private final FollowPageSearchRepository followPageSearchRepository;

  public FollowPageQueryService(
    FollowPageRepository followPageRepository,
    FollowPageMapper followPageMapper,
    FollowPageSearchRepository followPageSearchRepository
  ) {
    this.followPageRepository = followPageRepository;
    this.followPageMapper = followPageMapper;
    this.followPageSearchRepository = followPageSearchRepository;
  }

  /**
   * Return a {@link List} of {@link FollowPageDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<FollowPageDTO> findByCriteria(FollowPageCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<FollowPage> specification = createSpecification(criteria);
    return followPageMapper.toDto(followPageRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link FollowPageDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<FollowPageDTO> findByCriteria(FollowPageCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<FollowPage> specification = createSpecification(criteria);
    return followPageRepository.findAll(specification, page).map(followPageMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(FollowPageCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<FollowPage> specification = createSpecification(criteria);
    return followPageRepository.count(specification);
  }

  /**
   * Function to convert {@link FollowPageCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<FollowPage> createSpecification(FollowPageCriteria criteria) {
    Specification<FollowPage> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), FollowPage_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), FollowPage_.uuid));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(FollowPage_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getFollowPageDetailsId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getFollowPageDetailsId(),
              root -> root.join(FollowPage_.followPageDetails, JoinType.LEFT).get(PagePost_.id)
            )
          );
      }
    }
    return specification;
  }
}
