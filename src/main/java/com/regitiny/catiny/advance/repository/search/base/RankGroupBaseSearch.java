package com.regitiny.catiny.advance.repository.search.base;

import com.regitiny.catiny.repository.search.RankGroupSearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link com.regitiny.catiny.domain.RankGroup} entity.
 * <p>
 * here contains simple queries same as JPA syntax.
 * if you want to write simple query then you should write to {@link com.regitiny.catiny.advance.repository.search.RankGroupAdvanceSearch}
 */
public interface RankGroupBaseSearch extends RankGroupSearchRepository
{
}
