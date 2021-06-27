package com.regitiny.catiny.domain;

import com.regitiny.catiny.GeneratedByJHipster;
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
@Table(name = "message_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "messagegroup")
@GeneratedByJHipster
public class MessageGroup implements Serializable {

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
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private String groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "add_by")
    private String addBy;

    @Lob
    @Column(name = "last_content")
    private String lastContent;

    /**
     * searchField
     */
    @Lob
    @Column(name = "search_field")
    private String searchField;

    /**
     * role
     */
    @Column(name = "role")
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
    @Column(name = "comment")
    private String comment;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MessageGroup id(Long id) {
        this.id = id;
        return this;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public MessageGroup uuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Long getUserId() {
        return this.userId;
    }

    public MessageGroup userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public MessageGroup groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public MessageGroup groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAddBy() {
        return this.addBy;
    }

    public MessageGroup addBy(String addBy) {
        this.addBy = addBy;
        return this;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }

    public String getLastContent() {
        return this.lastContent;
    }

    public MessageGroup lastContent(String lastContent) {
        this.lastContent = lastContent;
        return this;
    }

    public void setLastContent(String lastContent) {
        this.lastContent = lastContent;
    }

    public String getSearchField() {
        return this.searchField;
    }

    public MessageGroup searchField(String searchField) {
        this.searchField = searchField;
        return this;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getRole() {
        return this.role;
    }

    public MessageGroup role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public MessageGroup createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getModifiedDate() {
        return this.modifiedDate;
    }

    public MessageGroup modifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public MessageGroup createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public MessageGroup modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getComment() {
        return this.comment;
    }

    public MessageGroup comment(String comment) {
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
        if (!(o instanceof MessageGroup)) {
            return false;
        }
        return id != null && id.equals(((MessageGroup) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageGroup{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", userId=" + getUserId() +
            ", groupId='" + getGroupId() + "'" +
            ", groupName='" + getGroupName() + "'" +
            ", addBy='" + getAddBy() + "'" +
            ", lastContent='" + getLastContent() + "'" +
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
