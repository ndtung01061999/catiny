package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.PagePost;
import com.regitiny.catiny.repository.PagePostRepository;

/**
 * Spring Data SQL repository for the {@link PagePost} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.PagePostAdvanceRepository}
 */
public interface PagePostBaseRepository extends BaseRepository<PagePost>, CommonRepository<PagePost>, PagePostRepository
{
}
