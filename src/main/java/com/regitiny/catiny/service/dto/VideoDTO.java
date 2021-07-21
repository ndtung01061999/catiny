package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.Video} entity.
 */
@ApiModel(
  description = "@what?            -> The Video entity.\n@why?             ->\n@use-to           -> Lưu thông tin video mà người dùng upload lên\n@commonly-used-in ->\n\n@describe         ->"
)
@GeneratedByJHipster
public class VideoDTO implements Serializable {

  private Long id;

  /**
   * uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)
   */
  @NotNull
  @ApiModelProperty(
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)",
    required = true
  )
  private UUID uuid;

  private String name;

  /**
   * width          : chiều rộng video
   */
  @ApiModelProperty(value = "width          : chiều rộng video")
  private Integer width;

  /**
   * height         : chiều cao video
   */
  @ApiModelProperty(value = "height         : chiều cao video")
  private Integer height;

  /**
   * qualityImage   : chất lượng ảnh sau khi xử lý
   */
  @DecimalMin(value = "0")
  @DecimalMax(value = "1")
  @ApiModelProperty(value = "qualityImage   : chất lượng ảnh sau khi xử lý")
  private Float qualityImage;

  /**
   * qualityAudio   : chất lượng âm thanh sau khi xử lý
   */
  @DecimalMin(value = "0")
  @DecimalMax(value = "1")
  @ApiModelProperty(value = "qualityAudio   : chất lượng âm thanh sau khi xử lý")
  private Float qualityAudio;

  /**
   * quality        : chất lượng chung sau khi xử lý
   */
  @DecimalMin(value = "0")
  @DecimalMax(value = "1")
  @ApiModelProperty(value = "quality        : chất lượng chung sau khi xử lý")
  private Float quality;

  /**
   * pixelSize      : kích thước của ảnh
   */
  @ApiModelProperty(value = "pixelSize      : kích thước của ảnh")
  private Integer pixelSize;

  /**
   * priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)
   */
  @ApiModelProperty(value = "priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
  private Long priorityIndex;

  /**
   * dataSize       : kích thước file theo byte
   */
  @ApiModelProperty(value = "dataSize       : kích thước file theo byte")
  private Long dataSize;

  private FileInfoDTO fileInfo;

  private BaseInfoDTO baseInfo;

  private VideoDTO videoOriginal;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Float getQualityImage() {
    return qualityImage;
  }

  public void setQualityImage(Float qualityImage) {
    this.qualityImage = qualityImage;
  }

  public Float getQualityAudio() {
    return qualityAudio;
  }

  public void setQualityAudio(Float qualityAudio) {
    this.qualityAudio = qualityAudio;
  }

  public Float getQuality() {
    return quality;
  }

  public void setQuality(Float quality) {
    this.quality = quality;
  }

  public Integer getPixelSize() {
    return pixelSize;
  }

  public void setPixelSize(Integer pixelSize) {
    this.pixelSize = pixelSize;
  }

  public Long getPriorityIndex() {
    return priorityIndex;
  }

  public void setPriorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
  }

  public Long getDataSize() {
    return dataSize;
  }

  public void setDataSize(Long dataSize) {
    this.dataSize = dataSize;
  }

  public FileInfoDTO getFileInfo() {
    return fileInfo;
  }

  public void setFileInfo(FileInfoDTO fileInfo) {
    this.fileInfo = fileInfo;
  }

  public BaseInfoDTO getBaseInfo() {
    return baseInfo;
  }

  public void setBaseInfo(BaseInfoDTO baseInfo) {
    this.baseInfo = baseInfo;
  }

  public VideoDTO getVideoOriginal() {
    return videoOriginal;
  }

  public void setVideoOriginal(VideoDTO videoOriginal) {
    this.videoOriginal = videoOriginal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof VideoDTO)) {
      return false;
    }

    VideoDTO videoDTO = (VideoDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, videoDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "VideoDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", qualityImage=" + getQualityImage() +
            ", qualityAudio=" + getQualityAudio() +
            ", quality=" + getQuality() +
            ", pixelSize=" + getPixelSize() +
            ", priorityIndex=" + getPriorityIndex() +
            ", dataSize=" + getDataSize() +
            ", fileInfo=" + getFileInfo() +
            ", baseInfo=" + getBaseInfo() +
            ", videoOriginal=" + getVideoOriginal() +
            "}";
    }
}
