package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class BaseInfoTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(BaseInfo.class);
    BaseInfo baseInfo1 = new BaseInfo();
    baseInfo1.setId(1L);
    BaseInfo baseInfo2 = new BaseInfo();
    baseInfo2.setId(baseInfo1.getId());
    assertThat(baseInfo1).isEqualTo(baseInfo2);
    baseInfo2.setId(2L);
    assertThat(baseInfo1).isNotEqualTo(baseInfo2);
    baseInfo1.setId(null);
    assertThat(baseInfo1).isNotEqualTo(baseInfo2);
  }
}
