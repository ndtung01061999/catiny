package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class GroupProfileDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(GroupProfileDTO.class);
    GroupProfileDTO groupProfileDTO1 = new GroupProfileDTO();
    groupProfileDTO1.setId(1L);
    GroupProfileDTO groupProfileDTO2 = new GroupProfileDTO();
    assertThat(groupProfileDTO1).isNotEqualTo(groupProfileDTO2);
    groupProfileDTO2.setId(groupProfileDTO1.getId());
    assertThat(groupProfileDTO1).isEqualTo(groupProfileDTO2);
    groupProfileDTO2.setId(2L);
    assertThat(groupProfileDTO1).isNotEqualTo(groupProfileDTO2);
    groupProfileDTO1.setId(null);
    assertThat(groupProfileDTO1).isNotEqualTo(groupProfileDTO2);
  }
}
