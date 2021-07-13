package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class PagePostTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(PagePost.class);
    PagePost pagePost1 = new PagePost();
    pagePost1.setId(1L);
    PagePost pagePost2 = new PagePost();
    pagePost2.setId(pagePost1.getId());
    assertThat(pagePost1).isEqualTo(pagePost2);
    pagePost2.setId(2L);
    assertThat(pagePost1).isNotEqualTo(pagePost2);
    pagePost1.setId(null);
    assertThat(pagePost1).isNotEqualTo(pagePost2);
  }
}
