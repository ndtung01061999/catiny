package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.AccountStatusRepository;
import com.regitiny.catiny.service.AccountStatusQueryService;
import com.regitiny.catiny.service.AccountStatusService;
import com.regitiny.catiny.service.criteria.AccountStatusCriteria;
import com.regitiny.catiny.service.dto.AccountStatusDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.AccountStatus}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class AccountStatusResource {

  private final Logger log = LoggerFactory.getLogger(AccountStatusResource.class);

  private static final String ENTITY_NAME = "accountStatus";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final AccountStatusService accountStatusService;

  private final AccountStatusRepository accountStatusRepository;

  private final AccountStatusQueryService accountStatusQueryService;

  public AccountStatusResource(
    AccountStatusService accountStatusService,
    AccountStatusRepository accountStatusRepository,
    AccountStatusQueryService accountStatusQueryService
  ) {
    this.accountStatusService = accountStatusService;
    this.accountStatusRepository = accountStatusRepository;
    this.accountStatusQueryService = accountStatusQueryService;
  }

  /**
   * {@code POST  /account-statuses} : Create a new accountStatus.
   *
   * @param accountStatusDTO the accountStatusDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountStatusDTO, or with status {@code 400 (Bad Request)} if the accountStatus has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/account-statuses")
  public ResponseEntity<AccountStatusDTO> createAccountStatus(@Valid @RequestBody AccountStatusDTO accountStatusDTO)
    throws URISyntaxException {
    log.debug("REST request to save AccountStatus : {}", accountStatusDTO);
    if (accountStatusDTO.getId() != null) {
      throw new BadRequestAlertException("A new accountStatus cannot already have an ID", ENTITY_NAME, "idexists");
    }
    AccountStatusDTO result = accountStatusService.save(accountStatusDTO);
    return ResponseEntity
      .created(new URI("/api/account-statuses/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /account-statuses/:id} : Updates an existing accountStatus.
   *
   * @param id the id of the accountStatusDTO to save.
   * @param accountStatusDTO the accountStatusDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountStatusDTO,
   * or with status {@code 400 (Bad Request)} if the accountStatusDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the accountStatusDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/account-statuses/{id}")
  public ResponseEntity<AccountStatusDTO> updateAccountStatus(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody AccountStatusDTO accountStatusDTO
  ) throws URISyntaxException {
    log.debug("REST request to update AccountStatus : {}, {}", id, accountStatusDTO);
    if (accountStatusDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, accountStatusDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!accountStatusRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    AccountStatusDTO result = accountStatusService.save(accountStatusDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountStatusDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /account-statuses/:id} : Partial updates given fields of an existing accountStatus, field will ignore if it is null
   *
   * @param id the id of the accountStatusDTO to save.
   * @param accountStatusDTO the accountStatusDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountStatusDTO,
   * or with status {@code 400 (Bad Request)} if the accountStatusDTO is not valid,
   * or with status {@code 404 (Not Found)} if the accountStatusDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the accountStatusDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/account-statuses/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<AccountStatusDTO> partialUpdateAccountStatus(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody AccountStatusDTO accountStatusDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update AccountStatus partially : {}, {}", id, accountStatusDTO);
    if (accountStatusDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, accountStatusDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!accountStatusRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<AccountStatusDTO> result = accountStatusService.partialUpdate(accountStatusDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountStatusDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /account-statuses} : get all the accountStatuses.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountStatuses in body.
   */
  @GetMapping("/account-statuses")
  public ResponseEntity<List<AccountStatusDTO>> getAllAccountStatuses(AccountStatusCriteria criteria, Pageable pageable) {
    log.debug("REST request to get AccountStatuses by criteria: {}", criteria);
    Page<AccountStatusDTO> page = accountStatusQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /account-statuses/count} : count all the accountStatuses.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/account-statuses/count")
  public ResponseEntity<Long> countAccountStatuses(AccountStatusCriteria criteria) {
    log.debug("REST request to count AccountStatuses by criteria: {}", criteria);
    return ResponseEntity.ok().body(accountStatusQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /account-statuses/:id} : get the "id" accountStatus.
   *
   * @param id the id of the accountStatusDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountStatusDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/account-statuses/{id}")
  public ResponseEntity<AccountStatusDTO> getAccountStatus(@PathVariable Long id) {
    log.debug("REST request to get AccountStatus : {}", id);
    Optional<AccountStatusDTO> accountStatusDTO = accountStatusService.findOne(id);
    return ResponseUtil.wrapOrNotFound(accountStatusDTO);
  }

  /**
   * {@code DELETE  /account-statuses/:id} : delete the "id" accountStatus.
   *
   * @param id the id of the accountStatusDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/account-statuses/{id}")
  public ResponseEntity<Void> deleteAccountStatus(@PathVariable Long id) {
    log.debug("REST request to delete AccountStatus : {}", id);
    accountStatusService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/account-statuses?query=:query} : search for the accountStatus corresponding
   * to the query.
   *
   * @param query the query of the accountStatus search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/account-statuses")
  public ResponseEntity<List<AccountStatusDTO>> searchAccountStatuses(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of AccountStatuses for query {}", query);
    Page<AccountStatusDTO> page = accountStatusService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
