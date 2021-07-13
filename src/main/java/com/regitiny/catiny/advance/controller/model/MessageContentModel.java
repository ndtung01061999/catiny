package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
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
public class MessageContentModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  @Lob
  private String content;

  @ApiModelProperty("status : trạng thái của tin nhắn này, đã gửi chưa , ai đã nhận được , đã xem chưa đã thu hồi hay đã xóa...\n"
    + "@type           : Json")
  @Lob
  private String status;

  @ApiModelProperty("searchField : lưu content tin nhắn lọc dấu ... để sau này search")
  @Lob
  private String searchField;

  private BaseInfoDTO baseInfo;

  private MasterUserDTO sender;

  private MessageGroupDTO messageGroup;

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

    @Lob
    private String content;

    @ApiModelProperty("status : trạng thái của tin nhắn này, đã gửi chưa , ai đã nhận được , đã xem chưa đã thu hồi hay đã xóa...\n"
      + "@type           : Json")
    @Lob
    private String status;

    @ApiModelProperty("searchField : lưu content tin nhắn lọc dấu ... để sau này search")
    @Lob
    private String searchField;

    private BaseInfoDTO baseInfo;

    private MasterUserDTO sender;

    private MessageGroupDTO messageGroup;
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

    @Lob
    private String content;

    @ApiModelProperty("status : trạng thái của tin nhắn này, đã gửi chưa , ai đã nhận được , đã xem chưa đã thu hồi hay đã xóa...\n"
      + "@type           : Json")
    @Lob
    private String status;

    @ApiModelProperty("searchField : lưu content tin nhắn lọc dấu ... để sau này search")
    @Lob
    private String searchField;

    private BaseInfoDTO baseInfo;

    private MasterUserDTO sender;

    private MessageGroupDTO messageGroup;
  }
}
