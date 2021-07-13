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
import com.regitiny.catiny.domain.VideoLiveStreamBuffer;
import com.regitiny.catiny.domain.VideoStream;
import com.regitiny.catiny.repository.VideoLiveStreamBufferRepository;
import com.regitiny.catiny.repository.search.VideoLiveStreamBufferSearchRepository;
import com.regitiny.catiny.service.criteria.VideoLiveStreamBufferCriteria;
import com.regitiny.catiny.service.dto.VideoLiveStreamBufferDTO;
import com.regitiny.catiny.service.mapper.VideoLiveStreamBufferMapper;
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
 * Integration tests for the {@link VideoLiveStreamBufferResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class VideoLiveStreamBufferResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final byte[] DEFAULT_BUFFER_DATA = TestUtil.createByteArray(1, "0");
  private static final byte[] UPDATED_BUFFER_DATA = TestUtil.createByteArray(1, "1");
  private static final String DEFAULT_BUFFER_DATA_CONTENT_TYPE = "image/jpg";
  private static final String UPDATED_BUFFER_DATA_CONTENT_TYPE = "image/png";

  private static final String ENTITY_API_URL = "/api/video-live-stream-buffers";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/video-live-stream-buffers";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private VideoLiveStreamBufferRepository videoLiveStreamBufferRepository;

  @Autowired
  private VideoLiveStreamBufferMapper videoLiveStreamBufferMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.VideoLiveStreamBufferSearchRepositoryMockConfiguration
   */
  @Autowired
  private VideoLiveStreamBufferSearchRepository mockVideoLiveStreamBufferSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restVideoLiveStreamBufferMockMvc;

  private VideoLiveStreamBuffer videoLiveStreamBuffer;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static VideoLiveStreamBuffer createEntity(EntityManager em) {
    VideoLiveStreamBuffer videoLiveStreamBuffer = new VideoLiveStreamBuffer()
      .uuid(DEFAULT_UUID)
      .bufferData(DEFAULT_BUFFER_DATA)
      .bufferDataContentType(DEFAULT_BUFFER_DATA_CONTENT_TYPE);
    return videoLiveStreamBuffer;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static VideoLiveStreamBuffer createUpdatedEntity(EntityManager em) {
    VideoLiveStreamBuffer videoLiveStreamBuffer = new VideoLiveStreamBuffer()
      .uuid(UPDATED_UUID)
      .bufferData(UPDATED_BUFFER_DATA)
      .bufferDataContentType(UPDATED_BUFFER_DATA_CONTENT_TYPE);
    return videoLiveStreamBuffer;
  }

  @BeforeEach
  public void initTest() {
    videoLiveStreamBuffer = createEntity(em);
  }

  @Test
  @Transactional
  void createVideoLiveStreamBuffer() throws Exception {
    int databaseSizeBeforeCreate = videoLiveStreamBufferRepository.findAll().size();
    // Create the VideoLiveStreamBuffer
    VideoLiveStreamBufferDTO videoLiveStreamBufferDTO = videoLiveStreamBufferMapper.toDto(videoLiveStreamBuffer);
    restVideoLiveStreamBufferMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(videoLiveStreamBufferDTO))
      )
      .andExpect(status().isCreated());

    // Validate the VideoLiveStreamBuffer in the database
    List<VideoLiveStreamBuffer> videoLiveStreamBufferList = videoLiveStreamBufferRepository.findAll();
    assertThat(videoLiveStreamBufferList).hasSize(databaseSizeBeforeCreate + 1);
    VideoLiveStreamBuffer testVideoLiveStreamBuffer = videoLiveStreamBufferList.get(videoLiveStreamBufferList.size() - 1);
    assertThat(testVideoLiveStreamBuffer.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testVideoLiveStreamBuffer.getBufferData()).isEqualTo(DEFAULT_BUFFER_DATA);
    assertThat(testVideoLiveStreamBuffer.getBufferDataContentType()).isEqualTo(DEFAULT_BUFFER_DATA_CONTENT_TYPE);

    // Validate the VideoLiveStreamBuffer in Elasticsearch
    verify(mockVideoLiveStreamBufferSearchRepository, times(1)).save(testVideoLiveStreamBuffer);
  }

  @Test
  @Transactional
  void createVideoLiveStreamBufferWithExistingId() throws Exception {
    // Create the VideoLiveStreamBuffer with an existing ID
    videoLiveStreamBuffer.setId(1L);
    VideoLiveStreamBufferDTO videoLiveStreamBufferDTO = videoLiveStreamBufferMapper.toDto(videoLiveStreamBuffer);

    int databaseSizeBeforeCreate = videoLiveStreamBufferRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restVideoLiveStreamBufferMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(videoLiveStreamBufferDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the VideoLiveStreamBuffer in the database
    List<VideoLiveStreamBuffer> videoLiveStreamBufferList = videoLiveStreamBufferRepository.findAll();
    assertThat(videoLiveStreamBufferList).hasSize(databaseSizeBeforeCreate);

    // Validate the VideoLiveStreamBuffer in Elasticsearch
    verify(mockVideoLiveStreamBufferSearchRepository, times(0)).save(videoLiveStreamBuffer);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = videoLiveStreamBufferRepository.findAll().size();
    // set the field null
    videoLiveStreamBuffer.setUuid(null);

    // Create the VideoLiveStreamBuffer, which fails.
    VideoLiveStreamBufferDTO videoLiveStreamBufferDTO = videoLiveStreamBufferMapper.toDto(videoLiveStreamBuffer);

    restVideoLiveStreamBufferMockMvc
      .perform(
        post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(videoLiveStreamBufferDTO))
      )
      .andExpect(status().isBadRequest());

    List<VideoLiveStreamBuffer> videoLiveStreamBufferList = videoLiveStreamBufferRepository.findAll();
    assertThat(videoLiveStreamBufferList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllVideoLiveStreamBuffers() throws Exception {
    // Initialize the database
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);

    // Get all the videoLiveStreamBufferList
    restVideoLiveStreamBufferMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(videoLiveStreamBuffer.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].bufferDataContentType").value(hasItem(DEFAULT_BUFFER_DATA_CONTENT_TYPE)))
      .andExpect(jsonPath("$.[*].bufferData").value(hasItem(Base64Utils.encodeToString(DEFAULT_BUFFER_DATA))));
  }

  @Test
  @Transactional
  void getVideoLiveStreamBuffer() throws Exception {
    // Initialize the database
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);

    // Get the videoLiveStreamBuffer
    restVideoLiveStreamBufferMockMvc
      .perform(get(ENTITY_API_URL_ID, videoLiveStreamBuffer.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(videoLiveStreamBuffer.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.bufferDataContentType").value(DEFAULT_BUFFER_DATA_CONTENT_TYPE))
      .andExpect(jsonPath("$.bufferData").value(Base64Utils.encodeToString(DEFAULT_BUFFER_DATA)));
  }

  @Test
  @Transactional
  void getVideoLiveStreamBuffersByIdFiltering() throws Exception {
    // Initialize the database
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);

    Long id = videoLiveStreamBuffer.getId();

    defaultVideoLiveStreamBufferShouldBeFound("id.equals=" + id);
    defaultVideoLiveStreamBufferShouldNotBeFound("id.notEquals=" + id);

    defaultVideoLiveStreamBufferShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultVideoLiveStreamBufferShouldNotBeFound("id.greaterThan=" + id);

    defaultVideoLiveStreamBufferShouldBeFound("id.lessThanOrEqual=" + id);
    defaultVideoLiveStreamBufferShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllVideoLiveStreamBuffersByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);

    // Get all the videoLiveStreamBufferList where uuid equals to DEFAULT_UUID
    defaultVideoLiveStreamBufferShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the videoLiveStreamBufferList where uuid equals to UPDATED_UUID
    defaultVideoLiveStreamBufferShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllVideoLiveStreamBuffersByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);

    // Get all the videoLiveStreamBufferList where uuid not equals to DEFAULT_UUID
    defaultVideoLiveStreamBufferShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the videoLiveStreamBufferList where uuid not equals to UPDATED_UUID
    defaultVideoLiveStreamBufferShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllVideoLiveStreamBuffersByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);

    // Get all the videoLiveStreamBufferList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultVideoLiveStreamBufferShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the videoLiveStreamBufferList where uuid equals to UPDATED_UUID
    defaultVideoLiveStreamBufferShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllVideoLiveStreamBuffersByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);

    // Get all the videoLiveStreamBufferList where uuid is not null
    defaultVideoLiveStreamBufferShouldBeFound("uuid.specified=true");

    // Get all the videoLiveStreamBufferList where uuid is null
    defaultVideoLiveStreamBufferShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllVideoLiveStreamBuffersByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    videoLiveStreamBuffer.setBaseInfo(baseInfo);
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);
    Long baseInfoId = baseInfo.getId();

    // Get all the videoLiveStreamBufferList where baseInfo equals to baseInfoId
    defaultVideoLiveStreamBufferShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the videoLiveStreamBufferList where baseInfo equals to (baseInfoId + 1)
    defaultVideoLiveStreamBufferShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllVideoLiveStreamBuffersByVideoStreamIsEqualToSomething() throws Exception {
    // Initialize the database
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);
    VideoStream videoStream = VideoStreamResourceIT.createEntity(em);
    em.persist(videoStream);
    em.flush();
    videoLiveStreamBuffer.setVideoStream(videoStream);
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);
    Long videoStreamId = videoStream.getId();

    // Get all the videoLiveStreamBufferList where videoStream equals to videoStreamId
    defaultVideoLiveStreamBufferShouldBeFound("videoStreamId.equals=" + videoStreamId);

    // Get all the videoLiveStreamBufferList where videoStream equals to (videoStreamId + 1)
    defaultVideoLiveStreamBufferShouldNotBeFound("videoStreamId.equals=" + (videoStreamId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultVideoLiveStreamBufferShouldBeFound(String filter) throws Exception {
    restVideoLiveStreamBufferMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(videoLiveStreamBuffer.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].bufferDataContentType").value(hasItem(DEFAULT_BUFFER_DATA_CONTENT_TYPE)))
      .andExpect(jsonPath("$.[*].bufferData").value(hasItem(Base64Utils.encodeToString(DEFAULT_BUFFER_DATA))));

    // Check, that the count call also returns 1
    restVideoLiveStreamBufferMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultVideoLiveStreamBufferShouldNotBeFound(String filter) throws Exception {
    restVideoLiveStreamBufferMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restVideoLiveStreamBufferMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingVideoLiveStreamBuffer() throws Exception {
    // Get the videoLiveStreamBuffer
    restVideoLiveStreamBufferMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewVideoLiveStreamBuffer() throws Exception {
    // Initialize the database
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);

    int databaseSizeBeforeUpdate = videoLiveStreamBufferRepository.findAll().size();

    // Update the videoLiveStreamBuffer
    VideoLiveStreamBuffer updatedVideoLiveStreamBuffer = videoLiveStreamBufferRepository.findById(videoLiveStreamBuffer.getId()).get();
    // Disconnect from session so that the updates on updatedVideoLiveStreamBuffer are not directly saved in db
    em.detach(updatedVideoLiveStreamBuffer);
    updatedVideoLiveStreamBuffer.uuid(UPDATED_UUID).bufferData(UPDATED_BUFFER_DATA).bufferDataContentType(UPDATED_BUFFER_DATA_CONTENT_TYPE);
    VideoLiveStreamBufferDTO videoLiveStreamBufferDTO = videoLiveStreamBufferMapper.toDto(updatedVideoLiveStreamBuffer);

    restVideoLiveStreamBufferMockMvc
      .perform(
        put(ENTITY_API_URL_ID, videoLiveStreamBufferDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(videoLiveStreamBufferDTO))
      )
      .andExpect(status().isOk());

    // Validate the VideoLiveStreamBuffer in the database
    List<VideoLiveStreamBuffer> videoLiveStreamBufferList = videoLiveStreamBufferRepository.findAll();
    assertThat(videoLiveStreamBufferList).hasSize(databaseSizeBeforeUpdate);
    VideoLiveStreamBuffer testVideoLiveStreamBuffer = videoLiveStreamBufferList.get(videoLiveStreamBufferList.size() - 1);
    assertThat(testVideoLiveStreamBuffer.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testVideoLiveStreamBuffer.getBufferData()).isEqualTo(UPDATED_BUFFER_DATA);
    assertThat(testVideoLiveStreamBuffer.getBufferDataContentType()).isEqualTo(UPDATED_BUFFER_DATA_CONTENT_TYPE);

    // Validate the VideoLiveStreamBuffer in Elasticsearch
    verify(mockVideoLiveStreamBufferSearchRepository).save(testVideoLiveStreamBuffer);
  }

  @Test
  @Transactional
  void putNonExistingVideoLiveStreamBuffer() throws Exception {
    int databaseSizeBeforeUpdate = videoLiveStreamBufferRepository.findAll().size();
    videoLiveStreamBuffer.setId(count.incrementAndGet());

    // Create the VideoLiveStreamBuffer
    VideoLiveStreamBufferDTO videoLiveStreamBufferDTO = videoLiveStreamBufferMapper.toDto(videoLiveStreamBuffer);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restVideoLiveStreamBufferMockMvc
      .perform(
        put(ENTITY_API_URL_ID, videoLiveStreamBufferDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(videoLiveStreamBufferDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the VideoLiveStreamBuffer in the database
    List<VideoLiveStreamBuffer> videoLiveStreamBufferList = videoLiveStreamBufferRepository.findAll();
    assertThat(videoLiveStreamBufferList).hasSize(databaseSizeBeforeUpdate);

    // Validate the VideoLiveStreamBuffer in Elasticsearch
    verify(mockVideoLiveStreamBufferSearchRepository, times(0)).save(videoLiveStreamBuffer);
  }

  @Test
  @Transactional
  void putWithIdMismatchVideoLiveStreamBuffer() throws Exception {
    int databaseSizeBeforeUpdate = videoLiveStreamBufferRepository.findAll().size();
    videoLiveStreamBuffer.setId(count.incrementAndGet());

    // Create the VideoLiveStreamBuffer
    VideoLiveStreamBufferDTO videoLiveStreamBufferDTO = videoLiveStreamBufferMapper.toDto(videoLiveStreamBuffer);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restVideoLiveStreamBufferMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(videoLiveStreamBufferDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the VideoLiveStreamBuffer in the database
    List<VideoLiveStreamBuffer> videoLiveStreamBufferList = videoLiveStreamBufferRepository.findAll();
    assertThat(videoLiveStreamBufferList).hasSize(databaseSizeBeforeUpdate);

    // Validate the VideoLiveStreamBuffer in Elasticsearch
    verify(mockVideoLiveStreamBufferSearchRepository, times(0)).save(videoLiveStreamBuffer);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamVideoLiveStreamBuffer() throws Exception {
    int databaseSizeBeforeUpdate = videoLiveStreamBufferRepository.findAll().size();
    videoLiveStreamBuffer.setId(count.incrementAndGet());

    // Create the VideoLiveStreamBuffer
    VideoLiveStreamBufferDTO videoLiveStreamBufferDTO = videoLiveStreamBufferMapper.toDto(videoLiveStreamBuffer);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restVideoLiveStreamBufferMockMvc
      .perform(
        put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(videoLiveStreamBufferDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the VideoLiveStreamBuffer in the database
    List<VideoLiveStreamBuffer> videoLiveStreamBufferList = videoLiveStreamBufferRepository.findAll();
    assertThat(videoLiveStreamBufferList).hasSize(databaseSizeBeforeUpdate);

    // Validate the VideoLiveStreamBuffer in Elasticsearch
    verify(mockVideoLiveStreamBufferSearchRepository, times(0)).save(videoLiveStreamBuffer);
  }

  @Test
  @Transactional
  void partialUpdateVideoLiveStreamBufferWithPatch() throws Exception {
    // Initialize the database
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);

    int databaseSizeBeforeUpdate = videoLiveStreamBufferRepository.findAll().size();

    // Update the videoLiveStreamBuffer using partial update
    VideoLiveStreamBuffer partialUpdatedVideoLiveStreamBuffer = new VideoLiveStreamBuffer();
    partialUpdatedVideoLiveStreamBuffer.setId(videoLiveStreamBuffer.getId());

    restVideoLiveStreamBufferMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedVideoLiveStreamBuffer.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVideoLiveStreamBuffer))
      )
      .andExpect(status().isOk());

    // Validate the VideoLiveStreamBuffer in the database
    List<VideoLiveStreamBuffer> videoLiveStreamBufferList = videoLiveStreamBufferRepository.findAll();
    assertThat(videoLiveStreamBufferList).hasSize(databaseSizeBeforeUpdate);
    VideoLiveStreamBuffer testVideoLiveStreamBuffer = videoLiveStreamBufferList.get(videoLiveStreamBufferList.size() - 1);
    assertThat(testVideoLiveStreamBuffer.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testVideoLiveStreamBuffer.getBufferData()).isEqualTo(DEFAULT_BUFFER_DATA);
    assertThat(testVideoLiveStreamBuffer.getBufferDataContentType()).isEqualTo(DEFAULT_BUFFER_DATA_CONTENT_TYPE);
  }

  @Test
  @Transactional
  void fullUpdateVideoLiveStreamBufferWithPatch() throws Exception {
    // Initialize the database
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);

    int databaseSizeBeforeUpdate = videoLiveStreamBufferRepository.findAll().size();

    // Update the videoLiveStreamBuffer using partial update
    VideoLiveStreamBuffer partialUpdatedVideoLiveStreamBuffer = new VideoLiveStreamBuffer();
    partialUpdatedVideoLiveStreamBuffer.setId(videoLiveStreamBuffer.getId());

    partialUpdatedVideoLiveStreamBuffer
      .uuid(UPDATED_UUID)
      .bufferData(UPDATED_BUFFER_DATA)
      .bufferDataContentType(UPDATED_BUFFER_DATA_CONTENT_TYPE);

    restVideoLiveStreamBufferMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedVideoLiveStreamBuffer.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVideoLiveStreamBuffer))
      )
      .andExpect(status().isOk());

    // Validate the VideoLiveStreamBuffer in the database
    List<VideoLiveStreamBuffer> videoLiveStreamBufferList = videoLiveStreamBufferRepository.findAll();
    assertThat(videoLiveStreamBufferList).hasSize(databaseSizeBeforeUpdate);
    VideoLiveStreamBuffer testVideoLiveStreamBuffer = videoLiveStreamBufferList.get(videoLiveStreamBufferList.size() - 1);
    assertThat(testVideoLiveStreamBuffer.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testVideoLiveStreamBuffer.getBufferData()).isEqualTo(UPDATED_BUFFER_DATA);
    assertThat(testVideoLiveStreamBuffer.getBufferDataContentType()).isEqualTo(UPDATED_BUFFER_DATA_CONTENT_TYPE);
  }

  @Test
  @Transactional
  void patchNonExistingVideoLiveStreamBuffer() throws Exception {
    int databaseSizeBeforeUpdate = videoLiveStreamBufferRepository.findAll().size();
    videoLiveStreamBuffer.setId(count.incrementAndGet());

    // Create the VideoLiveStreamBuffer
    VideoLiveStreamBufferDTO videoLiveStreamBufferDTO = videoLiveStreamBufferMapper.toDto(videoLiveStreamBuffer);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restVideoLiveStreamBufferMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, videoLiveStreamBufferDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(videoLiveStreamBufferDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the VideoLiveStreamBuffer in the database
    List<VideoLiveStreamBuffer> videoLiveStreamBufferList = videoLiveStreamBufferRepository.findAll();
    assertThat(videoLiveStreamBufferList).hasSize(databaseSizeBeforeUpdate);

    // Validate the VideoLiveStreamBuffer in Elasticsearch
    verify(mockVideoLiveStreamBufferSearchRepository, times(0)).save(videoLiveStreamBuffer);
  }

  @Test
  @Transactional
  void patchWithIdMismatchVideoLiveStreamBuffer() throws Exception {
    int databaseSizeBeforeUpdate = videoLiveStreamBufferRepository.findAll().size();
    videoLiveStreamBuffer.setId(count.incrementAndGet());

    // Create the VideoLiveStreamBuffer
    VideoLiveStreamBufferDTO videoLiveStreamBufferDTO = videoLiveStreamBufferMapper.toDto(videoLiveStreamBuffer);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restVideoLiveStreamBufferMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(videoLiveStreamBufferDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the VideoLiveStreamBuffer in the database
    List<VideoLiveStreamBuffer> videoLiveStreamBufferList = videoLiveStreamBufferRepository.findAll();
    assertThat(videoLiveStreamBufferList).hasSize(databaseSizeBeforeUpdate);

    // Validate the VideoLiveStreamBuffer in Elasticsearch
    verify(mockVideoLiveStreamBufferSearchRepository, times(0)).save(videoLiveStreamBuffer);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamVideoLiveStreamBuffer() throws Exception {
    int databaseSizeBeforeUpdate = videoLiveStreamBufferRepository.findAll().size();
    videoLiveStreamBuffer.setId(count.incrementAndGet());

    // Create the VideoLiveStreamBuffer
    VideoLiveStreamBufferDTO videoLiveStreamBufferDTO = videoLiveStreamBufferMapper.toDto(videoLiveStreamBuffer);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restVideoLiveStreamBufferMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(videoLiveStreamBufferDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the VideoLiveStreamBuffer in the database
    List<VideoLiveStreamBuffer> videoLiveStreamBufferList = videoLiveStreamBufferRepository.findAll();
    assertThat(videoLiveStreamBufferList).hasSize(databaseSizeBeforeUpdate);

    // Validate the VideoLiveStreamBuffer in Elasticsearch
    verify(mockVideoLiveStreamBufferSearchRepository, times(0)).save(videoLiveStreamBuffer);
  }

  @Test
  @Transactional
  void deleteVideoLiveStreamBuffer() throws Exception {
    // Initialize the database
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);

    int databaseSizeBeforeDelete = videoLiveStreamBufferRepository.findAll().size();

    // Delete the videoLiveStreamBuffer
    restVideoLiveStreamBufferMockMvc
      .perform(delete(ENTITY_API_URL_ID, videoLiveStreamBuffer.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<VideoLiveStreamBuffer> videoLiveStreamBufferList = videoLiveStreamBufferRepository.findAll();
    assertThat(videoLiveStreamBufferList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the VideoLiveStreamBuffer in Elasticsearch
    verify(mockVideoLiveStreamBufferSearchRepository, times(1)).deleteById(videoLiveStreamBuffer.getId());
  }

  @Test
  @Transactional
  void searchVideoLiveStreamBuffer() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    videoLiveStreamBufferRepository.saveAndFlush(videoLiveStreamBuffer);
    when(mockVideoLiveStreamBufferSearchRepository.search(queryStringQuery("id:" + videoLiveStreamBuffer.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(videoLiveStreamBuffer), PageRequest.of(0, 1), 1));

    // Search the videoLiveStreamBuffer
    restVideoLiveStreamBufferMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + videoLiveStreamBuffer.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(videoLiveStreamBuffer.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].bufferDataContentType").value(hasItem(DEFAULT_BUFFER_DATA_CONTENT_TYPE)))
      .andExpect(jsonPath("$.[*].bufferData").value(hasItem(Base64Utils.encodeToString(DEFAULT_BUFFER_DATA))));
  }
}
