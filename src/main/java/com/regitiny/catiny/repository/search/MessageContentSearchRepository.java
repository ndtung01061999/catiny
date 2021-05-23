package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.domain.MessageContent;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MessageContent} entity.
 */
public interface MessageContentSearchRepository extends ElasticsearchRepository<MessageContent, Long> {}
