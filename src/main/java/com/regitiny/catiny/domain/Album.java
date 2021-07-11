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
 * @what?            -> The Album entity\n@why?             ->\n@use-to           -> Lưu thông tin về một bộ album của người dùng\n@commonly-used-in -> Người dùng nhóm một bộ ảnh vào một album\n\n@describe         ->
 */
@Entity
@Table(name = "album")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "album")
@GeneratedByJHipster
public class Album implements Serializable {

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

  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

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

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JoinTable(name = "rel_album__image", joinColumns = @JoinColumn(name = "album_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
  @JsonIgnoreProperties(value = { "fileInfo", "baseInfo", "imageProcesseds", "imageOriginal", "event", "albums" }, allowSetters = true)
  private Set<Image> images = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Album id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public Album uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return this.name;
  }

  public Album name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public Album baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Set<Image> getImages() {
    return this.images;
  }

  public Album images(Set<Image> images) {
    this.setImages(images);
    return this;
  }

  public Album addImage(Image image) {
    this.images.add(image);
    image.getAlbums().add(this);
    return this;
  }

  public Album removeImage(Image image) {
    this.images.remove(image);
    image.getAlbums().remove(this);
    return this;
  }

  public void setImages(Set<Image> images) {
    this.images = images;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Album)) {
      return false;
    }
    return id != null && id.equals(((Album) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Album{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
