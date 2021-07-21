package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.ProcessStatus;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * BaseInfo
 */
@Entity
@Table(name = "base_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "baseinfo")
@GeneratedByJHipster
public class BaseInfo implements Serializable
{

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
   * processStatus *: @defaultValue( DONE ) -> tình trạng xử lý sử dụng trong phê duyệt
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "process_status")
  private ProcessStatus processStatus;

  /**
   * modifiedClass *: thực hiện sửa đổi bản ghi này ở service class nào
   */
  @Column(name = "modified_class")
  private String modifiedClass;

  /**
   * createdDate *  : thời gian tạo ra bản ghi này (lần đầu tiên)
   */
  @Column(name = "created_date")
  private Instant createdDate;

  /**
   * modifiedDate * : thời gian sửa bản ghi này
   */
  @Column(name = "modified_date")
  private Instant modifiedDate;

  /**
   * notes *        : @type Json -> chú thích thêm hoặc những lưu ý cho bản ghi này ở dưới dạng Json\"
   */
  @Lob
  @Column(name = "notes")
  private String notes;

  /**
   * deleted *      : @defaultValue( false ) -> đánh dấu là đã xóa
   */
  @Column(name = "deleted")
  private Boolean deleted;

  /**
   * priorityIndex *: chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)
   */
  @Column(name = "priority_index")
  private Long priorityIndex;

  /**
   * countUse *     : đếm số lần truy cập vào bản ghi này để xem sửa xóa
   */
  @Column(name = "count_use")
  private Long countUse;

  @OneToMany(mappedBy = "baseInfo")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = {"baseInfo"}, allowSetters = true)
  private Set<HistoryUpdate> historyUpdates = new HashSet<>();

  @ManyToOne
  @JsonIgnoreProperties(value = {"baseInfos"}, allowSetters = true)
  private ClassInfo classInfo;

  @JsonIgnoreProperties(value = {"baseInfo"}, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private UserProfile userProfile;

  @JsonIgnoreProperties(value = {"baseInfo", "deviceStatuses"}, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private AccountStatus accountStatus;

  @JsonIgnoreProperties(value = { "baseInfo", "accountStatus" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private DeviceStatus deviceStatus;

  @JsonIgnoreProperties(value = { "baseInfo", "friendDetails" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private Friend friend;

  @JsonIgnoreProperties(value = { "baseInfo", "followUserDetails" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private FollowUser followUser;

  @JsonIgnoreProperties(value = { "baseInfo", "followGroupDetails" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private FollowGroup followGroup;

  @JsonIgnoreProperties(value = { "baseInfo", "followPageDetails" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private FollowPage followPage;

  @JsonIgnoreProperties(value = { "baseInfo" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private FileInfo fileInfo;

  @JsonIgnoreProperties(value = { "profile", "baseInfo", "myPostInPages", "topicInterests" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private PagePost pagePost;

  @JsonIgnoreProperties(value = { "baseInfo", "page" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private PageProfile pageProfile;

  @JsonIgnoreProperties(value = { "profile", "baseInfo", "myPostInGroups", "topicInterests" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private GroupPost groupPost;

  @JsonIgnoreProperties(
    value = {
      "baseInfo", "comments", "likes", "postShareChildren", "groupPost", "pagePost", "postShareParent", "newsFeeds", "topicInterests",
    },
    allowSetters = true
  )
  @OneToOne(mappedBy = "baseInfo")
  private Post post;

  @JsonIgnoreProperties(value = { "baseInfo", "likes", "commentReplies", "post", "commentParent" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private PostComment postComment;

  @JsonIgnoreProperties(value = { "baseInfo", "post", "postComment" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private PostLike postLike;

  @JsonIgnoreProperties(value = { "baseInfo", "group" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private GroupProfile groupProfile;

  @JsonIgnoreProperties(value = { "baseInfo", "post" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private NewsFeed newsFeed;

  @JsonIgnoreProperties(value = { "baseInfo", "messageContents" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private MessageGroup messageGroup;

  @JsonIgnoreProperties(value = { "baseInfo", "messageGroup" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private MessageContent messageContent;

  @JsonIgnoreProperties(value = { "baseInfo", "rankGroup" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private RankUser rankUser;

  @JsonIgnoreProperties(value = { "baseInfo", "rankUsers" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private RankGroup rankGroup;

  @JsonIgnoreProperties(value = { "baseInfo" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private Notification notification;

  @JsonIgnoreProperties(value = { "baseInfo", "images" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private Album album;

  @JsonIgnoreProperties(value = { "fileInfo", "baseInfo", "videoProcesseds", "videoStream", "videoOriginal" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private Video video;

  @JsonIgnoreProperties(value = { "fileInfo", "baseInfo", "imageProcesseds", "imageOriginal", "albums" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private Image image;

  @JsonIgnoreProperties(value = { "video", "baseInfo", "videoLiveStreamBuffers" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private VideoStream videoStream;

  @JsonIgnoreProperties(value = { "baseInfo", "videoStream" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private VideoLiveStreamBuffer videoLiveStreamBuffer;

  @JsonIgnoreProperties(value = { "baseInfo", "posts", "pagePosts", "groupPosts", "masterUsers" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private TopicInterest topicInterest;

  @JsonIgnoreProperties(value = { "baseInfo" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private TodoList todoList;

  @JsonIgnoreProperties(value = { "baseInfo" }, allowSetters = true)
  @OneToOne(mappedBy = "baseInfo")
  private Event event;

  @ManyToOne
  @JsonIgnoreProperties(
    value = { "user", "myRank", "baseInfo", "myBaseInfoCreateds", "myBaseInfoModifieds", "ownerOfs", "permissions", "topicInterests" },
    allowSetters = true
  )
  private MasterUser createdBy;

  @ManyToOne
  @JsonIgnoreProperties(
    value = { "user", "myRank", "baseInfo", "myBaseInfoCreateds", "myBaseInfoModifieds", "ownerOfs", "permissions", "topicInterests" },
    allowSetters = true
  )
  private MasterUser modifiedBy;

  @ManyToOne
  @JsonIgnoreProperties(
    value = { "user", "myRank", "baseInfo", "myBaseInfoCreateds", "myBaseInfoModifieds", "ownerOfs", "permissions", "topicInterests" },
    allowSetters = true
  )
  private MasterUser owner;

  @OneToMany(mappedBy = "baseInfo")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "masterUser" }, allowSetters = true)
  private Set<Permission> permissions = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public BaseInfo id(Long id)
  {
    this.id = id;
    return this;
  }

  public UUID getUuid()
  {
    return this.uuid;
  }

  public void setUuid(UUID uuid)
  {
    this.uuid = uuid;
  }

  public BaseInfo uuid(UUID uuid)
  {
    this.uuid = uuid;
    return this;
  }

  public ProcessStatus getProcessStatus()
  {
    return this.processStatus;
  }

  public BaseInfo processStatus(ProcessStatus processStatus)
  {
    this.processStatus = processStatus;
    return this;
  }

  public void setProcessStatus(ProcessStatus processStatus) {
    this.processStatus = processStatus;
  }

  public String getModifiedClass() {
    return this.modifiedClass;
  }

  public BaseInfo modifiedClass(String modifiedClass) {
    this.modifiedClass = modifiedClass;
    return this;
  }

  public void setModifiedClass(String modifiedClass) {
    this.modifiedClass = modifiedClass;
  }

  public Instant getCreatedDate() {
    return this.createdDate;
  }

  public BaseInfo createdDate(Instant createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

  public Instant getModifiedDate() {
    return this.modifiedDate;
  }

  public BaseInfo modifiedDate(Instant modifiedDate) {
    this.modifiedDate = modifiedDate;
    return this;
  }

  public void setModifiedDate(Instant modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public String getNotes() {
    return this.notes;
  }

  public BaseInfo notes(String notes) {
    this.notes = notes;
    return this;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Boolean getDeleted() {
    return this.deleted;
  }

  public BaseInfo deleted(Boolean deleted) {
    this.deleted = deleted;
    return this;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public Long getPriorityIndex() {
    return this.priorityIndex;
  }

  public BaseInfo priorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
    return this;
  }

  public void setPriorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
  }

  public Long getCountUse() {
    return this.countUse;
  }

  public BaseInfo countUse(Long countUse)
  {
    this.countUse = countUse;
    return this;
  }

  public void setCountUse(Long countUse)
  {
    this.countUse = countUse;
  }

  public Set<HistoryUpdate> getHistoryUpdates()
  {
    return this.historyUpdates;
  }

  public void setHistoryUpdates(Set<HistoryUpdate> historyUpdates)
  {
    if (this.historyUpdates != null)
    {
      this.historyUpdates.forEach(i -> i.setBaseInfo(null));
    }
    if (historyUpdates != null)
    {
      historyUpdates.forEach(i -> i.setBaseInfo(this));
    }
    this.historyUpdates = historyUpdates;
  }

  public BaseInfo historyUpdates(Set<HistoryUpdate> historyUpdates)
  {
    this.setHistoryUpdates(historyUpdates);
    return this;
  }

  public BaseInfo addHistoryUpdate(HistoryUpdate historyUpdate)
  {
    this.historyUpdates.add(historyUpdate);
    historyUpdate.setBaseInfo(this);
    return this;
  }

  public BaseInfo removeHistoryUpdate(HistoryUpdate historyUpdate)
  {
    this.historyUpdates.remove(historyUpdate);
    historyUpdate.setBaseInfo(null);
    return this;
  }

  public ClassInfo getClassInfo()
  {
    return this.classInfo;
  }

  public BaseInfo classInfo(ClassInfo classInfo)
  {
    this.setClassInfo(classInfo);
    return this;
  }

  public void setClassInfo(ClassInfo classInfo) {
    this.classInfo = classInfo;
  }

  public UserProfile getUserProfile() {
    return this.userProfile;
  }

  public BaseInfo userProfile(UserProfile userProfile) {
    this.setUserProfile(userProfile);
    return this;
  }

  public void setUserProfile(UserProfile userProfile) {
    if (this.userProfile != null) {
      this.userProfile.setBaseInfo(null);
    }
    if (userProfile != null) {
      userProfile.setBaseInfo(this);
    }
    this.userProfile = userProfile;
  }

  public AccountStatus getAccountStatus() {
    return this.accountStatus;
  }

  public BaseInfo accountStatus(AccountStatus accountStatus) {
    this.setAccountStatus(accountStatus);
    return this;
  }

  public void setAccountStatus(AccountStatus accountStatus) {
    if (this.accountStatus != null) {
      this.accountStatus.setBaseInfo(null);
    }
    if (accountStatus != null) {
      accountStatus.setBaseInfo(this);
    }
    this.accountStatus = accountStatus;
  }

  public DeviceStatus getDeviceStatus() {
    return this.deviceStatus;
  }

  public BaseInfo deviceStatus(DeviceStatus deviceStatus) {
    this.setDeviceStatus(deviceStatus);
    return this;
  }

  public void setDeviceStatus(DeviceStatus deviceStatus) {
    if (this.deviceStatus != null) {
      this.deviceStatus.setBaseInfo(null);
    }
    if (deviceStatus != null) {
      deviceStatus.setBaseInfo(this);
    }
    this.deviceStatus = deviceStatus;
  }

  public Friend getFriend() {
    return this.friend;
  }

  public BaseInfo friend(Friend friend) {
    this.setFriend(friend);
    return this;
  }

  public void setFriend(Friend friend) {
    if (this.friend != null) {
      this.friend.setBaseInfo(null);
    }
    if (friend != null) {
      friend.setBaseInfo(this);
    }
    this.friend = friend;
  }

  public FollowUser getFollowUser() {
    return this.followUser;
  }

  public BaseInfo followUser(FollowUser followUser) {
    this.setFollowUser(followUser);
    return this;
  }

  public void setFollowUser(FollowUser followUser) {
    if (this.followUser != null) {
      this.followUser.setBaseInfo(null);
    }
    if (followUser != null) {
      followUser.setBaseInfo(this);
    }
    this.followUser = followUser;
  }

  public FollowGroup getFollowGroup() {
    return this.followGroup;
  }

  public BaseInfo followGroup(FollowGroup followGroup) {
    this.setFollowGroup(followGroup);
    return this;
  }

  public void setFollowGroup(FollowGroup followGroup) {
    if (this.followGroup != null) {
      this.followGroup.setBaseInfo(null);
    }
    if (followGroup != null) {
      followGroup.setBaseInfo(this);
    }
    this.followGroup = followGroup;
  }

  public FollowPage getFollowPage() {
    return this.followPage;
  }

  public BaseInfo followPage(FollowPage followPage) {
    this.setFollowPage(followPage);
    return this;
  }

  public void setFollowPage(FollowPage followPage) {
    if (this.followPage != null) {
      this.followPage.setBaseInfo(null);
    }
    if (followPage != null) {
      followPage.setBaseInfo(this);
    }
    this.followPage = followPage;
  }

  public FileInfo getFileInfo() {
    return this.fileInfo;
  }

  public BaseInfo fileInfo(FileInfo fileInfo) {
    this.setFileInfo(fileInfo);
    return this;
  }

  public void setFileInfo(FileInfo fileInfo) {
    if (this.fileInfo != null) {
      this.fileInfo.setBaseInfo(null);
    }
    if (fileInfo != null) {
      fileInfo.setBaseInfo(this);
    }
    this.fileInfo = fileInfo;
  }

  public PagePost getPagePost() {
    return this.pagePost;
  }

  public BaseInfo pagePost(PagePost pagePost) {
    this.setPagePost(pagePost);
    return this;
  }

  public void setPagePost(PagePost pagePost) {
    if (this.pagePost != null) {
      this.pagePost.setBaseInfo(null);
    }
    if (pagePost != null) {
      pagePost.setBaseInfo(this);
    }
    this.pagePost = pagePost;
  }

  public PageProfile getPageProfile() {
    return this.pageProfile;
  }

  public BaseInfo pageProfile(PageProfile pageProfile) {
    this.setPageProfile(pageProfile);
    return this;
  }

  public void setPageProfile(PageProfile pageProfile) {
    if (this.pageProfile != null) {
      this.pageProfile.setBaseInfo(null);
    }
    if (pageProfile != null) {
      pageProfile.setBaseInfo(this);
    }
    this.pageProfile = pageProfile;
  }

  public GroupPost getGroupPost() {
    return this.groupPost;
  }

  public BaseInfo groupPost(GroupPost groupPost) {
    this.setGroupPost(groupPost);
    return this;
  }

  public void setGroupPost(GroupPost groupPost) {
    if (this.groupPost != null) {
      this.groupPost.setBaseInfo(null);
    }
    if (groupPost != null) {
      groupPost.setBaseInfo(this);
    }
    this.groupPost = groupPost;
  }

  public Post getPost() {
    return this.post;
  }

  public BaseInfo post(Post post) {
    this.setPost(post);
    return this;
  }

  public void setPost(Post post) {
    if (this.post != null) {
      this.post.setBaseInfo(null);
    }
    if (post != null) {
      post.setBaseInfo(this);
    }
    this.post = post;
  }

  public PostComment getPostComment() {
    return this.postComment;
  }

  public BaseInfo postComment(PostComment postComment) {
    this.setPostComment(postComment);
    return this;
  }

  public void setPostComment(PostComment postComment) {
    if (this.postComment != null) {
      this.postComment.setBaseInfo(null);
    }
    if (postComment != null) {
      postComment.setBaseInfo(this);
    }
    this.postComment = postComment;
  }

  public PostLike getPostLike() {
    return this.postLike;
  }

  public BaseInfo postLike(PostLike postLike) {
    this.setPostLike(postLike);
    return this;
  }

  public void setPostLike(PostLike postLike) {
    if (this.postLike != null) {
      this.postLike.setBaseInfo(null);
    }
    if (postLike != null) {
      postLike.setBaseInfo(this);
    }
    this.postLike = postLike;
  }

  public GroupProfile getGroupProfile() {
    return this.groupProfile;
  }

  public BaseInfo groupProfile(GroupProfile groupProfile) {
    this.setGroupProfile(groupProfile);
    return this;
  }

  public void setGroupProfile(GroupProfile groupProfile) {
    if (this.groupProfile != null) {
      this.groupProfile.setBaseInfo(null);
    }
    if (groupProfile != null) {
      groupProfile.setBaseInfo(this);
    }
    this.groupProfile = groupProfile;
  }

  public NewsFeed getNewsFeed() {
    return this.newsFeed;
  }

  public BaseInfo newsFeed(NewsFeed newsFeed) {
    this.setNewsFeed(newsFeed);
    return this;
  }

  public void setNewsFeed(NewsFeed newsFeed) {
    if (this.newsFeed != null) {
      this.newsFeed.setBaseInfo(null);
    }
    if (newsFeed != null) {
      newsFeed.setBaseInfo(this);
    }
    this.newsFeed = newsFeed;
  }

  public MessageGroup getMessageGroup() {
    return this.messageGroup;
  }

  public BaseInfo messageGroup(MessageGroup messageGroup) {
    this.setMessageGroup(messageGroup);
    return this;
  }

  public void setMessageGroup(MessageGroup messageGroup) {
    if (this.messageGroup != null) {
      this.messageGroup.setBaseInfo(null);
    }
    if (messageGroup != null) {
      messageGroup.setBaseInfo(this);
    }
    this.messageGroup = messageGroup;
  }

  public MessageContent getMessageContent() {
    return this.messageContent;
  }

  public BaseInfo messageContent(MessageContent messageContent) {
    this.setMessageContent(messageContent);
    return this;
  }

  public void setMessageContent(MessageContent messageContent) {
    if (this.messageContent != null) {
      this.messageContent.setBaseInfo(null);
    }
    if (messageContent != null) {
      messageContent.setBaseInfo(this);
    }
    this.messageContent = messageContent;
  }

  public RankUser getRankUser() {
    return this.rankUser;
  }

  public BaseInfo rankUser(RankUser rankUser) {
    this.setRankUser(rankUser);
    return this;
  }

  public void setRankUser(RankUser rankUser) {
    if (this.rankUser != null) {
      this.rankUser.setBaseInfo(null);
    }
    if (rankUser != null) {
      rankUser.setBaseInfo(this);
    }
    this.rankUser = rankUser;
  }

  public RankGroup getRankGroup() {
    return this.rankGroup;
  }

  public BaseInfo rankGroup(RankGroup rankGroup) {
    this.setRankGroup(rankGroup);
    return this;
  }

  public void setRankGroup(RankGroup rankGroup) {
    if (this.rankGroup != null) {
      this.rankGroup.setBaseInfo(null);
    }
    if (rankGroup != null) {
      rankGroup.setBaseInfo(this);
    }
    this.rankGroup = rankGroup;
  }

  public Notification getNotification() {
    return this.notification;
  }

  public BaseInfo notification(Notification notification) {
    this.setNotification(notification);
    return this;
  }

  public void setNotification(Notification notification) {
    if (this.notification != null) {
      this.notification.setBaseInfo(null);
    }
    if (notification != null) {
      notification.setBaseInfo(this);
    }
    this.notification = notification;
  }

  public Album getAlbum() {
    return this.album;
  }

  public BaseInfo album(Album album) {
    this.setAlbum(album);
    return this;
  }

  public void setAlbum(Album album) {
    if (this.album != null) {
      this.album.setBaseInfo(null);
    }
    if (album != null) {
      album.setBaseInfo(this);
    }
    this.album = album;
  }

  public Video getVideo() {
    return this.video;
  }

  public BaseInfo video(Video video) {
    this.setVideo(video);
    return this;
  }

  public void setVideo(Video video) {
    if (this.video != null) {
      this.video.setBaseInfo(null);
    }
    if (video != null) {
      video.setBaseInfo(this);
    }
    this.video = video;
  }

  public Image getImage() {
    return this.image;
  }

  public BaseInfo image(Image image) {
    this.setImage(image);
    return this;
  }

  public void setImage(Image image) {
    if (this.image != null) {
      this.image.setBaseInfo(null);
    }
    if (image != null) {
      image.setBaseInfo(this);
    }
    this.image = image;
  }

  public VideoStream getVideoStream() {
    return this.videoStream;
  }

  public BaseInfo videoStream(VideoStream videoStream) {
    this.setVideoStream(videoStream);
    return this;
  }

  public void setVideoStream(VideoStream videoStream) {
    if (this.videoStream != null) {
      this.videoStream.setBaseInfo(null);
    }
    if (videoStream != null) {
      videoStream.setBaseInfo(this);
    }
    this.videoStream = videoStream;
  }

  public VideoLiveStreamBuffer getVideoLiveStreamBuffer() {
    return this.videoLiveStreamBuffer;
  }

  public BaseInfo videoLiveStreamBuffer(VideoLiveStreamBuffer videoLiveStreamBuffer) {
    this.setVideoLiveStreamBuffer(videoLiveStreamBuffer);
    return this;
  }

  public void setVideoLiveStreamBuffer(VideoLiveStreamBuffer videoLiveStreamBuffer) {
    if (this.videoLiveStreamBuffer != null) {
      this.videoLiveStreamBuffer.setBaseInfo(null);
    }
    if (videoLiveStreamBuffer != null) {
      videoLiveStreamBuffer.setBaseInfo(this);
    }
    this.videoLiveStreamBuffer = videoLiveStreamBuffer;
  }

  public TopicInterest getTopicInterest() {
    return this.topicInterest;
  }

  public BaseInfo topicInterest(TopicInterest topicInterest) {
    this.setTopicInterest(topicInterest);
    return this;
  }

  public void setTopicInterest(TopicInterest topicInterest) {
    if (this.topicInterest != null) {
      this.topicInterest.setBaseInfo(null);
    }
    if (topicInterest != null) {
      topicInterest.setBaseInfo(this);
    }
    this.topicInterest = topicInterest;
  }

  public TodoList getTodoList() {
    return this.todoList;
  }

  public BaseInfo todoList(TodoList todoList) {
    this.setTodoList(todoList);
    return this;
  }

  public void setTodoList(TodoList todoList) {
    if (this.todoList != null) {
      this.todoList.setBaseInfo(null);
    }
    if (todoList != null) {
      todoList.setBaseInfo(this);
    }
    this.todoList = todoList;
  }

  public Event getEvent() {
    return this.event;
  }

  public BaseInfo event(Event event) {
    this.setEvent(event);
    return this;
  }

  public void setEvent(Event event) {
    if (this.event != null) {
      this.event.setBaseInfo(null);
    }
    if (event != null) {
      event.setBaseInfo(this);
    }
    this.event = event;
  }

  public MasterUser getCreatedBy() {
    return this.createdBy;
  }

  public BaseInfo createdBy(MasterUser masterUser) {
    this.setCreatedBy(masterUser);
    return this;
  }

  public void setCreatedBy(MasterUser masterUser) {
    this.createdBy = masterUser;
  }

  public MasterUser getModifiedBy() {
    return this.modifiedBy;
  }

  public BaseInfo modifiedBy(MasterUser masterUser) {
    this.setModifiedBy(masterUser);
    return this;
  }

  public void setModifiedBy(MasterUser masterUser) {
    this.modifiedBy = masterUser;
  }

  public MasterUser getOwner() {
    return this.owner;
  }

  public BaseInfo owner(MasterUser masterUser) {
    this.setOwner(masterUser);
    return this;
  }

  public void setOwner(MasterUser masterUser) {
    this.owner = masterUser;
  }

  public Set<Permission> getPermissions() {
    return this.permissions;
  }

  public BaseInfo permissions(Set<Permission> permissions) {
    this.setPermissions(permissions);
    return this;
  }

  public BaseInfo addPermission(Permission permission) {
    this.permissions.add(permission);
    permission.setBaseInfo(this);
    return this;
  }

  public BaseInfo removePermission(Permission permission) {
    this.permissions.remove(permission);
    permission.setBaseInfo(null);
    return this;
  }

  public void setPermissions(Set<Permission> permissions) {
    if (this.permissions != null) {
      this.permissions.forEach(i -> i.setBaseInfo(null));
    }
    if (permissions != null) {
      permissions.forEach(i -> i.setBaseInfo(this));
    }
    this.permissions = permissions;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseInfo)) {
      return false;
    }
    return id != null && id.equals(((BaseInfo) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "BaseInfo{" +
          "id=" + getId() +
          ", uuid='" + getUuid() + "'" +
          ", processStatus='" + getProcessStatus() + "'" +
          ", modifiedClass='" + getModifiedClass() + "'" +
          ", createdDate='" + getCreatedDate() + "'" +
          ", modifiedDate='" + getModifiedDate() + "'" +
          ", notes='" + getNotes() + "'" +
          ", deleted='" + getDeleted() + "'" +
          ", priorityIndex=" + getPriorityIndex() +
          ", countUse=" + getCountUse() +
          "}";
    }
}
