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

    private StringFilter groupId;

    private StringFilter sender;

    private StringFilter status;

    private StringFilter role;

    private InstantFilter createdDate;

    private InstantFilter modifiedDate;

    private StringFilter createdBy;

    private StringFilter modifiedBy;

    private StringFilter comment;

    public MessageContentCriteria() {}

    public MessageContentCriteria(MessageContentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.uuid = other.uuid == null ? null : other.uuid.copy();
        this.groupId = other.groupId == null ? null : other.groupId.copy();
        this.sender = other.sender == null ? null : other.sender.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.role = other.role == null ? null : other.role.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
        this.modifiedDate = other.modifiedDate == null ? null : other.modifiedDate.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.modifiedBy = other.modifiedBy == null ? null : other.modifiedBy.copy();
        this.comment = other.comment == null ? null : other.comment.copy();
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

    public StringFilter getSender() {
        return sender;
    }

    public StringFilter sender() {
        if (sender == null) {
            sender = new StringFilter();
        }
        return sender;
    }

    public void setSender(StringFilter sender) {
        this.sender = sender;
    }

    public StringFilter getStatus() {
        return status;
    }

    public StringFilter status() {
        if (status == null) {
            status = new StringFilter();
        }
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
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
        final MessageContentCriteria that = (MessageContentCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(uuid, that.uuid) &&
            Objects.equals(groupId, that.groupId) &&
            Objects.equals(sender, that.sender) &&
            Objects.equals(status, that.status) &&
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
        return Objects.hash(id, uuid, groupId, sender, status, role, createdDate, modifiedDate, createdBy, modifiedBy, comment);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageContentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (groupId != null ? "groupId=" + groupId + ", " : "") +
            (sender != null ? "sender=" + sender + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (role != null ? "role=" + role + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (modifiedDate != null ? "modifiedDate=" + modifiedDate + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (modifiedBy != null ? "modifiedBy=" + modifiedBy + ", " : "") +
            (comment != null ? "comment=" + comment + ", " : "") +
            "}";
    }
}
