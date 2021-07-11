package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
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
 * Criteria class for the {@link com.regitiny.catiny.domain.AccountStatus} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.AccountStatusResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /account-statuses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class AccountStatusCriteria implements Serializable, Criteria {

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

  private StatusNameFilter accountStatus;

  private InstantFilter lastVisited;

  private StringFilter statusComment;

  private LongFilter baseInfoId;

  private LongFilter deviceStatusId;

  public AccountStatusCriteria() {}

  public AccountStatusCriteria(AccountStatusCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.accountStatus = other.accountStatus == null ? null : other.accountStatus.copy();
    this.lastVisited = other.lastVisited == null ? null : other.lastVisited.copy();
    this.statusComment = other.statusComment == null ? null : other.statusComment.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.deviceStatusId = other.deviceStatusId == null ? null : other.deviceStatusId.copy();
  }

  @Override
  public AccountStatusCriteria copy() {
    return new AccountStatusCriteria(this);
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

  public StatusNameFilter getAccountStatus() {
    return accountStatus;
  }

  public StatusNameFilter accountStatus() {
    if (accountStatus == null) {
      accountStatus = new StatusNameFilter();
    }
    return accountStatus;
  }

  public void setAccountStatus(StatusNameFilter accountStatus) {
    this.accountStatus = accountStatus;
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

  public LongFilter getDeviceStatusId() {
    return deviceStatusId;
  }

  public LongFilter deviceStatusId() {
    if (deviceStatusId == null) {
      deviceStatusId = new LongFilter();
    }
    return deviceStatusId;
  }

  public void setDeviceStatusId(LongFilter deviceStatusId) {
    this.deviceStatusId = deviceStatusId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AccountStatusCriteria that = (AccountStatusCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(accountStatus, that.accountStatus) &&
      Objects.equals(lastVisited, that.lastVisited) &&
      Objects.equals(statusComment, that.statusComment) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(deviceStatusId, that.deviceStatusId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, accountStatus, lastVisited, statusComment, baseInfoId, deviceStatusId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "AccountStatusCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (accountStatus != null ? "accountStatus=" + accountStatus + ", " : "") +
            (lastVisited != null ? "lastVisited=" + lastVisited + ", " : "") +
            (statusComment != null ? "statusComment=" + statusComment + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (deviceStatusId != null ? "deviceStatusId=" + deviceStatusId + ", " : "") +
            "}";
    }
}
