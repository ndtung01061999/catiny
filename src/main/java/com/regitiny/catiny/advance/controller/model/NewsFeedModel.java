package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.dto.PostDTO;
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
public class NewsFeedModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  @ApiModelProperty("priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
  private Long priorityIndex;

  private BaseInfoDTO baseInfo;

  private PostDTO post;

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

    @ApiModelProperty("priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
    private Long priorityIndex;

    private BaseInfoDTO baseInfo;

    private PostDTO post;
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

    @ApiModelProperty("priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
    private Long priorityIndex;

    private BaseInfoDTO baseInfo;

    private PostDTO post;
  }
}
