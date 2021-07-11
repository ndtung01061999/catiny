package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.HanhChinhVNDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.HanhChinhVN}.
 */
@GeneratedByJHipster
public interface HanhChinhVNService {
  /**
   * Save a hanhChinhVN.
   *
   * @param hanhChinhVNDTO the entity to save.
   * @return the persisted entity.
   */
  HanhChinhVNDTO save(HanhChinhVNDTO hanhChinhVNDTO);

  /**
   * Partially updates a hanhChinhVN.
   *
   * @param hanhChinhVNDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<HanhChinhVNDTO> partialUpdate(HanhChinhVNDTO hanhChinhVNDTO);

  /**
   * Get all the hanhChinhVNS.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<HanhChinhVNDTO> findAll(Pageable pageable);

  /**
   * Get the "id" hanhChinhVN.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<HanhChinhVNDTO> findOne(Long id);

  /**
   * Delete the "id" hanhChinhVN.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the hanhChinhVN corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<HanhChinhVNDTO> search(String query, Pageable pageable);
}
