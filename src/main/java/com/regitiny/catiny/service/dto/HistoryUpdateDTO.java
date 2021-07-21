package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.HistoryUpdate} entity.
 */
@GeneratedByJHipster
public class HistoryUpdateDTO implements Serializable
{

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
   * version        : phiên bản (bắt đầu từ 0)
   */
  @ApiModelProperty(value = "version        : phiên bản (bắt đầu từ 0)")
  private Integer version;

  /**
   * content        : @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json
   */
  @ApiModelProperty(
    value = "content        : @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json"
  )
  @Lob
  private String content;

  private BaseInfoDTO baseInfo;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public UUID getUuid()
  {
    return uuid;
  }

  public void setUuid(UUID uuid)
  {
    this.uuid = uuid;
  }

  public Integer getVersion()
  {
    return version;
  }

  public void setVersion(Integer version)
  {
    this.version = version;
  }

  public String getContent()
  {
    return content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  public BaseInfoDTO getBaseInfo()
  {
    return baseInfo;
  }

  public void setBaseInfo(BaseInfoDTO baseInfo)
  {
    this.baseInfo = baseInfo;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (!(o instanceof HistoryUpdateDTO))
    {
      return false;
    }

    HistoryUpdateDTO historyUpdateDTO = (HistoryUpdateDTO) o;
    if (this.id == null)
    {
      return false;
    }
    return Objects.equals(this.id, historyUpdateDTO.id);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(this.id);
  }

  // prettier-ignore
  @Override
  public String toString()
  {
    return "HistoryUpdateDTO{" +
      "id=" + getId() +
      ", uuid='" + getUuid() + "'" +
      ", version=" + getVersion() +
      ", content='" + getContent() + "'" +
      ", baseInfo=" + getBaseInfo() +
      "}";
  }
}
