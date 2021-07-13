package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.VideoStreamAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.VideoStreamAdvanceSearch;
import com.regitiny.catiny.advance.service.VideoStreamAdvanceService;
import com.regitiny.catiny.advance.service.mapper.VideoStreamAdvanceMapper;
import com.regitiny.catiny.service.VideoStreamQueryService;
import com.regitiny.catiny.service.VideoStreamService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class VideoStreamAdvanceServiceImpl extends LocalServiceImpl<VideoStreamService, VideoStreamQueryService> implements VideoStreamAdvanceService
{
  private final VideoStreamAdvanceRepository videoStreamAdvanceRepository;

  private final VideoStreamAdvanceSearch videoStreamAdvanceSearch;

  private final VideoStreamAdvanceMapper videoStreamAdvanceMapper;

  public VideoStreamAdvanceServiceImpl(VideoStreamAdvanceRepository videoStreamAdvanceRepository,
    VideoStreamAdvanceSearch videoStreamAdvanceSearch,
    VideoStreamAdvanceMapper videoStreamAdvanceMapper)
  {
    this.videoStreamAdvanceRepository = videoStreamAdvanceRepository;
    this.videoStreamAdvanceSearch = videoStreamAdvanceSearch;
    this.videoStreamAdvanceMapper = videoStreamAdvanceMapper;
  }
}
