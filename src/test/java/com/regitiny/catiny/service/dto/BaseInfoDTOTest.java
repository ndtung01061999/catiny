package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class BaseInfoDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(BaseInfoDTO.class);
    BaseInfoDTO baseInfoDTO1 = new BaseInfoDTO();
    baseInfoDTO1.setId(1L);
    BaseInfoDTO baseInfoDTO2 = new BaseInfoDTO();
    assertThat(baseInfoDTO1).isNotEqualTo(baseInfoDTO2);
    baseInfoDTO2.setId(baseInfoDTO1.getId());
    assertThat(baseInfoDTO1).isEqualTo(baseInfoDTO2);
    baseInfoDTO2.setId(2L);
    assertThat(baseInfoDTO1).isNotEqualTo(baseInfoDTO2);
    baseInfoDTO1.setId(null);
    assertThat(baseInfoDTO1).isNotEqualTo(baseInfoDTO2);
  }
}
