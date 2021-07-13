package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.PostComment;
import com.regitiny.catiny.repository.PostCommentRepository;
import com.regitiny.catiny.repository.search.PostCommentSearchRepository;
import com.regitiny.catiny.service.criteria.PostCommentCriteria;
import com.regitiny.catiny.service.dto.PostCommentDTO;
import com.regitiny.catiny.service.mapper.PostCommentMapper;
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
 * Service for executing complex queries for {@link PostComment} entities in the database.
 * The main input is a {@link PostCommentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PostCommentDTO} or a {@link Page} of {@link PostCommentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class PostCommentQueryService extends QueryService<PostComment> {

  private final Logger log = LoggerFactory.getLogger(PostCommentQueryService.class);

  private final PostCommentRepository postCommentRepository;

  private final PostCommentMapper postCommentMapper;

  private final PostCommentSearchRepository postCommentSearchRepository;

  public PostCommentQueryService(
    PostCommentRepository postCommentRepository,
    PostCommentMapper postCommentMapper,
    PostCommentSearchRepository postCommentSearchRepository
  ) {
    this.postCommentRepository = postCommentRepository;
    this.postCommentMapper = postCommentMapper;
    this.postCommentSearchRepository = postCommentSearchRepository;
  }

  /**
   * Return a {@link List} of {@link PostCommentDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<PostCommentDTO> findByCriteria(PostCommentCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<PostComment> specification = createSpecification(criteria);
    return postCommentMapper.toDto(postCommentRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link PostCommentDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<PostCommentDTO> findByCriteria(PostCommentCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<PostComment> specification = createSpecification(criteria);
    return postCommentRepository.findAll(specification, page).map(postCommentMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(PostCommentCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<PostComment> specification = createSpecification(criteria);
    return postCommentRepository.count(specification);
  }

  /**
   * Function to convert {@link PostCommentCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<PostComment> createSpecification(PostCommentCriteria criteria) {
    Specification<PostComment> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), PostComment_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), PostComment_.uuid));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(PostComment_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getCommentReplyId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getCommentReplyId(),
              root -> root.join(PostComment_.commentReplies, JoinType.LEFT).get(PostComment_.id)
            )
          );
      }
      if (criteria.getUserCommentId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getUserCommentId(), root -> root.join(PostComment_.userComment, JoinType.LEFT).get(MasterUser_.id))
          );
      }
      if (criteria.getPostId() != null) {
        specification =
          specification.and(buildSpecification(criteria.getPostId(), root -> root.join(PostComment_.post, JoinType.LEFT).get(Post_.id)));
      }
      if (criteria.getCommentParentId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getCommentParentId(),
              root -> root.join(PostComment_.commentParent, JoinType.LEFT).get(PostComment_.id)
            )
          );
      }
    }
    return specification;
  }
}
