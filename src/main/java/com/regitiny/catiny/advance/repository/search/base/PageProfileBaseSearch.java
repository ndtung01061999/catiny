package com.regitiny.catiny.advance.repository.search.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.PageProfile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PageProfile} entity.
 * <p>
 * here contains simple queries same as JPA syntax.
 * if you want to write simple query then you should write to {@link com.regitiny.catiny.advance.repository.search.PageProfileAdvanceSearch}
 */
public interface PageProfileBaseSearch extends BaseSearch<PageProfile>, CommonRepository<PageProfile>, ElasticsearchRepository<PageProfile, Long>
{
}
