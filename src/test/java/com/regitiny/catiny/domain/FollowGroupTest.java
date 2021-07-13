package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class FollowGroupTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(FollowGroup.class);
    FollowGroup followGroup1 = new FollowGroup();
    followGroup1.setId(1L);
    FollowGroup followGroup2 = new FollowGroup();
    followGroup2.setId(followGroup1.getId());
    assertThat(followGroup1).isEqualTo(followGroup2);
    followGroup2.setId(2L);
    assertThat(followGroup1).isNotEqualTo(followGroup2);
    followGroup1.setId(null);
    assertThat(followGroup1).isNotEqualTo(followGroup2);
  }
}
