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
 * @what?            -> The Image entity.\n@why?             ->\n@use-to           -> Lưu thông tin Ảnh mà người dùng upload lên\n@commonly-used-in ->\n\n@describe         ->
 */
@Entity
@Table(name = "image")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "image")
@GeneratedByJHipster
public class Image implements Serializable {

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

  @Column(name = "name")
  private String name;

  @JsonIgnoreProperties(value = { "baseInfo", "masterUser" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private FileInfo fileInfo;

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

  @OneToMany(mappedBy = "imageOriginal")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "fileInfo", "baseInfo", "imageProcesseds", "imageOriginal", "event", "albums" }, allowSetters = true)
  private Set<Image> imageProcesseds = new HashSet<>();

  @ManyToOne
  @JsonIgnoreProperties(value = { "fileInfo", "baseInfo", "imageProcesseds", "imageOriginal", "event", "albums" }, allowSetters = true)
  private Image imageOriginal;

  @ManyToOne
  @JsonIgnoreProperties(value = { "baseInfo", "otherImages", "otherVideos" }, allowSetters = true)
  private Event event;

  @ManyToMany(mappedBy = "images")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "images" }, allowSetters = true)
  private Set<Album> albums = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Image id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public Image uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return this.name;
  }

  public Image name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FileInfo getFileInfo() {
    return this.fileInfo;
  }

  public Image fileInfo(FileInfo fileInfo) {
    this.setFileInfo(fileInfo);
    return this;
  }

  public void setFileInfo(FileInfo fileInfo) {
    this.fileInfo = fileInfo;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public Image baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Set<Image> getImageProcesseds() {
    return this.imageProcesseds;
  }

  public Image imageProcesseds(Set<Image> images) {
    this.setImageProcesseds(images);
    return this;
  }

  public Image addImageProcessed(Image image) {
    this.imageProcesseds.add(image);
    image.setImageOriginal(this);
    return this;
  }

  public Image removeImageProcessed(Image image) {
    this.imageProcesseds.remove(image);
    image.setImageOriginal(null);
    return this;
  }

  public void setImageProcesseds(Set<Image> images) {
    if (this.imageProcesseds != null) {
      this.imageProcesseds.forEach(i -> i.setImageOriginal(null));
    }
    if (images != null) {
      images.forEach(i -> i.setImageOriginal(this));
    }
    this.imageProcesseds = images;
  }

  public Image getImageOriginal() {
    return this.imageOriginal;
  }

  public Image imageOriginal(Image image) {
    this.setImageOriginal(image);
    return this;
  }

  public void setImageOriginal(Image image) {
    this.imageOriginal = image;
  }

  public Event getEvent() {
    return this.event;
  }

  public Image event(Event event) {
    this.setEvent(event);
    return this;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public Set<Album> getAlbums() {
    return this.albums;
  }

  public Image albums(Set<Album> albums) {
    this.setAlbums(albums);
    return this;
  }

  public Image addAlbum(Album album) {
    this.albums.add(album);
    album.getImages().add(this);
    return this;
  }

  public Image removeAlbum(Album album) {
    this.albums.remove(album);
    album.getImages().remove(this);
    return this;
  }

  public void setAlbums(Set<Album> albums) {
    if (this.albums != null) {
      this.albums.forEach(i -> i.removeImage(this));
    }
    if (albums != null) {
      albums.forEach(i -> i.addImage(this));
    }
    this.albums = albums;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Image)) {
      return false;
    }
    return id != null && id.equals(((Image) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Image{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
