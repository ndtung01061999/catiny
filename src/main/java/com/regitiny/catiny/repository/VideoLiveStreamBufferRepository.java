package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.VideoLiveStreamBuffer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the VideoLiveStreamBuffer entity.
 */
@SuppressWarnings("unused")
@Repository
@GeneratedByJHipster
public interface VideoLiveStreamBufferRepository
  extends JpaRepository<VideoLiveStreamBuffer, Long>, JpaSpecificationExecutor<VideoLiveStreamBuffer> {}
