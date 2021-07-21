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

  private StringFilter groupName;

  private StringFilter addBy;

  private LongFilter baseInfoId;

  private LongFilter messageContentId;

  public MessageGroupCriteria() {}

  public MessageGroupCriteria(MessageGroupCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.groupName = other.groupName == null ? null : other.groupName.copy();
    this.addBy = other.addBy == null ? null : other.addBy.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.messageContentId = other.messageContentId == null ? null : other.messageContentId.copy();
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
      Objects.equals(groupName, that.groupName) &&
      Objects.equals(addBy, that.addBy) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(messageContentId, that.messageContentId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, groupName, addBy, baseInfoId, messageContentId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MessageGroupCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (groupName != null ? "groupName=" + groupName + ", " : "") +
            (addBy != null ? "addBy=" + addBy + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (messageContentId != null ? "messageContentId=" + messageContentId + ", " : "") +
            "}";
    }
}
