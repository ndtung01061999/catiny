package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.PageProfile;
import com.regitiny.catiny.repository.PageProfileRepository;

/**
 * Spring Data SQL repository for the {@link PageProfile} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.PageProfileAdvanceRepository}
 */
public interface PageProfileBaseRepository extends BaseRepository<PageProfile>, CommonRepository<PageProfile>, PageProfileRepository
{
}
