package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.NotifyType;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @what?            -> The Notification entity.\n@why?             ->\n@use-to           -> Chứa những thông báo đến người dùng\n@commonly-used-in -> Thường xuất hiện trong chức năng thông báo của người dùng\n\n@describe         ->
 */
@Entity
@Table(name = "notification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "notification")
@GeneratedByJHipster
public class Notification implements Serializable {

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
   * notifyType     : loại thông báo
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "notify_type")
  private NotifyType notifyType;

  @Column(name = "title")
  private String title;

  @Lob
  @Column(name = "content")
  private String content;

  @JsonIgnoreProperties(
    value = {
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
    },
    allowSetters = true
  )
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo baseInfo;

  @ManyToOne
  @JsonIgnoreProperties(
    value = {
      "user",
      "myProfile",
      "myAccountStatus",
      "myRank",
      "avatar",
      "baseInfo",
      "myPages",
      "myFiles",
      "myNotifications",
      "myFriends",
      "myFollowUsers",
      "myFollowGroups",
      "myFollowPages",
      "myNewsFeeds",
      "myTodoLists",
      "myPosts",
      "myGroupPosts",
      "messageGroups",
      "topicInterests",
      "myLikes",
      "myComments",
    },
    allowSetters = true
  )
  private MasterUser masterUser;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Notification id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public Notification uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public NotifyType getNotifyType() {
    return this.notifyType;
  }

  public Notification notifyType(NotifyType notifyType) {
    this.notifyType = notifyType;
    return this;
  }

  public void setNotifyType(NotifyType notifyType) {
    this.notifyType = notifyType;
  }

  public String getTitle() {
    return this.title;
  }

  public Notification title(String title) {
    this.title = title;
    return this;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return this.content;
  }

  public Notification content(String content) {
    this.content = content;
    return this;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public Notification baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public MasterUser getMasterUser() {
    return this.masterUser;
  }

  public Notification masterUser(MasterUser masterUser) {
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
    if (!(o instanceof Notification)) {
      return false;
    }
    return id != null && id.equals(((Notification) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Notification{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", notifyType='" + getNotifyType() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}
