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

/**
 * Criteria class for the {@link com.regitiny.catiny.domain.Permission} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.PermissionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /permissions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class PermissionCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private BooleanFilter read;

  private BooleanFilter write;

  private BooleanFilter share;

  private BooleanFilter delete;

  private BooleanFilter add;

  private IntegerFilter level;

  private LongFilter baseInfoId;

  private LongFilter masterUserId;

  public PermissionCriteria() {}

  public PermissionCriteria(PermissionCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.read = other.read == null ? null : other.read.copy();
    this.write = other.write == null ? null : other.write.copy();
    this.share = other.share == null ? null : other.share.copy();
    this.delete = other.delete == null ? null : other.delete.copy();
    this.add = other.add == null ? null : other.add.copy();
    this.level = other.level == null ? null : other.level.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.masterUserId = other.masterUserId == null ? null : other.masterUserId.copy();
  }

  @Override
  public PermissionCriteria copy() {
    return new PermissionCriteria(this);
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

  public BooleanFilter getRead() {
    return read;
  }

  public BooleanFilter read() {
    if (read == null) {
      read = new BooleanFilter();
    }
    return read;
  }

  public void setRead(BooleanFilter read) {
    this.read = read;
  }

  public BooleanFilter getWrite() {
    return write;
  }

  public BooleanFilter write() {
    if (write == null) {
      write = new BooleanFilter();
    }
    return write;
  }

  public void setWrite(BooleanFilter write) {
    this.write = write;
  }

  public BooleanFilter getShare() {
    return share;
  }

  public BooleanFilter share() {
    if (share == null) {
      share = new BooleanFilter();
    }
    return share;
  }

  public void setShare(BooleanFilter share) {
    this.share = share;
  }

  public BooleanFilter getDelete() {
    return delete;
  }

  public BooleanFilter delete() {
    if (delete == null) {
      delete = new BooleanFilter();
    }
    return delete;
  }

  public void setDelete(BooleanFilter delete) {
    this.delete = delete;
  }

  public BooleanFilter getAdd() {
    return add;
  }

  public BooleanFilter add() {
    if (add == null) {
      add = new BooleanFilter();
    }
    return add;
  }

  public void setAdd(BooleanFilter add) {
    this.add = add;
  }

  public IntegerFilter getLevel() {
    return level;
  }

  public IntegerFilter level() {
    if (level == null) {
      level = new IntegerFilter();
    }
    return level;
  }

  public void setLevel(IntegerFilter level) {
    this.level = level;
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
    final PermissionCriteria that = (PermissionCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(read, that.read) &&
      Objects.equals(write, that.write) &&
      Objects.equals(share, that.share) &&
      Objects.equals(delete, that.delete) &&
      Objects.equals(add, that.add) &&
      Objects.equals(level, that.level) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(masterUserId, that.masterUserId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, read, write, share, delete, add, level, baseInfoId, masterUserId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PermissionCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (read != null ? "read=" + read + ", " : "") +
            (write != null ? "write=" + write + ", " : "") +
            (share != null ? "share=" + share + ", " : "") +
            (delete != null ? "delete=" + delete + ", " : "") +
            (add != null ? "add=" + add + ", " : "") +
            (level != null ? "level=" + level + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (masterUserId != null ? "masterUserId=" + masterUserId + ", " : "") +
            "}";
    }
}
