package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MessageGroupDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(MessageGroupDTO.class);
    MessageGroupDTO messageGroupDTO1 = new MessageGroupDTO();
    messageGroupDTO1.setId(1L);
    MessageGroupDTO messageGroupDTO2 = new MessageGroupDTO();
    assertThat(messageGroupDTO1).isNotEqualTo(messageGroupDTO2);
    messageGroupDTO2.setId(messageGroupDTO1.getId());
    assertThat(messageGroupDTO1).isEqualTo(messageGroupDTO2);
    messageGroupDTO2.setId(2L);
    assertThat(messageGroupDTO1).isNotEqualTo(messageGroupDTO2);
    messageGroupDTO1.setId(null);
    assertThat(messageGroupDTO1).isNotEqualTo(messageGroupDTO2);
  }
}
