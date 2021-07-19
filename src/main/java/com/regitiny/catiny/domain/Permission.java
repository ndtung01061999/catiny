package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

/**
 * A Permission.
 */
@Entity
@Table(name = "permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "permission")
@GeneratedByJHipster
public class Permission implements Serializable
{

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
   * quyền đọc
   */
  @Column(name = "jhi_read")
  private Boolean read;

  /**
   * quyền ghi và sửa
   */
  @Column(name = "jhi_write")
  private Boolean write;

  /**
   * quyền chia sẻ
   */
  @Column(name = "share")
  private Boolean share;

  /**
   * quyền xóa
   */
  @Column(name = "jhi_delete")
  private Boolean delete;

  /**
   * quyền trao quyền cho user khác
   */
  @Column(name = "jhi_add")
  private Boolean add;

  /**
   * cấp độ 0->* số nhỏ hơn sẽ có quyền lớn hơn
   */
  @Column(name = "level")
  private Integer level;

  @ManyToOne
  @JsonIgnoreProperties(
    value = {
      "historyUpdates",
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
  private BaseInfo baseInfo;

  @ManyToOne
  @JsonIgnoreProperties(
    value = { "user", "myRank", "baseInfo", "myBaseInfoCreateds", "myBaseInfoModifieds", "ownerOfs", "permissions", "topicInterests" },
    allowSetters = true
  )
  private MasterUser masterUser;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Permission id(Long id)
  {
    this.id = id;
    return this;
  }

  public UUID getUuid()
  {
    return this.uuid;
  }

  public void setUuid(UUID uuid)
  {
    this.uuid = uuid;
  }

  public Permission uuid(UUID uuid)
  {
    this.uuid = uuid;
    return this;
  }

  public Boolean getRead()
  {
    return this.read;
  }

  public Permission read(Boolean read)
  {
    this.read = read;
    return this;
  }

  public void setRead(Boolean read) {
    this.read = read;
  }

  public Boolean getWrite() {
    return this.write;
  }

  public Permission write(Boolean write) {
    this.write = write;
    return this;
  }

  public void setWrite(Boolean write) {
    this.write = write;
  }

  public Boolean getShare() {
    return this.share;
  }

  public Permission share(Boolean share) {
    this.share = share;
    return this;
  }

  public void setShare(Boolean share) {
    this.share = share;
  }

  public Boolean getDelete() {
    return this.delete;
  }

  public Permission delete(Boolean delete) {
    this.delete = delete;
    return this;
  }

  public void setDelete(Boolean delete) {
    this.delete = delete;
  }

  public Boolean getAdd() {
    return this.add;
  }

  public Permission add(Boolean add) {
    this.add = add;
    return this;
  }

  public void setAdd(Boolean add) {
    this.add = add;
  }

  public Integer getLevel() {
    return this.level;
  }

  public Permission level(Integer level) {
    this.level = level;
    return this;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public Permission baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public MasterUser getMasterUser() {
    return this.masterUser;
  }

  public Permission masterUser(MasterUser masterUser) {
    this.setMasterUser(masterUser);
    return this;
  }

  public void setMasterUser(MasterUser masterUser) {
    this.masterUser = masterUser;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Permission)) {
      return false;
    }
    return id != null && id.equals(((Permission) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Permission{" +
          "id=" + getId() +
          ", uuid='" + getUuid() + "'" +
          ", read='" + getRead() + "'" +
          ", write='" + getWrite() + "'" +
          ", share='" + getShare() + "'" +
          ", delete='" + getDelete() + "'" +
          ", add='" + getAdd() + "'" +
          ", level=" + getLevel() +
          "}";
    }
}
