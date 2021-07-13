package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.PostComment} entity.
 */
@ApiModel(
  description = "@what?            -> The PostComment entity.\n@why?             ->\n@use-to           -> Lưu những bình luận của người dùng trong một bài đăng cụ thể\n@commonly-used-in -> được biết dưới dạng bình luận của các bài đăng\n\n@describe         ->"
)
@GeneratedByJHipster
public class PostCommentDTO implements Serializable {

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

  @Lob
  private String content;

  private BaseInfoDTO baseInfo;

  private MasterUserDTO userComment;

  private PostDTO post;

  private PostCommentDTO commentParent;

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

  public MasterUserDTO getUserComment() {
    return userComment;
  }

  public void setUserComment(MasterUserDTO userComment) {
    this.userComment = userComment;
  }

  public PostDTO getPost() {
    return post;
  }

  public void setPost(PostDTO post) {
    this.post = post;
  }

  public PostCommentDTO getCommentParent() {
    return commentParent;
  }

  public void setCommentParent(PostCommentDTO commentParent) {
    this.commentParent = commentParent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PostCommentDTO)) {
      return false;
    }

    PostCommentDTO postCommentDTO = (PostCommentDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, postCommentDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PostCommentDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", content='" + getContent() + "'" +
            ", baseInfo=" + getBaseInfo() +
            ", userComment=" + getUserComment() +
            ", post=" + getPost() +
            ", commentParent=" + getCommentParent() +
            "}";
    }
}
