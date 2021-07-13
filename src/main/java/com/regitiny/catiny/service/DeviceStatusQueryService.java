package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.DeviceStatus;
import com.regitiny.catiny.repository.DeviceStatusRepository;
import com.regitiny.catiny.repository.search.DeviceStatusSearchRepository;
import com.regitiny.catiny.service.criteria.DeviceStatusCriteria;
import com.regitiny.catiny.service.dto.DeviceStatusDTO;
import com.regitiny.catiny.service.mapper.DeviceStatusMapper;
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
 * Service for executing complex queries for {@link DeviceStatus} entities in the database.
 * The main input is a {@link DeviceStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DeviceStatusDTO} or a {@link Page} of {@link DeviceStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class DeviceStatusQueryService extends QueryService<DeviceStatus> {

  private final Logger log = LoggerFactory.getLogger(DeviceStatusQueryService.class);

  private final DeviceStatusRepository deviceStatusRepository;

  private final DeviceStatusMapper deviceStatusMapper;

  private final DeviceStatusSearchRepository deviceStatusSearchRepository;

  public DeviceStatusQueryService(
    DeviceStatusRepository deviceStatusRepository,
    DeviceStatusMapper deviceStatusMapper,
    DeviceStatusSearchRepository deviceStatusSearchRepository
  ) {
    this.deviceStatusRepository = deviceStatusRepository;
    this.deviceStatusMapper = deviceStatusMapper;
    this.deviceStatusSearchRepository = deviceStatusSearchRepository;
  }

  /**
   * Return a {@link List} of {@link DeviceStatusDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<DeviceStatusDTO> findByCriteria(DeviceStatusCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<DeviceStatus> specification = createSpecification(criteria);
    return deviceStatusMapper.toDto(deviceStatusRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link DeviceStatusDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<DeviceStatusDTO> findByCriteria(DeviceStatusCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<DeviceStatus> specification = createSpecification(criteria);
    return deviceStatusRepository.findAll(specification, page).map(deviceStatusMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(DeviceStatusCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<DeviceStatus> specification = createSpecification(criteria);
    return deviceStatusRepository.count(specification);
  }

  /**
   * Function to convert {@link DeviceStatusCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<DeviceStatus> createSpecification(DeviceStatusCriteria criteria) {
    Specification<DeviceStatus> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), DeviceStatus_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), DeviceStatus_.uuid));
      }
      if (criteria.getDeviceName() != null) {
        specification = specification.and(buildStringSpecification(criteria.getDeviceName(), DeviceStatus_.deviceName));
      }
      if (criteria.getDeviceType() != null) {
        specification = specification.and(buildSpecification(criteria.getDeviceType(), DeviceStatus_.deviceType));
      }
      if (criteria.getDeviceStatus() != null) {
        specification = specification.and(buildSpecification(criteria.getDeviceStatus(), DeviceStatus_.deviceStatus));
      }
      if (criteria.getLastVisited() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getLastVisited(), DeviceStatus_.lastVisited));
      }
      if (criteria.getStatusComment() != null) {
        specification = specification.and(buildStringSpecification(criteria.getStatusComment(), DeviceStatus_.statusComment));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(DeviceStatus_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getAccountStatusId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getAccountStatusId(),
              root -> root.join(DeviceStatus_.accountStatus, JoinType.LEFT).get(AccountStatus_.id)
            )
          );
      }
    }
    return specification;
  }
}
