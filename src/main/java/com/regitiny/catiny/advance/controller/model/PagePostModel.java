package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.dto.PageProfileDTO;
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
public class PagePostModel implements Serializable
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
    value = "name           : tên của page này"
  )
  private String name;

  @ApiModelProperty("avatar : @type Json -> ảnh đại diện của Page")
  @Lob
  private String avatar;

  @ApiModelProperty("quickInfo      : @type Json ->thông tin nổi bật giới thiệu sơ qua về page")
  @Lob
  private String quickInfo;

  private PageProfileDTO profile;

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
      value = "name           : tên của page này"
    )
    private String name;

    @ApiModelProperty("avatar : @type Json -> ảnh đại diện của Page")
    @Lob
    private String avatar;

    @ApiModelProperty("quickInfo      : @type Json ->thông tin nổi bật giới thiệu sơ qua về page")
    @Lob
    private String quickInfo;

    private PageProfileDTO profile;

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
      value = "name           : tên của page này"
    )
    private String name;

    @ApiModelProperty("avatar : @type Json -> ảnh đại diện của Page")
    @Lob
    private String avatar;

    @ApiModelProperty("quickInfo      : @type Json ->thông tin nổi bật giới thiệu sơ qua về page")
    @Lob
    private String quickInfo;

    private PageProfileDTO profile;

    private BaseInfoDTO baseInfo;
  }
}
