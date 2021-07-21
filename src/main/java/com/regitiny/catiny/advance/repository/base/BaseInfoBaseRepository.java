package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.repository.BaseInfoRepository;

/**
 * Spring Data SQL repository for the {@link BaseInfo} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.BaseInfoAdvanceRepository}
 */
public interface BaseInfoBaseRepository extends BaseRepository<BaseInfo>, CommonRepository<BaseInfo>, BaseInfoRepository
{
}
