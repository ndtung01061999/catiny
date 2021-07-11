package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.TopicInterest;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TopicInterest entity.
 */
@Repository
@GeneratedByJHipster
public interface TopicInterestRepository extends JpaRepository<TopicInterest, Long>, JpaSpecificationExecutor<TopicInterest> {
  @Query(
    value = "select distinct topicInterest from TopicInterest topicInterest left join fetch topicInterest.posts left join fetch topicInterest.pagePosts left join fetch topicInterest.groupPosts",
    countQuery = "select count(distinct topicInterest) from TopicInterest topicInterest"
  )
  Page<TopicInterest> findAllWithEagerRelationships(Pageable pageable);

  @Query(
    "select distinct topicInterest from TopicInterest topicInterest left join fetch topicInterest.posts left join fetch topicInterest.pagePosts left join fetch topicInterest.groupPosts"
  )
  List<TopicInterest> findAllWithEagerRelationships();

  @Query(
    "select topicInterest from TopicInterest topicInterest left join fetch topicInterest.posts left join fetch topicInterest.pagePosts left join fetch topicInterest.groupPosts where topicInterest.id =:id"
  )
  Optional<TopicInterest> findOneWithEagerRelationships(@Param("id") Long id);
}
