package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class MasterUserDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(MasterUserDTO.class);
    MasterUserDTO masterUserDTO1 = new MasterUserDTO();
    masterUserDTO1.setId(1L);
    MasterUserDTO masterUserDTO2 = new MasterUserDTO();
    assertThat(masterUserDTO1).isNotEqualTo(masterUserDTO2);
    masterUserDTO2.setId(masterUserDTO1.getId());
    assertThat(masterUserDTO1).isEqualTo(masterUserDTO2);
    masterUserDTO2.setId(2L);
    assertThat(masterUserDTO1).isNotEqualTo(masterUserDTO2);
    masterUserDTO1.setId(null);
    assertThat(masterUserDTO1).isNotEqualTo(masterUserDTO2);
  }
}
