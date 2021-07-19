package com.regitiny.catiny.domain;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@GeneratedByJHipster
class HistoryUpdateTest
{

  @Test
  void equalsVerifier() throws Exception
  {
    TestUtil.equalsVerifier(HistoryUpdate.class);
    HistoryUpdate historyUpdate1 = new HistoryUpdate();
    historyUpdate1.setId(1L);
    HistoryUpdate historyUpdate2 = new HistoryUpdate();
    historyUpdate2.setId(historyUpdate1.getId());
    assertThat(historyUpdate1).isEqualTo(historyUpdate2);
    historyUpdate2.setId(2L);
    assertThat(historyUpdate1).isNotEqualTo(historyUpdate2);
    historyUpdate1.setId(null);
    assertThat(historyUpdate1).isNotEqualTo(historyUpdate2);
  }
}
