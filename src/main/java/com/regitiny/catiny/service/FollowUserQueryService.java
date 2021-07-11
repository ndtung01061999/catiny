package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.FollowUser;
import com.regitiny.catiny.repository.FollowUserRepository;
import com.regitiny.catiny.repository.search.FollowUserSearchRepository;
import com.regitiny.catiny.service.criteria.FollowUserCriteria;
import com.regitiny.catiny.service.dto.FollowUserDTO;
import com.regitiny.catiny.service.mapper.FollowUserMapper;
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
 * Service for executing complex queries for {@link FollowUser} entities in the database.
 * The main input is a {@link FollowUserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FollowUserDTO} or a {@link Page} of {@link FollowUserDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class FollowUserQueryService extends QueryService<FollowUser> {

  private final Logger log = LoggerFactory.getLogger(FollowUserQueryService.class);

  private final FollowUserRepository followUserRepository;

  private final FollowUserMapper followUserMapper;

  private final FollowUserSearchRepository followUserSearchRepository;

  public FollowUserQueryService(
    FollowUserRepository followUserRepository,
    FollowUserMapper followUserMapper,
    FollowUserSearchRepository followUserSearchRepository
  ) {
    this.followUserRepository = followUserRepository;
    this.followUserMapper = followUserMapper;
    this.followUserSearchRepository = followUserSearchRepository;
  }

  /**
   * Return a {@link List} of {@link FollowUserDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<FollowUserDTO> findByCriteria(FollowUserCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<FollowUser> specification = createSpecification(criteria);
    return followUserMapper.toDto(followUserRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link FollowUserDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<FollowUserDTO> findByCriteria(FollowUserCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<FollowUser> specification = createSpecification(criteria);
    return followUserRepository.findAll(specification, page).map(followUserMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(FollowUserCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<FollowUser> specification = createSpecification(criteria);
    return followUserRepository.count(specification);
  }

  /**
   * Function to convert {@link FollowUserCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<FollowUser> createSpecification(FollowUserCriteria criteria) {
    Specification<FollowUser> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), FollowUser_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), FollowUser_.uuid));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(FollowUser_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getFollowUserDetailsId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getFollowUserDetailsId(),
              root -> root.join(FollowUser_.followUserDetails, JoinType.LEFT).get(MasterUser_.id)
            )
          );
      }
      if (criteria.getMasterUserId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMasterUserId(), root -> root.join(FollowUser_.masterUser, JoinType.LEFT).get(MasterUser_.id))
          );
      }
    }
    return specification;
  }
}
