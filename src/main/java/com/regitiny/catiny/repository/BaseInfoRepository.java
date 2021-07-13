package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.BaseInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BaseInfo entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface BaseInfoRepository extends JpaRepository<BaseInfo, Long>, JpaSpecificationExecutor<BaseInfo> {}
