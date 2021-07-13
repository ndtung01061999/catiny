package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class NewsFeedTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(NewsFeed.class);
    NewsFeed newsFeed1 = new NewsFeed();
    newsFeed1.setId(1L);
    NewsFeed newsFeed2 = new NewsFeed();
    newsFeed2.setId(newsFeed1.getId());
    assertThat(newsFeed1).isEqualTo(newsFeed2);
    newsFeed2.setId(2L);
    assertThat(newsFeed1).isNotEqualTo(newsFeed2);
    newsFeed1.setId(null);
    assertThat(newsFeed1).isNotEqualTo(newsFeed2);
  }
}
