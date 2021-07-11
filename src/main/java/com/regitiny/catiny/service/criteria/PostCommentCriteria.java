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
 * Criteria class for the {@link com.regitiny.catiny.domain.PostComment} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.PostCommentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /post-comments?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class PostCommentCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private LongFilter baseInfoId;

  private LongFilter commentReplyId;

  private LongFilter userCommentId;

  private LongFilter postId;

  private LongFilter commentParentId;

  public PostCommentCriteria() {}

  public PostCommentCriteria(PostCommentCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.commentReplyId = other.commentReplyId == null ? null : other.commentReplyId.copy();
    this.userCommentId = other.userCommentId == null ? null : other.userCommentId.copy();
    this.postId = other.postId == null ? null : other.postId.copy();
    this.commentParentId = other.commentParentId == null ? null : other.commentParentId.copy();
  }

  @Override
  public PostCommentCriteria copy() {
    return new PostCommentCriteria(this);
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

  public LongFilter getCommentReplyId() {
    return commentReplyId;
  }

  public LongFilter commentReplyId() {
    if (commentReplyId == null) {
      commentReplyId = new LongFilter();
    }
    return commentReplyId;
  }

  public void setCommentReplyId(LongFilter commentReplyId) {
    this.commentReplyId = commentReplyId;
  }

  public LongFilter getUserCommentId() {
    return userCommentId;
  }

  public LongFilter userCommentId() {
    if (userCommentId == null) {
      userCommentId = new LongFilter();
    }
    return userCommentId;
  }

  public void setUserCommentId(LongFilter userCommentId) {
    this.userCommentId = userCommentId;
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

  public LongFilter getCommentParentId() {
    return commentParentId;
  }

  public LongFilter commentParentId() {
    if (commentParentId == null) {
      commentParentId = new LongFilter();
    }
    return commentParentId;
  }

  public void setCommentParentId(LongFilter commentParentId) {
    this.commentParentId = commentParentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PostCommentCriteria that = (PostCommentCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(commentReplyId, that.commentReplyId) &&
      Objects.equals(userCommentId, that.userCommentId) &&
      Objects.equals(postId, that.postId) &&
      Objects.equals(commentParentId, that.commentParentId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, baseInfoId, commentReplyId, userCommentId, postId, commentParentId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PostCommentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (commentReplyId != null ? "commentReplyId=" + commentReplyId + ", " : "") +
            (userCommentId != null ? "userCommentId=" + userCommentId + ", " : "") +
            (postId != null ? "postId=" + postId + ", " : "") +
            (commentParentId != null ? "commentParentId=" + commentParentId + ", " : "") +
            "}";
    }
}
