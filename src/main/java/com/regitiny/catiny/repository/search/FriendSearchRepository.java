package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Friend;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Friend} entity.
 */
@GeneratedByJHipster
public interface FriendSearchRepository extends ElasticsearchRepository<Friend, Long> {}
