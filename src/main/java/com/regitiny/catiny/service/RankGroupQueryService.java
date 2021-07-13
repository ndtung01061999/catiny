package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.RankGroup;
import com.regitiny.catiny.repository.RankGroupRepository;
import com.regitiny.catiny.repository.search.RankGroupSearchRepository;
import com.regitiny.catiny.service.criteria.RankGroupCriteria;
import com.regitiny.catiny.service.dto.RankGroupDTO;
import com.regitiny.catiny.service.mapper.RankGroupMapper;
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
 * Service for executing complex queries for {@link RankGroup} entities in the database.
 * The main input is a {@link RankGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RankGroupDTO} or a {@link Page} of {@link RankGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class RankGroupQueryService extends QueryService<RankGroup> {

  private final Logger log = LoggerFactory.getLogger(RankGroupQueryService.class);

  private final RankGroupRepository rankGroupRepository;

  private final RankGroupMapper rankGroupMapper;

  private final RankGroupSearchRepository rankGroupSearchRepository;

  public RankGroupQueryService(
    RankGroupRepository rankGroupRepository,
    RankGroupMapper rankGroupMapper,
    RankGroupSearchRepository rankGroupSearchRepository
  ) {
    this.rankGroupRepository = rankGroupRepository;
    this.rankGroupMapper = rankGroupMapper;
    this.rankGroupSearchRepository = rankGroupSearchRepository;
  }

  /**
   * Return a {@link List} of {@link RankGroupDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<RankGroupDTO> findByCriteria(RankGroupCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<RankGroup> specification = createSpecification(criteria);
    return rankGroupMapper.toDto(rankGroupRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link RankGroupDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<RankGroupDTO> findByCriteria(RankGroupCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<RankGroup> specification = createSpecification(criteria);
    return rankGroupRepository.findAll(specification, page).map(rankGroupMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(RankGroupCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<RankGroup> specification = createSpecification(criteria);
    return rankGroupRepository.count(specification);
  }

  /**
   * Function to convert {@link RankGroupCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<RankGroup> createSpecification(RankGroupCriteria criteria) {
    Specification<RankGroup> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), RankGroup_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), RankGroup_.uuid));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(RankGroup_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getRankUserId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getRankUserId(), root -> root.join(RankGroup_.rankUsers, JoinType.LEFT).get(RankUser_.id))
          );
      }
    }
    return specification;
  }
}
