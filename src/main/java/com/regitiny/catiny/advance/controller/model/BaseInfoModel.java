package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.domain.enumeration.ProcessStatus;
import com.regitiny.catiny.service.dto.ClassInfoDTO;
import com.regitiny.catiny.service.dto.MasterUserDTO;
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

  @ApiModelProperty("modifiedClass *: thực hiện sửa đổi bản ghi này ở service class nào")
  private String modifiedClass;

  @ApiModelProperty("createdDate *  : thời gian tạo ra bản ghi này (lần đầu tiên)")
  private Instant createdDate;

  @ApiModelProperty("modifiedDate * : thời gian sửa bản ghi này")
  private Instant modifiedDate;

  @ApiModelProperty("notes *        : @type Json -> chú thích thêm hoặc những lưu ý cho bản ghi này ở dưới dạng Json\"")
  @Lob
  private String notes;

  @ApiModelProperty("historyUpdate *: @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json")
  @Lob
  private String historyUpdate;

  @ApiModelProperty("deleted *      : @defaultValue( false ) -> đánh dấu là đã xóa")
  private Boolean deleted;

  @ApiModelProperty("priorityIndex *: chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
  private Long priorityIndex;

  @ApiModelProperty("countUse *     : đếm số lần truy cập vào bản ghi này để xem sửa xóa")
  private Long countUse;

  private ClassInfoDTO classInfo;

  private MasterUserDTO createdBy;

  private MasterUserDTO modifiedBy;

  private MasterUserDTO owner;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request implements Serializable
  {
    private Long id;

    @ApiModelProperty("processStatus *: @defaultValue( DONE ) -> tình trạng xử lý sử dụng trong phê duyệt")
    private ProcessStatus processStatus;

    @ApiModelProperty("modifiedClass *: thực hiện sửa đổi bản ghi này ở service class nào")
    private String modifiedClass;

    @ApiModelProperty("createdDate *  : thời gian tạo ra bản ghi này (lần đầu tiên)")
    private Instant createdDate;

    @ApiModelProperty("modifiedDate * : thời gian sửa bản ghi này")
    private Instant modifiedDate;

    @ApiModelProperty("notes *        : @type Json -> chú thích thêm hoặc những lưu ý cho bản ghi này ở dưới dạng Json\"")
    @Lob
    private String notes;

    @ApiModelProperty("historyUpdate *: @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json")
    @Lob
    private String historyUpdate;

    @ApiModelProperty("deleted *      : @defaultValue( false ) -> đánh dấu là đã xóa")
    private Boolean deleted;

    @ApiModelProperty("priorityIndex *: chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
    private Long priorityIndex;

    @ApiModelProperty("countUse *     : đếm số lần truy cập vào bản ghi này để xem sửa xóa")
    private Long countUse;

    private ClassInfoDTO classInfo;

    private MasterUserDTO createdBy;

    private MasterUserDTO modifiedBy;

    private MasterUserDTO owner;
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

    @ApiModelProperty("modifiedClass *: thực hiện sửa đổi bản ghi này ở service class nào")
    private String modifiedClass;

    @ApiModelProperty("createdDate *  : thời gian tạo ra bản ghi này (lần đầu tiên)")
    private Instant createdDate;

    @ApiModelProperty("modifiedDate * : thời gian sửa bản ghi này")
    private Instant modifiedDate;

    @ApiModelProperty("notes *        : @type Json -> chú thích thêm hoặc những lưu ý cho bản ghi này ở dưới dạng Json\"")
    @Lob
    private String notes;

    @ApiModelProperty("historyUpdate *: @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json")
    @Lob
    private String historyUpdate;

    @ApiModelProperty("deleted *      : @defaultValue( false ) -> đánh dấu là đã xóa")
    private Boolean deleted;

    @ApiModelProperty("priorityIndex *: chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
    private Long priorityIndex;

    @ApiModelProperty("countUse *     : đếm số lần truy cập vào bản ghi này để xem sửa xóa")
    private Long countUse;

    private ClassInfoDTO classInfo;

    private MasterUserDTO createdBy;

    private MasterUserDTO modifiedBy;

    private MasterUserDTO owner;
  }
}
