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

  /**
   * priorityIndex *: chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)
   */
  @ApiModelProperty(value = "priorityIndex *: chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
  private Long priorityIndex;

  /**
   * countUse *     : đếm số lần truy cập vào bản ghi này để xem sửa xóa
   */
  @ApiModelProperty(value = "countUse *     : đếm số lần truy cập vào bản ghi này để xem sửa xóa")
  private Long countUse;

  private ClassInfoDTO classInfo;

  private MasterUserDTO createdBy;

  private MasterUserDTO modifiedBy;

  private MasterUserDTO owner;

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

  public Long getPriorityIndex() {
    return priorityIndex;
  }

  public void setPriorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
  }

  public Long getCountUse() {
    return countUse;
  }

  public void setCountUse(Long countUse) {
    this.countUse = countUse;
  }

  public ClassInfoDTO getClassInfo() {
    return classInfo;
  }

  public void setClassInfo(ClassInfoDTO classInfo) {
    this.classInfo = classInfo;
  }

  public MasterUserDTO getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(MasterUserDTO createdBy) {
    this.createdBy = createdBy;
  }

  public MasterUserDTO getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(MasterUserDTO modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public MasterUserDTO getOwner() {
    return owner;
  }

  public void setOwner(MasterUserDTO owner) {
    this.owner = owner;
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
            ", modifiedClass='" + getModifiedClass() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", notes='" + getNotes() + "'" +
            ", historyUpdate='" + getHistoryUpdate() + "'" +
            ", deleted='" + getDeleted() + "'" +
            ", priorityIndex=" + getPriorityIndex() +
            ", countUse=" + getCountUse() +
            ", classInfo=" + getClassInfo() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", owner=" + getOwner() +
            "}";
    }
}
