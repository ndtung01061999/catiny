package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.UserProfile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link UserProfile} entity.
 */
@GeneratedByJHipster
public interface UserProfileSearchRepository extends ElasticsearchRepository<UserProfile, Long> {}
