package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.GroupProfileRepository;
import com.regitiny.catiny.service.GroupProfileQueryService;
import com.regitiny.catiny.service.GroupProfileService;
import com.regitiny.catiny.service.criteria.GroupProfileCriteria;
import com.regitiny.catiny.service.dto.GroupProfileDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.GroupProfile}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class GroupProfileResource {

  private final Logger log = LoggerFactory.getLogger(GroupProfileResource.class);

  private static final String ENTITY_NAME = "groupProfile";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final GroupProfileService groupProfileService;

  private final GroupProfileRepository groupProfileRepository;

  private final GroupProfileQueryService groupProfileQueryService;

  public GroupProfileResource(
    GroupProfileService groupProfileService,
    GroupProfileRepository groupProfileRepository,
    GroupProfileQueryService groupProfileQueryService
  ) {
    this.groupProfileService = groupProfileService;
    this.groupProfileRepository = groupProfileRepository;
    this.groupProfileQueryService = groupProfileQueryService;
  }

  /**
   * {@code POST  /group-profiles} : Create a new groupProfile.
   *
   * @param groupProfileDTO the groupProfileDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new groupProfileDTO, or with status {@code 400 (Bad Request)} if the groupProfile has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/group-profiles")
  public ResponseEntity<GroupProfileDTO> createGroupProfile(@Valid @RequestBody GroupProfileDTO groupProfileDTO) throws URISyntaxException {
    log.debug("REST request to save GroupProfile : {}", groupProfileDTO);
    if (groupProfileDTO.getId() != null) {
      throw new BadRequestAlertException("A new groupProfile cannot already have an ID", ENTITY_NAME, "idexists");
    }
    GroupProfileDTO result = groupProfileService.save(groupProfileDTO);
    return ResponseEntity
      .created(new URI("/api/group-profiles/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /group-profiles/:id} : Updates an existing groupProfile.
   *
   * @param id the id of the groupProfileDTO to save.
   * @param groupProfileDTO the groupProfileDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupProfileDTO,
   * or with status {@code 400 (Bad Request)} if the groupProfileDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the groupProfileDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/group-profiles/{id}")
  public ResponseEntity<GroupProfileDTO> updateGroupProfile(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody GroupProfileDTO groupProfileDTO
  ) throws URISyntaxException {
    log.debug("REST request to update GroupProfile : {}, {}", id, groupProfileDTO);
    if (groupProfileDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, groupProfileDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!groupProfileRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    GroupProfileDTO result = groupProfileService.save(groupProfileDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupProfileDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /group-profiles/:id} : Partial updates given fields of an existing groupProfile, field will ignore if it is null
   *
   * @param id the id of the groupProfileDTO to save.
   * @param groupProfileDTO the groupProfileDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupProfileDTO,
   * or with status {@code 400 (Bad Request)} if the groupProfileDTO is not valid,
   * or with status {@code 404 (Not Found)} if the groupProfileDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the groupProfileDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/group-profiles/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<GroupProfileDTO> partialUpdateGroupProfile(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody GroupProfileDTO groupProfileDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update GroupProfile partially : {}, {}", id, groupProfileDTO);
    if (groupProfileDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, groupProfileDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!groupProfileRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<GroupProfileDTO> result = groupProfileService.partialUpdate(groupProfileDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupProfileDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /group-profiles} : get all the groupProfiles.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of groupProfiles in body.
   */
  @GetMapping("/group-profiles")
  public ResponseEntity<List<GroupProfileDTO>> getAllGroupProfiles(GroupProfileCriteria criteria, Pageable pageable) {
    log.debug("REST request to get GroupProfiles by criteria: {}", criteria);
    Page<GroupProfileDTO> page = groupProfileQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /group-profiles/count} : count all the groupProfiles.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/group-profiles/count")
  public ResponseEntity<Long> countGroupProfiles(GroupProfileCriteria criteria) {
    log.debug("REST request to count GroupProfiles by criteria: {}", criteria);
    return ResponseEntity.ok().body(groupProfileQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /group-profiles/:id} : get the "id" groupProfile.
   *
   * @param id the id of the groupProfileDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the groupProfileDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/group-profiles/{id}")
  public ResponseEntity<GroupProfileDTO> getGroupProfile(@PathVariable Long id) {
    log.debug("REST request to get GroupProfile : {}", id);
    Optional<GroupProfileDTO> groupProfileDTO = groupProfileService.findOne(id);
    return ResponseUtil.wrapOrNotFound(groupProfileDTO);
  }

  /**
   * {@code DELETE  /group-profiles/:id} : delete the "id" groupProfile.
   *
   * @param id the id of the groupProfileDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/group-profiles/{id}")
  public ResponseEntity<Void> deleteGroupProfile(@PathVariable Long id) {
    log.debug("REST request to delete GroupProfile : {}", id);
    groupProfileService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/group-profiles?query=:query} : search for the groupProfile corresponding
   * to the query.
   *
   * @param query the query of the groupProfile search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/group-profiles")
  public ResponseEntity<List<GroupProfileDTO>> searchGroupProfiles(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of GroupProfiles for query {}", query);
    Page<GroupProfileDTO> page = groupProfileService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
