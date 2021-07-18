package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.NewsFeed} entity.
 */
@ApiModel(
  description = "@what?            -> The NewsFeed entity.\n@why?             -> người dùng mà xem trực tiếp các Post thì một số bài đăng sẽ không phù hợp dễ gây chán khi xem\n@use-to           -> Ở đây chứa thông tin của những Post hiển thị cho người dùng xem\n@commonly-used-in -> Được sử dụng trong phần hiển thị các bài đăng trên news feed\n\n@describe         -> trong phần bản tin thay vì hiển thị trực tiếp các Post cho người dùng xem\nta sẽ tính toán độ phù hợp và add vào bảng này sau đó cho người dùng xem"
)
@GeneratedByJHipster
public class NewsFeedDTO implements Serializable {

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
   * priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)
   */
  @ApiModelProperty(value = "priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
  private Long priorityIndex;

  private BaseInfoDTO baseInfo;

  private PostDTO post;

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

  public Long getPriorityIndex() {
    return priorityIndex;
  }

  public void setPriorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
  }

  public BaseInfoDTO getBaseInfo() {
    return baseInfo;
  }

  public void setBaseInfo(BaseInfoDTO baseInfo) {
    this.baseInfo = baseInfo;
  }

  public PostDTO getPost() {
    return post;
  }

  public void setPost(PostDTO post) {
    this.post = post;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NewsFeedDTO)) {
      return false;
    }

    NewsFeedDTO newsFeedDTO = (NewsFeedDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, newsFeedDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "NewsFeedDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", priorityIndex=" + getPriorityIndex() +
            ", baseInfo=" + getBaseInfo() +
            ", post=" + getPost() +
            "}";
    }
}
