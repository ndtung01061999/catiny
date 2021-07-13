package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.NewsFeed;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the NewsFeed entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface NewsFeedRepository extends JpaRepository<NewsFeed, Long>, JpaSpecificationExecutor<NewsFeed> {}
