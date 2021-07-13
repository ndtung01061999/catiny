package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.PageProfileRepository;
import com.regitiny.catiny.service.PageProfileQueryService;
import com.regitiny.catiny.service.PageProfileService;
import com.regitiny.catiny.service.criteria.PageProfileCriteria;
import com.regitiny.catiny.service.dto.PageProfileDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.PageProfile}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class PageProfileResource {

  private final Logger log = LoggerFactory.getLogger(PageProfileResource.class);

  private static final String ENTITY_NAME = "pageProfile";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final PageProfileService pageProfileService;

  private final PageProfileRepository pageProfileRepository;

  private final PageProfileQueryService pageProfileQueryService;

  public PageProfileResource(
    PageProfileService pageProfileService,
    PageProfileRepository pageProfileRepository,
    PageProfileQueryService pageProfileQueryService
  ) {
    this.pageProfileService = pageProfileService;
    this.pageProfileRepository = pageProfileRepository;
    this.pageProfileQueryService = pageProfileQueryService;
  }

  /**
   * {@code POST  /page-profiles} : Create a new pageProfile.
   *
   * @param pageProfileDTO the pageProfileDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pageProfileDTO, or with status {@code 400 (Bad Request)} if the pageProfile has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/page-profiles")
  public ResponseEntity<PageProfileDTO> createPageProfile(@Valid @RequestBody PageProfileDTO pageProfileDTO) throws URISyntaxException {
    log.debug("REST request to save PageProfile : {}", pageProfileDTO);
    if (pageProfileDTO.getId() != null) {
      throw new BadRequestAlertException("A new pageProfile cannot already have an ID", ENTITY_NAME, "idexists");
    }
    PageProfileDTO result = pageProfileService.save(pageProfileDTO);
    return ResponseEntity
      .created(new URI("/api/page-profiles/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /page-profiles/:id} : Updates an existing pageProfile.
   *
   * @param id the id of the pageProfileDTO to save.
   * @param pageProfileDTO the pageProfileDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pageProfileDTO,
   * or with status {@code 400 (Bad Request)} if the pageProfileDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the pageProfileDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/page-profiles/{id}")
  public ResponseEntity<PageProfileDTO> updatePageProfile(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody PageProfileDTO pageProfileDTO
  ) throws URISyntaxException {
    log.debug("REST request to update PageProfile : {}, {}", id, pageProfileDTO);
    if (pageProfileDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, pageProfileDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!pageProfileRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    PageProfileDTO result = pageProfileService.save(pageProfileDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pageProfileDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /page-profiles/:id} : Partial updates given fields of an existing pageProfile, field will ignore if it is null
   *
   * @param id the id of the pageProfileDTO to save.
   * @param pageProfileDTO the pageProfileDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pageProfileDTO,
   * or with status {@code 400 (Bad Request)} if the pageProfileDTO is not valid,
   * or with status {@code 404 (Not Found)} if the pageProfileDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the pageProfileDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/page-profiles/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<PageProfileDTO> partialUpdatePageProfile(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody PageProfileDTO pageProfileDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update PageProfile partially : {}, {}", id, pageProfileDTO);
    if (pageProfileDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, pageProfileDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!pageProfileRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<PageProfileDTO> result = pageProfileService.partialUpdate(pageProfileDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pageProfileDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /page-profiles} : get all the pageProfiles.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pageProfiles in body.
   */
  @GetMapping("/page-profiles")
  public ResponseEntity<List<PageProfileDTO>> getAllPageProfiles(PageProfileCriteria criteria, Pageable pageable) {
    log.debug("REST request to get PageProfiles by criteria: {}", criteria);
    Page<PageProfileDTO> page = pageProfileQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /page-profiles/count} : count all the pageProfiles.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/page-profiles/count")
  public ResponseEntity<Long> countPageProfiles(PageProfileCriteria criteria) {
    log.debug("REST request to count PageProfiles by criteria: {}", criteria);
    return ResponseEntity.ok().body(pageProfileQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /page-profiles/:id} : get the "id" pageProfile.
   *
   * @param id the id of the pageProfileDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pageProfileDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/page-profiles/{id}")
  public ResponseEntity<PageProfileDTO> getPageProfile(@PathVariable Long id) {
    log.debug("REST request to get PageProfile : {}", id);
    Optional<PageProfileDTO> pageProfileDTO = pageProfileService.findOne(id);
    return ResponseUtil.wrapOrNotFound(pageProfileDTO);
  }

  /**
   * {@code DELETE  /page-profiles/:id} : delete the "id" pageProfile.
   *
   * @param id the id of the pageProfileDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/page-profiles/{id}")
  public ResponseEntity<Void> deletePageProfile(@PathVariable Long id) {
    log.debug("REST request to delete PageProfile : {}", id);
    pageProfileService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/page-profiles?query=:query} : search for the pageProfile corresponding
   * to the query.
   *
   * @param query the query of the pageProfile search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/page-profiles")
  public ResponseEntity<List<PageProfileDTO>> searchPageProfiles(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of PageProfiles for query {}", query);
    Page<PageProfileDTO> page = pageProfileService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
