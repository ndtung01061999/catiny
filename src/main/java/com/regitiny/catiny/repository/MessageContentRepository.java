package com.regitiny.catiny.repository;

import com.regitiny.catiny.domain.MessageContent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MessageContent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageContentRepository extends JpaRepository<MessageContent, Long> {}
