package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.repository.MasterUserRepository;
import com.regitiny.catiny.repository.search.MasterUserSearchRepository;
import com.regitiny.catiny.service.criteria.MasterUserCriteria;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import com.regitiny.catiny.service.mapper.MasterUserMapper;
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
 * Service for executing complex queries for {@link MasterUser} entities in the database.
 * The main input is a {@link MasterUserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MasterUserDTO} or a {@link Page} of {@link MasterUserDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class MasterUserQueryService extends QueryService<MasterUser> {

  private final Logger log = LoggerFactory.getLogger(MasterUserQueryService.class);

  private final MasterUserRepository masterUserRepository;

  private final MasterUserMapper masterUserMapper;

  private final MasterUserSearchRepository masterUserSearchRepository;

  public MasterUserQueryService(
    MasterUserRepository masterUserRepository,
    MasterUserMapper masterUserMapper,
    MasterUserSearchRepository masterUserSearchRepository
  ) {
    this.masterUserRepository = masterUserRepository;
    this.masterUserMapper = masterUserMapper;
    this.masterUserSearchRepository = masterUserSearchRepository;
  }

  /**
   * Return a {@link List} of {@link MasterUserDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<MasterUserDTO> findByCriteria(MasterUserCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<MasterUser> specification = createSpecification(criteria);
    return masterUserMapper.toDto(masterUserRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link MasterUserDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<MasterUserDTO> findByCriteria(MasterUserCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<MasterUser> specification = createSpecification(criteria);
    return masterUserRepository.findAll(specification, page).map(masterUserMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(MasterUserCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<MasterUser> specification = createSpecification(criteria);
    return masterUserRepository.count(specification);
  }

  /**
   * Function to convert {@link MasterUserCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<MasterUser> createSpecification(MasterUserCriteria criteria) {
    Specification<MasterUser> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), MasterUser_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), MasterUser_.uuid));
      }
      if (criteria.getFullName() != null) {
        specification = specification.and(buildStringSpecification(criteria.getFullName(), MasterUser_.fullName));
      }
      if (criteria.getNickname() != null) {
        specification = specification.and(buildStringSpecification(criteria.getNickname(), MasterUser_.nickname));
      }
      if (criteria.getUserId() != null) {
        specification =
          specification.and(buildSpecification(criteria.getUserId(), root -> root.join(MasterUser_.user, JoinType.LEFT).get(User_.id)));
      }
      if (criteria.getMyRankId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMyRankId(), root -> root.join(MasterUser_.myRank, JoinType.LEFT).get(RankUser_.id))
          );
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(MasterUser_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getMyBaseInfoCreatedId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getMyBaseInfoCreatedId(),
              root -> root.join(MasterUser_.myBaseInfoCreateds, JoinType.LEFT).get(BaseInfo_.id)
            )
          );
      }
      if (criteria.getMyBaseInfoModifiedId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getMyBaseInfoModifiedId(),
              root -> root.join(MasterUser_.myBaseInfoModifieds, JoinType.LEFT).get(BaseInfo_.id)
            )
          );
      }
      if (criteria.getOwnerOfId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getOwnerOfId(), root -> root.join(MasterUser_.ownerOfs, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getPermissionId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPermissionId(), root -> root.join(MasterUser_.permissions, JoinType.LEFT).get(Permission_.id))
          );
      }
      if (criteria.getTopicInterestId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getTopicInterestId(),
              root -> root.join(MasterUser_.topicInterests, JoinType.LEFT).get(TopicInterest_.id)
            )
          );
      }
    }
    return specification;
  }
}
