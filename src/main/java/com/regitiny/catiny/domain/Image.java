package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

  /**
   * name           : tên của ảnh . muốn lấy ảnh sẽ gọi theo tên này. sẽ ra một danh sách các anh gồm (ảnh nguyên gốc , các ảnh đã tối ưu , cắt ... từ ảnh gốc đó)
   */
  @Column(name = "name")
  private String name;

  /**
   * width          : chiều rộng ảnh
   */
  @Column(name = "width")
  private Integer width;

  /**
   * height         : chiều cao ảnh
   */
  @Column(name = "height")
  private Integer height;

  /**
   * quality        : chất lượng sau khi xử lý
   */
  @DecimalMin(value = "0")
  @DecimalMax(value = "1")
  @Column(name = "quality")
  private Float quality;

  /**
   * pixelSize      : kích thước của ảnh
   */
  @Column(name = "pixel_size")
  private Integer pixelSize;

  /**
   * priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)
   */
  @Column(name = "priority_index")
  private Long priorityIndex;

  /**
   * dataSize       : kích thước file theo byte
   */
  @Column(name = "data_size")
  private Long dataSize;

  @JsonIgnoreProperties(value = { "baseInfo" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private FileInfo fileInfo;

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

  @OneToMany(mappedBy = "imageOriginal")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "fileInfo", "baseInfo", "imageProcesseds", "imageOriginal", "albums" }, allowSetters = true)
  private Set<Image> imageProcesseds = new HashSet<>();

  @ManyToOne
  @JsonIgnoreProperties(value = { "fileInfo", "baseInfo", "imageProcesseds", "imageOriginal", "albums" }, allowSetters = true)
  private Image imageOriginal;

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

  public Integer getWidth() {
    return this.width;
  }

  public Image width(Integer width) {
    this.width = width;
    return this;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return this.height;
  }

  public Image height(Integer height) {
    this.height = height;
    return this;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Float getQuality() {
    return this.quality;
  }

  public Image quality(Float quality) {
    this.quality = quality;
    return this;
  }

  public void setQuality(Float quality) {
    this.quality = quality;
  }

  public Integer getPixelSize() {
    return this.pixelSize;
  }

  public Image pixelSize(Integer pixelSize) {
    this.pixelSize = pixelSize;
    return this;
  }

  public void setPixelSize(Integer pixelSize) {
    this.pixelSize = pixelSize;
  }

  public Long getPriorityIndex() {
    return this.priorityIndex;
  }

  public Image priorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
    return this;
  }

  public void setPriorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
  }

  public Long getDataSize() {
    return this.dataSize;
  }

  public Image dataSize(Long dataSize) {
    this.dataSize = dataSize;
    return this;
  }

  public void setDataSize(Long dataSize) {
    this.dataSize = dataSize;
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
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", quality=" + getQuality() +
            ", pixelSize=" + getPixelSize() +
            ", priorityIndex=" + getPriorityIndex() +
            ", dataSize=" + getDataSize() +
            "}";
    }
}
