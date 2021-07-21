package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @what?            -> The MasterUser entity.\n@why?             -> User (mặc định của jhipster) không cho thêm cột (nếu thêm thì sau khó update)\n@use-to:          -> Lưu thông tin cơ bản của một người dùng\n@commonly-used-in -> Thường sử dụng khi thao tác với tài khoản trong service trên server\n\n@describe      	  -> Những dữ liệu của tài khoản và thương xuyên sử dụng (trong service) sẽ được lưu ở đây
 */
@Entity
@Table(name = "master_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "masteruser")
@GeneratedByJHipster
public class MasterUser implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  /**
   * uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)
   */
  @NotNull
  @Type(type = "uuid-char")
  @Column(name = "uuid", length = 36, nullable = false, unique = true)
  private UUID uuid;

  /**
   * fullName : tên hiển thị . thực ra chỉ là firstName + lastName . nhưng sẽ rất bất tiện
   */
  @NotNull
  @Column(name = "full_name", nullable = false)
  private String fullName;

  /**
   * nickname : biệt danh của người dùng
   */
  @Column(name = "nickname")
  private String nickname;

  /**
   * avatar : @type Json -> ảnh đại diện của người dùng
   */
  @Lob
  @Column(name = "avatar")
  private String avatar;

  /**
   * quickInfo      : @type Json -> thông tin nhanh về người dùng này dùng trong giới thiệu sơ khi chỉ chuột vào người dùng
   */
  @Lob
  @Column(name = "quick_info")
  private String quickInfo;

  @OneToOne
  @MapsId
  @JoinColumn(name = "id")
  private User user;

  @JsonIgnoreProperties(value = { "baseInfo", "rankGroup" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private RankUser myRank;

  @JsonIgnoreProperties(
    value = {
      "historyUpdates",
      "classInfo",
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
      "createdBy",
      "modifiedBy",
      "owner",
      "permissions",
    },
    allowSetters = true
  )
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo baseInfo;

  @OneToMany(mappedBy = "createdBy")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = {
      "historyUpdates",
      "classInfo",
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
      "createdBy",
      "modifiedBy",
      "owner",
      "permissions",
    },
    allowSetters = true
  )
  private Set<BaseInfo> myBaseInfoCreateds = new HashSet<>();

  @OneToMany(mappedBy = "modifiedBy")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = {
      "historyUpdates",
      "classInfo",
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
      "createdBy",
      "modifiedBy",
      "owner",
      "permissions",
    },
    allowSetters = true
  )
  private Set<BaseInfo> myBaseInfoModifieds = new HashSet<>();

  @OneToMany(mappedBy = "owner")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = {
      "historyUpdates",
      "classInfo",
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
      "createdBy",
      "modifiedBy",
      "owner",
      "permissions",
    },
    allowSetters = true
  )
  private Set<BaseInfo> ownerOfs = new HashSet<>();

  @OneToMany(mappedBy = "masterUser")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "masterUser" }, allowSetters = true)
  private Set<Permission> permissions = new HashSet<>();

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JoinTable(
    name = "rel_master_user__topic_interest",
    joinColumns = @JoinColumn(name = "master_user_id"),
    inverseJoinColumns = @JoinColumn(name = "topic_interest_id")
  )
  @JsonIgnoreProperties(value = { "baseInfo", "posts", "pagePosts", "groupPosts", "masterUsers" }, allowSetters = true)
  private Set<TopicInterest> topicInterests = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MasterUser id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public MasterUser uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getFullName() {
    return this.fullName;
  }

  public MasterUser fullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getNickname() {
    return this.nickname;
  }

  public MasterUser nickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getAvatar() {
    return this.avatar;
  }

  public MasterUser avatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getQuickInfo() {
    return this.quickInfo;
  }

  public MasterUser quickInfo(String quickInfo) {
    this.quickInfo = quickInfo;
    return this;
  }

  public void setQuickInfo(String quickInfo) {
    this.quickInfo = quickInfo;
  }

  public User getUser() {
    return this.user;
  }

  public MasterUser user(User user) {
    this.setUser(user);
    return this;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public RankUser getMyRank() {
    return this.myRank;
  }

  public MasterUser myRank(RankUser rankUser) {
    this.setMyRank(rankUser);
    return this;
  }

  public void setMyRank(RankUser rankUser) {
    this.myRank = rankUser;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public MasterUser baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Set<BaseInfo> getMyBaseInfoCreateds() {
    return this.myBaseInfoCreateds;
  }

  public MasterUser myBaseInfoCreateds(Set<BaseInfo> baseInfos) {
    this.setMyBaseInfoCreateds(baseInfos);
    return this;
  }

  public MasterUser addMyBaseInfoCreated(BaseInfo baseInfo) {
    this.myBaseInfoCreateds.add(baseInfo);
    baseInfo.setCreatedBy(this);
    return this;
  }

  public MasterUser removeMyBaseInfoCreated(BaseInfo baseInfo) {
    this.myBaseInfoCreateds.remove(baseInfo);
    baseInfo.setCreatedBy(null);
    return this;
  }

  public void setMyBaseInfoCreateds(Set<BaseInfo> baseInfos) {
    if (this.myBaseInfoCreateds != null) {
      this.myBaseInfoCreateds.forEach(i -> i.setCreatedBy(null));
    }
    if (baseInfos != null) {
      baseInfos.forEach(i -> i.setCreatedBy(this));
    }
    this.myBaseInfoCreateds = baseInfos;
  }

  public Set<BaseInfo> getMyBaseInfoModifieds() {
    return this.myBaseInfoModifieds;
  }

  public MasterUser myBaseInfoModifieds(Set<BaseInfo> baseInfos) {
    this.setMyBaseInfoModifieds(baseInfos);
    return this;
  }

  public MasterUser addMyBaseInfoModified(BaseInfo baseInfo) {
    this.myBaseInfoModifieds.add(baseInfo);
    baseInfo.setModifiedBy(this);
    return this;
  }

  public MasterUser removeMyBaseInfoModified(BaseInfo baseInfo) {
    this.myBaseInfoModifieds.remove(baseInfo);
    baseInfo.setModifiedBy(null);
    return this;
  }

  public void setMyBaseInfoModifieds(Set<BaseInfo> baseInfos) {
    if (this.myBaseInfoModifieds != null) {
      this.myBaseInfoModifieds.forEach(i -> i.setModifiedBy(null));
    }
    if (baseInfos != null) {
      baseInfos.forEach(i -> i.setModifiedBy(this));
    }
    this.myBaseInfoModifieds = baseInfos;
  }

  public Set<BaseInfo> getOwnerOfs() {
    return this.ownerOfs;
  }

  public MasterUser ownerOfs(Set<BaseInfo> baseInfos) {
    this.setOwnerOfs(baseInfos);
    return this;
  }

  public MasterUser addOwnerOf(BaseInfo baseInfo) {
    this.ownerOfs.add(baseInfo);
    baseInfo.setOwner(this);
    return this;
  }

  public MasterUser removeOwnerOf(BaseInfo baseInfo) {
    this.ownerOfs.remove(baseInfo);
    baseInfo.setOwner(null);
    return this;
  }

  public void setOwnerOfs(Set<BaseInfo> baseInfos) {
    if (this.ownerOfs != null) {
      this.ownerOfs.forEach(i -> i.setOwner(null));
    }
    if (baseInfos != null) {
      baseInfos.forEach(i -> i.setOwner(this));
    }
    this.ownerOfs = baseInfos;
  }

  public Set<Permission> getPermissions() {
    return this.permissions;
  }

  public MasterUser permissions(Set<Permission> permissions) {
    this.setPermissions(permissions);
    return this;
  }

  public MasterUser addPermission(Permission permission) {
    this.permissions.add(permission);
    permission.setMasterUser(this);
    return this;
  }

  public MasterUser removePermission(Permission permission) {
    this.permissions.remove(permission);
    permission.setMasterUser(null);
    return this;
  }

  public void setPermissions(Set<Permission> permissions) {
    if (this.permissions != null) {
      this.permissions.forEach(i -> i.setMasterUser(null));
    }
    if (permissions != null) {
      permissions.forEach(i -> i.setMasterUser(this));
    }
    this.permissions = permissions;
  }

  public Set<TopicInterest> getTopicInterests() {
    return this.topicInterests;
  }

  public MasterUser topicInterests(Set<TopicInterest> topicInterests) {
    this.setTopicInterests(topicInterests);
    return this;
  }

  public MasterUser addTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.add(topicInterest);
    topicInterest.getMasterUsers().add(this);
    return this;
  }

  public MasterUser removeTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.remove(topicInterest);
    topicInterest.getMasterUsers().remove(this);
    return this;
  }

  public void setTopicInterests(Set<TopicInterest> topicInterests) {
    this.topicInterests = topicInterests;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MasterUser)) {
      return false;
    }
    return id != null && id.equals(((MasterUser) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MasterUser{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", nickname='" + getNickname() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", quickInfo='" + getQuickInfo() + "'" +
            "}";
    }
}
