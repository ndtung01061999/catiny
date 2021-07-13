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
 * A DTO for the {@link com.regitiny.catiny.domain.PagePost} entity.
 */
@ApiModel(
  description = "@what?            -> The PagePost entity.\n@why?             ->\n@use-to           -> Lưu các Trang người dùng tạo ra\n@commonly-used-in -> Cũng tương tự như bài đăng của một người dùng những sẽ chuyên biệt về  một chủ đề\n\n@describe         ->"
)
@GeneratedByJHipster
public class PagePostDTO implements Serializable {

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
   * name           : tên của page này
   */
  @NotNull
  @ApiModelProperty(value = "name           : tên của page này", required = true)
  private String name;

  /**
   * quickInfo      : @type Json ->thông tin nổi bật giới thiệu sơ qua về page
   */
  @ApiModelProperty(value = "quickInfo      : @type Json ->thông tin nổi bật giới thiệu sơ qua về page")
  @Lob
  private String quickInfo;

  private PageProfileDTO profile;

  private BaseInfoDTO baseInfo;

  private MasterUserDTO masterUser;

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

  public String getQuickInfo() {
    return quickInfo;
  }

  public void setQuickInfo(String quickInfo) {
    this.quickInfo = quickInfo;
  }

  public PageProfileDTO getProfile() {
    return profile;
  }

  public void setProfile(PageProfileDTO profile) {
    this.profile = profile;
  }

  public BaseInfoDTO getBaseInfo() {
    return baseInfo;
  }

  public void setBaseInfo(BaseInfoDTO baseInfo) {
    this.baseInfo = baseInfo;
  }

  public MasterUserDTO getMasterUser() {
    return masterUser;
  }

  public void setMasterUser(MasterUserDTO masterUser) {
    this.masterUser = masterUser;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PagePostDTO)) {
      return false;
    }

    PagePostDTO pagePostDTO = (PagePostDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, pagePostDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PagePostDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", quickInfo='" + getQuickInfo() + "'" +
            ", profile=" + getProfile() +
            ", baseInfo=" + getBaseInfo() +
            ", masterUser=" + getMasterUser() +
            "}";
    }
}
