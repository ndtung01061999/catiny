package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Event;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Event} entity.
 */
@GeneratedByJHipster
public interface EventSearchRepository extends ElasticsearchRepository<Event, Long> {}
