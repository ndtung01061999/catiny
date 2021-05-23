package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MessageContentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageContent.class);
        MessageContent messageContent1 = new MessageContent();
        messageContent1.setId(1L);
        MessageContent messageContent2 = new MessageContent();
        messageContent2.setId(messageContent1.getId());
        assertThat(messageContent1).isEqualTo(messageContent2);
        messageContent2.setId(2L);
        assertThat(messageContent1).isNotEqualTo(messageContent2);
        messageContent1.setId(null);
        assertThat(messageContent1).isNotEqualTo(messageContent2);
    }
}
