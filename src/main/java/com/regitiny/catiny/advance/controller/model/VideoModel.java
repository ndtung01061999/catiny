package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.dto.FileInfoDTO;
import com.regitiny.catiny.service.dto.VideoDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  private String name;

  @ApiModelProperty("width          : chiều rộng video")
  private Integer width;

  @ApiModelProperty("height         : chiều cao video")
  private Integer height;

  @DecimalMin("0")
  @DecimalMax("1")
  @ApiModelProperty("qualityImage   : chất lượng ảnh sau khi xử lý")
  private Float qualityImage;

  @DecimalMin("0")
  @DecimalMax("1")
  @ApiModelProperty("qualityAudio   : chất lượng âm thanh sau khi xử lý")
  private Float qualityAudio;

  @DecimalMin("0")
  @DecimalMax("1")
  @ApiModelProperty("quality        : chất lượng chung sau khi xử lý")
  private Float quality;

  @ApiModelProperty("pixelSize      : kích thước của ảnh")
  private Integer pixelSize;

  @ApiModelProperty("priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
  private Long priorityIndex;

  @ApiModelProperty("dataSize       : kích thước file theo byte")
  private Long dataSize;

  private FileInfoDTO fileInfo;

  private BaseInfoDTO baseInfo;

  private VideoDTO videoOriginal;

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

    private String name;

    @ApiModelProperty("width          : chiều rộng video")
    private Integer width;

    @ApiModelProperty("height         : chiều cao video")
    private Integer height;

    @DecimalMin("0")
    @DecimalMax("1")
    @ApiModelProperty("qualityImage   : chất lượng ảnh sau khi xử lý")
    private Float qualityImage;

    @DecimalMin("0")
    @DecimalMax("1")
    @ApiModelProperty("qualityAudio   : chất lượng âm thanh sau khi xử lý")
    private Float qualityAudio;

    @DecimalMin("0")
    @DecimalMax("1")
    @ApiModelProperty("quality        : chất lượng chung sau khi xử lý")
    private Float quality;

    @ApiModelProperty("pixelSize      : kích thước của ảnh")
    private Integer pixelSize;

    @ApiModelProperty("priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
    private Long priorityIndex;

    @ApiModelProperty("dataSize       : kích thước file theo byte")
    private Long dataSize;

    private FileInfoDTO fileInfo;

    private BaseInfoDTO baseInfo;

    private VideoDTO videoOriginal;
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

    private String name;

    @ApiModelProperty("width          : chiều rộng video")
    private Integer width;

    @ApiModelProperty("height         : chiều cao video")
    private Integer height;

    @DecimalMin("0")
    @DecimalMax("1")
    @ApiModelProperty("qualityImage   : chất lượng ảnh sau khi xử lý")
    private Float qualityImage;

    @DecimalMin("0")
    @DecimalMax("1")
    @ApiModelProperty("qualityAudio   : chất lượng âm thanh sau khi xử lý")
    private Float qualityAudio;

    @DecimalMin("0")
    @DecimalMax("1")
    @ApiModelProperty("quality        : chất lượng chung sau khi xử lý")
    private Float quality;

    @ApiModelProperty("pixelSize      : kích thước của ảnh")
    private Integer pixelSize;

    @ApiModelProperty("priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
    private Long priorityIndex;

    @ApiModelProperty("dataSize       : kích thước file theo byte")
    private Long dataSize;

    private FileInfoDTO fileInfo;

    private BaseInfoDTO baseInfo;

    private VideoDTO videoOriginal;
  }
}
