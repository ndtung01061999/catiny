package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.FollowPage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the FollowPage entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface FollowPageRepository extends JpaRepository<FollowPage, Long>, JpaSpecificationExecutor<FollowPage> {}
