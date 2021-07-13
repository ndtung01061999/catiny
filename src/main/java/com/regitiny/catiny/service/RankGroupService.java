package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.RankGroupDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.RankGroup}.
 */
@GeneratedByJHipster
public interface RankGroupService {
  /**
   * Save a rankGroup.
   *
   * @param rankGroupDTO the entity to save.
   * @return the persisted entity.
   */
  RankGroupDTO save(RankGroupDTO rankGroupDTO);

  /**
   * Partially updates a rankGroup.
   *
   * @param rankGroupDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<RankGroupDTO> partialUpdate(RankGroupDTO rankGroupDTO);

  /**
   * Get all the rankGroups.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<RankGroupDTO> findAll(Pageable pageable);

  /**
   * Get the "id" rankGroup.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<RankGroupDTO> findOne(Long id);

  /**
   * Delete the "id" rankGroup.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the rankGroup corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<RankGroupDTO> search(String query, Pageable pageable);
}
