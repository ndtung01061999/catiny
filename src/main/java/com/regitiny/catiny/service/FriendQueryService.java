package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.Friend;
import com.regitiny.catiny.repository.FriendRepository;
import com.regitiny.catiny.repository.search.FriendSearchRepository;
import com.regitiny.catiny.service.criteria.FriendCriteria;
import com.regitiny.catiny.service.dto.FriendDTO;
import com.regitiny.catiny.service.mapper.FriendMapper;
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
 * Service for executing complex queries for {@link Friend} entities in the database.
 * The main input is a {@link FriendCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FriendDTO} or a {@link Page} of {@link FriendDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class FriendQueryService extends QueryService<Friend> {

  private final Logger log = LoggerFactory.getLogger(FriendQueryService.class);

  private final FriendRepository friendRepository;

  private final FriendMapper friendMapper;

  private final FriendSearchRepository friendSearchRepository;

  public FriendQueryService(FriendRepository friendRepository, FriendMapper friendMapper, FriendSearchRepository friendSearchRepository) {
    this.friendRepository = friendRepository;
    this.friendMapper = friendMapper;
    this.friendSearchRepository = friendSearchRepository;
  }

  /**
   * Return a {@link List} of {@link FriendDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<FriendDTO> findByCriteria(FriendCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<Friend> specification = createSpecification(criteria);
    return friendMapper.toDto(friendRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link FriendDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<FriendDTO> findByCriteria(FriendCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<Friend> specification = createSpecification(criteria);
    return friendRepository.findAll(specification, page).map(friendMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(FriendCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<Friend> specification = createSpecification(criteria);
    return friendRepository.count(specification);
  }

  /**
   * Function to convert {@link FriendCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<Friend> createSpecification(FriendCriteria criteria) {
    Specification<Friend> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), Friend_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), Friend_.uuid));
      }
      if (criteria.getFriendType() != null) {
        specification = specification.and(buildSpecification(criteria.getFriendType(), Friend_.friendType));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(Friend_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getFriendDetailsId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getFriendDetailsId(), root -> root.join(Friend_.friendDetails, JoinType.LEFT).get(MasterUser_.id))
          );
      }
    }
    return specification;
  }
}
