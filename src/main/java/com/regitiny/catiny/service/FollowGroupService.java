package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.FollowGroupDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.FollowGroup}.
 */
@GeneratedByJHipster
public interface FollowGroupService {
  /**
   * Save a followGroup.
   *
   * @param followGroupDTO the entity to save.
   * @return the persisted entity.
   */
  FollowGroupDTO save(FollowGroupDTO followGroupDTO);

  /**
   * Partially updates a followGroup.
   *
   * @param followGroupDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<FollowGroupDTO> partialUpdate(FollowGroupDTO followGroupDTO);

  /**
   * Get all the followGroups.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<FollowGroupDTO> findAll(Pageable pageable);

  /**
   * Get the "id" followGroup.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<FollowGroupDTO> findOne(Long id);

  /**
   * Delete the "id" followGroup.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the followGroup corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<FollowGroupDTO> search(String query, Pageable pageable);
}
