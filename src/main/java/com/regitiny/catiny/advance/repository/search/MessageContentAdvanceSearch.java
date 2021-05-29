package com.regitiny.catiny.advance.repository.search;

import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.repository.search.MessageContentSearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MessageContent} entity.
 */
public interface MessageContentAdvanceSearch extends MessageContentSearchRepository{}
