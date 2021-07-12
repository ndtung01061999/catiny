package com.regitiny.catiny.advance.repository.search;

import com.regitiny.catiny.advance.repository.search.base.TopicInterestBaseSearch;

/**
 * Spring Data Elasticsearch repository for the {@link com.regitiny.catiny.domain.TopicInterest} entity.
 * <p>
 * here contains complex queries with pure Elasticsearch syntax.
 * if you want to write simple query then you should write to {@link TopicInterestBaseSearch}
 */
public interface TopicInterestAdvanceSearch extends TopicInterestBaseSearch
{
}
