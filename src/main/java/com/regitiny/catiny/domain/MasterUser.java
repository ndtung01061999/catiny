package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
   * quickInfo      : @type Json -> thông tin nhanh về người dùng này dùng trong giới thiệu sơ khi chỉ chuột vào người dùng
   */
  @Lob
  @Column(name = "quick_info")
  private String quickInfo;

  @OneToOne
  @MapsId
  @JoinColumn(name = "id")
  private User user;

  @JsonIgnoreProperties(value = { "baseInfo" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private UserProfile myProfile;

  @JsonIgnoreProperties(value = { "baseInfo", "deviceStatuses" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private AccountStatus myAccountStatus;

  @JsonIgnoreProperties(value = { "baseInfo", "rankGroup" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private RankUser myRank;

  @JsonIgnoreProperties(value = { "fileInfo", "baseInfo", "imageProcesseds", "imageOriginal", "event", "albums" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private Image avatar;

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

  @OneToMany(mappedBy = "masterUser")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "profile", "baseInfo", "myPostInPages", "masterUser", "topicInterests" }, allowSetters = true)
  private Set<PagePost> myPages = new HashSet<>();

  @OneToMany(mappedBy = "masterUser")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "masterUser" }, allowSetters = true)
  private Set<FileInfo> myFiles = new HashSet<>();

  @OneToMany(mappedBy = "masterUser")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "masterUser" }, allowSetters = true)
  private Set<Notification> myNotifications = new HashSet<>();

  @OneToMany(mappedBy = "masterUser")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "friendDetails", "masterUser" }, allowSetters = true)
  private Set<Friend> myFriends = new HashSet<>();

  @OneToMany(mappedBy = "masterUser")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "followUserDetails", "masterUser" }, allowSetters = true)
  private Set<FollowUser> myFollowUsers = new HashSet<>();

  @OneToMany(mappedBy = "masterUser")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "followGroupDetails", "masterUser" }, allowSetters = true)
  private Set<FollowGroup> myFollowGroups = new HashSet<>();

  @OneToMany(mappedBy = "masterUser")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "followPageDetails", "masterUser" }, allowSetters = true)
  private Set<FollowPage> myFollowPages = new HashSet<>();

  @OneToMany(mappedBy = "masterUser")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "post", "masterUser" }, allowSetters = true)
  private Set<NewsFeed> myNewsFeeds = new HashSet<>();

  @OneToMany(mappedBy = "masterUser")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "masterUser" }, allowSetters = true)
  private Set<TodoList> myTodoLists = new HashSet<>();

  @OneToMany(mappedBy = "poster")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = {
      "baseInfo",
      "postComments",
      "postLikes",
      "postShareChildren",
      "groupPost",
      "pagePost",
      "postShareParent",
      "poster",
      "newsFeeds",
      "topicInterests",
    },
    allowSetters = true
  )
  private Set<Post> myPosts = new HashSet<>();

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JoinTable(
    name = "rel_master_user__my_group_post",
    joinColumns = @JoinColumn(name = "master_user_id"),
    inverseJoinColumns = @JoinColumn(name = "my_group_post_id")
  )
  @JsonIgnoreProperties(value = { "profile", "baseInfo", "myPostInGroups", "topicInterests", "userInGroups" }, allowSetters = true)
  private Set<GroupPost> myGroupPosts = new HashSet<>();

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JoinTable(
    name = "rel_master_user__message_group",
    joinColumns = @JoinColumn(name = "master_user_id"),
    inverseJoinColumns = @JoinColumn(name = "message_group_id")
  )
  @JsonIgnoreProperties(value = { "baseInfo", "messageContents", "masterUsers" }, allowSetters = true)
  private Set<MessageGroup> messageGroups = new HashSet<>();

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JoinTable(
    name = "rel_master_user__topic_interest",
    joinColumns = @JoinColumn(name = "master_user_id"),
    inverseJoinColumns = @JoinColumn(name = "topic_interest_id")
  )
  @JsonIgnoreProperties(value = { "baseInfo", "posts", "pagePosts", "groupPosts", "masterUsers" }, allowSetters = true)
  private Set<TopicInterest> topicInterests = new HashSet<>();

  @OneToMany(mappedBy = "userLike")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "userLike", "post" }, allowSetters = true)
  private Set<PostLike> myLikes = new HashSet<>();

  @OneToMany(mappedBy = "userComment")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "commentReplies", "userComment", "post", "commentParent" }, allowSetters = true)
  private Set<PostComment> myComments = new HashSet<>();

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

  public UserProfile getMyProfile() {
    return this.myProfile;
  }

  public MasterUser myProfile(UserProfile userProfile) {
    this.setMyProfile(userProfile);
    return this;
  }

  public void setMyProfile(UserProfile userProfile) {
    this.myProfile = userProfile;
  }

  public AccountStatus getMyAccountStatus() {
    return this.myAccountStatus;
  }

  public MasterUser myAccountStatus(AccountStatus accountStatus) {
    this.setMyAccountStatus(accountStatus);
    return this;
  }

  public void setMyAccountStatus(AccountStatus accountStatus) {
    this.myAccountStatus = accountStatus;
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

  public Image getAvatar() {
    return this.avatar;
  }

  public MasterUser avatar(Image image) {
    this.setAvatar(image);
    return this;
  }

  public void setAvatar(Image image) {
    this.avatar = image;
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

  public Set<PagePost> getMyPages() {
    return this.myPages;
  }

  public MasterUser myPages(Set<PagePost> pagePosts) {
    this.setMyPages(pagePosts);
    return this;
  }

  public MasterUser addMyPage(PagePost pagePost) {
    this.myPages.add(pagePost);
    pagePost.setMasterUser(this);
    return this;
  }

  public MasterUser removeMyPage(PagePost pagePost) {
    this.myPages.remove(pagePost);
    pagePost.setMasterUser(null);
    return this;
  }

  public void setMyPages(Set<PagePost> pagePosts) {
    if (this.myPages != null) {
      this.myPages.forEach(i -> i.setMasterUser(null));
    }
    if (pagePosts != null) {
      pagePosts.forEach(i -> i.setMasterUser(this));
    }
    this.myPages = pagePosts;
  }

  public Set<FileInfo> getMyFiles() {
    return this.myFiles;
  }

  public MasterUser myFiles(Set<FileInfo> fileInfos) {
    this.setMyFiles(fileInfos);
    return this;
  }

  public MasterUser addMyFile(FileInfo fileInfo) {
    this.myFiles.add(fileInfo);
    fileInfo.setMasterUser(this);
    return this;
  }

  public MasterUser removeMyFile(FileInfo fileInfo) {
    this.myFiles.remove(fileInfo);
    fileInfo.setMasterUser(null);
    return this;
  }

  public void setMyFiles(Set<FileInfo> fileInfos) {
    if (this.myFiles != null) {
      this.myFiles.forEach(i -> i.setMasterUser(null));
    }
    if (fileInfos != null) {
      fileInfos.forEach(i -> i.setMasterUser(this));
    }
    this.myFiles = fileInfos;
  }

  public Set<Notification> getMyNotifications() {
    return this.myNotifications;
  }

  public MasterUser myNotifications(Set<Notification> notifications) {
    this.setMyNotifications(notifications);
    return this;
  }

  public MasterUser addMyNotification(Notification notification) {
    this.myNotifications.add(notification);
    notification.setMasterUser(this);
    return this;
  }

  public MasterUser removeMyNotification(Notification notification) {
    this.myNotifications.remove(notification);
    notification.setMasterUser(null);
    return this;
  }

  public void setMyNotifications(Set<Notification> notifications) {
    if (this.myNotifications != null) {
      this.myNotifications.forEach(i -> i.setMasterUser(null));
    }
    if (notifications != null) {
      notifications.forEach(i -> i.setMasterUser(this));
    }
    this.myNotifications = notifications;
  }

  public Set<Friend> getMyFriends() {
    return this.myFriends;
  }

  public MasterUser myFriends(Set<Friend> friends) {
    this.setMyFriends(friends);
    return this;
  }

  public MasterUser addMyFriend(Friend friend) {
    this.myFriends.add(friend);
    friend.setMasterUser(this);
    return this;
  }

  public MasterUser removeMyFriend(Friend friend) {
    this.myFriends.remove(friend);
    friend.setMasterUser(null);
    return this;
  }

  public void setMyFriends(Set<Friend> friends) {
    if (this.myFriends != null) {
      this.myFriends.forEach(i -> i.setMasterUser(null));
    }
    if (friends != null) {
      friends.forEach(i -> i.setMasterUser(this));
    }
    this.myFriends = friends;
  }

  public Set<FollowUser> getMyFollowUsers() {
    return this.myFollowUsers;
  }

  public MasterUser myFollowUsers(Set<FollowUser> followUsers) {
    this.setMyFollowUsers(followUsers);
    return this;
  }

  public MasterUser addMyFollowUser(FollowUser followUser) {
    this.myFollowUsers.add(followUser);
    followUser.setMasterUser(this);
    return this;
  }

  public MasterUser removeMyFollowUser(FollowUser followUser) {
    this.myFollowUsers.remove(followUser);
    followUser.setMasterUser(null);
    return this;
  }

  public void setMyFollowUsers(Set<FollowUser> followUsers) {
    if (this.myFollowUsers != null) {
      this.myFollowUsers.forEach(i -> i.setMasterUser(null));
    }
    if (followUsers != null) {
      followUsers.forEach(i -> i.setMasterUser(this));
    }
    this.myFollowUsers = followUsers;
  }

  public Set<FollowGroup> getMyFollowGroups() {
    return this.myFollowGroups;
  }

  public MasterUser myFollowGroups(Set<FollowGroup> followGroups) {
    this.setMyFollowGroups(followGroups);
    return this;
  }

  public MasterUser addMyFollowGroup(FollowGroup followGroup) {
    this.myFollowGroups.add(followGroup);
    followGroup.setMasterUser(this);
    return this;
  }

  public MasterUser removeMyFollowGroup(FollowGroup followGroup) {
    this.myFollowGroups.remove(followGroup);
    followGroup.setMasterUser(null);
    return this;
  }

  public void setMyFollowGroups(Set<FollowGroup> followGroups) {
    if (this.myFollowGroups != null) {
      this.myFollowGroups.forEach(i -> i.setMasterUser(null));
    }
    if (followGroups != null) {
      followGroups.forEach(i -> i.setMasterUser(this));
    }
    this.myFollowGroups = followGroups;
  }

  public Set<FollowPage> getMyFollowPages() {
    return this.myFollowPages;
  }

  public MasterUser myFollowPages(Set<FollowPage> followPages) {
    this.setMyFollowPages(followPages);
    return this;
  }

  public MasterUser addMyFollowPage(FollowPage followPage) {
    this.myFollowPages.add(followPage);
    followPage.setMasterUser(this);
    return this;
  }

  public MasterUser removeMyFollowPage(FollowPage followPage) {
    this.myFollowPages.remove(followPage);
    followPage.setMasterUser(null);
    return this;
  }

  public void setMyFollowPages(Set<FollowPage> followPages) {
    if (this.myFollowPages != null) {
      this.myFollowPages.forEach(i -> i.setMasterUser(null));
    }
    if (followPages != null) {
      followPages.forEach(i -> i.setMasterUser(this));
    }
    this.myFollowPages = followPages;
  }

  public Set<NewsFeed> getMyNewsFeeds() {
    return this.myNewsFeeds;
  }

  public MasterUser myNewsFeeds(Set<NewsFeed> newsFeeds) {
    this.setMyNewsFeeds(newsFeeds);
    return this;
  }

  public MasterUser addMyNewsFeed(NewsFeed newsFeed) {
    this.myNewsFeeds.add(newsFeed);
    newsFeed.setMasterUser(this);
    return this;
  }

  public MasterUser removeMyNewsFeed(NewsFeed newsFeed) {
    this.myNewsFeeds.remove(newsFeed);
    newsFeed.setMasterUser(null);
    return this;
  }

  public void setMyNewsFeeds(Set<NewsFeed> newsFeeds) {
    if (this.myNewsFeeds != null) {
      this.myNewsFeeds.forEach(i -> i.setMasterUser(null));
    }
    if (newsFeeds != null) {
      newsFeeds.forEach(i -> i.setMasterUser(this));
    }
    this.myNewsFeeds = newsFeeds;
  }

  public Set<TodoList> getMyTodoLists() {
    return this.myTodoLists;
  }

  public MasterUser myTodoLists(Set<TodoList> todoLists) {
    this.setMyTodoLists(todoLists);
    return this;
  }

  public MasterUser addMyTodoList(TodoList todoList) {
    this.myTodoLists.add(todoList);
    todoList.setMasterUser(this);
    return this;
  }

  public MasterUser removeMyTodoList(TodoList todoList) {
    this.myTodoLists.remove(todoList);
    todoList.setMasterUser(null);
    return this;
  }

  public void setMyTodoLists(Set<TodoList> todoLists) {
    if (this.myTodoLists != null) {
      this.myTodoLists.forEach(i -> i.setMasterUser(null));
    }
    if (todoLists != null) {
      todoLists.forEach(i -> i.setMasterUser(this));
    }
    this.myTodoLists = todoLists;
  }

  public Set<Post> getMyPosts() {
    return this.myPosts;
  }

  public MasterUser myPosts(Set<Post> posts) {
    this.setMyPosts(posts);
    return this;
  }

  public MasterUser addMyPost(Post post) {
    this.myPosts.add(post);
    post.setPoster(this);
    return this;
  }

  public MasterUser removeMyPost(Post post) {
    this.myPosts.remove(post);
    post.setPoster(null);
    return this;
  }

  public void setMyPosts(Set<Post> posts) {
    if (this.myPosts != null) {
      this.myPosts.forEach(i -> i.setPoster(null));
    }
    if (posts != null) {
      posts.forEach(i -> i.setPoster(this));
    }
    this.myPosts = posts;
  }

  public Set<GroupPost> getMyGroupPosts() {
    return this.myGroupPosts;
  }

  public MasterUser myGroupPosts(Set<GroupPost> groupPosts) {
    this.setMyGroupPosts(groupPosts);
    return this;
  }

  public MasterUser addMyGroupPost(GroupPost groupPost) {
    this.myGroupPosts.add(groupPost);
    groupPost.getUserInGroups().add(this);
    return this;
  }

  public MasterUser removeMyGroupPost(GroupPost groupPost) {
    this.myGroupPosts.remove(groupPost);
    groupPost.getUserInGroups().remove(this);
    return this;
  }

  public void setMyGroupPosts(Set<GroupPost> groupPosts) {
    this.myGroupPosts = groupPosts;
  }

  public Set<MessageGroup> getMessageGroups() {
    return this.messageGroups;
  }

  public MasterUser messageGroups(Set<MessageGroup> messageGroups) {
    this.setMessageGroups(messageGroups);
    return this;
  }

  public MasterUser addMessageGroup(MessageGroup messageGroup) {
    this.messageGroups.add(messageGroup);
    messageGroup.getMasterUsers().add(this);
    return this;
  }

  public MasterUser removeMessageGroup(MessageGroup messageGroup) {
    this.messageGroups.remove(messageGroup);
    messageGroup.getMasterUsers().remove(this);
    return this;
  }

  public void setMessageGroups(Set<MessageGroup> messageGroups) {
    this.messageGroups = messageGroups;
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

  public Set<PostLike> getMyLikes() {
    return this.myLikes;
  }

  public MasterUser myLikes(Set<PostLike> postLikes) {
    this.setMyLikes(postLikes);
    return this;
  }

  public MasterUser addMyLike(PostLike postLike) {
    this.myLikes.add(postLike);
    postLike.setUserLike(this);
    return this;
  }

  public MasterUser removeMyLike(PostLike postLike) {
    this.myLikes.remove(postLike);
    postLike.setUserLike(null);
    return this;
  }

  public void setMyLikes(Set<PostLike> postLikes) {
    if (this.myLikes != null) {
      this.myLikes.forEach(i -> i.setUserLike(null));
    }
    if (postLikes != null) {
      postLikes.forEach(i -> i.setUserLike(this));
    }
    this.myLikes = postLikes;
  }

  public Set<PostComment> getMyComments() {
    return this.myComments;
  }

  public MasterUser myComments(Set<PostComment> postComments) {
    this.setMyComments(postComments);
    return this;
  }

  public MasterUser addMyComment(PostComment postComment) {
    this.myComments.add(postComment);
    postComment.setUserComment(this);
    return this;
  }

  public MasterUser removeMyComment(PostComment postComment) {
    this.myComments.remove(postComment);
    postComment.setUserComment(null);
    return this;
  }

  public void setMyComments(Set<PostComment> postComments) {
    if (this.myComments != null) {
      this.myComments.forEach(i -> i.setUserComment(null));
    }
    if (postComments != null) {
      postComments.forEach(i -> i.setUserComment(this));
    }
    this.myComments = postComments;
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
            ", quickInfo='" + getQuickInfo() + "'" +
            "}";
    }
}
