package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.PagePost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PagePost entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface PagePostRepository extends JpaRepository<PagePost, Long>, JpaSpecificationExecutor<PagePost> {}
