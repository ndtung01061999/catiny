package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.PostLikeRepository;
import com.regitiny.catiny.service.PostLikeQueryService;
import com.regitiny.catiny.service.PostLikeService;
import com.regitiny.catiny.service.criteria.PostLikeCriteria;
import com.regitiny.catiny.service.dto.PostLikeDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.PostLike}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class PostLikeResource {

  private final Logger log = LoggerFactory.getLogger(PostLikeResource.class);

  private static final String ENTITY_NAME = "postLike";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final PostLikeService postLikeService;

  private final PostLikeRepository postLikeRepository;

  private final PostLikeQueryService postLikeQueryService;

  public PostLikeResource(
    PostLikeService postLikeService,
    PostLikeRepository postLikeRepository,
    PostLikeQueryService postLikeQueryService
  ) {
    this.postLikeService = postLikeService;
    this.postLikeRepository = postLikeRepository;
    this.postLikeQueryService = postLikeQueryService;
  }

  /**
   * {@code POST  /post-likes} : Create a new postLike.
   *
   * @param postLikeDTO the postLikeDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new postLikeDTO, or with status {@code 400 (Bad Request)} if the postLike has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/post-likes")
  public ResponseEntity<PostLikeDTO> createPostLike(@Valid @RequestBody PostLikeDTO postLikeDTO) throws URISyntaxException {
    log.debug("REST request to save PostLike : {}", postLikeDTO);
    if (postLikeDTO.getId() != null) {
      throw new BadRequestAlertException("A new postLike cannot already have an ID", ENTITY_NAME, "idexists");
    }
    PostLikeDTO result = postLikeService.save(postLikeDTO);
    return ResponseEntity
      .created(new URI("/api/post-likes/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /post-likes/:id} : Updates an existing postLike.
   *
   * @param id the id of the postLikeDTO to save.
   * @param postLikeDTO the postLikeDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated postLikeDTO,
   * or with status {@code 400 (Bad Request)} if the postLikeDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the postLikeDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/post-likes/{id}")
  public ResponseEntity<PostLikeDTO> updatePostLike(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody PostLikeDTO postLikeDTO
  ) throws URISyntaxException {
    log.debug("REST request to update PostLike : {}, {}", id, postLikeDTO);
    if (postLikeDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, postLikeDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!postLikeRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    PostLikeDTO result = postLikeService.save(postLikeDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, postLikeDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /post-likes/:id} : Partial updates given fields of an existing postLike, field will ignore if it is null
   *
   * @param id the id of the postLikeDTO to save.
   * @param postLikeDTO the postLikeDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated postLikeDTO,
   * or with status {@code 400 (Bad Request)} if the postLikeDTO is not valid,
   * or with status {@code 404 (Not Found)} if the postLikeDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the postLikeDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/post-likes/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<PostLikeDTO> partialUpdatePostLike(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody PostLikeDTO postLikeDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update PostLike partially : {}, {}", id, postLikeDTO);
    if (postLikeDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, postLikeDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!postLikeRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<PostLikeDTO> result = postLikeService.partialUpdate(postLikeDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, postLikeDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /post-likes} : get all the postLikes.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of postLikes in body.
   */
  @GetMapping("/post-likes")
  public ResponseEntity<List<PostLikeDTO>> getAllPostLikes(PostLikeCriteria criteria, Pageable pageable) {
    log.debug("REST request to get PostLikes by criteria: {}", criteria);
    Page<PostLikeDTO> page = postLikeQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /post-likes/count} : count all the postLikes.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/post-likes/count")
  public ResponseEntity<Long> countPostLikes(PostLikeCriteria criteria) {
    log.debug("REST request to count PostLikes by criteria: {}", criteria);
    return ResponseEntity.ok().body(postLikeQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /post-likes/:id} : get the "id" postLike.
   *
   * @param id the id of the postLikeDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the postLikeDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/post-likes/{id}")
  public ResponseEntity<PostLikeDTO> getPostLike(@PathVariable Long id) {
    log.debug("REST request to get PostLike : {}", id);
    Optional<PostLikeDTO> postLikeDTO = postLikeService.findOne(id);
    return ResponseUtil.wrapOrNotFound(postLikeDTO);
  }

  /**
   * {@code DELETE  /post-likes/:id} : delete the "id" postLike.
   *
   * @param id the id of the postLikeDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/post-likes/{id}")
  public ResponseEntity<Void> deletePostLike(@PathVariable Long id) {
    log.debug("REST request to delete PostLike : {}", id);
    postLikeService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/post-likes?query=:query} : search for the postLike corresponding
   * to the query.
   *
   * @param query the query of the postLike search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/post-likes")
  public ResponseEntity<List<PostLikeDTO>> searchPostLikes(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of PostLikes for query {}", query);
    Page<PostLikeDTO> page = postLikeService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
