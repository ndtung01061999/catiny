package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.BaseInfoRepository;
import com.regitiny.catiny.service.BaseInfoQueryService;
import com.regitiny.catiny.service.BaseInfoService;
import com.regitiny.catiny.service.criteria.BaseInfoCriteria;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.BaseInfo}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class BaseInfoResource {

  private final Logger log = LoggerFactory.getLogger(BaseInfoResource.class);

  private static final String ENTITY_NAME = "baseInfo";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final BaseInfoService baseInfoService;

  private final BaseInfoRepository baseInfoRepository;

  private final BaseInfoQueryService baseInfoQueryService;

  public BaseInfoResource(
    BaseInfoService baseInfoService,
    BaseInfoRepository baseInfoRepository,
    BaseInfoQueryService baseInfoQueryService
  ) {
    this.baseInfoService = baseInfoService;
    this.baseInfoRepository = baseInfoRepository;
    this.baseInfoQueryService = baseInfoQueryService;
  }

  /**
   * {@code POST  /base-infos} : Create a new baseInfo.
   *
   * @param baseInfoDTO the baseInfoDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new baseInfoDTO, or with status {@code 400 (Bad Request)} if the baseInfo has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/base-infos")
  public ResponseEntity<BaseInfoDTO> createBaseInfo(@RequestBody BaseInfoDTO baseInfoDTO) throws URISyntaxException {
    log.debug("REST request to save BaseInfo : {}", baseInfoDTO);
    if (baseInfoDTO.getId() != null) {
      throw new BadRequestAlertException("A new baseInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    BaseInfoDTO result = baseInfoService.save(baseInfoDTO);
    return ResponseEntity
      .created(new URI("/api/base-infos/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /base-infos/:id} : Updates an existing baseInfo.
   *
   * @param id the id of the baseInfoDTO to save.
   * @param baseInfoDTO the baseInfoDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated baseInfoDTO,
   * or with status {@code 400 (Bad Request)} if the baseInfoDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the baseInfoDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/base-infos/{id}")
  public ResponseEntity<BaseInfoDTO> updateBaseInfo(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody BaseInfoDTO baseInfoDTO
  ) throws URISyntaxException {
    log.debug("REST request to update BaseInfo : {}, {}", id, baseInfoDTO);
    if (baseInfoDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, baseInfoDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!baseInfoRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    BaseInfoDTO result = baseInfoService.save(baseInfoDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, baseInfoDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /base-infos/:id} : Partial updates given fields of an existing baseInfo, field will ignore if it is null
   *
   * @param id the id of the baseInfoDTO to save.
   * @param baseInfoDTO the baseInfoDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated baseInfoDTO,
   * or with status {@code 400 (Bad Request)} if the baseInfoDTO is not valid,
   * or with status {@code 404 (Not Found)} if the baseInfoDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the baseInfoDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/base-infos/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<BaseInfoDTO> partialUpdateBaseInfo(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody BaseInfoDTO baseInfoDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update BaseInfo partially : {}, {}", id, baseInfoDTO);
    if (baseInfoDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, baseInfoDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!baseInfoRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<BaseInfoDTO> result = baseInfoService.partialUpdate(baseInfoDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, baseInfoDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /base-infos} : get all the baseInfos.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of baseInfos in body.
   */
  @GetMapping("/base-infos")
  public ResponseEntity<List<BaseInfoDTO>> getAllBaseInfos(BaseInfoCriteria criteria, Pageable pageable) {
    log.debug("REST request to get BaseInfos by criteria: {}", criteria);
    Page<BaseInfoDTO> page = baseInfoQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /base-infos/count} : count all the baseInfos.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/base-infos/count")
  public ResponseEntity<Long> countBaseInfos(BaseInfoCriteria criteria) {
    log.debug("REST request to count BaseInfos by criteria: {}", criteria);
    return ResponseEntity.ok().body(baseInfoQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /base-infos/:id} : get the "id" baseInfo.
   *
   * @param id the id of the baseInfoDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the baseInfoDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/base-infos/{id}")
  public ResponseEntity<BaseInfoDTO> getBaseInfo(@PathVariable Long id) {
    log.debug("REST request to get BaseInfo : {}", id);
    Optional<BaseInfoDTO> baseInfoDTO = baseInfoService.findOne(id);
    return ResponseUtil.wrapOrNotFound(baseInfoDTO);
  }

  /**
   * {@code DELETE  /base-infos/:id} : delete the "id" baseInfo.
   *
   * @param id the id of the baseInfoDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/base-infos/{id}")
  public ResponseEntity<Void> deleteBaseInfo(@PathVariable Long id) {
    log.debug("REST request to delete BaseInfo : {}", id);
    baseInfoService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/base-infos?query=:query} : search for the baseInfo corresponding
   * to the query.
   *
   * @param query the query of the baseInfo search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/base-infos")
  public ResponseEntity<List<BaseInfoDTO>> searchBaseInfos(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of BaseInfos for query {}", query);
    Page<BaseInfoDTO> page = baseInfoService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
