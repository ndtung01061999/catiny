package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.domain.enumeration.ProcessStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseInfoModel implements Serializable
{
  private Long id;

  @ApiModelProperty("processStatus *: @defaultValue( DONE ) -> tình trạng xử lý sử dụng trong phê duyệt")
  private ProcessStatus processStatus;

  @ApiModelProperty("owner *        : @type Json -> chủ sở hữu bản ghi này --> nhớ sử dụng Set thay cho List")
  @Lob
  private String owner;

  @ApiModelProperty("role *         : @type Json -> những role được phép thực hiện <xem,sửa,xóa>")
  @Lob
  private String role;

  @ApiModelProperty("modifiedClass *: thực hiện sửa đổi bản ghi này ở service class nào")
  private String modifiedClass;

  @ApiModelProperty("createdDate *  : thời gian tạo ra bản ghi này (lần đầu tiên)")
  private Instant createdDate;

  @ApiModelProperty("modifiedDate * : thời gian sửa bản ghi này")
  private Instant modifiedDate;

  @ApiModelProperty("createdBy *    : người tạo ra bản gi này (lần đầu tiên)")
  private String createdBy;

  @ApiModelProperty("modifiedBy *   : người sửa lại bản ghi này")
  private String modifiedBy;

  @ApiModelProperty("notes *        : @type Json -> chú thích thêm hoặc những lưu ý cho bản ghi này ở dưới dạng Json\"")
  @Lob
  private String notes;

  @ApiModelProperty("historyUpdate *: @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json")
  @Lob
  private String historyUpdate;

  @ApiModelProperty("deleted *      : @defaultValue( false ) -> đánh dấu là đã xóa")
  private Boolean deleted;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request implements Serializable
  {
    private Long id;

    @ApiModelProperty("processStatus *: @defaultValue( DONE ) -> tình trạng xử lý sử dụng trong phê duyệt")
    private ProcessStatus processStatus;

    @ApiModelProperty("owner *        : @type Json -> chủ sở hữu bản ghi này --> nhớ sử dụng Set thay cho List")
    @Lob
    private String owner;

    @ApiModelProperty("role *         : @type Json -> những role được phép thực hiện <xem,sửa,xóa>")
    @Lob
    private String role;

    @ApiModelProperty("modifiedClass *: thực hiện sửa đổi bản ghi này ở service class nào")
    private String modifiedClass;

    @ApiModelProperty("createdDate *  : thời gian tạo ra bản ghi này (lần đầu tiên)")
    private Instant createdDate;

    @ApiModelProperty("modifiedDate * : thời gian sửa bản ghi này")
    private Instant modifiedDate;

    @ApiModelProperty("createdBy *    : người tạo ra bản gi này (lần đầu tiên)")
    private String createdBy;

    @ApiModelProperty("modifiedBy *   : người sửa lại bản ghi này")
    private String modifiedBy;

    @ApiModelProperty("notes *        : @type Json -> chú thích thêm hoặc những lưu ý cho bản ghi này ở dưới dạng Json\"")
    @Lob
    private String notes;

    @ApiModelProperty("historyUpdate *: @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json")
    @Lob
    private String historyUpdate;

    @ApiModelProperty("deleted *      : @defaultValue( false ) -> đánh dấu là đã xóa")
    private Boolean deleted;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Response implements Serializable
  {
    private Long id;

    @ApiModelProperty("processStatus *: @defaultValue( DONE ) -> tình trạng xử lý sử dụng trong phê duyệt")
    private ProcessStatus processStatus;

    @ApiModelProperty("owner *        : @type Json -> chủ sở hữu bản ghi này --> nhớ sử dụng Set thay cho List")
    @Lob
    private String owner;

    @ApiModelProperty("role *         : @type Json -> những role được phép thực hiện <xem,sửa,xóa>")
    @Lob
    private String role;

    @ApiModelProperty("modifiedClass *: thực hiện sửa đổi bản ghi này ở service class nào")
    private String modifiedClass;

    @ApiModelProperty("createdDate *  : thời gian tạo ra bản ghi này (lần đầu tiên)")
    private Instant createdDate;

    @ApiModelProperty("modifiedDate * : thời gian sửa bản ghi này")
    private Instant modifiedDate;

    @ApiModelProperty("createdBy *    : người tạo ra bản gi này (lần đầu tiên)")
    private String createdBy;

    @ApiModelProperty("modifiedBy *   : người sửa lại bản ghi này")
    private String modifiedBy;

    @ApiModelProperty("notes *        : @type Json -> chú thích thêm hoặc những lưu ý cho bản ghi này ở dưới dạng Json\"")
    @Lob
    private String notes;

    @ApiModelProperty("historyUpdate *: @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json")
    @Lob
    private String historyUpdate;

    @ApiModelProperty("deleted *      : @defaultValue( false ) -> đánh dấu là đã xóa")
    private Boolean deleted;
  }
}
