package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.FollowUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the FollowUser entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface FollowUserRepository extends JpaRepository<FollowUser, Long>, JpaSpecificationExecutor<FollowUser> {}
