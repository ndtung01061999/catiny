package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.GroupProfile} entity.
 */
@ApiModel(
  description = "@what?            -> The GroupProfile entity.\n@why?             ->\n@use-to           -> Những thông tin trong phần giới thiệu của nhóm được lưu tại đây\n@commonly-used-in -> Thường thấy trong phần giới thiệu của nhóm\n\n@describe         -> Đây là một bảng NoSQL dữ liệu một số field ở dưới dạng Json"
)
@GeneratedByJHipster
public class GroupProfileDTO implements Serializable {

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
    if (!(o instanceof GroupProfileDTO)) {
      return false;
    }

    GroupProfileDTO groupProfileDTO = (GroupProfileDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, groupProfileDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "GroupProfileDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", baseInfo=" + getBaseInfo() +
            "}";
    }
}
