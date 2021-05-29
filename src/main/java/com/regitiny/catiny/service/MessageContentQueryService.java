package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.repository.MessageContentRepository;
import com.regitiny.catiny.repository.search.MessageContentSearchRepository;
import com.regitiny.catiny.service.criteria.MessageContentCriteria;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.service.mapper.MessageContentMapper;
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
 * Service for executing complex queries for {@link MessageContent} entities in the database.
 * The main input is a {@link MessageContentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MessageContentDTO} or a {@link Page} of {@link MessageContentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class MessageContentQueryService extends QueryService<MessageContent> {

  private final Logger log = LoggerFactory.getLogger(MessageContentQueryService.class);

  private final MessageContentRepository messageContentRepository;

  private final MessageContentMapper messageContentMapper;

  private final MessageContentSearchRepository messageContentSearchRepository;

  public MessageContentQueryService(
    MessageContentRepository messageContentRepository,
    MessageContentMapper messageContentMapper,
    MessageContentSearchRepository messageContentSearchRepository
  ) {
    this.messageContentRepository = messageContentRepository;
    this.messageContentMapper = messageContentMapper;
    this.messageContentSearchRepository = messageContentSearchRepository;
  }

  /**
   * Return a {@link List} of {@link MessageContentDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<MessageContentDTO> findByCriteria(MessageContentCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<MessageContent> specification = createSpecification(criteria);
    return messageContentMapper.toDto(messageContentRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link MessageContentDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<MessageContentDTO> findByCriteria(MessageContentCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<MessageContent> specification = createSpecification(criteria);
    return messageContentRepository.findAll(specification, page).map(messageContentMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(MessageContentCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<MessageContent> specification = createSpecification(criteria);
    return messageContentRepository.count(specification);
  }

  /**
   * Function to convert {@link MessageContentCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<MessageContent> createSpecification(MessageContentCriteria criteria) {
    Specification<MessageContent> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), MessageContent_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), MessageContent_.uuid));
      }
      if (criteria.getGroupId() != null) {
        specification = specification.and(buildStringSpecification(criteria.getGroupId(), MessageContent_.groupId));
      }
      if (criteria.getSender() != null) {
        specification = specification.and(buildStringSpecification(criteria.getSender(), MessageContent_.sender));
      }
      if (criteria.getStatus() != null) {
        specification = specification.and(buildStringSpecification(criteria.getStatus(), MessageContent_.status));
      }
      if (criteria.getRole() != null) {
        specification = specification.and(buildStringSpecification(criteria.getRole(), MessageContent_.role));
      }
      if (criteria.getCreatedDate() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), MessageContent_.createdDate));
      }
      if (criteria.getModifiedDate() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getModifiedDate(), MessageContent_.modifiedDate));
      }
      if (criteria.getCreatedBy() != null) {
        specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), MessageContent_.createdBy));
      }
      if (criteria.getModifiedBy() != null) {
        specification = specification.and(buildStringSpecification(criteria.getModifiedBy(), MessageContent_.modifiedBy));
      }
      if (criteria.getComment() != null) {
        specification = specification.and(buildStringSpecification(criteria.getComment(), MessageContent_.comment));
      }
    }
    return specification;
  }
}
