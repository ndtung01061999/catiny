package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.HanhChinhVN} entity.
 */
@GeneratedByJHipster
public class HanhChinhVNDTO implements Serializable {

  private Long id;

  /**
   * name Example(\"Ninh Kiều\")
   */
  @NotNull
  @ApiModelProperty(value = "name Example(\"Ninh Kiều\")", required = true)
  private String name;

  /**
   * slug Example(\"ninh-kieu\")
   */
  @NotNull
  @ApiModelProperty(value = "slug Example(\"ninh-kieu\")", required = true)
  private String slug;

  /**
   * type Example(\"quan\")
   */
  @NotNull
  @ApiModelProperty(value = "type Example(\"quan\")", required = true)
  private String type;

  /**
   * nameWithType Example(\"Quận Ninh Kiều\")
   */
  @NotNull
  @ApiModelProperty(value = "nameWithType Example(\"Quận Ninh Kiều\")", required = true)
  private String nameWithType;

  /**
   * code Example(\"916\")
   */
  @NotNull
  @ApiModelProperty(value = "code Example(\"916\")", required = true)
  private String code;

  /**
   * parentCode Example(\"92\") , equal to 0 is the city
   */
  @NotNull
  @ApiModelProperty(value = "parentCode Example(\"92\") , equal to 0 is the city", required = true)
  private String parentCode;

  /**
   * path Example(\"Ninh Kiều, Cần Thơ\")
   */
  @ApiModelProperty(value = "path Example(\"Ninh Kiều, Cần Thơ\")")
  private String path;

  /**
   * pathWithType Example(\"Quận Ninh Kiều, Thành phố Cần Thơ\")
   */
  @ApiModelProperty(value = "pathWithType Example(\"Quận Ninh Kiều, Thành phố Cần Thơ\")")
  private String pathWithType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getNameWithType() {
    return nameWithType;
  }

  public void setNameWithType(String nameWithType) {
    this.nameWithType = nameWithType;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getParentCode() {
    return parentCode;
  }

  public void setParentCode(String parentCode) {
    this.parentCode = parentCode;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getPathWithType() {
    return pathWithType;
  }

  public void setPathWithType(String pathWithType) {
    this.pathWithType = pathWithType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof HanhChinhVNDTO)) {
      return false;
    }

    HanhChinhVNDTO hanhChinhVNDTO = (HanhChinhVNDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, hanhChinhVNDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "HanhChinhVNDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", slug='" + getSlug() + "'" +
            ", type='" + getType() + "'" +
            ", nameWithType='" + getNameWithType() + "'" +
            ", code='" + getCode() + "'" +
            ", parentCode='" + getParentCode() + "'" +
            ", path='" + getPath() + "'" +
            ", pathWithType='" + getPathWithType() + "'" +
            "}";
    }
}
