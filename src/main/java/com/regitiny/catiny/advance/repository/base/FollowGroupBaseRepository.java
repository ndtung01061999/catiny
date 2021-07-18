package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.repository.FollowGroupRepository;

/**
 * Spring Data SQL repository for the {@link com.regitiny.catiny.domain.FollowGroup} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.FollowGroupAdvanceRepository}
 */
public interface FollowGroupBaseRepository extends BaseRepository, CommonRepository, FollowGroupRepository
{
}
