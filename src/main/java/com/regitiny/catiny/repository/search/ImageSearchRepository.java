package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Image;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Image} entity.
 */
@GeneratedByJHipster
public interface ImageSearchRepository extends ElasticsearchRepository<Image, Long> {}
