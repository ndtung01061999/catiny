package com.regitiny.catiny.repository;

import com.regitiny.catiny.domain.MessageGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the MessageGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageGroupRepository extends JpaRepository<MessageGroup, Long> {
  Optional<List<MessageGroup>> findAllByGroupId(String groupId);


  Page<MessageGroup> findAllByUserId(Long userId, Pageable pageable);


  Optional<MessageGroup> findByGroupIdAndUserId(String groupId, Long userId);
}
