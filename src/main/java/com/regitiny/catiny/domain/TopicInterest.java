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
 * @what?            -> The TopicInterest entity.\n@why?             ->\n@use-to           -> Lưu những chủ đề mà người dùng quan tâm\n@commonly-used-in -> chủ đề quan tâm để lọc ra cho người dùng xem\n\n@describe         ->
 */
@Entity
@Table(name = "topic_interest")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "topicinterest")
@GeneratedByJHipster
public class TopicInterest implements Serializable {

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

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JoinTable(
    name = "rel_topic_interest__post",
    joinColumns = @JoinColumn(name = "topic_interest_id"),
    inverseJoinColumns = @JoinColumn(name = "post_id")
  )
  @JsonIgnoreProperties(
    value = {
      "baseInfo",
      "postComments",
      "postLikes",
      "postShareChildren",
      "groupPost",
      "pagePost",
      "postShareParent",
      "poster",
      "newsFeeds",
      "topicInterests",
    },
    allowSetters = true
  )
  private Set<Post> posts = new HashSet<>();

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JoinTable(
    name = "rel_topic_interest__page_post",
    joinColumns = @JoinColumn(name = "topic_interest_id"),
    inverseJoinColumns = @JoinColumn(name = "page_post_id")
  )
  @JsonIgnoreProperties(value = { "profile", "baseInfo", "myPostInPages", "masterUser", "topicInterests" }, allowSetters = true)
  private Set<PagePost> pagePosts = new HashSet<>();

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JoinTable(
    name = "rel_topic_interest__group_post",
    joinColumns = @JoinColumn(name = "topic_interest_id"),
    inverseJoinColumns = @JoinColumn(name = "group_post_id")
  )
  @JsonIgnoreProperties(value = { "profile", "baseInfo", "myPostInGroups", "topicInterests", "userInGroups" }, allowSetters = true)
  private Set<GroupPost> groupPosts = new HashSet<>();

  @ManyToMany(mappedBy = "topicInterests")
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

  public TopicInterest id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public TopicInterest uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getTitle() {
    return this.title;
  }

  public TopicInterest title(String title) {
    this.title = title;
    return this;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return this.content;
  }

  public TopicInterest content(String content) {
    this.content = content;
    return this;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public TopicInterest baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Set<Post> getPosts() {
    return this.posts;
  }

  public TopicInterest posts(Set<Post> posts) {
    this.setPosts(posts);
    return this;
  }

  public TopicInterest addPost(Post post) {
    this.posts.add(post);
    post.getTopicInterests().add(this);
    return this;
  }

  public TopicInterest removePost(Post post) {
    this.posts.remove(post);
    post.getTopicInterests().remove(this);
    return this;
  }

  public void setPosts(Set<Post> posts) {
    this.posts = posts;
  }

  public Set<PagePost> getPagePosts() {
    return this.pagePosts;
  }

  public TopicInterest pagePosts(Set<PagePost> pagePosts) {
    this.setPagePosts(pagePosts);
    return this;
  }

  public TopicInterest addPagePost(PagePost pagePost) {
    this.pagePosts.add(pagePost);
    pagePost.getTopicInterests().add(this);
    return this;
  }

  public TopicInterest removePagePost(PagePost pagePost) {
    this.pagePosts.remove(pagePost);
    pagePost.getTopicInterests().remove(this);
    return this;
  }

  public void setPagePosts(Set<PagePost> pagePosts) {
    this.pagePosts = pagePosts;
  }

  public Set<GroupPost> getGroupPosts() {
    return this.groupPosts;
  }

  public TopicInterest groupPosts(Set<GroupPost> groupPosts) {
    this.setGroupPosts(groupPosts);
    return this;
  }

  public TopicInterest addGroupPost(GroupPost groupPost) {
    this.groupPosts.add(groupPost);
    groupPost.getTopicInterests().add(this);
    return this;
  }

  public TopicInterest removeGroupPost(GroupPost groupPost) {
    this.groupPosts.remove(groupPost);
    groupPost.getTopicInterests().remove(this);
    return this;
  }

  public void setGroupPosts(Set<GroupPost> groupPosts) {
    this.groupPosts = groupPosts;
  }

  public Set<MasterUser> getMasterUsers() {
    return this.masterUsers;
  }

  public TopicInterest masterUsers(Set<MasterUser> masterUsers) {
    this.setMasterUsers(masterUsers);
    return this;
  }

  public TopicInterest addMasterUser(MasterUser masterUser) {
    this.masterUsers.add(masterUser);
    masterUser.getTopicInterests().add(this);
    return this;
  }

  public TopicInterest removeMasterUser(MasterUser masterUser) {
    this.masterUsers.remove(masterUser);
    masterUser.getTopicInterests().remove(this);
    return this;
  }

  public void setMasterUsers(Set<MasterUser> masterUsers) {
    if (this.masterUsers != null) {
      this.masterUsers.forEach(i -> i.removeTopicInterest(this));
    }
    if (masterUsers != null) {
      masterUsers.forEach(i -> i.addTopicInterest(this));
    }
    this.masterUsers = masterUsers;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TopicInterest)) {
      return false;
    }
    return id != null && id.equals(((TopicInterest) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "TopicInterest{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}
