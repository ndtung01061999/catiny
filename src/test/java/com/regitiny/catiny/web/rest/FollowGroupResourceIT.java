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
import com.regitiny.catiny.domain.FollowGroup;
import com.regitiny.catiny.domain.GroupPost;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.repository.FollowGroupRepository;
import com.regitiny.catiny.repository.search.FollowGroupSearchRepository;
import com.regitiny.catiny.service.criteria.FollowGroupCriteria;
import com.regitiny.catiny.service.dto.FollowGroupDTO;
import com.regitiny.catiny.service.mapper.FollowGroupMapper;
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
 * Integration tests for the {@link FollowGroupResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class FollowGroupResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String ENTITY_API_URL = "/api/follow-groups";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/follow-groups";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private FollowGroupRepository followGroupRepository;

  @Autowired
  private FollowGroupMapper followGroupMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.FollowGroupSearchRepositoryMockConfiguration
   */
  @Autowired
  private FollowGroupSearchRepository mockFollowGroupSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restFollowGroupMockMvc;

  private FollowGroup followGroup;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static FollowGroup createEntity(EntityManager em) {
    FollowGroup followGroup = new FollowGroup().uuid(DEFAULT_UUID);
    return followGroup;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static FollowGroup createUpdatedEntity(EntityManager em) {
    FollowGroup followGroup = new FollowGroup().uuid(UPDATED_UUID);
    return followGroup;
  }

  @BeforeEach
  public void initTest() {
    followGroup = createEntity(em);
  }

  @Test
  @Transactional
  void createFollowGroup() throws Exception {
    int databaseSizeBeforeCreate = followGroupRepository.findAll().size();
    // Create the FollowGroup
    FollowGroupDTO followGroupDTO = followGroupMapper.toDto(followGroup);
    restFollowGroupMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(followGroupDTO)))
      .andExpect(status().isCreated());

    // Validate the FollowGroup in the database
    List<FollowGroup> followGroupList = followGroupRepository.findAll();
    assertThat(followGroupList).hasSize(databaseSizeBeforeCreate + 1);
    FollowGroup testFollowGroup = followGroupList.get(followGroupList.size() - 1);
    assertThat(testFollowGroup.getUuid()).isEqualTo(DEFAULT_UUID);

    // Validate the FollowGroup in Elasticsearch
    verify(mockFollowGroupSearchRepository, times(1)).save(testFollowGroup);
  }

  @Test
  @Transactional
  void createFollowGroupWithExistingId() throws Exception {
    // Create the FollowGroup with an existing ID
    followGroup.setId(1L);
    FollowGroupDTO followGroupDTO = followGroupMapper.toDto(followGroup);

    int databaseSizeBeforeCreate = followGroupRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restFollowGroupMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(followGroupDTO)))
      .andExpect(status().isBadRequest());

    // Validate the FollowGroup in the database
    List<FollowGroup> followGroupList = followGroupRepository.findAll();
    assertThat(followGroupList).hasSize(databaseSizeBeforeCreate);

    // Validate the FollowGroup in Elasticsearch
    verify(mockFollowGroupSearchRepository, times(0)).save(followGroup);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = followGroupRepository.findAll().size();
    // set the field null
    followGroup.setUuid(null);

    // Create the FollowGroup, which fails.
    FollowGroupDTO followGroupDTO = followGroupMapper.toDto(followGroup);

    restFollowGroupMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(followGroupDTO)))
      .andExpect(status().isBadRequest());

    List<FollowGroup> followGroupList = followGroupRepository.findAll();
    assertThat(followGroupList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllFollowGroups() throws Exception {
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);

    // Get all the followGroupList
    restFollowGroupMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(followGroup.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
  }

  @Test
  @Transactional
  void getFollowGroup() throws Exception {
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);

    // Get the followGroup
    restFollowGroupMockMvc
      .perform(get(ENTITY_API_URL_ID, followGroup.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(followGroup.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()));
  }

  @Test
  @Transactional
  void getFollowGroupsByIdFiltering() throws Exception {
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);

    Long id = followGroup.getId();

    defaultFollowGroupShouldBeFound("id.equals=" + id);
    defaultFollowGroupShouldNotBeFound("id.notEquals=" + id);

    defaultFollowGroupShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultFollowGroupShouldNotBeFound("id.greaterThan=" + id);

    defaultFollowGroupShouldBeFound("id.lessThanOrEqual=" + id);
    defaultFollowGroupShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllFollowGroupsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);

    // Get all the followGroupList where uuid equals to DEFAULT_UUID
    defaultFollowGroupShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the followGroupList where uuid equals to UPDATED_UUID
    defaultFollowGroupShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFollowGroupsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);

    // Get all the followGroupList where uuid not equals to DEFAULT_UUID
    defaultFollowGroupShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the followGroupList where uuid not equals to UPDATED_UUID
    defaultFollowGroupShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFollowGroupsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);

    // Get all the followGroupList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultFollowGroupShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the followGroupList where uuid equals to UPDATED_UUID
    defaultFollowGroupShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFollowGroupsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);

    // Get all the followGroupList where uuid is not null
    defaultFollowGroupShouldBeFound("uuid.specified=true");

    // Get all the followGroupList where uuid is null
    defaultFollowGroupShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllFollowGroupsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    followGroup.setBaseInfo(baseInfo);
    followGroupRepository.saveAndFlush(followGroup);
    Long baseInfoId = baseInfo.getId();

    // Get all the followGroupList where baseInfo equals to baseInfoId
    defaultFollowGroupShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the followGroupList where baseInfo equals to (baseInfoId + 1)
    defaultFollowGroupShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllFollowGroupsByFollowGroupDetailsIsEqualToSomething() throws Exception {
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);
    GroupPost followGroupDetails = GroupPostResourceIT.createEntity(em);
    em.persist(followGroupDetails);
    em.flush();
    followGroup.setFollowGroupDetails(followGroupDetails);
    followGroupRepository.saveAndFlush(followGroup);
    Long followGroupDetailsId = followGroupDetails.getId();

    // Get all the followGroupList where followGroupDetails equals to followGroupDetailsId
    defaultFollowGroupShouldBeFound("followGroupDetailsId.equals=" + followGroupDetailsId);

    // Get all the followGroupList where followGroupDetails equals to (followGroupDetailsId + 1)
    defaultFollowGroupShouldNotBeFound("followGroupDetailsId.equals=" + (followGroupDetailsId + 1));
  }

  @Test
  @Transactional
  void getAllFollowGroupsByMasterUserIsEqualToSomething() throws Exception {
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);
    MasterUser masterUser = MasterUserResourceIT.createEntity(em);
    em.persist(masterUser);
    em.flush();
    followGroup.setMasterUser(masterUser);
    followGroupRepository.saveAndFlush(followGroup);
    Long masterUserId = masterUser.getId();

    // Get all the followGroupList where masterUser equals to masterUserId
    defaultFollowGroupShouldBeFound("masterUserId.equals=" + masterUserId);

    // Get all the followGroupList where masterUser equals to (masterUserId + 1)
    defaultFollowGroupShouldNotBeFound("masterUserId.equals=" + (masterUserId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultFollowGroupShouldBeFound(String filter) throws Exception {
    restFollowGroupMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(followGroup.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));

    // Check, that the count call also returns 1
    restFollowGroupMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultFollowGroupShouldNotBeFound(String filter) throws Exception {
    restFollowGroupMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restFollowGroupMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingFollowGroup() throws Exception {
    // Get the followGroup
    restFollowGroupMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewFollowGroup() throws Exception {
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);

    int databaseSizeBeforeUpdate = followGroupRepository.findAll().size();

    // Update the followGroup
    FollowGroup updatedFollowGroup = followGroupRepository.findById(followGroup.getId()).get();
    // Disconnect from session so that the updates on updatedFollowGroup are not directly saved in db
    em.detach(updatedFollowGroup);
    updatedFollowGroup.uuid(UPDATED_UUID);
    FollowGroupDTO followGroupDTO = followGroupMapper.toDto(updatedFollowGroup);

    restFollowGroupMockMvc
      .perform(
        put(ENTITY_API_URL_ID, followGroupDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(followGroupDTO))
      )
      .andExpect(status().isOk());

    // Validate the FollowGroup in the database
    List<FollowGroup> followGroupList = followGroupRepository.findAll();
    assertThat(followGroupList).hasSize(databaseSizeBeforeUpdate);
    FollowGroup testFollowGroup = followGroupList.get(followGroupList.size() - 1);
    assertThat(testFollowGroup.getUuid()).isEqualTo(UPDATED_UUID);

    // Validate the FollowGroup in Elasticsearch
    verify(mockFollowGroupSearchRepository).save(testFollowGroup);
  }

  @Test
  @Transactional
  void putNonExistingFollowGroup() throws Exception {
    int databaseSizeBeforeUpdate = followGroupRepository.findAll().size();
    followGroup.setId(count.incrementAndGet());

    // Create the FollowGroup
    FollowGroupDTO followGroupDTO = followGroupMapper.toDto(followGroup);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restFollowGroupMockMvc
      .perform(
        put(ENTITY_API_URL_ID, followGroupDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(followGroupDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FollowGroup in the database
    List<FollowGroup> followGroupList = followGroupRepository.findAll();
    assertThat(followGroupList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowGroup in Elasticsearch
    verify(mockFollowGroupSearchRepository, times(0)).save(followGroup);
  }

  @Test
  @Transactional
  void putWithIdMismatchFollowGroup() throws Exception {
    int databaseSizeBeforeUpdate = followGroupRepository.findAll().size();
    followGroup.setId(count.incrementAndGet());

    // Create the FollowGroup
    FollowGroupDTO followGroupDTO = followGroupMapper.toDto(followGroup);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFollowGroupMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(followGroupDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FollowGroup in the database
    List<FollowGroup> followGroupList = followGroupRepository.findAll();
    assertThat(followGroupList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowGroup in Elasticsearch
    verify(mockFollowGroupSearchRepository, times(0)).save(followGroup);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamFollowGroup() throws Exception {
    int databaseSizeBeforeUpdate = followGroupRepository.findAll().size();
    followGroup.setId(count.incrementAndGet());

    // Create the FollowGroup
    FollowGroupDTO followGroupDTO = followGroupMapper.toDto(followGroup);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFollowGroupMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(followGroupDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the FollowGroup in the database
    List<FollowGroup> followGroupList = followGroupRepository.findAll();
    assertThat(followGroupList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowGroup in Elasticsearch
    verify(mockFollowGroupSearchRepository, times(0)).save(followGroup);
  }

  @Test
  @Transactional
  void partialUpdateFollowGroupWithPatch() throws Exception {
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);

    int databaseSizeBeforeUpdate = followGroupRepository.findAll().size();

    // Update the followGroup using partial update
    FollowGroup partialUpdatedFollowGroup = new FollowGroup();
    partialUpdatedFollowGroup.setId(followGroup.getId());

    partialUpdatedFollowGroup.uuid(UPDATED_UUID);

    restFollowGroupMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedFollowGroup.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFollowGroup))
      )
      .andExpect(status().isOk());

    // Validate the FollowGroup in the database
    List<FollowGroup> followGroupList = followGroupRepository.findAll();
    assertThat(followGroupList).hasSize(databaseSizeBeforeUpdate);
    FollowGroup testFollowGroup = followGroupList.get(followGroupList.size() - 1);
    assertThat(testFollowGroup.getUuid()).isEqualTo(UPDATED_UUID);
  }

  @Test
  @Transactional
  void fullUpdateFollowGroupWithPatch() throws Exception {
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);

    int databaseSizeBeforeUpdate = followGroupRepository.findAll().size();

    // Update the followGroup using partial update
    FollowGroup partialUpdatedFollowGroup = new FollowGroup();
    partialUpdatedFollowGroup.setId(followGroup.getId());

    partialUpdatedFollowGroup.uuid(UPDATED_UUID);

    restFollowGroupMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedFollowGroup.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFollowGroup))
      )
      .andExpect(status().isOk());

    // Validate the FollowGroup in the database
    List<FollowGroup> followGroupList = followGroupRepository.findAll();
    assertThat(followGroupList).hasSize(databaseSizeBeforeUpdate);
    FollowGroup testFollowGroup = followGroupList.get(followGroupList.size() - 1);
    assertThat(testFollowGroup.getUuid()).isEqualTo(UPDATED_UUID);
  }

  @Test
  @Transactional
  void patchNonExistingFollowGroup() throws Exception {
    int databaseSizeBeforeUpdate = followGroupRepository.findAll().size();
    followGroup.setId(count.incrementAndGet());

    // Create the FollowGroup
    FollowGroupDTO followGroupDTO = followGroupMapper.toDto(followGroup);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restFollowGroupMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, followGroupDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(followGroupDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FollowGroup in the database
    List<FollowGroup> followGroupList = followGroupRepository.findAll();
    assertThat(followGroupList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowGroup in Elasticsearch
    verify(mockFollowGroupSearchRepository, times(0)).save(followGroup);
  }

  @Test
  @Transactional
  void patchWithIdMismatchFollowGroup() throws Exception {
    int databaseSizeBeforeUpdate = followGroupRepository.findAll().size();
    followGroup.setId(count.incrementAndGet());

    // Create the FollowGroup
    FollowGroupDTO followGroupDTO = followGroupMapper.toDto(followGroup);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFollowGroupMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(followGroupDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FollowGroup in the database
    List<FollowGroup> followGroupList = followGroupRepository.findAll();
    assertThat(followGroupList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowGroup in Elasticsearch
    verify(mockFollowGroupSearchRepository, times(0)).save(followGroup);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamFollowGroup() throws Exception {
    int databaseSizeBeforeUpdate = followGroupRepository.findAll().size();
    followGroup.setId(count.incrementAndGet());

    // Create the FollowGroup
    FollowGroupDTO followGroupDTO = followGroupMapper.toDto(followGroup);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFollowGroupMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(followGroupDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the FollowGroup in the database
    List<FollowGroup> followGroupList = followGroupRepository.findAll();
    assertThat(followGroupList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowGroup in Elasticsearch
    verify(mockFollowGroupSearchRepository, times(0)).save(followGroup);
  }

  @Test
  @Transactional
  void deleteFollowGroup() throws Exception {
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);

    int databaseSizeBeforeDelete = followGroupRepository.findAll().size();

    // Delete the followGroup
    restFollowGroupMockMvc
      .perform(delete(ENTITY_API_URL_ID, followGroup.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<FollowGroup> followGroupList = followGroupRepository.findAll();
    assertThat(followGroupList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the FollowGroup in Elasticsearch
    verify(mockFollowGroupSearchRepository, times(1)).deleteById(followGroup.getId());
  }

  @Test
  @Transactional
  void searchFollowGroup() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    followGroupRepository.saveAndFlush(followGroup);
    when(mockFollowGroupSearchRepository.search(queryStringQuery("id:" + followGroup.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(followGroup), PageRequest.of(0, 1), 1));

    // Search the followGroup
    restFollowGroupMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + followGroup.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(followGroup.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
  }
}
