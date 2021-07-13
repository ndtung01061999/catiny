package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.UserProfile} entity.
 */
@ApiModel(
  description = "@what?         	  -> The UserProfile entity .\n@why?          	  ->\n@use-to:       	  -> Lưu thông tin mở rộng của người dùng (trường học cơ quan làm việc ...)\n@commonly-used-in -> Trang cá nhân (trang giới thiệu)\n\n@describe      	  -> Đây là bảng NoSQL một số trường ở dưới dạng JSON ,NoSQL vì dữ liệu mỗi trường học , cơ quan ,nới sống ... đôi khikhông giống nhau"
)
@GeneratedByJHipster
public class UserProfileDTO implements Serializable {

  private Long id;

  /**
   * uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)
   */
  @NotNull
  @ApiModelProperty(
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)",
    required = true
  )
  private UUID uuid;

  /**
   * work : nơi làm việc\n@data-type : Json\n@describe : những nơi, công ty đã từng làm việc , khoảng thời gian vị trí ...\n@example :\n-companyName\n-companyAddress\n-companyContact\n@author           -> yuvytung.
   */
  @ApiModelProperty(
    value = "work : nơi làm việc\n@data-type : Json\n@describe : những nơi, công ty đã từng làm việc , khoảng thời gian vị trí ...\n@example :\n-companyName\n-companyAddress\n-companyContact\n@author           -> yuvytung."
  )
  @Lob
  private String work;

  /**
   * education : học vấn\n@data-type : Json\n@describe : học vấn thế nào tốt nghiệp trường nào học tại đâu khoảng thời gian nào , bằng cấp chứng chỉ ra sao  ...
   */
  @ApiModelProperty(
    value = "education : học vấn\n@data-type : Json\n@describe : học vấn thế nào tốt nghiệp trường nào học tại đâu khoảng thời gian nào , bằng cấp chứng chỉ ra sao  ..."
  )
  @Lob
  private String education;

  /**
   * placesLived : những nơi đã từng sống\n@data-type : Json\n@describe : sống ở đâu khoảng thời gian nào có gì tại nơi sống ...
   */
  @ApiModelProperty(
    value = "placesLived : những nơi đã từng sống\n@data-type : Json\n@describe : sống ở đâu khoảng thời gian nào có gì tại nơi sống ..."
  )
  @Lob
  private String placesLived;

  /**
   * contactInfo : những thông in liên hệ cơ bản\n@data-type : Json\n@describe : số điện thoại email , địa chỉ ...
   */
  @ApiModelProperty(value = "contactInfo : những thông in liên hệ cơ bản\n@data-type : Json\n@describe : số điện thoại email , địa chỉ ...")
  @Lob
  private String contactInfo;

  /**
   * webSocialLinks : các mạng xã hội sử dụng . trang web ...\n@data-type : Json\n@describe : thông tin về mạng xã hội sử dụng ...
   */
  @ApiModelProperty(
    value = "webSocialLinks : các mạng xã hội sử dụng . trang web ...\n@data-type : Json\n@describe : thông tin về mạng xã hội sử dụng ..."
  )
  @Lob
  private String webSocialLinks;

  /**
   * basicInfo : thông tin cơ bản của người dùng\n@data-type : Json\n@describe : những thông tin tóm tắt để giới thiệu nổi bật
   */
  @ApiModelProperty(
    value = "basicInfo : thông tin cơ bản của người dùng\n@data-type : Json\n@describe : những thông tin tóm tắt để giới thiệu nổi bật"
  )
  @Lob
  private String basicInfo;

  /**
   * relationshipInfo : những mối liên hệ với ai đó\n@data-type : Json\n@describe : ví dụ như đã kết hôn với ai ...
   */
  @ApiModelProperty(
    value = "relationshipInfo : những mối liên hệ với ai đó\n@data-type : Json\n@describe : ví dụ như đã kết hôn với ai ..."
  )
  @Lob
  private String relationshipInfo;

  /**
   * family : những người trong gia đình\n@data-type : Json\n@describe : anh em bạn bè người thân trong gia đình
   */
  @ApiModelProperty(value = "family : những người trong gia đình\n@data-type : Json\n@describe : anh em bạn bè người thân trong gia đình")
  @Lob
  private String family;

  /**
   * detailAbout : chi tiết về bản thân mình\n@data-type : Json\n@describe : những thôn tin chi tết về bản thân như ngày tháng năm sinh nơi ở ...
   */
  @ApiModelProperty(
    value = "detailAbout : chi tiết về bản thân mình\n@data-type : Json\n@describe : những thôn tin chi tết về bản thân như ngày tháng năm sinh nơi ở ..."
  )
  @Lob
  private String detailAbout;

  /**
   * lifeEvents : sự kiên lớn trong cuộc đời\n@data-type : Json\n@describe : những sự kiện như sinh , tốt nghiệp . lấy vợ chồng , có con ...
   */
  @ApiModelProperty(
    value = "lifeEvents : sự kiên lớn trong cuộc đời\n@data-type : Json\n@describe : những sự kiện như sinh , tốt nghiệp . lấy vợ chồng , có con ..."
  )
  @Lob
  private String lifeEvents;

  /**
   * hobbies : sở thích của bản thân\n@data-type : Json\n@describe : sở thích của ban thân là gì , phim , nhạc , sách , ...
   */
  @ApiModelProperty(
    value = "hobbies : sở thích của bản thân\n@data-type : Json\n@describe : sở thích của ban thân là gì , phim , nhạc , sách , ..."
  )
  @Lob
  private String hobbies;

  /**
   * featured : điểm nổi bật của bản thân\n@data-type : Json\n@describe : cá tính thế nào , đã làm nhứng gì rất đặc sắc ....
   */
  @ApiModelProperty(
    value = "featured : điểm nổi bật của bản thân\n@data-type : Json\n@describe : cá tính thế nào , đã làm nhứng gì rất đặc sắc ...."
  )
  @Lob
  private String featured;

  private BaseInfoDTO baseInfo;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getWork() {
    return work;
  }

  public void setWork(String work) {
    this.work = work;
  }

  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
  }

  public String getPlacesLived() {
    return placesLived;
  }

  public void setPlacesLived(String placesLived) {
    this.placesLived = placesLived;
  }

  public String getContactInfo() {
    return contactInfo;
  }

  public void setContactInfo(String contactInfo) {
    this.contactInfo = contactInfo;
  }

  public String getWebSocialLinks() {
    return webSocialLinks;
  }

  public void setWebSocialLinks(String webSocialLinks) {
    this.webSocialLinks = webSocialLinks;
  }

  public String getBasicInfo() {
    return basicInfo;
  }

  public void setBasicInfo(String basicInfo) {
    this.basicInfo = basicInfo;
  }

  public String getRelationshipInfo() {
    return relationshipInfo;
  }

  public void setRelationshipInfo(String relationshipInfo) {
    this.relationshipInfo = relationshipInfo;
  }

  public String getFamily() {
    return family;
  }

  public void setFamily(String family) {
    this.family = family;
  }

  public String getDetailAbout() {
    return detailAbout;
  }

  public void setDetailAbout(String detailAbout) {
    this.detailAbout = detailAbout;
  }

  public String getLifeEvents() {
    return lifeEvents;
  }

  public void setLifeEvents(String lifeEvents) {
    this.lifeEvents = lifeEvents;
  }

  public String getHobbies() {
    return hobbies;
  }

  public void setHobbies(String hobbies) {
    this.hobbies = hobbies;
  }

  public String getFeatured() {
    return featured;
  }

  public void setFeatured(String featured) {
    this.featured = featured;
  }

  public BaseInfoDTO getBaseInfo() {
    return baseInfo;
  }

  public void setBaseInfo(BaseInfoDTO baseInfo) {
    this.baseInfo = baseInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserProfileDTO)) {
      return false;
    }

    UserProfileDTO userProfileDTO = (UserProfileDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, userProfileDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "UserProfileDTO{" +
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
            ", baseInfo=" + getBaseInfo() +
            "}";
    }
}
