package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.VideoLiveStreamBufferRepository;
import com.regitiny.catiny.service.VideoLiveStreamBufferQueryService;
import com.regitiny.catiny.service.VideoLiveStreamBufferService;
import com.regitiny.catiny.service.criteria.VideoLiveStreamBufferCriteria;
import com.regitiny.catiny.service.dto.VideoLiveStreamBufferDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.VideoLiveStreamBuffer}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class VideoLiveStreamBufferResource {

  private final Logger log = LoggerFactory.getLogger(VideoLiveStreamBufferResource.class);

  private static final String ENTITY_NAME = "videoLiveStreamBuffer";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final VideoLiveStreamBufferService videoLiveStreamBufferService;

  private final VideoLiveStreamBufferRepository videoLiveStreamBufferRepository;

  private final VideoLiveStreamBufferQueryService videoLiveStreamBufferQueryService;

  public VideoLiveStreamBufferResource(
    VideoLiveStreamBufferService videoLiveStreamBufferService,
    VideoLiveStreamBufferRepository videoLiveStreamBufferRepository,
    VideoLiveStreamBufferQueryService videoLiveStreamBufferQueryService
  ) {
    this.videoLiveStreamBufferService = videoLiveStreamBufferService;
    this.videoLiveStreamBufferRepository = videoLiveStreamBufferRepository;
    this.videoLiveStreamBufferQueryService = videoLiveStreamBufferQueryService;
  }

  /**
   * {@code POST  /video-live-stream-buffers} : Create a new videoLiveStreamBuffer.
   *
   * @param videoLiveStreamBufferDTO the videoLiveStreamBufferDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new videoLiveStreamBufferDTO, or with status {@code 400 (Bad Request)} if the videoLiveStreamBuffer has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/video-live-stream-buffers")
  public ResponseEntity<VideoLiveStreamBufferDTO> createVideoLiveStreamBuffer(
    @Valid @RequestBody VideoLiveStreamBufferDTO videoLiveStreamBufferDTO
  ) throws URISyntaxException {
    log.debug("REST request to save VideoLiveStreamBuffer : {}", videoLiveStreamBufferDTO);
    if (videoLiveStreamBufferDTO.getId() != null) {
      throw new BadRequestAlertException("A new videoLiveStreamBuffer cannot already have an ID", ENTITY_NAME, "idexists");
    }
    VideoLiveStreamBufferDTO result = videoLiveStreamBufferService.save(videoLiveStreamBufferDTO);
    return ResponseEntity
      .created(new URI("/api/video-live-stream-buffers/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /video-live-stream-buffers/:id} : Updates an existing videoLiveStreamBuffer.
   *
   * @param id the id of the videoLiveStreamBufferDTO to save.
   * @param videoLiveStreamBufferDTO the videoLiveStreamBufferDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoLiveStreamBufferDTO,
   * or with status {@code 400 (Bad Request)} if the videoLiveStreamBufferDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the videoLiveStreamBufferDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/video-live-stream-buffers/{id}")
  public ResponseEntity<VideoLiveStreamBufferDTO> updateVideoLiveStreamBuffer(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody VideoLiveStreamBufferDTO videoLiveStreamBufferDTO
  ) throws URISyntaxException {
    log.debug("REST request to update VideoLiveStreamBuffer : {}, {}", id, videoLiveStreamBufferDTO);
    if (videoLiveStreamBufferDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, videoLiveStreamBufferDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!videoLiveStreamBufferRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    VideoLiveStreamBufferDTO result = videoLiveStreamBufferService.save(videoLiveStreamBufferDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, videoLiveStreamBufferDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /video-live-stream-buffers/:id} : Partial updates given fields of an existing videoLiveStreamBuffer, field will ignore if it is null
   *
   * @param id the id of the videoLiveStreamBufferDTO to save.
   * @param videoLiveStreamBufferDTO the videoLiveStreamBufferDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoLiveStreamBufferDTO,
   * or with status {@code 400 (Bad Request)} if the videoLiveStreamBufferDTO is not valid,
   * or with status {@code 404 (Not Found)} if the videoLiveStreamBufferDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the videoLiveStreamBufferDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/video-live-stream-buffers/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<VideoLiveStreamBufferDTO> partialUpdateVideoLiveStreamBuffer(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody VideoLiveStreamBufferDTO videoLiveStreamBufferDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update VideoLiveStreamBuffer partially : {}, {}", id, videoLiveStreamBufferDTO);
    if (videoLiveStreamBufferDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, videoLiveStreamBufferDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!videoLiveStreamBufferRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<VideoLiveStreamBufferDTO> result = videoLiveStreamBufferService.partialUpdate(videoLiveStreamBufferDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, videoLiveStreamBufferDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /video-live-stream-buffers} : get all the videoLiveStreamBuffers.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of videoLiveStreamBuffers in body.
   */
  @GetMapping("/video-live-stream-buffers")
  public ResponseEntity<List<VideoLiveStreamBufferDTO>> getAllVideoLiveStreamBuffers(
    VideoLiveStreamBufferCriteria criteria,
    Pageable pageable
  ) {
    log.debug("REST request to get VideoLiveStreamBuffers by criteria: {}", criteria);
    Page<VideoLiveStreamBufferDTO> page = videoLiveStreamBufferQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /video-live-stream-buffers/count} : count all the videoLiveStreamBuffers.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/video-live-stream-buffers/count")
  public ResponseEntity<Long> countVideoLiveStreamBuffers(VideoLiveStreamBufferCriteria criteria) {
    log.debug("REST request to count VideoLiveStreamBuffers by criteria: {}", criteria);
    return ResponseEntity.ok().body(videoLiveStreamBufferQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /video-live-stream-buffers/:id} : get the "id" videoLiveStreamBuffer.
   *
   * @param id the id of the videoLiveStreamBufferDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the videoLiveStreamBufferDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/video-live-stream-buffers/{id}")
  public ResponseEntity<VideoLiveStreamBufferDTO> getVideoLiveStreamBuffer(@PathVariable Long id) {
    log.debug("REST request to get VideoLiveStreamBuffer : {}", id);
    Optional<VideoLiveStreamBufferDTO> videoLiveStreamBufferDTO = videoLiveStreamBufferService.findOne(id);
    return ResponseUtil.wrapOrNotFound(videoLiveStreamBufferDTO);
  }

  /**
   * {@code DELETE  /video-live-stream-buffers/:id} : delete the "id" videoLiveStreamBuffer.
   *
   * @param id the id of the videoLiveStreamBufferDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/video-live-stream-buffers/{id}")
  public ResponseEntity<Void> deleteVideoLiveStreamBuffer(@PathVariable Long id) {
    log.debug("REST request to delete VideoLiveStreamBuffer : {}", id);
    videoLiveStreamBufferService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/video-live-stream-buffers?query=:query} : search for the videoLiveStreamBuffer corresponding
   * to the query.
   *
   * @param query the query of the videoLiveStreamBuffer search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/video-live-stream-buffers")
  public ResponseEntity<List<VideoLiveStreamBufferDTO>> searchVideoLiveStreamBuffers(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of VideoLiveStreamBuffers for query {}", query);
    Page<VideoLiveStreamBufferDTO> page = videoLiveStreamBufferService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
