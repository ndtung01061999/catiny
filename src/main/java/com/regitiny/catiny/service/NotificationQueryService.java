package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.Notification;
import com.regitiny.catiny.repository.NotificationRepository;
import com.regitiny.catiny.repository.search.NotificationSearchRepository;
import com.regitiny.catiny.service.criteria.NotificationCriteria;
import com.regitiny.catiny.service.dto.NotificationDTO;
import com.regitiny.catiny.service.mapper.NotificationMapper;
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
 * Service for executing complex queries for {@link Notification} entities in the database.
 * The main input is a {@link NotificationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NotificationDTO} or a {@link Page} of {@link NotificationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class NotificationQueryService extends QueryService<Notification> {

  private final Logger log = LoggerFactory.getLogger(NotificationQueryService.class);

  private final NotificationRepository notificationRepository;

  private final NotificationMapper notificationMapper;

  private final NotificationSearchRepository notificationSearchRepository;

  public NotificationQueryService(
    NotificationRepository notificationRepository,
    NotificationMapper notificationMapper,
    NotificationSearchRepository notificationSearchRepository
  ) {
    this.notificationRepository = notificationRepository;
    this.notificationMapper = notificationMapper;
    this.notificationSearchRepository = notificationSearchRepository;
  }

  /**
   * Return a {@link List} of {@link NotificationDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<NotificationDTO> findByCriteria(NotificationCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<Notification> specification = createSpecification(criteria);
    return notificationMapper.toDto(notificationRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link NotificationDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<NotificationDTO> findByCriteria(NotificationCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<Notification> specification = createSpecification(criteria);
    return notificationRepository.findAll(specification, page).map(notificationMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(NotificationCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<Notification> specification = createSpecification(criteria);
    return notificationRepository.count(specification);
  }

  /**
   * Function to convert {@link NotificationCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<Notification> createSpecification(NotificationCriteria criteria) {
    Specification<Notification> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), Notification_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), Notification_.uuid));
      }
      if (criteria.getNotifyType() != null) {
        specification = specification.and(buildSpecification(criteria.getNotifyType(), Notification_.notifyType));
      }
      if (criteria.getTitle() != null) {
        specification = specification.and(buildStringSpecification(criteria.getTitle(), Notification_.title));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(Notification_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getMasterUserId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getMasterUserId(), root -> root.join(Notification_.masterUser, JoinType.LEFT).get(MasterUser_.id))
          );
      }
    }
    return specification;
  }
}
