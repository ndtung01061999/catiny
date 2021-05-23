package com.regitiny.catiny.repository;

import com.regitiny.catiny.domain.MessageGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MessageGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageGroupRepository extends JpaRepository<MessageGroup, Long> {}
