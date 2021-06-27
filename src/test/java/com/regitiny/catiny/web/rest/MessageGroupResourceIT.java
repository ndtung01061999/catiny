package com.regitiny.catiny.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.IntegrationTest;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.repository.MessageGroupRepository;
import com.regitiny.catiny.repository.search.MessageGroupSearchRepository;
import com.regitiny.catiny.service.criteria.MessageGroupCriteria;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import com.regitiny.catiny.service.mapper.MessageGroupMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link MessageGroupResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class MessageGroupResourceIT {

    private static final UUID DEFAULT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_UUID = UUID.randomUUID();

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;
    private static final Long SMALLER_USER_ID = 1L - 1L;

    private static final String DEFAULT_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADD_BY = "AAAAAAAAAA";
    private static final String UPDATED_ADD_BY = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_LAST_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_SEARCH_FIELD = "AAAAAAAAAA";
    private static final String UPDATED_SEARCH_FIELD = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/message-groups";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/message-groups";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MessageGroupRepository messageGroupRepository;

    @Autowired
    private MessageGroupMapper messageGroupMapper;

    /**
     * This repository is mocked in the com.regitiny.catiny.repository.search test package.
     *
     * @see com.regitiny.catiny.repository.search.MessageGroupSearchRepositoryMockConfiguration
     */
    @Autowired
    private MessageGroupSearchRepository mockMessageGroupSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMessageGroupMockMvc;

    private MessageGroup messageGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MessageGroup createEntity(EntityManager em) {
        MessageGroup messageGroup = new MessageGroup()
            .uuid(DEFAULT_UUID)
            .userId(DEFAULT_USER_ID)
            .groupId(DEFAULT_GROUP_ID)
            .groupName(DEFAULT_GROUP_NAME)
            .addBy(DEFAULT_ADD_BY)
            .lastContent(DEFAULT_LAST_CONTENT)
            .searchField(DEFAULT_SEARCH_FIELD)
            .role(DEFAULT_ROLE)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .comment(DEFAULT_COMMENT);
        return messageGroup;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MessageGroup createUpdatedEntity(EntityManager em) {
        MessageGroup messageGroup = new MessageGroup()
            .uuid(UPDATED_UUID)
            .userId(UPDATED_USER_ID)
            .groupId(UPDATED_GROUP_ID)
            .groupName(UPDATED_GROUP_NAME)
            .addBy(UPDATED_ADD_BY)
            .lastContent(UPDATED_LAST_CONTENT)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .comment(UPDATED_COMMENT);
        return messageGroup;
    }

    @BeforeEach
    public void initTest() {
        messageGroup = createEntity(em);
    }

    @Test
    @Transactional
    void createMessageGroup() throws Exception {
        int databaseSizeBeforeCreate = messageGroupRepository.findAll().size();
        // Create the MessageGroup
        MessageGroupDTO messageGroupDTO = messageGroupMapper.toDto(messageGroup);
        restMessageGroupMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageGroupDTO))
            )
            .andExpect(status().isCreated());

        // Validate the MessageGroup in the database
        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MessageGroup testMessageGroup = messageGroupList.get(messageGroupList.size() - 1);
        assertThat(testMessageGroup.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testMessageGroup.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testMessageGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMessageGroup.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
        assertThat(testMessageGroup.getAddBy()).isEqualTo(DEFAULT_ADD_BY);
        assertThat(testMessageGroup.getLastContent()).isEqualTo(DEFAULT_LAST_CONTENT);
        assertThat(testMessageGroup.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);
        assertThat(testMessageGroup.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testMessageGroup.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMessageGroup.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testMessageGroup.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testMessageGroup.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testMessageGroup.getComment()).isEqualTo(DEFAULT_COMMENT);

        // Validate the MessageGroup in Elasticsearch
        verify(mockMessageGroupSearchRepository, times(1)).save(testMessageGroup);
    }

    @Test
    @Transactional
    void createMessageGroupWithExistingId() throws Exception {
        // Create the MessageGroup with an existing ID
        messageGroup.setId(1L);
        MessageGroupDTO messageGroupDTO = messageGroupMapper.toDto(messageGroup);

        int databaseSizeBeforeCreate = messageGroupRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMessageGroupMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessageGroup in the database
        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeCreate);

        // Validate the MessageGroup in Elasticsearch
        verify(mockMessageGroupSearchRepository, times(0)).save(messageGroup);
    }

    @Test
    @Transactional
    void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = messageGroupRepository.findAll().size();
        // set the field null
        messageGroup.setUuid(null);

        // Create the MessageGroup, which fails.
        MessageGroupDTO messageGroupDTO = messageGroupMapper.toDto(messageGroup);

        restMessageGroupMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageGroupDTO))
            )
            .andExpect(status().isBadRequest());

        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = messageGroupRepository.findAll().size();
        // set the field null
        messageGroup.setUserId(null);

        // Create the MessageGroup, which fails.
        MessageGroupDTO messageGroupDTO = messageGroupMapper.toDto(messageGroup);

        restMessageGroupMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageGroupDTO))
            )
            .andExpect(status().isBadRequest());

        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = messageGroupRepository.findAll().size();
        // set the field null
        messageGroup.setGroupId(null);

        // Create the MessageGroup, which fails.
        MessageGroupDTO messageGroupDTO = messageGroupMapper.toDto(messageGroup);

        restMessageGroupMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageGroupDTO))
            )
            .andExpect(status().isBadRequest());

        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllMessageGroups() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList
        restMessageGroupMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(messageGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].addBy").value(hasItem(DEFAULT_ADD_BY)))
            .andExpect(jsonPath("$.[*].lastContent").value(hasItem(DEFAULT_LAST_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)));
    }

    @Test
    @Transactional
    void getMessageGroup() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get the messageGroup
        restMessageGroupMockMvc
            .perform(get(ENTITY_API_URL_ID, messageGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(messageGroup.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.groupName").value(DEFAULT_GROUP_NAME))
            .andExpect(jsonPath("$.addBy").value(DEFAULT_ADD_BY))
            .andExpect(jsonPath("$.lastContent").value(DEFAULT_LAST_CONTENT.toString()))
            .andExpect(jsonPath("$.searchField").value(DEFAULT_SEARCH_FIELD.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT));
    }

    @Test
    @Transactional
    void getMessageGroupsByIdFiltering() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        Long id = messageGroup.getId();

        defaultMessageGroupShouldBeFound("id.equals=" + id);
        defaultMessageGroupShouldNotBeFound("id.notEquals=" + id);

        defaultMessageGroupShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultMessageGroupShouldNotBeFound("id.greaterThan=" + id);

        defaultMessageGroupShouldBeFound("id.lessThanOrEqual=" + id);
        defaultMessageGroupShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByUuidIsEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where uuid equals to DEFAULT_UUID
        defaultMessageGroupShouldBeFound("uuid.equals=" + DEFAULT_UUID);

        // Get all the messageGroupList where uuid equals to UPDATED_UUID
        defaultMessageGroupShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByUuidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where uuid not equals to DEFAULT_UUID
        defaultMessageGroupShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

        // Get all the messageGroupList where uuid not equals to UPDATED_UUID
        defaultMessageGroupShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByUuidIsInShouldWork() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where uuid in DEFAULT_UUID or UPDATED_UUID
        defaultMessageGroupShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

        // Get all the messageGroupList where uuid equals to UPDATED_UUID
        defaultMessageGroupShouldNotBeFound("uuid.in=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByUuidIsNullOrNotNull() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where uuid is not null
        defaultMessageGroupShouldBeFound("uuid.specified=true");

        // Get all the messageGroupList where uuid is null
        defaultMessageGroupShouldNotBeFound("uuid.specified=false");
    }

    @Test
    @Transactional
    void getAllMessageGroupsByUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where userId equals to DEFAULT_USER_ID
        defaultMessageGroupShouldBeFound("userId.equals=" + DEFAULT_USER_ID);

        // Get all the messageGroupList where userId equals to UPDATED_USER_ID
        defaultMessageGroupShouldNotBeFound("userId.equals=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByUserIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where userId not equals to DEFAULT_USER_ID
        defaultMessageGroupShouldNotBeFound("userId.notEquals=" + DEFAULT_USER_ID);

        // Get all the messageGroupList where userId not equals to UPDATED_USER_ID
        defaultMessageGroupShouldBeFound("userId.notEquals=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where userId in DEFAULT_USER_ID or UPDATED_USER_ID
        defaultMessageGroupShouldBeFound("userId.in=" + DEFAULT_USER_ID + "," + UPDATED_USER_ID);

        // Get all the messageGroupList where userId equals to UPDATED_USER_ID
        defaultMessageGroupShouldNotBeFound("userId.in=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where userId is not null
        defaultMessageGroupShouldBeFound("userId.specified=true");

        // Get all the messageGroupList where userId is null
        defaultMessageGroupShouldNotBeFound("userId.specified=false");
    }

    @Test
    @Transactional
    void getAllMessageGroupsByUserIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where userId is greater than or equal to DEFAULT_USER_ID
        defaultMessageGroupShouldBeFound("userId.greaterThanOrEqual=" + DEFAULT_USER_ID);

        // Get all the messageGroupList where userId is greater than or equal to UPDATED_USER_ID
        defaultMessageGroupShouldNotBeFound("userId.greaterThanOrEqual=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByUserIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where userId is less than or equal to DEFAULT_USER_ID
        defaultMessageGroupShouldBeFound("userId.lessThanOrEqual=" + DEFAULT_USER_ID);

        // Get all the messageGroupList where userId is less than or equal to SMALLER_USER_ID
        defaultMessageGroupShouldNotBeFound("userId.lessThanOrEqual=" + SMALLER_USER_ID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByUserIdIsLessThanSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where userId is less than DEFAULT_USER_ID
        defaultMessageGroupShouldNotBeFound("userId.lessThan=" + DEFAULT_USER_ID);

        // Get all the messageGroupList where userId is less than UPDATED_USER_ID
        defaultMessageGroupShouldBeFound("userId.lessThan=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByUserIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where userId is greater than DEFAULT_USER_ID
        defaultMessageGroupShouldNotBeFound("userId.greaterThan=" + DEFAULT_USER_ID);

        // Get all the messageGroupList where userId is greater than SMALLER_USER_ID
        defaultMessageGroupShouldBeFound("userId.greaterThan=" + SMALLER_USER_ID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMessageGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the messageGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMessageGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByGroupIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where groupId not equals to DEFAULT_GROUP_ID
        defaultMessageGroupShouldNotBeFound("groupId.notEquals=" + DEFAULT_GROUP_ID);

        // Get all the messageGroupList where groupId not equals to UPDATED_GROUP_ID
        defaultMessageGroupShouldBeFound("groupId.notEquals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMessageGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the messageGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMessageGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where groupId is not null
        defaultMessageGroupShouldBeFound("groupId.specified=true");

        // Get all the messageGroupList where groupId is null
        defaultMessageGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    void getAllMessageGroupsByGroupIdContainsSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where groupId contains DEFAULT_GROUP_ID
        defaultMessageGroupShouldBeFound("groupId.contains=" + DEFAULT_GROUP_ID);

        // Get all the messageGroupList where groupId contains UPDATED_GROUP_ID
        defaultMessageGroupShouldNotBeFound("groupId.contains=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByGroupIdNotContainsSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where groupId does not contain DEFAULT_GROUP_ID
        defaultMessageGroupShouldNotBeFound("groupId.doesNotContain=" + DEFAULT_GROUP_ID);

        // Get all the messageGroupList where groupId does not contain UPDATED_GROUP_ID
        defaultMessageGroupShouldBeFound("groupId.doesNotContain=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByGroupNameIsEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where groupName equals to DEFAULT_GROUP_NAME
        defaultMessageGroupShouldBeFound("groupName.equals=" + DEFAULT_GROUP_NAME);

        // Get all the messageGroupList where groupName equals to UPDATED_GROUP_NAME
        defaultMessageGroupShouldNotBeFound("groupName.equals=" + UPDATED_GROUP_NAME);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByGroupNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where groupName not equals to DEFAULT_GROUP_NAME
        defaultMessageGroupShouldNotBeFound("groupName.notEquals=" + DEFAULT_GROUP_NAME);

        // Get all the messageGroupList where groupName not equals to UPDATED_GROUP_NAME
        defaultMessageGroupShouldBeFound("groupName.notEquals=" + UPDATED_GROUP_NAME);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByGroupNameIsInShouldWork() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where groupName in DEFAULT_GROUP_NAME or UPDATED_GROUP_NAME
        defaultMessageGroupShouldBeFound("groupName.in=" + DEFAULT_GROUP_NAME + "," + UPDATED_GROUP_NAME);

        // Get all the messageGroupList where groupName equals to UPDATED_GROUP_NAME
        defaultMessageGroupShouldNotBeFound("groupName.in=" + UPDATED_GROUP_NAME);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByGroupNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where groupName is not null
        defaultMessageGroupShouldBeFound("groupName.specified=true");

        // Get all the messageGroupList where groupName is null
        defaultMessageGroupShouldNotBeFound("groupName.specified=false");
    }

    @Test
    @Transactional
    void getAllMessageGroupsByGroupNameContainsSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where groupName contains DEFAULT_GROUP_NAME
        defaultMessageGroupShouldBeFound("groupName.contains=" + DEFAULT_GROUP_NAME);

        // Get all the messageGroupList where groupName contains UPDATED_GROUP_NAME
        defaultMessageGroupShouldNotBeFound("groupName.contains=" + UPDATED_GROUP_NAME);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByGroupNameNotContainsSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where groupName does not contain DEFAULT_GROUP_NAME
        defaultMessageGroupShouldNotBeFound("groupName.doesNotContain=" + DEFAULT_GROUP_NAME);

        // Get all the messageGroupList where groupName does not contain UPDATED_GROUP_NAME
        defaultMessageGroupShouldBeFound("groupName.doesNotContain=" + UPDATED_GROUP_NAME);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByAddByIsEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where addBy equals to DEFAULT_ADD_BY
        defaultMessageGroupShouldBeFound("addBy.equals=" + DEFAULT_ADD_BY);

        // Get all the messageGroupList where addBy equals to UPDATED_ADD_BY
        defaultMessageGroupShouldNotBeFound("addBy.equals=" + UPDATED_ADD_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByAddByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where addBy not equals to DEFAULT_ADD_BY
        defaultMessageGroupShouldNotBeFound("addBy.notEquals=" + DEFAULT_ADD_BY);

        // Get all the messageGroupList where addBy not equals to UPDATED_ADD_BY
        defaultMessageGroupShouldBeFound("addBy.notEquals=" + UPDATED_ADD_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByAddByIsInShouldWork() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where addBy in DEFAULT_ADD_BY or UPDATED_ADD_BY
        defaultMessageGroupShouldBeFound("addBy.in=" + DEFAULT_ADD_BY + "," + UPDATED_ADD_BY);

        // Get all the messageGroupList where addBy equals to UPDATED_ADD_BY
        defaultMessageGroupShouldNotBeFound("addBy.in=" + UPDATED_ADD_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByAddByIsNullOrNotNull() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where addBy is not null
        defaultMessageGroupShouldBeFound("addBy.specified=true");

        // Get all the messageGroupList where addBy is null
        defaultMessageGroupShouldNotBeFound("addBy.specified=false");
    }

    @Test
    @Transactional
    void getAllMessageGroupsByAddByContainsSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where addBy contains DEFAULT_ADD_BY
        defaultMessageGroupShouldBeFound("addBy.contains=" + DEFAULT_ADD_BY);

        // Get all the messageGroupList where addBy contains UPDATED_ADD_BY
        defaultMessageGroupShouldNotBeFound("addBy.contains=" + UPDATED_ADD_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByAddByNotContainsSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where addBy does not contain DEFAULT_ADD_BY
        defaultMessageGroupShouldNotBeFound("addBy.doesNotContain=" + DEFAULT_ADD_BY);

        // Get all the messageGroupList where addBy does not contain UPDATED_ADD_BY
        defaultMessageGroupShouldBeFound("addBy.doesNotContain=" + UPDATED_ADD_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByRoleIsEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where role equals to DEFAULT_ROLE
        defaultMessageGroupShouldBeFound("role.equals=" + DEFAULT_ROLE);

        // Get all the messageGroupList where role equals to UPDATED_ROLE
        defaultMessageGroupShouldNotBeFound("role.equals=" + UPDATED_ROLE);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByRoleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where role not equals to DEFAULT_ROLE
        defaultMessageGroupShouldNotBeFound("role.notEquals=" + DEFAULT_ROLE);

        // Get all the messageGroupList where role not equals to UPDATED_ROLE
        defaultMessageGroupShouldBeFound("role.notEquals=" + UPDATED_ROLE);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByRoleIsInShouldWork() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where role in DEFAULT_ROLE or UPDATED_ROLE
        defaultMessageGroupShouldBeFound("role.in=" + DEFAULT_ROLE + "," + UPDATED_ROLE);

        // Get all the messageGroupList where role equals to UPDATED_ROLE
        defaultMessageGroupShouldNotBeFound("role.in=" + UPDATED_ROLE);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByRoleIsNullOrNotNull() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where role is not null
        defaultMessageGroupShouldBeFound("role.specified=true");

        // Get all the messageGroupList where role is null
        defaultMessageGroupShouldNotBeFound("role.specified=false");
    }

    @Test
    @Transactional
    void getAllMessageGroupsByRoleContainsSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where role contains DEFAULT_ROLE
        defaultMessageGroupShouldBeFound("role.contains=" + DEFAULT_ROLE);

        // Get all the messageGroupList where role contains UPDATED_ROLE
        defaultMessageGroupShouldNotBeFound("role.contains=" + UPDATED_ROLE);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByRoleNotContainsSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where role does not contain DEFAULT_ROLE
        defaultMessageGroupShouldNotBeFound("role.doesNotContain=" + DEFAULT_ROLE);

        // Get all the messageGroupList where role does not contain UPDATED_ROLE
        defaultMessageGroupShouldBeFound("role.doesNotContain=" + UPDATED_ROLE);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCreatedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where createdDate equals to DEFAULT_CREATED_DATE
        defaultMessageGroupShouldBeFound("createdDate.equals=" + DEFAULT_CREATED_DATE);

        // Get all the messageGroupList where createdDate equals to UPDATED_CREATED_DATE
        defaultMessageGroupShouldNotBeFound("createdDate.equals=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCreatedDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where createdDate not equals to DEFAULT_CREATED_DATE
        defaultMessageGroupShouldNotBeFound("createdDate.notEquals=" + DEFAULT_CREATED_DATE);

        // Get all the messageGroupList where createdDate not equals to UPDATED_CREATED_DATE
        defaultMessageGroupShouldBeFound("createdDate.notEquals=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCreatedDateIsInShouldWork() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
        defaultMessageGroupShouldBeFound("createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE);

        // Get all the messageGroupList where createdDate equals to UPDATED_CREATED_DATE
        defaultMessageGroupShouldNotBeFound("createdDate.in=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCreatedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where createdDate is not null
        defaultMessageGroupShouldBeFound("createdDate.specified=true");

        // Get all the messageGroupList where createdDate is null
        defaultMessageGroupShouldNotBeFound("createdDate.specified=false");
    }

    @Test
    @Transactional
    void getAllMessageGroupsByModifiedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where modifiedDate equals to DEFAULT_MODIFIED_DATE
        defaultMessageGroupShouldBeFound("modifiedDate.equals=" + DEFAULT_MODIFIED_DATE);

        // Get all the messageGroupList where modifiedDate equals to UPDATED_MODIFIED_DATE
        defaultMessageGroupShouldNotBeFound("modifiedDate.equals=" + UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByModifiedDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where modifiedDate not equals to DEFAULT_MODIFIED_DATE
        defaultMessageGroupShouldNotBeFound("modifiedDate.notEquals=" + DEFAULT_MODIFIED_DATE);

        // Get all the messageGroupList where modifiedDate not equals to UPDATED_MODIFIED_DATE
        defaultMessageGroupShouldBeFound("modifiedDate.notEquals=" + UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByModifiedDateIsInShouldWork() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where modifiedDate in DEFAULT_MODIFIED_DATE or UPDATED_MODIFIED_DATE
        defaultMessageGroupShouldBeFound("modifiedDate.in=" + DEFAULT_MODIFIED_DATE + "," + UPDATED_MODIFIED_DATE);

        // Get all the messageGroupList where modifiedDate equals to UPDATED_MODIFIED_DATE
        defaultMessageGroupShouldNotBeFound("modifiedDate.in=" + UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByModifiedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where modifiedDate is not null
        defaultMessageGroupShouldBeFound("modifiedDate.specified=true");

        // Get all the messageGroupList where modifiedDate is null
        defaultMessageGroupShouldNotBeFound("modifiedDate.specified=false");
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where createdBy equals to DEFAULT_CREATED_BY
        defaultMessageGroupShouldBeFound("createdBy.equals=" + DEFAULT_CREATED_BY);

        // Get all the messageGroupList where createdBy equals to UPDATED_CREATED_BY
        defaultMessageGroupShouldNotBeFound("createdBy.equals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCreatedByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where createdBy not equals to DEFAULT_CREATED_BY
        defaultMessageGroupShouldNotBeFound("createdBy.notEquals=" + DEFAULT_CREATED_BY);

        // Get all the messageGroupList where createdBy not equals to UPDATED_CREATED_BY
        defaultMessageGroupShouldBeFound("createdBy.notEquals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCreatedByIsInShouldWork() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
        defaultMessageGroupShouldBeFound("createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY);

        // Get all the messageGroupList where createdBy equals to UPDATED_CREATED_BY
        defaultMessageGroupShouldNotBeFound("createdBy.in=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCreatedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where createdBy is not null
        defaultMessageGroupShouldBeFound("createdBy.specified=true");

        // Get all the messageGroupList where createdBy is null
        defaultMessageGroupShouldNotBeFound("createdBy.specified=false");
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCreatedByContainsSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where createdBy contains DEFAULT_CREATED_BY
        defaultMessageGroupShouldBeFound("createdBy.contains=" + DEFAULT_CREATED_BY);

        // Get all the messageGroupList where createdBy contains UPDATED_CREATED_BY
        defaultMessageGroupShouldNotBeFound("createdBy.contains=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCreatedByNotContainsSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where createdBy does not contain DEFAULT_CREATED_BY
        defaultMessageGroupShouldNotBeFound("createdBy.doesNotContain=" + DEFAULT_CREATED_BY);

        // Get all the messageGroupList where createdBy does not contain UPDATED_CREATED_BY
        defaultMessageGroupShouldBeFound("createdBy.doesNotContain=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where modifiedBy equals to DEFAULT_MODIFIED_BY
        defaultMessageGroupShouldBeFound("modifiedBy.equals=" + DEFAULT_MODIFIED_BY);

        // Get all the messageGroupList where modifiedBy equals to UPDATED_MODIFIED_BY
        defaultMessageGroupShouldNotBeFound("modifiedBy.equals=" + UPDATED_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByModifiedByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where modifiedBy not equals to DEFAULT_MODIFIED_BY
        defaultMessageGroupShouldNotBeFound("modifiedBy.notEquals=" + DEFAULT_MODIFIED_BY);

        // Get all the messageGroupList where modifiedBy not equals to UPDATED_MODIFIED_BY
        defaultMessageGroupShouldBeFound("modifiedBy.notEquals=" + UPDATED_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByModifiedByIsInShouldWork() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where modifiedBy in DEFAULT_MODIFIED_BY or UPDATED_MODIFIED_BY
        defaultMessageGroupShouldBeFound("modifiedBy.in=" + DEFAULT_MODIFIED_BY + "," + UPDATED_MODIFIED_BY);

        // Get all the messageGroupList where modifiedBy equals to UPDATED_MODIFIED_BY
        defaultMessageGroupShouldNotBeFound("modifiedBy.in=" + UPDATED_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByModifiedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where modifiedBy is not null
        defaultMessageGroupShouldBeFound("modifiedBy.specified=true");

        // Get all the messageGroupList where modifiedBy is null
        defaultMessageGroupShouldNotBeFound("modifiedBy.specified=false");
    }

    @Test
    @Transactional
    void getAllMessageGroupsByModifiedByContainsSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where modifiedBy contains DEFAULT_MODIFIED_BY
        defaultMessageGroupShouldBeFound("modifiedBy.contains=" + DEFAULT_MODIFIED_BY);

        // Get all the messageGroupList where modifiedBy contains UPDATED_MODIFIED_BY
        defaultMessageGroupShouldNotBeFound("modifiedBy.contains=" + UPDATED_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByModifiedByNotContainsSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where modifiedBy does not contain DEFAULT_MODIFIED_BY
        defaultMessageGroupShouldNotBeFound("modifiedBy.doesNotContain=" + DEFAULT_MODIFIED_BY);

        // Get all the messageGroupList where modifiedBy does not contain UPDATED_MODIFIED_BY
        defaultMessageGroupShouldBeFound("modifiedBy.doesNotContain=" + UPDATED_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where comment equals to DEFAULT_COMMENT
        defaultMessageGroupShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the messageGroupList where comment equals to UPDATED_COMMENT
        defaultMessageGroupShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCommentIsNotEqualToSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where comment not equals to DEFAULT_COMMENT
        defaultMessageGroupShouldNotBeFound("comment.notEquals=" + DEFAULT_COMMENT);

        // Get all the messageGroupList where comment not equals to UPDATED_COMMENT
        defaultMessageGroupShouldBeFound("comment.notEquals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultMessageGroupShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the messageGroupList where comment equals to UPDATED_COMMENT
        defaultMessageGroupShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where comment is not null
        defaultMessageGroupShouldBeFound("comment.specified=true");

        // Get all the messageGroupList where comment is null
        defaultMessageGroupShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCommentContainsSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where comment contains DEFAULT_COMMENT
        defaultMessageGroupShouldBeFound("comment.contains=" + DEFAULT_COMMENT);

        // Get all the messageGroupList where comment contains UPDATED_COMMENT
        defaultMessageGroupShouldNotBeFound("comment.contains=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void getAllMessageGroupsByCommentNotContainsSomething() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        // Get all the messageGroupList where comment does not contain DEFAULT_COMMENT
        defaultMessageGroupShouldNotBeFound("comment.doesNotContain=" + DEFAULT_COMMENT);

        // Get all the messageGroupList where comment does not contain UPDATED_COMMENT
        defaultMessageGroupShouldBeFound("comment.doesNotContain=" + UPDATED_COMMENT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMessageGroupShouldBeFound(String filter) throws Exception {
        restMessageGroupMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(messageGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].addBy").value(hasItem(DEFAULT_ADD_BY)))
            .andExpect(jsonPath("$.[*].lastContent").value(hasItem(DEFAULT_LAST_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)));

        // Check, that the count call also returns 1
        restMessageGroupMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMessageGroupShouldNotBeFound(String filter) throws Exception {
        restMessageGroupMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMessageGroupMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingMessageGroup() throws Exception {
        // Get the messageGroup
        restMessageGroupMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewMessageGroup() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        int databaseSizeBeforeUpdate = messageGroupRepository.findAll().size();

        // Update the messageGroup
        MessageGroup updatedMessageGroup = messageGroupRepository.findById(messageGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMessageGroup are not directly saved in db
        em.detach(updatedMessageGroup);
        updatedMessageGroup
            .uuid(UPDATED_UUID)
            .userId(UPDATED_USER_ID)
            .groupId(UPDATED_GROUP_ID)
            .groupName(UPDATED_GROUP_NAME)
            .addBy(UPDATED_ADD_BY)
            .lastContent(UPDATED_LAST_CONTENT)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .comment(UPDATED_COMMENT);
        MessageGroupDTO messageGroupDTO = messageGroupMapper.toDto(updatedMessageGroup);

        restMessageGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, messageGroupDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(messageGroupDTO))
            )
            .andExpect(status().isOk());

        // Validate the MessageGroup in the database
        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeUpdate);
        MessageGroup testMessageGroup = messageGroupList.get(messageGroupList.size() - 1);
        assertThat(testMessageGroup.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testMessageGroup.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testMessageGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMessageGroup.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
        assertThat(testMessageGroup.getAddBy()).isEqualTo(UPDATED_ADD_BY);
        assertThat(testMessageGroup.getLastContent()).isEqualTo(UPDATED_LAST_CONTENT);
        assertThat(testMessageGroup.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        assertThat(testMessageGroup.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testMessageGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMessageGroup.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testMessageGroup.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testMessageGroup.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testMessageGroup.getComment()).isEqualTo(UPDATED_COMMENT);

        // Validate the MessageGroup in Elasticsearch
        verify(mockMessageGroupSearchRepository).save(testMessageGroup);
    }

    @Test
    @Transactional
    void putNonExistingMessageGroup() throws Exception {
        int databaseSizeBeforeUpdate = messageGroupRepository.findAll().size();
        messageGroup.setId(count.incrementAndGet());

        // Create the MessageGroup
        MessageGroupDTO messageGroupDTO = messageGroupMapper.toDto(messageGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMessageGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, messageGroupDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(messageGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessageGroup in the database
        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MessageGroup in Elasticsearch
        verify(mockMessageGroupSearchRepository, times(0)).save(messageGroup);
    }

    @Test
    @Transactional
    void putWithIdMismatchMessageGroup() throws Exception {
        int databaseSizeBeforeUpdate = messageGroupRepository.findAll().size();
        messageGroup.setId(count.incrementAndGet());

        // Create the MessageGroup
        MessageGroupDTO messageGroupDTO = messageGroupMapper.toDto(messageGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessageGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(messageGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessageGroup in the database
        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MessageGroup in Elasticsearch
        verify(mockMessageGroupSearchRepository, times(0)).save(messageGroup);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMessageGroup() throws Exception {
        int databaseSizeBeforeUpdate = messageGroupRepository.findAll().size();
        messageGroup.setId(count.incrementAndGet());

        // Create the MessageGroup
        MessageGroupDTO messageGroupDTO = messageGroupMapper.toDto(messageGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessageGroupMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageGroupDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MessageGroup in the database
        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MessageGroup in Elasticsearch
        verify(mockMessageGroupSearchRepository, times(0)).save(messageGroup);
    }

    @Test
    @Transactional
    void partialUpdateMessageGroupWithPatch() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        int databaseSizeBeforeUpdate = messageGroupRepository.findAll().size();

        // Update the messageGroup using partial update
        MessageGroup partialUpdatedMessageGroup = new MessageGroup();
        partialUpdatedMessageGroup.setId(messageGroup.getId());

        partialUpdatedMessageGroup
            .uuid(UPDATED_UUID)
            .userId(UPDATED_USER_ID)
            .addBy(UPDATED_ADD_BY)
            .searchField(UPDATED_SEARCH_FIELD)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restMessageGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMessageGroup.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMessageGroup))
            )
            .andExpect(status().isOk());

        // Validate the MessageGroup in the database
        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeUpdate);
        MessageGroup testMessageGroup = messageGroupList.get(messageGroupList.size() - 1);
        assertThat(testMessageGroup.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testMessageGroup.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testMessageGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMessageGroup.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
        assertThat(testMessageGroup.getAddBy()).isEqualTo(UPDATED_ADD_BY);
        assertThat(testMessageGroup.getLastContent()).isEqualTo(DEFAULT_LAST_CONTENT);
        assertThat(testMessageGroup.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        assertThat(testMessageGroup.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testMessageGroup.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMessageGroup.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testMessageGroup.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testMessageGroup.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testMessageGroup.getComment()).isEqualTo(DEFAULT_COMMENT);
    }

    @Test
    @Transactional
    void fullUpdateMessageGroupWithPatch() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        int databaseSizeBeforeUpdate = messageGroupRepository.findAll().size();

        // Update the messageGroup using partial update
        MessageGroup partialUpdatedMessageGroup = new MessageGroup();
        partialUpdatedMessageGroup.setId(messageGroup.getId());

        partialUpdatedMessageGroup
            .uuid(UPDATED_UUID)
            .userId(UPDATED_USER_ID)
            .groupId(UPDATED_GROUP_ID)
            .groupName(UPDATED_GROUP_NAME)
            .addBy(UPDATED_ADD_BY)
            .lastContent(UPDATED_LAST_CONTENT)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .comment(UPDATED_COMMENT);

        restMessageGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMessageGroup.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMessageGroup))
            )
            .andExpect(status().isOk());

        // Validate the MessageGroup in the database
        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeUpdate);
        MessageGroup testMessageGroup = messageGroupList.get(messageGroupList.size() - 1);
        assertThat(testMessageGroup.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testMessageGroup.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testMessageGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMessageGroup.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
        assertThat(testMessageGroup.getAddBy()).isEqualTo(UPDATED_ADD_BY);
        assertThat(testMessageGroup.getLastContent()).isEqualTo(UPDATED_LAST_CONTENT);
        assertThat(testMessageGroup.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        assertThat(testMessageGroup.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testMessageGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMessageGroup.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testMessageGroup.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testMessageGroup.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testMessageGroup.getComment()).isEqualTo(UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void patchNonExistingMessageGroup() throws Exception {
        int databaseSizeBeforeUpdate = messageGroupRepository.findAll().size();
        messageGroup.setId(count.incrementAndGet());

        // Create the MessageGroup
        MessageGroupDTO messageGroupDTO = messageGroupMapper.toDto(messageGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMessageGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, messageGroupDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(messageGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessageGroup in the database
        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MessageGroup in Elasticsearch
        verify(mockMessageGroupSearchRepository, times(0)).save(messageGroup);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMessageGroup() throws Exception {
        int databaseSizeBeforeUpdate = messageGroupRepository.findAll().size();
        messageGroup.setId(count.incrementAndGet());

        // Create the MessageGroup
        MessageGroupDTO messageGroupDTO = messageGroupMapper.toDto(messageGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessageGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(messageGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessageGroup in the database
        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MessageGroup in Elasticsearch
        verify(mockMessageGroupSearchRepository, times(0)).save(messageGroup);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMessageGroup() throws Exception {
        int databaseSizeBeforeUpdate = messageGroupRepository.findAll().size();
        messageGroup.setId(count.incrementAndGet());

        // Create the MessageGroup
        MessageGroupDTO messageGroupDTO = messageGroupMapper.toDto(messageGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessageGroupMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(messageGroupDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MessageGroup in the database
        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MessageGroup in Elasticsearch
        verify(mockMessageGroupSearchRepository, times(0)).save(messageGroup);
    }

    @Test
    @Transactional
    void deleteMessageGroup() throws Exception {
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);

        int databaseSizeBeforeDelete = messageGroupRepository.findAll().size();

        // Delete the messageGroup
        restMessageGroupMockMvc
            .perform(delete(ENTITY_API_URL_ID, messageGroup.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MessageGroup> messageGroupList = messageGroupRepository.findAll();
        assertThat(messageGroupList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the MessageGroup in Elasticsearch
        verify(mockMessageGroupSearchRepository, times(1)).deleteById(messageGroup.getId());
    }

    @Test
    @Transactional
    void searchMessageGroup() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        messageGroupRepository.saveAndFlush(messageGroup);
        when(mockMessageGroupSearchRepository.search(queryStringQuery("id:" + messageGroup.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(messageGroup), PageRequest.of(0, 1), 1));

        // Search the messageGroup
        restMessageGroupMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + messageGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(messageGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].addBy").value(hasItem(DEFAULT_ADD_BY)))
            .andExpect(jsonPath("$.[*].lastContent").value(hasItem(DEFAULT_LAST_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)));
    }
}
