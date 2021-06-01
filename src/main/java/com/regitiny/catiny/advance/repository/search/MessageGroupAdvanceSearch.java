package com.regitiny.catiny.advance.repository.search;

import com.regitiny.catiny.advance.repository.search.base.MessageGroupBaseSearch;
import com.regitiny.catiny.domain.MessageGroup;

/**
 * Spring Data Elasticsearch repository for the {@link MessageGroup} entity.
 * <p>
 * here contains complex queries with pure Elasticsearch syntax.
 * if you want to write simple query then you should write to {@link MessageGroupBaseSearch}
 */
public interface MessageGroupAdvanceSearch extends MessageGroupBaseSearch
{
}
