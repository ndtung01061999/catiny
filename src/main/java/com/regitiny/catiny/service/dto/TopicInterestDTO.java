package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.TopicInterest} entity.
 */
@ApiModel(
  description = "@what?            -> The TopicInterest entity.\n@why?             ->\n@use-to           -> Lưu những chủ đề mà người dùng quan tâm\n@commonly-used-in -> chủ đề quan tâm để lọc ra cho người dùng xem\n\n@describe         ->"
)
@GeneratedByJHipster
public class TopicInterestDTO implements Serializable {

  private Long id;

  /**
   * uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)
   */
  @NotNull
  @ApiModelProperty(
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)",
    required = true
  )
  private UUID uuid;

  private String title;

  @Lob
  private String content;

  private BaseInfoDTO baseInfo;

  private Set<PostDTO> posts = new HashSet<>();

  private Set<PagePostDTO> pagePosts = new HashSet<>();

  private Set<GroupPostDTO> groupPosts = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public BaseInfoDTO getBaseInfo() {
    return baseInfo;
  }

  public void setBaseInfo(BaseInfoDTO baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Set<PostDTO> getPosts() {
    return posts;
  }

  public void setPosts(Set<PostDTO> posts) {
    this.posts = posts;
  }

  public Set<PagePostDTO> getPagePosts() {
    return pagePosts;
  }

  public void setPagePosts(Set<PagePostDTO> pagePosts) {
    this.pagePosts = pagePosts;
  }

  public Set<GroupPostDTO> getGroupPosts() {
    return groupPosts;
  }

  public void setGroupPosts(Set<GroupPostDTO> groupPosts) {
    this.groupPosts = groupPosts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TopicInterestDTO)) {
      return false;
    }

    TopicInterestDTO topicInterestDTO = (TopicInterestDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, topicInterestDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "TopicInterestDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", baseInfo=" + getBaseInfo() +
            ", posts=" + getPosts() +
            ", pagePosts=" + getPagePosts() +
            ", groupPosts=" + getGroupPosts() +
            "}";
    }
}
