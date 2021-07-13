package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.VideoLiveStreamBuffer;
import com.regitiny.catiny.repository.VideoLiveStreamBufferRepository;
import com.regitiny.catiny.repository.search.VideoLiveStreamBufferSearchRepository;
import com.regitiny.catiny.service.VideoLiveStreamBufferService;
import com.regitiny.catiny.service.dto.VideoLiveStreamBufferDTO;
import com.regitiny.catiny.service.mapper.VideoLiveStreamBufferMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link VideoLiveStreamBuffer}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class VideoLiveStreamBufferServiceImpl implements VideoLiveStreamBufferService {

  private final Logger log = LoggerFactory.getLogger(VideoLiveStreamBufferServiceImpl.class);

  private final VideoLiveStreamBufferRepository videoLiveStreamBufferRepository;

  private final VideoLiveStreamBufferMapper videoLiveStreamBufferMapper;

  private final VideoLiveStreamBufferSearchRepository videoLiveStreamBufferSearchRepository;

  public VideoLiveStreamBufferServiceImpl(
    VideoLiveStreamBufferRepository videoLiveStreamBufferRepository,
    VideoLiveStreamBufferMapper videoLiveStreamBufferMapper,
    VideoLiveStreamBufferSearchRepository videoLiveStreamBufferSearchRepository
  ) {
    this.videoLiveStreamBufferRepository = videoLiveStreamBufferRepository;
    this.videoLiveStreamBufferMapper = videoLiveStreamBufferMapper;
    this.videoLiveStreamBufferSearchRepository = videoLiveStreamBufferSearchRepository;
  }

  @Override
  public VideoLiveStreamBufferDTO save(VideoLiveStreamBufferDTO videoLiveStreamBufferDTO) {
    log.debug("Request to save VideoLiveStreamBuffer : {}", videoLiveStreamBufferDTO);
    VideoLiveStreamBuffer videoLiveStreamBuffer = videoLiveStreamBufferMapper.toEntity(videoLiveStreamBufferDTO);
    videoLiveStreamBuffer = videoLiveStreamBufferRepository.save(videoLiveStreamBuffer);
    VideoLiveStreamBufferDTO result = videoLiveStreamBufferMapper.toDto(videoLiveStreamBuffer);
    videoLiveStreamBufferSearchRepository.save(videoLiveStreamBuffer);
    return result;
  }

  @Override
  public Optional<VideoLiveStreamBufferDTO> partialUpdate(VideoLiveStreamBufferDTO videoLiveStreamBufferDTO) {
    log.debug("Request to partially update VideoLiveStreamBuffer : {}", videoLiveStreamBufferDTO);

    return videoLiveStreamBufferRepository
      .findById(videoLiveStreamBufferDTO.getId())
      .map(
        existingVideoLiveStreamBuffer -> {
          videoLiveStreamBufferMapper.partialUpdate(existingVideoLiveStreamBuffer, videoLiveStreamBufferDTO);

          return existingVideoLiveStreamBuffer;
        }
      )
      .map(videoLiveStreamBufferRepository::save)
      .map(
        savedVideoLiveStreamBuffer -> {
          videoLiveStreamBufferSearchRepository.save(savedVideoLiveStreamBuffer);

          return savedVideoLiveStreamBuffer;
        }
      )
      .map(videoLiveStreamBufferMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<VideoLiveStreamBufferDTO> findAll(Pageable pageable) {
    log.debug("Request to get all VideoLiveStreamBuffers");
    return videoLiveStreamBufferRepository.findAll(pageable).map(videoLiveStreamBufferMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<VideoLiveStreamBufferDTO> findOne(Long id) {
    log.debug("Request to get VideoLiveStreamBuffer : {}", id);
    return videoLiveStreamBufferRepository.findById(id).map(videoLiveStreamBufferMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete VideoLiveStreamBuffer : {}", id);
    videoLiveStreamBufferRepository.deleteById(id);
    videoLiveStreamBufferSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<VideoLiveStreamBufferDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of VideoLiveStreamBuffers for query {}", query);
    return videoLiveStreamBufferSearchRepository.search(queryStringQuery(query), pageable).map(videoLiveStreamBufferMapper::toDto);
  }
}
