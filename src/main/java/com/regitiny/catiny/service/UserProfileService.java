package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.UserProfileDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.UserProfile}.
 */
@GeneratedByJHipster
public interface UserProfileService {
  /**
   * Save a userProfile.
   *
   * @param userProfileDTO the entity to save.
   * @return the persisted entity.
   */
  UserProfileDTO save(UserProfileDTO userProfileDTO);

  /**
   * Partially updates a userProfile.
   *
   * @param userProfileDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<UserProfileDTO> partialUpdate(UserProfileDTO userProfileDTO);

  /**
   * Get all the userProfiles.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<UserProfileDTO> findAll(Pageable pageable);

  /**
   * Get the "id" userProfile.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<UserProfileDTO> findOne(Long id);

  /**
   * Delete the "id" userProfile.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the userProfile corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<UserProfileDTO> search(String query, Pageable pageable);
}
