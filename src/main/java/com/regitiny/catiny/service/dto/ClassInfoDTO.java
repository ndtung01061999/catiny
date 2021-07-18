package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.ClassInfo} entity.
 */
@GeneratedByJHipster
public class ClassInfoDTO implements Serializable {

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
   * packageName *  : tên package
   */
  @ApiModelProperty(value = "packageName *  : tên package")
  private String packageName;

  /**
   * fullName *     : tên đầy đủ của class . package+ClassName
   */
  @NotNull
  @ApiModelProperty(value = "fullName *     : tên đầy đủ của class . package+ClassName", required = true)
  private String fullName;

  /**
   * classname *    : tên của class
   */
  @ApiModelProperty(value = "classname *    : tên của class")
  private String className;

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

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ClassInfoDTO)) {
      return false;
    }

    ClassInfoDTO classInfoDTO = (ClassInfoDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, classInfoDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "ClassInfoDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", packageName='" + getPackageName() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", className='" + getClassName() + "'" +
            "}";
    }
}
