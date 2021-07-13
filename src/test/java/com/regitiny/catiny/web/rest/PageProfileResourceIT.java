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
import com.regitiny.catiny.domain.PagePost;
import com.regitiny.catiny.domain.PageProfile;
import com.regitiny.catiny.repository.PageProfileRepository;
import com.regitiny.catiny.repository.search.PageProfileSearchRepository;
import com.regitiny.catiny.service.criteria.PageProfileCriteria;
import com.regitiny.catiny.service.dto.PageProfileDTO;
import com.regitiny.catiny.service.mapper.PageProfileMapper;
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
 * Integration tests for the {@link PageProfileResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class PageProfileResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String ENTITY_API_URL = "/api/page-profiles";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/page-profiles";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private PageProfileRepository pageProfileRepository;

  @Autowired
  private PageProfileMapper pageProfileMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.PageProfileSearchRepositoryMockConfiguration
   */
  @Autowired
  private PageProfileSearchRepository mockPageProfileSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restPageProfileMockMvc;

  private PageProfile pageProfile;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static PageProfile createEntity(EntityManager em) {
    PageProfile pageProfile = new PageProfile().uuid(DEFAULT_UUID);
    return pageProfile;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static PageProfile createUpdatedEntity(EntityManager em) {
    PageProfile pageProfile = new PageProfile().uuid(UPDATED_UUID);
    return pageProfile;
  }

  @BeforeEach
  public void initTest() {
    pageProfile = createEntity(em);
  }

  @Test
  @Transactional
  void createPageProfile() throws Exception {
    int databaseSizeBeforeCreate = pageProfileRepository.findAll().size();
    // Create the PageProfile
    PageProfileDTO pageProfileDTO = pageProfileMapper.toDto(pageProfile);
    restPageProfileMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pageProfileDTO)))
      .andExpect(status().isCreated());

    // Validate the PageProfile in the database
    List<PageProfile> pageProfileList = pageProfileRepository.findAll();
    assertThat(pageProfileList).hasSize(databaseSizeBeforeCreate + 1);
    PageProfile testPageProfile = pageProfileList.get(pageProfileList.size() - 1);
    assertThat(testPageProfile.getUuid()).isEqualTo(DEFAULT_UUID);

    // Validate the PageProfile in Elasticsearch
    verify(mockPageProfileSearchRepository, times(1)).save(testPageProfile);
  }

  @Test
  @Transactional
  void createPageProfileWithExistingId() throws Exception {
    // Create the PageProfile with an existing ID
    pageProfile.setId(1L);
    PageProfileDTO pageProfileDTO = pageProfileMapper.toDto(pageProfile);

    int databaseSizeBeforeCreate = pageProfileRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restPageProfileMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pageProfileDTO)))
      .andExpect(status().isBadRequest());

    // Validate the PageProfile in the database
    List<PageProfile> pageProfileList = pageProfileRepository.findAll();
    assertThat(pageProfileList).hasSize(databaseSizeBeforeCreate);

    // Validate the PageProfile in Elasticsearch
    verify(mockPageProfileSearchRepository, times(0)).save(pageProfile);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = pageProfileRepository.findAll().size();
    // set the field null
    pageProfile.setUuid(null);

    // Create the PageProfile, which fails.
    PageProfileDTO pageProfileDTO = pageProfileMapper.toDto(pageProfile);

    restPageProfileMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pageProfileDTO)))
      .andExpect(status().isBadRequest());

    List<PageProfile> pageProfileList = pageProfileRepository.findAll();
    assertThat(pageProfileList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllPageProfiles() throws Exception {
    // Initialize the database
    pageProfileRepository.saveAndFlush(pageProfile);

    // Get all the pageProfileList
    restPageProfileMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(pageProfile.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
  }

  @Test
  @Transactional
  void getPageProfile() throws Exception {
    // Initialize the database
    pageProfileRepository.saveAndFlush(pageProfile);

    // Get the pageProfile
    restPageProfileMockMvc
      .perform(get(ENTITY_API_URL_ID, pageProfile.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(pageProfile.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()));
  }

  @Test
  @Transactional
  void getPageProfilesByIdFiltering() throws Exception {
    // Initialize the database
    pageProfileRepository.saveAndFlush(pageProfile);

    Long id = pageProfile.getId();

    defaultPageProfileShouldBeFound("id.equals=" + id);
    defaultPageProfileShouldNotBeFound("id.notEquals=" + id);

    defaultPageProfileShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultPageProfileShouldNotBeFound("id.greaterThan=" + id);

    defaultPageProfileShouldBeFound("id.lessThanOrEqual=" + id);
    defaultPageProfileShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllPageProfilesByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    pageProfileRepository.saveAndFlush(pageProfile);

    // Get all the pageProfileList where uuid equals to DEFAULT_UUID
    defaultPageProfileShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the pageProfileList where uuid equals to UPDATED_UUID
    defaultPageProfileShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPageProfilesByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    pageProfileRepository.saveAndFlush(pageProfile);

    // Get all the pageProfileList where uuid not equals to DEFAULT_UUID
    defaultPageProfileShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the pageProfileList where uuid not equals to UPDATED_UUID
    defaultPageProfileShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPageProfilesByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    pageProfileRepository.saveAndFlush(pageProfile);

    // Get all the pageProfileList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultPageProfileShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the pageProfileList where uuid equals to UPDATED_UUID
    defaultPageProfileShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllPageProfilesByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    pageProfileRepository.saveAndFlush(pageProfile);

    // Get all the pageProfileList where uuid is not null
    defaultPageProfileShouldBeFound("uuid.specified=true");

    // Get all the pageProfileList where uuid is null
    defaultPageProfileShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllPageProfilesByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    pageProfileRepository.saveAndFlush(pageProfile);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    pageProfile.setBaseInfo(baseInfo);
    pageProfileRepository.saveAndFlush(pageProfile);
    Long baseInfoId = baseInfo.getId();

    // Get all the pageProfileList where baseInfo equals to baseInfoId
    defaultPageProfileShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the pageProfileList where baseInfo equals to (baseInfoId + 1)
    defaultPageProfileShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllPageProfilesByPageIsEqualToSomething() throws Exception {
    // Initialize the database
    pageProfileRepository.saveAndFlush(pageProfile);
    PagePost page = PagePostResourceIT.createEntity(em);
    em.persist(page);
    em.flush();
    pageProfile.setPage(page);
    page.setProfile(pageProfile);
    pageProfileRepository.saveAndFlush(pageProfile);
    Long pageId = page.getId();

    // Get all the pageProfileList where page equals to pageId
    defaultPageProfileShouldBeFound("pageId.equals=" + pageId);

    // Get all the pageProfileList where page equals to (pageId + 1)
    defaultPageProfileShouldNotBeFound("pageId.equals=" + (pageId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultPageProfileShouldBeFound(String filter) throws Exception {
    restPageProfileMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(pageProfile.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));

    // Check, that the count call also returns 1
    restPageProfileMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultPageProfileShouldNotBeFound(String filter) throws Exception {
    restPageProfileMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restPageProfileMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingPageProfile() throws Exception {
    // Get the pageProfile
    restPageProfileMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewPageProfile() throws Exception {
    // Initialize the database
    pageProfileRepository.saveAndFlush(pageProfile);

    int databaseSizeBeforeUpdate = pageProfileRepository.findAll().size();

    // Update the pageProfile
    PageProfile updatedPageProfile = pageProfileRepository.findById(pageProfile.getId()).get();
    // Disconnect from session so that the updates on updatedPageProfile are not directly saved in db
    em.detach(updatedPageProfile);
    updatedPageProfile.uuid(UPDATED_UUID);
    PageProfileDTO pageProfileDTO = pageProfileMapper.toDto(updatedPageProfile);

    restPageProfileMockMvc
      .perform(
        put(ENTITY_API_URL_ID, pageProfileDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(pageProfileDTO))
      )
      .andExpect(status().isOk());

    // Validate the PageProfile in the database
    List<PageProfile> pageProfileList = pageProfileRepository.findAll();
    assertThat(pageProfileList).hasSize(databaseSizeBeforeUpdate);
    PageProfile testPageProfile = pageProfileList.get(pageProfileList.size() - 1);
    assertThat(testPageProfile.getUuid()).isEqualTo(UPDATED_UUID);

    // Validate the PageProfile in Elasticsearch
    verify(mockPageProfileSearchRepository).save(testPageProfile);
  }

  @Test
  @Transactional
  void putNonExistingPageProfile() throws Exception {
    int databaseSizeBeforeUpdate = pageProfileRepository.findAll().size();
    pageProfile.setId(count.incrementAndGet());

    // Create the PageProfile
    PageProfileDTO pageProfileDTO = pageProfileMapper.toDto(pageProfile);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restPageProfileMockMvc
      .perform(
        put(ENTITY_API_URL_ID, pageProfileDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(pageProfileDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PageProfile in the database
    List<PageProfile> pageProfileList = pageProfileRepository.findAll();
    assertThat(pageProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PageProfile in Elasticsearch
    verify(mockPageProfileSearchRepository, times(0)).save(pageProfile);
  }

  @Test
  @Transactional
  void putWithIdMismatchPageProfile() throws Exception {
    int databaseSizeBeforeUpdate = pageProfileRepository.findAll().size();
    pageProfile.setId(count.incrementAndGet());

    // Create the PageProfile
    PageProfileDTO pageProfileDTO = pageProfileMapper.toDto(pageProfile);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPageProfileMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(pageProfileDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PageProfile in the database
    List<PageProfile> pageProfileList = pageProfileRepository.findAll();
    assertThat(pageProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PageProfile in Elasticsearch
    verify(mockPageProfileSearchRepository, times(0)).save(pageProfile);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamPageProfile() throws Exception {
    int databaseSizeBeforeUpdate = pageProfileRepository.findAll().size();
    pageProfile.setId(count.incrementAndGet());

    // Create the PageProfile
    PageProfileDTO pageProfileDTO = pageProfileMapper.toDto(pageProfile);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPageProfileMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pageProfileDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the PageProfile in the database
    List<PageProfile> pageProfileList = pageProfileRepository.findAll();
    assertThat(pageProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PageProfile in Elasticsearch
    verify(mockPageProfileSearchRepository, times(0)).save(pageProfile);
  }

  @Test
  @Transactional
  void partialUpdatePageProfileWithPatch() throws Exception {
    // Initialize the database
    pageProfileRepository.saveAndFlush(pageProfile);

    int databaseSizeBeforeUpdate = pageProfileRepository.findAll().size();

    // Update the pageProfile using partial update
    PageProfile partialUpdatedPageProfile = new PageProfile();
    partialUpdatedPageProfile.setId(pageProfile.getId());

    partialUpdatedPageProfile.uuid(UPDATED_UUID);

    restPageProfileMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedPageProfile.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPageProfile))
      )
      .andExpect(status().isOk());

    // Validate the PageProfile in the database
    List<PageProfile> pageProfileList = pageProfileRepository.findAll();
    assertThat(pageProfileList).hasSize(databaseSizeBeforeUpdate);
    PageProfile testPageProfile = pageProfileList.get(pageProfileList.size() - 1);
    assertThat(testPageProfile.getUuid()).isEqualTo(UPDATED_UUID);
  }

  @Test
  @Transactional
  void fullUpdatePageProfileWithPatch() throws Exception {
    // Initialize the database
    pageProfileRepository.saveAndFlush(pageProfile);

    int databaseSizeBeforeUpdate = pageProfileRepository.findAll().size();

    // Update the pageProfile using partial update
    PageProfile partialUpdatedPageProfile = new PageProfile();
    partialUpdatedPageProfile.setId(pageProfile.getId());

    partialUpdatedPageProfile.uuid(UPDATED_UUID);

    restPageProfileMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedPageProfile.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPageProfile))
      )
      .andExpect(status().isOk());

    // Validate the PageProfile in the database
    List<PageProfile> pageProfileList = pageProfileRepository.findAll();
    assertThat(pageProfileList).hasSize(databaseSizeBeforeUpdate);
    PageProfile testPageProfile = pageProfileList.get(pageProfileList.size() - 1);
    assertThat(testPageProfile.getUuid()).isEqualTo(UPDATED_UUID);
  }

  @Test
  @Transactional
  void patchNonExistingPageProfile() throws Exception {
    int databaseSizeBeforeUpdate = pageProfileRepository.findAll().size();
    pageProfile.setId(count.incrementAndGet());

    // Create the PageProfile
    PageProfileDTO pageProfileDTO = pageProfileMapper.toDto(pageProfile);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restPageProfileMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, pageProfileDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(pageProfileDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PageProfile in the database
    List<PageProfile> pageProfileList = pageProfileRepository.findAll();
    assertThat(pageProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PageProfile in Elasticsearch
    verify(mockPageProfileSearchRepository, times(0)).save(pageProfile);
  }

  @Test
  @Transactional
  void patchWithIdMismatchPageProfile() throws Exception {
    int databaseSizeBeforeUpdate = pageProfileRepository.findAll().size();
    pageProfile.setId(count.incrementAndGet());

    // Create the PageProfile
    PageProfileDTO pageProfileDTO = pageProfileMapper.toDto(pageProfile);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPageProfileMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(pageProfileDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PageProfile in the database
    List<PageProfile> pageProfileList = pageProfileRepository.findAll();
    assertThat(pageProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PageProfile in Elasticsearch
    verify(mockPageProfileSearchRepository, times(0)).save(pageProfile);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamPageProfile() throws Exception {
    int databaseSizeBeforeUpdate = pageProfileRepository.findAll().size();
    pageProfile.setId(count.incrementAndGet());

    // Create the PageProfile
    PageProfileDTO pageProfileDTO = pageProfileMapper.toDto(pageProfile);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restPageProfileMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(pageProfileDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the PageProfile in the database
    List<PageProfile> pageProfileList = pageProfileRepository.findAll();
    assertThat(pageProfileList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PageProfile in Elasticsearch
    verify(mockPageProfileSearchRepository, times(0)).save(pageProfile);
  }

  @Test
  @Transactional
  void deletePageProfile() throws Exception {
    // Initialize the database
    pageProfileRepository.saveAndFlush(pageProfile);

    int databaseSizeBeforeDelete = pageProfileRepository.findAll().size();

    // Delete the pageProfile
    restPageProfileMockMvc
      .perform(delete(ENTITY_API_URL_ID, pageProfile.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<PageProfile> pageProfileList = pageProfileRepository.findAll();
    assertThat(pageProfileList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the PageProfile in Elasticsearch
    verify(mockPageProfileSearchRepository, times(1)).deleteById(pageProfile.getId());
  }

  @Test
  @Transactional
  void searchPageProfile() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    pageProfileRepository.saveAndFlush(pageProfile);
    when(mockPageProfileSearchRepository.search(queryStringQuery("id:" + pageProfile.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(pageProfile), PageRequest.of(0, 1), 1));

    // Search the pageProfile
    restPageProfileMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + pageProfile.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(pageProfile.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
  }
}
