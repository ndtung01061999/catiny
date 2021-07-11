package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.AccountStatusDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.AccountStatus}.
 */
@GeneratedByJHipster
public interface AccountStatusService {
  /**
   * Save a accountStatus.
   *
   * @param accountStatusDTO the entity to save.
   * @return the persisted entity.
   */
  AccountStatusDTO save(AccountStatusDTO accountStatusDTO);

  /**
   * Partially updates a accountStatus.
   *
   * @param accountStatusDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<AccountStatusDTO> partialUpdate(AccountStatusDTO accountStatusDTO);

  /**
   * Get all the accountStatuses.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<AccountStatusDTO> findAll(Pageable pageable);

  /**
   * Get the "id" accountStatus.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<AccountStatusDTO> findOne(Long id);

  /**
   * Delete the "id" accountStatus.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the accountStatus corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<AccountStatusDTO> search(String query, Pageable pageable);
}
