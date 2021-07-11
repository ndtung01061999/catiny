package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class FollowPageTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(FollowPage.class);
    FollowPage followPage1 = new FollowPage();
    followPage1.setId(1L);
    FollowPage followPage2 = new FollowPage();
    followPage2.setId(followPage1.getId());
    assertThat(followPage1).isEqualTo(followPage2);
    followPage2.setId(2L);
    assertThat(followPage1).isNotEqualTo(followPage2);
    followPage1.setId(null);
    assertThat(followPage1).isNotEqualTo(followPage2);
  }
}
