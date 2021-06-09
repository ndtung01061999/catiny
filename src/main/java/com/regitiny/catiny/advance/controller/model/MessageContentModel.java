package com.regitiny.catiny.advance.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageContentModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid"
  )
  private UUID uuid;

  @NotNull
  private String groupId;

  @Lob
  private String content;

  private String sender;

  private String status;

  @ApiModelProperty("searchField")
  @Lob
  private String searchField;

  @ApiModelProperty("role")
  private String role;

  @ApiModelProperty("createdDate")
  private Instant createdDate;

  @ApiModelProperty("modifiedDate")
  private Instant modifiedDate;

  @ApiModelProperty("createdBy")
  private String createdBy;

  @ApiModelProperty("modifiedBy")
  private String modifiedBy;

  @ApiModelProperty("comment")
  private String comment;

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
      value = "uuid"
    )
    private UUID uuid;

    @NotNull
    private String groupId;

    @Lob
    private String content;

    private String sender;

    private String status;

    @ApiModelProperty("searchField")
    @Lob
    private String searchField;

    @ApiModelProperty("role")
    private String role;

    @ApiModelProperty("createdDate")
    private Instant createdDate;

    @ApiModelProperty("modifiedDate")
    private Instant modifiedDate;

    @ApiModelProperty("createdBy")
    private String createdBy;

    @ApiModelProperty("modifiedBy")
    private String modifiedBy;

    @ApiModelProperty("comment")
    private String comment;
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
      value = "uuid"
    )
    private UUID uuid;

    @NotNull
    private String groupId;

    @Lob
    private String content;

    private String sender;

    private String status;

    @ApiModelProperty("searchField")
    @Lob
    private String searchField;

    @ApiModelProperty("role")
    private String role;

    @ApiModelProperty("createdDate")
    private Instant createdDate;

    @ApiModelProperty("modifiedDate")
    private Instant modifiedDate;

    @ApiModelProperty("createdBy")
    private String createdBy;

    @ApiModelProperty("modifiedBy")
    private String modifiedBy;

    @ApiModelProperty("comment")
    private String comment;
  }
}
