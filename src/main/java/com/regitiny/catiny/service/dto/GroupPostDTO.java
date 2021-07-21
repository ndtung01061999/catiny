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
 * A DTO for the {@link com.regitiny.catiny.domain.GroupPost} entity.
 */
@ApiModel(
  description = "@what?            -> The GroupPost entity\n@why?             -> mọi người cần tạo ra một nhóm riêng hoặc chung để có thể trao đổi\n@use-to           -> quản lý nhóm\n@commonly-used-in -> các nhóm\n\n@describe         ->"
)
@GeneratedByJHipster
public class GroupPostDTO implements Serializable {

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
   * name           : tên của group này
   */
  @NotNull
  @ApiModelProperty(value = "name           : tên của group này", required = true)
  private String name;

  /**
   * avatar : @type Json -> ảnh đại diện của Group
   */
  @ApiModelProperty(value = "avatar : @type Json -> ảnh đại diện của Group")
  @Lob
  private String avatar;

  /**
   * quickInfo      : @type Json -> thông tin giới thiệu sơ qua của group này
   */
  @ApiModelProperty(value = "quickInfo      : @type Json -> thông tin giới thiệu sơ qua của group này")
  @Lob
  private String quickInfo;

  private GroupProfileDTO profile;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getQuickInfo() {
    return quickInfo;
  }

  public void setQuickInfo(String quickInfo) {
    this.quickInfo = quickInfo;
  }

  public GroupProfileDTO getProfile() {
    return profile;
  }

  public void setProfile(GroupProfileDTO profile) {
    this.profile = profile;
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
    if (!(o instanceof GroupPostDTO)) {
      return false;
    }

    GroupPostDTO groupPostDTO = (GroupPostDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, groupPostDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "GroupPostDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", quickInfo='" + getQuickInfo() + "'" +
            ", profile=" + getProfile() +
            ", baseInfo=" + getBaseInfo() +
            "}";
    }
}
