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
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.repository.MessageContentRepository;
import com.regitiny.catiny.repository.search.MessageContentSearchRepository;
import com.regitiny.catiny.service.criteria.MessageContentCriteria;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.service.mapper.MessageContentMapper;
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

  private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
  private static final String UPDATED_CONTENT = "BBBBBBBBBB";

  private static final String DEFAULT_STATUS = "AAAAAAAAAA";
  private static final String UPDATED_STATUS = "BBBBBBBBBB";

  private static final String DEFAULT_SEARCH_FIELD = "AAAAAAAAAA";
  private static final String UPDATED_SEARCH_FIELD = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/message-contents";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/message-contents";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

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
      .content(DEFAULT_CONTENT)
      .status(DEFAULT_STATUS)
      .searchField(DEFAULT_SEARCH_FIELD);
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
      .content(UPDATED_CONTENT)
      .status(UPDATED_STATUS)
      .searchField(UPDATED_SEARCH_FIELD);
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
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageContentDTO)))
      .andExpect(status().isCreated());

    // Validate the MessageContent in the database
    List<MessageContent> messageContentList = messageContentRepository.findAll();
    assertThat(messageContentList).hasSize(databaseSizeBeforeCreate + 1);
    MessageContent testMessageContent = messageContentList.get(messageContentList.size() - 1);
    assertThat(testMessageContent.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testMessageContent.getContent()).isEqualTo(DEFAULT_CONTENT);
    assertThat(testMessageContent.getStatus()).isEqualTo(DEFAULT_STATUS);
    assertThat(testMessageContent.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);

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
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageContentDTO)))
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
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageContentDTO)))
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
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
      .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
      .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())));
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
      .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
      .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
      .andExpect(jsonPath("$.searchField").value(DEFAULT_SEARCH_FIELD.toString()));
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
  void getAllMessageContentsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    messageContent.setBaseInfo(baseInfo);
    messageContentRepository.saveAndFlush(messageContent);
    Long baseInfoId = baseInfo.getId();

    // Get all the messageContentList where baseInfo equals to baseInfoId
    defaultMessageContentShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the messageContentList where baseInfo equals to (baseInfoId + 1)
    defaultMessageContentShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllMessageContentsBySenderIsEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);
    MasterUser sender = MasterUserResourceIT.createEntity(em);
    em.persist(sender);
    em.flush();
    messageContent.setSender(sender);
    messageContentRepository.saveAndFlush(messageContent);
    Long senderId = sender.getId();

    // Get all the messageContentList where sender equals to senderId
    defaultMessageContentShouldBeFound("senderId.equals=" + senderId);

    // Get all the messageContentList where sender equals to (senderId + 1)
    defaultMessageContentShouldNotBeFound("senderId.equals=" + (senderId + 1));
  }

  @Test
  @Transactional
  void getAllMessageContentsByMessageGroupIsEqualToSomething() throws Exception {
    // Initialize the database
    messageContentRepository.saveAndFlush(messageContent);
    MessageGroup messageGroup = MessageGroupResourceIT.createEntity(em);
    em.persist(messageGroup);
    em.flush();
    messageContent.setMessageGroup(messageGroup);
    messageContentRepository.saveAndFlush(messageContent);
    Long messageGroupId = messageGroup.getId();

    // Get all the messageContentList where messageGroup equals to messageGroupId
    defaultMessageContentShouldBeFound("messageGroupId.equals=" + messageGroupId);

    // Get all the messageContentList where messageGroup equals to (messageGroupId + 1)
    defaultMessageContentShouldNotBeFound("messageGroupId.equals=" + (messageGroupId + 1));
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
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
      .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
      .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())));

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
    updatedMessageContent.uuid(UPDATED_UUID).content(UPDATED_CONTENT).status(UPDATED_STATUS).searchField(UPDATED_SEARCH_FIELD);
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
    assertThat(testMessageContent.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testMessageContent.getStatus()).isEqualTo(UPDATED_STATUS);
    assertThat(testMessageContent.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);

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
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageContentDTO)))
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

    partialUpdatedMessageContent.searchField(UPDATED_SEARCH_FIELD);

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
    assertThat(testMessageContent.getContent()).isEqualTo(DEFAULT_CONTENT);
    assertThat(testMessageContent.getStatus()).isEqualTo(DEFAULT_STATUS);
    assertThat(testMessageContent.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
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

    partialUpdatedMessageContent.uuid(UPDATED_UUID).content(UPDATED_CONTENT).status(UPDATED_STATUS).searchField(UPDATED_SEARCH_FIELD);

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
    assertThat(testMessageContent.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testMessageContent.getStatus()).isEqualTo(UPDATED_STATUS);
    assertThat(testMessageContent.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
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
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(messageContentDTO))
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
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
      .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
      .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())));
  }
}
