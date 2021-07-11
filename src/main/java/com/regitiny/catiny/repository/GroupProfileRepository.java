package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.GroupProfile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GroupProfile entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface GroupProfileRepository extends JpaRepository<GroupProfile, Long>, JpaSpecificationExecutor<GroupProfile> {}
