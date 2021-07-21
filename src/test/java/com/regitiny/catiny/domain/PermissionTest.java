package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class PermissionTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(Permission.class);
    Permission permission1 = new Permission();
    permission1.setId(1L);
    Permission permission2 = new Permission();
    permission2.setId(permission1.getId());
    assertThat(permission1).isEqualTo(permission2);
    permission2.setId(2L);
    assertThat(permission1).isNotEqualTo(permission2);
    permission1.setId(null);
    assertThat(permission1).isNotEqualTo(permission2);
  }
}
