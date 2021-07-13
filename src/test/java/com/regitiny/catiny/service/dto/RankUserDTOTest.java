package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class RankUserDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(RankUserDTO.class);
    RankUserDTO rankUserDTO1 = new RankUserDTO();
    rankUserDTO1.setId(1L);
    RankUserDTO rankUserDTO2 = new RankUserDTO();
    assertThat(rankUserDTO1).isNotEqualTo(rankUserDTO2);
    rankUserDTO2.setId(rankUserDTO1.getId());
    assertThat(rankUserDTO1).isEqualTo(rankUserDTO2);
    rankUserDTO2.setId(2L);
    assertThat(rankUserDTO1).isNotEqualTo(rankUserDTO2);
    rankUserDTO1.setId(null);
    assertThat(rankUserDTO1).isNotEqualTo(rankUserDTO2);
  }
}
