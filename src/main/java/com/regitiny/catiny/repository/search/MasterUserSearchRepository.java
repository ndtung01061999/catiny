package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.MasterUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MasterUser} entity.
 */
@GeneratedByJHipster
public interface MasterUserSearchRepository extends ElasticsearchRepository<MasterUser, Long> {}
