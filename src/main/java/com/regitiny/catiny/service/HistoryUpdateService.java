package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.HistoryUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.HistoryUpdate}.
 */
@GeneratedByJHipster
public interface HistoryUpdateService
{
  /**
   * Save a historyUpdate.
   *
   * @param historyUpdateDTO the entity to save.
   * @return the persisted entity.
   */
  HistoryUpdateDTO save(HistoryUpdateDTO historyUpdateDTO);


  /**
   * Partially updates a historyUpdate.
   *
   * @param historyUpdateDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<HistoryUpdateDTO> partialUpdate(HistoryUpdateDTO historyUpdateDTO);


  /**
   * Get all the historyUpdates.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<HistoryUpdateDTO> findAll(Pageable pageable);


  /**
   * Get the "id" historyUpdate.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<HistoryUpdateDTO> findOne(Long id);


  /**
   * Delete the "id" historyUpdate.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);


  /**
   * Search for the historyUpdate corresponding to the query.
   *
   * @param query    the query of the search.
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<HistoryUpdateDTO> search(String query, Pageable pageable);
}
