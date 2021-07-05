package com.regitiny.catiny.web.rest;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.IntegrationTest;
import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.repository.MessageContentRepository;
import com.regitiny.catiny.repository.search.MessageContentSearchRepository;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.service.mapper.MessageContentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MessageContentResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class MessageContentResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_GROUP_ID = "AAAAAAAAAA";
  private static final String UPDATED_GROUP_ID = "BBBBBBBBBB";

  private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
  private static final String UPDATED_CONTENT = "BBBBBBBBBB";

  private static final String DEFAULT_SENDER = "AAAAAAAAAA";
  private static final String UPDATED_SENDER = "BBBBBBBBBB";

  private static final String DEFAULT_STATUS = "AAAAAAAAAA";
  private static final String UPDATED_STATUS = "BBBBBBBBBB";

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

  private static final String ENTITY_API_URL = "/api/message-contents";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/message-contents";

  private static final Random random = new Random();
  private static final AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private MessageContentRepository messageContentRepository;

  @Autowired
  private MessageContentMapper messageContentMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.MessageContentSearchRepositoryMockConfiguration
   */
  @Autowired
  private MessageContentSearchRepository mockMessageContentSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restMessageContentMockMvc;

  private MessageContent messageContent;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static MessageContent createEntity(EntityManager em) {
    MessageContent messageContent = new MessageContent()
      .uuid(DEFAULT_UUID)
      .groupId(DEFAULT_GROUP_ID)
      .content(DEFAULT_CONTENT)
      .sender(DEFAULT_SENDER)
      .status(DEFAULT_STATUS)
      .searchField(DEFAULT_SEARCH_FIELD)
      .role(DEFAULT_ROLE)
      .createdDate(DEFAULT_CREATED_DATE)
      .modifiedDate(DEFAULT_MODIFIED_DATE)
      .createdBy(DEFAULT_CREATED_BY)
      .modifiedBy(DEFAULT_MODIFIED_BY)
      .comment(DEFAULT_COMMENT);
    return messageContent;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static MessageContent createUpdatedEntity(EntityManager em) {
    MessageContent messageContent = new MessageContent()
      .uuid(UPDATED_UUID)
      .groupId(UPDATED_GROUP_ID)
      .content(UPDATED_CONTENT)
      .sender(UPDATED_SENDER)
      .status(UPDATED_STATUS)
      .searchField(UPDATED_SEARCH_FIELD)
      .role(UPDATED_ROLE)
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .modifiedBy(UPDATED_MODIFIED_BY)
      .comment(UPDATED_COMMENT);
    return messageContent;
  }

  @BeforeEach
  public void initTest() {
    messageContent = createEntity(em);
  }

  @Test
  @Transactional
  void createMessageContent() throws Exception {
    int databaseSizeBeforeCreate = messageContentRepository.findAll().size();
    // Create the MessageContent
    MessageContentDTO messageContentDTO = messageContentMapper.toDto(messageContent);
    restMessageContentMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageContentDTO))
      )
      .andExpect(status().isCreated());

    // Validate the MessageContent in the database
    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeCreate + 1);
    MessageContent testMessageContent = messageContentList.get(messageContentList.size() - 1);
    assertThat(testMessageContent.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testMessageContent.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
    assertThat(testMessageContent.getContent()).isEqualTo(DEFAULT_CONTENT);
    assertThat(testMessageContent.getSender()).isEqualTo(DEFAULT_SENDER);
    assertThat(testMessageContent.getStatus()).isEqualTo(DEFAULT_STATUS);
    assertThat(testMessageContent.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);
    assertThat(testMessageContent.getRole()).isEqualTo(DEFAULT_ROLE);
    assertThat(testMessageContent.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testMessageContent.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    assertThat(testMessageContent.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testMessageContent.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
    assertThat(testMessageContent.getComment()).isEqualTo(DEFAULT_COMMENT);

    // Validate the MessageContent in Elasticsearch
    verify(mockMessageContentSearchRepository, times(1)).save(testMessageContent);
  }

  @Test
  @Transactional
  void createMessageContentWithExistingId() throws Exception {
    // Create the MessageContent with an existing ID
    messageContent.setId(1L);
    MessageContentDTO messageContentDTO = messageContentMapper.toDto(messageContent);

    int databaseSizeBeforeCreate = messageContentRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restMessageContentMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageContentDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the MessageContent in the database
    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeCreate);

    // Validate the MessageContent in Elasticsearch
    verify(mockMessageContentSearchRepository, times(0)).save(messageContent);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = messageContentRepository.findAll().size();
    // set the field null
    messageContent.setUuid(null);

    // Create the MessageContent, which fails.
    MessageContentDTO messageContentDTO = messageContentMapper.toDto(messageContent);

    restMessageContentMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageContentDTO))
      )
      .andExpect(status().isBadRequest());

    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void checkGroupIdIsRequired() throws Exception {
    int databaseSizeBeforeTest = messageContentRepository.findAll().size();
    // set the field null
    messageContent.setGroupId(null);

    // Create the MessageContent, which fails.
    MessageContentDTO messageContentDTO = messageContentMapper.toDto(messageContent);

    restMessageContentMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageContentDTO))
      )
      .andExpect(status().isBadRequest());

    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllMessageContents() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList
    restMessageContentMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(messageContent.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
      .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
      .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
      .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD)))
      .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
      .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
      .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
      .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
      .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
      .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)));
  }

  @Test
  @Transactional
  void getMessageContent() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get the messageContent
    restMessageContentMockMvc
      .perform(get(ENTITY_API_URL_ID, messageContent.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(messageContent.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
      .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
      .andExpect(jsonPath("$.sender").value(DEFAULT_SENDER))
      .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
      .andExpect(jsonPath("$.searchField").value(DEFAULT_SEARCH_FIELD))
      .andExpect(jsonPath("$.role").value(DEFAULT_ROLE))
      .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
      .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
      .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
      .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
      .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT));
  }

  @Test
  @Transactional
  void getMessageContentsByIdFiltering() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    Long id = messageContent.getId();

    defaultMessageContentShouldBeFound("id.equals=" + id);
    defaultMessageContentShouldNotBeFound("id.notEquals=" + id);

    defaultMessageContentShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultMessageContentShouldNotBeFound("id.greaterThan=" + id);

    defaultMessageContentShouldBeFound("id.lessThanOrEqual=" + id);
    defaultMessageContentShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllMessageContentsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where uuid equals to DEFAULT_UUID
    defaultMessageContentShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the messageContentList where uuid equals to UPDATED_UUID
    defaultMessageContentShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllMessageContentsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where uuid not equals to DEFAULT_UUID
    defaultMessageContentShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the messageContentList where uuid not equals to UPDATED_UUID
    defaultMessageContentShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllMessageContentsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultMessageContentShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the messageContentList where uuid equals to UPDATED_UUID
    defaultMessageContentShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllMessageContentsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where uuid is not null
    defaultMessageContentShouldBeFound("uuid.specified=true");

    // Get all the messageContentList where uuid is null
    defaultMessageContentShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllMessageContentsByGroupIdIsEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where groupId equals to DEFAULT_GROUP_ID
    defaultMessageContentShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

    // Get all the messageContentList where groupId equals to UPDATED_GROUP_ID
    defaultMessageContentShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
  }

  @Test
  @Transactional
  void getAllMessageContentsByGroupIdIsNotEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where groupId not equals to DEFAULT_GROUP_ID
    defaultMessageContentShouldNotBeFound("groupId.notEquals=" + DEFAULT_GROUP_ID);

    // Get all the messageContentList where groupId not equals to UPDATED_GROUP_ID
    defaultMessageContentShouldBeFound("groupId.notEquals=" + UPDATED_GROUP_ID);
  }

  @Test
  @Transactional
  void getAllMessageContentsByGroupIdIsInShouldWork() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
    defaultMessageContentShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

    // Get all the messageContentList where groupId equals to UPDATED_GROUP_ID
    defaultMessageContentShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
  }

  @Test
  @Transactional
  void getAllMessageContentsByGroupIdIsNullOrNotNull() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where groupId is not null
    defaultMessageContentShouldBeFound("groupId.specified=true");

    // Get all the messageContentList where groupId is null
    defaultMessageContentShouldNotBeFound("groupId.specified=false");
  }

  @Test
  @Transactional
  void getAllMessageContentsByGroupIdContainsSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where groupId contains DEFAULT_GROUP_ID
    defaultMessageContentShouldBeFound("groupId.contains=" + DEFAULT_GROUP_ID);

    // Get all the messageContentList where groupId contains UPDATED_GROUP_ID
    defaultMessageContentShouldNotBeFound("groupId.contains=" + UPDATED_GROUP_ID);
  }

  @Test
  @Transactional
  void getAllMessageContentsByGroupIdNotContainsSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where groupId does not contain DEFAULT_GROUP_ID
    defaultMessageContentShouldNotBeFound("groupId.doesNotContain=" + DEFAULT_GROUP_ID);

    // Get all the messageContentList where groupId does not contain UPDATED_GROUP_ID
    defaultMessageContentShouldBeFound("groupId.doesNotContain=" + UPDATED_GROUP_ID);
  }

  @Test
  @Transactional
  void getAllMessageContentsBySenderIsEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where sender equals to DEFAULT_SENDER
    defaultMessageContentShouldBeFound("sender.equals=" + DEFAULT_SENDER);

    // Get all the messageContentList where sender equals to UPDATED_SENDER
    defaultMessageContentShouldNotBeFound("sender.equals=" + UPDATED_SENDER);
  }

  @Test
  @Transactional
  void getAllMessageContentsBySenderIsNotEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where sender not equals to DEFAULT_SENDER
    defaultMessageContentShouldNotBeFound("sender.notEquals=" + DEFAULT_SENDER);

    // Get all the messageContentList where sender not equals to UPDATED_SENDER
    defaultMessageContentShouldBeFound("sender.notEquals=" + UPDATED_SENDER);
  }

  @Test
  @Transactional
  void getAllMessageContentsBySenderIsInShouldWork() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where sender in DEFAULT_SENDER or UPDATED_SENDER
    defaultMessageContentShouldBeFound("sender.in=" + DEFAULT_SENDER + "," + UPDATED_SENDER);

    // Get all the messageContentList where sender equals to UPDATED_SENDER
    defaultMessageContentShouldNotBeFound("sender.in=" + UPDATED_SENDER);
  }

  @Test
  @Transactional
  void getAllMessageContentsBySenderIsNullOrNotNull() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where sender is not null
    defaultMessageContentShouldBeFound("sender.specified=true");

    // Get all the messageContentList where sender is null
    defaultMessageContentShouldNotBeFound("sender.specified=false");
  }

  @Test
  @Transactional
  void getAllMessageContentsBySenderContainsSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where sender contains DEFAULT_SENDER
    defaultMessageContentShouldBeFound("sender.contains=" + DEFAULT_SENDER);

    // Get all the messageContentList where sender contains UPDATED_SENDER
    defaultMessageContentShouldNotBeFound("sender.contains=" + UPDATED_SENDER);
  }

  @Test
  @Transactional
  void getAllMessageContentsBySenderNotContainsSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where sender does not contain DEFAULT_SENDER
    defaultMessageContentShouldNotBeFound("sender.doesNotContain=" + DEFAULT_SENDER);

    // Get all the messageContentList where sender does not contain UPDATED_SENDER
    defaultMessageContentShouldBeFound("sender.doesNotContain=" + UPDATED_SENDER);
  }

  @Test
  @Transactional
  void getAllMessageContentsByStatusIsEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where status equals to DEFAULT_STATUS
    defaultMessageContentShouldBeFound("status.equals=" + DEFAULT_STATUS);

    // Get all the messageContentList where status equals to UPDATED_STATUS
    defaultMessageContentShouldNotBeFound("status.equals=" + UPDATED_STATUS);
  }

  @Test
  @Transactional
  void getAllMessageContentsByStatusIsNotEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where status not equals to DEFAULT_STATUS
    defaultMessageContentShouldNotBeFound("status.notEquals=" + DEFAULT_STATUS);

    // Get all the messageContentList where status not equals to UPDATED_STATUS
    defaultMessageContentShouldBeFound("status.notEquals=" + UPDATED_STATUS);
  }

  @Test
  @Transactional
  void getAllMessageContentsByStatusIsInShouldWork() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where status in DEFAULT_STATUS or UPDATED_STATUS
    defaultMessageContentShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

    // Get all the messageContentList where status equals to UPDATED_STATUS
    defaultMessageContentShouldNotBeFound("status.in=" + UPDATED_STATUS);
  }

  @Test
  @Transactional
  void getAllMessageContentsByStatusIsNullOrNotNull() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where status is not null
    defaultMessageContentShouldBeFound("status.specified=true");

    // Get all the messageContentList where status is null
    defaultMessageContentShouldNotBeFound("status.specified=false");
  }

  @Test
  @Transactional
  void getAllMessageContentsByStatusContainsSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where status contains DEFAULT_STATUS
    defaultMessageContentShouldBeFound("status.contains=" + DEFAULT_STATUS);

    // Get all the messageContentList where status contains UPDATED_STATUS
    defaultMessageContentShouldNotBeFound("status.contains=" + UPDATED_STATUS);
  }

  @Test
  @Transactional
  void getAllMessageContentsByStatusNotContainsSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where status does not contain DEFAULT_STATUS
    defaultMessageContentShouldNotBeFound("status.doesNotContain=" + DEFAULT_STATUS);

    // Get all the messageContentList where status does not contain UPDATED_STATUS
    defaultMessageContentShouldBeFound("status.doesNotContain=" + UPDATED_STATUS);
  }

  @Test
  @Transactional
  void getAllMessageContentsByRoleIsEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where role equals to DEFAULT_ROLE
    defaultMessageContentShouldBeFound("role.equals=" + DEFAULT_ROLE);

    // Get all the messageContentList where role equals to UPDATED_ROLE
    defaultMessageContentShouldNotBeFound("role.equals=" + UPDATED_ROLE);
  }

  @Test
  @Transactional
  void getAllMessageContentsByRoleIsNotEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where role not equals to DEFAULT_ROLE
    defaultMessageContentShouldNotBeFound("role.notEquals=" + DEFAULT_ROLE);

    // Get all the messageContentList where role not equals to UPDATED_ROLE
    defaultMessageContentShouldBeFound("role.notEquals=" + UPDATED_ROLE);
  }

  @Test
  @Transactional
  void getAllMessageContentsByRoleIsInShouldWork() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where role in DEFAULT_ROLE or UPDATED_ROLE
    defaultMessageContentShouldBeFound("role.in=" + DEFAULT_ROLE + "," + UPDATED_ROLE);

    // Get all the messageContentList where role equals to UPDATED_ROLE
    defaultMessageContentShouldNotBeFound("role.in=" + UPDATED_ROLE);
  }

  @Test
  @Transactional
  void getAllMessageContentsByRoleIsNullOrNotNull() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where role is not null
    defaultMessageContentShouldBeFound("role.specified=true");

    // Get all the messageContentList where role is null
    defaultMessageContentShouldNotBeFound("role.specified=false");
  }

  @Test
  @Transactional
  void getAllMessageContentsByRoleContainsSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where role contains DEFAULT_ROLE
    defaultMessageContentShouldBeFound("role.contains=" + DEFAULT_ROLE);

    // Get all the messageContentList where role contains UPDATED_ROLE
    defaultMessageContentShouldNotBeFound("role.contains=" + UPDATED_ROLE);
  }

  @Test
  @Transactional
  void getAllMessageContentsByRoleNotContainsSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where role does not contain DEFAULT_ROLE
    defaultMessageContentShouldNotBeFound("role.doesNotContain=" + DEFAULT_ROLE);

    // Get all the messageContentList where role does not contain UPDATED_ROLE
    defaultMessageContentShouldBeFound("role.doesNotContain=" + UPDATED_ROLE);
  }

  @Test
  @Transactional
  void getAllMessageContentsByCreatedDateIsEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where createdDate equals to DEFAULT_CREATED_DATE
    defaultMessageContentShouldBeFound("createdDate.equals=" + DEFAULT_CREATED_DATE);

    // Get all the messageContentList where createdDate equals to UPDATED_CREATED_DATE
    defaultMessageContentShouldNotBeFound("createdDate.equals=" + UPDATED_CREATED_DATE);
  }

  @Test
  @Transactional
  void getAllMessageContentsByCreatedDateIsNotEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where createdDate not equals to DEFAULT_CREATED_DATE
    defaultMessageContentShouldNotBeFound("createdDate.notEquals=" + DEFAULT_CREATED_DATE);

    // Get all the messageContentList where createdDate not equals to UPDATED_CREATED_DATE
    defaultMessageContentShouldBeFound("createdDate.notEquals=" + UPDATED_CREATED_DATE);
  }

  @Test
  @Transactional
  void getAllMessageContentsByCreatedDateIsInShouldWork() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
    defaultMessageContentShouldBeFound("createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE);

    // Get all the messageContentList where createdDate equals to UPDATED_CREATED_DATE
    defaultMessageContentShouldNotBeFound("createdDate.in=" + UPDATED_CREATED_DATE);
  }

  @Test
  @Transactional
  void getAllMessageContentsByCreatedDateIsNullOrNotNull() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where createdDate is not null
    defaultMessageContentShouldBeFound("createdDate.specified=true");

    // Get all the messageContentList where createdDate is null
    defaultMessageContentShouldNotBeFound("createdDate.specified=false");
  }

  @Test
  @Transactional
  void getAllMessageContentsByModifiedDateIsEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where modifiedDate equals to DEFAULT_MODIFIED_DATE
    defaultMessageContentShouldBeFound("modifiedDate.equals=" + DEFAULT_MODIFIED_DATE);

    // Get all the messageContentList where modifiedDate equals to UPDATED_MODIFIED_DATE
    defaultMessageContentShouldNotBeFound("modifiedDate.equals=" + UPDATED_MODIFIED_DATE);
  }

  @Test
  @Transactional
  void getAllMessageContentsByModifiedDateIsNotEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where modifiedDate not equals to DEFAULT_MODIFIED_DATE
    defaultMessageContentShouldNotBeFound("modifiedDate.notEquals=" + DEFAULT_MODIFIED_DATE);

    // Get all the messageContentList where modifiedDate not equals to UPDATED_MODIFIED_DATE
    defaultMessageContentShouldBeFound("modifiedDate.notEquals=" + UPDATED_MODIFIED_DATE);
  }

  @Test
  @Transactional
  void getAllMessageContentsByModifiedDateIsInShouldWork() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where modifiedDate in DEFAULT_MODIFIED_DATE or UPDATED_MODIFIED_DATE
    defaultMessageContentShouldBeFound("modifiedDate.in=" + DEFAULT_MODIFIED_DATE + "," + UPDATED_MODIFIED_DATE);

    // Get all the messageContentList where modifiedDate equals to UPDATED_MODIFIED_DATE
    defaultMessageContentShouldNotBeFound("modifiedDate.in=" + UPDATED_MODIFIED_DATE);
  }

  @Test
  @Transactional
  void getAllMessageContentsByModifiedDateIsNullOrNotNull() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where modifiedDate is not null
    defaultMessageContentShouldBeFound("modifiedDate.specified=true");

    // Get all the messageContentList where modifiedDate is null
    defaultMessageContentShouldNotBeFound("modifiedDate.specified=false");
  }

  @Test
  @Transactional
  void getAllMessageContentsByCreatedByIsEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where createdBy equals to DEFAULT_CREATED_BY
    defaultMessageContentShouldBeFound("createdBy.equals=" + DEFAULT_CREATED_BY);

    // Get all the messageContentList where createdBy equals to UPDATED_CREATED_BY
    defaultMessageContentShouldNotBeFound("createdBy.equals=" + UPDATED_CREATED_BY);
  }

  @Test
  @Transactional
  void getAllMessageContentsByCreatedByIsNotEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where createdBy not equals to DEFAULT_CREATED_BY
    defaultMessageContentShouldNotBeFound("createdBy.notEquals=" + DEFAULT_CREATED_BY);

    // Get all the messageContentList where createdBy not equals to UPDATED_CREATED_BY
    defaultMessageContentShouldBeFound("createdBy.notEquals=" + UPDATED_CREATED_BY);
  }

  @Test
  @Transactional
  void getAllMessageContentsByCreatedByIsInShouldWork() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
    defaultMessageContentShouldBeFound("createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY);

    // Get all the messageContentList where createdBy equals to UPDATED_CREATED_BY
    defaultMessageContentShouldNotBeFound("createdBy.in=" + UPDATED_CREATED_BY);
  }

  @Test
  @Transactional
  void getAllMessageContentsByCreatedByIsNullOrNotNull() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where createdBy is not null
    defaultMessageContentShouldBeFound("createdBy.specified=true");

    // Get all the messageContentList where createdBy is null
    defaultMessageContentShouldNotBeFound("createdBy.specified=false");
  }

  @Test
  @Transactional
  void getAllMessageContentsByCreatedByContainsSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where createdBy contains DEFAULT_CREATED_BY
    defaultMessageContentShouldBeFound("createdBy.contains=" + DEFAULT_CREATED_BY);

    // Get all the messageContentList where createdBy contains UPDATED_CREATED_BY
    defaultMessageContentShouldNotBeFound("createdBy.contains=" + UPDATED_CREATED_BY);
  }

  @Test
  @Transactional
  void getAllMessageContentsByCreatedByNotContainsSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where createdBy does not contain DEFAULT_CREATED_BY
    defaultMessageContentShouldNotBeFound("createdBy.doesNotContain=" + DEFAULT_CREATED_BY);

    // Get all the messageContentList where createdBy does not contain UPDATED_CREATED_BY
    defaultMessageContentShouldBeFound("createdBy.doesNotContain=" + UPDATED_CREATED_BY);
  }

  @Test
  @Transactional
  void getAllMessageContentsByModifiedByIsEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where modifiedBy equals to DEFAULT_MODIFIED_BY
    defaultMessageContentShouldBeFound("modifiedBy.equals=" + DEFAULT_MODIFIED_BY);

    // Get all the messageContentList where modifiedBy equals to UPDATED_MODIFIED_BY
    defaultMessageContentShouldNotBeFound("modifiedBy.equals=" + UPDATED_MODIFIED_BY);
  }

  @Test
  @Transactional
  void getAllMessageContentsByModifiedByIsNotEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where modifiedBy not equals to DEFAULT_MODIFIED_BY
    defaultMessageContentShouldNotBeFound("modifiedBy.notEquals=" + DEFAULT_MODIFIED_BY);

    // Get all the messageContentList where modifiedBy not equals to UPDATED_MODIFIED_BY
    defaultMessageContentShouldBeFound("modifiedBy.notEquals=" + UPDATED_MODIFIED_BY);
  }

  @Test
  @Transactional
  void getAllMessageContentsByModifiedByIsInShouldWork() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where modifiedBy in DEFAULT_MODIFIED_BY or UPDATED_MODIFIED_BY
    defaultMessageContentShouldBeFound("modifiedBy.in=" + DEFAULT_MODIFIED_BY + "," + UPDATED_MODIFIED_BY);

    // Get all the messageContentList where modifiedBy equals to UPDATED_MODIFIED_BY
    defaultMessageContentShouldNotBeFound("modifiedBy.in=" + UPDATED_MODIFIED_BY);
  }

  @Test
  @Transactional
  void getAllMessageContentsByModifiedByIsNullOrNotNull() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where modifiedBy is not null
    defaultMessageContentShouldBeFound("modifiedBy.specified=true");

    // Get all the messageContentList where modifiedBy is null
    defaultMessageContentShouldNotBeFound("modifiedBy.specified=false");
  }

  @Test
  @Transactional
  void getAllMessageContentsByModifiedByContainsSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where modifiedBy contains DEFAULT_MODIFIED_BY
    defaultMessageContentShouldBeFound("modifiedBy.contains=" + DEFAULT_MODIFIED_BY);

    // Get all the messageContentList where modifiedBy contains UPDATED_MODIFIED_BY
    defaultMessageContentShouldNotBeFound("modifiedBy.contains=" + UPDATED_MODIFIED_BY);
  }

  @Test
  @Transactional
  void getAllMessageContentsByModifiedByNotContainsSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where modifiedBy does not contain DEFAULT_MODIFIED_BY
    defaultMessageContentShouldNotBeFound("modifiedBy.doesNotContain=" + DEFAULT_MODIFIED_BY);

    // Get all the messageContentList where modifiedBy does not contain UPDATED_MODIFIED_BY
    defaultMessageContentShouldBeFound("modifiedBy.doesNotContain=" + UPDATED_MODIFIED_BY);
  }

  @Test
  @Transactional
  void getAllMessageContentsByCommentIsEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where comment equals to DEFAULT_COMMENT
    defaultMessageContentShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

    // Get all the messageContentList where comment equals to UPDATED_COMMENT
    defaultMessageContentShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
  }

  @Test
  @Transactional
  void getAllMessageContentsByCommentIsNotEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where comment not equals to DEFAULT_COMMENT
    defaultMessageContentShouldNotBeFound("comment.notEquals=" + DEFAULT_COMMENT);

    // Get all the messageContentList where comment not equals to UPDATED_COMMENT
    defaultMessageContentShouldBeFound("comment.notEquals=" + UPDATED_COMMENT);
  }

  @Test
  @Transactional
  void getAllMessageContentsByCommentIsInShouldWork() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
    defaultMessageContentShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

    // Get all the messageContentList where comment equals to UPDATED_COMMENT
    defaultMessageContentShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
  }

  @Test
  @Transactional
  void getAllMessageContentsByCommentIsNullOrNotNull() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where comment is not null
    defaultMessageContentShouldBeFound("comment.specified=true");

    // Get all the messageContentList where comment is null
    defaultMessageContentShouldNotBeFound("comment.specified=false");
  }

  @Test
  @Transactional
  void getAllMessageContentsByCommentContainsSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where comment contains DEFAULT_COMMENT
    defaultMessageContentShouldBeFound("comment.contains=" + DEFAULT_COMMENT);

    // Get all the messageContentList where comment contains UPDATED_COMMENT
    defaultMessageContentShouldNotBeFound("comment.contains=" + UPDATED_COMMENT);
  }

  @Test
  @Transactional
  void getAllMessageContentsByCommentNotContainsSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    // Get all the messageContentList where comment does not contain DEFAULT_COMMENT
    defaultMessageContentShouldNotBeFound("comment.doesNotContain=" + DEFAULT_COMMENT);

    // Get all the messageContentList where comment does not contain UPDATED_COMMENT
    defaultMessageContentShouldBeFound("comment.doesNotContain=" + UPDATED_COMMENT);
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultMessageContentShouldBeFound(String filter) throws Exception {
    restMessageContentMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(messageContent.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
      .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
      .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
      .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD)))
      .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
      .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
      .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
      .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
      .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
      .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)));

    // Check, that the count call also returns 1
    restMessageContentMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultMessageContentShouldNotBeFound(String filter) throws Exception {
    restMessageContentMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restMessageContentMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingMessageContent() throws Exception {
    // Get the messageContent
    restMessageContentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewMessageContent() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    int databaseSizeBeforeUpdate = messageContentRepository.findAll().size();

    // Update the messageContent
    MessageContent updatedMessageContent = messageContentRepository.findById(messageContent.getId()).get();
    // Disconnect from session so that the updates on updatedMessageContent are not directly saved in db
    em.detach(updatedMessageContent);
    updatedMessageContent
      .uuid(UPDATED_UUID)
      .groupId(UPDATED_GROUP_ID)
      .content(UPDATED_CONTENT)
      .sender(UPDATED_SENDER)
      .status(UPDATED_STATUS)
      .searchField(UPDATED_SEARCH_FIELD)
      .role(UPDATED_ROLE)
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .modifiedBy(UPDATED_MODIFIED_BY)
      .comment(UPDATED_COMMENT);
    MessageContentDTO messageContentDTO = messageContentMapper.toDto(updatedMessageContent);

    restMessageContentMockMvc
      .perform(
        put(ENTITY_API_URL_ID, messageContentDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(messageContentDTO))
      )
      .andExpect(status().isOk());

    // Validate the MessageContent in the database
    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeUpdate);
    MessageContent testMessageContent = messageContentList.get(messageContentList.size() - 1);
    assertThat(testMessageContent.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testMessageContent.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
    assertThat(testMessageContent.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testMessageContent.getSender()).isEqualTo(UPDATED_SENDER);
    assertThat(testMessageContent.getStatus()).isEqualTo(UPDATED_STATUS);
    assertThat(testMessageContent.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
    assertThat(testMessageContent.getRole()).isEqualTo(UPDATED_ROLE);
    assertThat(testMessageContent.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testMessageContent.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    assertThat(testMessageContent.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testMessageContent.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    assertThat(testMessageContent.getComment()).isEqualTo(UPDATED_COMMENT);

    // Validate the MessageContent in Elasticsearch
    verify(mockMessageContentSearchRepository).save(testMessageContent);
  }

  @Test
  @Transactional
  void putNonExistingMessageContent() throws Exception {
    int databaseSizeBeforeUpdate = messageContentRepository.findAll().size();
    messageContent.setId(count.incrementAndGet());

    // Create the MessageContent
    MessageContentDTO messageContentDTO = messageContentMapper.toDto(messageContent);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restMessageContentMockMvc
      .perform(
        put(ENTITY_API_URL_ID, messageContentDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(messageContentDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the MessageContent in the database
    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeUpdate);

    // Validate the MessageContent in Elasticsearch
    verify(mockMessageContentSearchRepository, times(0)).save(messageContent);
  }

  @Test
  @Transactional
  void putWithIdMismatchMessageContent() throws Exception {
    int databaseSizeBeforeUpdate = messageContentRepository.findAll().size();
    messageContent.setId(count.incrementAndGet());

    // Create the MessageContent
    MessageContentDTO messageContentDTO = messageContentMapper.toDto(messageContent);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMessageContentMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(messageContentDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the MessageContent in the database
    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeUpdate);

    // Validate the MessageContent in Elasticsearch
    verify(mockMessageContentSearchRepository, times(0)).save(messageContent);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamMessageContent() throws Exception {
    int databaseSizeBeforeUpdate = messageContentRepository.findAll().size();
    messageContent.setId(count.incrementAndGet());

    // Create the MessageContent
    MessageContentDTO messageContentDTO = messageContentMapper.toDto(messageContent);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMessageContentMockMvc
      .perform(
        put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageContentDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the MessageContent in the database
    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeUpdate);

    // Validate the MessageContent in Elasticsearch
    verify(mockMessageContentSearchRepository, times(0)).save(messageContent);
  }

  @Test
  @Transactional
  void partialUpdateMessageContentWithPatch() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    int databaseSizeBeforeUpdate = messageContentRepository.findAll().size();

    // Update the messageContent using partial update
    MessageContent partialUpdatedMessageContent = new MessageContent();
    partialUpdatedMessageContent.setId(messageContent.getId());

    partialUpdatedMessageContent.sender(UPDATED_SENDER).status(UPDATED_STATUS).role(UPDATED_ROLE).modifiedDate(UPDATED_MODIFIED_DATE);

    restMessageContentMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedMessageContent.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMessageContent))
      )
      .andExpect(status().isOk());

    // Validate the MessageContent in the database
    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeUpdate);
    MessageContent testMessageContent = messageContentList.get(messageContentList.size() - 1);
    assertThat(testMessageContent.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testMessageContent.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
    assertThat(testMessageContent.getContent()).isEqualTo(DEFAULT_CONTENT);
    assertThat(testMessageContent.getSender()).isEqualTo(UPDATED_SENDER);
    assertThat(testMessageContent.getStatus()).isEqualTo(UPDATED_STATUS);
    assertThat(testMessageContent.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);
    assertThat(testMessageContent.getRole()).isEqualTo(UPDATED_ROLE);
    assertThat(testMessageContent.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testMessageContent.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    assertThat(testMessageContent.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testMessageContent.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
    assertThat(testMessageContent.getComment()).isEqualTo(DEFAULT_COMMENT);
  }

  @Test
  @Transactional
  void fullUpdateMessageContentWithPatch() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    int databaseSizeBeforeUpdate = messageContentRepository.findAll().size();

    // Update the messageContent using partial update
    MessageContent partialUpdatedMessageContent = new MessageContent();
    partialUpdatedMessageContent.setId(messageContent.getId());

    partialUpdatedMessageContent
      .uuid(UPDATED_UUID)
      .groupId(UPDATED_GROUP_ID)
      .content(UPDATED_CONTENT)
      .sender(UPDATED_SENDER)
      .status(UPDATED_STATUS)
      .searchField(UPDATED_SEARCH_FIELD)
      .role(UPDATED_ROLE)
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .modifiedBy(UPDATED_MODIFIED_BY)
      .comment(UPDATED_COMMENT);

    restMessageContentMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedMessageContent.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMessageContent))
      )
      .andExpect(status().isOk());

    // Validate the MessageContent in the database
    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeUpdate);
    MessageContent testMessageContent = messageContentList.get(messageContentList.size() - 1);
    assertThat(testMessageContent.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testMessageContent.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
    assertThat(testMessageContent.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testMessageContent.getSender()).isEqualTo(UPDATED_SENDER);
    assertThat(testMessageContent.getStatus()).isEqualTo(UPDATED_STATUS);
    assertThat(testMessageContent.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
    assertThat(testMessageContent.getRole()).isEqualTo(UPDATED_ROLE);
    assertThat(testMessageContent.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testMessageContent.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    assertThat(testMessageContent.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testMessageContent.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    assertThat(testMessageContent.getComment()).isEqualTo(UPDATED_COMMENT);
  }

  @Test
  @Transactional
  void patchNonExistingMessageContent() throws Exception {
    int databaseSizeBeforeUpdate = messageContentRepository.findAll().size();
    messageContent.setId(count.incrementAndGet());

    // Create the MessageContent
    MessageContentDTO messageContentDTO = messageContentMapper.toDto(messageContent);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restMessageContentMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, messageContentDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(messageContentDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the MessageContent in the database
    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeUpdate);

    // Validate the MessageContent in Elasticsearch
    verify(mockMessageContentSearchRepository, times(0)).save(messageContent);
  }

  @Test
  @Transactional
  void patchWithIdMismatchMessageContent() throws Exception {
    int databaseSizeBeforeUpdate = messageContentRepository.findAll().size();
    messageContent.setId(count.incrementAndGet());

    // Create the MessageContent
    MessageContentDTO messageContentDTO = messageContentMapper.toDto(messageContent);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMessageContentMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(messageContentDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the MessageContent in the database
    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeUpdate);

    // Validate the MessageContent in Elasticsearch
    verify(mockMessageContentSearchRepository, times(0)).save(messageContent);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamMessageContent() throws Exception {
    int databaseSizeBeforeUpdate = messageContentRepository.findAll().size();
    messageContent.setId(count.incrementAndGet());

    // Create the MessageContent
    MessageContentDTO messageContentDTO = messageContentMapper.toDto(messageContent);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMessageContentMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(messageContentDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the MessageContent in the database
    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeUpdate);

    // Validate the MessageContent in Elasticsearch
    verify(mockMessageContentSearchRepository, times(0)).save(messageContent);
  }

  @Test
  @Transactional
  void deleteMessageContent() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);

    int databaseSizeBeforeDelete = messageContentRepository.findAll().size();

    // Delete the messageContent
    restMessageContentMockMvc
      .perform(delete(ENTITY_API_URL_ID, messageContent.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the MessageContent in Elasticsearch
    verify(mockMessageContentSearchRepository, times(1)).deleteById(messageContent.getId());
  }

  @Test
  @Transactional
  void searchMessageContent() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);
    when(mockMessageContentSearchRepository.search(queryStringQuery("id:" + messageContent.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(messageContent), PageRequest.of(0, 1), 1));

    // Search the messageContent
    restMessageContentMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + messageContent.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(messageContent.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
      .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
      .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
      .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD)))
      .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
      .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
      .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
      .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
      .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
      .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)));
  }
}
