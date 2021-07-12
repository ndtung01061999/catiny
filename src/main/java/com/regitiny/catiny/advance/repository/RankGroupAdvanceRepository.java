package com.regitiny.catiny.advance.repository;

import com.regitiny.catiny.advance.repository.base.RankGroupBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the {@link com.regitiny.catiny.domain.RankGroup} entity.
 * <p>
 * here contains complex queries with pure SQL or HQL syntax.
 * if you want to write simple query then you should write to :
 * {@link RankGroupBaseRepository}
 */
@Repository
@SuppressWarnings("unused")
public interface RankGroupAdvanceRepository extends RankGroupBaseRepository
{
}
