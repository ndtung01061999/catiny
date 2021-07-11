package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Video;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Video} entity.
 */
@GeneratedByJHipster
public interface VideoSearchRepository extends ElasticsearchRepository<Video, Long> {}
