package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;
import tech.jhipster.service.filter.UUIDFilter;

/**
 * Criteria class for the {@link com.regitiny.catiny.domain.RankUser} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.RankUserResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /rank-users?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class RankUserCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private FloatFilter ratingPoints;

  private LongFilter baseInfoId;

  private LongFilter rankGroupId;

  public RankUserCriteria() {}

  public RankUserCriteria(RankUserCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.ratingPoints = other.ratingPoints == null ? null : other.ratingPoints.copy();
    this.baseInfoId = other.baseInfoId == null ? null : other.baseInfoId.copy();
    this.rankGroupId = other.rankGroupId == null ? null : other.rankGroupId.copy();
  }

  @Override
  public RankUserCriteria copy() {
    return new RankUserCriteria(this);
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

  public FloatFilter getRatingPoints() {
    return ratingPoints;
  }

  public FloatFilter ratingPoints() {
    if (ratingPoints == null) {
      ratingPoints = new FloatFilter();
    }
    return ratingPoints;
  }

  public void setRatingPoints(FloatFilter ratingPoints) {
    this.ratingPoints = ratingPoints;
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

  public LongFilter getRankGroupId() {
    return rankGroupId;
  }

  public LongFilter rankGroupId() {
    if (rankGroupId == null) {
      rankGroupId = new LongFilter();
    }
    return rankGroupId;
  }

  public void setRankGroupId(LongFilter rankGroupId) {
    this.rankGroupId = rankGroupId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final RankUserCriteria that = (RankUserCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(ratingPoints, that.ratingPoints) &&
      Objects.equals(baseInfoId, that.baseInfoId) &&
      Objects.equals(rankGroupId, that.rankGroupId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, ratingPoints, baseInfoId, rankGroupId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "RankUserCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (ratingPoints != null ? "ratingPoints=" + ratingPoints + ", " : "") +
            (baseInfoId != null ? "baseInfoId=" + baseInfoId + ", " : "") +
            (rankGroupId != null ? "rankGroupId=" + rankGroupId + ", " : "") +
            "}";
    }
}
