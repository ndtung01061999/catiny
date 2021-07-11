package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.DeviceStatusRepository;
import com.regitiny.catiny.service.DeviceStatusQueryService;
import com.regitiny.catiny.service.DeviceStatusService;
import com.regitiny.catiny.service.criteria.DeviceStatusCriteria;
import com.regitiny.catiny.service.dto.DeviceStatusDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.DeviceStatus}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class DeviceStatusResource {

  private final Logger log = LoggerFactory.getLogger(DeviceStatusResource.class);

  private static final String ENTITY_NAME = "deviceStatus";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final DeviceStatusService deviceStatusService;

  private final DeviceStatusRepository deviceStatusRepository;

  private final DeviceStatusQueryService deviceStatusQueryService;

  public DeviceStatusResource(
    DeviceStatusService deviceStatusService,
    DeviceStatusRepository deviceStatusRepository,
    DeviceStatusQueryService deviceStatusQueryService
  ) {
    this.deviceStatusService = deviceStatusService;
    this.deviceStatusRepository = deviceStatusRepository;
    this.deviceStatusQueryService = deviceStatusQueryService;
  }

  /**
   * {@code POST  /device-statuses} : Create a new deviceStatus.
   *
   * @param deviceStatusDTO the deviceStatusDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deviceStatusDTO, or with status {@code 400 (Bad Request)} if the deviceStatus has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/device-statuses")
  public ResponseEntity<DeviceStatusDTO> createDeviceStatus(@Valid @RequestBody DeviceStatusDTO deviceStatusDTO) throws URISyntaxException {
    log.debug("REST request to save DeviceStatus : {}", deviceStatusDTO);
    if (deviceStatusDTO.getId() != null) {
      throw new BadRequestAlertException("A new deviceStatus cannot already have an ID", ENTITY_NAME, "idexists");
    }
    DeviceStatusDTO result = deviceStatusService.save(deviceStatusDTO);
    return ResponseEntity
      .created(new URI("/api/device-statuses/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /device-statuses/:id} : Updates an existing deviceStatus.
   *
   * @param id the id of the deviceStatusDTO to save.
   * @param deviceStatusDTO the deviceStatusDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deviceStatusDTO,
   * or with status {@code 400 (Bad Request)} if the deviceStatusDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the deviceStatusDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/device-statuses/{id}")
  public ResponseEntity<DeviceStatusDTO> updateDeviceStatus(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody DeviceStatusDTO deviceStatusDTO
  ) throws URISyntaxException {
    log.debug("REST request to update DeviceStatus : {}, {}", id, deviceStatusDTO);
    if (deviceStatusDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, deviceStatusDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!deviceStatusRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    DeviceStatusDTO result = deviceStatusService.save(deviceStatusDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deviceStatusDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /device-statuses/:id} : Partial updates given fields of an existing deviceStatus, field will ignore if it is null
   *
   * @param id the id of the deviceStatusDTO to save.
   * @param deviceStatusDTO the deviceStatusDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deviceStatusDTO,
   * or with status {@code 400 (Bad Request)} if the deviceStatusDTO is not valid,
   * or with status {@code 404 (Not Found)} if the deviceStatusDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the deviceStatusDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/device-statuses/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<DeviceStatusDTO> partialUpdateDeviceStatus(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody DeviceStatusDTO deviceStatusDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update DeviceStatus partially : {}, {}", id, deviceStatusDTO);
    if (deviceStatusDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, deviceStatusDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!deviceStatusRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<DeviceStatusDTO> result = deviceStatusService.partialUpdate(deviceStatusDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deviceStatusDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /device-statuses} : get all the deviceStatuses.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deviceStatuses in body.
   */
  @GetMapping("/device-statuses")
  public ResponseEntity<List<DeviceStatusDTO>> getAllDeviceStatuses(DeviceStatusCriteria criteria, Pageable pageable) {
    log.debug("REST request to get DeviceStatuses by criteria: {}", criteria);
    Page<DeviceStatusDTO> page = deviceStatusQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /device-statuses/count} : count all the deviceStatuses.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/device-statuses/count")
  public ResponseEntity<Long> countDeviceStatuses(DeviceStatusCriteria criteria) {
    log.debug("REST request to count DeviceStatuses by criteria: {}", criteria);
    return ResponseEntity.ok().body(deviceStatusQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /device-statuses/:id} : get the "id" deviceStatus.
   *
   * @param id the id of the deviceStatusDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deviceStatusDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/device-statuses/{id}")
  public ResponseEntity<DeviceStatusDTO> getDeviceStatus(@PathVariable Long id) {
    log.debug("REST request to get DeviceStatus : {}", id);
    Optional<DeviceStatusDTO> deviceStatusDTO = deviceStatusService.findOne(id);
    return ResponseUtil.wrapOrNotFound(deviceStatusDTO);
  }

  /**
   * {@code DELETE  /device-statuses/:id} : delete the "id" deviceStatus.
   *
   * @param id the id of the deviceStatusDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/device-statuses/{id}")
  public ResponseEntity<Void> deleteDeviceStatus(@PathVariable Long id) {
    log.debug("REST request to delete DeviceStatus : {}", id);
    deviceStatusService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/device-statuses?query=:query} : search for the deviceStatus corresponding
   * to the query.
   *
   * @param query the query of the deviceStatus search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/device-statuses")
  public ResponseEntity<List<DeviceStatusDTO>> searchDeviceStatuses(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of DeviceStatuses for query {}", query);
    Page<DeviceStatusDTO> page = deviceStatusService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
