package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.HistoryUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the HistoryUpdate entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface HistoryUpdateRepository extends JpaRepository<HistoryUpdate, Long>, JpaSpecificationExecutor<HistoryUpdate>
{
}
