package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.ClassInfoDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.ClassInfo}.
 */
@GeneratedByJHipster
public interface ClassInfoService {
  /**
   * Save a classInfo.
   *
   * @param classInfoDTO the entity to save.
   * @return the persisted entity.
   */
  ClassInfoDTO save(ClassInfoDTO classInfoDTO);

  /**
   * Partially updates a classInfo.
   *
   * @param classInfoDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<ClassInfoDTO> partialUpdate(ClassInfoDTO classInfoDTO);

  /**
   * Get all the classInfos.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<ClassInfoDTO> findAll(Pageable pageable);

  /**
   * Get the "id" classInfo.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<ClassInfoDTO> findOne(Long id);

  /**
   * Delete the "id" classInfo.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the classInfo corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<ClassInfoDTO> search(String query, Pageable pageable);
}
