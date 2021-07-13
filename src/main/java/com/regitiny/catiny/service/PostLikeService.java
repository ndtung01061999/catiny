package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.PostLikeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.PostLike}.
 */
@GeneratedByJHipster
public interface PostLikeService {
  /**
   * Save a postLike.
   *
   * @param postLikeDTO the entity to save.
   * @return the persisted entity.
   */
  PostLikeDTO save(PostLikeDTO postLikeDTO);

  /**
   * Partially updates a postLike.
   *
   * @param postLikeDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<PostLikeDTO> partialUpdate(PostLikeDTO postLikeDTO);

  /**
   * Get all the postLikes.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<PostLikeDTO> findAll(Pageable pageable);

  /**
   * Get the "id" postLike.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<PostLikeDTO> findOne(Long id);

  /**
   * Delete the "id" postLike.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the postLike corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<PostLikeDTO> search(String query, Pageable pageable);
}
