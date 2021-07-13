package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class GroupPostTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(GroupPost.class);
    GroupPost groupPost1 = new GroupPost();
    groupPost1.setId(1L);
    GroupPost groupPost2 = new GroupPost();
    groupPost2.setId(groupPost1.getId());
    assertThat(groupPost1).isEqualTo(groupPost2);
    groupPost2.setId(2L);
    assertThat(groupPost1).isNotEqualTo(groupPost2);
    groupPost1.setId(null);
    assertThat(groupPost1).isNotEqualTo(groupPost2);
  }
}
