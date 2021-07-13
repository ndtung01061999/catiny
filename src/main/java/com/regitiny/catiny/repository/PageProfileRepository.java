package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.PageProfile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PageProfile entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface PageProfileRepository extends JpaRepository<PageProfile, Long>, JpaSpecificationExecutor<PageProfile> {}
