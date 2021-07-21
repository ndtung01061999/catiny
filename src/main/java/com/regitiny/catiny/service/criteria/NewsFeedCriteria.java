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

  private LongFilter priorityIndex;

  private LongFilter baseInfoId;

  private LongFilter postId;

  public NewsFeedCriteria() {}

  public NewsFeedCriteria(NewsFeedCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.priorityIndex = other.priorityIndex == null ? null : other.priorityIndex.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.postId = other.postId == null ? null : other.postId.copy();
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
      Objects.equals(priorityIndex, that.priorityIndex) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(postId, that.postId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, priorityIndex, baseInfoId, postId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "NewsFeedCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (priorityIndex != null ? "priorityIndex=" + priorityIndex + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (postId != null ? "postId=" + postId + ", " : "") +
            "}";
    }
}
