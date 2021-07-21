package com.regitiny.catiny.web.rest;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.IntegrationTest;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.ClassInfo;
import com.regitiny.catiny.repository.ClassInfoRepository;
import com.regitiny.catiny.repository.search.ClassInfoSearchRepository;
import com.regitiny.catiny.service.dto.ClassInfoDTO;
import com.regitiny.catiny.service.mapper.ClassInfoMapper;
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
 * Integration tests for the {@link ClassInfoResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class ClassInfoResourceIT
{

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_NAME_PACKAGE = "AAAAAAAAAA";
  private static final String UPDATED_NAME_PACKAGE = "BBBBBBBBBB";

  private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
  private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_CLASS_NAME = "AAAAAAAAAA";
  private static final String UPDATED_CLASS_NAME = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/class-infos";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/class-infos";

  private static final Random random = new Random();
  private static final AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private ClassInfoRepository classInfoRepository;

  @Autowired
  private ClassInfoMapper classInfoMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.ClassInfoSearchRepositoryMockConfiguration
   */
  @Autowired
  private ClassInfoSearchRepository mockClassInfoSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restClassInfoMockMvc;

  private ClassInfo classInfo;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static ClassInfo createEntity(EntityManager em) {
    ClassInfo classInfo = new ClassInfo()
      .uuid(DEFAULT_UUID)
      .namePackage(DEFAULT_NAME_PACKAGE)
      .fullName(DEFAULT_FULL_NAME)
      .className(DEFAULT_CLASS_NAME);
    return classInfo;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static ClassInfo createUpdatedEntity(EntityManager em) {
    ClassInfo classInfo = new ClassInfo()
      .uuid(UPDATED_UUID)
      .namePackage(UPDATED_NAME_PACKAGE)
      .fullName(UPDATED_FULL_NAME)
      .className(UPDATED_CLASS_NAME);
    return classInfo;
  }

  @BeforeEach
  public void initTest() {
    classInfo = createEntity(em);
  }

  @Test
  @Transactional
  void createClassInfo() throws Exception {
    int databaseSizeBeforeCreate = classInfoRepository.findAll().size();
    // Create the ClassInfo
    ClassInfoDTO classInfoDTO = classInfoMapper.toDto(classInfo);
    restClassInfoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(classInfoDTO)))
      .andExpect(status().isCreated());

    // Validate the ClassInfo in the database
    List<ClassInfo> classInfoList = classInfoRepository.findAll();
    assertThat(classInfoList).hasSize(databaseSizeBeforeCreate + 1);
    ClassInfo testClassInfo = classInfoList.get(classInfoList.size() - 1);
    assertThat(testClassInfo.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testClassInfo.getNamePackage()).isEqualTo(DEFAULT_NAME_PACKAGE);
    assertThat(testClassInfo.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
    assertThat(testClassInfo.getClassName()).isEqualTo(DEFAULT_CLASS_NAME);

    // Validate the ClassInfo in Elasticsearch
    verify(mockClassInfoSearchRepository, times(1)).save(testClassInfo);
  }

  @Test
  @Transactional
  void createClassInfoWithExistingId() throws Exception {
    // Create the ClassInfo with an existing ID
    classInfo.setId(1L);
    ClassInfoDTO classInfoDTO = classInfoMapper.toDto(classInfo);

    int databaseSizeBeforeCreate = classInfoRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restClassInfoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(classInfoDTO)))
      .andExpect(status().isBadRequest());

    // Validate the ClassInfo in the database
    List<ClassInfo> classInfoList = classInfoRepository.findAll();
    assertThat(classInfoList).hasSize(databaseSizeBeforeCreate);

    // Validate the ClassInfo in Elasticsearch
    verify(mockClassInfoSearchRepository, times(0)).save(classInfo);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = classInfoRepository.findAll().size();
    // set the field null
    classInfo.setUuid(null);

    // Create the ClassInfo, which fails.
    ClassInfoDTO classInfoDTO = classInfoMapper.toDto(classInfo);

    restClassInfoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(classInfoDTO)))
      .andExpect(status().isBadRequest());

    List<ClassInfo> classInfoList = classInfoRepository.findAll();
    assertThat(classInfoList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void checkFullNameIsRequired() throws Exception {
    int databaseSizeBeforeTest = classInfoRepository.findAll().size();
    // set the field null
    classInfo.setFullName(null);

    // Create the ClassInfo, which fails.
    ClassInfoDTO classInfoDTO = classInfoMapper.toDto(classInfo);

    restClassInfoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(classInfoDTO)))
      .andExpect(status().isBadRequest());

    List<ClassInfo> classInfoList = classInfoRepository.findAll();
    assertThat(classInfoList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllClassInfos() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList
    restClassInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(classInfo.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].namePackage").value(hasItem(DEFAULT_NAME_PACKAGE)))
      .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
      .andExpect(jsonPath("$.[*].className").value(hasItem(DEFAULT_CLASS_NAME)));
  }

  @Test
  @Transactional
  void getClassInfo() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get the classInfo
    restClassInfoMockMvc
      .perform(get(ENTITY_API_URL_ID, classInfo.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(classInfo.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.namePackage").value(DEFAULT_NAME_PACKAGE))
      .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
      .andExpect(jsonPath("$.className").value(DEFAULT_CLASS_NAME));
  }

  @Test
  @Transactional
  void getClassInfosByIdFiltering() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    Long id = classInfo.getId();

    defaultClassInfoShouldBeFound("id.equals=" + id);
    defaultClassInfoShouldNotBeFound("id.notEquals=" + id);

    defaultClassInfoShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultClassInfoShouldNotBeFound("id.greaterThan=" + id);

    defaultClassInfoShouldBeFound("id.lessThanOrEqual=" + id);
    defaultClassInfoShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllClassInfosByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where uuid equals to DEFAULT_UUID
    defaultClassInfoShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the classInfoList where uuid equals to UPDATED_UUID
    defaultClassInfoShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllClassInfosByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where uuid not equals to DEFAULT_UUID
    defaultClassInfoShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the classInfoList where uuid not equals to UPDATED_UUID
    defaultClassInfoShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllClassInfosByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultClassInfoShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the classInfoList where uuid equals to UPDATED_UUID
    defaultClassInfoShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllClassInfosByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where uuid is not null
    defaultClassInfoShouldBeFound("uuid.specified=true");

    // Get all the classInfoList where uuid is null
    defaultClassInfoShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllClassInfosByNamePackageIsEqualToSomething() throws Exception
  {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where namePackage equals to DEFAULT_NAME_PACKAGE
    defaultClassInfoShouldBeFound("namePackage.equals=" + DEFAULT_NAME_PACKAGE);

    // Get all the classInfoList where namePackage equals to UPDATED_NAME_PACKAGE
    defaultClassInfoShouldNotBeFound("namePackage.equals=" + UPDATED_NAME_PACKAGE);
  }

  @Test
  @Transactional
  void getAllClassInfosByNamePackageIsNotEqualToSomething() throws Exception
  {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where namePackage not equals to DEFAULT_NAME_PACKAGE
    defaultClassInfoShouldNotBeFound("namePackage.notEquals=" + DEFAULT_NAME_PACKAGE);

    // Get all the classInfoList where namePackage not equals to UPDATED_NAME_PACKAGE
    defaultClassInfoShouldBeFound("namePackage.notEquals=" + UPDATED_NAME_PACKAGE);
  }

  @Test
  @Transactional
  void getAllClassInfosByNamePackageIsInShouldWork() throws Exception
  {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where namePackage in DEFAULT_NAME_PACKAGE or UPDATED_NAME_PACKAGE
    defaultClassInfoShouldBeFound("namePackage.in=" + DEFAULT_NAME_PACKAGE + "," + UPDATED_NAME_PACKAGE);

    // Get all the classInfoList where namePackage equals to UPDATED_NAME_PACKAGE
    defaultClassInfoShouldNotBeFound("namePackage.in=" + UPDATED_NAME_PACKAGE);
  }

  @Test
  @Transactional
  void getAllClassInfosByNamePackageIsNullOrNotNull() throws Exception
  {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where namePackage is not null
    defaultClassInfoShouldBeFound("namePackage.specified=true");

    // Get all the classInfoList where namePackage is null
    defaultClassInfoShouldNotBeFound("namePackage.specified=false");
  }

  @Test
  @Transactional
  void getAllClassInfosByNamePackageContainsSomething() throws Exception
  {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where namePackage contains DEFAULT_NAME_PACKAGE
    defaultClassInfoShouldBeFound("namePackage.contains=" + DEFAULT_NAME_PACKAGE);

    // Get all the classInfoList where namePackage contains UPDATED_NAME_PACKAGE
    defaultClassInfoShouldNotBeFound("namePackage.contains=" + UPDATED_NAME_PACKAGE);
  }

  @Test
  @Transactional
  void getAllClassInfosByNamePackageNotContainsSomething() throws Exception
  {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where namePackage does not contain DEFAULT_NAME_PACKAGE
    defaultClassInfoShouldNotBeFound("namePackage.doesNotContain=" + DEFAULT_NAME_PACKAGE);

    // Get all the classInfoList where namePackage does not contain UPDATED_NAME_PACKAGE
    defaultClassInfoShouldBeFound("namePackage.doesNotContain=" + UPDATED_NAME_PACKAGE);
  }

  @Test
  @Transactional
  void getAllClassInfosByFullNameIsEqualToSomething() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where fullName equals to DEFAULT_FULL_NAME
    defaultClassInfoShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

    // Get all the classInfoList where fullName equals to UPDATED_FULL_NAME
    defaultClassInfoShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
  }

  @Test
  @Transactional
  void getAllClassInfosByFullNameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where fullName not equals to DEFAULT_FULL_NAME
    defaultClassInfoShouldNotBeFound("fullName.notEquals=" + DEFAULT_FULL_NAME);

    // Get all the classInfoList where fullName not equals to UPDATED_FULL_NAME
    defaultClassInfoShouldBeFound("fullName.notEquals=" + UPDATED_FULL_NAME);
  }

  @Test
  @Transactional
  void getAllClassInfosByFullNameIsInShouldWork() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
    defaultClassInfoShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

    // Get all the classInfoList where fullName equals to UPDATED_FULL_NAME
    defaultClassInfoShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
  }

  @Test
  @Transactional
  void getAllClassInfosByFullNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where fullName is not null
    defaultClassInfoShouldBeFound("fullName.specified=true");

    // Get all the classInfoList where fullName is null
    defaultClassInfoShouldNotBeFound("fullName.specified=false");
  }

  @Test
  @Transactional
  void getAllClassInfosByFullNameContainsSomething() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where fullName contains DEFAULT_FULL_NAME
    defaultClassInfoShouldBeFound("fullName.contains=" + DEFAULT_FULL_NAME);

    // Get all the classInfoList where fullName contains UPDATED_FULL_NAME
    defaultClassInfoShouldNotBeFound("fullName.contains=" + UPDATED_FULL_NAME);
  }

  @Test
  @Transactional
  void getAllClassInfosByFullNameNotContainsSomething() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where fullName does not contain DEFAULT_FULL_NAME
    defaultClassInfoShouldNotBeFound("fullName.doesNotContain=" + DEFAULT_FULL_NAME);

    // Get all the classInfoList where fullName does not contain UPDATED_FULL_NAME
    defaultClassInfoShouldBeFound("fullName.doesNotContain=" + UPDATED_FULL_NAME);
  }

  @Test
  @Transactional
  void getAllClassInfosByClassNameIsEqualToSomething() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where className equals to DEFAULT_CLASS_NAME
    defaultClassInfoShouldBeFound("className.equals=" + DEFAULT_CLASS_NAME);

    // Get all the classInfoList where className equals to UPDATED_CLASS_NAME
    defaultClassInfoShouldNotBeFound("className.equals=" + UPDATED_CLASS_NAME);
  }

  @Test
  @Transactional
  void getAllClassInfosByClassNameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where className not equals to DEFAULT_CLASS_NAME
    defaultClassInfoShouldNotBeFound("className.notEquals=" + DEFAULT_CLASS_NAME);

    // Get all the classInfoList where className not equals to UPDATED_CLASS_NAME
    defaultClassInfoShouldBeFound("className.notEquals=" + UPDATED_CLASS_NAME);
  }

  @Test
  @Transactional
  void getAllClassInfosByClassNameIsInShouldWork() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where className in DEFAULT_CLASS_NAME or UPDATED_CLASS_NAME
    defaultClassInfoShouldBeFound("className.in=" + DEFAULT_CLASS_NAME + "," + UPDATED_CLASS_NAME);

    // Get all the classInfoList where className equals to UPDATED_CLASS_NAME
    defaultClassInfoShouldNotBeFound("className.in=" + UPDATED_CLASS_NAME);
  }

  @Test
  @Transactional
  void getAllClassInfosByClassNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where className is not null
    defaultClassInfoShouldBeFound("className.specified=true");

    // Get all the classInfoList where className is null
    defaultClassInfoShouldNotBeFound("className.specified=false");
  }

  @Test
  @Transactional
  void getAllClassInfosByClassNameContainsSomething() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where className contains DEFAULT_CLASS_NAME
    defaultClassInfoShouldBeFound("className.contains=" + DEFAULT_CLASS_NAME);

    // Get all the classInfoList where className contains UPDATED_CLASS_NAME
    defaultClassInfoShouldNotBeFound("className.contains=" + UPDATED_CLASS_NAME);
  }

  @Test
  @Transactional
  void getAllClassInfosByClassNameNotContainsSomething() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    // Get all the classInfoList where className does not contain DEFAULT_CLASS_NAME
    defaultClassInfoShouldNotBeFound("className.doesNotContain=" + DEFAULT_CLASS_NAME);

    // Get all the classInfoList where className does not contain UPDATED_CLASS_NAME
    defaultClassInfoShouldBeFound("className.doesNotContain=" + UPDATED_CLASS_NAME);
  }

  @Test
  @Transactional
  void getAllClassInfosByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    classInfo.addBaseInfo(baseInfo);
    classInfoRepository.saveAndFlush(classInfo);
    Long baseInfoId = baseInfo.getId();

    // Get all the classInfoList where baseInfo equals to baseInfoId
    defaultClassInfoShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the classInfoList where baseInfo equals to (baseInfoId + 1)
    defaultClassInfoShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultClassInfoShouldBeFound(String filter) throws Exception {
    restClassInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(classInfo.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].namePackage").value(hasItem(DEFAULT_NAME_PACKAGE)))
      .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
      .andExpect(jsonPath("$.[*].className").value(hasItem(DEFAULT_CLASS_NAME)));

    // Check, that the count call also returns 1
    restClassInfoMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultClassInfoShouldNotBeFound(String filter) throws Exception {
    restClassInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restClassInfoMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingClassInfo() throws Exception {
    // Get the classInfo
    restClassInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewClassInfo() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    int databaseSizeBeforeUpdate = classInfoRepository.findAll().size();

    // Update the classInfo
    ClassInfo updatedClassInfo = classInfoRepository.findById(classInfo.getId()).get();
    // Disconnect from session so that the updates on updatedClassInfo are not directly saved in db
    em.detach(updatedClassInfo);
    updatedClassInfo.uuid(UPDATED_UUID).namePackage(UPDATED_NAME_PACKAGE).fullName(UPDATED_FULL_NAME).className(UPDATED_CLASS_NAME);
    ClassInfoDTO classInfoDTO = classInfoMapper.toDto(updatedClassInfo);

    restClassInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, classInfoDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(classInfoDTO))
      )
      .andExpect(status().isOk());

    // Validate the ClassInfo in the database
    List<ClassInfo> classInfoList = classInfoRepository.findAll();
    assertThat(classInfoList).hasSize(databaseSizeBeforeUpdate);
    ClassInfo testClassInfo = classInfoList.get(classInfoList.size() - 1);
    assertThat(testClassInfo.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testClassInfo.getNamePackage()).isEqualTo(UPDATED_NAME_PACKAGE);
    assertThat(testClassInfo.getFullName()).isEqualTo(UPDATED_FULL_NAME);
    assertThat(testClassInfo.getClassName()).isEqualTo(UPDATED_CLASS_NAME);

    // Validate the ClassInfo in Elasticsearch
    verify(mockClassInfoSearchRepository).save(testClassInfo);
  }

  @Test
  @Transactional
  void putNonExistingClassInfo() throws Exception {
    int databaseSizeBeforeUpdate = classInfoRepository.findAll().size();
    classInfo.setId(count.incrementAndGet());

    // Create the ClassInfo
    ClassInfoDTO classInfoDTO = classInfoMapper.toDto(classInfo);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restClassInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, classInfoDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(classInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ClassInfo in the database
    List<ClassInfo> classInfoList = classInfoRepository.findAll();
    assertThat(classInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the ClassInfo in Elasticsearch
    verify(mockClassInfoSearchRepository, times(0)).save(classInfo);
  }

  @Test
  @Transactional
  void putWithIdMismatchClassInfo() throws Exception {
    int databaseSizeBeforeUpdate = classInfoRepository.findAll().size();
    classInfo.setId(count.incrementAndGet());

    // Create the ClassInfo
    ClassInfoDTO classInfoDTO = classInfoMapper.toDto(classInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restClassInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(classInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ClassInfo in the database
    List<ClassInfo> classInfoList = classInfoRepository.findAll();
    assertThat(classInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the ClassInfo in Elasticsearch
    verify(mockClassInfoSearchRepository, times(0)).save(classInfo);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamClassInfo() throws Exception {
    int databaseSizeBeforeUpdate = classInfoRepository.findAll().size();
    classInfo.setId(count.incrementAndGet());

    // Create the ClassInfo
    ClassInfoDTO classInfoDTO = classInfoMapper.toDto(classInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restClassInfoMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(classInfoDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the ClassInfo in the database
    List<ClassInfo> classInfoList = classInfoRepository.findAll();
    assertThat(classInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the ClassInfo in Elasticsearch
    verify(mockClassInfoSearchRepository, times(0)).save(classInfo);
  }

  @Test
  @Transactional
  void partialUpdateClassInfoWithPatch() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    int databaseSizeBeforeUpdate = classInfoRepository.findAll().size();

    // Update the classInfo using partial update
    ClassInfo partialUpdatedClassInfo = new ClassInfo();
    partialUpdatedClassInfo.setId(classInfo.getId());

    partialUpdatedClassInfo.className(UPDATED_CLASS_NAME);

    restClassInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedClassInfo.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClassInfo))
      )
      .andExpect(status().isOk());

    // Validate the ClassInfo in the database
    List<ClassInfo> classInfoList = classInfoRepository.findAll();
    assertThat(classInfoList).hasSize(databaseSizeBeforeUpdate);
    ClassInfo testClassInfo = classInfoList.get(classInfoList.size() - 1);
    assertThat(testClassInfo.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testClassInfo.getNamePackage()).isEqualTo(DEFAULT_NAME_PACKAGE);
    assertThat(testClassInfo.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
    assertThat(testClassInfo.getClassName()).isEqualTo(UPDATED_CLASS_NAME);
  }

  @Test
  @Transactional
  void fullUpdateClassInfoWithPatch() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    int databaseSizeBeforeUpdate = classInfoRepository.findAll().size();

    // Update the classInfo using partial update
    ClassInfo partialUpdatedClassInfo = new ClassInfo();
    partialUpdatedClassInfo.setId(classInfo.getId());

    partialUpdatedClassInfo.uuid(UPDATED_UUID).namePackage(UPDATED_NAME_PACKAGE).fullName(UPDATED_FULL_NAME).className(UPDATED_CLASS_NAME);

    restClassInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedClassInfo.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClassInfo))
      )
      .andExpect(status().isOk());

    // Validate the ClassInfo in the database
    List<ClassInfo> classInfoList = classInfoRepository.findAll();
    assertThat(classInfoList).hasSize(databaseSizeBeforeUpdate);
    ClassInfo testClassInfo = classInfoList.get(classInfoList.size() - 1);
    assertThat(testClassInfo.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testClassInfo.getNamePackage()).isEqualTo(UPDATED_NAME_PACKAGE);
    assertThat(testClassInfo.getFullName()).isEqualTo(UPDATED_FULL_NAME);
    assertThat(testClassInfo.getClassName()).isEqualTo(UPDATED_CLASS_NAME);
  }

  @Test
  @Transactional
  void patchNonExistingClassInfo() throws Exception {
    int databaseSizeBeforeUpdate = classInfoRepository.findAll().size();
    classInfo.setId(count.incrementAndGet());

    // Create the ClassInfo
    ClassInfoDTO classInfoDTO = classInfoMapper.toDto(classInfo);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restClassInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, classInfoDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(classInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ClassInfo in the database
    List<ClassInfo> classInfoList = classInfoRepository.findAll();
    assertThat(classInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the ClassInfo in Elasticsearch
    verify(mockClassInfoSearchRepository, times(0)).save(classInfo);
  }

  @Test
  @Transactional
  void patchWithIdMismatchClassInfo() throws Exception {
    int databaseSizeBeforeUpdate = classInfoRepository.findAll().size();
    classInfo.setId(count.incrementAndGet());

    // Create the ClassInfo
    ClassInfoDTO classInfoDTO = classInfoMapper.toDto(classInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restClassInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(classInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ClassInfo in the database
    List<ClassInfo> classInfoList = classInfoRepository.findAll();
    assertThat(classInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the ClassInfo in Elasticsearch
    verify(mockClassInfoSearchRepository, times(0)).save(classInfo);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamClassInfo() throws Exception {
    int databaseSizeBeforeUpdate = classInfoRepository.findAll().size();
    classInfo.setId(count.incrementAndGet());

    // Create the ClassInfo
    ClassInfoDTO classInfoDTO = classInfoMapper.toDto(classInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restClassInfoMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(classInfoDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the ClassInfo in the database
    List<ClassInfo> classInfoList = classInfoRepository.findAll();
    assertThat(classInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the ClassInfo in Elasticsearch
    verify(mockClassInfoSearchRepository, times(0)).save(classInfo);
  }

  @Test
  @Transactional
  void deleteClassInfo() throws Exception {
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);

    int databaseSizeBeforeDelete = classInfoRepository.findAll().size();

    // Delete the classInfo
    restClassInfoMockMvc
      .perform(delete(ENTITY_API_URL_ID, classInfo.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<ClassInfo> classInfoList = classInfoRepository.findAll();
    assertThat(classInfoList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the ClassInfo in Elasticsearch
    verify(mockClassInfoSearchRepository, times(1)).deleteById(classInfo.getId());
  }

  @Test
  @Transactional
  void searchClassInfo() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    classInfoRepository.saveAndFlush(classInfo);
    when(mockClassInfoSearchRepository.search(queryStringQuery("id:" + classInfo.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(classInfo), PageRequest.of(0, 1), 1));

    // Search the classInfo
    restClassInfoMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + classInfo.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(classInfo.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].namePackage").value(hasItem(DEFAULT_NAME_PACKAGE)))
      .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
      .andExpect(jsonPath("$.[*].className").value(hasItem(DEFAULT_CLASS_NAME)));
  }
}
