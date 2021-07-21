package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.UUIDFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.regitiny.catiny.domain.HistoryUpdate} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.HistoryUpdateResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /history-updates?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class HistoryUpdateCriteria implements Serializable, Criteria
{

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private IntegerFilter version;

  private LongFilter baseInfoId;

  public HistoryUpdateCriteria()
  {
  }

  public HistoryUpdateCriteria(HistoryUpdateCriteria other)
  {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.version = other.version == null ? null : other.version.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
  }

  @Override
  public HistoryUpdateCriteria copy()
  {
    return new HistoryUpdateCriteria(this);
  }

  public LongFilter getId()
  {
    return id;
  }

  public void setId(LongFilter id)
  {
    this.id = id;
  }

  public LongFilter id()
  {
    if (id == null)
    {
      id = new LongFilter();
    }
    return id;
  }

  public UUIDFilter getUuid()
  {
    return uuid;
  }

  public void setUuid(UUIDFilter uuid)
  {
    this.uuid = uuid;
  }

  public UUIDFilter uuid()
  {
    if (uuid == null)
    {
      uuid = new UUIDFilter();
    }
    return uuid;
  }

  public IntegerFilter getVersion()
  {
    return version;
  }

  public void setVersion(IntegerFilter version)
  {
    this.version = version;
  }

  public IntegerFilter version()
  {
    if (version == null)
    {
      version = new IntegerFilter();
    }
    return version;
  }

  public LongFilter getBaseInfoId()
  {
    return baseInfoId;
  }

  public void setBaseInfoId(LongFilter baseInfoId)
  {
    this.baseInfoId = baseInfoId;
  }

  public LongFilter baseInfoId()
  {
    if (baseInfoId == null)
    {
      baseInfoId = new LongFilter();
    }
    return baseInfoId;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }
    final HistoryUpdateCriteria that = (HistoryUpdateCriteria) o;
    return (
      Objects.equals(id, that.id) &&
        Objects.equals(uuid, that.uuid) &&
        Objects.equals(version, that.version) &&
        Objects.equals(baseInfoId, that.baseInfoId)
    );
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id, uuid, version, baseInfoId);
  }

  // prettier-ignore
  @Override
  public String toString()
  {
    return "HistoryUpdateCriteria{" +
      (id != null ? "id=" + id + ", " : "") +
      (uuid != null ? "uuid=" + uuid + ", " : "") +
      (version != null ? "version=" + version + ", " : "") +
      (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
      "}";
  }
}
