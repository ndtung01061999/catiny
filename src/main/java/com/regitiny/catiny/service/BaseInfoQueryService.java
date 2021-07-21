package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.repository.BaseInfoRepository;
import com.regitiny.catiny.repository.search.BaseInfoSearchRepository;
import com.regitiny.catiny.service.criteria.BaseInfoCriteria;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.mapper.BaseInfoMapper;
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
 * Service for executing complex queries for {@link BaseInfo} entities in the database.
 * The main input is a {@link BaseInfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BaseInfoDTO} or a {@link Page} of {@link BaseInfoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class BaseInfoQueryService extends QueryService<BaseInfo> {

  private final Logger log = LoggerFactory.getLogger(BaseInfoQueryService.class);

  private final BaseInfoRepository baseInfoRepository;

  private final BaseInfoMapper baseInfoMapper;

  private final BaseInfoSearchRepository baseInfoSearchRepository;

  public BaseInfoQueryService(
    BaseInfoRepository baseInfoRepository,
    BaseInfoMapper baseInfoMapper,
    BaseInfoSearchRepository baseInfoSearchRepository
  ) {
    this.baseInfoRepository = baseInfoRepository;
    this.baseInfoMapper = baseInfoMapper;
    this.baseInfoSearchRepository = baseInfoSearchRepository;
  }

  /**
   * Return a {@link List} of {@link BaseInfoDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findByCriteria(BaseInfoCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<BaseInfo> specification = createSpecification(criteria);
    return baseInfoMapper.toDto(baseInfoRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link BaseInfoDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<BaseInfoDTO> findByCriteria(BaseInfoCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<BaseInfo> specification = createSpecification(criteria);
    return baseInfoRepository.findAll(specification, page).map(baseInfoMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(BaseInfoCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<BaseInfo> specification = createSpecification(criteria);
    return baseInfoRepository.count(specification);
  }

  /**
   * Function to convert {@link BaseInfoCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<BaseInfo> createSpecification(BaseInfoCriteria criteria) {
    Specification<BaseInfo> specification = Specification.where(null);
    if (criteria != null)
    {
      if (criteria.getId() != null)
      {
        specification = specification.and(buildRangeSpecification(criteria.getId(), BaseInfo_.id));
      }
      if (criteria.getUuid() != null)
      {
        specification = specification.and(buildSpecification(criteria.getUuid(), BaseInfo_.uuid));
      }
      if (criteria.getProcessStatus() != null)
      {
        specification = specification.and(buildSpecification(criteria.getProcessStatus(), BaseInfo_.processStatus));
      }
      if (criteria.getModifiedClass() != null)
      {
        specification = specification.and(buildStringSpecification(criteria.getModifiedClass(), BaseInfo_.modifiedClass));
      }
      if (criteria.getCreatedDate() != null)
      {
        specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), BaseInfo_.createdDate));
      }
      if (criteria.getModifiedDate() != null)
      {
        specification = specification.and(buildRangeSpecification(criteria.getModifiedDate(), BaseInfo_.modifiedDate));
      }
      if (criteria.getDeleted() != null)
      {
        specification = specification.and(buildSpecification(criteria.getDeleted(), BaseInfo_.deleted));
      }
      if (criteria.getPriorityIndex() != null)
      {
        specification = specification.and(buildRangeSpecification(criteria.getPriorityIndex(), BaseInfo_.priorityIndex));
      }
      if (criteria.getCountUse() != null)
      {
        specification = specification.and(buildRangeSpecification(criteria.getCountUse(), BaseInfo_.countUse));
      }
      if (criteria.getHistoryUpdateId() != null)
      {
        specification =
          specification.and(
            buildSpecification(
              criteria.getHistoryUpdateId(),
              root -> root.join(BaseInfo_.historyUpdates, JoinType.LEFT).get(HistoryUpdate_.id)
            )
          );
      }
      if (criteria.getClassInfoId() != null)
      {
        specification =
          specification.and(
            buildSpecification(criteria.getClassInfoId(), root -> root.join(BaseInfo_.classInfo, JoinType.LEFT).get(ClassInfo_.id))
          );
      }
      if (criteria.getUserProfileId() != null)
      {
        specification =
          specification.and(
            buildSpecification(criteria.getUserProfileId(), root -> root.join(BaseInfo_.userProfile, JoinType.LEFT).get(UserProfile_.id))
          );
      }
      if (criteria.getAccountStatusId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getAccountStatusId(),
              root -> root.join(BaseInfo_.accountStatus, JoinType.LEFT).get(AccountStatus_.id)
            )
          );
      }
      if (criteria.getDeviceStatusId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getDeviceStatusId(), root -> root.join(BaseInfo_.deviceStatus, JoinType.LEFT).get(DeviceStatus_.id))
          );
      }
      if (criteria.getFriendId() != null) {
        specification =
          specification.and(buildSpecification(criteria.getFriendId(), root -> root.join(BaseInfo_.friend, JoinType.LEFT).get(Friend_.id)));
      }
      if (criteria.getFollowUserId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getFollowUserId(), root -> root.join(BaseInfo_.followUser, JoinType.LEFT).get(FollowUser_.id))
          );
      }
      if (criteria.getFollowGroupId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getFollowGroupId(), root -> root.join(BaseInfo_.followGroup, JoinType.LEFT).get(FollowGroup_.id))
          );
      }
      if (criteria.getFollowPageId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getFollowPageId(), root -> root.join(BaseInfo_.followPage, JoinType.LEFT).get(FollowPage_.id))
          );
      }
      if (criteria.getFileInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getFileInfoId(), root -> root.join(BaseInfo_.fileInfo, JoinType.LEFT).get(FileInfo_.id))
          );
      }
      if (criteria.getPagePostId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPagePostId(), root -> root.join(BaseInfo_.pagePost, JoinType.LEFT).get(PagePost_.id))
          );
      }
      if (criteria.getPageProfileId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPageProfileId(), root -> root.join(BaseInfo_.pageProfile, JoinType.LEFT).get(PageProfile_.id))
          );
      }
      if (criteria.getGroupPostId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getGroupPostId(), root -> root.join(BaseInfo_.groupPost, JoinType.LEFT).get(GroupPost_.id))
          );
      }
      if (criteria.getPostId() != null) {
        specification =
          specification.and(buildSpecification(criteria.getPostId(), root -> root.join(BaseInfo_.post, JoinType.LEFT).get(Post_.id)));
      }
      if (criteria.getPostCommentId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPostCommentId(), root -> root.join(BaseInfo_.postComment, JoinType.LEFT).get(PostComment_.id))
          );
      }
      if (criteria.getPostLikeId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPostLikeId(), root -> root.join(BaseInfo_.postLike, JoinType.LEFT).get(PostLike_.id))
          );
      }
      if (criteria.getGroupProfileId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getGroupProfileId(), root -> root.join(BaseInfo_.groupProfile, JoinType.LEFT).get(GroupProfile_.id))
          );
      }
      if (criteria.getNewsFeedId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getNewsFeedId(), root -> root.join(BaseInfo_.newsFeed, JoinType.LEFT).get(NewsFeed_.id))
          );
      }
      if (criteria.getMessageGroupId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMessageGroupId(), root -> root.join(BaseInfo_.messageGroup, JoinType.LEFT).get(MessageGroup_.id))
          );
      }
      if (criteria.getMessageContentId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getMessageContentId(),
              root -> root.join(BaseInfo_.messageContent, JoinType.LEFT).get(MessageContent_.id)
            )
          );
      }
      if (criteria.getRankUserId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getRankUserId(), root -> root.join(BaseInfo_.rankUser, JoinType.LEFT).get(RankUser_.id))
          );
      }
      if (criteria.getRankGroupId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getRankGroupId(), root -> root.join(BaseInfo_.rankGroup, JoinType.LEFT).get(RankGroup_.id))
          );
      }
      if (criteria.getNotificationId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getNotificationId(), root -> root.join(BaseInfo_.notification, JoinType.LEFT).get(Notification_.id))
          );
      }
      if (criteria.getAlbumId() != null) {
        specification =
          specification.and(buildSpecification(criteria.getAlbumId(), root -> root.join(BaseInfo_.album, JoinType.LEFT).get(Album_.id)));
      }
      if (criteria.getVideoId() != null) {
        specification =
          specification.and(buildSpecification(criteria.getVideoId(), root -> root.join(BaseInfo_.video, JoinType.LEFT).get(Video_.id)));
      }
      if (criteria.getImageId() != null) {
        specification =
          specification.and(buildSpecification(criteria.getImageId(), root -> root.join(BaseInfo_.image, JoinType.LEFT).get(Image_.id)));
      }
      if (criteria.getVideoStreamId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getVideoStreamId(), root -> root.join(BaseInfo_.videoStream, JoinType.LEFT).get(VideoStream_.id))
          );
      }
      if (criteria.getVideoLiveStreamBufferId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getVideoLiveStreamBufferId(),
              root -> root.join(BaseInfo_.videoLiveStreamBuffer, JoinType.LEFT).get(VideoLiveStreamBuffer_.id)
            )
          );
      }
      if (criteria.getTopicInterestId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getTopicInterestId(),
              root -> root.join(BaseInfo_.topicInterest, JoinType.LEFT).get(TopicInterest_.id)
            )
          );
      }
      if (criteria.getTodoListId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getTodoListId(), root -> root.join(BaseInfo_.todoList, JoinType.LEFT).get(TodoList_.id))
          );
      }
      if (criteria.getEventId() != null) {
        specification =
          specification.and(buildSpecification(criteria.getEventId(), root -> root.join(BaseInfo_.event, JoinType.LEFT).get(Event_.id)));
      }
      if (criteria.getCreatedById() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getCreatedById(), root -> root.join(BaseInfo_.createdBy, JoinType.LEFT).get(MasterUser_.id))
          );
      }
      if (criteria.getModifiedById() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getModifiedById(), root -> root.join(BaseInfo_.modifiedBy, JoinType.LEFT).get(MasterUser_.id))
          );
      }
      if (criteria.getOwnerId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getOwnerId(), root -> root.join(BaseInfo_.owner, JoinType.LEFT).get(MasterUser_.id))
          );
      }
      if (criteria.getPermissionId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPermissionId(), root -> root.join(BaseInfo_.permissions, JoinType.LEFT).get(Permission_.id))
          );
      }
    }
    return specification;
  }
}
