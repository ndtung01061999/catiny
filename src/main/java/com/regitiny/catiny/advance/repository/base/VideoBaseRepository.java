package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.Video;
import com.regitiny.catiny.repository.VideoRepository;

/**
 * Spring Data SQL repository for the {@link Video} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.VideoAdvanceRepository}
 */
public interface VideoBaseRepository extends BaseRepository<Video>, CommonRepository<Video>, VideoRepository
{
}
