package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.RankUser} entity.
 */
@ApiModel(
  description = "@what?            -> The RankUser entity.\n@why?             ->\n@use-to           -> Xếp hạng của bản thân Trong toàn mạng , trong khu vực , trong nhóm người\n@commonly-used-in -> thường thấy trong phần\n\n@describe         ->"
)
@GeneratedByJHipster
public class RankUserDTO implements Serializable {

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

  private Float ratingPoints;

  private BaseInfoDTO baseInfo;

  private RankGroupDTO rankGroup;

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

  public Float getRatingPoints() {
    return ratingPoints;
  }

  public void setRatingPoints(Float ratingPoints) {
    this.ratingPoints = ratingPoints;
  }

  public BaseInfoDTO getBaseInfo() {
    return baseInfo;
  }

  public void setBaseInfo(BaseInfoDTO baseInfo) {
    this.baseInfo = baseInfo;
  }

  public RankGroupDTO getRankGroup() {
    return rankGroup;
  }

  public void setRankGroup(RankGroupDTO rankGroup) {
    this.rankGroup = rankGroup;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RankUserDTO)) {
      return false;
    }

    RankUserDTO rankUserDTO = (RankUserDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, rankUserDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "RankUserDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", ratingPoints=" + getRatingPoints() +
            ", baseInfo=" + getBaseInfo() +
            ", rankGroup=" + getRankGroup() +
            "}";
    }
}
