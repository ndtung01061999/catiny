package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class RankGroupTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(RankGroup.class);
    RankGroup rankGroup1 = new RankGroup();
    rankGroup1.setId(1L);
    RankGroup rankGroup2 = new RankGroup();
    rankGroup2.setId(rankGroup1.getId());
    assertThat(rankGroup1).isEqualTo(rankGroup2);
    rankGroup2.setId(2L);
    assertThat(rankGroup1).isNotEqualTo(rankGroup2);
    rankGroup1.setId(null);
    assertThat(rankGroup1).isNotEqualTo(rankGroup2);
  }
}
