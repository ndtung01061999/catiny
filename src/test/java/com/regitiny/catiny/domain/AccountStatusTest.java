package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class AccountStatusTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(AccountStatus.class);
    AccountStatus accountStatus1 = new AccountStatus();
    accountStatus1.setId(1L);
    AccountStatus accountStatus2 = new AccountStatus();
    accountStatus2.setId(accountStatus1.getId());
    assertThat(accountStatus1).isEqualTo(accountStatus2);
    accountStatus2.setId(2L);
    assertThat(accountStatus1).isNotEqualTo(accountStatus2);
    accountStatus1.setId(null);
    assertThat(accountStatus1).isNotEqualTo(accountStatus2);
  }
}
