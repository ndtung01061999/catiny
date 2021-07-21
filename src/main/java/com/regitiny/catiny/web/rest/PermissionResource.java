package com.regitiny.catiny.web.rest;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.PermissionRepository;
import com.regitiny.catiny.service.PermissionQueryService;
import com.regitiny.catiny.service.PermissionService;
import com.regitiny.catiny.service.criteria.PermissionCriteria;
import com.regitiny.catiny.service.dto.PermissionDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.Permission}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class PermissionResource
{

  private final Logger log = LoggerFactory.getLogger(PermissionResource.class);

  private static final String ENTITY_NAME = "permission";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final PermissionService permissionService;

  private final PermissionRepository permissionRepository;

  private final PermissionQueryService permissionQueryService;

  public PermissionResource(
    PermissionService permissionService,
    PermissionRepository permissionRepository,
    PermissionQueryService permissionQueryService
  ) {
    this.permissionService = permissionService;
    this.permissionRepository = permissionRepository;
    this.permissionQueryService = permissionQueryService;
  }

  /**
   * {@code POST  /permissions} : Create a new permission.
   *
   * @param permissionDTO the permissionDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new permissionDTO, or with status {@code 400 (Bad Request)} if the permission has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/permissions")
  public ResponseEntity<PermissionDTO> createPermission(@Valid @RequestBody PermissionDTO permissionDTO) throws URISyntaxException
  {
    log.debug("REST request to save Permission : {}", permissionDTO);
    if (permissionDTO.getId() != null)
    {
      throw new BadRequestAlertException("A new permission cannot already have an ID", ENTITY_NAME, "idexists");
    }
    PermissionDTO result = permissionService.save(permissionDTO);
    return ResponseEntity
      .created(new URI("/api/permissions/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /permissions/:id} : Updates an existing permission.
   *
   * @param id the id of the permissionDTO to save.
   * @param permissionDTO the permissionDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated permissionDTO,
   * or with status {@code 400 (Bad Request)} if the permissionDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the permissionDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/permissions/{id}")
  public ResponseEntity<PermissionDTO> updatePermission(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody PermissionDTO permissionDTO
  ) throws URISyntaxException {
    log.debug("REST request to update Permission : {}, {}", id, permissionDTO);
    if (permissionDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, permissionDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!permissionRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    PermissionDTO result = permissionService.save(permissionDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, permissionDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /permissions/:id} : Partial updates given fields of an existing permission, field will ignore if it is null
   *
   * @param id the id of the permissionDTO to save.
   * @param permissionDTO the permissionDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated permissionDTO,
   * or with status {@code 400 (Bad Request)} if the permissionDTO is not valid,
   * or with status {@code 404 (Not Found)} if the permissionDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the permissionDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/permissions/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<PermissionDTO> partialUpdatePermission(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody PermissionDTO permissionDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update Permission partially : {}, {}", id, permissionDTO);
    if (permissionDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, permissionDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!permissionRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<PermissionDTO> result = permissionService.partialUpdate(permissionDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, permissionDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /permissions} : get all the permissions.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of permissions in body.
   */
  @GetMapping("/permissions")
  public ResponseEntity<List<PermissionDTO>> getAllPermissions(PermissionCriteria criteria, Pageable pageable) {
    log.debug("REST request to get Permissions by criteria: {}", criteria);
    Page<PermissionDTO> page = permissionQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /permissions/count} : count all the permissions.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/permissions/count")
  public ResponseEntity<Long> countPermissions(PermissionCriteria criteria) {
    log.debug("REST request to count Permissions by criteria: {}", criteria);
    return ResponseEntity.ok().body(permissionQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /permissions/:id} : get the "id" permission.
   *
   * @param id the id of the permissionDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the permissionDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/permissions/{id}")
  public ResponseEntity<PermissionDTO> getPermission(@PathVariable Long id) {
    log.debug("REST request to get Permission : {}", id);
    Optional<PermissionDTO> permissionDTO = permissionService.findOne(id);
    return ResponseUtil.wrapOrNotFound(permissionDTO);
  }

  /**
   * {@code DELETE  /permissions/:id} : delete the "id" permission.
   *
   * @param id the id of the permissionDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/permissions/{id}")
  public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
    log.debug("REST request to delete Permission : {}", id);
    permissionService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/permissions?query=:query} : search for the permission corresponding
   * to the query.
   *
   * @param query the query of the permission search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/permissions")
  public ResponseEntity<List<PermissionDTO>> searchPermissions(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of Permissions for query {}", query);
    Page<PermissionDTO> page = permissionService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
