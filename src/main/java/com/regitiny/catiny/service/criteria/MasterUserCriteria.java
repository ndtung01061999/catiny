package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;
import tech.jhipster.service.filter.UUIDFilter;

/**
 * Criteria class for the {@link com.regitiny.catiny.domain.MasterUser} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.MasterUserResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /master-users?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class MasterUserCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter fullName;

  private StringFilter nickname;

  private LongFilter userId;

  private LongFilter myProfileId;

  private LongFilter myAccountStatusId;

  private LongFilter myRankId;

  private LongFilter avatarId;

  private LongFilter baseInfoId;

  private LongFilter myPageId;

  private LongFilter myFileId;

  private LongFilter myNotificationId;

  private LongFilter myFriendId;

  private LongFilter myFollowUserId;

  private LongFilter myFollowGroupId;

  private LongFilter myFollowPageId;

  private LongFilter myNewsFeedId;

  private LongFilter myTodoListId;

  private LongFilter myPostId;

  private LongFilter myGroupPostId;

  private LongFilter messageGroupId;

  private LongFilter topicInterestId;

  private LongFilter myLikeId;

  private LongFilter myCommentId;

  public MasterUserCriteria() {}

  public MasterUserCriteria(MasterUserCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.fullName = other.fullName == null ? null : other.fullName.copy();
    this.nickname = other.nickname == null ? null : other.nickname.copy();
    this.userId = other.userId == null ? null : other.userId.copy();
    this.myProfileId = other.myProfileId == null ? null : other.myProfileId.copy();
    this.myAccountStatusId = other.myAccountStatusId == null ? null : other.myAccountStatusId.copy();
    this.myRankId = other.myRankId == null ? null : other.myRankId.copy();
    this.avatarId = other.avatarId == null ? null : other.avatarId.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.myPageId = other.myPageId == null ? null : other.myPageId.copy();
    this.myFileId = other.myFileId == null ? null : other.myFileId.copy();
    this.myNotificationId = other.myNotificationId == null ? null : other.myNotificationId.copy();
    this.myFriendId = other.myFriendId == null ? null : other.myFriendId.copy();
    this.myFollowUserId = other.myFollowUserId == null ? null : other.myFollowUserId.copy();
    this.myFollowGroupId = other.myFollowGroupId == null ? null : other.myFollowGroupId.copy();
    this.myFollowPageId = other.myFollowPageId == null ? null : other.myFollowPageId.copy();
    this.myNewsFeedId = other.myNewsFeedId == null ? null : other.myNewsFeedId.copy();
    this.myTodoListId = other.myTodoListId == null ? null : other.myTodoListId.copy();
    this.myPostId = other.myPostId == null ? null : other.myPostId.copy();
    this.myGroupPostId = other.myGroupPostId == null ? null : other.myGroupPostId.copy();
    this.messageGroupId = other.messageGroupId == null ? null : other.messageGroupId.copy();
    this.topicInterestId = other.topicInterestId == null ? null : other.topicInterestId.copy();
    this.myLikeId = other.myLikeId == null ? null : other.myLikeId.copy();
    this.myCommentId = other.myCommentId == null ? null : other.myCommentId.copy();
  }

  @Override
  public MasterUserCriteria copy() {
    return new MasterUserCriteria(this);
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

  public UUIDFilter getUuid() {
    return uuid;
  }

  public UUIDFilter uuid() {
    if (uuid == null) {
      uuid = new UUIDFilter();
    }
    return uuid;
  }

  public void setUuid(UUIDFilter uuid) {
    this.uuid = uuid;
  }

  public StringFilter getFullName() {
    return fullName;
  }

  public StringFilter fullName() {
    if (fullName == null) {
      fullName = new StringFilter();
    }
    return fullName;
  }

  public void setFullName(StringFilter fullName) {
    this.fullName = fullName;
  }

  public StringFilter getNickname() {
    return nickname;
  }

  public StringFilter nickname() {
    if (nickname == null) {
      nickname = new StringFilter();
    }
    return nickname;
  }

  public void setNickname(StringFilter nickname) {
    this.nickname = nickname;
  }

  public LongFilter getUserId() {
    return userId;
  }

  public LongFilter userId() {
    if (userId == null) {
      userId = new LongFilter();
    }
    return userId;
  }

  public void setUserId(LongFilter userId) {
    this.userId = userId;
  }

  public LongFilter getMyProfileId() {
    return myProfileId;
  }

  public LongFilter myProfileId() {
    if (myProfileId == null) {
      myProfileId = new LongFilter();
    }
    return myProfileId;
  }

  public void setMyProfileId(LongFilter myProfileId) {
    this.myProfileId = myProfileId;
  }

  public LongFilter getMyAccountStatusId() {
    return myAccountStatusId;
  }

  public LongFilter myAccountStatusId() {
    if (myAccountStatusId == null) {
      myAccountStatusId = new LongFilter();
    }
    return myAccountStatusId;
  }

  public void setMyAccountStatusId(LongFilter myAccountStatusId) {
    this.myAccountStatusId = myAccountStatusId;
  }

  public LongFilter getMyRankId() {
    return myRankId;
  }

  public LongFilter myRankId() {
    if (myRankId == null) {
      myRankId = new LongFilter();
    }
    return myRankId;
  }

  public void setMyRankId(LongFilter myRankId) {
    this.myRankId = myRankId;
  }

  public LongFilter getAvatarId() {
    return avatarId;
  }

  public LongFilter avatarId() {
    if (avatarId == null) {
      avatarId = new LongFilter();
    }
    return avatarId;
  }

  public void setAvatarId(LongFilter avatarId) {
    this.avatarId = avatarId;
  }

  public LongFilter getBaseInfoId() {
    return baseInfoId;
  }

  public LongFilter baseInfoId() {
    if (baseInfoId == null) {
      baseInfoId = new LongFilter();
    }
    return baseInfoId;
  }

  public void setBaseInfoId(LongFilter baseInfoId) {
    this.baseInfoId = baseInfoId;
  }

  public LongFilter getMyPageId() {
    return myPageId;
  }

  public LongFilter myPageId() {
    if (myPageId == null) {
      myPageId = new LongFilter();
    }
    return myPageId;
  }

  public void setMyPageId(LongFilter myPageId) {
    this.myPageId = myPageId;
  }

  public LongFilter getMyFileId() {
    return myFileId;
  }

  public LongFilter myFileId() {
    if (myFileId == null) {
      myFileId = new LongFilter();
    }
    return myFileId;
  }

  public void setMyFileId(LongFilter myFileId) {
    this.myFileId = myFileId;
  }

  public LongFilter getMyNotificationId() {
    return myNotificationId;
  }

  public LongFilter myNotificationId() {
    if (myNotificationId == null) {
      myNotificationId = new LongFilter();
    }
    return myNotificationId;
  }

  public void setMyNotificationId(LongFilter myNotificationId) {
    this.myNotificationId = myNotificationId;
  }

  public LongFilter getMyFriendId() {
    return myFriendId;
  }

  public LongFilter myFriendId() {
    if (myFriendId == null) {
      myFriendId = new LongFilter();
    }
    return myFriendId;
  }

  public void setMyFriendId(LongFilter myFriendId) {
    this.myFriendId = myFriendId;
  }

  public LongFilter getMyFollowUserId() {
    return myFollowUserId;
  }

  public LongFilter myFollowUserId() {
    if (myFollowUserId == null) {
      myFollowUserId = new LongFilter();
    }
    return myFollowUserId;
  }

  public void setMyFollowUserId(LongFilter myFollowUserId) {
    this.myFollowUserId = myFollowUserId;
  }

  public LongFilter getMyFollowGroupId() {
    return myFollowGroupId;
  }

  public LongFilter myFollowGroupId() {
    if (myFollowGroupId == null) {
      myFollowGroupId = new LongFilter();
    }
    return myFollowGroupId;
  }

  public void setMyFollowGroupId(LongFilter myFollowGroupId) {
    this.myFollowGroupId = myFollowGroupId;
  }

  public LongFilter getMyFollowPageId() {
    return myFollowPageId;
  }

  public LongFilter myFollowPageId() {
    if (myFollowPageId == null) {
      myFollowPageId = new LongFilter();
    }
    return myFollowPageId;
  }

  public void setMyFollowPageId(LongFilter myFollowPageId) {
    this.myFollowPageId = myFollowPageId;
  }

  public LongFilter getMyNewsFeedId() {
    return myNewsFeedId;
  }

  public LongFilter myNewsFeedId() {
    if (myNewsFeedId == null) {
      myNewsFeedId = new LongFilter();
    }
    return myNewsFeedId;
  }

  public void setMyNewsFeedId(LongFilter myNewsFeedId) {
    this.myNewsFeedId = myNewsFeedId;
  }

  public LongFilter getMyTodoListId() {
    return myTodoListId;
  }

  public LongFilter myTodoListId() {
    if (myTodoListId == null) {
      myTodoListId = new LongFilter();
    }
    return myTodoListId;
  }

  public void setMyTodoListId(LongFilter myTodoListId) {
    this.myTodoListId = myTodoListId;
  }

  public LongFilter getMyPostId() {
    return myPostId;
  }

  public LongFilter myPostId() {
    if (myPostId == null) {
      myPostId = new LongFilter();
    }
    return myPostId;
  }

  public void setMyPostId(LongFilter myPostId) {
    this.myPostId = myPostId;
  }

  public LongFilter getMyGroupPostId() {
    return myGroupPostId;
  }

  public LongFilter myGroupPostId() {
    if (myGroupPostId == null) {
      myGroupPostId = new LongFilter();
    }
    return myGroupPostId;
  }

  public void setMyGroupPostId(LongFilter myGroupPostId) {
    this.myGroupPostId = myGroupPostId;
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

  public LongFilter getMyLikeId() {
    return myLikeId;
  }

  public LongFilter myLikeId() {
    if (myLikeId == null) {
      myLikeId = new LongFilter();
    }
    return myLikeId;
  }

  public void setMyLikeId(LongFilter myLikeId) {
    this.myLikeId = myLikeId;
  }

  public LongFilter getMyCommentId() {
    return myCommentId;
  }

  public LongFilter myCommentId() {
    if (myCommentId == null) {
      myCommentId = new LongFilter();
    }
    return myCommentId;
  }

  public void setMyCommentId(LongFilter myCommentId) {
    this.myCommentId = myCommentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final MasterUserCriteria that = (MasterUserCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(fullName, that.fullName) &&
      Objects.equals(nickname, that.nickname) &&
      Objects.equals(userId, that.userId) &&
      Objects.equals(myProfileId, that.myProfileId) &&
      Objects.equals(myAccountStatusId, that.myAccountStatusId) &&
      Objects.equals(myRankId, that.myRankId) &&
      Objects.equals(avatarId, that.avatarId) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(myPageId, that.myPageId) &&
      Objects.equals(myFileId, that.myFileId) &&
      Objects.equals(myNotificationId, that.myNotificationId) &&
      Objects.equals(myFriendId, that.myFriendId) &&
      Objects.equals(myFollowUserId, that.myFollowUserId) &&
      Objects.equals(myFollowGroupId, that.myFollowGroupId) &&
      Objects.equals(myFollowPageId, that.myFollowPageId) &&
      Objects.equals(myNewsFeedId, that.myNewsFeedId) &&
      Objects.equals(myTodoListId, that.myTodoListId) &&
      Objects.equals(myPostId, that.myPostId) &&
      Objects.equals(myGroupPostId, that.myGroupPostId) &&
      Objects.equals(messageGroupId, that.messageGroupId) &&
      Objects.equals(topicInterestId, that.topicInterestId) &&
      Objects.equals(myLikeId, that.myLikeId) &&
      Objects.equals(myCommentId, that.myCommentId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(
      id,
      uuid,
      fullName,
      nickname,
      userId,
      myProfileId,
      myAccountStatusId,
      myRankId,
      avatarId,
      baseInfoId,
      myPageId,
      myFileId,
      myNotificationId,
      myFriendId,
      myFollowUserId,
      myFollowGroupId,
      myFollowPageId,
      myNewsFeedId,
      myTodoListId,
      myPostId,
      myGroupPostId,
      messageGroupId,
      topicInterestId,
      myLikeId,
      myCommentId
    );
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MasterUserCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (fullName != null ? "fullName=" + fullName + ", " : "") +
            (nickname != null ? "nickname=" + nickname + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (myProfileId != null ? "myProfileId=" + myProfileId + ", " : "") +
            (myAccountStatusId != null ? "myAccountStatusId=" + myAccountStatusId + ", " : "") +
            (myRankId != null ? "myRankId=" + myRankId + ", " : "") +
            (avatarId != null ? "avatarId=" + avatarId + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (myPageId != null ? "myPageId=" + myPageId + ", " : "") +
            (myFileId != null ? "myFileId=" + myFileId + ", " : "") +
            (myNotificationId != null ? "myNotificationId=" + myNotificationId + ", " : "") +
            (myFriendId != null ? "myFriendId=" + myFriendId + ", " : "") +
            (myFollowUserId != null ? "myFollowUserId=" + myFollowUserId + ", " : "") +
            (myFollowGroupId != null ? "myFollowGroupId=" + myFollowGroupId + ", " : "") +
            (myFollowPageId != null ? "myFollowPageId=" + myFollowPageId + ", " : "") +
            (myNewsFeedId != null ? "myNewsFeedId=" + myNewsFeedId + ", " : "") +
            (myTodoListId != null ? "myTodoListId=" + myTodoListId + ", " : "") +
            (myPostId != null ? "myPostId=" + myPostId + ", " : "") +
            (myGroupPostId != null ? "myGroupPostId=" + myGroupPostId + ", " : "") +
            (messageGroupId != null ? "messageGroupId=" + messageGroupId + ", " : "") +
            (topicInterestId != null ? "topicInterestId=" + topicInterestId + ", " : "") +
            (myLikeId != null ? "myLikeId=" + myLikeId + ", " : "") +
            (myCommentId != null ? "myCommentId=" + myCommentId + ", " : "") +
            "}";
    }
}
