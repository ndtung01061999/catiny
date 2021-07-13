package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.ProcessStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.BaseInfo} entity.
 */
@ApiModel(description = "BaseInfo")
@GeneratedByJHipster
public class BaseInfoDTO implements Serializable {

  private Long id;

  /**
   * processStatus *: @defaultValue( DONE ) -> tình trạng xử lý sử dụng trong phê duyệt
   */
  @ApiModelProperty(value = "processStatus *: @defaultValue( DONE ) -> tình trạng xử lý sử dụng trong phê duyệt")
  private ProcessStatus processStatus;

  /**
   * owner *        : @type Json -> chủ sở hữu bản ghi này --> nhớ sử dụng Set thay cho List
   */
  @ApiModelProperty(value = "owner *        : @type Json -> chủ sở hữu bản ghi này --> nhớ sử dụng Set thay cho List")
  @Lob
  private String owner;

  /**
   * role *         : @type Json -> những role được phép thực hiện <xem,sửa,xóa>
   */
  @ApiModelProperty(value = "role *         : @type Json -> những role được phép thực hiện <xem,sửa,xóa>")
  @Lob
  private String role;

  /**
   * modifiedClass *: thực hiện sửa đổi bản ghi này ở service class nào
   */
  @ApiModelProperty(value = "modifiedClass *: thực hiện sửa đổi bản ghi này ở service class nào")
  private String modifiedClass;

  /**
   * createdDate *  : thời gian tạo ra bản ghi này (lần đầu tiên)
   */
  @ApiModelProperty(value = "createdDate *  : thời gian tạo ra bản ghi này (lần đầu tiên)")
  private Instant createdDate;

  /**
   * modifiedDate * : thời gian sửa bản ghi này
   */
  @ApiModelProperty(value = "modifiedDate * : thời gian sửa bản ghi này")
  private Instant modifiedDate;

  /**
   * createdBy *    : người tạo ra bản gi này (lần đầu tiên)
   */
  @ApiModelProperty(value = "createdBy *    : người tạo ra bản gi này (lần đầu tiên)")
  private String createdBy;

  /**
   * modifiedBy *   : người sửa lại bản ghi này
   */
  @ApiModelProperty(value = "modifiedBy *   : người sửa lại bản ghi này")
  private String modifiedBy;

  /**
   * notes *        : @type Json -> chú thích thêm hoặc những lưu ý cho bản ghi này ở dưới dạng Json\"
   */
  @ApiModelProperty(value = "notes *        : @type Json -> chú thích thêm hoặc những lưu ý cho bản ghi này ở dưới dạng Json\"")
  @Lob
  private String notes;

  /**
   * historyUpdate *: @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json
   */
  @ApiModelProperty(
    value = "historyUpdate *: @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json"
  )
  @Lob
  private String historyUpdate;

  /**
   * deleted *      : @defaultValue( false ) -> đánh dấu là đã xóa
   */
  @ApiModelProperty(value = "deleted *      : @defaultValue( false ) -> đánh dấu là đã xóa")
  private Boolean deleted;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProcessStatus getProcessStatus() {
    return processStatus;
  }

  public void setProcessStatus(ProcessStatus processStatus) {
    this.processStatus = processStatus;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getModifiedClass() {
    return modifiedClass;
  }

  public void setModifiedClass(String modifiedClass) {
    this.modifiedClass = modifiedClass;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

  public Instant getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(Instant modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getHistoryUpdate() {
    return historyUpdate;
  }

  public void setHistoryUpdate(String historyUpdate) {
    this.historyUpdate = historyUpdate;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseInfoDTO)) {
      return false;
    }

    BaseInfoDTO baseInfoDTO = (BaseInfoDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, baseInfoDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "BaseInfoDTO{" +
            "id=" + getId() +
            ", processStatus='" + getProcessStatus() + "'" +
            ", owner='" + getOwner() + "'" +
            ", role='" + getRole() + "'" +
            ", modifiedClass='" + getModifiedClass() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", notes='" + getNotes() + "'" +
            ", historyUpdate='" + getHistoryUpdate() + "'" +
            ", deleted='" + getDeleted() + "'" +
            "}";
    }
}
