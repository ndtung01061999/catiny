package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.NewsFeedDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.NewsFeed}.
 */
@GeneratedByJHipster
public interface NewsFeedService {
  /**
   * Save a newsFeed.
   *
   * @param newsFeedDTO the entity to save.
   * @return the persisted entity.
   */
  NewsFeedDTO save(NewsFeedDTO newsFeedDTO);

  /**
   * Partially updates a newsFeed.
   *
   * @param newsFeedDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<NewsFeedDTO> partialUpdate(NewsFeedDTO newsFeedDTO);

  /**
   * Get all the newsFeeds.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<NewsFeedDTO> findAll(Pageable pageable);

  /**
   * Get the "id" newsFeed.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<NewsFeedDTO> findOne(Long id);

  /**
   * Delete the "id" newsFeed.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the newsFeed corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<NewsFeedDTO> search(String query, Pageable pageable);
}
