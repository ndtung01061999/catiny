package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.FollowUser;
import com.regitiny.catiny.repository.FollowUserRepository;

/**
 * Spring Data SQL repository for the {@link FollowUser} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.FollowUserAdvanceRepository}
 */
public interface FollowUserBaseRepository extends BaseRepository<FollowUser>, CommonRepository<FollowUser>, FollowUserRepository
{
}
