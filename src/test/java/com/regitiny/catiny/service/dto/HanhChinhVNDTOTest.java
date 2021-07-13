package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class HanhChinhVNDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(HanhChinhVNDTO.class);
    HanhChinhVNDTO hanhChinhVNDTO1 = new HanhChinhVNDTO();
    hanhChinhVNDTO1.setId(1L);
    HanhChinhVNDTO hanhChinhVNDTO2 = new HanhChinhVNDTO();
    assertThat(hanhChinhVNDTO1).isNotEqualTo(hanhChinhVNDTO2);
    hanhChinhVNDTO2.setId(hanhChinhVNDTO1.getId());
    assertThat(hanhChinhVNDTO1).isEqualTo(hanhChinhVNDTO2);
    hanhChinhVNDTO2.setId(2L);
    assertThat(hanhChinhVNDTO1).isNotEqualTo(hanhChinhVNDTO2);
    hanhChinhVNDTO1.setId(null);
    assertThat(hanhChinhVNDTO1).isNotEqualTo(hanhChinhVNDTO2);
  }
}
