package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class PageProfileDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(PageProfileDTO.class);
    PageProfileDTO pageProfileDTO1 = new PageProfileDTO();
    pageProfileDTO1.setId(1L);
    PageProfileDTO pageProfileDTO2 = new PageProfileDTO();
    assertThat(pageProfileDTO1).isNotEqualTo(pageProfileDTO2);
    pageProfileDTO2.setId(pageProfileDTO1.getId());
    assertThat(pageProfileDTO1).isEqualTo(pageProfileDTO2);
    pageProfileDTO2.setId(2L);
    assertThat(pageProfileDTO1).isNotEqualTo(pageProfileDTO2);
    pageProfileDTO1.setId(null);
    assertThat(pageProfileDTO1).isNotEqualTo(pageProfileDTO2);
  }
}
