package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.NewsFeed;
import com.regitiny.catiny.repository.NewsFeedRepository;
import com.regitiny.catiny.repository.search.NewsFeedSearchRepository;
import com.regitiny.catiny.service.NewsFeedService;
import com.regitiny.catiny.service.dto.NewsFeedDTO;
import com.regitiny.catiny.service.mapper.NewsFeedMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NewsFeed}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class NewsFeedServiceImpl implements NewsFeedService {

  private final Logger log = LoggerFactory.getLogger(NewsFeedServiceImpl.class);

  private final NewsFeedRepository newsFeedRepository;

  private final NewsFeedMapper newsFeedMapper;

  private final NewsFeedSearchRepository newsFeedSearchRepository;

  public NewsFeedServiceImpl(
    NewsFeedRepository newsFeedRepository,
    NewsFeedMapper newsFeedMapper,
    NewsFeedSearchRepository newsFeedSearchRepository
  ) {
    this.newsFeedRepository = newsFeedRepository;
    this.newsFeedMapper = newsFeedMapper;
    this.newsFeedSearchRepository = newsFeedSearchRepository;
  }

  @Override
  public NewsFeedDTO save(NewsFeedDTO newsFeedDTO) {
    log.debug("Request to save NewsFeed : {}", newsFeedDTO);
    NewsFeed newsFeed = newsFeedMapper.toEntity(newsFeedDTO);
    newsFeed = newsFeedRepository.save(newsFeed);
    NewsFeedDTO result = newsFeedMapper.toDto(newsFeed);
    newsFeedSearchRepository.save(newsFeed);
    return result;
  }

  @Override
  public Optional<NewsFeedDTO> partialUpdate(NewsFeedDTO newsFeedDTO) {
    log.debug("Request to partially update NewsFeed : {}", newsFeedDTO);

    return newsFeedRepository
      .findById(newsFeedDTO.getId())
      .map(
        existingNewsFeed -> {
          newsFeedMapper.partialUpdate(existingNewsFeed, newsFeedDTO);

          return existingNewsFeed;
        }
      )
      .map(newsFeedRepository::save)
      .map(
        savedNewsFeed -> {
          newsFeedSearchRepository.save(savedNewsFeed);

          return savedNewsFeed;
        }
      )
      .map(newsFeedMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<NewsFeedDTO> findAll(Pageable pageable) {
    log.debug("Request to get all NewsFeeds");
    return newsFeedRepository.findAll(pageable).map(newsFeedMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<NewsFeedDTO> findOne(Long id) {
    log.debug("Request to get NewsFeed : {}", id);
    return newsFeedRepository.findById(id).map(newsFeedMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete NewsFeed : {}", id);
    newsFeedRepository.deleteById(id);
    newsFeedSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<NewsFeedDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of NewsFeeds for query {}", query);
    return newsFeedSearchRepository.search(queryStringQuery(query), pageable).map(newsFeedMapper::toDto);
  }
}
