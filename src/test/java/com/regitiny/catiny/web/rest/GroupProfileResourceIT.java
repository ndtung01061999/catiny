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
import com.regitiny.catiny.domain.GroupPost;
import com.regitiny.catiny.domain.GroupProfile;
import com.regitiny.catiny.repository.GroupProfileRepository;
import com.regitiny.catiny.repository.search.GroupProfileSearchRepository;
import com.regitiny.catiny.service.criteria.GroupProfileCriteria;
import com.regitiny.catiny.service.dto.GroupProfileDTO;
import com.regitiny.catiny.service.mapper.GroupProfileMapper;
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
 * Integration tests for the {@link GroupProfileResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class GroupProfileResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String ENTITY_API_URL = "/api/group-profiles";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/group-profiles";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private GroupProfileRepository groupProfileRepository;

  @Autowired
  private GroupProfileMapper groupProfileMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.GroupProfileSearchRepositoryMockConfiguration
   */
  @Autowired
  private GroupProfileSearchRepository mockGroupProfileSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restGroupProfileMockMvc;

  private GroupProfile groupProfile;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static GroupProfile createEntity(EntityManager em) {
    GroupProfile groupProfile = new GroupProfile().uuid(DEFAULT_UUID);
    return groupProfile;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static GroupProfile createUpdatedEntity(EntityManager em) {
    GroupProfile groupProfile = new GroupProfile().uuid(UPDATED_UUID);
    return groupProfile;
  }

  @BeforeEach
  public void initTest() {
    groupProfile = createEntity(em);
  }

  @Test
  @Transactional
  void createGroupProfile() throws Exception {
    int databaseSizeBeforeCreate = groupProfileRepository.findAll().size();
    // Create the GroupProfile
    GroupProfileDTO groupProfileDTO = groupProfileMapper.toDto(groupProfile);
    restGroupProfileMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupProfileDTO)))
      .andExpect(status().isCreated());

    // Validate the GroupProfile in the database
    List<GroupProfile> groupProfileList = groupProfileRepository.findAll();
    assertThat(groupProfileList).hasSize(databaseSizeBeforeCreate + 1);
    GroupProfile testGroupProfile = groupProfileList.get(groupProfileList.size() - 1);
    assertThat(testGroupProfile.getUuid()).isEqualTo(DEFAULT_UUID);

    // Validate the GroupProfile in Elasticsearch
    verify(mockGroupProfileSearchRepository, times(1)).save(testGroupProfile);
  }

  @Test
  @Transactional
  void createGroupProfileWithExistingId() throws Exception {
    // Create the GroupProfile with an existing ID
    groupProfile.setId(1L);
    GroupProfileDTO groupProfileDTO = groupProfileMapper.toDto(groupProfile);

    int databaseSizeBeforeCreate = groupProfileRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restGroupProfileMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupProfileDTO)))
      .andExpect(status().isBadRequest());

    // Validate the GroupProfile in the database
    List<GroupProfile> groupProfileList = groupProfileRepository.findAll();
    assertThat(groupProfileList).hasSize(databaseSizeBeforeCreate);

    // Validate the GroupProfile in Elasticsearch
    verify(mockGroupProfileSearchRepository, times(0)).save(groupProfile);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = groupProfileRepository.findAll().size();
    // set the field null
    groupProfile.setUuid(null);

    // Create the GroupProfile, which fails.
    GroupProfileDTO groupProfileDTO = groupProfileMapper.toDto(groupProfile);

    restGroupProfileMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupProfileDTO)))
      .andExpect(status().isBadRequest());

    List<GroupProfile> groupProfileList = groupProfileRepository.findAll();
    assertThat(groupProfileList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllGroupProfiles() throws Exception {
    // Initialize the database
    groupProfileRepository.saveAndFlush(groupProfile);

    // Get all the groupProfileList
    restGroupProfileMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(groupProfile.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
  }

  @Test
  @Transactional
  void getGroupProfile() throws Exception {
    // Initialize the database
    groupProfileRepository.saveAndFlush(groupProfile);

    // Get the groupProfile
    restGroupProfileMockMvc
      .perform(get(ENTITY_API_URL_ID, groupProfile.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(groupProfile.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()));
  }

  @Test
  @Transactional
  void getGroupProfilesByIdFiltering() throws Exception {
    // Initialize the database
    groupProfileRepository.saveAndFlush(groupProfile);

    Long id = groupProfile.getId();

    defaultGroupProfileShouldBeFound("id.equals=" + id);
    defaultGroupProfileShouldNotBeFound("id.notEquals=" + id);

    defaultGroupProfileShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultGroupProfileShouldNotBeFound("id.greaterThan=" + id);

    defaultGroupProfileShouldBeFound("id.lessThanOrEqual=" + id);
    defaultGroupProfileShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllGroupProfilesByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    groupProfileRepository.saveAndFlush(groupProfile);

    // Get all the groupProfileList where uuid equals to DEFAULT_UUID
    defaultGroupProfileShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the groupProfileList where uuid equals to UPDATED_UUID
    defaultGroupProfileShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllGroupProfilesByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    groupProfileRepository.saveAndFlush(groupProfile);

    // Get all the groupProfileList where uuid not equals to DEFAULT_UUID
    defaultGroupProfileShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the groupProfileList where uuid not equals to UPDATED_UUID
    defaultGroupProfileShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllGroupProfilesByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    groupProfileRepository.saveAndFlush(groupProfile);

    // Get all the groupProfileList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultGroupProfileShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the groupProfileList where uuid equals to UPDATED_UUID
    defaultGroupProfileShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllGroupProfilesByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    groupProfileRepository.saveAndFlush(groupProfile);

    // Get all the groupProfileList where uuid is not null
    defaultGroupProfileShouldBeFound("uuid.specified=true");

    // Get all the groupProfileList where uuid is null
    defaultGroupProfileShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllGroupProfilesByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    groupProfileRepository.saveAndFlush(groupProfile);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    groupProfile.setBaseInfo(baseInfo);
    groupProfileRepository.saveAndFlush(groupProfile);
    Long baseInfoId = baseInfo.getId();

    // Get all the groupProfileList where baseInfo equals to baseInfoId
    defaultGroupProfileShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the groupProfileList where baseInfo equals to (baseInfoId + 1)
    defaultGroupProfileShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllGroupProfilesByGroupIsEqualToSomething() throws Exception {
    // Initialize the database
    groupProfileRepository.saveAndFlush(groupProfile);
    GroupPost group = GroupPostResourceIT.createEntity(em);
    em.persist(group);
    em.flush();
    groupProfile.setGroup(group);
    group.setProfile(groupProfile);
    groupProfileRepository.saveAndFlush(groupProfile);
    Long groupId = group.getId();

    // Get all the groupProfileList where group equals to groupId
    defaultGroupProfileShouldBeFound("groupId.equals=" + groupId);

    // Get all the groupProfileList where group equals to (groupId + 1)
    defaultGroupProfileShouldNotBeFound("groupId.equals=" + (groupId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultGroupProfileShouldBeFound(String filter) throws Exception {
    restGroupProfileMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(groupProfile.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));

    // Check, that the count call also returns 1
    restGroupProfileMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultGroupProfileShouldNotBeFound(String filter) throws Exception {
    restGroupProfileMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restGroupProfileMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingGroupProfile() throws Exception {
    // Get the groupProfile
    restGroupProfileMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewGroupProfile() throws Exception {
    // Initialize the database
    groupProfileRepository.saveAndFlush(groupProfile);

    int databaseSizeBeforeUpdate = groupProfileRepository.findAll().size();

    // Update the groupProfile
    GroupProfile updatedGroupProfile = groupProfileRepository.findById(groupProfile.getId()).get();
    // Disconnect from session so that the updates on updatedGroupProfile are not directly saved in db
    em.detach(updatedGroupProfile);
    updatedGroupProfile.uuid(UPDATED_UUID);
    GroupProfileDTO groupProfileDTO = groupProfileMapper.toDto(updatedGroupProfile);

    restGroupProfileMockMvc
      .perform(
        put(ENTITY_API_URL_ID, groupProfileDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(groupProfileDTO))
      )
      .andExpect(status().isOk());

    // Validate the GroupProfile in the database
    List<GroupProfile> groupProfileList = groupProfileRepository.findAll();
    assertThat(groupProfileList).hasSize(databaseSizeBeforeUpdate);
    GroupProfile testGroupProfile = groupProfileList.get(groupProfileList.size() - 1);
    assertThat(testGroupProfile.getUuid()).isEqualTo(UPDATED_UUID);

    // Validate the GroupProfile in Elasticsearch
    verify(mockGroupProfileSearchRepository).save(testGroupProfile);
  }

  @Test
  @Transactional
  void putNonExistingGroupProfile() throws Exception {
    int databaseSizeBeforeUpdate = groupProfileRepository.findAll().size();
    groupProfile.setId(count.incrementAndGet());

    // Create the GroupProfile
    GroupProfileDTO groupProfileDTO = groupProfileMapper.toDto(groupProfile);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restGroupProfileMockMvc
      .perform(
        put(ENTITY_API_URL_ID, groupProfileDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(groupProfileDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the GroupProfile in the database
    List<GroupProfile> groupProfileList = groupProfileRepository.findAll();
    assertThat(groupProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the GroupProfile in Elasticsearch
    verify(mockGroupProfileSearchRepository, times(0)).save(groupProfile);
  }

  @Test
  @Transactional
  void putWithIdMismatchGroupProfile() throws Exception {
    int databaseSizeBeforeUpdate = groupProfileRepository.findAll().size();
    groupProfile.setId(count.incrementAndGet());

    // Create the GroupProfile
    GroupProfileDTO groupProfileDTO = groupProfileMapper.toDto(groupProfile);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restGroupProfileMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(groupProfileDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the GroupProfile in the database
    List<GroupProfile> groupProfileList = groupProfileRepository.findAll();
    assertThat(groupProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the GroupProfile in Elasticsearch
    verify(mockGroupProfileSearchRepository, times(0)).save(groupProfile);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamGroupProfile() throws Exception {
    int databaseSizeBeforeUpdate = groupProfileRepository.findAll().size();
    groupProfile.setId(count.incrementAndGet());

    // Create the GroupProfile
    GroupProfileDTO groupProfileDTO = groupProfileMapper.toDto(groupProfile);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restGroupProfileMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupProfileDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the GroupProfile in the database
    List<GroupProfile> groupProfileList = groupProfileRepository.findAll();
    assertThat(groupProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the GroupProfile in Elasticsearch
    verify(mockGroupProfileSearchRepository, times(0)).save(groupProfile);
  }

  @Test
  @Transactional
  void partialUpdateGroupProfileWithPatch() throws Exception {
    // Initialize the database
    groupProfileRepository.saveAndFlush(groupProfile);

    int databaseSizeBeforeUpdate = groupProfileRepository.findAll().size();

    // Update the groupProfile using partial update
    GroupProfile partialUpdatedGroupProfile = new GroupProfile();
    partialUpdatedGroupProfile.setId(groupProfile.getId());

    partialUpdatedGroupProfile.uuid(UPDATED_UUID);

    restGroupProfileMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedGroupProfile.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGroupProfile))
      )
      .andExpect(status().isOk());

    // Validate the GroupProfile in the database
    List<GroupProfile> groupProfileList = groupProfileRepository.findAll();
    assertThat(groupProfileList).hasSize(databaseSizeBeforeUpdate);
    GroupProfile testGroupProfile = groupProfileList.get(groupProfileList.size() - 1);
    assertThat(testGroupProfile.getUuid()).isEqualTo(UPDATED_UUID);
  }

  @Test
  @Transactional
  void fullUpdateGroupProfileWithPatch() throws Exception {
    // Initialize the database
    groupProfileRepository.saveAndFlush(groupProfile);

    int databaseSizeBeforeUpdate = groupProfileRepository.findAll().size();

    // Update the groupProfile using partial update
    GroupProfile partialUpdatedGroupProfile = new GroupProfile();
    partialUpdatedGroupProfile.setId(groupProfile.getId());

    partialUpdatedGroupProfile.uuid(UPDATED_UUID);

    restGroupProfileMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedGroupProfile.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGroupProfile))
      )
      .andExpect(status().isOk());

    // Validate the GroupProfile in the database
    List<GroupProfile> groupProfileList = groupProfileRepository.findAll();
    assertThat(groupProfileList).hasSize(databaseSizeBeforeUpdate);
    GroupProfile testGroupProfile = groupProfileList.get(groupProfileList.size() - 1);
    assertThat(testGroupProfile.getUuid()).isEqualTo(UPDATED_UUID);
  }

  @Test
  @Transactional
  void patchNonExistingGroupProfile() throws Exception {
    int databaseSizeBeforeUpdate = groupProfileRepository.findAll().size();
    groupProfile.setId(count.incrementAndGet());

    // Create the GroupProfile
    GroupProfileDTO groupProfileDTO = groupProfileMapper.toDto(groupProfile);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restGroupProfileMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, groupProfileDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(groupProfileDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the GroupProfile in the database
    List<GroupProfile> groupProfileList = groupProfileRepository.findAll();
    assertThat(groupProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the GroupProfile in Elasticsearch
    verify(mockGroupProfileSearchRepository, times(0)).save(groupProfile);
  }

  @Test
  @Transactional
  void patchWithIdMismatchGroupProfile() throws Exception {
    int databaseSizeBeforeUpdate = groupProfileRepository.findAll().size();
    groupProfile.setId(count.incrementAndGet());

    // Create the GroupProfile
    GroupProfileDTO groupProfileDTO = groupProfileMapper.toDto(groupProfile);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restGroupProfileMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(groupProfileDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the GroupProfile in the database
    List<GroupProfile> groupProfileList = groupProfileRepository.findAll();
    assertThat(groupProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the GroupProfile in Elasticsearch
    verify(mockGroupProfileSearchRepository, times(0)).save(groupProfile);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamGroupProfile() throws Exception {
    int databaseSizeBeforeUpdate = groupProfileRepository.findAll().size();
    groupProfile.setId(count.incrementAndGet());

    // Create the GroupProfile
    GroupProfileDTO groupProfileDTO = groupProfileMapper.toDto(groupProfile);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restGroupProfileMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(groupProfileDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the GroupProfile in the database
    List<GroupProfile> groupProfileList = groupProfileRepository.findAll();
    assertThat(groupProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the GroupProfile in Elasticsearch
    verify(mockGroupProfileSearchRepository, times(0)).save(groupProfile);
  }

  @Test
  @Transactional
  void deleteGroupProfile() throws Exception {
    // Initialize the database
    groupProfileRepository.saveAndFlush(groupProfile);

    int databaseSizeBeforeDelete = groupProfileRepository.findAll().size();

    // Delete the groupProfile
    restGroupProfileMockMvc
      .perform(delete(ENTITY_API_URL_ID, groupProfile.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<GroupProfile> groupProfileList = groupProfileRepository.findAll();
    assertThat(groupProfileList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the GroupProfile in Elasticsearch
    verify(mockGroupProfileSearchRepository, times(1)).deleteById(groupProfile.getId());
  }

  @Test
  @Transactional
  void searchGroupProfile() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    groupProfileRepository.saveAndFlush(groupProfile);
    when(mockGroupProfileSearchRepository.search(queryStringQuery("id:" + groupProfile.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(groupProfile), PageRequest.of(0, 1), 1));

    // Search the groupProfile
    restGroupProfileMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + groupProfile.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(groupProfile.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
  }
}
