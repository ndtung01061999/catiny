package com.regitiny.catiny.advance.repository.search;

import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.repository.search.MessageGroupSearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MessageGroup} entity.
 */
public interface MessageGroupAdvanceSearch extends MessageGroupSearchRepository{}
