package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.FriendRepository;
import com.regitiny.catiny.service.FriendQueryService;
import com.regitiny.catiny.service.FriendService;
import com.regitiny.catiny.service.criteria.FriendCriteria;
import com.regitiny.catiny.service.dto.FriendDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.Friend}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class FriendResource {

  private final Logger log = LoggerFactory.getLogger(FriendResource.class);

  private static final String ENTITY_NAME = "friend";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final FriendService friendService;

  private final FriendRepository friendRepository;

  private final FriendQueryService friendQueryService;

  public FriendResource(FriendService friendService, FriendRepository friendRepository, FriendQueryService friendQueryService) {
    this.friendService = friendService;
    this.friendRepository = friendRepository;
    this.friendQueryService = friendQueryService;
  }

  /**
   * {@code POST  /friends} : Create a new friend.
   *
   * @param friendDTO the friendDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new friendDTO, or with status {@code 400 (Bad Request)} if the friend has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/friends")
  public ResponseEntity<FriendDTO> createFriend(@Valid @RequestBody FriendDTO friendDTO) throws URISyntaxException {
    log.debug("REST request to save Friend : {}", friendDTO);
    if (friendDTO.getId() != null) {
      throw new BadRequestAlertException("A new friend cannot already have an ID", ENTITY_NAME, "idexists");
    }
    FriendDTO result = friendService.save(friendDTO);
    return ResponseEntity
      .created(new URI("/api/friends/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /friends/:id} : Updates an existing friend.
   *
   * @param id the id of the friendDTO to save.
   * @param friendDTO the friendDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated friendDTO,
   * or with status {@code 400 (Bad Request)} if the friendDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the friendDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/friends/{id}")
  public ResponseEntity<FriendDTO> updateFriend(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody FriendDTO friendDTO
  ) throws URISyntaxException {
    log.debug("REST request to update Friend : {}, {}", id, friendDTO);
    if (friendDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, friendDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!friendRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    FriendDTO result = friendService.save(friendDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, friendDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /friends/:id} : Partial updates given fields of an existing friend, field will ignore if it is null
   *
   * @param id the id of the friendDTO to save.
   * @param friendDTO the friendDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated friendDTO,
   * or with status {@code 400 (Bad Request)} if the friendDTO is not valid,
   * or with status {@code 404 (Not Found)} if the friendDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the friendDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/friends/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<FriendDTO> partialUpdateFriend(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody FriendDTO friendDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update Friend partially : {}, {}", id, friendDTO);
    if (friendDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, friendDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!friendRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<FriendDTO> result = friendService.partialUpdate(friendDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, friendDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /friends} : get all the friends.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of friends in body.
   */
  @GetMapping("/friends")
  public ResponseEntity<List<FriendDTO>> getAllFriends(FriendCriteria criteria, Pageable pageable) {
    log.debug("REST request to get Friends by criteria: {}", criteria);
    Page<FriendDTO> page = friendQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /friends/count} : count all the friends.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/friends/count")
  public ResponseEntity<Long> countFriends(FriendCriteria criteria) {
    log.debug("REST request to count Friends by criteria: {}", criteria);
    return ResponseEntity.ok().body(friendQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /friends/:id} : get the "id" friend.
   *
   * @param id the id of the friendDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the friendDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/friends/{id}")
  public ResponseEntity<FriendDTO> getFriend(@PathVariable Long id) {
    log.debug("REST request to get Friend : {}", id);
    Optional<FriendDTO> friendDTO = friendService.findOne(id);
    return ResponseUtil.wrapOrNotFound(friendDTO);
  }

  /**
   * {@code DELETE  /friends/:id} : delete the "id" friend.
   *
   * @param id the id of the friendDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/friends/{id}")
  public ResponseEntity<Void> deleteFriend(@PathVariable Long id) {
    log.debug("REST request to delete Friend : {}", id);
    friendService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/friends?query=:query} : search for the friend corresponding
   * to the query.
   *
   * @param query the query of the friend search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/friends")
  public ResponseEntity<List<FriendDTO>> searchFriends(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of Friends for query {}", query);
    Page<FriendDTO> page = friendService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
