package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class HanhChinhVNTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(HanhChinhVN.class);
    HanhChinhVN hanhChinhVN1 = new HanhChinhVN();
    hanhChinhVN1.setId(1L);
    HanhChinhVN hanhChinhVN2 = new HanhChinhVN();
    hanhChinhVN2.setId(hanhChinhVN1.getId());
    assertThat(hanhChinhVN1).isEqualTo(hanhChinhVN2);
    hanhChinhVN2.setId(2L);
    assertThat(hanhChinhVN1).isNotEqualTo(hanhChinhVN2);
    hanhChinhVN1.setId(null);
    assertThat(hanhChinhVN1).isNotEqualTo(hanhChinhVN2);
  }
}
