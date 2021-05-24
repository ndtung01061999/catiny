package com.regitiny.catiny.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * The PostDetails entity.\n@author A true hipster
 */
@Entity
@Table(name = "message_content")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "messagecontent")
public class MessageContent implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  /**
   * uuid
   */
  @NotNull
  @Type(type = "uuid-char")
  @Column(name = "uuid", length = 36, nullable = false, unique = true)
  private UUID uuid;

  @NotNull
  @Column(name = "group_id", nullable = false, unique = true)
  private String groupId;

  @Lob
  @Column(name = "content")
  private String content;

  @Column(name = "sender")
  private String sender;

  @Column(name = "status")
  private String status;

  /**
   * searchField
   */
  @Lob
  @Column(name = "search_field")
  private String searchField;

  /**
   * role
   */
  @Size(max = 8091)
  @Column(name = "role", length = 8091)
  private String role;

  /**
   * createdDate
   */
  @Column(name = "created_date")
  private Instant createdDate;

  /**
   * modifiedDate
   */
  @Column(name = "modified_date")
  private Instant modifiedDate;

  /**
   * createdBy
   */
  @Column(name = "created_by")
  private String createdBy;

  /**
   * modifiedBy
   */
  @Column(name = "modified_by")
  private String modifiedBy;

  /**
   * comment
   */
  @Size(max = 8091)
  @Column(name = "comment", length = 8091)
  private String comment;

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

  public String getGroupId() {
    return this.groupId;
  }

  public MessageContent groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
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

  public String getSender() {
    return this.sender;
  }

  public MessageContent sender(String sender) {
    this.sender = sender;
    return this;
  }

  public void setSender(String sender) {
    this.sender = sender;
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

  public String getRole() {
    return this.role;
  }

  public MessageContent role(String role) {
    this.role = role;
    return this;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Instant getCreatedDate() {
    return this.createdDate;
  }

  public MessageContent createdDate(Instant createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

  public Instant getModifiedDate() {
    return this.modifiedDate;
  }

  public MessageContent modifiedDate(Instant modifiedDate) {
    this.modifiedDate = modifiedDate;
    return this;
  }

  public void setModifiedDate(Instant modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public MessageContent createdBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getModifiedBy() {
    return this.modifiedBy;
  }

  public MessageContent modifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
    return this;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public String getComment() {
    return this.comment;
  }

  public MessageContent comment(String comment) {
    this.comment = comment;
    return this;
  }

  public void setComment(String comment) {
    this.comment = comment;
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
            ", groupId='" + getGroupId() + "'" +
            ", content='" + getContent() + "'" +
            ", sender='" + getSender() + "'" +
            ", status='" + getStatus() + "'" +
            ", searchField='" + getSearchField() + "'" +
            ", role='" + getRole() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", comment='" + getComment() + "'" +
            "}";
    }
}
