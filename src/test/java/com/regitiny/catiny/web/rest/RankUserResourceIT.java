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
import com.regitiny.catiny.repository.RankUserRepository;
import com.regitiny.catiny.repository.search.RankUserSearchRepository;
import com.regitiny.catiny.service.criteria.RankUserCriteria;
import com.regitiny.catiny.service.dto.RankUserDTO;
import com.regitiny.catiny.service.mapper.RankUserMapper;
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
 * Integration tests for the {@link RankUserResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class RankUserResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final Float DEFAULT_RATING_POINTS = 1F;
  private static final Float UPDATED_RATING_POINTS = 2F;
  private static final Float SMALLER_RATING_POINTS = 1F - 1F;

  private static final String ENTITY_API_URL = "/api/rank-users";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/rank-users";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private RankUserRepository rankUserRepository;

  @Autowired
  private RankUserMapper rankUserMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.RankUserSearchRepositoryMockConfiguration
   */
  @Autowired
  private RankUserSearchRepository mockRankUserSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restRankUserMockMvc;

  private RankUser rankUser;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static RankUser createEntity(EntityManager em) {
    RankUser rankUser = new RankUser().uuid(DEFAULT_UUID).ratingPoints(DEFAULT_RATING_POINTS);
    return rankUser;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static RankUser createUpdatedEntity(EntityManager em) {
    RankUser rankUser = new RankUser().uuid(UPDATED_UUID).ratingPoints(UPDATED_RATING_POINTS);
    return rankUser;
  }

  @BeforeEach
  public void initTest() {
    rankUser = createEntity(em);
  }

  @Test
  @Transactional
  void createRankUser() throws Exception {
    int databaseSizeBeforeCreate = rankUserRepository.findAll().size();
    // Create the RankUser
    RankUserDTO rankUserDTO = rankUserMapper.toDto(rankUser);
    restRankUserMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rankUserDTO)))
      .andExpect(status().isCreated());

    // Validate the RankUser in the database
    List<RankUser> rankUserList = rankUserRepository.findAll();
    assertThat(rankUserList).hasSize(databaseSizeBeforeCreate + 1);
    RankUser testRankUser = rankUserList.get(rankUserList.size() - 1);
    assertThat(testRankUser.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testRankUser.getRatingPoints()).isEqualTo(DEFAULT_RATING_POINTS);

    // Validate the RankUser in Elasticsearch
    verify(mockRankUserSearchRepository, times(1)).save(testRankUser);
  }

  @Test
  @Transactional
  void createRankUserWithExistingId() throws Exception {
    // Create the RankUser with an existing ID
    rankUser.setId(1L);
    RankUserDTO rankUserDTO = rankUserMapper.toDto(rankUser);

    int databaseSizeBeforeCreate = rankUserRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restRankUserMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rankUserDTO)))
      .andExpect(status().isBadRequest());

    // Validate the RankUser in the database
    List<RankUser> rankUserList = rankUserRepository.findAll();
    assertThat(rankUserList).hasSize(databaseSizeBeforeCreate);

    // Validate the RankUser in Elasticsearch
    verify(mockRankUserSearchRepository, times(0)).save(rankUser);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = rankUserRepository.findAll().size();
    // set the field null
    rankUser.setUuid(null);

    // Create the RankUser, which fails.
    RankUserDTO rankUserDTO = rankUserMapper.toDto(rankUser);

    restRankUserMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rankUserDTO)))
      .andExpect(status().isBadRequest());

    List<RankUser> rankUserList = rankUserRepository.findAll();
    assertThat(rankUserList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllRankUsers() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    // Get all the rankUserList
    restRankUserMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(rankUser.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].ratingPoints").value(hasItem(DEFAULT_RATING_POINTS.doubleValue())));
  }

  @Test
  @Transactional
  void getRankUser() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    // Get the rankUser
    restRankUserMockMvc
      .perform(get(ENTITY_API_URL_ID, rankUser.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(rankUser.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.ratingPoints").value(DEFAULT_RATING_POINTS.doubleValue()));
  }

  @Test
  @Transactional
  void getRankUsersByIdFiltering() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    Long id = rankUser.getId();

    defaultRankUserShouldBeFound("id.equals=" + id);
    defaultRankUserShouldNotBeFound("id.notEquals=" + id);

    defaultRankUserShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultRankUserShouldNotBeFound("id.greaterThan=" + id);

    defaultRankUserShouldBeFound("id.lessThanOrEqual=" + id);
    defaultRankUserShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllRankUsersByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    // Get all the rankUserList where uuid equals to DEFAULT_UUID
    defaultRankUserShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the rankUserList where uuid equals to UPDATED_UUID
    defaultRankUserShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllRankUsersByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    // Get all the rankUserList where uuid not equals to DEFAULT_UUID
    defaultRankUserShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the rankUserList where uuid not equals to UPDATED_UUID
    defaultRankUserShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllRankUsersByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    // Get all the rankUserList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultRankUserShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the rankUserList where uuid equals to UPDATED_UUID
    defaultRankUserShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllRankUsersByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    // Get all the rankUserList where uuid is not null
    defaultRankUserShouldBeFound("uuid.specified=true");

    // Get all the rankUserList where uuid is null
    defaultRankUserShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllRankUsersByRatingPointsIsEqualToSomething() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    // Get all the rankUserList where ratingPoints equals to DEFAULT_RATING_POINTS
    defaultRankUserShouldBeFound("ratingPoints.equals=" + DEFAULT_RATING_POINTS);

    // Get all the rankUserList where ratingPoints equals to UPDATED_RATING_POINTS
    defaultRankUserShouldNotBeFound("ratingPoints.equals=" + UPDATED_RATING_POINTS);
  }

  @Test
  @Transactional
  void getAllRankUsersByRatingPointsIsNotEqualToSomething() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    // Get all the rankUserList where ratingPoints not equals to DEFAULT_RATING_POINTS
    defaultRankUserShouldNotBeFound("ratingPoints.notEquals=" + DEFAULT_RATING_POINTS);

    // Get all the rankUserList where ratingPoints not equals to UPDATED_RATING_POINTS
    defaultRankUserShouldBeFound("ratingPoints.notEquals=" + UPDATED_RATING_POINTS);
  }

  @Test
  @Transactional
  void getAllRankUsersByRatingPointsIsInShouldWork() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    // Get all the rankUserList where ratingPoints in DEFAULT_RATING_POINTS or UPDATED_RATING_POINTS
    defaultRankUserShouldBeFound("ratingPoints.in=" + DEFAULT_RATING_POINTS + "," + UPDATED_RATING_POINTS);

    // Get all the rankUserList where ratingPoints equals to UPDATED_RATING_POINTS
    defaultRankUserShouldNotBeFound("ratingPoints.in=" + UPDATED_RATING_POINTS);
  }

  @Test
  @Transactional
  void getAllRankUsersByRatingPointsIsNullOrNotNull() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    // Get all the rankUserList where ratingPoints is not null
    defaultRankUserShouldBeFound("ratingPoints.specified=true");

    // Get all the rankUserList where ratingPoints is null
    defaultRankUserShouldNotBeFound("ratingPoints.specified=false");
  }

  @Test
  @Transactional
  void getAllRankUsersByRatingPointsIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    // Get all the rankUserList where ratingPoints is greater than or equal to DEFAULT_RATING_POINTS
    defaultRankUserShouldBeFound("ratingPoints.greaterThanOrEqual=" + DEFAULT_RATING_POINTS);

    // Get all the rankUserList where ratingPoints is greater than or equal to UPDATED_RATING_POINTS
    defaultRankUserShouldNotBeFound("ratingPoints.greaterThanOrEqual=" + UPDATED_RATING_POINTS);
  }

  @Test
  @Transactional
  void getAllRankUsersByRatingPointsIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    // Get all the rankUserList where ratingPoints is less than or equal to DEFAULT_RATING_POINTS
    defaultRankUserShouldBeFound("ratingPoints.lessThanOrEqual=" + DEFAULT_RATING_POINTS);

    // Get all the rankUserList where ratingPoints is less than or equal to SMALLER_RATING_POINTS
    defaultRankUserShouldNotBeFound("ratingPoints.lessThanOrEqual=" + SMALLER_RATING_POINTS);
  }

  @Test
  @Transactional
  void getAllRankUsersByRatingPointsIsLessThanSomething() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    // Get all the rankUserList where ratingPoints is less than DEFAULT_RATING_POINTS
    defaultRankUserShouldNotBeFound("ratingPoints.lessThan=" + DEFAULT_RATING_POINTS);

    // Get all the rankUserList where ratingPoints is less than UPDATED_RATING_POINTS
    defaultRankUserShouldBeFound("ratingPoints.lessThan=" + UPDATED_RATING_POINTS);
  }

  @Test
  @Transactional
  void getAllRankUsersByRatingPointsIsGreaterThanSomething() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    // Get all the rankUserList where ratingPoints is greater than DEFAULT_RATING_POINTS
    defaultRankUserShouldNotBeFound("ratingPoints.greaterThan=" + DEFAULT_RATING_POINTS);

    // Get all the rankUserList where ratingPoints is greater than SMALLER_RATING_POINTS
    defaultRankUserShouldBeFound("ratingPoints.greaterThan=" + SMALLER_RATING_POINTS);
  }

  @Test
  @Transactional
  void getAllRankUsersByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    rankUser.setBaseInfo(baseInfo);
    rankUserRepository.saveAndFlush(rankUser);
    Long baseInfoId = baseInfo.getId();

    // Get all the rankUserList where baseInfo equals to baseInfoId
    defaultRankUserShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the rankUserList where baseInfo equals to (baseInfoId + 1)
    defaultRankUserShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllRankUsersByRankGroupIsEqualToSomething() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);
    RankGroup rankGroup = RankGroupResourceIT.createEntity(em);
    em.persist(rankGroup);
    em.flush();
    rankUser.setRankGroup(rankGroup);
    rankUserRepository.saveAndFlush(rankUser);
    Long rankGroupId = rankGroup.getId();

    // Get all the rankUserList where rankGroup equals to rankGroupId
    defaultRankUserShouldBeFound("rankGroupId.equals=" + rankGroupId);

    // Get all the rankUserList where rankGroup equals to (rankGroupId + 1)
    defaultRankUserShouldNotBeFound("rankGroupId.equals=" + (rankGroupId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultRankUserShouldBeFound(String filter) throws Exception {
    restRankUserMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(rankUser.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].ratingPoints").value(hasItem(DEFAULT_RATING_POINTS.doubleValue())));

    // Check, that the count call also returns 1
    restRankUserMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultRankUserShouldNotBeFound(String filter) throws Exception {
    restRankUserMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restRankUserMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingRankUser() throws Exception {
    // Get the rankUser
    restRankUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewRankUser() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    int databaseSizeBeforeUpdate = rankUserRepository.findAll().size();

    // Update the rankUser
    RankUser updatedRankUser = rankUserRepository.findById(rankUser.getId()).get();
    // Disconnect from session so that the updates on updatedRankUser are not directly saved in db
    em.detach(updatedRankUser);
    updatedRankUser.uuid(UPDATED_UUID).ratingPoints(UPDATED_RATING_POINTS);
    RankUserDTO rankUserDTO = rankUserMapper.toDto(updatedRankUser);

    restRankUserMockMvc
      .perform(
        put(ENTITY_API_URL_ID, rankUserDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(rankUserDTO))
      )
      .andExpect(status().isOk());

    // Validate the RankUser in the database
    List<RankUser> rankUserList = rankUserRepository.findAll();
    assertThat(rankUserList).hasSize(databaseSizeBeforeUpdate);
    RankUser testRankUser = rankUserList.get(rankUserList.size() - 1);
    assertThat(testRankUser.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testRankUser.getRatingPoints()).isEqualTo(UPDATED_RATING_POINTS);

    // Validate the RankUser in Elasticsearch
    verify(mockRankUserSearchRepository).save(testRankUser);
  }

  @Test
  @Transactional
  void putNonExistingRankUser() throws Exception {
    int databaseSizeBeforeUpdate = rankUserRepository.findAll().size();
    rankUser.setId(count.incrementAndGet());

    // Create the RankUser
    RankUserDTO rankUserDTO = rankUserMapper.toDto(rankUser);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restRankUserMockMvc
      .perform(
        put(ENTITY_API_URL_ID, rankUserDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(rankUserDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the RankUser in the database
    List<RankUser> rankUserList = rankUserRepository.findAll();
    assertThat(rankUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the RankUser in Elasticsearch
    verify(mockRankUserSearchRepository, times(0)).save(rankUser);
  }

  @Test
  @Transactional
  void putWithIdMismatchRankUser() throws Exception {
    int databaseSizeBeforeUpdate = rankUserRepository.findAll().size();
    rankUser.setId(count.incrementAndGet());

    // Create the RankUser
    RankUserDTO rankUserDTO = rankUserMapper.toDto(rankUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restRankUserMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(rankUserDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the RankUser in the database
    List<RankUser> rankUserList = rankUserRepository.findAll();
    assertThat(rankUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the RankUser in Elasticsearch
    verify(mockRankUserSearchRepository, times(0)).save(rankUser);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamRankUser() throws Exception {
    int databaseSizeBeforeUpdate = rankUserRepository.findAll().size();
    rankUser.setId(count.incrementAndGet());

    // Create the RankUser
    RankUserDTO rankUserDTO = rankUserMapper.toDto(rankUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restRankUserMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rankUserDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the RankUser in the database
    List<RankUser> rankUserList = rankUserRepository.findAll();
    assertThat(rankUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the RankUser in Elasticsearch
    verify(mockRankUserSearchRepository, times(0)).save(rankUser);
  }

  @Test
  @Transactional
  void partialUpdateRankUserWithPatch() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    int databaseSizeBeforeUpdate = rankUserRepository.findAll().size();

    // Update the rankUser using partial update
    RankUser partialUpdatedRankUser = new RankUser();
    partialUpdatedRankUser.setId(rankUser.getId());

    partialUpdatedRankUser.uuid(UPDATED_UUID);

    restRankUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedRankUser.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRankUser))
      )
      .andExpect(status().isOk());

    // Validate the RankUser in the database
    List<RankUser> rankUserList = rankUserRepository.findAll();
    assertThat(rankUserList).hasSize(databaseSizeBeforeUpdate);
    RankUser testRankUser = rankUserList.get(rankUserList.size() - 1);
    assertThat(testRankUser.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testRankUser.getRatingPoints()).isEqualTo(DEFAULT_RATING_POINTS);
  }

  @Test
  @Transactional
  void fullUpdateRankUserWithPatch() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    int databaseSizeBeforeUpdate = rankUserRepository.findAll().size();

    // Update the rankUser using partial update
    RankUser partialUpdatedRankUser = new RankUser();
    partialUpdatedRankUser.setId(rankUser.getId());

    partialUpdatedRankUser.uuid(UPDATED_UUID).ratingPoints(UPDATED_RATING_POINTS);

    restRankUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedRankUser.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRankUser))
      )
      .andExpect(status().isOk());

    // Validate the RankUser in the database
    List<RankUser> rankUserList = rankUserRepository.findAll();
    assertThat(rankUserList).hasSize(databaseSizeBeforeUpdate);
    RankUser testRankUser = rankUserList.get(rankUserList.size() - 1);
    assertThat(testRankUser.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testRankUser.getRatingPoints()).isEqualTo(UPDATED_RATING_POINTS);
  }

  @Test
  @Transactional
  void patchNonExistingRankUser() throws Exception {
    int databaseSizeBeforeUpdate = rankUserRepository.findAll().size();
    rankUser.setId(count.incrementAndGet());

    // Create the RankUser
    RankUserDTO rankUserDTO = rankUserMapper.toDto(rankUser);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restRankUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, rankUserDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(rankUserDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the RankUser in the database
    List<RankUser> rankUserList = rankUserRepository.findAll();
    assertThat(rankUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the RankUser in Elasticsearch
    verify(mockRankUserSearchRepository, times(0)).save(rankUser);
  }

  @Test
  @Transactional
  void patchWithIdMismatchRankUser() throws Exception {
    int databaseSizeBeforeUpdate = rankUserRepository.findAll().size();
    rankUser.setId(count.incrementAndGet());

    // Create the RankUser
    RankUserDTO rankUserDTO = rankUserMapper.toDto(rankUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restRankUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(rankUserDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the RankUser in the database
    List<RankUser> rankUserList = rankUserRepository.findAll();
    assertThat(rankUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the RankUser in Elasticsearch
    verify(mockRankUserSearchRepository, times(0)).save(rankUser);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamRankUser() throws Exception {
    int databaseSizeBeforeUpdate = rankUserRepository.findAll().size();
    rankUser.setId(count.incrementAndGet());

    // Create the RankUser
    RankUserDTO rankUserDTO = rankUserMapper.toDto(rankUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restRankUserMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(rankUserDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the RankUser in the database
    List<RankUser> rankUserList = rankUserRepository.findAll();
    assertThat(rankUserList).hasSize(databaseSizeBeforeUpdate);

    // Validate the RankUser in Elasticsearch
    verify(mockRankUserSearchRepository, times(0)).save(rankUser);
  }

  @Test
  @Transactional
  void deleteRankUser() throws Exception {
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);

    int databaseSizeBeforeDelete = rankUserRepository.findAll().size();

    // Delete the rankUser
    restRankUserMockMvc
      .perform(delete(ENTITY_API_URL_ID, rankUser.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<RankUser> rankUserList = rankUserRepository.findAll();
    assertThat(rankUserList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the RankUser in Elasticsearch
    verify(mockRankUserSearchRepository, times(1)).deleteById(rankUser.getId());
  }

  @Test
  @Transactional
  void searchRankUser() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    rankUserRepository.saveAndFlush(rankUser);
    when(mockRankUserSearchRepository.search(queryStringQuery("id:" + rankUser.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(rankUser), PageRequest.of(0, 1), 1));

    // Search the rankUser
    restRankUserMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + rankUser.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(rankUser.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].ratingPoints").value(hasItem(DEFAULT_RATING_POINTS.doubleValue())));
  }
}
