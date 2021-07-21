package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.MessageGroup} entity.
 */
@ApiModel(
  description = "@what?            -> The MessageGroup entity.\n@why?             ->\n@use-to           -> Chứa thông tin các nhóm mà hiện tại người dùng đang ở trong đó (phần nhắn tin)\n@commonly-used-in -> Hiển thị các tin nhắn\n\n@describe         -> một nhóm tạo ra sẽ là một uuid . nếu nhắn tin cặp thì sẽ sắp xếp login sau đó hash md5 rồi chuyển thành định dạng uuid"
)
@GeneratedByJHipster
public class MessageGroupDTO implements Serializable {

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
   * groupName
   */
  @ApiModelProperty(value = "groupName")
  private String groupName;

  /**
   * avatar : @type Json -> ảnh đại diện của MessageGroup
   */
  @ApiModelProperty(value = "avatar : @type Json -> ảnh đại diện của MessageGroup")
  @Lob
  private String avatar;

  /**
   * addBy
   */
  @ApiModelProperty(value = "addBy")
  private String addBy;

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

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getAddBy() {
    return addBy;
  }

  public void setAddBy(String addBy) {
    this.addBy = addBy;
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
    if (!(o instanceof MessageGroupDTO)) {
      return false;
    }

    MessageGroupDTO messageGroupDTO = (MessageGroupDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, messageGroupDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MessageGroupDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", groupName='" + getGroupName() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", addBy='" + getAddBy() + "'" +
            ", baseInfo=" + getBaseInfo() +
            "}";
    }
}
