package com.regitiny.catiny.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.regitiny.catiny.IntegrationTest;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.repository.MessageGroupRepository;
import com.regitiny.catiny.repository.search.MessageGroupSearchRepository;
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
class MessageGroupResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final Long DEFAULT_USER_ID = 1L;
  private static final Long UPDATED_USER_ID = 2L;

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
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageGroupDTO)))
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
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageGroupDTO)))
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
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageGroupDTO)))
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
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageGroupDTO)))
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
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageGroupDTO)))
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
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageGroupDTO)))
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
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
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
    assertThat(testMessageGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
    assertThat(testMessageGroup.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
    assertThat(testMessageGroup.getAddBy()).isEqualTo(UPDATED_ADD_BY);
    assertThat(testMessageGroup.getLastContent()).isEqualTo(DEFAULT_LAST_CONTENT);
    assertThat(testMessageGroup.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
    assertThat(testMessageGroup.getRole()).isEqualTo(DEFAULT_ROLE);
    assertThat(testMessageGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testMessageGroup.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    assertThat(testMessageGroup.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testMessageGroup.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    assertThat(testMessageGroup.getComment()).isEqualTo(UPDATED_COMMENT);
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
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(messageGroupDTO))
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
