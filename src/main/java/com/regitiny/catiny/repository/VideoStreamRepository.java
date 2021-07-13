package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.VideoStream;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the VideoStream entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface VideoStreamRepository extends JpaRepository<VideoStream, Long>, JpaSpecificationExecutor<VideoStream> {}
