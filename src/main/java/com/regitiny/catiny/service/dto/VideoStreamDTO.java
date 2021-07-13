package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.VideoStream} entity.
 */
@ApiModel(
  description = "@what?            -> The VideoStream entity.\n@why?             ->\n@use-to           -> Lưu thông tin video ... khi đã kết thúc stream\n@commonly-used-in -> Sau khi kết thức stream thì video lưu lai cũng chỉ tương tự như một video thông thường\n\n@describe         ->"
)
@GeneratedByJHipster
public class VideoStreamDTO implements Serializable {

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

  private VideoDTO video;

  private BaseInfoDTO baseInfo;

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

  public VideoDTO getVideo() {
    return video;
  }

  public void setVideo(VideoDTO video) {
    this.video = video;
  }

  public BaseInfoDTO getBaseInfo() {
    return baseInfo;
  }

  public void setBaseInfo(BaseInfoDTO baseInfo) {
    this.baseInfo = baseInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof VideoStreamDTO)) {
      return false;
    }

    VideoStreamDTO videoStreamDTO = (VideoStreamDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, videoStreamDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "VideoStreamDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", video=" + getVideo() +
            ", baseInfo=" + getBaseInfo() +
            "}";
    }
}
