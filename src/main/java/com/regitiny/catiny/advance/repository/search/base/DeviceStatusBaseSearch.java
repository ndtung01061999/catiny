package com.regitiny.catiny.advance.repository.search.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.DeviceStatus;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link DeviceStatus} entity.
 * <p>
 * here contains simple queries same as JPA syntax.
 * if you want to write simple query then you should write to {@link com.regitiny.catiny.advance.repository.search.DeviceStatusAdvanceSearch}
 */
public interface DeviceStatusBaseSearch extends BaseSearch<DeviceStatus>, CommonRepository<DeviceStatus>, ElasticsearchRepository<DeviceStatus, Long>
{
}
