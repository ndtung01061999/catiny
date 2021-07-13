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
import com.regitiny.catiny.domain.Notification;
import com.regitiny.catiny.domain.enumeration.NotifyType;
import com.regitiny.catiny.repository.NotificationRepository;
import com.regitiny.catiny.repository.search.NotificationSearchRepository;
import com.regitiny.catiny.service.criteria.NotificationCriteria;
import com.regitiny.catiny.service.dto.NotificationDTO;
import com.regitiny.catiny.service.mapper.NotificationMapper;
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
 * Integration tests for the {@link NotificationResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class NotificationResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final NotifyType DEFAULT_NOTIFY_TYPE = NotifyType.SYSTEM;
  private static final NotifyType UPDATED_NOTIFY_TYPE = NotifyType.MANAGER;

  private static final String DEFAULT_TITLE = "AAAAAAAAAA";
  private static final String UPDATED_TITLE = "BBBBBBBBBB";

  private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
  private static final String UPDATED_CONTENT = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/notifications";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/notifications";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private NotificationRepository notificationRepository;

  @Autowired
  private NotificationMapper notificationMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.NotificationSearchRepositoryMockConfiguration
   */
  @Autowired
  private NotificationSearchRepository mockNotificationSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restNotificationMockMvc;

  private Notification notification;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Notification createEntity(EntityManager em) {
    Notification notification = new Notification()
      .uuid(DEFAULT_UUID)
      .notifyType(DEFAULT_NOTIFY_TYPE)
      .title(DEFAULT_TITLE)
      .content(DEFAULT_CONTENT);
    return notification;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Notification createUpdatedEntity(EntityManager em) {
    Notification notification = new Notification()
      .uuid(UPDATED_UUID)
      .notifyType(UPDATED_NOTIFY_TYPE)
      .title(UPDATED_TITLE)
      .content(UPDATED_CONTENT);
    return notification;
  }

  @BeforeEach
  public void initTest() {
    notification = createEntity(em);
  }

  @Test
  @Transactional
  void createNotification() throws Exception {
    int databaseSizeBeforeCreate = notificationRepository.findAll().size();
    // Create the Notification
    NotificationDTO notificationDTO = notificationMapper.toDto(notification);
    restNotificationMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
      .andExpect(status().isCreated());

    // Validate the Notification in the database
    List<Notification> notificationList = notificationRepository.findAll();
    assertThat(notificationList).hasSize(databaseSizeBeforeCreate + 1);
    Notification testNotification = notificationList.get(notificationList.size() - 1);
    assertThat(testNotification.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testNotification.getNotifyType()).isEqualTo(DEFAULT_NOTIFY_TYPE);
    assertThat(testNotification.getTitle()).isEqualTo(DEFAULT_TITLE);
    assertThat(testNotification.getContent()).isEqualTo(DEFAULT_CONTENT);

    // Validate the Notification in Elasticsearch
    verify(mockNotificationSearchRepository, times(1)).save(testNotification);
  }

  @Test
  @Transactional
  void createNotificationWithExistingId() throws Exception {
    // Create the Notification with an existing ID
    notification.setId(1L);
    NotificationDTO notificationDTO = notificationMapper.toDto(notification);

    int databaseSizeBeforeCreate = notificationRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restNotificationMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
      .andExpect(status().isBadRequest());

    // Validate the Notification in the database
    List<Notification> notificationList = notificationRepository.findAll();
    assertThat(notificationList).hasSize(databaseSizeBeforeCreate);

    // Validate the Notification in Elasticsearch
    verify(mockNotificationSearchRepository, times(0)).save(notification);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = notificationRepository.findAll().size();
    // set the field null
    notification.setUuid(null);

    // Create the Notification, which fails.
    NotificationDTO notificationDTO = notificationMapper.toDto(notification);

    restNotificationMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
      .andExpect(status().isBadRequest());

    List<Notification> notificationList = notificationRepository.findAll();
    assertThat(notificationList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllNotifications() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList
    restNotificationMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(notification.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].notifyType").value(hasItem(DEFAULT_NOTIFY_TYPE.toString())))
      .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));
  }

  @Test
  @Transactional
  void getNotification() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get the notification
    restNotificationMockMvc
      .perform(get(ENTITY_API_URL_ID, notification.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(notification.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.notifyType").value(DEFAULT_NOTIFY_TYPE.toString()))
      .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
      .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()));
  }

  @Test
  @Transactional
  void getNotificationsByIdFiltering() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    Long id = notification.getId();

    defaultNotificationShouldBeFound("id.equals=" + id);
    defaultNotificationShouldNotBeFound("id.notEquals=" + id);

    defaultNotificationShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultNotificationShouldNotBeFound("id.greaterThan=" + id);

    defaultNotificationShouldBeFound("id.lessThanOrEqual=" + id);
    defaultNotificationShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllNotificationsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList where uuid equals to DEFAULT_UUID
    defaultNotificationShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the notificationList where uuid equals to UPDATED_UUID
    defaultNotificationShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllNotificationsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList where uuid not equals to DEFAULT_UUID
    defaultNotificationShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the notificationList where uuid not equals to UPDATED_UUID
    defaultNotificationShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllNotificationsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultNotificationShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the notificationList where uuid equals to UPDATED_UUID
    defaultNotificationShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllNotificationsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList where uuid is not null
    defaultNotificationShouldBeFound("uuid.specified=true");

    // Get all the notificationList where uuid is null
    defaultNotificationShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllNotificationsByNotifyTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList where notifyType equals to DEFAULT_NOTIFY_TYPE
    defaultNotificationShouldBeFound("notifyType.equals=" + DEFAULT_NOTIFY_TYPE);

    // Get all the notificationList where notifyType equals to UPDATED_NOTIFY_TYPE
    defaultNotificationShouldNotBeFound("notifyType.equals=" + UPDATED_NOTIFY_TYPE);
  }

  @Test
  @Transactional
  void getAllNotificationsByNotifyTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList where notifyType not equals to DEFAULT_NOTIFY_TYPE
    defaultNotificationShouldNotBeFound("notifyType.notEquals=" + DEFAULT_NOTIFY_TYPE);

    // Get all the notificationList where notifyType not equals to UPDATED_NOTIFY_TYPE
    defaultNotificationShouldBeFound("notifyType.notEquals=" + UPDATED_NOTIFY_TYPE);
  }

  @Test
  @Transactional
  void getAllNotificationsByNotifyTypeIsInShouldWork() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList where notifyType in DEFAULT_NOTIFY_TYPE or UPDATED_NOTIFY_TYPE
    defaultNotificationShouldBeFound("notifyType.in=" + DEFAULT_NOTIFY_TYPE + "," + UPDATED_NOTIFY_TYPE);

    // Get all the notificationList where notifyType equals to UPDATED_NOTIFY_TYPE
    defaultNotificationShouldNotBeFound("notifyType.in=" + UPDATED_NOTIFY_TYPE);
  }

  @Test
  @Transactional
  void getAllNotificationsByNotifyTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList where notifyType is not null
    defaultNotificationShouldBeFound("notifyType.specified=true");

    // Get all the notificationList where notifyType is null
    defaultNotificationShouldNotBeFound("notifyType.specified=false");
  }

  @Test
  @Transactional
  void getAllNotificationsByTitleIsEqualToSomething() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList where title equals to DEFAULT_TITLE
    defaultNotificationShouldBeFound("title.equals=" + DEFAULT_TITLE);

    // Get all the notificationList where title equals to UPDATED_TITLE
    defaultNotificationShouldNotBeFound("title.equals=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllNotificationsByTitleIsNotEqualToSomething() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList where title not equals to DEFAULT_TITLE
    defaultNotificationShouldNotBeFound("title.notEquals=" + DEFAULT_TITLE);

    // Get all the notificationList where title not equals to UPDATED_TITLE
    defaultNotificationShouldBeFound("title.notEquals=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllNotificationsByTitleIsInShouldWork() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList where title in DEFAULT_TITLE or UPDATED_TITLE
    defaultNotificationShouldBeFound("title.in=" + DEFAULT_TITLE + "," + UPDATED_TITLE);

    // Get all the notificationList where title equals to UPDATED_TITLE
    defaultNotificationShouldNotBeFound("title.in=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllNotificationsByTitleIsNullOrNotNull() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList where title is not null
    defaultNotificationShouldBeFound("title.specified=true");

    // Get all the notificationList where title is null
    defaultNotificationShouldNotBeFound("title.specified=false");
  }

  @Test
  @Transactional
  void getAllNotificationsByTitleContainsSomething() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList where title contains DEFAULT_TITLE
    defaultNotificationShouldBeFound("title.contains=" + DEFAULT_TITLE);

    // Get all the notificationList where title contains UPDATED_TITLE
    defaultNotificationShouldNotBeFound("title.contains=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllNotificationsByTitleNotContainsSomething() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    // Get all the notificationList where title does not contain DEFAULT_TITLE
    defaultNotificationShouldNotBeFound("title.doesNotContain=" + DEFAULT_TITLE);

    // Get all the notificationList where title does not contain UPDATED_TITLE
    defaultNotificationShouldBeFound("title.doesNotContain=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllNotificationsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    notification.setBaseInfo(baseInfo);
    notificationRepository.saveAndFlush(notification);
    Long baseInfoId = baseInfo.getId();

    // Get all the notificationList where baseInfo equals to baseInfoId
    defaultNotificationShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the notificationList where baseInfo equals to (baseInfoId + 1)
    defaultNotificationShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllNotificationsByMasterUserIsEqualToSomething() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);
    MasterUser masterUser = MasterUserResourceIT.createEntity(em);
    em.persist(masterUser);
    em.flush();
    notification.setMasterUser(masterUser);
    notificationRepository.saveAndFlush(notification);
    Long masterUserId = masterUser.getId();

    // Get all the notificationList where masterUser equals to masterUserId
    defaultNotificationShouldBeFound("masterUserId.equals=" + masterUserId);

    // Get all the notificationList where masterUser equals to (masterUserId + 1)
    defaultNotificationShouldNotBeFound("masterUserId.equals=" + (masterUserId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultNotificationShouldBeFound(String filter) throws Exception {
    restNotificationMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(notification.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].notifyType").value(hasItem(DEFAULT_NOTIFY_TYPE.toString())))
      .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));

    // Check, that the count call also returns 1
    restNotificationMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultNotificationShouldNotBeFound(String filter) throws Exception {
    restNotificationMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restNotificationMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingNotification() throws Exception {
    // Get the notification
    restNotificationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewNotification() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    int databaseSizeBeforeUpdate = notificationRepository.findAll().size();

    // Update the notification
    Notification updatedNotification = notificationRepository.findById(notification.getId()).get();
    // Disconnect from session so that the updates on updatedNotification are not directly saved in db
    em.detach(updatedNotification);
    updatedNotification.uuid(UPDATED_UUID).notifyType(UPDATED_NOTIFY_TYPE).title(UPDATED_TITLE).content(UPDATED_CONTENT);
    NotificationDTO notificationDTO = notificationMapper.toDto(updatedNotification);

    restNotificationMockMvc
      .perform(
        put(ENTITY_API_URL_ID, notificationDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(notificationDTO))
      )
      .andExpect(status().isOk());

    // Validate the Notification in the database
    List<Notification> notificationList = notificationRepository.findAll();
    assertThat(notificationList).hasSize(databaseSizeBeforeUpdate);
    Notification testNotification = notificationList.get(notificationList.size() - 1);
    assertThat(testNotification.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testNotification.getNotifyType()).isEqualTo(UPDATED_NOTIFY_TYPE);
    assertThat(testNotification.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testNotification.getContent()).isEqualTo(UPDATED_CONTENT);

    // Validate the Notification in Elasticsearch
    verify(mockNotificationSearchRepository).save(testNotification);
  }

  @Test
  @Transactional
  void putNonExistingNotification() throws Exception {
    int databaseSizeBeforeUpdate = notificationRepository.findAll().size();
    notification.setId(count.incrementAndGet());

    // Create the Notification
    NotificationDTO notificationDTO = notificationMapper.toDto(notification);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restNotificationMockMvc
      .perform(
        put(ENTITY_API_URL_ID, notificationDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(notificationDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Notification in the database
    List<Notification> notificationList = notificationRepository.findAll();
    assertThat(notificationList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Notification in Elasticsearch
    verify(mockNotificationSearchRepository, times(0)).save(notification);
  }

  @Test
  @Transactional
  void putWithIdMismatchNotification() throws Exception {
    int databaseSizeBeforeUpdate = notificationRepository.findAll().size();
    notification.setId(count.incrementAndGet());

    // Create the Notification
    NotificationDTO notificationDTO = notificationMapper.toDto(notification);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restNotificationMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(notificationDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Notification in the database
    List<Notification> notificationList = notificationRepository.findAll();
    assertThat(notificationList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Notification in Elasticsearch
    verify(mockNotificationSearchRepository, times(0)).save(notification);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamNotification() throws Exception {
    int databaseSizeBeforeUpdate = notificationRepository.findAll().size();
    notification.setId(count.incrementAndGet());

    // Create the Notification
    NotificationDTO notificationDTO = notificationMapper.toDto(notification);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restNotificationMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Notification in the database
    List<Notification> notificationList = notificationRepository.findAll();
    assertThat(notificationList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Notification in Elasticsearch
    verify(mockNotificationSearchRepository, times(0)).save(notification);
  }

  @Test
  @Transactional
  void partialUpdateNotificationWithPatch() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    int databaseSizeBeforeUpdate = notificationRepository.findAll().size();

    // Update the notification using partial update
    Notification partialUpdatedNotification = new Notification();
    partialUpdatedNotification.setId(notification.getId());

    partialUpdatedNotification.title(UPDATED_TITLE).content(UPDATED_CONTENT);

    restNotificationMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedNotification.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNotification))
      )
      .andExpect(status().isOk());

    // Validate the Notification in the database
    List<Notification> notificationList = notificationRepository.findAll();
    assertThat(notificationList).hasSize(databaseSizeBeforeUpdate);
    Notification testNotification = notificationList.get(notificationList.size() - 1);
    assertThat(testNotification.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testNotification.getNotifyType()).isEqualTo(DEFAULT_NOTIFY_TYPE);
    assertThat(testNotification.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testNotification.getContent()).isEqualTo(UPDATED_CONTENT);
  }

  @Test
  @Transactional
  void fullUpdateNotificationWithPatch() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    int databaseSizeBeforeUpdate = notificationRepository.findAll().size();

    // Update the notification using partial update
    Notification partialUpdatedNotification = new Notification();
    partialUpdatedNotification.setId(notification.getId());

    partialUpdatedNotification.uuid(UPDATED_UUID).notifyType(UPDATED_NOTIFY_TYPE).title(UPDATED_TITLE).content(UPDATED_CONTENT);

    restNotificationMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedNotification.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNotification))
      )
      .andExpect(status().isOk());

    // Validate the Notification in the database
    List<Notification> notificationList = notificationRepository.findAll();
    assertThat(notificationList).hasSize(databaseSizeBeforeUpdate);
    Notification testNotification = notificationList.get(notificationList.size() - 1);
    assertThat(testNotification.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testNotification.getNotifyType()).isEqualTo(UPDATED_NOTIFY_TYPE);
    assertThat(testNotification.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testNotification.getContent()).isEqualTo(UPDATED_CONTENT);
  }

  @Test
  @Transactional
  void patchNonExistingNotification() throws Exception {
    int databaseSizeBeforeUpdate = notificationRepository.findAll().size();
    notification.setId(count.incrementAndGet());

    // Create the Notification
    NotificationDTO notificationDTO = notificationMapper.toDto(notification);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restNotificationMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, notificationDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(notificationDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Notification in the database
    List<Notification> notificationList = notificationRepository.findAll();
    assertThat(notificationList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Notification in Elasticsearch
    verify(mockNotificationSearchRepository, times(0)).save(notification);
  }

  @Test
  @Transactional
  void patchWithIdMismatchNotification() throws Exception {
    int databaseSizeBeforeUpdate = notificationRepository.findAll().size();
    notification.setId(count.incrementAndGet());

    // Create the Notification
    NotificationDTO notificationDTO = notificationMapper.toDto(notification);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restNotificationMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(notificationDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Notification in the database
    List<Notification> notificationList = notificationRepository.findAll();
    assertThat(notificationList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Notification in Elasticsearch
    verify(mockNotificationSearchRepository, times(0)).save(notification);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamNotification() throws Exception {
    int databaseSizeBeforeUpdate = notificationRepository.findAll().size();
    notification.setId(count.incrementAndGet());

    // Create the Notification
    NotificationDTO notificationDTO = notificationMapper.toDto(notification);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restNotificationMockMvc
      .perform(
        patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(notificationDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the Notification in the database
    List<Notification> notificationList = notificationRepository.findAll();
    assertThat(notificationList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Notification in Elasticsearch
    verify(mockNotificationSearchRepository, times(0)).save(notification);
  }

  @Test
  @Transactional
  void deleteNotification() throws Exception {
    // Initialize the database
    notificationRepository.saveAndFlush(notification);

    int databaseSizeBeforeDelete = notificationRepository.findAll().size();

    // Delete the notification
    restNotificationMockMvc
      .perform(delete(ENTITY_API_URL_ID, notification.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<Notification> notificationList = notificationRepository.findAll();
    assertThat(notificationList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the Notification in Elasticsearch
    verify(mockNotificationSearchRepository, times(1)).deleteById(notification.getId());
  }

  @Test
  @Transactional
  void searchNotification() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    notificationRepository.saveAndFlush(notification);
    when(mockNotificationSearchRepository.search(queryStringQuery("id:" + notification.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(notification), PageRequest.of(0, 1), 1));

    // Search the notification
    restNotificationMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + notification.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(notification.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].notifyType").value(hasItem(DEFAULT_NOTIFY_TYPE.toString())))
      .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));
  }
}
