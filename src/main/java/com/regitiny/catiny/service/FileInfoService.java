package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.FileInfoDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.FileInfo}.
 */
@GeneratedByJHipster
public interface FileInfoService {
  /**
   * Save a fileInfo.
   *
   * @param fileInfoDTO the entity to save.
   * @return the persisted entity.
   */
  FileInfoDTO save(FileInfoDTO fileInfoDTO);

  /**
   * Partially updates a fileInfo.
   *
   * @param fileInfoDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<FileInfoDTO> partialUpdate(FileInfoDTO fileInfoDTO);

  /**
   * Get all the fileInfos.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<FileInfoDTO> findAll(Pageable pageable);

  /**
   * Get the "id" fileInfo.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<FileInfoDTO> findOne(Long id);

  /**
   * Delete the "id" fileInfo.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the fileInfo corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<FileInfoDTO> search(String query, Pageable pageable);
}
