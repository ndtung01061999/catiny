package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.PagePostRepository;
import com.regitiny.catiny.service.PagePostQueryService;
import com.regitiny.catiny.service.PagePostService;
import com.regitiny.catiny.service.criteria.PagePostCriteria;
import com.regitiny.catiny.service.dto.PagePostDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.PagePost}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class PagePostResource {

  private final Logger log = LoggerFactory.getLogger(PagePostResource.class);

  private static final String ENTITY_NAME = "pagePost";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final PagePostService pagePostService;

  private final PagePostRepository pagePostRepository;

  private final PagePostQueryService pagePostQueryService;

  public PagePostResource(
    PagePostService pagePostService,
    PagePostRepository pagePostRepository,
    PagePostQueryService pagePostQueryService
  ) {
    this.pagePostService = pagePostService;
    this.pagePostRepository = pagePostRepository;
    this.pagePostQueryService = pagePostQueryService;
  }

  /**
   * {@code POST  /page-posts} : Create a new pagePost.
   *
   * @param pagePostDTO the pagePostDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pagePostDTO, or with status {@code 400 (Bad Request)} if the pagePost has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/page-posts")
  public ResponseEntity<PagePostDTO> createPagePost(@Valid @RequestBody PagePostDTO pagePostDTO) throws URISyntaxException {
    log.debug("REST request to save PagePost : {}", pagePostDTO);
    if (pagePostDTO.getId() != null) {
      throw new BadRequestAlertException("A new pagePost cannot already have an ID", ENTITY_NAME, "idexists");
    }
    PagePostDTO result = pagePostService.save(pagePostDTO);
    return ResponseEntity
      .created(new URI("/api/page-posts/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /page-posts/:id} : Updates an existing pagePost.
   *
   * @param id the id of the pagePostDTO to save.
   * @param pagePostDTO the pagePostDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pagePostDTO,
   * or with status {@code 400 (Bad Request)} if the pagePostDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the pagePostDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/page-posts/{id}")
  public ResponseEntity<PagePostDTO> updatePagePost(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody PagePostDTO pagePostDTO
  ) throws URISyntaxException {
    log.debug("REST request to update PagePost : {}, {}", id, pagePostDTO);
    if (pagePostDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, pagePostDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!pagePostRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    PagePostDTO result = pagePostService.save(pagePostDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pagePostDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /page-posts/:id} : Partial updates given fields of an existing pagePost, field will ignore if it is null
   *
   * @param id the id of the pagePostDTO to save.
   * @param pagePostDTO the pagePostDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pagePostDTO,
   * or with status {@code 400 (Bad Request)} if the pagePostDTO is not valid,
   * or with status {@code 404 (Not Found)} if the pagePostDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the pagePostDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/page-posts/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<PagePostDTO> partialUpdatePagePost(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody PagePostDTO pagePostDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update PagePost partially : {}, {}", id, pagePostDTO);
    if (pagePostDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, pagePostDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!pagePostRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<PagePostDTO> result = pagePostService.partialUpdate(pagePostDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pagePostDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /page-posts} : get all the pagePosts.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pagePosts in body.
   */
  @GetMapping("/page-posts")
  public ResponseEntity<List<PagePostDTO>> getAllPagePosts(PagePostCriteria criteria, Pageable pageable) {
    log.debug("REST request to get PagePosts by criteria: {}", criteria);
    Page<PagePostDTO> page = pagePostQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /page-posts/count} : count all the pagePosts.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/page-posts/count")
  public ResponseEntity<Long> countPagePosts(PagePostCriteria criteria) {
    log.debug("REST request to count PagePosts by criteria: {}", criteria);
    return ResponseEntity.ok().body(pagePostQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /page-posts/:id} : get the "id" pagePost.
   *
   * @param id the id of the pagePostDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pagePostDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/page-posts/{id}")
  public ResponseEntity<PagePostDTO> getPagePost(@PathVariable Long id) {
    log.debug("REST request to get PagePost : {}", id);
    Optional<PagePostDTO> pagePostDTO = pagePostService.findOne(id);
    return ResponseUtil.wrapOrNotFound(pagePostDTO);
  }

  /**
   * {@code DELETE  /page-posts/:id} : delete the "id" pagePost.
   *
   * @param id the id of the pagePostDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/page-posts/{id}")
  public ResponseEntity<Void> deletePagePost(@PathVariable Long id) {
    log.debug("REST request to delete PagePost : {}", id);
    pagePostService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/page-posts?query=:query} : search for the pagePost corresponding
   * to the query.
   *
   * @param query the query of the pagePost search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/page-posts")
  public ResponseEntity<List<PagePostDTO>> searchPagePosts(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of PagePosts for query {}", query);
    Page<PagePostDTO> page = pagePostService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
