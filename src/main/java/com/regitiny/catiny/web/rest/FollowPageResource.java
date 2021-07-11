package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.FollowPageRepository;
import com.regitiny.catiny.service.FollowPageQueryService;
import com.regitiny.catiny.service.FollowPageService;
import com.regitiny.catiny.service.criteria.FollowPageCriteria;
import com.regitiny.catiny.service.dto.FollowPageDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.FollowPage}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class FollowPageResource {

  private final Logger log = LoggerFactory.getLogger(FollowPageResource.class);

  private static final String ENTITY_NAME = "followPage";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final FollowPageService followPageService;

  private final FollowPageRepository followPageRepository;

  private final FollowPageQueryService followPageQueryService;

  public FollowPageResource(
    FollowPageService followPageService,
    FollowPageRepository followPageRepository,
    FollowPageQueryService followPageQueryService
  ) {
    this.followPageService = followPageService;
    this.followPageRepository = followPageRepository;
    this.followPageQueryService = followPageQueryService;
  }

  /**
   * {@code POST  /follow-pages} : Create a new followPage.
   *
   * @param followPageDTO the followPageDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new followPageDTO, or with status {@code 400 (Bad Request)} if the followPage has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/follow-pages")
  public ResponseEntity<FollowPageDTO> createFollowPage(@Valid @RequestBody FollowPageDTO followPageDTO) throws URISyntaxException {
    log.debug("REST request to save FollowPage : {}", followPageDTO);
    if (followPageDTO.getId() != null) {
      throw new BadRequestAlertException("A new followPage cannot already have an ID", ENTITY_NAME, "idexists");
    }
    FollowPageDTO result = followPageService.save(followPageDTO);
    return ResponseEntity
      .created(new URI("/api/follow-pages/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /follow-pages/:id} : Updates an existing followPage.
   *
   * @param id the id of the followPageDTO to save.
   * @param followPageDTO the followPageDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated followPageDTO,
   * or with status {@code 400 (Bad Request)} if the followPageDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the followPageDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/follow-pages/{id}")
  public ResponseEntity<FollowPageDTO> updateFollowPage(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody FollowPageDTO followPageDTO
  ) throws URISyntaxException {
    log.debug("REST request to update FollowPage : {}, {}", id, followPageDTO);
    if (followPageDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, followPageDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!followPageRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    FollowPageDTO result = followPageService.save(followPageDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, followPageDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /follow-pages/:id} : Partial updates given fields of an existing followPage, field will ignore if it is null
   *
   * @param id the id of the followPageDTO to save.
   * @param followPageDTO the followPageDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated followPageDTO,
   * or with status {@code 400 (Bad Request)} if the followPageDTO is not valid,
   * or with status {@code 404 (Not Found)} if the followPageDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the followPageDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/follow-pages/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<FollowPageDTO> partialUpdateFollowPage(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody FollowPageDTO followPageDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update FollowPage partially : {}, {}", id, followPageDTO);
    if (followPageDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, followPageDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!followPageRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<FollowPageDTO> result = followPageService.partialUpdate(followPageDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, followPageDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /follow-pages} : get all the followPages.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of followPages in body.
   */
  @GetMapping("/follow-pages")
  public ResponseEntity<List<FollowPageDTO>> getAllFollowPages(FollowPageCriteria criteria, Pageable pageable) {
    log.debug("REST request to get FollowPages by criteria: {}", criteria);
    Page<FollowPageDTO> page = followPageQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /follow-pages/count} : count all the followPages.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/follow-pages/count")
  public ResponseEntity<Long> countFollowPages(FollowPageCriteria criteria) {
    log.debug("REST request to count FollowPages by criteria: {}", criteria);
    return ResponseEntity.ok().body(followPageQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /follow-pages/:id} : get the "id" followPage.
   *
   * @param id the id of the followPageDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the followPageDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/follow-pages/{id}")
  public ResponseEntity<FollowPageDTO> getFollowPage(@PathVariable Long id) {
    log.debug("REST request to get FollowPage : {}", id);
    Optional<FollowPageDTO> followPageDTO = followPageService.findOne(id);
    return ResponseUtil.wrapOrNotFound(followPageDTO);
  }

  /**
   * {@code DELETE  /follow-pages/:id} : delete the "id" followPage.
   *
   * @param id the id of the followPageDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/follow-pages/{id}")
  public ResponseEntity<Void> deleteFollowPage(@PathVariable Long id) {
    log.debug("REST request to delete FollowPage : {}", id);
    followPageService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/follow-pages?query=:query} : search for the followPage corresponding
   * to the query.
   *
   * @param query the query of the followPage search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/follow-pages")
  public ResponseEntity<List<FollowPageDTO>> searchFollowPages(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of FollowPages for query {}", query);
    Page<FollowPageDTO> page = followPageService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
