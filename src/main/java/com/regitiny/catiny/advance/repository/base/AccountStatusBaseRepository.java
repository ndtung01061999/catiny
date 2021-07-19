package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.AccountStatus;
import com.regitiny.catiny.repository.AccountStatusRepository;

/**
 * Spring Data SQL repository for the {@link AccountStatus} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.AccountStatusAdvanceRepository}
 */
public interface AccountStatusBaseRepository extends BaseRepository<AccountStatus>, CommonRepository<AccountStatus>, AccountStatusRepository
{
}
