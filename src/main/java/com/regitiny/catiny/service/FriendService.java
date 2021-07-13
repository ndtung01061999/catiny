package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.FriendDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.Friend}.
 */
@GeneratedByJHipster
public interface FriendService {
  /**
   * Save a friend.
   *
   * @param friendDTO the entity to save.
   * @return the persisted entity.
   */
  FriendDTO save(FriendDTO friendDTO);

  /**
   * Partially updates a friend.
   *
   * @param friendDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<FriendDTO> partialUpdate(FriendDTO friendDTO);

  /**
   * Get all the friends.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<FriendDTO> findAll(Pageable pageable);

  /**
   * Get the "id" friend.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<FriendDTO> findOne(Long id);

  /**
   * Delete the "id" friend.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the friend corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<FriendDTO> search(String query, Pageable pageable);
}
