package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.GroupPostRepository;
import com.regitiny.catiny.service.GroupPostQueryService;
import com.regitiny.catiny.service.GroupPostService;
import com.regitiny.catiny.service.criteria.GroupPostCriteria;
import com.regitiny.catiny.service.dto.GroupPostDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.GroupPost}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class GroupPostResource {

  private final Logger log = LoggerFactory.getLogger(GroupPostResource.class);

  private static final String ENTITY_NAME = "groupPost";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final GroupPostService groupPostService;

  private final GroupPostRepository groupPostRepository;

  private final GroupPostQueryService groupPostQueryService;

  public GroupPostResource(
    GroupPostService groupPostService,
    GroupPostRepository groupPostRepository,
    GroupPostQueryService groupPostQueryService
  ) {
    this.groupPostService = groupPostService;
    this.groupPostRepository = groupPostRepository;
    this.groupPostQueryService = groupPostQueryService;
  }

  /**
   * {@code POST  /group-posts} : Create a new groupPost.
   *
   * @param groupPostDTO the groupPostDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new groupPostDTO, or with status {@code 400 (Bad Request)} if the groupPost has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/group-posts")
  public ResponseEntity<GroupPostDTO> createGroupPost(@Valid @RequestBody GroupPostDTO groupPostDTO) throws URISyntaxException {
    log.debug("REST request to save GroupPost : {}", groupPostDTO);
    if (groupPostDTO.getId() != null) {
      throw new BadRequestAlertException("A new groupPost cannot already have an ID", ENTITY_NAME, "idexists");
    }
    GroupPostDTO result = groupPostService.save(groupPostDTO);
    return ResponseEntity
      .created(new URI("/api/group-posts/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /group-posts/:id} : Updates an existing groupPost.
   *
   * @param id the id of the groupPostDTO to save.
   * @param groupPostDTO the groupPostDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupPostDTO,
   * or with status {@code 400 (Bad Request)} if the groupPostDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the groupPostDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/group-posts/{id}")
  public ResponseEntity<GroupPostDTO> updateGroupPost(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody GroupPostDTO groupPostDTO
  ) throws URISyntaxException {
    log.debug("REST request to update GroupPost : {}, {}", id, groupPostDTO);
    if (groupPostDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, groupPostDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!groupPostRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    GroupPostDTO result = groupPostService.save(groupPostDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupPostDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /group-posts/:id} : Partial updates given fields of an existing groupPost, field will ignore if it is null
   *
   * @param id the id of the groupPostDTO to save.
   * @param groupPostDTO the groupPostDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupPostDTO,
   * or with status {@code 400 (Bad Request)} if the groupPostDTO is not valid,
   * or with status {@code 404 (Not Found)} if the groupPostDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the groupPostDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/group-posts/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<GroupPostDTO> partialUpdateGroupPost(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody GroupPostDTO groupPostDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update GroupPost partially : {}, {}", id, groupPostDTO);
    if (groupPostDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, groupPostDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!groupPostRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<GroupPostDTO> result = groupPostService.partialUpdate(groupPostDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupPostDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /group-posts} : get all the groupPosts.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of groupPosts in body.
   */
  @GetMapping("/group-posts")
  public ResponseEntity<List<GroupPostDTO>> getAllGroupPosts(GroupPostCriteria criteria, Pageable pageable) {
    log.debug("REST request to get GroupPosts by criteria: {}", criteria);
    Page<GroupPostDTO> page = groupPostQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /group-posts/count} : count all the groupPosts.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/group-posts/count")
  public ResponseEntity<Long> countGroupPosts(GroupPostCriteria criteria) {
    log.debug("REST request to count GroupPosts by criteria: {}", criteria);
    return ResponseEntity.ok().body(groupPostQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /group-posts/:id} : get the "id" groupPost.
   *
   * @param id the id of the groupPostDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the groupPostDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/group-posts/{id}")
  public ResponseEntity<GroupPostDTO> getGroupPost(@PathVariable Long id) {
    log.debug("REST request to get GroupPost : {}", id);
    Optional<GroupPostDTO> groupPostDTO = groupPostService.findOne(id);
    return ResponseUtil.wrapOrNotFound(groupPostDTO);
  }

  /**
   * {@code DELETE  /group-posts/:id} : delete the "id" groupPost.
   *
   * @param id the id of the groupPostDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/group-posts/{id}")
  public ResponseEntity<Void> deleteGroupPost(@PathVariable Long id) {
    log.debug("REST request to delete GroupPost : {}", id);
    groupPostService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/group-posts?query=:query} : search for the groupPost corresponding
   * to the query.
   *
   * @param query the query of the groupPost search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/group-posts")
  public ResponseEntity<List<GroupPostDTO>> searchGroupPosts(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of GroupPosts for query {}", query);
    Page<GroupPostDTO> page = groupPostService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
