package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.Event;
import com.regitiny.catiny.repository.EventRepository;

/**
 * Spring Data SQL repository for the {@link Event} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.EventAdvanceRepository}
 */
public interface EventBaseRepository extends BaseRepository<Event>, CommonRepository<Event>, EventRepository
{
}
