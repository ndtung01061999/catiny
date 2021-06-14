package com.regitiny.catiny.advance.repository.search.base;

import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.repository.search.MessageContentSearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MessageContent} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure Elasticsearch then you should write to :
 * {@link com.regitiny.catiny.advance.repository.search.MessageContentAdvanceSearch}
 */
public interface MessageContentBaseSearch extends MessageContentSearchRepository
{
}
