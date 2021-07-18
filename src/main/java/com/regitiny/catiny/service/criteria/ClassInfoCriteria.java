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
 * Criteria class for the {@link com.regitiny.catiny.domain.ClassInfo} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.ClassInfoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /class-infos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class ClassInfoCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter packageName;

  private StringFilter fullName;

  private StringFilter className;

  private LongFilter baseInfoId;

  public ClassInfoCriteria() {}

  public ClassInfoCriteria(ClassInfoCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.packageName = other.packageName == null ? null : other.packageName.copy();
    this.fullName = other.fullName == null ? null : other.fullName.copy();
    this.className = other.className == null ? null : other.className.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
  }

  @Override
  public ClassInfoCriteria copy() {
    return new ClassInfoCriteria(this);
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

  public StringFilter getPackageName() {
    return packageName;
  }

  public StringFilter packageName() {
    if (packageName == null) {
      packageName = new StringFilter();
    }
    return packageName;
  }

  public void setPackageName(StringFilter packageName) {
    this.packageName = packageName;
  }

  public StringFilter getFullName() {
    return fullName;
  }

  public StringFilter fullName() {
    if (fullName == null) {
      fullName = new StringFilter();
    }
    return fullName;
  }

  public void setFullName(StringFilter fullName) {
    this.fullName = fullName;
  }

  public StringFilter getClassName() {
    return className;
  }

  public StringFilter className() {
    if (className == null) {
      className = new StringFilter();
    }
    return className;
  }

  public void setClassName(StringFilter className) {
    this.className = className;
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
    final ClassInfoCriteria that = (ClassInfoCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(packageName, that.packageName) &&
      Objects.equals(fullName, that.fullName) &&
      Objects.equals(className, that.className) &&
      Objects.equals(baseInfoId, that.baseInfoId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, packageName, fullName, className, baseInfoId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "ClassInfoCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (packageName != null ? "packageName=" + packageName + ", " : "") +
            (fullName != null ? "fullName=" + fullName + ", " : "") +
            (className != null ? "className=" + className + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            "}";
    }
}
