package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class UserProfileDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(UserProfileDTO.class);
    UserProfileDTO userProfileDTO1 = new UserProfileDTO();
    userProfileDTO1.setId(1L);
    UserProfileDTO userProfileDTO2 = new UserProfileDTO();
    assertThat(userProfileDTO1).isNotEqualTo(userProfileDTO2);
    userProfileDTO2.setId(userProfileDTO1.getId());
    assertThat(userProfileDTO1).isEqualTo(userProfileDTO2);
    userProfileDTO2.setId(2L);
    assertThat(userProfileDTO1).isNotEqualTo(userProfileDTO2);
    userProfileDTO1.setId(null);
    assertThat(userProfileDTO1).isNotEqualTo(userProfileDTO2);
  }
}
