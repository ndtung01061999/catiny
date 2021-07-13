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
 * Criteria class for the {@link com.regitiny.catiny.domain.NewsFeed} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.NewsFeedResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /news-feeds?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class NewsFeedCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private DoubleFilter score;

  private LongFilter baseInfoId;

  private LongFilter postId;

  private LongFilter masterUserId;

  public NewsFeedCriteria() {}

  public NewsFeedCriteria(NewsFeedCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.score = other.score == null ? null : other.score.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.postId = other.postId == null ? null : other.postId.copy();
    this.masterUserId = other.masterUserId == null ? null : other.masterUserId.copy();
  }

  @Override
  public NewsFeedCriteria copy() {
    return new NewsFeedCriteria(this);
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

  public DoubleFilter getScore() {
    return score;
  }

  public DoubleFilter score() {
    if (score == null) {
      score = new DoubleFilter();
    }
    return score;
  }

  public void setScore(DoubleFilter score) {
    this.score = score;
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

  public LongFilter getMasterUserId() {
    return masterUserId;
  }

  public LongFilter masterUserId() {
    if (masterUserId == null) {
      masterUserId = new LongFilter();
    }
    return masterUserId;
  }

  public void setMasterUserId(LongFilter masterUserId) {
    this.masterUserId = masterUserId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final NewsFeedCriteria that = (NewsFeedCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(score, that.score) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(postId, that.postId) &&
      Objects.equals(masterUserId, that.masterUserId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, score, baseInfoId, postId, masterUserId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "NewsFeedCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (score != null ? "score=" + score + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (postId != null ? "postId=" + postId + ", " : "") +
            (masterUserId != null ? "masterUserId=" + masterUserId + ", " : "") +
            "}";
    }
}
