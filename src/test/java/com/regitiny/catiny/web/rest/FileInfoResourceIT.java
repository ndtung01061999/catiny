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
import com.regitiny.catiny.domain.FileInfo;
import com.regitiny.catiny.repository.FileInfoRepository;
import com.regitiny.catiny.repository.search.FileInfoSearchRepository;
import com.regitiny.catiny.service.criteria.FileInfoCriteria;
import com.regitiny.catiny.service.dto.FileInfoDTO;
import com.regitiny.catiny.service.mapper.FileInfoMapper;
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
 * Integration tests for the {@link FileInfoResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class FileInfoResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_NAME_FILE = "AAAAAAAAAA";
  private static final String UPDATED_NAME_FILE = "BBBBBBBBBB";

  private static final String DEFAULT_TYPE_FILE = "AAAAAAAAAA";
  private static final String UPDATED_TYPE_FILE = "BBBBBBBBBB";

  private static final String DEFAULT_PATH = "AAAAAAAAAA";
  private static final String UPDATED_PATH = "BBBBBBBBBB";

  private static final Long DEFAULT_DATA_SIZE = 1L;
  private static final Long UPDATED_DATA_SIZE = 2L;
  private static final Long SMALLER_DATA_SIZE = 1L - 1L;

  private static final String ENTITY_API_URL = "/api/file-infos";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/file-infos";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private FileInfoRepository fileInfoRepository;

  @Autowired
  private FileInfoMapper fileInfoMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.FileInfoSearchRepositoryMockConfiguration
   */
  @Autowired
  private FileInfoSearchRepository mockFileInfoSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restFileInfoMockMvc;

  private FileInfo fileInfo;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static FileInfo createEntity(EntityManager em) {
    FileInfo fileInfo = new FileInfo()
      .uuid(DEFAULT_UUID)
      .nameFile(DEFAULT_NAME_FILE)
      .typeFile(DEFAULT_TYPE_FILE)
      .path(DEFAULT_PATH)
      .dataSize(DEFAULT_DATA_SIZE);
    return fileInfo;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static FileInfo createUpdatedEntity(EntityManager em) {
    FileInfo fileInfo = new FileInfo()
      .uuid(UPDATED_UUID)
      .nameFile(UPDATED_NAME_FILE)
      .typeFile(UPDATED_TYPE_FILE)
      .path(UPDATED_PATH)
      .dataSize(UPDATED_DATA_SIZE);
    return fileInfo;
  }

  @BeforeEach
  public void initTest() {
    fileInfo = createEntity(em);
  }

  @Test
  @Transactional
  void createFileInfo() throws Exception {
    int databaseSizeBeforeCreate = fileInfoRepository.findAll().size();
    // Create the FileInfo
    FileInfoDTO fileInfoDTO = fileInfoMapper.toDto(fileInfo);
    restFileInfoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fileInfoDTO)))
      .andExpect(status().isCreated());

    // Validate the FileInfo in the database
    List<FileInfo> fileInfoList = fileInfoRepository.findAll();
    assertThat(fileInfoList).hasSize(databaseSizeBeforeCreate + 1);
    FileInfo testFileInfo = fileInfoList.get(fileInfoList.size() - 1);
    assertThat(testFileInfo.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testFileInfo.getNameFile()).isEqualTo(DEFAULT_NAME_FILE);
    assertThat(testFileInfo.getTypeFile()).isEqualTo(DEFAULT_TYPE_FILE);
    assertThat(testFileInfo.getPath()).isEqualTo(DEFAULT_PATH);
    assertThat(testFileInfo.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);

    // Validate the FileInfo in Elasticsearch
    verify(mockFileInfoSearchRepository, times(1)).save(testFileInfo);
  }

  @Test
  @Transactional
  void createFileInfoWithExistingId() throws Exception {
    // Create the FileInfo with an existing ID
    fileInfo.setId(1L);
    FileInfoDTO fileInfoDTO = fileInfoMapper.toDto(fileInfo);

    int databaseSizeBeforeCreate = fileInfoRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restFileInfoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fileInfoDTO)))
      .andExpect(status().isBadRequest());

    // Validate the FileInfo in the database
    List<FileInfo> fileInfoList = fileInfoRepository.findAll();
    assertThat(fileInfoList).hasSize(databaseSizeBeforeCreate);

    // Validate the FileInfo in Elasticsearch
    verify(mockFileInfoSearchRepository, times(0)).save(fileInfo);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = fileInfoRepository.findAll().size();
    // set the field null
    fileInfo.setUuid(null);

    // Create the FileInfo, which fails.
    FileInfoDTO fileInfoDTO = fileInfoMapper.toDto(fileInfo);

    restFileInfoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fileInfoDTO)))
      .andExpect(status().isBadRequest());

    List<FileInfo> fileInfoList = fileInfoRepository.findAll();
    assertThat(fileInfoList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllFileInfos() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList
    restFileInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(fileInfo.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].nameFile").value(hasItem(DEFAULT_NAME_FILE)))
      .andExpect(jsonPath("$.[*].typeFile").value(hasItem(DEFAULT_TYPE_FILE)))
      .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
      .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE.intValue())));
  }

  @Test
  @Transactional
  void getFileInfo() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get the fileInfo
    restFileInfoMockMvc
      .perform(get(ENTITY_API_URL_ID, fileInfo.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(fileInfo.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.nameFile").value(DEFAULT_NAME_FILE))
      .andExpect(jsonPath("$.typeFile").value(DEFAULT_TYPE_FILE))
      .andExpect(jsonPath("$.path").value(DEFAULT_PATH))
      .andExpect(jsonPath("$.dataSize").value(DEFAULT_DATA_SIZE.intValue()));
  }

  @Test
  @Transactional
  void getFileInfosByIdFiltering() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    Long id = fileInfo.getId();

    defaultFileInfoShouldBeFound("id.equals=" + id);
    defaultFileInfoShouldNotBeFound("id.notEquals=" + id);

    defaultFileInfoShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultFileInfoShouldNotBeFound("id.greaterThan=" + id);

    defaultFileInfoShouldBeFound("id.lessThanOrEqual=" + id);
    defaultFileInfoShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllFileInfosByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where uuid equals to DEFAULT_UUID
    defaultFileInfoShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the fileInfoList where uuid equals to UPDATED_UUID
    defaultFileInfoShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFileInfosByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where uuid not equals to DEFAULT_UUID
    defaultFileInfoShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the fileInfoList where uuid not equals to UPDATED_UUID
    defaultFileInfoShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFileInfosByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultFileInfoShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the fileInfoList where uuid equals to UPDATED_UUID
    defaultFileInfoShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFileInfosByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where uuid is not null
    defaultFileInfoShouldBeFound("uuid.specified=true");

    // Get all the fileInfoList where uuid is null
    defaultFileInfoShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllFileInfosByNameFileIsEqualToSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where nameFile equals to DEFAULT_NAME_FILE
    defaultFileInfoShouldBeFound("nameFile.equals=" + DEFAULT_NAME_FILE);

    // Get all the fileInfoList where nameFile equals to UPDATED_NAME_FILE
    defaultFileInfoShouldNotBeFound("nameFile.equals=" + UPDATED_NAME_FILE);
  }

  @Test
  @Transactional
  void getAllFileInfosByNameFileIsNotEqualToSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where nameFile not equals to DEFAULT_NAME_FILE
    defaultFileInfoShouldNotBeFound("nameFile.notEquals=" + DEFAULT_NAME_FILE);

    // Get all the fileInfoList where nameFile not equals to UPDATED_NAME_FILE
    defaultFileInfoShouldBeFound("nameFile.notEquals=" + UPDATED_NAME_FILE);
  }

  @Test
  @Transactional
  void getAllFileInfosByNameFileIsInShouldWork() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where nameFile in DEFAULT_NAME_FILE or UPDATED_NAME_FILE
    defaultFileInfoShouldBeFound("nameFile.in=" + DEFAULT_NAME_FILE + "," + UPDATED_NAME_FILE);

    // Get all the fileInfoList where nameFile equals to UPDATED_NAME_FILE
    defaultFileInfoShouldNotBeFound("nameFile.in=" + UPDATED_NAME_FILE);
  }

  @Test
  @Transactional
  void getAllFileInfosByNameFileIsNullOrNotNull() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where nameFile is not null
    defaultFileInfoShouldBeFound("nameFile.specified=true");

    // Get all the fileInfoList where nameFile is null
    defaultFileInfoShouldNotBeFound("nameFile.specified=false");
  }

  @Test
  @Transactional
  void getAllFileInfosByNameFileContainsSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where nameFile contains DEFAULT_NAME_FILE
    defaultFileInfoShouldBeFound("nameFile.contains=" + DEFAULT_NAME_FILE);

    // Get all the fileInfoList where nameFile contains UPDATED_NAME_FILE
    defaultFileInfoShouldNotBeFound("nameFile.contains=" + UPDATED_NAME_FILE);
  }

  @Test
  @Transactional
  void getAllFileInfosByNameFileNotContainsSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where nameFile does not contain DEFAULT_NAME_FILE
    defaultFileInfoShouldNotBeFound("nameFile.doesNotContain=" + DEFAULT_NAME_FILE);

    // Get all the fileInfoList where nameFile does not contain UPDATED_NAME_FILE
    defaultFileInfoShouldBeFound("nameFile.doesNotContain=" + UPDATED_NAME_FILE);
  }

  @Test
  @Transactional
  void getAllFileInfosByTypeFileIsEqualToSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where typeFile equals to DEFAULT_TYPE_FILE
    defaultFileInfoShouldBeFound("typeFile.equals=" + DEFAULT_TYPE_FILE);

    // Get all the fileInfoList where typeFile equals to UPDATED_TYPE_FILE
    defaultFileInfoShouldNotBeFound("typeFile.equals=" + UPDATED_TYPE_FILE);
  }

  @Test
  @Transactional
  void getAllFileInfosByTypeFileIsNotEqualToSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where typeFile not equals to DEFAULT_TYPE_FILE
    defaultFileInfoShouldNotBeFound("typeFile.notEquals=" + DEFAULT_TYPE_FILE);

    // Get all the fileInfoList where typeFile not equals to UPDATED_TYPE_FILE
    defaultFileInfoShouldBeFound("typeFile.notEquals=" + UPDATED_TYPE_FILE);
  }

  @Test
  @Transactional
  void getAllFileInfosByTypeFileIsInShouldWork() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where typeFile in DEFAULT_TYPE_FILE or UPDATED_TYPE_FILE
    defaultFileInfoShouldBeFound("typeFile.in=" + DEFAULT_TYPE_FILE + "," + UPDATED_TYPE_FILE);

    // Get all the fileInfoList where typeFile equals to UPDATED_TYPE_FILE
    defaultFileInfoShouldNotBeFound("typeFile.in=" + UPDATED_TYPE_FILE);
  }

  @Test
  @Transactional
  void getAllFileInfosByTypeFileIsNullOrNotNull() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where typeFile is not null
    defaultFileInfoShouldBeFound("typeFile.specified=true");

    // Get all the fileInfoList where typeFile is null
    defaultFileInfoShouldNotBeFound("typeFile.specified=false");
  }

  @Test
  @Transactional
  void getAllFileInfosByTypeFileContainsSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where typeFile contains DEFAULT_TYPE_FILE
    defaultFileInfoShouldBeFound("typeFile.contains=" + DEFAULT_TYPE_FILE);

    // Get all the fileInfoList where typeFile contains UPDATED_TYPE_FILE
    defaultFileInfoShouldNotBeFound("typeFile.contains=" + UPDATED_TYPE_FILE);
  }

  @Test
  @Transactional
  void getAllFileInfosByTypeFileNotContainsSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where typeFile does not contain DEFAULT_TYPE_FILE
    defaultFileInfoShouldNotBeFound("typeFile.doesNotContain=" + DEFAULT_TYPE_FILE);

    // Get all the fileInfoList where typeFile does not contain UPDATED_TYPE_FILE
    defaultFileInfoShouldBeFound("typeFile.doesNotContain=" + UPDATED_TYPE_FILE);
  }

  @Test
  @Transactional
  void getAllFileInfosByPathIsEqualToSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where path equals to DEFAULT_PATH
    defaultFileInfoShouldBeFound("path.equals=" + DEFAULT_PATH);

    // Get all the fileInfoList where path equals to UPDATED_PATH
    defaultFileInfoShouldNotBeFound("path.equals=" + UPDATED_PATH);
  }

  @Test
  @Transactional
  void getAllFileInfosByPathIsNotEqualToSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where path not equals to DEFAULT_PATH
    defaultFileInfoShouldNotBeFound("path.notEquals=" + DEFAULT_PATH);

    // Get all the fileInfoList where path not equals to UPDATED_PATH
    defaultFileInfoShouldBeFound("path.notEquals=" + UPDATED_PATH);
  }

  @Test
  @Transactional
  void getAllFileInfosByPathIsInShouldWork() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where path in DEFAULT_PATH or UPDATED_PATH
    defaultFileInfoShouldBeFound("path.in=" + DEFAULT_PATH + "," + UPDATED_PATH);

    // Get all the fileInfoList where path equals to UPDATED_PATH
    defaultFileInfoShouldNotBeFound("path.in=" + UPDATED_PATH);
  }

  @Test
  @Transactional
  void getAllFileInfosByPathIsNullOrNotNull() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where path is not null
    defaultFileInfoShouldBeFound("path.specified=true");

    // Get all the fileInfoList where path is null
    defaultFileInfoShouldNotBeFound("path.specified=false");
  }

  @Test
  @Transactional
  void getAllFileInfosByPathContainsSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where path contains DEFAULT_PATH
    defaultFileInfoShouldBeFound("path.contains=" + DEFAULT_PATH);

    // Get all the fileInfoList where path contains UPDATED_PATH
    defaultFileInfoShouldNotBeFound("path.contains=" + UPDATED_PATH);
  }

  @Test
  @Transactional
  void getAllFileInfosByPathNotContainsSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where path does not contain DEFAULT_PATH
    defaultFileInfoShouldNotBeFound("path.doesNotContain=" + DEFAULT_PATH);

    // Get all the fileInfoList where path does not contain UPDATED_PATH
    defaultFileInfoShouldBeFound("path.doesNotContain=" + UPDATED_PATH);
  }

  @Test
  @Transactional
  void getAllFileInfosByDataSizeIsEqualToSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where dataSize equals to DEFAULT_DATA_SIZE
    defaultFileInfoShouldBeFound("dataSize.equals=" + DEFAULT_DATA_SIZE);

    // Get all the fileInfoList where dataSize equals to UPDATED_DATA_SIZE
    defaultFileInfoShouldNotBeFound("dataSize.equals=" + UPDATED_DATA_SIZE);
  }

  @Test
  @Transactional
  void getAllFileInfosByDataSizeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where dataSize not equals to DEFAULT_DATA_SIZE
    defaultFileInfoShouldNotBeFound("dataSize.notEquals=" + DEFAULT_DATA_SIZE);

    // Get all the fileInfoList where dataSize not equals to UPDATED_DATA_SIZE
    defaultFileInfoShouldBeFound("dataSize.notEquals=" + UPDATED_DATA_SIZE);
  }

  @Test
  @Transactional
  void getAllFileInfosByDataSizeIsInShouldWork() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where dataSize in DEFAULT_DATA_SIZE or UPDATED_DATA_SIZE
    defaultFileInfoShouldBeFound("dataSize.in=" + DEFAULT_DATA_SIZE + "," + UPDATED_DATA_SIZE);

    // Get all the fileInfoList where dataSize equals to UPDATED_DATA_SIZE
    defaultFileInfoShouldNotBeFound("dataSize.in=" + UPDATED_DATA_SIZE);
  }

  @Test
  @Transactional
  void getAllFileInfosByDataSizeIsNullOrNotNull() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where dataSize is not null
    defaultFileInfoShouldBeFound("dataSize.specified=true");

    // Get all the fileInfoList where dataSize is null
    defaultFileInfoShouldNotBeFound("dataSize.specified=false");
  }

  @Test
  @Transactional
  void getAllFileInfosByDataSizeIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where dataSize is greater than or equal to DEFAULT_DATA_SIZE
    defaultFileInfoShouldBeFound("dataSize.greaterThanOrEqual=" + DEFAULT_DATA_SIZE);

    // Get all the fileInfoList where dataSize is greater than or equal to UPDATED_DATA_SIZE
    defaultFileInfoShouldNotBeFound("dataSize.greaterThanOrEqual=" + UPDATED_DATA_SIZE);
  }

  @Test
  @Transactional
  void getAllFileInfosByDataSizeIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where dataSize is less than or equal to DEFAULT_DATA_SIZE
    defaultFileInfoShouldBeFound("dataSize.lessThanOrEqual=" + DEFAULT_DATA_SIZE);

    // Get all the fileInfoList where dataSize is less than or equal to SMALLER_DATA_SIZE
    defaultFileInfoShouldNotBeFound("dataSize.lessThanOrEqual=" + SMALLER_DATA_SIZE);
  }

  @Test
  @Transactional
  void getAllFileInfosByDataSizeIsLessThanSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where dataSize is less than DEFAULT_DATA_SIZE
    defaultFileInfoShouldNotBeFound("dataSize.lessThan=" + DEFAULT_DATA_SIZE);

    // Get all the fileInfoList where dataSize is less than UPDATED_DATA_SIZE
    defaultFileInfoShouldBeFound("dataSize.lessThan=" + UPDATED_DATA_SIZE);
  }

  @Test
  @Transactional
  void getAllFileInfosByDataSizeIsGreaterThanSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    // Get all the fileInfoList where dataSize is greater than DEFAULT_DATA_SIZE
    defaultFileInfoShouldNotBeFound("dataSize.greaterThan=" + DEFAULT_DATA_SIZE);

    // Get all the fileInfoList where dataSize is greater than SMALLER_DATA_SIZE
    defaultFileInfoShouldBeFound("dataSize.greaterThan=" + SMALLER_DATA_SIZE);
  }

  @Test
  @Transactional
  void getAllFileInfosByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    fileInfo.setBaseInfo(baseInfo);
    fileInfoRepository.saveAndFlush(fileInfo);
    Long baseInfoId = baseInfo.getId();

    // Get all the fileInfoList where baseInfo equals to baseInfoId
    defaultFileInfoShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the fileInfoList where baseInfo equals to (baseInfoId + 1)
    defaultFileInfoShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultFileInfoShouldBeFound(String filter) throws Exception {
    restFileInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(fileInfo.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].nameFile").value(hasItem(DEFAULT_NAME_FILE)))
      .andExpect(jsonPath("$.[*].typeFile").value(hasItem(DEFAULT_TYPE_FILE)))
      .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
      .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE.intValue())));

    // Check, that the count call also returns 1
    restFileInfoMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultFileInfoShouldNotBeFound(String filter) throws Exception {
    restFileInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restFileInfoMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingFileInfo() throws Exception {
    // Get the fileInfo
    restFileInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewFileInfo() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    int databaseSizeBeforeUpdate = fileInfoRepository.findAll().size();

    // Update the fileInfo
    FileInfo updatedFileInfo = fileInfoRepository.findById(fileInfo.getId()).get();
    // Disconnect from session so that the updates on updatedFileInfo are not directly saved in db
    em.detach(updatedFileInfo);
    updatedFileInfo
      .uuid(UPDATED_UUID)
      .nameFile(UPDATED_NAME_FILE)
      .typeFile(UPDATED_TYPE_FILE)
      .path(UPDATED_PATH)
      .dataSize(UPDATED_DATA_SIZE);
    FileInfoDTO fileInfoDTO = fileInfoMapper.toDto(updatedFileInfo);

    restFileInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, fileInfoDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(fileInfoDTO))
      )
      .andExpect(status().isOk());

    // Validate the FileInfo in the database
    List<FileInfo> fileInfoList = fileInfoRepository.findAll();
    assertThat(fileInfoList).hasSize(databaseSizeBeforeUpdate);
    FileInfo testFileInfo = fileInfoList.get(fileInfoList.size() - 1);
    assertThat(testFileInfo.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testFileInfo.getNameFile()).isEqualTo(UPDATED_NAME_FILE);
    assertThat(testFileInfo.getTypeFile()).isEqualTo(UPDATED_TYPE_FILE);
    assertThat(testFileInfo.getPath()).isEqualTo(UPDATED_PATH);
    assertThat(testFileInfo.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);

    // Validate the FileInfo in Elasticsearch
    verify(mockFileInfoSearchRepository).save(testFileInfo);
  }

  @Test
  @Transactional
  void putNonExistingFileInfo() throws Exception {
    int databaseSizeBeforeUpdate = fileInfoRepository.findAll().size();
    fileInfo.setId(count.incrementAndGet());

    // Create the FileInfo
    FileInfoDTO fileInfoDTO = fileInfoMapper.toDto(fileInfo);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restFileInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, fileInfoDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(fileInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FileInfo in the database
    List<FileInfo> fileInfoList = fileInfoRepository.findAll();
    assertThat(fileInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FileInfo in Elasticsearch
    verify(mockFileInfoSearchRepository, times(0)).save(fileInfo);
  }

  @Test
  @Transactional
  void putWithIdMismatchFileInfo() throws Exception {
    int databaseSizeBeforeUpdate = fileInfoRepository.findAll().size();
    fileInfo.setId(count.incrementAndGet());

    // Create the FileInfo
    FileInfoDTO fileInfoDTO = fileInfoMapper.toDto(fileInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFileInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(fileInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FileInfo in the database
    List<FileInfo> fileInfoList = fileInfoRepository.findAll();
    assertThat(fileInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FileInfo in Elasticsearch
    verify(mockFileInfoSearchRepository, times(0)).save(fileInfo);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamFileInfo() throws Exception {
    int databaseSizeBeforeUpdate = fileInfoRepository.findAll().size();
    fileInfo.setId(count.incrementAndGet());

    // Create the FileInfo
    FileInfoDTO fileInfoDTO = fileInfoMapper.toDto(fileInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFileInfoMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(fileInfoDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the FileInfo in the database
    List<FileInfo> fileInfoList = fileInfoRepository.findAll();
    assertThat(fileInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FileInfo in Elasticsearch
    verify(mockFileInfoSearchRepository, times(0)).save(fileInfo);
  }

  @Test
  @Transactional
  void partialUpdateFileInfoWithPatch() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    int databaseSizeBeforeUpdate = fileInfoRepository.findAll().size();

    // Update the fileInfo using partial update
    FileInfo partialUpdatedFileInfo = new FileInfo();
    partialUpdatedFileInfo.setId(fileInfo.getId());

    partialUpdatedFileInfo.typeFile(UPDATED_TYPE_FILE).path(UPDATED_PATH).dataSize(UPDATED_DATA_SIZE);

    restFileInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedFileInfo.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFileInfo))
      )
      .andExpect(status().isOk());

    // Validate the FileInfo in the database
    List<FileInfo> fileInfoList = fileInfoRepository.findAll();
    assertThat(fileInfoList).hasSize(databaseSizeBeforeUpdate);
    FileInfo testFileInfo = fileInfoList.get(fileInfoList.size() - 1);
    assertThat(testFileInfo.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testFileInfo.getNameFile()).isEqualTo(DEFAULT_NAME_FILE);
    assertThat(testFileInfo.getTypeFile()).isEqualTo(UPDATED_TYPE_FILE);
    assertThat(testFileInfo.getPath()).isEqualTo(UPDATED_PATH);
    assertThat(testFileInfo.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
  }

  @Test
  @Transactional
  void fullUpdateFileInfoWithPatch() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    int databaseSizeBeforeUpdate = fileInfoRepository.findAll().size();

    // Update the fileInfo using partial update
    FileInfo partialUpdatedFileInfo = new FileInfo();
    partialUpdatedFileInfo.setId(fileInfo.getId());

    partialUpdatedFileInfo
      .uuid(UPDATED_UUID)
      .nameFile(UPDATED_NAME_FILE)
      .typeFile(UPDATED_TYPE_FILE)
      .path(UPDATED_PATH)
      .dataSize(UPDATED_DATA_SIZE);

    restFileInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedFileInfo.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFileInfo))
      )
      .andExpect(status().isOk());

    // Validate the FileInfo in the database
    List<FileInfo> fileInfoList = fileInfoRepository.findAll();
    assertThat(fileInfoList).hasSize(databaseSizeBeforeUpdate);
    FileInfo testFileInfo = fileInfoList.get(fileInfoList.size() - 1);
    assertThat(testFileInfo.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testFileInfo.getNameFile()).isEqualTo(UPDATED_NAME_FILE);
    assertThat(testFileInfo.getTypeFile()).isEqualTo(UPDATED_TYPE_FILE);
    assertThat(testFileInfo.getPath()).isEqualTo(UPDATED_PATH);
    assertThat(testFileInfo.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
  }

  @Test
  @Transactional
  void patchNonExistingFileInfo() throws Exception {
    int databaseSizeBeforeUpdate = fileInfoRepository.findAll().size();
    fileInfo.setId(count.incrementAndGet());

    // Create the FileInfo
    FileInfoDTO fileInfoDTO = fileInfoMapper.toDto(fileInfo);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restFileInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, fileInfoDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(fileInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FileInfo in the database
    List<FileInfo> fileInfoList = fileInfoRepository.findAll();
    assertThat(fileInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FileInfo in Elasticsearch
    verify(mockFileInfoSearchRepository, times(0)).save(fileInfo);
  }

  @Test
  @Transactional
  void patchWithIdMismatchFileInfo() throws Exception {
    int databaseSizeBeforeUpdate = fileInfoRepository.findAll().size();
    fileInfo.setId(count.incrementAndGet());

    // Create the FileInfo
    FileInfoDTO fileInfoDTO = fileInfoMapper.toDto(fileInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFileInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(fileInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FileInfo in the database
    List<FileInfo> fileInfoList = fileInfoRepository.findAll();
    assertThat(fileInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FileInfo in Elasticsearch
    verify(mockFileInfoSearchRepository, times(0)).save(fileInfo);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamFileInfo() throws Exception {
    int databaseSizeBeforeUpdate = fileInfoRepository.findAll().size();
    fileInfo.setId(count.incrementAndGet());

    // Create the FileInfo
    FileInfoDTO fileInfoDTO = fileInfoMapper.toDto(fileInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFileInfoMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(fileInfoDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the FileInfo in the database
    List<FileInfo> fileInfoList = fileInfoRepository.findAll();
    assertThat(fileInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FileInfo in Elasticsearch
    verify(mockFileInfoSearchRepository, times(0)).save(fileInfo);
  }

  @Test
  @Transactional
  void deleteFileInfo() throws Exception {
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);

    int databaseSizeBeforeDelete = fileInfoRepository.findAll().size();

    // Delete the fileInfo
    restFileInfoMockMvc
      .perform(delete(ENTITY_API_URL_ID, fileInfo.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<FileInfo> fileInfoList = fileInfoRepository.findAll();
    assertThat(fileInfoList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the FileInfo in Elasticsearch
    verify(mockFileInfoSearchRepository, times(1)).deleteById(fileInfo.getId());
  }

  @Test
  @Transactional
  void searchFileInfo() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    fileInfoRepository.saveAndFlush(fileInfo);
    when(mockFileInfoSearchRepository.search(queryStringQuery("id:" + fileInfo.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(fileInfo), PageRequest.of(0, 1), 1));

    // Search the fileInfo
    restFileInfoMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + fileInfo.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(fileInfo.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].nameFile").value(hasItem(DEFAULT_NAME_FILE)))
      .andExpect(jsonPath("$.[*].typeFile").value(hasItem(DEFAULT_TYPE_FILE)))
      .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
      .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE.intValue())));
  }
}
