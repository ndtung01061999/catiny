package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.PageProfile} entity.
 */
@ApiModel(
  description = "@what?            -> The PageProfile entity\n@why?             ->\n@use-to           -> Lưu phần giới thiệu của các trang\n@commonly-used-in -> hiển thị giới thiệu của các trang\n\n@describe         ->"
)
@GeneratedByJHipster
public class PageProfileDTO implements Serializable {

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
    if (!(o instanceof PageProfileDTO)) {
      return false;
    }

    PageProfileDTO pageProfileDTO = (PageProfileDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, pageProfileDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PageProfileDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", baseInfo=" + getBaseInfo() +
            "}";
    }
}
