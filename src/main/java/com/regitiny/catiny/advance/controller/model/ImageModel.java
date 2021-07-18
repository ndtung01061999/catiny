package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.dto.FileInfoDTO;
import com.regitiny.catiny.service.dto.ImageDTO;
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
public class ImageModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  @ApiModelProperty("name           : tên của ảnh . muốn lấy ảnh sẽ gọi theo tên này. sẽ ra một danh sách các anh gồm (ảnh nguyên gốc , các ảnh đã tối ưu , cắt ... từ ảnh gốc đó)")
  private String name;

  @ApiModelProperty("width          : chiều rộng ảnh")
  private Integer width;

  @ApiModelProperty("height         : chiều cao ảnh")
  private Integer height;

  @DecimalMin("0")
  @DecimalMax("1")
  @ApiModelProperty("quality        : chất lượng sau khi xử lý")
  private Float quality;

  @ApiModelProperty("pixelSize      : kích thước của ảnh")
  private Integer pixelSize;

  @ApiModelProperty("priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
  private Long priorityIndex;

  @ApiModelProperty("dataSize       : kích thước file theo byte")
  private Long dataSize;

  private FileInfoDTO fileInfo;

  private BaseInfoDTO baseInfo;

  private ImageDTO imageOriginal;

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

    @ApiModelProperty("name           : tên của ảnh . muốn lấy ảnh sẽ gọi theo tên này. sẽ ra một danh sách các anh gồm (ảnh nguyên gốc , các ảnh đã tối ưu , cắt ... từ ảnh gốc đó)")
    private String name;

    @ApiModelProperty("width          : chiều rộng ảnh")
    private Integer width;

    @ApiModelProperty("height         : chiều cao ảnh")
    private Integer height;

    @DecimalMin("0")
    @DecimalMax("1")
    @ApiModelProperty("quality        : chất lượng sau khi xử lý")
    private Float quality;

    @ApiModelProperty("pixelSize      : kích thước của ảnh")
    private Integer pixelSize;

    @ApiModelProperty("priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
    private Long priorityIndex;

    @ApiModelProperty("dataSize       : kích thước file theo byte")
    private Long dataSize;

    private FileInfoDTO fileInfo;

    private BaseInfoDTO baseInfo;

    private ImageDTO imageOriginal;
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

    @ApiModelProperty("name           : tên của ảnh . muốn lấy ảnh sẽ gọi theo tên này. sẽ ra một danh sách các anh gồm (ảnh nguyên gốc , các ảnh đã tối ưu , cắt ... từ ảnh gốc đó)")
    private String name;

    @ApiModelProperty("width          : chiều rộng ảnh")
    private Integer width;

    @ApiModelProperty("height         : chiều cao ảnh")
    private Integer height;

    @DecimalMin("0")
    @DecimalMax("1")
    @ApiModelProperty("quality        : chất lượng sau khi xử lý")
    private Float quality;

    @ApiModelProperty("pixelSize      : kích thước của ảnh")
    private Integer pixelSize;

    @ApiModelProperty("priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
    private Long priorityIndex;

    @ApiModelProperty("dataSize       : kích thước file theo byte")
    private Long dataSize;

    private FileInfoDTO fileInfo;

    private BaseInfoDTO baseInfo;

    private ImageDTO imageOriginal;
  }
}
