package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.service.dto.BaseInfoDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  @ApiModelProperty("work : nơi làm việc\n"
    + "@data-type : Json\n"
    + "@describe : những nơi, công ty đã từng làm việc , khoảng thời gian vị trí ...\n"
    + "@example :\n"
    + "-companyName\n"
    + "-companyAddress\n"
    + "-companyContact\n"
    + "@author           -> yuvytung.")
  @Lob
  private String work;

  @ApiModelProperty("education : học vấn\n"
    + "@data-type : Json\n"
    + "@describe : học vấn thế nào tốt nghiệp trường nào học tại đâu khoảng thời gian nào , bằng cấp chứng chỉ ra sao  ...")
  @Lob
  private String education;

  @ApiModelProperty("placesLived : những nơi đã từng sống\n"
    + "@data-type : Json\n"
    + "@describe : sống ở đâu khoảng thời gian nào có gì tại nơi sống ...")
  @Lob
  private String placesLived;

  @ApiModelProperty("contactInfo : những thông in liên hệ cơ bản\n"
    + "@data-type : Json\n"
    + "@describe : số điện thoại email , địa chỉ ...")
  @Lob
  private String contactInfo;

  @ApiModelProperty("webSocialLinks : các mạng xã hội sử dụng . trang web ...\n"
    + "@data-type : Json\n"
    + "@describe : thông tin về mạng xã hội sử dụng ...")
  @Lob
  private String webSocialLinks;

  @ApiModelProperty("basicInfo : thông tin cơ bản của người dùng\n"
    + "@data-type : Json\n"
    + "@describe : những thông tin tóm tắt để giới thiệu nổi bật")
  @Lob
  private String basicInfo;

  @ApiModelProperty("relationshipInfo : những mối liên hệ với ai đó\n"
    + "@data-type : Json\n"
    + "@describe : ví dụ như đã kết hôn với ai ...")
  @Lob
  private String relationshipInfo;

  @ApiModelProperty("family : những người trong gia đình\n"
    + "@data-type : Json\n"
    + "@describe : anh em bạn bè người thân trong gia đình")
  @Lob
  private String family;

  @ApiModelProperty("detailAbout : chi tiết về bản thân mình\n"
    + "@data-type : Json\n"
    + "@describe : những thôn tin chi tết về bản thân như ngày tháng năm sinh nơi ở ...")
  @Lob
  private String detailAbout;

  @ApiModelProperty("lifeEvents : sự kiên lớn trong cuộc đời\n"
    + "@data-type : Json\n"
    + "@describe : những sự kiện như sinh , tốt nghiệp . lấy vợ chồng , có con ...")
  @Lob
  private String lifeEvents;

  @ApiModelProperty("hobbies : sở thích của bản thân\n"
    + "@data-type : Json\n"
    + "@describe : sở thích của ban thân là gì , phim , nhạc , sách , ...")
  @Lob
  private String hobbies;

  @ApiModelProperty("featured : điểm nổi bật của bản thân\n"
    + "@data-type : Json\n"
    + "@describe : cá tính thế nào , đã làm nhứng gì rất đặc sắc ....")
  @Lob
  private String featured;

  private BaseInfoDTO baseInfo;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request implements Serializable
  {
    private Long id;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
    )
    private UUID uuid;

    @ApiModelProperty("work : nơi làm việc\n"
      + "@data-type : Json\n"
      + "@describe : những nơi, công ty đã từng làm việc , khoảng thời gian vị trí ...\n"
      + "@example :\n"
      + "-companyName\n"
      + "-companyAddress\n"
      + "-companyContact\n"
      + "@author           -> yuvytung.")
    @Lob
    private String work;

    @ApiModelProperty("education : học vấn\n"
      + "@data-type : Json\n"
      + "@describe : học vấn thế nào tốt nghiệp trường nào học tại đâu khoảng thời gian nào , bằng cấp chứng chỉ ra sao  ...")
    @Lob
    private String education;

    @ApiModelProperty("placesLived : những nơi đã từng sống\n"
      + "@data-type : Json\n"
      + "@describe : sống ở đâu khoảng thời gian nào có gì tại nơi sống ...")
    @Lob
    private String placesLived;

    @ApiModelProperty("contactInfo : những thông in liên hệ cơ bản\n"
      + "@data-type : Json\n"
      + "@describe : số điện thoại email , địa chỉ ...")
    @Lob
    private String contactInfo;

    @ApiModelProperty("webSocialLinks : các mạng xã hội sử dụng . trang web ...\n"
      + "@data-type : Json\n"
      + "@describe : thông tin về mạng xã hội sử dụng ...")
    @Lob
    private String webSocialLinks;

    @ApiModelProperty("basicInfo : thông tin cơ bản của người dùng\n"
      + "@data-type : Json\n"
      + "@describe : những thông tin tóm tắt để giới thiệu nổi bật")
    @Lob
    private String basicInfo;

    @ApiModelProperty("relationshipInfo : những mối liên hệ với ai đó\n"
      + "@data-type : Json\n"
      + "@describe : ví dụ như đã kết hôn với ai ...")
    @Lob
    private String relationshipInfo;

    @ApiModelProperty("family : những người trong gia đình\n"
      + "@data-type : Json\n"
      + "@describe : anh em bạn bè người thân trong gia đình")
    @Lob
    private String family;

    @ApiModelProperty("detailAbout : chi tiết về bản thân mình\n"
      + "@data-type : Json\n"
      + "@describe : những thôn tin chi tết về bản thân như ngày tháng năm sinh nơi ở ...")
    @Lob
    private String detailAbout;

    @ApiModelProperty("lifeEvents : sự kiên lớn trong cuộc đời\n"
      + "@data-type : Json\n"
      + "@describe : những sự kiện như sinh , tốt nghiệp . lấy vợ chồng , có con ...")
    @Lob
    private String lifeEvents;

    @ApiModelProperty("hobbies : sở thích của bản thân\n"
      + "@data-type : Json\n"
      + "@describe : sở thích của ban thân là gì , phim , nhạc , sách , ...")
    @Lob
    private String hobbies;

    @ApiModelProperty("featured : điểm nổi bật của bản thân\n"
      + "@data-type : Json\n"
      + "@describe : cá tính thế nào , đã làm nhứng gì rất đặc sắc ....")
    @Lob
    private String featured;

    private BaseInfoDTO baseInfo;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Response implements Serializable
  {
    private Long id;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
    )
    private UUID uuid;

    @ApiModelProperty("work : nơi làm việc\n"
      + "@data-type : Json\n"
      + "@describe : những nơi, công ty đã từng làm việc , khoảng thời gian vị trí ...\n"
      + "@example :\n"
      + "-companyName\n"
      + "-companyAddress\n"
      + "-companyContact\n"
      + "@author           -> yuvytung.")
    @Lob
    private String work;

    @ApiModelProperty("education : học vấn\n"
      + "@data-type : Json\n"
      + "@describe : học vấn thế nào tốt nghiệp trường nào học tại đâu khoảng thời gian nào , bằng cấp chứng chỉ ra sao  ...")
    @Lob
    private String education;

    @ApiModelProperty("placesLived : những nơi đã từng sống\n"
      + "@data-type : Json\n"
      + "@describe : sống ở đâu khoảng thời gian nào có gì tại nơi sống ...")
    @Lob
    private String placesLived;

    @ApiModelProperty("contactInfo : những thông in liên hệ cơ bản\n"
      + "@data-type : Json\n"
      + "@describe : số điện thoại email , địa chỉ ...")
    @Lob
    private String contactInfo;

    @ApiModelProperty("webSocialLinks : các mạng xã hội sử dụng . trang web ...\n"
      + "@data-type : Json\n"
      + "@describe : thông tin về mạng xã hội sử dụng ...")
    @Lob
    private String webSocialLinks;

    @ApiModelProperty("basicInfo : thông tin cơ bản của người dùng\n"
      + "@data-type : Json\n"
      + "@describe : những thông tin tóm tắt để giới thiệu nổi bật")
    @Lob
    private String basicInfo;

    @ApiModelProperty("relationshipInfo : những mối liên hệ với ai đó\n"
      + "@data-type : Json\n"
      + "@describe : ví dụ như đã kết hôn với ai ...")
    @Lob
    private String relationshipInfo;

    @ApiModelProperty("family : những người trong gia đình\n"
      + "@data-type : Json\n"
      + "@describe : anh em bạn bè người thân trong gia đình")
    @Lob
    private String family;

    @ApiModelProperty("detailAbout : chi tiết về bản thân mình\n"
      + "@data-type : Json\n"
      + "@describe : những thôn tin chi tết về bản thân như ngày tháng năm sinh nơi ở ...")
    @Lob
    private String detailAbout;

    @ApiModelProperty("lifeEvents : sự kiên lớn trong cuộc đời\n"
      + "@data-type : Json\n"
      + "@describe : những sự kiện như sinh , tốt nghiệp . lấy vợ chồng , có con ...")
    @Lob
    private String lifeEvents;

    @ApiModelProperty("hobbies : sở thích của bản thân\n"
      + "@data-type : Json\n"
      + "@describe : sở thích của ban thân là gì , phim , nhạc , sách , ...")
    @Lob
    private String hobbies;

    @ApiModelProperty("featured : điểm nổi bật của bản thân\n"
      + "@data-type : Json\n"
      + "@describe : cá tính thế nào , đã làm nhứng gì rất đặc sắc ....")
    @Lob
    private String featured;

    private BaseInfoDTO baseInfo;
  }
}
