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
import com.regitiny.catiny.domain.Event;
import com.regitiny.catiny.domain.FileInfo;
import com.regitiny.catiny.domain.Video;
import com.regitiny.catiny.domain.Video;
import com.regitiny.catiny.domain.VideoStream;
import com.regitiny.catiny.repository.VideoRepository;
import com.regitiny.catiny.repository.search.VideoSearchRepository;
import com.regitiny.catiny.service.criteria.VideoCriteria;
import com.regitiny.catiny.service.dto.VideoDTO;
import com.regitiny.catiny.service.mapper.VideoMapper;
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
 * Integration tests for the {@link VideoResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class VideoResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/videos";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/videos";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private VideoRepository videoRepository;

  @Autowired
  private VideoMapper videoMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.VideoSearchRepositoryMockConfiguration
   */
  @Autowired
  private VideoSearchRepository mockVideoSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restVideoMockMvc;

  private Video video;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Video createEntity(EntityManager em) {
    Video video = new Video().uuid(DEFAULT_UUID).name(DEFAULT_NAME);
    return video;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Video createUpdatedEntity(EntityManager em) {
    Video video = new Video().uuid(UPDATED_UUID).name(UPDATED_NAME);
    return video;
  }

  @BeforeEach
  public void initTest() {
    video = createEntity(em);
  }

  @Test
  @Transactional
  void createVideo() throws Exception {
    int databaseSizeBeforeCreate = videoRepository.findAll().size();
    // Create the Video
    VideoDTO videoDTO = videoMapper.toDto(video);
    restVideoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(videoDTO)))
      .andExpect(status().isCreated());

    // Validate the Video in the database
    List<Video> videoList = videoRepository.findAll();
    assertThat(videoList).hasSize(databaseSizeBeforeCreate + 1);
    Video testVideo = videoList.get(videoList.size() - 1);
    assertThat(testVideo.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testVideo.getName()).isEqualTo(DEFAULT_NAME);

    // Validate the Video in Elasticsearch
    verify(mockVideoSearchRepository, times(1)).save(testVideo);
  }

  @Test
  @Transactional
  void createVideoWithExistingId() throws Exception {
    // Create the Video with an existing ID
    video.setId(1L);
    VideoDTO videoDTO = videoMapper.toDto(video);

    int databaseSizeBeforeCreate = videoRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restVideoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(videoDTO)))
      .andExpect(status().isBadRequest());

    // Validate the Video in the database
    List<Video> videoList = videoRepository.findAll();
    assertThat(videoList).hasSize(databaseSizeBeforeCreate);

    // Validate the Video in Elasticsearch
    verify(mockVideoSearchRepository, times(0)).save(video);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = videoRepository.findAll().size();
    // set the field null
    video.setUuid(null);

    // Create the Video, which fails.
    VideoDTO videoDTO = videoMapper.toDto(video);

    restVideoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(videoDTO)))
      .andExpect(status().isBadRequest());

    List<Video> videoList = videoRepository.findAll();
    assertThat(videoList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllVideos() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList
    restVideoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(video.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
  }

  @Test
  @Transactional
  void getVideo() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get the video
    restVideoMockMvc
      .perform(get(ENTITY_API_URL_ID, video.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(video.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
  }

  @Test
  @Transactional
  void getVideosByIdFiltering() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    Long id = video.getId();

    defaultVideoShouldBeFound("id.equals=" + id);
    defaultVideoShouldNotBeFound("id.notEquals=" + id);

    defaultVideoShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultVideoShouldNotBeFound("id.greaterThan=" + id);

    defaultVideoShouldBeFound("id.lessThanOrEqual=" + id);
    defaultVideoShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllVideosByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where uuid equals to DEFAULT_UUID
    defaultVideoShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the videoList where uuid equals to UPDATED_UUID
    defaultVideoShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllVideosByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where uuid not equals to DEFAULT_UUID
    defaultVideoShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the videoList where uuid not equals to UPDATED_UUID
    defaultVideoShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllVideosByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultVideoShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the videoList where uuid equals to UPDATED_UUID
    defaultVideoShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllVideosByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where uuid is not null
    defaultVideoShouldBeFound("uuid.specified=true");

    // Get all the videoList where uuid is null
    defaultVideoShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllVideosByNameIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where name equals to DEFAULT_NAME
    defaultVideoShouldBeFound("name.equals=" + DEFAULT_NAME);

    // Get all the videoList where name equals to UPDATED_NAME
    defaultVideoShouldNotBeFound("name.equals=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllVideosByNameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where name not equals to DEFAULT_NAME
    defaultVideoShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

    // Get all the videoList where name not equals to UPDATED_NAME
    defaultVideoShouldBeFound("name.notEquals=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllVideosByNameIsInShouldWork() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where name in DEFAULT_NAME or UPDATED_NAME
    defaultVideoShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

    // Get all the videoList where name equals to UPDATED_NAME
    defaultVideoShouldNotBeFound("name.in=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllVideosByNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where name is not null
    defaultVideoShouldBeFound("name.specified=true");

    // Get all the videoList where name is null
    defaultVideoShouldNotBeFound("name.specified=false");
  }

  @Test
  @Transactional
  void getAllVideosByNameContainsSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where name contains DEFAULT_NAME
    defaultVideoShouldBeFound("name.contains=" + DEFAULT_NAME);

    // Get all the videoList where name contains UPDATED_NAME
    defaultVideoShouldNotBeFound("name.contains=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllVideosByNameNotContainsSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where name does not contain DEFAULT_NAME
    defaultVideoShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

    // Get all the videoList where name does not contain UPDATED_NAME
    defaultVideoShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllVideosByFileInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);
    FileInfo fileInfo = FileInfoResourceIT.createEntity(em);
    em.persist(fileInfo);
    em.flush();
    video.setFileInfo(fileInfo);
    videoRepository.saveAndFlush(video);
    Long fileInfoId = fileInfo.getId();

    // Get all the videoList where fileInfo equals to fileInfoId
    defaultVideoShouldBeFound("fileInfoId.equals=" + fileInfoId);

    // Get all the videoList where fileInfo equals to (fileInfoId + 1)
    defaultVideoShouldNotBeFound("fileInfoId.equals=" + (fileInfoId + 1));
  }

  @Test
  @Transactional
  void getAllVideosByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    video.setBaseInfo(baseInfo);
    videoRepository.saveAndFlush(video);
    Long baseInfoId = baseInfo.getId();

    // Get all the videoList where baseInfo equals to baseInfoId
    defaultVideoShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the videoList where baseInfo equals to (baseInfoId + 1)
    defaultVideoShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllVideosByVideoProcessedIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);
    Video videoProcessed = VideoResourceIT.createEntity(em);
    em.persist(videoProcessed);
    em.flush();
    video.addVideoProcessed(videoProcessed);
    videoRepository.saveAndFlush(video);
    Long videoProcessedId = videoProcessed.getId();

    // Get all the videoList where videoProcessed equals to videoProcessedId
    defaultVideoShouldBeFound("videoProcessedId.equals=" + videoProcessedId);

    // Get all the videoList where videoProcessed equals to (videoProcessedId + 1)
    defaultVideoShouldNotBeFound("videoProcessedId.equals=" + (videoProcessedId + 1));
  }

  @Test
  @Transactional
  void getAllVideosByVideoStreamIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);
    VideoStream videoStream = VideoStreamResourceIT.createEntity(em);
    em.persist(videoStream);
    em.flush();
    video.setVideoStream(videoStream);
    videoStream.setVideo(video);
    videoRepository.saveAndFlush(video);
    Long videoStreamId = videoStream.getId();

    // Get all the videoList where videoStream equals to videoStreamId
    defaultVideoShouldBeFound("videoStreamId.equals=" + videoStreamId);

    // Get all the videoList where videoStream equals to (videoStreamId + 1)
    defaultVideoShouldNotBeFound("videoStreamId.equals=" + (videoStreamId + 1));
  }

  @Test
  @Transactional
  void getAllVideosByVideoOriginalIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);
    Video videoOriginal = VideoResourceIT.createEntity(em);
    em.persist(videoOriginal);
    em.flush();
    video.setVideoOriginal(videoOriginal);
    videoRepository.saveAndFlush(video);
    Long videoOriginalId = videoOriginal.getId();

    // Get all the videoList where videoOriginal equals to videoOriginalId
    defaultVideoShouldBeFound("videoOriginalId.equals=" + videoOriginalId);

    // Get all the videoList where videoOriginal equals to (videoOriginalId + 1)
    defaultVideoShouldNotBeFound("videoOriginalId.equals=" + (videoOriginalId + 1));
  }

  @Test
  @Transactional
  void getAllVideosByEventIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);
    Event event = EventResourceIT.createEntity(em);
    em.persist(event);
    em.flush();
    video.setEvent(event);
    videoRepository.saveAndFlush(video);
    Long eventId = event.getId();

    // Get all the videoList where event equals to eventId
    defaultVideoShouldBeFound("eventId.equals=" + eventId);

    // Get all the videoList where event equals to (eventId + 1)
    defaultVideoShouldNotBeFound("eventId.equals=" + (eventId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultVideoShouldBeFound(String filter) throws Exception {
    restVideoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(video.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));

    // Check, that the count call also returns 1
    restVideoMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultVideoShouldNotBeFound(String filter) throws Exception {
    restVideoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restVideoMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingVideo() throws Exception {
    // Get the video
    restVideoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewVideo() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    int databaseSizeBeforeUpdate = videoRepository.findAll().size();

    // Update the video
    Video updatedVideo = videoRepository.findById(video.getId()).get();
    // Disconnect from session so that the updates on updatedVideo are not directly saved in db
    em.detach(updatedVideo);
    updatedVideo.uuid(UPDATED_UUID).name(UPDATED_NAME);
    VideoDTO videoDTO = videoMapper.toDto(updatedVideo);

    restVideoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, videoDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(videoDTO))
      )
      .andExpect(status().isOk());

    // Validate the Video in the database
    List<Video> videoList = videoRepository.findAll();
    assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    Video testVideo = videoList.get(videoList.size() - 1);
    assertThat(testVideo.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testVideo.getName()).isEqualTo(UPDATED_NAME);

    // Validate the Video in Elasticsearch
    verify(mockVideoSearchRepository).save(testVideo);
  }

  @Test
  @Transactional
  void putNonExistingVideo() throws Exception {
    int databaseSizeBeforeUpdate = videoRepository.findAll().size();
    video.setId(count.incrementAndGet());

    // Create the Video
    VideoDTO videoDTO = videoMapper.toDto(video);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restVideoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, videoDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(videoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Video in the database
    List<Video> videoList = videoRepository.findAll();
    assertThat(videoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Video in Elasticsearch
    verify(mockVideoSearchRepository, times(0)).save(video);
  }

  @Test
  @Transactional
  void putWithIdMismatchVideo() throws Exception {
    int databaseSizeBeforeUpdate = videoRepository.findAll().size();
    video.setId(count.incrementAndGet());

    // Create the Video
    VideoDTO videoDTO = videoMapper.toDto(video);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restVideoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(videoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Video in the database
    List<Video> videoList = videoRepository.findAll();
    assertThat(videoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Video in Elasticsearch
    verify(mockVideoSearchRepository, times(0)).save(video);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamVideo() throws Exception {
    int databaseSizeBeforeUpdate = videoRepository.findAll().size();
    video.setId(count.incrementAndGet());

    // Create the Video
    VideoDTO videoDTO = videoMapper.toDto(video);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restVideoMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(videoDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Video in the database
    List<Video> videoList = videoRepository.findAll();
    assertThat(videoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Video in Elasticsearch
    verify(mockVideoSearchRepository, times(0)).save(video);
  }

  @Test
  @Transactional
  void partialUpdateVideoWithPatch() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    int databaseSizeBeforeUpdate = videoRepository.findAll().size();

    // Update the video using partial update
    Video partialUpdatedVideo = new Video();
    partialUpdatedVideo.setId(video.getId());

    partialUpdatedVideo.name(UPDATED_NAME);

    restVideoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedVideo.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVideo))
      )
      .andExpect(status().isOk());

    // Validate the Video in the database
    List<Video> videoList = videoRepository.findAll();
    assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    Video testVideo = videoList.get(videoList.size() - 1);
    assertThat(testVideo.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testVideo.getName()).isEqualTo(UPDATED_NAME);
  }

  @Test
  @Transactional
  void fullUpdateVideoWithPatch() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    int databaseSizeBeforeUpdate = videoRepository.findAll().size();

    // Update the video using partial update
    Video partialUpdatedVideo = new Video();
    partialUpdatedVideo.setId(video.getId());

    partialUpdatedVideo.uuid(UPDATED_UUID).name(UPDATED_NAME);

    restVideoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedVideo.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVideo))
      )
      .andExpect(status().isOk());

    // Validate the Video in the database
    List<Video> videoList = videoRepository.findAll();
    assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    Video testVideo = videoList.get(videoList.size() - 1);
    assertThat(testVideo.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testVideo.getName()).isEqualTo(UPDATED_NAME);
  }

  @Test
  @Transactional
  void patchNonExistingVideo() throws Exception {
    int databaseSizeBeforeUpdate = videoRepository.findAll().size();
    video.setId(count.incrementAndGet());

    // Create the Video
    VideoDTO videoDTO = videoMapper.toDto(video);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restVideoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, videoDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(videoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Video in the database
    List<Video> videoList = videoRepository.findAll();
    assertThat(videoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Video in Elasticsearch
    verify(mockVideoSearchRepository, times(0)).save(video);
  }

  @Test
  @Transactional
  void patchWithIdMismatchVideo() throws Exception {
    int databaseSizeBeforeUpdate = videoRepository.findAll().size();
    video.setId(count.incrementAndGet());

    // Create the Video
    VideoDTO videoDTO = videoMapper.toDto(video);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restVideoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(videoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Video in the database
    List<Video> videoList = videoRepository.findAll();
    assertThat(videoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Video in Elasticsearch
    verify(mockVideoSearchRepository, times(0)).save(video);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamVideo() throws Exception {
    int databaseSizeBeforeUpdate = videoRepository.findAll().size();
    video.setId(count.incrementAndGet());

    // Create the Video
    VideoDTO videoDTO = videoMapper.toDto(video);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restVideoMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(videoDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Video in the database
    List<Video> videoList = videoRepository.findAll();
    assertThat(videoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Video in Elasticsearch
    verify(mockVideoSearchRepository, times(0)).save(video);
  }

  @Test
  @Transactional
  void deleteVideo() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    int databaseSizeBeforeDelete = videoRepository.findAll().size();

    // Delete the video
    restVideoMockMvc.perform(delete(ENTITY_API_URL_ID, video.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<Video> videoList = videoRepository.findAll();
    assertThat(videoList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the Video in Elasticsearch
    verify(mockVideoSearchRepository, times(1)).deleteById(video.getId());
  }

  @Test
  @Transactional
  void searchVideo() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    videoRepository.saveAndFlush(video);
    when(mockVideoSearchRepository.search(queryStringQuery("id:" + video.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(video), PageRequest.of(0, 1), 1));

    // Search the video
    restVideoMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + video.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(video.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
  }
}
