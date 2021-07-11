package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.RankUserRepository;
import com.regitiny.catiny.service.RankUserQueryService;
import com.regitiny.catiny.service.RankUserService;
import com.regitiny.catiny.service.criteria.RankUserCriteria;
import com.regitiny.catiny.service.dto.RankUserDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.RankUser}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class RankUserResource {

  private final Logger log = LoggerFactory.getLogger(RankUserResource.class);

  private static final String ENTITY_NAME = "rankUser";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final RankUserService rankUserService;

  private final RankUserRepository rankUserRepository;

  private final RankUserQueryService rankUserQueryService;

  public RankUserResource(
    RankUserService rankUserService,
    RankUserRepository rankUserRepository,
    RankUserQueryService rankUserQueryService
  ) {
    this.rankUserService = rankUserService;
    this.rankUserRepository = rankUserRepository;
    this.rankUserQueryService = rankUserQueryService;
  }

  /**
   * {@code POST  /rank-users} : Create a new rankUser.
   *
   * @param rankUserDTO the rankUserDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rankUserDTO, or with status {@code 400 (Bad Request)} if the rankUser has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/rank-users")
  public ResponseEntity<RankUserDTO> createRankUser(@Valid @RequestBody RankUserDTO rankUserDTO) throws URISyntaxException {
    log.debug("REST request to save RankUser : {}", rankUserDTO);
    if (rankUserDTO.getId() != null) {
      throw new BadRequestAlertException("A new rankUser cannot already have an ID", ENTITY_NAME, "idexists");
    }
    RankUserDTO result = rankUserService.save(rankUserDTO);
    return ResponseEntity
      .created(new URI("/api/rank-users/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /rank-users/:id} : Updates an existing rankUser.
   *
   * @param id the id of the rankUserDTO to save.
   * @param rankUserDTO the rankUserDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rankUserDTO,
   * or with status {@code 400 (Bad Request)} if the rankUserDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the rankUserDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/rank-users/{id}")
  public ResponseEntity<RankUserDTO> updateRankUser(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody RankUserDTO rankUserDTO
  ) throws URISyntaxException {
    log.debug("REST request to update RankUser : {}, {}", id, rankUserDTO);
    if (rankUserDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, rankUserDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!rankUserRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    RankUserDTO result = rankUserService.save(rankUserDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rankUserDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /rank-users/:id} : Partial updates given fields of an existing rankUser, field will ignore if it is null
   *
   * @param id the id of the rankUserDTO to save.
   * @param rankUserDTO the rankUserDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rankUserDTO,
   * or with status {@code 400 (Bad Request)} if the rankUserDTO is not valid,
   * or with status {@code 404 (Not Found)} if the rankUserDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the rankUserDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/rank-users/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<RankUserDTO> partialUpdateRankUser(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody RankUserDTO rankUserDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update RankUser partially : {}, {}", id, rankUserDTO);
    if (rankUserDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, rankUserDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!rankUserRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<RankUserDTO> result = rankUserService.partialUpdate(rankUserDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rankUserDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /rank-users} : get all the rankUsers.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rankUsers in body.
   */
  @GetMapping("/rank-users")
  public ResponseEntity<List<RankUserDTO>> getAllRankUsers(RankUserCriteria criteria, Pageable pageable) {
    log.debug("REST request to get RankUsers by criteria: {}", criteria);
    Page<RankUserDTO> page = rankUserQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /rank-users/count} : count all the rankUsers.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/rank-users/count")
  public ResponseEntity<Long> countRankUsers(RankUserCriteria criteria) {
    log.debug("REST request to count RankUsers by criteria: {}", criteria);
    return ResponseEntity.ok().body(rankUserQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /rank-users/:id} : get the "id" rankUser.
   *
   * @param id the id of the rankUserDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rankUserDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/rank-users/{id}")
  public ResponseEntity<RankUserDTO> getRankUser(@PathVariable Long id) {
    log.debug("REST request to get RankUser : {}", id);
    Optional<RankUserDTO> rankUserDTO = rankUserService.findOne(id);
    return ResponseUtil.wrapOrNotFound(rankUserDTO);
  }

  /**
   * {@code DELETE  /rank-users/:id} : delete the "id" rankUser.
   *
   * @param id the id of the rankUserDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/rank-users/{id}")
  public ResponseEntity<Void> deleteRankUser(@PathVariable Long id) {
    log.debug("REST request to delete RankUser : {}", id);
    rankUserService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/rank-users?query=:query} : search for the rankUser corresponding
   * to the query.
   *
   * @param query the query of the rankUser search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/rank-users")
  public ResponseEntity<List<RankUserDTO>> searchRankUsers(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of RankUsers for query {}", query);
    Page<RankUserDTO> page = rankUserService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
