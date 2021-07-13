package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.PageProfileDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.PageProfile}.
 */
@GeneratedByJHipster
public interface PageProfileService {
  /**
   * Save a pageProfile.
   *
   * @param pageProfileDTO the entity to save.
   * @return the persisted entity.
   */
  PageProfileDTO save(PageProfileDTO pageProfileDTO);

  /**
   * Partially updates a pageProfile.
   *
   * @param pageProfileDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<PageProfileDTO> partialUpdate(PageProfileDTO pageProfileDTO);

  /**
   * Get all the pageProfiles.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<PageProfileDTO> findAll(Pageable pageable);
  /**
   * Get all the PageProfileDTO where Page is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<PageProfileDTO> findAllWherePageIsNull();

  /**
   * Get the "id" pageProfile.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<PageProfileDTO> findOne(Long id);

  /**
   * Delete the "id" pageProfile.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the pageProfile corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<PageProfileDTO> search(String query, Pageable pageable);
}
