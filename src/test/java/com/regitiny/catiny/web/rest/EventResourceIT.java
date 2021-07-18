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
import com.regitiny.catiny.domain.enumeration.EventType;
import com.regitiny.catiny.repository.EventRepository;
import com.regitiny.catiny.repository.search.EventSearchRepository;
import com.regitiny.catiny.service.criteria.EventCriteria;
import com.regitiny.catiny.service.dto.EventDTO;
import com.regitiny.catiny.service.mapper.EventMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link EventResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class EventResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_TITLE = "AAAAAAAAAA";
  private static final String UPDATED_TITLE = "BBBBBBBBBB";

  private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
  private static final String UPDATED_AVATAR = "BBBBBBBBBB";

  private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
  private static final String UPDATED_CONTENT = "BBBBBBBBBB";

  private static final EventType DEFAULT_TYPE = EventType.DAY;
  private static final EventType UPDATED_TYPE = EventType.MONTH;

  private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
  private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

  private static final Instant DEFAULT_START_TIME = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

  private static final Instant DEFAULT_END_TIME = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_END_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

  private static final String DEFAULT_TAG_LINE = "AAAAAAAAAA";
  private static final String UPDATED_TAG_LINE = "BBBBBBBBBB";

  private static final String DEFAULT_IMAGE_COLLECTION = "AAAAAAAAAA";
  private static final String UPDATED_IMAGE_COLLECTION = "BBBBBBBBBB";

  private static final String DEFAULT_VIDEO_COLLECTION = "AAAAAAAAAA";
  private static final String UPDATED_VIDEO_COLLECTION = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/events";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/events";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private EventMapper eventMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.EventSearchRepositoryMockConfiguration
   */
  @Autowired
  private EventSearchRepository mockEventSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restEventMockMvc;

  private Event event;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Event createEntity(EntityManager em) {
    Event event = new Event()
      .uuid(DEFAULT_UUID)
      .title(DEFAULT_TITLE)
      .avatar(DEFAULT_AVATAR)
      .content(DEFAULT_CONTENT)
      .type(DEFAULT_TYPE)
      .description(DEFAULT_DESCRIPTION)
      .startTime(DEFAULT_START_TIME)
      .endTime(DEFAULT_END_TIME)
      .tagLine(DEFAULT_TAG_LINE)
      .imageCollection(DEFAULT_IMAGE_COLLECTION)
      .videoCollection(DEFAULT_VIDEO_COLLECTION);
    return event;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Event createUpdatedEntity(EntityManager em) {
    Event event = new Event()
      .uuid(UPDATED_UUID)
      .title(UPDATED_TITLE)
      .avatar(UPDATED_AVATAR)
      .content(UPDATED_CONTENT)
      .type(UPDATED_TYPE)
      .description(UPDATED_DESCRIPTION)
      .startTime(UPDATED_START_TIME)
      .endTime(UPDATED_END_TIME)
      .tagLine(UPDATED_TAG_LINE)
      .imageCollection(UPDATED_IMAGE_COLLECTION)
      .videoCollection(UPDATED_VIDEO_COLLECTION);
    return event;
  }

  @BeforeEach
  public void initTest() {
    event = createEntity(em);
  }

  @Test
  @Transactional
  void createEvent() throws Exception {
    int databaseSizeBeforeCreate = eventRepository.findAll().size();
    // Create the Event
    EventDTO eventDTO = eventMapper.toDto(event);
    restEventMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(eventDTO)))
      .andExpect(status().isCreated());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeCreate + 1);
    Event testEvent = eventList.get(eventList.size() - 1);
    assertThat(testEvent.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testEvent.getTitle()).isEqualTo(DEFAULT_TITLE);
    assertThat(testEvent.getAvatar()).isEqualTo(DEFAULT_AVATAR);
    assertThat(testEvent.getContent()).isEqualTo(DEFAULT_CONTENT);
    assertThat(testEvent.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testEvent.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    assertThat(testEvent.getStartTime()).isEqualTo(DEFAULT_START_TIME);
    assertThat(testEvent.getEndTime()).isEqualTo(DEFAULT_END_TIME);
    assertThat(testEvent.getTagLine()).isEqualTo(DEFAULT_TAG_LINE);
    assertThat(testEvent.getImageCollection()).isEqualTo(DEFAULT_IMAGE_COLLECTION);
    assertThat(testEvent.getVideoCollection()).isEqualTo(DEFAULT_VIDEO_COLLECTION);

    // Validate the Event in Elasticsearch
    verify(mockEventSearchRepository, times(1)).save(testEvent);
  }

  @Test
  @Transactional
  void createEventWithExistingId() throws Exception {
    // Create the Event with an existing ID
    event.setId(1L);
    EventDTO eventDTO = eventMapper.toDto(event);

    int databaseSizeBeforeCreate = eventRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restEventMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(eventDTO)))
      .andExpect(status().isBadRequest());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeCreate);

    // Validate the Event in Elasticsearch
    verify(mockEventSearchRepository, times(0)).save(event);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = eventRepository.findAll().size();
    // set the field null
    event.setUuid(null);

    // Create the Event, which fails.
    EventDTO eventDTO = eventMapper.toDto(event);

    restEventMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(eventDTO)))
      .andExpect(status().isBadRequest());

    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllEvents() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList
    restEventMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(event.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
      .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
      .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
      .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())))
      .andExpect(jsonPath("$.[*].tagLine").value(hasItem(DEFAULT_TAG_LINE)))
      .andExpect(jsonPath("$.[*].imageCollection").value(hasItem(DEFAULT_IMAGE_COLLECTION.toString())))
      .andExpect(jsonPath("$.[*].videoCollection").value(hasItem(DEFAULT_VIDEO_COLLECTION.toString())));
  }

  @Test
  @Transactional
  void getEvent() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get the event
    restEventMockMvc
      .perform(get(ENTITY_API_URL_ID, event.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(event.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
      .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR.toString()))
      .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
      .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
      .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
      .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
      .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME.toString()))
      .andExpect(jsonPath("$.tagLine").value(DEFAULT_TAG_LINE))
      .andExpect(jsonPath("$.imageCollection").value(DEFAULT_IMAGE_COLLECTION.toString()))
      .andExpect(jsonPath("$.videoCollection").value(DEFAULT_VIDEO_COLLECTION.toString()));
  }

  @Test
  @Transactional
  void getEventsByIdFiltering() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    Long id = event.getId();

    defaultEventShouldBeFound("id.equals=" + id);
    defaultEventShouldNotBeFound("id.notEquals=" + id);

    defaultEventShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultEventShouldNotBeFound("id.greaterThan=" + id);

    defaultEventShouldBeFound("id.lessThanOrEqual=" + id);
    defaultEventShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllEventsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where uuid equals to DEFAULT_UUID
    defaultEventShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the eventList where uuid equals to UPDATED_UUID
    defaultEventShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllEventsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where uuid not equals to DEFAULT_UUID
    defaultEventShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the eventList where uuid not equals to UPDATED_UUID
    defaultEventShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllEventsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultEventShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the eventList where uuid equals to UPDATED_UUID
    defaultEventShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllEventsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where uuid is not null
    defaultEventShouldBeFound("uuid.specified=true");

    // Get all the eventList where uuid is null
    defaultEventShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllEventsByTitleIsEqualToSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where title equals to DEFAULT_TITLE
    defaultEventShouldBeFound("title.equals=" + DEFAULT_TITLE);

    // Get all the eventList where title equals to UPDATED_TITLE
    defaultEventShouldNotBeFound("title.equals=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllEventsByTitleIsNotEqualToSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where title not equals to DEFAULT_TITLE
    defaultEventShouldNotBeFound("title.notEquals=" + DEFAULT_TITLE);

    // Get all the eventList where title not equals to UPDATED_TITLE
    defaultEventShouldBeFound("title.notEquals=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllEventsByTitleIsInShouldWork() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where title in DEFAULT_TITLE or UPDATED_TITLE
    defaultEventShouldBeFound("title.in=" + DEFAULT_TITLE + "," + UPDATED_TITLE);

    // Get all the eventList where title equals to UPDATED_TITLE
    defaultEventShouldNotBeFound("title.in=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllEventsByTitleIsNullOrNotNull() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where title is not null
    defaultEventShouldBeFound("title.specified=true");

    // Get all the eventList where title is null
    defaultEventShouldNotBeFound("title.specified=false");
  }

  @Test
  @Transactional
  void getAllEventsByTitleContainsSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where title contains DEFAULT_TITLE
    defaultEventShouldBeFound("title.contains=" + DEFAULT_TITLE);

    // Get all the eventList where title contains UPDATED_TITLE
    defaultEventShouldNotBeFound("title.contains=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllEventsByTitleNotContainsSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where title does not contain DEFAULT_TITLE
    defaultEventShouldNotBeFound("title.doesNotContain=" + DEFAULT_TITLE);

    // Get all the eventList where title does not contain UPDATED_TITLE
    defaultEventShouldBeFound("title.doesNotContain=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllEventsByTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where type equals to DEFAULT_TYPE
    defaultEventShouldBeFound("type.equals=" + DEFAULT_TYPE);

    // Get all the eventList where type equals to UPDATED_TYPE
    defaultEventShouldNotBeFound("type.equals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllEventsByTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where type not equals to DEFAULT_TYPE
    defaultEventShouldNotBeFound("type.notEquals=" + DEFAULT_TYPE);

    // Get all the eventList where type not equals to UPDATED_TYPE
    defaultEventShouldBeFound("type.notEquals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllEventsByTypeIsInShouldWork() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where type in DEFAULT_TYPE or UPDATED_TYPE
    defaultEventShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

    // Get all the eventList where type equals to UPDATED_TYPE
    defaultEventShouldNotBeFound("type.in=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllEventsByTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where type is not null
    defaultEventShouldBeFound("type.specified=true");

    // Get all the eventList where type is null
    defaultEventShouldNotBeFound("type.specified=false");
  }

  @Test
  @Transactional
  void getAllEventsByStartTimeIsEqualToSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where startTime equals to DEFAULT_START_TIME
    defaultEventShouldBeFound("startTime.equals=" + DEFAULT_START_TIME);

    // Get all the eventList where startTime equals to UPDATED_START_TIME
    defaultEventShouldNotBeFound("startTime.equals=" + UPDATED_START_TIME);
  }

  @Test
  @Transactional
  void getAllEventsByStartTimeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where startTime not equals to DEFAULT_START_TIME
    defaultEventShouldNotBeFound("startTime.notEquals=" + DEFAULT_START_TIME);

    // Get all the eventList where startTime not equals to UPDATED_START_TIME
    defaultEventShouldBeFound("startTime.notEquals=" + UPDATED_START_TIME);
  }

  @Test
  @Transactional
  void getAllEventsByStartTimeIsInShouldWork() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where startTime in DEFAULT_START_TIME or UPDATED_START_TIME
    defaultEventShouldBeFound("startTime.in=" + DEFAULT_START_TIME + "," + UPDATED_START_TIME);

    // Get all the eventList where startTime equals to UPDATED_START_TIME
    defaultEventShouldNotBeFound("startTime.in=" + UPDATED_START_TIME);
  }

  @Test
  @Transactional
  void getAllEventsByStartTimeIsNullOrNotNull() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where startTime is not null
    defaultEventShouldBeFound("startTime.specified=true");

    // Get all the eventList where startTime is null
    defaultEventShouldNotBeFound("startTime.specified=false");
  }

  @Test
  @Transactional
  void getAllEventsByEndTimeIsEqualToSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where endTime equals to DEFAULT_END_TIME
    defaultEventShouldBeFound("endTime.equals=" + DEFAULT_END_TIME);

    // Get all the eventList where endTime equals to UPDATED_END_TIME
    defaultEventShouldNotBeFound("endTime.equals=" + UPDATED_END_TIME);
  }

  @Test
  @Transactional
  void getAllEventsByEndTimeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where endTime not equals to DEFAULT_END_TIME
    defaultEventShouldNotBeFound("endTime.notEquals=" + DEFAULT_END_TIME);

    // Get all the eventList where endTime not equals to UPDATED_END_TIME
    defaultEventShouldBeFound("endTime.notEquals=" + UPDATED_END_TIME);
  }

  @Test
  @Transactional
  void getAllEventsByEndTimeIsInShouldWork() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where endTime in DEFAULT_END_TIME or UPDATED_END_TIME
    defaultEventShouldBeFound("endTime.in=" + DEFAULT_END_TIME + "," + UPDATED_END_TIME);

    // Get all the eventList where endTime equals to UPDATED_END_TIME
    defaultEventShouldNotBeFound("endTime.in=" + UPDATED_END_TIME);
  }

  @Test
  @Transactional
  void getAllEventsByEndTimeIsNullOrNotNull() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where endTime is not null
    defaultEventShouldBeFound("endTime.specified=true");

    // Get all the eventList where endTime is null
    defaultEventShouldNotBeFound("endTime.specified=false");
  }

  @Test
  @Transactional
  void getAllEventsByTagLineIsEqualToSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where tagLine equals to DEFAULT_TAG_LINE
    defaultEventShouldBeFound("tagLine.equals=" + DEFAULT_TAG_LINE);

    // Get all the eventList where tagLine equals to UPDATED_TAG_LINE
    defaultEventShouldNotBeFound("tagLine.equals=" + UPDATED_TAG_LINE);
  }

  @Test
  @Transactional
  void getAllEventsByTagLineIsNotEqualToSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where tagLine not equals to DEFAULT_TAG_LINE
    defaultEventShouldNotBeFound("tagLine.notEquals=" + DEFAULT_TAG_LINE);

    // Get all the eventList where tagLine not equals to UPDATED_TAG_LINE
    defaultEventShouldBeFound("tagLine.notEquals=" + UPDATED_TAG_LINE);
  }

  @Test
  @Transactional
  void getAllEventsByTagLineIsInShouldWork() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where tagLine in DEFAULT_TAG_LINE or UPDATED_TAG_LINE
    defaultEventShouldBeFound("tagLine.in=" + DEFAULT_TAG_LINE + "," + UPDATED_TAG_LINE);

    // Get all the eventList where tagLine equals to UPDATED_TAG_LINE
    defaultEventShouldNotBeFound("tagLine.in=" + UPDATED_TAG_LINE);
  }

  @Test
  @Transactional
  void getAllEventsByTagLineIsNullOrNotNull() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where tagLine is not null
    defaultEventShouldBeFound("tagLine.specified=true");

    // Get all the eventList where tagLine is null
    defaultEventShouldNotBeFound("tagLine.specified=false");
  }

  @Test
  @Transactional
  void getAllEventsByTagLineContainsSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where tagLine contains DEFAULT_TAG_LINE
    defaultEventShouldBeFound("tagLine.contains=" + DEFAULT_TAG_LINE);

    // Get all the eventList where tagLine contains UPDATED_TAG_LINE
    defaultEventShouldNotBeFound("tagLine.contains=" + UPDATED_TAG_LINE);
  }

  @Test
  @Transactional
  void getAllEventsByTagLineNotContainsSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList where tagLine does not contain DEFAULT_TAG_LINE
    defaultEventShouldNotBeFound("tagLine.doesNotContain=" + DEFAULT_TAG_LINE);

    // Get all the eventList where tagLine does not contain UPDATED_TAG_LINE
    defaultEventShouldBeFound("tagLine.doesNotContain=" + UPDATED_TAG_LINE);
  }

  @Test
  @Transactional
  void getAllEventsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    event.setBaseInfo(baseInfo);
    eventRepository.saveAndFlush(event);
    Long baseInfoId = baseInfo.getId();

    // Get all the eventList where baseInfo equals to baseInfoId
    defaultEventShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the eventList where baseInfo equals to (baseInfoId + 1)
    defaultEventShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultEventShouldBeFound(String filter) throws Exception {
    restEventMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(event.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
      .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
      .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
      .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())))
      .andExpect(jsonPath("$.[*].tagLine").value(hasItem(DEFAULT_TAG_LINE)))
      .andExpect(jsonPath("$.[*].imageCollection").value(hasItem(DEFAULT_IMAGE_COLLECTION.toString())))
      .andExpect(jsonPath("$.[*].videoCollection").value(hasItem(DEFAULT_VIDEO_COLLECTION.toString())));

    // Check, that the count call also returns 1
    restEventMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultEventShouldNotBeFound(String filter) throws Exception {
    restEventMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restEventMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingEvent() throws Exception {
    // Get the event
    restEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewEvent() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    int databaseSizeBeforeUpdate = eventRepository.findAll().size();

    // Update the event
    Event updatedEvent = eventRepository.findById(event.getId()).get();
    // Disconnect from session so that the updates on updatedEvent are not directly saved in db
    em.detach(updatedEvent);
    updatedEvent
      .uuid(UPDATED_UUID)
      .title(UPDATED_TITLE)
      .avatar(UPDATED_AVATAR)
      .content(UPDATED_CONTENT)
      .type(UPDATED_TYPE)
      .description(UPDATED_DESCRIPTION)
      .startTime(UPDATED_START_TIME)
      .endTime(UPDATED_END_TIME)
      .tagLine(UPDATED_TAG_LINE)
      .imageCollection(UPDATED_IMAGE_COLLECTION)
      .videoCollection(UPDATED_VIDEO_COLLECTION);
    EventDTO eventDTO = eventMapper.toDto(updatedEvent);

    restEventMockMvc
      .perform(
        put(ENTITY_API_URL_ID, eventDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(eventDTO))
      )
      .andExpect(status().isOk());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeUpdate);
    Event testEvent = eventList.get(eventList.size() - 1);
    assertThat(testEvent.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testEvent.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testEvent.getAvatar()).isEqualTo(UPDATED_AVATAR);
    assertThat(testEvent.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testEvent.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testEvent.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    assertThat(testEvent.getStartTime()).isEqualTo(UPDATED_START_TIME);
    assertThat(testEvent.getEndTime()).isEqualTo(UPDATED_END_TIME);
    assertThat(testEvent.getTagLine()).isEqualTo(UPDATED_TAG_LINE);
    assertThat(testEvent.getImageCollection()).isEqualTo(UPDATED_IMAGE_COLLECTION);
    assertThat(testEvent.getVideoCollection()).isEqualTo(UPDATED_VIDEO_COLLECTION);

    // Validate the Event in Elasticsearch
    verify(mockEventSearchRepository).save(testEvent);
  }

  @Test
  @Transactional
  void putNonExistingEvent() throws Exception {
    int databaseSizeBeforeUpdate = eventRepository.findAll().size();
    event.setId(count.incrementAndGet());

    // Create the Event
    EventDTO eventDTO = eventMapper.toDto(event);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEventMockMvc
      .perform(
        put(ENTITY_API_URL_ID, eventDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(eventDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Event in Elasticsearch
    verify(mockEventSearchRepository, times(0)).save(event);
  }

  @Test
  @Transactional
  void putWithIdMismatchEvent() throws Exception {
    int databaseSizeBeforeUpdate = eventRepository.findAll().size();
    event.setId(count.incrementAndGet());

    // Create the Event
    EventDTO eventDTO = eventMapper.toDto(event);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEventMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(eventDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Event in Elasticsearch
    verify(mockEventSearchRepository, times(0)).save(event);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamEvent() throws Exception {
    int databaseSizeBeforeUpdate = eventRepository.findAll().size();
    event.setId(count.incrementAndGet());

    // Create the Event
    EventDTO eventDTO = eventMapper.toDto(event);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEventMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(eventDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Event in Elasticsearch
    verify(mockEventSearchRepository, times(0)).save(event);
  }

  @Test
  @Transactional
  void partialUpdateEventWithPatch() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    int databaseSizeBeforeUpdate = eventRepository.findAll().size();

    // Update the event using partial update
    Event partialUpdatedEvent = new Event();
    partialUpdatedEvent.setId(event.getId());

    partialUpdatedEvent.title(UPDATED_TITLE).endTime(UPDATED_END_TIME);

    restEventMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEvent.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEvent))
      )
      .andExpect(status().isOk());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeUpdate);
    Event testEvent = eventList.get(eventList.size() - 1);
    assertThat(testEvent.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testEvent.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testEvent.getAvatar()).isEqualTo(DEFAULT_AVATAR);
    assertThat(testEvent.getContent()).isEqualTo(DEFAULT_CONTENT);
    assertThat(testEvent.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testEvent.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    assertThat(testEvent.getStartTime()).isEqualTo(DEFAULT_START_TIME);
    assertThat(testEvent.getEndTime()).isEqualTo(UPDATED_END_TIME);
    assertThat(testEvent.getTagLine()).isEqualTo(DEFAULT_TAG_LINE);
    assertThat(testEvent.getImageCollection()).isEqualTo(DEFAULT_IMAGE_COLLECTION);
    assertThat(testEvent.getVideoCollection()).isEqualTo(DEFAULT_VIDEO_COLLECTION);
  }

  @Test
  @Transactional
  void fullUpdateEventWithPatch() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    int databaseSizeBeforeUpdate = eventRepository.findAll().size();

    // Update the event using partial update
    Event partialUpdatedEvent = new Event();
    partialUpdatedEvent.setId(event.getId());

    partialUpdatedEvent
      .uuid(UPDATED_UUID)
      .title(UPDATED_TITLE)
      .avatar(UPDATED_AVATAR)
      .content(UPDATED_CONTENT)
      .type(UPDATED_TYPE)
      .description(UPDATED_DESCRIPTION)
      .startTime(UPDATED_START_TIME)
      .endTime(UPDATED_END_TIME)
      .tagLine(UPDATED_TAG_LINE)
      .imageCollection(UPDATED_IMAGE_COLLECTION)
      .videoCollection(UPDATED_VIDEO_COLLECTION);

    restEventMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedEvent.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEvent))
      )
      .andExpect(status().isOk());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeUpdate);
    Event testEvent = eventList.get(eventList.size() - 1);
    assertThat(testEvent.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testEvent.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testEvent.getAvatar()).isEqualTo(UPDATED_AVATAR);
    assertThat(testEvent.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testEvent.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testEvent.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    assertThat(testEvent.getStartTime()).isEqualTo(UPDATED_START_TIME);
    assertThat(testEvent.getEndTime()).isEqualTo(UPDATED_END_TIME);
    assertThat(testEvent.getTagLine()).isEqualTo(UPDATED_TAG_LINE);
    assertThat(testEvent.getImageCollection()).isEqualTo(UPDATED_IMAGE_COLLECTION);
    assertThat(testEvent.getVideoCollection()).isEqualTo(UPDATED_VIDEO_COLLECTION);
  }

  @Test
  @Transactional
  void patchNonExistingEvent() throws Exception {
    int databaseSizeBeforeUpdate = eventRepository.findAll().size();
    event.setId(count.incrementAndGet());

    // Create the Event
    EventDTO eventDTO = eventMapper.toDto(event);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restEventMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, eventDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(eventDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Event in Elasticsearch
    verify(mockEventSearchRepository, times(0)).save(event);
  }

  @Test
  @Transactional
  void patchWithIdMismatchEvent() throws Exception {
    int databaseSizeBeforeUpdate = eventRepository.findAll().size();
    event.setId(count.incrementAndGet());

    // Create the Event
    EventDTO eventDTO = eventMapper.toDto(event);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEventMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(eventDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Event in Elasticsearch
    verify(mockEventSearchRepository, times(0)).save(event);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamEvent() throws Exception {
    int databaseSizeBeforeUpdate = eventRepository.findAll().size();
    event.setId(count.incrementAndGet());

    // Create the Event
    EventDTO eventDTO = eventMapper.toDto(event);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restEventMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(eventDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Event in Elasticsearch
    verify(mockEventSearchRepository, times(0)).save(event);
  }

  @Test
  @Transactional
  void deleteEvent() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    int databaseSizeBeforeDelete = eventRepository.findAll().size();

    // Delete the event
    restEventMockMvc.perform(delete(ENTITY_API_URL_ID, event.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the Event in Elasticsearch
    verify(mockEventSearchRepository, times(1)).deleteById(event.getId());
  }

  @Test
  @Transactional
  void searchEvent() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    eventRepository.saveAndFlush(event);
    when(mockEventSearchRepository.search(queryStringQuery("id:" + event.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(event), PageRequest.of(0, 1), 1));

    // Search the event
    restEventMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + event.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(event.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
      .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
      .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
      .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())))
      .andExpect(jsonPath("$.[*].tagLine").value(hasItem(DEFAULT_TAG_LINE)))
      .andExpect(jsonPath("$.[*].imageCollection").value(hasItem(DEFAULT_IMAGE_COLLECTION.toString())))
      .andExpect(jsonPath("$.[*].videoCollection").value(hasItem(DEFAULT_VIDEO_COLLECTION.toString())));
  }
}
