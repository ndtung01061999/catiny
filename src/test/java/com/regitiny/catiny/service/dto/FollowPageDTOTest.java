package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class FollowPageDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(FollowPageDTO.class);
    FollowPageDTO followPageDTO1 = new FollowPageDTO();
    followPageDTO1.setId(1L);
    FollowPageDTO followPageDTO2 = new FollowPageDTO();
    assertThat(followPageDTO1).isNotEqualTo(followPageDTO2);
    followPageDTO2.setId(followPageDTO1.getId());
    assertThat(followPageDTO1).isEqualTo(followPageDTO2);
    followPageDTO2.setId(2L);
    assertThat(followPageDTO1).isNotEqualTo(followPageDTO2);
    followPageDTO1.setId(null);
    assertThat(followPageDTO1).isNotEqualTo(followPageDTO2);
  }
}
