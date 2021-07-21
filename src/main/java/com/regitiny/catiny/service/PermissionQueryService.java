package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.BaseInfo_;
import com.regitiny.catiny.domain.MasterUser_;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.domain.Permission_;
import com.regitiny.catiny.repository.PermissionRepository;
import com.regitiny.catiny.repository.search.PermissionSearchRepository;
import com.regitiny.catiny.service.criteria.PermissionCriteria;
import com.regitiny.catiny.service.dto.PermissionDTO;
import com.regitiny.catiny.service.mapper.PermissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import javax.persistence.criteria.JoinType;
import java.util.List;

/**
 * Service for executing complex queries for {@link Permission} entities in the database.
 * The main input is a {@link PermissionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PermissionDTO} or a {@link Page} of {@link PermissionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class PermissionQueryService extends QueryService<Permission> {

  private final Logger log = LoggerFactory.getLogger(PermissionQueryService.class);

  private final PermissionRepository permissionRepository;

  private final PermissionMapper permissionMapper;

  private final PermissionSearchRepository permissionSearchRepository;

  public PermissionQueryService(
    PermissionRepository permissionRepository,
    PermissionMapper permissionMapper,
    PermissionSearchRepository permissionSearchRepository
  ) {
    this.permissionRepository = permissionRepository;
    this.permissionMapper = permissionMapper;
    this.permissionSearchRepository = permissionSearchRepository;
  }

  /**
   * Return a {@link List} of {@link PermissionDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<PermissionDTO> findByCriteria(PermissionCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<Permission> specification = createSpecification(criteria);
    return permissionMapper.toDto(permissionRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link PermissionDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<PermissionDTO> findByCriteria(PermissionCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<Permission> specification = createSpecification(criteria);
    return permissionRepository.findAll(specification, page).map(permissionMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(PermissionCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<Permission> specification = createSpecification(criteria);
    return permissionRepository.count(specification);
  }

  /**
   * Function to convert {@link PermissionCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<Permission> createSpecification(PermissionCriteria criteria) {
    Specification<Permission> specification = Specification.where(null);
    if (criteria != null)
    {
      if (criteria.getId() != null)
      {
        specification = specification.and(buildRangeSpecification(criteria.getId(), Permission_.id));
      }
      if (criteria.getUuid() != null)
      {
        specification = specification.and(buildSpecification(criteria.getUuid(), Permission_.uuid));
      }
      if (criteria.getRead() != null)
      {
        specification = specification.and(buildSpecification(criteria.getRead(), Permission_.read));
      }
      if (criteria.getWrite() != null)
      {
        specification = specification.and(buildSpecification(criteria.getWrite(), Permission_.write));
      }
      if (criteria.getShare() != null)
      {
        specification = specification.and(buildSpecification(criteria.getShare(), Permission_.share));
      }
      if (criteria.getDelete() != null)
      {
        specification = specification.and(buildSpecification(criteria.getDelete(), Permission_.delete));
      }
      if (criteria.getAdd() != null) {
        specification = specification.and(buildSpecification(criteria.getAdd(), Permission_.add));
      }
      if (criteria.getLevel() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getLevel(), Permission_.level));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(Permission_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getMasterUserId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMasterUserId(), root -> root.join(Permission_.masterUser, JoinType.LEFT).get(MasterUser_.id))
          );
      }
    }
    return specification;
  }
}
