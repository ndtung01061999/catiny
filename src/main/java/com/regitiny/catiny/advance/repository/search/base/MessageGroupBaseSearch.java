package com.regitiny.catiny.advance.repository.search.base;

import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.repository.search.MessageGroupSearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MessageGroup} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure Elasticsearch then you should write to :
 * {@link com.regitiny.catiny.advance.repository.search.MessageGroupAdvanceSearch}
 */
public interface MessageGroupBaseSearch extends MessageGroupSearchRepository
{
}
