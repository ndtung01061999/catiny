package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.ProcessStatus;
import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.InstantFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.regitiny.catiny.domain.BaseInfo} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.BaseInfoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /base-infos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class BaseInfoCriteria implements Serializable, Criteria {

  /**
   * Class for filtering ProcessStatus
   */
  @GeneratedByJHipster
  public static class ProcessStatusFilter extends Filter<ProcessStatus> {

    public ProcessStatusFilter() {}

    public ProcessStatusFilter(ProcessStatusFilter filter) {
      super(filter);
    }

    @Override
    public ProcessStatusFilter copy() {
      return new ProcessStatusFilter(this);
    }
  }

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private ProcessStatusFilter processStatus;

  private StringFilter modifiedClass;

  private InstantFilter createdDate;

  private InstantFilter modifiedDate;

  private BooleanFilter deleted;

  private LongFilter priorityIndex;

  private LongFilter countUse;

  private LongFilter classInfoId;

  private LongFilter userProfileId;

  private LongFilter accountStatusId;

  private LongFilter deviceStatusId;

  private LongFilter friendId;

  private LongFilter followUserId;

  private LongFilter followGroupId;

  private LongFilter followPageId;

  private LongFilter fileInfoId;

  private LongFilter pagePostId;

  private LongFilter pageProfileId;

  private LongFilter groupPostId;

  private LongFilter postId;

  private LongFilter postCommentId;

  private LongFilter postLikeId;

  private LongFilter groupProfileId;

  private LongFilter newsFeedId;

  private LongFilter messageGroupId;

  private LongFilter messageContentId;

  private LongFilter rankUserId;

  private LongFilter rankGroupId;

  private LongFilter notificationId;

  private LongFilter albumId;

  private LongFilter videoId;

  private LongFilter imageId;

  private LongFilter videoStreamId;

  private LongFilter videoLiveStreamBufferId;

  private LongFilter topicInterestId;

  private LongFilter todoListId;

  private LongFilter eventId;

  private LongFilter createdById;

  private LongFilter modifiedById;

  private LongFilter ownerId;

  private LongFilter permissionId;

  public BaseInfoCriteria() {}

  public BaseInfoCriteria(BaseInfoCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.processStatus = other.processStatus == null ? null : other.processStatus.copy();
    this.modifiedClass = other.modifiedClass == null ? null : other.modifiedClass.copy();
    this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
    this.modifiedDate = other.modifiedDate == null ? null : other.modifiedDate.copy();
    this.deleted = other.deleted == null ? null : other.deleted.copy();
    this.priorityIndex = other.priorityIndex == null ? null : other.priorityIndex.copy();
    this.countUse = other.countUse == null ? null : other.countUse.copy();
    this.classInfoId = other.classInfoId == null ? null : other.classInfoId.copy();
    this.userProfileId = other.userProfileId == null ? null : other.userProfileId.copy();
    this.accountStatusId = other.accountStatusId == null ? null : other.accountStatusId.copy();
    this.deviceStatusId = other.deviceStatusId == null ? null : other.deviceStatusId.copy();
    this.friendId = other.friendId == null ? null : other.friendId.copy();
    this.followUserId = other.followUserId == null ? null : other.followUserId.copy();
    this.followGroupId = other.followGroupId == null ? null : other.followGroupId.copy();
    this.followPageId = other.followPageId == null ? null : other.followPageId.copy();
    this.fileInfoId = other.fileInfoId == null ? null : other.fileInfoId.copy();
    this.pagePostId = other.pagePostId == null ? null : other.pagePostId.copy();
    this.pageProfileId = other.pageProfileId == null ? null : other.pageProfileId.copy();
    this.groupPostId = other.groupPostId == null ? null : other.groupPostId.copy();
    this.postId = other.postId == null ? null : other.postId.copy();
    this.postCommentId = other.postCommentId == null ? null : other.postCommentId.copy();
    this.postLikeId = other.postLikeId == null ? null : other.postLikeId.copy();
    this.groupProfileId = other.groupProfileId == null ? null : other.groupProfileId.copy();
    this.newsFeedId = other.newsFeedId == null ? null : other.newsFeedId.copy();
    this.messageGroupId = other.messageGroupId == null ? null : other.messageGroupId.copy();
    this.messageContentId = other.messageContentId == null ? null : other.messageContentId.copy();
    this.rankUserId = other.rankUserId == null ? null : other.rankUserId.copy();
    this.rankGroupId = other.rankGroupId == null ? null : other.rankGroupId.copy();
    this.notificationId = other.notificationId == null ? null : other.notificationId.copy();
    this.albumId = other.albumId == null ? null : other.albumId.copy();
    this.videoId = other.videoId == null ? null : other.videoId.copy();
    this.imageId = other.imageId == null ? null : other.imageId.copy();
    this.videoStreamId = other.videoStreamId == null ? null : other.videoStreamId.copy();
    this.videoLiveStreamBufferId = other.videoLiveStreamBufferId == null ? null : other.videoLiveStreamBufferId.copy();
    this.topicInterestId = other.topicInterestId == null ? null : other.topicInterestId.copy();
    this.todoListId = other.todoListId == null ? null : other.todoListId.copy();
    this.eventId = other.eventId == null ? null : other.eventId.copy();
    this.createdById = other.createdById == null ? null : other.createdById.copy();
    this.modifiedById = other.modifiedById == null ? null : other.modifiedById.copy();
    this.ownerId = other.ownerId == null ? null : other.ownerId.copy();
    this.permissionId = other.permissionId == null ? null : other.permissionId.copy();
  }

  @Override
  public BaseInfoCriteria copy() {
    return new BaseInfoCriteria(this);
  }

  public LongFilter getId() {
    return id;
  }

  public LongFilter id() {
    if (id == null) {
      id = new LongFilter();
    }
    return id;
  }

  public void setId(LongFilter id) {
    this.id = id;
  }

  public ProcessStatusFilter getProcessStatus() {
    return processStatus;
  }

  public ProcessStatusFilter processStatus() {
    if (processStatus == null) {
      processStatus = new ProcessStatusFilter();
    }
    return processStatus;
  }

  public void setProcessStatus(ProcessStatusFilter processStatus) {
    this.processStatus = processStatus;
  }

  public StringFilter getModifiedClass() {
    return modifiedClass;
  }

  public StringFilter modifiedClass() {
    if (modifiedClass == null) {
      modifiedClass = new StringFilter();
    }
    return modifiedClass;
  }

  public void setModifiedClass(StringFilter modifiedClass) {
    this.modifiedClass = modifiedClass;
  }

  public InstantFilter getCreatedDate() {
    return createdDate;
  }

  public InstantFilter createdDate() {
    if (createdDate == null) {
      createdDate = new InstantFilter();
    }
    return createdDate;
  }

  public void setCreatedDate(InstantFilter createdDate) {
    this.createdDate = createdDate;
  }

  public InstantFilter getModifiedDate() {
    return modifiedDate;
  }

  public InstantFilter modifiedDate() {
    if (modifiedDate == null) {
      modifiedDate = new InstantFilter();
    }
    return modifiedDate;
  }

  public void setModifiedDate(InstantFilter modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public BooleanFilter getDeleted() {
    return deleted;
  }

  public BooleanFilter deleted() {
    if (deleted == null) {
      deleted = new BooleanFilter();
    }
    return deleted;
  }

  public void setDeleted(BooleanFilter deleted) {
    this.deleted = deleted;
  }

  public LongFilter getPriorityIndex() {
    return priorityIndex;
  }

  public LongFilter priorityIndex() {
    if (priorityIndex == null) {
      priorityIndex = new LongFilter();
    }
    return priorityIndex;
  }

  public void setPriorityIndex(LongFilter priorityIndex) {
    this.priorityIndex = priorityIndex;
  }

  public LongFilter getCountUse() {
    return countUse;
  }

  public LongFilter countUse() {
    if (countUse == null) {
      countUse = new LongFilter();
    }
    return countUse;
  }

  public void setCountUse(LongFilter countUse) {
    this.countUse = countUse;
  }

  public LongFilter getClassInfoId() {
    return classInfoId;
  }

  public LongFilter classInfoId() {
    if (classInfoId == null) {
      classInfoId = new LongFilter();
    }
    return classInfoId;
  }

  public void setClassInfoId(LongFilter classInfoId) {
    this.classInfoId = classInfoId;
  }

  public LongFilter getUserProfileId() {
    return userProfileId;
  }

  public LongFilter userProfileId() {
    if (userProfileId == null) {
      userProfileId = new LongFilter();
    }
    return userProfileId;
  }

  public void setUserProfileId(LongFilter userProfileId) {
    this.userProfileId = userProfileId;
  }

  public LongFilter getAccountStatusId() {
    return accountStatusId;
  }

  public LongFilter accountStatusId() {
    if (accountStatusId == null) {
      accountStatusId = new LongFilter();
    }
    return accountStatusId;
  }

  public void setAccountStatusId(LongFilter accountStatusId) {
    this.accountStatusId = accountStatusId;
  }

  public LongFilter getDeviceStatusId() {
    return deviceStatusId;
  }

  public LongFilter deviceStatusId() {
    if (deviceStatusId == null) {
      deviceStatusId = new LongFilter();
    }
    return deviceStatusId;
  }

  public void setDeviceStatusId(LongFilter deviceStatusId) {
    this.deviceStatusId = deviceStatusId;
  }

  public LongFilter getFriendId() {
    return friendId;
  }

  public LongFilter friendId() {
    if (friendId == null) {
      friendId = new LongFilter();
    }
    return friendId;
  }

  public void setFriendId(LongFilter friendId) {
    this.friendId = friendId;
  }

  public LongFilter getFollowUserId() {
    return followUserId;
  }

  public LongFilter followUserId() {
    if (followUserId == null) {
      followUserId = new LongFilter();
    }
    return followUserId;
  }

  public void setFollowUserId(LongFilter followUserId) {
    this.followUserId = followUserId;
  }

  public LongFilter getFollowGroupId() {
    return followGroupId;
  }

  public LongFilter followGroupId() {
    if (followGroupId == null) {
      followGroupId = new LongFilter();
    }
    return followGroupId;
  }

  public void setFollowGroupId(LongFilter followGroupId) {
    this.followGroupId = followGroupId;
  }

  public LongFilter getFollowPageId() {
    return followPageId;
  }

  public LongFilter followPageId() {
    if (followPageId == null) {
      followPageId = new LongFilter();
    }
    return followPageId;
  }

  public void setFollowPageId(LongFilter followPageId) {
    this.followPageId = followPageId;
  }

  public LongFilter getFileInfoId() {
    return fileInfoId;
  }

  public LongFilter fileInfoId() {
    if (fileInfoId == null) {
      fileInfoId = new LongFilter();
    }
    return fileInfoId;
  }

  public void setFileInfoId(LongFilter fileInfoId) {
    this.fileInfoId = fileInfoId;
  }

  public LongFilter getPagePostId() {
    return pagePostId;
  }

  public LongFilter pagePostId() {
    if (pagePostId == null) {
      pagePostId = new LongFilter();
    }
    return pagePostId;
  }

  public void setPagePostId(LongFilter pagePostId) {
    this.pagePostId = pagePostId;
  }

  public LongFilter getPageProfileId() {
    return pageProfileId;
  }

  public LongFilter pageProfileId() {
    if (pageProfileId == null) {
      pageProfileId = new LongFilter();
    }
    return pageProfileId;
  }

  public void setPageProfileId(LongFilter pageProfileId) {
    this.pageProfileId = pageProfileId;
  }

  public LongFilter getGroupPostId() {
    return groupPostId;
  }

  public LongFilter groupPostId() {
    if (groupPostId == null) {
      groupPostId = new LongFilter();
    }
    return groupPostId;
  }

  public void setGroupPostId(LongFilter groupPostId) {
    this.groupPostId = groupPostId;
  }

  public LongFilter getPostId() {
    return postId;
  }

  public LongFilter postId() {
    if (postId == null) {
      postId = new LongFilter();
    }
    return postId;
  }

  public void setPostId(LongFilter postId) {
    this.postId = postId;
  }

  public LongFilter getPostCommentId() {
    return postCommentId;
  }

  public LongFilter postCommentId() {
    if (postCommentId == null) {
      postCommentId = new LongFilter();
    }
    return postCommentId;
  }

  public void setPostCommentId(LongFilter postCommentId) {
    this.postCommentId = postCommentId;
  }

  public LongFilter getPostLikeId() {
    return postLikeId;
  }

  public LongFilter postLikeId() {
    if (postLikeId == null) {
      postLikeId = new LongFilter();
    }
    return postLikeId;
  }

  public void setPostLikeId(LongFilter postLikeId) {
    this.postLikeId = postLikeId;
  }

  public LongFilter getGroupProfileId() {
    return groupProfileId;
  }

  public LongFilter groupProfileId() {
    if (groupProfileId == null) {
      groupProfileId = new LongFilter();
    }
    return groupProfileId;
  }

  public void setGroupProfileId(LongFilter groupProfileId) {
    this.groupProfileId = groupProfileId;
  }

  public LongFilter getNewsFeedId() {
    return newsFeedId;
  }

  public LongFilter newsFeedId() {
    if (newsFeedId == null) {
      newsFeedId = new LongFilter();
    }
    return newsFeedId;
  }

  public void setNewsFeedId(LongFilter newsFeedId) {
    this.newsFeedId = newsFeedId;
  }

  public LongFilter getMessageGroupId() {
    return messageGroupId;
  }

  public LongFilter messageGroupId() {
    if (messageGroupId == null) {
      messageGroupId = new LongFilter();
    }
    return messageGroupId;
  }

  public void setMessageGroupId(LongFilter messageGroupId) {
    this.messageGroupId = messageGroupId;
  }

  public LongFilter getMessageContentId() {
    return messageContentId;
  }

  public LongFilter messageContentId() {
    if (messageContentId == null) {
      messageContentId = new LongFilter();
    }
    return messageContentId;
  }

  public void setMessageContentId(LongFilter messageContentId) {
    this.messageContentId = messageContentId;
  }

  public LongFilter getRankUserId() {
    return rankUserId;
  }

  public LongFilter rankUserId() {
    if (rankUserId == null) {
      rankUserId = new LongFilter();
    }
    return rankUserId;
  }

  public void setRankUserId(LongFilter rankUserId) {
    this.rankUserId = rankUserId;
  }

  public LongFilter getRankGroupId() {
    return rankGroupId;
  }

  public LongFilter rankGroupId() {
    if (rankGroupId == null) {
      rankGroupId = new LongFilter();
    }
    return rankGroupId;
  }

  public void setRankGroupId(LongFilter rankGroupId) {
    this.rankGroupId = rankGroupId;
  }

  public LongFilter getNotificationId() {
    return notificationId;
  }

  public LongFilter notificationId() {
    if (notificationId == null) {
      notificationId = new LongFilter();
    }
    return notificationId;
  }

  public void setNotificationId(LongFilter notificationId) {
    this.notificationId = notificationId;
  }

  public LongFilter getAlbumId() {
    return albumId;
  }

  public LongFilter albumId() {
    if (albumId == null) {
      albumId = new LongFilter();
    }
    return albumId;
  }

  public void setAlbumId(LongFilter albumId) {
    this.albumId = albumId;
  }

  public LongFilter getVideoId() {
    return videoId;
  }

  public LongFilter videoId() {
    if (videoId == null) {
      videoId = new LongFilter();
    }
    return videoId;
  }

  public void setVideoId(LongFilter videoId) {
    this.videoId = videoId;
  }

  public LongFilter getImageId() {
    return imageId;
  }

  public LongFilter imageId() {
    if (imageId == null) {
      imageId = new LongFilter();
    }
    return imageId;
  }

  public void setImageId(LongFilter imageId) {
    this.imageId = imageId;
  }

  public LongFilter getVideoStreamId() {
    return videoStreamId;
  }

  public LongFilter videoStreamId() {
    if (videoStreamId == null) {
      videoStreamId = new LongFilter();
    }
    return videoStreamId;
  }

  public void setVideoStreamId(LongFilter videoStreamId) {
    this.videoStreamId = videoStreamId;
  }

  public LongFilter getVideoLiveStreamBufferId() {
    return videoLiveStreamBufferId;
  }

  public LongFilter videoLiveStreamBufferId() {
    if (videoLiveStreamBufferId == null) {
      videoLiveStreamBufferId = new LongFilter();
    }
    return videoLiveStreamBufferId;
  }

  public void setVideoLiveStreamBufferId(LongFilter videoLiveStreamBufferId) {
    this.videoLiveStreamBufferId = videoLiveStreamBufferId;
  }

  public LongFilter getTopicInterestId() {
    return topicInterestId;
  }

  public LongFilter topicInterestId() {
    if (topicInterestId == null) {
      topicInterestId = new LongFilter();
    }
    return topicInterestId;
  }

  public void setTopicInterestId(LongFilter topicInterestId) {
    this.topicInterestId = topicInterestId;
  }

  public LongFilter getTodoListId() {
    return todoListId;
  }

  public LongFilter todoListId() {
    if (todoListId == null) {
      todoListId = new LongFilter();
    }
    return todoListId;
  }

  public void setTodoListId(LongFilter todoListId) {
    this.todoListId = todoListId;
  }

  public LongFilter getEventId() {
    return eventId;
  }

  public LongFilter eventId() {
    if (eventId == null) {
      eventId = new LongFilter();
    }
    return eventId;
  }

  public void setEventId(LongFilter eventId) {
    this.eventId = eventId;
  }

  public LongFilter getCreatedById() {
    return createdById;
  }

  public LongFilter createdById() {
    if (createdById == null) {
      createdById = new LongFilter();
    }
    return createdById;
  }

  public void setCreatedById(LongFilter createdById) {
    this.createdById = createdById;
  }

  public LongFilter getModifiedById() {
    return modifiedById;
  }

  public LongFilter modifiedById() {
    if (modifiedById == null) {
      modifiedById = new LongFilter();
    }
    return modifiedById;
  }

  public void setModifiedById(LongFilter modifiedById) {
    this.modifiedById = modifiedById;
  }

  public LongFilter getOwnerId() {
    return ownerId;
  }

  public LongFilter ownerId() {
    if (ownerId == null) {
      ownerId = new LongFilter();
    }
    return ownerId;
  }

  public void setOwnerId(LongFilter ownerId) {
    this.ownerId = ownerId;
  }

  public LongFilter getPermissionId() {
    return permissionId;
  }

  public LongFilter permissionId() {
    if (permissionId == null) {
      permissionId = new LongFilter();
    }
    return permissionId;
  }

  public void setPermissionId(LongFilter permissionId) {
    this.permissionId = permissionId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final BaseInfoCriteria that = (BaseInfoCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(processStatus, that.processStatus) &&
      Objects.equals(modifiedClass, that.modifiedClass) &&
      Objects.equals(createdDate, that.createdDate) &&
      Objects.equals(modifiedDate, that.modifiedDate) &&
      Objects.equals(deleted, that.deleted) &&
      Objects.equals(priorityIndex, that.priorityIndex) &&
      Objects.equals(countUse, that.countUse) &&
      Objects.equals(classInfoId, that.classInfoId) &&
      Objects.equals(userProfileId, that.userProfileId) &&
      Objects.equals(accountStatusId, that.accountStatusId) &&
      Objects.equals(deviceStatusId, that.deviceStatusId) &&
      Objects.equals(friendId, that.friendId) &&
      Objects.equals(followUserId, that.followUserId) &&
      Objects.equals(followGroupId, that.followGroupId) &&
      Objects.equals(followPageId, that.followPageId) &&
      Objects.equals(fileInfoId, that.fileInfoId) &&
      Objects.equals(pagePostId, that.pagePostId) &&
      Objects.equals(pageProfileId, that.pageProfileId) &&
      Objects.equals(groupPostId, that.groupPostId) &&
      Objects.equals(postId, that.postId) &&
      Objects.equals(postCommentId, that.postCommentId) &&
      Objects.equals(postLikeId, that.postLikeId) &&
      Objects.equals(groupProfileId, that.groupProfileId) &&
      Objects.equals(newsFeedId, that.newsFeedId) &&
      Objects.equals(messageGroupId, that.messageGroupId) &&
      Objects.equals(messageContentId, that.messageContentId) &&
      Objects.equals(rankUserId, that.rankUserId) &&
      Objects.equals(rankGroupId, that.rankGroupId) &&
      Objects.equals(notificationId, that.notificationId) &&
      Objects.equals(albumId, that.albumId) &&
      Objects.equals(videoId, that.videoId) &&
      Objects.equals(imageId, that.imageId) &&
      Objects.equals(videoStreamId, that.videoStreamId) &&
      Objects.equals(videoLiveStreamBufferId, that.videoLiveStreamBufferId) &&
      Objects.equals(topicInterestId, that.topicInterestId) &&
      Objects.equals(todoListId, that.todoListId) &&
      Objects.equals(eventId, that.eventId) &&
      Objects.equals(createdById, that.createdById) &&
      Objects.equals(modifiedById, that.modifiedById) &&
      Objects.equals(ownerId, that.ownerId) &&
      Objects.equals(permissionId, that.permissionId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(
      id,
      processStatus,
      modifiedClass,
      createdDate,
      modifiedDate,
      deleted,
      priorityIndex,
      countUse,
      classInfoId,
      userProfileId,
      accountStatusId,
      deviceStatusId,
      friendId,
      followUserId,
      followGroupId,
      followPageId,
      fileInfoId,
      pagePostId,
      pageProfileId,
      groupPostId,
      postId,
      postCommentId,
      postLikeId,
      groupProfileId,
      newsFeedId,
      messageGroupId,
      messageContentId,
      rankUserId,
      rankGroupId,
      notificationId,
      albumId,
      videoId,
      imageId,
      videoStreamId,
      videoLiveStreamBufferId,
      topicInterestId,
      todoListId,
      eventId,
      createdById,
      modifiedById,
      ownerId,
      permissionId
    );
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "BaseInfoCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (processStatus != null ? "processStatus=" + processStatus + ", " : "") +
            (modifiedClass != null ? "modifiedClass=" + modifiedClass + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (modifiedDate != null ? "modifiedDate=" + modifiedDate + ", " : "") +
            (deleted != null ? "deleted=" + deleted + ", " : "") +
            (priorityIndex != null ? "priorityIndex=" + priorityIndex + ", " : "") +
            (countUse != null ? "countUse=" + countUse + ", " : "") +
            (classInfoId != null ? "classInfoId=" + classInfoId + ", " : "") +
            (userProfileId != null ? "userProfileId=" + userProfileId + ", " : "") +
            (accountStatusId != null ? "accountStatusId=" + accountStatusId + ", " : "") +
            (deviceStatusId != null ? "deviceStatusId=" + deviceStatusId + ", " : "") +
            (friendId != null ? "friendId=" + friendId + ", " : "") +
            (followUserId != null ? "followUserId=" + followUserId + ", " : "") +
            (followGroupId != null ? "followGroupId=" + followGroupId + ", " : "") +
            (followPageId != null ? "followPageId=" + followPageId + ", " : "") +
            (fileInfoId != null ? "fileInfoId=" + fileInfoId + ", " : "") +
            (pagePostId != null ? "pagePostId=" + pagePostId + ", " : "") +
            (pageProfileId != null ? "pageProfileId=" + pageProfileId + ", " : "") +
            (groupPostId != null ? "groupPostId=" + groupPostId + ", " : "") +
            (postId != null ? "postId=" + postId + ", " : "") +
            (postCommentId != null ? "postCommentId=" + postCommentId + ", " : "") +
            (postLikeId != null ? "postLikeId=" + postLikeId + ", " : "") +
            (groupProfileId != null ? "groupProfileId=" + groupProfileId + ", " : "") +
            (newsFeedId != null ? "newsFeedId=" + newsFeedId + ", " : "") +
            (messageGroupId != null ? "messageGroupId=" + messageGroupId + ", " : "") +
            (messageContentId != null ? "messageContentId=" + messageContentId + ", " : "") +
            (rankUserId != null ? "rankUserId=" + rankUserId + ", " : "") +
            (rankGroupId != null ? "rankGroupId=" + rankGroupId + ", " : "") +
            (notificationId != null ? "notificationId=" + notificationId + ", " : "") +
            (albumId != null ? "albumId=" + albumId + ", " : "") +
            (videoId != null ? "videoId=" + videoId + ", " : "") +
            (imageId != null ? "imageId=" + imageId + ", " : "") +
            (videoStreamId != null ? "videoStreamId=" + videoStreamId + ", " : "") +
            (videoLiveStreamBufferId != null ? "videoLiveStreamBufferId=" + videoLiveStreamBufferId + ", " : "") +
            (topicInterestId != null ? "topicInterestId=" + topicInterestId + ", " : "") +
            (todoListId != null ? "todoListId=" + todoListId + ", " : "") +
            (eventId != null ? "eventId=" + eventId + ", " : "") +
            (createdById != null ? "createdById=" + createdById + ", " : "") +
            (modifiedById != null ? "modifiedById=" + modifiedById + ", " : "") +
            (ownerId != null ? "ownerId=" + ownerId + ", " : "") +
            (permissionId != null ? "permissionId=" + permissionId + ", " : "") +
            "}";
    }
}
