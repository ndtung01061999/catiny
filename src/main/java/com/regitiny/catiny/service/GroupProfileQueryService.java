package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.GroupProfile;
import com.regitiny.catiny.repository.GroupProfileRepository;
import com.regitiny.catiny.repository.search.GroupProfileSearchRepository;
import com.regitiny.catiny.service.criteria.GroupProfileCriteria;
import com.regitiny.catiny.service.dto.GroupProfileDTO;
import com.regitiny.catiny.service.mapper.GroupProfileMapper;
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
 * Service for executing complex queries for {@link GroupProfile} entities in the database.
 * The main input is a {@link GroupProfileCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GroupProfileDTO} or a {@link Page} of {@link GroupProfileDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class GroupProfileQueryService extends QueryService<GroupProfile> {

  private final Logger log = LoggerFactory.getLogger(GroupProfileQueryService.class);

  private final GroupProfileRepository groupProfileRepository;

  private final GroupProfileMapper groupProfileMapper;

  private final GroupProfileSearchRepository groupProfileSearchRepository;

  public GroupProfileQueryService(
    GroupProfileRepository groupProfileRepository,
    GroupProfileMapper groupProfileMapper,
    GroupProfileSearchRepository groupProfileSearchRepository
  ) {
    this.groupProfileRepository = groupProfileRepository;
    this.groupProfileMapper = groupProfileMapper;
    this.groupProfileSearchRepository = groupProfileSearchRepository;
  }

  /**
   * Return a {@link List} of {@link GroupProfileDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<GroupProfileDTO> findByCriteria(GroupProfileCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<GroupProfile> specification = createSpecification(criteria);
    return groupProfileMapper.toDto(groupProfileRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link GroupProfileDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<GroupProfileDTO> findByCriteria(GroupProfileCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<GroupProfile> specification = createSpecification(criteria);
    return groupProfileRepository.findAll(specification, page).map(groupProfileMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(GroupProfileCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<GroupProfile> specification = createSpecification(criteria);
    return groupProfileRepository.count(specification);
  }

  /**
   * Function to convert {@link GroupProfileCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<GroupProfile> createSpecification(GroupProfileCriteria criteria) {
    Specification<GroupProfile> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), GroupProfile_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), GroupProfile_.uuid));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(GroupProfile_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getGroupId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getGroupId(), root -> root.join(GroupProfile_.group, JoinType.LEFT).get(GroupPost_.id))
          );
      }
    }
    return specification;
  }
}
