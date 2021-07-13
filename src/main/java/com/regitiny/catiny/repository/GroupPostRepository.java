package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.GroupPost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GroupPost entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface GroupPostRepository extends JpaRepository<GroupPost, Long>, JpaSpecificationExecutor<GroupPost> {}
