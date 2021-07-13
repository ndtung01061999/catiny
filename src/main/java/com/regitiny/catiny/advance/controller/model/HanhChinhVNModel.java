package com.regitiny.catiny.advance.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HanhChinhVNModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "name Example(\"Ninh Kiều\")"
  )
  private String name;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "slug Example(\"ninh-kieu\")"
  )
  private String slug;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "type Example(\"quan\")"
  )
  private String type;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "nameWithType Example(\"Quận Ninh Kiều\")"
  )
  private String nameWithType;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "code Example(\"916\")"
  )
  private String code;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "parentCode Example(\"92\") , equal to 0 is the city"
  )
  private String parentCode;

  @ApiModelProperty("path Example(\"Ninh Kiều, Cần Thơ\")")
  private String path;

  @ApiModelProperty("pathWithType Example(\"Quận Ninh Kiều, Thành phố Cần Thơ\")")
  private String pathWithType;

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
      value = "name Example(\"Ninh Kiều\")"
    )
    private String name;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "slug Example(\"ninh-kieu\")"
    )
    private String slug;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "type Example(\"quan\")"
    )
    private String type;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "nameWithType Example(\"Quận Ninh Kiều\")"
    )
    private String nameWithType;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "code Example(\"916\")"
    )
    private String code;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "parentCode Example(\"92\") , equal to 0 is the city"
    )
    private String parentCode;

    @ApiModelProperty("path Example(\"Ninh Kiều, Cần Thơ\")")
    private String path;

    @ApiModelProperty("pathWithType Example(\"Quận Ninh Kiều, Thành phố Cần Thơ\")")
    private String pathWithType;
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
      value = "name Example(\"Ninh Kiều\")"
    )
    private String name;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "slug Example(\"ninh-kieu\")"
    )
    private String slug;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "type Example(\"quan\")"
    )
    private String type;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "nameWithType Example(\"Quận Ninh Kiều\")"
    )
    private String nameWithType;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "code Example(\"916\")"
    )
    private String code;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "parentCode Example(\"92\") , equal to 0 is the city"
    )
    private String parentCode;

    @ApiModelProperty("path Example(\"Ninh Kiều, Cần Thơ\")")
    private String path;

    @ApiModelProperty("pathWithType Example(\"Quận Ninh Kiều, Thành phố Cần Thơ\")")
    private String pathWithType;
  }
}
