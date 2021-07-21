package com.regitiny.catiny.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.IntegrationTest;
import com.regitiny.catiny.domain.Album;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.Image;
import com.regitiny.catiny.repository.AlbumRepository;
import com.regitiny.catiny.repository.search.AlbumSearchRepository;
import com.regitiny.catiny.service.AlbumService;
import com.regitiny.catiny.service.criteria.AlbumCriteria;
import com.regitiny.catiny.service.dto.AlbumDTO;
import com.regitiny.catiny.service.mapper.AlbumMapper;
import java.util.ArrayList;
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
 * Integration tests for the {@link AlbumResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class AlbumResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_NOTE = "AAAAAAAAAA";
  private static final String UPDATED_NOTE = "BBBBBBBBBB";

  private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
  private static final String UPDATED_AVATAR = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/albums";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/albums";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private AlbumRepository albumRepository;

  @Mock
  private AlbumRepository albumRepositoryMock;

  @Autowired
  private AlbumMapper albumMapper;

  @Mock
  private AlbumService albumServiceMock;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.AlbumSearchRepositoryMockConfiguration
   */
  @Autowired
  private AlbumSearchRepository mockAlbumSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restAlbumMockMvc;

  private Album album;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Album createEntity(EntityManager em) {
    Album album = new Album().uuid(DEFAULT_UUID).name(DEFAULT_NAME).note(DEFAULT_NOTE).avatar(DEFAULT_AVATAR);
    return album;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Album createUpdatedEntity(EntityManager em) {
    Album album = new Album().uuid(UPDATED_UUID).name(UPDATED_NAME).note(UPDATED_NOTE).avatar(UPDATED_AVATAR);
    return album;
  }

  @BeforeEach
  public void initTest() {
    album = createEntity(em);
  }

  @Test
  @Transactional
  void createAlbum() throws Exception {
    int databaseSizeBeforeCreate = albumRepository.findAll().size();
    // Create the Album
    AlbumDTO albumDTO = albumMapper.toDto(album);
    restAlbumMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(albumDTO)))
      .andExpect(status().isCreated());

    // Validate the Album in the database
    List<Album> albumList = albumRepository.findAll();
    assertThat(albumList).hasSize(databaseSizeBeforeCreate + 1);
    Album testAlbum = albumList.get(albumList.size() - 1);
    assertThat(testAlbum.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testAlbum.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testAlbum.getNote()).isEqualTo(DEFAULT_NOTE);
    assertThat(testAlbum.getAvatar()).isEqualTo(DEFAULT_AVATAR);

    // Validate the Album in Elasticsearch
    verify(mockAlbumSearchRepository, times(1)).save(testAlbum);
  }

  @Test
  @Transactional
  void createAlbumWithExistingId() throws Exception {
    // Create the Album with an existing ID
    album.setId(1L);
    AlbumDTO albumDTO = albumMapper.toDto(album);

    int databaseSizeBeforeCreate = albumRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restAlbumMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(albumDTO)))
      .andExpect(status().isBadRequest());

    // Validate the Album in the database
    List<Album> albumList = albumRepository.findAll();
    assertThat(albumList).hasSize(databaseSizeBeforeCreate);

    // Validate the Album in Elasticsearch
    verify(mockAlbumSearchRepository, times(0)).save(album);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = albumRepository.findAll().size();
    // set the field null
    album.setUuid(null);

    // Create the Album, which fails.
    AlbumDTO albumDTO = albumMapper.toDto(album);

    restAlbumMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(albumDTO)))
      .andExpect(status().isBadRequest());

    List<Album> albumList = albumRepository.findAll();
    assertThat(albumList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void checkNameIsRequired() throws Exception {
    int databaseSizeBeforeTest = albumRepository.findAll().size();
    // set the field null
    album.setName(null);

    // Create the Album, which fails.
    AlbumDTO albumDTO = albumMapper.toDto(album);

    restAlbumMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(albumDTO)))
      .andExpect(status().isBadRequest());

    List<Album> albumList = albumRepository.findAll();
    assertThat(albumList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllAlbums() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList
    restAlbumMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(album.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())));
  }

  @SuppressWarnings({ "unchecked" })
  void getAllAlbumsWithEagerRelationshipsIsEnabled() throws Exception {
    when(albumServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

    restAlbumMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

    verify(albumServiceMock, times(1)).findAllWithEagerRelationships(any());
  }

  @SuppressWarnings({ "unchecked" })
  void getAllAlbumsWithEagerRelationshipsIsNotEnabled() throws Exception {
    when(albumServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

    restAlbumMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

    verify(albumServiceMock, times(1)).findAllWithEagerRelationships(any());
  }

  @Test
  @Transactional
  void getAlbum() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get the album
    restAlbumMockMvc
      .perform(get(ENTITY_API_URL_ID, album.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(album.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
      .andExpect(jsonPath("$.note").value(DEFAULT_NOTE))
      .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR.toString()));
  }

  @Test
  @Transactional
  void getAlbumsByIdFiltering() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    Long id = album.getId();

    defaultAlbumShouldBeFound("id.equals=" + id);
    defaultAlbumShouldNotBeFound("id.notEquals=" + id);

    defaultAlbumShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultAlbumShouldNotBeFound("id.greaterThan=" + id);

    defaultAlbumShouldBeFound("id.lessThanOrEqual=" + id);
    defaultAlbumShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllAlbumsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where uuid equals to DEFAULT_UUID
    defaultAlbumShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the albumList where uuid equals to UPDATED_UUID
    defaultAlbumShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllAlbumsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where uuid not equals to DEFAULT_UUID
    defaultAlbumShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the albumList where uuid not equals to UPDATED_UUID
    defaultAlbumShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllAlbumsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultAlbumShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the albumList where uuid equals to UPDATED_UUID
    defaultAlbumShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllAlbumsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where uuid is not null
    defaultAlbumShouldBeFound("uuid.specified=true");

    // Get all the albumList where uuid is null
    defaultAlbumShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllAlbumsByNameIsEqualToSomething() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where name equals to DEFAULT_NAME
    defaultAlbumShouldBeFound("name.equals=" + DEFAULT_NAME);

    // Get all the albumList where name equals to UPDATED_NAME
    defaultAlbumShouldNotBeFound("name.equals=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllAlbumsByNameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where name not equals to DEFAULT_NAME
    defaultAlbumShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

    // Get all the albumList where name not equals to UPDATED_NAME
    defaultAlbumShouldBeFound("name.notEquals=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllAlbumsByNameIsInShouldWork() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where name in DEFAULT_NAME or UPDATED_NAME
    defaultAlbumShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

    // Get all the albumList where name equals to UPDATED_NAME
    defaultAlbumShouldNotBeFound("name.in=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllAlbumsByNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where name is not null
    defaultAlbumShouldBeFound("name.specified=true");

    // Get all the albumList where name is null
    defaultAlbumShouldNotBeFound("name.specified=false");
  }

  @Test
  @Transactional
  void getAllAlbumsByNameContainsSomething() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where name contains DEFAULT_NAME
    defaultAlbumShouldBeFound("name.contains=" + DEFAULT_NAME);

    // Get all the albumList where name contains UPDATED_NAME
    defaultAlbumShouldNotBeFound("name.contains=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllAlbumsByNameNotContainsSomething() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where name does not contain DEFAULT_NAME
    defaultAlbumShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

    // Get all the albumList where name does not contain UPDATED_NAME
    defaultAlbumShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllAlbumsByNoteIsEqualToSomething() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where note equals to DEFAULT_NOTE
    defaultAlbumShouldBeFound("note.equals=" + DEFAULT_NOTE);

    // Get all the albumList where note equals to UPDATED_NOTE
    defaultAlbumShouldNotBeFound("note.equals=" + UPDATED_NOTE);
  }

  @Test
  @Transactional
  void getAllAlbumsByNoteIsNotEqualToSomething() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where note not equals to DEFAULT_NOTE
    defaultAlbumShouldNotBeFound("note.notEquals=" + DEFAULT_NOTE);

    // Get all the albumList where note not equals to UPDATED_NOTE
    defaultAlbumShouldBeFound("note.notEquals=" + UPDATED_NOTE);
  }

  @Test
  @Transactional
  void getAllAlbumsByNoteIsInShouldWork() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where note in DEFAULT_NOTE or UPDATED_NOTE
    defaultAlbumShouldBeFound("note.in=" + DEFAULT_NOTE + "," + UPDATED_NOTE);

    // Get all the albumList where note equals to UPDATED_NOTE
    defaultAlbumShouldNotBeFound("note.in=" + UPDATED_NOTE);
  }

  @Test
  @Transactional
  void getAllAlbumsByNoteIsNullOrNotNull() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where note is not null
    defaultAlbumShouldBeFound("note.specified=true");

    // Get all the albumList where note is null
    defaultAlbumShouldNotBeFound("note.specified=false");
  }

  @Test
  @Transactional
  void getAllAlbumsByNoteContainsSomething() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where note contains DEFAULT_NOTE
    defaultAlbumShouldBeFound("note.contains=" + DEFAULT_NOTE);

    // Get all the albumList where note contains UPDATED_NOTE
    defaultAlbumShouldNotBeFound("note.contains=" + UPDATED_NOTE);
  }

  @Test
  @Transactional
  void getAllAlbumsByNoteNotContainsSomething() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    // Get all the albumList where note does not contain DEFAULT_NOTE
    defaultAlbumShouldNotBeFound("note.doesNotContain=" + DEFAULT_NOTE);

    // Get all the albumList where note does not contain UPDATED_NOTE
    defaultAlbumShouldBeFound("note.doesNotContain=" + UPDATED_NOTE);
  }

  @Test
  @Transactional
  void getAllAlbumsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    album.setBaseInfo(baseInfo);
    albumRepository.saveAndFlush(album);
    Long baseInfoId = baseInfo.getId();

    // Get all the albumList where baseInfo equals to baseInfoId
    defaultAlbumShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the albumList where baseInfo equals to (baseInfoId + 1)
    defaultAlbumShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllAlbumsByImageIsEqualToSomething() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);
    Image image = ImageResourceIT.createEntity(em);
    em.persist(image);
    em.flush();
    album.addImage(image);
    albumRepository.saveAndFlush(album);
    Long imageId = image.getId();

    // Get all the albumList where image equals to imageId
    defaultAlbumShouldBeFound("imageId.equals=" + imageId);

    // Get all the albumList where image equals to (imageId + 1)
    defaultAlbumShouldNotBeFound("imageId.equals=" + (imageId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultAlbumShouldBeFound(String filter) throws Exception {
    restAlbumMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(album.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())));

    // Check, that the count call also returns 1
    restAlbumMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultAlbumShouldNotBeFound(String filter) throws Exception {
    restAlbumMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restAlbumMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingAlbum() throws Exception {
    // Get the album
    restAlbumMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewAlbum() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    int databaseSizeBeforeUpdate = albumRepository.findAll().size();

    // Update the album
    Album updatedAlbum = albumRepository.findById(album.getId()).get();
    // Disconnect from session so that the updates on updatedAlbum are not directly saved in db
    em.detach(updatedAlbum);
    updatedAlbum.uuid(UPDATED_UUID).name(UPDATED_NAME).note(UPDATED_NOTE).avatar(UPDATED_AVATAR);
    AlbumDTO albumDTO = albumMapper.toDto(updatedAlbum);

    restAlbumMockMvc
      .perform(
        put(ENTITY_API_URL_ID, albumDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(albumDTO))
      )
      .andExpect(status().isOk());

    // Validate the Album in the database
    List<Album> albumList = albumRepository.findAll();
    assertThat(albumList).hasSize(databaseSizeBeforeUpdate);
    Album testAlbum = albumList.get(albumList.size() - 1);
    assertThat(testAlbum.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testAlbum.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testAlbum.getNote()).isEqualTo(UPDATED_NOTE);
    assertThat(testAlbum.getAvatar()).isEqualTo(UPDATED_AVATAR);

    // Validate the Album in Elasticsearch
    verify(mockAlbumSearchRepository).save(testAlbum);
  }

  @Test
  @Transactional
  void putNonExistingAlbum() throws Exception {
    int databaseSizeBeforeUpdate = albumRepository.findAll().size();
    album.setId(count.incrementAndGet());

    // Create the Album
    AlbumDTO albumDTO = albumMapper.toDto(album);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restAlbumMockMvc
      .perform(
        put(ENTITY_API_URL_ID, albumDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(albumDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Album in the database
    List<Album> albumList = albumRepository.findAll();
    assertThat(albumList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Album in Elasticsearch
    verify(mockAlbumSearchRepository, times(0)).save(album);
  }

  @Test
  @Transactional
  void putWithIdMismatchAlbum() throws Exception {
    int databaseSizeBeforeUpdate = albumRepository.findAll().size();
    album.setId(count.incrementAndGet());

    // Create the Album
    AlbumDTO albumDTO = albumMapper.toDto(album);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAlbumMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(albumDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Album in the database
    List<Album> albumList = albumRepository.findAll();
    assertThat(albumList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Album in Elasticsearch
    verify(mockAlbumSearchRepository, times(0)).save(album);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamAlbum() throws Exception {
    int databaseSizeBeforeUpdate = albumRepository.findAll().size();
    album.setId(count.incrementAndGet());

    // Create the Album
    AlbumDTO albumDTO = albumMapper.toDto(album);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAlbumMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(albumDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Album in the database
    List<Album> albumList = albumRepository.findAll();
    assertThat(albumList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Album in Elasticsearch
    verify(mockAlbumSearchRepository, times(0)).save(album);
  }

  @Test
  @Transactional
  void partialUpdateAlbumWithPatch() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    int databaseSizeBeforeUpdate = albumRepository.findAll().size();

    // Update the album using partial update
    Album partialUpdatedAlbum = new Album();
    partialUpdatedAlbum.setId(album.getId());

    partialUpdatedAlbum.name(UPDATED_NAME).note(UPDATED_NOTE);

    restAlbumMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedAlbum.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAlbum))
      )
      .andExpect(status().isOk());

    // Validate the Album in the database
    List<Album> albumList = albumRepository.findAll();
    assertThat(albumList).hasSize(databaseSizeBeforeUpdate);
    Album testAlbum = albumList.get(albumList.size() - 1);
    assertThat(testAlbum.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testAlbum.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testAlbum.getNote()).isEqualTo(UPDATED_NOTE);
    assertThat(testAlbum.getAvatar()).isEqualTo(DEFAULT_AVATAR);
  }

  @Test
  @Transactional
  void fullUpdateAlbumWithPatch() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    int databaseSizeBeforeUpdate = albumRepository.findAll().size();

    // Update the album using partial update
    Album partialUpdatedAlbum = new Album();
    partialUpdatedAlbum.setId(album.getId());

    partialUpdatedAlbum.uuid(UPDATED_UUID).name(UPDATED_NAME).note(UPDATED_NOTE).avatar(UPDATED_AVATAR);

    restAlbumMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedAlbum.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAlbum))
      )
      .andExpect(status().isOk());

    // Validate the Album in the database
    List<Album> albumList = albumRepository.findAll();
    assertThat(albumList).hasSize(databaseSizeBeforeUpdate);
    Album testAlbum = albumList.get(albumList.size() - 1);
    assertThat(testAlbum.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testAlbum.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testAlbum.getNote()).isEqualTo(UPDATED_NOTE);
    assertThat(testAlbum.getAvatar()).isEqualTo(UPDATED_AVATAR);
  }

  @Test
  @Transactional
  void patchNonExistingAlbum() throws Exception {
    int databaseSizeBeforeUpdate = albumRepository.findAll().size();
    album.setId(count.incrementAndGet());

    // Create the Album
    AlbumDTO albumDTO = albumMapper.toDto(album);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restAlbumMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, albumDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(albumDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Album in the database
    List<Album> albumList = albumRepository.findAll();
    assertThat(albumList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Album in Elasticsearch
    verify(mockAlbumSearchRepository, times(0)).save(album);
  }

  @Test
  @Transactional
  void patchWithIdMismatchAlbum() throws Exception {
    int databaseSizeBeforeUpdate = albumRepository.findAll().size();
    album.setId(count.incrementAndGet());

    // Create the Album
    AlbumDTO albumDTO = albumMapper.toDto(album);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAlbumMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(albumDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Album in the database
    List<Album> albumList = albumRepository.findAll();
    assertThat(albumList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Album in Elasticsearch
    verify(mockAlbumSearchRepository, times(0)).save(album);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamAlbum() throws Exception {
    int databaseSizeBeforeUpdate = albumRepository.findAll().size();
    album.setId(count.incrementAndGet());

    // Create the Album
    AlbumDTO albumDTO = albumMapper.toDto(album);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAlbumMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(albumDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Album in the database
    List<Album> albumList = albumRepository.findAll();
    assertThat(albumList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Album in Elasticsearch
    verify(mockAlbumSearchRepository, times(0)).save(album);
  }

  @Test
  @Transactional
  void deleteAlbum() throws Exception {
    // Initialize the database
    albumRepository.saveAndFlush(album);

    int databaseSizeBeforeDelete = albumRepository.findAll().size();

    // Delete the album
    restAlbumMockMvc.perform(delete(ENTITY_API_URL_ID, album.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<Album> albumList = albumRepository.findAll();
    assertThat(albumList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the Album in Elasticsearch
    verify(mockAlbumSearchRepository, times(1)).deleteById(album.getId());
  }

  @Test
  @Transactional
  void searchAlbum() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    albumRepository.saveAndFlush(album);
    when(mockAlbumSearchRepository.search(queryStringQuery("id:" + album.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(album), PageRequest.of(0, 1), 1));

    // Search the album
    restAlbumMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + album.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(album.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
      .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())));
  }
}
