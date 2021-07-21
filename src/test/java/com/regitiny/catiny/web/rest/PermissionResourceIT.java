package com.regitiny.catiny.web.rest;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.IntegrationTest;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.repository.PermissionRepository;
import com.regitiny.catiny.repository.search.PermissionSearchRepository;
import com.regitiny.catiny.service.dto.PermissionDTO;
import com.regitiny.catiny.service.mapper.PermissionMapper;
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
 * Integration tests for the {@link PermissionResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class PermissionResourceIT
{

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final Boolean DEFAULT_READ = false;
  private static final Boolean UPDATED_READ = true;

  private static final Boolean DEFAULT_WRITE = false;
  private static final Boolean UPDATED_WRITE = true;

  private static final Boolean DEFAULT_SHARE = false;
  private static final Boolean UPDATED_SHARE = true;

  private static final Boolean DEFAULT_DELETE = false;
  private static final Boolean UPDATED_DELETE = true;

  private static final Boolean DEFAULT_ADD = false;
  private static final Boolean UPDATED_ADD = true;

  private static final Integer DEFAULT_LEVEL = 1;
  private static final Integer UPDATED_LEVEL = 2;
  private static final Integer SMALLER_LEVEL = 1 - 1;

  private static final String ENTITY_API_URL = "/api/permissions";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/permissions";

  private static final Random random = new Random();
  private static final AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private PermissionRepository permissionRepository;

  @Autowired
  private PermissionMapper permissionMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.PermissionSearchRepositoryMockConfiguration
   */
  @Autowired
  private PermissionSearchRepository mockPermissionSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restPermissionMockMvc;

  private Permission permission;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Permission createEntity(EntityManager em) {
    Permission permission = new Permission()
      .uuid(DEFAULT_UUID)
      .read(DEFAULT_READ)
      .write(DEFAULT_WRITE)
      .share(DEFAULT_SHARE)
      .delete(DEFAULT_DELETE)
      .add(DEFAULT_ADD)
      .level(DEFAULT_LEVEL);
    return permission;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Permission createUpdatedEntity(EntityManager em) {
    Permission permission = new Permission()
      .uuid(UPDATED_UUID)
      .read(UPDATED_READ)
      .write(UPDATED_WRITE)
      .share(UPDATED_SHARE)
      .delete(UPDATED_DELETE)
      .add(UPDATED_ADD)
      .level(UPDATED_LEVEL);
    return permission;
  }

  @BeforeEach
  public void initTest() {
    permission = createEntity(em);
  }

  @Test
  @Transactional
  void createPermission() throws Exception {
    int databaseSizeBeforeCreate = permissionRepository.findAll().size();
    // Create the Permission
    PermissionDTO permissionDTO = permissionMapper.toDto(permission);
    restPermissionMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(permissionDTO)))
      .andExpect(status().isCreated());

    // Validate the Permission in the database
    List<Permission> permissionList = permissionRepository.findAll();
    assertThat(permissionList).hasSize(databaseSizeBeforeCreate + 1);
    Permission testPermission = permissionList.get(permissionList.size() - 1);
    assertThat(testPermission.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testPermission.getRead()).isEqualTo(DEFAULT_READ);
    assertThat(testPermission.getWrite()).isEqualTo(DEFAULT_WRITE);
    assertThat(testPermission.getShare()).isEqualTo(DEFAULT_SHARE);
    assertThat(testPermission.getDelete()).isEqualTo(DEFAULT_DELETE);
    assertThat(testPermission.getAdd()).isEqualTo(DEFAULT_ADD);
    assertThat(testPermission.getLevel()).isEqualTo(DEFAULT_LEVEL);

    // Validate the Permission in Elasticsearch
    verify(mockPermissionSearchRepository, times(1)).save(testPermission);
  }

  @Test
  @Transactional
  void createPermissionWithExistingId() throws Exception {
    // Create the Permission with an existing ID
    permission.setId(1L);
    PermissionDTO permissionDTO = permissionMapper.toDto(permission);

    int databaseSizeBeforeCreate = permissionRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restPermissionMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(permissionDTO)))
      .andExpect(status().isBadRequest());

    // Validate the Permission in the database
    List<Permission> permissionList = permissionRepository.findAll();
    assertThat(permissionList).hasSize(databaseSizeBeforeCreate);

    // Validate the Permission in Elasticsearch
    verify(mockPermissionSearchRepository, times(0)).save(permission);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception
  {
    int databaseSizeBeforeTest = permissionRepository.findAll().size();
    // set the field null
    permission.setUuid(null);

    // Create the Permission, which fails.
    PermissionDTO permissionDTO = permissionMapper.toDto(permission);

    restPermissionMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(permissionDTO)))
      .andExpect(status().isBadRequest());

    List<Permission> permissionList = permissionRepository.findAll();
    assertThat(permissionList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllPermissions() throws Exception
  {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList
    restPermissionMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(permission.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].read").value(hasItem(DEFAULT_READ.booleanValue())))
      .andExpect(jsonPath("$.[*].write").value(hasItem(DEFAULT_WRITE.booleanValue())))
      .andExpect(jsonPath("$.[*].share").value(hasItem(DEFAULT_SHARE.booleanValue())))
      .andExpect(jsonPath("$.[*].delete").value(hasItem(DEFAULT_DELETE.booleanValue())))
      .andExpect(jsonPath("$.[*].add").value(hasItem(DEFAULT_ADD.booleanValue())))
      .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)));
  }

  @Test
  @Transactional
  void getPermission() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get the permission
    restPermissionMockMvc
      .perform(get(ENTITY_API_URL_ID, permission.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(permission.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.read").value(DEFAULT_READ.booleanValue()))
      .andExpect(jsonPath("$.write").value(DEFAULT_WRITE.booleanValue()))
      .andExpect(jsonPath("$.share").value(DEFAULT_SHARE.booleanValue()))
      .andExpect(jsonPath("$.delete").value(DEFAULT_DELETE.booleanValue()))
      .andExpect(jsonPath("$.add").value(DEFAULT_ADD.booleanValue()))
      .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL));
  }

  @Test
  @Transactional
  void getPermissionsByIdFiltering() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    Long id = permission.getId();

    defaultPermissionShouldBeFound("id.equals=" + id);
    defaultPermissionShouldNotBeFound("id.notEquals=" + id);

    defaultPermissionShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultPermissionShouldNotBeFound("id.greaterThan=" + id);

    defaultPermissionShouldBeFound("id.lessThanOrEqual=" + id);
    defaultPermissionShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllPermissionsByUuidIsEqualToSomething() throws Exception
  {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where uuid equals to DEFAULT_UUID
    defaultPermissionShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the permissionList where uuid equals to UPDATED_UUID
    defaultPermissionShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPermissionsByUuidIsNotEqualToSomething() throws Exception
  {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where uuid not equals to DEFAULT_UUID
    defaultPermissionShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the permissionList where uuid not equals to UPDATED_UUID
    defaultPermissionShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPermissionsByUuidIsInShouldWork() throws Exception
  {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultPermissionShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the permissionList where uuid equals to UPDATED_UUID
    defaultPermissionShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPermissionsByUuidIsNullOrNotNull() throws Exception
  {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where uuid is not null
    defaultPermissionShouldBeFound("uuid.specified=true");

    // Get all the permissionList where uuid is null
    defaultPermissionShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllPermissionsByReadIsEqualToSomething() throws Exception
  {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where read equals to DEFAULT_READ
    defaultPermissionShouldBeFound("read.equals=" + DEFAULT_READ);

    // Get all the permissionList where read equals to UPDATED_READ
    defaultPermissionShouldNotBeFound("read.equals=" + UPDATED_READ);
  }

  @Test
  @Transactional
  void getAllPermissionsByReadIsNotEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where read not equals to DEFAULT_READ
    defaultPermissionShouldNotBeFound("read.notEquals=" + DEFAULT_READ);

    // Get all the permissionList where read not equals to UPDATED_READ
    defaultPermissionShouldBeFound("read.notEquals=" + UPDATED_READ);
  }

  @Test
  @Transactional
  void getAllPermissionsByReadIsInShouldWork() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where read in DEFAULT_READ or UPDATED_READ
    defaultPermissionShouldBeFound("read.in=" + DEFAULT_READ + "," + UPDATED_READ);

    // Get all the permissionList where read equals to UPDATED_READ
    defaultPermissionShouldNotBeFound("read.in=" + UPDATED_READ);
  }

  @Test
  @Transactional
  void getAllPermissionsByReadIsNullOrNotNull() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where read is not null
    defaultPermissionShouldBeFound("read.specified=true");

    // Get all the permissionList where read is null
    defaultPermissionShouldNotBeFound("read.specified=false");
  }

  @Test
  @Transactional
  void getAllPermissionsByWriteIsEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where write equals to DEFAULT_WRITE
    defaultPermissionShouldBeFound("write.equals=" + DEFAULT_WRITE);

    // Get all the permissionList where write equals to UPDATED_WRITE
    defaultPermissionShouldNotBeFound("write.equals=" + UPDATED_WRITE);
  }

  @Test
  @Transactional
  void getAllPermissionsByWriteIsNotEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where write not equals to DEFAULT_WRITE
    defaultPermissionShouldNotBeFound("write.notEquals=" + DEFAULT_WRITE);

    // Get all the permissionList where write not equals to UPDATED_WRITE
    defaultPermissionShouldBeFound("write.notEquals=" + UPDATED_WRITE);
  }

  @Test
  @Transactional
  void getAllPermissionsByWriteIsInShouldWork() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where write in DEFAULT_WRITE or UPDATED_WRITE
    defaultPermissionShouldBeFound("write.in=" + DEFAULT_WRITE + "," + UPDATED_WRITE);

    // Get all the permissionList where write equals to UPDATED_WRITE
    defaultPermissionShouldNotBeFound("write.in=" + UPDATED_WRITE);
  }

  @Test
  @Transactional
  void getAllPermissionsByWriteIsNullOrNotNull() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where write is not null
    defaultPermissionShouldBeFound("write.specified=true");

    // Get all the permissionList where write is null
    defaultPermissionShouldNotBeFound("write.specified=false");
  }

  @Test
  @Transactional
  void getAllPermissionsByShareIsEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where share equals to DEFAULT_SHARE
    defaultPermissionShouldBeFound("share.equals=" + DEFAULT_SHARE);

    // Get all the permissionList where share equals to UPDATED_SHARE
    defaultPermissionShouldNotBeFound("share.equals=" + UPDATED_SHARE);
  }

  @Test
  @Transactional
  void getAllPermissionsByShareIsNotEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where share not equals to DEFAULT_SHARE
    defaultPermissionShouldNotBeFound("share.notEquals=" + DEFAULT_SHARE);

    // Get all the permissionList where share not equals to UPDATED_SHARE
    defaultPermissionShouldBeFound("share.notEquals=" + UPDATED_SHARE);
  }

  @Test
  @Transactional
  void getAllPermissionsByShareIsInShouldWork() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where share in DEFAULT_SHARE or UPDATED_SHARE
    defaultPermissionShouldBeFound("share.in=" + DEFAULT_SHARE + "," + UPDATED_SHARE);

    // Get all the permissionList where share equals to UPDATED_SHARE
    defaultPermissionShouldNotBeFound("share.in=" + UPDATED_SHARE);
  }

  @Test
  @Transactional
  void getAllPermissionsByShareIsNullOrNotNull() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where share is not null
    defaultPermissionShouldBeFound("share.specified=true");

    // Get all the permissionList where share is null
    defaultPermissionShouldNotBeFound("share.specified=false");
  }

  @Test
  @Transactional
  void getAllPermissionsByDeleteIsEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where delete equals to DEFAULT_DELETE
    defaultPermissionShouldBeFound("delete.equals=" + DEFAULT_DELETE);

    // Get all the permissionList where delete equals to UPDATED_DELETE
    defaultPermissionShouldNotBeFound("delete.equals=" + UPDATED_DELETE);
  }

  @Test
  @Transactional
  void getAllPermissionsByDeleteIsNotEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where delete not equals to DEFAULT_DELETE
    defaultPermissionShouldNotBeFound("delete.notEquals=" + DEFAULT_DELETE);

    // Get all the permissionList where delete not equals to UPDATED_DELETE
    defaultPermissionShouldBeFound("delete.notEquals=" + UPDATED_DELETE);
  }

  @Test
  @Transactional
  void getAllPermissionsByDeleteIsInShouldWork() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where delete in DEFAULT_DELETE or UPDATED_DELETE
    defaultPermissionShouldBeFound("delete.in=" + DEFAULT_DELETE + "," + UPDATED_DELETE);

    // Get all the permissionList where delete equals to UPDATED_DELETE
    defaultPermissionShouldNotBeFound("delete.in=" + UPDATED_DELETE);
  }

  @Test
  @Transactional
  void getAllPermissionsByDeleteIsNullOrNotNull() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where delete is not null
    defaultPermissionShouldBeFound("delete.specified=true");

    // Get all the permissionList where delete is null
    defaultPermissionShouldNotBeFound("delete.specified=false");
  }

  @Test
  @Transactional
  void getAllPermissionsByAddIsEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where add equals to DEFAULT_ADD
    defaultPermissionShouldBeFound("add.equals=" + DEFAULT_ADD);

    // Get all the permissionList where add equals to UPDATED_ADD
    defaultPermissionShouldNotBeFound("add.equals=" + UPDATED_ADD);
  }

  @Test
  @Transactional
  void getAllPermissionsByAddIsNotEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where add not equals to DEFAULT_ADD
    defaultPermissionShouldNotBeFound("add.notEquals=" + DEFAULT_ADD);

    // Get all the permissionList where add not equals to UPDATED_ADD
    defaultPermissionShouldBeFound("add.notEquals=" + UPDATED_ADD);
  }

  @Test
  @Transactional
  void getAllPermissionsByAddIsInShouldWork() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where add in DEFAULT_ADD or UPDATED_ADD
    defaultPermissionShouldBeFound("add.in=" + DEFAULT_ADD + "," + UPDATED_ADD);

    // Get all the permissionList where add equals to UPDATED_ADD
    defaultPermissionShouldNotBeFound("add.in=" + UPDATED_ADD);
  }

  @Test
  @Transactional
  void getAllPermissionsByAddIsNullOrNotNull() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where add is not null
    defaultPermissionShouldBeFound("add.specified=true");

    // Get all the permissionList where add is null
    defaultPermissionShouldNotBeFound("add.specified=false");
  }

  @Test
  @Transactional
  void getAllPermissionsByLevelIsEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where level equals to DEFAULT_LEVEL
    defaultPermissionShouldBeFound("level.equals=" + DEFAULT_LEVEL);

    // Get all the permissionList where level equals to UPDATED_LEVEL
    defaultPermissionShouldNotBeFound("level.equals=" + UPDATED_LEVEL);
  }

  @Test
  @Transactional
  void getAllPermissionsByLevelIsNotEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where level not equals to DEFAULT_LEVEL
    defaultPermissionShouldNotBeFound("level.notEquals=" + DEFAULT_LEVEL);

    // Get all the permissionList where level not equals to UPDATED_LEVEL
    defaultPermissionShouldBeFound("level.notEquals=" + UPDATED_LEVEL);
  }

  @Test
  @Transactional
  void getAllPermissionsByLevelIsInShouldWork() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where level in DEFAULT_LEVEL or UPDATED_LEVEL
    defaultPermissionShouldBeFound("level.in=" + DEFAULT_LEVEL + "," + UPDATED_LEVEL);

    // Get all the permissionList where level equals to UPDATED_LEVEL
    defaultPermissionShouldNotBeFound("level.in=" + UPDATED_LEVEL);
  }

  @Test
  @Transactional
  void getAllPermissionsByLevelIsNullOrNotNull() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where level is not null
    defaultPermissionShouldBeFound("level.specified=true");

    // Get all the permissionList where level is null
    defaultPermissionShouldNotBeFound("level.specified=false");
  }

  @Test
  @Transactional
  void getAllPermissionsByLevelIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where level is greater than or equal to DEFAULT_LEVEL
    defaultPermissionShouldBeFound("level.greaterThanOrEqual=" + DEFAULT_LEVEL);

    // Get all the permissionList where level is greater than or equal to UPDATED_LEVEL
    defaultPermissionShouldNotBeFound("level.greaterThanOrEqual=" + UPDATED_LEVEL);
  }

  @Test
  @Transactional
  void getAllPermissionsByLevelIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where level is less than or equal to DEFAULT_LEVEL
    defaultPermissionShouldBeFound("level.lessThanOrEqual=" + DEFAULT_LEVEL);

    // Get all the permissionList where level is less than or equal to SMALLER_LEVEL
    defaultPermissionShouldNotBeFound("level.lessThanOrEqual=" + SMALLER_LEVEL);
  }

  @Test
  @Transactional
  void getAllPermissionsByLevelIsLessThanSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where level is less than DEFAULT_LEVEL
    defaultPermissionShouldNotBeFound("level.lessThan=" + DEFAULT_LEVEL);

    // Get all the permissionList where level is less than UPDATED_LEVEL
    defaultPermissionShouldBeFound("level.lessThan=" + UPDATED_LEVEL);
  }

  @Test
  @Transactional
  void getAllPermissionsByLevelIsGreaterThanSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    // Get all the permissionList where level is greater than DEFAULT_LEVEL
    defaultPermissionShouldNotBeFound("level.greaterThan=" + DEFAULT_LEVEL);

    // Get all the permissionList where level is greater than SMALLER_LEVEL
    defaultPermissionShouldBeFound("level.greaterThan=" + SMALLER_LEVEL);
  }

  @Test
  @Transactional
  void getAllPermissionsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    permission.setBaseInfo(baseInfo);
    permissionRepository.saveAndFlush(permission);
    Long baseInfoId = baseInfo.getId();

    // Get all the permissionList where baseInfo equals to baseInfoId
    defaultPermissionShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the permissionList where baseInfo equals to (baseInfoId + 1)
    defaultPermissionShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllPermissionsByMasterUserIsEqualToSomething() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);
    MasterUser masterUser = MasterUserResourceIT.createEntity(em);
    em.persist(masterUser);
    em.flush();
    permission.setMasterUser(masterUser);
    permissionRepository.saveAndFlush(permission);
    Long masterUserId = masterUser.getId();

    // Get all the permissionList where masterUser equals to masterUserId
    defaultPermissionShouldBeFound("masterUserId.equals=" + masterUserId);

    // Get all the permissionList where masterUser equals to (masterUserId + 1)
    defaultPermissionShouldNotBeFound("masterUserId.equals=" + (masterUserId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultPermissionShouldBeFound(String filter) throws Exception {
    restPermissionMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(permission.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].read").value(hasItem(DEFAULT_READ.booleanValue())))
      .andExpect(jsonPath("$.[*].write").value(hasItem(DEFAULT_WRITE.booleanValue())))
      .andExpect(jsonPath("$.[*].share").value(hasItem(DEFAULT_SHARE.booleanValue())))
      .andExpect(jsonPath("$.[*].delete").value(hasItem(DEFAULT_DELETE.booleanValue())))
      .andExpect(jsonPath("$.[*].add").value(hasItem(DEFAULT_ADD.booleanValue())))
      .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)));

    // Check, that the count call also returns 1
    restPermissionMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultPermissionShouldNotBeFound(String filter) throws Exception {
    restPermissionMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restPermissionMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingPermission() throws Exception {
    // Get the permission
    restPermissionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewPermission() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    int databaseSizeBeforeUpdate = permissionRepository.findAll().size();

    // Update the permission
    Permission updatedPermission = permissionRepository.findById(permission.getId()).get();
    // Disconnect from session so that the updates on updatedPermission are not directly saved in db
    em.detach(updatedPermission);
    updatedPermission
      .uuid(UPDATED_UUID)
      .read(UPDATED_READ)
      .write(UPDATED_WRITE)
      .share(UPDATED_SHARE)
      .delete(UPDATED_DELETE)
      .add(UPDATED_ADD)
      .level(UPDATED_LEVEL);
    PermissionDTO permissionDTO = permissionMapper.toDto(updatedPermission);

    restPermissionMockMvc
      .perform(
        put(ENTITY_API_URL_ID, permissionDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(permissionDTO))
      )
      .andExpect(status().isOk());

    // Validate the Permission in the database
    List<Permission> permissionList = permissionRepository.findAll();
    assertThat(permissionList).hasSize(databaseSizeBeforeUpdate);
    Permission testPermission = permissionList.get(permissionList.size() - 1);
    assertThat(testPermission.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testPermission.getRead()).isEqualTo(UPDATED_READ);
    assertThat(testPermission.getWrite()).isEqualTo(UPDATED_WRITE);
    assertThat(testPermission.getShare()).isEqualTo(UPDATED_SHARE);
    assertThat(testPermission.getDelete()).isEqualTo(UPDATED_DELETE);
    assertThat(testPermission.getAdd()).isEqualTo(UPDATED_ADD);
    assertThat(testPermission.getLevel()).isEqualTo(UPDATED_LEVEL);

    // Validate the Permission in Elasticsearch
    verify(mockPermissionSearchRepository).save(testPermission);
  }

  @Test
  @Transactional
  void putNonExistingPermission() throws Exception {
    int databaseSizeBeforeUpdate = permissionRepository.findAll().size();
    permission.setId(count.incrementAndGet());

    // Create the Permission
    PermissionDTO permissionDTO = permissionMapper.toDto(permission);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restPermissionMockMvc
      .perform(
        put(ENTITY_API_URL_ID, permissionDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(permissionDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Permission in the database
    List<Permission> permissionList = permissionRepository.findAll();
    assertThat(permissionList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Permission in Elasticsearch
    verify(mockPermissionSearchRepository, times(0)).save(permission);
  }

  @Test
  @Transactional
  void putWithIdMismatchPermission() throws Exception {
    int databaseSizeBeforeUpdate = permissionRepository.findAll().size();
    permission.setId(count.incrementAndGet());

    // Create the Permission
    PermissionDTO permissionDTO = permissionMapper.toDto(permission);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPermissionMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(permissionDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Permission in the database
    List<Permission> permissionList = permissionRepository.findAll();
    assertThat(permissionList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Permission in Elasticsearch
    verify(mockPermissionSearchRepository, times(0)).save(permission);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamPermission() throws Exception {
    int databaseSizeBeforeUpdate = permissionRepository.findAll().size();
    permission.setId(count.incrementAndGet());

    // Create the Permission
    PermissionDTO permissionDTO = permissionMapper.toDto(permission);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPermissionMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(permissionDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Permission in the database
    List<Permission> permissionList = permissionRepository.findAll();
    assertThat(permissionList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Permission in Elasticsearch
    verify(mockPermissionSearchRepository, times(0)).save(permission);
  }

  @Test
  @Transactional
  void partialUpdatePermissionWithPatch() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    int databaseSizeBeforeUpdate = permissionRepository.findAll().size();

    // Update the permission using partial update
    Permission partialUpdatedPermission = new Permission();
    partialUpdatedPermission.setId(permission.getId());

    partialUpdatedPermission
      .uuid(UPDATED_UUID)
      .read(UPDATED_READ)
      .write(UPDATED_WRITE)
      .share(UPDATED_SHARE)
      .delete(UPDATED_DELETE)
      .add(UPDATED_ADD);

    restPermissionMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedPermission.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPermission))
      )
      .andExpect(status().isOk());

    // Validate the Permission in the database
    List<Permission> permissionList = permissionRepository.findAll();
    assertThat(permissionList).hasSize(databaseSizeBeforeUpdate);
    Permission testPermission = permissionList.get(permissionList.size() - 1);
    assertThat(testPermission.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testPermission.getRead()).isEqualTo(UPDATED_READ);
    assertThat(testPermission.getWrite()).isEqualTo(UPDATED_WRITE);
    assertThat(testPermission.getShare()).isEqualTo(UPDATED_SHARE);
    assertThat(testPermission.getDelete()).isEqualTo(UPDATED_DELETE);
    assertThat(testPermission.getAdd()).isEqualTo(UPDATED_ADD);
    assertThat(testPermission.getLevel()).isEqualTo(DEFAULT_LEVEL);
  }

  @Test
  @Transactional
  void fullUpdatePermissionWithPatch() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    int databaseSizeBeforeUpdate = permissionRepository.findAll().size();

    // Update the permission using partial update
    Permission partialUpdatedPermission = new Permission();
    partialUpdatedPermission.setId(permission.getId());

    partialUpdatedPermission
      .uuid(UPDATED_UUID)
      .read(UPDATED_READ)
      .write(UPDATED_WRITE)
      .share(UPDATED_SHARE)
      .delete(UPDATED_DELETE)
      .add(UPDATED_ADD)
      .level(UPDATED_LEVEL);

    restPermissionMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedPermission.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPermission))
      )
      .andExpect(status().isOk());

    // Validate the Permission in the database
    List<Permission> permissionList = permissionRepository.findAll();
    assertThat(permissionList).hasSize(databaseSizeBeforeUpdate);
    Permission testPermission = permissionList.get(permissionList.size() - 1);
    assertThat(testPermission.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testPermission.getRead()).isEqualTo(UPDATED_READ);
    assertThat(testPermission.getWrite()).isEqualTo(UPDATED_WRITE);
    assertThat(testPermission.getShare()).isEqualTo(UPDATED_SHARE);
    assertThat(testPermission.getDelete()).isEqualTo(UPDATED_DELETE);
    assertThat(testPermission.getAdd()).isEqualTo(UPDATED_ADD);
    assertThat(testPermission.getLevel()).isEqualTo(UPDATED_LEVEL);
  }

  @Test
  @Transactional
  void patchNonExistingPermission() throws Exception {
    int databaseSizeBeforeUpdate = permissionRepository.findAll().size();
    permission.setId(count.incrementAndGet());

    // Create the Permission
    PermissionDTO permissionDTO = permissionMapper.toDto(permission);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restPermissionMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, permissionDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(permissionDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Permission in the database
    List<Permission> permissionList = permissionRepository.findAll();
    assertThat(permissionList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Permission in Elasticsearch
    verify(mockPermissionSearchRepository, times(0)).save(permission);
  }

  @Test
  @Transactional
  void patchWithIdMismatchPermission() throws Exception {
    int databaseSizeBeforeUpdate = permissionRepository.findAll().size();
    permission.setId(count.incrementAndGet());

    // Create the Permission
    PermissionDTO permissionDTO = permissionMapper.toDto(permission);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPermissionMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(permissionDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Permission in the database
    List<Permission> permissionList = permissionRepository.findAll();
    assertThat(permissionList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Permission in Elasticsearch
    verify(mockPermissionSearchRepository, times(0)).save(permission);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamPermission() throws Exception {
    int databaseSizeBeforeUpdate = permissionRepository.findAll().size();
    permission.setId(count.incrementAndGet());

    // Create the Permission
    PermissionDTO permissionDTO = permissionMapper.toDto(permission);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPermissionMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(permissionDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Permission in the database
    List<Permission> permissionList = permissionRepository.findAll();
    assertThat(permissionList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Permission in Elasticsearch
    verify(mockPermissionSearchRepository, times(0)).save(permission);
  }

  @Test
  @Transactional
  void deletePermission() throws Exception {
    // Initialize the database
    permissionRepository.saveAndFlush(permission);

    int databaseSizeBeforeDelete = permissionRepository.findAll().size();

    // Delete the permission
    restPermissionMockMvc
      .perform(delete(ENTITY_API_URL_ID, permission.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<Permission> permissionList = permissionRepository.findAll();
    assertThat(permissionList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the Permission in Elasticsearch
    verify(mockPermissionSearchRepository, times(1)).deleteById(permission.getId());
  }

  @Test
  @Transactional
  void searchPermission() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    permissionRepository.saveAndFlush(permission);
    when(mockPermissionSearchRepository.search(queryStringQuery("id:" + permission.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(permission), PageRequest.of(0, 1), 1));

    // Search the permission
    restPermissionMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + permission.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(permission.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].read").value(hasItem(DEFAULT_READ.booleanValue())))
      .andExpect(jsonPath("$.[*].write").value(hasItem(DEFAULT_WRITE.booleanValue())))
      .andExpect(jsonPath("$.[*].share").value(hasItem(DEFAULT_SHARE.booleanValue())))
      .andExpect(jsonPath("$.[*].delete").value(hasItem(DEFAULT_DELETE.booleanValue())))
      .andExpect(jsonPath("$.[*].add").value(hasItem(DEFAULT_ADD.booleanValue())))
      .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)));
  }
}
