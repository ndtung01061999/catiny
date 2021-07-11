package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.GroupPostDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.GroupPost}.
 */
@GeneratedByJHipster
public interface GroupPostService {
  /**
   * Save a groupPost.
   *
   * @param groupPostDTO the entity to save.
   * @return the persisted entity.
   */
  GroupPostDTO save(GroupPostDTO groupPostDTO);

  /**
   * Partially updates a groupPost.
   *
   * @param groupPostDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<GroupPostDTO> partialUpdate(GroupPostDTO groupPostDTO);

  /**
   * Get all the groupPosts.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<GroupPostDTO> findAll(Pageable pageable);

  /**
   * Get the "id" groupPost.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<GroupPostDTO> findOne(Long id);

  /**
   * Delete the "id" groupPost.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the groupPost corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<GroupPostDTO> search(String query, Pageable pageable);
}
