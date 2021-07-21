package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.VideoLiveStreamBuffer;
import com.regitiny.catiny.repository.VideoLiveStreamBufferRepository;

/**
 * Spring Data SQL repository for the {@link VideoLiveStreamBuffer} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.VideoLiveStreamBufferAdvanceRepository}
 */
public interface VideoLiveStreamBufferBaseRepository extends BaseRepository<VideoLiveStreamBuffer>, CommonRepository<VideoLiveStreamBuffer>, VideoLiveStreamBufferRepository
{
}
