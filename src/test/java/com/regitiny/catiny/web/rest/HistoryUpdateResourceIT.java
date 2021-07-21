package com.regitiny.catiny.web.rest;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.IntegrationTest;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.HistoryUpdate;
import com.regitiny.catiny.repository.HistoryUpdateRepository;
import com.regitiny.catiny.repository.search.HistoryUpdateSearchRepository;
import com.regitiny.catiny.service.dto.HistoryUpdateDTO;
import com.regitiny.catiny.service.mapper.HistoryUpdateMapper;
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
 * Integration tests for the {@link HistoryUpdateResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class HistoryUpdateResourceIT
{

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final Integer DEFAULT_VERSION = 1;
  private static final Integer UPDATED_VERSION = 2;
  private static final Integer SMALLER_VERSION = 1 - 1;

  private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
  private static final String UPDATED_CONTENT = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/history-updates";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/history-updates";

  private static final Random random = new Random();
  private static final AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private HistoryUpdateRepository historyUpdateRepository;

  @Autowired
  private HistoryUpdateMapper historyUpdateMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.HistoryUpdateSearchRepositoryMockConfiguration
   */
  @Autowired
  private HistoryUpdateSearchRepository mockHistoryUpdateSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restHistoryUpdateMockMvc;

  private HistoryUpdate historyUpdate;

  /**
   * Create an entity for this test.
   * <p>
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static HistoryUpdate createEntity(EntityManager em)
  {
    HistoryUpdate historyUpdate = new HistoryUpdate().uuid(DEFAULT_UUID).version(DEFAULT_VERSION).content(DEFAULT_CONTENT);
    return historyUpdate;
  }

  /**
   * Create an updated entity for this test.
   * <p>
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static HistoryUpdate createUpdatedEntity(EntityManager em)
  {
    HistoryUpdate historyUpdate = new HistoryUpdate().uuid(UPDATED_UUID).version(UPDATED_VERSION).content(UPDATED_CONTENT);
    return historyUpdate;
  }

  @BeforeEach
  public void initTest()
  {
    historyUpdate = createEntity(em);
  }

  @Test
  @Transactional
  void createHistoryUpdate() throws Exception
  {
    int databaseSizeBeforeCreate = historyUpdateRepository.findAll().size();
    // Create the HistoryUpdate
    HistoryUpdateDTO historyUpdateDTO = historyUpdateMapper.toDto(historyUpdate);
    restHistoryUpdateMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historyUpdateDTO)))
      .andExpect(status().isCreated());

    // Validate the HistoryUpdate in the database
    List<HistoryUpdate> historyUpdateList = historyUpdateRepository.findAll();
    assertThat(historyUpdateList).hasSize(databaseSizeBeforeCreate + 1);
    HistoryUpdate testHistoryUpdate = historyUpdateList.get(historyUpdateList.size() - 1);
    assertThat(testHistoryUpdate.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testHistoryUpdate.getVersion()).isEqualTo(DEFAULT_VERSION);
    assertThat(testHistoryUpdate.getContent()).isEqualTo(DEFAULT_CONTENT);

    // Validate the HistoryUpdate in Elasticsearch
    verify(mockHistoryUpdateSearchRepository, times(1)).save(testHistoryUpdate);
  }

  @Test
  @Transactional
  void createHistoryUpdateWithExistingId() throws Exception
  {
    // Create the HistoryUpdate with an existing ID
    historyUpdate.setId(1L);
    HistoryUpdateDTO historyUpdateDTO = historyUpdateMapper.toDto(historyUpdate);

    int databaseSizeBeforeCreate = historyUpdateRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restHistoryUpdateMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historyUpdateDTO)))
      .andExpect(status().isBadRequest());

    // Validate the HistoryUpdate in the database
    List<HistoryUpdate> historyUpdateList = historyUpdateRepository.findAll();
    assertThat(historyUpdateList).hasSize(databaseSizeBeforeCreate);

    // Validate the HistoryUpdate in Elasticsearch
    verify(mockHistoryUpdateSearchRepository, times(0)).save(historyUpdate);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception
  {
    int databaseSizeBeforeTest = historyUpdateRepository.findAll().size();
    // set the field null
    historyUpdate.setUuid(null);

    // Create the HistoryUpdate, which fails.
    HistoryUpdateDTO historyUpdateDTO = historyUpdateMapper.toDto(historyUpdate);

    restHistoryUpdateMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historyUpdateDTO)))
      .andExpect(status().isBadRequest());

    List<HistoryUpdate> historyUpdateList = historyUpdateRepository.findAll();
    assertThat(historyUpdateList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllHistoryUpdates() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    // Get all the historyUpdateList
    restHistoryUpdateMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(historyUpdate.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)));
  }

  @Test
  @Transactional
  void getHistoryUpdate() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    // Get the historyUpdate
    restHistoryUpdateMockMvc
      .perform(get(ENTITY_API_URL_ID, historyUpdate.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(historyUpdate.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
      .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT));
  }

  @Test
  @Transactional
  void getHistoryUpdatesByIdFiltering() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    Long id = historyUpdate.getId();

    defaultHistoryUpdateShouldBeFound("id.equals=" + id);
    defaultHistoryUpdateShouldNotBeFound("id.notEquals=" + id);

    defaultHistoryUpdateShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultHistoryUpdateShouldNotBeFound("id.greaterThan=" + id);

    defaultHistoryUpdateShouldBeFound("id.lessThanOrEqual=" + id);
    defaultHistoryUpdateShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllHistoryUpdatesByUuidIsEqualToSomething() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    // Get all the historyUpdateList where uuid equals to DEFAULT_UUID
    defaultHistoryUpdateShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the historyUpdateList where uuid equals to UPDATED_UUID
    defaultHistoryUpdateShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllHistoryUpdatesByUuidIsNotEqualToSomething() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    // Get all the historyUpdateList where uuid not equals to DEFAULT_UUID
    defaultHistoryUpdateShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the historyUpdateList where uuid not equals to UPDATED_UUID
    defaultHistoryUpdateShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllHistoryUpdatesByUuidIsInShouldWork() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    // Get all the historyUpdateList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultHistoryUpdateShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the historyUpdateList where uuid equals to UPDATED_UUID
    defaultHistoryUpdateShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllHistoryUpdatesByUuidIsNullOrNotNull() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    // Get all the historyUpdateList where uuid is not null
    defaultHistoryUpdateShouldBeFound("uuid.specified=true");

    // Get all the historyUpdateList where uuid is null
    defaultHistoryUpdateShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllHistoryUpdatesByVersionIsEqualToSomething() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    // Get all the historyUpdateList where version equals to DEFAULT_VERSION
    defaultHistoryUpdateShouldBeFound("version.equals=" + DEFAULT_VERSION);

    // Get all the historyUpdateList where version equals to UPDATED_VERSION
    defaultHistoryUpdateShouldNotBeFound("version.equals=" + UPDATED_VERSION);
  }

  @Test
  @Transactional
  void getAllHistoryUpdatesByVersionIsNotEqualToSomething() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    // Get all the historyUpdateList where version not equals to DEFAULT_VERSION
    defaultHistoryUpdateShouldNotBeFound("version.notEquals=" + DEFAULT_VERSION);

    // Get all the historyUpdateList where version not equals to UPDATED_VERSION
    defaultHistoryUpdateShouldBeFound("version.notEquals=" + UPDATED_VERSION);
  }

  @Test
  @Transactional
  void getAllHistoryUpdatesByVersionIsInShouldWork() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    // Get all the historyUpdateList where version in DEFAULT_VERSION or UPDATED_VERSION
    defaultHistoryUpdateShouldBeFound("version.in=" + DEFAULT_VERSION + "," + UPDATED_VERSION);

    // Get all the historyUpdateList where version equals to UPDATED_VERSION
    defaultHistoryUpdateShouldNotBeFound("version.in=" + UPDATED_VERSION);
  }

  @Test
  @Transactional
  void getAllHistoryUpdatesByVersionIsNullOrNotNull() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    // Get all the historyUpdateList where version is not null
    defaultHistoryUpdateShouldBeFound("version.specified=true");

    // Get all the historyUpdateList where version is null
    defaultHistoryUpdateShouldNotBeFound("version.specified=false");
  }

  @Test
  @Transactional
  void getAllHistoryUpdatesByVersionIsGreaterThanOrEqualToSomething() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    // Get all the historyUpdateList where version is greater than or equal to DEFAULT_VERSION
    defaultHistoryUpdateShouldBeFound("version.greaterThanOrEqual=" + DEFAULT_VERSION);

    // Get all the historyUpdateList where version is greater than or equal to UPDATED_VERSION
    defaultHistoryUpdateShouldNotBeFound("version.greaterThanOrEqual=" + UPDATED_VERSION);
  }

  @Test
  @Transactional
  void getAllHistoryUpdatesByVersionIsLessThanOrEqualToSomething() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    // Get all the historyUpdateList where version is less than or equal to DEFAULT_VERSION
    defaultHistoryUpdateShouldBeFound("version.lessThanOrEqual=" + DEFAULT_VERSION);

    // Get all the historyUpdateList where version is less than or equal to SMALLER_VERSION
    defaultHistoryUpdateShouldNotBeFound("version.lessThanOrEqual=" + SMALLER_VERSION);
  }

  @Test
  @Transactional
  void getAllHistoryUpdatesByVersionIsLessThanSomething() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    // Get all the historyUpdateList where version is less than DEFAULT_VERSION
    defaultHistoryUpdateShouldNotBeFound("version.lessThan=" + DEFAULT_VERSION);

    // Get all the historyUpdateList where version is less than UPDATED_VERSION
    defaultHistoryUpdateShouldBeFound("version.lessThan=" + UPDATED_VERSION);
  }

  @Test
  @Transactional
  void getAllHistoryUpdatesByVersionIsGreaterThanSomething() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    // Get all the historyUpdateList where version is greater than DEFAULT_VERSION
    defaultHistoryUpdateShouldNotBeFound("version.greaterThan=" + DEFAULT_VERSION);

    // Get all the historyUpdateList where version is greater than SMALLER_VERSION
    defaultHistoryUpdateShouldBeFound("version.greaterThan=" + SMALLER_VERSION);
  }

  @Test
  @Transactional
  void getAllHistoryUpdatesByBaseInfoIsEqualToSomething() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    historyUpdate.setBaseInfo(baseInfo);
    historyUpdateRepository.saveAndFlush(historyUpdate);
    Long baseInfoId = baseInfo.getId();

    // Get all the historyUpdateList where baseInfo equals to baseInfoId
    defaultHistoryUpdateShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the historyUpdateList where baseInfo equals to (baseInfoId + 1)
    defaultHistoryUpdateShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultHistoryUpdateShouldBeFound(String filter) throws Exception
  {
    restHistoryUpdateMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(historyUpdate.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)));

    // Check, that the count call also returns 1
    restHistoryUpdateMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultHistoryUpdateShouldNotBeFound(String filter) throws Exception
  {
    restHistoryUpdateMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restHistoryUpdateMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingHistoryUpdate() throws Exception
  {
    // Get the historyUpdate
    restHistoryUpdateMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewHistoryUpdate() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    int databaseSizeBeforeUpdate = historyUpdateRepository.findAll().size();

    // Update the historyUpdate
    HistoryUpdate updatedHistoryUpdate = historyUpdateRepository.findById(historyUpdate.getId()).get();
    // Disconnect from session so that the updates on updatedHistoryUpdate are not directly saved in db
    em.detach(updatedHistoryUpdate);
    updatedHistoryUpdate.uuid(UPDATED_UUID).version(UPDATED_VERSION).content(UPDATED_CONTENT);
    HistoryUpdateDTO historyUpdateDTO = historyUpdateMapper.toDto(updatedHistoryUpdate);

    restHistoryUpdateMockMvc
      .perform(
        put(ENTITY_API_URL_ID, historyUpdateDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(historyUpdateDTO))
      )
      .andExpect(status().isOk());

    // Validate the HistoryUpdate in the database
    List<HistoryUpdate> historyUpdateList = historyUpdateRepository.findAll();
    assertThat(historyUpdateList).hasSize(databaseSizeBeforeUpdate);
    HistoryUpdate testHistoryUpdate = historyUpdateList.get(historyUpdateList.size() - 1);
    assertThat(testHistoryUpdate.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testHistoryUpdate.getVersion()).isEqualTo(UPDATED_VERSION);
    assertThat(testHistoryUpdate.getContent()).isEqualTo(UPDATED_CONTENT);

    // Validate the HistoryUpdate in Elasticsearch
    verify(mockHistoryUpdateSearchRepository).save(testHistoryUpdate);
  }

  @Test
  @Transactional
  void putNonExistingHistoryUpdate() throws Exception
  {
    int databaseSizeBeforeUpdate = historyUpdateRepository.findAll().size();
    historyUpdate.setId(count.incrementAndGet());

    // Create the HistoryUpdate
    HistoryUpdateDTO historyUpdateDTO = historyUpdateMapper.toDto(historyUpdate);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restHistoryUpdateMockMvc
      .perform(
        put(ENTITY_API_URL_ID, historyUpdateDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(historyUpdateDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the HistoryUpdate in the database
    List<HistoryUpdate> historyUpdateList = historyUpdateRepository.findAll();
    assertThat(historyUpdateList).hasSize(databaseSizeBeforeUpdate);

    // Validate the HistoryUpdate in Elasticsearch
    verify(mockHistoryUpdateSearchRepository, times(0)).save(historyUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchHistoryUpdate() throws Exception
  {
    int databaseSizeBeforeUpdate = historyUpdateRepository.findAll().size();
    historyUpdate.setId(count.incrementAndGet());

    // Create the HistoryUpdate
    HistoryUpdateDTO historyUpdateDTO = historyUpdateMapper.toDto(historyUpdate);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restHistoryUpdateMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(historyUpdateDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the HistoryUpdate in the database
    List<HistoryUpdate> historyUpdateList = historyUpdateRepository.findAll();
    assertThat(historyUpdateList).hasSize(databaseSizeBeforeUpdate);

    // Validate the HistoryUpdate in Elasticsearch
    verify(mockHistoryUpdateSearchRepository, times(0)).save(historyUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamHistoryUpdate() throws Exception
  {
    int databaseSizeBeforeUpdate = historyUpdateRepository.findAll().size();
    historyUpdate.setId(count.incrementAndGet());

    // Create the HistoryUpdate
    HistoryUpdateDTO historyUpdateDTO = historyUpdateMapper.toDto(historyUpdate);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restHistoryUpdateMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historyUpdateDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the HistoryUpdate in the database
    List<HistoryUpdate> historyUpdateList = historyUpdateRepository.findAll();
    assertThat(historyUpdateList).hasSize(databaseSizeBeforeUpdate);

    // Validate the HistoryUpdate in Elasticsearch
    verify(mockHistoryUpdateSearchRepository, times(0)).save(historyUpdate);
  }

  @Test
  @Transactional
  void partialUpdateHistoryUpdateWithPatch() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    int databaseSizeBeforeUpdate = historyUpdateRepository.findAll().size();

    // Update the historyUpdate using partial update
    HistoryUpdate partialUpdatedHistoryUpdate = new HistoryUpdate();
    partialUpdatedHistoryUpdate.setId(historyUpdate.getId());

    partialUpdatedHistoryUpdate.uuid(UPDATED_UUID).version(UPDATED_VERSION);

    restHistoryUpdateMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedHistoryUpdate.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHistoryUpdate))
      )
      .andExpect(status().isOk());

    // Validate the HistoryUpdate in the database
    List<HistoryUpdate> historyUpdateList = historyUpdateRepository.findAll();
    assertThat(historyUpdateList).hasSize(databaseSizeBeforeUpdate);
    HistoryUpdate testHistoryUpdate = historyUpdateList.get(historyUpdateList.size() - 1);
    assertThat(testHistoryUpdate.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testHistoryUpdate.getVersion()).isEqualTo(UPDATED_VERSION);
    assertThat(testHistoryUpdate.getContent()).isEqualTo(DEFAULT_CONTENT);
  }

  @Test
  @Transactional
  void fullUpdateHistoryUpdateWithPatch() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    int databaseSizeBeforeUpdate = historyUpdateRepository.findAll().size();

    // Update the historyUpdate using partial update
    HistoryUpdate partialUpdatedHistoryUpdate = new HistoryUpdate();
    partialUpdatedHistoryUpdate.setId(historyUpdate.getId());

    partialUpdatedHistoryUpdate.uuid(UPDATED_UUID).version(UPDATED_VERSION).content(UPDATED_CONTENT);

    restHistoryUpdateMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedHistoryUpdate.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHistoryUpdate))
      )
      .andExpect(status().isOk());

    // Validate the HistoryUpdate in the database
    List<HistoryUpdate> historyUpdateList = historyUpdateRepository.findAll();
    assertThat(historyUpdateList).hasSize(databaseSizeBeforeUpdate);
    HistoryUpdate testHistoryUpdate = historyUpdateList.get(historyUpdateList.size() - 1);
    assertThat(testHistoryUpdate.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testHistoryUpdate.getVersion()).isEqualTo(UPDATED_VERSION);
    assertThat(testHistoryUpdate.getContent()).isEqualTo(UPDATED_CONTENT);
  }

  @Test
  @Transactional
  void patchNonExistingHistoryUpdate() throws Exception
  {
    int databaseSizeBeforeUpdate = historyUpdateRepository.findAll().size();
    historyUpdate.setId(count.incrementAndGet());

    // Create the HistoryUpdate
    HistoryUpdateDTO historyUpdateDTO = historyUpdateMapper.toDto(historyUpdate);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restHistoryUpdateMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, historyUpdateDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(historyUpdateDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the HistoryUpdate in the database
    List<HistoryUpdate> historyUpdateList = historyUpdateRepository.findAll();
    assertThat(historyUpdateList).hasSize(databaseSizeBeforeUpdate);

    // Validate the HistoryUpdate in Elasticsearch
    verify(mockHistoryUpdateSearchRepository, times(0)).save(historyUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchHistoryUpdate() throws Exception
  {
    int databaseSizeBeforeUpdate = historyUpdateRepository.findAll().size();
    historyUpdate.setId(count.incrementAndGet());

    // Create the HistoryUpdate
    HistoryUpdateDTO historyUpdateDTO = historyUpdateMapper.toDto(historyUpdate);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restHistoryUpdateMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(historyUpdateDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the HistoryUpdate in the database
    List<HistoryUpdate> historyUpdateList = historyUpdateRepository.findAll();
    assertThat(historyUpdateList).hasSize(databaseSizeBeforeUpdate);

    // Validate the HistoryUpdate in Elasticsearch
    verify(mockHistoryUpdateSearchRepository, times(0)).save(historyUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamHistoryUpdate() throws Exception
  {
    int databaseSizeBeforeUpdate = historyUpdateRepository.findAll().size();
    historyUpdate.setId(count.incrementAndGet());

    // Create the HistoryUpdate
    HistoryUpdateDTO historyUpdateDTO = historyUpdateMapper.toDto(historyUpdate);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restHistoryUpdateMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(historyUpdateDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the HistoryUpdate in the database
    List<HistoryUpdate> historyUpdateList = historyUpdateRepository.findAll();
    assertThat(historyUpdateList).hasSize(databaseSizeBeforeUpdate);

    // Validate the HistoryUpdate in Elasticsearch
    verify(mockHistoryUpdateSearchRepository, times(0)).save(historyUpdate);
  }

  @Test
  @Transactional
  void deleteHistoryUpdate() throws Exception
  {
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);

    int databaseSizeBeforeDelete = historyUpdateRepository.findAll().size();

    // Delete the historyUpdate
    restHistoryUpdateMockMvc
      .perform(delete(ENTITY_API_URL_ID, historyUpdate.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<HistoryUpdate> historyUpdateList = historyUpdateRepository.findAll();
    assertThat(historyUpdateList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the HistoryUpdate in Elasticsearch
    verify(mockHistoryUpdateSearchRepository, times(1)).deleteById(historyUpdate.getId());
  }

  @Test
  @Transactional
  void searchHistoryUpdate() throws Exception
  {
    // Configure the mock search repository
    // Initialize the database
    historyUpdateRepository.saveAndFlush(historyUpdate);
    when(mockHistoryUpdateSearchRepository.search(queryStringQuery("id:" + historyUpdate.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(historyUpdate), PageRequest.of(0, 1), 1));

    // Search the historyUpdate
    restHistoryUpdateMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + historyUpdate.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(historyUpdate.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)));
  }
}
