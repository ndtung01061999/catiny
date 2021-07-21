package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Permission;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Permission} entity.
 */
@GeneratedByJHipster
public interface PermissionSearchRepository extends ElasticsearchRepository<Permission, Long> {}
