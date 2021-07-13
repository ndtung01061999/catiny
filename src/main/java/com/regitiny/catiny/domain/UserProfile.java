package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @what?         	  -> The UserProfile entity .\n@why?          	  ->\n@use-to:       	  -> Lưu thông tin mở rộng của người dùng (trường học cơ quan làm việc ...)\n@commonly-used-in -> Trang cá nhân (trang giới thiệu)\n\n@describe      	  -> Đây là bảng NoSQL một số trường ở dưới dạng JSON ,NoSQL vì dữ liệu mỗi trường học , cơ quan ,nới sống ... đôi khikhông giống nhau
 */
@Entity
@Table(name = "user_profile")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "userprofile")
@GeneratedByJHipster
public class UserProfile implements Serializable {

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
   * work : nơi làm việc\n@data-type : Json\n@describe : những nơi, công ty đã từng làm việc , khoảng thời gian vị trí ...\n@example :\n-companyName\n-companyAddress\n-companyContact\n@author           -> yuvytung.
   */
  @Lob
  @Column(name = "work")
  private String work;

  /**
   * education : học vấn\n@data-type : Json\n@describe : học vấn thế nào tốt nghiệp trường nào học tại đâu khoảng thời gian nào , bằng cấp chứng chỉ ra sao  ...
   */
  @Lob
  @Column(name = "education")
  private String education;

  /**
   * placesLived : những nơi đã từng sống\n@data-type : Json\n@describe : sống ở đâu khoảng thời gian nào có gì tại nơi sống ...
   */
  @Lob
  @Column(name = "places_lived")
  private String placesLived;

  /**
   * contactInfo : những thông in liên hệ cơ bản\n@data-type : Json\n@describe : số điện thoại email , địa chỉ ...
   */
  @Lob
  @Column(name = "contact_info")
  private String contactInfo;

  /**
   * webSocialLinks : các mạng xã hội sử dụng . trang web ...\n@data-type : Json\n@describe : thông tin về mạng xã hội sử dụng ...
   */
  @Lob
  @Column(name = "web_social_links")
  private String webSocialLinks;

  /**
   * basicInfo : thông tin cơ bản của người dùng\n@data-type : Json\n@describe : những thông tin tóm tắt để giới thiệu nổi bật
   */
  @Lob
  @Column(name = "basic_info")
  private String basicInfo;

  /**
   * relationshipInfo : những mối liên hệ với ai đó\n@data-type : Json\n@describe : ví dụ như đã kết hôn với ai ...
   */
  @Lob
  @Column(name = "relationship_info")
  private String relationshipInfo;

  /**
   * family : những người trong gia đình\n@data-type : Json\n@describe : anh em bạn bè người thân trong gia đình
   */
  @Lob
  @Column(name = "family")
  private String family;

  /**
   * detailAbout : chi tiết về bản thân mình\n@data-type : Json\n@describe : những thôn tin chi tết về bản thân như ngày tháng năm sinh nơi ở ...
   */
  @Lob
  @Column(name = "detail_about")
  private String detailAbout;

  /**
   * lifeEvents : sự kiên lớn trong cuộc đời\n@data-type : Json\n@describe : những sự kiện như sinh , tốt nghiệp . lấy vợ chồng , có con ...
   */
  @Lob
  @Column(name = "life_events")
  private String lifeEvents;

  /**
   * hobbies : sở thích của bản thân\n@data-type : Json\n@describe : sở thích của ban thân là gì , phim , nhạc , sách , ...
   */
  @Lob
  @Column(name = "hobbies")
  private String hobbies;

  /**
   * featured : điểm nổi bật của bản thân\n@data-type : Json\n@describe : cá tính thế nào , đã làm nhứng gì rất đặc sắc ....
   */
  @Lob
  @Column(name = "featured")
  private String featured;

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

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserProfile id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public UserProfile uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getWork() {
    return this.work;
  }

