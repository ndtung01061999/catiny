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
 * @what?            -> The NewsFeed entity.\n@why?             -> người dùng mà xem trực tiếp các Post thì một số bài đăng sẽ không phù hợp dễ gây chán khi xem\n@use-to           -> Ở đây chứa thông tin của những Post hiển thị cho người dùng xem\n@commonly-used-in -> Được sử dụng trong phần hiển thị các bài đăng trên news feed\n\n@describe         -> trong phần bản tin thay vì hiển thị trực tiếp các Post cho người dùng xem\nta sẽ tính toán độ phù hợp và add vào bảng này sau đó cho người dùng xem
 */
@Entity
@Table(name = "news_feed")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "newsfeed")
@GeneratedByJHipster
public class NewsFeed implements Serializable {

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
   * priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)
   */
  @Column(name = "priority_index")
  private Long priorityIndex;

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

  @ManyToOne
  @JsonIgnoreProperties(
    value = {
      "baseInfo", "comments", "likes", "postShareChildren", "groupPost", "pagePost", "postShareParent", "newsFeeds", "topicInterests",
    },
    allowSetters = true
  )
  private Post post;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public NewsFeed id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public NewsFeed uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public Long getPriorityIndex() {
    return this.priorityIndex;
  }

  public NewsFeed priorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
    return this;
  }

  public void setPriorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public NewsFeed baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Post getPost() {
    return this.post;
  }

  public NewsFeed post(Post post) {
    this.setPost(post);
    return this;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NewsFeed)) {
      return false;
    }
    return id != null && id.equals(((NewsFeed) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "NewsFeed{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", priorityIndex=" + getPriorityIndex() +
            "}";
    }
}
