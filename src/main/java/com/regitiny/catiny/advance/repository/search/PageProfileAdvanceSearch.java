package com.regitiny.catiny.advance.repository.search;

import com.regitiny.catiny.advance.repository.search.base.PageProfileBaseSearch;

/**
 * Spring Data Elasticsearch repository for the {@link com.regitiny.catiny.domain.PageProfile} entity.
 * <p>
 * here contains complex queries with pure Elasticsearch syntax.
 * if you want to write simple query then you should write to {@link PageProfileBaseSearch}
 */
public interface PageProfileAdvanceSearch extends PageProfileBaseSearch
{
}
