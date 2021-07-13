package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class MasterUserTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(MasterUser.class);
    MasterUser masterUser1 = new MasterUser();
    masterUser1.setId(1L);
    MasterUser masterUser2 = new MasterUser();
    masterUser2.setId(masterUser1.getId());
    assertThat(masterUser1).isEqualTo(masterUser2);
    masterUser2.setId(2L);
    assertThat(masterUser1).isNotEqualTo(masterUser2);
    masterUser1.setId(null);
    assertThat(masterUser1).isNotEqualTo(masterUser2);
  }
}
