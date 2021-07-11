package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class TopicInterestTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(TopicInterest.class);
    TopicInterest topicInterest1 = new TopicInterest();
    topicInterest1.setId(1L);
    TopicInterest topicInterest2 = new TopicInterest();
    topicInterest2.setId(topicInterest1.getId());
    assertThat(topicInterest1).isEqualTo(topicInterest2);
    topicInterest2.setId(2L);
    assertThat(topicInterest1).isNotEqualTo(topicInterest2);
    topicInterest1.setId(null);
    assertThat(topicInterest1).isNotEqualTo(topicInterest2);
  }
}
