package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class ClassInfoDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(ClassInfoDTO.class);
    ClassInfoDTO classInfoDTO1 = new ClassInfoDTO();
    classInfoDTO1.setId(1L);
    ClassInfoDTO classInfoDTO2 = new ClassInfoDTO();
    assertThat(classInfoDTO1).isNotEqualTo(classInfoDTO2);
    classInfoDTO2.setId(classInfoDTO1.getId());
    assertThat(classInfoDTO1).isEqualTo(classInfoDTO2);
    classInfoDTO2.setId(2L);
    assertThat(classInfoDTO1).isNotEqualTo(classInfoDTO2);
    classInfoDTO1.setId(null);
    assertThat(classInfoDTO1).isNotEqualTo(classInfoDTO2);
  }
}
