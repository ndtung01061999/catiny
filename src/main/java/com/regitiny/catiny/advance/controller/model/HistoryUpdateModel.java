package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.service.dto.BaseInfoDTO;
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
public class HistoryUpdateModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  @ApiModelProperty("version        : phiên bản (bắt đầu từ 0)")
  private Integer version;

  @ApiModelProperty("content        : @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json")
  @Lob
  private String content;

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

    @ApiModelProperty("version        : phiên bản (bắt đầu từ 0)")
    private Integer version;

    @ApiModelProperty("content        : @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json")
    @Lob
    private String content;

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

    @ApiModelProperty("version        : phiên bản (bắt đầu từ 0)")
    private Integer version;

    @ApiModelProperty("content        : @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json")
    @Lob
    private String content;

    private BaseInfoDTO baseInfo;
  }
}
