package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.repository.PostRepository;
import com.regitiny.catiny.repository.search.PostSearchRepository;
import com.regitiny.catiny.service.criteria.PostCriteria;
import com.regitiny.catiny.service.dto.PostDTO;
import com.regitiny.catiny.service.mapper.PostMapper;
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
 * Service for executing complex queries for {@link Post} entities in the database.
 * The main input is a {@link PostCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PostDTO} or a {@link Page} of {@link PostDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class PostQueryService extends QueryService<Post> {

  private final Logger log = LoggerFactory.getLogger(PostQueryService.class);

  private final PostRepository postRepository;

  private final PostMapper postMapper;

  private final PostSearchRepository postSearchRepository;

  public PostQueryService(PostRepository postRepository, PostMapper postMapper, PostSearchRepository postSearchRepository) {
    this.postRepository = postRepository;
    this.postMapper = postMapper;
    this.postSearchRepository = postSearchRepository;
  }

  /**
   * Return a {@link List} of {@link PostDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<PostDTO> findByCriteria(PostCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<Post> specification = createSpecification(criteria);
    return postMapper.toDto(postRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link PostDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<PostDTO> findByCriteria(PostCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<Post> specification = createSpecification(criteria);
    return postRepository.findAll(specification, page).map(postMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(PostCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<Post> specification = createSpecification(criteria);
    return postRepository.count(specification);
  }

  /**
   * Function to convert {@link PostCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<Post> createSpecification(PostCriteria criteria) {
    Specification<Post> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), Post_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), Post_.uuid));
      }
      if (criteria.getPostInType() != null) {
        specification = specification.and(buildSpecification(criteria.getPostInType(), Post_.postInType));
      }
      if (criteria.getPostType() != null) {
        specification = specification.and(buildSpecification(criteria.getPostType(), Post_.postType));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(Post_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getPostCommentId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPostCommentId(), root -> root.join(Post_.postComments, JoinType.LEFT).get(PostComment_.id))
          );
      }
      if (criteria.getPostLikeId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPostLikeId(), root -> root.join(Post_.postLikes, JoinType.LEFT).get(PostLike_.id))
          );
      }
      if (criteria.getPostShareChildrenId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPostShareChildrenId(), root -> root.join(Post_.postShareChildren, JoinType.LEFT).get(Post_.id))
          );
      }
      if (criteria.getGroupPostId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getGroupPostId(), root -> root.join(Post_.groupPost, JoinType.LEFT).get(GroupPost_.id))
          );
      }
      if (criteria.getPagePostId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPagePostId(), root -> root.join(Post_.pagePost, JoinType.LEFT).get(PagePost_.id))
          );
      }
      if (criteria.getPostShareParentId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPostShareParentId(), root -> root.join(Post_.postShareParent, JoinType.LEFT).get(Post_.id))
          );
      }
      if (criteria.getPosterId() != null) {
        specification =
          specification.and(buildSpecification(criteria.getPosterId(), root -> root.join(Post_.poster, JoinType.LEFT).get(MasterUser_.id)));
      }
      if (criteria.getNewsFeedId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getNewsFeedId(), root -> root.join(Post_.newsFeeds, JoinType.LEFT).get(NewsFeed_.id))
          );
      }
      if (criteria.getTopicInterestId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getTopicInterestId(), root -> root.join(Post_.topicInterests, JoinType.LEFT).get(TopicInterest_.id))
          );
      }
    }
    return specification;
  }
}
