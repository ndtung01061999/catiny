package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.domain.enumeration.EventType;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
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
public class EventModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  @ApiModelProperty("title          : tiêu đề event")
  private String title;

  @ApiModelProperty("avatar         : @type Json -> ảnh đại diện event")
  @Lob
  private String avatar;

  @ApiModelProperty("content        : nội dung event")
  @Lob
  private String content;

  @ApiModelProperty("type           :loại event")
  private EventType type;

  @ApiModelProperty("description    : mô tả chi tết về event")
  @Lob
  private String description;

  @ApiModelProperty("startTime      : thời gian bắt đầu")
  private Instant startTime;

  @ApiModelProperty("endTime        : thời gian kết thúc")
  private Instant endTime;

  @ApiModelProperty("tagLine        : thẻ cho event")
  private String tagLine;

  @ApiModelProperty("imageCollection: @type Json -> tập ảnh của event")
  @Lob
  private String imageCollection;

  @ApiModelProperty("videoCollection: @type Json -> tập video của event")
  @Lob
  private String videoCollection;

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

    @ApiModelProperty("title          : tiêu đề event")
    private String title;

    @ApiModelProperty("avatar         : @type Json -> ảnh đại diện event")
    @Lob
    private String avatar;

    @ApiModelProperty("content        : nội dung event")
    @Lob
    private String content;

    @ApiModelProperty("type           :loại event")
    private EventType type;

    @ApiModelProperty("description    : mô tả chi tết về event")
    @Lob
    private String description;

    @ApiModelProperty("startTime      : thời gian bắt đầu")
    private Instant startTime;

    @ApiModelProperty("endTime        : thời gian kết thúc")
    private Instant endTime;

    @ApiModelProperty("tagLine        : thẻ cho event")
    private String tagLine;

    @ApiModelProperty("imageCollection: @type Json -> tập ảnh của event")
    @Lob
    private String imageCollection;

    @ApiModelProperty("videoCollection: @type Json -> tập video của event")
    @Lob
    private String videoCollection;

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

    @ApiModelProperty("title          : tiêu đề event")
    private String title;

    @ApiModelProperty("avatar         : @type Json -> ảnh đại diện event")
    @Lob
    private String avatar;

    @ApiModelProperty("content        : nội dung event")
    @Lob
    private String content;

    @ApiModelProperty("type           :loại event")
    private EventType type;

    @ApiModelProperty("description    : mô tả chi tết về event")
    @Lob
    private String description;

    @ApiModelProperty("startTime      : thời gian bắt đầu")
    private Instant startTime;

    @ApiModelProperty("endTime        : thời gian kết thúc")
    private Instant endTime;

    @ApiModelProperty("tagLine        : thẻ cho event")
    private String tagLine;

    @ApiModelProperty("imageCollection: @type Json -> tập ảnh của event")
    @Lob
    private String imageCollection;

    @ApiModelProperty("videoCollection: @type Json -> tập video của event")
    @Lob
    private String videoCollection;

    private BaseInfoDTO baseInfo;
  }
}
