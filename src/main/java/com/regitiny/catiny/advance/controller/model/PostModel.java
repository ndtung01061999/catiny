package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.domain.enumeration.PostInType;
import com.regitiny.catiny.domain.enumeration.PostType;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.dto.GroupPostDTO;
import com.regitiny.catiny.service.dto.PagePostDTO;
import com.regitiny.catiny.service.dto.PostDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  @ApiModelProperty("postInType     : bài đăng này đăng ở đâu : người dùng , nhóm hay trang")
  private PostInType postInType;

  @ApiModelProperty("postType       : bài đăng này đơn giản , phức tạp hay dùng froala")
  private PostType postType;

  @ApiModelProperty("content        : @type Json -> nội dùng bài đăng dữ liệu tùy theo postType")
  @Lob
  private String content;

  @Lob
  private String searchField;

  private BaseInfoDTO baseInfo;

  private GroupPostDTO groupPost;

  private PagePostDTO pagePost;

  private PostDTO postShareParent;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request implements Serializable
  {
    private Long id;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
    )
    private UUID uuid;

    @ApiModelProperty("postInType     : bài đăng này đăng ở đâu : người dùng , nhóm hay trang")
    private PostInType postInType;

    @ApiModelProperty("postType       : bài đăng này đơn giản , phức tạp hay dùng froala")
    private PostType postType;

    @ApiModelProperty("content        : @type Json -> nội dùng bài đăng dữ liệu tùy theo postType")
    @Lob
    private String content;

    @Lob
    private String searchField;

    private BaseInfoDTO baseInfo;

    private GroupPostDTO groupPost;

    private PagePostDTO pagePost;

    private PostDTO postShareParent;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Response implements Serializable
  {
    private Long id;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
    )
    private UUID uuid;

    @ApiModelProperty("postInType     : bài đăng này đăng ở đâu : người dùng , nhóm hay trang")
    private PostInType postInType;

    @ApiModelProperty("postType       : bài đăng này đơn giản , phức tạp hay dùng froala")
    private PostType postType;

    @ApiModelProperty("content        : @type Json -> nội dùng bài đăng dữ liệu tùy theo postType")
    @Lob
    private String content;

    @Lob
    private String searchField;

    private BaseInfoDTO baseInfo;

    private GroupPostDTO groupPost;

    private PagePostDTO pagePost;

    private PostDTO postShareParent;
  }
}
