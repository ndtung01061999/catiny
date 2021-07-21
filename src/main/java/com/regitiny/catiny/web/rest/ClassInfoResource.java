package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.ClassInfoRepository;
import com.regitiny.catiny.service.ClassInfoQueryService;
import com.regitiny.catiny.service.ClassInfoService;
import com.regitiny.catiny.service.criteria.ClassInfoCriteria;
import com.regitiny.catiny.service.dto.ClassInfoDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.ClassInfo}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class ClassInfoResource {

  private final Logger log = LoggerFactory.getLogger(ClassInfoResource.class);

  private static final String ENTITY_NAME = "classInfo";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final ClassInfoService classInfoService;

  private final ClassInfoRepository classInfoRepository;

  private final ClassInfoQueryService classInfoQueryService;

  public ClassInfoResource(
    ClassInfoService classInfoService,
    ClassInfoRepository classInfoRepository,
    ClassInfoQueryService classInfoQueryService
  ) {
    this.classInfoService = classInfoService;
    this.classInfoRepository = classInfoRepository;
    this.classInfoQueryService = classInfoQueryService;
  }

  /**
   * {@code POST  /class-infos} : Create a new classInfo.
   *
   * @param classInfoDTO the classInfoDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new classInfoDTO, or with status {@code 400 (Bad Request)} if the classInfo has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/class-infos")
  public ResponseEntity<ClassInfoDTO> createClassInfo(@Valid @RequestBody ClassInfoDTO classInfoDTO) throws URISyntaxException {
    log.debug("REST request to save ClassInfo : {}", classInfoDTO);
    if (classInfoDTO.getId() != null) {
      throw new BadRequestAlertException("A new classInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    ClassInfoDTO result = classInfoService.save(classInfoDTO);
    return ResponseEntity
      .created(new URI("/api/class-infos/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /class-infos/:id} : Updates an existing classInfo.
   *
   * @param id the id of the classInfoDTO to save.
   * @param classInfoDTO the classInfoDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated classInfoDTO,
   * or with status {@code 400 (Bad Request)} if the classInfoDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the classInfoDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/class-infos/{id}")
  public ResponseEntity<ClassInfoDTO> updateClassInfo(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody ClassInfoDTO classInfoDTO
  ) throws URISyntaxException {
    log.debug("REST request to update ClassInfo : {}, {}", id, classInfoDTO);
    if (classInfoDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, classInfoDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!classInfoRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    ClassInfoDTO result = classInfoService.save(classInfoDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, classInfoDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /class-infos/:id} : Partial updates given fields of an existing classInfo, field will ignore if it is null
   *
   * @param id the id of the classInfoDTO to save.
   * @param classInfoDTO the classInfoDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated classInfoDTO,
   * or with status {@code 400 (Bad Request)} if the classInfoDTO is not valid,
   * or with status {@code 404 (Not Found)} if the classInfoDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the classInfoDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/class-infos/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<ClassInfoDTO> partialUpdateClassInfo(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody ClassInfoDTO classInfoDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update ClassInfo partially : {}, {}", id, classInfoDTO);
    if (classInfoDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, classInfoDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!classInfoRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<ClassInfoDTO> result = classInfoService.partialUpdate(classInfoDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, classInfoDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /class-infos} : get all the classInfos.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of classInfos in body.
   */
  @GetMapping("/class-infos")
  public ResponseEntity<List<ClassInfoDTO>> getAllClassInfos(ClassInfoCriteria criteria, Pageable pageable) {
    log.debug("REST request to get ClassInfos by criteria: {}", criteria);
    Page<ClassInfoDTO> page = classInfoQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /class-infos/count} : count all the classInfos.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/class-infos/count")
  public ResponseEntity<Long> countClassInfos(ClassInfoCriteria criteria) {
    log.debug("REST request to count ClassInfos by criteria: {}", criteria);
    return ResponseEntity.ok().body(classInfoQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /class-infos/:id} : get the "id" classInfo.
   *
   * @param id the id of the classInfoDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the classInfoDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/class-infos/{id}")
  public ResponseEntity<ClassInfoDTO> getClassInfo(@PathVariable Long id) {
    log.debug("REST request to get ClassInfo : {}", id);
    Optional<ClassInfoDTO> classInfoDTO = classInfoService.findOne(id);
    return ResponseUtil.wrapOrNotFound(classInfoDTO);
  }

  /**
   * {@code DELETE  /class-infos/:id} : delete the "id" classInfo.
   *
   * @param id the id of the classInfoDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/class-infos/{id}")
  public ResponseEntity<Void> deleteClassInfo(@PathVariable Long id) {
    log.debug("REST request to delete ClassInfo : {}", id);
    classInfoService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/class-infos?query=:query} : search for the classInfo corresponding
   * to the query.
   *
   * @param query the query of the classInfo search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/class-infos")
  public ResponseEntity<List<ClassInfoDTO>> searchClassInfos(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of ClassInfos for query {}", query);
    Page<ClassInfoDTO> page = classInfoService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
