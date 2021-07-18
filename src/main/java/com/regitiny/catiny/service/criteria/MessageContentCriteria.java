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
 * Criteria class for the {@link com.regitiny.catiny.domain.MessageContent} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.MessageContentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /message-contents?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class MessageContentCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter senderName;

  private LongFilter baseInfoId;

  private LongFilter messageGroupId;

  public MessageContentCriteria() {}

  public MessageContentCriteria(MessageContentCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.senderName = other.senderName == null ? null : other.senderName.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.messageGroupId = other.messageGroupId == null ? null : other.messageGroupId.copy();
  }

  @Override
  public MessageContentCriteria copy() {
    return new MessageContentCriteria(this);
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

  public StringFilter getSenderName() {
    return senderName;
  }

  public StringFilter senderName() {
    if (senderName == null) {
      senderName = new StringFilter();
    }
    return senderName;
  }

  public void setSenderName(StringFilter senderName) {
    this.senderName = senderName;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final MessageContentCriteria that = (MessageContentCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(senderName, that.senderName) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(messageGroupId, that.messageGroupId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, senderName, baseInfoId, messageGroupId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MessageContentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (senderName != null ? "senderName=" + senderName + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (messageGroupId != null ? "messageGroupId=" + messageGroupId + ", " : "") +
            "}";
    }
}
