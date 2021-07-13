package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.VideoStreamDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.VideoStream}.
 */
@GeneratedByJHipster
public interface VideoStreamService {
  /**
   * Save a videoStream.
   *
   * @param videoStreamDTO the entity to save.
   * @return the persisted entity.
   */
  VideoStreamDTO save(VideoStreamDTO videoStreamDTO);

  /**
   * Partially updates a videoStream.
   *
   * @param videoStreamDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<VideoStreamDTO> partialUpdate(VideoStreamDTO videoStreamDTO);

  /**
   * Get all the videoStreams.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<VideoStreamDTO> findAll(Pageable pageable);

  /**
   * Get the "id" videoStream.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<VideoStreamDTO> findOne(Long id);

  /**
   * Delete the "id" videoStream.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the videoStream corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<VideoStreamDTO> search(String query, Pageable pageable);
}
