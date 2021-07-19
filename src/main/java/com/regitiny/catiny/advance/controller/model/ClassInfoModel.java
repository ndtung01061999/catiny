package com.regitiny.catiny.advance.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassInfoModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  @ApiModelProperty("packageName *  : tên package")
  private String namePackage;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "fullName *     : tên đầy đủ của class . package+ClassName"
  )
  private String fullName;

  @ApiModelProperty("classname *    : tên của class")
  private String className;

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

    @ApiModelProperty("packageName *  : tên package")
    private String namePackage;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "fullName *     : tên đầy đủ của class . package+ClassName"
    )
    private String fullName;

    @ApiModelProperty("classname *    : tên của class")
    private String className;
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

    @ApiModelProperty("packageName *  : tên package")
    private String namePackage;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "fullName *     : tên đầy đủ của class . package+ClassName"
    )
    private String fullName;

    @ApiModelProperty("classname *    : tên của class")
    private String className;
  }
}
