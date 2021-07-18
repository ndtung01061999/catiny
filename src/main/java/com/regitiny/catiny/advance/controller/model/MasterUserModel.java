package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.dto.RankUserDTO;
import com.regitiny.catiny.service.dto.TopicInterestDTO;
import com.regitiny.catiny.service.dto.UserDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterUserModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "fullName : tên hiển thị . thực ra chỉ là firstName + lastName . nhưng sẽ rất bất tiện"
  )
  private String fullName;

  @ApiModelProperty("nickname : biệt danh của người dùng")
  private String nickname;

  @ApiModelProperty("avatar : @type Json -> ảnh đại diện của người dùng")
  @Lob
  private String avatar;

  @ApiModelProperty("quickInfo      : @type Json -> thông tin nhanh về người dùng này dùng trong giới thiệu sơ khi chỉ chuột vào người dùng")
  @Lob
  private String quickInfo;

  private UserDTO user;

  private RankUserDTO myRank;

  private BaseInfoDTO baseInfo;

  private Set<TopicInterestDTO> topicInterests;

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

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "fullName : tên hiển thị . thực ra chỉ là firstName + lastName . nhưng sẽ rất bất tiện"
    )
    private String fullName;

    @ApiModelProperty("nickname : biệt danh của người dùng")
    private String nickname;

    @ApiModelProperty("avatar : @type Json -> ảnh đại diện của người dùng")
    @Lob
    private String avatar;

    @ApiModelProperty("quickInfo      : @type Json -> thông tin nhanh về người dùng này dùng trong giới thiệu sơ khi chỉ chuột vào người dùng")
    @Lob
    private String quickInfo;

    private UserDTO user;

    private RankUserDTO myRank;

    private BaseInfoDTO baseInfo;

    private Set<TopicInterestDTO> topicInterests;
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

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "fullName : tên hiển thị . thực ra chỉ là firstName + lastName . nhưng sẽ rất bất tiện"
    )
    private String fullName;

    @ApiModelProperty("nickname : biệt danh của người dùng")
    private String nickname;

    @ApiModelProperty("avatar : @type Json -> ảnh đại diện của người dùng")
    @Lob
    private String avatar;

    @ApiModelProperty("quickInfo      : @type Json -> thông tin nhanh về người dùng này dùng trong giới thiệu sơ khi chỉ chuột vào người dùng")
    @Lob
    private String quickInfo;

    private UserDTO user;

    private RankUserDTO myRank;

    private BaseInfoDTO baseInfo;

    private Set<TopicInterestDTO> topicInterests;
  }
}
