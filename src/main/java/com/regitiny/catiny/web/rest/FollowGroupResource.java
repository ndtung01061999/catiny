package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.FollowGroupRepository;
import com.regitiny.catiny.service.FollowGroupQueryService;
import com.regitiny.catiny.service.FollowGroupService;
import com.regitiny.catiny.service.criteria.FollowGroupCriteria;
import com.regitiny.catiny.service.dto.FollowGroupDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.FollowGroup}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class FollowGroupResource {

  private final Logger log = LoggerFactory.getLogger(FollowGroupResource.class);

  private static final String ENTITY_NAME = "followGroup";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final FollowGroupService followGroupService;

  private final FollowGroupRepository followGroupRepository;

  private final FollowGroupQueryService followGroupQueryService;

  public FollowGroupResource(
    FollowGroupService followGroupService,
    FollowGroupRepository followGroupRepository,
    FollowGroupQueryService followGroupQueryService
  ) {
    this.followGroupService = followGroupService;
    this.followGroupRepository = followGroupRepository;
    this.followGroupQueryService = followGroupQueryService;
  }

  /**
   * {@code POST  /follow-groups} : Create a new followGroup.
   *
   * @param followGroupDTO the followGroupDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new followGroupDTO, or with status {@code 400 (Bad Request)} if the followGroup has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/follow-groups")
  public ResponseEntity<FollowGroupDTO> createFollowGroup(@Valid @RequestBody FollowGroupDTO followGroupDTO) throws URISyntaxException {
    log.debug("REST request to save FollowGroup : {}", followGroupDTO);
    if (followGroupDTO.getId() != null) {
      throw new BadRequestAlertException("A new followGroup cannot already have an ID", ENTITY_NAME, "idexists");
    }
    FollowGroupDTO result = followGroupService.save(followGroupDTO);
    return ResponseEntity
      .created(new URI("/api/follow-groups/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /follow-groups/:id} : Updates an existing followGroup.
   *
   * @param id the id of the followGroupDTO to save.
   * @param followGroupDTO the followGroupDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated followGroupDTO,
   * or with status {@code 400 (Bad Request)} if the followGroupDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the followGroupDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/follow-groups/{id}")
  public ResponseEntity<FollowGroupDTO> updateFollowGroup(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody FollowGroupDTO followGroupDTO
  ) throws URISyntaxException {
    log.debug("REST request to update FollowGroup : {}, {}", id, followGroupDTO);
    if (followGroupDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, followGroupDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!followGroupRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    FollowGroupDTO result = followGroupService.save(followGroupDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, followGroupDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /follow-groups/:id} : Partial updates given fields of an existing followGroup, field will ignore if it is null
   *
   * @param id the id of the followGroupDTO to save.
   * @param followGroupDTO the followGroupDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated followGroupDTO,
   * or with status {@code 400 (Bad Request)} if the followGroupDTO is not valid,
   * or with status {@code 404 (Not Found)} if the followGroupDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the followGroupDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/follow-groups/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<FollowGroupDTO> partialUpdateFollowGroup(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody FollowGroupDTO followGroupDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update FollowGroup partially : {}, {}", id, followGroupDTO);
    if (followGroupDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, followGroupDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!followGroupRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<FollowGroupDTO> result = followGroupService.partialUpdate(followGroupDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, followGroupDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /follow-groups} : get all the followGroups.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of followGroups in body.
   */
  @GetMapping("/follow-groups")
  public ResponseEntity<List<FollowGroupDTO>> getAllFollowGroups(FollowGroupCriteria criteria, Pageable pageable) {
    log.debug("REST request to get FollowGroups by criteria: {}", criteria);
    Page<FollowGroupDTO> page = followGroupQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /follow-groups/count} : count all the followGroups.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/follow-groups/count")
  public ResponseEntity<Long> countFollowGroups(FollowGroupCriteria criteria) {
    log.debug("REST request to count FollowGroups by criteria: {}", criteria);
    return ResponseEntity.ok().body(followGroupQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /follow-groups/:id} : get the "id" followGroup.
   *
   * @param id the id of the followGroupDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the followGroupDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/follow-groups/{id}")
  public ResponseEntity<FollowGroupDTO> getFollowGroup(@PathVariable Long id) {
    log.debug("REST request to get FollowGroup : {}", id);
    Optional<FollowGroupDTO> followGroupDTO = followGroupService.findOne(id);
    return ResponseUtil.wrapOrNotFound(followGroupDTO);
  }

  /**
   * {@code DELETE  /follow-groups/:id} : delete the "id" followGroup.
   *
   * @param id the id of the followGroupDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/follow-groups/{id}")
  public ResponseEntity<Void> deleteFollowGroup(@PathVariable Long id) {
    log.debug("REST request to delete FollowGroup : {}", id);
    followGroupService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/follow-groups?query=:query} : search for the followGroup corresponding
   * to the query.
   *
   * @param query the query of the followGroup search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/follow-groups")
  public ResponseEntity<List<FollowGroupDTO>> searchFollowGroups(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of FollowGroups for query {}", query);
    Page<FollowGroupDTO> page = followGroupService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
