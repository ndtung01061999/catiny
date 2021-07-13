package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.FollowUserDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.FollowUser}.
 */
@GeneratedByJHipster
public interface FollowUserService {
  /**
   * Save a followUser.
   *
   * @param followUserDTO the entity to save.
   * @return the persisted entity.
   */
  FollowUserDTO save(FollowUserDTO followUserDTO);

  /**
   * Partially updates a followUser.
   *
   * @param followUserDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<FollowUserDTO> partialUpdate(FollowUserDTO followUserDTO);

  /**
   * Get all the followUsers.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<FollowUserDTO> findAll(Pageable pageable);

  /**
   * Get the "id" followUser.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<FollowUserDTO> findOne(Long id);

  /**
   * Delete the "id" followUser.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the followUser corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<FollowUserDTO> search(String query, Pageable pageable);
}
