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
import com.regitiny.catiny.domain.NewsFeed;
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.repository.NewsFeedRepository;
import com.regitiny.catiny.repository.search.NewsFeedSearchRepository;
import com.regitiny.catiny.service.criteria.NewsFeedCriteria;
import com.regitiny.catiny.service.dto.NewsFeedDTO;
import com.regitiny.catiny.service.mapper.NewsFeedMapper;
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

/**
 * Integration tests for the {@link NewsFeedResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class NewsFeedResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final Long DEFAULT_PRIORITY_INDEX = 1L;
  private static final Long UPDATED_PRIORITY_INDEX = 2L;
  private static final Long SMALLER_PRIORITY_INDEX = 1L - 1L;

  private static final String ENTITY_API_URL = "/api/news-feeds";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/news-feeds";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private NewsFeedRepository newsFeedRepository;

  @Autowired
  private NewsFeedMapper newsFeedMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.NewsFeedSearchRepositoryMockConfiguration
   */
  @Autowired
  private NewsFeedSearchRepository mockNewsFeedSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restNewsFeedMockMvc;

  private NewsFeed newsFeed;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static NewsFeed createEntity(EntityManager em) {
    NewsFeed newsFeed = new NewsFeed().uuid(DEFAULT_UUID).priorityIndex(DEFAULT_PRIORITY_INDEX);
    return newsFeed;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static NewsFeed createUpdatedEntity(EntityManager em) {
    NewsFeed newsFeed = new NewsFeed().uuid(UPDATED_UUID).priorityIndex(UPDATED_PRIORITY_INDEX);
    return newsFeed;
  }

  @BeforeEach
  public void initTest() {
    newsFeed = createEntity(em);
  }

  @Test
  @Transactional
  void createNewsFeed() throws Exception {
    int databaseSizeBeforeCreate = newsFeedRepository.findAll().size();
    // Create the NewsFeed
    NewsFeedDTO newsFeedDTO = newsFeedMapper.toDto(newsFeed);
    restNewsFeedMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(newsFeedDTO)))
      .andExpect(status().isCreated());

    // Validate the NewsFeed in the database
    List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
    assertThat(newsFeedList).hasSize(databaseSizeBeforeCreate + 1);
    NewsFeed testNewsFeed = newsFeedList.get(newsFeedList.size() - 1);
    assertThat(testNewsFeed.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testNewsFeed.getPriorityIndex()).isEqualTo(DEFAULT_PRIORITY_INDEX);

    // Validate the NewsFeed in Elasticsearch
    verify(mockNewsFeedSearchRepository, times(1)).save(testNewsFeed);
  }

  @Test
  @Transactional
  void createNewsFeedWithExistingId() throws Exception {
    // Create the NewsFeed with an existing ID
    newsFeed.setId(1L);
    NewsFeedDTO newsFeedDTO = newsFeedMapper.toDto(newsFeed);

    int databaseSizeBeforeCreate = newsFeedRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restNewsFeedMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(newsFeedDTO)))
      .andExpect(status().isBadRequest());

    // Validate the NewsFeed in the database
    List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
    assertThat(newsFeedList).hasSize(databaseSizeBeforeCreate);

    // Validate the NewsFeed in Elasticsearch
    verify(mockNewsFeedSearchRepository, times(0)).save(newsFeed);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = newsFeedRepository.findAll().size();
    // set the field null
    newsFeed.setUuid(null);

    // Create the NewsFeed, which fails.
    NewsFeedDTO newsFeedDTO = newsFeedMapper.toDto(newsFeed);

    restNewsFeedMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(newsFeedDTO)))
      .andExpect(status().isBadRequest());

    List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
    assertThat(newsFeedList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllNewsFeeds() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    // Get all the newsFeedList
    restNewsFeedMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(newsFeed.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].priorityIndex").value(hasItem(DEFAULT_PRIORITY_INDEX.intValue())));
  }

  @Test
  @Transactional
  void getNewsFeed() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    // Get the newsFeed
    restNewsFeedMockMvc
      .perform(get(ENTITY_API_URL_ID, newsFeed.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(newsFeed.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.priorityIndex").value(DEFAULT_PRIORITY_INDEX.intValue()));
  }

  @Test
  @Transactional
  void getNewsFeedsByIdFiltering() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    Long id = newsFeed.getId();

    defaultNewsFeedShouldBeFound("id.equals=" + id);
    defaultNewsFeedShouldNotBeFound("id.notEquals=" + id);

    defaultNewsFeedShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultNewsFeedShouldNotBeFound("id.greaterThan=" + id);

    defaultNewsFeedShouldBeFound("id.lessThanOrEqual=" + id);
    defaultNewsFeedShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllNewsFeedsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    // Get all the newsFeedList where uuid equals to DEFAULT_UUID
    defaultNewsFeedShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the newsFeedList where uuid equals to UPDATED_UUID
    defaultNewsFeedShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllNewsFeedsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    // Get all the newsFeedList where uuid not equals to DEFAULT_UUID
    defaultNewsFeedShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the newsFeedList where uuid not equals to UPDATED_UUID
    defaultNewsFeedShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllNewsFeedsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    // Get all the newsFeedList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultNewsFeedShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the newsFeedList where uuid equals to UPDATED_UUID
    defaultNewsFeedShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllNewsFeedsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    // Get all the newsFeedList where uuid is not null
    defaultNewsFeedShouldBeFound("uuid.specified=true");

    // Get all the newsFeedList where uuid is null
    defaultNewsFeedShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllNewsFeedsByPriorityIndexIsEqualToSomething() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    // Get all the newsFeedList where priorityIndex equals to DEFAULT_PRIORITY_INDEX
    defaultNewsFeedShouldBeFound("priorityIndex.equals=" + DEFAULT_PRIORITY_INDEX);

    // Get all the newsFeedList where priorityIndex equals to UPDATED_PRIORITY_INDEX
    defaultNewsFeedShouldNotBeFound("priorityIndex.equals=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllNewsFeedsByPriorityIndexIsNotEqualToSomething() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    // Get all the newsFeedList where priorityIndex not equals to DEFAULT_PRIORITY_INDEX
    defaultNewsFeedShouldNotBeFound("priorityIndex.notEquals=" + DEFAULT_PRIORITY_INDEX);

    // Get all the newsFeedList where priorityIndex not equals to UPDATED_PRIORITY_INDEX
    defaultNewsFeedShouldBeFound("priorityIndex.notEquals=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllNewsFeedsByPriorityIndexIsInShouldWork() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    // Get all the newsFeedList where priorityIndex in DEFAULT_PRIORITY_INDEX or UPDATED_PRIORITY_INDEX
    defaultNewsFeedShouldBeFound("priorityIndex.in=" + DEFAULT_PRIORITY_INDEX + "," + UPDATED_PRIORITY_INDEX);

    // Get all the newsFeedList where priorityIndex equals to UPDATED_PRIORITY_INDEX
    defaultNewsFeedShouldNotBeFound("priorityIndex.in=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllNewsFeedsByPriorityIndexIsNullOrNotNull() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    // Get all the newsFeedList where priorityIndex is not null
    defaultNewsFeedShouldBeFound("priorityIndex.specified=true");

    // Get all the newsFeedList where priorityIndex is null
    defaultNewsFeedShouldNotBeFound("priorityIndex.specified=false");
  }

  @Test
  @Transactional
  void getAllNewsFeedsByPriorityIndexIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    // Get all the newsFeedList where priorityIndex is greater than or equal to DEFAULT_PRIORITY_INDEX
    defaultNewsFeedShouldBeFound("priorityIndex.greaterThanOrEqual=" + DEFAULT_PRIORITY_INDEX);

    // Get all the newsFeedList where priorityIndex is greater than or equal to UPDATED_PRIORITY_INDEX
    defaultNewsFeedShouldNotBeFound("priorityIndex.greaterThanOrEqual=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllNewsFeedsByPriorityIndexIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    // Get all the newsFeedList where priorityIndex is less than or equal to DEFAULT_PRIORITY_INDEX
    defaultNewsFeedShouldBeFound("priorityIndex.lessThanOrEqual=" + DEFAULT_PRIORITY_INDEX);

    // Get all the newsFeedList where priorityIndex is less than or equal to SMALLER_PRIORITY_INDEX
    defaultNewsFeedShouldNotBeFound("priorityIndex.lessThanOrEqual=" + SMALLER_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllNewsFeedsByPriorityIndexIsLessThanSomething() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    // Get all the newsFeedList where priorityIndex is less than DEFAULT_PRIORITY_INDEX
    defaultNewsFeedShouldNotBeFound("priorityIndex.lessThan=" + DEFAULT_PRIORITY_INDEX);

    // Get all the newsFeedList where priorityIndex is less than UPDATED_PRIORITY_INDEX
    defaultNewsFeedShouldBeFound("priorityIndex.lessThan=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllNewsFeedsByPriorityIndexIsGreaterThanSomething() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    // Get all the newsFeedList where priorityIndex is greater than DEFAULT_PRIORITY_INDEX
    defaultNewsFeedShouldNotBeFound("priorityIndex.greaterThan=" + DEFAULT_PRIORITY_INDEX);

    // Get all the newsFeedList where priorityIndex is greater than SMALLER_PRIORITY_INDEX
    defaultNewsFeedShouldBeFound("priorityIndex.greaterThan=" + SMALLER_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllNewsFeedsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    newsFeed.setBaseInfo(baseInfo);
    newsFeedRepository.saveAndFlush(newsFeed);
    Long baseInfoId = baseInfo.getId();

    // Get all the newsFeedList where baseInfo equals to baseInfoId
    defaultNewsFeedShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the newsFeedList where baseInfo equals to (baseInfoId + 1)
    defaultNewsFeedShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllNewsFeedsByPostIsEqualToSomething() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);
    Post post = PostResourceIT.createEntity(em);
    em.persist(post);
    em.flush();
    newsFeed.setPost(post);
    newsFeedRepository.saveAndFlush(newsFeed);
    Long postId = post.getId();

    // Get all the newsFeedList where post equals to postId
    defaultNewsFeedShouldBeFound("postId.equals=" + postId);

    // Get all the newsFeedList where post equals to (postId + 1)
    defaultNewsFeedShouldNotBeFound("postId.equals=" + (postId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultNewsFeedShouldBeFound(String filter) throws Exception {
    restNewsFeedMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(newsFeed.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].priorityIndex").value(hasItem(DEFAULT_PRIORITY_INDEX.intValue())));

    // Check, that the count call also returns 1
    restNewsFeedMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultNewsFeedShouldNotBeFound(String filter) throws Exception {
    restNewsFeedMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restNewsFeedMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingNewsFeed() throws Exception {
    // Get the newsFeed
    restNewsFeedMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewNewsFeed() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    int databaseSizeBeforeUpdate = newsFeedRepository.findAll().size();

    // Update the newsFeed
    NewsFeed updatedNewsFeed = newsFeedRepository.findById(newsFeed.getId()).get();
    // Disconnect from session so that the updates on updatedNewsFeed are not directly saved in db
    em.detach(updatedNewsFeed);
    updatedNewsFeed.uuid(UPDATED_UUID).priorityIndex(UPDATED_PRIORITY_INDEX);
    NewsFeedDTO newsFeedDTO = newsFeedMapper.toDto(updatedNewsFeed);

    restNewsFeedMockMvc
      .perform(
        put(ENTITY_API_URL_ID, newsFeedDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(newsFeedDTO))
      )
      .andExpect(status().isOk());

    // Validate the NewsFeed in the database
    List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
    assertThat(newsFeedList).hasSize(databaseSizeBeforeUpdate);
    NewsFeed testNewsFeed = newsFeedList.get(newsFeedList.size() - 1);
    assertThat(testNewsFeed.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testNewsFeed.getPriorityIndex()).isEqualTo(UPDATED_PRIORITY_INDEX);

    // Validate the NewsFeed in Elasticsearch
    verify(mockNewsFeedSearchRepository).save(testNewsFeed);
  }

  @Test
  @Transactional
  void putNonExistingNewsFeed() throws Exception {
    int databaseSizeBeforeUpdate = newsFeedRepository.findAll().size();
    newsFeed.setId(count.incrementAndGet());

    // Create the NewsFeed
    NewsFeedDTO newsFeedDTO = newsFeedMapper.toDto(newsFeed);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restNewsFeedMockMvc
      .perform(
        put(ENTITY_API_URL_ID, newsFeedDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(newsFeedDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the NewsFeed in the database
    List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
    assertThat(newsFeedList).hasSize(databaseSizeBeforeUpdate);

    // Validate the NewsFeed in Elasticsearch
    verify(mockNewsFeedSearchRepository, times(0)).save(newsFeed);
  }

  @Test
  @Transactional
  void putWithIdMismatchNewsFeed() throws Exception {
    int databaseSizeBeforeUpdate = newsFeedRepository.findAll().size();
    newsFeed.setId(count.incrementAndGet());

    // Create the NewsFeed
    NewsFeedDTO newsFeedDTO = newsFeedMapper.toDto(newsFeed);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restNewsFeedMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(newsFeedDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the NewsFeed in the database
    List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
    assertThat(newsFeedList).hasSize(databaseSizeBeforeUpdate);

    // Validate the NewsFeed in Elasticsearch
    verify(mockNewsFeedSearchRepository, times(0)).save(newsFeed);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamNewsFeed() throws Exception {
    int databaseSizeBeforeUpdate = newsFeedRepository.findAll().size();
    newsFeed.setId(count.incrementAndGet());

    // Create the NewsFeed
    NewsFeedDTO newsFeedDTO = newsFeedMapper.toDto(newsFeed);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restNewsFeedMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(newsFeedDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the NewsFeed in the database
    List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
    assertThat(newsFeedList).hasSize(databaseSizeBeforeUpdate);

    // Validate the NewsFeed in Elasticsearch
    verify(mockNewsFeedSearchRepository, times(0)).save(newsFeed);
  }

  @Test
  @Transactional
  void partialUpdateNewsFeedWithPatch() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    int databaseSizeBeforeUpdate = newsFeedRepository.findAll().size();

    // Update the newsFeed using partial update
    NewsFeed partialUpdatedNewsFeed = new NewsFeed();
    partialUpdatedNewsFeed.setId(newsFeed.getId());

    partialUpdatedNewsFeed.uuid(UPDATED_UUID);

    restNewsFeedMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedNewsFeed.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNewsFeed))
      )
      .andExpect(status().isOk());

    // Validate the NewsFeed in the database
    List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
    assertThat(newsFeedList).hasSize(databaseSizeBeforeUpdate);
    NewsFeed testNewsFeed = newsFeedList.get(newsFeedList.size() - 1);
    assertThat(testNewsFeed.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testNewsFeed.getPriorityIndex()).isEqualTo(DEFAULT_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void fullUpdateNewsFeedWithPatch() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    int databaseSizeBeforeUpdate = newsFeedRepository.findAll().size();

    // Update the newsFeed using partial update
    NewsFeed partialUpdatedNewsFeed = new NewsFeed();
    partialUpdatedNewsFeed.setId(newsFeed.getId());

    partialUpdatedNewsFeed.uuid(UPDATED_UUID).priorityIndex(UPDATED_PRIORITY_INDEX);

    restNewsFeedMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedNewsFeed.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNewsFeed))
      )
      .andExpect(status().isOk());

    // Validate the NewsFeed in the database
    List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
    assertThat(newsFeedList).hasSize(databaseSizeBeforeUpdate);
    NewsFeed testNewsFeed = newsFeedList.get(newsFeedList.size() - 1);
    assertThat(testNewsFeed.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testNewsFeed.getPriorityIndex()).isEqualTo(UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void patchNonExistingNewsFeed() throws Exception {
    int databaseSizeBeforeUpdate = newsFeedRepository.findAll().size();
    newsFeed.setId(count.incrementAndGet());

    // Create the NewsFeed
    NewsFeedDTO newsFeedDTO = newsFeedMapper.toDto(newsFeed);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restNewsFeedMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, newsFeedDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(newsFeedDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the NewsFeed in the database
    List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
    assertThat(newsFeedList).hasSize(databaseSizeBeforeUpdate);

    // Validate the NewsFeed in Elasticsearch
    verify(mockNewsFeedSearchRepository, times(0)).save(newsFeed);
  }

  @Test
  @Transactional
  void patchWithIdMismatchNewsFeed() throws Exception {
    int databaseSizeBeforeUpdate = newsFeedRepository.findAll().size();
    newsFeed.setId(count.incrementAndGet());

    // Create the NewsFeed
    NewsFeedDTO newsFeedDTO = newsFeedMapper.toDto(newsFeed);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restNewsFeedMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(newsFeedDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the NewsFeed in the database
    List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
    assertThat(newsFeedList).hasSize(databaseSizeBeforeUpdate);

    // Validate the NewsFeed in Elasticsearch
    verify(mockNewsFeedSearchRepository, times(0)).save(newsFeed);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamNewsFeed() throws Exception {
    int databaseSizeBeforeUpdate = newsFeedRepository.findAll().size();
    newsFeed.setId(count.incrementAndGet());

    // Create the NewsFeed
    NewsFeedDTO newsFeedDTO = newsFeedMapper.toDto(newsFeed);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restNewsFeedMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(newsFeedDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the NewsFeed in the database
    List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
    assertThat(newsFeedList).hasSize(databaseSizeBeforeUpdate);

    // Validate the NewsFeed in Elasticsearch
    verify(mockNewsFeedSearchRepository, times(0)).save(newsFeed);
  }

  @Test
  @Transactional
  void deleteNewsFeed() throws Exception {
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);

    int databaseSizeBeforeDelete = newsFeedRepository.findAll().size();

    // Delete the newsFeed
    restNewsFeedMockMvc
      .perform(delete(ENTITY_API_URL_ID, newsFeed.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
    assertThat(newsFeedList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the NewsFeed in Elasticsearch
    verify(mockNewsFeedSearchRepository, times(1)).deleteById(newsFeed.getId());
  }

  @Test
  @Transactional
  void searchNewsFeed() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    newsFeedRepository.saveAndFlush(newsFeed);
    when(mockNewsFeedSearchRepository.search(queryStringQuery("id:" + newsFeed.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(newsFeed), PageRequest.of(0, 1), 1));

    // Search the newsFeed
    restNewsFeedMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + newsFeed.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(newsFeed.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].priorityIndex").value(hasItem(DEFAULT_PRIORITY_INDEX.intValue())));
  }
}
