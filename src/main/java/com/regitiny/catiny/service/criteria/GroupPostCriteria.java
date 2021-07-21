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
 * Criteria class for the {@link com.regitiny.catiny.domain.GroupPost} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.GroupPostResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /group-posts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class GroupPostCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter name;

  private LongFilter profileId;

  private LongFilter baseInfoId;

  private LongFilter myPostInGroupId;

  private LongFilter topicInterestId;

  public GroupPostCriteria() {}

  public GroupPostCriteria(GroupPostCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.name = other.name == null ? null : other.name.copy();
    this.profileId = other.profileId == null ? null : other.profileId.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.myPostInGroupId = other.myPostInGroupId == null ? null : other.myPostInGroupId.copy();
    this.topicInterestId = other.topicInterestId == null ? null : other.topicInterestId.copy();
  }

  @Override
  public GroupPostCriteria copy() {
    return new GroupPostCriteria(this);
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

  public StringFilter getName() {
    return name;
  }

  public StringFilter name() {
    if (name == null) {
      name = new StringFilter();
    }
    return name;
  }

  public void setName(StringFilter name) {
    this.name = name;
  }

  public LongFilter getProfileId() {
    return profileId;
  }

  public LongFilter profileId() {
    if (profileId == null) {
      profileId = new LongFilter();
    }
    return profileId;
  }

  public void setProfileId(LongFilter profileId) {
    this.profileId = profileId;
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

  public LongFilter getMyPostInGroupId() {
    return myPostInGroupId;
  }

  public LongFilter myPostInGroupId() {
    if (myPostInGroupId == null) {
      myPostInGroupId = new LongFilter();
    }
    return myPostInGroupId;
  }

  public void setMyPostInGroupId(LongFilter myPostInGroupId) {
    this.myPostInGroupId = myPostInGroupId;
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
    final GroupPostCriteria that = (GroupPostCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(name, that.name) &&
      Objects.equals(profileId, that.profileId) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(myPostInGroupId, that.myPostInGroupId) &&
      Objects.equals(topicInterestId, that.topicInterestId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, name, profileId, baseInfoId, myPostInGroupId, topicInterestId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "GroupPostCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (profileId != null ? "profileId=" + profileId + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (myPostInGroupId != null ? "myPostInGroupId=" + myPostInGroupId + ", " : "") +
            (topicInterestId != null ? "topicInterestId=" + topicInterestId + ", " : "") +
            "}";
    }
}
