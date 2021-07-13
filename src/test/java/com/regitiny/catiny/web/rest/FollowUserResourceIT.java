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
import com.regitiny.catiny.domain.FollowUser;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.repository.FollowUserRepository;
import com.regitiny.catiny.repository.search.FollowUserSearchRepository;
import com.regitiny.catiny.service.criteria.FollowUserCriteria;
import com.regitiny.catiny.service.dto.FollowUserDTO;
import com.regitiny.catiny.service.mapper.FollowUserMapper;
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
 * Integration tests for the {@link FollowUserResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class FollowUserResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String ENTITY_API_URL = "/api/follow-users";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/follow-users";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private FollowUserRepository followUserRepository;

  @Autowired
  private FollowUserMapper followUserMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.FollowUserSearchRepositoryMockConfiguration
   */
  @Autowired
  private FollowUserSearchRepository mockFollowUserSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restFollowUserMockMvc;

  private FollowUser followUser;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static FollowUser createEntity(EntityManager em) {
    FollowUser followUser = new FollowUser().uuid(DEFAULT_UUID);
    return followUser;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static FollowUser createUpdatedEntity(EntityManager em) {
    FollowUser followUser = new FollowUser().uuid(UPDATED_UUID);
    return followUser;
  }

  @BeforeEach
  public void initTest() {
    followUser = createEntity(em);
  }

  @Test
  @Transactional
  void createFollowUser() throws Exception {
    int databaseSizeBeforeCreate = followUserRepository.findAll().size();
    // Create the FollowUser
    FollowUserDTO followUserDTO = followUserMapper.toDto(followUser);
    restFollowUserMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(followUserDTO)))
      .andExpect(status().isCreated());

    // Validate the FollowUser in the database
    List<FollowUser> followUserList = followUserRepository.findAll();
    assertThat(followUserList).hasSize(databaseSizeBeforeCreate + 1);
    FollowUser testFollowUser = followUserList.get(followUserList.size() - 1);
    assertThat(testFollowUser.getUuid()).isEqualTo(DEFAULT_UUID);

    // Validate the FollowUser in Elasticsearch
    verify(mockFollowUserSearchRepository, times(1)).save(testFollowUser);
  }

  @Test
  @Transactional
  void createFollowUserWithExistingId() throws Exception {
    // Create the FollowUser with an existing ID
    followUser.setId(1L);
    FollowUserDTO followUserDTO = followUserMapper.toDto(followUser);

    int databaseSizeBeforeCreate = followUserRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restFollowUserMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(followUserDTO)))
      .andExpect(status().isBadRequest());

    // Validate the FollowUser in the database
    List<FollowUser> followUserList = followUserRepository.findAll();
    assertThat(followUserList).hasSize(databaseSizeBeforeCreate);

    // Validate the FollowUser in Elasticsearch
    verify(mockFollowUserSearchRepository, times(0)).save(followUser);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = followUserRepository.findAll().size();
    // set the field null
    followUser.setUuid(null);

    // Create the FollowUser, which fails.
    FollowUserDTO followUserDTO = followUserMapper.toDto(followUser);

    restFollowUserMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(followUserDTO)))
      .andExpect(status().isBadRequest());

    List<FollowUser> followUserList = followUserRepository.findAll();
    assertThat(followUserList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllFollowUsers() throws Exception {
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);

    // Get all the followUserList
    restFollowUserMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(followUser.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
  }

  @Test
  @Transactional
  void getFollowUser() throws Exception {
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);

    // Get the followUser
    restFollowUserMockMvc
      .perform(get(ENTITY_API_URL_ID, followUser.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(followUser.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()));
  }

  @Test
  @Transactional
  void getFollowUsersByIdFiltering() throws Exception {
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);

    Long id = followUser.getId();

    defaultFollowUserShouldBeFound("id.equals=" + id);
    defaultFollowUserShouldNotBeFound("id.notEquals=" + id);

    defaultFollowUserShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultFollowUserShouldNotBeFound("id.greaterThan=" + id);

    defaultFollowUserShouldBeFound("id.lessThanOrEqual=" + id);
    defaultFollowUserShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllFollowUsersByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);

    // Get all the followUserList where uuid equals to DEFAULT_UUID
    defaultFollowUserShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the followUserList where uuid equals to UPDATED_UUID
    defaultFollowUserShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFollowUsersByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);

    // Get all the followUserList where uuid not equals to DEFAULT_UUID
    defaultFollowUserShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the followUserList where uuid not equals to UPDATED_UUID
    defaultFollowUserShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFollowUsersByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);

    // Get all the followUserList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultFollowUserShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the followUserList where uuid equals to UPDATED_UUID
    defaultFollowUserShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFollowUsersByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);

    // Get all the followUserList where uuid is not null
    defaultFollowUserShouldBeFound("uuid.specified=true");

    // Get all the followUserList where uuid is null
    defaultFollowUserShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllFollowUsersByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    followUser.setBaseInfo(baseInfo);
    followUserRepository.saveAndFlush(followUser);
    Long baseInfoId = baseInfo.getId();

    // Get all the followUserList where baseInfo equals to baseInfoId
    defaultFollowUserShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the followUserList where baseInfo equals to (baseInfoId + 1)
    defaultFollowUserShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllFollowUsersByFollowUserDetailsIsEqualToSomething() throws Exception {
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);
    MasterUser followUserDetails = MasterUserResourceIT.createEntity(em);
    em.persist(followUserDetails);
    em.flush();
    followUser.setFollowUserDetails(followUserDetails);
    followUserRepository.saveAndFlush(followUser);
    Long followUserDetailsId = followUserDetails.getId();

    // Get all the followUserList where followUserDetails equals to followUserDetailsId
    defaultFollowUserShouldBeFound("followUserDetailsId.equals=" + followUserDetailsId);

    // Get all the followUserList where followUserDetails equals to (followUserDetailsId + 1)
    defaultFollowUserShouldNotBeFound("followUserDetailsId.equals=" + (followUserDetailsId + 1));
  }

  @Test
  @Transactional
  void getAllFollowUsersByMasterUserIsEqualToSomething() throws Exception {
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);
    MasterUser masterUser = MasterUserResourceIT.createEntity(em);
    em.persist(masterUser);
    em.flush();
    followUser.setMasterUser(masterUser);
    followUserRepository.saveAndFlush(followUser);
    Long masterUserId = masterUser.getId();

    // Get all the followUserList where masterUser equals to masterUserId
    defaultFollowUserShouldBeFound("masterUserId.equals=" + masterUserId);

    // Get all the followUserList where masterUser equals to (masterUserId + 1)
    defaultFollowUserShouldNotBeFound("masterUserId.equals=" + (masterUserId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultFollowUserShouldBeFound(String filter) throws Exception {
    restFollowUserMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(followUser.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));

    // Check, that the count call also returns 1
    restFollowUserMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultFollowUserShouldNotBeFound(String filter) throws Exception {
    restFollowUserMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restFollowUserMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingFollowUser() throws Exception {
    // Get the followUser
    restFollowUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewFollowUser() throws Exception {
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);

    int databaseSizeBeforeUpdate = followUserRepository.findAll().size();

    // Update the followUser
    FollowUser updatedFollowUser = followUserRepository.findById(followUser.getId()).get();
    // Disconnect from session so that the updates on updatedFollowUser are not directly saved in db
    em.detach(updatedFollowUser);
    updatedFollowUser.uuid(UPDATED_UUID);
    FollowUserDTO followUserDTO = followUserMapper.toDto(updatedFollowUser);

    restFollowUserMockMvc
      .perform(
        put(ENTITY_API_URL_ID, followUserDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(followUserDTO))
      )
      .andExpect(status().isOk());

    // Validate the FollowUser in the database
    List<FollowUser> followUserList = followUserRepository.findAll();
    assertThat(followUserList).hasSize(databaseSizeBeforeUpdate);
    FollowUser testFollowUser = followUserList.get(followUserList.size() - 1);
    assertThat(testFollowUser.getUuid()).isEqualTo(UPDATED_UUID);

    // Validate the FollowUser in Elasticsearch
    verify(mockFollowUserSearchRepository).save(testFollowUser);
  }

  @Test
  @Transactional
  void putNonExistingFollowUser() throws Exception {
    int databaseSizeBeforeUpdate = followUserRepository.findAll().size();
    followUser.setId(count.incrementAndGet());

    // Create the FollowUser
    FollowUserDTO followUserDTO = followUserMapper.toDto(followUser);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restFollowUserMockMvc
      .perform(
        put(ENTITY_API_URL_ID, followUserDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(followUserDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FollowUser in the database
    List<FollowUser> followUserList = followUserRepository.findAll();
    assertThat(followUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowUser in Elasticsearch
    verify(mockFollowUserSearchRepository, times(0)).save(followUser);
  }

  @Test
  @Transactional
  void putWithIdMismatchFollowUser() throws Exception {
    int databaseSizeBeforeUpdate = followUserRepository.findAll().size();
    followUser.setId(count.incrementAndGet());

    // Create the FollowUser
    FollowUserDTO followUserDTO = followUserMapper.toDto(followUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFollowUserMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(followUserDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FollowUser in the database
    List<FollowUser> followUserList = followUserRepository.findAll();
    assertThat(followUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowUser in Elasticsearch
    verify(mockFollowUserSearchRepository, times(0)).save(followUser);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamFollowUser() throws Exception {
    int databaseSizeBeforeUpdate = followUserRepository.findAll().size();
    followUser.setId(count.incrementAndGet());

    // Create the FollowUser
    FollowUserDTO followUserDTO = followUserMapper.toDto(followUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFollowUserMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(followUserDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the FollowUser in the database
    List<FollowUser> followUserList = followUserRepository.findAll();
    assertThat(followUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowUser in Elasticsearch
    verify(mockFollowUserSearchRepository, times(0)).save(followUser);
  }

  @Test
  @Transactional
  void partialUpdateFollowUserWithPatch() throws Exception {
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);

    int databaseSizeBeforeUpdate = followUserRepository.findAll().size();

    // Update the followUser using partial update
    FollowUser partialUpdatedFollowUser = new FollowUser();
    partialUpdatedFollowUser.setId(followUser.getId());

    restFollowUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedFollowUser.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFollowUser))
      )
      .andExpect(status().isOk());

    // Validate the FollowUser in the database
    List<FollowUser> followUserList = followUserRepository.findAll();
    assertThat(followUserList).hasSize(databaseSizeBeforeUpdate);
    FollowUser testFollowUser = followUserList.get(followUserList.size() - 1);
    assertThat(testFollowUser.getUuid()).isEqualTo(DEFAULT_UUID);
  }

  @Test
  @Transactional
  void fullUpdateFollowUserWithPatch() throws Exception {
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);

    int databaseSizeBeforeUpdate = followUserRepository.findAll().size();

    // Update the followUser using partial update
    FollowUser partialUpdatedFollowUser = new FollowUser();
    partialUpdatedFollowUser.setId(followUser.getId());

    partialUpdatedFollowUser.uuid(UPDATED_UUID);

    restFollowUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedFollowUser.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFollowUser))
      )
      .andExpect(status().isOk());

    // Validate the FollowUser in the database
    List<FollowUser> followUserList = followUserRepository.findAll();
    assertThat(followUserList).hasSize(databaseSizeBeforeUpdate);
    FollowUser testFollowUser = followUserList.get(followUserList.size() - 1);
    assertThat(testFollowUser.getUuid()).isEqualTo(UPDATED_UUID);
  }

  @Test
  @Transactional
  void patchNonExistingFollowUser() throws Exception {
    int databaseSizeBeforeUpdate = followUserRepository.findAll().size();
    followUser.setId(count.incrementAndGet());

    // Create the FollowUser
    FollowUserDTO followUserDTO = followUserMapper.toDto(followUser);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restFollowUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, followUserDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(followUserDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FollowUser in the database
    List<FollowUser> followUserList = followUserRepository.findAll();
    assertThat(followUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowUser in Elasticsearch
    verify(mockFollowUserSearchRepository, times(0)).save(followUser);
  }

  @Test
  @Transactional
  void patchWithIdMismatchFollowUser() throws Exception {
    int databaseSizeBeforeUpdate = followUserRepository.findAll().size();
    followUser.setId(count.incrementAndGet());

    // Create the FollowUser
    FollowUserDTO followUserDTO = followUserMapper.toDto(followUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFollowUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(followUserDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FollowUser in the database
    List<FollowUser> followUserList = followUserRepository.findAll();
    assertThat(followUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowUser in Elasticsearch
    verify(mockFollowUserSearchRepository, times(0)).save(followUser);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamFollowUser() throws Exception {
    int databaseSizeBeforeUpdate = followUserRepository.findAll().size();
    followUser.setId(count.incrementAndGet());

    // Create the FollowUser
    FollowUserDTO followUserDTO = followUserMapper.toDto(followUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFollowUserMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(followUserDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the FollowUser in the database
    List<FollowUser> followUserList = followUserRepository.findAll();
    assertThat(followUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowUser in Elasticsearch
    verify(mockFollowUserSearchRepository, times(0)).save(followUser);
  }

  @Test
  @Transactional
  void deleteFollowUser() throws Exception {
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);

    int databaseSizeBeforeDelete = followUserRepository.findAll().size();

    // Delete the followUser
    restFollowUserMockMvc
      .perform(delete(ENTITY_API_URL_ID, followUser.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<FollowUser> followUserList = followUserRepository.findAll();
    assertThat(followUserList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the FollowUser in Elasticsearch
    verify(mockFollowUserSearchRepository, times(1)).deleteById(followUser.getId());
  }

  @Test
  @Transactional
  void searchFollowUser() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    followUserRepository.saveAndFlush(followUser);
    when(mockFollowUserSearchRepository.search(queryStringQuery("id:" + followUser.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(followUser), PageRequest.of(0, 1), 1));

    // Search the followUser
    restFollowUserMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + followUser.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(followUser.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
  }
}
