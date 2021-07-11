package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.MasterUserRepository;
import com.regitiny.catiny.service.MasterUserQueryService;
import com.regitiny.catiny.service.MasterUserService;
import com.regitiny.catiny.service.criteria.MasterUserCriteria;
import com.regitiny.catiny.service.dto.MasterUserDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.MasterUser}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class MasterUserResource {

  private final Logger log = LoggerFactory.getLogger(MasterUserResource.class);

  private static final String ENTITY_NAME = "masterUser";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final MasterUserService masterUserService;

  private final MasterUserRepository masterUserRepository;

  private final MasterUserQueryService masterUserQueryService;

  public MasterUserResource(
    MasterUserService masterUserService,
    MasterUserRepository masterUserRepository,
    MasterUserQueryService masterUserQueryService
  ) {
    this.masterUserService = masterUserService;
    this.masterUserRepository = masterUserRepository;
    this.masterUserQueryService = masterUserQueryService;
  }

  /**
   * {@code POST  /master-users} : Create a new masterUser.
   *
   * @param masterUserDTO the masterUserDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new masterUserDTO, or with status {@code 400 (Bad Request)} if the masterUser has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/master-users")
  public ResponseEntity<MasterUserDTO> createMasterUser(@Valid @RequestBody MasterUserDTO masterUserDTO) throws URISyntaxException {
    log.debug("REST request to save MasterUser : {}", masterUserDTO);
    if (masterUserDTO.getId() != null) {
      throw new BadRequestAlertException("A new masterUser cannot already have an ID", ENTITY_NAME, "idexists");
    }
    if (Objects.isNull(masterUserDTO.getUser())) {
      throw new BadRequestAlertException("Invalid association value provided", ENTITY_NAME, "null");
    }
    MasterUserDTO result = masterUserService.save(masterUserDTO);
    return ResponseEntity
      .created(new URI("/api/master-users/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /master-users/:id} : Updates an existing masterUser.
   *
   * @param id the id of the masterUserDTO to save.
   * @param masterUserDTO the masterUserDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated masterUserDTO,
   * or with status {@code 400 (Bad Request)} if the masterUserDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the masterUserDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/master-users/{id}")
  public ResponseEntity<MasterUserDTO> updateMasterUser(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody MasterUserDTO masterUserDTO
  ) throws URISyntaxException {
    log.debug("REST request to update MasterUser : {}, {}", id, masterUserDTO);
    if (masterUserDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, masterUserDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!masterUserRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    MasterUserDTO result = masterUserService.save(masterUserDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, masterUserDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /master-users/:id} : Partial updates given fields of an existing masterUser, field will ignore if it is null
   *
   * @param id the id of the masterUserDTO to save.
   * @param masterUserDTO the masterUserDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated masterUserDTO,
   * or with status {@code 400 (Bad Request)} if the masterUserDTO is not valid,
   * or with status {@code 404 (Not Found)} if the masterUserDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the masterUserDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/master-users/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<MasterUserDTO> partialUpdateMasterUser(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody MasterUserDTO masterUserDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update MasterUser partially : {}, {}", id, masterUserDTO);
    if (masterUserDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, masterUserDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!masterUserRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<MasterUserDTO> result = masterUserService.partialUpdate(masterUserDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, masterUserDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /master-users} : get all the masterUsers.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of masterUsers in body.
   */
  @GetMapping("/master-users")
  public ResponseEntity<List<MasterUserDTO>> getAllMasterUsers(MasterUserCriteria criteria, Pageable pageable) {
    log.debug("REST request to get MasterUsers by criteria: {}", criteria);
    Page<MasterUserDTO> page = masterUserQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /master-users/count} : count all the masterUsers.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/master-users/count")
  public ResponseEntity<Long> countMasterUsers(MasterUserCriteria criteria) {
    log.debug("REST request to count MasterUsers by criteria: {}", criteria);
    return ResponseEntity.ok().body(masterUserQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /master-users/:id} : get the "id" masterUser.
   *
   * @param id the id of the masterUserDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the masterUserDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/master-users/{id}")
  public ResponseEntity<MasterUserDTO> getMasterUser(@PathVariable Long id) {
    log.debug("REST request to get MasterUser : {}", id);
    Optional<MasterUserDTO> masterUserDTO = masterUserService.findOne(id);
    return ResponseUtil.wrapOrNotFound(masterUserDTO);
  }

  /**
   * {@code DELETE  /master-users/:id} : delete the "id" masterUser.
   *
   * @param id the id of the masterUserDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/master-users/{id}")
  public ResponseEntity<Void> deleteMasterUser(@PathVariable Long id) {
    log.debug("REST request to delete MasterUser : {}", id);
    masterUserService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/master-users?query=:query} : search for the masterUser corresponding
   * to the query.
   *
   * @param query the query of the masterUser search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/master-users")
  public ResponseEntity<List<MasterUserDTO>> searchMasterUsers(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of MasterUsers for query {}", query);
    Page<MasterUserDTO> page = masterUserService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
