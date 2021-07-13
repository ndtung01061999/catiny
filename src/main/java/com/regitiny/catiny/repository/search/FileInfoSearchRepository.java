package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.FileInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link FileInfo} entity.
 */
@GeneratedByJHipster
public interface FileInfoSearchRepository extends ElasticsearchRepository<FileInfo, Long> {}
