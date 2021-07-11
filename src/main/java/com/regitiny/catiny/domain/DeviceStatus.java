package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.DeviceType;
import com.regitiny.catiny.domain.enumeration.StatusName;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @what?            -> The DeviceStatus entity.\n@why?             ->\n@use-to           -> Những thiết bị đang truy cập thông tin chi tiết về chúng ...\n@commonly-used-in -> Những nghiệp vụ cần biết chi tiết trang thái của các thiết bị\n\n@describe         ->
 */
@Entity
@Table(name = "device_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "devicestatus")
@GeneratedByJHipster
public class DeviceStatus implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  /**
   * uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)
   */
  @NotNull
  @Type(type = "uuid-char")
  @Column(name = "uuid", length = 36, nullable = false, unique = true)
  private UUID uuid;

  /**
   * deviceName     : tên của thiết bị nếu có
   */
  @Column(name = "device_name")
  private String deviceName;

  /**
   * deviceName     : loại thiết bị
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "device_type")
  private DeviceType deviceType;

  /**
   * deviceStatus   : trạng thái hiện tại của thiết bị là gì
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "device_status")
  private StatusName deviceStatus;

  /**
   * lastVisited    : thời gian truy cập lần cuối cùng của thiết bị này
   */
  @Column(name = "last_visited")
  private Instant lastVisited;

  /**
   * statusComment  : người dùng comment lại trạng thái nếu muốn
   */
  @Column(name = "status_comment")
  private String statusComment;

  @JsonIgnoreProperties(
    value = {
      "userProfile",
      "accountStatus",
      "deviceStatus",
      "friend",
      "followUser",
      "followGroup",
      "followPage",
      "fileInfo",
      "pagePost",
      "pageProfile",
      "groupPost",
      "post",
      "postComment",
      "postLike",
      "groupProfile",
      "newsFeed",
      "messageGroup",
      "messageContent",
      "rankUser",
      "rankGroup",
      "notification",
      "album",
      "video",
      "image",
      "videoStream",
      "videoLiveStreamBuffer",
      "topicInterest",
      "todoList",
      "event",
    },
    allowSetters = true
  )
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo baseInfo;

  @ManyToOne
  @JsonIgnoreProperties(value = { "baseInfo", "deviceStatuses" }, allowSetters = true)
  private AccountStatus accountStatus;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public DeviceStatus id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public DeviceStatus uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getDeviceName() {
    return this.deviceName;
  }

  public DeviceStatus deviceName(String deviceName) {
    this.deviceName = deviceName;
    return this;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public DeviceType getDeviceType() {
    return this.deviceType;
  }

  public DeviceStatus deviceType(DeviceType deviceType) {
    this.deviceType = deviceType;
    return this;
  }

  public void setDeviceType(DeviceType deviceType) {
    this.deviceType = deviceType;
  }

  public StatusName getDeviceStatus() {
    return this.deviceStatus;
  }

  public DeviceStatus deviceStatus(StatusName deviceStatus) {
    this.deviceStatus = deviceStatus;
    return this;
  }

  public void setDeviceStatus(StatusName deviceStatus) {
    this.deviceStatus = deviceStatus;
  }

  public Instant getLastVisited() {
    return this.lastVisited;
  }

  public DeviceStatus lastVisited(Instant lastVisited) {
    this.lastVisited = lastVisited;
    return this;
  }

  public void setLastVisited(Instant lastVisited) {
    this.lastVisited = lastVisited;
  }

  public String getStatusComment() {
    return this.statusComment;
  }

  public DeviceStatus statusComment(String statusComment) {
    this.statusComment = statusComment;
    return this;
  }

  public void setStatusComment(String statusComment) {
    this.statusComment = statusComment;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public DeviceStatus baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public AccountStatus getAccountStatus() {
    return this.accountStatus;
  }

  public DeviceStatus accountStatus(AccountStatus accountStatus) {
    this.setAccountStatus(accountStatus);
    return this;
  }

  public void setAccountStatus(AccountStatus accountStatus) {
    this.accountStatus = accountStatus;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DeviceStatus)) {
      return false;
    }
    return id != null && id.equals(((DeviceStatus) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "DeviceStatus{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", deviceName='" + getDeviceName() + "'" +
            ", deviceType='" + getDeviceType() + "'" +
            ", deviceStatus='" + getDeviceStatus() + "'" +
            ", lastVisited='" + getLastVisited() + "'" +
            ", statusComment='" + getStatusComment() + "'" +
            "}";
    }
}
