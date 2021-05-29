package com.regitiny.catiny.advance.repository;

import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.repository.MessageContentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

/**
 * Spring Data SQL repository for the MessageContent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageContentAdvanceRepository extends MessageContentRepository
{
  Page<MessageContent> findAllByGroupIdAndCreatedDateGreaterThanEqualOrderByCreatedDateDesc(String groupId, Instant createdDate, Pageable pageable);
}
