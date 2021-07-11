package com.regitiny.catiny.advance.repository;


import com.regitiny.catiny.advance.repository.base.MessageContentBaseRepository;
import com.regitiny.catiny.domain.MessageContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Instant;

/**
 * Spring Data SQL repository for the MessageContent entity.
 * <p>
 * here contains complex queries with pure SQL or HQL syntax.
 * if you want to write simple query then you should write to :
 * {@link MessageContentBaseRepository}
 */
@SuppressWarnings("unused")
@Repository
public interface MessageContentAdvanceRepository extends MessageContentBaseRepository
{
    @Override
    default Page<MessageContent> findAllByGroupIdAndCreatedDateGreaterThanEqualOrderByCreatedDateDesc(String groupId, Instant createdDate, Pageable pageable) {
        return null;
    }
}
