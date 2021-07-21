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
import com.regitiny.catiny.domain.FollowPage;
import com.regitiny.catiny.domain.PagePost;
import com.regitiny.catiny.repository.FollowPageRepository;
import com.regitiny.catiny.repository.search.FollowPageSearchRepository;
import com.regitiny.catiny.service.criteria.FollowPageCriteria;
import com.regitiny.catiny.service.dto.FollowPageDTO;
import com.regitiny.catiny.service.mapper.FollowPageMapper;
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
 * Integration tests for the {@link FollowPageResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class FollowPageResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String ENTITY_API_URL = "/api/follow-pages";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/follow-pages";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private FollowPageRepository followPageRepository;

  @Autowired
  private FollowPageMapper followPageMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.FollowPageSearchRepositoryMockConfiguration
   */
  @Autowired
  private FollowPageSearchRepository mockFollowPageSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restFollowPageMockMvc;

  private FollowPage followPage;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static FollowPage createEntity(EntityManager em) {
    FollowPage followPage = new FollowPage().uuid(DEFAULT_UUID);
    return followPage;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static FollowPage createUpdatedEntity(EntityManager em) {
    FollowPage followPage = new FollowPage().uuid(UPDATED_UUID);
    return followPage;
  }

  @BeforeEach
  public void initTest() {
    followPage = createEntity(em);
  }

  @Test
  @Transactional
  void createFollowPage() throws Exception {
    int databaseSizeBeforeCreate = followPageRepository.findAll().size();
    // Create the FollowPage
    FollowPageDTO followPageDTO = followPageMapper.toDto(followPage);
    restFollowPageMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(followPageDTO)))
      .andExpect(status().isCreated());

    // Validate the FollowPage in the database
    List<FollowPage> followPageList = followPageRepository.findAll();
    assertThat(followPageList).hasSize(databaseSizeBeforeCreate + 1);
    FollowPage testFollowPage = followPageList.get(followPageList.size() - 1);
    assertThat(testFollowPage.getUuid()).isEqualTo(DEFAULT_UUID);

    // Validate the FollowPage in Elasticsearch
    verify(mockFollowPageSearchRepository, times(1)).save(testFollowPage);
  }

  @Test
  @Transactional
  void createFollowPageWithExistingId() throws Exception {
    // Create the FollowPage with an existing ID
    followPage.setId(1L);
    FollowPageDTO followPageDTO = followPageMapper.toDto(followPage);

    int databaseSizeBeforeCreate = followPageRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restFollowPageMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(followPageDTO)))
      .andExpect(status().isBadRequest());

    // Validate the FollowPage in the database
    List<FollowPage> followPageList = followPageRepository.findAll();
    assertThat(followPageList).hasSize(databaseSizeBeforeCreate);

    // Validate the FollowPage in Elasticsearch
    verify(mockFollowPageSearchRepository, times(0)).save(followPage);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = followPageRepository.findAll().size();
    // set the field null
    followPage.setUuid(null);

    // Create the FollowPage, which fails.
    FollowPageDTO followPageDTO = followPageMapper.toDto(followPage);

    restFollowPageMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(followPageDTO)))
      .andExpect(status().isBadRequest());

    List<FollowPage> followPageList = followPageRepository.findAll();
    assertThat(followPageList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllFollowPages() throws Exception {
    // Initialize the database
    followPageRepository.saveAndFlush(followPage);

    // Get all the followPageList
    restFollowPageMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(followPage.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
  }

  @Test
  @Transactional
  void getFollowPage() throws Exception {
    // Initialize the database
    followPageRepository.saveAndFlush(followPage);

    // Get the followPage
    restFollowPageMockMvc
      .perform(get(ENTITY_API_URL_ID, followPage.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(followPage.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()));
  }

  @Test
  @Transactional
  void getFollowPagesByIdFiltering() throws Exception {
    // Initialize the database
    followPageRepository.saveAndFlush(followPage);

    Long id = followPage.getId();

    defaultFollowPageShouldBeFound("id.equals=" + id);
    defaultFollowPageShouldNotBeFound("id.notEquals=" + id);

    defaultFollowPageShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultFollowPageShouldNotBeFound("id.greaterThan=" + id);

    defaultFollowPageShouldBeFound("id.lessThanOrEqual=" + id);
    defaultFollowPageShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllFollowPagesByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    followPageRepository.saveAndFlush(followPage);

    // Get all the followPageList where uuid equals to DEFAULT_UUID
    defaultFollowPageShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the followPageList where uuid equals to UPDATED_UUID
    defaultFollowPageShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFollowPagesByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    followPageRepository.saveAndFlush(followPage);

    // Get all the followPageList where uuid not equals to DEFAULT_UUID
    defaultFollowPageShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the followPageList where uuid not equals to UPDATED_UUID
    defaultFollowPageShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFollowPagesByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    followPageRepository.saveAndFlush(followPage);

    // Get all the followPageList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultFollowPageShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the followPageList where uuid equals to UPDATED_UUID
    defaultFollowPageShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllFollowPagesByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    followPageRepository.saveAndFlush(followPage);

    // Get all the followPageList where uuid is not null
    defaultFollowPageShouldBeFound("uuid.specified=true");

    // Get all the followPageList where uuid is null
    defaultFollowPageShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllFollowPagesByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    followPageRepository.saveAndFlush(followPage);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    followPage.setBaseInfo(baseInfo);
    followPageRepository.saveAndFlush(followPage);
    Long baseInfoId = baseInfo.getId();

    // Get all the followPageList where baseInfo equals to baseInfoId
    defaultFollowPageShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the followPageList where baseInfo equals to (baseInfoId + 1)
    defaultFollowPageShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllFollowPagesByFollowPageDetailsIsEqualToSomething() throws Exception {
    // Initialize the database
    followPageRepository.saveAndFlush(followPage);
    PagePost followPageDetails = PagePostResourceIT.createEntity(em);
    em.persist(followPageDetails);
    em.flush();
    followPage.setFollowPageDetails(followPageDetails);
    followPageRepository.saveAndFlush(followPage);
    Long followPageDetailsId = followPageDetails.getId();

    // Get all the followPageList where followPageDetails equals to followPageDetailsId
    defaultFollowPageShouldBeFound("followPageDetailsId.equals=" + followPageDetailsId);

    // Get all the followPageList where followPageDetails equals to (followPageDetailsId + 1)
    defaultFollowPageShouldNotBeFound("followPageDetailsId.equals=" + (followPageDetailsId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultFollowPageShouldBeFound(String filter) throws Exception {
    restFollowPageMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(followPage.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));

    // Check, that the count call also returns 1
    restFollowPageMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultFollowPageShouldNotBeFound(String filter) throws Exception {
    restFollowPageMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restFollowPageMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingFollowPage() throws Exception {
    // Get the followPage
    restFollowPageMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewFollowPage() throws Exception {
    // Initialize the database
    followPageRepository.saveAndFlush(followPage);

    int databaseSizeBeforeUpdate = followPageRepository.findAll().size();

    // Update the followPage
    FollowPage updatedFollowPage = followPageRepository.findById(followPage.getId()).get();
    // Disconnect from session so that the updates on updatedFollowPage are not directly saved in db
    em.detach(updatedFollowPage);
    updatedFollowPage.uuid(UPDATED_UUID);
    FollowPageDTO followPageDTO = followPageMapper.toDto(updatedFollowPage);

    restFollowPageMockMvc
      .perform(
        put(ENTITY_API_URL_ID, followPageDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(followPageDTO))
      )
      .andExpect(status().isOk());

    // Validate the FollowPage in the database
    List<FollowPage> followPageList = followPageRepository.findAll();
    assertThat(followPageList).hasSize(databaseSizeBeforeUpdate);
    FollowPage testFollowPage = followPageList.get(followPageList.size() - 1);
    assertThat(testFollowPage.getUuid()).isEqualTo(UPDATED_UUID);

    // Validate the FollowPage in Elasticsearch
    verify(mockFollowPageSearchRepository).save(testFollowPage);
  }

  @Test
  @Transactional
  void putNonExistingFollowPage() throws Exception {
    int databaseSizeBeforeUpdate = followPageRepository.findAll().size();
    followPage.setId(count.incrementAndGet());

    // Create the FollowPage
    FollowPageDTO followPageDTO = followPageMapper.toDto(followPage);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restFollowPageMockMvc
      .perform(
        put(ENTITY_API_URL_ID, followPageDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(followPageDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FollowPage in the database
    List<FollowPage> followPageList = followPageRepository.findAll();
    assertThat(followPageList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowPage in Elasticsearch
    verify(mockFollowPageSearchRepository, times(0)).save(followPage);
  }

  @Test
  @Transactional
  void putWithIdMismatchFollowPage() throws Exception {
    int databaseSizeBeforeUpdate = followPageRepository.findAll().size();
    followPage.setId(count.incrementAndGet());

    // Create the FollowPage
    FollowPageDTO followPageDTO = followPageMapper.toDto(followPage);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFollowPageMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(followPageDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FollowPage in the database
    List<FollowPage> followPageList = followPageRepository.findAll();
    assertThat(followPageList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowPage in Elasticsearch
    verify(mockFollowPageSearchRepository, times(0)).save(followPage);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamFollowPage() throws Exception {
    int databaseSizeBeforeUpdate = followPageRepository.findAll().size();
    followPage.setId(count.incrementAndGet());

    // Create the FollowPage
    FollowPageDTO followPageDTO = followPageMapper.toDto(followPage);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFollowPageMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(followPageDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the FollowPage in the database
    List<FollowPage> followPageList = followPageRepository.findAll();
    assertThat(followPageList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowPage in Elasticsearch
    verify(mockFollowPageSearchRepository, times(0)).save(followPage);
  }

  @Test
  @Transactional
  void partialUpdateFollowPageWithPatch() throws Exception {
    // Initialize the database
    followPageRepository.saveAndFlush(followPage);

    int databaseSizeBeforeUpdate = followPageRepository.findAll().size();

    // Update the followPage using partial update
    FollowPage partialUpdatedFollowPage = new FollowPage();
    partialUpdatedFollowPage.setId(followPage.getId());

    restFollowPageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedFollowPage.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFollowPage))
      )
      .andExpect(status().isOk());

    // Validate the FollowPage in the database
    List<FollowPage> followPageList = followPageRepository.findAll();
    assertThat(followPageList).hasSize(databaseSizeBeforeUpdate);
    FollowPage testFollowPage = followPageList.get(followPageList.size() - 1);
    assertThat(testFollowPage.getUuid()).isEqualTo(DEFAULT_UUID);
  }

  @Test
  @Transactional
  void fullUpdateFollowPageWithPatch() throws Exception {
    // Initialize the database
    followPageRepository.saveAndFlush(followPage);

    int databaseSizeBeforeUpdate = followPageRepository.findAll().size();

    // Update the followPage using partial update
    FollowPage partialUpdatedFollowPage = new FollowPage();
    partialUpdatedFollowPage.setId(followPage.getId());

    partialUpdatedFollowPage.uuid(UPDATED_UUID);

    restFollowPageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedFollowPage.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFollowPage))
      )
      .andExpect(status().isOk());

    // Validate the FollowPage in the database
    List<FollowPage> followPageList = followPageRepository.findAll();
    assertThat(followPageList).hasSize(databaseSizeBeforeUpdate);
    FollowPage testFollowPage = followPageList.get(followPageList.size() - 1);
    assertThat(testFollowPage.getUuid()).isEqualTo(UPDATED_UUID);
  }

  @Test
  @Transactional
  void patchNonExistingFollowPage() throws Exception {
    int databaseSizeBeforeUpdate = followPageRepository.findAll().size();
    followPage.setId(count.incrementAndGet());

    // Create the FollowPage
    FollowPageDTO followPageDTO = followPageMapper.toDto(followPage);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restFollowPageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, followPageDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(followPageDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FollowPage in the database
    List<FollowPage> followPageList = followPageRepository.findAll();
    assertThat(followPageList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowPage in Elasticsearch
    verify(mockFollowPageSearchRepository, times(0)).save(followPage);
  }

  @Test
  @Transactional
  void patchWithIdMismatchFollowPage() throws Exception {
    int databaseSizeBeforeUpdate = followPageRepository.findAll().size();
    followPage.setId(count.incrementAndGet());

    // Create the FollowPage
    FollowPageDTO followPageDTO = followPageMapper.toDto(followPage);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFollowPageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(followPageDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FollowPage in the database
    List<FollowPage> followPageList = followPageRepository.findAll();
    assertThat(followPageList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowPage in Elasticsearch
    verify(mockFollowPageSearchRepository, times(0)).save(followPage);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamFollowPage() throws Exception {
    int databaseSizeBeforeUpdate = followPageRepository.findAll().size();
    followPage.setId(count.incrementAndGet());

    // Create the FollowPage
    FollowPageDTO followPageDTO = followPageMapper.toDto(followPage);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFollowPageMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(followPageDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the FollowPage in the database
    List<FollowPage> followPageList = followPageRepository.findAll();
    assertThat(followPageList).hasSize(databaseSizeBeforeUpdate);

    // Validate the FollowPage in Elasticsearch
    verify(mockFollowPageSearchRepository, times(0)).save(followPage);
  }

  @Test
  @Transactional
  void deleteFollowPage() throws Exception {
    // Initialize the database
    followPageRepository.saveAndFlush(followPage);

    int databaseSizeBeforeDelete = followPageRepository.findAll().size();

    // Delete the followPage
    restFollowPageMockMvc
      .perform(delete(ENTITY_API_URL_ID, followPage.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<FollowPage> followPageList = followPageRepository.findAll();
    assertThat(followPageList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the FollowPage in Elasticsearch
    verify(mockFollowPageSearchRepository, times(1)).deleteById(followPage.getId());
  }

  @Test
  @Transactional
  void searchFollowPage() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    followPageRepository.saveAndFlush(followPage);
    when(mockFollowPageSearchRepository.search(queryStringQuery("id:" + followPage.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(followPage), PageRequest.of(0, 1), 1));

    // Search the followPage
    restFollowPageMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + followPage.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(followPage.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())));
  }
}
