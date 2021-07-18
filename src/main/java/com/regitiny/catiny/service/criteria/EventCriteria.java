package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.EventType;
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
 * Criteria class for the {@link com.regitiny.catiny.domain.Event} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.EventResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /events?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class EventCriteria implements Serializable, Criteria {

  /**
   * Class for filtering EventType
   */
  @GeneratedByJHipster
  public static class EventTypeFilter extends Filter<EventType> {

    public EventTypeFilter() {}

    public EventTypeFilter(EventTypeFilter filter) {
      super(filter);
    }

    @Override
    public EventTypeFilter copy() {
      return new EventTypeFilter(this);
    }
  }

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter title;

  private EventTypeFilter type;

  private InstantFilter startTime;

  private InstantFilter endTime;

  private StringFilter tagLine;

  private LongFilter baseInfoId;

  public EventCriteria() {}

  public EventCriteria(EventCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.title = other.title == null ? null : other.title.copy();
    this.type = other.type == null ? null : other.type.copy();
    this.startTime = other.startTime == null ? null : other.startTime.copy();
    this.endTime = other.endTime == null ? null : other.endTime.copy();
    this.tagLine = other.tagLine == null ? null : other.tagLine.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
  }

  @Override
  public EventCriteria copy() {
    return new EventCriteria(this);
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

  public EventTypeFilter getType() {
    return type;
  }

  public EventTypeFilter type() {
    if (type == null) {
      type = new EventTypeFilter();
    }
    return type;
  }

  public void setType(EventTypeFilter type) {
    this.type = type;
  }

  public InstantFilter getStartTime() {
    return startTime;
  }

  public InstantFilter startTime() {
    if (startTime == null) {
      startTime = new InstantFilter();
    }
    return startTime;
  }

  public void setStartTime(InstantFilter startTime) {
    this.startTime = startTime;
  }

  public InstantFilter getEndTime() {
    return endTime;
  }

  public InstantFilter endTime() {
    if (endTime == null) {
      endTime = new InstantFilter();
    }
    return endTime;
  }

  public void setEndTime(InstantFilter endTime) {
    this.endTime = endTime;
  }

  public StringFilter getTagLine() {
    return tagLine;
  }

  public StringFilter tagLine() {
    if (tagLine == null) {
      tagLine = new StringFilter();
    }
    return tagLine;
  }

  public void setTagLine(StringFilter tagLine) {
    this.tagLine = tagLine;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final EventCriteria that = (EventCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(title, that.title) &&
      Objects.equals(type, that.type) &&
      Objects.equals(startTime, that.startTime) &&
      Objects.equals(endTime, that.endTime) &&
      Objects.equals(tagLine, that.tagLine) &&
      Objects.equals(baseInfoId, that.baseInfoId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, title, type, startTime, endTime, tagLine, baseInfoId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EventCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (title != null ? "title=" + title + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (startTime != null ? "startTime=" + startTime + ", " : "") +
            (endTime != null ? "endTime=" + endTime + ", " : "") +
            (tagLine != null ? "tagLine=" + tagLine + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            "}";
    }
}
