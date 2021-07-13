package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class FollowGroupDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(FollowGroupDTO.class);
    FollowGroupDTO followGroupDTO1 = new FollowGroupDTO();
    followGroupDTO1.setId(1L);
    FollowGroupDTO followGroupDTO2 = new FollowGroupDTO();
    assertThat(followGroupDTO1).isNotEqualTo(followGroupDTO2);
    followGroupDTO2.setId(followGroupDTO1.getId());
    assertThat(followGroupDTO1).isEqualTo(followGroupDTO2);
    followGroupDTO2.setId(2L);
    assertThat(followGroupDTO1).isNotEqualTo(followGroupDTO2);
    followGroupDTO1.setId(null);
    assertThat(followGroupDTO1).isNotEqualTo(followGroupDTO2);
  }
}
