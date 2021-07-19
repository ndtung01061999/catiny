package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.Friend;
import com.regitiny.catiny.repository.FriendRepository;

/**
 * Spring Data SQL repository for the {@link Friend} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.FriendAdvanceRepository}
 */
public interface FriendBaseRepository extends BaseRepository<Friend>, CommonRepository<Friend>, FriendRepository
{
}
