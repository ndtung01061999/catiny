package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class GroupPostDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(GroupPostDTO.class);
    GroupPostDTO groupPostDTO1 = new GroupPostDTO();
    groupPostDTO1.setId(1L);
    GroupPostDTO groupPostDTO2 = new GroupPostDTO();
    assertThat(groupPostDTO1).isNotEqualTo(groupPostDTO2);
    groupPostDTO2.setId(groupPostDTO1.getId());
    assertThat(groupPostDTO1).isEqualTo(groupPostDTO2);
    groupPostDTO2.setId(2L);
    assertThat(groupPostDTO1).isNotEqualTo(groupPostDTO2);
    groupPostDTO1.setId(null);
    assertThat(groupPostDTO1).isNotEqualTo(groupPostDTO2);
  }
}
