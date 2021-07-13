package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.domain.enumeration.DeviceType;
import com.regitiny.catiny.domain.enumeration.StatusName;
import com.regitiny.catiny.service.dto.AccountStatusDTO;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStatusModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  @ApiModelProperty("deviceName     : tên của thiết bị nếu có")
  private String deviceName;

  @ApiModelProperty("deviceName     : loại thiết bị")
  private DeviceType deviceType;

  @ApiModelProperty("deviceStatus   : trạng thái hiện tại của thiết bị là gì")
  private StatusName deviceStatus;

  @ApiModelProperty("lastVisited    : thời gian truy cập lần cuối cùng của thiết bị này")
  private Instant lastVisited;

  @ApiModelProperty("statusComment  : người dùng comment lại trạng thái nếu muốn")
  private String statusComment;

  private BaseInfoDTO baseInfo;

  private AccountStatusDTO accountStatus;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request implements Serializable
  {
    private Long id;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
    )
    private UUID uuid;

    @ApiModelProperty("deviceName     : tên của thiết bị nếu có")
    private String deviceName;

    @ApiModelProperty("deviceName     : loại thiết bị")
    private DeviceType deviceType;

    @ApiModelProperty("deviceStatus   : trạng thái hiện tại của thiết bị là gì")
    private StatusName deviceStatus;

    @ApiModelProperty("lastVisited    : thời gian truy cập lần cuối cùng của thiết bị này")
    private Instant lastVisited;

    @ApiModelProperty("statusComment  : người dùng comment lại trạng thái nếu muốn")
    private String statusComment;

    private BaseInfoDTO baseInfo;

    private AccountStatusDTO accountStatus;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Response implements Serializable
  {
    private Long id;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
    )
    private UUID uuid;

    @ApiModelProperty("deviceName     : tên của thiết bị nếu có")
    private String deviceName;

    @ApiModelProperty("deviceName     : loại thiết bị")
    private DeviceType deviceType;

    @ApiModelProperty("deviceStatus   : trạng thái hiện tại của thiết bị là gì")
    private StatusName deviceStatus;

    @ApiModelProperty("lastVisited    : thời gian truy cập lần cuối cùng của thiết bị này")
    private Instant lastVisited;

    @ApiModelProperty("statusComment  : người dùng comment lại trạng thái nếu muốn")
    private String statusComment;

    private BaseInfoDTO baseInfo;

    private AccountStatusDTO accountStatus;
  }
}
