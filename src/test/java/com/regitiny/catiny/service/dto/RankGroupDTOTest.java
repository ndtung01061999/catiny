package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class RankGroupDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(RankGroupDTO.class);
    RankGroupDTO rankGroupDTO1 = new RankGroupDTO();
    rankGroupDTO1.setId(1L);
    RankGroupDTO rankGroupDTO2 = new RankGroupDTO();
    assertThat(rankGroupDTO1).isNotEqualTo(rankGroupDTO2);
    rankGroupDTO2.setId(rankGroupDTO1.getId());
    assertThat(rankGroupDTO1).isEqualTo(rankGroupDTO2);
    rankGroupDTO2.setId(2L);
    assertThat(rankGroupDTO1).isNotEqualTo(rankGroupDTO2);
    rankGroupDTO1.setId(null);
    assertThat(rankGroupDTO1).isNotEqualTo(rankGroupDTO2);
  }
}
