package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.AccountStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AccountStatus entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface AccountStatusRepository extends JpaRepository<AccountStatus, Long>, JpaSpecificationExecutor<AccountStatus> {}
