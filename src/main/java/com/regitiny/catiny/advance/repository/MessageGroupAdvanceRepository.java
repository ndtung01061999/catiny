package com.regitiny.catiny.advance.repository;

import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.repository.MessageGroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the MessageGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageGroupAdvanceRepository extends MessageGroupRepository
{
  Optional<List<MessageGroup>> findAllByGroupId(String groupId);


  Page<MessageGroup> findAllByUserId(Long userId, Pageable pageable);


  Optional<MessageGroup> findByGroupIdAndUserId(String groupId, Long userId);
}
