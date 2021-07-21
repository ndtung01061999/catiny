package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * A ClassInfo.
 */
@Entity
@Table(name = "class_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "classinfo")
@GeneratedByJHipster
public class ClassInfo implements Serializable {

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
   * packageName *  : tên package
   */
  @Column(name = "name_package")
  private String namePackage;

  /**
   * fullName *     : tên đầy đủ của class . package+ClassName
   */
  @NotNull
  @Column(name = "full_name", nullable = false, unique = true)
  private String fullName;

  /**
   * classname *    : tên của class
   */
  @Column(name = "class_name")
  private String className;

  @OneToMany(mappedBy = "classInfo")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
  private Set<BaseInfo> baseInfos = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ClassInfo id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public ClassInfo uuid(UUID uuid)
  {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid)
  {
    this.uuid = uuid;
  }

  public String getNamePackage()
  {
    return this.namePackage;
  }

  public void setNamePackage(String namePackage)
  {
    this.namePackage = namePackage;
  }

  public ClassInfo namePackage(String namePackage)
  {
    this.namePackage = namePackage;
    return this;
  }

  public String getFullName()
  {
    return this.fullName;
  }

  public ClassInfo fullName(String fullName)
  {
    this.fullName = fullName;
    return this;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getClassName() {
    return this.className;
  }

  public ClassInfo className(String className) {
    this.className = className;
    return this;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public Set<BaseInfo> getBaseInfos() {
    return this.baseInfos;
  }

  public ClassInfo baseInfos(Set<BaseInfo> baseInfos) {
    this.setBaseInfos(baseInfos);
    return this;
  }

  public ClassInfo addBaseInfo(BaseInfo baseInfo) {
    this.baseInfos.add(baseInfo);
    baseInfo.setClassInfo(this);
    return this;
  }

  public ClassInfo removeBaseInfo(BaseInfo baseInfo) {
    this.baseInfos.remove(baseInfo);
    baseInfo.setClassInfo(null);
    return this;
  }

  public void setBaseInfos(Set<BaseInfo> baseInfos) {
    if (this.baseInfos != null) {
      this.baseInfos.forEach(i -> i.setClassInfo(null));
    }
    if (baseInfos != null) {
      baseInfos.forEach(i -> i.setClassInfo(this));
    }
    this.baseInfos = baseInfos;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ClassInfo)) {
      return false;
    }
    return id != null && id.equals(((ClassInfo) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "ClassInfo{" +
          "id=" + getId() +
          ", uuid='" + getUuid() + "'" +
          ", namePackage='" + getNamePackage() + "'" +
          ", fullName='" + getFullName() + "'" +
          ", className='" + getClassName() + "'" +
          "}";
    }
}
