package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class FriendTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(Friend.class);
    Friend friend1 = new Friend();
    friend1.setId(1L);
    Friend friend2 = new Friend();
    friend2.setId(friend1.getId());
    assertThat(friend1).isEqualTo(friend2);
    friend2.setId(2L);
    assertThat(friend1).isNotEqualTo(friend2);
    friend1.setId(null);
    assertThat(friend1).isNotEqualTo(friend2);
  }
}
