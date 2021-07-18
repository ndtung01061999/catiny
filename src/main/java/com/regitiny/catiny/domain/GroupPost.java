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
 * @what?            -> The GroupPost entity\n@why?             -> mọi người cần tạo ra một nhóm riêng hoặc chung để có thể trao đổi\n@use-to           -> quản lý nhóm\n@commonly-used-in -> các nhóm\n\n@describe         ->
 */
@Entity
@Table(name = "group_post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "grouppost")
@GeneratedByJHipster
public class GroupPost implements Serializable {

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
   * name           : tên của group này
   */
  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * avatar : @type Json -> ảnh đại diện của Group
   */
  @Lob
  @Column(name = "avatar")
  private String avatar;

  /**
   * quickInfo      : @type Json -> thông tin giới thiệu sơ qua của group này
   */
  @Lob
  @Column(name = "quick_info")
  private String quickInfo;

  @JsonIgnoreProperties(value = { "baseInfo", "group" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private GroupProfile profile;

  @JsonIgnoreProperties(
    value = {
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

  @OneToMany(mappedBy = "groupPost")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = {
      "baseInfo", "comments", "likes", "postShareChildren", "groupPost", "pagePost", "postShareParent", "newsFeeds", "topicInterests",
    },
    allowSetters = true
  )
  private Set<Post> myPostInGroups = new HashSet<>();

  @ManyToMany(mappedBy = "groupPosts")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "posts", "pagePosts", "groupPosts", "masterUsers" }, allowSetters = true)
  private Set<TopicInterest> topicInterests = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public GroupPost id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public GroupPost uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return this.name;
  }

  public GroupPost name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAvatar() {
    return this.avatar;
  }

  public GroupPost avatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getQuickInfo() {
    return this.quickInfo;
  }

  public GroupPost quickInfo(String quickInfo) {
    this.quickInfo = quickInfo;
    return this;
  }

  public void setQuickInfo(String quickInfo) {
    this.quickInfo = quickInfo;
  }

  public GroupProfile getProfile() {
    return this.profile;
  }

  public GroupPost profile(GroupProfile groupProfile) {
    this.setProfile(groupProfile);
    return this;
  }

  public void setProfile(GroupProfile groupProfile) {
    this.profile = groupProfile;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public GroupPost baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Set<Post> getMyPostInGroups() {
    return this.myPostInGroups;
  }

  public GroupPost myPostInGroups(Set<Post> posts) {
    this.setMyPostInGroups(posts);
    return this;
  }

  public GroupPost addMyPostInGroup(Post post) {
    this.myPostInGroups.add(post);
    post.setGroupPost(this);
    return this;
  }

  public GroupPost removeMyPostInGroup(Post post) {
    this.myPostInGroups.remove(post);
    post.setGroupPost(null);
    return this;
  }

  public void setMyPostInGroups(Set<Post> posts) {
    if (this.myPostInGroups != null) {
      this.myPostInGroups.forEach(i -> i.setGroupPost(null));
    }
    if (posts != null) {
      posts.forEach(i -> i.setGroupPost(this));
    }
    this.myPostInGroups = posts;
  }

  public Set<TopicInterest> getTopicInterests() {
    return this.topicInterests;
  }

  public GroupPost topicInterests(Set<TopicInterest> topicInterests) {
    this.setTopicInterests(topicInterests);
    return this;
  }

  public GroupPost addTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.add(topicInterest);
    topicInterest.getGroupPosts().add(this);
    return this;
  }

  public GroupPost removeTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.remove(topicInterest);
    topicInterest.getGroupPosts().remove(this);
    return this;
  }

  public void setTopicInterests(Set<TopicInterest> topicInterests) {
    if (this.topicInterests != null) {
      this.topicInterests.forEach(i -> i.removeGroupPost(this));
    }
    if (topicInterests != null) {
      topicInterests.forEach(i -> i.addGroupPost(this));
    }
    this.topicInterests = topicInterests;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GroupPost)) {
      return false;
    }
    return id != null && id.equals(((GroupPost) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "GroupPost{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", quickInfo='" + getQuickInfo() + "'" +
            "}";
    }
}
