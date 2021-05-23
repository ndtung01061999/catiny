package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.domain.MessageGroup;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MessageGroup} entity.
 */
public interface MessageGroupSearchRepository extends ElasticsearchRepository<MessageGroup, Long> {}
