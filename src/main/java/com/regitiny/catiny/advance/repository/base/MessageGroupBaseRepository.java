package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.repository.MessageGroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the MessageGroup entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.MessageGroupAdvanceRepository}
 */
public interface MessageGroupBaseRepository extends MessageGroupRepository
{
  default Optional<List<MessageGroup>> findAllByGroupId(String groupId)
  {
    return null;
  }


  default Page<MessageGroup> findAllByUserId(Long userId, Pageable pageable)
  {
    return null;
  }


  default Optional<MessageGroup> findByGroupIdAndUserId(String groupId, Long userId)
  {
    return null;
  }
}
