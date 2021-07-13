package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.Event;
import com.regitiny.catiny.repository.EventRepository;
import com.regitiny.catiny.repository.search.EventSearchRepository;
import com.regitiny.catiny.service.criteria.EventCriteria;
import com.regitiny.catiny.service.dto.EventDTO;
import com.regitiny.catiny.service.mapper.EventMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Event} entities in the database.
 * The main input is a {@link EventCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EventDTO} or a {@link Page} of {@link EventDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class EventQueryService extends QueryService<Event> {

  private final Logger log = LoggerFactory.getLogger(EventQueryService.class);

  private final EventRepository eventRepository;

  private final EventMapper eventMapper;

  private final EventSearchRepository eventSearchRepository;

  public EventQueryService(EventRepository eventRepository, EventMapper eventMapper, EventSearchRepository eventSearchRepository) {
    this.eventRepository = eventRepository;
    this.eventMapper = eventMapper;
    this.eventSearchRepository = eventSearchRepository;
  }

  /**
   * Return a {@link List} of {@link EventDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<EventDTO> findByCriteria(EventCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<Event> specification = createSpecification(criteria);
    return eventMapper.toDto(eventRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link EventDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<EventDTO> findByCriteria(EventCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<Event> specification = createSpecification(criteria);
    return eventRepository.findAll(specification, page).map(eventMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(EventCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<Event> specification = createSpecification(criteria);
    return eventRepository.count(specification);
  }

  /**
   * Function to convert {@link EventCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<Event> createSpecification(EventCriteria criteria) {
    Specification<Event> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), Event_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), Event_.uuid));
      }
      if (criteria.getTitle() != null) {
        specification = specification.and(buildStringSpecification(criteria.getTitle(), Event_.title));
      }
      if (criteria.getType() != null) {
        specification = specification.and(buildSpecification(criteria.getType(), Event_.type));
      }
      if (criteria.getStartTime() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getStartTime(), Event_.startTime));
      }
      if (criteria.getEndTime() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getEndTime(), Event_.endTime));
      }
      if (criteria.getTagLine() != null) {
        specification = specification.and(buildStringSpecification(criteria.getTagLine(), Event_.tagLine));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(Event_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getOtherImageId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getOtherImageId(), root -> root.join(Event_.otherImages, JoinType.LEFT).get(Image_.id))
          );
      }
      if (criteria.getOtherVideoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getOtherVideoId(), root -> root.join(Event_.otherVideos, JoinType.LEFT).get(Video_.id))
          );
      }
    }
    return specification;
  }
}
