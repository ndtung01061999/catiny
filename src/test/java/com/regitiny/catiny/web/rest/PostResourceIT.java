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
import com.regitiny.catiny.domain.NewsFeed;
import com.regitiny.catiny.domain.PagePost;
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.domain.PostComment;
import com.regitiny.catiny.domain.PostLike;
import com.regitiny.catiny.domain.TopicInterest;
import com.regitiny.catiny.domain.enumeration.PostInType;
import com.regitiny.catiny.domain.enumeration.PostType;
import com.regitiny.catiny.repository.PostRepository;
import com.regitiny.catiny.repository.search.PostSearchRepository;
import com.regitiny.catiny.service.criteria.PostCriteria;
import com.regitiny.catiny.service.dto.PostDTO;
import com.regitiny.catiny.service.mapper.PostMapper;
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
 * Integration tests for the {@link PostResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class PostResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final PostInType DEFAULT_POST_IN_TYPE = PostInType.GROUP;
  private static final PostInType UPDATED_POST_IN_TYPE = PostInType.USER;

  private static final PostType DEFAULT_POST_TYPE = PostType.SIMPLE;
  private static final PostType UPDATED_POST_TYPE = PostType.ADVANCE;

  private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
  private static final String UPDATED_CONTENT = "BBBBBBBBBB";

  private static final String DEFAULT_SEARCH_FIELD = "AAAAAAAAAA";
  private static final String UPDATED_SEARCH_FIELD = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/posts";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/posts";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private PostMapper postMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.PostSearchRepositoryMockConfiguration
   */
  @Autowired
  private PostSearchRepository mockPostSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restPostMockMvc;

  private Post post;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Post createEntity(EntityManager em) {
    Post post = new Post()
      .uuid(DEFAULT_UUID)
      .postInType(DEFAULT_POST_IN_TYPE)
      .postType(DEFAULT_POST_TYPE)
      .content(DEFAULT_CONTENT)
      .searchField(DEFAULT_SEARCH_FIELD);
    return post;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Post createUpdatedEntity(EntityManager em) {
    Post post = new Post()
      .uuid(UPDATED_UUID)
      .postInType(UPDATED_POST_IN_TYPE)
      .postType(UPDATED_POST_TYPE)
      .content(UPDATED_CONTENT)
      .searchField(UPDATED_SEARCH_FIELD);
    return post;
  }

  @BeforeEach
  public void initTest() {
    post = createEntity(em);
  }

  @Test
  @Transactional
  void createPost() throws Exception {
    int databaseSizeBeforeCreate = postRepository.findAll().size();
    // Create the Post
    PostDTO postDTO = postMapper.toDto(post);
    restPostMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postDTO)))
      .andExpect(status().isCreated());

    // Validate the Post in the database
    List<Post> postList = postRepository.findAll();
    assertThat(postList).hasSize(databaseSizeBeforeCreate + 1);
    Post testPost = postList.get(postList.size() - 1);
    assertThat(testPost.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testPost.getPostInType()).isEqualTo(DEFAULT_POST_IN_TYPE);
    assertThat(testPost.getPostType()).isEqualTo(DEFAULT_POST_TYPE);
    assertThat(testPost.getContent()).isEqualTo(DEFAULT_CONTENT);
    assertThat(testPost.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);

    // Validate the Post in Elasticsearch
    verify(mockPostSearchRepository, times(1)).save(testPost);
  }

  @Test
  @Transactional
  void createPostWithExistingId() throws Exception {
    // Create the Post with an existing ID
    post.setId(1L);
    PostDTO postDTO = postMapper.toDto(post);

    int databaseSizeBeforeCreate = postRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restPostMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postDTO)))
      .andExpect(status().isBadRequest());

    // Validate the Post in the database
    List<Post> postList = postRepository.findAll();
    assertThat(postList).hasSize(databaseSizeBeforeCreate);

    // Validate the Post in Elasticsearch
    verify(mockPostSearchRepository, times(0)).save(post);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = postRepository.findAll().size();
    // set the field null
    post.setUuid(null);

    // Create the Post, which fails.
    PostDTO postDTO = postMapper.toDto(post);

    restPostMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postDTO)))
      .andExpect(status().isBadRequest());

    List<Post> postList = postRepository.findAll();
    assertThat(postList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllPosts() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    // Get all the postList
    restPostMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(post.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].postInType").value(hasItem(DEFAULT_POST_IN_TYPE.toString())))
      .andExpect(jsonPath("$.[*].postType").value(hasItem(DEFAULT_POST_TYPE.toString())))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
      .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())));
  }

  @Test
  @Transactional
  void getPost() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    // Get the post
    restPostMockMvc
      .perform(get(ENTITY_API_URL_ID, post.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(post.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.postInType").value(DEFAULT_POST_IN_TYPE.toString()))
      .andExpect(jsonPath("$.postType").value(DEFAULT_POST_TYPE.toString()))
      .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
      .andExpect(jsonPath("$.searchField").value(DEFAULT_SEARCH_FIELD.toString()));
  }

  @Test
  @Transactional
  void getPostsByIdFiltering() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    Long id = post.getId();

    defaultPostShouldBeFound("id.equals=" + id);
    defaultPostShouldNotBeFound("id.notEquals=" + id);

    defaultPostShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultPostShouldNotBeFound("id.greaterThan=" + id);

    defaultPostShouldBeFound("id.lessThanOrEqual=" + id);
    defaultPostShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllPostsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    // Get all the postList where uuid equals to DEFAULT_UUID
    defaultPostShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the postList where uuid equals to UPDATED_UUID
    defaultPostShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPostsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    // Get all the postList where uuid not equals to DEFAULT_UUID
    defaultPostShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the postList where uuid not equals to UPDATED_UUID
    defaultPostShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPostsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    // Get all the postList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultPostShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the postList where uuid equals to UPDATED_UUID
    defaultPostShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPostsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    // Get all the postList where uuid is not null
    defaultPostShouldBeFound("uuid.specified=true");

    // Get all the postList where uuid is null
    defaultPostShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllPostsByPostInTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    // Get all the postList where postInType equals to DEFAULT_POST_IN_TYPE
    defaultPostShouldBeFound("postInType.equals=" + DEFAULT_POST_IN_TYPE);

    // Get all the postList where postInType equals to UPDATED_POST_IN_TYPE
    defaultPostShouldNotBeFound("postInType.equals=" + UPDATED_POST_IN_TYPE);
  }

  @Test
  @Transactional
  void getAllPostsByPostInTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    // Get all the postList where postInType not equals to DEFAULT_POST_IN_TYPE
    defaultPostShouldNotBeFound("postInType.notEquals=" + DEFAULT_POST_IN_TYPE);

    // Get all the postList where postInType not equals to UPDATED_POST_IN_TYPE
    defaultPostShouldBeFound("postInType.notEquals=" + UPDATED_POST_IN_TYPE);
  }

  @Test
  @Transactional
  void getAllPostsByPostInTypeIsInShouldWork() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    // Get all the postList where postInType in DEFAULT_POST_IN_TYPE or UPDATED_POST_IN_TYPE
    defaultPostShouldBeFound("postInType.in=" + DEFAULT_POST_IN_TYPE + "," + UPDATED_POST_IN_TYPE);

    // Get all the postList where postInType equals to UPDATED_POST_IN_TYPE
    defaultPostShouldNotBeFound("postInType.in=" + UPDATED_POST_IN_TYPE);
  }

  @Test
  @Transactional
  void getAllPostsByPostInTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    // Get all the postList where postInType is not null
    defaultPostShouldBeFound("postInType.specified=true");

    // Get all the postList where postInType is null
    defaultPostShouldNotBeFound("postInType.specified=false");
  }

  @Test
  @Transactional
  void getAllPostsByPostTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    // Get all the postList where postType equals to DEFAULT_POST_TYPE
    defaultPostShouldBeFound("postType.equals=" + DEFAULT_POST_TYPE);

    // Get all the postList where postType equals to UPDATED_POST_TYPE
    defaultPostShouldNotBeFound("postType.equals=" + UPDATED_POST_TYPE);
  }

  @Test
  @Transactional
  void getAllPostsByPostTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    // Get all the postList where postType not equals to DEFAULT_POST_TYPE
    defaultPostShouldNotBeFound("postType.notEquals=" + DEFAULT_POST_TYPE);

    // Get all the postList where postType not equals to UPDATED_POST_TYPE
    defaultPostShouldBeFound("postType.notEquals=" + UPDATED_POST_TYPE);
  }

  @Test
  @Transactional
  void getAllPostsByPostTypeIsInShouldWork() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    // Get all the postList where postType in DEFAULT_POST_TYPE or UPDATED_POST_TYPE
    defaultPostShouldBeFound("postType.in=" + DEFAULT_POST_TYPE + "," + UPDATED_POST_TYPE);

    // Get all the postList where postType equals to UPDATED_POST_TYPE
    defaultPostShouldNotBeFound("postType.in=" + UPDATED_POST_TYPE);
  }

  @Test
  @Transactional
  void getAllPostsByPostTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    // Get all the postList where postType is not null
    defaultPostShouldBeFound("postType.specified=true");

    // Get all the postList where postType is null
    defaultPostShouldNotBeFound("postType.specified=false");
  }

  @Test
  @Transactional
  void getAllPostsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    post.setBaseInfo(baseInfo);
    postRepository.saveAndFlush(post);
    Long baseInfoId = baseInfo.getId();

    // Get all the postList where baseInfo equals to baseInfoId
    defaultPostShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the postList where baseInfo equals to (baseInfoId + 1)
    defaultPostShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllPostsByPostCommentIsEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);
    PostComment postComment = PostCommentResourceIT.createEntity(em);
    em.persist(postComment);
    em.flush();
    post.addPostComment(postComment);
    postRepository.saveAndFlush(post);
    Long postCommentId = postComment.getId();

    // Get all the postList where postComment equals to postCommentId
    defaultPostShouldBeFound("postCommentId.equals=" + postCommentId);

    // Get all the postList where postComment equals to (postCommentId + 1)
    defaultPostShouldNotBeFound("postCommentId.equals=" + (postCommentId + 1));
  }

  @Test
  @Transactional
  void getAllPostsByPostLikeIsEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);
    PostLike postLike = PostLikeResourceIT.createEntity(em);
    em.persist(postLike);
    em.flush();
    post.addPostLike(postLike);
    postRepository.saveAndFlush(post);
    Long postLikeId = postLike.getId();

    // Get all the postList where postLike equals to postLikeId
    defaultPostShouldBeFound("postLikeId.equals=" + postLikeId);

    // Get all the postList where postLike equals to (postLikeId + 1)
    defaultPostShouldNotBeFound("postLikeId.equals=" + (postLikeId + 1));
  }

  @Test
  @Transactional
  void getAllPostsByPostShareChildrenIsEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);
    Post postShareChildren = PostResourceIT.createEntity(em);
    em.persist(postShareChildren);
    em.flush();
    post.addPostShareChildren(postShareChildren);
    postRepository.saveAndFlush(post);
    Long postShareChildrenId = postShareChildren.getId();

    // Get all the postList where postShareChildren equals to postShareChildrenId
    defaultPostShouldBeFound("postShareChildrenId.equals=" + postShareChildrenId);

    // Get all the postList where postShareChildren equals to (postShareChildrenId + 1)
    defaultPostShouldNotBeFound("postShareChildrenId.equals=" + (postShareChildrenId + 1));
  }

  @Test
  @Transactional
  void getAllPostsByGroupPostIsEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);
    GroupPost groupPost = GroupPostResourceIT.createEntity(em);
    em.persist(groupPost);
    em.flush();
    post.setGroupPost(groupPost);
    postRepository.saveAndFlush(post);
    Long groupPostId = groupPost.getId();

    // Get all the postList where groupPost equals to groupPostId
    defaultPostShouldBeFound("groupPostId.equals=" + groupPostId);

    // Get all the postList where groupPost equals to (groupPostId + 1)
    defaultPostShouldNotBeFound("groupPostId.equals=" + (groupPostId + 1));
  }

  @Test
  @Transactional
  void getAllPostsByPagePostIsEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);
    PagePost pagePost = PagePostResourceIT.createEntity(em);
    em.persist(pagePost);
    em.flush();
    post.setPagePost(pagePost);
    postRepository.saveAndFlush(post);
    Long pagePostId = pagePost.getId();

    // Get all the postList where pagePost equals to pagePostId
    defaultPostShouldBeFound("pagePostId.equals=" + pagePostId);

    // Get all the postList where pagePost equals to (pagePostId + 1)
    defaultPostShouldNotBeFound("pagePostId.equals=" + (pagePostId + 1));
  }

  @Test
  @Transactional
  void getAllPostsByPostShareParentIsEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);
    Post postShareParent = PostResourceIT.createEntity(em);
    em.persist(postShareParent);
    em.flush();
    post.setPostShareParent(postShareParent);
    postRepository.saveAndFlush(post);
    Long postShareParentId = postShareParent.getId();

    // Get all the postList where postShareParent equals to postShareParentId
    defaultPostShouldBeFound("postShareParentId.equals=" + postShareParentId);

    // Get all the postList where postShareParent equals to (postShareParentId + 1)
    defaultPostShouldNotBeFound("postShareParentId.equals=" + (postShareParentId + 1));
  }

  @Test
  @Transactional
  void getAllPostsByPosterIsEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);
    MasterUser poster = MasterUserResourceIT.createEntity(em);
    em.persist(poster);
    em.flush();
    post.setPoster(poster);
    postRepository.saveAndFlush(post);
    Long posterId = poster.getId();

    // Get all the postList where poster equals to posterId
    defaultPostShouldBeFound("posterId.equals=" + posterId);

    // Get all the postList where poster equals to (posterId + 1)
    defaultPostShouldNotBeFound("posterId.equals=" + (posterId + 1));
  }

  @Test
  @Transactional
  void getAllPostsByNewsFeedIsEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);
    NewsFeed newsFeed = NewsFeedResourceIT.createEntity(em);
    em.persist(newsFeed);
    em.flush();
    post.addNewsFeed(newsFeed);
    postRepository.saveAndFlush(post);
    Long newsFeedId = newsFeed.getId();

    // Get all the postList where newsFeed equals to newsFeedId
    defaultPostShouldBeFound("newsFeedId.equals=" + newsFeedId);

    // Get all the postList where newsFeed equals to (newsFeedId + 1)
    defaultPostShouldNotBeFound("newsFeedId.equals=" + (newsFeedId + 1));
  }

  @Test
  @Transactional
  void getAllPostsByTopicInterestIsEqualToSomething() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);
    TopicInterest topicInterest = TopicInterestResourceIT.createEntity(em);
    em.persist(topicInterest);
    em.flush();
    post.addTopicInterest(topicInterest);
    postRepository.saveAndFlush(post);
    Long topicInterestId = topicInterest.getId();

    // Get all the postList where topicInterest equals to topicInterestId
    defaultPostShouldBeFound("topicInterestId.equals=" + topicInterestId);

    // Get all the postList where topicInterest equals to (topicInterestId + 1)
    defaultPostShouldNotBeFound("topicInterestId.equals=" + (topicInterestId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultPostShouldBeFound(String filter) throws Exception {
    restPostMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(post.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].postInType").value(hasItem(DEFAULT_POST_IN_TYPE.toString())))
      .andExpect(jsonPath("$.[*].postType").value(hasItem(DEFAULT_POST_TYPE.toString())))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
      .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())));

    // Check, that the count call also returns 1
    restPostMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultPostShouldNotBeFound(String filter) throws Exception {
    restPostMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restPostMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingPost() throws Exception {
    // Get the post
    restPostMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewPost() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    int databaseSizeBeforeUpdate = postRepository.findAll().size();

    // Update the post
    Post updatedPost = postRepository.findById(post.getId()).get();
    // Disconnect from session so that the updates on updatedPost are not directly saved in db
    em.detach(updatedPost);
    updatedPost
      .uuid(UPDATED_UUID)
      .postInType(UPDATED_POST_IN_TYPE)
      .postType(UPDATED_POST_TYPE)
      .content(UPDATED_CONTENT)
      .searchField(UPDATED_SEARCH_FIELD);
    PostDTO postDTO = postMapper.toDto(updatedPost);

    restPostMockMvc
      .perform(
        put(ENTITY_API_URL_ID, postDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postDTO))
      )
      .andExpect(status().isOk());

    // Validate the Post in the database
    List<Post> postList = postRepository.findAll();
    assertThat(postList).hasSize(databaseSizeBeforeUpdate);
    Post testPost = postList.get(postList.size() - 1);
    assertThat(testPost.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testPost.getPostInType()).isEqualTo(UPDATED_POST_IN_TYPE);
    assertThat(testPost.getPostType()).isEqualTo(UPDATED_POST_TYPE);
    assertThat(testPost.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testPost.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);

    // Validate the Post in Elasticsearch
    verify(mockPostSearchRepository).save(testPost);
  }

  @Test
  @Transactional
  void putNonExistingPost() throws Exception {
    int databaseSizeBeforeUpdate = postRepository.findAll().size();
    post.setId(count.incrementAndGet());

    // Create the Post
    PostDTO postDTO = postMapper.toDto(post);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restPostMockMvc
      .perform(
        put(ENTITY_API_URL_ID, postDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Post in the database
    List<Post> postList = postRepository.findAll();
    assertThat(postList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Post in Elasticsearch
    verify(mockPostSearchRepository, times(0)).save(post);
  }

  @Test
  @Transactional
  void putWithIdMismatchPost() throws Exception {
    int databaseSizeBeforeUpdate = postRepository.findAll().size();
    post.setId(count.incrementAndGet());

    // Create the Post
    PostDTO postDTO = postMapper.toDto(post);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPostMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(postDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Post in the database
    List<Post> postList = postRepository.findAll();
    assertThat(postList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Post in Elasticsearch
    verify(mockPostSearchRepository, times(0)).save(post);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamPost() throws Exception {
    int databaseSizeBeforeUpdate = postRepository.findAll().size();
    post.setId(count.incrementAndGet());

    // Create the Post
    PostDTO postDTO = postMapper.toDto(post);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPostMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Post in the database
    List<Post> postList = postRepository.findAll();
    assertThat(postList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Post in Elasticsearch
    verify(mockPostSearchRepository, times(0)).save(post);
  }

  @Test
  @Transactional
  void partialUpdatePostWithPatch() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    int databaseSizeBeforeUpdate = postRepository.findAll().size();

    // Update the post using partial update
    Post partialUpdatedPost = new Post();
    partialUpdatedPost.setId(post.getId());

    partialUpdatedPost.content(UPDATED_CONTENT);

    restPostMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedPost.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPost))
      )
      .andExpect(status().isOk());

    // Validate the Post in the database
    List<Post> postList = postRepository.findAll();
    assertThat(postList).hasSize(databaseSizeBeforeUpdate);
    Post testPost = postList.get(postList.size() - 1);
    assertThat(testPost.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testPost.getPostInType()).isEqualTo(DEFAULT_POST_IN_TYPE);
    assertThat(testPost.getPostType()).isEqualTo(DEFAULT_POST_TYPE);
    assertThat(testPost.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testPost.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);
  }

  @Test
  @Transactional
  void fullUpdatePostWithPatch() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    int databaseSizeBeforeUpdate = postRepository.findAll().size();

    // Update the post using partial update
    Post partialUpdatedPost = new Post();
    partialUpdatedPost.setId(post.getId());

    partialUpdatedPost
      .uuid(UPDATED_UUID)
      .postInType(UPDATED_POST_IN_TYPE)
      .postType(UPDATED_POST_TYPE)
      .content(UPDATED_CONTENT)
      .searchField(UPDATED_SEARCH_FIELD);

    restPostMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedPost.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPost))
      )
      .andExpect(status().isOk());

    // Validate the Post in the database
    List<Post> postList = postRepository.findAll();
    assertThat(postList).hasSize(databaseSizeBeforeUpdate);
    Post testPost = postList.get(postList.size() - 1);
    assertThat(testPost.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testPost.getPostInType()).isEqualTo(UPDATED_POST_IN_TYPE);
    assertThat(testPost.getPostType()).isEqualTo(UPDATED_POST_TYPE);
    assertThat(testPost.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testPost.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
  }

  @Test
  @Transactional
  void patchNonExistingPost() throws Exception {
    int databaseSizeBeforeUpdate = postRepository.findAll().size();
    post.setId(count.incrementAndGet());

    // Create the Post
    PostDTO postDTO = postMapper.toDto(post);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restPostMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, postDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(postDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Post in the database
    List<Post> postList = postRepository.findAll();
    assertThat(postList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Post in Elasticsearch
    verify(mockPostSearchRepository, times(0)).save(post);
  }

  @Test
  @Transactional
  void patchWithIdMismatchPost() throws Exception {
    int databaseSizeBeforeUpdate = postRepository.findAll().size();
    post.setId(count.incrementAndGet());

    // Create the Post
    PostDTO postDTO = postMapper.toDto(post);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPostMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(postDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Post in the database
    List<Post> postList = postRepository.findAll();
    assertThat(postList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Post in Elasticsearch
    verify(mockPostSearchRepository, times(0)).save(post);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamPost() throws Exception {
    int databaseSizeBeforeUpdate = postRepository.findAll().size();
    post.setId(count.incrementAndGet());

    // Create the Post
    PostDTO postDTO = postMapper.toDto(post);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPostMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(postDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Post in the database
    List<Post> postList = postRepository.findAll();
    assertThat(postList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Post in Elasticsearch
    verify(mockPostSearchRepository, times(0)).save(post);
  }

  @Test
  @Transactional
  void deletePost() throws Exception {
    // Initialize the database
    postRepository.saveAndFlush(post);

    int databaseSizeBeforeDelete = postRepository.findAll().size();

    // Delete the post
    restPostMockMvc.perform(delete(ENTITY_API_URL_ID, post.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<Post> postList = postRepository.findAll();
    assertThat(postList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the Post in Elasticsearch
    verify(mockPostSearchRepository, times(1)).deleteById(post.getId());
  }

  @Test
  @Transactional
  void searchPost() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    postRepository.saveAndFlush(post);
    when(mockPostSearchRepository.search(queryStringQuery("id:" + post.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(post), PageRequest.of(0, 1), 1));

    // Search the post
    restPostMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + post.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(post.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].postInType").value(hasItem(DEFAULT_POST_IN_TYPE.toString())))
      .andExpect(jsonPath("$.[*].postType").value(hasItem(DEFAULT_POST_TYPE.toString())))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
      .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())));
  }
}
