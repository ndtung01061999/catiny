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
 * Criteria class for the {@link com.regitiny.catiny.domain.TodoList} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.TodoListResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /todo-lists?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class TodoListCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter title;

  private LongFilter baseInfoId;

  private LongFilter masterUserId;

  public TodoListCriteria() {}

  public TodoListCriteria(TodoListCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.title = other.title == null ? null : other.title.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.masterUserId = other.masterUserId == null ? null : other.masterUserId.copy();
  }

  @Override
  public TodoListCriteria copy() {
    return new TodoListCriteria(this);
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

  public StringFilter getTitle() {
    return title;
  }

  public StringFilter title() {
    if (title == null) {
      title = new StringFilter();
    }
    return title;
  }

  public void setTitle(StringFilter title) {
    this.title = title;
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
    final TodoListCriteria that = (TodoListCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(title, that.title) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(masterUserId, that.masterUserId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, title, baseInfoId, masterUserId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "TodoListCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (title != null ? "title=" + title + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (masterUserId != null ? "masterUserId=" + masterUserId + ", " : "") +
            "}";
    }
}
