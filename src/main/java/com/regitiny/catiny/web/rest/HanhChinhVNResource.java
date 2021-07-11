package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.HanhChinhVNRepository;
import com.regitiny.catiny.service.HanhChinhVNQueryService;
import com.regitiny.catiny.service.HanhChinhVNService;
import com.regitiny.catiny.service.criteria.HanhChinhVNCriteria;
import com.regitiny.catiny.service.dto.HanhChinhVNDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.HanhChinhVN}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class HanhChinhVNResource {

  private final Logger log = LoggerFactory.getLogger(HanhChinhVNResource.class);

  private static final String ENTITY_NAME = "hanhChinhVN";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final HanhChinhVNService hanhChinhVNService;

  private final HanhChinhVNRepository hanhChinhVNRepository;

  private final HanhChinhVNQueryService hanhChinhVNQueryService;

  public HanhChinhVNResource(
    HanhChinhVNService hanhChinhVNService,
    HanhChinhVNRepository hanhChinhVNRepository,
    HanhChinhVNQueryService hanhChinhVNQueryService
  ) {
    this.hanhChinhVNService = hanhChinhVNService;
    this.hanhChinhVNRepository = hanhChinhVNRepository;
    this.hanhChinhVNQueryService = hanhChinhVNQueryService;
  }

  /**
   * {@code POST  /hanh-chinh-vns} : Create a new hanhChinhVN.
   *
   * @param hanhChinhVNDTO the hanhChinhVNDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hanhChinhVNDTO, or with status {@code 400 (Bad Request)} if the hanhChinhVN has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/hanh-chinh-vns")
  public ResponseEntity<HanhChinhVNDTO> createHanhChinhVN(@Valid @RequestBody HanhChinhVNDTO hanhChinhVNDTO) throws URISyntaxException {
    log.debug("REST request to save HanhChinhVN : {}", hanhChinhVNDTO);
    if (hanhChinhVNDTO.getId() != null) {
      throw new BadRequestAlertException("A new hanhChinhVN cannot already have an ID", ENTITY_NAME, "idexists");
    }
    HanhChinhVNDTO result = hanhChinhVNService.save(hanhChinhVNDTO);
    return ResponseEntity
      .created(new URI("/api/hanh-chinh-vns/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /hanh-chinh-vns/:id} : Updates an existing hanhChinhVN.
   *
   * @param id the id of the hanhChinhVNDTO to save.
   * @param hanhChinhVNDTO the hanhChinhVNDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hanhChinhVNDTO,
   * or with status {@code 400 (Bad Request)} if the hanhChinhVNDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the hanhChinhVNDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/hanh-chinh-vns/{id}")
  public ResponseEntity<HanhChinhVNDTO> updateHanhChinhVN(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody HanhChinhVNDTO hanhChinhVNDTO
  ) throws URISyntaxException {
    log.debug("REST request to update HanhChinhVN : {}, {}", id, hanhChinhVNDTO);
    if (hanhChinhVNDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, hanhChinhVNDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!hanhChinhVNRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    HanhChinhVNDTO result = hanhChinhVNService.save(hanhChinhVNDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, hanhChinhVNDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /hanh-chinh-vns/:id} : Partial updates given fields of an existing hanhChinhVN, field will ignore if it is null
   *
   * @param id the id of the hanhChinhVNDTO to save.
   * @param hanhChinhVNDTO the hanhChinhVNDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hanhChinhVNDTO,
   * or with status {@code 400 (Bad Request)} if the hanhChinhVNDTO is not valid,
   * or with status {@code 404 (Not Found)} if the hanhChinhVNDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the hanhChinhVNDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/hanh-chinh-vns/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<HanhChinhVNDTO> partialUpdateHanhChinhVN(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody HanhChinhVNDTO hanhChinhVNDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update HanhChinhVN partially : {}, {}", id, hanhChinhVNDTO);
    if (hanhChinhVNDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, hanhChinhVNDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!hanhChinhVNRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<HanhChinhVNDTO> result = hanhChinhVNService.partialUpdate(hanhChinhVNDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, hanhChinhVNDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /hanh-chinh-vns} : get all the hanhChinhVNS.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hanhChinhVNS in body.
   */
  @GetMapping("/hanh-chinh-vns")
  public ResponseEntity<List<HanhChinhVNDTO>> getAllHanhChinhVNS(HanhChinhVNCriteria criteria, Pageable pageable) {
    log.debug("REST request to get HanhChinhVNS by criteria: {}", criteria);
    Page<HanhChinhVNDTO> page = hanhChinhVNQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /hanh-chinh-vns/count} : count all the hanhChinhVNS.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/hanh-chinh-vns/count")
  public ResponseEntity<Long> countHanhChinhVNS(HanhChinhVNCriteria criteria) {
    log.debug("REST request to count HanhChinhVNS by criteria: {}", criteria);
    return ResponseEntity.ok().body(hanhChinhVNQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /hanh-chinh-vns/:id} : get the "id" hanhChinhVN.
   *
   * @param id the id of the hanhChinhVNDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hanhChinhVNDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/hanh-chinh-vns/{id}")
  public ResponseEntity<HanhChinhVNDTO> getHanhChinhVN(@PathVariable Long id) {
    log.debug("REST request to get HanhChinhVN : {}", id);
    Optional<HanhChinhVNDTO> hanhChinhVNDTO = hanhChinhVNService.findOne(id);
    return ResponseUtil.wrapOrNotFound(hanhChinhVNDTO);
  }

  /**
   * {@code DELETE  /hanh-chinh-vns/:id} : delete the "id" hanhChinhVN.
   *
   * @param id the id of the hanhChinhVNDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/hanh-chinh-vns/{id}")
  public ResponseEntity<Void> deleteHanhChinhVN(@PathVariable Long id) {
    log.debug("REST request to delete HanhChinhVN : {}", id);
    hanhChinhVNService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/hanh-chinh-vns?query=:query} : search for the hanhChinhVN corresponding
   * to the query.
   *
   * @param query the query of the hanhChinhVN search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/hanh-chinh-vns")
  public ResponseEntity<List<HanhChinhVNDTO>> searchHanhChinhVNS(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of HanhChinhVNS for query {}", query);
    Page<HanhChinhVNDTO> page = hanhChinhVNService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
