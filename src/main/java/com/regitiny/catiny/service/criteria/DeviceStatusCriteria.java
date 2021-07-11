package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.DeviceType;
import com.regitiny.catiny.domain.enumeration.StatusName;
import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.InstantFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;
import tech.jhipster.service.filter.UUIDFilter;

/**
 * Criteria class for the {@link com.regitiny.catiny.domain.DeviceStatus} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.DeviceStatusResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /device-statuses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class DeviceStatusCriteria implements Serializable, Criteria {

  /**
   * Class for filtering DeviceType
   */
  @GeneratedByJHipster
  public static class DeviceTypeFilter extends Filter<DeviceType> {

    public DeviceTypeFilter() {}

    public DeviceTypeFilter(DeviceTypeFilter filter) {
      super(filter);
    }

    @Override
    public DeviceTypeFilter copy() {
      return new DeviceTypeFilter(this);
    }
  }

  /**
   * Class for filtering StatusName
   */
  @GeneratedByJHipster
  public static class StatusNameFilter extends Filter<StatusName> {

    public StatusNameFilter() {}

    public StatusNameFilter(StatusNameFilter filter) {
      super(filter);
    }

    @Override
    public StatusNameFilter copy() {
      return new StatusNameFilter(this);
    }
  }

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter deviceName;

  private DeviceTypeFilter deviceType;

  private StatusNameFilter deviceStatus;

  private InstantFilter lastVisited;

  private StringFilter statusComment;

  private LongFilter baseInfoId;

  private LongFilter accountStatusId;

  public DeviceStatusCriteria() {}

  public DeviceStatusCriteria(DeviceStatusCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.deviceName = other.deviceName == null ? null : other.deviceName.copy();
    this.deviceType = other.deviceType == null ? null : other.deviceType.copy();
    this.deviceStatus = other.deviceStatus == null ? null : other.deviceStatus.copy();
    this.lastVisited = other.lastVisited == null ? null : other.lastVisited.copy();
    this.statusComment = other.statusComment == null ? null : other.statusComment.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.accountStatusId = other.accountStatusId == null ? null : other.accountStatusId.copy();
  }

  @Override
  public DeviceStatusCriteria copy() {
    return new DeviceStatusCriteria(this);
  }

  public LongFilter getId() {
    return id;
  }

  public LongFilter id() {
    if (id == null) {
      id = new LongFilter();
    }
    return id;
  }

  public void setId(LongFilter id) {
    this.id = id;
  }

  public UUIDFilter getUuid() {
    return uuid;
  }

  public UUIDFilter uuid() {
    if (uuid == null) {
      uuid = new UUIDFilter();
    }
    return uuid;
  }

  public void setUuid(UUIDFilter uuid) {
    this.uuid = uuid;
  }

  public StringFilter getDeviceName() {
    return deviceName;
  }

  public StringFilter deviceName() {
    if (deviceName == null) {
      deviceName = new StringFilter();
    }
    return deviceName;
  }

  public void setDeviceName(StringFilter deviceName) {
    this.deviceName = deviceName;
  }

  public DeviceTypeFilter getDeviceType() {
    return deviceType;
  }

  public DeviceTypeFilter deviceType() {
    if (deviceType == null) {
      deviceType = new DeviceTypeFilter();
    }
    return deviceType;
  }

  public void setDeviceType(DeviceTypeFilter deviceType) {
    this.deviceType = deviceType;
  }

  public StatusNameFilter getDeviceStatus() {
    return deviceStatus;
  }

  public StatusNameFilter deviceStatus() {
    if (deviceStatus == null) {
      deviceStatus = new StatusNameFilter();
    }
    return deviceStatus;
  }

  public void setDeviceStatus(StatusNameFilter deviceStatus) {
    this.deviceStatus = deviceStatus;
  }

  public InstantFilter getLastVisited() {
    return lastVisited;
  }

  public InstantFilter lastVisited() {
    if (lastVisited == null) {
      lastVisited = new InstantFilter();
    }
    return lastVisited;
  }

  public void setLastVisited(InstantFilter lastVisited) {
    this.lastVisited = lastVisited;
  }

  public StringFilter getStatusComment() {
    return statusComment;
  }

  public StringFilter statusComment() {
    if (statusComment == null) {
      statusComment = new StringFilter();
    }
    return statusComment;
  }

  public void setStatusComment(StringFilter statusComment) {
    this.statusComment = statusComment;
  }

  public LongFilter getBaseInfoId() {
    return baseInfoId;
  }

  public LongFilter baseInfoId() {
    if (baseInfoId == null) {
      baseInfoId = new LongFilter();
    }
    return baseInfoId;
  }

  public void setBaseInfoId(LongFilter baseInfoId) {
    this.baseInfoId = baseInfoId;
  }

  public LongFilter getAccountStatusId() {
    return accountStatusId;
  }

  public LongFilter accountStatusId() {
    if (accountStatusId == null) {
      accountStatusId = new LongFilter();
    }
    return accountStatusId;
  }

  public void setAccountStatusId(LongFilter accountStatusId) {
    this.accountStatusId = accountStatusId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final DeviceStatusCriteria that = (DeviceStatusCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(deviceName, that.deviceName) &&
      Objects.equals(deviceType, that.deviceType) &&
      Objects.equals(deviceStatus, that.deviceStatus) &&
      Objects.equals(lastVisited, that.lastVisited) &&
      Objects.equals(statusComment, that.statusComment) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(accountStatusId, that.accountStatusId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, deviceName, deviceType, deviceStatus, lastVisited, statusComment, baseInfoId, accountStatusId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "DeviceStatusCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (deviceName != null ? "deviceName=" + deviceName + ", " : "") +
            (deviceType != null ? "deviceType=" + deviceType + ", " : "") +
            (deviceStatus != null ? "deviceStatus=" + deviceStatus + ", " : "") +
            (lastVisited != null ? "lastVisited=" + lastVisited + ", " : "") +
            (statusComment != null ? "statusComment=" + statusComment + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (accountStatusId != null ? "accountStatusId=" + accountStatusId + ", " : "") +
            "}";
    }
}
