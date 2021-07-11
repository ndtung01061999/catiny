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
      if (criteria.getMyProfileId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMyProfileId(), root -> root.join(MasterUser_.myProfile, JoinType.LEFT).get(UserProfile_.id))
          );
      }
      if (criteria.getMyAccountStatusId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getMyAccountStatusId(),
              root -> root.join(MasterUser_.myAccountStatus, JoinType.LEFT).get(AccountStatus_.id)
            )
          );
      }
      if (criteria.getMyRankId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMyRankId(), root -> root.join(MasterUser_.myRank, JoinType.LEFT).get(RankUser_.id))
          );
      }
      if (criteria.getAvatarId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getAvatarId(), root -> root.join(MasterUser_.avatar, JoinType.LEFT).get(Image_.id))
          );
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(MasterUser_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getMyPageId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMyPageId(), root -> root.join(MasterUser_.myPages, JoinType.LEFT).get(PagePost_.id))
          );
      }
      if (criteria.getMyFileId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMyFileId(), root -> root.join(MasterUser_.myFiles, JoinType.LEFT).get(FileInfo_.id))
          );
      }
      if (criteria.getMyNotificationId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getMyNotificationId(),
              root -> root.join(MasterUser_.myNotifications, JoinType.LEFT).get(Notification_.id)
            )
          );
      }
      if (criteria.getMyFriendId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMyFriendId(), root -> root.join(MasterUser_.myFriends, JoinType.LEFT).get(Friend_.id))
          );
      }
      if (criteria.getMyFollowUserId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getMyFollowUserId(),
              root -> root.join(MasterUser_.myFollowUsers, JoinType.LEFT).get(FollowUser_.id)
            )
          );
      }
      if (criteria.getMyFollowGroupId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getMyFollowGroupId(),
              root -> root.join(MasterUser_.myFollowGroups, JoinType.LEFT).get(FollowGroup_.id)
            )
          );
      }
      if (criteria.getMyFollowPageId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getMyFollowPageId(),
              root -> root.join(MasterUser_.myFollowPages, JoinType.LEFT).get(FollowPage_.id)
            )
          );
      }
      if (criteria.getMyNewsFeedId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMyNewsFeedId(), root -> root.join(MasterUser_.myNewsFeeds, JoinType.LEFT).get(NewsFeed_.id))
          );
      }
      if (criteria.getMyTodoListId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMyTodoListId(), root -> root.join(MasterUser_.myTodoLists, JoinType.LEFT).get(TodoList_.id))
          );
      }
      if (criteria.getMyPostId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMyPostId(), root -> root.join(MasterUser_.myPosts, JoinType.LEFT).get(Post_.id))
          );
      }
      if (criteria.getMyGroupPostId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMyGroupPostId(), root -> root.join(MasterUser_.myGroupPosts, JoinType.LEFT).get(GroupPost_.id))
          );
      }
      if (criteria.getMessageGroupId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getMessageGroupId(),
              root -> root.join(MasterUser_.messageGroups, JoinType.LEFT).get(MessageGroup_.id)
            )
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
      if (criteria.getMyLikeId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMyLikeId(), root -> root.join(MasterUser_.myLikes, JoinType.LEFT).get(PostLike_.id))
          );
      }
      if (criteria.getMyCommentId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMyCommentId(), root -> root.join(MasterUser_.myComments, JoinType.LEFT).get(PostComment_.id))
          );
      }
    }
    return specification;
  }
}
