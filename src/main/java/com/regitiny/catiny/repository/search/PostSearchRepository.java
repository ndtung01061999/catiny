package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Post} entity.
 */
@GeneratedByJHipster
public interface PostSearchRepository extends ElasticsearchRepository<Post, Long> {}
