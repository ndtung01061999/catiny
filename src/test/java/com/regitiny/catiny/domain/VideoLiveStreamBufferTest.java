package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class VideoLiveStreamBufferTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(VideoLiveStreamBuffer.class);
    VideoLiveStreamBuffer videoLiveStreamBuffer1 = new VideoLiveStreamBuffer();
    videoLiveStreamBuffer1.setId(1L);
    VideoLiveStreamBuffer videoLiveStreamBuffer2 = new VideoLiveStreamBuffer();
    videoLiveStreamBuffer2.setId(videoLiveStreamBuffer1.getId());
    assertThat(videoLiveStreamBuffer1).isEqualTo(videoLiveStreamBuffer2);
    videoLiveStreamBuffer2.setId(2L);
    assertThat(videoLiveStreamBuffer1).isNotEqualTo(videoLiveStreamBuffer2);
    videoLiveStreamBuffer1.setId(null);
    assertThat(videoLiveStreamBuffer1).isNotEqualTo(videoLiveStreamBuffer2);
  }
}
