package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.MasterUser}.
 */
@GeneratedByJHipster
public interface MasterUserService {
  /**
   * Save a masterUser.
   *
   * @param masterUserDTO the entity to save.
   * @return the persisted entity.
   */
  MasterUserDTO save(MasterUserDTO masterUserDTO);

  /**
   * Partially updates a masterUser.
   *
   * @param masterUserDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<MasterUserDTO> partialUpdate(MasterUserDTO masterUserDTO);

  /**
   * Get all the masterUsers.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<MasterUserDTO> findAll(Pageable pageable);

  /**
   * Get all the masterUsers with eager load of many-to-many relationships.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<MasterUserDTO> findAllWithEagerRelationships(Pageable pageable);

  /**
   * Get the "id" masterUser.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<MasterUserDTO> findOne(Long id);

  /**
   * Delete the "id" masterUser.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the masterUser corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<MasterUserDTO> search(String query, Pageable pageable);
}
