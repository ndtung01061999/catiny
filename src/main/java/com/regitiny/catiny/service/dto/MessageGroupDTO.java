package com.regitiny.catiny.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.MessageGroup} entity.
 */
@ApiModel(description = "The PostDetails entity.\n@author A true hipster")
public class MessageGroupDTO implements Serializable {

    private Long id;

    /**
     * uuid
     */
    @NotNull
    @ApiModelProperty(value = "uuid", required = true)
    private UUID uuid;

    private Long userId;

    private String groupId;

    private String groupName;

    private String addBy;

    @Lob
    private String lastContent;

    /**
     * searchField
     */
    @ApiModelProperty(value = "searchField")
    @Lob
    private String searchField;

    /**
     * role
     */
    @Size(max = 8091)
    @ApiModelProperty(value = "role")
    private String role;

    /**
     * createdDate
     */
    @ApiModelProperty(value = "createdDate")
    private Instant createdDate;

    /**
     * modifiedDate
     */
    @ApiModelProperty(value = "modifiedDate")
    private Instant modifiedDate;

    /**
     * createdBy
     */
    @ApiModelProperty(value = "createdBy")
    private String createdBy;

    /**
     * modifiedBy
     */
    @ApiModelProperty(value = "modifiedBy")
    private String modifiedBy;

    /**
     * comment
     */
    @Size(max = 8091)
    @ApiModelProperty(value = "comment")
    private String comment;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAddBy() {
        return addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }

    public String getLastContent() {
        return lastContent;
    }

    public void setLastContent(String lastContent) {
        this.lastContent = lastContent;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MessageGroupDTO)) {
            return false;
        }

        MessageGroupDTO messageGroupDTO = (MessageGroupDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, messageGroupDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
  @Override
  public String toString()
  {
    return "MessageGroupDTO{" +
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
