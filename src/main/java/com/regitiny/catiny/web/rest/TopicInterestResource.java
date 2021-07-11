package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.TopicInterestRepository;
import com.regitiny.catiny.service.TopicInterestQueryService;
import com.regitiny.catiny.service.TopicInterestService;
import com.regitiny.catiny.service.criteria.TopicInterestCriteria;
import com.regitiny.catiny.service.dto.TopicInterestDTO;
import com.regitiny.catiny.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.regitiny.catiny.domain.TopicInterest}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class TopicInterestResource {

  private final Logger log = LoggerFactory.getLogger(TopicInterestResource.class);

  private static final String ENTITY_NAME = "topicInterest";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final TopicInterestService topicInterestService;

  private final TopicInterestRepository topicInterestRepository;

  private final TopicInterestQueryService topicInterestQueryService;

  public TopicInterestResource(
    TopicInterestService topicInterestService,
    TopicInterestRepository topicInterestRepository,
    TopicInterestQueryService topicInterestQueryService
  ) {
    this.topicInterestService = topicInterestService;
    this.topicInterestRepository = topicInterestRepository;
    this.topicInterestQueryService = topicInterestQueryService;
  }

  /**
   * {@code POST  /topic-interests} : Create a new topicInterest.
   *
   * @param topicInterestDTO the topicInterestDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new topicInterestDTO, or with status {@code 400 (Bad Request)} if the topicInterest has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/topic-interests")
  public ResponseEntity<TopicInterestDTO> createTopicInterest(@Valid @RequestBody TopicInterestDTO topicInterestDTO)
    throws URISyntaxException {
    log.debug("REST request to save TopicInterest : {}", topicInterestDTO);
    if (topicInterestDTO.getId() != null) {
      throw new BadRequestAlertException("A new topicInterest cannot already have an ID", ENTITY_NAME, "idexists");
    }
    TopicInterestDTO result = topicInterestService.save(topicInterestDTO);
    return ResponseEntity
      .created(new URI("/api/topic-interests/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /topic-interests/:id} : Updates an existing topicInterest.
   *
   * @param id the id of the topicInterestDTO to save.
   * @param topicInterestDTO the topicInterestDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated topicInterestDTO,
   * or with status {@code 400 (Bad Request)} if the topicInterestDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the topicInterestDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/topic-interests/{id}")
  public ResponseEntity<TopicInterestDTO> updateTopicInterest(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody TopicInterestDTO topicInterestDTO
  ) throws URISyntaxException {
    log.debug("REST request to update TopicInterest : {}, {}", id, topicInterestDTO);
    if (topicInterestDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, topicInterestDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!topicInterestRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    TopicInterestDTO result = topicInterestService.save(topicInterestDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, topicInterestDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /topic-interests/:id} : Partial updates given fields of an existing topicInterest, field will ignore if it is null
   *
   * @param id the id of the topicInterestDTO to save.
   * @param topicInterestDTO the topicInterestDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated topicInterestDTO,
   * or with status {@code 400 (Bad Request)} if the topicInterestDTO is not valid,
   * or with status {@code 404 (Not Found)} if the topicInterestDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the topicInterestDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/topic-interests/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<TopicInterestDTO> partialUpdateTopicInterest(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody TopicInterestDTO topicInterestDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update TopicInterest partially : {}, {}", id, topicInterestDTO);
    if (topicInterestDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, topicInterestDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!topicInterestRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<TopicInterestDTO> result = topicInterestService.partialUpdate(topicInterestDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, topicInterestDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /topic-interests} : get all the topicInterests.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of topicInterests in body.
   */
  @GetMapping("/topic-interests")
  public ResponseEntity<List<TopicInterestDTO>> getAllTopicInterests(TopicInterestCriteria criteria, Pageable pageable) {
    log.debug("REST request to get TopicInterests by criteria: {}", criteria);
    Page<TopicInterestDTO> page = topicInterestQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /topic-interests/count} : count all the topicInterests.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/topic-interests/count")
  public ResponseEntity<Long> countTopicInterests(TopicInterestCriteria criteria) {
    log.debug("REST request to count TopicInterests by criteria: {}", criteria);
    return ResponseEntity.ok().body(topicInterestQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /topic-interests/:id} : get the "id" topicInterest.
   *
   * @param id the id of the topicInterestDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the topicInterestDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/topic-interests/{id}")
  public ResponseEntity<TopicInterestDTO> getTopicInterest(@PathVariable Long id) {
    log.debug("REST request to get TopicInterest : {}", id);
    Optional<TopicInterestDTO> topicInterestDTO = topicInterestService.findOne(id);
    return ResponseUtil.wrapOrNotFound(topicInterestDTO);
  }

  /**
   * {@code DELETE  /topic-interests/:id} : delete the "id" topicInterest.
   *
   * @param id the id of the topicInterestDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/topic-interests/{id}")
  public ResponseEntity<Void> deleteTopicInterest(@PathVariable Long id) {
    log.debug("REST request to delete TopicInterest : {}", id);
    topicInterestService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/topic-interests?query=:query} : search for the topicInterest corresponding
   * to the query.
   *
   * @param query the query of the topicInterest search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/topic-interests")
  public ResponseEntity<List<TopicInterestDTO>> searchTopicInterests(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of TopicInterests for query {}", query);
    Page<TopicInterestDTO> page = topicInterestService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
