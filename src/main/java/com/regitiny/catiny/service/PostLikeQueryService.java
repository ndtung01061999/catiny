package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.PostLike;
import com.regitiny.catiny.repository.PostLikeRepository;
import com.regitiny.catiny.repository.search.PostLikeSearchRepository;
import com.regitiny.catiny.service.criteria.PostLikeCriteria;
import com.regitiny.catiny.service.dto.PostLikeDTO;
import com.regitiny.catiny.service.mapper.PostLikeMapper;
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
 * Service for executing complex queries for {@link PostLike} entities in the database.
 * The main input is a {@link PostLikeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PostLikeDTO} or a {@link Page} of {@link PostLikeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class PostLikeQueryService extends QueryService<PostLike> {

  private final Logger log = LoggerFactory.getLogger(PostLikeQueryService.class);

  private final PostLikeRepository postLikeRepository;

  private final PostLikeMapper postLikeMapper;

  private final PostLikeSearchRepository postLikeSearchRepository;

  public PostLikeQueryService(
    PostLikeRepository postLikeRepository,
    PostLikeMapper postLikeMapper,
    PostLikeSearchRepository postLikeSearchRepository
  ) {
    this.postLikeRepository = postLikeRepository;
    this.postLikeMapper = postLikeMapper;
    this.postLikeSearchRepository = postLikeSearchRepository;
  }

  /**
   * Return a {@link List} of {@link PostLikeDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<PostLikeDTO> findByCriteria(PostLikeCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<PostLike> specification = createSpecification(criteria);
    return postLikeMapper.toDto(postLikeRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link PostLikeDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<PostLikeDTO> findByCriteria(PostLikeCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<PostLike> specification = createSpecification(criteria);
    return postLikeRepository.findAll(specification, page).map(postLikeMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(PostLikeCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<PostLike> specification = createSpecification(criteria);
    return postLikeRepository.count(specification);
  }

  /**
   * Function to convert {@link PostLikeCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<PostLike> createSpecification(PostLikeCriteria criteria) {
    Specification<PostLike> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), PostLike_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), PostLike_.uuid));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(PostLike_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getPostId() != null) {
        specification =
          specification.and(buildSpecification(criteria.getPostId(), root -> root.join(PostLike_.post, JoinType.LEFT).get(Post_.id)));
      }
      if (criteria.getPostCommentId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPostCommentId(), root -> root.join(PostLike_.postComment, JoinType.LEFT).get(PostComment_.id))
          );
      }
    }
    return specification;
  }
}
