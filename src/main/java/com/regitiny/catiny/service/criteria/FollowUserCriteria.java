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
 * Criteria class for the {@link com.regitiny.catiny.domain.FollowUser} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.FollowUserResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /follow-users?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class FollowUserCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private LongFilter baseInfoId;

  private LongFilter followUserDetailsId;

  public FollowUserCriteria() {}

  public FollowUserCriteria(FollowUserCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.followUserDetailsId = other.followUserDetailsId == null ? null : other.followUserDetailsId.copy();
  }

  @Override
  public FollowUserCriteria copy() {
    return new FollowUserCriteria(this);
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

  public LongFilter getFollowUserDetailsId() {
    return followUserDetailsId;
  }

  public LongFilter followUserDetailsId() {
    if (followUserDetailsId == null) {
      followUserDetailsId = new LongFilter();
    }
    return followUserDetailsId;
  }

  public void setFollowUserDetailsId(LongFilter followUserDetailsId) {
    this.followUserDetailsId = followUserDetailsId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final FollowUserCriteria that = (FollowUserCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(followUserDetailsId, that.followUserDetailsId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, baseInfoId, followUserDetailsId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "FollowUserCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (followUserDetailsId != null ? "followUserDetailsId=" + followUserDetailsId + ", " : "") +
            "}";
    }
}
