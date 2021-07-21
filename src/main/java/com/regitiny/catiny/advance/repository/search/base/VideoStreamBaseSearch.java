package com.regitiny.catiny.advance.repository.search.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.VideoStream;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link VideoStream} entity.
 * <p>
 * here contains simple queries same as JPA syntax.
 * if you want to write simple query then you should write to {@link com.regitiny.catiny.advance.repository.search.VideoStreamAdvanceSearch}
 */
public interface VideoStreamBaseSearch extends BaseSearch<VideoStream>, CommonRepository<VideoStream>, ElasticsearchRepository<VideoStream, Long>
{
}
