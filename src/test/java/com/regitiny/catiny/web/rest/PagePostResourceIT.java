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
import com.regitiny.catiny.domain.PagePost;
import com.regitiny.catiny.domain.PageProfile;
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.domain.TopicInterest;
import com.regitiny.catiny.repository.PagePostRepository;
import com.regitiny.catiny.repository.search.PagePostSearchRepository;
import com.regitiny.catiny.service.criteria.PagePostCriteria;
import com.regitiny.catiny.service.dto.PagePostDTO;
import com.regitiny.catiny.service.mapper.PagePostMapper;
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
 * Integration tests for the {@link PagePostResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class PagePostResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_QUICK_INFO = "AAAAAAAAAA";
  private static final String UPDATED_QUICK_INFO = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/page-posts";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/page-posts";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private PagePostRepository pagePostRepository;

  @Autowired
  private PagePostMapper pagePostMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.PagePostSearchRepositoryMockConfiguration
   */
  @Autowired
  private PagePostSearchRepository mockPagePostSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restPagePostMockMvc;

  private PagePost pagePost;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static PagePost createEntity(EntityManager em) {
    PagePost pagePost = new PagePost().uuid(DEFAULT_UUID).name(DEFAULT_NAME).quickInfo(DEFAULT_QUICK_INFO);
    return pagePost;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static PagePost createUpdatedEntity(EntityManager em) {
    PagePost pagePost = new PagePost().uuid(UPDATED_UUID).name(UPDATED_NAME).quickInfo(UPDATED_QUICK_INFO);
    return pagePost;
  }

  @BeforeEach
  public void initTest() {
    pagePost = createEntity(em);
  }

  @Test
  @Transactional
  void createPagePost() throws Exception {
    int databaseSizeBeforeCreate = pagePostRepository.findAll().size();
    // Create the PagePost
    PagePostDTO pagePostDTO = pagePostMapper.toDto(pagePost);
    restPagePostMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pagePostDTO)))
      .andExpect(status().isCreated());

    // Validate the PagePost in the database
    List<PagePost> pagePostList = pagePostRepository.findAll();
    assertThat(pagePostList).hasSize(databaseSizeBeforeCreate + 1);
    PagePost testPagePost = pagePostList.get(pagePostList.size() - 1);
    assertThat(testPagePost.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testPagePost.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testPagePost.getQuickInfo()).isEqualTo(DEFAULT_QUICK_INFO);

    // Validate the PagePost in Elasticsearch
    verify(mockPagePostSearchRepository, times(1)).save(testPagePost);
  }

  @Test
  @Transactional
  void createPagePostWithExistingId() throws Exception {
    // Create the PagePost with an existing ID
    pagePost.setId(1L);
    PagePostDTO pagePostDTO = pagePostMapper.toDto(pagePost);

    int databaseSizeBeforeCreate = pagePostRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restPagePostMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pagePostDTO)))
      .andExpect(status().isBadRequest());

    // Validate the PagePost in the database
    List<PagePost> pagePostList = pagePostRepository.findAll();
    assertThat(pagePostList).hasSize(databaseSizeBeforeCreate);

    // Validate the PagePost in Elasticsearch
    verify(mockPagePostSearchRepository, times(0)).save(pagePost);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = pagePostRepository.findAll().size();
    // set the field null
    pagePost.setUuid(null);

    // Create the PagePost, which fails.
    PagePostDTO pagePostDTO = pagePostMapper.toDto(pagePost);

    restPagePostMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pagePostDTO)))
      .andExpect(status().isBadRequest());

    List<PagePost> pagePostList = pagePostRepository.findAll();
    assertThat(pagePostList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void checkNameIsRequired() throws Exception {
    int databaseSizeBeforeTest = pagePostRepository.findAll().size();
    // set the field null
    pagePost.setName(null);

    // Create the PagePost, which fails.
    PagePostDTO pagePostDTO = pagePostMapper.toDto(pagePost);

    restPagePostMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pagePostDTO)))
      .andExpect(status().isBadRequest());

    List<PagePost> pagePostList = pagePostRepository.findAll();
    assertThat(pagePostList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllPagePosts() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    // Get all the pagePostList
    restPagePostMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(pagePost.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].quickInfo").value(hasItem(DEFAULT_QUICK_INFO.toString())));
  }

  @Test
  @Transactional
  void getPagePost() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    // Get the pagePost
    restPagePostMockMvc
      .perform(get(ENTITY_API_URL_ID, pagePost.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(pagePost.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
      .andExpect(jsonPath("$.quickInfo").value(DEFAULT_QUICK_INFO.toString()));
  }

  @Test
  @Transactional
  void getPagePostsByIdFiltering() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    Long id = pagePost.getId();

    defaultPagePostShouldBeFound("id.equals=" + id);
    defaultPagePostShouldNotBeFound("id.notEquals=" + id);

    defaultPagePostShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultPagePostShouldNotBeFound("id.greaterThan=" + id);

    defaultPagePostShouldBeFound("id.lessThanOrEqual=" + id);
    defaultPagePostShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllPagePostsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    // Get all the pagePostList where uuid equals to DEFAULT_UUID
    defaultPagePostShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the pagePostList where uuid equals to UPDATED_UUID
    defaultPagePostShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPagePostsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    // Get all the pagePostList where uuid not equals to DEFAULT_UUID
    defaultPagePostShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the pagePostList where uuid not equals to UPDATED_UUID
    defaultPagePostShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPagePostsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    // Get all the pagePostList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultPagePostShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the pagePostList where uuid equals to UPDATED_UUID
    defaultPagePostShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPagePostsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    // Get all the pagePostList where uuid is not null
    defaultPagePostShouldBeFound("uuid.specified=true");

    // Get all the pagePostList where uuid is null
    defaultPagePostShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllPagePostsByNameIsEqualToSomething() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    // Get all the pagePostList where name equals to DEFAULT_NAME
    defaultPagePostShouldBeFound("name.equals=" + DEFAULT_NAME);

    // Get all the pagePostList where name equals to UPDATED_NAME
    defaultPagePostShouldNotBeFound("name.equals=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllPagePostsByNameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    // Get all the pagePostList where name not equals to DEFAULT_NAME
    defaultPagePostShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

    // Get all the pagePostList where name not equals to UPDATED_NAME
    defaultPagePostShouldBeFound("name.notEquals=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllPagePostsByNameIsInShouldWork() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    // Get all the pagePostList where name in DEFAULT_NAME or UPDATED_NAME
    defaultPagePostShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

    // Get all the pagePostList where name equals to UPDATED_NAME
    defaultPagePostShouldNotBeFound("name.in=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllPagePostsByNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    // Get all the pagePostList where name is not null
    defaultPagePostShouldBeFound("name.specified=true");

    // Get all the pagePostList where name is null
    defaultPagePostShouldNotBeFound("name.specified=false");
  }

  @Test
  @Transactional
  void getAllPagePostsByNameContainsSomething() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    // Get all the pagePostList where name contains DEFAULT_NAME
    defaultPagePostShouldBeFound("name.contains=" + DEFAULT_NAME);

    // Get all the pagePostList where name contains UPDATED_NAME
    defaultPagePostShouldNotBeFound("name.contains=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllPagePostsByNameNotContainsSomething() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    // Get all the pagePostList where name does not contain DEFAULT_NAME
    defaultPagePostShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

    // Get all the pagePostList where name does not contain UPDATED_NAME
    defaultPagePostShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllPagePostsByProfileIsEqualToSomething() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);
    PageProfile profile = PageProfileResourceIT.createEntity(em);
    em.persist(profile);
    em.flush();
    pagePost.setProfile(profile);
    pagePostRepository.saveAndFlush(pagePost);
    Long profileId = profile.getId();

    // Get all the pagePostList where profile equals to profileId
    defaultPagePostShouldBeFound("profileId.equals=" + profileId);

    // Get all the pagePostList where profile equals to (profileId + 1)
    defaultPagePostShouldNotBeFound("profileId.equals=" + (profileId + 1));
  }

  @Test
  @Transactional
  void getAllPagePostsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    pagePost.setBaseInfo(baseInfo);
    pagePostRepository.saveAndFlush(pagePost);
    Long baseInfoId = baseInfo.getId();

    // Get all the pagePostList where baseInfo equals to baseInfoId
    defaultPagePostShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the pagePostList where baseInfo equals to (baseInfoId + 1)
    defaultPagePostShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllPagePostsByMyPostInPageIsEqualToSomething() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);
    Post myPostInPage = PostResourceIT.createEntity(em);
    em.persist(myPostInPage);
    em.flush();
    pagePost.addMyPostInPage(myPostInPage);
    pagePostRepository.saveAndFlush(pagePost);
    Long myPostInPageId = myPostInPage.getId();

    // Get all the pagePostList where myPostInPage equals to myPostInPageId
    defaultPagePostShouldBeFound("myPostInPageId.equals=" + myPostInPageId);

    // Get all the pagePostList where myPostInPage equals to (myPostInPageId + 1)
    defaultPagePostShouldNotBeFound("myPostInPageId.equals=" + (myPostInPageId + 1));
  }

  @Test
  @Transactional
  void getAllPagePostsByMasterUserIsEqualToSomething() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);
    MasterUser masterUser = MasterUserResourceIT.createEntity(em);
    em.persist(masterUser);
    em.flush();
    pagePost.setMasterUser(masterUser);
    pagePostRepository.saveAndFlush(pagePost);
    Long masterUserId = masterUser.getId();

    // Get all the pagePostList where masterUser equals to masterUserId
    defaultPagePostShouldBeFound("masterUserId.equals=" + masterUserId);

    // Get all the pagePostList where masterUser equals to (masterUserId + 1)
    defaultPagePostShouldNotBeFound("masterUserId.equals=" + (masterUserId + 1));
  }

  @Test
  @Transactional
  void getAllPagePostsByTopicInterestIsEqualToSomething() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);
    TopicInterest topicInterest = TopicInterestResourceIT.createEntity(em);
    em.persist(topicInterest);
    em.flush();
    pagePost.addTopicInterest(topicInterest);
    pagePostRepository.saveAndFlush(pagePost);
    Long topicInterestId = topicInterest.getId();

    // Get all the pagePostList where topicInterest equals to topicInterestId
    defaultPagePostShouldBeFound("topicInterestId.equals=" + topicInterestId);

    // Get all the pagePostList where topicInterest equals to (topicInterestId + 1)
    defaultPagePostShouldNotBeFound("topicInterestId.equals=" + (topicInterestId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultPagePostShouldBeFound(String filter) throws Exception {
    restPagePostMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(pagePost.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].quickInfo").value(hasItem(DEFAULT_QUICK_INFO.toString())));

    // Check, that the count call also returns 1
    restPagePostMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultPagePostShouldNotBeFound(String filter) throws Exception {
    restPagePostMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restPagePostMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingPagePost() throws Exception {
    // Get the pagePost
    restPagePostMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewPagePost() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    int databaseSizeBeforeUpdate = pagePostRepository.findAll().size();

    // Update the pagePost
    PagePost updatedPagePost = pagePostRepository.findById(pagePost.getId()).get();
    // Disconnect from session so that the updates on updatedPagePost are not directly saved in db
    em.detach(updatedPagePost);
    updatedPagePost.uuid(UPDATED_UUID).name(UPDATED_NAME).quickInfo(UPDATED_QUICK_INFO);
    PagePostDTO pagePostDTO = pagePostMapper.toDto(updatedPagePost);

    restPagePostMockMvc
      .perform(
        put(ENTITY_API_URL_ID, pagePostDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(pagePostDTO))
      )
      .andExpect(status().isOk());

    // Validate the PagePost in the database
    List<PagePost> pagePostList = pagePostRepository.findAll();
    assertThat(pagePostList).hasSize(databaseSizeBeforeUpdate);
    PagePost testPagePost = pagePostList.get(pagePostList.size() - 1);
    assertThat(testPagePost.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testPagePost.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testPagePost.getQuickInfo()).isEqualTo(UPDATED_QUICK_INFO);

    // Validate the PagePost in Elasticsearch
    verify(mockPagePostSearchRepository).save(testPagePost);
  }

  @Test
  @Transactional
  void putNonExistingPagePost() throws Exception {
    int databaseSizeBeforeUpdate = pagePostRepository.findAll().size();
    pagePost.setId(count.incrementAndGet());

    // Create the PagePost
    PagePostDTO pagePostDTO = pagePostMapper.toDto(pagePost);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restPagePostMockMvc
      .perform(
        put(ENTITY_API_URL_ID, pagePostDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(pagePostDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PagePost in the database
    List<PagePost> pagePostList = pagePostRepository.findAll();
    assertThat(pagePostList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PagePost in Elasticsearch
    verify(mockPagePostSearchRepository, times(0)).save(pagePost);
  }

  @Test
  @Transactional
  void putWithIdMismatchPagePost() throws Exception {
    int databaseSizeBeforeUpdate = pagePostRepository.findAll().size();
    pagePost.setId(count.incrementAndGet());

    // Create the PagePost
    PagePostDTO pagePostDTO = pagePostMapper.toDto(pagePost);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPagePostMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(pagePostDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PagePost in the database
    List<PagePost> pagePostList = pagePostRepository.findAll();
    assertThat(pagePostList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PagePost in Elasticsearch
    verify(mockPagePostSearchRepository, times(0)).save(pagePost);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamPagePost() throws Exception {
    int databaseSizeBeforeUpdate = pagePostRepository.findAll().size();
    pagePost.setId(count.incrementAndGet());

    // Create the PagePost
    PagePostDTO pagePostDTO = pagePostMapper.toDto(pagePost);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPagePostMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pagePostDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the PagePost in the database
    List<PagePost> pagePostList = pagePostRepository.findAll();
    assertThat(pagePostList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PagePost in Elasticsearch
    verify(mockPagePostSearchRepository, times(0)).save(pagePost);
  }

  @Test
  @Transactional
  void partialUpdatePagePostWithPatch() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    int databaseSizeBeforeUpdate = pagePostRepository.findAll().size();

    // Update the pagePost using partial update
    PagePost partialUpdatedPagePost = new PagePost();
    partialUpdatedPagePost.setId(pagePost.getId());

    partialUpdatedPagePost.uuid(UPDATED_UUID).name(UPDATED_NAME).quickInfo(UPDATED_QUICK_INFO);

    restPagePostMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedPagePost.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPagePost))
      )
      .andExpect(status().isOk());

    // Validate the PagePost in the database
    List<PagePost> pagePostList = pagePostRepository.findAll();
    assertThat(pagePostList).hasSize(databaseSizeBeforeUpdate);
    PagePost testPagePost = pagePostList.get(pagePostList.size() - 1);
    assertThat(testPagePost.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testPagePost.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testPagePost.getQuickInfo()).isEqualTo(UPDATED_QUICK_INFO);
  }

  @Test
  @Transactional
  void fullUpdatePagePostWithPatch() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    int databaseSizeBeforeUpdate = pagePostRepository.findAll().size();

    // Update the pagePost using partial update
    PagePost partialUpdatedPagePost = new PagePost();
    partialUpdatedPagePost.setId(pagePost.getId());

    partialUpdatedPagePost.uuid(UPDATED_UUID).name(UPDATED_NAME).quickInfo(UPDATED_QUICK_INFO);

    restPagePostMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedPagePost.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPagePost))
      )
      .andExpect(status().isOk());

    // Validate the PagePost in the database
    List<PagePost> pagePostList = pagePostRepository.findAll();
    assertThat(pagePostList).hasSize(databaseSizeBeforeUpdate);
    PagePost testPagePost = pagePostList.get(pagePostList.size() - 1);
    assertThat(testPagePost.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testPagePost.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testPagePost.getQuickInfo()).isEqualTo(UPDATED_QUICK_INFO);
  }

  @Test
  @Transactional
  void patchNonExistingPagePost() throws Exception {
    int databaseSizeBeforeUpdate = pagePostRepository.findAll().size();
    pagePost.setId(count.incrementAndGet());

    // Create the PagePost
    PagePostDTO pagePostDTO = pagePostMapper.toDto(pagePost);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restPagePostMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, pagePostDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(pagePostDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PagePost in the database
    List<PagePost> pagePostList = pagePostRepository.findAll();
    assertThat(pagePostList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PagePost in Elasticsearch
    verify(mockPagePostSearchRepository, times(0)).save(pagePost);
  }

  @Test
  @Transactional
  void patchWithIdMismatchPagePost() throws Exception {
    int databaseSizeBeforeUpdate = pagePostRepository.findAll().size();
    pagePost.setId(count.incrementAndGet());

    // Create the PagePost
    PagePostDTO pagePostDTO = pagePostMapper.toDto(pagePost);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPagePostMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(pagePostDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PagePost in the database
    List<PagePost> pagePostList = pagePostRepository.findAll();
    assertThat(pagePostList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PagePost in Elasticsearch
    verify(mockPagePostSearchRepository, times(0)).save(pagePost);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamPagePost() throws Exception {
    int databaseSizeBeforeUpdate = pagePostRepository.findAll().size();
    pagePost.setId(count.incrementAndGet());

    // Create the PagePost
    PagePostDTO pagePostDTO = pagePostMapper.toDto(pagePost);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPagePostMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(pagePostDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the PagePost in the database
    List<PagePost> pagePostList = pagePostRepository.findAll();
    assertThat(pagePostList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PagePost in Elasticsearch
    verify(mockPagePostSearchRepository, times(0)).save(pagePost);
  }

  @Test
  @Transactional
  void deletePagePost() throws Exception {
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);

    int databaseSizeBeforeDelete = pagePostRepository.findAll().size();

    // Delete the pagePost
    restPagePostMockMvc
      .perform(delete(ENTITY_API_URL_ID, pagePost.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<PagePost> pagePostList = pagePostRepository.findAll();
    assertThat(pagePostList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the PagePost in Elasticsearch
    verify(mockPagePostSearchRepository, times(1)).deleteById(pagePost.getId());
  }

  @Test
  @Transactional
  void searchPagePost() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    pagePostRepository.saveAndFlush(pagePost);
    when(mockPagePostSearchRepository.search(queryStringQuery("id:" + pagePost.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(pagePost), PageRequest.of(0, 1), 1));

    // Search the pagePost
    restPagePostMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + pagePost.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(pagePost.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].quickInfo").value(hasItem(DEFAULT_QUICK_INFO.toString())));
  }
}
