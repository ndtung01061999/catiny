package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.domain.enumeration.StatusName;
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
public class AccountStatusModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  @ApiModelProperty("accountStatus  : thạng thái hiện tại của người dùng")
  private StatusName accountStatus;

  @ApiModelProperty("lastVisited    : thời gian truy cập cuối cùng gần nhất")
  private Instant lastVisited;

  @ApiModelProperty("statusComment  : người dùng comment lại trạng thái để hiển thị ra nếu muốn")
  private String statusComment;

  private BaseInfoDTO baseInfo;

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

    @ApiModelProperty("accountStatus  : thạng thái hiện tại của người dùng")
    private StatusName accountStatus;

    @ApiModelProperty("lastVisited    : thời gian truy cập cuối cùng gần nhất")
    private Instant lastVisited;

    @ApiModelProperty("statusComment  : người dùng comment lại trạng thái để hiển thị ra nếu muốn")
    private String statusComment;

    private BaseInfoDTO baseInfo;
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

    @ApiModelProperty("accountStatus  : thạng thái hiện tại của người dùng")
    private StatusName accountStatus;

    @ApiModelProperty("lastVisited    : thời gian truy cập cuối cùng gần nhất")
    private Instant lastVisited;

    @ApiModelProperty("statusComment  : người dùng comment lại trạng thái để hiển thị ra nếu muốn")
    private String statusComment;

    private BaseInfoDTO baseInfo;
  }
}
