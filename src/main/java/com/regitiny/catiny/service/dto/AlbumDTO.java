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
 * A DTO for the {@link com.regitiny.catiny.domain.Album} entity.
 */
@ApiModel(
  description = "@what?            -> The Album entity\n@why?             ->\n@use-to           -> Lưu thông tin về một bộ album của người dùng\n@commonly-used-in -> Người dùng nhóm một bộ ảnh vào một album\n\n@describe         ->"
)
@GeneratedByJHipster
public class AlbumDTO implements Serializable {

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
   * name           : tên của album
   */
  @NotNull
  @ApiModelProperty(value = "name           : tên của album", required = true)
  private String name;

  /**
   * note           : trú thích của album (ví dụ album đại học)
   */
  @ApiModelProperty(value = "note           : trú thích của album (ví dụ album đại học)")
  private String note;

  /**
   * avatar         : @type Json -> ảnh đại diện của Album
   */
  @ApiModelProperty(value = "avatar         : @type Json -> ảnh đại diện của Album")
  @Lob
  private String avatar;

  private BaseInfoDTO baseInfo;

  private Set<ImageDTO> images = new HashSet<>();

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public BaseInfoDTO getBaseInfo() {
    return baseInfo;
  }

  public void setBaseInfo(BaseInfoDTO baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Set<ImageDTO> getImages() {
    return images;
  }

  public void setImages(Set<ImageDTO> images) {
    this.images = images;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AlbumDTO)) {
      return false;
    }

    AlbumDTO albumDTO = (AlbumDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, albumDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "AlbumDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", note='" + getNote() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", baseInfo=" + getBaseInfo() +
            ", images=" + getImages() +
            "}";
    }
}
