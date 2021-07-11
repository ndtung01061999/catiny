package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.Image} entity.
 */
@ApiModel(
  description = "@what?            -> The Image entity.\n@why?             ->\n@use-to           -> Lưu thông tin Ảnh mà người dùng upload lên\n@commonly-used-in ->\n\n@describe         ->"
)
@GeneratedByJHipster
public class ImageDTO implements Serializable {

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

  private FileInfoDTO fileInfo;

  private BaseInfoDTO baseInfo;

  private ImageDTO imageOriginal;

  private EventDTO event;

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

  public ImageDTO getImageOriginal() {
    return imageOriginal;
  }

  public void setImageOriginal(ImageDTO imageOriginal) {
    this.imageOriginal = imageOriginal;
  }

  public EventDTO getEvent() {
    return event;
  }

  public void setEvent(EventDTO event) {
    this.event = event;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ImageDTO)) {
      return false;
    }

    ImageDTO imageDTO = (ImageDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, imageDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "ImageDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", fileInfo=" + getFileInfo() +
            ", baseInfo=" + getBaseInfo() +
            ", imageOriginal=" + getImageOriginal() +
            ", event=" + getEvent() +
            "}";
    }
}
