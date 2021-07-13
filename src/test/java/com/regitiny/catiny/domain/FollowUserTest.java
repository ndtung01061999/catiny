package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class FollowUserTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(FollowUser.class);
    FollowUser followUser1 = new FollowUser();
    followUser1.setId(1L);
    FollowUser followUser2 = new FollowUser();
    followUser2.setId(followUser1.getId());
    assertThat(followUser1).isEqualTo(followUser2);
    followUser2.setId(2L);
    assertThat(followUser1).isNotEqualTo(followUser2);
    followUser1.setId(null);
    assertThat(followUser1).isNotEqualTo(followUser2);
  }
}
