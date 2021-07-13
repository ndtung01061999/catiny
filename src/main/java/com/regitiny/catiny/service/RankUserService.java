package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.RankUserDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.RankUser}.
 */
@GeneratedByJHipster
public interface RankUserService {
  /**
   * Save a rankUser.
   *
   * @param rankUserDTO the entity to save.
   * @return the persisted entity.
   */
  RankUserDTO save(RankUserDTO rankUserDTO);

  /**
   * Partially updates a rankUser.
   *
   * @param rankUserDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<RankUserDTO> partialUpdate(RankUserDTO rankUserDTO);

  /**
   * Get all the rankUsers.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<RankUserDTO> findAll(Pageable pageable);

  /**
   * Get the "id" rankUser.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<RankUserDTO> findOne(Long id);

  /**
   * Delete the "id" rankUser.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the rankUser corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<RankUserDTO> search(String query, Pageable pageable);
}
