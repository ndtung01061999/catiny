package com.regitiny.catiny.advance.repository.search.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.VideoLiveStreamBuffer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link VideoLiveStreamBuffer} entity.
 * <p>
 * here contains simple queries same as JPA syntax.
 * if you want to write simple query then you should write to {@link com.regitiny.catiny.advance.repository.search.VideoLiveStreamBufferAdvanceSearch}
 */
public interface VideoLiveStreamBufferBaseSearch extends BaseSearch, CommonRepository, ElasticsearchRepository<VideoLiveStreamBuffer, Long>
{
}
