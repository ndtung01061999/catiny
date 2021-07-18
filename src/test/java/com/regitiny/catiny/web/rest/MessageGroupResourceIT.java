package com.regitiny.catiny.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.IntegrationTest;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.repository.MessageGroupRepository;
import com.regitiny.catiny.repository.search.MessageGroupSearchRepository;
import com.regitiny.catiny.service.criteria.MessageGroupCriteria;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import com.regitiny.catiny.service.mapper.MessageGroupMapper;
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

  private static final String DEFAULT_GROUP_NAME = "AAAAAAAAAA";
  private static final String UPDATED_GROUP_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
  private static final String UPDATED_AVATAR = "BBBBBBBBBB";

  private static final String DEFAULT_ADD_BY = "AAAAAAAAAA";
  private static final String UPDATED_ADD_BY = "BBBBBBBBBB";

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
      .groupName(DEFAULT_GROUP_NAME)
      .avatar(DEFAULT_AVATAR)
      .addBy(DEFAULT_ADD_BY);
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
      .groupName(UPDATED_GROUP_NAME)
      .avatar(UPDATED_AVATAR)
      .addBy(UPDATED_ADD_BY);
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
    assertThat(testMessageGroup.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
    assertThat(testMessageGroup.getAvatar()).isEqualTo(DEFAULT_AVATAR);
    assertThat(testMessageGroup.getAddBy()).isEqualTo(DEFAULT_ADD_BY);

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
      .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
      .andExpect(jsonPath("$.[*].addBy").value(hasItem(DEFAULT_ADD_BY)));
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
      .andExpect(jsonPath("$.groupName").value(DEFAULT_GROUP_NAME))
      .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR.toString()))
      .andExpect(jsonPath("$.addBy").value(DEFAULT_ADD_BY));
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
  void getAllMessageGroupsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    messageGroupRepository.saveAndFlush(messageGroup);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    messageGroup.setBaseInfo(baseInfo);
    messageGroupRepository.saveAndFlush(messageGroup);
    Long baseInfoId = baseInfo.getId();

    // Get all the messageGroupList where baseInfo equals to baseInfoId
    defaultMessageGroupShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the messageGroupList where baseInfo equals to (baseInfoId + 1)
    defaultMessageGroupShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllMessageGroupsByMessageContentIsEqualToSomething() throws Exception {
    // Initialize the database
    messageGroupRepository.saveAndFlush(messageGroup);
    MessageContent messageContent = MessageContentResourceIT.createEntity(em);
    em.persist(messageContent);
    em.flush();
    messageGroup.addMessageContent(messageContent);
    messageGroupRepository.saveAndFlush(messageGroup);
    Long messageContentId = messageContent.getId();

    // Get all the messageGroupList where messageContent equals to messageContentId
    defaultMessageGroupShouldBeFound("messageContentId.equals=" + messageContentId);

    // Get all the messageGroupList where messageContent equals to (messageContentId + 1)
    defaultMessageGroupShouldNotBeFound("messageContentId.equals=" + (messageContentId + 1));
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
      .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
      .andExpect(jsonPath("$.[*].addBy").value(hasItem(DEFAULT_ADD_BY)));

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
    updatedMessageGroup.uuid(UPDATED_UUID).groupName(UPDATED_GROUP_NAME).avatar(UPDATED_AVATAR).addBy(UPDATED_ADD_BY);
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
    assertThat(testMessageGroup.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
    assertThat(testMessageGroup.getAvatar()).isEqualTo(UPDATED_AVATAR);
    assertThat(testMessageGroup.getAddBy()).isEqualTo(UPDATED_ADD_BY);

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

    partialUpdatedMessageGroup.uuid(UPDATED_UUID).groupName(UPDATED_GROUP_NAME);

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
    assertThat(testMessageGroup.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
    assertThat(testMessageGroup.getAvatar()).isEqualTo(DEFAULT_AVATAR);
    assertThat(testMessageGroup.getAddBy()).isEqualTo(DEFAULT_ADD_BY);
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

    partialUpdatedMessageGroup.uuid(UPDATED_UUID).groupName(UPDATED_GROUP_NAME).avatar(UPDATED_AVATAR).addBy(UPDATED_ADD_BY);

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
    assertThat(testMessageGroup.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
    assertThat(testMessageGroup.getAvatar()).isEqualTo(UPDATED_AVATAR);
    assertThat(testMessageGroup.getAddBy()).isEqualTo(UPDATED_ADD_BY);
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
      .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
      .andExpect(jsonPath("$.[*].addBy").value(hasItem(DEFAULT_ADD_BY)));
  }
}
