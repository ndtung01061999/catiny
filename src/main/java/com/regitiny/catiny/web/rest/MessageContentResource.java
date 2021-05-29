package com.regitiny.catiny.web.rest;

import com.regitiny.catiny.repository.MessageContentRepository;
import com.regitiny.catiny.service.MessageContentService;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link com.regitiny.catiny.domain.MessageContent}.
 */
@RestController
@RequestMapping("/api")
public class MessageContentResource {

  private final Logger log = LoggerFactory.getLogger(MessageContentResource.class);

  private static final String ENTITY_NAME = "messageContent";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final MessageContentService messageContentService;

  private final MessageContentRepository messageContentRepository;

  public MessageContentResource(MessageContentService messageContentService, MessageContentRepository messageContentRepository) {
    this.messageContentService = messageContentService;
    this.messageContentRepository = messageContentRepository;
  }

  /**
   * {@code POST  /message-contents} : Create a new messageContent.
   *
   * @param messageContentDTO the messageContentDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messageContentDTO, or with status {@code 400 (Bad Request)} if the messageContent has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/message-contents")
  public ResponseEntity<MessageContentDTO> createMessageContent(@Valid @RequestBody MessageContentDTO messageContentDTO)
    throws URISyntaxException {
    log.debug("REST request to save MessageContent : {}", messageContentDTO);
    if (messageContentDTO.getId() != null) {
      throw new BadRequestAlertException("A new messageContent cannot already have an ID", ENTITY_NAME, "idexists");
    }
    MessageContentDTO result = messageContentService.save(messageContentDTO);
    return ResponseEntity
      .created(new URI("/api/message-contents/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /message-contents/:id} : Updates an existing messageContent.
   *
   * @param id the id of the messageContentDTO to save.
   * @param messageContentDTO the messageContentDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messageContentDTO,
   * or with status {@code 400 (Bad Request)} if the messageContentDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the messageContentDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/message-contents/{id}")
  public ResponseEntity<MessageContentDTO> updateMessageContent(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody MessageContentDTO messageContentDTO
  ) throws URISyntaxException {
    log.debug("REST request to update MessageContent : {}, {}", id, messageContentDTO);
    if (messageContentDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, messageContentDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!messageContentRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    MessageContentDTO result = messageContentService.save(messageContentDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, messageContentDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /message-contents/:id} : Partial updates given fields of an existing messageContent, field will ignore if it is null
   *
   * @param id the id of the messageContentDTO to save.
   * @param messageContentDTO the messageContentDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messageContentDTO,
   * or with status {@code 400 (Bad Request)} if the messageContentDTO is not valid,
   * or with status {@code 404 (Not Found)} if the messageContentDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the messageContentDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/message-contents/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<MessageContentDTO> partialUpdateMessageContent(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody MessageContentDTO messageContentDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update MessageContent partially : {}, {}", id, messageContentDTO);
    if (messageContentDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, messageContentDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!messageContentRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<MessageContentDTO> result = messageContentService.partialUpdate(messageContentDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, messageContentDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /message-contents} : get all the messageContents.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of messageContents in body.
   */
  @GetMapping("/message-contents")
  public ResponseEntity<List<MessageContentDTO>> getAllMessageContents(Pageable pageable) {
    log.debug("REST request to get a page of MessageContents");
    Page<MessageContentDTO> page = messageContentService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /message-contents/:id} : get the "id" messageContent.
   *
   * @param id the id of the messageContentDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the messageContentDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/message-contents/{id}")
  public ResponseEntity<MessageContentDTO> getMessageContent(@PathVariable Long id) {
    log.debug("REST request to get MessageContent : {}", id);
    Optional<MessageContentDTO> messageContentDTO = messageContentService.findOne(id);
    return ResponseUtil.wrapOrNotFound(messageContentDTO);
  }

  /**
   * {@code DELETE  /message-contents/:id} : delete the "id" messageContent.
   *
   * @param id the id of the messageContentDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/message-contents/{id}")
  public ResponseEntity<Void> deleteMessageContent(@PathVariable Long id) {
    log.debug("REST request to delete MessageContent : {}", id);
    messageContentService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/message-contents?query=:query} : search for the messageContent corresponding
   * to the query.
   *
   * @param query the query of the messageContent search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/message-contents")
  public ResponseEntity<List<MessageContentDTO>> searchMessageContents(@RequestParam String query, Pageable pageable)
  {
    log.debug("REST request to search for a page of MessageContents for query {}", query);
    Page<MessageContentDTO> page = messageContentService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

}
