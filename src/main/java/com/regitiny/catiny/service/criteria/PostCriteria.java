package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.PostInType;
import com.regitiny.catiny.domain.enumeration.PostType;
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
 * Criteria class for the {@link com.regitiny.catiny.domain.Post} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.PostResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /posts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class PostCriteria implements Serializable, Criteria {

  /**
   * Class for filtering PostInType
   */
  @GeneratedByJHipster
  public static class PostInTypeFilter extends Filter<PostInType> {

    public PostInTypeFilter() {}

    public PostInTypeFilter(PostInTypeFilter filter) {
      super(filter);
    }

    @Override
    public PostInTypeFilter copy() {
      return new PostInTypeFilter(this);
    }
  }

  /**
   * Class for filtering PostType
   */
  @GeneratedByJHipster
  public static class PostTypeFilter extends Filter<PostType> {

    public PostTypeFilter() {}

    public PostTypeFilter(PostTypeFilter filter) {
      super(filter);
    }

    @Override
    public PostTypeFilter copy() {
      return new PostTypeFilter(this);
    }
  }

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private PostInTypeFilter postInType;

  private PostTypeFilter postType;

  private LongFilter baseInfoId;

  private LongFilter postCommentId;

  private LongFilter postLikeId;

  private LongFilter postShareChildrenId;

  private LongFilter groupPostId;

  private LongFilter pagePostId;

  private LongFilter postShareParentId;

  private LongFilter posterId;

  private LongFilter newsFeedId;

  private LongFilter topicInterestId;

  public PostCriteria() {}

  public PostCriteria(PostCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.postInType = other.postInType == null ? null : other.postInType.copy();
    this.postType = other.postType == null ? null : other.postType.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.postCommentId = other.postCommentId == null ? null : other.postCommentId.copy();
    this.postLikeId = other.postLikeId == null ? null : other.postLikeId.copy();
    this.postShareChildrenId = other.postShareChildrenId == null ? null : other.postShareChildrenId.copy();
    this.groupPostId = other.groupPostId == null ? null : other.groupPostId.copy();
    this.pagePostId = other.pagePostId == null ? null : other.pagePostId.copy();
    this.postShareParentId = other.postShareParentId == null ? null : other.postShareParentId.copy();
    this.posterId = other.posterId == null ? null : other.posterId.copy();
    this.newsFeedId = other.newsFeedId == null ? null : other.newsFeedId.copy();
    this.topicInterestId = other.topicInterestId == null ? null : other.topicInterestId.copy();
  }

  @Override
  public PostCriteria copy() {
    return new PostCriteria(this);
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

  public PostInTypeFilter getPostInType() {
    return postInType;
  }

  public PostInTypeFilter postInType() {
    if (postInType == null) {
      postInType = new PostInTypeFilter();
    }
    return postInType;
  }

  public void setPostInType(PostInTypeFilter postInType) {
    this.postInType = postInType;
  }

  public PostTypeFilter getPostType() {
    return postType;
  }

  public PostTypeFilter postType() {
    if (postType == null) {
      postType = new PostTypeFilter();
    }
    return postType;
  }

  public void setPostType(PostTypeFilter postType) {
    this.postType = postType;
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

  public LongFilter getPostShareChildrenId() {
    return postShareChildrenId;
  }

  public LongFilter postShareChildrenId() {
    if (postShareChildrenId == null) {
      postShareChildrenId = new LongFilter();
    }
    return postShareChildrenId;
  }

  public void setPostShareChildrenId(LongFilter postShareChildrenId) {
    this.postShareChildrenId = postShareChildrenId;
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

  public LongFilter getPostShareParentId() {
    return postShareParentId;
  }

  public LongFilter postShareParentId() {
    if (postShareParentId == null) {
      postShareParentId = new LongFilter();
    }
    return postShareParentId;
  }

  public void setPostShareParentId(LongFilter postShareParentId) {
    this.postShareParentId = postShareParentId;
  }

  public LongFilter getPosterId() {
    return posterId;
  }

  public LongFilter posterId() {
    if (posterId == null) {
      posterId = new LongFilter();
    }
    return posterId;
  }

  public void setPosterId(LongFilter posterId) {
    this.posterId = posterId;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PostCriteria that = (PostCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(postInType, that.postInType) &&
      Objects.equals(postType, that.postType) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(postCommentId, that.postCommentId) &&
      Objects.equals(postLikeId, that.postLikeId) &&
      Objects.equals(postShareChildrenId, that.postShareChildrenId) &&
      Objects.equals(groupPostId, that.groupPostId) &&
      Objects.equals(pagePostId, that.pagePostId) &&
      Objects.equals(postShareParentId, that.postShareParentId) &&
      Objects.equals(posterId, that.posterId) &&
      Objects.equals(newsFeedId, that.newsFeedId) &&
      Objects.equals(topicInterestId, that.topicInterestId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(
      id,
      uuid,
      postInType,
      postType,
      baseInfoId,
      postCommentId,
      postLikeId,
      postShareChildrenId,
      groupPostId,
      pagePostId,
      postShareParentId,
      posterId,
      newsFeedId,
      topicInterestId
    );
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PostCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (postInType != null ? "postInType=" + postInType + ", " : "") +
            (postType != null ? "postType=" + postType + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (postCommentId != null ? "postCommentId=" + postCommentId + ", " : "") +
            (postLikeId != null ? "postLikeId=" + postLikeId + ", " : "") +
            (postShareChildrenId != null ? "postShareChildrenId=" + postShareChildrenId + ", " : "") +
            (groupPostId != null ? "groupPostId=" + groupPostId + ", " : "") +
            (pagePostId != null ? "pagePostId=" + pagePostId + ", " : "") +
            (postShareParentId != null ? "postShareParentId=" + postShareParentId + ", " : "") +
            (posterId != null ? "posterId=" + posterId + ", " : "") +
            (newsFeedId != null ? "newsFeedId=" + newsFeedId + ", " : "") +
            (topicInterestId != null ? "topicInterestId=" + topicInterestId + ", " : "") +
            "}";
    }
}
