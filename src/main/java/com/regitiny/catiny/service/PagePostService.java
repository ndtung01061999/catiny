package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.PagePostDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.PagePost}.
 */
@GeneratedByJHipster
public interface PagePostService {
  /**
   * Save a pagePost.
   *
   * @param pagePostDTO the entity to save.
   * @return the persisted entity.
   */
  PagePostDTO save(PagePostDTO pagePostDTO);

  /**
   * Partially updates a pagePost.
   *
   * @param pagePostDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<PagePostDTO> partialUpdate(PagePostDTO pagePostDTO);

  /**
   * Get all the pagePosts.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<PagePostDTO> findAll(Pageable pageable);

  /**
   * Get the "id" pagePost.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<PagePostDTO> findOne(Long id);

  /**
   * Delete the "id" pagePost.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the pagePost corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<PagePostDTO> search(String query, Pageable pageable);
}
