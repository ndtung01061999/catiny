package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.GroupProfileDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.GroupProfile}.
 */
@GeneratedByJHipster
public interface GroupProfileService {
  /**
   * Save a groupProfile.
   *
   * @param groupProfileDTO the entity to save.
   * @return the persisted entity.
   */
  GroupProfileDTO save(GroupProfileDTO groupProfileDTO);

  /**
   * Partially updates a groupProfile.
   *
   * @param groupProfileDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<GroupProfileDTO> partialUpdate(GroupProfileDTO groupProfileDTO);

  /**
   * Get all the groupProfiles.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<GroupProfileDTO> findAll(Pageable pageable);
  /**
   * Get all the GroupProfileDTO where Group is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<GroupProfileDTO> findAllWhereGroupIsNull();

  /**
   * Get the "id" groupProfile.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<GroupProfileDTO> findOne(Long id);

  /**
   * Delete the "id" groupProfile.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the groupProfile corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<GroupProfileDTO> search(String query, Pageable pageable);
}
