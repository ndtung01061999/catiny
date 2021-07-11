package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class PagePostDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(PagePostDTO.class);
    PagePostDTO pagePostDTO1 = new PagePostDTO();
    pagePostDTO1.setId(1L);
    PagePostDTO pagePostDTO2 = new PagePostDTO();
    assertThat(pagePostDTO1).isNotEqualTo(pagePostDTO2);
    pagePostDTO2.setId(pagePostDTO1.getId());
    assertThat(pagePostDTO1).isEqualTo(pagePostDTO2);
    pagePostDTO2.setId(2L);
    assertThat(pagePostDTO1).isNotEqualTo(pagePostDTO2);
    pagePostDTO1.setId(null);
    assertThat(pagePostDTO1).isNotEqualTo(pagePostDTO2);
  }
}
