package com.regitiny.catiny.service;

import com.regitiny.catiny.service.dto.MessageGroupDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.MessageGroup}.
 */
public interface MessageGroupService {
  /**
   * Save a messageGroup.
   *
   * @param messageGroupDTO the entity to save.
   * @return the persisted entity.
   */
  MessageGroupDTO save(MessageGroupDTO messageGroupDTO);

  /**
   * Partially updates a messageGroup.
   *
   * @param messageGroupDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<MessageGroupDTO> partialUpdate(MessageGroupDTO messageGroupDTO);

  /**
   * Get all the messageGroups.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<MessageGroupDTO> findAll(Pageable pageable);

  /**
   * Get the "id" messageGroup.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<MessageGroupDTO> findOne(Long id);

  /**
   * Delete the "id" messageGroup.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the messageGroup corresponding to the query.
   *
   * @param query    the query of the search.
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<MessageGroupDTO> search(String query, Pageable pageable);
  // custom by yuvytung

  List<MessageGroupDTO> createMessageGroup(String groupName, String lastContent, List<Long> userIds);

  List<MessageGroupDTO> addUserToGroup(List<Long> userIds, String groupId);
}
