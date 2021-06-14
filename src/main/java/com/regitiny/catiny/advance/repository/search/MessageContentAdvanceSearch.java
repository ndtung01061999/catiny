package com.regitiny.catiny.advance.repository.search;

import com.regitiny.catiny.advance.repository.search.base.MessageContentBaseSearch;
import com.regitiny.catiny.domain.MessageContent;

/**
 * Spring Data Elasticsearch repository for the {@link MessageContent} entity.
 * <p>
 * here contains complex queries with pure Elasticsearch syntax.
 * if you want to write simple query then you should write to {@link MessageContentBaseSearch}
 */
public interface MessageContentAdvanceSearch extends MessageContentBaseSearch
{
}
