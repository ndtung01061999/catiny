package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class VideoLiveStreamBufferDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(VideoLiveStreamBufferDTO.class);
    VideoLiveStreamBufferDTO videoLiveStreamBufferDTO1 = new VideoLiveStreamBufferDTO();
    videoLiveStreamBufferDTO1.setId(1L);
    VideoLiveStreamBufferDTO videoLiveStreamBufferDTO2 = new VideoLiveStreamBufferDTO();
    assertThat(videoLiveStreamBufferDTO1).isNotEqualTo(videoLiveStreamBufferDTO2);
    videoLiveStreamBufferDTO2.setId(videoLiveStreamBufferDTO1.getId());
    assertThat(videoLiveStreamBufferDTO1).isEqualTo(videoLiveStreamBufferDTO2);
    videoLiveStreamBufferDTO2.setId(2L);
    assertThat(videoLiveStreamBufferDTO1).isNotEqualTo(videoLiveStreamBufferDTO2);
    videoLiveStreamBufferDTO1.setId(null);
    assertThat(videoLiveStreamBufferDTO1).isNotEqualTo(videoLiveStreamBufferDTO2);
  }
}
