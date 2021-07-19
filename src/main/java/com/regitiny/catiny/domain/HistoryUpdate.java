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
 * A HistoryUpdate.
 */
@Entity
@Table(name = "history_update")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "historyupdate")
@GeneratedByJHipster
public class HistoryUpdate implements Serializable
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
   * version        : phiên bản (bắt đầu từ 0)
   */
  @Column(name = "version")
  private Integer version;

  /**
   * content        : @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json
   */
  @Lob
  @Column(name = "content")
  private String content;

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

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public HistoryUpdate id(Long id)
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

  public HistoryUpdate uuid(UUID uuid)
  {
    this.uuid = uuid;
    return this;
  }

  public Integer getVersion()
  {
    return this.version;
  }

  public void setVersion(Integer version)
  {
    this.version = version;
  }

  public HistoryUpdate version(Integer version)
  {
    this.version = version;
    return this;
  }

  public String getContent()
  {
    return this.content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  public HistoryUpdate content(String content)
  {
    this.content = content;
    return this;
  }

  public BaseInfo getBaseInfo()
  {
    return this.baseInfo;
  }

  public void setBaseInfo(BaseInfo baseInfo)
  {
    this.baseInfo = baseInfo;
  }

  public HistoryUpdate baseInfo(BaseInfo baseInfo)
  {
    this.setBaseInfo(baseInfo);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (!(o instanceof HistoryUpdate))
    {
      return false;
    }
    return id != null && id.equals(((HistoryUpdate) o).id);
  }

  @Override
  public int hashCode()
  {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
  @Override
  public String toString()
  {
    return "HistoryUpdate{" +
      "id=" + getId() +
      ", uuid='" + getUuid() + "'" +
      ", version=" + getVersion() +
      ", content='" + getContent() + "'" +
      "}";
  }
}
