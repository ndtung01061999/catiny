package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class NewsFeedDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(NewsFeedDTO.class);
    NewsFeedDTO newsFeedDTO1 = new NewsFeedDTO();
    newsFeedDTO1.setId(1L);
    NewsFeedDTO newsFeedDTO2 = new NewsFeedDTO();
    assertThat(newsFeedDTO1).isNotEqualTo(newsFeedDTO2);
    newsFeedDTO2.setId(newsFeedDTO1.getId());
    assertThat(newsFeedDTO1).isEqualTo(newsFeedDTO2);
    newsFeedDTO2.setId(2L);
    assertThat(newsFeedDTO1).isNotEqualTo(newsFeedDTO2);
    newsFeedDTO1.setId(null);
    assertThat(newsFeedDTO1).isNotEqualTo(newsFeedDTO2);
  }
}
