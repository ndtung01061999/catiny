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
import com.regitiny.catiny.domain.TodoList;
import com.regitiny.catiny.repository.TodoListRepository;
import com.regitiny.catiny.repository.search.TodoListSearchRepository;
import com.regitiny.catiny.service.criteria.TodoListCriteria;
import com.regitiny.catiny.service.dto.TodoListDTO;
import com.regitiny.catiny.service.mapper.TodoListMapper;
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
 * Integration tests for the {@link TodoListResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class TodoListResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_TITLE = "AAAAAAAAAA";
  private static final String UPDATED_TITLE = "BBBBBBBBBB";

  private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
  private static final String UPDATED_CONTENT = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/todo-lists";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/todo-lists";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private TodoListRepository todoListRepository;

  @Autowired
  private TodoListMapper todoListMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.TodoListSearchRepositoryMockConfiguration
   */
  @Autowired
  private TodoListSearchRepository mockTodoListSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restTodoListMockMvc;

  private TodoList todoList;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static TodoList createEntity(EntityManager em) {
    TodoList todoList = new TodoList().uuid(DEFAULT_UUID).title(DEFAULT_TITLE).content(DEFAULT_CONTENT);
    return todoList;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static TodoList createUpdatedEntity(EntityManager em) {
    TodoList todoList = new TodoList().uuid(UPDATED_UUID).title(UPDATED_TITLE).content(UPDATED_CONTENT);
    return todoList;
  }

  @BeforeEach
  public void initTest() {
    todoList = createEntity(em);
  }

  @Test
  @Transactional
  void createTodoList() throws Exception {
    int databaseSizeBeforeCreate = todoListRepository.findAll().size();
    // Create the TodoList
    TodoListDTO todoListDTO = todoListMapper.toDto(todoList);
    restTodoListMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(todoListDTO)))
      .andExpect(status().isCreated());

    // Validate the TodoList in the database
    List<TodoList> todoListList = todoListRepository.findAll();
    assertThat(todoListList).hasSize(databaseSizeBeforeCreate + 1);
    TodoList testTodoList = todoListList.get(todoListList.size() - 1);
    assertThat(testTodoList.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testTodoList.getTitle()).isEqualTo(DEFAULT_TITLE);
    assertThat(testTodoList.getContent()).isEqualTo(DEFAULT_CONTENT);

    // Validate the TodoList in Elasticsearch
    verify(mockTodoListSearchRepository, times(1)).save(testTodoList);
  }

  @Test
  @Transactional
  void createTodoListWithExistingId() throws Exception {
    // Create the TodoList with an existing ID
    todoList.setId(1L);
    TodoListDTO todoListDTO = todoListMapper.toDto(todoList);

    int databaseSizeBeforeCreate = todoListRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restTodoListMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(todoListDTO)))
      .andExpect(status().isBadRequest());

    // Validate the TodoList in the database
    List<TodoList> todoListList = todoListRepository.findAll();
    assertThat(todoListList).hasSize(databaseSizeBeforeCreate);

    // Validate the TodoList in Elasticsearch
    verify(mockTodoListSearchRepository, times(0)).save(todoList);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = todoListRepository.findAll().size();
    // set the field null
    todoList.setUuid(null);

    // Create the TodoList, which fails.
    TodoListDTO todoListDTO = todoListMapper.toDto(todoList);

    restTodoListMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(todoListDTO)))
      .andExpect(status().isBadRequest());

    List<TodoList> todoListList = todoListRepository.findAll();
    assertThat(todoListList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllTodoLists() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    // Get all the todoListList
    restTodoListMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(todoList.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));
  }

  @Test
  @Transactional
  void getTodoList() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    // Get the todoList
    restTodoListMockMvc
      .perform(get(ENTITY_API_URL_ID, todoList.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(todoList.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
      .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()));
  }

  @Test
  @Transactional
  void getTodoListsByIdFiltering() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    Long id = todoList.getId();

    defaultTodoListShouldBeFound("id.equals=" + id);
    defaultTodoListShouldNotBeFound("id.notEquals=" + id);

    defaultTodoListShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultTodoListShouldNotBeFound("id.greaterThan=" + id);

    defaultTodoListShouldBeFound("id.lessThanOrEqual=" + id);
    defaultTodoListShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllTodoListsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    // Get all the todoListList where uuid equals to DEFAULT_UUID
    defaultTodoListShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the todoListList where uuid equals to UPDATED_UUID
    defaultTodoListShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllTodoListsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    // Get all the todoListList where uuid not equals to DEFAULT_UUID
    defaultTodoListShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the todoListList where uuid not equals to UPDATED_UUID
    defaultTodoListShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllTodoListsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    // Get all the todoListList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultTodoListShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the todoListList where uuid equals to UPDATED_UUID
    defaultTodoListShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllTodoListsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    // Get all the todoListList where uuid is not null
    defaultTodoListShouldBeFound("uuid.specified=true");

    // Get all the todoListList where uuid is null
    defaultTodoListShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllTodoListsByTitleIsEqualToSomething() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    // Get all the todoListList where title equals to DEFAULT_TITLE
    defaultTodoListShouldBeFound("title.equals=" + DEFAULT_TITLE);

    // Get all the todoListList where title equals to UPDATED_TITLE
    defaultTodoListShouldNotBeFound("title.equals=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllTodoListsByTitleIsNotEqualToSomething() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    // Get all the todoListList where title not equals to DEFAULT_TITLE
    defaultTodoListShouldNotBeFound("title.notEquals=" + DEFAULT_TITLE);

    // Get all the todoListList where title not equals to UPDATED_TITLE
    defaultTodoListShouldBeFound("title.notEquals=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllTodoListsByTitleIsInShouldWork() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    // Get all the todoListList where title in DEFAULT_TITLE or UPDATED_TITLE
    defaultTodoListShouldBeFound("title.in=" + DEFAULT_TITLE + "," + UPDATED_TITLE);

    // Get all the todoListList where title equals to UPDATED_TITLE
    defaultTodoListShouldNotBeFound("title.in=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllTodoListsByTitleIsNullOrNotNull() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    // Get all the todoListList where title is not null
    defaultTodoListShouldBeFound("title.specified=true");

    // Get all the todoListList where title is null
    defaultTodoListShouldNotBeFound("title.specified=false");
  }

  @Test
  @Transactional
  void getAllTodoListsByTitleContainsSomething() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    // Get all the todoListList where title contains DEFAULT_TITLE
    defaultTodoListShouldBeFound("title.contains=" + DEFAULT_TITLE);

    // Get all the todoListList where title contains UPDATED_TITLE
    defaultTodoListShouldNotBeFound("title.contains=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllTodoListsByTitleNotContainsSomething() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    // Get all the todoListList where title does not contain DEFAULT_TITLE
    defaultTodoListShouldNotBeFound("title.doesNotContain=" + DEFAULT_TITLE);

    // Get all the todoListList where title does not contain UPDATED_TITLE
    defaultTodoListShouldBeFound("title.doesNotContain=" + UPDATED_TITLE);
  }

  @Test
  @Transactional
  void getAllTodoListsByBaseInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);
    BaseInfo baseInfo = BaseInfoResourceIT.createEntity(em);
    em.persist(baseInfo);
    em.flush();
    todoList.setBaseInfo(baseInfo);
    todoListRepository.saveAndFlush(todoList);
    Long baseInfoId = baseInfo.getId();

    // Get all the todoListList where baseInfo equals to baseInfoId
    defaultTodoListShouldBeFound("baseInfoId.equals=" + baseInfoId);

    // Get all the todoListList where baseInfo equals to (baseInfoId + 1)
    defaultTodoListShouldNotBeFound("baseInfoId.equals=" + (baseInfoId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultTodoListShouldBeFound(String filter) throws Exception {
    restTodoListMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(todoList.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));

    // Check, that the count call also returns 1
    restTodoListMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultTodoListShouldNotBeFound(String filter) throws Exception {
    restTodoListMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restTodoListMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingTodoList() throws Exception {
    // Get the todoList
    restTodoListMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewTodoList() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    int databaseSizeBeforeUpdate = todoListRepository.findAll().size();

    // Update the todoList
    TodoList updatedTodoList = todoListRepository.findById(todoList.getId()).get();
    // Disconnect from session so that the updates on updatedTodoList are not directly saved in db
    em.detach(updatedTodoList);
    updatedTodoList.uuid(UPDATED_UUID).title(UPDATED_TITLE).content(UPDATED_CONTENT);
    TodoListDTO todoListDTO = todoListMapper.toDto(updatedTodoList);

    restTodoListMockMvc
      .perform(
        put(ENTITY_API_URL_ID, todoListDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(todoListDTO))
      )
      .andExpect(status().isOk());

    // Validate the TodoList in the database
    List<TodoList> todoListList = todoListRepository.findAll();
    assertThat(todoListList).hasSize(databaseSizeBeforeUpdate);
    TodoList testTodoList = todoListList.get(todoListList.size() - 1);
    assertThat(testTodoList.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testTodoList.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testTodoList.getContent()).isEqualTo(UPDATED_CONTENT);

    // Validate the TodoList in Elasticsearch
    verify(mockTodoListSearchRepository).save(testTodoList);
  }

  @Test
  @Transactional
  void putNonExistingTodoList() throws Exception {
    int databaseSizeBeforeUpdate = todoListRepository.findAll().size();
    todoList.setId(count.incrementAndGet());

    // Create the TodoList
    TodoListDTO todoListDTO = todoListMapper.toDto(todoList);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restTodoListMockMvc
      .perform(
        put(ENTITY_API_URL_ID, todoListDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(todoListDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TodoList in the database
    List<TodoList> todoListList = todoListRepository.findAll();
    assertThat(todoListList).hasSize(databaseSizeBeforeUpdate);

    // Validate the TodoList in Elasticsearch
    verify(mockTodoListSearchRepository, times(0)).save(todoList);
  }

  @Test
  @Transactional
  void putWithIdMismatchTodoList() throws Exception {
    int databaseSizeBeforeUpdate = todoListRepository.findAll().size();
    todoList.setId(count.incrementAndGet());

    // Create the TodoList
    TodoListDTO todoListDTO = todoListMapper.toDto(todoList);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTodoListMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(todoListDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TodoList in the database
    List<TodoList> todoListList = todoListRepository.findAll();
    assertThat(todoListList).hasSize(databaseSizeBeforeUpdate);

    // Validate the TodoList in Elasticsearch
    verify(mockTodoListSearchRepository, times(0)).save(todoList);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamTodoList() throws Exception {
    int databaseSizeBeforeUpdate = todoListRepository.findAll().size();
    todoList.setId(count.incrementAndGet());

    // Create the TodoList
    TodoListDTO todoListDTO = todoListMapper.toDto(todoList);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTodoListMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(todoListDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the TodoList in the database
    List<TodoList> todoListList = todoListRepository.findAll();
    assertThat(todoListList).hasSize(databaseSizeBeforeUpdate);

    // Validate the TodoList in Elasticsearch
    verify(mockTodoListSearchRepository, times(0)).save(todoList);
  }

  @Test
  @Transactional
  void partialUpdateTodoListWithPatch() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    int databaseSizeBeforeUpdate = todoListRepository.findAll().size();

    // Update the todoList using partial update
    TodoList partialUpdatedTodoList = new TodoList();
    partialUpdatedTodoList.setId(todoList.getId());

    partialUpdatedTodoList.title(UPDATED_TITLE).content(UPDATED_CONTENT);

    restTodoListMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedTodoList.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTodoList))
      )
      .andExpect(status().isOk());

    // Validate the TodoList in the database
    List<TodoList> todoListList = todoListRepository.findAll();
    assertThat(todoListList).hasSize(databaseSizeBeforeUpdate);
    TodoList testTodoList = todoListList.get(todoListList.size() - 1);
    assertThat(testTodoList.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testTodoList.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testTodoList.getContent()).isEqualTo(UPDATED_CONTENT);
  }

  @Test
  @Transactional
  void fullUpdateTodoListWithPatch() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    int databaseSizeBeforeUpdate = todoListRepository.findAll().size();

    // Update the todoList using partial update
    TodoList partialUpdatedTodoList = new TodoList();
    partialUpdatedTodoList.setId(todoList.getId());

    partialUpdatedTodoList.uuid(UPDATED_UUID).title(UPDATED_TITLE).content(UPDATED_CONTENT);

    restTodoListMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedTodoList.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTodoList))
      )
      .andExpect(status().isOk());

    // Validate the TodoList in the database
    List<TodoList> todoListList = todoListRepository.findAll();
    assertThat(todoListList).hasSize(databaseSizeBeforeUpdate);
    TodoList testTodoList = todoListList.get(todoListList.size() - 1);
    assertThat(testTodoList.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testTodoList.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testTodoList.getContent()).isEqualTo(UPDATED_CONTENT);
  }

  @Test
  @Transactional
  void patchNonExistingTodoList() throws Exception {
    int databaseSizeBeforeUpdate = todoListRepository.findAll().size();
    todoList.setId(count.incrementAndGet());

    // Create the TodoList
    TodoListDTO todoListDTO = todoListMapper.toDto(todoList);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restTodoListMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, todoListDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(todoListDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TodoList in the database
    List<TodoList> todoListList = todoListRepository.findAll();
    assertThat(todoListList).hasSize(databaseSizeBeforeUpdate);

    // Validate the TodoList in Elasticsearch
    verify(mockTodoListSearchRepository, times(0)).save(todoList);
  }

  @Test
  @Transactional
  void patchWithIdMismatchTodoList() throws Exception {
    int databaseSizeBeforeUpdate = todoListRepository.findAll().size();
    todoList.setId(count.incrementAndGet());

    // Create the TodoList
    TodoListDTO todoListDTO = todoListMapper.toDto(todoList);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTodoListMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(todoListDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TodoList in the database
    List<TodoList> todoListList = todoListRepository.findAll();
    assertThat(todoListList).hasSize(databaseSizeBeforeUpdate);

    // Validate the TodoList in Elasticsearch
    verify(mockTodoListSearchRepository, times(0)).save(todoList);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamTodoList() throws Exception {
    int databaseSizeBeforeUpdate = todoListRepository.findAll().size();
    todoList.setId(count.incrementAndGet());

    // Create the TodoList
    TodoListDTO todoListDTO = todoListMapper.toDto(todoList);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTodoListMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(todoListDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the TodoList in the database
    List<TodoList> todoListList = todoListRepository.findAll();
    assertThat(todoListList).hasSize(databaseSizeBeforeUpdate);

    // Validate the TodoList in Elasticsearch
    verify(mockTodoListSearchRepository, times(0)).save(todoList);
  }

  @Test
  @Transactional
  void deleteTodoList() throws Exception {
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);

    int databaseSizeBeforeDelete = todoListRepository.findAll().size();

    // Delete the todoList
    restTodoListMockMvc
      .perform(delete(ENTITY_API_URL_ID, todoList.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<TodoList> todoListList = todoListRepository.findAll();
    assertThat(todoListList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the TodoList in Elasticsearch
    verify(mockTodoListSearchRepository, times(1)).deleteById(todoList.getId());
  }

  @Test
  @Transactional
  void searchTodoList() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    todoListRepository.saveAndFlush(todoList);
    when(mockTodoListSearchRepository.search(queryStringQuery("id:" + todoList.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(todoList), PageRequest.of(0, 1), 1));

    // Search the todoList
    restTodoListMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + todoList.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(todoList.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));
  }
}
