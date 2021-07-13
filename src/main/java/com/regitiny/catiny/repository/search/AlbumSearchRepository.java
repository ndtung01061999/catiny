package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Album;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Album} entity.
 */
@GeneratedByJHipster
public interface AlbumSearchRepository extends ElasticsearchRepository<Album, Long> {}
