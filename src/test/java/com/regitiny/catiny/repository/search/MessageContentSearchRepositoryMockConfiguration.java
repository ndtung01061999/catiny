package com.regitiny.catiny.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link MessageContentSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class MessageContentSearchRepositoryMockConfiguration {

    @MockBean
    private MessageContentSearchRepository mockMessageContentSearchRepository;
}
