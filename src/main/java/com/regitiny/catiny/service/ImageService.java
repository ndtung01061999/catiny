package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.ImageDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.Image}.
 */
@GeneratedByJHipster
public interface ImageService {
  /**
   * Save a image.
   *
   * @param imageDTO the entity to save.
   * @return the persisted entity.
   */
  ImageDTO save(ImageDTO imageDTO);

  /**
   * Partially updates a image.
   *
   * @param imageDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<ImageDTO> partialUpdate(ImageDTO imageDTO);

  /**
   * Get all the images.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<ImageDTO> findAll(Pageable pageable);

  /**
   * Get the "id" image.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<ImageDTO> findOne(Long id);

  /**
   * Delete the "id" image.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the image corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<ImageDTO> search(String query, Pageable pageable);
}
