package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.ClassInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ClassInfo entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface ClassInfoRepository extends JpaRepository<ClassInfo, Long>, JpaSpecificationExecutor<ClassInfo> {}
