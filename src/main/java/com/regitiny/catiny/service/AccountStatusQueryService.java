package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.AccountStatus;
import com.regitiny.catiny.repository.AccountStatusRepository;
import com.regitiny.catiny.repository.search.AccountStatusSearchRepository;
import com.regitiny.catiny.service.criteria.AccountStatusCriteria;
import com.regitiny.catiny.service.dto.AccountStatusDTO;
import com.regitiny.catiny.service.mapper.AccountStatusMapper;
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
 * Service for executing complex queries for {@link AccountStatus} entities in the database.
 * The main input is a {@link AccountStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AccountStatusDTO} or a {@link Page} of {@link AccountStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class AccountStatusQueryService extends QueryService<AccountStatus> {

  private final Logger log = LoggerFactory.getLogger(AccountStatusQueryService.class);

  private final AccountStatusRepository accountStatusRepository;

  private final AccountStatusMapper accountStatusMapper;

  private final AccountStatusSearchRepository accountStatusSearchRepository;

  public AccountStatusQueryService(
    AccountStatusRepository accountStatusRepository,
    AccountStatusMapper accountStatusMapper,
    AccountStatusSearchRepository accountStatusSearchRepository
  ) {
    this.accountStatusRepository = accountStatusRepository;
    this.accountStatusMapper = accountStatusMapper;
    this.accountStatusSearchRepository = accountStatusSearchRepository;
  }

  /**
   * Return a {@link List} of {@link AccountStatusDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<AccountStatusDTO> findByCriteria(AccountStatusCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<AccountStatus> specification = createSpecification(criteria);
    return accountStatusMapper.toDto(accountStatusRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link AccountStatusDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<AccountStatusDTO> findByCriteria(AccountStatusCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<AccountStatus> specification = createSpecification(criteria);
    return accountStatusRepository.findAll(specification, page).map(accountStatusMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(AccountStatusCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<AccountStatus> specification = createSpecification(criteria);
    return accountStatusRepository.count(specification);
  }

  /**
   * Function to convert {@link AccountStatusCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<AccountStatus> createSpecification(AccountStatusCriteria criteria) {
    Specification<AccountStatus> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), AccountStatus_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), AccountStatus_.uuid));
      }
      if (criteria.getAccountStatus() != null) {
        specification = specification.and(buildSpecification(criteria.getAccountStatus(), AccountStatus_.accountStatus));
      }
      if (criteria.getLastVisited() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getLastVisited(), AccountStatus_.lastVisited));
      }
      if (criteria.getStatusComment() != null) {
        specification = specification.and(buildStringSpecification(criteria.getStatusComment(), AccountStatus_.statusComment));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(AccountStatus_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getDeviceStatusId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getDeviceStatusId(),
              root -> root.join(AccountStatus_.deviceStatuses, JoinType.LEFT).get(DeviceStatus_.id)
            )
          );
      }
    }
    return specification;
  }
}
