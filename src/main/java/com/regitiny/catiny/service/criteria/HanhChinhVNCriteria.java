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
 * Criteria class for the {@link com.regitiny.catiny.domain.HanhChinhVN} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.HanhChinhVNResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /hanh-chinh-vns?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class HanhChinhVNCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private StringFilter name;

  private StringFilter slug;

  private StringFilter type;

  private StringFilter nameWithType;

  private StringFilter code;

  private StringFilter parentCode;

  private StringFilter path;

  private StringFilter pathWithType;

  public HanhChinhVNCriteria() {}

  public HanhChinhVNCriteria(HanhChinhVNCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.name = other.name == null ? null : other.name.copy();
    this.slug = other.slug == null ? null : other.slug.copy();
    this.type = other.type == null ? null : other.type.copy();
    this.nameWithType = other.nameWithType == null ? null : other.nameWithType.copy();
    this.code = other.code == null ? null : other.code.copy();
    this.parentCode = other.parentCode == null ? null : other.parentCode.copy();
    this.path = other.path == null ? null : other.path.copy();
    this.pathWithType = other.pathWithType == null ? null : other.pathWithType.copy();
  }

  @Override
  public HanhChinhVNCriteria copy() {
    return new HanhChinhVNCriteria(this);
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

  public StringFilter getSlug() {
    return slug;
  }

  public StringFilter slug() {
    if (slug == null) {
      slug = new StringFilter();
    }
    return slug;
  }

  public void setSlug(StringFilter slug) {
    this.slug = slug;
  }

  public StringFilter getType() {
    return type;
  }

  public StringFilter type() {
    if (type == null) {
      type = new StringFilter();
    }
    return type;
  }

  public void setType(StringFilter type) {
    this.type = type;
  }

  public StringFilter getNameWithType() {
    return nameWithType;
  }

  public StringFilter nameWithType() {
    if (nameWithType == null) {
      nameWithType = new StringFilter();
    }
    return nameWithType;
  }

  public void setNameWithType(StringFilter nameWithType) {
    this.nameWithType = nameWithType;
  }

  public StringFilter getCode() {
    return code;
  }

  public StringFilter code() {
    if (code == null) {
      code = new StringFilter();
    }
    return code;
  }

  public void setCode(StringFilter code) {
    this.code = code;
  }

  public StringFilter getParentCode() {
    return parentCode;
  }

  public StringFilter parentCode() {
    if (parentCode == null) {
      parentCode = new StringFilter();
    }
    return parentCode;
  }

  public void setParentCode(StringFilter parentCode) {
    this.parentCode = parentCode;
  }

  public StringFilter getPath() {
    return path;
  }

  public StringFilter path() {
    if (path == null) {
      path = new StringFilter();
    }
    return path;
  }

  public void setPath(StringFilter path) {
    this.path = path;
  }

  public StringFilter getPathWithType() {
    return pathWithType;
  }

  public StringFilter pathWithType() {
    if (pathWithType == null) {
      pathWithType = new StringFilter();
    }
    return pathWithType;
  }

  public void setPathWithType(StringFilter pathWithType) {
    this.pathWithType = pathWithType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final HanhChinhVNCriteria that = (HanhChinhVNCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(name, that.name) &&
      Objects.equals(slug, that.slug) &&
      Objects.equals(type, that.type) &&
      Objects.equals(nameWithType, that.nameWithType) &&
      Objects.equals(code, that.code) &&
      Objects.equals(parentCode, that.parentCode) &&
      Objects.equals(path, that.path) &&
      Objects.equals(pathWithType, that.pathWithType)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, slug, type, nameWithType, code, parentCode, path, pathWithType);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "HanhChinhVNCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (slug != null ? "slug=" + slug + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (nameWithType != null ? "nameWithType=" + nameWithType + ", " : "") +
            (code != null ? "code=" + code + ", " : "") +
            (parentCode != null ? "parentCode=" + parentCode + ", " : "") +
            (path != null ? "path=" + path + ", " : "") +
            (pathWithType != null ? "pathWithType=" + pathWithType + ", " : "") +
            "}";
    }
}
