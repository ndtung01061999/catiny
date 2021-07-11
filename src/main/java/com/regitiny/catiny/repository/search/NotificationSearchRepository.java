package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Notification;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Notification} entity.
 */
@GeneratedByJHipster
public interface NotificationSearchRepository extends ElasticsearchRepository<Notification, Long> {}
