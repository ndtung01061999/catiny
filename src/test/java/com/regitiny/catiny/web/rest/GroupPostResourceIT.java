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
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.domain.TopicInterest;
import com.regitiny.catiny.repository.GroupPostRepository;
import com.regitiny.catiny.repository.search.GroupPostSearchRepository;
import com.regitiny.catiny.service.criteria.GroupPostCriteria;
import com.regitiny.catiny.service.dto.GroupPostDTO;
import com.regitiny.catiny.service.mapper.GroupPostMapper;
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
 * Integration tests for the {@link GroupPostResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class GroupPostResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
  private static final String UPDATED_AVATAR = "BBBBBBBBBB";

  private static final String DEFAULT_QUICK_INFO = "AAAAAAAAAA";
  private static final String UPDATED_QUICK_INFO = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/group-posts";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/group-posts";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private GroupPostRepository groupPostRepository;

  @Autowired
  private GroupPostMapper groupPostMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.GroupPostSearchRepositoryMockConfiguration
   */
  @Autowired
  private GroupPostSearchRepository mockGroupPostSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restGroupPostMockMvc;

  private GroupPost groupPost;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static GroupPost createEntity(EntityManager em) {
    GroupPost groupPost = new GroupPost().uuid(DEFAULT_UUID).name(DEFAULT_NAME).avatar(DEFAULT_AVATAR).quickInfo(DEFAULT_QUICK_INFO);
    return groupPost;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static GroupPost createUpdatedEntity(EntityManager em) {
    GroupPost groupPost = new GroupPost().uuid(UPDATED_UUID).name(UPDATED_NAME).avatar(UPDATED_AVATAR).quickInfo(UPDATED_QUICK_INFO);
    return groupPost;
  }

  @BeforeEach
  public void initTest() {
    groupPost = createEntity(em);
  }

  @Test
  @Transactional
  void createGroupPost() throws Exception {
    int databaseSizeBeforeCreate = groupPostRepository.findAll().size();
    // Create the GroupPost
    GroupPostDTO groupPostDTO = groupPostMapper.toDto(groupPost);
    restGroupPostMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupPostDTO)))
      .andExpect(status().isCreated());

    // Validate the GroupPost in the database
    List<GroupPost> groupPostList = groupPostRepository.findAll();
    assertThat(groupPostList).hasSize(databaseSizeBeforeCreate + 1);
    GroupPost testGroupPost = groupPostList.get(groupPostList.size() - 1);
    assertThat(testGroupPost.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testGroupPost.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testGroupPost.getAvatar()).isEqualTo(DEFAULT_AVATAR);
    assertThat(testGroupPost.getQuickInfo()).isEqualTo(DEFAULT_QUICK_INFO);

    // Validate the GroupPost in Elasticsearch
    verify(mockGroupPostSearchRepository, times(1)).save(testGroupPost);
  }

  @Test
  @Transactional
  void createGroupPostWithExistingId() throws Exception {
    // Create the GroupPost with an existing ID
    groupPost.setId(1L);
    GroupPostDTO groupPostDTO = groupPostMapper.toDto(groupPost);

    int databaseSizeBeforeCreate = groupPostRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restGroupPostMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupPostDTO)))
      .andExpect(status().isBadRequest());

    // Validate the GroupPost in the database
    List<GroupPost> groupPostList = groupPostRepository.findAll();
    assertThat(groupPostList).hasSize(databaseSizeBeforeCreate);

    // Validate the GroupPost in Elasticsearch
    verify(mockGroupPostSearchRepository, times(0)).save(groupPost);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = groupPostRepository.findAll().size();
    // set the field null
    groupPost.setUuid(null);

    // Create the GroupPost, which fails.
    GroupPostDTO groupPostDTO = groupPostMapper.toDto(groupPost);

    restGroupPostMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupPostDTO)))
      .andExpect(status().isBadRequest());

    List<GroupPost> groupPostList = groupPostRepository.findAll();
    assertThat(groupPostList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void checkNameIsRequired() throws Exception {
    int databaseSizeBeforeTest = groupPostRepository.findAll().size();
    // set the field null
    groupPost.setName(null);

    // Create the GroupPost, which fails.
    GroupPostDTO groupPostDTO = groupPostMapper.toDto(groupPost);

    restGroupPostMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupPostDTO)))
      .andExpect(status().isBadRequest());

    List<GroupPost> groupPostList = groupPostRepository.findAll();
    assertThat(groupPostList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllGroupPosts() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    // Get all the groupPostList
    restGroupPostMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(groupPost.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
      .andExpect(jsonPath("$.[*].quickInfo").value(hasItem(DEFAULT_QUICK_INFO.toString())));
  }

  @Test
  @Transactional
  void getGroupPost() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    // Get the groupPost
    restGroupPostMockMvc
      .perform(get(ENTITY_API_URL_ID, groupPost.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(groupPost.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
      .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR.toString()))
      .andExpect(jsonPath("$.quickInfo").value(DEFAULT_QUICK_INFO.toString()));
  }

  @Test
  @Transactional
  void getGroupPostsByIdFiltering() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    Long id = groupPost.getId();

    defaultGroupPostShouldBeFound("id.equals=" + id);
    defaultGroupPostShouldNotBeFound("id.notEquals=" + id);

    defaultGroupPostShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultGroupPostShouldNotBeFound("id.greaterThan=" + id);

    defaultGroupPostShouldBeFound("id.lessThanOrEqual=" + id);
    defaultGroupPostShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllGroupPostsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    // Get all the groupPostList where uuid equals to DEFAULT_UUID
    defaultGroupPostShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the groupPostList where uuid equals to UPDATED_UUID
    defaultGroupPostShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllGroupPostsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    // Get all the groupPostList where uuid not equals to DEFAULT_UUID
    defaultGroupPostShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the groupPostList where uuid not equals to UPDATED_UUID
    defaultGroupPostShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllGroupPostsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    // Get all the groupPostList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultGroupPostShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the groupPostList where uuid equals to UPDATED_UUID
    defaultGroupPostShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllGroupPostsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    // Get all the groupPostList where uuid is not null
    defaultGroupPostShouldBeFound("uuid.specified=true");

    // Get all the groupPostList where uuid is null
    defaultGroupPostShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllGroupPostsByNameIsEqualToSomething() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    // Get all the groupPostList where name equals to DEFAULT_NAME
    defaultGroupPostShouldBeFound("name.equals=" + DEFAULT_NAME);

    // Get all the groupPostList where name equals to UPDATED_NAME
    defaultGroupPostShouldNotBeFound("name.equals=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllGroupPostsByNameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    // Get all the groupPostList where name not equals to DEFAULT_NAME
    defaultGroupPostShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

    // Get all the groupPostList where name not equals to UPDATED_NAME
    defaultGroupPostShouldBeFound("name.notEquals=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllGroupPostsByNameIsInShouldWork() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    // Get all the groupPostList where name in DEFAULT_NAME or UPDATED_NAME
    defaultGroupPostShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

    // Get all the groupPostList where name equals to UPDATED_NAME
    defaultGroupPostShouldNotBeFound("name.in=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllGroupPostsByNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    // Get all the groupPostList where name is not null
    defaultGroupPostShouldBeFound("name.specified=true");

    // Get all the groupPostList where name is null
    defaultGroupPostShouldNotBeFound("name.specified=false");
  }

  @Test
  @Transactional
  void getAllGroupPostsByNameContainsSomething() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    // Get all the groupPostList where name contains DEFAULT_NAME
    defaultGroupPostShouldBeFound("name.contains=" + DEFAULT_NAME);

    // Get all the groupPostList where name contains UPDATED_NAME
    defaultGroupPostShouldNotBeFound("name.contains=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllGroupPostsByNameNotContainsSomething() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    // Get all the groupPostList where name does not contain DEFAULT_NAME
    defaultGroupPostShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

    // Get all the groupPostList where name does not contain UPDATED_NAME
    defaultGroupPostShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllGroupPostsByProfileIsEqualToSomething() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);
    GroupProfile profile = GroupProfileResourceIT.createEntity(em);
    em.persist(profile);
    em.flush();
    groupPost.setProfile(profile);
    groupPostRepository.saveAndFlush(groupPost);
    Long profileId = profile.getId();

    // Get all the groupPostList where profile equals to profileId
    defaultGroupPostShouldBeFound("profileId.equals=" + profileId);

    // Get all the groupPostList where profile equals to (profileId + 1)
    defaultGroupPostShouldNotBeFound("profileId.equals=" + (profileId + 1));
  }

  @Test
  @Transactional
  void getAllGroupPostsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    groupPost.setBaseInfo(baseInfo);
    groupPostRepository.saveAndFlush(groupPost);
    Long baseInfoId = baseInfo.getId();

    // Get all the groupPostList where baseInfo equals to baseInfoId
    defaultGroupPostShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the groupPostList where baseInfo equals to (baseInfoId + 1)
    defaultGroupPostShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllGroupPostsByMyPostInGroupIsEqualToSomething() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);
    Post myPostInGroup = PostResourceIT.createEntity(em);
    em.persist(myPostInGroup);
    em.flush();
    groupPost.addMyPostInGroup(myPostInGroup);
    groupPostRepository.saveAndFlush(groupPost);
    Long myPostInGroupId = myPostInGroup.getId();

    // Get all the groupPostList where myPostInGroup equals to myPostInGroupId
    defaultGroupPostShouldBeFound("myPostInGroupId.equals=" + myPostInGroupId);

    // Get all the groupPostList where myPostInGroup equals to (myPostInGroupId + 1)
    defaultGroupPostShouldNotBeFound("myPostInGroupId.equals=" + (myPostInGroupId + 1));
  }

  @Test
  @Transactional
  void getAllGroupPostsByTopicInterestIsEqualToSomething() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);
    TopicInterest topicInterest = TopicInterestResourceIT.createEntity(em);
    em.persist(topicInterest);
    em.flush();
    groupPost.addTopicInterest(topicInterest);
    groupPostRepository.saveAndFlush(groupPost);
    Long topicInterestId = topicInterest.getId();

    // Get all the groupPostList where topicInterest equals to topicInterestId
    defaultGroupPostShouldBeFound("topicInterestId.equals=" + topicInterestId);

    // Get all the groupPostList where topicInterest equals to (topicInterestId + 1)
    defaultGroupPostShouldNotBeFound("topicInterestId.equals=" + (topicInterestId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultGroupPostShouldBeFound(String filter) throws Exception {
    restGroupPostMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(groupPost.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
      .andExpect(jsonPath("$.[*].quickInfo").value(hasItem(DEFAULT_QUICK_INFO.toString())));

    // Check, that the count call also returns 1
    restGroupPostMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultGroupPostShouldNotBeFound(String filter) throws Exception {
    restGroupPostMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restGroupPostMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingGroupPost() throws Exception {
    // Get the groupPost
    restGroupPostMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewGroupPost() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    int databaseSizeBeforeUpdate = groupPostRepository.findAll().size();

    // Update the groupPost
    GroupPost updatedGroupPost = groupPostRepository.findById(groupPost.getId()).get();
    // Disconnect from session so that the updates on updatedGroupPost are not directly saved in db
    em.detach(updatedGroupPost);
    updatedGroupPost.uuid(UPDATED_UUID).name(UPDATED_NAME).avatar(UPDATED_AVATAR).quickInfo(UPDATED_QUICK_INFO);
    GroupPostDTO groupPostDTO = groupPostMapper.toDto(updatedGroupPost);

    restGroupPostMockMvc
      .perform(
        put(ENTITY_API_URL_ID, groupPostDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(groupPostDTO))
      )
      .andExpect(status().isOk());

    // Validate the GroupPost in the database
    List<GroupPost> groupPostList = groupPostRepository.findAll();
    assertThat(groupPostList).hasSize(databaseSizeBeforeUpdate);
    GroupPost testGroupPost = groupPostList.get(groupPostList.size() - 1);
    assertThat(testGroupPost.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testGroupPost.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testGroupPost.getAvatar()).isEqualTo(UPDATED_AVATAR);
    assertThat(testGroupPost.getQuickInfo()).isEqualTo(UPDATED_QUICK_INFO);

    // Validate the GroupPost in Elasticsearch
    verify(mockGroupPostSearchRepository).save(testGroupPost);
  }

  @Test
  @Transactional
  void putNonExistingGroupPost() throws Exception {
    int databaseSizeBeforeUpdate = groupPostRepository.findAll().size();
    groupPost.setId(count.incrementAndGet());

    // Create the GroupPost
    GroupPostDTO groupPostDTO = groupPostMapper.toDto(groupPost);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restGroupPostMockMvc
      .perform(
        put(ENTITY_API_URL_ID, groupPostDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(groupPostDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the GroupPost in the database
    List<GroupPost> groupPostList = groupPostRepository.findAll();
    assertThat(groupPostList).hasSize(databaseSizeBeforeUpdate);

    // Validate the GroupPost in Elasticsearch
    verify(mockGroupPostSearchRepository, times(0)).save(groupPost);
  }

  @Test
  @Transactional
  void putWithIdMismatchGroupPost() throws Exception {
    int databaseSizeBeforeUpdate = groupPostRepository.findAll().size();
    groupPost.setId(count.incrementAndGet());

    // Create the GroupPost
    GroupPostDTO groupPostDTO = groupPostMapper.toDto(groupPost);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restGroupPostMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(groupPostDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the GroupPost in the database
    List<GroupPost> groupPostList = groupPostRepository.findAll();
    assertThat(groupPostList).hasSize(databaseSizeBeforeUpdate);

    // Validate the GroupPost in Elasticsearch
    verify(mockGroupPostSearchRepository, times(0)).save(groupPost);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamGroupPost() throws Exception {
    int databaseSizeBeforeUpdate = groupPostRepository.findAll().size();
    groupPost.setId(count.incrementAndGet());

    // Create the GroupPost
    GroupPostDTO groupPostDTO = groupPostMapper.toDto(groupPost);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restGroupPostMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupPostDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the GroupPost in the database
    List<GroupPost> groupPostList = groupPostRepository.findAll();
    assertThat(groupPostList).hasSize(databaseSizeBeforeUpdate);

    // Validate the GroupPost in Elasticsearch
    verify(mockGroupPostSearchRepository, times(0)).save(groupPost);
  }

  @Test
  @Transactional
  void partialUpdateGroupPostWithPatch() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    int databaseSizeBeforeUpdate = groupPostRepository.findAll().size();

    // Update the groupPost using partial update
    GroupPost partialUpdatedGroupPost = new GroupPost();
    partialUpdatedGroupPost.setId(groupPost.getId());

    partialUpdatedGroupPost.uuid(UPDATED_UUID).name(UPDATED_NAME).avatar(UPDATED_AVATAR);

    restGroupPostMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedGroupPost.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGroupPost))
      )
      .andExpect(status().isOk());

    // Validate the GroupPost in the database
    List<GroupPost> groupPostList = groupPostRepository.findAll();
    assertThat(groupPostList).hasSize(databaseSizeBeforeUpdate);
    GroupPost testGroupPost = groupPostList.get(groupPostList.size() - 1);
    assertThat(testGroupPost.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testGroupPost.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testGroupPost.getAvatar()).isEqualTo(UPDATED_AVATAR);
    assertThat(testGroupPost.getQuickInfo()).isEqualTo(DEFAULT_QUICK_INFO);
  }

  @Test
  @Transactional
  void fullUpdateGroupPostWithPatch() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    int databaseSizeBeforeUpdate = groupPostRepository.findAll().size();

    // Update the groupPost using partial update
    GroupPost partialUpdatedGroupPost = new GroupPost();
    partialUpdatedGroupPost.setId(groupPost.getId());

    partialUpdatedGroupPost.uuid(UPDATED_UUID).name(UPDATED_NAME).avatar(UPDATED_AVATAR).quickInfo(UPDATED_QUICK_INFO);

    restGroupPostMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedGroupPost.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGroupPost))
      )
      .andExpect(status().isOk());

    // Validate the GroupPost in the database
    List<GroupPost> groupPostList = groupPostRepository.findAll();
    assertThat(groupPostList).hasSize(databaseSizeBeforeUpdate);
    GroupPost testGroupPost = groupPostList.get(groupPostList.size() - 1);
    assertThat(testGroupPost.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testGroupPost.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testGroupPost.getAvatar()).isEqualTo(UPDATED_AVATAR);
    assertThat(testGroupPost.getQuickInfo()).isEqualTo(UPDATED_QUICK_INFO);
  }

  @Test
  @Transactional
  void patchNonExistingGroupPost() throws Exception {
    int databaseSizeBeforeUpdate = groupPostRepository.findAll().size();
    groupPost.setId(count.incrementAndGet());

    // Create the GroupPost
    GroupPostDTO groupPostDTO = groupPostMapper.toDto(groupPost);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restGroupPostMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, groupPostDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(groupPostDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the GroupPost in the database
    List<GroupPost> groupPostList = groupPostRepository.findAll();
    assertThat(groupPostList).hasSize(databaseSizeBeforeUpdate);

    // Validate the GroupPost in Elasticsearch
    verify(mockGroupPostSearchRepository, times(0)).save(groupPost);
  }

  @Test
  @Transactional
  void patchWithIdMismatchGroupPost() throws Exception {
    int databaseSizeBeforeUpdate = groupPostRepository.findAll().size();
    groupPost.setId(count.incrementAndGet());

    // Create the GroupPost
    GroupPostDTO groupPostDTO = groupPostMapper.toDto(groupPost);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restGroupPostMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(groupPostDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the GroupPost in the database
    List<GroupPost> groupPostList = groupPostRepository.findAll();
    assertThat(groupPostList).hasSize(databaseSizeBeforeUpdate);

    // Validate the GroupPost in Elasticsearch
    verify(mockGroupPostSearchRepository, times(0)).save(groupPost);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamGroupPost() throws Exception {
    int databaseSizeBeforeUpdate = groupPostRepository.findAll().size();
    groupPost.setId(count.incrementAndGet());

    // Create the GroupPost
    GroupPostDTO groupPostDTO = groupPostMapper.toDto(groupPost);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restGroupPostMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(groupPostDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the GroupPost in the database
    List<GroupPost> groupPostList = groupPostRepository.findAll();
    assertThat(groupPostList).hasSize(databaseSizeBeforeUpdate);

    // Validate the GroupPost in Elasticsearch
    verify(mockGroupPostSearchRepository, times(0)).save(groupPost);
  }

  @Test
  @Transactional
  void deleteGroupPost() throws Exception {
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);

    int databaseSizeBeforeDelete = groupPostRepository.findAll().size();

    // Delete the groupPost
    restGroupPostMockMvc
      .perform(delete(ENTITY_API_URL_ID, groupPost.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<GroupPost> groupPostList = groupPostRepository.findAll();
    assertThat(groupPostList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the GroupPost in Elasticsearch
    verify(mockGroupPostSearchRepository, times(1)).deleteById(groupPost.getId());
  }

  @Test
  @Transactional
  void searchGroupPost() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    groupPostRepository.saveAndFlush(groupPost);
    when(mockGroupPostSearchRepository.search(queryStringQuery("id:" + groupPost.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(groupPost), PageRequest.of(0, 1), 1));

    // Search the groupPost
    restGroupPostMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + groupPost.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(groupPost.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
      .andExpect(jsonPath("$.[*].quickInfo").value(hasItem(DEFAULT_QUICK_INFO.toString())));
  }
}
