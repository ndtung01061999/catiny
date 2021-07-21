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
 * A DTO for the {@link com.regitiny.catiny.domain.MasterUser} entity.
 */
@ApiModel(
  description = "@what?            -> The MasterUser entity.\n@why?             -> User (mặc định của jhipster) không cho thêm cột (nếu thêm thì sau khó update)\n@use-to:          -> Lưu thông tin cơ bản của một người dùng\n@commonly-used-in -> Thường sử dụng khi thao tác với tài khoản trong service trên server\n\n@describe      	  -> Những dữ liệu của tài khoản và thương xuyên sử dụng (trong service) sẽ được lưu ở đây"
)
@GeneratedByJHipster
public class MasterUserDTO implements Serializable {

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

  /**
   * fullName : tên hiển thị . thực ra chỉ là firstName + lastName . nhưng sẽ rất bất tiện
   */
  @NotNull
  @ApiModelProperty(value = "fullName : tên hiển thị . thực ra chỉ là firstName + lastName . nhưng sẽ rất bất tiện", required = true)
  private String fullName;

  /**
   * nickname : biệt danh của người dùng
   */
  @ApiModelProperty(value = "nickname : biệt danh của người dùng")
  private String nickname;

  /**
   * avatar : @type Json -> ảnh đại diện của người dùng
   */
  @ApiModelProperty(value = "avatar : @type Json -> ảnh đại diện của người dùng")
  @Lob
  private String avatar;

  /**
   * quickInfo      : @type Json -> thông tin nhanh về người dùng này dùng trong giới thiệu sơ khi chỉ chuột vào người dùng
   */
  @ApiModelProperty(
    value = "quickInfo      : @type Json -> thông tin nhanh về người dùng này dùng trong giới thiệu sơ khi chỉ chuột vào người dùng"
  )
  @Lob
  private String quickInfo;

  private UserDTO user;

  private RankUserDTO myRank;

  private BaseInfoDTO baseInfo;

  private Set<TopicInterestDTO> topicInterests = new HashSet<>();

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

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getQuickInfo() {
    return quickInfo;
  }

  public void setQuickInfo(String quickInfo) {
    this.quickInfo = quickInfo;
  }

  public UserDTO getUser() {
    return user;
  }

  public void setUser(UserDTO user) {
    this.user = user;
  }

  public RankUserDTO getMyRank() {
    return myRank;
  }

  public void setMyRank(RankUserDTO myRank) {
    this.myRank = myRank;
  }

  public BaseInfoDTO getBaseInfo() {
    return baseInfo;
  }

  public void setBaseInfo(BaseInfoDTO baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Set<TopicInterestDTO> getTopicInterests() {
    return topicInterests;
  }

  public void setTopicInterests(Set<TopicInterestDTO> topicInterests) {
    this.topicInterests = topicInterests;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MasterUserDTO)) {
      return false;
    }

    MasterUserDTO masterUserDTO = (MasterUserDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, masterUserDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MasterUserDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", nickname='" + getNickname() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", quickInfo='" + getQuickInfo() + "'" +
            ", user=" + getUser() +
            ", myRank=" + getMyRank() +
            ", baseInfo=" + getBaseInfo() +
            ", topicInterests=" + getTopicInterests() +
            "}";
    }
}
