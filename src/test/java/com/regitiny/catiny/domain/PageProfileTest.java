package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class PageProfileTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(PageProfile.class);
    PageProfile pageProfile1 = new PageProfile();
    pageProfile1.setId(1L);
    PageProfile pageProfile2 = new PageProfile();
    pageProfile2.setId(pageProfile1.getId());
    assertThat(pageProfile1).isEqualTo(pageProfile2);
    pageProfile2.setId(2L);
    assertThat(pageProfile1).isNotEqualTo(pageProfile2);
    pageProfile1.setId(null);
    assertThat(pageProfile1).isNotEqualTo(pageProfile2);
  }
}
