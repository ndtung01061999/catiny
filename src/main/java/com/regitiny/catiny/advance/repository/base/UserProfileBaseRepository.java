package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.repository.UserProfileRepository;

/**
 * Spring Data SQL repository for the {@link com.regitiny.catiny.domain.UserProfile} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.UserProfileAdvanceRepository}
 */
public interface UserProfileBaseRepository extends BaseRepository, CommonRepository, UserProfileRepository
{
}
