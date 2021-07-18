package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.FileInfo} entity.
 */
@ApiModel(
  description = "@what?            -> The FileInfo entity.\n@why?             ->\n@use-to           -> Quản lý thông tin về file, vị trí file ...\n@commonly-used-in -> Những file mà người dùng upload (ảnh video ...)\n\n@describe         ->"
)
@GeneratedByJHipster
public class FileInfoDTO implements Serializable {

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
   * nameFile       : tên của file nếu có
   */
  @ApiModelProperty(value = "nameFile       : tên của file nếu có")
  private String nameFile;

  /**
   * typeFile       : loại file
   */
  @ApiModelProperty(value = "typeFile       : loại file")
  private String typeFile;

  /**
   * path           : đường dẫn file trên server
   */
  @Size(max = 1024)
  @ApiModelProperty(value = "path           : đường dẫn file trên server")
  private String path;

  /**
   * dataSize       : kích thước file theo byte
   */
  @ApiModelProperty(value = "dataSize       : kích thước file theo byte")
  private Long dataSize;

  private BaseInfoDTO baseInfo;

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

  public String getNameFile() {
    return nameFile;
  }

  public void setNameFile(String nameFile) {
    this.nameFile = nameFile;
  }

  public String getTypeFile() {
    return typeFile;
  }

  public void setTypeFile(String typeFile) {
    this.typeFile = typeFile;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Long getDataSize() {
    return dataSize;
  }

  public void setDataSize(Long dataSize) {
    this.dataSize = dataSize;
  }

  public BaseInfoDTO getBaseInfo() {
    return baseInfo;
  }

  public void setBaseInfo(BaseInfoDTO baseInfo) {
    this.baseInfo = baseInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FileInfoDTO)) {
      return false;
    }

    FileInfoDTO fileInfoDTO = (FileInfoDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, fileInfoDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "FileInfoDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", nameFile='" + getNameFile() + "'" +
            ", typeFile='" + getTypeFile() + "'" +
            ", path='" + getPath() + "'" +
            ", dataSize=" + getDataSize() +
            ", baseInfo=" + getBaseInfo() +
            "}";
    }
}
