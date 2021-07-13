package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @what?            -> The MessageGroup entity.\n@why?             ->\n@use-to           -> Chứa thông tin các nhóm mà hiện tại người dùng đang ở trong đó (phần nhắn tin)\n@commonly-used-in -> Hiển thị các tin nhắn\n\n@describe         -> một nhóm tạo ra sẽ là một uuid . nếu nhắn tin cặp thì sẽ sắp xếp login sau đó hash md5 rồi chuyển thành định dạng uuid
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
   * uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)
   */
  @NotNull
  @Type(type = "uuid-char")
  @Column(name = "uuid", length = 36, nullable = false, unique = true)
  private UUID uuid;

  /**
   * groupName
   */
  @Column(name = "group_name")
  private String groupName;

  /**
   * addBy
   */
  @Column(name = "add_by")
  private String addBy;

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

  @OneToMany(mappedBy = "messageGroup")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "sender", "messageGroup" }, allowSetters = true)
  private Set<MessageContent> messageContents = new HashSet<>();

  @ManyToMany(mappedBy = "messageGroups")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
  private Set<MasterUser> masterUsers = new HashSet<>();

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

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public MessageGroup baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Set<MessageContent> getMessageContents() {
    return this.messageContents;
  }

  public MessageGroup messageContents(Set<MessageContent> messageContents) {
    this.setMessageContents(messageContents);
    return this;
  }

  public MessageGroup addMessageContent(MessageContent messageContent) {
    this.messageContents.add(messageContent);
    messageContent.setMessageGroup(this);
    return this;
  }

  public MessageGroup removeMessageContent(MessageContent messageContent) {
    this.messageContents.remove(messageContent);
    messageContent.setMessageGroup(null);
    return this;
  }

  public void setMessageContents(Set<MessageContent> messageContents) {
    if (this.messageContents != null) {
      this.messageContents.forEach(i -> i.setMessageGroup(null));
    }
    if (messageContents != null) {
      messageContents.forEach(i -> i.setMessageGroup(this));
    }
    this.messageContents = messageContents;
  }

  public Set<MasterUser> getMasterUsers() {
    return this.masterUsers;
  }

  public MessageGroup masterUsers(Set<MasterUser> masterUsers) {
    this.setMasterUsers(masterUsers);
    return this;
  }

  public MessageGroup addMasterUser(MasterUser masterUser) {
    this.masterUsers.add(masterUser);
    masterUser.getMessageGroups().add(this);
    return this;
  }

  public MessageGroup removeMasterUser(MasterUser masterUser) {
    this.masterUsers.remove(masterUser);
    masterUser.getMessageGroups().remove(this);
    return this;
  }

  public void setMasterUsers(Set<MasterUser> masterUsers) {
    if (this.masterUsers != null) {
      this.masterUsers.forEach(i -> i.removeMessageGroup(this));
    }
    if (masterUsers != null) {
      masterUsers.forEach(i -> i.addMessageGroup(this));
    }
    this.masterUsers = masterUsers;
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
            ", groupName='" + getGroupName() + "'" +
            ", addBy='" + getAddBy() + "'" +
            "}";
    }
}
