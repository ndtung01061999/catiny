package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class MessageContentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageContentDTO.class);
        MessageContentDTO messageContentDTO1 = new MessageContentDTO();
        messageContentDTO1.setId(1L);
        MessageContentDTO messageContentDTO2 = new MessageContentDTO();
        assertThat(messageContentDTO1).isNotEqualTo(messageContentDTO2);
        messageContentDTO2.setId(messageContentDTO1.getId());
        assertThat(messageContentDTO1).isEqualTo(messageContentDTO2);
        messageContentDTO2.setId(2L);
        assertThat(messageContentDTO1).isNotEqualTo(messageContentDTO2);
        messageContentDTO1.setId(null);
        assertThat(messageContentDTO1).isNotEqualTo(messageContentDTO2);
    }
}
