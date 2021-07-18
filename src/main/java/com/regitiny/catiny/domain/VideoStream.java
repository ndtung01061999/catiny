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
 * @what?            -> The VideoStream entity.\n@why?             ->\n@use-to           -> Lưu thông tin video ... khi đã kết thúc stream\n@commonly-used-in -> Sau khi kết thức stream thì video lưu lai cũng chỉ tương tự như một video thông thường\n\n@describe         ->
 */
@Entity
@Table(name = "video_stream")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "videostream")
@GeneratedByJHipster
public class VideoStream implements Serializable {

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

  @JsonIgnoreProperties(value = { "fileInfo", "baseInfo", "videoProcesseds", "videoStream", "videoOriginal" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private Video video;

  @JsonIgnoreProperties(
    value = {
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

  @OneToMany(mappedBy = "videoStream")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "videoStream" }, allowSetters = true)
  private Set<VideoLiveStreamBuffer> videoLiveStreamBuffers = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public VideoStream id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public VideoStream uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public Video getVideo() {
    return this.video;
  }

  public VideoStream video(Video video) {
    this.setVideo(video);
    return this;
  }

  public void setVideo(Video video) {
    this.video = video;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public VideoStream baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Set<VideoLiveStreamBuffer> getVideoLiveStreamBuffers() {
    return this.videoLiveStreamBuffers;
  }

  public VideoStream videoLiveStreamBuffers(Set<VideoLiveStreamBuffer> videoLiveStreamBuffers) {
    this.setVideoLiveStreamBuffers(videoLiveStreamBuffers);
    return this;
  }

  public VideoStream addVideoLiveStreamBuffer(VideoLiveStreamBuffer videoLiveStreamBuffer) {
    this.videoLiveStreamBuffers.add(videoLiveStreamBuffer);
    videoLiveStreamBuffer.setVideoStream(this);
    return this;
  }

  public VideoStream removeVideoLiveStreamBuffer(VideoLiveStreamBuffer videoLiveStreamBuffer) {
    this.videoLiveStreamBuffers.remove(videoLiveStreamBuffer);
    videoLiveStreamBuffer.setVideoStream(null);
    return this;
  }

  public void setVideoLiveStreamBuffers(Set<VideoLiveStreamBuffer> videoLiveStreamBuffers) {
    if (this.videoLiveStreamBuffers != null) {
      this.videoLiveStreamBuffers.forEach(i -> i.setVideoStream(null));
    }
    if (videoLiveStreamBuffers != null) {
      videoLiveStreamBuffers.forEach(i -> i.setVideoStream(this));
    }
    this.videoLiveStreamBuffers = videoLiveStreamBuffers;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof VideoStream)) {
      return false;
    }
    return id != null && id.equals(((VideoStream) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "VideoStream{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            "}";
    }
}
