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
import com.regitiny.catiny.domain.Event;
import com.regitiny.catiny.domain.FileInfo;
import com.regitiny.catiny.domain.Image;
import com.regitiny.catiny.domain.Image;
import com.regitiny.catiny.repository.ImageRepository;
import com.regitiny.catiny.repository.search.ImageSearchRepository;
import com.regitiny.catiny.service.criteria.ImageCriteria;
import com.regitiny.catiny.service.dto.ImageDTO;
import com.regitiny.catiny.service.mapper.ImageMapper;
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
 * Integration tests for the {@link ImageResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class ImageResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/images";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/images";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private ImageRepository imageRepository;

  @Autowired
  private ImageMapper imageMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.ImageSearchRepositoryMockConfiguration
   */
  @Autowired
  private ImageSearchRepository mockImageSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restImageMockMvc;

  private Image image;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Image createEntity(EntityManager em) {
    Image image = new Image().uuid(DEFAULT_UUID).name(DEFAULT_NAME);
    return image;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Image createUpdatedEntity(EntityManager em) {
    Image image = new Image().uuid(UPDATED_UUID).name(UPDATED_NAME);
    return image;
  }

  @BeforeEach
  public void initTest() {
    image = createEntity(em);
  }

  @Test
  @Transactional
  void createImage() throws Exception {
    int databaseSizeBeforeCreate = imageRepository.findAll().size();
    // Create the Image
    ImageDTO imageDTO = imageMapper.toDto(image);
    restImageMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(imageDTO)))
      .andExpect(status().isCreated());

    // Validate the Image in the database
    List<Image> imageList = imageRepository.findAll();
    assertThat(imageList).hasSize(databaseSizeBeforeCreate + 1);
    Image testImage = imageList.get(imageList.size() - 1);
    assertThat(testImage.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testImage.getName()).isEqualTo(DEFAULT_NAME);

    // Validate the Image in Elasticsearch
    verify(mockImageSearchRepository, times(1)).save(testImage);
  }

  @Test
  @Transactional
  void createImageWithExistingId() throws Exception {
    // Create the Image with an existing ID
    image.setId(1L);
    ImageDTO imageDTO = imageMapper.toDto(image);

    int databaseSizeBeforeCreate = imageRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restImageMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(imageDTO)))
      .andExpect(status().isBadRequest());

    // Validate the Image in the database
    List<Image> imageList = imageRepository.findAll();
    assertThat(imageList).hasSize(databaseSizeBeforeCreate);

    // Validate the Image in Elasticsearch
    verify(mockImageSearchRepository, times(0)).save(image);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = imageRepository.findAll().size();
    // set the field null
    image.setUuid(null);

    // Create the Image, which fails.
    ImageDTO imageDTO = imageMapper.toDto(image);

    restImageMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(imageDTO)))
      .andExpect(status().isBadRequest());

    List<Image> imageList = imageRepository.findAll();
    assertThat(imageList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllImages() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    // Get all the imageList
    restImageMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(image.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
  }

  @Test
  @Transactional
  void getImage() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    // Get the image
    restImageMockMvc
      .perform(get(ENTITY_API_URL_ID, image.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(image.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
  }

  @Test
  @Transactional
  void getImagesByIdFiltering() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    Long id = image.getId();

    defaultImageShouldBeFound("id.equals=" + id);
    defaultImageShouldNotBeFound("id.notEquals=" + id);

    defaultImageShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultImageShouldNotBeFound("id.greaterThan=" + id);

    defaultImageShouldBeFound("id.lessThanOrEqual=" + id);
    defaultImageShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllImagesByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    // Get all the imageList where uuid equals to DEFAULT_UUID
    defaultImageShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the imageList where uuid equals to UPDATED_UUID
    defaultImageShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllImagesByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    // Get all the imageList where uuid not equals to DEFAULT_UUID
    defaultImageShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the imageList where uuid not equals to UPDATED_UUID
    defaultImageShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllImagesByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    // Get all the imageList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultImageShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the imageList where uuid equals to UPDATED_UUID
    defaultImageShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllImagesByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    // Get all the imageList where uuid is not null
    defaultImageShouldBeFound("uuid.specified=true");

    // Get all the imageList where uuid is null
    defaultImageShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllImagesByNameIsEqualToSomething() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    // Get all the imageList where name equals to DEFAULT_NAME
    defaultImageShouldBeFound("name.equals=" + DEFAULT_NAME);

    // Get all the imageList where name equals to UPDATED_NAME
    defaultImageShouldNotBeFound("name.equals=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllImagesByNameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    // Get all the imageList where name not equals to DEFAULT_NAME
    defaultImageShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

    // Get all the imageList where name not equals to UPDATED_NAME
    defaultImageShouldBeFound("name.notEquals=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllImagesByNameIsInShouldWork() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    // Get all the imageList where name in DEFAULT_NAME or UPDATED_NAME
    defaultImageShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

    // Get all the imageList where name equals to UPDATED_NAME
    defaultImageShouldNotBeFound("name.in=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllImagesByNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    // Get all the imageList where name is not null
    defaultImageShouldBeFound("name.specified=true");

    // Get all the imageList where name is null
    defaultImageShouldNotBeFound("name.specified=false");
  }

  @Test
  @Transactional
  void getAllImagesByNameContainsSomething() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    // Get all the imageList where name contains DEFAULT_NAME
    defaultImageShouldBeFound("name.contains=" + DEFAULT_NAME);

    // Get all the imageList where name contains UPDATED_NAME
    defaultImageShouldNotBeFound("name.contains=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllImagesByNameNotContainsSomething() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    // Get all the imageList where name does not contain DEFAULT_NAME
    defaultImageShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

    // Get all the imageList where name does not contain UPDATED_NAME
    defaultImageShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllImagesByFileInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);
    FileInfo fileInfo = FileInfoResourceIT.createEntity(em);
    em.persist(fileInfo);
    em.flush();
    image.setFileInfo(fileInfo);
    imageRepository.saveAndFlush(image);
    Long fileInfoId = fileInfo.getId();

    // Get all the imageList where fileInfo equals to fileInfoId
    defaultImageShouldBeFound("fileInfoId.equals=" + fileInfoId);

    // Get all the imageList where fileInfo equals to (fileInfoId + 1)
    defaultImageShouldNotBeFound("fileInfoId.equals=" + (fileInfoId + 1));
  }

  @Test
  @Transactional
  void getAllImagesByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    image.setBaseInfo(baseInfo);
    imageRepository.saveAndFlush(image);
    Long baseInfoId = baseInfo.getId();

    // Get all the imageList where baseInfo equals to baseInfoId
    defaultImageShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the imageList where baseInfo equals to (baseInfoId + 1)
    defaultImageShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  @Test
  @Transactional
  void getAllImagesByImageProcessedIsEqualToSomething() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);
    Image imageProcessed = ImageResourceIT.createEntity(em);
    em.persist(imageProcessed);
    em.flush();
    image.addImageProcessed(imageProcessed);
    imageRepository.saveAndFlush(image);
    Long imageProcessedId = imageProcessed.getId();

    // Get all the imageList where imageProcessed equals to imageProcessedId
    defaultImageShouldBeFound("imageProcessedId.equals=" + imageProcessedId);

    // Get all the imageList where imageProcessed equals to (imageProcessedId + 1)
    defaultImageShouldNotBeFound("imageProcessedId.equals=" + (imageProcessedId + 1));
  }

  @Test
  @Transactional
  void getAllImagesByImageOriginalIsEqualToSomething() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);
    Image imageOriginal = ImageResourceIT.createEntity(em);
    em.persist(imageOriginal);
    em.flush();
    image.setImageOriginal(imageOriginal);
    imageRepository.saveAndFlush(image);
    Long imageOriginalId = imageOriginal.getId();

    // Get all the imageList where imageOriginal equals to imageOriginalId
    defaultImageShouldBeFound("imageOriginalId.equals=" + imageOriginalId);

    // Get all the imageList where imageOriginal equals to (imageOriginalId + 1)
    defaultImageShouldNotBeFound("imageOriginalId.equals=" + (imageOriginalId + 1));
  }

  @Test
  @Transactional
  void getAllImagesByEventIsEqualToSomething() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);
    Event event = EventResourceIT.createEntity(em);
    em.persist(event);
    em.flush();
    image.setEvent(event);
    imageRepository.saveAndFlush(image);
    Long eventId = event.getId();

    // Get all the imageList where event equals to eventId
    defaultImageShouldBeFound("eventId.equals=" + eventId);

    // Get all the imageList where event equals to (eventId + 1)
    defaultImageShouldNotBeFound("eventId.equals=" + (eventId + 1));
  }

  @Test
  @Transactional
  void getAllImagesByAlbumIsEqualToSomething() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);
    Album album = AlbumResourceIT.createEntity(em);
    em.persist(album);
    em.flush();
    image.addAlbum(album);
    imageRepository.saveAndFlush(image);
    Long albumId = album.getId();

    // Get all the imageList where album equals to albumId
    defaultImageShouldBeFound("albumId.equals=" + albumId);

    // Get all the imageList where album equals to (albumId + 1)
    defaultImageShouldNotBeFound("albumId.equals=" + (albumId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultImageShouldBeFound(String filter) throws Exception {
    restImageMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(image.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));

    // Check, that the count call also returns 1
    restImageMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultImageShouldNotBeFound(String filter) throws Exception {
    restImageMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restImageMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingImage() throws Exception {
    // Get the image
    restImageMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewImage() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    int databaseSizeBeforeUpdate = imageRepository.findAll().size();

    // Update the image
    Image updatedImage = imageRepository.findById(image.getId()).get();
    // Disconnect from session so that the updates on updatedImage are not directly saved in db
    em.detach(updatedImage);
    updatedImage.uuid(UPDATED_UUID).name(UPDATED_NAME);
    ImageDTO imageDTO = imageMapper.toDto(updatedImage);

    restImageMockMvc
      .perform(
        put(ENTITY_API_URL_ID, imageDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(imageDTO))
      )
      .andExpect(status().isOk());

    // Validate the Image in the database
    List<Image> imageList = imageRepository.findAll();
    assertThat(imageList).hasSize(databaseSizeBeforeUpdate);
    Image testImage = imageList.get(imageList.size() - 1);
    assertThat(testImage.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testImage.getName()).isEqualTo(UPDATED_NAME);

    // Validate the Image in Elasticsearch
    verify(mockImageSearchRepository).save(testImage);
  }

  @Test
  @Transactional
  void putNonExistingImage() throws Exception {
    int databaseSizeBeforeUpdate = imageRepository.findAll().size();
    image.setId(count.incrementAndGet());

    // Create the Image
    ImageDTO imageDTO = imageMapper.toDto(image);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restImageMockMvc
      .perform(
        put(ENTITY_API_URL_ID, imageDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(imageDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Image in the database
    List<Image> imageList = imageRepository.findAll();
    assertThat(imageList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Image in Elasticsearch
    verify(mockImageSearchRepository, times(0)).save(image);
  }

  @Test
  @Transactional
  void putWithIdMismatchImage() throws Exception {
    int databaseSizeBeforeUpdate = imageRepository.findAll().size();
    image.setId(count.incrementAndGet());

    // Create the Image
    ImageDTO imageDTO = imageMapper.toDto(image);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restImageMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(imageDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Image in the database
    List<Image> imageList = imageRepository.findAll();
    assertThat(imageList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Image in Elasticsearch
    verify(mockImageSearchRepository, times(0)).save(image);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamImage() throws Exception {
    int databaseSizeBeforeUpdate = imageRepository.findAll().size();
    image.setId(count.incrementAndGet());

    // Create the Image
    ImageDTO imageDTO = imageMapper.toDto(image);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restImageMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(imageDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Image in the database
    List<Image> imageList = imageRepository.findAll();
    assertThat(imageList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Image in Elasticsearch
    verify(mockImageSearchRepository, times(0)).save(image);
  }

  @Test
  @Transactional
  void partialUpdateImageWithPatch() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    int databaseSizeBeforeUpdate = imageRepository.findAll().size();

    // Update the image using partial update
    Image partialUpdatedImage = new Image();
    partialUpdatedImage.setId(image.getId());

    partialUpdatedImage.name(UPDATED_NAME);

    restImageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedImage.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedImage))
      )
      .andExpect(status().isOk());

    // Validate the Image in the database
    List<Image> imageList = imageRepository.findAll();
    assertThat(imageList).hasSize(databaseSizeBeforeUpdate);
    Image testImage = imageList.get(imageList.size() - 1);
    assertThat(testImage.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testImage.getName()).isEqualTo(UPDATED_NAME);
  }

  @Test
  @Transactional
  void fullUpdateImageWithPatch() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    int databaseSizeBeforeUpdate = imageRepository.findAll().size();

    // Update the image using partial update
    Image partialUpdatedImage = new Image();
    partialUpdatedImage.setId(image.getId());

    partialUpdatedImage.uuid(UPDATED_UUID).name(UPDATED_NAME);

    restImageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedImage.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedImage))
      )
      .andExpect(status().isOk());

    // Validate the Image in the database
    List<Image> imageList = imageRepository.findAll();
    assertThat(imageList).hasSize(databaseSizeBeforeUpdate);
    Image testImage = imageList.get(imageList.size() - 1);
    assertThat(testImage.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testImage.getName()).isEqualTo(UPDATED_NAME);
  }

  @Test
  @Transactional
  void patchNonExistingImage() throws Exception {
    int databaseSizeBeforeUpdate = imageRepository.findAll().size();
    image.setId(count.incrementAndGet());

    // Create the Image
    ImageDTO imageDTO = imageMapper.toDto(image);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restImageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, imageDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(imageDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Image in the database
    List<Image> imageList = imageRepository.findAll();
    assertThat(imageList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Image in Elasticsearch
    verify(mockImageSearchRepository, times(0)).save(image);
  }

  @Test
  @Transactional
  void patchWithIdMismatchImage() throws Exception {
    int databaseSizeBeforeUpdate = imageRepository.findAll().size();
    image.setId(count.incrementAndGet());

    // Create the Image
    ImageDTO imageDTO = imageMapper.toDto(image);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restImageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(imageDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Image in the database
    List<Image> imageList = imageRepository.findAll();
    assertThat(imageList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Image in Elasticsearch
    verify(mockImageSearchRepository, times(0)).save(image);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamImage() throws Exception {
    int databaseSizeBeforeUpdate = imageRepository.findAll().size();
    image.setId(count.incrementAndGet());

    // Create the Image
    ImageDTO imageDTO = imageMapper.toDto(image);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restImageMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(imageDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the Image in the database
    List<Image> imageList = imageRepository.findAll();
    assertThat(imageList).hasSize(databaseSizeBeforeUpdate);

    // Validate the Image in Elasticsearch
    verify(mockImageSearchRepository, times(0)).save(image);
  }

  @Test
  @Transactional
  void deleteImage() throws Exception {
    // Initialize the database
    imageRepository.saveAndFlush(image);

    int databaseSizeBeforeDelete = imageRepository.findAll().size();

    // Delete the image
    restImageMockMvc.perform(delete(ENTITY_API_URL_ID, image.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<Image> imageList = imageRepository.findAll();
    assertThat(imageList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the Image in Elasticsearch
    verify(mockImageSearchRepository, times(1)).deleteById(image.getId());
  }

  @Test
  @Transactional
  void searchImage() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    imageRepository.saveAndFlush(image);
    when(mockImageSearchRepository.search(queryStringQuery("id:" + image.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(image), PageRequest.of(0, 1), 1));

    // Search the image
    restImageMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + image.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(image.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
  }
}
