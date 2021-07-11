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
import com.regitiny.catiny.domain.Friend;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.enumeration.FriendType;
import com.regitiny.catiny.repository.FriendRepository;
import com.regitiny.catiny.repository.search.FriendSearchRepository;
import com.regitiny.catiny.service.criteria.FriendCriteria;
import com.regitiny.catiny.service.dto.FriendDTO;
import com.regitiny.catiny.service.mapper.FriendMapper;
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
 * Integration tests for the {@link FriendResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class FriendResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final FriendType DEFAULT_FRIEND_TYPE = FriendType.FRIEND;
  private static final FriendType UPDATED_FRIEND_TYPE = FriendType.BEST_FRIEND;

  private static final String ENTITY_API_URL = "/api/friends";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/friends";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private FriendRepository friendRepository;

  @Autowired
  private FriendMapper friendMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.FriendSearchRepositoryMockConfiguration
   */
  @Autowired
  private FriendSearchRepository mockFriendSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restFriendMockMvc;

  private Friend friend;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Friend createEntity(EntityManager em) {
    Friend friend = new Friend().uuid(DEFAULT_UUID).friendType(DEFAULT_FRIEND_TYPE);
    return friend;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Friend createUpdatedEntity(EntityManager em) {
    Friend friend = new Friend().uuid(UPDATED_UUID).friendType(UPDATED_FRIEND_TYPE);
    return friend;
  }

  @BeforeEach
  public void initTest() {
    friend = createEntity(em);
  }

  @Test
  @Transactional
  void createFriend() throws Exception {
    int databaseSizeBeforeCreate = friendRepository.findAll().size();
    // Create the Friend
    FriendDTO friendDTO = friendMapper.toDto(friend);
    restFriendMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(friendDTO)))
      .andExpect(status().isCreated());

    // Validate the Friend in the database
    List<Friend> friendList = friendRepository.findAll();
    assertThat(friendList).hasSize(databaseSizeBeforeCreate + 1);
    Friend testFriend = friendList.get(friendList.size() - 1);
    assertThat(testFriend.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testFriend.getFriendType()).isEqualTo(DEFAULT_FRIEND_TYPE);

    // Validate the Friend in Elasticsearch
    verify(mockFriendSearchRepository, times(1)).save(testFriend);
  }

  @Test
  @Transactional
  void createFriendWithExistingId() throws Exception {
    // Create the Friend with an existing ID
    friend.setId(1L);
    FriendDTO friendDTO = friendMapper.toDto(friend);

    int databaseSizeBeforeCreate = friendRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restFriendMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(friendDTO)))
      .andExpect(status().isBadRequest());

    // Validate the Friend in the database
    List<Friend> friendList = friendRepository.findAll();
    assertThat(friendList).hasSize(databaseSizeBeforeCreate);

    // Validate the Friend in Elasticsearch
    verify(mockFriendSearchRepository, times(0)).save(friend);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = friendRepository.findAll().size();
    // set the field null
    friend.setUuid(null);

    // Create the Friend, which fails.
    FriendDTO friendDTO = friendMapper.toDto(friend);

    restFriendMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(friendDTO)))
      .andExpect(status().isBadRequest());

    List<Friend> friendList = friendRepository.findAll();
    assertThat(friendList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllFriends() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    // Get all the friendList
    restFriendMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(friend.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].friendType").value(hasItem(DEFAULT_FRIEND_TYPE.toString())));
  }

  @Test
  @Transactional
  void getFriend() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    // Get the friend
    restFriendMockMvc
      .perform(get(ENTITY_API_URL_ID, friend.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(friend.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.friendType").value(DEFAULT_FRIEND_TYPE.toString()));
  }

  @Test
  @Transactional
  void getFriendsByIdFiltering() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    Long id = friend.getId();

    defaultFriendShouldBeFound("id.equals=" + id);
    defaultFriendShouldNotBeFound("id.notEquals=" + id);

    defaultFriendShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultFriendShouldNotBeFound("id.greaterThan=" + id);

    defaultFriendShouldBeFound("id.lessThanOrEqual=" + id);
    defaultFriendShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllFriendsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    // Get all the friendList where uuid equals to DEFAULT_UUID
    defaultFriendShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the friendList where uuid equals to UPDATED_UUID
    defaultFriendShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFriendsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    // Get all the friendList where uuid not equals to DEFAULT_UUID
    defaultFriendShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the friendList where uuid not equals to UPDATED_UUID
    defaultFriendShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFriendsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    // Get all the friendList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultFriendShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the friendList where uuid equals to UPDATED_UUID
    defaultFriendShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFriendsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    // Get all the friendList where uuid is not null
    defaultFriendShouldBeFound("uuid.specified=true");

    // Get all the friendList where uuid is null
    defaultFriendShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllFriendsByFriendTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    // Get all the friendList where friendType equals to DEFAULT_FRIEND_TYPE
    defaultFriendShouldBeFound("friendType.equals=" + DEFAULT_FRIEND_TYPE);

    // Get all the friendList where friendType equals to UPDATED_FRIEND_TYPE
    defaultFriendShouldNotBeFound("friendType.equals=" + UPDATED_FRIEND_TYPE);
  }

  @Test
  @Transactional
  void getAllFriendsByFriendTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    // Get all the friendList where friendType not equals to DEFAULT_FRIEND_TYPE
    defaultFriendShouldNotBeFound("friendType.notEquals=" + DEFAULT_FRIEND_TYPE);

    // Get all the friendList where friendType not equals to UPDATED_FRIEND_TYPE
    defaultFriendShouldBeFound("friendType.notEquals=" + UPDATED_FRIEND_TYPE);
  }

  @Test
  @Transactional
  void getAllFriendsByFriendTypeIsInShouldWork() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    // Get all the friendList where friendType in DEFAULT_FRIEND_TYPE or UPDATED_FRIEND_TYPE
    defaultFriendShouldBeFound("friendType.in=" + DEFAULT_FRIEND_TYPE + "," + UPDATED_FRIEND_TYPE);

    // Get all the friendList where friendType equals to UPDATED_FRIEND_TYPE
    defaultFriendShouldNotBeFound("friendType.in=" + UPDATED_FRIEND_TYPE);
  }

  @Test
  @Transactional
  void getAllFriendsByFriendTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    // Get all the friendList where friendType is not null
    defaultFriendShouldBeFound("friendType.specified=true");

    // Get all the friendList where friendType is null
    defaultFriendShouldNotBeFound("friendType.specified=false");
  }

  @Test
  @Transactional
  void getAllFriendsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    friend.setBaseInfo(baseInfo);
    friendRepository.saveAndFlush(friend);
    Long baseInfoId = baseInfo.getId();

    // Get all the friendList where baseInfo equals to baseInfoId
    defaultFriendShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the friendList where baseInfo equals to (baseInfoId + 1)
    defaultFriendShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllFriendsByFriendDetailsIsEqualToSomething() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);
    MasterUser friendDetails = MasterUserResourceIT.createEntity(em);
    em.persist(friendDetails);
    em.flush();
    friend.setFriendDetails(friendDetails);
    friendRepository.saveAndFlush(friend);
    Long friendDetailsId = friendDetails.getId();

    // Get all the friendList where friendDetails equals to friendDetailsId
    defaultFriendShouldBeFound("friendDetailsId.equals=" + friendDetailsId);

    // Get all the friendList where friendDetails equals to (friendDetailsId + 1)
    defaultFriendShouldNotBeFound("friendDetailsId.equals=" + (friendDetailsId + 1));
  }

  @Test
  @Transactional
  void getAllFriendsByMasterUserIsEqualToSomething() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);
    MasterUser masterUser = MasterUserResourceIT.createEntity(em);
    em.persist(masterUser);
    em.flush();
    friend.setMasterUser(masterUser);
    friendRepository.saveAndFlush(friend);
    Long masterUserId = masterUser.getId();

    // Get all the friendList where masterUser equals to masterUserId
    defaultFriendShouldBeFound("masterUserId.equals=" + masterUserId);

    // Get all the friendList where masterUser equals to (masterUserId + 1)
    defaultFriendShouldNotBeFound("masterUserId.equals=" + (masterUserId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultFriendShouldBeFound(String filter) throws Exception {
    restFriendMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(friend.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].friendType").value(hasItem(DEFAULT_FRIEND_TYPE.toString())));

    // Check, that the count call also returns 1
    restFriendMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultFriendShouldNotBeFound(String filter) throws Exception {
    restFriendMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restFriendMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingFriend() throws Exception {
    // Get the friend
    restFriendMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewFriend() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    int databaseSizeBeforeUpdate = friendRepository.findAll().size();

    // Update the friend
    Friend updatedFriend = friendRepository.findById(friend.getId()).get();
    // Disconnect from session so that the updates on updatedFriend are not directly saved in db
    em.detach(updatedFriend);
    updatedFriend.uuid(UPDATED_UUID).friendType(UPDATED_FRIEND_TYPE);
    FriendDTO friendDTO = friendMapper.toDto(updatedFriend);

    restFriendMockMvc
      .perform(
        put(ENTITY_API_URL_ID, friendDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(friendDTO))
      )
      .andExpect(status().isOk());

    // Validate the Friend in the database
    List<Friend> friendList = friendRepository.findAll();
    assertThat(friendList).hasSize(databaseSizeBeforeUpdate);
    Friend testFriend = friendList.get(friendList.size() - 1);
    assertThat(testFriend.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testFriend.getFriendType()).isEqualTo(UPDATED_FRIEND_TYPE);

    // Validate the Friend in Elasticsearch
    verify(mockFriendSearchRepository).save(testFriend);
  }

  @Test
  @Transactional
  void putNonExistingFriend() throws Exception {
    int databaseSizeBeforeUpdate = friendRepository.findAll().size();
    friend.setId(count.incrementAndGet());

    // Create the Friend
    FriendDTO friendDTO = friendMapper.toDto(friend);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restFriendMockMvc
      .perform(
        put(ENTITY_API_URL_ID, friendDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(friendDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Friend in the database
    List<Friend> friendList = friendRepository.findAll();
    assertThat(friendList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Friend in Elasticsearch
    verify(mockFriendSearchRepository, times(0)).save(friend);
  }

  @Test
  @Transactional
  void putWithIdMismatchFriend() throws Exception {
    int databaseSizeBeforeUpdate = friendRepository.findAll().size();
    friend.setId(count.incrementAndGet());

    // Create the Friend
    FriendDTO friendDTO = friendMapper.toDto(friend);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFriendMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(friendDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Friend in the database
    List<Friend> friendList = friendRepository.findAll();
    assertThat(friendList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Friend in Elasticsearch
    verify(mockFriendSearchRepository, times(0)).save(friend);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamFriend() throws Exception {
    int databaseSizeBeforeUpdate = friendRepository.findAll().size();
    friend.setId(count.incrementAndGet());

    // Create the Friend
    FriendDTO friendDTO = friendMapper.toDto(friend);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFriendMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(friendDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Friend in the database
    List<Friend> friendList = friendRepository.findAll();
    assertThat(friendList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Friend in Elasticsearch
    verify(mockFriendSearchRepository, times(0)).save(friend);
  }

  @Test
  @Transactional
  void partialUpdateFriendWithPatch() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    int databaseSizeBeforeUpdate = friendRepository.findAll().size();

    // Update the friend using partial update
    Friend partialUpdatedFriend = new Friend();
    partialUpdatedFriend.setId(friend.getId());

    partialUpdatedFriend.friendType(UPDATED_FRIEND_TYPE);

    restFriendMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedFriend.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFriend))
      )
      .andExpect(status().isOk());

    // Validate the Friend in the database
    List<Friend> friendList = friendRepository.findAll();
    assertThat(friendList).hasSize(databaseSizeBeforeUpdate);
    Friend testFriend = friendList.get(friendList.size() - 1);
    assertThat(testFriend.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testFriend.getFriendType()).isEqualTo(UPDATED_FRIEND_TYPE);
  }

  @Test
  @Transactional
  void fullUpdateFriendWithPatch() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    int databaseSizeBeforeUpdate = friendRepository.findAll().size();

    // Update the friend using partial update
    Friend partialUpdatedFriend = new Friend();
    partialUpdatedFriend.setId(friend.getId());

    partialUpdatedFriend.uuid(UPDATED_UUID).friendType(UPDATED_FRIEND_TYPE);

    restFriendMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedFriend.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFriend))
      )
      .andExpect(status().isOk());

    // Validate the Friend in the database
    List<Friend> friendList = friendRepository.findAll();
    assertThat(friendList).hasSize(databaseSizeBeforeUpdate);
    Friend testFriend = friendList.get(friendList.size() - 1);
    assertThat(testFriend.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testFriend.getFriendType()).isEqualTo(UPDATED_FRIEND_TYPE);
  }

  @Test
  @Transactional
  void patchNonExistingFriend() throws Exception {
    int databaseSizeBeforeUpdate = friendRepository.findAll().size();
    friend.setId(count.incrementAndGet());

    // Create the Friend
    FriendDTO friendDTO = friendMapper.toDto(friend);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restFriendMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, friendDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(friendDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Friend in the database
    List<Friend> friendList = friendRepository.findAll();
    assertThat(friendList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Friend in Elasticsearch
    verify(mockFriendSearchRepository, times(0)).save(friend);
  }

  @Test
  @Transactional
  void patchWithIdMismatchFriend() throws Exception {
    int databaseSizeBeforeUpdate = friendRepository.findAll().size();
    friend.setId(count.incrementAndGet());

    // Create the Friend
    FriendDTO friendDTO = friendMapper.toDto(friend);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFriendMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(friendDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Friend in the database
    List<Friend> friendList = friendRepository.findAll();
    assertThat(friendList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Friend in Elasticsearch
    verify(mockFriendSearchRepository, times(0)).save(friend);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamFriend() throws Exception {
    int databaseSizeBeforeUpdate = friendRepository.findAll().size();
    friend.setId(count.incrementAndGet());

    // Create the Friend
    FriendDTO friendDTO = friendMapper.toDto(friend);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFriendMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(friendDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Friend in the database
    List<Friend> friendList = friendRepository.findAll();
    assertThat(friendList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Friend in Elasticsearch
    verify(mockFriendSearchRepository, times(0)).save(friend);
  }

  @Test
  @Transactional
  void deleteFriend() throws Exception {
    // Initialize the database
    friendRepository.saveAndFlush(friend);

    int databaseSizeBeforeDelete = friendRepository.findAll().size();

    // Delete the friend
    restFriendMockMvc
      .perform(delete(ENTITY_API_URL_ID, friend.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<Friend> friendList = friendRepository.findAll();
    assertThat(friendList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the Friend in Elasticsearch
    verify(mockFriendSearchRepository, times(1)).deleteById(friend.getId());
  }

  @Test
  @Transactional
  void searchFriend() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    friendRepository.saveAndFlush(friend);
    when(mockFriendSearchRepository.search(queryStringQuery("id:" + friend.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(friend), PageRequest.of(0, 1), 1));

    // Search the friend
    restFriendMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + friend.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(friend.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].friendType").value(hasItem(DEFAULT_FRIEND_TYPE.toString())));
  }
}
