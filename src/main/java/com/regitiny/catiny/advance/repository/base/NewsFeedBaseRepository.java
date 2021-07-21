package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.NewsFeed;
import com.regitiny.catiny.repository.NewsFeedRepository;

/**
 * Spring Data SQL repository for the {@link NewsFeed} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.NewsFeedAdvanceRepository}
 */
public interface NewsFeedBaseRepository extends BaseRepository<NewsFeed>, CommonRepository<NewsFeed>, NewsFeedRepository
{
}
