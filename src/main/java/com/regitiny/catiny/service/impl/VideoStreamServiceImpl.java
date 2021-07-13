package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.VideoStream;
import com.regitiny.catiny.repository.VideoStreamRepository;
import com.regitiny.catiny.repository.search.VideoStreamSearchRepository;
import com.regitiny.catiny.service.VideoStreamService;
import com.regitiny.catiny.service.dto.VideoStreamDTO;
import com.regitiny.catiny.service.mapper.VideoStreamMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link VideoStream}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class VideoStreamServiceImpl implements VideoStreamService {

  private final Logger log = LoggerFactory.getLogger(VideoStreamServiceImpl.class);

  private final VideoStreamRepository videoStreamRepository;

  private final VideoStreamMapper videoStreamMapper;

  private final VideoStreamSearchRepository videoStreamSearchRepository;

  public VideoStreamServiceImpl(
    VideoStreamRepository videoStreamRepository,
    VideoStreamMapper videoStreamMapper,
    VideoStreamSearchRepository videoStreamSearchRepository
  ) {
    this.videoStreamRepository = videoStreamRepository;
    this.videoStreamMapper = videoStreamMapper;
    this.videoStreamSearchRepository = videoStreamSearchRepository;
  }

  @Override
  public VideoStreamDTO save(VideoStreamDTO videoStreamDTO) {
    log.debug("Request to save VideoStream : {}", videoStreamDTO);
    VideoStream videoStream = videoStreamMapper.toEntity(videoStreamDTO);
    videoStream = videoStreamRepository.save(videoStream);
    VideoStreamDTO result = videoStreamMapper.toDto(videoStream);
    videoStreamSearchRepository.save(videoStream);
    return result;
  }

  @Override
  public Optional<VideoStreamDTO> partialUpdate(VideoStreamDTO videoStreamDTO) {
    log.debug("Request to partially update VideoStream : {}", videoStreamDTO);

    return videoStreamRepository
      .findById(videoStreamDTO.getId())
      .map(
        existingVideoStream -> {
          videoStreamMapper.partialUpdate(existingVideoStream, videoStreamDTO);

          return existingVideoStream;
        }
      )
      .map(videoStreamRepository::save)
      .map(
        savedVideoStream -> {
          videoStreamSearchRepository.save(savedVideoStream);

          return savedVideoStream;
        }
      )
      .map(videoStreamMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<VideoStreamDTO> findAll(Pageable pageable) {
    log.debug("Request to get all VideoStreams");
    return videoStreamRepository.findAll(pageable).map(videoStreamMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<VideoStreamDTO> findOne(Long id) {
    log.debug("Request to get VideoStream : {}", id);
    return videoStreamRepository.findById(id).map(videoStreamMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete VideoStream : {}", id);
    videoStreamRepository.deleteById(id);
    videoStreamSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<VideoStreamDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of VideoStreams for query {}", query);
    return videoStreamSearchRepository.search(queryStringQuery(query), pageable).map(videoStreamMapper::toDto);
  }
}
