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
 * @what?            -> The PagePost entity.\n@why?             ->\n@use-to           -> Lưu các Trang người dùng tạo ra\n@commonly-used-in -> Cũng tương tự như bài đăng của một người dùng những sẽ chuyên biệt về  một chủ đề\n\n@describe         ->
 */
@Entity
@Table(name = "page_post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "pagepost")
@GeneratedByJHipster
public class PagePost implements Serializable {

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
   * name           : tên của page này
   */
  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * quickInfo      : @type Json ->thông tin nổi bật giới thiệu sơ qua về page
   */
  @Lob
  @Column(name = "quick_info")
  private String quickInfo;

  @JsonIgnoreProperties(value = { "baseInfo", "page" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private PageProfile profile;

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

  @OneToMany(mappedBy = "pagePost")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
  private Set<Post> myPostInPages = new HashSet<>();

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

  @ManyToMany(mappedBy = "pagePosts")
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

  public PagePost id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public PagePost uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return this.name;
  }

  public PagePost name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getQuickInfo() {
    return this.quickInfo;
  }

  public PagePost quickInfo(String quickInfo) {
    this.quickInfo = quickInfo;
    return this;
  }

  public void setQuickInfo(String quickInfo) {
    this.quickInfo = quickInfo;
  }

  public PageProfile getProfile() {
    return this.profile;
  }

  public PagePost profile(PageProfile pageProfile) {
    this.setProfile(pageProfile);
    return this;
  }

  public void setProfile(PageProfile pageProfile) {
    this.profile = pageProfile;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public PagePost baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Set<Post> getMyPostInPages() {
    return this.myPostInPages;
  }

  public PagePost myPostInPages(Set<Post> posts) {
    this.setMyPostInPages(posts);
    return this;
  }

  public PagePost addMyPostInPage(Post post) {
    this.myPostInPages.add(post);
    post.setPagePost(this);
    return this;
  }

  public PagePost removeMyPostInPage(Post post) {
    this.myPostInPages.remove(post);
    post.setPagePost(null);
    return this;
  }

  public void setMyPostInPages(Set<Post> posts) {
    if (this.myPostInPages != null) {
      this.myPostInPages.forEach(i -> i.setPagePost(null));
    }
    if (posts != null) {
      posts.forEach(i -> i.setPagePost(this));
    }
    this.myPostInPages = posts;
  }

  public MasterUser getMasterUser() {
    return this.masterUser;
  }

  public PagePost masterUser(MasterUser masterUser) {
    this.setMasterUser(masterUser);
    return this;
  }

  public void setMasterUser(MasterUser masterUser) {
    this.masterUser = masterUser;
  }

  public Set<TopicInterest> getTopicInterests() {
    return this.topicInterests;
  }

  public PagePost topicInterests(Set<TopicInterest> topicInterests) {
    this.setTopicInterests(topicInterests);
    return this;
  }

  public PagePost addTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.add(topicInterest);
    topicInterest.getPagePosts().add(this);
    return this;
  }

  public PagePost removeTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.remove(topicInterest);
    topicInterest.getPagePosts().remove(this);
    return this;
  }

  public void setTopicInterests(Set<TopicInterest> topicInterests) {
    if (this.topicInterests != null) {
      this.topicInterests.forEach(i -> i.removePagePost(this));
    }
    if (topicInterests != null) {
      topicInterests.forEach(i -> i.addPagePost(this));
    }
    this.topicInterests = topicInterests;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PagePost)) {
      return false;
    }
    return id != null && id.equals(((PagePost) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PagePost{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", quickInfo='" + getQuickInfo() + "'" +
            "}";
    }
}
