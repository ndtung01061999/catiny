package com.regitiny.catiny.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.regitiny.catiny.IntegrationTest;
import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.repository.MessageContentRepository;
import com.regitiny.catiny.repository.search.MessageContentSearchRepository;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.service.mapper.MessageContentMapper;
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
 * Integration tests for the {@link MessageContentResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
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
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messageContentDTO)))
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
  void checkGroupIdIsRequired() throws Exception {
    int databaseSizeBeforeTest = messageContentRepository.findAll().size();
    // set the field null
    messageContent.setGroupId(null);

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
      .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
      .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
      .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
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
      .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
      .andExpect(jsonPath("$.sender").value(DEFAULT_SENDER))
      .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
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

    partialUpdatedMessageContent
      .groupId(UPDATED_GROUP_ID)
      .content(UPDATED_CONTENT)
      .status(UPDATED_STATUS)
      .role(UPDATED_ROLE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .modifiedBy(UPDATED_MODIFIED_BY);

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
    assertThat(testMessageContent.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
    assertThat(testMessageContent.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testMessageContent.getSender()).isEqualTo(DEFAULT_SENDER);
    assertThat(testMessageContent.getStatus()).isEqualTo(UPDATED_STATUS);
    assertThat(testMessageContent.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);
    assertThat(testMessageContent.getRole()).isEqualTo(UPDATED_ROLE);
    assertThat(testMessageContent.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testMessageContent.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    assertThat(testMessageContent.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testMessageContent.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
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
      .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
      .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
      .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
      .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())))
      .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
      .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
      .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
      .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
      .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
      .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)));
  }
}
