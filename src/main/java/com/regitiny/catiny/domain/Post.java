package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.PostInType;
import com.regitiny.catiny.domain.enumeration.PostType;
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
 * @what?            -> The Post entity\n@why?             ->\n@use-to           -> lưu các bài viết của người dùng\n@commonly-used-in -> đăng và xem các bài viết\n\n@describe         ->
 */
@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "post")
@GeneratedByJHipster
public class Post implements Serializable {

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
   * postInType     : bài đăng này đăng ở đâu : người dùng , nhóm hay trang
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "post_in_type")
  private PostInType postInType;

  /**
   * postType       : bài đăng này đơn giản , phức tạp hay dùng froala
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "post_type")
  private PostType postType;

  /**
   * content        : @type Json -> nội dùng bài đăng dữ liệu tùy theo postType
   */
  @Lob
  @Column(name = "content")
  private String content;

  @Lob
  @Column(name = "search_field")
  private String searchField;

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

  @OneToMany(mappedBy = "post")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "likes", "commentReplies", "post", "commentParent" }, allowSetters = true)
  private Set<PostComment> comments = new HashSet<>();

  @OneToMany(mappedBy = "post")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "post", "postComment" }, allowSetters = true)
  private Set<PostLike> likes = new HashSet<>();

  @OneToMany(mappedBy = "postShareParent")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = {
      "baseInfo", "comments", "likes", "postShareChildren", "groupPost", "pagePost", "postShareParent", "newsFeeds", "topicInterests",
    },
    allowSetters = true
  )
  private Set<Post> postShareChildren = new HashSet<>();

  @ManyToOne
  @JsonIgnoreProperties(value = { "profile", "baseInfo", "myPostInGroups", "topicInterests" }, allowSetters = true)
  private GroupPost groupPost;

  @ManyToOne
  @JsonIgnoreProperties(value = { "profile", "baseInfo", "myPostInPages", "topicInterests" }, allowSetters = true)
  private PagePost pagePost;

  @ManyToOne
  @JsonIgnoreProperties(
    value = {
      "baseInfo", "comments", "likes", "postShareChildren", "groupPost", "pagePost", "postShareParent", "newsFeeds", "topicInterests",
    },
    allowSetters = true
  )
  private Post postShareParent;

  @OneToMany(mappedBy = "post")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "post" }, allowSetters = true)
  private Set<NewsFeed> newsFeeds = new HashSet<>();

  @ManyToMany(mappedBy = "posts")
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

  public Post id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public Post uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public PostInType getPostInType() {
    return this.postInType;
  }

  public Post postInType(PostInType postInType) {
    this.postInType = postInType;
    return this;
  }

  public void setPostInType(PostInType postInType) {
    this.postInType = postInType;
  }

  public PostType getPostType() {
    return this.postType;
  }

  public Post postType(PostType postType) {
    this.postType = postType;
    return this;
  }

  public void setPostType(PostType postType) {
    this.postType = postType;
  }

  public String getContent() {
    return this.content;
  }

  public Post content(String content) {
    this.content = content;
    return this;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSearchField() {
    return this.searchField;
  }

  public Post searchField(String searchField) {
    this.searchField = searchField;
    return this;
  }

  public void setSearchField(String searchField) {
    this.searchField = searchField;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public Post baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Set<PostComment> getComments() {
    return this.comments;
  }

  public Post comments(Set<PostComment> postComments) {
    this.setComments(postComments);
    return this;
  }

  public Post addComment(PostComment postComment) {
    this.comments.add(postComment);
    postComment.setPost(this);
    return this;
  }

  public Post removeComment(PostComment postComment) {
    this.comments.remove(postComment);
    postComment.setPost(null);
    return this;
  }

  public void setComments(Set<PostComment> postComments) {
    if (this.comments != null) {
      this.comments.forEach(i -> i.setPost(null));
    }
    if (postComments != null) {
      postComments.forEach(i -> i.setPost(this));
    }
    this.comments = postComments;
  }

  public Set<PostLike> getLikes() {
    return this.likes;
  }

  public Post likes(Set<PostLike> postLikes) {
    this.setLikes(postLikes);
    return this;
  }

  public Post addLike(PostLike postLike) {
    this.likes.add(postLike);
    postLike.setPost(this);
    return this;
  }

  public Post removeLike(PostLike postLike) {
    this.likes.remove(postLike);
    postLike.setPost(null);
    return this;
  }

  public void setLikes(Set<PostLike> postLikes) {
    if (this.likes != null) {
      this.likes.forEach(i -> i.setPost(null));
    }
    if (postLikes != null) {
      postLikes.forEach(i -> i.setPost(this));
    }
    this.likes = postLikes;
  }

  public Set<Post> getPostShareChildren() {
    return this.postShareChildren;
  }

  public Post postShareChildren(Set<Post> posts) {
    this.setPostShareChildren(posts);
    return this;
  }

  public Post addPostShareChildren(Post post) {
    this.postShareChildren.add(post);
    post.setPostShareParent(this);
    return this;
  }

  public Post removePostShareChildren(Post post) {
    this.postShareChildren.remove(post);
    post.setPostShareParent(null);
    return this;
  }

  public void setPostShareChildren(Set<Post> posts) {
    if (this.postShareChildren != null) {
      this.postShareChildren.forEach(i -> i.setPostShareParent(null));
    }
    if (posts != null) {
      posts.forEach(i -> i.setPostShareParent(this));
    }
    this.postShareChildren = posts;
  }

  public GroupPost getGroupPost() {
    return this.groupPost;
  }

  public Post groupPost(GroupPost groupPost) {
    this.setGroupPost(groupPost);
    return this;
  }

  public void setGroupPost(GroupPost groupPost) {
    this.groupPost = groupPost;
  }

  public PagePost getPagePost() {
    return this.pagePost;
  }

  public Post pagePost(PagePost pagePost) {
    this.setPagePost(pagePost);
    return this;
  }

  public void setPagePost(PagePost pagePost) {
    this.pagePost = pagePost;
  }

  public Post getPostShareParent() {
    return this.postShareParent;
  }

  public Post postShareParent(Post post) {
    this.setPostShareParent(post);
    return this;
  }

  public void setPostShareParent(Post post) {
    this.postShareParent = post;
  }

  public Set<NewsFeed> getNewsFeeds() {
    return this.newsFeeds;
  }

  public Post newsFeeds(Set<NewsFeed> newsFeeds) {
    this.setNewsFeeds(newsFeeds);
    return this;
  }

  public Post addNewsFeed(NewsFeed newsFeed) {
    this.newsFeeds.add(newsFeed);
    newsFeed.setPost(this);
    return this;
  }

  public Post removeNewsFeed(NewsFeed newsFeed) {
    this.newsFeeds.remove(newsFeed);
    newsFeed.setPost(null);
    return this;
  }

  public void setNewsFeeds(Set<NewsFeed> newsFeeds) {
    if (this.newsFeeds != null) {
      this.newsFeeds.forEach(i -> i.setPost(null));
    }
    if (newsFeeds != null) {
      newsFeeds.forEach(i -> i.setPost(this));
    }
    this.newsFeeds = newsFeeds;
  }

  public Set<TopicInterest> getTopicInterests() {
    return this.topicInterests;
  }

  public Post topicInterests(Set<TopicInterest> topicInterests) {
    this.setTopicInterests(topicInterests);
    return this;
  }

  public Post addTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.add(topicInterest);
    topicInterest.getPosts().add(this);
    return this;
  }

  public Post removeTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.remove(topicInterest);
    topicInterest.getPosts().remove(this);
    return this;
  }

  public void setTopicInterests(Set<TopicInterest> topicInterests) {
    if (this.topicInterests != null) {
      this.topicInterests.forEach(i -> i.removePost(this));
    }
    if (topicInterests != null) {
      topicInterests.forEach(i -> i.addPost(this));
    }
    this.topicInterests = topicInterests;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Post)) {
      return false;
    }
    return id != null && id.equals(((Post) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Post{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", postInType='" + getPostInType() + "'" +
            ", postType='" + getPostType() + "'" +
            ", content='" + getContent() + "'" +
            ", searchField='" + getSearchField() + "'" +
            "}";
    }
}
