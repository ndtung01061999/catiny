package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.RankUser;
import com.regitiny.catiny.repository.RankUserRepository;
import com.regitiny.catiny.repository.search.RankUserSearchRepository;
import com.regitiny.catiny.service.criteria.RankUserCriteria;
import com.regitiny.catiny.service.dto.RankUserDTO;
import com.regitiny.catiny.service.mapper.RankUserMapper;
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
 * Service for executing complex queries for {@link RankUser} entities in the database.
 * The main input is a {@link RankUserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RankUserDTO} or a {@link Page} of {@link RankUserDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class RankUserQueryService extends QueryService<RankUser> {

  private final Logger log = LoggerFactory.getLogger(RankUserQueryService.class);

  private final RankUserRepository rankUserRepository;

  private final RankUserMapper rankUserMapper;

  private final RankUserSearchRepository rankUserSearchRepository;

  public RankUserQueryService(
    RankUserRepository rankUserRepository,
    RankUserMapper rankUserMapper,
    RankUserSearchRepository rankUserSearchRepository
  ) {
    this.rankUserRepository = rankUserRepository;
    this.rankUserMapper = rankUserMapper;
    this.rankUserSearchRepository = rankUserSearchRepository;
  }

  /**
   * Return a {@link List} of {@link RankUserDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<RankUserDTO> findByCriteria(RankUserCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<RankUser> specification = createSpecification(criteria);
    return rankUserMapper.toDto(rankUserRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link RankUserDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<RankUserDTO> findByCriteria(RankUserCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<RankUser> specification = createSpecification(criteria);
    return rankUserRepository.findAll(specification, page).map(rankUserMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(RankUserCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<RankUser> specification = createSpecification(criteria);
    return rankUserRepository.count(specification);
  }

  /**
   * Function to convert {@link RankUserCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<RankUser> createSpecification(RankUserCriteria criteria) {
    Specification<RankUser> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), RankUser_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), RankUser_.uuid));
      }
      if (criteria.getRatingPoints() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getRatingPoints(), RankUser_.ratingPoints));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(RankUser_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getRankGroupId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getRankGroupId(), root -> root.join(RankUser_.rankGroup, JoinType.LEFT).get(RankGroup_.id))
          );
      }
    }
    return specification;
  }
}