  public UserProfile work(String work) {
    this.work = work;
    return this;
  }

  public void setWork(String work) {
    this.work = work;
  }

  public String getEducation() {
    return this.education;
  }

  public UserProfile education(String education) {
    this.education = education;
    return this;
  }

  public void setEducation(String education) {
    this.education = education;
  }

  public String getPlacesLived() {
    return this.placesLived;
  }

  public UserProfile placesLived(String placesLived) {
    this.placesLived = placesLived;
    return this;
  }

  public void setPlacesLived(String placesLived) {
    this.placesLived = placesLived;
  }

  public String getContactInfo() {
    return this.contactInfo;
  }

  public UserProfile contactInfo(String contactInfo) {
    this.contactInfo = contactInfo;
    return this;
  }

  public void setContactInfo(String contactInfo) {
    this.contactInfo = contactInfo;
  }

  public String getWebSocialLinks() {
    return this.webSocialLinks;
  }

  public UserProfile webSocialLinks(String webSocialLinks) {
    this.webSocialLinks = webSocialLinks;
    return this;
  }

  public void setWebSocialLinks(String webSocialLinks) {
    this.webSocialLinks = webSocialLinks;
  }

  public String getBasicInfo() {
    return this.basicInfo;
  }

  public UserProfile basicInfo(String basicInfo) {
    this.basicInfo = basicInfo;
    return this;
  }

  public void setBasicInfo(String basicInfo) {
    this.basicInfo = basicInfo;
  }

  public String getRelationshipInfo() {
    return this.relationshipInfo;
  }

  public UserProfile relationshipInfo(String relationshipInfo) {
    this.relationshipInfo = relationshipInfo;
    return this;
  }

  public void setRelationshipInfo(String relationshipInfo) {
    this.relationshipInfo = relationshipInfo;
  }

  public String getFamily() {
    return this.family;
  }

  public UserProfile family(String family) {
    this.family = family;
    return this;
  }

  public void setFamily(String family) {
    this.family = family;
  }

  public String getDetailAbout() {
    return this.detailAbout;
  }

  public UserProfile detailAbout(String detailAbout) {
    this.detailAbout = detailAbout;
    return this;
  }

  public void setDetailAbout(String detailAbout) {
    this.detailAbout = detailAbout;
  }

  public String getLifeEvents() {
    return this.lifeEvents;
  }

  public UserProfile lifeEvents(String lifeEvents) {
    this.lifeEvents = lifeEvents;
    return this;
  }

  public void setLifeEvents(String lifeEvents) {
    this.lifeEvents = lifeEvents;
  }

  public String getHobbies() {
    return this.hobbies;
  }

  public UserProfile hobbies(String hobbies) {
    this.hobbies = hobbies;
    return this;
  }

  public void setHobbies(String hobbies) {
    this.hobbies = hobbies;
  }

  public String getFeatured() {
    return this.featured;
  }

  public UserProfile featured(String featured) {
    this.featured = featured;
    return this;
  }

  public void setFeatured(String featured) {
    this.featured = featured;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public UserProfile baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserProfile)) {
      return false;
    }
    return id != null && id.equals(((UserProfile) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "UserProfile{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", work='" + getWork() + "'" +
            ", education='" + getEducation() + "'" +
            ", placesLived='" + getPlacesLived() + "'" +
            ", contactInfo='" + getContactInfo() + "'" +
            ", webSocialLinks='" + getWebSocialLinks() + "'" +
            ", basicInfo='" + getBasicInfo() + "'" +
            ", relationshipInfo='" + getRelationshipInfo() + "'" +
            ", family='" + getFamily() + "'" +
            ", detailAbout='" + getDetailAbout() + "'" +
            ", lifeEvents='" + getLifeEvents() + "'" +
            ", hobbies='" + getHobbies() + "'" +
            ", featured='" + getFeatured() + "'" +
            "}";
    }
}
