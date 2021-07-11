package com.regitiny.catiny.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.IntegrationTest;
import com.regitiny.catiny.domain.AccountStatus;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.DeviceStatus;
import com.regitiny.catiny.domain.enumeration.StatusName;
import com.regitiny.catiny.repository.AccountStatusRepository;
import com.regitiny.catiny.repository.search.AccountStatusSearchRepository;
import com.regitiny.catiny.service.criteria.AccountStatusCriteria;
import com.regitiny.catiny.service.dto.AccountStatusDTO;
import com.regitiny.catiny.service.mapper.AccountStatusMapper;
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

/**
 * Integration tests for the {@link AccountStatusResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class AccountStatusResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final StatusName DEFAULT_ACCOUNT_STATUS = StatusName.ONLINE;
  private static final StatusName UPDATED_ACCOUNT_STATUS = StatusName.OFFLINE;

  private static final Instant DEFAULT_LAST_VISITED = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_LAST_VISITED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

  private static final String DEFAULT_STATUS_COMMENT = "AAAAAAAAAA";
  private static final String UPDATED_STATUS_COMMENT = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/account-statuses";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/account-statuses";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private AccountStatusRepository accountStatusRepository;

  @Autowired
  private AccountStatusMapper accountStatusMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.AccountStatusSearchRepositoryMockConfiguration
   */
  @Autowired
  private AccountStatusSearchRepository mockAccountStatusSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restAccountStatusMockMvc;

  private AccountStatus accountStatus;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static AccountStatus createEntity(EntityManager em) {
    AccountStatus accountStatus = new AccountStatus()
      .uuid(DEFAULT_UUID)
      .accountStatus(DEFAULT_ACCOUNT_STATUS)
      .lastVisited(DEFAULT_LAST_VISITED)
      .statusComment(DEFAULT_STATUS_COMMENT);
    return accountStatus;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static AccountStatus createUpdatedEntity(EntityManager em) {
    AccountStatus accountStatus = new AccountStatus()
      .uuid(UPDATED_UUID)
      .accountStatus(UPDATED_ACCOUNT_STATUS)
      .lastVisited(UPDATED_LAST_VISITED)
      .statusComment(UPDATED_STATUS_COMMENT);
    return accountStatus;
  }

  @BeforeEach
  public void initTest() {
    accountStatus = createEntity(em);
  }

  @Test
  @Transactional
  void createAccountStatus() throws Exception {
    int databaseSizeBeforeCreate = accountStatusRepository.findAll().size();
    // Create the AccountStatus
    AccountStatusDTO accountStatusDTO = accountStatusMapper.toDto(accountStatus);
    restAccountStatusMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(accountStatusDTO)))
      .andExpect(status().isCreated());

    // Validate the AccountStatus in the database
    List<AccountStatus> accountStatusList = accountStatusRepository.findAll();
    assertThat(accountStatusList).hasSize(databaseSizeBeforeCreate + 1);
    AccountStatus testAccountStatus = accountStatusList.get(accountStatusList.size() - 1);
    assertThat(testAccountStatus.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testAccountStatus.getAccountStatus()).isEqualTo(DEFAULT_ACCOUNT_STATUS);
    assertThat(testAccountStatus.getLastVisited()).isEqualTo(DEFAULT_LAST_VISITED);
    assertThat(testAccountStatus.getStatusComment()).isEqualTo(DEFAULT_STATUS_COMMENT);

    // Validate the AccountStatus in Elasticsearch
    verify(mockAccountStatusSearchRepository, times(1)).save(testAccountStatus);
  }

  @Test
  @Transactional
  void createAccountStatusWithExistingId() throws Exception {
    // Create the AccountStatus with an existing ID
    accountStatus.setId(1L);
    AccountStatusDTO accountStatusDTO = accountStatusMapper.toDto(accountStatus);

    int databaseSizeBeforeCreate = accountStatusRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restAccountStatusMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(accountStatusDTO)))
      .andExpect(status().isBadRequest());

    // Validate the AccountStatus in the database
    List<AccountStatus> accountStatusList = accountStatusRepository.findAll();
    assertThat(accountStatusList).hasSize(databaseSizeBeforeCreate);

    // Validate the AccountStatus in Elasticsearch
    verify(mockAccountStatusSearchRepository, times(0)).save(accountStatus);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = accountStatusRepository.findAll().size();
    // set the field null
    accountStatus.setUuid(null);

    // Create the AccountStatus, which fails.
    AccountStatusDTO accountStatusDTO = accountStatusMapper.toDto(accountStatus);

    restAccountStatusMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(accountStatusDTO)))
      .andExpect(status().isBadRequest());

    List<AccountStatus> accountStatusList = accountStatusRepository.findAll();
    assertThat(accountStatusList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllAccountStatuses() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList
    restAccountStatusMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(accountStatus.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].accountStatus").value(hasItem(DEFAULT_ACCOUNT_STATUS.toString())))
      .andExpect(jsonPath("$.[*].lastVisited").value(hasItem(DEFAULT_LAST_VISITED.toString())))
      .andExpect(jsonPath("$.[*].statusComment").value(hasItem(DEFAULT_STATUS_COMMENT)));
  }

  @Test
  @Transactional
  void getAccountStatus() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get the accountStatus
    restAccountStatusMockMvc
      .perform(get(ENTITY_API_URL_ID, accountStatus.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(accountStatus.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.accountStatus").value(DEFAULT_ACCOUNT_STATUS.toString()))
      .andExpect(jsonPath("$.lastVisited").value(DEFAULT_LAST_VISITED.toString()))
      .andExpect(jsonPath("$.statusComment").value(DEFAULT_STATUS_COMMENT));
  }

  @Test
  @Transactional
  void getAccountStatusesByIdFiltering() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    Long id = accountStatus.getId();

    defaultAccountStatusShouldBeFound("id.equals=" + id);
    defaultAccountStatusShouldNotBeFound("id.notEquals=" + id);

    defaultAccountStatusShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultAccountStatusShouldNotBeFound("id.greaterThan=" + id);

    defaultAccountStatusShouldBeFound("id.lessThanOrEqual=" + id);
    defaultAccountStatusShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where uuid equals to DEFAULT_UUID
    defaultAccountStatusShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the accountStatusList where uuid equals to UPDATED_UUID
    defaultAccountStatusShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where uuid not equals to DEFAULT_UUID
    defaultAccountStatusShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the accountStatusList where uuid not equals to UPDATED_UUID
    defaultAccountStatusShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultAccountStatusShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the accountStatusList where uuid equals to UPDATED_UUID
    defaultAccountStatusShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where uuid is not null
    defaultAccountStatusShouldBeFound("uuid.specified=true");

    // Get all the accountStatusList where uuid is null
    defaultAccountStatusShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllAccountStatusesByAccountStatusIsEqualToSomething() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where accountStatus equals to DEFAULT_ACCOUNT_STATUS
    defaultAccountStatusShouldBeFound("accountStatus.equals=" + DEFAULT_ACCOUNT_STATUS);

    // Get all the accountStatusList where accountStatus equals to UPDATED_ACCOUNT_STATUS
    defaultAccountStatusShouldNotBeFound("accountStatus.equals=" + UPDATED_ACCOUNT_STATUS);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByAccountStatusIsNotEqualToSomething() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where accountStatus not equals to DEFAULT_ACCOUNT_STATUS
    defaultAccountStatusShouldNotBeFound("accountStatus.notEquals=" + DEFAULT_ACCOUNT_STATUS);

    // Get all the accountStatusList where accountStatus not equals to UPDATED_ACCOUNT_STATUS
    defaultAccountStatusShouldBeFound("accountStatus.notEquals=" + UPDATED_ACCOUNT_STATUS);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByAccountStatusIsInShouldWork() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where accountStatus in DEFAULT_ACCOUNT_STATUS or UPDATED_ACCOUNT_STATUS
    defaultAccountStatusShouldBeFound("accountStatus.in=" + DEFAULT_ACCOUNT_STATUS + "," + UPDATED_ACCOUNT_STATUS);

    // Get all the accountStatusList where accountStatus equals to UPDATED_ACCOUNT_STATUS
    defaultAccountStatusShouldNotBeFound("accountStatus.in=" + UPDATED_ACCOUNT_STATUS);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByAccountStatusIsNullOrNotNull() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where accountStatus is not null
    defaultAccountStatusShouldBeFound("accountStatus.specified=true");

    // Get all the accountStatusList where accountStatus is null
    defaultAccountStatusShouldNotBeFound("accountStatus.specified=false");
  }

  @Test
  @Transactional
  void getAllAccountStatusesByLastVisitedIsEqualToSomething() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where lastVisited equals to DEFAULT_LAST_VISITED
    defaultAccountStatusShouldBeFound("lastVisited.equals=" + DEFAULT_LAST_VISITED);

    // Get all the accountStatusList where lastVisited equals to UPDATED_LAST_VISITED
    defaultAccountStatusShouldNotBeFound("lastVisited.equals=" + UPDATED_LAST_VISITED);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByLastVisitedIsNotEqualToSomething() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where lastVisited not equals to DEFAULT_LAST_VISITED
    defaultAccountStatusShouldNotBeFound("lastVisited.notEquals=" + DEFAULT_LAST_VISITED);

    // Get all the accountStatusList where lastVisited not equals to UPDATED_LAST_VISITED
    defaultAccountStatusShouldBeFound("lastVisited.notEquals=" + UPDATED_LAST_VISITED);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByLastVisitedIsInShouldWork() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where lastVisited in DEFAULT_LAST_VISITED or UPDATED_LAST_VISITED
    defaultAccountStatusShouldBeFound("lastVisited.in=" + DEFAULT_LAST_VISITED + "," + UPDATED_LAST_VISITED);

    // Get all the accountStatusList where lastVisited equals to UPDATED_LAST_VISITED
    defaultAccountStatusShouldNotBeFound("lastVisited.in=" + UPDATED_LAST_VISITED);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByLastVisitedIsNullOrNotNull() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where lastVisited is not null
    defaultAccountStatusShouldBeFound("lastVisited.specified=true");

    // Get all the accountStatusList where lastVisited is null
    defaultAccountStatusShouldNotBeFound("lastVisited.specified=false");
  }

  @Test
  @Transactional
  void getAllAccountStatusesByStatusCommentIsEqualToSomething() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where statusComment equals to DEFAULT_STATUS_COMMENT
    defaultAccountStatusShouldBeFound("statusComment.equals=" + DEFAULT_STATUS_COMMENT);

    // Get all the accountStatusList where statusComment equals to UPDATED_STATUS_COMMENT
    defaultAccountStatusShouldNotBeFound("statusComment.equals=" + UPDATED_STATUS_COMMENT);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByStatusCommentIsNotEqualToSomething() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where statusComment not equals to DEFAULT_STATUS_COMMENT
    defaultAccountStatusShouldNotBeFound("statusComment.notEquals=" + DEFAULT_STATUS_COMMENT);

    // Get all the accountStatusList where statusComment not equals to UPDATED_STATUS_COMMENT
    defaultAccountStatusShouldBeFound("statusComment.notEquals=" + UPDATED_STATUS_COMMENT);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByStatusCommentIsInShouldWork() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where statusComment in DEFAULT_STATUS_COMMENT or UPDATED_STATUS_COMMENT
    defaultAccountStatusShouldBeFound("statusComment.in=" + DEFAULT_STATUS_COMMENT + "," + UPDATED_STATUS_COMMENT);

    // Get all the accountStatusList where statusComment equals to UPDATED_STATUS_COMMENT
    defaultAccountStatusShouldNotBeFound("statusComment.in=" + UPDATED_STATUS_COMMENT);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByStatusCommentIsNullOrNotNull() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where statusComment is not null
    defaultAccountStatusShouldBeFound("statusComment.specified=true");

    // Get all the accountStatusList where statusComment is null
    defaultAccountStatusShouldNotBeFound("statusComment.specified=false");
  }

  @Test
  @Transactional
  void getAllAccountStatusesByStatusCommentContainsSomething() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where statusComment contains DEFAULT_STATUS_COMMENT
    defaultAccountStatusShouldBeFound("statusComment.contains=" + DEFAULT_STATUS_COMMENT);

    // Get all the accountStatusList where statusComment contains UPDATED_STATUS_COMMENT
    defaultAccountStatusShouldNotBeFound("statusComment.contains=" + UPDATED_STATUS_COMMENT);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByStatusCommentNotContainsSomething() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    // Get all the accountStatusList where statusComment does not contain DEFAULT_STATUS_COMMENT
    defaultAccountStatusShouldNotBeFound("statusComment.doesNotContain=" + DEFAULT_STATUS_COMMENT);

    // Get all the accountStatusList where statusComment does not contain UPDATED_STATUS_COMMENT
    defaultAccountStatusShouldBeFound("statusComment.doesNotContain=" + UPDATED_STATUS_COMMENT);
  }

  @Test
  @Transactional
  void getAllAccountStatusesByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    accountStatus.setBaseInfo(baseInfo);
    accountStatusRepository.saveAndFlush(accountStatus);
    Long baseInfoId = baseInfo.getId();

    // Get all the accountStatusList where baseInfo equals to baseInfoId
    defaultAccountStatusShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the accountStatusList where baseInfo equals to (baseInfoId + 1)
    defaultAccountStatusShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllAccountStatusesByDeviceStatusIsEqualToSomething() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);
    DeviceStatus deviceStatus = DeviceStatusResourceIT.createEntity(em);
    em.persist(deviceStatus);
    em.flush();
    accountStatus.addDeviceStatus(deviceStatus);
    accountStatusRepository.saveAndFlush(accountStatus);
    Long deviceStatusId = deviceStatus.getId();

    // Get all the accountStatusList where deviceStatus equals to deviceStatusId
    defaultAccountStatusShouldBeFound("deviceStatusId.equals=" + deviceStatusId);

    // Get all the accountStatusList where deviceStatus equals to (deviceStatusId + 1)
    defaultAccountStatusShouldNotBeFound("deviceStatusId.equals=" + (deviceStatusId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultAccountStatusShouldBeFound(String filter) throws Exception {
    restAccountStatusMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(accountStatus.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].accountStatus").value(hasItem(DEFAULT_ACCOUNT_STATUS.toString())))
      .andExpect(jsonPath("$.[*].lastVisited").value(hasItem(DEFAULT_LAST_VISITED.toString())))
      .andExpect(jsonPath("$.[*].statusComment").value(hasItem(DEFAULT_STATUS_COMMENT)));

    // Check, that the count call also returns 1
    restAccountStatusMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultAccountStatusShouldNotBeFound(String filter) throws Exception {
    restAccountStatusMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restAccountStatusMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingAccountStatus() throws Exception {
    // Get the accountStatus
    restAccountStatusMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewAccountStatus() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    int databaseSizeBeforeUpdate = accountStatusRepository.findAll().size();

    // Update the accountStatus
    AccountStatus updatedAccountStatus = accountStatusRepository.findById(accountStatus.getId()).get();
    // Disconnect from session so that the updates on updatedAccountStatus are not directly saved in db
    em.detach(updatedAccountStatus);
    updatedAccountStatus
      .uuid(UPDATED_UUID)
      .accountStatus(UPDATED_ACCOUNT_STATUS)
      .lastVisited(UPDATED_LAST_VISITED)
      .statusComment(UPDATED_STATUS_COMMENT);
    AccountStatusDTO accountStatusDTO = accountStatusMapper.toDto(updatedAccountStatus);

    restAccountStatusMockMvc
      .perform(
        put(ENTITY_API_URL_ID, accountStatusDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(accountStatusDTO))
      )
      .andExpect(status().isOk());

    // Validate the AccountStatus in the database
    List<AccountStatus> accountStatusList = accountStatusRepository.findAll();
    assertThat(accountStatusList).hasSize(databaseSizeBeforeUpdate);
    AccountStatus testAccountStatus = accountStatusList.get(accountStatusList.size() - 1);
    assertThat(testAccountStatus.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testAccountStatus.getAccountStatus()).isEqualTo(UPDATED_ACCOUNT_STATUS);
    assertThat(testAccountStatus.getLastVisited()).isEqualTo(UPDATED_LAST_VISITED);
    assertThat(testAccountStatus.getStatusComment()).isEqualTo(UPDATED_STATUS_COMMENT);

    // Validate the AccountStatus in Elasticsearch
    verify(mockAccountStatusSearchRepository).save(testAccountStatus);
  }

  @Test
  @Transactional
  void putNonExistingAccountStatus() throws Exception {
    int databaseSizeBeforeUpdate = accountStatusRepository.findAll().size();
    accountStatus.setId(count.incrementAndGet());

    // Create the AccountStatus
    AccountStatusDTO accountStatusDTO = accountStatusMapper.toDto(accountStatus);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restAccountStatusMockMvc
      .perform(
        put(ENTITY_API_URL_ID, accountStatusDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(accountStatusDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the AccountStatus in the database
    List<AccountStatus> accountStatusList = accountStatusRepository.findAll();
    assertThat(accountStatusList).hasSize(databaseSizeBeforeUpdate);

    // Validate the AccountStatus in Elasticsearch
    verify(mockAccountStatusSearchRepository, times(0)).save(accountStatus);
  }

  @Test
  @Transactional
  void putWithIdMismatchAccountStatus() throws Exception {
    int databaseSizeBeforeUpdate = accountStatusRepository.findAll().size();
    accountStatus.setId(count.incrementAndGet());

    // Create the AccountStatus
    AccountStatusDTO accountStatusDTO = accountStatusMapper.toDto(accountStatus);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAccountStatusMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(accountStatusDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the AccountStatus in the database
    List<AccountStatus> accountStatusList = accountStatusRepository.findAll();
    assertThat(accountStatusList).hasSize(databaseSizeBeforeUpdate);

    // Validate the AccountStatus in Elasticsearch
    verify(mockAccountStatusSearchRepository, times(0)).save(accountStatus);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamAccountStatus() throws Exception {
    int databaseSizeBeforeUpdate = accountStatusRepository.findAll().size();
    accountStatus.setId(count.incrementAndGet());

    // Create the AccountStatus
    AccountStatusDTO accountStatusDTO = accountStatusMapper.toDto(accountStatus);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAccountStatusMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(accountStatusDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the AccountStatus in the database
    List<AccountStatus> accountStatusList = accountStatusRepository.findAll();
    assertThat(accountStatusList).hasSize(databaseSizeBeforeUpdate);

    // Validate the AccountStatus in Elasticsearch
    verify(mockAccountStatusSearchRepository, times(0)).save(accountStatus);
  }

  @Test
  @Transactional
  void partialUpdateAccountStatusWithPatch() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    int databaseSizeBeforeUpdate = accountStatusRepository.findAll().size();

    // Update the accountStatus using partial update
    AccountStatus partialUpdatedAccountStatus = new AccountStatus();
    partialUpdatedAccountStatus.setId(accountStatus.getId());

    partialUpdatedAccountStatus.uuid(UPDATED_UUID).statusComment(UPDATED_STATUS_COMMENT);

    restAccountStatusMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedAccountStatus.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAccountStatus))
      )
      .andExpect(status().isOk());

    // Validate the AccountStatus in the database
    List<AccountStatus> accountStatusList = accountStatusRepository.findAll();
    assertThat(accountStatusList).hasSize(databaseSizeBeforeUpdate);
    AccountStatus testAccountStatus = accountStatusList.get(accountStatusList.size() - 1);
    assertThat(testAccountStatus.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testAccountStatus.getAccountStatus()).isEqualTo(DEFAULT_ACCOUNT_STATUS);
    assertThat(testAccountStatus.getLastVisited()).isEqualTo(DEFAULT_LAST_VISITED);
    assertThat(testAccountStatus.getStatusComment()).isEqualTo(UPDATED_STATUS_COMMENT);
  }

  @Test
  @Transactional
  void fullUpdateAccountStatusWithPatch() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    int databaseSizeBeforeUpdate = accountStatusRepository.findAll().size();

    // Update the accountStatus using partial update
    AccountStatus partialUpdatedAccountStatus = new AccountStatus();
    partialUpdatedAccountStatus.setId(accountStatus.getId());

    partialUpdatedAccountStatus
      .uuid(UPDATED_UUID)
      .accountStatus(UPDATED_ACCOUNT_STATUS)
      .lastVisited(UPDATED_LAST_VISITED)
      .statusComment(UPDATED_STATUS_COMMENT);

    restAccountStatusMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedAccountStatus.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAccountStatus))
      )
      .andExpect(status().isOk());

    // Validate the AccountStatus in the database
    List<AccountStatus> accountStatusList = accountStatusRepository.findAll();
    assertThat(accountStatusList).hasSize(databaseSizeBeforeUpdate);
    AccountStatus testAccountStatus = accountStatusList.get(accountStatusList.size() - 1);
    assertThat(testAccountStatus.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testAccountStatus.getAccountStatus()).isEqualTo(UPDATED_ACCOUNT_STATUS);
    assertThat(testAccountStatus.getLastVisited()).isEqualTo(UPDATED_LAST_VISITED);
    assertThat(testAccountStatus.getStatusComment()).isEqualTo(UPDATED_STATUS_COMMENT);
  }

  @Test
  @Transactional
  void patchNonExistingAccountStatus() throws Exception {
    int databaseSizeBeforeUpdate = accountStatusRepository.findAll().size();
    accountStatus.setId(count.incrementAndGet());

    // Create the AccountStatus
    AccountStatusDTO accountStatusDTO = accountStatusMapper.toDto(accountStatus);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restAccountStatusMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, accountStatusDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(accountStatusDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the AccountStatus in the database
    List<AccountStatus> accountStatusList = accountStatusRepository.findAll();
    assertThat(accountStatusList).hasSize(databaseSizeBeforeUpdate);

    // Validate the AccountStatus in Elasticsearch
    verify(mockAccountStatusSearchRepository, times(0)).save(accountStatus);
  }

  @Test
  @Transactional
  void patchWithIdMismatchAccountStatus() throws Exception {
    int databaseSizeBeforeUpdate = accountStatusRepository.findAll().size();
    accountStatus.setId(count.incrementAndGet());

    // Create the AccountStatus
    AccountStatusDTO accountStatusDTO = accountStatusMapper.toDto(accountStatus);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAccountStatusMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(accountStatusDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the AccountStatus in the database
    List<AccountStatus> accountStatusList = accountStatusRepository.findAll();
    assertThat(accountStatusList).hasSize(databaseSizeBeforeUpdate);

    // Validate the AccountStatus in Elasticsearch
    verify(mockAccountStatusSearchRepository, times(0)).save(accountStatus);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamAccountStatus() throws Exception {
    int databaseSizeBeforeUpdate = accountStatusRepository.findAll().size();
    accountStatus.setId(count.incrementAndGet());

    // Create the AccountStatus
    AccountStatusDTO accountStatusDTO = accountStatusMapper.toDto(accountStatus);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAccountStatusMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(accountStatusDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the AccountStatus in the database
    List<AccountStatus> accountStatusList = accountStatusRepository.findAll();
    assertThat(accountStatusList).hasSize(databaseSizeBeforeUpdate);

    // Validate the AccountStatus in Elasticsearch
    verify(mockAccountStatusSearchRepository, times(0)).save(accountStatus);
  }

  @Test
  @Transactional
  void deleteAccountStatus() throws Exception {
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);

    int databaseSizeBeforeDelete = accountStatusRepository.findAll().size();

    // Delete the accountStatus
    restAccountStatusMockMvc
      .perform(delete(ENTITY_API_URL_ID, accountStatus.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<AccountStatus> accountStatusList = accountStatusRepository.findAll();
    assertThat(accountStatusList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the AccountStatus in Elasticsearch
    verify(mockAccountStatusSearchRepository, times(1)).deleteById(accountStatus.getId());
  }

  @Test
  @Transactional
  void searchAccountStatus() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    accountStatusRepository.saveAndFlush(accountStatus);
    when(mockAccountStatusSearchRepository.search(queryStringQuery("id:" + accountStatus.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(accountStatus), PageRequest.of(0, 1), 1));

    // Search the accountStatus
    restAccountStatusMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + accountStatus.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(accountStatus.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].accountStatus").value(hasItem(DEFAULT_ACCOUNT_STATUS.toString())))
      .andExpect(jsonPath("$.[*].lastVisited").value(hasItem(DEFAULT_LAST_VISITED.toString())))
      .andExpect(jsonPath("$.[*].statusComment").value(hasItem(DEFAULT_STATUS_COMMENT)));
  }
}
