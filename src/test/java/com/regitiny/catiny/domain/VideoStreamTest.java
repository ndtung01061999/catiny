package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class VideoStreamTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(VideoStream.class);
    VideoStream videoStream1 = new VideoStream();
    videoStream1.setId(1L);
    VideoStream videoStream2 = new VideoStream();
    videoStream2.setId(videoStream1.getId());
    assertThat(videoStream1).isEqualTo(videoStream2);
    videoStream2.setId(2L);
    assertThat(videoStream1).isNotEqualTo(videoStream2);
    videoStream1.setId(null);
    assertThat(videoStream1).isNotEqualTo(videoStream2);
  }
}
