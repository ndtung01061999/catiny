package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.RankUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RankUser entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface RankUserRepository extends JpaRepository<RankUser, Long>, JpaSpecificationExecutor<RankUser> {}
