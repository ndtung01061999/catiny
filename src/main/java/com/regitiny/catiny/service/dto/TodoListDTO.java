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
 * A DTO for the {@link com.regitiny.catiny.domain.TodoList} entity.
 */
@ApiModel(
  description = "@what?            -> The TodoList entity.\n@why?             ->\n@use-to           -> Lưu danh sách công việc gợi ý cho người dùng thực hiện\n@commonly-used-in -> Hiển thị bảng TodoList cho người dùng thực hiện\n\n@describe         ->"
)
@GeneratedByJHipster
public class TodoListDTO implements Serializable {

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

  private MasterUserDTO masterUser;

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

  public MasterUserDTO getMasterUser() {
    return masterUser;
  }

  public void setMasterUser(MasterUserDTO masterUser) {
    this.masterUser = masterUser;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TodoListDTO)) {
      return false;
    }

    TodoListDTO todoListDTO = (TodoListDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, todoListDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "TodoListDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", baseInfo=" + getBaseInfo() +
            ", masterUser=" + getMasterUser() +
            "}";
    }
}
