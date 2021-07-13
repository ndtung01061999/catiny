package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.VideoLiveStreamBufferAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.VideoLiveStreamBufferAdvanceSearch;
import com.regitiny.catiny.advance.service.VideoLiveStreamBufferAdvanceService;
import com.regitiny.catiny.advance.service.mapper.VideoLiveStreamBufferAdvanceMapper;
import com.regitiny.catiny.service.VideoLiveStreamBufferQueryService;
import com.regitiny.catiny.service.VideoLiveStreamBufferService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class VideoLiveStreamBufferAdvanceServiceImpl extends LocalServiceImpl<VideoLiveStreamBufferService, VideoLiveStreamBufferQueryService> implements VideoLiveStreamBufferAdvanceService
{
  private final VideoLiveStreamBufferAdvanceRepository videoLiveStreamBufferAdvanceRepository;

  private final VideoLiveStreamBufferAdvanceSearch videoLiveStreamBufferAdvanceSearch;

  private final VideoLiveStreamBufferAdvanceMapper videoLiveStreamBufferAdvanceMapper;

  public VideoLiveStreamBufferAdvanceServiceImpl(
    VideoLiveStreamBufferAdvanceRepository videoLiveStreamBufferAdvanceRepository,
    VideoLiveStreamBufferAdvanceSearch videoLiveStreamBufferAdvanceSearch,
    VideoLiveStreamBufferAdvanceMapper videoLiveStreamBufferAdvanceMapper)
  {
    this.videoLiveStreamBufferAdvanceRepository = videoLiveStreamBufferAdvanceRepository;
    this.videoLiveStreamBufferAdvanceSearch = videoLiveStreamBufferAdvanceSearch;
    this.videoLiveStreamBufferAdvanceMapper = videoLiveStreamBufferAdvanceMapper;
  }
}
