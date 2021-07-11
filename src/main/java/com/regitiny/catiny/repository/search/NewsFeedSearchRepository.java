package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.NewsFeed;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link NewsFeed} entity.
 */
@GeneratedByJHipster
public interface NewsFeedSearchRepository extends ElasticsearchRepository<NewsFeed, Long> {}
