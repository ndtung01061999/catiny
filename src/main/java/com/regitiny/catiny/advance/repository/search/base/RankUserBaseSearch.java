package com.regitiny.catiny.advance.repository.search.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.RankUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link RankUser} entity.
 * <p>
 * here contains simple queries same as JPA syntax.
 * if you want to write simple query then you should write to {@link com.regitiny.catiny.advance.repository.search.RankUserAdvanceSearch}
 */
public interface RankUserBaseSearch extends BaseSearch<RankUser>, CommonRepository<RankUser>, ElasticsearchRepository<RankUser, Long>
{
}
