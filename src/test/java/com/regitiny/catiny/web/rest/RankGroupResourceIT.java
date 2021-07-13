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
import com.regitiny.catiny.domain.RankGroup;
import com.regitiny.catiny.domain.RankUser;
import com.regitiny.catiny.repository.RankGroupRepository;
import com.regitiny.catiny.repository.search.RankGroupSearchRepository;
import com.regitiny.catiny.service.criteria.RankGroupCriteria;
import com.regitiny.catiny.service.dto.RankGroupDTO;
import com.regitiny.catiny.service.mapper.RankGroupMapper;
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
 * Integration tests for the {@link RankGroupResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class RankGroupResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String ENTITY_API_URL = "/api/rank-groups";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/rank-groups";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private RankGroupRepository rankGroupRepository;

  @Autowired
  private RankGroupMapper rankGroupMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.RankGroupSearchRepositoryMockConfiguration
   */
  @Autowired
  private RankGroupSearchRepository mockRankGroupSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restRankGroupMockMvc;

  private RankGroup rankGroup;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static RankGroup createEntity(EntityManager em) {
    RankGroup rankGroup = new RankGroup().uuid(DEFAULT_UUID);
    return rankGroup;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static RankGroup createUpdatedEntity(EntityManager em) {
    RankGroup rankGroup = new RankGroup().uuid(UPDATED_UUID);
    return rankGroup;
  }

  @BeforeEach
  public void initTest() {
    rankGroup = createEntity(em);
  }

  @Test
  @Transactional
  void createRankGroup() throws Exception {
    int databaseSizeBeforeCreate = rankGroupRepository.findAll().size();
    // Create the RankGroup
    RankGroupDTO rankGroupDTO = rankGroupMapper.toDto(rankGroup);
    restRankGroupMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rankGroupDTO)))
      .andExpect(status().isCreated());

    // Validate the RankGroup in the database
    List<RankGroup> rankGroupList = rankGroupRepository.findAll();
    assertThat(rankGroupList).hasSize(databaseSizeBeforeCreate + 1);
    RankGroup testRankGroup = rankGroupList.get(rankGroupList.size() - 1);
    assertThat(testRankGroup.getUuid()).isEqualTo(DEFAULT_UUID);

    // Validate the RankGroup in Elasticsearch
    verify(mockRankGroupSearchRepository, times(1)).save(testRankGroup);
  }

  @Test
  @Transactional
  void createRankGroupWithExistingId() throws Exception {
    // Create the RankGroup with an existing ID
    rankGroup.setId(1L);
    RankGroupDTO rankGroupDTO = rankGroupMapper.toDto(rankGroup);

    int databaseSizeBeforeCreate = rankGroupRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restRankGroupMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rankGroupDTO)))
      .andExpect(status().isBadRequest());

    // Validate the RankGroup in the database
    List<RankGroup> rankGroupList = rankGroupRepository.findAll();
    assertThat(rankGroupList).hasSize(databaseSizeBeforeCreate);

    // Validate the RankGroup in Elasticsearch
    verify(mockRankGroupSearchRepository, times(0)).save(rankGroup);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = rankGroupRepository.findAll().size();
    // set the field null
    rankGroup.setUuid(null);

    // Create the RankGroup, which fails.
    RankGroupDTO rankGroupDTO = rankGroupMapper.toDto(rankGroup);

    restRankGroupMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rankGroupDTO)))
      .andExpect(status().isBadRequest());

    List<RankGroup> rankGroupList = rankGroupRepository.findAll();
    assertThat(rankGroupList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllRankGroups() throws Exception {
    // Initialize the database
    rankGroupRepository.saveAndFlush(rankGroup);

    // Get all the rankGroupList
    restRankGroupMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(rankGroup.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
  }

  @Test
  @Transactional
  void getRankGroup() throws Exception {
    // Initialize the database
    rankGroupRepository.saveAndFlush(rankGroup);

    // Get the rankGroup
    restRankGroupMockMvc
      .perform(get(ENTITY_API_URL_ID, rankGroup.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(rankGroup.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()));
  }

  @Test
  @Transactional
  void getRankGroupsByIdFiltering() throws Exception {
    // Initialize the database
    rankGroupRepository.saveAndFlush(rankGroup);

    Long id = rankGroup.getId();

    defaultRankGroupShouldBeFound("id.equals=" + id);
    defaultRankGroupShouldNotBeFound("id.notEquals=" + id);

    defaultRankGroupShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultRankGroupShouldNotBeFound("id.greaterThan=" + id);

    defaultRankGroupShouldBeFound("id.lessThanOrEqual=" + id);
    defaultRankGroupShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllRankGroupsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    rankGroupRepository.saveAndFlush(rankGroup);

    // Get all the rankGroupList where uuid equals to DEFAULT_UUID
    defaultRankGroupShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the rankGroupList where uuid equals to UPDATED_UUID
    defaultRankGroupShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllRankGroupsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    rankGroupRepository.saveAndFlush(rankGroup);

    // Get all the rankGroupList where uuid not equals to DEFAULT_UUID
    defaultRankGroupShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the rankGroupList where uuid not equals to UPDATED_UUID
    defaultRankGroupShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllRankGroupsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    rankGroupRepository.saveAndFlush(rankGroup);

    // Get all the rankGroupList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultRankGroupShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the rankGroupList where uuid equals to UPDATED_UUID
    defaultRankGroupShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllRankGroupsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    rankGroupRepository.saveAndFlush(rankGroup);

    // Get all the rankGroupList where uuid is not null
    defaultRankGroupShouldBeFound("uuid.specified=true");

    // Get all the rankGroupList where uuid is null
    defaultRankGroupShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllRankGroupsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    rankGroupRepository.saveAndFlush(rankGroup);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    rankGroup.setBaseInfo(baseInfo);
    rankGroupRepository.saveAndFlush(rankGroup);
    Long baseInfoId = baseInfo.getId();

    // Get all the rankGroupList where baseInfo equals to baseInfoId
    defaultRankGroupShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the rankGroupList where baseInfo equals to (baseInfoId + 1)
    defaultRankGroupShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllRankGroupsByRankUserIsEqualToSomething() throws Exception {
    // Initialize the database
    rankGroupRepository.saveAndFlush(rankGroup);
    RankUser rankUser = RankUserResourceIT.createEntity(em);
    em.persist(rankUser);
    em.flush();
    rankGroup.addRankUser(rankUser);
    rankGroupRepository.saveAndFlush(rankGroup);
    Long rankUserId = rankUser.getId();

    // Get all the rankGroupList where rankUser equals to rankUserId
    defaultRankGroupShouldBeFound("rankUserId.equals=" + rankUserId);

    // Get all the rankGroupList where rankUser equals to (rankUserId + 1)
    defaultRankGroupShouldNotBeFound("rankUserId.equals=" + (rankUserId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultRankGroupShouldBeFound(String filter) throws Exception {
    restRankGroupMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(rankGroup.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));

    // Check, that the count call also returns 1
    restRankGroupMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultRankGroupShouldNotBeFound(String filter) throws Exception {
    restRankGroupMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restRankGroupMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingRankGroup() throws Exception {
    // Get the rankGroup
    restRankGroupMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewRankGroup() throws Exception {
    // Initialize the database
    rankGroupRepository.saveAndFlush(rankGroup);

    int databaseSizeBeforeUpdate = rankGroupRepository.findAll().size();

    // Update the rankGroup
    RankGroup updatedRankGroup = rankGroupRepository.findById(rankGroup.getId()).get();
    // Disconnect from session so that the updates on updatedRankGroup are not directly saved in db
    em.detach(updatedRankGroup);
    updatedRankGroup.uuid(UPDATED_UUID);
    RankGroupDTO rankGroupDTO = rankGroupMapper.toDto(updatedRankGroup);

    restRankGroupMockMvc
      .perform(
        put(ENTITY_API_URL_ID, rankGroupDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(rankGroupDTO))
      )
      .andExpect(status().isOk());

    // Validate the RankGroup in the database
    List<RankGroup> rankGroupList = rankGroupRepository.findAll();
    assertThat(rankGroupList).hasSize(databaseSizeBeforeUpdate);
    RankGroup testRankGroup = rankGroupList.get(rankGroupList.size() - 1);
    assertThat(testRankGroup.getUuid()).isEqualTo(UPDATED_UUID);

    // Validate the RankGroup in Elasticsearch
    verify(mockRankGroupSearchRepository).save(testRankGroup);
  }

  @Test
  @Transactional
  void putNonExistingRankGroup() throws Exception {
    int databaseSizeBeforeUpdate = rankGroupRepository.findAll().size();
    rankGroup.setId(count.incrementAndGet());

    // Create the RankGroup
    RankGroupDTO rankGroupDTO = rankGroupMapper.toDto(rankGroup);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restRankGroupMockMvc
      .perform(
        put(ENTITY_API_URL_ID, rankGroupDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(rankGroupDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the RankGroup in the database
    List<RankGroup> rankGroupList = rankGroupRepository.findAll();
    assertThat(rankGroupList).hasSize(databaseSizeBeforeUpdate);

    // Validate the RankGroup in Elasticsearch
    verify(mockRankGroupSearchRepository, times(0)).save(rankGroup);
  }

  @Test
  @Transactional
  void putWithIdMismatchRankGroup() throws Exception {
    int databaseSizeBeforeUpdate = rankGroupRepository.findAll().size();
    rankGroup.setId(count.incrementAndGet());

    // Create the RankGroup
    RankGroupDTO rankGroupDTO = rankGroupMapper.toDto(rankGroup);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restRankGroupMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(rankGroupDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the RankGroup in the database
    List<RankGroup> rankGroupList = rankGroupRepository.findAll();
    assertThat(rankGroupList).hasSize(databaseSizeBeforeUpdate);

    // Validate the RankGroup in Elasticsearch
    verify(mockRankGroupSearchRepository, times(0)).save(rankGroup);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamRankGroup() throws Exception {
    int databaseSizeBeforeUpdate = rankGroupRepository.findAll().size();
    rankGroup.setId(count.incrementAndGet());

    // Create the RankGroup
    RankGroupDTO rankGroupDTO = rankGroupMapper.toDto(rankGroup);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restRankGroupMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rankGroupDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the RankGroup in the database
    List<RankGroup> rankGroupList = rankGroupRepository.findAll();
    assertThat(rankGroupList).hasSize(databaseSizeBeforeUpdate);

    // Validate the RankGroup in Elasticsearch
    verify(mockRankGroupSearchRepository, times(0)).save(rankGroup);
  }

  @Test
  @Transactional
  void partialUpdateRankGroupWithPatch() throws Exception {
    // Initialize the database
    rankGroupRepository.saveAndFlush(rankGroup);

    int databaseSizeBeforeUpdate = rankGroupRepository.findAll().size();

    // Update the rankGroup using partial update
    RankGroup partialUpdatedRankGroup = new RankGroup();
    partialUpdatedRankGroup.setId(rankGroup.getId());

    restRankGroupMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedRankGroup.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRankGroup))
      )
      .andExpect(status().isOk());

    // Validate the RankGroup in the database
    List<RankGroup> rankGroupList = rankGroupRepository.findAll();
    assertThat(rankGroupList).hasSize(databaseSizeBeforeUpdate);
    RankGroup testRankGroup = rankGroupList.get(rankGroupList.size() - 1);
    assertThat(testRankGroup.getUuid()).isEqualTo(DEFAULT_UUID);
  }

  @Test
  @Transactional
  void fullUpdateRankGroupWithPatch() throws Exception {
    // Initialize the database
    rankGroupRepository.saveAndFlush(rankGroup);

    int databaseSizeBeforeUpdate = rankGroupRepository.findAll().size();

    // Update the rankGroup using partial update
    RankGroup partialUpdatedRankGroup = new RankGroup();
    partialUpdatedRankGroup.setId(rankGroup.getId());

    partialUpdatedRankGroup.uuid(UPDATED_UUID);

    restRankGroupMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedRankGroup.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRankGroup))
      )
      .andExpect(status().isOk());

    // Validate the RankGroup in the database
    List<RankGroup> rankGroupList = rankGroupRepository.findAll();
    assertThat(rankGroupList).hasSize(databaseSizeBeforeUpdate);
    RankGroup testRankGroup = rankGroupList.get(rankGroupList.size() - 1);
    assertThat(testRankGroup.getUuid()).isEqualTo(UPDATED_UUID);
  }

  @Test
  @Transactional
  void patchNonExistingRankGroup() throws Exception {
    int databaseSizeBeforeUpdate = rankGroupRepository.findAll().size();
    rankGroup.setId(count.incrementAndGet());

    // Create the RankGroup
    RankGroupDTO rankGroupDTO = rankGroupMapper.toDto(rankGroup);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restRankGroupMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, rankGroupDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(rankGroupDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the RankGroup in the database
    List<RankGroup> rankGroupList = rankGroupRepository.findAll();
    assertThat(rankGroupList).hasSize(databaseSizeBeforeUpdate);

    // Validate the RankGroup in Elasticsearch
    verify(mockRankGroupSearchRepository, times(0)).save(rankGroup);
  }

  @Test
  @Transactional
  void patchWithIdMismatchRankGroup() throws Exception {
    int databaseSizeBeforeUpdate = rankGroupRepository.findAll().size();
    rankGroup.setId(count.incrementAndGet());

    // Create the RankGroup
    RankGroupDTO rankGroupDTO = rankGroupMapper.toDto(rankGroup);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restRankGroupMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(rankGroupDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the RankGroup in the database
    List<RankGroup> rankGroupList = rankGroupRepository.findAll();
    assertThat(rankGroupList).hasSize(databaseSizeBeforeUpdate);

    // Validate the RankGroup in Elasticsearch
    verify(mockRankGroupSearchRepository, times(0)).save(rankGroup);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamRankGroup() throws Exception {
    int databaseSizeBeforeUpdate = rankGroupRepository.findAll().size();
    rankGroup.setId(count.incrementAndGet());

    // Create the RankGroup
    RankGroupDTO rankGroupDTO = rankGroupMapper.toDto(rankGroup);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restRankGroupMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(rankGroupDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the RankGroup in the database
    List<RankGroup> rankGroupList = rankGroupRepository.findAll();
    assertThat(rankGroupList).hasSize(databaseSizeBeforeUpdate);

    // Validate the RankGroup in Elasticsearch
    verify(mockRankGroupSearchRepository, times(0)).save(rankGroup);
  }

  @Test
  @Transactional
  void deleteRankGroup() throws Exception {
    // Initialize the database
    rankGroupRepository.saveAndFlush(rankGroup);

    int databaseSizeBeforeDelete = rankGroupRepository.findAll().size();

    // Delete the rankGroup
    restRankGroupMockMvc
      .perform(delete(ENTITY_API_URL_ID, rankGroup.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<RankGroup> rankGroupList = rankGroupRepository.findAll();
    assertThat(rankGroupList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the RankGroup in Elasticsearch
    verify(mockRankGroupSearchRepository, times(1)).deleteById(rankGroup.getId());
  }

  @Test
  @Transactional
  void searchRankGroup() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    rankGroupRepository.saveAndFlush(rankGroup);
    when(mockRankGroupSearchRepository.search(queryStringQuery("id:" + rankGroup.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(rankGroup), PageRequest.of(0, 1), 1));

    // Search the rankGroup
    restRankGroupMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + rankGroup.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(rankGroup.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
  }
}
