package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.FollowPageDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.FollowPage}.
 */
@GeneratedByJHipster
public interface FollowPageService {
  /**
   * Save a followPage.
   *
   * @param followPageDTO the entity to save.
   * @return the persisted entity.
   */
  FollowPageDTO save(FollowPageDTO followPageDTO);

  /**
   * Partially updates a followPage.
   *
   * @param followPageDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<FollowPageDTO> partialUpdate(FollowPageDTO followPageDTO);

  /**
   * Get all the followPages.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<FollowPageDTO> findAll(Pageable pageable);

  /**
   * Get the "id" followPage.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<FollowPageDTO> findOne(Long id);

  /**
   * Delete the "id" followPage.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the followPage corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<FollowPageDTO> search(String query, Pageable pageable);
}
