package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionModel implements Serializable
{
  private Long id;

  @ApiModelProperty("quyền đọc")
  private Boolean read;

  @ApiModelProperty("quyền ghi và sửa")
  private Boolean write;

  @ApiModelProperty("quyền chia sẻ")
  private Boolean share;

  @ApiModelProperty("quyền xóa")
  private Boolean delete;

  @ApiModelProperty("quyền trao quyền cho user khác")
  private Boolean add;

  @ApiModelProperty("cấp độ 0->* số nhỏ hơn sẽ có quyền lớn hơn")
  private Integer level;

  private BaseInfoDTO baseInfo;

  private MasterUserDTO masterUser;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request implements Serializable
  {
    private Long id;

    @ApiModelProperty("quyền đọc")
    private Boolean read;

    @ApiModelProperty("quyền ghi và sửa")
    private Boolean write;

    @ApiModelProperty("quyền chia sẻ")
    private Boolean share;

    @ApiModelProperty("quyền xóa")
    private Boolean delete;

    @ApiModelProperty("quyền trao quyền cho user khác")
    private Boolean add;

    @ApiModelProperty("cấp độ 0->* số nhỏ hơn sẽ có quyền lớn hơn")
    private Integer level;

    private BaseInfoDTO baseInfo;

    private MasterUserDTO masterUser;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Response implements Serializable
  {
    private Long id;

    @ApiModelProperty("quyền đọc")
    private Boolean read;

    @ApiModelProperty("quyền ghi và sửa")
    private Boolean write;

    @ApiModelProperty("quyền chia sẻ")
    private Boolean share;

    @ApiModelProperty("quyền xóa")
    private Boolean delete;

    @ApiModelProperty("quyền trao quyền cho user khác")
    private Boolean add;

    @ApiModelProperty("cấp độ 0->* số nhỏ hơn sẽ có quyền lớn hơn")
    private Integer level;

    private BaseInfoDTO baseInfo;

    private MasterUserDTO masterUser;
  }
}
