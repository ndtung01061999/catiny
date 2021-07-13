package com.regitiny.catiny.advance.repository.search;

import com.regitiny.catiny.advance.repository.search.base.ImageBaseSearch;

/**
 * Spring Data Elasticsearch repository for the {@link com.regitiny.catiny.domain.Image} entity.
 * <p>
 * here contains complex queries with pure Elasticsearch syntax.
 * if you want to write simple query then you should write to {@link ImageBaseSearch}
 */
public interface ImageAdvanceSearch extends ImageBaseSearch
{
}
