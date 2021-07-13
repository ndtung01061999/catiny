package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.FollowUserRepository;
import com.regitiny.catiny.service.FollowUserQueryService;
import com.regitiny.catiny.service.FollowUserService;
import com.regitiny.catiny.service.criteria.FollowUserCriteria;
import com.regitiny.catiny.service.dto.FollowUserDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.FollowUser}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class FollowUserResource {

  private final Logger log = LoggerFactory.getLogger(FollowUserResource.class);

  private static final String ENTITY_NAME = "followUser";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final FollowUserService followUserService;

  private final FollowUserRepository followUserRepository;

  private final FollowUserQueryService followUserQueryService;

  public FollowUserResource(
    FollowUserService followUserService,
    FollowUserRepository followUserRepository,
    FollowUserQueryService followUserQueryService
  ) {
    this.followUserService = followUserService;
    this.followUserRepository = followUserRepository;
    this.followUserQueryService = followUserQueryService;
  }

  /**
   * {@code POST  /follow-users} : Create a new followUser.
   *
   * @param followUserDTO the followUserDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new followUserDTO, or with status {@code 400 (Bad Request)} if the followUser has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/follow-users")
  public ResponseEntity<FollowUserDTO> createFollowUser(@Valid @RequestBody FollowUserDTO followUserDTO) throws URISyntaxException {
    log.debug("REST request to save FollowUser : {}", followUserDTO);
    if (followUserDTO.getId() != null) {
      throw new BadRequestAlertException("A new followUser cannot already have an ID", ENTITY_NAME, "idexists");
    }
    FollowUserDTO result = followUserService.save(followUserDTO);
    return ResponseEntity
      .created(new URI("/api/follow-users/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /follow-users/:id} : Updates an existing followUser.
   *
   * @param id the id of the followUserDTO to save.
   * @param followUserDTO the followUserDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated followUserDTO,
   * or with status {@code 400 (Bad Request)} if the followUserDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the followUserDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/follow-users/{id}")
  public ResponseEntity<FollowUserDTO> updateFollowUser(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody FollowUserDTO followUserDTO
  ) throws URISyntaxException {
    log.debug("REST request to update FollowUser : {}, {}", id, followUserDTO);
    if (followUserDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, followUserDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!followUserRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    FollowUserDTO result = followUserService.save(followUserDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, followUserDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /follow-users/:id} : Partial updates given fields of an existing followUser, field will ignore if it is null
   *
   * @param id the id of the followUserDTO to save.
   * @param followUserDTO the followUserDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated followUserDTO,
   * or with status {@code 400 (Bad Request)} if the followUserDTO is not valid,
   * or with status {@code 404 (Not Found)} if the followUserDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the followUserDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/follow-users/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<FollowUserDTO> partialUpdateFollowUser(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody FollowUserDTO followUserDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update FollowUser partially : {}, {}", id, followUserDTO);
    if (followUserDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, followUserDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!followUserRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<FollowUserDTO> result = followUserService.partialUpdate(followUserDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, followUserDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /follow-users} : get all the followUsers.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of followUsers in body.
   */
  @GetMapping("/follow-users")
  public ResponseEntity<List<FollowUserDTO>> getAllFollowUsers(FollowUserCriteria criteria, Pageable pageable) {
    log.debug("REST request to get FollowUsers by criteria: {}", criteria);
    Page<FollowUserDTO> page = followUserQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /follow-users/count} : count all the followUsers.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/follow-users/count")
  public ResponseEntity<Long> countFollowUsers(FollowUserCriteria criteria) {
    log.debug("REST request to count FollowUsers by criteria: {}", criteria);
    return ResponseEntity.ok().body(followUserQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /follow-users/:id} : get the "id" followUser.
   *
   * @param id the id of the followUserDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the followUserDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/follow-users/{id}")
  public ResponseEntity<FollowUserDTO> getFollowUser(@PathVariable Long id) {
    log.debug("REST request to get FollowUser : {}", id);
    Optional<FollowUserDTO> followUserDTO = followUserService.findOne(id);
    return ResponseUtil.wrapOrNotFound(followUserDTO);
  }

  /**
   * {@code DELETE  /follow-users/:id} : delete the "id" followUser.
   *
   * @param id the id of the followUserDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/follow-users/{id}")
  public ResponseEntity<Void> deleteFollowUser(@PathVariable Long id) {
    log.debug("REST request to delete FollowUser : {}", id);
    followUserService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/follow-users?query=:query} : search for the followUser corresponding
   * to the query.
   *
   * @param query the query of the followUser search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/follow-users")
  public ResponseEntity<List<FollowUserDTO>> searchFollowUsers(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of FollowUsers for query {}", query);
    Page<FollowUserDTO> page = followUserService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
