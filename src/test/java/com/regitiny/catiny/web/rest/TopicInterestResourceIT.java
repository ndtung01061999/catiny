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
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.PagePost;
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.domain.TopicInterest;
import com.regitiny.catiny.repository.TopicInterestRepository;
import com.regitiny.catiny.repository.search.TopicInterestSearchRepository;
import com.regitiny.catiny.service.TopicInterestService;
import com.regitiny.catiny.service.criteria.TopicInterestCriteria;
import com.regitiny.catiny.service.dto.TopicInterestDTO;
import com.regitiny.catiny.service.mapper.TopicInterestMapper;
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
 * Integration tests for the {@link TopicInterestResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class TopicInterestResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_TITLE = "AAAAAAAAAA";
  private static final String UPDATED_TITLE = "BBBBBBBBBB";

  private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
  private static final String UPDATED_CONTENT = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/topic-interests";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/topic-interests";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private TopicInterestRepository topicInterestRepository;

  @Mock
  private TopicInterestRepository topicInterestRepositoryMock;

  @Autowired
  private TopicInterestMapper topicInterestMapper;

  @Mock
  private TopicInterestService topicInterestServiceMock;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.TopicInterestSearchRepositoryMockConfiguration
   */
  @Autowired
  private TopicInterestSearchRepository mockTopicInterestSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restTopicInterestMockMvc;

  private TopicInterest topicInterest;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static TopicInterest createEntity(EntityManager em) {
    TopicInterest topicInterest = new TopicInterest().uuid(DEFAULT_UUID).title(DEFAULT_TITLE).content(DEFAULT_CONTENT);
    return topicInterest;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static TopicInterest createUpdatedEntity(EntityManager em) {
    TopicInterest topicInterest = new TopicInterest().uuid(UPDATED_UUID).title(UPDATED_TITLE).content(UPDATED_CONTENT);
    return topicInterest;
  }

  @BeforeEach
  public void initTest() {
    topicInterest = createEntity(em);
  }

  @Test
  @Transactional
  void createTopicInterest() throws Exception {
    int databaseSizeBeforeCreate = topicInterestRepository.findAll().size();
    // Create the TopicInterest
    TopicInterestDTO topicInterestDTO = topicInterestMapper.toDto(topicInterest);
    restTopicInterestMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(topicInterestDTO)))
      .andExpect(status().isCreated());

    // Validate the TopicInterest in the database
    List<TopicInterest> topicInterestList = topicInterestRepository.findAll();
    assertThat(topicInterestList).hasSize(databaseSizeBeforeCreate + 1);
    TopicInterest testTopicInterest = topicInterestList.get(topicInterestList.size() - 1);
    assertThat(testTopicInterest.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testTopicInterest.getTitle()).isEqualTo(DEFAULT_TITLE);
    assertThat(testTopicInterest.getContent()).isEqualTo(DEFAULT_CONTENT);

    // Validate the TopicInterest in Elasticsearch
    verify(mockTopicInterestSearchRepository, times(1)).save(testTopicInterest);
  }

  @Test
  @Transactional
  void createTopicInterestWithExistingId() throws Exception {
    // Create the TopicInterest with an existing ID
    topicInterest.setId(1L);
    TopicInterestDTO topicInterestDTO = topicInterestMapper.toDto(topicInterest);

    int databaseSizeBeforeCreate = topicInterestRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restTopicInterestMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(topicInterestDTO)))
      .andExpect(status().isBadRequest());

    // Validate the TopicInterest in the database
    List<TopicInterest> topicInterestList = topicInterestRepository.findAll();
    assertThat(topicInterestList).hasSize(databaseSizeBeforeCreate);

    // Validate the TopicInterest in Elasticsearch
    verify(mockTopicInterestSearchRepository, times(0)).save(topicInterest);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = topicInterestRepository.findAll().size();
    // set the field null
    topicInterest.setUuid(null);

    // Create the TopicInterest, which fails.
    TopicInterestDTO topicInterestDTO = topicInterestMapper.toDto(topicInterest);

    restTopicInterestMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(topicInterestDTO)))
      .andExpect(status().isBadRequest());

    List<TopicInterest> topicInterestList = topicInterestRepository.findAll();
    assertThat(topicInterestList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllTopicInterests() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    // Get all the topicInterestList
    restTopicInterestMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(topicInterest.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));
  }

  @SuppressWarnings({ "unchecked" })
  void getAllTopicInterestsWithEagerRelationshipsIsEnabled() throws Exception {
    when(topicInterestServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

    restTopicInterestMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

    verify(topicInterestServiceMock, times(1)).findAllWithEagerRelationships(any());
  }

  @SuppressWarnings({ "unchecked" })
  void getAllTopicInterestsWithEagerRelationshipsIsNotEnabled() throws Exception {
    when(topicInterestServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

    restTopicInterestMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

    verify(topicInterestServiceMock, times(1)).findAllWithEagerRelationships(any());
  }

  @Test
  @Transactional
  void getTopicInterest() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    // Get the topicInterest
    restTopicInterestMockMvc
      .perform(get(ENTITY_API_URL_ID, topicInterest.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(topicInterest.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
      .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()));
  }

  @Test
  @Transactional
  void getTopicInterestsByIdFiltering() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    Long id = topicInterest.getId();

    defaultTopicInterestShouldBeFound("id.equals=" + id);
    defaultTopicInterestShouldNotBeFound("id.notEquals=" + id);

    defaultTopicInterestShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultTopicInterestShouldNotBeFound("id.greaterThan=" + id);

    defaultTopicInterestShouldBeFound("id.lessThanOrEqual=" + id);
    defaultTopicInterestShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllTopicInterestsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    // Get all the topicInterestList where uuid equals to DEFAULT_UUID
    defaultTopicInterestShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the topicInterestList where uuid equals to UPDATED_UUID
    defaultTopicInterestShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllTopicInterestsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    // Get all the topicInterestList where uuid not equals to DEFAULT_UUID
    defaultTopicInterestShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the topicInterestList where uuid not equals to UPDATED_UUID
    defaultTopicInterestShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllTopicInterestsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    // Get all the topicInterestList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultTopicInterestShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the topicInterestList where uuid equals to UPDATED_UUID
    defaultTopicInterestShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllTopicInterestsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    // Get all the topicInterestList where uuid is not null
    defaultTopicInterestShouldBeFound("uuid.specified=true");

    // Get all the topicInterestList where uuid is null
    defaultTopicInterestShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllTopicInterestsByTitleIsEqualToSomething() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    // Get all the topicInterestList where title equals to DEFAULT_TITLE
    defaultTopicInterestShouldBeFound("title.equals=" + DEFAULT_TITLE);

    // Get all the topicInterestList where title equals to UPDATED_TITLE
    defaultTopicInterestShouldNotBeFound("title.equals=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllTopicInterestsByTitleIsNotEqualToSomething() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    // Get all the topicInterestList where title not equals to DEFAULT_TITLE
    defaultTopicInterestShouldNotBeFound("title.notEquals=" + DEFAULT_TITLE);

    // Get all the topicInterestList where title not equals to UPDATED_TITLE
    defaultTopicInterestShouldBeFound("title.notEquals=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllTopicInterestsByTitleIsInShouldWork() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    // Get all the topicInterestList where title in DEFAULT_TITLE or UPDATED_TITLE
    defaultTopicInterestShouldBeFound("title.in=" + DEFAULT_TITLE + "," + UPDATED_TITLE);

    // Get all the topicInterestList where title equals to UPDATED_TITLE
    defaultTopicInterestShouldNotBeFound("title.in=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllTopicInterestsByTitleIsNullOrNotNull() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    // Get all the topicInterestList where title is not null
    defaultTopicInterestShouldBeFound("title.specified=true");

    // Get all the topicInterestList where title is null
    defaultTopicInterestShouldNotBeFound("title.specified=false");
  }

  @Test
  @Transactional
  void getAllTopicInterestsByTitleContainsSomething() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    // Get all the topicInterestList where title contains DEFAULT_TITLE
    defaultTopicInterestShouldBeFound("title.contains=" + DEFAULT_TITLE);

    // Get all the topicInterestList where title contains UPDATED_TITLE
    defaultTopicInterestShouldNotBeFound("title.contains=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllTopicInterestsByTitleNotContainsSomething() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    // Get all the topicInterestList where title does not contain DEFAULT_TITLE
    defaultTopicInterestShouldNotBeFound("title.doesNotContain=" + DEFAULT_TITLE);

    // Get all the topicInterestList where title does not contain UPDATED_TITLE
    defaultTopicInterestShouldBeFound("title.doesNotContain=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllTopicInterestsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    topicInterest.setBaseInfo(baseInfo);
    topicInterestRepository.saveAndFlush(topicInterest);
    Long baseInfoId = baseInfo.getId();

    // Get all the topicInterestList where baseInfo equals to baseInfoId
    defaultTopicInterestShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the topicInterestList where baseInfo equals to (baseInfoId + 1)
    defaultTopicInterestShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllTopicInterestsByPostIsEqualToSomething() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);
    Post post = PostResourceIT.createEntity(em);
    em.persist(post);
    em.flush();
    topicInterest.addPost(post);
    topicInterestRepository.saveAndFlush(topicInterest);
    Long postId = post.getId();

    // Get all the topicInterestList where post equals to postId
    defaultTopicInterestShouldBeFound("postId.equals=" + postId);

    // Get all the topicInterestList where post equals to (postId + 1)
    defaultTopicInterestShouldNotBeFound("postId.equals=" + (postId + 1));
  }

  @Test
  @Transactional
  void getAllTopicInterestsByPagePostIsEqualToSomething() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);
    PagePost pagePost = PagePostResourceIT.createEntity(em);
    em.persist(pagePost);
    em.flush();
    topicInterest.addPagePost(pagePost);
    topicInterestRepository.saveAndFlush(topicInterest);
    Long pagePostId = pagePost.getId();

    // Get all the topicInterestList where pagePost equals to pagePostId
    defaultTopicInterestShouldBeFound("pagePostId.equals=" + pagePostId);

    // Get all the topicInterestList where pagePost equals to (pagePostId + 1)
    defaultTopicInterestShouldNotBeFound("pagePostId.equals=" + (pagePostId + 1));
  }

  @Test
  @Transactional
  void getAllTopicInterestsByGroupPostIsEqualToSomething() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);
    GroupPost groupPost = GroupPostResourceIT.createEntity(em);
    em.persist(groupPost);
    em.flush();
    topicInterest.addGroupPost(groupPost);
    topicInterestRepository.saveAndFlush(topicInterest);
    Long groupPostId = groupPost.getId();

    // Get all the topicInterestList where groupPost equals to groupPostId
    defaultTopicInterestShouldBeFound("groupPostId.equals=" + groupPostId);

    // Get all the topicInterestList where groupPost equals to (groupPostId + 1)
    defaultTopicInterestShouldNotBeFound("groupPostId.equals=" + (groupPostId + 1));
  }

  @Test
  @Transactional
  void getAllTopicInterestsByMasterUserIsEqualToSomething() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);
    MasterUser masterUser = MasterUserResourceIT.createEntity(em);
    em.persist(masterUser);
    em.flush();
    topicInterest.addMasterUser(masterUser);
    topicInterestRepository.saveAndFlush(topicInterest);
    Long masterUserId = masterUser.getId();

    // Get all the topicInterestList where masterUser equals to masterUserId
    defaultTopicInterestShouldBeFound("masterUserId.equals=" + masterUserId);

    // Get all the topicInterestList where masterUser equals to (masterUserId + 1)
    defaultTopicInterestShouldNotBeFound("masterUserId.equals=" + (masterUserId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultTopicInterestShouldBeFound(String filter) throws Exception {
    restTopicInterestMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(topicInterest.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));

    // Check, that the count call also returns 1
    restTopicInterestMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultTopicInterestShouldNotBeFound(String filter) throws Exception {
    restTopicInterestMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restTopicInterestMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingTopicInterest() throws Exception {
    // Get the topicInterest
    restTopicInterestMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewTopicInterest() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    int databaseSizeBeforeUpdate = topicInterestRepository.findAll().size();

    // Update the topicInterest
    TopicInterest updatedTopicInterest = topicInterestRepository.findById(topicInterest.getId()).get();
    // Disconnect from session so that the updates on updatedTopicInterest are not directly saved in db
    em.detach(updatedTopicInterest);
    updatedTopicInterest.uuid(UPDATED_UUID).title(UPDATED_TITLE).content(UPDATED_CONTENT);
    TopicInterestDTO topicInterestDTO = topicInterestMapper.toDto(updatedTopicInterest);

    restTopicInterestMockMvc
      .perform(
        put(ENTITY_API_URL_ID, topicInterestDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(topicInterestDTO))
      )
      .andExpect(status().isOk());

    // Validate the TopicInterest in the database
    List<TopicInterest> topicInterestList = topicInterestRepository.findAll();
    assertThat(topicInterestList).hasSize(databaseSizeBeforeUpdate);
    TopicInterest testTopicInterest = topicInterestList.get(topicInterestList.size() - 1);
    assertThat(testTopicInterest.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testTopicInterest.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testTopicInterest.getContent()).isEqualTo(UPDATED_CONTENT);

    // Validate the TopicInterest in Elasticsearch
    verify(mockTopicInterestSearchRepository).save(testTopicInterest);
  }

  @Test
  @Transactional
  void putNonExistingTopicInterest() throws Exception {
    int databaseSizeBeforeUpdate = topicInterestRepository.findAll().size();
    topicInterest.setId(count.incrementAndGet());

    // Create the TopicInterest
    TopicInterestDTO topicInterestDTO = topicInterestMapper.toDto(topicInterest);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restTopicInterestMockMvc
      .perform(
        put(ENTITY_API_URL_ID, topicInterestDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(topicInterestDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TopicInterest in the database
    List<TopicInterest> topicInterestList = topicInterestRepository.findAll();
    assertThat(topicInterestList).hasSize(databaseSizeBeforeUpdate);

    // Validate the TopicInterest in Elasticsearch
    verify(mockTopicInterestSearchRepository, times(0)).save(topicInterest);
  }

  @Test
  @Transactional
  void putWithIdMismatchTopicInterest() throws Exception {
    int databaseSizeBeforeUpdate = topicInterestRepository.findAll().size();
    topicInterest.setId(count.incrementAndGet());

    // Create the TopicInterest
    TopicInterestDTO topicInterestDTO = topicInterestMapper.toDto(topicInterest);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTopicInterestMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(topicInterestDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TopicInterest in the database
    List<TopicInterest> topicInterestList = topicInterestRepository.findAll();
    assertThat(topicInterestList).hasSize(databaseSizeBeforeUpdate);

    // Validate the TopicInterest in Elasticsearch
    verify(mockTopicInterestSearchRepository, times(0)).save(topicInterest);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamTopicInterest() throws Exception {
    int databaseSizeBeforeUpdate = topicInterestRepository.findAll().size();
    topicInterest.setId(count.incrementAndGet());

    // Create the TopicInterest
    TopicInterestDTO topicInterestDTO = topicInterestMapper.toDto(topicInterest);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTopicInterestMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(topicInterestDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the TopicInterest in the database
    List<TopicInterest> topicInterestList = topicInterestRepository.findAll();
    assertThat(topicInterestList).hasSize(databaseSizeBeforeUpdate);

    // Validate the TopicInterest in Elasticsearch
    verify(mockTopicInterestSearchRepository, times(0)).save(topicInterest);
  }

  @Test
  @Transactional
  void partialUpdateTopicInterestWithPatch() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    int databaseSizeBeforeUpdate = topicInterestRepository.findAll().size();

    // Update the topicInterest using partial update
    TopicInterest partialUpdatedTopicInterest = new TopicInterest();
    partialUpdatedTopicInterest.setId(topicInterest.getId());

    partialUpdatedTopicInterest.content(UPDATED_CONTENT);

    restTopicInterestMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedTopicInterest.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTopicInterest))
      )
      .andExpect(status().isOk());

    // Validate the TopicInterest in the database
    List<TopicInterest> topicInterestList = topicInterestRepository.findAll();
    assertThat(topicInterestList).hasSize(databaseSizeBeforeUpdate);
    TopicInterest testTopicInterest = topicInterestList.get(topicInterestList.size() - 1);
    assertThat(testTopicInterest.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testTopicInterest.getTitle()).isEqualTo(DEFAULT_TITLE);
    assertThat(testTopicInterest.getContent()).isEqualTo(UPDATED_CONTENT);
  }

  @Test
  @Transactional
  void fullUpdateTopicInterestWithPatch() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    int databaseSizeBeforeUpdate = topicInterestRepository.findAll().size();

    // Update the topicInterest using partial update
    TopicInterest partialUpdatedTopicInterest = new TopicInterest();
    partialUpdatedTopicInterest.setId(topicInterest.getId());

    partialUpdatedTopicInterest.uuid(UPDATED_UUID).title(UPDATED_TITLE).content(UPDATED_CONTENT);

    restTopicInterestMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedTopicInterest.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTopicInterest))
      )
      .andExpect(status().isOk());

    // Validate the TopicInterest in the database
    List<TopicInterest> topicInterestList = topicInterestRepository.findAll();
    assertThat(topicInterestList).hasSize(databaseSizeBeforeUpdate);
    TopicInterest testTopicInterest = topicInterestList.get(topicInterestList.size() - 1);
    assertThat(testTopicInterest.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testTopicInterest.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testTopicInterest.getContent()).isEqualTo(UPDATED_CONTENT);
  }

  @Test
  @Transactional
  void patchNonExistingTopicInterest() throws Exception {
    int databaseSizeBeforeUpdate = topicInterestRepository.findAll().size();
    topicInterest.setId(count.incrementAndGet());

    // Create the TopicInterest
    TopicInterestDTO topicInterestDTO = topicInterestMapper.toDto(topicInterest);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restTopicInterestMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, topicInterestDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(topicInterestDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TopicInterest in the database
    List<TopicInterest> topicInterestList = topicInterestRepository.findAll();
    assertThat(topicInterestList).hasSize(databaseSizeBeforeUpdate);

    // Validate the TopicInterest in Elasticsearch
    verify(mockTopicInterestSearchRepository, times(0)).save(topicInterest);
  }

  @Test
  @Transactional
  void patchWithIdMismatchTopicInterest() throws Exception {
    int databaseSizeBeforeUpdate = topicInterestRepository.findAll().size();
    topicInterest.setId(count.incrementAndGet());

    // Create the TopicInterest
    TopicInterestDTO topicInterestDTO = topicInterestMapper.toDto(topicInterest);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTopicInterestMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(topicInterestDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TopicInterest in the database
    List<TopicInterest> topicInterestList = topicInterestRepository.findAll();
    assertThat(topicInterestList).hasSize(databaseSizeBeforeUpdate);

    // Validate the TopicInterest in Elasticsearch
    verify(mockTopicInterestSearchRepository, times(0)).save(topicInterest);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamTopicInterest() throws Exception {
    int databaseSizeBeforeUpdate = topicInterestRepository.findAll().size();
    topicInterest.setId(count.incrementAndGet());

    // Create the TopicInterest
    TopicInterestDTO topicInterestDTO = topicInterestMapper.toDto(topicInterest);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTopicInterestMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(topicInterestDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the TopicInterest in the database
    List<TopicInterest> topicInterestList = topicInterestRepository.findAll();
    assertThat(topicInterestList).hasSize(databaseSizeBeforeUpdate);

    // Validate the TopicInterest in Elasticsearch
    verify(mockTopicInterestSearchRepository, times(0)).save(topicInterest);
  }

  @Test
  @Transactional
  void deleteTopicInterest() throws Exception {
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);

    int databaseSizeBeforeDelete = topicInterestRepository.findAll().size();

    // Delete the topicInterest
    restTopicInterestMockMvc
      .perform(delete(ENTITY_API_URL_ID, topicInterest.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<TopicInterest> topicInterestList = topicInterestRepository.findAll();
    assertThat(topicInterestList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the TopicInterest in Elasticsearch
    verify(mockTopicInterestSearchRepository, times(1)).deleteById(topicInterest.getId());
  }

  @Test
  @Transactional
  void searchTopicInterest() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    topicInterestRepository.saveAndFlush(topicInterest);
    when(mockTopicInterestSearchRepository.search(queryStringQuery("id:" + topicInterest.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(topicInterest), PageRequest.of(0, 1), 1));

    // Search the topicInterest
    restTopicInterestMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + topicInterest.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(topicInterest.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));
  }
}
