package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  @ApiModelProperty("nameFile       : tên của file nếu có")
  private String nameFile;

  @ApiModelProperty("typeFile       : loại file")
  private String typeFile;

  @Size(
    max = 1024
  )
  @ApiModelProperty("path           : đường dẫn file nguyên gốc")
  private String path;

  @ApiModelProperty("dataSize       : kích thước file theo byte")
  private Long dataSize;

  private BaseInfoDTO baseInfo;

  private MasterUserDTO masterUser;

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

    @ApiModelProperty("nameFile       : tên của file nếu có")
    private String nameFile;

    @ApiModelProperty("typeFile       : loại file")
    private String typeFile;

    @Size(
      max = 1024
    )
    @ApiModelProperty("path           : đường dẫn file nguyên gốc")
    private String path;

    @ApiModelProperty("dataSize       : kích thước file theo byte")
    private Long dataSize;

    private BaseInfoDTO baseInfo;

    private MasterUserDTO masterUser;
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

    @ApiModelProperty("nameFile       : tên của file nếu có")
    private String nameFile;

    @ApiModelProperty("typeFile       : loại file")
    private String typeFile;

    @Size(
      max = 1024
    )
    @ApiModelProperty("path           : đường dẫn file nguyên gốc")
    private String path;

    @ApiModelProperty("dataSize       : kích thước file theo byte")
    private Long dataSize;

    private BaseInfoDTO baseInfo;

    private MasterUserDTO masterUser;
  }
}
