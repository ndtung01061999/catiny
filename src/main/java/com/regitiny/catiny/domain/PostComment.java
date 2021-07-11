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
 * @what?            -> The PostComment entity.\n@why?             ->\n@use-to           -> Lưu những bình luận của người dùng trong một bài đăng cụ thể\n@commonly-used-in -> được biết dưới dạng bình luận của các bài đăng\n\n@describe         ->
 */
@Entity
@Table(name = "post_comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "postcomment")
@GeneratedByJHipster
public class PostComment implements Serializable {

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

  @OneToMany(mappedBy = "commentParent")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "commentReplies", "userComment", "post", "commentParent" }, allowSetters = true)
  private Set<PostComment> commentReplies = new HashSet<>();

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
  private MasterUser userComment;

  @ManyToOne
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
  private Post post;

  @ManyToOne
  @JsonIgnoreProperties(value = { "baseInfo", "commentReplies", "userComment", "post", "commentParent" }, allowSetters = true)
  private PostComment commentParent;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PostComment id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public PostComment uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getContent() {
    return this.content;
  }

  public PostComment content(String content) {
    this.content = content;
    return this;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public PostComment baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Set<PostComment> getCommentReplies() {
    return this.commentReplies;
  }

  public PostComment commentReplies(Set<PostComment> postComments) {
    this.setCommentReplies(postComments);
    return this;
  }

  public PostComment addCommentReply(PostComment postComment) {
    this.commentReplies.add(postComment);
    postComment.setCommentParent(this);
    return this;
  }

  public PostComment removeCommentReply(PostComment postComment) {
    this.commentReplies.remove(postComment);
    postComment.setCommentParent(null);
    return this;
  }

  public void setCommentReplies(Set<PostComment> postComments) {
    if (this.commentReplies != null) {
      this.commentReplies.forEach(i -> i.setCommentParent(null));
    }
    if (postComments != null) {
      postComments.forEach(i -> i.setCommentParent(this));
    }
    this.commentReplies = postComments;
  }

  public MasterUser getUserComment() {
    return this.userComment;
  }

  public PostComment userComment(MasterUser masterUser) {
    this.setUserComment(masterUser);
    return this;
  }

  public void setUserComment(MasterUser masterUser) {
    this.userComment = masterUser;
  }

  public Post getPost() {
    return this.post;
  }

  public PostComment post(Post post) {
    this.setPost(post);
    return this;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public PostComment getCommentParent() {
    return this.commentParent;
  }

  public PostComment commentParent(PostComment postComment) {
    this.setCommentParent(postComment);
    return this;
  }

  public void setCommentParent(PostComment postComment) {
    this.commentParent = postComment;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PostComment)) {
      return false;
    }
    return id != null && id.equals(((PostComment) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PostComment{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}
