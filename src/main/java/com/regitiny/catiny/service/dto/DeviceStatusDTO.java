package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.DeviceType;
import com.regitiny.catiny.domain.enumeration.StatusName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.DeviceStatus} entity.
 */
@ApiModel(
  description = "@what?            -> The DeviceStatus entity.\n@why?             ->\n@use-to           -> Những thiết bị đang truy cập thông tin chi tiết về chúng ...\n@commonly-used-in -> Những nghiệp vụ cần biết chi tiết trang thái của các thiết bị\n\n@describe         ->"
)
@GeneratedByJHipster
public class DeviceStatusDTO implements Serializable {

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
   * deviceName     : tên của thiết bị nếu có
   */
  @ApiModelProperty(value = "deviceName     : tên của thiết bị nếu có")
  private String deviceName;

  /**
   * deviceName     : loại thiết bị
   */
  @ApiModelProperty(value = "deviceName     : loại thiết bị")
  private DeviceType deviceType;

  /**
   * deviceStatus   : trạng thái hiện tại của thiết bị là gì
   */
  @ApiModelProperty(value = "deviceStatus   : trạng thái hiện tại của thiết bị là gì")
  private StatusName deviceStatus;

  /**
   * lastVisited    : thời gian truy cập lần cuối cùng của thiết bị này
   */
  @ApiModelProperty(value = "lastVisited    : thời gian truy cập lần cuối cùng của thiết bị này")
  private Instant lastVisited;

  /**
   * statusComment  : người dùng comment lại trạng thái nếu muốn
   */
  @ApiModelProperty(value = "statusComment  : người dùng comment lại trạng thái nếu muốn")
  private String statusComment;

  private BaseInfoDTO baseInfo;

  private AccountStatusDTO accountStatus;

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

  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public DeviceType getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(DeviceType deviceType) {
    this.deviceType = deviceType;
  }

  public StatusName getDeviceStatus() {
    return deviceStatus;
  }

  public void setDeviceStatus(StatusName deviceStatus) {
    this.deviceStatus = deviceStatus;
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

  public AccountStatusDTO getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(AccountStatusDTO accountStatus) {
    this.accountStatus = accountStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DeviceStatusDTO)) {
      return false;
    }

    DeviceStatusDTO deviceStatusDTO = (DeviceStatusDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, deviceStatusDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "DeviceStatusDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", deviceName='" + getDeviceName() + "'" +
            ", deviceType='" + getDeviceType() + "'" +
            ", deviceStatus='" + getDeviceStatus() + "'" +
            ", lastVisited='" + getLastVisited() + "'" +
            ", statusComment='" + getStatusComment() + "'" +
            ", baseInfo=" + getBaseInfo() +
            ", accountStatus=" + getAccountStatus() +
            "}";
    }
}
