package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.TopicInterest;
import com.regitiny.catiny.repository.TopicInterestRepository;
import com.regitiny.catiny.repository.search.TopicInterestSearchRepository;
import com.regitiny.catiny.service.TopicInterestService;
import com.regitiny.catiny.service.dto.TopicInterestDTO;
import com.regitiny.catiny.service.mapper.TopicInterestMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TopicInterest}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class TopicInterestServiceImpl implements TopicInterestService {

  private final Logger log = LoggerFactory.getLogger(TopicInterestServiceImpl.class);

  private final TopicInterestRepository topicInterestRepository;

  private final TopicInterestMapper topicInterestMapper;

  private final TopicInterestSearchRepository topicInterestSearchRepository;

  public TopicInterestServiceImpl(
    TopicInterestRepository topicInterestRepository,
    TopicInterestMapper topicInterestMapper,
    TopicInterestSearchRepository topicInterestSearchRepository
  ) {
    this.topicInterestRepository = topicInterestRepository;
    this.topicInterestMapper = topicInterestMapper;
    this.topicInterestSearchRepository = topicInterestSearchRepository;
  }

  @Override
  public TopicInterestDTO save(TopicInterestDTO topicInterestDTO) {
    log.debug("Request to save TopicInterest : {}", topicInterestDTO);
    TopicInterest topicInterest = topicInterestMapper.toEntity(topicInterestDTO);
    topicInterest = topicInterestRepository.save(topicInterest);
    TopicInterestDTO result = topicInterestMapper.toDto(topicInterest);
    topicInterestSearchRepository.save(topicInterest);
    return result;
  }

  @Override
  public Optional<TopicInterestDTO> partialUpdate(TopicInterestDTO topicInterestDTO) {
    log.debug("Request to partially update TopicInterest : {}", topicInterestDTO);

    return topicInterestRepository
      .findById(topicInterestDTO.getId())
      .map(
        existingTopicInterest -> {
          topicInterestMapper.partialUpdate(existingTopicInterest, topicInterestDTO);

          return existingTopicInterest;
        }
      )
      .map(topicInterestRepository::save)
      .map(
        savedTopicInterest -> {
          topicInterestSearchRepository.save(savedTopicInterest);

          return savedTopicInterest;
        }
      )
      .map(topicInterestMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<TopicInterestDTO> findAll(Pageable pageable) {
    log.debug("Request to get all TopicInterests");
    return topicInterestRepository.findAll(pageable).map(topicInterestMapper::toDto);
  }

  public Page<TopicInterestDTO> findAllWithEagerRelationships(Pageable pageable) {
    return topicInterestRepository.findAllWithEagerRelationships(pageable).map(topicInterestMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<TopicInterestDTO> findOne(Long id) {
    log.debug("Request to get TopicInterest : {}", id);
    return topicInterestRepository.findOneWithEagerRelationships(id).map(topicInterestMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete TopicInterest : {}", id);
    topicInterestRepository.deleteById(id);
    topicInterestSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<TopicInterestDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of TopicInterests for query {}", query);
    return topicInterestSearchRepository.search(queryStringQuery(query), pageable).map(topicInterestMapper::toDto);
  }
}
