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
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.domain.PostLike;
import com.regitiny.catiny.repository.PostLikeRepository;
import com.regitiny.catiny.repository.search.PostLikeSearchRepository;
import com.regitiny.catiny.service.criteria.PostLikeCriteria;
import com.regitiny.catiny.service.dto.PostLikeDTO;
import com.regitiny.catiny.service.mapper.PostLikeMapper;
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
 * Integration tests for the {@link PostLikeResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class PostLikeResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String ENTITY_API_URL = "/api/post-likes";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/post-likes";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private PostLikeRepository postLikeRepository;

  @Autowired
  private PostLikeMapper postLikeMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.PostLikeSearchRepositoryMockConfiguration
   */
  @Autowired
  private PostLikeSearchRepository mockPostLikeSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restPostLikeMockMvc;

  private PostLike postLike;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static PostLike createEntity(EntityManager em) {
    PostLike postLike = new PostLike().uuid(DEFAULT_UUID);
    return postLike;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static PostLike createUpdatedEntity(EntityManager em) {
    PostLike postLike = new PostLike().uuid(UPDATED_UUID);
    return postLike;
  }

  @BeforeEach
  public void initTest() {
    postLike = createEntity(em);
  }

  @Test
  @Transactional
  void createPostLike() throws Exception {
    int databaseSizeBeforeCreate = postLikeRepository.findAll().size();
    // Create the PostLike
    PostLikeDTO postLikeDTO = postLikeMapper.toDto(postLike);
    restPostLikeMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postLikeDTO)))
      .andExpect(status().isCreated());

    // Validate the PostLike in the database
    List<PostLike> postLikeList = postLikeRepository.findAll();
    assertThat(postLikeList).hasSize(databaseSizeBeforeCreate + 1);
    PostLike testPostLike = postLikeList.get(postLikeList.size() - 1);
    assertThat(testPostLike.getUuid()).isEqualTo(DEFAULT_UUID);

    // Validate the PostLike in Elasticsearch
    verify(mockPostLikeSearchRepository, times(1)).save(testPostLike);
  }

  @Test
  @Transactional
  void createPostLikeWithExistingId() throws Exception {
    // Create the PostLike with an existing ID
    postLike.setId(1L);
    PostLikeDTO postLikeDTO = postLikeMapper.toDto(postLike);

    int databaseSizeBeforeCreate = postLikeRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restPostLikeMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postLikeDTO)))
      .andExpect(status().isBadRequest());

    // Validate the PostLike in the database
    List<PostLike> postLikeList = postLikeRepository.findAll();
    assertThat(postLikeList).hasSize(databaseSizeBeforeCreate);

    // Validate the PostLike in Elasticsearch
    verify(mockPostLikeSearchRepository, times(0)).save(postLike);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = postLikeRepository.findAll().size();
    // set the field null
    postLike.setUuid(null);

    // Create the PostLike, which fails.
    PostLikeDTO postLikeDTO = postLikeMapper.toDto(postLike);

    restPostLikeMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postLikeDTO)))
      .andExpect(status().isBadRequest());

    List<PostLike> postLikeList = postLikeRepository.findAll();
    assertThat(postLikeList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllPostLikes() throws Exception {
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);

    // Get all the postLikeList
    restPostLikeMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(postLike.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
  }

  @Test
  @Transactional
  void getPostLike() throws Exception {
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);

    // Get the postLike
    restPostLikeMockMvc
      .perform(get(ENTITY_API_URL_ID, postLike.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(postLike.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()));
  }

  @Test
  @Transactional
  void getPostLikesByIdFiltering() throws Exception {
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);

    Long id = postLike.getId();

    defaultPostLikeShouldBeFound("id.equals=" + id);
    defaultPostLikeShouldNotBeFound("id.notEquals=" + id);

    defaultPostLikeShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultPostLikeShouldNotBeFound("id.greaterThan=" + id);

    defaultPostLikeShouldBeFound("id.lessThanOrEqual=" + id);
    defaultPostLikeShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllPostLikesByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);

    // Get all the postLikeList where uuid equals to DEFAULT_UUID
    defaultPostLikeShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the postLikeList where uuid equals to UPDATED_UUID
    defaultPostLikeShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPostLikesByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);

    // Get all the postLikeList where uuid not equals to DEFAULT_UUID
    defaultPostLikeShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the postLikeList where uuid not equals to UPDATED_UUID
    defaultPostLikeShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPostLikesByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);

    // Get all the postLikeList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultPostLikeShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the postLikeList where uuid equals to UPDATED_UUID
    defaultPostLikeShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPostLikesByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);

    // Get all the postLikeList where uuid is not null
    defaultPostLikeShouldBeFound("uuid.specified=true");

    // Get all the postLikeList where uuid is null
    defaultPostLikeShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllPostLikesByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    postLike.setBaseInfo(baseInfo);
    postLikeRepository.saveAndFlush(postLike);
    Long baseInfoId = baseInfo.getId();

    // Get all the postLikeList where baseInfo equals to baseInfoId
    defaultPostLikeShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the postLikeList where baseInfo equals to (baseInfoId + 1)
    defaultPostLikeShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllPostLikesByUserLikeIsEqualToSomething() throws Exception {
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);
    MasterUser userLike = MasterUserResourceIT.createEntity(em);
    em.persist(userLike);
    em.flush();
    postLike.setUserLike(userLike);
    postLikeRepository.saveAndFlush(postLike);
    Long userLikeId = userLike.getId();

    // Get all the postLikeList where userLike equals to userLikeId
    defaultPostLikeShouldBeFound("userLikeId.equals=" + userLikeId);

    // Get all the postLikeList where userLike equals to (userLikeId + 1)
    defaultPostLikeShouldNotBeFound("userLikeId.equals=" + (userLikeId + 1));
  }

  @Test
  @Transactional
  void getAllPostLikesByPostIsEqualToSomething() throws Exception {
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);
    Post post = PostResourceIT.createEntity(em);
    em.persist(post);
    em.flush();
    postLike.setPost(post);
    postLikeRepository.saveAndFlush(postLike);
    Long postId = post.getId();

    // Get all the postLikeList where post equals to postId
    defaultPostLikeShouldBeFound("postId.equals=" + postId);

    // Get all the postLikeList where post equals to (postId + 1)
    defaultPostLikeShouldNotBeFound("postId.equals=" + (postId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultPostLikeShouldBeFound(String filter) throws Exception {
    restPostLikeMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(postLike.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));

    // Check, that the count call also returns 1
    restPostLikeMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultPostLikeShouldNotBeFound(String filter) throws Exception {
    restPostLikeMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restPostLikeMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingPostLike() throws Exception {
    // Get the postLike
    restPostLikeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewPostLike() throws Exception {
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);

    int databaseSizeBeforeUpdate = postLikeRepository.findAll().size();

    // Update the postLike
    PostLike updatedPostLike = postLikeRepository.findById(postLike.getId()).get();
    // Disconnect from session so that the updates on updatedPostLike are not directly saved in db
    em.detach(updatedPostLike);
    updatedPostLike.uuid(UPDATED_UUID);
    PostLikeDTO postLikeDTO = postLikeMapper.toDto(updatedPostLike);

    restPostLikeMockMvc
      .perform(
        put(ENTITY_API_URL_ID, postLikeDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(postLikeDTO))
      )
      .andExpect(status().isOk());

    // Validate the PostLike in the database
    List<PostLike> postLikeList = postLikeRepository.findAll();
    assertThat(postLikeList).hasSize(databaseSizeBeforeUpdate);
    PostLike testPostLike = postLikeList.get(postLikeList.size() - 1);
    assertThat(testPostLike.getUuid()).isEqualTo(UPDATED_UUID);

    // Validate the PostLike in Elasticsearch
    verify(mockPostLikeSearchRepository).save(testPostLike);
  }

  @Test
  @Transactional
  void putNonExistingPostLike() throws Exception {
    int databaseSizeBeforeUpdate = postLikeRepository.findAll().size();
    postLike.setId(count.incrementAndGet());

    // Create the PostLike
    PostLikeDTO postLikeDTO = postLikeMapper.toDto(postLike);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restPostLikeMockMvc
      .perform(
        put(ENTITY_API_URL_ID, postLikeDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(postLikeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PostLike in the database
    List<PostLike> postLikeList = postLikeRepository.findAll();
    assertThat(postLikeList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PostLike in Elasticsearch
    verify(mockPostLikeSearchRepository, times(0)).save(postLike);
  }

  @Test
  @Transactional
  void putWithIdMismatchPostLike() throws Exception {
    int databaseSizeBeforeUpdate = postLikeRepository.findAll().size();
    postLike.setId(count.incrementAndGet());

    // Create the PostLike
    PostLikeDTO postLikeDTO = postLikeMapper.toDto(postLike);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPostLikeMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(postLikeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PostLike in the database
    List<PostLike> postLikeList = postLikeRepository.findAll();
    assertThat(postLikeList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PostLike in Elasticsearch
    verify(mockPostLikeSearchRepository, times(0)).save(postLike);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamPostLike() throws Exception {
    int databaseSizeBeforeUpdate = postLikeRepository.findAll().size();
    postLike.setId(count.incrementAndGet());

    // Create the PostLike
    PostLikeDTO postLikeDTO = postLikeMapper.toDto(postLike);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPostLikeMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postLikeDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the PostLike in the database
    List<PostLike> postLikeList = postLikeRepository.findAll();
    assertThat(postLikeList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PostLike in Elasticsearch
    verify(mockPostLikeSearchRepository, times(0)).save(postLike);
  }

  @Test
  @Transactional
  void partialUpdatePostLikeWithPatch() throws Exception {
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);

    int databaseSizeBeforeUpdate = postLikeRepository.findAll().size();

    // Update the postLike using partial update
    PostLike partialUpdatedPostLike = new PostLike();
    partialUpdatedPostLike.setId(postLike.getId());

    partialUpdatedPostLike.uuid(UPDATED_UUID);

    restPostLikeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedPostLike.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPostLike))
      )
      .andExpect(status().isOk());

    // Validate the PostLike in the database
    List<PostLike> postLikeList = postLikeRepository.findAll();
    assertThat(postLikeList).hasSize(databaseSizeBeforeUpdate);
    PostLike testPostLike = postLikeList.get(postLikeList.size() - 1);
    assertThat(testPostLike.getUuid()).isEqualTo(UPDATED_UUID);
  }

  @Test
  @Transactional
  void fullUpdatePostLikeWithPatch() throws Exception {
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);

    int databaseSizeBeforeUpdate = postLikeRepository.findAll().size();

    // Update the postLike using partial update
    PostLike partialUpdatedPostLike = new PostLike();
    partialUpdatedPostLike.setId(postLike.getId());

    partialUpdatedPostLike.uuid(UPDATED_UUID);

    restPostLikeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedPostLike.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPostLike))
      )
      .andExpect(status().isOk());

    // Validate the PostLike in the database
    List<PostLike> postLikeList = postLikeRepository.findAll();
    assertThat(postLikeList).hasSize(databaseSizeBeforeUpdate);
    PostLike testPostLike = postLikeList.get(postLikeList.size() - 1);
    assertThat(testPostLike.getUuid()).isEqualTo(UPDATED_UUID);
  }

  @Test
  @Transactional
  void patchNonExistingPostLike() throws Exception {
    int databaseSizeBeforeUpdate = postLikeRepository.findAll().size();
    postLike.setId(count.incrementAndGet());

    // Create the PostLike
    PostLikeDTO postLikeDTO = postLikeMapper.toDto(postLike);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restPostLikeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, postLikeDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(postLikeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PostLike in the database
    List<PostLike> postLikeList = postLikeRepository.findAll();
    assertThat(postLikeList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PostLike in Elasticsearch
    verify(mockPostLikeSearchRepository, times(0)).save(postLike);
  }

  @Test
  @Transactional
  void patchWithIdMismatchPostLike() throws Exception {
    int databaseSizeBeforeUpdate = postLikeRepository.findAll().size();
    postLike.setId(count.incrementAndGet());

    // Create the PostLike
    PostLikeDTO postLikeDTO = postLikeMapper.toDto(postLike);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPostLikeMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(postLikeDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PostLike in the database
    List<PostLike> postLikeList = postLikeRepository.findAll();
    assertThat(postLikeList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PostLike in Elasticsearch
    verify(mockPostLikeSearchRepository, times(0)).save(postLike);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamPostLike() throws Exception {
    int databaseSizeBeforeUpdate = postLikeRepository.findAll().size();
    postLike.setId(count.incrementAndGet());

    // Create the PostLike
    PostLikeDTO postLikeDTO = postLikeMapper.toDto(postLike);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPostLikeMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(postLikeDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the PostLike in the database
    List<PostLike> postLikeList = postLikeRepository.findAll();
    assertThat(postLikeList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PostLike in Elasticsearch
    verify(mockPostLikeSearchRepository, times(0)).save(postLike);
  }

  @Test
  @Transactional
  void deletePostLike() throws Exception {
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);

    int databaseSizeBeforeDelete = postLikeRepository.findAll().size();

    // Delete the postLike
    restPostLikeMockMvc
      .perform(delete(ENTITY_API_URL_ID, postLike.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<PostLike> postLikeList = postLikeRepository.findAll();
    assertThat(postLikeList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the PostLike in Elasticsearch
    verify(mockPostLikeSearchRepository, times(1)).deleteById(postLike.getId());
  }

  @Test
  @Transactional
  void searchPostLike() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    postLikeRepository.saveAndFlush(postLike);
    when(mockPostLikeSearchRepository.search(queryStringQuery("id:" + postLike.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(postLike), PageRequest.of(0, 1), 1));

    // Search the postLike
    restPostLikeMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + postLike.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(postLike.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
  }
}
