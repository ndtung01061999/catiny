package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.TopicInterestDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.TopicInterest}.
 */
@GeneratedByJHipster
public interface TopicInterestService {
  /**
   * Save a topicInterest.
   *
   * @param topicInterestDTO the entity to save.
   * @return the persisted entity.
   */
  TopicInterestDTO save(TopicInterestDTO topicInterestDTO);

  /**
   * Partially updates a topicInterest.
   *
   * @param topicInterestDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<TopicInterestDTO> partialUpdate(TopicInterestDTO topicInterestDTO);

  /**
   * Get all the topicInterests.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<TopicInterestDTO> findAll(Pageable pageable);

  /**
   * Get all the topicInterests with eager load of many-to-many relationships.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<TopicInterestDTO> findAllWithEagerRelationships(Pageable pageable);

  /**
   * Get the "id" topicInterest.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<TopicInterestDTO> findOne(Long id);

  /**
   * Delete the "id" topicInterest.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the topicInterest corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<TopicInterestDTO> search(String query, Pageable pageable);
}
