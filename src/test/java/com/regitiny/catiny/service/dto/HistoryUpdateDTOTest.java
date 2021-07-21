package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@GeneratedByJHipster
class HistoryUpdateDTOTest
{

  @Test
  void dtoEqualsVerifier() throws Exception
  {
    TestUtil.equalsVerifier(HistoryUpdateDTO.class);
    HistoryUpdateDTO historyUpdateDTO1 = new HistoryUpdateDTO();
    historyUpdateDTO1.setId(1L);
    HistoryUpdateDTO historyUpdateDTO2 = new HistoryUpdateDTO();
    assertThat(historyUpdateDTO1).isNotEqualTo(historyUpdateDTO2);
    historyUpdateDTO2.setId(historyUpdateDTO1.getId());
    assertThat(historyUpdateDTO1).isEqualTo(historyUpdateDTO2);
    historyUpdateDTO2.setId(2L);
    assertThat(historyUpdateDTO1).isNotEqualTo(historyUpdateDTO2);
    historyUpdateDTO1.setId(null);
    assertThat(historyUpdateDTO1).isNotEqualTo(historyUpdateDTO2);
  }
}
