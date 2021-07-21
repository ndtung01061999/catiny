package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.RankGroup;
import com.regitiny.catiny.repository.RankGroupRepository;

/**
 * Spring Data SQL repository for the {@link RankGroup} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.RankGroupAdvanceRepository}
 */
public interface RankGroupBaseRepository extends BaseRepository<RankGroup>, CommonRepository<RankGroup>, RankGroupRepository
{
}
