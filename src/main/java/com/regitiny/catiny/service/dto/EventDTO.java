package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.EventType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.Event} entity.
 */
@ApiModel(
  description = "@what?            -> The Event entity.\n@why?             ->\n@use-to           -> Lưu những sự kiện\n@commonly-used-in -> Tạo Sự kiện\n\n@describe         ->"
)
@GeneratedByJHipster
public class EventDTO implements Serializable {

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

  /**
   * title          : tiêu đề event
   */
  @ApiModelProperty(value = "title          : tiêu đề event")
  private String title;

  /**
   * avatar         : @type Json -> ảnh đại diện event
   */
  @ApiModelProperty(value = "avatar         : @type Json -> ảnh đại diện event")
  @Lob
  private String avatar;

  /**
   * content        : nội dung event
   */
  @ApiModelProperty(value = "content        : nội dung event")
  @Lob
  private String content;

  /**
   * type           :loại event
   */
  @ApiModelProperty(value = "type           :loại event")
  private EventType type;

  /**
   * description    : mô tả chi tết về event
   */
  @ApiModelProperty(value = "description    : mô tả chi tết về event")
  @Lob
  private String description;

  /**
   * startTime      : thời gian bắt đầu
   */
  @ApiModelProperty(value = "startTime      : thời gian bắt đầu")
  private Instant startTime;

  /**
   * endTime        : thời gian kết thúc
   */
  @ApiModelProperty(value = "endTime        : thời gian kết thúc")
  private Instant endTime;

  /**
   * tagLine        : thẻ cho event
   */
  @ApiModelProperty(value = "tagLine        : thẻ cho event")
  private String tagLine;

  /**
   * imageCollection: @type Json -> tập ảnh của event
   */
  @ApiModelProperty(value = "imageCollection: @type Json -> tập ảnh của event")
  @Lob
  private String imageCollection;

  /**
   * videoCollection: @type Json -> tập video của event
   */
  @ApiModelProperty(value = "videoCollection: @type Json -> tập video của event")
  @Lob
  private String videoCollection;

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public EventType getType() {
    return type;
  }

  public void setType(EventType type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Instant getStartTime() {
    return startTime;
  }

  public void setStartTime(Instant startTime) {
    this.startTime = startTime;
  }

  public Instant getEndTime() {
    return endTime;
  }

  public void setEndTime(Instant endTime) {
    this.endTime = endTime;
  }

  public String getTagLine() {
    return tagLine;
  }

  public void setTagLine(String tagLine) {
    this.tagLine = tagLine;
  }

  public String getImageCollection() {
    return imageCollection;
  }

  public void setImageCollection(String imageCollection) {
    this.imageCollection = imageCollection;
  }

  public String getVideoCollection() {
    return videoCollection;
  }

  public void setVideoCollection(String videoCollection) {
    this.videoCollection = videoCollection;
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
    if (!(o instanceof EventDTO)) {
      return false;
    }

    EventDTO eventDTO = (EventDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, eventDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EventDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", title='" + getTitle() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", content='" + getContent() + "'" +
            ", type='" + getType() + "'" +
            ", description='" + getDescription() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", tagLine='" + getTagLine() + "'" +
            ", imageCollection='" + getImageCollection() + "'" +
            ", videoCollection='" + getVideoCollection() + "'" +
            ", baseInfo=" + getBaseInfo() +
            "}";
    }
}
