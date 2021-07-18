package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.Permission} entity.
 */
@GeneratedByJHipster
public class PermissionDTO implements Serializable {

  private Long id;

  /**
   * quyền đọc
   */
  @ApiModelProperty(value = "quyền đọc")
  private Boolean read;

  /**
   * quyền ghi và sửa
   */
  @ApiModelProperty(value = "quyền ghi và sửa")
  private Boolean write;

  /**
   * quyền chia sẻ
   */
  @ApiModelProperty(value = "quyền chia sẻ")
  private Boolean share;

  /**
   * quyền xóa
   */
  @ApiModelProperty(value = "quyền xóa")
  private Boolean delete;

  /**
   * quyền trao quyền cho user khác
   */
  @ApiModelProperty(value = "quyền trao quyền cho user khác")
  private Boolean add;

  /**
   * cấp độ 0->* số nhỏ hơn sẽ có quyền lớn hơn
   */
  @ApiModelProperty(value = "cấp độ 0->* số nhỏ hơn sẽ có quyền lớn hơn")
  private Integer level;

  private BaseInfoDTO baseInfo;

  private MasterUserDTO masterUser;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean getRead() {
    return read;
  }

  public void setRead(Boolean read) {
    this.read = read;
  }

  public Boolean getWrite() {
    return write;
  }

  public void setWrite(Boolean write) {
    this.write = write;
  }

  public Boolean getShare() {
    return share;
  }

  public void setShare(Boolean share) {
    this.share = share;
  }

  public Boolean getDelete() {
    return delete;
  }

  public void setDelete(Boolean delete) {
    this.delete = delete;
  }

  public Boolean getAdd() {
    return add;
  }

  public void setAdd(Boolean add) {
    this.add = add;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
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
    if (!(o instanceof PermissionDTO)) {
      return false;
    }

    PermissionDTO permissionDTO = (PermissionDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, permissionDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PermissionDTO{" +
            "id=" + getId() +
            ", read='" + getRead() + "'" +
            ", write='" + getWrite() + "'" +
            ", share='" + getShare() + "'" +
            ", delete='" + getDelete() + "'" +
            ", add='" + getAdd() + "'" +
            ", level=" + getLevel() +
            ", baseInfo=" + getBaseInfo() +
            ", masterUser=" + getMasterUser() +
            "}";
    }
}
