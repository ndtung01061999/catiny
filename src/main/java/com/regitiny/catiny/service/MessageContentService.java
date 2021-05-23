package com.regitiny.catiny.service;

import com.regitiny.catiny.service.dto.MessageContentDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.MessageContent}.
 */
public interface MessageContentService {
    /**
     * Save a messageContent.
     *
     * @param messageContentDTO the entity to save.
     * @return the persisted entity.
     */
    MessageContentDTO save(MessageContentDTO messageContentDTO);

    /**
     * Partially updates a messageContent.
     *
     * @param messageContentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<MessageContentDTO> partialUpdate(MessageContentDTO messageContentDTO);

    /**
     * Get all the messageContents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MessageContentDTO> findAll(Pageable pageable);

    /**
     * Get the "id" messageContent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MessageContentDTO> findOne(Long id);

    /**
     * Delete the "id" messageContent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the messageContent corresponding to the query.
     *
     * @param query the query of the search.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MessageContentDTO> search(String query, Pageable pageable);
}
