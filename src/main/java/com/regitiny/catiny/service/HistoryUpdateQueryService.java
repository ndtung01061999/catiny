package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.BaseInfo_;
import com.regitiny.catiny.domain.HistoryUpdate;
import com.regitiny.catiny.domain.HistoryUpdate_;
import com.regitiny.catiny.repository.HistoryUpdateRepository;
import com.regitiny.catiny.repository.search.HistoryUpdateSearchRepository;
import com.regitiny.catiny.service.criteria.HistoryUpdateCriteria;
import com.regitiny.catiny.service.dto.HistoryUpdateDTO;
import com.regitiny.catiny.service.mapper.HistoryUpdateMapper;
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
 * Service for executing complex queries for {@link HistoryUpdate} entities in the database.
 * The main input is a {@link HistoryUpdateCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link HistoryUpdateDTO} or a {@link Page} of {@link HistoryUpdateDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class HistoryUpdateQueryService extends QueryService<HistoryUpdate>
{

  private final Logger log = LoggerFactory.getLogger(HistoryUpdateQueryService.class);

  private final HistoryUpdateRepository historyUpdateRepository;

  private final HistoryUpdateMapper historyUpdateMapper;

  private final HistoryUpdateSearchRepository historyUpdateSearchRepository;

  public HistoryUpdateQueryService(
    HistoryUpdateRepository historyUpdateRepository,
    HistoryUpdateMapper historyUpdateMapper,
    HistoryUpdateSearchRepository historyUpdateSearchRepository
  )
  {
    this.historyUpdateRepository = historyUpdateRepository;
    this.historyUpdateMapper = historyUpdateMapper;
    this.historyUpdateSearchRepository = historyUpdateSearchRepository;
  }

  /**
   * Return a {@link List} of {@link HistoryUpdateDTO} which matches the criteria from the database.
   *
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<HistoryUpdateDTO> findByCriteria(HistoryUpdateCriteria criteria)
  {
    log.debug("find by criteria : {}", criteria);
    final Specification<HistoryUpdate> specification = createSpecification(criteria);
    return historyUpdateMapper.toDto(historyUpdateRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link HistoryUpdateDTO} which matches the criteria from the database.
   *
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page     The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<HistoryUpdateDTO> findByCriteria(HistoryUpdateCriteria criteria, Pageable page)
  {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<HistoryUpdate> specification = createSpecification(criteria);
    return historyUpdateRepository.findAll(specification, page).map(historyUpdateMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   *
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(HistoryUpdateCriteria criteria)
  {
    log.debug("count by criteria : {}", criteria);
    final Specification<HistoryUpdate> specification = createSpecification(criteria);
    return historyUpdateRepository.count(specification);
  }

  /**
   * Function to convert {@link HistoryUpdateCriteria} to a {@link Specification}
   *
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<HistoryUpdate> createSpecification(HistoryUpdateCriteria criteria)
  {
    Specification<HistoryUpdate> specification = Specification.where(null);
    if (criteria != null)
    {
      if (criteria.getId() != null)
      {
        specification = specification.and(buildRangeSpecification(criteria.getId(), HistoryUpdate_.id));
      }
      if (criteria.getUuid() != null)
      {
        specification = specification.and(buildSpecification(criteria.getUuid(), HistoryUpdate_.uuid));
      }
      if (criteria.getVersion() != null)
      {
        specification = specification.and(buildRangeSpecification(criteria.getVersion(), HistoryUpdate_.version));
      }
      if (criteria.getBaseInfoId() != null)
      {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(HistoryUpdate_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
    }
    return specification;
  }
}
