package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.UserProfile;
import com.regitiny.catiny.repository.UserProfileRepository;
import com.regitiny.catiny.repository.search.UserProfileSearchRepository;
import com.regitiny.catiny.service.criteria.UserProfileCriteria;
import com.regitiny.catiny.service.dto.UserProfileDTO;
import com.regitiny.catiny.service.mapper.UserProfileMapper;
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
 * Service for executing complex queries for {@link UserProfile} entities in the database.
 * The main input is a {@link UserProfileCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UserProfileDTO} or a {@link Page} of {@link UserProfileDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class UserProfileQueryService extends QueryService<UserProfile> {

  private final Logger log = LoggerFactory.getLogger(UserProfileQueryService.class);

  private final UserProfileRepository userProfileRepository;

  private final UserProfileMapper userProfileMapper;

  private final UserProfileSearchRepository userProfileSearchRepository;

  public UserProfileQueryService(
    UserProfileRepository userProfileRepository,
    UserProfileMapper userProfileMapper,
    UserProfileSearchRepository userProfileSearchRepository
  ) {
    this.userProfileRepository = userProfileRepository;
    this.userProfileMapper = userProfileMapper;
    this.userProfileSearchRepository = userProfileSearchRepository;
  }

  /**
   * Return a {@link List} of {@link UserProfileDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<UserProfileDTO> findByCriteria(UserProfileCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<UserProfile> specification = createSpecification(criteria);
    return userProfileMapper.toDto(userProfileRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link UserProfileDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<UserProfileDTO> findByCriteria(UserProfileCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<UserProfile> specification = createSpecification(criteria);
    return userProfileRepository.findAll(specification, page).map(userProfileMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(UserProfileCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<UserProfile> specification = createSpecification(criteria);
    return userProfileRepository.count(specification);
  }

  /**
   * Function to convert {@link UserProfileCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<UserProfile> createSpecification(UserProfileCriteria criteria) {
    Specification<UserProfile> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), UserProfile_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), UserProfile_.uuid));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(UserProfile_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
    }
    return specification;
  }
}
