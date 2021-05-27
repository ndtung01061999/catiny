package com.regitiny.catiny.repository;

import com.regitiny.catiny.domain.MessageContent;
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
public interface MessageContentRepository extends JpaRepository<MessageContent, Long>
{
  Page<MessageContent> findAllByGroupIdAndCreatedDateGreaterThanEqualOrderByCreatedDateDesc(String groupId, Instant createdDate, Pageable pageable);
}
