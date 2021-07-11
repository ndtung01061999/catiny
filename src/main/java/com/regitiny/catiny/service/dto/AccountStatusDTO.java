package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.StatusName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.AccountStatus} entity.
 */
@ApiModel(
  description = "@what?         	-> The AccountStatus entity.\n@why?          	->\n@use-to:       	-> Lưu , quản lý trạng thái của tài khoản đang on hay off ...\n@commonly-used-in -> Những nghiệp vũ nhắn tin,thông báo cần biết trạng thái của tài khoản ...\n\n@describe      	->"
)
@GeneratedByJHipster
public class AccountStatusDTO implements Serializable {

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
   * accountStatus  : thạng thái hiện tại của người dùng
   */
  @ApiModelProperty(value = "accountStatus  : thạng thái hiện tại của người dùng")
  private StatusName accountStatus;

  /**
   * lastVisited    : thời gian truy cập cuối cùng gần nhất
   */
  @ApiModelProperty(value = "lastVisited    : thời gian truy cập cuối cùng gần nhất")
  private Instant lastVisited;

  /**
   * statusComment  : người dùng comment lại trạng thái để hiển thị ra nếu muốn
   */
  @ApiModelProperty(value = "statusComment  : người dùng comment lại trạng thái để hiển thị ra nếu muốn")
  private String statusComment;

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

  public StatusName getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(StatusName accountStatus) {
    this.accountStatus = accountStatus;
  }

  public Instant getLastVisited() {
    return lastVisited;
  }

  public void setLastVisited(Instant lastVisited) {
    this.lastVisited = lastVisited;
  }

  public String getStatusComment() {
    return statusComment;
  }

  public void setStatusComment(String statusComment) {
    this.statusComment = statusComment;
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
    if (!(o instanceof AccountStatusDTO)) {
      return false;
    }

    AccountStatusDTO accountStatusDTO = (AccountStatusDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, accountStatusDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "AccountStatusDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", accountStatus='" + getAccountStatus() + "'" +
            ", lastVisited='" + getLastVisited() + "'" +
            ", statusComment='" + getStatusComment() + "'" +
            ", baseInfo=" + getBaseInfo() +
            "}";
    }
}
