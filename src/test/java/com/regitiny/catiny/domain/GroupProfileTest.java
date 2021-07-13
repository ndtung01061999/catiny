package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class GroupProfileTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(GroupProfile.class);
    GroupProfile groupProfile1 = new GroupProfile();
    groupProfile1.setId(1L);
    GroupProfile groupProfile2 = new GroupProfile();
    groupProfile2.setId(groupProfile1.getId());
    assertThat(groupProfile1).isEqualTo(groupProfile2);
    groupProfile2.setId(2L);
    assertThat(groupProfile1).isNotEqualTo(groupProfile2);
    groupProfile1.setId(null);
    assertThat(groupProfile1).isNotEqualTo(groupProfile2);
  }
}
