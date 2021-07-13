package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.VideoLiveStreamBufferDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.VideoLiveStreamBuffer}.
 */
@GeneratedByJHipster
public interface VideoLiveStreamBufferService {
  /**
   * Save a videoLiveStreamBuffer.
   *
   * @param videoLiveStreamBufferDTO the entity to save.
   * @return the persisted entity.
   */
  VideoLiveStreamBufferDTO save(VideoLiveStreamBufferDTO videoLiveStreamBufferDTO);

  /**
   * Partially updates a videoLiveStreamBuffer.
   *
   * @param videoLiveStreamBufferDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<VideoLiveStreamBufferDTO> partialUpdate(VideoLiveStreamBufferDTO videoLiveStreamBufferDTO);

  /**
   * Get all the videoLiveStreamBuffers.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<VideoLiveStreamBufferDTO> findAll(Pageable pageable);

  /**
   * Get the "id" videoLiveStreamBuffer.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<VideoLiveStreamBufferDTO> findOne(Long id);

  /**
   * Delete the "id" videoLiveStreamBuffer.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the videoLiveStreamBuffer corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<VideoLiveStreamBufferDTO> search(String query, Pageable pageable);
}
