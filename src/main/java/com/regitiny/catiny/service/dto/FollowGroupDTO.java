package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.FollowGroup} entity.
 */
@ApiModel(
  description = "@what?            -> The FollowGroup entity.\n@why?             ->\n@use-to           -> Quản lý các nhóm mà người dùng đăng ký theo dõi\n@commonly-used-in -> Chủ đề mà người dùng theo dõi\n\n@describe         ->"
)
@GeneratedByJHipster
public class FollowGroupDTO implements Serializable {

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

  private GroupPostDTO followGroupDetails;

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

  public BaseInfoDTO getBaseInfo() {
    return baseInfo;
  }

  public void setBaseInfo(BaseInfoDTO baseInfo) {
    this.baseInfo = baseInfo;
  }

  public GroupPostDTO getFollowGroupDetails() {
    return followGroupDetails;
  }

  public void setFollowGroupDetails(GroupPostDTO followGroupDetails) {
    this.followGroupDetails = followGroupDetails;
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
    if (!(o instanceof FollowGroupDTO)) {
      return false;
    }

    FollowGroupDTO followGroupDTO = (FollowGroupDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, followGroupDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "FollowGroupDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", baseInfo=" + getBaseInfo() +
            ", followGroupDetails=" + getFollowGroupDetails() +
            ", masterUser=" + getMasterUser() +
            "}";
    }
}
