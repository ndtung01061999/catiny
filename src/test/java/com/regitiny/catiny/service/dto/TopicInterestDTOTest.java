package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class TopicInterestDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(TopicInterestDTO.class);
    TopicInterestDTO topicInterestDTO1 = new TopicInterestDTO();
    topicInterestDTO1.setId(1L);
    TopicInterestDTO topicInterestDTO2 = new TopicInterestDTO();
    assertThat(topicInterestDTO1).isNotEqualTo(topicInterestDTO2);
    topicInterestDTO2.setId(topicInterestDTO1.getId());
    assertThat(topicInterestDTO1).isEqualTo(topicInterestDTO2);
    topicInterestDTO2.setId(2L);
    assertThat(topicInterestDTO1).isNotEqualTo(topicInterestDTO2);
    topicInterestDTO1.setId(null);
    assertThat(topicInterestDTO1).isNotEqualTo(topicInterestDTO2);
  }
}
