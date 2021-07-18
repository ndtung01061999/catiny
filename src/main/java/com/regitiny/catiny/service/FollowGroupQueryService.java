package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.FollowGroup;
import com.regitiny.catiny.repository.FollowGroupRepository;
import com.regitiny.catiny.repository.search.FollowGroupSearchRepository;
import com.regitiny.catiny.service.criteria.FollowGroupCriteria;
import com.regitiny.catiny.service.dto.FollowGroupDTO;
import com.regitiny.catiny.service.mapper.FollowGroupMapper;
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
 * Service for executing complex queries for {@link FollowGroup} entities in the database.
 * The main input is a {@link FollowGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FollowGroupDTO} or a {@link Page} of {@link FollowGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class FollowGroupQueryService extends QueryService<FollowGroup> {

  private final Logger log = LoggerFactory.getLogger(FollowGroupQueryService.class);

  private final FollowGroupRepository followGroupRepository;

  private final FollowGroupMapper followGroupMapper;

  private final FollowGroupSearchRepository followGroupSearchRepository;

  public FollowGroupQueryService(
    FollowGroupRepository followGroupRepository,
    FollowGroupMapper followGroupMapper,
    FollowGroupSearchRepository followGroupSearchRepository
  ) {
    this.followGroupRepository = followGroupRepository;
    this.followGroupMapper = followGroupMapper;
    this.followGroupSearchRepository = followGroupSearchRepository;
  }

  /**
   * Return a {@link List} of {@link FollowGroupDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<FollowGroupDTO> findByCriteria(FollowGroupCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<FollowGroup> specification = createSpecification(criteria);
    return followGroupMapper.toDto(followGroupRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link FollowGroupDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<FollowGroupDTO> findByCriteria(FollowGroupCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<FollowGroup> specification = createSpecification(criteria);
    return followGroupRepository.findAll(specification, page).map(followGroupMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(FollowGroupCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<FollowGroup> specification = createSpecification(criteria);
    return followGroupRepository.count(specification);
  }

  /**
   * Function to convert {@link FollowGroupCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<FollowGroup> createSpecification(FollowGroupCriteria criteria) {
    Specification<FollowGroup> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), FollowGroup_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), FollowGroup_.uuid));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(FollowGroup_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getFollowGroupDetailsId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getFollowGroupDetailsId(),
              root -> root.join(FollowGroup_.followGroupDetails, JoinType.LEFT).get(GroupPost_.id)
            )
          );
      }
    }
    return specification;
  }
}
