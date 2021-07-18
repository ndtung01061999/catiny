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
 * Criteria class for the {@link com.regitiny.catiny.domain.Video} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.VideoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /videos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class VideoCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter name;

  private IntegerFilter width;

  private IntegerFilter height;

  private FloatFilter qualityImage;

  private FloatFilter qualityAudio;

  private FloatFilter quality;

  private IntegerFilter pixelSize;

  private LongFilter priorityIndex;

  private LongFilter dataSize;

  private LongFilter fileInfoId;

  private LongFilter baseInfoId;

  private LongFilter videoProcessedId;

  private LongFilter videoStreamId;

  private LongFilter videoOriginalId;

  public VideoCriteria() {}

  public VideoCriteria(VideoCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.name = other.name == null ? null : other.name.copy();
    this.width = other.width == null ? null : other.width.copy();
    this.height = other.height == null ? null : other.height.copy();
    this.qualityImage = other.qualityImage == null ? null : other.qualityImage.copy();
    this.qualityAudio = other.qualityAudio == null ? null : other.qualityAudio.copy();
    this.quality = other.quality == null ? null : other.quality.copy();
    this.pixelSize = other.pixelSize == null ? null : other.pixelSize.copy();
    this.priorityIndex = other.priorityIndex == null ? null : other.priorityIndex.copy();
    this.dataSize = other.dataSize == null ? null : other.dataSize.copy();
    this.fileInfoId = other.fileInfoId == null ? null : other.fileInfoId.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.videoProcessedId = other.videoProcessedId == null ? null : other.videoProcessedId.copy();
    this.videoStreamId = other.videoStreamId == null ? null : other.videoStreamId.copy();
    this.videoOriginalId = other.videoOriginalId == null ? null : other.videoOriginalId.copy();
  }

  @Override
  public VideoCriteria copy() {
    return new VideoCriteria(this);
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

  public FloatFilter getQualityImage() {
    return qualityImage;
  }

  public FloatFilter qualityImage() {
    if (qualityImage == null) {
      qualityImage = new FloatFilter();
    }
    return qualityImage;
  }

  public void setQualityImage(FloatFilter qualityImage) {
    this.qualityImage = qualityImage;
  }

  public FloatFilter getQualityAudio() {
    return qualityAudio;
  }

  public FloatFilter qualityAudio() {
    if (qualityAudio == null) {
      qualityAudio = new FloatFilter();
    }
    return qualityAudio;
  }

  public void setQualityAudio(FloatFilter qualityAudio) {
    this.qualityAudio = qualityAudio;
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

  public LongFilter getVideoProcessedId() {
    return videoProcessedId;
  }

  public LongFilter videoProcessedId() {
    if (videoProcessedId == null) {
      videoProcessedId = new LongFilter();
    }
    return videoProcessedId;
  }

  public void setVideoProcessedId(LongFilter videoProcessedId) {
    this.videoProcessedId = videoProcessedId;
  }

  public LongFilter getVideoStreamId() {
    return videoStreamId;
  }

  public LongFilter videoStreamId() {
    if (videoStreamId == null) {
      videoStreamId = new LongFilter();
    }
    return videoStreamId;
  }

  public void setVideoStreamId(LongFilter videoStreamId) {
    this.videoStreamId = videoStreamId;
  }

  public LongFilter getVideoOriginalId() {
    return videoOriginalId;
  }

  public LongFilter videoOriginalId() {
    if (videoOriginalId == null) {
      videoOriginalId = new LongFilter();
    }
    return videoOriginalId;
  }

  public void setVideoOriginalId(LongFilter videoOriginalId) {
    this.videoOriginalId = videoOriginalId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final VideoCriteria that = (VideoCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(name, that.name) &&
      Objects.equals(width, that.width) &&
      Objects.equals(height, that.height) &&
      Objects.equals(qualityImage, that.qualityImage) &&
      Objects.equals(qualityAudio, that.qualityAudio) &&
      Objects.equals(quality, that.quality) &&
      Objects.equals(pixelSize, that.pixelSize) &&
      Objects.equals(priorityIndex, that.priorityIndex) &&
      Objects.equals(dataSize, that.dataSize) &&
      Objects.equals(fileInfoId, that.fileInfoId) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(videoProcessedId, that.videoProcessedId) &&
      Objects.equals(videoStreamId, that.videoStreamId) &&
      Objects.equals(videoOriginalId, that.videoOriginalId)
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
      qualityImage,
      qualityAudio,
      quality,
      pixelSize,
      priorityIndex,
      dataSize,
      fileInfoId,
      baseInfoId,
      videoProcessedId,
      videoStreamId,
      videoOriginalId
    );
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "VideoCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (width != null ? "width=" + width + ", " : "") +
            (height != null ? "height=" + height + ", " : "") +
            (qualityImage != null ? "qualityImage=" + qualityImage + ", " : "") +
            (qualityAudio != null ? "qualityAudio=" + qualityAudio + ", " : "") +
            (quality != null ? "quality=" + quality + ", " : "") +
            (pixelSize != null ? "pixelSize=" + pixelSize + ", " : "") +
            (priorityIndex != null ? "priorityIndex=" + priorityIndex + ", " : "") +
            (dataSize != null ? "dataSize=" + dataSize + ", " : "") +
            (fileInfoId != null ? "fileInfoId=" + fileInfoId + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (videoProcessedId != null ? "videoProcessedId=" + videoProcessedId + ", " : "") +
            (videoStreamId != null ? "videoStreamId=" + videoStreamId + ", " : "") +
            (videoOriginalId != null ? "videoOriginalId=" + videoOriginalId + ", " : "") +
            "}";
    }
}
