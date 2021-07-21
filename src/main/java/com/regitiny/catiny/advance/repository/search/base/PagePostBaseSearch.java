package com.regitiny.catiny.advance.repository.search.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.PagePost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PagePost} entity.
 * <p>
 * here contains simple queries same as JPA syntax.
 * if you want to write simple query then you should write to {@link com.regitiny.catiny.advance.repository.search.PagePostAdvanceSearch}
 */
public interface PagePostBaseSearch extends BaseSearch<PagePost>, CommonRepository<PagePost>, ElasticsearchRepository<PagePost, Long>
{
}
