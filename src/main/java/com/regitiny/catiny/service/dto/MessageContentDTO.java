package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.MessageContent} entity.
 */
@ApiModel(
  description = "@what?            -> The MessageContent entity.\n@why?             ->\n@use-to           -> Chứa Những tin nhắn trong các nhóm cụ thể\n@commonly-used-in ->\n\n@describe         ->"
)
@GeneratedByJHipster
public class MessageContentDTO implements Serializable {

  private Long id;

  /**
   * uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)
   */
  @NotNull
  @ApiModelProperty(
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)",
    required = true
  )
  private UUID uuid;

  /**
   * senderName     : tên người gửi
   */
  @ApiModelProperty(value = "senderName     : tên người gửi")
  private String senderName;

  /**
   * attachInfo     : đính kèm tệp ảnh video ...
   */
  @ApiModelProperty(value = "attachInfo     : đính kèm tệp ảnh video ...")
  @Lob
  private String attach;

  /**
   * content        : nội dung tin nhắn
   */
  @ApiModelProperty(value = "content        : nội dung tin nhắn")
  @Lob
  private String content;

  /**
   * status         : trạng thái của tin nhắn này, đã gửi chưa , ai đã nhận được , đã xem chưa đã thu hồi hay đã xóa...
   */
  @ApiModelProperty(
    value = "status         : trạng thái của tin nhắn này, đã gửi chưa , ai đã nhận được , đã xem chưa đã thu hồi hay đã xóa..."
  )
  @Lob
  private String status;

  /**
   * searchField    : lưu content tin nhắn lọc dấu ... để sau này search
   */
  @ApiModelProperty(value = "searchField    : lưu content tin nhắn lọc dấu ... để sau này search")
  @Lob
  private String searchField;

  private BaseInfoDTO baseInfo;

  private MessageGroupDTO messageGroup;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getSenderName() {
    return senderName;
  }

  public void setSenderName(String senderName) {
    this.senderName = senderName;
  }

  public String getAttach() {
    return attach;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSearchField() {
    return searchField;
  }

  public void setSearchField(String searchField) {
    this.searchField = searchField;
  }

  public BaseInfoDTO getBaseInfo() {
    return baseInfo;
  }

  public void setBaseInfo(BaseInfoDTO baseInfo) {
    this.baseInfo = baseInfo;
  }

  public MessageGroupDTO getMessageGroup() {
    return messageGroup;
  }

  public void setMessageGroup(MessageGroupDTO messageGroup) {
    this.messageGroup = messageGroup;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MessageContentDTO)) {
      return false;
    }

    MessageContentDTO messageContentDTO = (MessageContentDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, messageContentDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MessageContentDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", senderName='" + getSenderName() + "'" +
            ", attach='" + getAttach() + "'" +
            ", content='" + getContent() + "'" +
            ", status='" + getStatus() + "'" +
            ", searchField='" + getSearchField() + "'" +
            ", baseInfo=" + getBaseInfo() +
            ", messageGroup=" + getMessageGroup() +
            "}";
    }
}
