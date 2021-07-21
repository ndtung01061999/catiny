package com.regitiny.catiny.service.impl;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.HistoryUpdate;
import com.regitiny.catiny.repository.HistoryUpdateRepository;
import com.regitiny.catiny.repository.search.HistoryUpdateSearchRepository;
import com.regitiny.catiny.service.HistoryUpdateService;
import com.regitiny.catiny.service.dto.HistoryUpdateDTO;
import com.regitiny.catiny.service.mapper.HistoryUpdateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing {@link HistoryUpdate}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class HistoryUpdateServiceImpl implements HistoryUpdateService
{

  private final Logger log = LoggerFactory.getLogger(HistoryUpdateServiceImpl.class);

  private final HistoryUpdateRepository historyUpdateRepository;

  private final HistoryUpdateMapper historyUpdateMapper;

  private final HistoryUpdateSearchRepository historyUpdateSearchRepository;

  public HistoryUpdateServiceImpl(
    HistoryUpdateRepository historyUpdateRepository,
    HistoryUpdateMapper historyUpdateMapper,
    HistoryUpdateSearchRepository historyUpdateSearchRepository
  )
  {
    this.historyUpdateRepository = historyUpdateRepository;
    this.historyUpdateMapper = historyUpdateMapper;
    this.historyUpdateSearchRepository = historyUpdateSearchRepository;
  }

  @Override
  public HistoryUpdateDTO save(HistoryUpdateDTO historyUpdateDTO)
  {
    log.debug("Request to save HistoryUpdate : {}", historyUpdateDTO);
    HistoryUpdate historyUpdate = historyUpdateMapper.toEntity(historyUpdateDTO);
    historyUpdate = historyUpdateRepository.save(historyUpdate);
    HistoryUpdateDTO result = historyUpdateMapper.toDto(historyUpdate);
    historyUpdateSearchRepository.save(historyUpdate);
    return result;
  }

  @Override
  public Optional<HistoryUpdateDTO> partialUpdate(HistoryUpdateDTO historyUpdateDTO)
  {
    log.debug("Request to partially update HistoryUpdate : {}", historyUpdateDTO);

    return historyUpdateRepository
      .findById(historyUpdateDTO.getId())
      .map(
        existingHistoryUpdate ->
        {
          historyUpdateMapper.partialUpdate(existingHistoryUpdate, historyUpdateDTO);

          return existingHistoryUpdate;
        }
      )
      .map(historyUpdateRepository::save)
      .map(
        savedHistoryUpdate ->
        {
          historyUpdateSearchRepository.save(savedHistoryUpdate);

          return savedHistoryUpdate;
        }
      )
      .map(historyUpdateMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<HistoryUpdateDTO> findAll(Pageable pageable)
  {
    log.debug("Request to get all HistoryUpdates");
    return historyUpdateRepository.findAll(pageable).map(historyUpdateMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<HistoryUpdateDTO> findOne(Long id)
  {
    log.debug("Request to get HistoryUpdate : {}", id);
    return historyUpdateRepository.findById(id).map(historyUpdateMapper::toDto);
  }

  @Override
  public void delete(Long id)
  {
    log.debug("Request to delete HistoryUpdate : {}", id);
    historyUpdateRepository.deleteById(id);
    historyUpdateSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<HistoryUpdateDTO> search(String query, Pageable pageable)
  {
    log.debug("Request to search for a page of HistoryUpdates for query {}", query);
    return historyUpdateSearchRepository.search(queryStringQuery(query), pageable).map(historyUpdateMapper::toDto);
  }
}
