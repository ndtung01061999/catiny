package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.NewsFeedRepository;
import com.regitiny.catiny.service.NewsFeedQueryService;
import com.regitiny.catiny.service.NewsFeedService;
import com.regitiny.catiny.service.criteria.NewsFeedCriteria;
import com.regitiny.catiny.service.dto.NewsFeedDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.NewsFeed}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class NewsFeedResource {

  private final Logger log = LoggerFactory.getLogger(NewsFeedResource.class);

  private static final String ENTITY_NAME = "newsFeed";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final NewsFeedService newsFeedService;

  private final NewsFeedRepository newsFeedRepository;

  private final NewsFeedQueryService newsFeedQueryService;

  public NewsFeedResource(
    NewsFeedService newsFeedService,
    NewsFeedRepository newsFeedRepository,
    NewsFeedQueryService newsFeedQueryService
  ) {
    this.newsFeedService = newsFeedService;
    this.newsFeedRepository = newsFeedRepository;
    this.newsFeedQueryService = newsFeedQueryService;
  }

  /**
   * {@code POST  /news-feeds} : Create a new newsFeed.
   *
   * @param newsFeedDTO the newsFeedDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new newsFeedDTO, or with status {@code 400 (Bad Request)} if the newsFeed has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/news-feeds")
  public ResponseEntity<NewsFeedDTO> createNewsFeed(@Valid @RequestBody NewsFeedDTO newsFeedDTO) throws URISyntaxException {
    log.debug("REST request to save NewsFeed : {}", newsFeedDTO);
    if (newsFeedDTO.getId() != null) {
      throw new BadRequestAlertException("A new newsFeed cannot already have an ID", ENTITY_NAME, "idexists");
    }
    NewsFeedDTO result = newsFeedService.save(newsFeedDTO);
    return ResponseEntity
      .created(new URI("/api/news-feeds/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /news-feeds/:id} : Updates an existing newsFeed.
   *
   * @param id the id of the newsFeedDTO to save.
   * @param newsFeedDTO the newsFeedDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated newsFeedDTO,
   * or with status {@code 400 (Bad Request)} if the newsFeedDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the newsFeedDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/news-feeds/{id}")
  public ResponseEntity<NewsFeedDTO> updateNewsFeed(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody NewsFeedDTO newsFeedDTO
  ) throws URISyntaxException {
    log.debug("REST request to update NewsFeed : {}, {}", id, newsFeedDTO);
    if (newsFeedDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, newsFeedDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!newsFeedRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    NewsFeedDTO result = newsFeedService.save(newsFeedDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, newsFeedDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /news-feeds/:id} : Partial updates given fields of an existing newsFeed, field will ignore if it is null
   *
   * @param id the id of the newsFeedDTO to save.
   * @param newsFeedDTO the newsFeedDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated newsFeedDTO,
   * or with status {@code 400 (Bad Request)} if the newsFeedDTO is not valid,
   * or with status {@code 404 (Not Found)} if the newsFeedDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the newsFeedDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/news-feeds/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<NewsFeedDTO> partialUpdateNewsFeed(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody NewsFeedDTO newsFeedDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update NewsFeed partially : {}, {}", id, newsFeedDTO);
    if (newsFeedDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, newsFeedDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!newsFeedRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<NewsFeedDTO> result = newsFeedService.partialUpdate(newsFeedDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, newsFeedDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /news-feeds} : get all the newsFeeds.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of newsFeeds in body.
   */
  @GetMapping("/news-feeds")
  public ResponseEntity<List<NewsFeedDTO>> getAllNewsFeeds(NewsFeedCriteria criteria, Pageable pageable) {
    log.debug("REST request to get NewsFeeds by criteria: {}", criteria);
    Page<NewsFeedDTO> page = newsFeedQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /news-feeds/count} : count all the newsFeeds.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/news-feeds/count")
  public ResponseEntity<Long> countNewsFeeds(NewsFeedCriteria criteria) {
    log.debug("REST request to count NewsFeeds by criteria: {}", criteria);
    return ResponseEntity.ok().body(newsFeedQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /news-feeds/:id} : get the "id" newsFeed.
   *
   * @param id the id of the newsFeedDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the newsFeedDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/news-feeds/{id}")
  public ResponseEntity<NewsFeedDTO> getNewsFeed(@PathVariable Long id) {
    log.debug("REST request to get NewsFeed : {}", id);
    Optional<NewsFeedDTO> newsFeedDTO = newsFeedService.findOne(id);
    return ResponseUtil.wrapOrNotFound(newsFeedDTO);
  }

  /**
   * {@code DELETE  /news-feeds/:id} : delete the "id" newsFeed.
   *
   * @param id the id of the newsFeedDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/news-feeds/{id}")
  public ResponseEntity<Void> deleteNewsFeed(@PathVariable Long id) {
    log.debug("REST request to delete NewsFeed : {}", id);
    newsFeedService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/news-feeds?query=:query} : search for the newsFeed corresponding
   * to the query.
   *
   * @param query the query of the newsFeed search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/news-feeds")
  public ResponseEntity<List<NewsFeedDTO>> searchNewsFeeds(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of NewsFeeds for query {}", query);
    Page<NewsFeedDTO> page = newsFeedService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
