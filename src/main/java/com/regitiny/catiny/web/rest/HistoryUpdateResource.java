package com.regitiny.catiny.web.rest;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.HistoryUpdateRepository;
import com.regitiny.catiny.service.HistoryUpdateQueryService;
import com.regitiny.catiny.service.HistoryUpdateService;
import com.regitiny.catiny.service.criteria.HistoryUpdateCriteria;
import com.regitiny.catiny.service.dto.HistoryUpdateDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.HistoryUpdate}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class HistoryUpdateResource
{

  private static final String ENTITY_NAME = "historyUpdate";
  private final Logger log = LoggerFactory.getLogger(HistoryUpdateResource.class);
  private final HistoryUpdateService historyUpdateService;
  private final HistoryUpdateRepository historyUpdateRepository;
  private final HistoryUpdateQueryService historyUpdateQueryService;
  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  public HistoryUpdateResource(
    HistoryUpdateService historyUpdateService,
    HistoryUpdateRepository historyUpdateRepository,
    HistoryUpdateQueryService historyUpdateQueryService
  )
  {
    this.historyUpdateService = historyUpdateService;
    this.historyUpdateRepository = historyUpdateRepository;
    this.historyUpdateQueryService = historyUpdateQueryService;
  }

  /**
   * {@code POST  /history-updates} : Create a new historyUpdate.
   *
   * @param historyUpdateDTO the historyUpdateDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new historyUpdateDTO, or with status {@code 400 (Bad Request)} if the historyUpdate has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/history-updates")
  public ResponseEntity<HistoryUpdateDTO> createHistoryUpdate(@Valid @RequestBody HistoryUpdateDTO historyUpdateDTO)
    throws URISyntaxException
  {
    log.debug("REST request to save HistoryUpdate : {}", historyUpdateDTO);
    if (historyUpdateDTO.getId() != null)
    {
      throw new BadRequestAlertException("A new historyUpdate cannot already have an ID", ENTITY_NAME, "idexists");
    }
    HistoryUpdateDTO result = historyUpdateService.save(historyUpdateDTO);
    return ResponseEntity
      .created(new URI("/api/history-updates/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /history-updates/:id} : Updates an existing historyUpdate.
   *
   * @param id               the id of the historyUpdateDTO to save.
   * @param historyUpdateDTO the historyUpdateDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historyUpdateDTO,
   * or with status {@code 400 (Bad Request)} if the historyUpdateDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the historyUpdateDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/history-updates/{id}")
  public ResponseEntity<HistoryUpdateDTO> updateHistoryUpdate(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody HistoryUpdateDTO historyUpdateDTO
  ) throws URISyntaxException
  {
    log.debug("REST request to update HistoryUpdate : {}, {}", id, historyUpdateDTO);
    if (historyUpdateDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, historyUpdateDTO.getId()))
    {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!historyUpdateRepository.existsById(id))
    {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    HistoryUpdateDTO result = historyUpdateService.save(historyUpdateDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, historyUpdateDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /history-updates/:id} : Partial updates given fields of an existing historyUpdate, field will ignore if it is null
   *
   * @param id               the id of the historyUpdateDTO to save.
   * @param historyUpdateDTO the historyUpdateDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historyUpdateDTO,
   * or with status {@code 400 (Bad Request)} if the historyUpdateDTO is not valid,
   * or with status {@code 404 (Not Found)} if the historyUpdateDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the historyUpdateDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/history-updates/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<HistoryUpdateDTO> partialUpdateHistoryUpdate(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody HistoryUpdateDTO historyUpdateDTO
  ) throws URISyntaxException
  {
    log.debug("REST request to partial update HistoryUpdate partially : {}, {}", id, historyUpdateDTO);
    if (historyUpdateDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, historyUpdateDTO.getId()))
    {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!historyUpdateRepository.existsById(id))
    {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<HistoryUpdateDTO> result = historyUpdateService.partialUpdate(historyUpdateDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, historyUpdateDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /history-updates} : get all the historyUpdates.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of historyUpdates in body.
   */
  @GetMapping("/history-updates")
  public ResponseEntity<List<HistoryUpdateDTO>> getAllHistoryUpdates(HistoryUpdateCriteria criteria, Pageable pageable)
  {
    log.debug("REST request to get HistoryUpdates by criteria: {}", criteria);
    Page<HistoryUpdateDTO> page = historyUpdateQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /history-updates/count} : count all the historyUpdates.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/history-updates/count")
  public ResponseEntity<Long> countHistoryUpdates(HistoryUpdateCriteria criteria)
  {
    log.debug("REST request to count HistoryUpdates by criteria: {}", criteria);
    return ResponseEntity.ok().body(historyUpdateQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /history-updates/:id} : get the "id" historyUpdate.
   *
   * @param id the id of the historyUpdateDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the historyUpdateDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/history-updates/{id}")
  public ResponseEntity<HistoryUpdateDTO> getHistoryUpdate(@PathVariable Long id)
  {
    log.debug("REST request to get HistoryUpdate : {}", id);
    Optional<HistoryUpdateDTO> historyUpdateDTO = historyUpdateService.findOne(id);
    return ResponseUtil.wrapOrNotFound(historyUpdateDTO);
  }

  /**
   * {@code DELETE  /history-updates/:id} : delete the "id" historyUpdate.
   *
   * @param id the id of the historyUpdateDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/history-updates/{id}")
  public ResponseEntity<Void> deleteHistoryUpdate(@PathVariable Long id)
  {
    log.debug("REST request to delete HistoryUpdate : {}", id);
    historyUpdateService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/history-updates?query=:query} : search for the historyUpdate corresponding
   * to the query.
   *
   * @param query    the query of the historyUpdate search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/history-updates")
  public ResponseEntity<List<HistoryUpdateDTO>> searchHistoryUpdates(@RequestParam String query, Pageable pageable)
  {
    log.debug("REST request to search for a page of HistoryUpdates for query {}", query);
    Page<HistoryUpdateDTO> page = historyUpdateService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
