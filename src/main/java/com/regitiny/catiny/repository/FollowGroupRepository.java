package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.FollowGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the FollowGroup entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface FollowGroupRepository extends JpaRepository<FollowGroup, Long>, JpaSpecificationExecutor<FollowGroup> {}
