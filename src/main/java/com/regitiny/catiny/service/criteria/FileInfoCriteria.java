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
 * Criteria class for the {@link com.regitiny.catiny.domain.FileInfo} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.FileInfoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /file-infos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class FileInfoCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter nameFile;

  private StringFilter typeFile;

  private StringFilter path;

  private LongFilter dataSize;

  private LongFilter baseInfoId;

  private LongFilter masterUserId;

  public FileInfoCriteria() {}

  public FileInfoCriteria(FileInfoCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.nameFile = other.nameFile == null ? null : other.nameFile.copy();
    this.typeFile = other.typeFile == null ? null : other.typeFile.copy();
    this.path = other.path == null ? null : other.path.copy();
    this.dataSize = other.dataSize == null ? null : other.dataSize.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.masterUserId = other.masterUserId == null ? null : other.masterUserId.copy();
  }

  @Override
  public FileInfoCriteria copy() {
    return new FileInfoCriteria(this);
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

  public StringFilter getNameFile() {
    return nameFile;
  }

  public StringFilter nameFile() {
    if (nameFile == null) {
      nameFile = new StringFilter();
    }
    return nameFile;
  }

  public void setNameFile(StringFilter nameFile) {
    this.nameFile = nameFile;
  }

  public StringFilter getTypeFile() {
    return typeFile;
  }

  public StringFilter typeFile() {
    if (typeFile == null) {
      typeFile = new StringFilter();
    }
    return typeFile;
  }

  public void setTypeFile(StringFilter typeFile) {
    this.typeFile = typeFile;
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

  public LongFilter getDataSize() {
    return dataSize;
  }

  public LongFilter dataSize() {
    if (dataSize == null) {
      dataSize = new LongFilter();
    }
    return dataSize;
  }

  public void setDataSize(LongFilter dataSize) {
    this.dataSize = dataSize;
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
    final FileInfoCriteria that = (FileInfoCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(nameFile, that.nameFile) &&
      Objects.equals(typeFile, that.typeFile) &&
      Objects.equals(path, that.path) &&
      Objects.equals(dataSize, that.dataSize) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(masterUserId, that.masterUserId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, nameFile, typeFile, path, dataSize, baseInfoId, masterUserId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "FileInfoCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (nameFile != null ? "nameFile=" + nameFile + ", " : "") +
            (typeFile != null ? "typeFile=" + typeFile + ", " : "") +
            (path != null ? "path=" + path + ", " : "") +
            (dataSize != null ? "dataSize=" + dataSize + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (masterUserId != null ? "masterUserId=" + masterUserId + ", " : "") +
            "}";
    }
}
