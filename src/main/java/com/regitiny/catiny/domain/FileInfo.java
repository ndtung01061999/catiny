package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

/**
 * @what?            -> The FileInfo entity.\n@why?             ->\n@use-to           -> Quản lý thông tin về file, vị trí file ...\n@commonly-used-in -> Những file mà người dùng upload (ảnh video ...)\n\n@describe         ->
 */
@Entity
@Table(name = "file_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "fileinfo")
@GeneratedByJHipster
public class FileInfo implements Serializable {

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
   * nameFile       : tên của file nếu có
   */
  @Column(name = "name_file")
  private String nameFile;

  /**
   * typeFile       : loại file
   */
  @Column(name = "type_file")
  private String typeFile;

  /**
   * path           : đường dẫn file trên server
   */
  @Size(max = 1024)
  @Column(name = "path", length = 1024)
  private String path;

  /**
   * dataSize       : kích thước file theo byte
   */
  @Column(name = "data_size")
  private Long dataSize;

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
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo baseInfo;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public FileInfo id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public FileInfo uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getNameFile() {
    return this.nameFile;
  }

  public FileInfo nameFile(String nameFile) {
    this.nameFile = nameFile;
    return this;
  }

  public void setNameFile(String nameFile) {
    this.nameFile = nameFile;
  }

  public String getTypeFile() {
    return this.typeFile;
  }

  public FileInfo typeFile(String typeFile) {
    this.typeFile = typeFile;
    return this;
  }

  public void setTypeFile(String typeFile) {
    this.typeFile = typeFile;
  }

  public String getPath() {
    return this.path;
  }

  public FileInfo path(String path) {
    this.path = path;
    return this;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Long getDataSize() {
    return this.dataSize;
  }

  public FileInfo dataSize(Long dataSize) {
    this.dataSize = dataSize;
    return this;
  }

  public void setDataSize(Long dataSize) {
    this.dataSize = dataSize;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public FileInfo baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FileInfo)) {
      return false;
    }
    return id != null && id.equals(((FileInfo) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "FileInfo{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", nameFile='" + getNameFile() + "'" +
            ", typeFile='" + getTypeFile() + "'" +
            ", path='" + getPath() + "'" +
            ", dataSize=" + getDataSize() +
            "}";
    }
}
