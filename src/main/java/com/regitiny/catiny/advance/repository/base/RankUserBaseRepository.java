package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.RankUser;
import com.regitiny.catiny.repository.RankUserRepository;

/**
 * Spring Data SQL repository for the {@link RankUser} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.RankUserAdvanceRepository}
 */
public interface RankUserBaseRepository extends BaseRepository<RankUser>, CommonRepository<RankUser>, RankUserRepository
{
}
