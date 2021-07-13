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
import com.regitiny.catiny.domain.UserProfile;
import com.regitiny.catiny.repository.UserProfileRepository;
import com.regitiny.catiny.repository.search.UserProfileSearchRepository;
import com.regitiny.catiny.service.criteria.UserProfileCriteria;
import com.regitiny.catiny.service.dto.UserProfileDTO;
import com.regitiny.catiny.service.mapper.UserProfileMapper;
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
 * Integration tests for the {@link UserProfileResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class UserProfileResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_WORK = "AAAAAAAAAA";
  private static final String UPDATED_WORK = "BBBBBBBBBB";

  private static final String DEFAULT_EDUCATION = "AAAAAAAAAA";
  private static final String UPDATED_EDUCATION = "BBBBBBBBBB";

  private static final String DEFAULT_PLACES_LIVED = "AAAAAAAAAA";
  private static final String UPDATED_PLACES_LIVED = "BBBBBBBBBB";

  private static final String DEFAULT_CONTACT_INFO = "AAAAAAAAAA";
  private static final String UPDATED_CONTACT_INFO = "BBBBBBBBBB";

  private static final String DEFAULT_WEB_SOCIAL_LINKS = "AAAAAAAAAA";
  private static final String UPDATED_WEB_SOCIAL_LINKS = "BBBBBBBBBB";

  private static final String DEFAULT_BASIC_INFO = "AAAAAAAAAA";
  private static final String UPDATED_BASIC_INFO = "BBBBBBBBBB";

  private static final String DEFAULT_RELATIONSHIP_INFO = "AAAAAAAAAA";
  private static final String UPDATED_RELATIONSHIP_INFO = "BBBBBBBBBB";

  private static final String DEFAULT_FAMILY = "AAAAAAAAAA";
  private static final String UPDATED_FAMILY = "BBBBBBBBBB";

  private static final String DEFAULT_DETAIL_ABOUT = "AAAAAAAAAA";
  private static final String UPDATED_DETAIL_ABOUT = "BBBBBBBBBB";

  private static final String DEFAULT_LIFE_EVENTS = "AAAAAAAAAA";
  private static final String UPDATED_LIFE_EVENTS = "BBBBBBBBBB";

  private static final String DEFAULT_HOBBIES = "AAAAAAAAAA";
  private static final String UPDATED_HOBBIES = "BBBBBBBBBB";

  private static final String DEFAULT_FEATURED = "AAAAAAAAAA";
  private static final String UPDATED_FEATURED = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/user-profiles";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/user-profiles";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private UserProfileRepository userProfileRepository;

  @Autowired
  private UserProfileMapper userProfileMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.UserProfileSearchRepositoryMockConfiguration
   */
  @Autowired
  private UserProfileSearchRepository mockUserProfileSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restUserProfileMockMvc;

  private UserProfile userProfile;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static UserProfile createEntity(EntityManager em) {
    UserProfile userProfile = new UserProfile()
      .uuid(DEFAULT_UUID)
      .work(DEFAULT_WORK)
      .education(DEFAULT_EDUCATION)
      .placesLived(DEFAULT_PLACES_LIVED)
      .contactInfo(DEFAULT_CONTACT_INFO)
      .webSocialLinks(DEFAULT_WEB_SOCIAL_LINKS)
      .basicInfo(DEFAULT_BASIC_INFO)
      .relationshipInfo(DEFAULT_RELATIONSHIP_INFO)
      .family(DEFAULT_FAMILY)
      .detailAbout(DEFAULT_DETAIL_ABOUT)
      .lifeEvents(DEFAULT_LIFE_EVENTS)
      .hobbies(DEFAULT_HOBBIES)
      .featured(DEFAULT_FEATURED);
    return userProfile;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static UserProfile createUpdatedEntity(EntityManager em) {
    UserProfile userProfile = new UserProfile()
      .uuid(UPDATED_UUID)
      .work(UPDATED_WORK)
      .education(UPDATED_EDUCATION)
      .placesLived(UPDATED_PLACES_LIVED)
      .contactInfo(UPDATED_CONTACT_INFO)
      .webSocialLinks(UPDATED_WEB_SOCIAL_LINKS)
      .basicInfo(UPDATED_BASIC_INFO)
      .relationshipInfo(UPDATED_RELATIONSHIP_INFO)
      .family(UPDATED_FAMILY)
      .detailAbout(UPDATED_DETAIL_ABOUT)
      .lifeEvents(UPDATED_LIFE_EVENTS)
      .hobbies(UPDATED_HOBBIES)
      .featured(UPDATED_FEATURED);
    return userProfile;
  }

  @BeforeEach
  public void initTest() {
    userProfile = createEntity(em);
  }

  @Test
  @Transactional
  void createUserProfile() throws Exception {
    int databaseSizeBeforeCreate = userProfileRepository.findAll().size();
    // Create the UserProfile
    UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);
    restUserProfileMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userProfileDTO)))
      .andExpect(status().isCreated());

    // Validate the UserProfile in the database
    List<UserProfile> userProfileList = userProfileRepository.findAll();
    assertThat(userProfileList).hasSize(databaseSizeBeforeCreate + 1);
    UserProfile testUserProfile = userProfileList.get(userProfileList.size() - 1);
    assertThat(testUserProfile.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testUserProfile.getWork()).isEqualTo(DEFAULT_WORK);
    assertThat(testUserProfile.getEducation()).isEqualTo(DEFAULT_EDUCATION);
    assertThat(testUserProfile.getPlacesLived()).isEqualTo(DEFAULT_PLACES_LIVED);
    assertThat(testUserProfile.getContactInfo()).isEqualTo(DEFAULT_CONTACT_INFO);
    assertThat(testUserProfile.getWebSocialLinks()).isEqualTo(DEFAULT_WEB_SOCIAL_LINKS);
    assertThat(testUserProfile.getBasicInfo()).isEqualTo(DEFAULT_BASIC_INFO);
    assertThat(testUserProfile.getRelationshipInfo()).isEqualTo(DEFAULT_RELATIONSHIP_INFO);
    assertThat(testUserProfile.getFamily()).isEqualTo(DEFAULT_FAMILY);
    assertThat(testUserProfile.getDetailAbout()).isEqualTo(DEFAULT_DETAIL_ABOUT);
    assertThat(testUserProfile.getLifeEvents()).isEqualTo(DEFAULT_LIFE_EVENTS);
    assertThat(testUserProfile.getHobbies()).isEqualTo(DEFAULT_HOBBIES);
    assertThat(testUserProfile.getFeatured()).isEqualTo(DEFAULT_FEATURED);

    // Validate the UserProfile in Elasticsearch
    verify(mockUserProfileSearchRepository, times(1)).save(testUserProfile);
  }

  @Test
  @Transactional
  void createUserProfileWithExistingId() throws Exception {
    // Create the UserProfile with an existing ID
    userProfile.setId(1L);
    UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);

    int databaseSizeBeforeCreate = userProfileRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restUserProfileMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userProfileDTO)))
      .andExpect(status().isBadRequest());

    // Validate the UserProfile in the database
    List<UserProfile> userProfileList = userProfileRepository.findAll();
    assertThat(userProfileList).hasSize(databaseSizeBeforeCreate);

    // Validate the UserProfile in Elasticsearch
    verify(mockUserProfileSearchRepository, times(0)).save(userProfile);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = userProfileRepository.findAll().size();
    // set the field null
    userProfile.setUuid(null);

    // Create the UserProfile, which fails.
    UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);

    restUserProfileMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userProfileDTO)))
      .andExpect(status().isBadRequest());

    List<UserProfile> userProfileList = userProfileRepository.findAll();
    assertThat(userProfileList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllUserProfiles() throws Exception {
    // Initialize the database
    userProfileRepository.saveAndFlush(userProfile);

    // Get all the userProfileList
    restUserProfileMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(userProfile.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].work").value(hasItem(DEFAULT_WORK.toString())))
      .andExpect(jsonPath("$.[*].education").value(hasItem(DEFAULT_EDUCATION.toString())))
      .andExpect(jsonPath("$.[*].placesLived").value(hasItem(DEFAULT_PLACES_LIVED.toString())))
      .andExpect(jsonPath("$.[*].contactInfo").value(hasItem(DEFAULT_CONTACT_INFO.toString())))
      .andExpect(jsonPath("$.[*].webSocialLinks").value(hasItem(DEFAULT_WEB_SOCIAL_LINKS.toString())))
      .andExpect(jsonPath("$.[*].basicInfo").value(hasItem(DEFAULT_BASIC_INFO.toString())))
      .andExpect(jsonPath("$.[*].relationshipInfo").value(hasItem(DEFAULT_RELATIONSHIP_INFO.toString())))
      .andExpect(jsonPath("$.[*].family").value(hasItem(DEFAULT_FAMILY.toString())))
      .andExpect(jsonPath("$.[*].detailAbout").value(hasItem(DEFAULT_DETAIL_ABOUT.toString())))
      .andExpect(jsonPath("$.[*].lifeEvents").value(hasItem(DEFAULT_LIFE_EVENTS.toString())))
      .andExpect(jsonPath("$.[*].hobbies").value(hasItem(DEFAULT_HOBBIES.toString())))
      .andExpect(jsonPath("$.[*].featured").value(hasItem(DEFAULT_FEATURED.toString())));
  }

  @Test
  @Transactional
  void getUserProfile() throws Exception {
    // Initialize the database
    userProfileRepository.saveAndFlush(userProfile);

    // Get the userProfile
    restUserProfileMockMvc
      .perform(get(ENTITY_API_URL_ID, userProfile.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(userProfile.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.work").value(DEFAULT_WORK.toString()))
      .andExpect(jsonPath("$.education").value(DEFAULT_EDUCATION.toString()))
      .andExpect(jsonPath("$.placesLived").value(DEFAULT_PLACES_LIVED.toString()))
      .andExpect(jsonPath("$.contactInfo").value(DEFAULT_CONTACT_INFO.toString()))
      .andExpect(jsonPath("$.webSocialLinks").value(DEFAULT_WEB_SOCIAL_LINKS.toString()))
      .andExpect(jsonPath("$.basicInfo").value(DEFAULT_BASIC_INFO.toString()))
      .andExpect(jsonPath("$.relationshipInfo").value(DEFAULT_RELATIONSHIP_INFO.toString()))
      .andExpect(jsonPath("$.family").value(DEFAULT_FAMILY.toString()))
      .andExpect(jsonPath("$.detailAbout").value(DEFAULT_DETAIL_ABOUT.toString()))
      .andExpect(jsonPath("$.lifeEvents").value(DEFAULT_LIFE_EVENTS.toString()))
      .andExpect(jsonPath("$.hobbies").value(DEFAULT_HOBBIES.toString()))
      .andExpect(jsonPath("$.featured").value(DEFAULT_FEATURED.toString()));
  }

  @Test
  @Transactional
  void getUserProfilesByIdFiltering() throws Exception {
    // Initialize the database
    userProfileRepository.saveAndFlush(userProfile);

    Long id = userProfile.getId();

    defaultUserProfileShouldBeFound("id.equals=" + id);
    defaultUserProfileShouldNotBeFound("id.notEquals=" + id);

    defaultUserProfileShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultUserProfileShouldNotBeFound("id.greaterThan=" + id);

    defaultUserProfileShouldBeFound("id.lessThanOrEqual=" + id);
    defaultUserProfileShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllUserProfilesByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    userProfileRepository.saveAndFlush(userProfile);

    // Get all the userProfileList where uuid equals to DEFAULT_UUID
    defaultUserProfileShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the userProfileList where uuid equals to UPDATED_UUID
    defaultUserProfileShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllUserProfilesByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    userProfileRepository.saveAndFlush(userProfile);

    // Get all the userProfileList where uuid not equals to DEFAULT_UUID
    defaultUserProfileShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the userProfileList where uuid not equals to UPDATED_UUID
    defaultUserProfileShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllUserProfilesByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    userProfileRepository.saveAndFlush(userProfile);

    // Get all the userProfileList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultUserProfileShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the userProfileList where uuid equals to UPDATED_UUID
    defaultUserProfileShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllUserProfilesByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    userProfileRepository.saveAndFlush(userProfile);

    // Get all the userProfileList where uuid is not null
    defaultUserProfileShouldBeFound("uuid.specified=true");

    // Get all the userProfileList where uuid is null
    defaultUserProfileShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllUserProfilesByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    userProfileRepository.saveAndFlush(userProfile);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    userProfile.setBaseInfo(baseInfo);
    userProfileRepository.saveAndFlush(userProfile);
    Long baseInfoId = baseInfo.getId();

    // Get all the userProfileList where baseInfo equals to baseInfoId
    defaultUserProfileShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the userProfileList where baseInfo equals to (baseInfoId + 1)
    defaultUserProfileShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultUserProfileShouldBeFound(String filter) throws Exception {
    restUserProfileMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(userProfile.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].work").value(hasItem(DEFAULT_WORK.toString())))
      .andExpect(jsonPath("$.[*].education").value(hasItem(DEFAULT_EDUCATION.toString())))
      .andExpect(jsonPath("$.[*].placesLived").value(hasItem(DEFAULT_PLACES_LIVED.toString())))
      .andExpect(jsonPath("$.[*].contactInfo").value(hasItem(DEFAULT_CONTACT_INFO.toString())))
      .andExpect(jsonPath("$.[*].webSocialLinks").value(hasItem(DEFAULT_WEB_SOCIAL_LINKS.toString())))
      .andExpect(jsonPath("$.[*].basicInfo").value(hasItem(DEFAULT_BASIC_INFO.toString())))
      .andExpect(jsonPath("$.[*].relationshipInfo").value(hasItem(DEFAULT_RELATIONSHIP_INFO.toString())))
      .andExpect(jsonPath("$.[*].family").value(hasItem(DEFAULT_FAMILY.toString())))
      .andExpect(jsonPath("$.[*].detailAbout").value(hasItem(DEFAULT_DETAIL_ABOUT.toString())))
      .andExpect(jsonPath("$.[*].lifeEvents").value(hasItem(DEFAULT_LIFE_EVENTS.toString())))
      .andExpect(jsonPath("$.[*].hobbies").value(hasItem(DEFAULT_HOBBIES.toString())))
      .andExpect(jsonPath("$.[*].featured").value(hasItem(DEFAULT_FEATURED.toString())));

    // Check, that the count call also returns 1
    restUserProfileMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultUserProfileShouldNotBeFound(String filter) throws Exception {
    restUserProfileMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restUserProfileMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingUserProfile() throws Exception {
    // Get the userProfile
    restUserProfileMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewUserProfile() throws Exception {
    // Initialize the database
    userProfileRepository.saveAndFlush(userProfile);

    int databaseSizeBeforeUpdate = userProfileRepository.findAll().size();

    // Update the userProfile
    UserProfile updatedUserProfile = userProfileRepository.findById(userProfile.getId()).get();
    // Disconnect from session so that the updates on updatedUserProfile are not directly saved in db
    em.detach(updatedUserProfile);
    updatedUserProfile
      .uuid(UPDATED_UUID)
      .work(UPDATED_WORK)
      .education(UPDATED_EDUCATION)
      .placesLived(UPDATED_PLACES_LIVED)
      .contactInfo(UPDATED_CONTACT_INFO)
      .webSocialLinks(UPDATED_WEB_SOCIAL_LINKS)
      .basicInfo(UPDATED_BASIC_INFO)
      .relationshipInfo(UPDATED_RELATIONSHIP_INFO)
      .family(UPDATED_FAMILY)
      .detailAbout(UPDATED_DETAIL_ABOUT)
      .lifeEvents(UPDATED_LIFE_EVENTS)
      .hobbies(UPDATED_HOBBIES)
      .featured(UPDATED_FEATURED);
    UserProfileDTO userProfileDTO = userProfileMapper.toDto(updatedUserProfile);

    restUserProfileMockMvc
      .perform(
        put(ENTITY_API_URL_ID, userProfileDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(userProfileDTO))
      )
      .andExpect(status().isOk());

    // Validate the UserProfile in the database
    List<UserProfile> userProfileList = userProfileRepository.findAll();
    assertThat(userProfileList).hasSize(databaseSizeBeforeUpdate);
    UserProfile testUserProfile = userProfileList.get(userProfileList.size() - 1);
    assertThat(testUserProfile.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testUserProfile.getWork()).isEqualTo(UPDATED_WORK);
    assertThat(testUserProfile.getEducation()).isEqualTo(UPDATED_EDUCATION);
    assertThat(testUserProfile.getPlacesLived()).isEqualTo(UPDATED_PLACES_LIVED);
    assertThat(testUserProfile.getContactInfo()).isEqualTo(UPDATED_CONTACT_INFO);
    assertThat(testUserProfile.getWebSocialLinks()).isEqualTo(UPDATED_WEB_SOCIAL_LINKS);
    assertThat(testUserProfile.getBasicInfo()).isEqualTo(UPDATED_BASIC_INFO);
    assertThat(testUserProfile.getRelationshipInfo()).isEqualTo(UPDATED_RELATIONSHIP_INFO);
    assertThat(testUserProfile.getFamily()).isEqualTo(UPDATED_FAMILY);
    assertThat(testUserProfile.getDetailAbout()).isEqualTo(UPDATED_DETAIL_ABOUT);
    assertThat(testUserProfile.getLifeEvents()).isEqualTo(UPDATED_LIFE_EVENTS);
    assertThat(testUserProfile.getHobbies()).isEqualTo(UPDATED_HOBBIES);
    assertThat(testUserProfile.getFeatured()).isEqualTo(UPDATED_FEATURED);

    // Validate the UserProfile in Elasticsearch
    verify(mockUserProfileSearchRepository).save(testUserProfile);
  }

  @Test
  @Transactional
  void putNonExistingUserProfile() throws Exception {
    int databaseSizeBeforeUpdate = userProfileRepository.findAll().size();
    userProfile.setId(count.incrementAndGet());

    // Create the UserProfile
    UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restUserProfileMockMvc
      .perform(
        put(ENTITY_API_URL_ID, userProfileDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(userProfileDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the UserProfile in the database
    List<UserProfile> userProfileList = userProfileRepository.findAll();
    assertThat(userProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the UserProfile in Elasticsearch
    verify(mockUserProfileSearchRepository, times(0)).save(userProfile);
  }

  @Test
  @Transactional
  void putWithIdMismatchUserProfile() throws Exception {
    int databaseSizeBeforeUpdate = userProfileRepository.findAll().size();
    userProfile.setId(count.incrementAndGet());

    // Create the UserProfile
    UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restUserProfileMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(userProfileDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the UserProfile in the database
    List<UserProfile> userProfileList = userProfileRepository.findAll();
    assertThat(userProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the UserProfile in Elasticsearch
    verify(mockUserProfileSearchRepository, times(0)).save(userProfile);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamUserProfile() throws Exception {
    int databaseSizeBeforeUpdate = userProfileRepository.findAll().size();
    userProfile.setId(count.incrementAndGet());

    // Create the UserProfile
    UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restUserProfileMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userProfileDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the UserProfile in the database
    List<UserProfile> userProfileList = userProfileRepository.findAll();
    assertThat(userProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the UserProfile in Elasticsearch
    verify(mockUserProfileSearchRepository, times(0)).save(userProfile);
  }

  @Test
  @Transactional
  void partialUpdateUserProfileWithPatch() throws Exception {
    // Initialize the database
    userProfileRepository.saveAndFlush(userProfile);

    int databaseSizeBeforeUpdate = userProfileRepository.findAll().size();

    // Update the userProfile using partial update
    UserProfile partialUpdatedUserProfile = new UserProfile();
    partialUpdatedUserProfile.setId(userProfile.getId());

    partialUpdatedUserProfile
      .work(UPDATED_WORK)
      .education(UPDATED_EDUCATION)
      .contactInfo(UPDATED_CONTACT_INFO)
      .relationshipInfo(UPDATED_RELATIONSHIP_INFO)
      .family(UPDATED_FAMILY)
      .detailAbout(UPDATED_DETAIL_ABOUT)
      .hobbies(UPDATED_HOBBIES);

    restUserProfileMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedUserProfile.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserProfile))
      )
      .andExpect(status().isOk());

    // Validate the UserProfile in the database
    List<UserProfile> userProfileList = userProfileRepository.findAll();
    assertThat(userProfileList).hasSize(databaseSizeBeforeUpdate);
    UserProfile testUserProfile = userProfileList.get(userProfileList.size() - 1);
    assertThat(testUserProfile.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testUserProfile.getWork()).isEqualTo(UPDATED_WORK);
    assertThat(testUserProfile.getEducation()).isEqualTo(UPDATED_EDUCATION);
    assertThat(testUserProfile.getPlacesLived()).isEqualTo(DEFAULT_PLACES_LIVED);
    assertThat(testUserProfile.getContactInfo()).isEqualTo(UPDATED_CONTACT_INFO);
    assertThat(testUserProfile.getWebSocialLinks()).isEqualTo(DEFAULT_WEB_SOCIAL_LINKS);
    assertThat(testUserProfile.getBasicInfo()).isEqualTo(DEFAULT_BASIC_INFO);
    assertThat(testUserProfile.getRelationshipInfo()).isEqualTo(UPDATED_RELATIONSHIP_INFO);
    assertThat(testUserProfile.getFamily()).isEqualTo(UPDATED_FAMILY);
    assertThat(testUserProfile.getDetailAbout()).isEqualTo(UPDATED_DETAIL_ABOUT);
    assertThat(testUserProfile.getLifeEvents()).isEqualTo(DEFAULT_LIFE_EVENTS);
    assertThat(testUserProfile.getHobbies()).isEqualTo(UPDATED_HOBBIES);
    assertThat(testUserProfile.getFeatured()).isEqualTo(DEFAULT_FEATURED);
  }

  @Test
  @Transactional
  void fullUpdateUserProfileWithPatch() throws Exception {
    // Initialize the database
    userProfileRepository.saveAndFlush(userProfile);

    int databaseSizeBeforeUpdate = userProfileRepository.findAll().size();

    // Update the userProfile using partial update
    UserProfile partialUpdatedUserProfile = new UserProfile();
    partialUpdatedUserProfile.setId(userProfile.getId());

    partialUpdatedUserProfile
      .uuid(UPDATED_UUID)
      .work(UPDATED_WORK)
      .education(UPDATED_EDUCATION)
      .placesLived(UPDATED_PLACES_LIVED)
      .contactInfo(UPDATED_CONTACT_INFO)
      .webSocialLinks(UPDATED_WEB_SOCIAL_LINKS)
      .basicInfo(UPDATED_BASIC_INFO)
      .relationshipInfo(UPDATED_RELATIONSHIP_INFO)
      .family(UPDATED_FAMILY)
      .detailAbout(UPDATED_DETAIL_ABOUT)
      .lifeEvents(UPDATED_LIFE_EVENTS)
      .hobbies(UPDATED_HOBBIES)
      .featured(UPDATED_FEATURED);

    restUserProfileMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedUserProfile.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserProfile))
      )
      .andExpect(status().isOk());

    // Validate the UserProfile in the database
    List<UserProfile> userProfileList = userProfileRepository.findAll();
    assertThat(userProfileList).hasSize(databaseSizeBeforeUpdate);
    UserProfile testUserProfile = userProfileList.get(userProfileList.size() - 1);
    assertThat(testUserProfile.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testUserProfile.getWork()).isEqualTo(UPDATED_WORK);
    assertThat(testUserProfile.getEducation()).isEqualTo(UPDATED_EDUCATION);
    assertThat(testUserProfile.getPlacesLived()).isEqualTo(UPDATED_PLACES_LIVED);
    assertThat(testUserProfile.getContactInfo()).isEqualTo(UPDATED_CONTACT_INFO);
    assertThat(testUserProfile.getWebSocialLinks()).isEqualTo(UPDATED_WEB_SOCIAL_LINKS);
    assertThat(testUserProfile.getBasicInfo()).isEqualTo(UPDATED_BASIC_INFO);
    assertThat(testUserProfile.getRelationshipInfo()).isEqualTo(UPDATED_RELATIONSHIP_INFO);
    assertThat(testUserProfile.getFamily()).isEqualTo(UPDATED_FAMILY);
    assertThat(testUserProfile.getDetailAbout()).isEqualTo(UPDATED_DETAIL_ABOUT);
    assertThat(testUserProfile.getLifeEvents()).isEqualTo(UPDATED_LIFE_EVENTS);
    assertThat(testUserProfile.getHobbies()).isEqualTo(UPDATED_HOBBIES);
    assertThat(testUserProfile.getFeatured()).isEqualTo(UPDATED_FEATURED);
  }

  @Test
  @Transactional
  void patchNonExistingUserProfile() throws Exception {
    int databaseSizeBeforeUpdate = userProfileRepository.findAll().size();
    userProfile.setId(count.incrementAndGet());

    // Create the UserProfile
    UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restUserProfileMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, userProfileDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(userProfileDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the UserProfile in the database
    List<UserProfile> userProfileList = userProfileRepository.findAll();
    assertThat(userProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the UserProfile in Elasticsearch
    verify(mockUserProfileSearchRepository, times(0)).save(userProfile);
  }

  @Test
  @Transactional
  void patchWithIdMismatchUserProfile() throws Exception {
    int databaseSizeBeforeUpdate = userProfileRepository.findAll().size();
    userProfile.setId(count.incrementAndGet());

    // Create the UserProfile
    UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restUserProfileMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(userProfileDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the UserProfile in the database
    List<UserProfile> userProfileList = userProfileRepository.findAll();
    assertThat(userProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the UserProfile in Elasticsearch
    verify(mockUserProfileSearchRepository, times(0)).save(userProfile);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamUserProfile() throws Exception {
    int databaseSizeBeforeUpdate = userProfileRepository.findAll().size();
    userProfile.setId(count.incrementAndGet());

    // Create the UserProfile
    UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restUserProfileMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(userProfileDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the UserProfile in the database
    List<UserProfile> userProfileList = userProfileRepository.findAll();
    assertThat(userProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the UserProfile in Elasticsearch
    verify(mockUserProfileSearchRepository, times(0)).save(userProfile);
  }

  @Test
  @Transactional
  void deleteUserProfile() throws Exception {
    // Initialize the database
    userProfileRepository.saveAndFlush(userProfile);

    int databaseSizeBeforeDelete = userProfileRepository.findAll().size();

    // Delete the userProfile
    restUserProfileMockMvc
      .perform(delete(ENTITY_API_URL_ID, userProfile.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<UserProfile> userProfileList = userProfileRepository.findAll();
    assertThat(userProfileList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the UserProfile in Elasticsearch
    verify(mockUserProfileSearchRepository, times(1)).deleteById(userProfile.getId());
  }

  @Test
  @Transactional
  void searchUserProfile() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    userProfileRepository.saveAndFlush(userProfile);
    when(mockUserProfileSearchRepository.search(queryStringQuery("id:" + userProfile.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(userProfile), PageRequest.of(0, 1), 1));

    // Search the userProfile
    restUserProfileMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + userProfile.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(userProfile.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].work").value(hasItem(DEFAULT_WORK.toString())))
      .andExpect(jsonPath("$.[*].education").value(hasItem(DEFAULT_EDUCATION.toString())))
      .andExpect(jsonPath("$.[*].placesLived").value(hasItem(DEFAULT_PLACES_LIVED.toString())))
      .andExpect(jsonPath("$.[*].contactInfo").value(hasItem(DEFAULT_CONTACT_INFO.toString())))
      .andExpect(jsonPath("$.[*].webSocialLinks").value(hasItem(DEFAULT_WEB_SOCIAL_LINKS.toString())))
      .andExpect(jsonPath("$.[*].basicInfo").value(hasItem(DEFAULT_BASIC_INFO.toString())))
      .andExpect(jsonPath("$.[*].relationshipInfo").value(hasItem(DEFAULT_RELATIONSHIP_INFO.toString())))
      .andExpect(jsonPath("$.[*].family").value(hasItem(DEFAULT_FAMILY.toString())))
      .andExpect(jsonPath("$.[*].detailAbout").value(hasItem(DEFAULT_DETAIL_ABOUT.toString())))
      .andExpect(jsonPath("$.[*].lifeEvents").value(hasItem(DEFAULT_LIFE_EVENTS.toString())))
      .andExpect(jsonPath("$.[*].hobbies").value(hasItem(DEFAULT_HOBBIES.toString())))
      .andExpect(jsonPath("$.[*].featured").value(hasItem(DEFAULT_FEATURED.toString())));
  }
}
