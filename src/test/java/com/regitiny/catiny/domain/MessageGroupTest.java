package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class MessageGroupTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageGroup.class);
        MessageGroup messageGroup1 = new MessageGroup();
        messageGroup1.setId(1L);
        MessageGroup messageGroup2 = new MessageGroup();
        messageGroup2.setId(messageGroup1.getId());
        assertThat(messageGroup1).isEqualTo(messageGroup2);
        messageGroup2.setId(2L);
        assertThat(messageGroup1).isNotEqualTo(messageGroup2);
        messageGroup1.setId(null);
        assertThat(messageGroup1).isNotEqualTo(messageGroup2);
    }
}
