package com.regitiny.catiny.advance.repository.search;

import com.regitiny.catiny.advance.repository.search.base.VideoStreamBaseSearch;

/**
 * Spring Data Elasticsearch repository for the {@link com.regitiny.catiny.domain.VideoStream} entity.
 * <p>
 * here contains complex queries with pure Elasticsearch syntax.
 * if you want to write simple query then you should write to {@link VideoStreamBaseSearch}
 */
public interface VideoStreamAdvanceSearch extends VideoStreamBaseSearch
{
}
