package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.repository.MessageGroupRepository;
import com.regitiny.catiny.repository.search.MessageGroupSearchRepository;
import com.regitiny.catiny.service.criteria.MessageGroupCriteria;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import com.regitiny.catiny.service.mapper.MessageGroupMapper;
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
 * Service for executing complex queries for {@link MessageGroup} entities in the database.
 * The main input is a {@link MessageGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MessageGroupDTO} or a {@link Page} of {@link MessageGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class MessageGroupQueryService extends QueryService<MessageGroup> {

    private final Logger log = LoggerFactory.getLogger(MessageGroupQueryService.class);

    private final MessageGroupRepository messageGroupRepository;

    private final MessageGroupMapper messageGroupMapper;

    private final MessageGroupSearchRepository messageGroupSearchRepository;

    public MessageGroupQueryService(
        MessageGroupRepository messageGroupRepository,
        MessageGroupMapper messageGroupMapper,
        MessageGroupSearchRepository messageGroupSearchRepository
    ) {
        this.messageGroupRepository = messageGroupRepository;
        this.messageGroupMapper = messageGroupMapper;
        this.messageGroupSearchRepository = messageGroupSearchRepository;
    }

    /**
     * Return a {@link List} of {@link MessageGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MessageGroupDTO> findByCriteria(MessageGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MessageGroup> specification = createSpecification(criteria);
        return messageGroupMapper.toDto(messageGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MessageGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MessageGroupDTO> findByCriteria(MessageGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MessageGroup> specification = createSpecification(criteria);
        return messageGroupRepository.findAll(specification, page).map(messageGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MessageGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MessageGroup> specification = createSpecification(criteria);
        return messageGroupRepository.count(specification);
    }

    /**
     * Function to convert {@link MessageGroupCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<MessageGroup> createSpecification(MessageGroupCriteria criteria) {
        Specification<MessageGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), MessageGroup_.id));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildSpecification(criteria.getUuid(), MessageGroup_.uuid));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUserId(), MessageGroup_.userId));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGroupId(), MessageGroup_.groupId));
            }
            if (criteria.getGroupName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGroupName(), MessageGroup_.groupName));
            }
            if (criteria.getAddBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddBy(), MessageGroup_.addBy));
            }
            if (criteria.getRole() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRole(), MessageGroup_.role));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), MessageGroup_.createdDate));
            }
            if (criteria.getModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifiedDate(), MessageGroup_.modifiedDate));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), MessageGroup_.createdBy));
            }
            if (criteria.getModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifiedBy(), MessageGroup_.modifiedBy));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), MessageGroup_.comment));
            }
        }
        return specification;
    }
}
