package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.dto.GroupProfileDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupPostModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "name           : tên của group này"
  )
  private String name;

  @ApiModelProperty("avatar : @type Json -> ảnh đại diện của Group")
  @Lob
  private String avatar;

  @ApiModelProperty("quickInfo      : @type Json -> thông tin giới thiệu sơ qua của group này")
  @Lob
  private String quickInfo;

  private GroupProfileDTO profile;

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

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "name           : tên của group này"
    )
    private String name;

    @ApiModelProperty("avatar : @type Json -> ảnh đại diện của Group")
    @Lob
    private String avatar;

    @ApiModelProperty("quickInfo      : @type Json -> thông tin giới thiệu sơ qua của group này")
    @Lob
    private String quickInfo;

    private GroupProfileDTO profile;

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

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "name           : tên của group này"
    )
    private String name;

    @ApiModelProperty("avatar : @type Json -> ảnh đại diện của Group")
    @Lob
    private String avatar;

    @ApiModelProperty("quickInfo      : @type Json -> thông tin giới thiệu sơ qua của group này")
    @Lob
    private String quickInfo;

    private GroupProfileDTO profile;

    private BaseInfoDTO baseInfo;
  }
}
