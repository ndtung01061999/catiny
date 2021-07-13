package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.RankGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RankGroup entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface RankGroupRepository extends JpaRepository<RankGroup, Long>, JpaSpecificationExecutor<RankGroup> {}
