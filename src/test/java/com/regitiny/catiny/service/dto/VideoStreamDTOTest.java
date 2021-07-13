package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class VideoStreamDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(VideoStreamDTO.class);
    VideoStreamDTO videoStreamDTO1 = new VideoStreamDTO();
    videoStreamDTO1.setId(1L);
    VideoStreamDTO videoStreamDTO2 = new VideoStreamDTO();
    assertThat(videoStreamDTO1).isNotEqualTo(videoStreamDTO2);
    videoStreamDTO2.setId(videoStreamDTO1.getId());
    assertThat(videoStreamDTO1).isEqualTo(videoStreamDTO2);
    videoStreamDTO2.setId(2L);
    assertThat(videoStreamDTO1).isNotEqualTo(videoStreamDTO2);
    videoStreamDTO1.setId(null);
    assertThat(videoStreamDTO1).isNotEqualTo(videoStreamDTO2);
  }
}
