package com.regitiny.catiny.advance.repository;

import com.regitiny.catiny.advance.repository.base.MessageGroupBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MessageGroup entity.
 * <p>
 * here contains complex queries with pure SQL or HQL syntax.
 * if you want to write simple query then you should write to :
 * {@link MessageGroupBaseRepository}
 */
@SuppressWarnings("unused")
@Repository
public interface MessageGroupAdvanceRepository extends MessageGroupBaseRepository
{
}
