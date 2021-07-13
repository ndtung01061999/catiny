package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.GroupPost;
import com.regitiny.catiny.repository.GroupPostRepository;
import com.regitiny.catiny.repository.search.GroupPostSearchRepository;
import com.regitiny.catiny.service.criteria.GroupPostCriteria;
import com.regitiny.catiny.service.dto.GroupPostDTO;
import com.regitiny.catiny.service.mapper.GroupPostMapper;
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
 * Service for executing complex queries for {@link GroupPost} entities in the database.
 * The main input is a {@link GroupPostCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GroupPostDTO} or a {@link Page} of {@link GroupPostDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class GroupPostQueryService extends QueryService<GroupPost> {

  private final Logger log = LoggerFactory.getLogger(GroupPostQueryService.class);

  private final GroupPostRepository groupPostRepository;

  private final GroupPostMapper groupPostMapper;

  private final GroupPostSearchRepository groupPostSearchRepository;

  public GroupPostQueryService(
    GroupPostRepository groupPostRepository,
    GroupPostMapper groupPostMapper,
    GroupPostSearchRepository groupPostSearchRepository
  ) {
    this.groupPostRepository = groupPostRepository;
    this.groupPostMapper = groupPostMapper;
    this.groupPostSearchRepository = groupPostSearchRepository;
  }

  /**
   * Return a {@link List} of {@link GroupPostDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<GroupPostDTO> findByCriteria(GroupPostCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<GroupPost> specification = createSpecification(criteria);
    return groupPostMapper.toDto(groupPostRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link GroupPostDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<GroupPostDTO> findByCriteria(GroupPostCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<GroupPost> specification = createSpecification(criteria);
    return groupPostRepository.findAll(specification, page).map(groupPostMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(GroupPostCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<GroupPost> specification = createSpecification(criteria);
    return groupPostRepository.count(specification);
  }

  /**
   * Function to convert {@link GroupPostCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<GroupPost> createSpecification(GroupPostCriteria criteria) {
    Specification<GroupPost> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), GroupPost_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), GroupPost_.uuid));
      }
      if (criteria.getName() != null) {
        specification = specification.and(buildStringSpecification(criteria.getName(), GroupPost_.name));
      }
      if (criteria.getProfileId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getProfileId(), root -> root.join(GroupPost_.profile, JoinType.LEFT).get(GroupProfile_.id))
          );
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(GroupPost_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getMyPostInGroupId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMyPostInGroupId(), root -> root.join(GroupPost_.myPostInGroups, JoinType.LEFT).get(Post_.id))
          );
      }
      if (criteria.getTopicInterestId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getTopicInterestId(),
              root -> root.join(GroupPost_.topicInterests, JoinType.LEFT).get(TopicInterest_.id)
            )
          );
      }
      if (criteria.getUserInGroupId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getUserInGroupId(), root -> root.join(GroupPost_.userInGroups, JoinType.LEFT).get(MasterUser_.id))
          );
      }
    }
    return specification;
  }
}
