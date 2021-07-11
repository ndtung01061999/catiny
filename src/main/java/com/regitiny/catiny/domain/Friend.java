package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.FriendType;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @what?            -> The Friend entity.\n@why?             ->\n@use-to           -> Quản lý phần kết bạn, các mối liên hệ bạn bè ...\n@commonly-used-in -> Bạn bè và các liên kết bạn bè ...\n\n@describe         ->
 */
@Entity
@Table(name = "friend")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "friend")
@GeneratedByJHipster
public class Friend implements Serializable {

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

  @Enumerated(EnumType.STRING)
  @Column(name = "friend_type")
  private FriendType friendType;

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
  private MasterUser friendDetails;

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

  public Friend id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public Friend uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public FriendType getFriendType() {
    return this.friendType;
  }

  public Friend friendType(FriendType friendType) {
    this.friendType = friendType;
    return this;
  }

  public void setFriendType(FriendType friendType) {
    this.friendType = friendType;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public Friend baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public MasterUser getFriendDetails() {
    return this.friendDetails;
  }

  public Friend friendDetails(MasterUser masterUser) {
    this.setFriendDetails(masterUser);
    return this;
  }

  public void setFriendDetails(MasterUser masterUser) {
    this.friendDetails = masterUser;
  }

  public MasterUser getMasterUser() {
    return this.masterUser;
  }

  public Friend masterUser(MasterUser masterUser) {
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
    if (!(o instanceof Friend)) {
      return false;
    }
    return id != null && id.equals(((Friend) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Friend{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", friendType='" + getFriendType() + "'" +
            "}";
    }
}
