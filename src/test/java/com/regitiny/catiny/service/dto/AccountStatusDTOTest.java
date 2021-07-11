package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class AccountStatusDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(AccountStatusDTO.class);
    AccountStatusDTO accountStatusDTO1 = new AccountStatusDTO();
    accountStatusDTO1.setId(1L);
    AccountStatusDTO accountStatusDTO2 = new AccountStatusDTO();
    assertThat(accountStatusDTO1).isNotEqualTo(accountStatusDTO2);
    accountStatusDTO2.setId(accountStatusDTO1.getId());
    assertThat(accountStatusDTO1).isEqualTo(accountStatusDTO2);
    accountStatusDTO2.setId(2L);
    assertThat(accountStatusDTO1).isNotEqualTo(accountStatusDTO2);
    accountStatusDTO1.setId(null);
    assertThat(accountStatusDTO1).isNotEqualTo(accountStatusDTO2);
  }
}
