package com.regitiny.catiny.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.IntegrationTest;
import com.regitiny.catiny.domain.HanhChinhVN;
import com.regitiny.catiny.repository.HanhChinhVNRepository;
import com.regitiny.catiny.repository.search.HanhChinhVNSearchRepository;
import com.regitiny.catiny.service.criteria.HanhChinhVNCriteria;
import com.regitiny.catiny.service.dto.HanhChinhVNDTO;
import com.regitiny.catiny.service.mapper.HanhChinhVNMapper;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
 * Integration tests for the {@link HanhChinhVNResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class HanhChinhVNResourceIT {

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_SLUG = "AAAAAAAAAA";
  private static final String UPDATED_SLUG = "BBBBBBBBBB";

  private static final String DEFAULT_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_NAME_WITH_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_NAME_WITH_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_CODE = "AAAAAAAAAA";
  private static final String UPDATED_CODE = "BBBBBBBBBB";

  private static final String DEFAULT_PARENT_CODE = "AAAAAAAAAA";
  private static final String UPDATED_PARENT_CODE = "BBBBBBBBBB";

  private static final String DEFAULT_PATH = "AAAAAAAAAA";
  private static final String UPDATED_PATH = "BBBBBBBBBB";

  private static final String DEFAULT_PATH_WITH_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_PATH_WITH_TYPE = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/hanh-chinh-vns";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/hanh-chinh-vns";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private HanhChinhVNRepository hanhChinhVNRepository;

  @Autowired
  private HanhChinhVNMapper hanhChinhVNMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.HanhChinhVNSearchRepositoryMockConfiguration
   */
  @Autowired
  private HanhChinhVNSearchRepository mockHanhChinhVNSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restHanhChinhVNMockMvc;

  private HanhChinhVN hanhChinhVN;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static HanhChinhVN createEntity(EntityManager em) {
    HanhChinhVN hanhChinhVN = new HanhChinhVN()
      .name(DEFAULT_NAME)
      .slug(DEFAULT_SLUG)
      .type(DEFAULT_TYPE)
      .nameWithType(DEFAULT_NAME_WITH_TYPE)
      .code(DEFAULT_CODE)
      .parentCode(DEFAULT_PARENT_CODE)
      .path(DEFAULT_PATH)
      .pathWithType(DEFAULT_PATH_WITH_TYPE);
    return hanhChinhVN;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static HanhChinhVN createUpdatedEntity(EntityManager em) {
    HanhChinhVN hanhChinhVN = new HanhChinhVN()
      .name(UPDATED_NAME)
      .slug(UPDATED_SLUG)
      .type(UPDATED_TYPE)
      .nameWithType(UPDATED_NAME_WITH_TYPE)
      .code(UPDATED_CODE)
      .parentCode(UPDATED_PARENT_CODE)
      .path(UPDATED_PATH)
      .pathWithType(UPDATED_PATH_WITH_TYPE);
    return hanhChinhVN;
  }

  @BeforeEach
  public void initTest() {
    hanhChinhVN = createEntity(em);
  }

  @Test
  @Transactional
  void createHanhChinhVN() throws Exception {
    int databaseSizeBeforeCreate = hanhChinhVNRepository.findAll().size();
    // Create the HanhChinhVN
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);
    restHanhChinhVNMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO)))
      .andExpect(status().isCreated());

    // Validate the HanhChinhVN in the database
    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeCreate + 1);
    HanhChinhVN testHanhChinhVN = hanhChinhVNList.get(hanhChinhVNList.size() - 1);
    assertThat(testHanhChinhVN.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testHanhChinhVN.getSlug()).isEqualTo(DEFAULT_SLUG);
    assertThat(testHanhChinhVN.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testHanhChinhVN.getNameWithType()).isEqualTo(DEFAULT_NAME_WITH_TYPE);
    assertThat(testHanhChinhVN.getCode()).isEqualTo(DEFAULT_CODE);
    assertThat(testHanhChinhVN.getParentCode()).isEqualTo(DEFAULT_PARENT_CODE);
    assertThat(testHanhChinhVN.getPath()).isEqualTo(DEFAULT_PATH);
    assertThat(testHanhChinhVN.getPathWithType()).isEqualTo(DEFAULT_PATH_WITH_TYPE);

    // Validate the HanhChinhVN in Elasticsearch
    verify(mockHanhChinhVNSearchRepository, times(1)).save(testHanhChinhVN);
  }

  @Test
  @Transactional
  void createHanhChinhVNWithExistingId() throws Exception {
    // Create the HanhChinhVN with an existing ID
    hanhChinhVN.setId(1L);
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

    int databaseSizeBeforeCreate = hanhChinhVNRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restHanhChinhVNMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO)))
      .andExpect(status().isBadRequest());

    // Validate the HanhChinhVN in the database
    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeCreate);

    // Validate the HanhChinhVN in Elasticsearch
    verify(mockHanhChinhVNSearchRepository, times(0)).save(hanhChinhVN);
  }

  @Test
  @Transactional
  void checkNameIsRequired() throws Exception {
    int databaseSizeBeforeTest = hanhChinhVNRepository.findAll().size();
    // set the field null
    hanhChinhVN.setName(null);

    // Create the HanhChinhVN, which fails.
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

    restHanhChinhVNMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO)))
      .andExpect(status().isBadRequest());

    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void checkSlugIsRequired() throws Exception {
    int databaseSizeBeforeTest = hanhChinhVNRepository.findAll().size();
    // set the field null
    hanhChinhVN.setSlug(null);

    // Create the HanhChinhVN, which fails.
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

    restHanhChinhVNMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO)))
      .andExpect(status().isBadRequest());

    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void checkTypeIsRequired() throws Exception {
    int databaseSizeBeforeTest = hanhChinhVNRepository.findAll().size();
    // set the field null
    hanhChinhVN.setType(null);

    // Create the HanhChinhVN, which fails.
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

    restHanhChinhVNMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO)))
      .andExpect(status().isBadRequest());

    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void checkNameWithTypeIsRequired() throws Exception {
    int databaseSizeBeforeTest = hanhChinhVNRepository.findAll().size();
    // set the field null
    hanhChinhVN.setNameWithType(null);

    // Create the HanhChinhVN, which fails.
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

    restHanhChinhVNMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO)))
      .andExpect(status().isBadRequest());

    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void checkCodeIsRequired() throws Exception {
    int databaseSizeBeforeTest = hanhChinhVNRepository.findAll().size();
    // set the field null
    hanhChinhVN.setCode(null);

    // Create the HanhChinhVN, which fails.
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

    restHanhChinhVNMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO)))
      .andExpect(status().isBadRequest());

    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void checkParentCodeIsRequired() throws Exception {
    int databaseSizeBeforeTest = hanhChinhVNRepository.findAll().size();
    // set the field null
    hanhChinhVN.setParentCode(null);

    // Create the HanhChinhVN, which fails.
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

    restHanhChinhVNMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO)))
      .andExpect(status().isBadRequest());

    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNS() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList
    restHanhChinhVNMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(hanhChinhVN.getId().intValue())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].nameWithType").value(hasItem(DEFAULT_NAME_WITH_TYPE)))
      .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
      .andExpect(jsonPath("$.[*].parentCode").value(hasItem(DEFAULT_PARENT_CODE)))
      .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
      .andExpect(jsonPath("$.[*].pathWithType").value(hasItem(DEFAULT_PATH_WITH_TYPE)));
  }

  @Test
  @Transactional
  void getHanhChinhVN() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get the hanhChinhVN
    restHanhChinhVNMockMvc
      .perform(get(ENTITY_API_URL_ID, hanhChinhVN.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(hanhChinhVN.getId().intValue()))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
      .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
      .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
      .andExpect(jsonPath("$.nameWithType").value(DEFAULT_NAME_WITH_TYPE))
      .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
      .andExpect(jsonPath("$.parentCode").value(DEFAULT_PARENT_CODE))
      .andExpect(jsonPath("$.path").value(DEFAULT_PATH))
      .andExpect(jsonPath("$.pathWithType").value(DEFAULT_PATH_WITH_TYPE));
  }

  @Test
  @Transactional
  void getHanhChinhVNSByIdFiltering() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    Long id = hanhChinhVN.getId();

    defaultHanhChinhVNShouldBeFound("id.equals=" + id);
    defaultHanhChinhVNShouldNotBeFound("id.notEquals=" + id);

    defaultHanhChinhVNShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultHanhChinhVNShouldNotBeFound("id.greaterThan=" + id);

    defaultHanhChinhVNShouldBeFound("id.lessThanOrEqual=" + id);
    defaultHanhChinhVNShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByNameIsEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where name equals to DEFAULT_NAME
    defaultHanhChinhVNShouldBeFound("name.equals=" + DEFAULT_NAME);

    // Get all the hanhChinhVNList where name equals to UPDATED_NAME
    defaultHanhChinhVNShouldNotBeFound("name.equals=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByNameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where name not equals to DEFAULT_NAME
    defaultHanhChinhVNShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

    // Get all the hanhChinhVNList where name not equals to UPDATED_NAME
    defaultHanhChinhVNShouldBeFound("name.notEquals=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByNameIsInShouldWork() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where name in DEFAULT_NAME or UPDATED_NAME
    defaultHanhChinhVNShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

    // Get all the hanhChinhVNList where name equals to UPDATED_NAME
    defaultHanhChinhVNShouldNotBeFound("name.in=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where name is not null
    defaultHanhChinhVNShouldBeFound("name.specified=true");

    // Get all the hanhChinhVNList where name is null
    defaultHanhChinhVNShouldNotBeFound("name.specified=false");
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByNameContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where name contains DEFAULT_NAME
    defaultHanhChinhVNShouldBeFound("name.contains=" + DEFAULT_NAME);

    // Get all the hanhChinhVNList where name contains UPDATED_NAME
    defaultHanhChinhVNShouldNotBeFound("name.contains=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByNameNotContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where name does not contain DEFAULT_NAME
    defaultHanhChinhVNShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

    // Get all the hanhChinhVNList where name does not contain UPDATED_NAME
    defaultHanhChinhVNShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSBySlugIsEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where slug equals to DEFAULT_SLUG
    defaultHanhChinhVNShouldBeFound("slug.equals=" + DEFAULT_SLUG);

    // Get all the hanhChinhVNList where slug equals to UPDATED_SLUG
    defaultHanhChinhVNShouldNotBeFound("slug.equals=" + UPDATED_SLUG);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSBySlugIsNotEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where slug not equals to DEFAULT_SLUG
    defaultHanhChinhVNShouldNotBeFound("slug.notEquals=" + DEFAULT_SLUG);

    // Get all the hanhChinhVNList where slug not equals to UPDATED_SLUG
    defaultHanhChinhVNShouldBeFound("slug.notEquals=" + UPDATED_SLUG);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSBySlugIsInShouldWork() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where slug in DEFAULT_SLUG or UPDATED_SLUG
    defaultHanhChinhVNShouldBeFound("slug.in=" + DEFAULT_SLUG + "," + UPDATED_SLUG);

    // Get all the hanhChinhVNList where slug equals to UPDATED_SLUG
    defaultHanhChinhVNShouldNotBeFound("slug.in=" + UPDATED_SLUG);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSBySlugIsNullOrNotNull() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where slug is not null
    defaultHanhChinhVNShouldBeFound("slug.specified=true");

    // Get all the hanhChinhVNList where slug is null
    defaultHanhChinhVNShouldNotBeFound("slug.specified=false");
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSBySlugContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where slug contains DEFAULT_SLUG
    defaultHanhChinhVNShouldBeFound("slug.contains=" + DEFAULT_SLUG);

    // Get all the hanhChinhVNList where slug contains UPDATED_SLUG
    defaultHanhChinhVNShouldNotBeFound("slug.contains=" + UPDATED_SLUG);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSBySlugNotContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where slug does not contain DEFAULT_SLUG
    defaultHanhChinhVNShouldNotBeFound("slug.doesNotContain=" + DEFAULT_SLUG);

    // Get all the hanhChinhVNList where slug does not contain UPDATED_SLUG
    defaultHanhChinhVNShouldBeFound("slug.doesNotContain=" + UPDATED_SLUG);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where type equals to DEFAULT_TYPE
    defaultHanhChinhVNShouldBeFound("type.equals=" + DEFAULT_TYPE);

    // Get all the hanhChinhVNList where type equals to UPDATED_TYPE
    defaultHanhChinhVNShouldNotBeFound("type.equals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where type not equals to DEFAULT_TYPE
    defaultHanhChinhVNShouldNotBeFound("type.notEquals=" + DEFAULT_TYPE);

    // Get all the hanhChinhVNList where type not equals to UPDATED_TYPE
    defaultHanhChinhVNShouldBeFound("type.notEquals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByTypeIsInShouldWork() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where type in DEFAULT_TYPE or UPDATED_TYPE
    defaultHanhChinhVNShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

    // Get all the hanhChinhVNList where type equals to UPDATED_TYPE
    defaultHanhChinhVNShouldNotBeFound("type.in=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where type is not null
    defaultHanhChinhVNShouldBeFound("type.specified=true");

    // Get all the hanhChinhVNList where type is null
    defaultHanhChinhVNShouldNotBeFound("type.specified=false");
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByTypeContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where type contains DEFAULT_TYPE
    defaultHanhChinhVNShouldBeFound("type.contains=" + DEFAULT_TYPE);

    // Get all the hanhChinhVNList where type contains UPDATED_TYPE
    defaultHanhChinhVNShouldNotBeFound("type.contains=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByTypeNotContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where type does not contain DEFAULT_TYPE
    defaultHanhChinhVNShouldNotBeFound("type.doesNotContain=" + DEFAULT_TYPE);

    // Get all the hanhChinhVNList where type does not contain UPDATED_TYPE
    defaultHanhChinhVNShouldBeFound("type.doesNotContain=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByNameWithTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where nameWithType equals to DEFAULT_NAME_WITH_TYPE
    defaultHanhChinhVNShouldBeFound("nameWithType.equals=" + DEFAULT_NAME_WITH_TYPE);

    // Get all the hanhChinhVNList where nameWithType equals to UPDATED_NAME_WITH_TYPE
    defaultHanhChinhVNShouldNotBeFound("nameWithType.equals=" + UPDATED_NAME_WITH_TYPE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByNameWithTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where nameWithType not equals to DEFAULT_NAME_WITH_TYPE
    defaultHanhChinhVNShouldNotBeFound("nameWithType.notEquals=" + DEFAULT_NAME_WITH_TYPE);

    // Get all the hanhChinhVNList where nameWithType not equals to UPDATED_NAME_WITH_TYPE
    defaultHanhChinhVNShouldBeFound("nameWithType.notEquals=" + UPDATED_NAME_WITH_TYPE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByNameWithTypeIsInShouldWork() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where nameWithType in DEFAULT_NAME_WITH_TYPE or UPDATED_NAME_WITH_TYPE
    defaultHanhChinhVNShouldBeFound("nameWithType.in=" + DEFAULT_NAME_WITH_TYPE + "," + UPDATED_NAME_WITH_TYPE);

    // Get all the hanhChinhVNList where nameWithType equals to UPDATED_NAME_WITH_TYPE
    defaultHanhChinhVNShouldNotBeFound("nameWithType.in=" + UPDATED_NAME_WITH_TYPE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByNameWithTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where nameWithType is not null
    defaultHanhChinhVNShouldBeFound("nameWithType.specified=true");

    // Get all the hanhChinhVNList where nameWithType is null
    defaultHanhChinhVNShouldNotBeFound("nameWithType.specified=false");
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByNameWithTypeContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where nameWithType contains DEFAULT_NAME_WITH_TYPE
    defaultHanhChinhVNShouldBeFound("nameWithType.contains=" + DEFAULT_NAME_WITH_TYPE);

    // Get all the hanhChinhVNList where nameWithType contains UPDATED_NAME_WITH_TYPE
    defaultHanhChinhVNShouldNotBeFound("nameWithType.contains=" + UPDATED_NAME_WITH_TYPE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByNameWithTypeNotContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where nameWithType does not contain DEFAULT_NAME_WITH_TYPE
    defaultHanhChinhVNShouldNotBeFound("nameWithType.doesNotContain=" + DEFAULT_NAME_WITH_TYPE);

    // Get all the hanhChinhVNList where nameWithType does not contain UPDATED_NAME_WITH_TYPE
    defaultHanhChinhVNShouldBeFound("nameWithType.doesNotContain=" + UPDATED_NAME_WITH_TYPE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByCodeIsEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where code equals to DEFAULT_CODE
    defaultHanhChinhVNShouldBeFound("code.equals=" + DEFAULT_CODE);

    // Get all the hanhChinhVNList where code equals to UPDATED_CODE
    defaultHanhChinhVNShouldNotBeFound("code.equals=" + UPDATED_CODE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByCodeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where code not equals to DEFAULT_CODE
    defaultHanhChinhVNShouldNotBeFound("code.notEquals=" + DEFAULT_CODE);

    // Get all the hanhChinhVNList where code not equals to UPDATED_CODE
    defaultHanhChinhVNShouldBeFound("code.notEquals=" + UPDATED_CODE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByCodeIsInShouldWork() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where code in DEFAULT_CODE or UPDATED_CODE
    defaultHanhChinhVNShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

    // Get all the hanhChinhVNList where code equals to UPDATED_CODE
    defaultHanhChinhVNShouldNotBeFound("code.in=" + UPDATED_CODE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByCodeIsNullOrNotNull() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where code is not null
    defaultHanhChinhVNShouldBeFound("code.specified=true");

    // Get all the hanhChinhVNList where code is null
    defaultHanhChinhVNShouldNotBeFound("code.specified=false");
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByCodeContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where code contains DEFAULT_CODE
    defaultHanhChinhVNShouldBeFound("code.contains=" + DEFAULT_CODE);

    // Get all the hanhChinhVNList where code contains UPDATED_CODE
    defaultHanhChinhVNShouldNotBeFound("code.contains=" + UPDATED_CODE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByCodeNotContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where code does not contain DEFAULT_CODE
    defaultHanhChinhVNShouldNotBeFound("code.doesNotContain=" + DEFAULT_CODE);

    // Get all the hanhChinhVNList where code does not contain UPDATED_CODE
    defaultHanhChinhVNShouldBeFound("code.doesNotContain=" + UPDATED_CODE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByParentCodeIsEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where parentCode equals to DEFAULT_PARENT_CODE
    defaultHanhChinhVNShouldBeFound("parentCode.equals=" + DEFAULT_PARENT_CODE);

    // Get all the hanhChinhVNList where parentCode equals to UPDATED_PARENT_CODE
    defaultHanhChinhVNShouldNotBeFound("parentCode.equals=" + UPDATED_PARENT_CODE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByParentCodeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where parentCode not equals to DEFAULT_PARENT_CODE
    defaultHanhChinhVNShouldNotBeFound("parentCode.notEquals=" + DEFAULT_PARENT_CODE);

    // Get all the hanhChinhVNList where parentCode not equals to UPDATED_PARENT_CODE
    defaultHanhChinhVNShouldBeFound("parentCode.notEquals=" + UPDATED_PARENT_CODE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByParentCodeIsInShouldWork() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where parentCode in DEFAULT_PARENT_CODE or UPDATED_PARENT_CODE
    defaultHanhChinhVNShouldBeFound("parentCode.in=" + DEFAULT_PARENT_CODE + "," + UPDATED_PARENT_CODE);

    // Get all the hanhChinhVNList where parentCode equals to UPDATED_PARENT_CODE
    defaultHanhChinhVNShouldNotBeFound("parentCode.in=" + UPDATED_PARENT_CODE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByParentCodeIsNullOrNotNull() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where parentCode is not null
    defaultHanhChinhVNShouldBeFound("parentCode.specified=true");

    // Get all the hanhChinhVNList where parentCode is null
    defaultHanhChinhVNShouldNotBeFound("parentCode.specified=false");
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByParentCodeContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where parentCode contains DEFAULT_PARENT_CODE
    defaultHanhChinhVNShouldBeFound("parentCode.contains=" + DEFAULT_PARENT_CODE);

    // Get all the hanhChinhVNList where parentCode contains UPDATED_PARENT_CODE
    defaultHanhChinhVNShouldNotBeFound("parentCode.contains=" + UPDATED_PARENT_CODE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByParentCodeNotContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where parentCode does not contain DEFAULT_PARENT_CODE
    defaultHanhChinhVNShouldNotBeFound("parentCode.doesNotContain=" + DEFAULT_PARENT_CODE);

    // Get all the hanhChinhVNList where parentCode does not contain UPDATED_PARENT_CODE
    defaultHanhChinhVNShouldBeFound("parentCode.doesNotContain=" + UPDATED_PARENT_CODE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByPathIsEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where path equals to DEFAULT_PATH
    defaultHanhChinhVNShouldBeFound("path.equals=" + DEFAULT_PATH);

    // Get all the hanhChinhVNList where path equals to UPDATED_PATH
    defaultHanhChinhVNShouldNotBeFound("path.equals=" + UPDATED_PATH);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByPathIsNotEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where path not equals to DEFAULT_PATH
    defaultHanhChinhVNShouldNotBeFound("path.notEquals=" + DEFAULT_PATH);

    // Get all the hanhChinhVNList where path not equals to UPDATED_PATH
    defaultHanhChinhVNShouldBeFound("path.notEquals=" + UPDATED_PATH);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByPathIsInShouldWork() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where path in DEFAULT_PATH or UPDATED_PATH
    defaultHanhChinhVNShouldBeFound("path.in=" + DEFAULT_PATH + "," + UPDATED_PATH);

    // Get all the hanhChinhVNList where path equals to UPDATED_PATH
    defaultHanhChinhVNShouldNotBeFound("path.in=" + UPDATED_PATH);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByPathIsNullOrNotNull() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where path is not null
    defaultHanhChinhVNShouldBeFound("path.specified=true");

    // Get all the hanhChinhVNList where path is null
    defaultHanhChinhVNShouldNotBeFound("path.specified=false");
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByPathContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where path contains DEFAULT_PATH
    defaultHanhChinhVNShouldBeFound("path.contains=" + DEFAULT_PATH);

    // Get all the hanhChinhVNList where path contains UPDATED_PATH
    defaultHanhChinhVNShouldNotBeFound("path.contains=" + UPDATED_PATH);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByPathNotContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where path does not contain DEFAULT_PATH
    defaultHanhChinhVNShouldNotBeFound("path.doesNotContain=" + DEFAULT_PATH);

    // Get all the hanhChinhVNList where path does not contain UPDATED_PATH
    defaultHanhChinhVNShouldBeFound("path.doesNotContain=" + UPDATED_PATH);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByPathWithTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where pathWithType equals to DEFAULT_PATH_WITH_TYPE
    defaultHanhChinhVNShouldBeFound("pathWithType.equals=" + DEFAULT_PATH_WITH_TYPE);

    // Get all the hanhChinhVNList where pathWithType equals to UPDATED_PATH_WITH_TYPE
    defaultHanhChinhVNShouldNotBeFound("pathWithType.equals=" + UPDATED_PATH_WITH_TYPE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByPathWithTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where pathWithType not equals to DEFAULT_PATH_WITH_TYPE
    defaultHanhChinhVNShouldNotBeFound("pathWithType.notEquals=" + DEFAULT_PATH_WITH_TYPE);

    // Get all the hanhChinhVNList where pathWithType not equals to UPDATED_PATH_WITH_TYPE
    defaultHanhChinhVNShouldBeFound("pathWithType.notEquals=" + UPDATED_PATH_WITH_TYPE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByPathWithTypeIsInShouldWork() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where pathWithType in DEFAULT_PATH_WITH_TYPE or UPDATED_PATH_WITH_TYPE
    defaultHanhChinhVNShouldBeFound("pathWithType.in=" + DEFAULT_PATH_WITH_TYPE + "," + UPDATED_PATH_WITH_TYPE);

    // Get all the hanhChinhVNList where pathWithType equals to UPDATED_PATH_WITH_TYPE
    defaultHanhChinhVNShouldNotBeFound("pathWithType.in=" + UPDATED_PATH_WITH_TYPE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByPathWithTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where pathWithType is not null
    defaultHanhChinhVNShouldBeFound("pathWithType.specified=true");

    // Get all the hanhChinhVNList where pathWithType is null
    defaultHanhChinhVNShouldNotBeFound("pathWithType.specified=false");
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByPathWithTypeContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where pathWithType contains DEFAULT_PATH_WITH_TYPE
    defaultHanhChinhVNShouldBeFound("pathWithType.contains=" + DEFAULT_PATH_WITH_TYPE);

    // Get all the hanhChinhVNList where pathWithType contains UPDATED_PATH_WITH_TYPE
    defaultHanhChinhVNShouldNotBeFound("pathWithType.contains=" + UPDATED_PATH_WITH_TYPE);
  }

  @Test
  @Transactional
  void getAllHanhChinhVNSByPathWithTypeNotContainsSomething() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    // Get all the hanhChinhVNList where pathWithType does not contain DEFAULT_PATH_WITH_TYPE
    defaultHanhChinhVNShouldNotBeFound("pathWithType.doesNotContain=" + DEFAULT_PATH_WITH_TYPE);

    // Get all the hanhChinhVNList where pathWithType does not contain UPDATED_PATH_WITH_TYPE
    defaultHanhChinhVNShouldBeFound("pathWithType.doesNotContain=" + UPDATED_PATH_WITH_TYPE);
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultHanhChinhVNShouldBeFound(String filter) throws Exception {
    restHanhChinhVNMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(hanhChinhVN.getId().intValue())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].nameWithType").value(hasItem(DEFAULT_NAME_WITH_TYPE)))
      .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
      .andExpect(jsonPath("$.[*].parentCode").value(hasItem(DEFAULT_PARENT_CODE)))
      .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
      .andExpect(jsonPath("$.[*].pathWithType").value(hasItem(DEFAULT_PATH_WITH_TYPE)));

    // Check, that the count call also returns 1
    restHanhChinhVNMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultHanhChinhVNShouldNotBeFound(String filter) throws Exception {
    restHanhChinhVNMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restHanhChinhVNMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingHanhChinhVN() throws Exception {
    // Get the hanhChinhVN
    restHanhChinhVNMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewHanhChinhVN() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    int databaseSizeBeforeUpdate = hanhChinhVNRepository.findAll().size();

    // Update the hanhChinhVN
    HanhChinhVN updatedHanhChinhVN = hanhChinhVNRepository.findById(hanhChinhVN.getId()).get();
    // Disconnect from session so that the updates on updatedHanhChinhVN are not directly saved in db
    em.detach(updatedHanhChinhVN);
    updatedHanhChinhVN
      .name(UPDATED_NAME)
      .slug(UPDATED_SLUG)
      .type(UPDATED_TYPE)
      .nameWithType(UPDATED_NAME_WITH_TYPE)
      .code(UPDATED_CODE)
      .parentCode(UPDATED_PARENT_CODE)
      .path(UPDATED_PATH)
      .pathWithType(UPDATED_PATH_WITH_TYPE);
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(updatedHanhChinhVN);

    restHanhChinhVNMockMvc
      .perform(
        put(ENTITY_API_URL_ID, hanhChinhVNDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
      )
      .andExpect(status().isOk());

    // Validate the HanhChinhVN in the database
    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeUpdate);
    HanhChinhVN testHanhChinhVN = hanhChinhVNList.get(hanhChinhVNList.size() - 1);
    assertThat(testHanhChinhVN.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testHanhChinhVN.getSlug()).isEqualTo(UPDATED_SLUG);
    assertThat(testHanhChinhVN.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testHanhChinhVN.getNameWithType()).isEqualTo(UPDATED_NAME_WITH_TYPE);
    assertThat(testHanhChinhVN.getCode()).isEqualTo(UPDATED_CODE);
    assertThat(testHanhChinhVN.getParentCode()).isEqualTo(UPDATED_PARENT_CODE);
    assertThat(testHanhChinhVN.getPath()).isEqualTo(UPDATED_PATH);
    assertThat(testHanhChinhVN.getPathWithType()).isEqualTo(UPDATED_PATH_WITH_TYPE);

    // Validate the HanhChinhVN in Elasticsearch
    verify(mockHanhChinhVNSearchRepository).save(testHanhChinhVN);
  }

  @Test
  @Transactional
  void putNonExistingHanhChinhVN() throws Exception {
    int databaseSizeBeforeUpdate = hanhChinhVNRepository.findAll().size();
    hanhChinhVN.setId(count.incrementAndGet());

    // Create the HanhChinhVN
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restHanhChinhVNMockMvc
      .perform(
        put(ENTITY_API_URL_ID, hanhChinhVNDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the HanhChinhVN in the database
    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeUpdate);

    // Validate the HanhChinhVN in Elasticsearch
    verify(mockHanhChinhVNSearchRepository, times(0)).save(hanhChinhVN);
  }

  @Test
  @Transactional
  void putWithIdMismatchHanhChinhVN() throws Exception {
    int databaseSizeBeforeUpdate = hanhChinhVNRepository.findAll().size();
    hanhChinhVN.setId(count.incrementAndGet());

    // Create the HanhChinhVN
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restHanhChinhVNMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the HanhChinhVN in the database
    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeUpdate);

    // Validate the HanhChinhVN in Elasticsearch
    verify(mockHanhChinhVNSearchRepository, times(0)).save(hanhChinhVN);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamHanhChinhVN() throws Exception {
    int databaseSizeBeforeUpdate = hanhChinhVNRepository.findAll().size();
    hanhChinhVN.setId(count.incrementAndGet());

    // Create the HanhChinhVN
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restHanhChinhVNMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the HanhChinhVN in the database
    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeUpdate);

    // Validate the HanhChinhVN in Elasticsearch
    verify(mockHanhChinhVNSearchRepository, times(0)).save(hanhChinhVN);
  }

  @Test
  @Transactional
  void partialUpdateHanhChinhVNWithPatch() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    int databaseSizeBeforeUpdate = hanhChinhVNRepository.findAll().size();

    // Update the hanhChinhVN using partial update
    HanhChinhVN partialUpdatedHanhChinhVN = new HanhChinhVN();
    partialUpdatedHanhChinhVN.setId(hanhChinhVN.getId());

    partialUpdatedHanhChinhVN.pathWithType(UPDATED_PATH_WITH_TYPE);

    restHanhChinhVNMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedHanhChinhVN.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHanhChinhVN))
      )
      .andExpect(status().isOk());

    // Validate the HanhChinhVN in the database
    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeUpdate);
    HanhChinhVN testHanhChinhVN = hanhChinhVNList.get(hanhChinhVNList.size() - 1);
    assertThat(testHanhChinhVN.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testHanhChinhVN.getSlug()).isEqualTo(DEFAULT_SLUG);
    assertThat(testHanhChinhVN.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testHanhChinhVN.getNameWithType()).isEqualTo(DEFAULT_NAME_WITH_TYPE);
    assertThat(testHanhChinhVN.getCode()).isEqualTo(DEFAULT_CODE);
    assertThat(testHanhChinhVN.getParentCode()).isEqualTo(DEFAULT_PARENT_CODE);
    assertThat(testHanhChinhVN.getPath()).isEqualTo(DEFAULT_PATH);
    assertThat(testHanhChinhVN.getPathWithType()).isEqualTo(UPDATED_PATH_WITH_TYPE);
  }

  @Test
  @Transactional
  void fullUpdateHanhChinhVNWithPatch() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    int databaseSizeBeforeUpdate = hanhChinhVNRepository.findAll().size();

    // Update the hanhChinhVN using partial update
    HanhChinhVN partialUpdatedHanhChinhVN = new HanhChinhVN();
    partialUpdatedHanhChinhVN.setId(hanhChinhVN.getId());

    partialUpdatedHanhChinhVN
      .name(UPDATED_NAME)
      .slug(UPDATED_SLUG)
      .type(UPDATED_TYPE)
      .nameWithType(UPDATED_NAME_WITH_TYPE)
      .code(UPDATED_CODE)
      .parentCode(UPDATED_PARENT_CODE)
      .path(UPDATED_PATH)
      .pathWithType(UPDATED_PATH_WITH_TYPE);

    restHanhChinhVNMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedHanhChinhVN.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHanhChinhVN))
      )
      .andExpect(status().isOk());

    // Validate the HanhChinhVN in the database
    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeUpdate);
    HanhChinhVN testHanhChinhVN = hanhChinhVNList.get(hanhChinhVNList.size() - 1);
    assertThat(testHanhChinhVN.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testHanhChinhVN.getSlug()).isEqualTo(UPDATED_SLUG);
    assertThat(testHanhChinhVN.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testHanhChinhVN.getNameWithType()).isEqualTo(UPDATED_NAME_WITH_TYPE);
    assertThat(testHanhChinhVN.getCode()).isEqualTo(UPDATED_CODE);
    assertThat(testHanhChinhVN.getParentCode()).isEqualTo(UPDATED_PARENT_CODE);
    assertThat(testHanhChinhVN.getPath()).isEqualTo(UPDATED_PATH);
    assertThat(testHanhChinhVN.getPathWithType()).isEqualTo(UPDATED_PATH_WITH_TYPE);
  }

  @Test
  @Transactional
  void patchNonExistingHanhChinhVN() throws Exception {
    int databaseSizeBeforeUpdate = hanhChinhVNRepository.findAll().size();
    hanhChinhVN.setId(count.incrementAndGet());

    // Create the HanhChinhVN
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restHanhChinhVNMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, hanhChinhVNDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the HanhChinhVN in the database
    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeUpdate);

    // Validate the HanhChinhVN in Elasticsearch
    verify(mockHanhChinhVNSearchRepository, times(0)).save(hanhChinhVN);
  }

  @Test
  @Transactional
  void patchWithIdMismatchHanhChinhVN() throws Exception {
    int databaseSizeBeforeUpdate = hanhChinhVNRepository.findAll().size();
    hanhChinhVN.setId(count.incrementAndGet());

    // Create the HanhChinhVN
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restHanhChinhVNMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the HanhChinhVN in the database
    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeUpdate);

    // Validate the HanhChinhVN in Elasticsearch
    verify(mockHanhChinhVNSearchRepository, times(0)).save(hanhChinhVN);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamHanhChinhVN() throws Exception {
    int databaseSizeBeforeUpdate = hanhChinhVNRepository.findAll().size();
    hanhChinhVN.setId(count.incrementAndGet());

    // Create the HanhChinhVN
    HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restHanhChinhVNMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the HanhChinhVN in the database
    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeUpdate);

    // Validate the HanhChinhVN in Elasticsearch
    verify(mockHanhChinhVNSearchRepository, times(0)).save(hanhChinhVN);
  }

  @Test
  @Transactional
  void deleteHanhChinhVN() throws Exception {
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

    int databaseSizeBeforeDelete = hanhChinhVNRepository.findAll().size();

    // Delete the hanhChinhVN
    restHanhChinhVNMockMvc
      .perform(delete(ENTITY_API_URL_ID, hanhChinhVN.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
    assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the HanhChinhVN in Elasticsearch
    verify(mockHanhChinhVNSearchRepository, times(1)).deleteById(hanhChinhVN.getId());
  }

  @Test
  @Transactional
  void searchHanhChinhVN() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    hanhChinhVNRepository.saveAndFlush(hanhChinhVN);
    when(mockHanhChinhVNSearchRepository.search(queryStringQuery("id:" + hanhChinhVN.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(hanhChinhVN), PageRequest.of(0, 1), 1));

    // Search the hanhChinhVN
    restHanhChinhVNMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + hanhChinhVN.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(hanhChinhVN.getId().intValue())))
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].nameWithType").value(hasItem(DEFAULT_NAME_WITH_TYPE)))
      .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
      .andExpect(jsonPath("$.[*].parentCode").value(hasItem(DEFAULT_PARENT_CODE)))
      .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
      .andExpect(jsonPath("$.[*].pathWithType").value(hasItem(DEFAULT_PATH_WITH_TYPE)));
  }
}
