package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.repository.MessageGroupRepository;
import com.regitiny.catiny.service.MessageGroupService;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import com.regitiny.catiny.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import javax.persistence.Lob;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.MessageGroup}.
 */
@RestController
@RequestMapping("/api")
public class MessageGroupResource {

    private final Logger log = LoggerFactory.getLogger(MessageGroupResource.class);

    private static final String ENTITY_NAME = "messageGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MessageGroupService messageGroupService;

    private final MessageGroupRepository messageGroupRepository;

    public MessageGroupResource(MessageGroupService messageGroupService, MessageGroupRepository messageGroupRepository) {
        this.messageGroupService = messageGroupService;
        this.messageGroupRepository = messageGroupRepository;
    }

    /**
     * {@code POST  /message-groups} : Create a new messageGroup.
     *
     * @param messageGroupDTO the messageGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messageGroupDTO, or with status {@code 400 (Bad Request)} if the messageGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/message-groups")
    public ResponseEntity<MessageGroupDTO> createMessageGroup(@Valid @RequestBody MessageGroupDTO messageGroupDTO)
        throws URISyntaxException {
        log.debug("REST request to save MessageGroup : {}", messageGroupDTO);
        if (messageGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new messageGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MessageGroupDTO result = messageGroupService.save(messageGroupDTO);
        return ResponseEntity
            .created(new URI("/api/message-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /message-groups/:id} : Updates an existing messageGroup.
     *
     * @param id              the id of the messageGroupDTO to save.
     * @param messageGroupDTO the messageGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messageGroupDTO,
     * or with status {@code 400 (Bad Request)} if the messageGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the messageGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/message-groups/{id}")
    public ResponseEntity<MessageGroupDTO> updateMessageGroup(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody MessageGroupDTO messageGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to update MessageGroup : {}, {}", id, messageGroupDTO);
        if (messageGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, messageGroupDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!messageGroupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MessageGroupDTO result = messageGroupService.save(messageGroupDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, messageGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /message-groups/:id} : Partial updates given fields of an existing messageGroup, field will ignore if it is null
     *
     * @param id              the id of the messageGroupDTO to save.
     * @param messageGroupDTO the messageGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messageGroupDTO,
     * or with status {@code 400 (Bad Request)} if the messageGroupDTO is not valid,
     * or with status {@code 404 (Not Found)} if the messageGroupDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the messageGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/message-groups/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<MessageGroupDTO> partialUpdateMessageGroup(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody MessageGroupDTO messageGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update MessageGroup partially : {}, {}", id, messageGroupDTO);
        if (messageGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, messageGroupDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!messageGroupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MessageGroupDTO> result = messageGroupService.partialUpdate(messageGroupDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, messageGroupDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /message-groups} : get all the messageGroups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of messageGroups in body.
     */
    @GetMapping("/message-groups")
    public ResponseEntity<List<MessageGroupDTO>> getAllMessageGroups(Pageable pageable) {
        log.debug("REST request to get a page of MessageGroups");
        Page<MessageGroupDTO> page = messageGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /message-groups/:id} : get the "id" messageGroup.
     *
     * @param id the id of the messageGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the messageGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/message-groups/{id}")
    public ResponseEntity<MessageGroupDTO> getMessageGroup(@PathVariable Long id) {
        log.debug("REST request to get MessageGroup : {}", id);
        Optional<MessageGroupDTO> messageGroupDTO = messageGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(messageGroupDTO);
    }

    /**
     * {@code DELETE  /message-groups/:id} : delete the "id" messageGroup.
     *
     * @param id the id of the messageGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/message-groups/{id}")
    public ResponseEntity<Void> deleteMessageGroup(@PathVariable Long id) {
        log.debug("REST request to delete MessageGroup : {}", id);
        messageGroupService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /_search/message-groups?query=:query} : search for the messageGroup corresponding
     * to the query.
     *
     * @param query    the query of the messageGroup search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/message-groups")
    public ResponseEntity<List<MessageGroupDTO>> searchMessageGroups(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of MessageGroups for query {}", query);
        Page<MessageGroupDTO> page = messageGroupService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    // custom

    /**
     * {@code POST  /message-groups} : Create a new messageGroup.
     *
     * @param userIds danh sách những user được thêm vào trong lúc tạo group
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messageGroupDTO, or with status {@code 400 (Bad Request)} if the messageGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/message-groups/new-group")
    public ResponseEntity<List<MessageGroupDTO>> createNewMessageGroup(
        @RequestParam("groupName") String groupName,
        @RequestParam("lastContent") String lastContent,
        @RequestParam("userIds") List<Long> userIds
    ) throws URISyntaxException {
        log.debug("REST request to save MessageGroup . groupName : {} , lastContent : {}", groupName, lastContent);
        if (userIds.isEmpty()) throw new BadRequestAlertException(
            "create group but not add any user to the group , so what you want?",
            ENTITY_NAME,
            "idexists"
        );
        var result = messageGroupService.createMessageGroup(groupName, lastContent, userIds);
        if (Objects.isNull(result) || result.isEmpty()) throw new BadRequestAlertException(
            "not create new messageGroup ",
            ENTITY_NAME,
            "err"
        );
        return ResponseEntity.ok(result);
    }

    @PostMapping("/message-groups/{groupId}")
    public ResponseEntity<List<MessageGroupDTO>> addUserToGroup(@RequestParam List<Long> userIds, @PathVariable String groupId)
        throws URISyntaxException {
        log.debug("REST request to save userId : {} , groupId : {}", userIds, groupId);
        var result = messageGroupService.addUserToGroup(userIds, groupId);
        if (result.size() == 0) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("si đa quá bạn ơi không có thằng user nào đc add vào");
        return ResponseEntity.ok(result);
    }
}
