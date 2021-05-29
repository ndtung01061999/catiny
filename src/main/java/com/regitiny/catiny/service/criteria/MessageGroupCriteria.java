package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
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
import tech.jhipster.service.filter.UUIDFilter;

/**
 * Criteria class for the {@link com.regitiny.catiny.domain.MessageGroup} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.MessageGroupResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /message-groups?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class MessageGroupCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private LongFilter userId;

  private StringFilter groupId;

  private StringFilter groupName;

  private StringFilter addBy;

  private StringFilter role;

  private InstantFilter createdDate;

  private InstantFilter modifiedDate;

  private StringFilter createdBy;

  private StringFilter modifiedBy;

  private StringFilter comment;

  public MessageGroupCriteria() {}

  public MessageGroupCriteria(MessageGroupCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.userId = other.userId == null ? null : other.userId.copy();
    this.groupId = other.groupId == null ? null : other.groupId.copy();
    this.groupName = other.groupName == null ? null : other.groupName.copy();
    this.addBy = other.addBy == null ? null : other.addBy.copy();
    this.role = other.role == null ? null : other.role.copy();
    this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
    this.modifiedDate = other.modifiedDate == null ? null : other.modifiedDate.copy();
    this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
    this.modifiedBy = other.modifiedBy == null ? null : other.modifiedBy.copy();
    this.comment = other.comment == null ? null : other.comment.copy();
  }

  @Override
  public MessageGroupCriteria copy() {
    return new MessageGroupCriteria(this);
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

  public StringFilter getGroupId() {
    return groupId;
  }

  public StringFilter groupId() {
    if (groupId == null) {
      groupId = new StringFilter();
    }
    return groupId;
  }

  public void setGroupId(StringFilter groupId) {
    this.groupId = groupId;
  }

  public StringFilter getGroupName() {
    return groupName;
  }

  public StringFilter groupName() {
    if (groupName == null) {
      groupName = new StringFilter();
    }
    return groupName;
  }

  public void setGroupName(StringFilter groupName) {
    this.groupName = groupName;
  }

  public StringFilter getAddBy() {
    return addBy;
  }

  public StringFilter addBy() {
    if (addBy == null) {
      addBy = new StringFilter();
    }
    return addBy;
  }

  public void setAddBy(StringFilter addBy) {
    this.addBy = addBy;
  }

  public StringFilter getRole() {
    return role;
  }

  public StringFilter role() {
    if (role == null) {
      role = new StringFilter();
    }
    return role;
  }

  public void setRole(StringFilter role) {
    this.role = role;
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

  public StringFilter getCreatedBy() {
    return createdBy;
  }

  public StringFilter createdBy() {
    if (createdBy == null) {
      createdBy = new StringFilter();
    }
    return createdBy;
  }

  public void setCreatedBy(StringFilter createdBy) {
    this.createdBy = createdBy;
  }

  public StringFilter getModifiedBy() {
    return modifiedBy;
  }

  public StringFilter modifiedBy() {
    if (modifiedBy == null) {
      modifiedBy = new StringFilter();
    }
    return modifiedBy;
  }

  public void setModifiedBy(StringFilter modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public StringFilter getComment() {
    return comment;
  }

  public StringFilter comment() {
    if (comment == null) {
      comment = new StringFilter();
    }
    return comment;
  }

  public void setComment(StringFilter comment) {
    this.comment = comment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final MessageGroupCriteria that = (MessageGroupCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(userId, that.userId) &&
      Objects.equals(groupId, that.groupId) &&
      Objects.equals(groupName, that.groupName) &&
      Objects.equals(addBy, that.addBy) &&
      Objects.equals(role, that.role) &&
      Objects.equals(createdDate, that.createdDate) &&
      Objects.equals(modifiedDate, that.modifiedDate) &&
      Objects.equals(createdBy, that.createdBy) &&
      Objects.equals(modifiedBy, that.modifiedBy) &&
      Objects.equals(comment, that.comment)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, userId, groupId, groupName, addBy, role, createdDate, modifiedDate, createdBy, modifiedBy, comment);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MessageGroupCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (groupId != null ? "groupId=" + groupId + ", " : "") +
            (groupName != null ? "groupName=" + groupName + ", " : "") +
            (addBy != null ? "addBy=" + addBy + ", " : "") +
            (role != null ? "role=" + role + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (modifiedDate != null ? "modifiedDate=" + modifiedDate + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (modifiedBy != null ? "modifiedBy=" + modifiedBy + ", " : "") +
            (comment != null ? "comment=" + comment + ", " : "") +
            "}";
    }
}
