package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.VideoStreamRepository;
import com.regitiny.catiny.service.VideoStreamQueryService;
import com.regitiny.catiny.service.VideoStreamService;
import com.regitiny.catiny.service.criteria.VideoStreamCriteria;
import com.regitiny.catiny.service.dto.VideoStreamDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.VideoStream}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class VideoStreamResource {

  private final Logger log = LoggerFactory.getLogger(VideoStreamResource.class);

  private static final String ENTITY_NAME = "videoStream";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final VideoStreamService videoStreamService;

  private final VideoStreamRepository videoStreamRepository;

  private final VideoStreamQueryService videoStreamQueryService;

  public VideoStreamResource(
    VideoStreamService videoStreamService,
    VideoStreamRepository videoStreamRepository,
    VideoStreamQueryService videoStreamQueryService
  ) {
    this.videoStreamService = videoStreamService;
    this.videoStreamRepository = videoStreamRepository;
    this.videoStreamQueryService = videoStreamQueryService;
  }

  /**
   * {@code POST  /video-streams} : Create a new videoStream.
   *
   * @param videoStreamDTO the videoStreamDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new videoStreamDTO, or with status {@code 400 (Bad Request)} if the videoStream has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/video-streams")
  public ResponseEntity<VideoStreamDTO> createVideoStream(@Valid @RequestBody VideoStreamDTO videoStreamDTO) throws URISyntaxException {
    log.debug("REST request to save VideoStream : {}", videoStreamDTO);
    if (videoStreamDTO.getId() != null) {
      throw new BadRequestAlertException("A new videoStream cannot already have an ID", ENTITY_NAME, "idexists");
    }
    VideoStreamDTO result = videoStreamService.save(videoStreamDTO);
    return ResponseEntity
      .created(new URI("/api/video-streams/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /video-streams/:id} : Updates an existing videoStream.
   *
   * @param id the id of the videoStreamDTO to save.
   * @param videoStreamDTO the videoStreamDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoStreamDTO,
   * or with status {@code 400 (Bad Request)} if the videoStreamDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the videoStreamDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/video-streams/{id}")
  public ResponseEntity<VideoStreamDTO> updateVideoStream(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody VideoStreamDTO videoStreamDTO
  ) throws URISyntaxException {
    log.debug("REST request to update VideoStream : {}, {}", id, videoStreamDTO);
    if (videoStreamDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, videoStreamDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!videoStreamRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    VideoStreamDTO result = videoStreamService.save(videoStreamDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, videoStreamDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /video-streams/:id} : Partial updates given fields of an existing videoStream, field will ignore if it is null
   *
   * @param id the id of the videoStreamDTO to save.
   * @param videoStreamDTO the videoStreamDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoStreamDTO,
   * or with status {@code 400 (Bad Request)} if the videoStreamDTO is not valid,
   * or with status {@code 404 (Not Found)} if the videoStreamDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the videoStreamDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/video-streams/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<VideoStreamDTO> partialUpdateVideoStream(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody VideoStreamDTO videoStreamDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update VideoStream partially : {}, {}", id, videoStreamDTO);
    if (videoStreamDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, videoStreamDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!videoStreamRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<VideoStreamDTO> result = videoStreamService.partialUpdate(videoStreamDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, videoStreamDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /video-streams} : get all the videoStreams.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of videoStreams in body.
   */
  @GetMapping("/video-streams")
  public ResponseEntity<List<VideoStreamDTO>> getAllVideoStreams(VideoStreamCriteria criteria, Pageable pageable) {
    log.debug("REST request to get VideoStreams by criteria: {}", criteria);
    Page<VideoStreamDTO> page = videoStreamQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /video-streams/count} : count all the videoStreams.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/video-streams/count")
  public ResponseEntity<Long> countVideoStreams(VideoStreamCriteria criteria) {
    log.debug("REST request to count VideoStreams by criteria: {}", criteria);
    return ResponseEntity.ok().body(videoStreamQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /video-streams/:id} : get the "id" videoStream.
   *
   * @param id the id of the videoStreamDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the videoStreamDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/video-streams/{id}")
  public ResponseEntity<VideoStreamDTO> getVideoStream(@PathVariable Long id) {
    log.debug("REST request to get VideoStream : {}", id);
    Optional<VideoStreamDTO> videoStreamDTO = videoStreamService.findOne(id);
    return ResponseUtil.wrapOrNotFound(videoStreamDTO);
  }

  /**
   * {@code DELETE  /video-streams/:id} : delete the "id" videoStream.
   *
   * @param id the id of the videoStreamDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/video-streams/{id}")
  public ResponseEntity<Void> deleteVideoStream(@PathVariable Long id) {
    log.debug("REST request to delete VideoStream : {}", id);
    videoStreamService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/video-streams?query=:query} : search for the videoStream corresponding
   * to the query.
   *
   * @param query the query of the videoStream search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/video-streams")
  public ResponseEntity<List<VideoStreamDTO>> searchVideoStreams(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of VideoStreams for query {}", query);
    Page<VideoStreamDTO> page = videoStreamService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
