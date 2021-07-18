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
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.domain.RankUser;
import com.regitiny.catiny.domain.TopicInterest;
import com.regitiny.catiny.domain.User;
import com.regitiny.catiny.repository.MasterUserRepository;
import com.regitiny.catiny.repository.search.MasterUserSearchRepository;
import com.regitiny.catiny.service.MasterUserService;
import com.regitiny.catiny.service.criteria.MasterUserCriteria;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import com.regitiny.catiny.service.mapper.MasterUserMapper;
import java.util.ArrayList;
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
 * Integration tests for the {@link MasterUserResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class MasterUserResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
  private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_NICKNAME = "AAAAAAAAAA";
  private static final String UPDATED_NICKNAME = "BBBBBBBBBB";

  private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
  private static final String UPDATED_AVATAR = "BBBBBBBBBB";

  private static final String DEFAULT_QUICK_INFO = "AAAAAAAAAA";
  private static final String UPDATED_QUICK_INFO = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/master-users";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/master-users";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private MasterUserRepository masterUserRepository;

  @Mock
  private MasterUserRepository masterUserRepositoryMock;

  @Autowired
  private MasterUserMapper masterUserMapper;

  @Mock
  private MasterUserService masterUserServiceMock;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.MasterUserSearchRepositoryMockConfiguration
   */
  @Autowired
  private MasterUserSearchRepository mockMasterUserSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restMasterUserMockMvc;

  private MasterUser masterUser;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static MasterUser createEntity(EntityManager em) {
    MasterUser masterUser = new MasterUser()
      .uuid(DEFAULT_UUID)
      .fullName(DEFAULT_FULL_NAME)
      .nickname(DEFAULT_NICKNAME)
      .avatar(DEFAULT_AVATAR)
      .quickInfo(DEFAULT_QUICK_INFO);
    // Add required entity
    User user = UserResourceIT.createEntity(em);
    em.persist(user);
    em.flush();
    masterUser.setUser(user);
    return masterUser;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static MasterUser createUpdatedEntity(EntityManager em) {
    MasterUser masterUser = new MasterUser()
      .uuid(UPDATED_UUID)
      .fullName(UPDATED_FULL_NAME)
      .nickname(UPDATED_NICKNAME)
      .avatar(UPDATED_AVATAR)
      .quickInfo(UPDATED_QUICK_INFO);
    // Add required entity
    User user = UserResourceIT.createEntity(em);
    em.persist(user);
    em.flush();
    masterUser.setUser(user);
    return masterUser;
  }

  @BeforeEach
  public void initTest() {
    masterUser = createEntity(em);
  }

  @Test
  @Transactional
  void createMasterUser() throws Exception {
    int databaseSizeBeforeCreate = masterUserRepository.findAll().size();
    // Create the MasterUser
    MasterUserDTO masterUserDTO = masterUserMapper.toDto(masterUser);
    restMasterUserMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(masterUserDTO)))
      .andExpect(status().isCreated());

    // Validate the MasterUser in the database
    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeCreate + 1);
    MasterUser testMasterUser = masterUserList.get(masterUserList.size() - 1);
    assertThat(testMasterUser.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testMasterUser.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
    assertThat(testMasterUser.getNickname()).isEqualTo(DEFAULT_NICKNAME);
    assertThat(testMasterUser.getAvatar()).isEqualTo(DEFAULT_AVATAR);
    assertThat(testMasterUser.getQuickInfo()).isEqualTo(DEFAULT_QUICK_INFO);

    // Validate the id for MapsId, the ids must be same
    assertThat(testMasterUser.getId()).isEqualTo(testMasterUser.getUser().getId());

    // Validate the MasterUser in Elasticsearch
    verify(mockMasterUserSearchRepository, times(1)).save(testMasterUser);
  }

  @Test
  @Transactional
  void createMasterUserWithExistingId() throws Exception {
    // Create the MasterUser with an existing ID
    masterUser.setId(1L);
    MasterUserDTO masterUserDTO = masterUserMapper.toDto(masterUser);

    int databaseSizeBeforeCreate = masterUserRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restMasterUserMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(masterUserDTO)))
      .andExpect(status().isBadRequest());

    // Validate the MasterUser in the database
    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeCreate);

    // Validate the MasterUser in Elasticsearch
    verify(mockMasterUserSearchRepository, times(0)).save(masterUser);
  }

  @Test
  @Transactional
  void updateMasterUserMapsIdAssociationWithNewId() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);
    int databaseSizeBeforeCreate = masterUserRepository.findAll().size();

    // Add a new parent entity
    User user = UserResourceIT.createEntity(em);
    em.persist(user);
    em.flush();

    // Load the masterUser
    MasterUser updatedMasterUser = masterUserRepository.findById(masterUser.getId()).get();
    assertThat(updatedMasterUser).isNotNull();
    // Disconnect from session so that the updates on updatedMasterUser are not directly saved in db
    em.detach(updatedMasterUser);

    // Update the User with new association value
    updatedMasterUser.setUser(user);
    MasterUserDTO updatedMasterUserDTO = masterUserMapper.toDto(updatedMasterUser);
    assertThat(updatedMasterUserDTO).isNotNull();

    // Update the entity
    restMasterUserMockMvc
      .perform(
        put(ENTITY_API_URL_ID, updatedMasterUserDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(updatedMasterUserDTO))
      )
      .andExpect(status().isOk());

    // Validate the MasterUser in the database
    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeCreate);
    MasterUser testMasterUser = masterUserList.get(masterUserList.size() - 1);

    // Validate the id for MapsId, the ids must be same
    // Uncomment the following line for assertion. However, please note that there is a known issue and uncommenting will fail the test.
    // Please look at https://github.com/jhipster/generator-jhipster/issues/9100. You can modify this test as necessary.
    // assertThat(testMasterUser.getId()).isEqualTo(testMasterUser.getUser().getId());

    // Validate the MasterUser in Elasticsearch
    verify(mockMasterUserSearchRepository).save(masterUser);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = masterUserRepository.findAll().size();
    // set the field null
    masterUser.setUuid(null);

    // Create the MasterUser, which fails.
    MasterUserDTO masterUserDTO = masterUserMapper.toDto(masterUser);

    restMasterUserMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(masterUserDTO)))
      .andExpect(status().isBadRequest());

    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void checkFullNameIsRequired() throws Exception {
    int databaseSizeBeforeTest = masterUserRepository.findAll().size();
    // set the field null
    masterUser.setFullName(null);

    // Create the MasterUser, which fails.
    MasterUserDTO masterUserDTO = masterUserMapper.toDto(masterUser);

    restMasterUserMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(masterUserDTO)))
      .andExpect(status().isBadRequest());

    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllMasterUsers() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList
    restMasterUserMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(masterUser.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
      .andExpect(jsonPath("$.[*].nickname").value(hasItem(DEFAULT_NICKNAME)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
      .andExpect(jsonPath("$.[*].quickInfo").value(hasItem(DEFAULT_QUICK_INFO.toString())));
  }

  @SuppressWarnings({ "unchecked" })
  void getAllMasterUsersWithEagerRelationshipsIsEnabled() throws Exception {
    when(masterUserServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

    restMasterUserMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

    verify(masterUserServiceMock, times(1)).findAllWithEagerRelationships(any());
  }

  @SuppressWarnings({ "unchecked" })
  void getAllMasterUsersWithEagerRelationshipsIsNotEnabled() throws Exception {
    when(masterUserServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

    restMasterUserMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

    verify(masterUserServiceMock, times(1)).findAllWithEagerRelationships(any());
  }

  @Test
  @Transactional
  void getMasterUser() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get the masterUser
    restMasterUserMockMvc
      .perform(get(ENTITY_API_URL_ID, masterUser.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(masterUser.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
      .andExpect(jsonPath("$.nickname").value(DEFAULT_NICKNAME))
      .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR.toString()))
      .andExpect(jsonPath("$.quickInfo").value(DEFAULT_QUICK_INFO.toString()));
  }

  @Test
  @Transactional
  void getMasterUsersByIdFiltering() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    Long id = masterUser.getId();

    defaultMasterUserShouldBeFound("id.equals=" + id);
    defaultMasterUserShouldNotBeFound("id.notEquals=" + id);

    defaultMasterUserShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultMasterUserShouldNotBeFound("id.greaterThan=" + id);

    defaultMasterUserShouldBeFound("id.lessThanOrEqual=" + id);
    defaultMasterUserShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllMasterUsersByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where uuid equals to DEFAULT_UUID
    defaultMasterUserShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the masterUserList where uuid equals to UPDATED_UUID
    defaultMasterUserShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllMasterUsersByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where uuid not equals to DEFAULT_UUID
    defaultMasterUserShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the masterUserList where uuid not equals to UPDATED_UUID
    defaultMasterUserShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllMasterUsersByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultMasterUserShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the masterUserList where uuid equals to UPDATED_UUID
    defaultMasterUserShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllMasterUsersByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where uuid is not null
    defaultMasterUserShouldBeFound("uuid.specified=true");

    // Get all the masterUserList where uuid is null
    defaultMasterUserShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllMasterUsersByFullNameIsEqualToSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where fullName equals to DEFAULT_FULL_NAME
    defaultMasterUserShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

    // Get all the masterUserList where fullName equals to UPDATED_FULL_NAME
    defaultMasterUserShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
  }

  @Test
  @Transactional
  void getAllMasterUsersByFullNameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where fullName not equals to DEFAULT_FULL_NAME
    defaultMasterUserShouldNotBeFound("fullName.notEquals=" + DEFAULT_FULL_NAME);

    // Get all the masterUserList where fullName not equals to UPDATED_FULL_NAME
    defaultMasterUserShouldBeFound("fullName.notEquals=" + UPDATED_FULL_NAME);
  }

  @Test
  @Transactional
  void getAllMasterUsersByFullNameIsInShouldWork() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
    defaultMasterUserShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

    // Get all the masterUserList where fullName equals to UPDATED_FULL_NAME
    defaultMasterUserShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
  }

  @Test
  @Transactional
  void getAllMasterUsersByFullNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where fullName is not null
    defaultMasterUserShouldBeFound("fullName.specified=true");

    // Get all the masterUserList where fullName is null
    defaultMasterUserShouldNotBeFound("fullName.specified=false");
  }

  @Test
  @Transactional
  void getAllMasterUsersByFullNameContainsSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where fullName contains DEFAULT_FULL_NAME
    defaultMasterUserShouldBeFound("fullName.contains=" + DEFAULT_FULL_NAME);

    // Get all the masterUserList where fullName contains UPDATED_FULL_NAME
    defaultMasterUserShouldNotBeFound("fullName.contains=" + UPDATED_FULL_NAME);
  }

  @Test
  @Transactional
  void getAllMasterUsersByFullNameNotContainsSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where fullName does not contain DEFAULT_FULL_NAME
    defaultMasterUserShouldNotBeFound("fullName.doesNotContain=" + DEFAULT_FULL_NAME);

    // Get all the masterUserList where fullName does not contain UPDATED_FULL_NAME
    defaultMasterUserShouldBeFound("fullName.doesNotContain=" + UPDATED_FULL_NAME);
  }

  @Test
  @Transactional
  void getAllMasterUsersByNicknameIsEqualToSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where nickname equals to DEFAULT_NICKNAME
    defaultMasterUserShouldBeFound("nickname.equals=" + DEFAULT_NICKNAME);

    // Get all the masterUserList where nickname equals to UPDATED_NICKNAME
    defaultMasterUserShouldNotBeFound("nickname.equals=" + UPDATED_NICKNAME);
  }

  @Test
  @Transactional
  void getAllMasterUsersByNicknameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where nickname not equals to DEFAULT_NICKNAME
    defaultMasterUserShouldNotBeFound("nickname.notEquals=" + DEFAULT_NICKNAME);

    // Get all the masterUserList where nickname not equals to UPDATED_NICKNAME
    defaultMasterUserShouldBeFound("nickname.notEquals=" + UPDATED_NICKNAME);
  }

  @Test
  @Transactional
  void getAllMasterUsersByNicknameIsInShouldWork() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where nickname in DEFAULT_NICKNAME or UPDATED_NICKNAME
    defaultMasterUserShouldBeFound("nickname.in=" + DEFAULT_NICKNAME + "," + UPDATED_NICKNAME);

    // Get all the masterUserList where nickname equals to UPDATED_NICKNAME
    defaultMasterUserShouldNotBeFound("nickname.in=" + UPDATED_NICKNAME);
  }

  @Test
  @Transactional
  void getAllMasterUsersByNicknameIsNullOrNotNull() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where nickname is not null
    defaultMasterUserShouldBeFound("nickname.specified=true");

    // Get all the masterUserList where nickname is null
    defaultMasterUserShouldNotBeFound("nickname.specified=false");
  }

  @Test
  @Transactional
  void getAllMasterUsersByNicknameContainsSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where nickname contains DEFAULT_NICKNAME
    defaultMasterUserShouldBeFound("nickname.contains=" + DEFAULT_NICKNAME);

    // Get all the masterUserList where nickname contains UPDATED_NICKNAME
    defaultMasterUserShouldNotBeFound("nickname.contains=" + UPDATED_NICKNAME);
  }

  @Test
  @Transactional
  void getAllMasterUsersByNicknameNotContainsSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    // Get all the masterUserList where nickname does not contain DEFAULT_NICKNAME
    defaultMasterUserShouldNotBeFound("nickname.doesNotContain=" + DEFAULT_NICKNAME);

    // Get all the masterUserList where nickname does not contain UPDATED_NICKNAME
    defaultMasterUserShouldBeFound("nickname.doesNotContain=" + UPDATED_NICKNAME);
  }

  @Test
  @Transactional
  void getAllMasterUsersByUserIsEqualToSomething() throws Exception {
    // Get already existing entity
    User user = masterUser.getUser();
    masterUserRepository.saveAndFlush(masterUser);
    Long userId = user.getId();

    // Get all the masterUserList where user equals to userId
    defaultMasterUserShouldBeFound("userId.equals=" + userId);

    // Get all the masterUserList where user equals to (userId + 1)
    defaultMasterUserShouldNotBeFound("userId.equals=" + (userId + 1));
  }

  @Test
  @Transactional
  void getAllMasterUsersByMyRankIsEqualToSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);
    RankUser myRank = RankUserResourceIT.createEntity(em);
    em.persist(myRank);
    em.flush();
    masterUser.setMyRank(myRank);
    masterUserRepository.saveAndFlush(masterUser);
    Long myRankId = myRank.getId();

    // Get all the masterUserList where myRank equals to myRankId
    defaultMasterUserShouldBeFound("myRankId.equals=" + myRankId);

    // Get all the masterUserList where myRank equals to (myRankId + 1)
    defaultMasterUserShouldNotBeFound("myRankId.equals=" + (myRankId + 1));
  }

  @Test
  @Transactional
  void getAllMasterUsersByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    masterUser.setBaseInfo(baseInfo);
    masterUserRepository.saveAndFlush(masterUser);
    Long baseInfoId = baseInfo.getId();

    // Get all the masterUserList where baseInfo equals to baseInfoId
    defaultMasterUserShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the masterUserList where baseInfo equals to (baseInfoId + 1)
    defaultMasterUserShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllMasterUsersByMyBaseInfoCreatedIsEqualToSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);
    BaseInfo myBaseInfoCreated = BaseInfoResourceIT.createEntity(em);
    em.persist(myBaseInfoCreated);
    em.flush();
    masterUser.addMyBaseInfoCreated(myBaseInfoCreated);
    masterUserRepository.saveAndFlush(masterUser);
    Long myBaseInfoCreatedId = myBaseInfoCreated.getId();

    // Get all the masterUserList where myBaseInfoCreated equals to myBaseInfoCreatedId
    defaultMasterUserShouldBeFound("myBaseInfoCreatedId.equals=" + myBaseInfoCreatedId);

    // Get all the masterUserList where myBaseInfoCreated equals to (myBaseInfoCreatedId + 1)
    defaultMasterUserShouldNotBeFound("myBaseInfoCreatedId.equals=" + (myBaseInfoCreatedId + 1));
  }

  @Test
  @Transactional
  void getAllMasterUsersByMyBaseInfoModifiedIsEqualToSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);
    BaseInfo myBaseInfoModified = BaseInfoResourceIT.createEntity(em);
    em.persist(myBaseInfoModified);
    em.flush();
    masterUser.addMyBaseInfoModified(myBaseInfoModified);
    masterUserRepository.saveAndFlush(masterUser);
    Long myBaseInfoModifiedId = myBaseInfoModified.getId();

    // Get all the masterUserList where myBaseInfoModified equals to myBaseInfoModifiedId
    defaultMasterUserShouldBeFound("myBaseInfoModifiedId.equals=" + myBaseInfoModifiedId);

    // Get all the masterUserList where myBaseInfoModified equals to (myBaseInfoModifiedId + 1)
    defaultMasterUserShouldNotBeFound("myBaseInfoModifiedId.equals=" + (myBaseInfoModifiedId + 1));
  }

  @Test
  @Transactional
  void getAllMasterUsersByOwnerOfIsEqualToSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);
    BaseInfo ownerOf = BaseInfoResourceIT.createEntity(em);
    em.persist(ownerOf);
    em.flush();
    masterUser.addOwnerOf(ownerOf);
    masterUserRepository.saveAndFlush(masterUser);
    Long ownerOfId = ownerOf.getId();

    // Get all the masterUserList where ownerOf equals to ownerOfId
    defaultMasterUserShouldBeFound("ownerOfId.equals=" + ownerOfId);

    // Get all the masterUserList where ownerOf equals to (ownerOfId + 1)
    defaultMasterUserShouldNotBeFound("ownerOfId.equals=" + (ownerOfId + 1));
  }

  @Test
  @Transactional
  void getAllMasterUsersByPermissionIsEqualToSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);
    Permission permission = PermissionResourceIT.createEntity(em);
    em.persist(permission);
    em.flush();
    masterUser.addPermission(permission);
    masterUserRepository.saveAndFlush(masterUser);
    Long permissionId = permission.getId();

    // Get all the masterUserList where permission equals to permissionId
    defaultMasterUserShouldBeFound("permissionId.equals=" + permissionId);

    // Get all the masterUserList where permission equals to (permissionId + 1)
    defaultMasterUserShouldNotBeFound("permissionId.equals=" + (permissionId + 1));
  }

  @Test
  @Transactional
  void getAllMasterUsersByTopicInterestIsEqualToSomething() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);
    TopicInterest topicInterest = TopicInterestResourceIT.createEntity(em);
    em.persist(topicInterest);
    em.flush();
    masterUser.addTopicInterest(topicInterest);
    masterUserRepository.saveAndFlush(masterUser);
    Long topicInterestId = topicInterest.getId();

    // Get all the masterUserList where topicInterest equals to topicInterestId
    defaultMasterUserShouldBeFound("topicInterestId.equals=" + topicInterestId);

    // Get all the masterUserList where topicInterest equals to (topicInterestId + 1)
    defaultMasterUserShouldNotBeFound("topicInterestId.equals=" + (topicInterestId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultMasterUserShouldBeFound(String filter) throws Exception {
    restMasterUserMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(masterUser.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
      .andExpect(jsonPath("$.[*].nickname").value(hasItem(DEFAULT_NICKNAME)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
      .andExpect(jsonPath("$.[*].quickInfo").value(hasItem(DEFAULT_QUICK_INFO.toString())));

    // Check, that the count call also returns 1
    restMasterUserMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultMasterUserShouldNotBeFound(String filter) throws Exception {
    restMasterUserMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restMasterUserMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingMasterUser() throws Exception {
    // Get the masterUser
    restMasterUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewMasterUser() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    int databaseSizeBeforeUpdate = masterUserRepository.findAll().size();

    // Update the masterUser
    MasterUser updatedMasterUser = masterUserRepository.findById(masterUser.getId()).get();
    // Disconnect from session so that the updates on updatedMasterUser are not directly saved in db
    em.detach(updatedMasterUser);
    updatedMasterUser
      .uuid(UPDATED_UUID)
      .fullName(UPDATED_FULL_NAME)
      .nickname(UPDATED_NICKNAME)
      .avatar(UPDATED_AVATAR)
      .quickInfo(UPDATED_QUICK_INFO);
    MasterUserDTO masterUserDTO = masterUserMapper.toDto(updatedMasterUser);

    restMasterUserMockMvc
      .perform(
        put(ENTITY_API_URL_ID, masterUserDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(masterUserDTO))
      )
      .andExpect(status().isOk());

    // Validate the MasterUser in the database
    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeUpdate);
    MasterUser testMasterUser = masterUserList.get(masterUserList.size() - 1);
    assertThat(testMasterUser.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testMasterUser.getFullName()).isEqualTo(UPDATED_FULL_NAME);
    assertThat(testMasterUser.getNickname()).isEqualTo(UPDATED_NICKNAME);
    assertThat(testMasterUser.getAvatar()).isEqualTo(UPDATED_AVATAR);
    assertThat(testMasterUser.getQuickInfo()).isEqualTo(UPDATED_QUICK_INFO);

    // Validate the MasterUser in Elasticsearch
    verify(mockMasterUserSearchRepository).save(testMasterUser);
  }

  @Test
  @Transactional
  void putNonExistingMasterUser() throws Exception {
    int databaseSizeBeforeUpdate = masterUserRepository.findAll().size();
    masterUser.setId(count.incrementAndGet());

    // Create the MasterUser
    MasterUserDTO masterUserDTO = masterUserMapper.toDto(masterUser);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restMasterUserMockMvc
      .perform(
        put(ENTITY_API_URL_ID, masterUserDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(masterUserDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the MasterUser in the database
    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the MasterUser in Elasticsearch
    verify(mockMasterUserSearchRepository, times(0)).save(masterUser);
  }

  @Test
  @Transactional
  void putWithIdMismatchMasterUser() throws Exception {
    int databaseSizeBeforeUpdate = masterUserRepository.findAll().size();
    masterUser.setId(count.incrementAndGet());

    // Create the MasterUser
    MasterUserDTO masterUserDTO = masterUserMapper.toDto(masterUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMasterUserMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(masterUserDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the MasterUser in the database
    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the MasterUser in Elasticsearch
    verify(mockMasterUserSearchRepository, times(0)).save(masterUser);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamMasterUser() throws Exception {
    int databaseSizeBeforeUpdate = masterUserRepository.findAll().size();
    masterUser.setId(count.incrementAndGet());

    // Create the MasterUser
    MasterUserDTO masterUserDTO = masterUserMapper.toDto(masterUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMasterUserMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(masterUserDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the MasterUser in the database
    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the MasterUser in Elasticsearch
    verify(mockMasterUserSearchRepository, times(0)).save(masterUser);
  }

  @Test
  @Transactional
  void partialUpdateMasterUserWithPatch() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    int databaseSizeBeforeUpdate = masterUserRepository.findAll().size();

    // Update the masterUser using partial update
    MasterUser partialUpdatedMasterUser = new MasterUser();
    partialUpdatedMasterUser.setId(masterUser.getId());

    partialUpdatedMasterUser
      .uuid(UPDATED_UUID)
      .fullName(UPDATED_FULL_NAME)
      .nickname(UPDATED_NICKNAME)
      .avatar(UPDATED_AVATAR)
      .quickInfo(UPDATED_QUICK_INFO);

    restMasterUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedMasterUser.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMasterUser))
      )
      .andExpect(status().isOk());

    // Validate the MasterUser in the database
    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeUpdate);
    MasterUser testMasterUser = masterUserList.get(masterUserList.size() - 1);
    assertThat(testMasterUser.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testMasterUser.getFullName()).isEqualTo(UPDATED_FULL_NAME);
    assertThat(testMasterUser.getNickname()).isEqualTo(UPDATED_NICKNAME);
    assertThat(testMasterUser.getAvatar()).isEqualTo(UPDATED_AVATAR);
    assertThat(testMasterUser.getQuickInfo()).isEqualTo(UPDATED_QUICK_INFO);
  }

  @Test
  @Transactional
  void fullUpdateMasterUserWithPatch() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    int databaseSizeBeforeUpdate = masterUserRepository.findAll().size();

    // Update the masterUser using partial update
    MasterUser partialUpdatedMasterUser = new MasterUser();
    partialUpdatedMasterUser.setId(masterUser.getId());

    partialUpdatedMasterUser
      .uuid(UPDATED_UUID)
      .fullName(UPDATED_FULL_NAME)
      .nickname(UPDATED_NICKNAME)
      .avatar(UPDATED_AVATAR)
      .quickInfo(UPDATED_QUICK_INFO);

    restMasterUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedMasterUser.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMasterUser))
      )
      .andExpect(status().isOk());

    // Validate the MasterUser in the database
    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeUpdate);
    MasterUser testMasterUser = masterUserList.get(masterUserList.size() - 1);
    assertThat(testMasterUser.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testMasterUser.getFullName()).isEqualTo(UPDATED_FULL_NAME);
    assertThat(testMasterUser.getNickname()).isEqualTo(UPDATED_NICKNAME);
    assertThat(testMasterUser.getAvatar()).isEqualTo(UPDATED_AVATAR);
    assertThat(testMasterUser.getQuickInfo()).isEqualTo(UPDATED_QUICK_INFO);
  }

  @Test
  @Transactional
  void patchNonExistingMasterUser() throws Exception {
    int databaseSizeBeforeUpdate = masterUserRepository.findAll().size();
    masterUser.setId(count.incrementAndGet());

    // Create the MasterUser
    MasterUserDTO masterUserDTO = masterUserMapper.toDto(masterUser);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restMasterUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, masterUserDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(masterUserDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the MasterUser in the database
    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the MasterUser in Elasticsearch
    verify(mockMasterUserSearchRepository, times(0)).save(masterUser);
  }

  @Test
  @Transactional
  void patchWithIdMismatchMasterUser() throws Exception {
    int databaseSizeBeforeUpdate = masterUserRepository.findAll().size();
    masterUser.setId(count.incrementAndGet());

    // Create the MasterUser
    MasterUserDTO masterUserDTO = masterUserMapper.toDto(masterUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMasterUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(masterUserDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the MasterUser in the database
    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the MasterUser in Elasticsearch
    verify(mockMasterUserSearchRepository, times(0)).save(masterUser);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamMasterUser() throws Exception {
    int databaseSizeBeforeUpdate = masterUserRepository.findAll().size();
    masterUser.setId(count.incrementAndGet());

    // Create the MasterUser
    MasterUserDTO masterUserDTO = masterUserMapper.toDto(masterUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMasterUserMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(masterUserDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the MasterUser in the database
    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the MasterUser in Elasticsearch
    verify(mockMasterUserSearchRepository, times(0)).save(masterUser);
  }

  @Test
  @Transactional
  void deleteMasterUser() throws Exception {
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);

    int databaseSizeBeforeDelete = masterUserRepository.findAll().size();

    // Delete the masterUser
    restMasterUserMockMvc
      .perform(delete(ENTITY_API_URL_ID, masterUser.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<MasterUser> masterUserList = masterUserRepository.findAll();
    assertThat(masterUserList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the MasterUser in Elasticsearch
    verify(mockMasterUserSearchRepository, times(1)).deleteById(masterUser.getId());
  }

  @Test
  @Transactional
  void searchMasterUser() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    masterUserRepository.saveAndFlush(masterUser);
    when(mockMasterUserSearchRepository.search(queryStringQuery("id:" + masterUser.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(masterUser), PageRequest.of(0, 1), 1));

    // Search the masterUser
    restMasterUserMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + masterUser.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(masterUser.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
      .andExpect(jsonPath("$.[*].nickname").value(hasItem(DEFAULT_NICKNAME)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
      .andExpect(jsonPath("$.[*].quickInfo").value(hasItem(DEFAULT_QUICK_INFO.toString())));
  }
}
