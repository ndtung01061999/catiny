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
import com.regitiny.catiny.domain.enumeration.DeviceType;
import com.regitiny.catiny.domain.enumeration.StatusName;
import com.regitiny.catiny.repository.DeviceStatusRepository;
import com.regitiny.catiny.repository.search.DeviceStatusSearchRepository;
import com.regitiny.catiny.service.criteria.DeviceStatusCriteria;
import com.regitiny.catiny.service.dto.DeviceStatusDTO;
import com.regitiny.catiny.service.mapper.DeviceStatusMapper;
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
 * Integration tests for the {@link DeviceStatusResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class DeviceStatusResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_DEVICE_NAME = "AAAAAAAAAA";
  private static final String UPDATED_DEVICE_NAME = "BBBBBBBBBB";

  private static final DeviceType DEFAULT_DEVICE_TYPE = DeviceType.MOBILE;
  private static final DeviceType UPDATED_DEVICE_TYPE = DeviceType.TABLE;

  private static final StatusName DEFAULT_DEVICE_STATUS = StatusName.ONLINE;
  private static final StatusName UPDATED_DEVICE_STATUS = StatusName.OFFLINE;

  private static final Instant DEFAULT_LAST_VISITED = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_LAST_VISITED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

  private static final String DEFAULT_STATUS_COMMENT = "AAAAAAAAAA";
  private static final String UPDATED_STATUS_COMMENT = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/device-statuses";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/device-statuses";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private DeviceStatusRepository deviceStatusRepository;

  @Autowired
  private DeviceStatusMapper deviceStatusMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.DeviceStatusSearchRepositoryMockConfiguration
   */
  @Autowired
  private DeviceStatusSearchRepository mockDeviceStatusSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restDeviceStatusMockMvc;

  private DeviceStatus deviceStatus;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static DeviceStatus createEntity(EntityManager em) {
    DeviceStatus deviceStatus = new DeviceStatus()
      .uuid(DEFAULT_UUID)
      .deviceName(DEFAULT_DEVICE_NAME)
      .deviceType(DEFAULT_DEVICE_TYPE)
      .deviceStatus(DEFAULT_DEVICE_STATUS)
      .lastVisited(DEFAULT_LAST_VISITED)
      .statusComment(DEFAULT_STATUS_COMMENT);
    return deviceStatus;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static DeviceStatus createUpdatedEntity(EntityManager em) {
    DeviceStatus deviceStatus = new DeviceStatus()
      .uuid(UPDATED_UUID)
      .deviceName(UPDATED_DEVICE_NAME)
      .deviceType(UPDATED_DEVICE_TYPE)
      .deviceStatus(UPDATED_DEVICE_STATUS)
      .lastVisited(UPDATED_LAST_VISITED)
      .statusComment(UPDATED_STATUS_COMMENT);
    return deviceStatus;
  }

  @BeforeEach
  public void initTest() {
    deviceStatus = createEntity(em);
  }

  @Test
  @Transactional
  void createDeviceStatus() throws Exception {
    int databaseSizeBeforeCreate = deviceStatusRepository.findAll().size();
    // Create the DeviceStatus
    DeviceStatusDTO deviceStatusDTO = deviceStatusMapper.toDto(deviceStatus);
    restDeviceStatusMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deviceStatusDTO)))
      .andExpect(status().isCreated());

    // Validate the DeviceStatus in the database
    List<DeviceStatus> deviceStatusList = deviceStatusRepository.findAll();
    assertThat(deviceStatusList).hasSize(databaseSizeBeforeCreate + 1);
    DeviceStatus testDeviceStatus = deviceStatusList.get(deviceStatusList.size() - 1);
    assertThat(testDeviceStatus.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testDeviceStatus.getDeviceName()).isEqualTo(DEFAULT_DEVICE_NAME);
    assertThat(testDeviceStatus.getDeviceType()).isEqualTo(DEFAULT_DEVICE_TYPE);
    assertThat(testDeviceStatus.getDeviceStatus()).isEqualTo(DEFAULT_DEVICE_STATUS);
    assertThat(testDeviceStatus.getLastVisited()).isEqualTo(DEFAULT_LAST_VISITED);
    assertThat(testDeviceStatus.getStatusComment()).isEqualTo(DEFAULT_STATUS_COMMENT);

    // Validate the DeviceStatus in Elasticsearch
    verify(mockDeviceStatusSearchRepository, times(1)).save(testDeviceStatus);
  }

  @Test
  @Transactional
  void createDeviceStatusWithExistingId() throws Exception {
    // Create the DeviceStatus with an existing ID
    deviceStatus.setId(1L);
    DeviceStatusDTO deviceStatusDTO = deviceStatusMapper.toDto(deviceStatus);

    int databaseSizeBeforeCreate = deviceStatusRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restDeviceStatusMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deviceStatusDTO)))
      .andExpect(status().isBadRequest());

    // Validate the DeviceStatus in the database
    List<DeviceStatus> deviceStatusList = deviceStatusRepository.findAll();
    assertThat(deviceStatusList).hasSize(databaseSizeBeforeCreate);

    // Validate the DeviceStatus in Elasticsearch
    verify(mockDeviceStatusSearchRepository, times(0)).save(deviceStatus);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = deviceStatusRepository.findAll().size();
    // set the field null
    deviceStatus.setUuid(null);

    // Create the DeviceStatus, which fails.
    DeviceStatusDTO deviceStatusDTO = deviceStatusMapper.toDto(deviceStatus);

    restDeviceStatusMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deviceStatusDTO)))
      .andExpect(status().isBadRequest());

    List<DeviceStatus> deviceStatusList = deviceStatusRepository.findAll();
    assertThat(deviceStatusList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllDeviceStatuses() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList
    restDeviceStatusMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(deviceStatus.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].deviceName").value(hasItem(DEFAULT_DEVICE_NAME)))
      .andExpect(jsonPath("$.[*].deviceType").value(hasItem(DEFAULT_DEVICE_TYPE.toString())))
      .andExpect(jsonPath("$.[*].deviceStatus").value(hasItem(DEFAULT_DEVICE_STATUS.toString())))
      .andExpect(jsonPath("$.[*].lastVisited").value(hasItem(DEFAULT_LAST_VISITED.toString())))
      .andExpect(jsonPath("$.[*].statusComment").value(hasItem(DEFAULT_STATUS_COMMENT)));
  }

  @Test
  @Transactional
  void getDeviceStatus() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get the deviceStatus
    restDeviceStatusMockMvc
      .perform(get(ENTITY_API_URL_ID, deviceStatus.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(deviceStatus.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.deviceName").value(DEFAULT_DEVICE_NAME))
      .andExpect(jsonPath("$.deviceType").value(DEFAULT_DEVICE_TYPE.toString()))
      .andExpect(jsonPath("$.deviceStatus").value(DEFAULT_DEVICE_STATUS.toString()))
      .andExpect(jsonPath("$.lastVisited").value(DEFAULT_LAST_VISITED.toString()))
      .andExpect(jsonPath("$.statusComment").value(DEFAULT_STATUS_COMMENT));
  }

  @Test
  @Transactional
  void getDeviceStatusesByIdFiltering() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    Long id = deviceStatus.getId();

    defaultDeviceStatusShouldBeFound("id.equals=" + id);
    defaultDeviceStatusShouldNotBeFound("id.notEquals=" + id);

    defaultDeviceStatusShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultDeviceStatusShouldNotBeFound("id.greaterThan=" + id);

    defaultDeviceStatusShouldBeFound("id.lessThanOrEqual=" + id);
    defaultDeviceStatusShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where uuid equals to DEFAULT_UUID
    defaultDeviceStatusShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the deviceStatusList where uuid equals to UPDATED_UUID
    defaultDeviceStatusShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where uuid not equals to DEFAULT_UUID
    defaultDeviceStatusShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the deviceStatusList where uuid not equals to UPDATED_UUID
    defaultDeviceStatusShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultDeviceStatusShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the deviceStatusList where uuid equals to UPDATED_UUID
    defaultDeviceStatusShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where uuid is not null
    defaultDeviceStatusShouldBeFound("uuid.specified=true");

    // Get all the deviceStatusList where uuid is null
    defaultDeviceStatusShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByDeviceNameIsEqualToSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where deviceName equals to DEFAULT_DEVICE_NAME
    defaultDeviceStatusShouldBeFound("deviceName.equals=" + DEFAULT_DEVICE_NAME);

    // Get all the deviceStatusList where deviceName equals to UPDATED_DEVICE_NAME
    defaultDeviceStatusShouldNotBeFound("deviceName.equals=" + UPDATED_DEVICE_NAME);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByDeviceNameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where deviceName not equals to DEFAULT_DEVICE_NAME
    defaultDeviceStatusShouldNotBeFound("deviceName.notEquals=" + DEFAULT_DEVICE_NAME);

    // Get all the deviceStatusList where deviceName not equals to UPDATED_DEVICE_NAME
    defaultDeviceStatusShouldBeFound("deviceName.notEquals=" + UPDATED_DEVICE_NAME);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByDeviceNameIsInShouldWork() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where deviceName in DEFAULT_DEVICE_NAME or UPDATED_DEVICE_NAME
    defaultDeviceStatusShouldBeFound("deviceName.in=" + DEFAULT_DEVICE_NAME + "," + UPDATED_DEVICE_NAME);

    // Get all the deviceStatusList where deviceName equals to UPDATED_DEVICE_NAME
    defaultDeviceStatusShouldNotBeFound("deviceName.in=" + UPDATED_DEVICE_NAME);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByDeviceNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where deviceName is not null
    defaultDeviceStatusShouldBeFound("deviceName.specified=true");

    // Get all the deviceStatusList where deviceName is null
    defaultDeviceStatusShouldNotBeFound("deviceName.specified=false");
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByDeviceNameContainsSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where deviceName contains DEFAULT_DEVICE_NAME
    defaultDeviceStatusShouldBeFound("deviceName.contains=" + DEFAULT_DEVICE_NAME);

    // Get all the deviceStatusList where deviceName contains UPDATED_DEVICE_NAME
    defaultDeviceStatusShouldNotBeFound("deviceName.contains=" + UPDATED_DEVICE_NAME);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByDeviceNameNotContainsSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where deviceName does not contain DEFAULT_DEVICE_NAME
    defaultDeviceStatusShouldNotBeFound("deviceName.doesNotContain=" + DEFAULT_DEVICE_NAME);

    // Get all the deviceStatusList where deviceName does not contain UPDATED_DEVICE_NAME
    defaultDeviceStatusShouldBeFound("deviceName.doesNotContain=" + UPDATED_DEVICE_NAME);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByDeviceTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where deviceType equals to DEFAULT_DEVICE_TYPE
    defaultDeviceStatusShouldBeFound("deviceType.equals=" + DEFAULT_DEVICE_TYPE);

    // Get all the deviceStatusList where deviceType equals to UPDATED_DEVICE_TYPE
    defaultDeviceStatusShouldNotBeFound("deviceType.equals=" + UPDATED_DEVICE_TYPE);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByDeviceTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where deviceType not equals to DEFAULT_DEVICE_TYPE
    defaultDeviceStatusShouldNotBeFound("deviceType.notEquals=" + DEFAULT_DEVICE_TYPE);

    // Get all the deviceStatusList where deviceType not equals to UPDATED_DEVICE_TYPE
    defaultDeviceStatusShouldBeFound("deviceType.notEquals=" + UPDATED_DEVICE_TYPE);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByDeviceTypeIsInShouldWork() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where deviceType in DEFAULT_DEVICE_TYPE or UPDATED_DEVICE_TYPE
    defaultDeviceStatusShouldBeFound("deviceType.in=" + DEFAULT_DEVICE_TYPE + "," + UPDATED_DEVICE_TYPE);

    // Get all the deviceStatusList where deviceType equals to UPDATED_DEVICE_TYPE
    defaultDeviceStatusShouldNotBeFound("deviceType.in=" + UPDATED_DEVICE_TYPE);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByDeviceTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where deviceType is not null
    defaultDeviceStatusShouldBeFound("deviceType.specified=true");

    // Get all the deviceStatusList where deviceType is null
    defaultDeviceStatusShouldNotBeFound("deviceType.specified=false");
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByDeviceStatusIsEqualToSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where deviceStatus equals to DEFAULT_DEVICE_STATUS
    defaultDeviceStatusShouldBeFound("deviceStatus.equals=" + DEFAULT_DEVICE_STATUS);

    // Get all the deviceStatusList where deviceStatus equals to UPDATED_DEVICE_STATUS
    defaultDeviceStatusShouldNotBeFound("deviceStatus.equals=" + UPDATED_DEVICE_STATUS);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByDeviceStatusIsNotEqualToSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where deviceStatus not equals to DEFAULT_DEVICE_STATUS
    defaultDeviceStatusShouldNotBeFound("deviceStatus.notEquals=" + DEFAULT_DEVICE_STATUS);

    // Get all the deviceStatusList where deviceStatus not equals to UPDATED_DEVICE_STATUS
    defaultDeviceStatusShouldBeFound("deviceStatus.notEquals=" + UPDATED_DEVICE_STATUS);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByDeviceStatusIsInShouldWork() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where deviceStatus in DEFAULT_DEVICE_STATUS or UPDATED_DEVICE_STATUS
    defaultDeviceStatusShouldBeFound("deviceStatus.in=" + DEFAULT_DEVICE_STATUS + "," + UPDATED_DEVICE_STATUS);

    // Get all the deviceStatusList where deviceStatus equals to UPDATED_DEVICE_STATUS
    defaultDeviceStatusShouldNotBeFound("deviceStatus.in=" + UPDATED_DEVICE_STATUS);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByDeviceStatusIsNullOrNotNull() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where deviceStatus is not null
    defaultDeviceStatusShouldBeFound("deviceStatus.specified=true");

    // Get all the deviceStatusList where deviceStatus is null
    defaultDeviceStatusShouldNotBeFound("deviceStatus.specified=false");
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByLastVisitedIsEqualToSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where lastVisited equals to DEFAULT_LAST_VISITED
    defaultDeviceStatusShouldBeFound("lastVisited.equals=" + DEFAULT_LAST_VISITED);

    // Get all the deviceStatusList where lastVisited equals to UPDATED_LAST_VISITED
    defaultDeviceStatusShouldNotBeFound("lastVisited.equals=" + UPDATED_LAST_VISITED);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByLastVisitedIsNotEqualToSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where lastVisited not equals to DEFAULT_LAST_VISITED
    defaultDeviceStatusShouldNotBeFound("lastVisited.notEquals=" + DEFAULT_LAST_VISITED);

    // Get all the deviceStatusList where lastVisited not equals to UPDATED_LAST_VISITED
    defaultDeviceStatusShouldBeFound("lastVisited.notEquals=" + UPDATED_LAST_VISITED);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByLastVisitedIsInShouldWork() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where lastVisited in DEFAULT_LAST_VISITED or UPDATED_LAST_VISITED
    defaultDeviceStatusShouldBeFound("lastVisited.in=" + DEFAULT_LAST_VISITED + "," + UPDATED_LAST_VISITED);

    // Get all the deviceStatusList where lastVisited equals to UPDATED_LAST_VISITED
    defaultDeviceStatusShouldNotBeFound("lastVisited.in=" + UPDATED_LAST_VISITED);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByLastVisitedIsNullOrNotNull() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where lastVisited is not null
    defaultDeviceStatusShouldBeFound("lastVisited.specified=true");

    // Get all the deviceStatusList where lastVisited is null
    defaultDeviceStatusShouldNotBeFound("lastVisited.specified=false");
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByStatusCommentIsEqualToSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where statusComment equals to DEFAULT_STATUS_COMMENT
    defaultDeviceStatusShouldBeFound("statusComment.equals=" + DEFAULT_STATUS_COMMENT);

    // Get all the deviceStatusList where statusComment equals to UPDATED_STATUS_COMMENT
    defaultDeviceStatusShouldNotBeFound("statusComment.equals=" + UPDATED_STATUS_COMMENT);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByStatusCommentIsNotEqualToSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where statusComment not equals to DEFAULT_STATUS_COMMENT
    defaultDeviceStatusShouldNotBeFound("statusComment.notEquals=" + DEFAULT_STATUS_COMMENT);

    // Get all the deviceStatusList where statusComment not equals to UPDATED_STATUS_COMMENT
    defaultDeviceStatusShouldBeFound("statusComment.notEquals=" + UPDATED_STATUS_COMMENT);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByStatusCommentIsInShouldWork() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where statusComment in DEFAULT_STATUS_COMMENT or UPDATED_STATUS_COMMENT
    defaultDeviceStatusShouldBeFound("statusComment.in=" + DEFAULT_STATUS_COMMENT + "," + UPDATED_STATUS_COMMENT);

    // Get all the deviceStatusList where statusComment equals to UPDATED_STATUS_COMMENT
    defaultDeviceStatusShouldNotBeFound("statusComment.in=" + UPDATED_STATUS_COMMENT);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByStatusCommentIsNullOrNotNull() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where statusComment is not null
    defaultDeviceStatusShouldBeFound("statusComment.specified=true");

    // Get all the deviceStatusList where statusComment is null
    defaultDeviceStatusShouldNotBeFound("statusComment.specified=false");
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByStatusCommentContainsSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where statusComment contains DEFAULT_STATUS_COMMENT
    defaultDeviceStatusShouldBeFound("statusComment.contains=" + DEFAULT_STATUS_COMMENT);

    // Get all the deviceStatusList where statusComment contains UPDATED_STATUS_COMMENT
    defaultDeviceStatusShouldNotBeFound("statusComment.contains=" + UPDATED_STATUS_COMMENT);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByStatusCommentNotContainsSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    // Get all the deviceStatusList where statusComment does not contain DEFAULT_STATUS_COMMENT
    defaultDeviceStatusShouldNotBeFound("statusComment.doesNotContain=" + DEFAULT_STATUS_COMMENT);

    // Get all the deviceStatusList where statusComment does not contain UPDATED_STATUS_COMMENT
    defaultDeviceStatusShouldBeFound("statusComment.doesNotContain=" + UPDATED_STATUS_COMMENT);
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    deviceStatus.setBaseInfo(baseInfo);
    deviceStatusRepository.saveAndFlush(deviceStatus);
    Long baseInfoId = baseInfo.getId();

    // Get all the deviceStatusList where baseInfo equals to baseInfoId
    defaultDeviceStatusShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the deviceStatusList where baseInfo equals to (baseInfoId + 1)
    defaultDeviceStatusShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllDeviceStatusesByAccountStatusIsEqualToSomething() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);
    AccountStatus accountStatus = AccountStatusResourceIT.createEntity(em);
    em.persist(accountStatus);
    em.flush();
    deviceStatus.setAccountStatus(accountStatus);
    deviceStatusRepository.saveAndFlush(deviceStatus);
    Long accountStatusId = accountStatus.getId();

    // Get all the deviceStatusList where accountStatus equals to accountStatusId
    defaultDeviceStatusShouldBeFound("accountStatusId.equals=" + accountStatusId);

    // Get all the deviceStatusList where accountStatus equals to (accountStatusId + 1)
    defaultDeviceStatusShouldNotBeFound("accountStatusId.equals=" + (accountStatusId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultDeviceStatusShouldBeFound(String filter) throws Exception {
    restDeviceStatusMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(deviceStatus.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].deviceName").value(hasItem(DEFAULT_DEVICE_NAME)))
      .andExpect(jsonPath("$.[*].deviceType").value(hasItem(DEFAULT_DEVICE_TYPE.toString())))
      .andExpect(jsonPath("$.[*].deviceStatus").value(hasItem(DEFAULT_DEVICE_STATUS.toString())))
      .andExpect(jsonPath("$.[*].lastVisited").value(hasItem(DEFAULT_LAST_VISITED.toString())))
      .andExpect(jsonPath("$.[*].statusComment").value(hasItem(DEFAULT_STATUS_COMMENT)));

    // Check, that the count call also returns 1
    restDeviceStatusMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultDeviceStatusShouldNotBeFound(String filter) throws Exception {
    restDeviceStatusMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restDeviceStatusMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingDeviceStatus() throws Exception {
    // Get the deviceStatus
    restDeviceStatusMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewDeviceStatus() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    int databaseSizeBeforeUpdate = deviceStatusRepository.findAll().size();

    // Update the deviceStatus
    DeviceStatus updatedDeviceStatus = deviceStatusRepository.findById(deviceStatus.getId()).get();
    // Disconnect from session so that the updates on updatedDeviceStatus are not directly saved in db
    em.detach(updatedDeviceStatus);
    updatedDeviceStatus
      .uuid(UPDATED_UUID)
      .deviceName(UPDATED_DEVICE_NAME)
      .deviceType(UPDATED_DEVICE_TYPE)
      .deviceStatus(UPDATED_DEVICE_STATUS)
      .lastVisited(UPDATED_LAST_VISITED)
      .statusComment(UPDATED_STATUS_COMMENT);
    DeviceStatusDTO deviceStatusDTO = deviceStatusMapper.toDto(updatedDeviceStatus);

    restDeviceStatusMockMvc
      .perform(
        put(ENTITY_API_URL_ID, deviceStatusDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(deviceStatusDTO))
      )
      .andExpect(status().isOk());

    // Validate the DeviceStatus in the database
    List<DeviceStatus> deviceStatusList = deviceStatusRepository.findAll();
    assertThat(deviceStatusList).hasSize(databaseSizeBeforeUpdate);
    DeviceStatus testDeviceStatus = deviceStatusList.get(deviceStatusList.size() - 1);
    assertThat(testDeviceStatus.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testDeviceStatus.getDeviceName()).isEqualTo(UPDATED_DEVICE_NAME);
    assertThat(testDeviceStatus.getDeviceType()).isEqualTo(UPDATED_DEVICE_TYPE);
    assertThat(testDeviceStatus.getDeviceStatus()).isEqualTo(UPDATED_DEVICE_STATUS);
    assertThat(testDeviceStatus.getLastVisited()).isEqualTo(UPDATED_LAST_VISITED);
    assertThat(testDeviceStatus.getStatusComment()).isEqualTo(UPDATED_STATUS_COMMENT);

    // Validate the DeviceStatus in Elasticsearch
    verify(mockDeviceStatusSearchRepository).save(testDeviceStatus);
  }

  @Test
  @Transactional
  void putNonExistingDeviceStatus() throws Exception {
    int databaseSizeBeforeUpdate = deviceStatusRepository.findAll().size();
    deviceStatus.setId(count.incrementAndGet());

    // Create the DeviceStatus
    DeviceStatusDTO deviceStatusDTO = deviceStatusMapper.toDto(deviceStatus);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restDeviceStatusMockMvc
      .perform(
        put(ENTITY_API_URL_ID, deviceStatusDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(deviceStatusDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the DeviceStatus in the database
    List<DeviceStatus> deviceStatusList = deviceStatusRepository.findAll();
    assertThat(deviceStatusList).hasSize(databaseSizeBeforeUpdate);

    // Validate the DeviceStatus in Elasticsearch
    verify(mockDeviceStatusSearchRepository, times(0)).save(deviceStatus);
  }

  @Test
  @Transactional
  void putWithIdMismatchDeviceStatus() throws Exception {
    int databaseSizeBeforeUpdate = deviceStatusRepository.findAll().size();
    deviceStatus.setId(count.incrementAndGet());

    // Create the DeviceStatus
    DeviceStatusDTO deviceStatusDTO = deviceStatusMapper.toDto(deviceStatus);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restDeviceStatusMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(deviceStatusDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the DeviceStatus in the database
    List<DeviceStatus> deviceStatusList = deviceStatusRepository.findAll();
    assertThat(deviceStatusList).hasSize(databaseSizeBeforeUpdate);

    // Validate the DeviceStatus in Elasticsearch
    verify(mockDeviceStatusSearchRepository, times(0)).save(deviceStatus);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamDeviceStatus() throws Exception {
    int databaseSizeBeforeUpdate = deviceStatusRepository.findAll().size();
    deviceStatus.setId(count.incrementAndGet());

    // Create the DeviceStatus
    DeviceStatusDTO deviceStatusDTO = deviceStatusMapper.toDto(deviceStatus);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restDeviceStatusMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deviceStatusDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the DeviceStatus in the database
    List<DeviceStatus> deviceStatusList = deviceStatusRepository.findAll();
    assertThat(deviceStatusList).hasSize(databaseSizeBeforeUpdate);

    // Validate the DeviceStatus in Elasticsearch
    verify(mockDeviceStatusSearchRepository, times(0)).save(deviceStatus);
  }

  @Test
  @Transactional
  void partialUpdateDeviceStatusWithPatch() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    int databaseSizeBeforeUpdate = deviceStatusRepository.findAll().size();

    // Update the deviceStatus using partial update
    DeviceStatus partialUpdatedDeviceStatus = new DeviceStatus();
    partialUpdatedDeviceStatus.setId(deviceStatus.getId());

    partialUpdatedDeviceStatus.deviceType(UPDATED_DEVICE_TYPE).deviceStatus(UPDATED_DEVICE_STATUS).lastVisited(UPDATED_LAST_VISITED);

    restDeviceStatusMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedDeviceStatus.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeviceStatus))
      )
      .andExpect(status().isOk());

    // Validate the DeviceStatus in the database
    List<DeviceStatus> deviceStatusList = deviceStatusRepository.findAll();
    assertThat(deviceStatusList).hasSize(databaseSizeBeforeUpdate);
    DeviceStatus testDeviceStatus = deviceStatusList.get(deviceStatusList.size() - 1);
    assertThat(testDeviceStatus.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testDeviceStatus.getDeviceName()).isEqualTo(DEFAULT_DEVICE_NAME);
    assertThat(testDeviceStatus.getDeviceType()).isEqualTo(UPDATED_DEVICE_TYPE);
    assertThat(testDeviceStatus.getDeviceStatus()).isEqualTo(UPDATED_DEVICE_STATUS);
    assertThat(testDeviceStatus.getLastVisited()).isEqualTo(UPDATED_LAST_VISITED);
    assertThat(testDeviceStatus.getStatusComment()).isEqualTo(DEFAULT_STATUS_COMMENT);
  }

  @Test
  @Transactional
  void fullUpdateDeviceStatusWithPatch() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    int databaseSizeBeforeUpdate = deviceStatusRepository.findAll().size();

    // Update the deviceStatus using partial update
    DeviceStatus partialUpdatedDeviceStatus = new DeviceStatus();
    partialUpdatedDeviceStatus.setId(deviceStatus.getId());

    partialUpdatedDeviceStatus
      .uuid(UPDATED_UUID)
      .deviceName(UPDATED_DEVICE_NAME)
      .deviceType(UPDATED_DEVICE_TYPE)
      .deviceStatus(UPDATED_DEVICE_STATUS)
      .lastVisited(UPDATED_LAST_VISITED)
      .statusComment(UPDATED_STATUS_COMMENT);

    restDeviceStatusMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedDeviceStatus.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeviceStatus))
      )
      .andExpect(status().isOk());

    // Validate the DeviceStatus in the database
    List<DeviceStatus> deviceStatusList = deviceStatusRepository.findAll();
    assertThat(deviceStatusList).hasSize(databaseSizeBeforeUpdate);
    DeviceStatus testDeviceStatus = deviceStatusList.get(deviceStatusList.size() - 1);
    assertThat(testDeviceStatus.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testDeviceStatus.getDeviceName()).isEqualTo(UPDATED_DEVICE_NAME);
    assertThat(testDeviceStatus.getDeviceType()).isEqualTo(UPDATED_DEVICE_TYPE);
    assertThat(testDeviceStatus.getDeviceStatus()).isEqualTo(UPDATED_DEVICE_STATUS);
    assertThat(testDeviceStatus.getLastVisited()).isEqualTo(UPDATED_LAST_VISITED);
    assertThat(testDeviceStatus.getStatusComment()).isEqualTo(UPDATED_STATUS_COMMENT);
  }

  @Test
  @Transactional
  void patchNonExistingDeviceStatus() throws Exception {
    int databaseSizeBeforeUpdate = deviceStatusRepository.findAll().size();
    deviceStatus.setId(count.incrementAndGet());

    // Create the DeviceStatus
    DeviceStatusDTO deviceStatusDTO = deviceStatusMapper.toDto(deviceStatus);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restDeviceStatusMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, deviceStatusDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(deviceStatusDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the DeviceStatus in the database
    List<DeviceStatus> deviceStatusList = deviceStatusRepository.findAll();
    assertThat(deviceStatusList).hasSize(databaseSizeBeforeUpdate);

    // Validate the DeviceStatus in Elasticsearch
    verify(mockDeviceStatusSearchRepository, times(0)).save(deviceStatus);
  }

  @Test
  @Transactional
  void patchWithIdMismatchDeviceStatus() throws Exception {
    int databaseSizeBeforeUpdate = deviceStatusRepository.findAll().size();
    deviceStatus.setId(count.incrementAndGet());

    // Create the DeviceStatus
    DeviceStatusDTO deviceStatusDTO = deviceStatusMapper.toDto(deviceStatus);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restDeviceStatusMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(deviceStatusDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the DeviceStatus in the database
    List<DeviceStatus> deviceStatusList = deviceStatusRepository.findAll();
    assertThat(deviceStatusList).hasSize(databaseSizeBeforeUpdate);

    // Validate the DeviceStatus in Elasticsearch
    verify(mockDeviceStatusSearchRepository, times(0)).save(deviceStatus);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamDeviceStatus() throws Exception {
    int databaseSizeBeforeUpdate = deviceStatusRepository.findAll().size();
    deviceStatus.setId(count.incrementAndGet());

    // Create the DeviceStatus
    DeviceStatusDTO deviceStatusDTO = deviceStatusMapper.toDto(deviceStatus);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restDeviceStatusMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(deviceStatusDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the DeviceStatus in the database
    List<DeviceStatus> deviceStatusList = deviceStatusRepository.findAll();
    assertThat(deviceStatusList).hasSize(databaseSizeBeforeUpdate);

    // Validate the DeviceStatus in Elasticsearch
    verify(mockDeviceStatusSearchRepository, times(0)).save(deviceStatus);
  }

  @Test
  @Transactional
  void deleteDeviceStatus() throws Exception {
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);

    int databaseSizeBeforeDelete = deviceStatusRepository.findAll().size();

    // Delete the deviceStatus
    restDeviceStatusMockMvc
      .perform(delete(ENTITY_API_URL_ID, deviceStatus.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<DeviceStatus> deviceStatusList = deviceStatusRepository.findAll();
    assertThat(deviceStatusList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the DeviceStatus in Elasticsearch
    verify(mockDeviceStatusSearchRepository, times(1)).deleteById(deviceStatus.getId());
  }

  @Test
  @Transactional
  void searchDeviceStatus() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    deviceStatusRepository.saveAndFlush(deviceStatus);
    when(mockDeviceStatusSearchRepository.search(queryStringQuery("id:" + deviceStatus.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(deviceStatus), PageRequest.of(0, 1), 1));

    // Search the deviceStatus
    restDeviceStatusMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + deviceStatus.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(deviceStatus.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].deviceName").value(hasItem(DEFAULT_DEVICE_NAME)))
      .andExpect(jsonPath("$.[*].deviceType").value(hasItem(DEFAULT_DEVICE_TYPE.toString())))
      .andExpect(jsonPath("$.[*].deviceStatus").value(hasItem(DEFAULT_DEVICE_STATUS.toString())))
      .andExpect(jsonPath("$.[*].lastVisited").value(hasItem(DEFAULT_LAST_VISITED.toString())))
      .andExpect(jsonPath("$.[*].statusComment").value(hasItem(DEFAULT_STATUS_COMMENT)));
  }
}
