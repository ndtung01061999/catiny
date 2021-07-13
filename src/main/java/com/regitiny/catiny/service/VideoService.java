package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.VideoDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.Video}.
 */
@GeneratedByJHipster
public interface VideoService {
  /**
   * Save a video.
   *
   * @param videoDTO the entity to save.
   * @return the persisted entity.
   */
  VideoDTO save(VideoDTO videoDTO);

  /**
   * Partially updates a video.
   *
   * @param videoDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<VideoDTO> partialUpdate(VideoDTO videoDTO);

  /**
   * Get all the videos.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<VideoDTO> findAll(Pageable pageable);
  /**
   * Get all the VideoDTO where VideoStream is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<VideoDTO> findAllWhereVideoStreamIsNull();

  /**
   * Get the "id" video.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<VideoDTO> findOne(Long id);

  /**
   * Delete the "id" video.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the video corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<VideoDTO> search(String query, Pageable pageable);
}
