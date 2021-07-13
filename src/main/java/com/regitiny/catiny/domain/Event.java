package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.EventType;
import java.io.Serializable;
import java.time.Instant;
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
 * @what?            -> The Event entity.\n@why?             ->\n@use-to           -> Lưu những sự kiện\n@commonly-used-in -> Tạo Sự kiện\n\n@describe         ->
 */
@Entity
@Table(name = "event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "event")
@GeneratedByJHipster
public class Event implements Serializable {

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

  @Column(name = "title")
  private String title;

  @Lob
  @Column(name = "content")
  private String content;

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private EventType type;

  @Lob
  @Column(name = "description")
  private String description;

  @Column(name = "start_time")
  private Instant startTime;

  @Column(name = "end_time")
  private Instant endTime;

  @Column(name = "tag_line")
  private String tagLine;

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

  @OneToMany(mappedBy = "event")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "fileInfo", "baseInfo", "imageProcesseds", "imageOriginal", "event", "albums" }, allowSetters = true)
  private Set<Image> otherImages = new HashSet<>();

  @OneToMany(mappedBy = "event")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "fileInfo", "baseInfo", "videoProcesseds", "videoStream", "videoOriginal", "event" }, allowSetters = true)
  private Set<Video> otherVideos = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Event id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public Event uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getTitle() {
    return this.title;
  }

  public Event title(String title) {
    this.title = title;
    return this;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return this.content;
  }

  public Event content(String content) {
    this.content = content;
    return this;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public EventType getType() {
    return this.type;
  }

  public Event type(EventType type) {
    this.type = type;
    return this;
  }

  public void setType(EventType type) {
    this.type = type;
  }

  public String getDescription() {
    return this.description;
  }

  public Event description(String description) {
    this.description = description;
    return this;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Instant getStartTime() {
    return this.startTime;
  }

  public Event startTime(Instant startTime) {
    this.startTime = startTime;
    return this;
  }

  public void setStartTime(Instant startTime) {
    this.startTime = startTime;
  }

  public Instant getEndTime() {
    return this.endTime;
  }

  public Event endTime(Instant endTime) {
    this.endTime = endTime;
    return this;
  }

  public void setEndTime(Instant endTime) {
    this.endTime = endTime;
  }

  public String getTagLine() {
    return this.tagLine;
  }

  public Event tagLine(String tagLine) {
    this.tagLine = tagLine;
    return this;
  }

  public void setTagLine(String tagLine) {
    this.tagLine = tagLine;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public Event baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Set<Image> getOtherImages() {
    return this.otherImages;
  }

  public Event otherImages(Set<Image> images) {
    this.setOtherImages(images);
    return this;
  }

  public Event addOtherImage(Image image) {
    this.otherImages.add(image);
    image.setEvent(this);
    return this;
  }

  public Event removeOtherImage(Image image) {
    this.otherImages.remove(image);
    image.setEvent(null);
    return this;
  }

  public void setOtherImages(Set<Image> images) {
    if (this.otherImages != null) {
      this.otherImages.forEach(i -> i.setEvent(null));
    }
    if (images != null) {
      images.forEach(i -> i.setEvent(this));
    }
    this.otherImages = images;
  }

  public Set<Video> getOtherVideos() {
    return this.otherVideos;
  }

  public Event otherVideos(Set<Video> videos) {
    this.setOtherVideos(videos);
    return this;
  }

  public Event addOtherVideo(Video video) {
    this.otherVideos.add(video);
    video.setEvent(this);
    return this;
  }

  public Event removeOtherVideo(Video video) {
    this.otherVideos.remove(video);
    video.setEvent(null);
    return this;
  }

  public void setOtherVideos(Set<Video> videos) {
    if (this.otherVideos != null) {
      this.otherVideos.forEach(i -> i.setEvent(null));
    }
    if (videos != null) {
      videos.forEach(i -> i.setEvent(this));
    }
    this.otherVideos = videos;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Event)) {
      return false;
    }
    return id != null && id.equals(((Event) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Event{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", type='" + getType() + "'" +
            ", description='" + getDescription() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", tagLine='" + getTagLine() + "'" +
            "}";
    }
}
