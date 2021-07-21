package com.regitiny.catiny.web.rest;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.IntegrationTest;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.domain.enumeration.ProcessStatus;
import com.regitiny.catiny.repository.BaseInfoRepository;
import com.regitiny.catiny.repository.search.BaseInfoSearchRepository;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.mapper.BaseInfoMapper;
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
 * Integration tests for the {@link BaseInfoResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class BaseInfoResourceIT
{

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final ProcessStatus DEFAULT_PROCESS_STATUS = ProcessStatus.NOT_PROCESSED;
  private static final ProcessStatus UPDATED_PROCESS_STATUS = ProcessStatus.PROCESSING;

  private static final String DEFAULT_MODIFIED_CLASS = "AAAAAAAAAA";
  private static final String UPDATED_MODIFIED_CLASS = "BBBBBBBBBB";

  private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

  private static final Instant DEFAULT_MODIFIED_DATE = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

  private static final String DEFAULT_NOTES = "AAAAAAAAAA";
  private static final String UPDATED_NOTES = "BBBBBBBBBB";

  private static final Boolean DEFAULT_DELETED = false;
  private static final Boolean UPDATED_DELETED = true;

  private static final Long DEFAULT_PRIORITY_INDEX = 1L;
  private static final Long UPDATED_PRIORITY_INDEX = 2L;
  private static final Long SMALLER_PRIORITY_INDEX = 1L - 1L;

  private static final Long DEFAULT_COUNT_USE = 1L;
  private static final Long UPDATED_COUNT_USE = 2L;
  private static final Long SMALLER_COUNT_USE = 1L - 1L;

  private static final String ENTITY_API_URL = "/api/base-infos";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/base-infos";

  private static final Random random = new Random();
  private static final AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private BaseInfoRepository baseInfoRepository;

  @Autowired
  private BaseInfoMapper baseInfoMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.BaseInfoSearchRepositoryMockConfiguration
   */
  @Autowired
  private BaseInfoSearchRepository mockBaseInfoSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restBaseInfoMockMvc;

  private BaseInfo baseInfo;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static BaseInfo createEntity(EntityManager em) {
    BaseInfo baseInfo = new BaseInfo()
      .uuid(DEFAULT_UUID)
      .processStatus(DEFAULT_PROCESS_STATUS)
      .modifiedClass(DEFAULT_MODIFIED_CLASS)
      .createdDate(DEFAULT_CREATED_DATE)
      .modifiedDate(DEFAULT_MODIFIED_DATE)
      .notes(DEFAULT_NOTES)
      .deleted(DEFAULT_DELETED)
      .priorityIndex(DEFAULT_PRIORITY_INDEX)
      .countUse(DEFAULT_COUNT_USE);
    return baseInfo;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static BaseInfo createUpdatedEntity(EntityManager em) {
    BaseInfo baseInfo = new BaseInfo()
      .uuid(UPDATED_UUID)
      .processStatus(UPDATED_PROCESS_STATUS)
      .modifiedClass(UPDATED_MODIFIED_CLASS)
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .notes(UPDATED_NOTES)
      .deleted(UPDATED_DELETED)
      .priorityIndex(UPDATED_PRIORITY_INDEX)
      .countUse(UPDATED_COUNT_USE);
    return baseInfo;
  }

  @BeforeEach
  public void initTest() {
    baseInfo = createEntity(em);
  }

  @Test
  @Transactional
  void createBaseInfo() throws Exception {
    int databaseSizeBeforeCreate = baseInfoRepository.findAll().size();
    // Create the BaseInfo
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);
    restBaseInfoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(baseInfoDTO)))
      .andExpect(status().isCreated());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeCreate + 1);
    BaseInfo testBaseInfo = baseInfoList.get(baseInfoList.size() - 1);
    assertThat(testBaseInfo.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testBaseInfo.getProcessStatus()).isEqualTo(DEFAULT_PROCESS_STATUS);
    assertThat(testBaseInfo.getModifiedClass()).isEqualTo(DEFAULT_MODIFIED_CLASS);
    assertThat(testBaseInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testBaseInfo.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    assertThat(testBaseInfo.getNotes()).isEqualTo(DEFAULT_NOTES);
    assertThat(testBaseInfo.getDeleted()).isEqualTo(DEFAULT_DELETED);
    assertThat(testBaseInfo.getPriorityIndex()).isEqualTo(DEFAULT_PRIORITY_INDEX);
    assertThat(testBaseInfo.getCountUse()).isEqualTo(DEFAULT_COUNT_USE);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(1)).save(testBaseInfo);
  }

  @Test
  @Transactional
  void createBaseInfoWithExistingId() throws Exception {
    // Create the BaseInfo with an existing ID
    baseInfo.setId(1L);
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    int databaseSizeBeforeCreate = baseInfoRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restBaseInfoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(baseInfoDTO)))
      .andExpect(status().isBadRequest());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeCreate);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(0)).save(baseInfo);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception
  {
    int databaseSizeBeforeTest = baseInfoRepository.findAll().size();
    // set the field null
    baseInfo.setUuid(null);

    // Create the BaseInfo, which fails.
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    restBaseInfoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(baseInfoDTO)))
      .andExpect(status().isBadRequest());

    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllBaseInfos() throws Exception
  {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList
    restBaseInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(baseInfo.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].processStatus").value(hasItem(DEFAULT_PROCESS_STATUS.toString())))
      .andExpect(jsonPath("$.[*].modifiedClass").value(hasItem(DEFAULT_MODIFIED_CLASS)))
      .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
      .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
      .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
      .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
      .andExpect(jsonPath("$.[*].priorityIndex").value(hasItem(DEFAULT_PRIORITY_INDEX.intValue())))
      .andExpect(jsonPath("$.[*].countUse").value(hasItem(DEFAULT_COUNT_USE.intValue())));
  }

  @Test
  @Transactional
  void getBaseInfo() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get the baseInfo
    restBaseInfoMockMvc
      .perform(get(ENTITY_API_URL_ID, baseInfo.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(baseInfo.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.processStatus").value(DEFAULT_PROCESS_STATUS.toString()))
      .andExpect(jsonPath("$.modifiedClass").value(DEFAULT_MODIFIED_CLASS))
      .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
      .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
      .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
      .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()))
      .andExpect(jsonPath("$.priorityIndex").value(DEFAULT_PRIORITY_INDEX.intValue()))
      .andExpect(jsonPath("$.countUse").value(DEFAULT_COUNT_USE.intValue()));
  }

  @Test
  @Transactional
  void getBaseInfosByIdFiltering() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    Long id = baseInfo.getId();

    defaultBaseInfoShouldBeFound("id.equals=" + id);
    defaultBaseInfoShouldNotBeFound("id.notEquals=" + id);

    defaultBaseInfoShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultBaseInfoShouldNotBeFound("id.greaterThan=" + id);

    defaultBaseInfoShouldBeFound("id.lessThanOrEqual=" + id);
    defaultBaseInfoShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllBaseInfosByUuidIsEqualToSomething() throws Exception
  {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where uuid equals to DEFAULT_UUID
    defaultBaseInfoShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the baseInfoList where uuid equals to UPDATED_UUID
    defaultBaseInfoShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllBaseInfosByUuidIsNotEqualToSomething() throws Exception
  {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where uuid not equals to DEFAULT_UUID
    defaultBaseInfoShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the baseInfoList where uuid not equals to UPDATED_UUID
    defaultBaseInfoShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllBaseInfosByUuidIsInShouldWork() throws Exception
  {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultBaseInfoShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the baseInfoList where uuid equals to UPDATED_UUID
    defaultBaseInfoShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllBaseInfosByUuidIsNullOrNotNull() throws Exception
  {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where uuid is not null
    defaultBaseInfoShouldBeFound("uuid.specified=true");

    // Get all the baseInfoList where uuid is null
    defaultBaseInfoShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByProcessStatusIsEqualToSomething() throws Exception
  {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where processStatus equals to DEFAULT_PROCESS_STATUS
    defaultBaseInfoShouldBeFound("processStatus.equals=" + DEFAULT_PROCESS_STATUS);

    // Get all the baseInfoList where processStatus equals to UPDATED_PROCESS_STATUS
    defaultBaseInfoShouldNotBeFound("processStatus.equals=" + UPDATED_PROCESS_STATUS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByProcessStatusIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where processStatus not equals to DEFAULT_PROCESS_STATUS
    defaultBaseInfoShouldNotBeFound("processStatus.notEquals=" + DEFAULT_PROCESS_STATUS);

    // Get all the baseInfoList where processStatus not equals to UPDATED_PROCESS_STATUS
    defaultBaseInfoShouldBeFound("processStatus.notEquals=" + UPDATED_PROCESS_STATUS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByProcessStatusIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where processStatus in DEFAULT_PROCESS_STATUS or UPDATED_PROCESS_STATUS
    defaultBaseInfoShouldBeFound("processStatus.in=" + DEFAULT_PROCESS_STATUS + "," + UPDATED_PROCESS_STATUS);

    // Get all the baseInfoList where processStatus equals to UPDATED_PROCESS_STATUS
    defaultBaseInfoShouldNotBeFound("processStatus.in=" + UPDATED_PROCESS_STATUS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByProcessStatusIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where processStatus is not null
    defaultBaseInfoShouldBeFound("processStatus.specified=true");

    // Get all the baseInfoList where processStatus is null
    defaultBaseInfoShouldNotBeFound("processStatus.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedClassIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedClass equals to DEFAULT_MODIFIED_CLASS
    defaultBaseInfoShouldBeFound("modifiedClass.equals=" + DEFAULT_MODIFIED_CLASS);

    // Get all the baseInfoList where modifiedClass equals to UPDATED_MODIFIED_CLASS
    defaultBaseInfoShouldNotBeFound("modifiedClass.equals=" + UPDATED_MODIFIED_CLASS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedClassIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedClass not equals to DEFAULT_MODIFIED_CLASS
    defaultBaseInfoShouldNotBeFound("modifiedClass.notEquals=" + DEFAULT_MODIFIED_CLASS);

    // Get all the baseInfoList where modifiedClass not equals to UPDATED_MODIFIED_CLASS
    defaultBaseInfoShouldBeFound("modifiedClass.notEquals=" + UPDATED_MODIFIED_CLASS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedClassIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedClass in DEFAULT_MODIFIED_CLASS or UPDATED_MODIFIED_CLASS
    defaultBaseInfoShouldBeFound("modifiedClass.in=" + DEFAULT_MODIFIED_CLASS + "," + UPDATED_MODIFIED_CLASS);

    // Get all the baseInfoList where modifiedClass equals to UPDATED_MODIFIED_CLASS
    defaultBaseInfoShouldNotBeFound("modifiedClass.in=" + UPDATED_MODIFIED_CLASS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedClassIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedClass is not null
    defaultBaseInfoShouldBeFound("modifiedClass.specified=true");

    // Get all the baseInfoList where modifiedClass is null
    defaultBaseInfoShouldNotBeFound("modifiedClass.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedClassContainsSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedClass contains DEFAULT_MODIFIED_CLASS
    defaultBaseInfoShouldBeFound("modifiedClass.contains=" + DEFAULT_MODIFIED_CLASS);

    // Get all the baseInfoList where modifiedClass contains UPDATED_MODIFIED_CLASS
    defaultBaseInfoShouldNotBeFound("modifiedClass.contains=" + UPDATED_MODIFIED_CLASS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedClassNotContainsSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedClass does not contain DEFAULT_MODIFIED_CLASS
    defaultBaseInfoShouldNotBeFound("modifiedClass.doesNotContain=" + DEFAULT_MODIFIED_CLASS);

    // Get all the baseInfoList where modifiedClass does not contain UPDATED_MODIFIED_CLASS
    defaultBaseInfoShouldBeFound("modifiedClass.doesNotContain=" + UPDATED_MODIFIED_CLASS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCreatedDateIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where createdDate equals to DEFAULT_CREATED_DATE
    defaultBaseInfoShouldBeFound("createdDate.equals=" + DEFAULT_CREATED_DATE);

    // Get all the baseInfoList where createdDate equals to UPDATED_CREATED_DATE
    defaultBaseInfoShouldNotBeFound("createdDate.equals=" + UPDATED_CREATED_DATE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCreatedDateIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where createdDate not equals to DEFAULT_CREATED_DATE
    defaultBaseInfoShouldNotBeFound("createdDate.notEquals=" + DEFAULT_CREATED_DATE);

    // Get all the baseInfoList where createdDate not equals to UPDATED_CREATED_DATE
    defaultBaseInfoShouldBeFound("createdDate.notEquals=" + UPDATED_CREATED_DATE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCreatedDateIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
    defaultBaseInfoShouldBeFound("createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE);

    // Get all the baseInfoList where createdDate equals to UPDATED_CREATED_DATE
    defaultBaseInfoShouldNotBeFound("createdDate.in=" + UPDATED_CREATED_DATE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCreatedDateIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where createdDate is not null
    defaultBaseInfoShouldBeFound("createdDate.specified=true");

    // Get all the baseInfoList where createdDate is null
    defaultBaseInfoShouldNotBeFound("createdDate.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedDateIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedDate equals to DEFAULT_MODIFIED_DATE
    defaultBaseInfoShouldBeFound("modifiedDate.equals=" + DEFAULT_MODIFIED_DATE);

    // Get all the baseInfoList where modifiedDate equals to UPDATED_MODIFIED_DATE
    defaultBaseInfoShouldNotBeFound("modifiedDate.equals=" + UPDATED_MODIFIED_DATE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedDateIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedDate not equals to DEFAULT_MODIFIED_DATE
    defaultBaseInfoShouldNotBeFound("modifiedDate.notEquals=" + DEFAULT_MODIFIED_DATE);

    // Get all the baseInfoList where modifiedDate not equals to UPDATED_MODIFIED_DATE
    defaultBaseInfoShouldBeFound("modifiedDate.notEquals=" + UPDATED_MODIFIED_DATE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedDateIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedDate in DEFAULT_MODIFIED_DATE or UPDATED_MODIFIED_DATE
    defaultBaseInfoShouldBeFound("modifiedDate.in=" + DEFAULT_MODIFIED_DATE + "," + UPDATED_MODIFIED_DATE);

    // Get all the baseInfoList where modifiedDate equals to UPDATED_MODIFIED_DATE
    defaultBaseInfoShouldNotBeFound("modifiedDate.in=" + UPDATED_MODIFIED_DATE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedDateIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedDate is not null
    defaultBaseInfoShouldBeFound("modifiedDate.specified=true");

    // Get all the baseInfoList where modifiedDate is null
    defaultBaseInfoShouldNotBeFound("modifiedDate.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByDeletedIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where deleted equals to DEFAULT_DELETED
    defaultBaseInfoShouldBeFound("deleted.equals=" + DEFAULT_DELETED);

    // Get all the baseInfoList where deleted equals to UPDATED_DELETED
    defaultBaseInfoShouldNotBeFound("deleted.equals=" + UPDATED_DELETED);
  }

  @Test
  @Transactional
  void getAllBaseInfosByDeletedIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where deleted not equals to DEFAULT_DELETED
    defaultBaseInfoShouldNotBeFound("deleted.notEquals=" + DEFAULT_DELETED);

    // Get all the baseInfoList where deleted not equals to UPDATED_DELETED
    defaultBaseInfoShouldBeFound("deleted.notEquals=" + UPDATED_DELETED);
  }

  @Test
  @Transactional
  void getAllBaseInfosByDeletedIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where deleted in DEFAULT_DELETED or UPDATED_DELETED
    defaultBaseInfoShouldBeFound("deleted.in=" + DEFAULT_DELETED + "," + UPDATED_DELETED);

    // Get all the baseInfoList where deleted equals to UPDATED_DELETED
    defaultBaseInfoShouldNotBeFound("deleted.in=" + UPDATED_DELETED);
  }

  @Test
  @Transactional
  void getAllBaseInfosByDeletedIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where deleted is not null
    defaultBaseInfoShouldBeFound("deleted.specified=true");

    // Get all the baseInfoList where deleted is null
    defaultBaseInfoShouldNotBeFound("deleted.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex equals to DEFAULT_PRIORITY_INDEX
    defaultBaseInfoShouldBeFound("priorityIndex.equals=" + DEFAULT_PRIORITY_INDEX);

    // Get all the baseInfoList where priorityIndex equals to UPDATED_PRIORITY_INDEX
    defaultBaseInfoShouldNotBeFound("priorityIndex.equals=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex not equals to DEFAULT_PRIORITY_INDEX
    defaultBaseInfoShouldNotBeFound("priorityIndex.notEquals=" + DEFAULT_PRIORITY_INDEX);

    // Get all the baseInfoList where priorityIndex not equals to UPDATED_PRIORITY_INDEX
    defaultBaseInfoShouldBeFound("priorityIndex.notEquals=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex in DEFAULT_PRIORITY_INDEX or UPDATED_PRIORITY_INDEX
    defaultBaseInfoShouldBeFound("priorityIndex.in=" + DEFAULT_PRIORITY_INDEX + "," + UPDATED_PRIORITY_INDEX);

    // Get all the baseInfoList where priorityIndex equals to UPDATED_PRIORITY_INDEX
    defaultBaseInfoShouldNotBeFound("priorityIndex.in=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex is not null
    defaultBaseInfoShouldBeFound("priorityIndex.specified=true");

    // Get all the baseInfoList where priorityIndex is null
    defaultBaseInfoShouldNotBeFound("priorityIndex.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex is greater than or equal to DEFAULT_PRIORITY_INDEX
    defaultBaseInfoShouldBeFound("priorityIndex.greaterThanOrEqual=" + DEFAULT_PRIORITY_INDEX);

    // Get all the baseInfoList where priorityIndex is greater than or equal to UPDATED_PRIORITY_INDEX
    defaultBaseInfoShouldNotBeFound("priorityIndex.greaterThanOrEqual=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex is less than or equal to DEFAULT_PRIORITY_INDEX
    defaultBaseInfoShouldBeFound("priorityIndex.lessThanOrEqual=" + DEFAULT_PRIORITY_INDEX);

    // Get all the baseInfoList where priorityIndex is less than or equal to SMALLER_PRIORITY_INDEX
    defaultBaseInfoShouldNotBeFound("priorityIndex.lessThanOrEqual=" + SMALLER_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsLessThanSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex is less than DEFAULT_PRIORITY_INDEX
    defaultBaseInfoShouldNotBeFound("priorityIndex.lessThan=" + DEFAULT_PRIORITY_INDEX);

    // Get all the baseInfoList where priorityIndex is less than UPDATED_PRIORITY_INDEX
    defaultBaseInfoShouldBeFound("priorityIndex.lessThan=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsGreaterThanSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex is greater than DEFAULT_PRIORITY_INDEX
    defaultBaseInfoShouldNotBeFound("priorityIndex.greaterThan=" + DEFAULT_PRIORITY_INDEX);

    // Get all the baseInfoList where priorityIndex is greater than SMALLER_PRIORITY_INDEX
    defaultBaseInfoShouldBeFound("priorityIndex.greaterThan=" + SMALLER_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse equals to DEFAULT_COUNT_USE
    defaultBaseInfoShouldBeFound("countUse.equals=" + DEFAULT_COUNT_USE);

    // Get all the baseInfoList where countUse equals to UPDATED_COUNT_USE
    defaultBaseInfoShouldNotBeFound("countUse.equals=" + UPDATED_COUNT_USE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse not equals to DEFAULT_COUNT_USE
    defaultBaseInfoShouldNotBeFound("countUse.notEquals=" + DEFAULT_COUNT_USE);

    // Get all the baseInfoList where countUse not equals to UPDATED_COUNT_USE
    defaultBaseInfoShouldBeFound("countUse.notEquals=" + UPDATED_COUNT_USE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse in DEFAULT_COUNT_USE or UPDATED_COUNT_USE
    defaultBaseInfoShouldBeFound("countUse.in=" + DEFAULT_COUNT_USE + "," + UPDATED_COUNT_USE);

    // Get all the baseInfoList where countUse equals to UPDATED_COUNT_USE
    defaultBaseInfoShouldNotBeFound("countUse.in=" + UPDATED_COUNT_USE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse is not null
    defaultBaseInfoShouldBeFound("countUse.specified=true");

    // Get all the baseInfoList where countUse is null
    defaultBaseInfoShouldNotBeFound("countUse.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse is greater than or equal to DEFAULT_COUNT_USE
    defaultBaseInfoShouldBeFound("countUse.greaterThanOrEqual=" + DEFAULT_COUNT_USE);

    // Get all the baseInfoList where countUse is greater than or equal to UPDATED_COUNT_USE
    defaultBaseInfoShouldNotBeFound("countUse.greaterThanOrEqual=" + UPDATED_COUNT_USE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse is less than or equal to DEFAULT_COUNT_USE
    defaultBaseInfoShouldBeFound("countUse.lessThanOrEqual=" + DEFAULT_COUNT_USE);

    // Get all the baseInfoList where countUse is less than or equal to SMALLER_COUNT_USE
    defaultBaseInfoShouldNotBeFound("countUse.lessThanOrEqual=" + SMALLER_COUNT_USE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsLessThanSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse is less than DEFAULT_COUNT_USE
    defaultBaseInfoShouldNotBeFound("countUse.lessThan=" + DEFAULT_COUNT_USE);

    // Get all the baseInfoList where countUse is less than UPDATED_COUNT_USE
    defaultBaseInfoShouldBeFound("countUse.lessThan=" + UPDATED_COUNT_USE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsGreaterThanSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse is greater than DEFAULT_COUNT_USE
    defaultBaseInfoShouldNotBeFound("countUse.greaterThan=" + DEFAULT_COUNT_USE);

    // Get all the baseInfoList where countUse is greater than SMALLER_COUNT_USE
    defaultBaseInfoShouldBeFound("countUse.greaterThan=" + SMALLER_COUNT_USE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByHistoryUpdateIsEqualToSomething() throws Exception
  {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    HistoryUpdate historyUpdate = HistoryUpdateResourceIT.createEntity(em);
    em.persist(historyUpdate);
    em.flush();
    baseInfo.addHistoryUpdate(historyUpdate);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long historyUpdateId = historyUpdate.getId();

    // Get all the baseInfoList where historyUpdate equals to historyUpdateId
    defaultBaseInfoShouldBeFound("historyUpdateId.equals=" + historyUpdateId);

    // Get all the baseInfoList where historyUpdate equals to (historyUpdateId + 1)
    defaultBaseInfoShouldNotBeFound("historyUpdateId.equals=" + (historyUpdateId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByClassInfoIsEqualToSomething() throws Exception
  {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    ClassInfo classInfo = ClassInfoResourceIT.createEntity(em);
    em.persist(classInfo);
    em.flush();
    baseInfo.setClassInfo(classInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long classInfoId = classInfo.getId();

    // Get all the baseInfoList where classInfo equals to classInfoId
    defaultBaseInfoShouldBeFound("classInfoId.equals=" + classInfoId);

    // Get all the baseInfoList where classInfo equals to (classInfoId + 1)
    defaultBaseInfoShouldNotBeFound("classInfoId.equals=" + (classInfoId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByUserProfileIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    UserProfile userProfile = UserProfileResourceIT.createEntity(em);
    em.persist(userProfile);
    em.flush();
    baseInfo.setUserProfile(userProfile);
    userProfile.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long userProfileId = userProfile.getId();

    // Get all the baseInfoList where userProfile equals to userProfileId
    defaultBaseInfoShouldBeFound("userProfileId.equals=" + userProfileId);

    // Get all the baseInfoList where userProfile equals to (userProfileId + 1)
    defaultBaseInfoShouldNotBeFound("userProfileId.equals=" + (userProfileId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByAccountStatusIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    AccountStatus accountStatus = AccountStatusResourceIT.createEntity(em);
    em.persist(accountStatus);
    em.flush();
    baseInfo.setAccountStatus(accountStatus);
    accountStatus.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long accountStatusId = accountStatus.getId();

    // Get all the baseInfoList where accountStatus equals to accountStatusId
    defaultBaseInfoShouldBeFound("accountStatusId.equals=" + accountStatusId);

    // Get all the baseInfoList where accountStatus equals to (accountStatusId + 1)
    defaultBaseInfoShouldNotBeFound("accountStatusId.equals=" + (accountStatusId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByDeviceStatusIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    DeviceStatus deviceStatus = DeviceStatusResourceIT.createEntity(em);
    em.persist(deviceStatus);
    em.flush();
    baseInfo.setDeviceStatus(deviceStatus);
    deviceStatus.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long deviceStatusId = deviceStatus.getId();

    // Get all the baseInfoList where deviceStatus equals to deviceStatusId
    defaultBaseInfoShouldBeFound("deviceStatusId.equals=" + deviceStatusId);

    // Get all the baseInfoList where deviceStatus equals to (deviceStatusId + 1)
    defaultBaseInfoShouldNotBeFound("deviceStatusId.equals=" + (deviceStatusId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByFriendIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    Friend friend = FriendResourceIT.createEntity(em);
    em.persist(friend);
    em.flush();
    baseInfo.setFriend(friend);
    friend.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long friendId = friend.getId();

    // Get all the baseInfoList where friend equals to friendId
    defaultBaseInfoShouldBeFound("friendId.equals=" + friendId);

    // Get all the baseInfoList where friend equals to (friendId + 1)
    defaultBaseInfoShouldNotBeFound("friendId.equals=" + (friendId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByFollowUserIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    FollowUser followUser = FollowUserResourceIT.createEntity(em);
    em.persist(followUser);
    em.flush();
    baseInfo.setFollowUser(followUser);
    followUser.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long followUserId = followUser.getId();

    // Get all the baseInfoList where followUser equals to followUserId
    defaultBaseInfoShouldBeFound("followUserId.equals=" + followUserId);

    // Get all the baseInfoList where followUser equals to (followUserId + 1)
    defaultBaseInfoShouldNotBeFound("followUserId.equals=" + (followUserId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByFollowGroupIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    FollowGroup followGroup = FollowGroupResourceIT.createEntity(em);
    em.persist(followGroup);
    em.flush();
    baseInfo.setFollowGroup(followGroup);
    followGroup.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long followGroupId = followGroup.getId();

    // Get all the baseInfoList where followGroup equals to followGroupId
    defaultBaseInfoShouldBeFound("followGroupId.equals=" + followGroupId);

    // Get all the baseInfoList where followGroup equals to (followGroupId + 1)
    defaultBaseInfoShouldNotBeFound("followGroupId.equals=" + (followGroupId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByFollowPageIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    FollowPage followPage = FollowPageResourceIT.createEntity(em);
    em.persist(followPage);
    em.flush();
    baseInfo.setFollowPage(followPage);
    followPage.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long followPageId = followPage.getId();

    // Get all the baseInfoList where followPage equals to followPageId
    defaultBaseInfoShouldBeFound("followPageId.equals=" + followPageId);

    // Get all the baseInfoList where followPage equals to (followPageId + 1)
    defaultBaseInfoShouldNotBeFound("followPageId.equals=" + (followPageId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByFileInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    FileInfo fileInfo = FileInfoResourceIT.createEntity(em);
    em.persist(fileInfo);
    em.flush();
    baseInfo.setFileInfo(fileInfo);
    fileInfo.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long fileInfoId = fileInfo.getId();

    // Get all the baseInfoList where fileInfo equals to fileInfoId
    defaultBaseInfoShouldBeFound("fileInfoId.equals=" + fileInfoId);

    // Get all the baseInfoList where fileInfo equals to (fileInfoId + 1)
    defaultBaseInfoShouldNotBeFound("fileInfoId.equals=" + (fileInfoId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByPagePostIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    PagePost pagePost = PagePostResourceIT.createEntity(em);
    em.persist(pagePost);
    em.flush();
    baseInfo.setPagePost(pagePost);
    pagePost.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long pagePostId = pagePost.getId();

    // Get all the baseInfoList where pagePost equals to pagePostId
    defaultBaseInfoShouldBeFound("pagePostId.equals=" + pagePostId);

    // Get all the baseInfoList where pagePost equals to (pagePostId + 1)
    defaultBaseInfoShouldNotBeFound("pagePostId.equals=" + (pagePostId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByPageProfileIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    PageProfile pageProfile = PageProfileResourceIT.createEntity(em);
    em.persist(pageProfile);
    em.flush();
    baseInfo.setPageProfile(pageProfile);
    pageProfile.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long pageProfileId = pageProfile.getId();

    // Get all the baseInfoList where pageProfile equals to pageProfileId
    defaultBaseInfoShouldBeFound("pageProfileId.equals=" + pageProfileId);

    // Get all the baseInfoList where pageProfile equals to (pageProfileId + 1)
    defaultBaseInfoShouldNotBeFound("pageProfileId.equals=" + (pageProfileId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByGroupPostIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    GroupPost groupPost = GroupPostResourceIT.createEntity(em);
    em.persist(groupPost);
    em.flush();
    baseInfo.setGroupPost(groupPost);
    groupPost.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long groupPostId = groupPost.getId();

    // Get all the baseInfoList where groupPost equals to groupPostId
    defaultBaseInfoShouldBeFound("groupPostId.equals=" + groupPostId);

    // Get all the baseInfoList where groupPost equals to (groupPostId + 1)
    defaultBaseInfoShouldNotBeFound("groupPostId.equals=" + (groupPostId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByPostIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    Post post = PostResourceIT.createEntity(em);
    em.persist(post);
    em.flush();
    baseInfo.setPost(post);
    post.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long postId = post.getId();

    // Get all the baseInfoList where post equals to postId
    defaultBaseInfoShouldBeFound("postId.equals=" + postId);

    // Get all the baseInfoList where post equals to (postId + 1)
    defaultBaseInfoShouldNotBeFound("postId.equals=" + (postId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByPostCommentIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    PostComment postComment = PostCommentResourceIT.createEntity(em);
    em.persist(postComment);
    em.flush();
    baseInfo.setPostComment(postComment);
    postComment.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long postCommentId = postComment.getId();

    // Get all the baseInfoList where postComment equals to postCommentId
    defaultBaseInfoShouldBeFound("postCommentId.equals=" + postCommentId);

    // Get all the baseInfoList where postComment equals to (postCommentId + 1)
    defaultBaseInfoShouldNotBeFound("postCommentId.equals=" + (postCommentId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByPostLikeIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    PostLike postLike = PostLikeResourceIT.createEntity(em);
    em.persist(postLike);
    em.flush();
    baseInfo.setPostLike(postLike);
    postLike.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long postLikeId = postLike.getId();

    // Get all the baseInfoList where postLike equals to postLikeId
    defaultBaseInfoShouldBeFound("postLikeId.equals=" + postLikeId);

    // Get all the baseInfoList where postLike equals to (postLikeId + 1)
    defaultBaseInfoShouldNotBeFound("postLikeId.equals=" + (postLikeId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByGroupProfileIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    GroupProfile groupProfile = GroupProfileResourceIT.createEntity(em);
    em.persist(groupProfile);
    em.flush();
    baseInfo.setGroupProfile(groupProfile);
    groupProfile.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long groupProfileId = groupProfile.getId();

    // Get all the baseInfoList where groupProfile equals to groupProfileId
    defaultBaseInfoShouldBeFound("groupProfileId.equals=" + groupProfileId);

    // Get all the baseInfoList where groupProfile equals to (groupProfileId + 1)
    defaultBaseInfoShouldNotBeFound("groupProfileId.equals=" + (groupProfileId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByNewsFeedIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    NewsFeed newsFeed = NewsFeedResourceIT.createEntity(em);
    em.persist(newsFeed);
    em.flush();
    baseInfo.setNewsFeed(newsFeed);
    newsFeed.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long newsFeedId = newsFeed.getId();

    // Get all the baseInfoList where newsFeed equals to newsFeedId
    defaultBaseInfoShouldBeFound("newsFeedId.equals=" + newsFeedId);

    // Get all the baseInfoList where newsFeed equals to (newsFeedId + 1)
    defaultBaseInfoShouldNotBeFound("newsFeedId.equals=" + (newsFeedId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByMessageGroupIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    MessageGroup messageGroup = MessageGroupResourceIT.createEntity(em);
    em.persist(messageGroup);
    em.flush();
    baseInfo.setMessageGroup(messageGroup);
    messageGroup.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long messageGroupId = messageGroup.getId();

    // Get all the baseInfoList where messageGroup equals to messageGroupId
    defaultBaseInfoShouldBeFound("messageGroupId.equals=" + messageGroupId);

    // Get all the baseInfoList where messageGroup equals to (messageGroupId + 1)
    defaultBaseInfoShouldNotBeFound("messageGroupId.equals=" + (messageGroupId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByMessageContentIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    MessageContent messageContent = MessageContentResourceIT.createEntity(em);
    em.persist(messageContent);
    em.flush();
    baseInfo.setMessageContent(messageContent);
    messageContent.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long messageContentId = messageContent.getId();

    // Get all the baseInfoList where messageContent equals to messageContentId
    defaultBaseInfoShouldBeFound("messageContentId.equals=" + messageContentId);

    // Get all the baseInfoList where messageContent equals to (messageContentId + 1)
    defaultBaseInfoShouldNotBeFound("messageContentId.equals=" + (messageContentId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByRankUserIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    RankUser rankUser = RankUserResourceIT.createEntity(em);
    em.persist(rankUser);
    em.flush();
    baseInfo.setRankUser(rankUser);
    rankUser.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long rankUserId = rankUser.getId();

    // Get all the baseInfoList where rankUser equals to rankUserId
    defaultBaseInfoShouldBeFound("rankUserId.equals=" + rankUserId);

    // Get all the baseInfoList where rankUser equals to (rankUserId + 1)
    defaultBaseInfoShouldNotBeFound("rankUserId.equals=" + (rankUserId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByRankGroupIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    RankGroup rankGroup = RankGroupResourceIT.createEntity(em);
    em.persist(rankGroup);
    em.flush();
    baseInfo.setRankGroup(rankGroup);
    rankGroup.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long rankGroupId = rankGroup.getId();

    // Get all the baseInfoList where rankGroup equals to rankGroupId
    defaultBaseInfoShouldBeFound("rankGroupId.equals=" + rankGroupId);

    // Get all the baseInfoList where rankGroup equals to (rankGroupId + 1)
    defaultBaseInfoShouldNotBeFound("rankGroupId.equals=" + (rankGroupId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByNotificationIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    Notification notification = NotificationResourceIT.createEntity(em);
    em.persist(notification);
    em.flush();
    baseInfo.setNotification(notification);
    notification.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long notificationId = notification.getId();

    // Get all the baseInfoList where notification equals to notificationId
    defaultBaseInfoShouldBeFound("notificationId.equals=" + notificationId);

    // Get all the baseInfoList where notification equals to (notificationId + 1)
    defaultBaseInfoShouldNotBeFound("notificationId.equals=" + (notificationId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByAlbumIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    Album album = AlbumResourceIT.createEntity(em);
    em.persist(album);
    em.flush();
    baseInfo.setAlbum(album);
    album.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long albumId = album.getId();

    // Get all the baseInfoList where album equals to albumId
    defaultBaseInfoShouldBeFound("albumId.equals=" + albumId);

    // Get all the baseInfoList where album equals to (albumId + 1)
    defaultBaseInfoShouldNotBeFound("albumId.equals=" + (albumId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByVideoIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    Video video = VideoResourceIT.createEntity(em);
    em.persist(video);
    em.flush();
    baseInfo.setVideo(video);
    video.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long videoId = video.getId();

    // Get all the baseInfoList where video equals to videoId
    defaultBaseInfoShouldBeFound("videoId.equals=" + videoId);

    // Get all the baseInfoList where video equals to (videoId + 1)
    defaultBaseInfoShouldNotBeFound("videoId.equals=" + (videoId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByImageIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    Image image = ImageResourceIT.createEntity(em);
    em.persist(image);
    em.flush();
    baseInfo.setImage(image);
    image.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long imageId = image.getId();

    // Get all the baseInfoList where image equals to imageId
    defaultBaseInfoShouldBeFound("imageId.equals=" + imageId);

    // Get all the baseInfoList where image equals to (imageId + 1)
    defaultBaseInfoShouldNotBeFound("imageId.equals=" + (imageId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByVideoStreamIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    VideoStream videoStream = VideoStreamResourceIT.createEntity(em);
    em.persist(videoStream);
    em.flush();
    baseInfo.setVideoStream(videoStream);
    videoStream.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long videoStreamId = videoStream.getId();

    // Get all the baseInfoList where videoStream equals to videoStreamId
    defaultBaseInfoShouldBeFound("videoStreamId.equals=" + videoStreamId);

    // Get all the baseInfoList where videoStream equals to (videoStreamId + 1)
    defaultBaseInfoShouldNotBeFound("videoStreamId.equals=" + (videoStreamId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByVideoLiveStreamBufferIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    VideoLiveStreamBuffer videoLiveStreamBuffer = VideoLiveStreamBufferResourceIT.createEntity(em);
    em.persist(videoLiveStreamBuffer);
    em.flush();
    baseInfo.setVideoLiveStreamBuffer(videoLiveStreamBuffer);
    videoLiveStreamBuffer.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long videoLiveStreamBufferId = videoLiveStreamBuffer.getId();

    // Get all the baseInfoList where videoLiveStreamBuffer equals to videoLiveStreamBufferId
    defaultBaseInfoShouldBeFound("videoLiveStreamBufferId.equals=" + videoLiveStreamBufferId);

    // Get all the baseInfoList where videoLiveStreamBuffer equals to (videoLiveStreamBufferId + 1)
    defaultBaseInfoShouldNotBeFound("videoLiveStreamBufferId.equals=" + (videoLiveStreamBufferId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByTopicInterestIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    TopicInterest topicInterest = TopicInterestResourceIT.createEntity(em);
    em.persist(topicInterest);
    em.flush();
    baseInfo.setTopicInterest(topicInterest);
    topicInterest.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long topicInterestId = topicInterest.getId();

    // Get all the baseInfoList where topicInterest equals to topicInterestId
    defaultBaseInfoShouldBeFound("topicInterestId.equals=" + topicInterestId);

    // Get all the baseInfoList where topicInterest equals to (topicInterestId + 1)
    defaultBaseInfoShouldNotBeFound("topicInterestId.equals=" + (topicInterestId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByTodoListIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    TodoList todoList = TodoListResourceIT.createEntity(em);
    em.persist(todoList);
    em.flush();
    baseInfo.setTodoList(todoList);
    todoList.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long todoListId = todoList.getId();

    // Get all the baseInfoList where todoList equals to todoListId
    defaultBaseInfoShouldBeFound("todoListId.equals=" + todoListId);

    // Get all the baseInfoList where todoList equals to (todoListId + 1)
    defaultBaseInfoShouldNotBeFound("todoListId.equals=" + (todoListId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByEventIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    Event event = EventResourceIT.createEntity(em);
    em.persist(event);
    em.flush();
    baseInfo.setEvent(event);
    event.setBaseInfo(baseInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long eventId = event.getId();

    // Get all the baseInfoList where event equals to eventId
    defaultBaseInfoShouldBeFound("eventId.equals=" + eventId);

    // Get all the baseInfoList where event equals to (eventId + 1)
    defaultBaseInfoShouldNotBeFound("eventId.equals=" + (eventId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByCreatedByIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    MasterUser createdBy = MasterUserResourceIT.createEntity(em);
    em.persist(createdBy);
    em.flush();
    baseInfo.setCreatedBy(createdBy);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long createdById = createdBy.getId();

    // Get all the baseInfoList where createdBy equals to createdById
    defaultBaseInfoShouldBeFound("createdById.equals=" + createdById);

    // Get all the baseInfoList where createdBy equals to (createdById + 1)
    defaultBaseInfoShouldNotBeFound("createdById.equals=" + (createdById + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedByIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    MasterUser modifiedBy = MasterUserResourceIT.createEntity(em);
    em.persist(modifiedBy);
    em.flush();
    baseInfo.setModifiedBy(modifiedBy);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long modifiedById = modifiedBy.getId();

    // Get all the baseInfoList where modifiedBy equals to modifiedById
    defaultBaseInfoShouldBeFound("modifiedById.equals=" + modifiedById);

    // Get all the baseInfoList where modifiedBy equals to (modifiedById + 1)
    defaultBaseInfoShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByOwnerIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    MasterUser owner = MasterUserResourceIT.createEntity(em);
    em.persist(owner);
    em.flush();
    baseInfo.setOwner(owner);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long ownerId = owner.getId();

    // Get all the baseInfoList where owner equals to ownerId
    defaultBaseInfoShouldBeFound("ownerId.equals=" + ownerId);

    // Get all the baseInfoList where owner equals to (ownerId + 1)
    defaultBaseInfoShouldNotBeFound("ownerId.equals=" + (ownerId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByPermissionIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    Permission permission = PermissionResourceIT.createEntity(em);
    em.persist(permission);
    em.flush();
    baseInfo.addPermission(permission);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long permissionId = permission.getId();

    // Get all the baseInfoList where permission equals to permissionId
    defaultBaseInfoShouldBeFound("permissionId.equals=" + permissionId);

    // Get all the baseInfoList where permission equals to (permissionId + 1)
    defaultBaseInfoShouldNotBeFound("permissionId.equals=" + (permissionId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultBaseInfoShouldBeFound(String filter) throws Exception {
    restBaseInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(baseInfo.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].processStatus").value(hasItem(DEFAULT_PROCESS_STATUS.toString())))
      .andExpect(jsonPath("$.[*].modifiedClass").value(hasItem(DEFAULT_MODIFIED_CLASS)))
      .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
      .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
      .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
      .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
      .andExpect(jsonPath("$.[*].priorityIndex").value(hasItem(DEFAULT_PRIORITY_INDEX.intValue())))
      .andExpect(jsonPath("$.[*].countUse").value(hasItem(DEFAULT_COUNT_USE.intValue())));

    // Check, that the count call also returns 1
    restBaseInfoMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultBaseInfoShouldNotBeFound(String filter) throws Exception {
    restBaseInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restBaseInfoMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingBaseInfo() throws Exception {
    // Get the baseInfo
    restBaseInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewBaseInfo() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();

    // Update the baseInfo
    BaseInfo updatedBaseInfo = baseInfoRepository.findById(baseInfo.getId()).get();
    // Disconnect from session so that the updates on updatedBaseInfo are not directly saved in db
    em.detach(updatedBaseInfo);
    updatedBaseInfo
      .uuid(UPDATED_UUID)
      .processStatus(UPDATED_PROCESS_STATUS)
      .modifiedClass(UPDATED_MODIFIED_CLASS)
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .notes(UPDATED_NOTES)
      .deleted(UPDATED_DELETED)
      .priorityIndex(UPDATED_PRIORITY_INDEX)
      .countUse(UPDATED_COUNT_USE);
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(updatedBaseInfo);

    restBaseInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, baseInfoDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(baseInfoDTO))
      )
      .andExpect(status().isOk());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);
    BaseInfo testBaseInfo = baseInfoList.get(baseInfoList.size() - 1);
    assertThat(testBaseInfo.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testBaseInfo.getProcessStatus()).isEqualTo(UPDATED_PROCESS_STATUS);
    assertThat(testBaseInfo.getModifiedClass()).isEqualTo(UPDATED_MODIFIED_CLASS);
    assertThat(testBaseInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testBaseInfo.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    assertThat(testBaseInfo.getNotes()).isEqualTo(UPDATED_NOTES);
    assertThat(testBaseInfo.getDeleted()).isEqualTo(UPDATED_DELETED);
    assertThat(testBaseInfo.getPriorityIndex()).isEqualTo(UPDATED_PRIORITY_INDEX);
    assertThat(testBaseInfo.getCountUse()).isEqualTo(UPDATED_COUNT_USE);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository).save(testBaseInfo);
  }

  @Test
  @Transactional
  void putNonExistingBaseInfo() throws Exception {
    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();
    baseInfo.setId(count.incrementAndGet());

    // Create the BaseInfo
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restBaseInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, baseInfoDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(baseInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(0)).save(baseInfo);
  }

  @Test
  @Transactional
  void putWithIdMismatchBaseInfo() throws Exception {
    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();
    baseInfo.setId(count.incrementAndGet());

    // Create the BaseInfo
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restBaseInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(baseInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(0)).save(baseInfo);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamBaseInfo() throws Exception {
    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();
    baseInfo.setId(count.incrementAndGet());

    // Create the BaseInfo
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restBaseInfoMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(baseInfoDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(0)).save(baseInfo);
  }

  @Test
  @Transactional
  void partialUpdateBaseInfoWithPatch() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();

    // Update the baseInfo using partial update
    BaseInfo partialUpdatedBaseInfo = new BaseInfo();
    partialUpdatedBaseInfo.setId(baseInfo.getId());

    partialUpdatedBaseInfo
      .modifiedClass(UPDATED_MODIFIED_CLASS)
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .notes(UPDATED_NOTES)
      .priorityIndex(UPDATED_PRIORITY_INDEX)
      .countUse(UPDATED_COUNT_USE);

    restBaseInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedBaseInfo.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBaseInfo))
      )
      .andExpect(status().isOk());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);
    BaseInfo testBaseInfo = baseInfoList.get(baseInfoList.size() - 1);
    assertThat(testBaseInfo.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testBaseInfo.getProcessStatus()).isEqualTo(DEFAULT_PROCESS_STATUS);
    assertThat(testBaseInfo.getModifiedClass()).isEqualTo(UPDATED_MODIFIED_CLASS);
    assertThat(testBaseInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testBaseInfo.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    assertThat(testBaseInfo.getNotes()).isEqualTo(UPDATED_NOTES);
    assertThat(testBaseInfo.getDeleted()).isEqualTo(DEFAULT_DELETED);
    assertThat(testBaseInfo.getPriorityIndex()).isEqualTo(UPDATED_PRIORITY_INDEX);
    assertThat(testBaseInfo.getCountUse()).isEqualTo(UPDATED_COUNT_USE);
  }

  @Test
  @Transactional
  void fullUpdateBaseInfoWithPatch() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();

    // Update the baseInfo using partial update
    BaseInfo partialUpdatedBaseInfo = new BaseInfo();
    partialUpdatedBaseInfo.setId(baseInfo.getId());

    partialUpdatedBaseInfo
      .uuid(UPDATED_UUID)
      .processStatus(UPDATED_PROCESS_STATUS)
      .modifiedClass(UPDATED_MODIFIED_CLASS)
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .notes(UPDATED_NOTES)
      .deleted(UPDATED_DELETED)
      .priorityIndex(UPDATED_PRIORITY_INDEX)
      .countUse(UPDATED_COUNT_USE);

    restBaseInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedBaseInfo.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBaseInfo))
      )
      .andExpect(status().isOk());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);
    BaseInfo testBaseInfo = baseInfoList.get(baseInfoList.size() - 1);
    assertThat(testBaseInfo.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testBaseInfo.getProcessStatus()).isEqualTo(UPDATED_PROCESS_STATUS);
    assertThat(testBaseInfo.getModifiedClass()).isEqualTo(UPDATED_MODIFIED_CLASS);
    assertThat(testBaseInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testBaseInfo.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    assertThat(testBaseInfo.getNotes()).isEqualTo(UPDATED_NOTES);
    assertThat(testBaseInfo.getDeleted()).isEqualTo(UPDATED_DELETED);
    assertThat(testBaseInfo.getPriorityIndex()).isEqualTo(UPDATED_PRIORITY_INDEX);
    assertThat(testBaseInfo.getCountUse()).isEqualTo(UPDATED_COUNT_USE);
  }

  @Test
  @Transactional
  void patchNonExistingBaseInfo() throws Exception {
    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();
    baseInfo.setId(count.incrementAndGet());

    // Create the BaseInfo
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restBaseInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, baseInfoDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(baseInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(0)).save(baseInfo);
  }

  @Test
  @Transactional
  void patchWithIdMismatchBaseInfo() throws Exception {
    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();
    baseInfo.setId(count.incrementAndGet());

    // Create the BaseInfo
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restBaseInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(baseInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(0)).save(baseInfo);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamBaseInfo() throws Exception {
    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();
    baseInfo.setId(count.incrementAndGet());

    // Create the BaseInfo
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restBaseInfoMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(baseInfoDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(0)).save(baseInfo);
  }

  @Test
  @Transactional
  void deleteBaseInfo() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    int databaseSizeBeforeDelete = baseInfoRepository.findAll().size();

    // Delete the baseInfo
    restBaseInfoMockMvc
      .perform(delete(ENTITY_API_URL_ID, baseInfo.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(1)).deleteById(baseInfo.getId());
  }

  @Test
  @Transactional
  void searchBaseInfo() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    when(mockBaseInfoSearchRepository.search(queryStringQuery("id:" + baseInfo.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(baseInfo), PageRequest.of(0, 1), 1));

    // Search the baseInfo
    restBaseInfoMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + baseInfo.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(baseInfo.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].processStatus").value(hasItem(DEFAULT_PROCESS_STATUS.toString())))
      .andExpect(jsonPath("$.[*].modifiedClass").value(hasItem(DEFAULT_MODIFIED_CLASS)))
      .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
      .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
      .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
      .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
      .andExpect(jsonPath("$.[*].priorityIndex").value(hasItem(DEFAULT_PRIORITY_INDEX.intValue())))
      .andExpect(jsonPath("$.[*].countUse").value(hasItem(DEFAULT_COUNT_USE.intValue())));
  }
}
