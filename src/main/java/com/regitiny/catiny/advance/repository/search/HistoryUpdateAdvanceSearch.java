package com.regitiny.catiny.advance.repository.search;

import com.regitiny.catiny.advance.repository.search.base.HistoryUpdateBaseSearch;

/**
 * Spring Data Elasticsearch repository for the {@link com.regitiny.catiny.domain.HistoryUpdate} entity.
 * <p>
 * here contains complex queries with pure Elasticsearch syntax.
 * if you want to write simple query then you should write to {@link com.regitiny.catiny.advance.repository.search.base.HistoryUpdateBaseSearch}
 */
public interface HistoryUpdateAdvanceSearch extends HistoryUpdateBaseSearch
{
}
