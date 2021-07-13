package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class RankUserTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(RankUser.class);
    RankUser rankUser1 = new RankUser();
    rankUser1.setId(1L);
    RankUser rankUser2 = new RankUser();
    rankUser2.setId(rankUser1.getId());
    assertThat(rankUser1).isEqualTo(rankUser2);
    rankUser2.setId(2L);
    assertThat(rankUser1).isNotEqualTo(rankUser2);
    rankUser1.setId(null);
    assertThat(rankUser1).isNotEqualTo(rankUser2);
  }
}
