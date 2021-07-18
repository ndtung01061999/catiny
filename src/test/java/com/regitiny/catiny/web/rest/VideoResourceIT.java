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

  private static final Integer DEFAULT_WIDTH = 1;
  private static final Integer UPDATED_WIDTH = 2;
  private static final Integer SMALLER_WIDTH = 1 - 1;

  private static final Integer DEFAULT_HEIGHT = 1;
  private static final Integer UPDATED_HEIGHT = 2;
  private static final Integer SMALLER_HEIGHT = 1 - 1;

  private static final Float DEFAULT_QUALITY_IMAGE = 0F;
  private static final Float UPDATED_QUALITY_IMAGE = 1F;
  private static final Float SMALLER_QUALITY_IMAGE = 0F - 1F;

  private static final Float DEFAULT_QUALITY_AUDIO = 0F;
  private static final Float UPDATED_QUALITY_AUDIO = 1F;
  private static final Float SMALLER_QUALITY_AUDIO = 0F - 1F;

  private static final Float DEFAULT_QUALITY = 0F;
  private static final Float UPDATED_QUALITY = 1F;
  private static final Float SMALLER_QUALITY = 0F - 1F;

  private static final Integer DEFAULT_PIXEL_SIZE = 1;
  private static final Integer UPDATED_PIXEL_SIZE = 2;
  private static final Integer SMALLER_PIXEL_SIZE = 1 - 1;

  private static final Long DEFAULT_PRIORITY_INDEX = 1L;
  private static final Long UPDATED_PRIORITY_INDEX = 2L;
  private static final Long SMALLER_PRIORITY_INDEX = 1L - 1L;

  private static final Long DEFAULT_DATA_SIZE = 1L;
  private static final Long UPDATED_DATA_SIZE = 2L;
  private static final Long SMALLER_DATA_SIZE = 1L - 1L;

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
    Video video = new Video()
      .uuid(DEFAULT_UUID)
      .name(DEFAULT_NAME)
      .width(DEFAULT_WIDTH)
      .height(DEFAULT_HEIGHT)
      .qualityImage(DEFAULT_QUALITY_IMAGE)
      .qualityAudio(DEFAULT_QUALITY_AUDIO)
      .quality(DEFAULT_QUALITY)
      .pixelSize(DEFAULT_PIXEL_SIZE)
      .priorityIndex(DEFAULT_PRIORITY_INDEX)
      .dataSize(DEFAULT_DATA_SIZE);
    return video;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Video createUpdatedEntity(EntityManager em) {
    Video video = new Video()
      .uuid(UPDATED_UUID)
      .name(UPDATED_NAME)
      .width(UPDATED_WIDTH)
      .height(UPDATED_HEIGHT)
      .qualityImage(UPDATED_QUALITY_IMAGE)
      .qualityAudio(UPDATED_QUALITY_AUDIO)
      .quality(UPDATED_QUALITY)
      .pixelSize(UPDATED_PIXEL_SIZE)
      .priorityIndex(UPDATED_PRIORITY_INDEX)
      .dataSize(UPDATED_DATA_SIZE);
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
    assertThat(testVideo.getWidth()).isEqualTo(DEFAULT_WIDTH);
    assertThat(testVideo.getHeight()).isEqualTo(DEFAULT_HEIGHT);
    assertThat(testVideo.getQualityImage()).isEqualTo(DEFAULT_QUALITY_IMAGE);
    assertThat(testVideo.getQualityAudio()).isEqualTo(DEFAULT_QUALITY_AUDIO);
    assertThat(testVideo.getQuality()).isEqualTo(DEFAULT_QUALITY);
    assertThat(testVideo.getPixelSize()).isEqualTo(DEFAULT_PIXEL_SIZE);
    assertThat(testVideo.getPriorityIndex()).isEqualTo(DEFAULT_PRIORITY_INDEX);
    assertThat(testVideo.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);

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
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
      .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
      .andExpect(jsonPath("$.[*].qualityImage").value(hasItem(DEFAULT_QUALITY_IMAGE.doubleValue())))
      .andExpect(jsonPath("$.[*].qualityAudio").value(hasItem(DEFAULT_QUALITY_AUDIO.doubleValue())))
      .andExpect(jsonPath("$.[*].quality").value(hasItem(DEFAULT_QUALITY.doubleValue())))
      .andExpect(jsonPath("$.[*].pixelSize").value(hasItem(DEFAULT_PIXEL_SIZE)))
      .andExpect(jsonPath("$.[*].priorityIndex").value(hasItem(DEFAULT_PRIORITY_INDEX.intValue())))
      .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE.intValue())));
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
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
      .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH))
      .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT))
      .andExpect(jsonPath("$.qualityImage").value(DEFAULT_QUALITY_IMAGE.doubleValue()))
      .andExpect(jsonPath("$.qualityAudio").value(DEFAULT_QUALITY_AUDIO.doubleValue()))
      .andExpect(jsonPath("$.quality").value(DEFAULT_QUALITY.doubleValue()))
      .andExpect(jsonPath("$.pixelSize").value(DEFAULT_PIXEL_SIZE))
      .andExpect(jsonPath("$.priorityIndex").value(DEFAULT_PRIORITY_INDEX.intValue()))
      .andExpect(jsonPath("$.dataSize").value(DEFAULT_DATA_SIZE.intValue()));
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
  void getAllVideosByWidthIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where width equals to DEFAULT_WIDTH
    defaultVideoShouldBeFound("width.equals=" + DEFAULT_WIDTH);

    // Get all the videoList where width equals to UPDATED_WIDTH
    defaultVideoShouldNotBeFound("width.equals=" + UPDATED_WIDTH);
  }

  @Test
  @Transactional
  void getAllVideosByWidthIsNotEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where width not equals to DEFAULT_WIDTH
    defaultVideoShouldNotBeFound("width.notEquals=" + DEFAULT_WIDTH);

    // Get all the videoList where width not equals to UPDATED_WIDTH
    defaultVideoShouldBeFound("width.notEquals=" + UPDATED_WIDTH);
  }

  @Test
  @Transactional
  void getAllVideosByWidthIsInShouldWork() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where width in DEFAULT_WIDTH or UPDATED_WIDTH
    defaultVideoShouldBeFound("width.in=" + DEFAULT_WIDTH + "," + UPDATED_WIDTH);

    // Get all the videoList where width equals to UPDATED_WIDTH
    defaultVideoShouldNotBeFound("width.in=" + UPDATED_WIDTH);
  }

  @Test
  @Transactional
  void getAllVideosByWidthIsNullOrNotNull() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where width is not null
    defaultVideoShouldBeFound("width.specified=true");

    // Get all the videoList where width is null
    defaultVideoShouldNotBeFound("width.specified=false");
  }

  @Test
  @Transactional
  void getAllVideosByWidthIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where width is greater than or equal to DEFAULT_WIDTH
    defaultVideoShouldBeFound("width.greaterThanOrEqual=" + DEFAULT_WIDTH);

    // Get all the videoList where width is greater than or equal to UPDATED_WIDTH
    defaultVideoShouldNotBeFound("width.greaterThanOrEqual=" + UPDATED_WIDTH);
  }

  @Test
  @Transactional
  void getAllVideosByWidthIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where width is less than or equal to DEFAULT_WIDTH
    defaultVideoShouldBeFound("width.lessThanOrEqual=" + DEFAULT_WIDTH);

    // Get all the videoList where width is less than or equal to SMALLER_WIDTH
    defaultVideoShouldNotBeFound("width.lessThanOrEqual=" + SMALLER_WIDTH);
  }

  @Test
  @Transactional
  void getAllVideosByWidthIsLessThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where width is less than DEFAULT_WIDTH
    defaultVideoShouldNotBeFound("width.lessThan=" + DEFAULT_WIDTH);

    // Get all the videoList where width is less than UPDATED_WIDTH
    defaultVideoShouldBeFound("width.lessThan=" + UPDATED_WIDTH);
  }

  @Test
  @Transactional
  void getAllVideosByWidthIsGreaterThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where width is greater than DEFAULT_WIDTH
    defaultVideoShouldNotBeFound("width.greaterThan=" + DEFAULT_WIDTH);

    // Get all the videoList where width is greater than SMALLER_WIDTH
    defaultVideoShouldBeFound("width.greaterThan=" + SMALLER_WIDTH);
  }

  @Test
  @Transactional
  void getAllVideosByHeightIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where height equals to DEFAULT_HEIGHT
    defaultVideoShouldBeFound("height.equals=" + DEFAULT_HEIGHT);

    // Get all the videoList where height equals to UPDATED_HEIGHT
    defaultVideoShouldNotBeFound("height.equals=" + UPDATED_HEIGHT);
  }

  @Test
  @Transactional
  void getAllVideosByHeightIsNotEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where height not equals to DEFAULT_HEIGHT
    defaultVideoShouldNotBeFound("height.notEquals=" + DEFAULT_HEIGHT);

    // Get all the videoList where height not equals to UPDATED_HEIGHT
    defaultVideoShouldBeFound("height.notEquals=" + UPDATED_HEIGHT);
  }

  @Test
  @Transactional
  void getAllVideosByHeightIsInShouldWork() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where height in DEFAULT_HEIGHT or UPDATED_HEIGHT
    defaultVideoShouldBeFound("height.in=" + DEFAULT_HEIGHT + "," + UPDATED_HEIGHT);

    // Get all the videoList where height equals to UPDATED_HEIGHT
    defaultVideoShouldNotBeFound("height.in=" + UPDATED_HEIGHT);
  }

  @Test
  @Transactional
  void getAllVideosByHeightIsNullOrNotNull() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where height is not null
    defaultVideoShouldBeFound("height.specified=true");

    // Get all the videoList where height is null
    defaultVideoShouldNotBeFound("height.specified=false");
  }

  @Test
  @Transactional
  void getAllVideosByHeightIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where height is greater than or equal to DEFAULT_HEIGHT
    defaultVideoShouldBeFound("height.greaterThanOrEqual=" + DEFAULT_HEIGHT);

    // Get all the videoList where height is greater than or equal to UPDATED_HEIGHT
    defaultVideoShouldNotBeFound("height.greaterThanOrEqual=" + UPDATED_HEIGHT);
  }

  @Test
  @Transactional
  void getAllVideosByHeightIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where height is less than or equal to DEFAULT_HEIGHT
    defaultVideoShouldBeFound("height.lessThanOrEqual=" + DEFAULT_HEIGHT);

    // Get all the videoList where height is less than or equal to SMALLER_HEIGHT
    defaultVideoShouldNotBeFound("height.lessThanOrEqual=" + SMALLER_HEIGHT);
  }

  @Test
  @Transactional
  void getAllVideosByHeightIsLessThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where height is less than DEFAULT_HEIGHT
    defaultVideoShouldNotBeFound("height.lessThan=" + DEFAULT_HEIGHT);

    // Get all the videoList where height is less than UPDATED_HEIGHT
    defaultVideoShouldBeFound("height.lessThan=" + UPDATED_HEIGHT);
  }

  @Test
  @Transactional
  void getAllVideosByHeightIsGreaterThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where height is greater than DEFAULT_HEIGHT
    defaultVideoShouldNotBeFound("height.greaterThan=" + DEFAULT_HEIGHT);

    // Get all the videoList where height is greater than SMALLER_HEIGHT
    defaultVideoShouldBeFound("height.greaterThan=" + SMALLER_HEIGHT);
  }

  @Test
  @Transactional
  void getAllVideosByQualityImageIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityImage equals to DEFAULT_QUALITY_IMAGE
    defaultVideoShouldBeFound("qualityImage.equals=" + DEFAULT_QUALITY_IMAGE);

    // Get all the videoList where qualityImage equals to UPDATED_QUALITY_IMAGE
    defaultVideoShouldNotBeFound("qualityImage.equals=" + UPDATED_QUALITY_IMAGE);
  }

  @Test
  @Transactional
  void getAllVideosByQualityImageIsNotEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityImage not equals to DEFAULT_QUALITY_IMAGE
    defaultVideoShouldNotBeFound("qualityImage.notEquals=" + DEFAULT_QUALITY_IMAGE);

    // Get all the videoList where qualityImage not equals to UPDATED_QUALITY_IMAGE
    defaultVideoShouldBeFound("qualityImage.notEquals=" + UPDATED_QUALITY_IMAGE);
  }

  @Test
  @Transactional
  void getAllVideosByQualityImageIsInShouldWork() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityImage in DEFAULT_QUALITY_IMAGE or UPDATED_QUALITY_IMAGE
    defaultVideoShouldBeFound("qualityImage.in=" + DEFAULT_QUALITY_IMAGE + "," + UPDATED_QUALITY_IMAGE);

    // Get all the videoList where qualityImage equals to UPDATED_QUALITY_IMAGE
    defaultVideoShouldNotBeFound("qualityImage.in=" + UPDATED_QUALITY_IMAGE);
  }

  @Test
  @Transactional
  void getAllVideosByQualityImageIsNullOrNotNull() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityImage is not null
    defaultVideoShouldBeFound("qualityImage.specified=true");

    // Get all the videoList where qualityImage is null
    defaultVideoShouldNotBeFound("qualityImage.specified=false");
  }

  @Test
  @Transactional
  void getAllVideosByQualityImageIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityImage is greater than or equal to DEFAULT_QUALITY_IMAGE
    defaultVideoShouldBeFound("qualityImage.greaterThanOrEqual=" + DEFAULT_QUALITY_IMAGE);

    // Get all the videoList where qualityImage is greater than or equal to (DEFAULT_QUALITY_IMAGE + 1)
    defaultVideoShouldNotBeFound("qualityImage.greaterThanOrEqual=" + (DEFAULT_QUALITY_IMAGE + 1));
  }

  @Test
  @Transactional
  void getAllVideosByQualityImageIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityImage is less than or equal to DEFAULT_QUALITY_IMAGE
    defaultVideoShouldBeFound("qualityImage.lessThanOrEqual=" + DEFAULT_QUALITY_IMAGE);

    // Get all the videoList where qualityImage is less than or equal to SMALLER_QUALITY_IMAGE
    defaultVideoShouldNotBeFound("qualityImage.lessThanOrEqual=" + SMALLER_QUALITY_IMAGE);
  }

  @Test
  @Transactional
  void getAllVideosByQualityImageIsLessThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityImage is less than DEFAULT_QUALITY_IMAGE
    defaultVideoShouldNotBeFound("qualityImage.lessThan=" + DEFAULT_QUALITY_IMAGE);

    // Get all the videoList where qualityImage is less than (DEFAULT_QUALITY_IMAGE + 1)
    defaultVideoShouldBeFound("qualityImage.lessThan=" + (DEFAULT_QUALITY_IMAGE + 1));
  }

  @Test
  @Transactional
  void getAllVideosByQualityImageIsGreaterThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityImage is greater than DEFAULT_QUALITY_IMAGE
    defaultVideoShouldNotBeFound("qualityImage.greaterThan=" + DEFAULT_QUALITY_IMAGE);

    // Get all the videoList where qualityImage is greater than SMALLER_QUALITY_IMAGE
    defaultVideoShouldBeFound("qualityImage.greaterThan=" + SMALLER_QUALITY_IMAGE);
  }

  @Test
  @Transactional
  void getAllVideosByQualityAudioIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityAudio equals to DEFAULT_QUALITY_AUDIO
    defaultVideoShouldBeFound("qualityAudio.equals=" + DEFAULT_QUALITY_AUDIO);

    // Get all the videoList where qualityAudio equals to UPDATED_QUALITY_AUDIO
    defaultVideoShouldNotBeFound("qualityAudio.equals=" + UPDATED_QUALITY_AUDIO);
  }

  @Test
  @Transactional
  void getAllVideosByQualityAudioIsNotEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityAudio not equals to DEFAULT_QUALITY_AUDIO
    defaultVideoShouldNotBeFound("qualityAudio.notEquals=" + DEFAULT_QUALITY_AUDIO);

    // Get all the videoList where qualityAudio not equals to UPDATED_QUALITY_AUDIO
    defaultVideoShouldBeFound("qualityAudio.notEquals=" + UPDATED_QUALITY_AUDIO);
  }

  @Test
  @Transactional
  void getAllVideosByQualityAudioIsInShouldWork() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityAudio in DEFAULT_QUALITY_AUDIO or UPDATED_QUALITY_AUDIO
    defaultVideoShouldBeFound("qualityAudio.in=" + DEFAULT_QUALITY_AUDIO + "," + UPDATED_QUALITY_AUDIO);

    // Get all the videoList where qualityAudio equals to UPDATED_QUALITY_AUDIO
    defaultVideoShouldNotBeFound("qualityAudio.in=" + UPDATED_QUALITY_AUDIO);
  }

  @Test
  @Transactional
  void getAllVideosByQualityAudioIsNullOrNotNull() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityAudio is not null
    defaultVideoShouldBeFound("qualityAudio.specified=true");

    // Get all the videoList where qualityAudio is null
    defaultVideoShouldNotBeFound("qualityAudio.specified=false");
  }

  @Test
  @Transactional
  void getAllVideosByQualityAudioIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityAudio is greater than or equal to DEFAULT_QUALITY_AUDIO
    defaultVideoShouldBeFound("qualityAudio.greaterThanOrEqual=" + DEFAULT_QUALITY_AUDIO);

    // Get all the videoList where qualityAudio is greater than or equal to (DEFAULT_QUALITY_AUDIO + 1)
    defaultVideoShouldNotBeFound("qualityAudio.greaterThanOrEqual=" + (DEFAULT_QUALITY_AUDIO + 1));
  }

  @Test
  @Transactional
  void getAllVideosByQualityAudioIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityAudio is less than or equal to DEFAULT_QUALITY_AUDIO
    defaultVideoShouldBeFound("qualityAudio.lessThanOrEqual=" + DEFAULT_QUALITY_AUDIO);

    // Get all the videoList where qualityAudio is less than or equal to SMALLER_QUALITY_AUDIO
    defaultVideoShouldNotBeFound("qualityAudio.lessThanOrEqual=" + SMALLER_QUALITY_AUDIO);
  }

  @Test
  @Transactional
  void getAllVideosByQualityAudioIsLessThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityAudio is less than DEFAULT_QUALITY_AUDIO
    defaultVideoShouldNotBeFound("qualityAudio.lessThan=" + DEFAULT_QUALITY_AUDIO);

    // Get all the videoList where qualityAudio is less than (DEFAULT_QUALITY_AUDIO + 1)
    defaultVideoShouldBeFound("qualityAudio.lessThan=" + (DEFAULT_QUALITY_AUDIO + 1));
  }

  @Test
  @Transactional
  void getAllVideosByQualityAudioIsGreaterThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where qualityAudio is greater than DEFAULT_QUALITY_AUDIO
    defaultVideoShouldNotBeFound("qualityAudio.greaterThan=" + DEFAULT_QUALITY_AUDIO);

    // Get all the videoList where qualityAudio is greater than SMALLER_QUALITY_AUDIO
    defaultVideoShouldBeFound("qualityAudio.greaterThan=" + SMALLER_QUALITY_AUDIO);
  }

  @Test
  @Transactional
  void getAllVideosByQualityIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where quality equals to DEFAULT_QUALITY
    defaultVideoShouldBeFound("quality.equals=" + DEFAULT_QUALITY);

    // Get all the videoList where quality equals to UPDATED_QUALITY
    defaultVideoShouldNotBeFound("quality.equals=" + UPDATED_QUALITY);
  }

  @Test
  @Transactional
  void getAllVideosByQualityIsNotEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where quality not equals to DEFAULT_QUALITY
    defaultVideoShouldNotBeFound("quality.notEquals=" + DEFAULT_QUALITY);

    // Get all the videoList where quality not equals to UPDATED_QUALITY
    defaultVideoShouldBeFound("quality.notEquals=" + UPDATED_QUALITY);
  }

  @Test
  @Transactional
  void getAllVideosByQualityIsInShouldWork() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where quality in DEFAULT_QUALITY or UPDATED_QUALITY
    defaultVideoShouldBeFound("quality.in=" + DEFAULT_QUALITY + "," + UPDATED_QUALITY);

    // Get all the videoList where quality equals to UPDATED_QUALITY
    defaultVideoShouldNotBeFound("quality.in=" + UPDATED_QUALITY);
  }

  @Test
  @Transactional
  void getAllVideosByQualityIsNullOrNotNull() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where quality is not null
    defaultVideoShouldBeFound("quality.specified=true");

    // Get all the videoList where quality is null
    defaultVideoShouldNotBeFound("quality.specified=false");
  }

  @Test
  @Transactional
  void getAllVideosByQualityIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where quality is greater than or equal to DEFAULT_QUALITY
    defaultVideoShouldBeFound("quality.greaterThanOrEqual=" + DEFAULT_QUALITY);

    // Get all the videoList where quality is greater than or equal to (DEFAULT_QUALITY + 1)
    defaultVideoShouldNotBeFound("quality.greaterThanOrEqual=" + (DEFAULT_QUALITY + 1));
  }

  @Test
  @Transactional
  void getAllVideosByQualityIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where quality is less than or equal to DEFAULT_QUALITY
    defaultVideoShouldBeFound("quality.lessThanOrEqual=" + DEFAULT_QUALITY);

    // Get all the videoList where quality is less than or equal to SMALLER_QUALITY
    defaultVideoShouldNotBeFound("quality.lessThanOrEqual=" + SMALLER_QUALITY);
  }

  @Test
  @Transactional
  void getAllVideosByQualityIsLessThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where quality is less than DEFAULT_QUALITY
    defaultVideoShouldNotBeFound("quality.lessThan=" + DEFAULT_QUALITY);

    // Get all the videoList where quality is less than (DEFAULT_QUALITY + 1)
    defaultVideoShouldBeFound("quality.lessThan=" + (DEFAULT_QUALITY + 1));
  }

  @Test
  @Transactional
  void getAllVideosByQualityIsGreaterThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where quality is greater than DEFAULT_QUALITY
    defaultVideoShouldNotBeFound("quality.greaterThan=" + DEFAULT_QUALITY);

    // Get all the videoList where quality is greater than SMALLER_QUALITY
    defaultVideoShouldBeFound("quality.greaterThan=" + SMALLER_QUALITY);
  }

  @Test
  @Transactional
  void getAllVideosByPixelSizeIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where pixelSize equals to DEFAULT_PIXEL_SIZE
    defaultVideoShouldBeFound("pixelSize.equals=" + DEFAULT_PIXEL_SIZE);

    // Get all the videoList where pixelSize equals to UPDATED_PIXEL_SIZE
    defaultVideoShouldNotBeFound("pixelSize.equals=" + UPDATED_PIXEL_SIZE);
  }

  @Test
  @Transactional
  void getAllVideosByPixelSizeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where pixelSize not equals to DEFAULT_PIXEL_SIZE
    defaultVideoShouldNotBeFound("pixelSize.notEquals=" + DEFAULT_PIXEL_SIZE);

    // Get all the videoList where pixelSize not equals to UPDATED_PIXEL_SIZE
    defaultVideoShouldBeFound("pixelSize.notEquals=" + UPDATED_PIXEL_SIZE);
  }

  @Test
  @Transactional
  void getAllVideosByPixelSizeIsInShouldWork() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where pixelSize in DEFAULT_PIXEL_SIZE or UPDATED_PIXEL_SIZE
    defaultVideoShouldBeFound("pixelSize.in=" + DEFAULT_PIXEL_SIZE + "," + UPDATED_PIXEL_SIZE);

    // Get all the videoList where pixelSize equals to UPDATED_PIXEL_SIZE
    defaultVideoShouldNotBeFound("pixelSize.in=" + UPDATED_PIXEL_SIZE);
  }

  @Test
  @Transactional
  void getAllVideosByPixelSizeIsNullOrNotNull() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where pixelSize is not null
    defaultVideoShouldBeFound("pixelSize.specified=true");

    // Get all the videoList where pixelSize is null
    defaultVideoShouldNotBeFound("pixelSize.specified=false");
  }

  @Test
  @Transactional
  void getAllVideosByPixelSizeIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where pixelSize is greater than or equal to DEFAULT_PIXEL_SIZE
    defaultVideoShouldBeFound("pixelSize.greaterThanOrEqual=" + DEFAULT_PIXEL_SIZE);

    // Get all the videoList where pixelSize is greater than or equal to UPDATED_PIXEL_SIZE
    defaultVideoShouldNotBeFound("pixelSize.greaterThanOrEqual=" + UPDATED_PIXEL_SIZE);
  }

  @Test
  @Transactional
  void getAllVideosByPixelSizeIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where pixelSize is less than or equal to DEFAULT_PIXEL_SIZE
    defaultVideoShouldBeFound("pixelSize.lessThanOrEqual=" + DEFAULT_PIXEL_SIZE);

    // Get all the videoList where pixelSize is less than or equal to SMALLER_PIXEL_SIZE
    defaultVideoShouldNotBeFound("pixelSize.lessThanOrEqual=" + SMALLER_PIXEL_SIZE);
  }

  @Test
  @Transactional
  void getAllVideosByPixelSizeIsLessThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where pixelSize is less than DEFAULT_PIXEL_SIZE
    defaultVideoShouldNotBeFound("pixelSize.lessThan=" + DEFAULT_PIXEL_SIZE);

    // Get all the videoList where pixelSize is less than UPDATED_PIXEL_SIZE
    defaultVideoShouldBeFound("pixelSize.lessThan=" + UPDATED_PIXEL_SIZE);
  }

  @Test
  @Transactional
  void getAllVideosByPixelSizeIsGreaterThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where pixelSize is greater than DEFAULT_PIXEL_SIZE
    defaultVideoShouldNotBeFound("pixelSize.greaterThan=" + DEFAULT_PIXEL_SIZE);

    // Get all the videoList where pixelSize is greater than SMALLER_PIXEL_SIZE
    defaultVideoShouldBeFound("pixelSize.greaterThan=" + SMALLER_PIXEL_SIZE);
  }

  @Test
  @Transactional
  void getAllVideosByPriorityIndexIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where priorityIndex equals to DEFAULT_PRIORITY_INDEX
    defaultVideoShouldBeFound("priorityIndex.equals=" + DEFAULT_PRIORITY_INDEX);

    // Get all the videoList where priorityIndex equals to UPDATED_PRIORITY_INDEX
    defaultVideoShouldNotBeFound("priorityIndex.equals=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllVideosByPriorityIndexIsNotEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where priorityIndex not equals to DEFAULT_PRIORITY_INDEX
    defaultVideoShouldNotBeFound("priorityIndex.notEquals=" + DEFAULT_PRIORITY_INDEX);

    // Get all the videoList where priorityIndex not equals to UPDATED_PRIORITY_INDEX
    defaultVideoShouldBeFound("priorityIndex.notEquals=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllVideosByPriorityIndexIsInShouldWork() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where priorityIndex in DEFAULT_PRIORITY_INDEX or UPDATED_PRIORITY_INDEX
    defaultVideoShouldBeFound("priorityIndex.in=" + DEFAULT_PRIORITY_INDEX + "," + UPDATED_PRIORITY_INDEX);

    // Get all the videoList where priorityIndex equals to UPDATED_PRIORITY_INDEX
    defaultVideoShouldNotBeFound("priorityIndex.in=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllVideosByPriorityIndexIsNullOrNotNull() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where priorityIndex is not null
    defaultVideoShouldBeFound("priorityIndex.specified=true");

    // Get all the videoList where priorityIndex is null
    defaultVideoShouldNotBeFound("priorityIndex.specified=false");
  }

  @Test
  @Transactional
  void getAllVideosByPriorityIndexIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where priorityIndex is greater than or equal to DEFAULT_PRIORITY_INDEX
    defaultVideoShouldBeFound("priorityIndex.greaterThanOrEqual=" + DEFAULT_PRIORITY_INDEX);

    // Get all the videoList where priorityIndex is greater than or equal to UPDATED_PRIORITY_INDEX
    defaultVideoShouldNotBeFound("priorityIndex.greaterThanOrEqual=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllVideosByPriorityIndexIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where priorityIndex is less than or equal to DEFAULT_PRIORITY_INDEX
    defaultVideoShouldBeFound("priorityIndex.lessThanOrEqual=" + DEFAULT_PRIORITY_INDEX);

    // Get all the videoList where priorityIndex is less than or equal to SMALLER_PRIORITY_INDEX
    defaultVideoShouldNotBeFound("priorityIndex.lessThanOrEqual=" + SMALLER_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllVideosByPriorityIndexIsLessThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where priorityIndex is less than DEFAULT_PRIORITY_INDEX
    defaultVideoShouldNotBeFound("priorityIndex.lessThan=" + DEFAULT_PRIORITY_INDEX);

    // Get all the videoList where priorityIndex is less than UPDATED_PRIORITY_INDEX
    defaultVideoShouldBeFound("priorityIndex.lessThan=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllVideosByPriorityIndexIsGreaterThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where priorityIndex is greater than DEFAULT_PRIORITY_INDEX
    defaultVideoShouldNotBeFound("priorityIndex.greaterThan=" + DEFAULT_PRIORITY_INDEX);

    // Get all the videoList where priorityIndex is greater than SMALLER_PRIORITY_INDEX
    defaultVideoShouldBeFound("priorityIndex.greaterThan=" + SMALLER_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllVideosByDataSizeIsEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where dataSize equals to DEFAULT_DATA_SIZE
    defaultVideoShouldBeFound("dataSize.equals=" + DEFAULT_DATA_SIZE);

    // Get all the videoList where dataSize equals to UPDATED_DATA_SIZE
    defaultVideoShouldNotBeFound("dataSize.equals=" + UPDATED_DATA_SIZE);
  }

  @Test
  @Transactional
  void getAllVideosByDataSizeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where dataSize not equals to DEFAULT_DATA_SIZE
    defaultVideoShouldNotBeFound("dataSize.notEquals=" + DEFAULT_DATA_SIZE);

    // Get all the videoList where dataSize not equals to UPDATED_DATA_SIZE
    defaultVideoShouldBeFound("dataSize.notEquals=" + UPDATED_DATA_SIZE);
  }

  @Test
  @Transactional
  void getAllVideosByDataSizeIsInShouldWork() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where dataSize in DEFAULT_DATA_SIZE or UPDATED_DATA_SIZE
    defaultVideoShouldBeFound("dataSize.in=" + DEFAULT_DATA_SIZE + "," + UPDATED_DATA_SIZE);

    // Get all the videoList where dataSize equals to UPDATED_DATA_SIZE
    defaultVideoShouldNotBeFound("dataSize.in=" + UPDATED_DATA_SIZE);
  }

  @Test
  @Transactional
  void getAllVideosByDataSizeIsNullOrNotNull() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where dataSize is not null
    defaultVideoShouldBeFound("dataSize.specified=true");

    // Get all the videoList where dataSize is null
    defaultVideoShouldNotBeFound("dataSize.specified=false");
  }

  @Test
  @Transactional
  void getAllVideosByDataSizeIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where dataSize is greater than or equal to DEFAULT_DATA_SIZE
    defaultVideoShouldBeFound("dataSize.greaterThanOrEqual=" + DEFAULT_DATA_SIZE);

    // Get all the videoList where dataSize is greater than or equal to UPDATED_DATA_SIZE
    defaultVideoShouldNotBeFound("dataSize.greaterThanOrEqual=" + UPDATED_DATA_SIZE);
  }

  @Test
  @Transactional
  void getAllVideosByDataSizeIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where dataSize is less than or equal to DEFAULT_DATA_SIZE
    defaultVideoShouldBeFound("dataSize.lessThanOrEqual=" + DEFAULT_DATA_SIZE);

    // Get all the videoList where dataSize is less than or equal to SMALLER_DATA_SIZE
    defaultVideoShouldNotBeFound("dataSize.lessThanOrEqual=" + SMALLER_DATA_SIZE);
  }

  @Test
  @Transactional
  void getAllVideosByDataSizeIsLessThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where dataSize is less than DEFAULT_DATA_SIZE
    defaultVideoShouldNotBeFound("dataSize.lessThan=" + DEFAULT_DATA_SIZE);

    // Get all the videoList where dataSize is less than UPDATED_DATA_SIZE
    defaultVideoShouldBeFound("dataSize.lessThan=" + UPDATED_DATA_SIZE);
  }

  @Test
  @Transactional
  void getAllVideosByDataSizeIsGreaterThanSomething() throws Exception {
    // Initialize the database
    videoRepository.saveAndFlush(video);

    // Get all the videoList where dataSize is greater than DEFAULT_DATA_SIZE
    defaultVideoShouldNotBeFound("dataSize.greaterThan=" + DEFAULT_DATA_SIZE);

    // Get all the videoList where dataSize is greater than SMALLER_DATA_SIZE
    defaultVideoShouldBeFound("dataSize.greaterThan=" + SMALLER_DATA_SIZE);
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
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
      .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
      .andExpect(jsonPath("$.[*].qualityImage").value(hasItem(DEFAULT_QUALITY_IMAGE.doubleValue())))
      .andExpect(jsonPath("$.[*].qualityAudio").value(hasItem(DEFAULT_QUALITY_AUDIO.doubleValue())))
      .andExpect(jsonPath("$.[*].quality").value(hasItem(DEFAULT_QUALITY.doubleValue())))
      .andExpect(jsonPath("$.[*].pixelSize").value(hasItem(DEFAULT_PIXEL_SIZE)))
      .andExpect(jsonPath("$.[*].priorityIndex").value(hasItem(DEFAULT_PRIORITY_INDEX.intValue())))
      .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE.intValue())));

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
    updatedVideo
      .uuid(UPDATED_UUID)
      .name(UPDATED_NAME)
      .width(UPDATED_WIDTH)
      .height(UPDATED_HEIGHT)
      .qualityImage(UPDATED_QUALITY_IMAGE)
      .qualityAudio(UPDATED_QUALITY_AUDIO)
      .quality(UPDATED_QUALITY)
      .pixelSize(UPDATED_PIXEL_SIZE)
      .priorityIndex(UPDATED_PRIORITY_INDEX)
      .dataSize(UPDATED_DATA_SIZE);
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
    assertThat(testVideo.getWidth()).isEqualTo(UPDATED_WIDTH);
    assertThat(testVideo.getHeight()).isEqualTo(UPDATED_HEIGHT);
    assertThat(testVideo.getQualityImage()).isEqualTo(UPDATED_QUALITY_IMAGE);
    assertThat(testVideo.getQualityAudio()).isEqualTo(UPDATED_QUALITY_AUDIO);
    assertThat(testVideo.getQuality()).isEqualTo(UPDATED_QUALITY);
    assertThat(testVideo.getPixelSize()).isEqualTo(UPDATED_PIXEL_SIZE);
    assertThat(testVideo.getPriorityIndex()).isEqualTo(UPDATED_PRIORITY_INDEX);
    assertThat(testVideo.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);

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

    partialUpdatedVideo.name(UPDATED_NAME).qualityImage(UPDATED_QUALITY_IMAGE).pixelSize(UPDATED_PIXEL_SIZE);

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
    assertThat(testVideo.getWidth()).isEqualTo(DEFAULT_WIDTH);
    assertThat(testVideo.getHeight()).isEqualTo(DEFAULT_HEIGHT);
    assertThat(testVideo.getQualityImage()).isEqualTo(UPDATED_QUALITY_IMAGE);
    assertThat(testVideo.getQualityAudio()).isEqualTo(DEFAULT_QUALITY_AUDIO);
    assertThat(testVideo.getQuality()).isEqualTo(DEFAULT_QUALITY);
    assertThat(testVideo.getPixelSize()).isEqualTo(UPDATED_PIXEL_SIZE);
    assertThat(testVideo.getPriorityIndex()).isEqualTo(DEFAULT_PRIORITY_INDEX);
    assertThat(testVideo.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);
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

    partialUpdatedVideo
      .uuid(UPDATED_UUID)
      .name(UPDATED_NAME)
      .width(UPDATED_WIDTH)
      .height(UPDATED_HEIGHT)
      .qualityImage(UPDATED_QUALITY_IMAGE)
      .qualityAudio(UPDATED_QUALITY_AUDIO)
      .quality(UPDATED_QUALITY)
      .pixelSize(UPDATED_PIXEL_SIZE)
      .priorityIndex(UPDATED_PRIORITY_INDEX)
      .dataSize(UPDATED_DATA_SIZE);

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
    assertThat(testVideo.getWidth()).isEqualTo(UPDATED_WIDTH);
    assertThat(testVideo.getHeight()).isEqualTo(UPDATED_HEIGHT);
    assertThat(testVideo.getQualityImage()).isEqualTo(UPDATED_QUALITY_IMAGE);
    assertThat(testVideo.getQualityAudio()).isEqualTo(UPDATED_QUALITY_AUDIO);
    assertThat(testVideo.getQuality()).isEqualTo(UPDATED_QUALITY);
    assertThat(testVideo.getPixelSize()).isEqualTo(UPDATED_PIXEL_SIZE);
    assertThat(testVideo.getPriorityIndex()).isEqualTo(UPDATED_PRIORITY_INDEX);
    assertThat(testVideo.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
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
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
      .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
      .andExpect(jsonPath("$.[*].qualityImage").value(hasItem(DEFAULT_QUALITY_IMAGE.doubleValue())))
      .andExpect(jsonPath("$.[*].qualityAudio").value(hasItem(DEFAULT_QUALITY_AUDIO.doubleValue())))
      .andExpect(jsonPath("$.[*].quality").value(hasItem(DEFAULT_QUALITY.doubleValue())))
      .andExpect(jsonPath("$.[*].pixelSize").value(hasItem(DEFAULT_PIXEL_SIZE)))
      .andExpect(jsonPath("$.[*].priorityIndex").value(hasItem(DEFAULT_PRIORITY_INDEX.intValue())))
      .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE.intValue())));
  }
}
