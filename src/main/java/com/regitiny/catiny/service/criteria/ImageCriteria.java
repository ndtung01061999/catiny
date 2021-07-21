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
 * Criteria class for the {@link com.regitiny.catiny.domain.Image} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.ImageResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /images?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class ImageCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter name;

  private IntegerFilter width;

  private IntegerFilter height;

  private FloatFilter quality;

  private IntegerFilter pixelSize;

  private LongFilter priorityIndex;

  private LongFilter dataSize;

  private LongFilter fileInfoId;

  private LongFilter baseInfoId;

  private LongFilter imageProcessedId;

  private LongFilter imageOriginalId;

  private LongFilter albumId;

  public ImageCriteria() {}

  public ImageCriteria(ImageCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.name = other.name == null ? null : other.name.copy();
    this.width = other.width == null ? null : other.width.copy();
    this.height = other.height == null ? null : other.height.copy();
    this.quality = other.quality == null ? null : other.quality.copy();
    this.pixelSize = other.pixelSize == null ? null : other.pixelSize.copy();
    this.priorityIndex = other.priorityIndex == null ? null : other.priorityIndex.copy();
    this.dataSize = other.dataSize == null ? null : other.dataSize.copy();
    this.fileInfoId = other.fileInfoId == null ? null : other.fileInfoId.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.imageProcessedId = other.imageProcessedId == null ? null : other.imageProcessedId.copy();
    this.imageOriginalId = other.imageOriginalId == null ? null : other.imageOriginalId.copy();
    this.albumId = other.albumId == null ? null : other.albumId.copy();
  }

  @Override
  public ImageCriteria copy() {
    return new ImageCriteria(this);
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

  public IntegerFilter getWidth() {
    return width;
  }

  public IntegerFilter width() {
    if (width == null) {
      width = new IntegerFilter();
    }
    return width;
  }

  public void setWidth(IntegerFilter width) {
    this.width = width;
  }

  public IntegerFilter getHeight() {
    return height;
  }

  public IntegerFilter height() {
    if (height == null) {
      height = new IntegerFilter();
    }
    return height;
  }

  public void setHeight(IntegerFilter height) {
    this.height = height;
  }

  public FloatFilter getQuality() {
    return quality;
  }

  public FloatFilter quality() {
    if (quality == null) {
      quality = new FloatFilter();
    }
    return quality;
  }

  public void setQuality(FloatFilter quality) {
    this.quality = quality;
  }

  public IntegerFilter getPixelSize() {
    return pixelSize;
  }

  public IntegerFilter pixelSize() {
    if (pixelSize == null) {
      pixelSize = new IntegerFilter();
    }
    return pixelSize;
  }

  public void setPixelSize(IntegerFilter pixelSize) {
    this.pixelSize = pixelSize;
  }

  public LongFilter getPriorityIndex() {
    return priorityIndex;
  }

  public LongFilter priorityIndex() {
    if (priorityIndex == null) {
      priorityIndex = new LongFilter();
    }
    return priorityIndex;
  }

  public void setPriorityIndex(LongFilter priorityIndex) {
    this.priorityIndex = priorityIndex;
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

  public LongFilter getFileInfoId() {
    return fileInfoId;
  }

  public LongFilter fileInfoId() {
    if (fileInfoId == null) {
      fileInfoId = new LongFilter();
    }
    return fileInfoId;
  }

  public void setFileInfoId(LongFilter fileInfoId) {
    this.fileInfoId = fileInfoId;
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

  public LongFilter getImageProcessedId() {
    return imageProcessedId;
  }

  public LongFilter imageProcessedId() {
    if (imageProcessedId == null) {
      imageProcessedId = new LongFilter();
    }
    return imageProcessedId;
  }

  public void setImageProcessedId(LongFilter imageProcessedId) {
    this.imageProcessedId = imageProcessedId;
  }

  public LongFilter getImageOriginalId() {
    return imageOriginalId;
  }

  public LongFilter imageOriginalId() {
    if (imageOriginalId == null) {
      imageOriginalId = new LongFilter();
    }
    return imageOriginalId;
  }

  public void setImageOriginalId(LongFilter imageOriginalId) {
    this.imageOriginalId = imageOriginalId;
  }

  public LongFilter getAlbumId() {
    return albumId;
  }

  public LongFilter albumId() {
    if (albumId == null) {
      albumId = new LongFilter();
    }
    return albumId;
  }

  public void setAlbumId(LongFilter albumId) {
    this.albumId = albumId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final ImageCriteria that = (ImageCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(name, that.name) &&
      Objects.equals(width, that.width) &&
      Objects.equals(height, that.height) &&
      Objects.equals(quality, that.quality) &&
      Objects.equals(pixelSize, that.pixelSize) &&
      Objects.equals(priorityIndex, that.priorityIndex) &&
      Objects.equals(dataSize, that.dataSize) &&
      Objects.equals(fileInfoId, that.fileInfoId) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(imageProcessedId, that.imageProcessedId) &&
      Objects.equals(imageOriginalId, that.imageOriginalId) &&
      Objects.equals(albumId, that.albumId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(
      id,
      uuid,
      name,
      width,
      height,
      quality,
      pixelSize,
      priorityIndex,
      dataSize,
      fileInfoId,
      baseInfoId,
      imageProcessedId,
      imageOriginalId,
      albumId
    );
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "ImageCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (width != null ? "width=" + width + ", " : "") +
            (height != null ? "height=" + height + ", " : "") +
            (quality != null ? "quality=" + quality + ", " : "") +
            (pixelSize != null ? "pixelSize=" + pixelSize + ", " : "") +
            (priorityIndex != null ? "priorityIndex=" + priorityIndex + ", " : "") +
            (dataSize != null ? "dataSize=" + dataSize + ", " : "") +
            (fileInfoId != null ? "fileInfoId=" + fileInfoId + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (imageProcessedId != null ? "imageProcessedId=" + imageProcessedId + ", " : "") +
            (imageOriginalId != null ? "imageOriginalId=" + imageOriginalId + ", " : "") +
            (albumId != null ? "albumId=" + albumId + ", " : "") +
            "}";
    }
}
