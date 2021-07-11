package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.DeviceStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DeviceStatus entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface DeviceStatusRepository extends JpaRepository<DeviceStatus, Long>, JpaSpecificationExecutor<DeviceStatus> {}
