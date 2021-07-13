package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.DeviceStatusDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.DeviceStatus}.
 */
@GeneratedByJHipster
public interface DeviceStatusService {
  /**
   * Save a deviceStatus.
   *
   * @param deviceStatusDTO the entity to save.
   * @return the persisted entity.
   */
  DeviceStatusDTO save(DeviceStatusDTO deviceStatusDTO);

  /**
   * Partially updates a deviceStatus.
   *
   * @param deviceStatusDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<DeviceStatusDTO> partialUpdate(DeviceStatusDTO deviceStatusDTO);

  /**
   * Get all the deviceStatuses.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<DeviceStatusDTO> findAll(Pageable pageable);

  /**
   * Get the "id" deviceStatus.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<DeviceStatusDTO> findOne(Long id);

  /**
   * Delete the "id" deviceStatus.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the deviceStatus corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<DeviceStatusDTO> search(String query, Pageable pageable);
}
