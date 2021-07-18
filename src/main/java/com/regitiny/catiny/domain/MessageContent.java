package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @what?            -> The MessageContent entity.\n@why?             ->\n@use-to           -> Chứa Những tin nhắn trong các nhóm cụ thể\n@commonly-used-in ->\n\n@describe         ->
 */
@Entity
@Table(name = "message_content")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "messagecontent")
@GeneratedByJHipster
public class MessageContent implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  /**
   * uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)
   */
  @NotNull
  @Type(type = "uuid-char")
  @Column(name = "uuid", length = 36, nullable = false, unique = true)
  private UUID uuid;

  /**
   * senderName     : tên người gửi
   */
  @Column(name = "sender_name")
  private String senderName;

  /**
   * attachInfo     : đính kèm tệp ảnh video ...
   */
  @Lob
  @Column(name = "attach")
  private String attach;

  /**
   * content        : nội dung tin nhắn
   */
  @Lob
  @Column(name = "content")
  private String content;

  /**
   * status         : trạng thái của tin nhắn này, đã gửi chưa , ai đã nhận được , đã xem chưa đã thu hồi hay đã xóa...
   */
  @Lob
  @Column(name = "status")
  private String status;

  /**
   * searchField    : lưu content tin nhắn lọc dấu ... để sau này search
   */
  @Lob
  @Column(name = "search_field")
  private String searchField;

  @JsonIgnoreProperties(
    value = {
      "classInfo",
      "userProfile",
      "accountStatus",
      "deviceStatus",
      "friend",
      "followUser",
      "followGroup",
      "followPage",
      "fileInfo",
      "pagePost",
      "pageProfile",
      "groupPost",
      "post",
      "postComment",
      "postLike",
      "groupProfile",
      "newsFeed",
      "messageGroup",
      "messageContent",
      "rankUser",
      "rankGroup",
      "notification",
      "album",
      "video",
      "image",
      "videoStream",
      "videoLiveStreamBuffer",
      "topicInterest",
      "todoList",
      "event",
      "createdBy",
      "modifiedBy",
      "owner",
      "permissions",
    },
    allowSetters = true
  )
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo baseInfo;

  @ManyToOne
  @JsonIgnoreProperties(value = { "baseInfo", "messageContents" }, allowSetters = true)
  private MessageGroup messageGroup;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MessageContent id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public MessageContent uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getSenderName() {
    return this.senderName;
  }

  public MessageContent senderName(String senderName) {
    this.senderName = senderName;
    return this;
  }

  public void setSenderName(String senderName) {
    this.senderName = senderName;
  }

  public String getAttach() {
    return this.attach;
  }

  public MessageContent attach(String attach) {
    this.attach = attach;
    return this;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }

  public String getContent() {
    return this.content;
  }

  public MessageContent content(String content) {
    this.content = content;
    return this;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getStatus() {
    return this.status;
  }

  public MessageContent status(String status) {
    this.status = status;
    return this;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSearchField() {
    return this.searchField;
  }

  public MessageContent searchField(String searchField) {
    this.searchField = searchField;
    return this;
  }

  public void setSearchField(String searchField) {
    this.searchField = searchField;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public MessageContent baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public MessageGroup getMessageGroup() {
    return this.messageGroup;
  }

  public MessageContent messageGroup(MessageGroup messageGroup) {
    this.setMessageGroup(messageGroup);
    return this;
  }

  public void setMessageGroup(MessageGroup messageGroup) {
    this.messageGroup = messageGroup;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MessageContent)) {
      return false;
    }
    return id != null && id.equals(((MessageContent) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MessageContent{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", senderName='" + getSenderName() + "'" +
            ", attach='" + getAttach() + "'" +
            ", content='" + getContent() + "'" +
            ", status='" + getStatus() + "'" +
            ", searchField='" + getSearchField() + "'" +
            "}";
    }
}
