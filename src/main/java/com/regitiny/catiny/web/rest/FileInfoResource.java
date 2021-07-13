package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.FileInfoRepository;
import com.regitiny.catiny.service.FileInfoQueryService;
import com.regitiny.catiny.service.FileInfoService;
import com.regitiny.catiny.service.criteria.FileInfoCriteria;
import com.regitiny.catiny.service.dto.FileInfoDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.FileInfo}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class FileInfoResource {

  private final Logger log = LoggerFactory.getLogger(FileInfoResource.class);

  private static final String ENTITY_NAME = "fileInfo";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final FileInfoService fileInfoService;

  private final FileInfoRepository fileInfoRepository;

  private final FileInfoQueryService fileInfoQueryService;

  public FileInfoResource(
    FileInfoService fileInfoService,
    FileInfoRepository fileInfoRepository,
    FileInfoQueryService fileInfoQueryService
  ) {
    this.fileInfoService = fileInfoService;
    this.fileInfoRepository = fileInfoRepository;
    this.fileInfoQueryService = fileInfoQueryService;
  }

  /**
   * {@code POST  /file-infos} : Create a new fileInfo.
   *
   * @param fileInfoDTO the fileInfoDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileInfoDTO, or with status {@code 400 (Bad Request)} if the fileInfo has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/file-infos")
  public ResponseEntity<FileInfoDTO> createFileInfo(@Valid @RequestBody FileInfoDTO fileInfoDTO) throws URISyntaxException {
    log.debug("REST request to save FileInfo : {}", fileInfoDTO);
    if (fileInfoDTO.getId() != null) {
      throw new BadRequestAlertException("A new fileInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    FileInfoDTO result = fileInfoService.save(fileInfoDTO);
    return ResponseEntity
      .created(new URI("/api/file-infos/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /file-infos/:id} : Updates an existing fileInfo.
   *
   * @param id the id of the fileInfoDTO to save.
   * @param fileInfoDTO the fileInfoDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileInfoDTO,
   * or with status {@code 400 (Bad Request)} if the fileInfoDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the fileInfoDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/file-infos/{id}")
  public ResponseEntity<FileInfoDTO> updateFileInfo(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody FileInfoDTO fileInfoDTO
  ) throws URISyntaxException {
    log.debug("REST request to update FileInfo : {}, {}", id, fileInfoDTO);
    if (fileInfoDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, fileInfoDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!fileInfoRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    FileInfoDTO result = fileInfoService.save(fileInfoDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fileInfoDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /file-infos/:id} : Partial updates given fields of an existing fileInfo, field will ignore if it is null
   *
   * @param id the id of the fileInfoDTO to save.
   * @param fileInfoDTO the fileInfoDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileInfoDTO,
   * or with status {@code 400 (Bad Request)} if the fileInfoDTO is not valid,
   * or with status {@code 404 (Not Found)} if the fileInfoDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the fileInfoDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/file-infos/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<FileInfoDTO> partialUpdateFileInfo(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody FileInfoDTO fileInfoDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update FileInfo partially : {}, {}", id, fileInfoDTO);
    if (fileInfoDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, fileInfoDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!fileInfoRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<FileInfoDTO> result = fileInfoService.partialUpdate(fileInfoDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fileInfoDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /file-infos} : get all the fileInfos.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileInfos in body.
   */
  @GetMapping("/file-infos")
  public ResponseEntity<List<FileInfoDTO>> getAllFileInfos(FileInfoCriteria criteria, Pageable pageable) {
    log.debug("REST request to get FileInfos by criteria: {}", criteria);
    Page<FileInfoDTO> page = fileInfoQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /file-infos/count} : count all the fileInfos.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/file-infos/count")
  public ResponseEntity<Long> countFileInfos(FileInfoCriteria criteria) {
    log.debug("REST request to count FileInfos by criteria: {}", criteria);
    return ResponseEntity.ok().body(fileInfoQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /file-infos/:id} : get the "id" fileInfo.
   *
   * @param id the id of the fileInfoDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileInfoDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/file-infos/{id}")
  public ResponseEntity<FileInfoDTO> getFileInfo(@PathVariable Long id) {
    log.debug("REST request to get FileInfo : {}", id);
    Optional<FileInfoDTO> fileInfoDTO = fileInfoService.findOne(id);
    return ResponseUtil.wrapOrNotFound(fileInfoDTO);
  }

  /**
   * {@code DELETE  /file-infos/:id} : delete the "id" fileInfo.
   *
   * @param id the id of the fileInfoDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/file-infos/{id}")
  public ResponseEntity<Void> deleteFileInfo(@PathVariable Long id) {
    log.debug("REST request to delete FileInfo : {}", id);
    fileInfoService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/file-infos?query=:query} : search for the fileInfo corresponding
   * to the query.
   *
   * @param query the query of the fileInfo search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/file-infos")
  public ResponseEntity<List<FileInfoDTO>> searchFileInfos(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of FileInfos for query {}", query);
    Page<FileInfoDTO> page = fileInfoService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
