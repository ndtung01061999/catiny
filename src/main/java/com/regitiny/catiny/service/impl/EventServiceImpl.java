package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Event;
import com.regitiny.catiny.repository.EventRepository;
import com.regitiny.catiny.repository.search.EventSearchRepository;
import com.regitiny.catiny.service.EventService;
import com.regitiny.catiny.service.dto.EventDTO;
import com.regitiny.catiny.service.mapper.EventMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Event}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class EventServiceImpl implements EventService {

  private final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

  private final EventRepository eventRepository;

  private final EventMapper eventMapper;

  private final EventSearchRepository eventSearchRepository;

  public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, EventSearchRepository eventSearchRepository) {
    this.eventRepository = eventRepository;
    this.eventMapper = eventMapper;
    this.eventSearchRepository = eventSearchRepository;
  }

  @Override
  public EventDTO save(EventDTO eventDTO) {
    log.debug("Request to save Event : {}", eventDTO);
    Event event = eventMapper.toEntity(eventDTO);
    event = eventRepository.save(event);
    EventDTO result = eventMapper.toDto(event);
    eventSearchRepository.save(event);
    return result;
  }

  @Override
  public Optional<EventDTO> partialUpdate(EventDTO eventDTO) {
    log.debug("Request to partially update Event : {}", eventDTO);

    return eventRepository
      .findById(eventDTO.getId())
      .map(
        existingEvent -> {
          eventMapper.partialUpdate(existingEvent, eventDTO);

          return existingEvent;
        }
      )
      .map(eventRepository::save)
      .map(
        savedEvent -> {
          eventSearchRepository.save(savedEvent);

          return savedEvent;
        }
      )
      .map(eventMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<EventDTO> findAll(Pageable pageable) {
    log.debug("Request to get all Events");
    return eventRepository.findAll(pageable).map(eventMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<EventDTO> findOne(Long id) {
    log.debug("Request to get Event : {}", id);
    return eventRepository.findById(id).map(eventMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete Event : {}", id);
    eventRepository.deleteById(id);
    eventSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<EventDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of Events for query {}", query);
    return eventSearchRepository.search(queryStringQuery(query), pageable).map(eventMapper::toDto);
  }
}
