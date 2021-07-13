package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.RankGroupRepository;
import com.regitiny.catiny.service.RankGroupQueryService;
import com.regitiny.catiny.service.RankGroupService;
import com.regitiny.catiny.service.criteria.RankGroupCriteria;
import com.regitiny.catiny.service.dto.RankGroupDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.RankGroup}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class RankGroupResource {

  private final Logger log = LoggerFactory.getLogger(RankGroupResource.class);

  private static final String ENTITY_NAME = "rankGroup";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final RankGroupService rankGroupService;

  private final RankGroupRepository rankGroupRepository;

  private final RankGroupQueryService rankGroupQueryService;

  public RankGroupResource(
    RankGroupService rankGroupService,
    RankGroupRepository rankGroupRepository,
    RankGroupQueryService rankGroupQueryService
  ) {
    this.rankGroupService = rankGroupService;
    this.rankGroupRepository = rankGroupRepository;
    this.rankGroupQueryService = rankGroupQueryService;
  }

  /**
   * {@code POST  /rank-groups} : Create a new rankGroup.
   *
   * @param rankGroupDTO the rankGroupDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rankGroupDTO, or with status {@code 400 (Bad Request)} if the rankGroup has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/rank-groups")
  public ResponseEntity<RankGroupDTO> createRankGroup(@Valid @RequestBody RankGroupDTO rankGroupDTO) throws URISyntaxException {
    log.debug("REST request to save RankGroup : {}", rankGroupDTO);
    if (rankGroupDTO.getId() != null) {
      throw new BadRequestAlertException("A new rankGroup cannot already have an ID", ENTITY_NAME, "idexists");
    }
    RankGroupDTO result = rankGroupService.save(rankGroupDTO);
    return ResponseEntity
      .created(new URI("/api/rank-groups/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /rank-groups/:id} : Updates an existing rankGroup.
   *
   * @param id the id of the rankGroupDTO to save.
   * @param rankGroupDTO the rankGroupDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rankGroupDTO,
   * or with status {@code 400 (Bad Request)} if the rankGroupDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the rankGroupDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/rank-groups/{id}")
  public ResponseEntity<RankGroupDTO> updateRankGroup(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody RankGroupDTO rankGroupDTO
  ) throws URISyntaxException {
    log.debug("REST request to update RankGroup : {}, {}", id, rankGroupDTO);
    if (rankGroupDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, rankGroupDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!rankGroupRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    RankGroupDTO result = rankGroupService.save(rankGroupDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rankGroupDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /rank-groups/:id} : Partial updates given fields of an existing rankGroup, field will ignore if it is null
   *
   * @param id the id of the rankGroupDTO to save.
   * @param rankGroupDTO the rankGroupDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rankGroupDTO,
   * or with status {@code 400 (Bad Request)} if the rankGroupDTO is not valid,
   * or with status {@code 404 (Not Found)} if the rankGroupDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the rankGroupDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/rank-groups/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<RankGroupDTO> partialUpdateRankGroup(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody RankGroupDTO rankGroupDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update RankGroup partially : {}, {}", id, rankGroupDTO);
    if (rankGroupDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, rankGroupDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!rankGroupRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<RankGroupDTO> result = rankGroupService.partialUpdate(rankGroupDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rankGroupDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /rank-groups} : get all the rankGroups.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rankGroups in body.
   */
  @GetMapping("/rank-groups")
  public ResponseEntity<List<RankGroupDTO>> getAllRankGroups(RankGroupCriteria criteria, Pageable pageable) {
    log.debug("REST request to get RankGroups by criteria: {}", criteria);
    Page<RankGroupDTO> page = rankGroupQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /rank-groups/count} : count all the rankGroups.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/rank-groups/count")
  public ResponseEntity<Long> countRankGroups(RankGroupCriteria criteria) {
    log.debug("REST request to count RankGroups by criteria: {}", criteria);
    return ResponseEntity.ok().body(rankGroupQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /rank-groups/:id} : get the "id" rankGroup.
   *
   * @param id the id of the rankGroupDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rankGroupDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/rank-groups/{id}")
  public ResponseEntity<RankGroupDTO> getRankGroup(@PathVariable Long id) {
    log.debug("REST request to get RankGroup : {}", id);
    Optional<RankGroupDTO> rankGroupDTO = rankGroupService.findOne(id);
    return ResponseUtil.wrapOrNotFound(rankGroupDTO);
  }

  /**
   * {@code DELETE  /rank-groups/:id} : delete the "id" rankGroup.
   *
   * @param id the id of the rankGroupDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/rank-groups/{id}")
  public ResponseEntity<Void> deleteRankGroup(@PathVariable Long id) {
    log.debug("REST request to delete RankGroup : {}", id);
    rankGroupService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/rank-groups?query=:query} : search for the rankGroup corresponding
   * to the query.
   *
   * @param query the query of the rankGroup search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/rank-groups")
  public ResponseEntity<List<RankGroupDTO>> searchRankGroups(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of RankGroups for query {}", query);
    Page<RankGroupDTO> page = rankGroupService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
