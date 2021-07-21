package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link HistoryUpdateSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
@GeneratedByJHipster
public class HistoryUpdateSearchRepositoryMockConfiguration
{

  @MockBean
  private HistoryUpdateSearchRepository mockHistoryUpdateSearchRepository;
}
