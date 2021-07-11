package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class FollowUserDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(FollowUserDTO.class);
    FollowUserDTO followUserDTO1 = new FollowUserDTO();
    followUserDTO1.setId(1L);
    FollowUserDTO followUserDTO2 = new FollowUserDTO();
    assertThat(followUserDTO1).isNotEqualTo(followUserDTO2);
    followUserDTO2.setId(followUserDTO1.getId());
    assertThat(followUserDTO1).isEqualTo(followUserDTO2);
    followUserDTO2.setId(2L);
    assertThat(followUserDTO1).isNotEqualTo(followUserDTO2);
    followUserDTO1.setId(null);
    assertThat(followUserDTO1).isNotEqualTo(followUserDTO2);
  }
}
