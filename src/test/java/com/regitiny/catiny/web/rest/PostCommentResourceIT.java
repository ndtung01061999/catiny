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
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.domain.PostComment;
import com.regitiny.catiny.domain.PostComment;
import com.regitiny.catiny.domain.PostLike;
import com.regitiny.catiny.repository.PostCommentRepository;
import com.regitiny.catiny.repository.search.PostCommentSearchRepository;
import com.regitiny.catiny.service.criteria.PostCommentCriteria;
import com.regitiny.catiny.service.dto.PostCommentDTO;
import com.regitiny.catiny.service.mapper.PostCommentMapper;
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
 * Integration tests for the {@link PostCommentResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class PostCommentResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
  private static final String UPDATED_CONTENT = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/post-comments";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/post-comments";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private PostCommentRepository postCommentRepository;

  @Autowired
  private PostCommentMapper postCommentMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.PostCommentSearchRepositoryMockConfiguration
   */
  @Autowired
  private PostCommentSearchRepository mockPostCommentSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restPostCommentMockMvc;

  private PostComment postComment;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static PostComment createEntity(EntityManager em) {
    PostComment postComment = new PostComment().uuid(DEFAULT_UUID).content(DEFAULT_CONTENT);
    return postComment;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static PostComment createUpdatedEntity(EntityManager em) {
    PostComment postComment = new PostComment().uuid(UPDATED_UUID).content(UPDATED_CONTENT);
    return postComment;
  }

  @BeforeEach
  public void initTest() {
    postComment = createEntity(em);
  }

  @Test
  @Transactional
  void createPostComment() throws Exception {
    int databaseSizeBeforeCreate = postCommentRepository.findAll().size();
    // Create the PostComment
    PostCommentDTO postCommentDTO = postCommentMapper.toDto(postComment);
    restPostCommentMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postCommentDTO)))
      .andExpect(status().isCreated());

    // Validate the PostComment in the database
    List<PostComment> postCommentList = postCommentRepository.findAll();
    assertThat(postCommentList).hasSize(databaseSizeBeforeCreate + 1);
    PostComment testPostComment = postCommentList.get(postCommentList.size() - 1);
    assertThat(testPostComment.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testPostComment.getContent()).isEqualTo(DEFAULT_CONTENT);

    // Validate the PostComment in Elasticsearch
    verify(mockPostCommentSearchRepository, times(1)).save(testPostComment);
  }

  @Test
  @Transactional
  void createPostCommentWithExistingId() throws Exception {
    // Create the PostComment with an existing ID
    postComment.setId(1L);
    PostCommentDTO postCommentDTO = postCommentMapper.toDto(postComment);

    int databaseSizeBeforeCreate = postCommentRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restPostCommentMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postCommentDTO)))
      .andExpect(status().isBadRequest());

    // Validate the PostComment in the database
    List<PostComment> postCommentList = postCommentRepository.findAll();
    assertThat(postCommentList).hasSize(databaseSizeBeforeCreate);

    // Validate the PostComment in Elasticsearch
    verify(mockPostCommentSearchRepository, times(0)).save(postComment);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = postCommentRepository.findAll().size();
    // set the field null
    postComment.setUuid(null);

    // Create the PostComment, which fails.
    PostCommentDTO postCommentDTO = postCommentMapper.toDto(postComment);

    restPostCommentMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postCommentDTO)))
      .andExpect(status().isBadRequest());

    List<PostComment> postCommentList = postCommentRepository.findAll();
    assertThat(postCommentList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllPostComments() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);

    // Get all the postCommentList
    restPostCommentMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(postComment.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));
  }

  @Test
  @Transactional
  void getPostComment() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);

    // Get the postComment
    restPostCommentMockMvc
      .perform(get(ENTITY_API_URL_ID, postComment.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(postComment.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()));
  }

  @Test
  @Transactional
  void getPostCommentsByIdFiltering() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);

    Long id = postComment.getId();

    defaultPostCommentShouldBeFound("id.equals=" + id);
    defaultPostCommentShouldNotBeFound("id.notEquals=" + id);

    defaultPostCommentShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultPostCommentShouldNotBeFound("id.greaterThan=" + id);

    defaultPostCommentShouldBeFound("id.lessThanOrEqual=" + id);
    defaultPostCommentShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllPostCommentsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);

    // Get all the postCommentList where uuid equals to DEFAULT_UUID
    defaultPostCommentShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the postCommentList where uuid equals to UPDATED_UUID
    defaultPostCommentShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPostCommentsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);

    // Get all the postCommentList where uuid not equals to DEFAULT_UUID
    defaultPostCommentShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the postCommentList where uuid not equals to UPDATED_UUID
    defaultPostCommentShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPostCommentsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);

    // Get all the postCommentList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultPostCommentShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the postCommentList where uuid equals to UPDATED_UUID
    defaultPostCommentShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPostCommentsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);

    // Get all the postCommentList where uuid is not null
    defaultPostCommentShouldBeFound("uuid.specified=true");

    // Get all the postCommentList where uuid is null
    defaultPostCommentShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllPostCommentsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    postComment.setBaseInfo(baseInfo);
    postCommentRepository.saveAndFlush(postComment);
    Long baseInfoId = baseInfo.getId();

    // Get all the postCommentList where baseInfo equals to baseInfoId
    defaultPostCommentShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the postCommentList where baseInfo equals to (baseInfoId + 1)
    defaultPostCommentShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllPostCommentsByLikeIsEqualToSomething() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);
    PostLike like = PostLikeResourceIT.createEntity(em);
    em.persist(like);
    em.flush();
    postComment.addLike(like);
    postCommentRepository.saveAndFlush(postComment);
    Long likeId = like.getId();

    // Get all the postCommentList where like equals to likeId
    defaultPostCommentShouldBeFound("likeId.equals=" + likeId);

    // Get all the postCommentList where like equals to (likeId + 1)
    defaultPostCommentShouldNotBeFound("likeId.equals=" + (likeId + 1));
  }

  @Test
  @Transactional
  void getAllPostCommentsByCommentReplyIsEqualToSomething() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);
    PostComment commentReply = PostCommentResourceIT.createEntity(em);
    em.persist(commentReply);
    em.flush();
    postComment.addCommentReply(commentReply);
    postCommentRepository.saveAndFlush(postComment);
    Long commentReplyId = commentReply.getId();

    // Get all the postCommentList where commentReply equals to commentReplyId
    defaultPostCommentShouldBeFound("commentReplyId.equals=" + commentReplyId);

    // Get all the postCommentList where commentReply equals to (commentReplyId + 1)
    defaultPostCommentShouldNotBeFound("commentReplyId.equals=" + (commentReplyId + 1));
  }

  @Test
  @Transactional
  void getAllPostCommentsByPostIsEqualToSomething() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);
    Post post = PostResourceIT.createEntity(em);
    em.persist(post);
    em.flush();
    postComment.setPost(post);
    postCommentRepository.saveAndFlush(postComment);
    Long postId = post.getId();

    // Get all the postCommentList where post equals to postId
    defaultPostCommentShouldBeFound("postId.equals=" + postId);

    // Get all the postCommentList where post equals to (postId + 1)
    defaultPostCommentShouldNotBeFound("postId.equals=" + (postId + 1));
  }

  @Test
  @Transactional
  void getAllPostCommentsByCommentParentIsEqualToSomething() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);
    PostComment commentParent = PostCommentResourceIT.createEntity(em);
    em.persist(commentParent);
    em.flush();
    postComment.setCommentParent(commentParent);
    postCommentRepository.saveAndFlush(postComment);
    Long commentParentId = commentParent.getId();

    // Get all the postCommentList where commentParent equals to commentParentId
    defaultPostCommentShouldBeFound("commentParentId.equals=" + commentParentId);

    // Get all the postCommentList where commentParent equals to (commentParentId + 1)
    defaultPostCommentShouldNotBeFound("commentParentId.equals=" + (commentParentId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultPostCommentShouldBeFound(String filter) throws Exception {
    restPostCommentMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(postComment.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));

    // Check, that the count call also returns 1
    restPostCommentMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultPostCommentShouldNotBeFound(String filter) throws Exception {
    restPostCommentMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restPostCommentMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingPostComment() throws Exception {
    // Get the postComment
    restPostCommentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewPostComment() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);

    int databaseSizeBeforeUpdate = postCommentRepository.findAll().size();

    // Update the postComment
    PostComment updatedPostComment = postCommentRepository.findById(postComment.getId()).get();
    // Disconnect from session so that the updates on updatedPostComment are not directly saved in db
    em.detach(updatedPostComment);
    updatedPostComment.uuid(UPDATED_UUID).content(UPDATED_CONTENT);
    PostCommentDTO postCommentDTO = postCommentMapper.toDto(updatedPostComment);

    restPostCommentMockMvc
      .perform(
        put(ENTITY_API_URL_ID, postCommentDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(postCommentDTO))
      )
      .andExpect(status().isOk());

    // Validate the PostComment in the database
    List<PostComment> postCommentList = postCommentRepository.findAll();
    assertThat(postCommentList).hasSize(databaseSizeBeforeUpdate);
    PostComment testPostComment = postCommentList.get(postCommentList.size() - 1);
    assertThat(testPostComment.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testPostComment.getContent()).isEqualTo(UPDATED_CONTENT);

    // Validate the PostComment in Elasticsearch
    verify(mockPostCommentSearchRepository).save(testPostComment);
  }

  @Test
  @Transactional
  void putNonExistingPostComment() throws Exception {
    int databaseSizeBeforeUpdate = postCommentRepository.findAll().size();
    postComment.setId(count.incrementAndGet());

    // Create the PostComment
    PostCommentDTO postCommentDTO = postCommentMapper.toDto(postComment);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restPostCommentMockMvc
      .perform(
        put(ENTITY_API_URL_ID, postCommentDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(postCommentDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PostComment in the database
    List<PostComment> postCommentList = postCommentRepository.findAll();
    assertThat(postCommentList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PostComment in Elasticsearch
    verify(mockPostCommentSearchRepository, times(0)).save(postComment);
  }

  @Test
  @Transactional
  void putWithIdMismatchPostComment() throws Exception {
    int databaseSizeBeforeUpdate = postCommentRepository.findAll().size();
    postComment.setId(count.incrementAndGet());

    // Create the PostComment
    PostCommentDTO postCommentDTO = postCommentMapper.toDto(postComment);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPostCommentMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(postCommentDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PostComment in the database
    List<PostComment> postCommentList = postCommentRepository.findAll();
    assertThat(postCommentList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PostComment in Elasticsearch
    verify(mockPostCommentSearchRepository, times(0)).save(postComment);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamPostComment() throws Exception {
    int databaseSizeBeforeUpdate = postCommentRepository.findAll().size();
    postComment.setId(count.incrementAndGet());

    // Create the PostComment
    PostCommentDTO postCommentDTO = postCommentMapper.toDto(postComment);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPostCommentMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postCommentDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the PostComment in the database
    List<PostComment> postCommentList = postCommentRepository.findAll();
    assertThat(postCommentList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PostComment in Elasticsearch
    verify(mockPostCommentSearchRepository, times(0)).save(postComment);
  }

  @Test
  @Transactional
  void partialUpdatePostCommentWithPatch() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);

    int databaseSizeBeforeUpdate = postCommentRepository.findAll().size();

    // Update the postComment using partial update
    PostComment partialUpdatedPostComment = new PostComment();
    partialUpdatedPostComment.setId(postComment.getId());

    partialUpdatedPostComment.uuid(UPDATED_UUID);

    restPostCommentMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedPostComment.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPostComment))
      )
      .andExpect(status().isOk());

    // Validate the PostComment in the database
    List<PostComment> postCommentList = postCommentRepository.findAll();
    assertThat(postCommentList).hasSize(databaseSizeBeforeUpdate);
    PostComment testPostComment = postCommentList.get(postCommentList.size() - 1);
    assertThat(testPostComment.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testPostComment.getContent()).isEqualTo(DEFAULT_CONTENT);
  }

  @Test
  @Transactional
  void fullUpdatePostCommentWithPatch() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);

    int databaseSizeBeforeUpdate = postCommentRepository.findAll().size();

    // Update the postComment using partial update
    PostComment partialUpdatedPostComment = new PostComment();
    partialUpdatedPostComment.setId(postComment.getId());

    partialUpdatedPostComment.uuid(UPDATED_UUID).content(UPDATED_CONTENT);

    restPostCommentMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedPostComment.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPostComment))
      )
      .andExpect(status().isOk());

    // Validate the PostComment in the database
    List<PostComment> postCommentList = postCommentRepository.findAll();
    assertThat(postCommentList).hasSize(databaseSizeBeforeUpdate);
    PostComment testPostComment = postCommentList.get(postCommentList.size() - 1);
    assertThat(testPostComment.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testPostComment.getContent()).isEqualTo(UPDATED_CONTENT);
  }

  @Test
  @Transactional
  void patchNonExistingPostComment() throws Exception {
    int databaseSizeBeforeUpdate = postCommentRepository.findAll().size();
    postComment.setId(count.incrementAndGet());

    // Create the PostComment
    PostCommentDTO postCommentDTO = postCommentMapper.toDto(postComment);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restPostCommentMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, postCommentDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(postCommentDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PostComment in the database
    List<PostComment> postCommentList = postCommentRepository.findAll();
    assertThat(postCommentList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PostComment in Elasticsearch
    verify(mockPostCommentSearchRepository, times(0)).save(postComment);
  }

  @Test
  @Transactional
  void patchWithIdMismatchPostComment() throws Exception {
    int databaseSizeBeforeUpdate = postCommentRepository.findAll().size();
    postComment.setId(count.incrementAndGet());

    // Create the PostComment
    PostCommentDTO postCommentDTO = postCommentMapper.toDto(postComment);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPostCommentMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(postCommentDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PostComment in the database
    List<PostComment> postCommentList = postCommentRepository.findAll();
    assertThat(postCommentList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PostComment in Elasticsearch
    verify(mockPostCommentSearchRepository, times(0)).save(postComment);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamPostComment() throws Exception {
    int databaseSizeBeforeUpdate = postCommentRepository.findAll().size();
    postComment.setId(count.incrementAndGet());

    // Create the PostComment
    PostCommentDTO postCommentDTO = postCommentMapper.toDto(postComment);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPostCommentMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(postCommentDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the PostComment in the database
    List<PostComment> postCommentList = postCommentRepository.findAll();
    assertThat(postCommentList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PostComment in Elasticsearch
    verify(mockPostCommentSearchRepository, times(0)).save(postComment);
  }

  @Test
  @Transactional
  void deletePostComment() throws Exception {
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);

    int databaseSizeBeforeDelete = postCommentRepository.findAll().size();

    // Delete the postComment
    restPostCommentMockMvc
      .perform(delete(ENTITY_API_URL_ID, postComment.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<PostComment> postCommentList = postCommentRepository.findAll();
    assertThat(postCommentList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the PostComment in Elasticsearch
    verify(mockPostCommentSearchRepository, times(1)).deleteById(postComment.getId());
  }

  @Test
  @Transactional
  void searchPostComment() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    postCommentRepository.saveAndFlush(postComment);
    when(mockPostCommentSearchRepository.search(queryStringQuery("id:" + postComment.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(postComment), PageRequest.of(0, 1), 1));

    // Search the postComment
    restPostCommentMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + postComment.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(postComment.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));
  }
}
