package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Video;
import com.regitiny.catiny.repository.VideoRepository;
import com.regitiny.catiny.repository.search.VideoSearchRepository;
import com.regitiny.catiny.service.VideoService;
import com.regitiny.catiny.service.dto.VideoDTO;
import com.regitiny.catiny.service.mapper.VideoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Video}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class VideoServiceImpl implements VideoService {

  private final Logger log = LoggerFactory.getLogger(VideoServiceImpl.class);

  private final VideoRepository videoRepository;

  private final VideoMapper videoMapper;

  private final VideoSearchRepository videoSearchRepository;

  public VideoServiceImpl(VideoRepository videoRepository, VideoMapper videoMapper, VideoSearchRepository videoSearchRepository) {
    this.videoRepository = videoRepository;
    this.videoMapper = videoMapper;
    this.videoSearchRepository = videoSearchRepository;
  }

  @Override
  public VideoDTO save(VideoDTO videoDTO) {
    log.debug("Request to save Video : {}", videoDTO);
    Video video = videoMapper.toEntity(videoDTO);
    video = videoRepository.save(video);
    VideoDTO result = videoMapper.toDto(video);
    videoSearchRepository.save(video);
    return result;
  }

  @Override
  public Optional<VideoDTO> partialUpdate(VideoDTO videoDTO) {
    log.debug("Request to partially update Video : {}", videoDTO);

    return videoRepository
      .findById(videoDTO.getId())
      .map(
        existingVideo -> {
          videoMapper.partialUpdate(existingVideo, videoDTO);

          return existingVideo;
        }
      )
      .map(videoRepository::save)
      .map(
        savedVideo -> {
          videoSearchRepository.save(savedVideo);

          return savedVideo;
        }
      )
      .map(videoMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<VideoDTO> findAll(Pageable pageable) {
    log.debug("Request to get all Videos");
    return videoRepository.findAll(pageable).map(videoMapper::toDto);
  }

  /**
   *  Get all the videos where VideoStream is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<VideoDTO> findAllWhereVideoStreamIsNull() {
    log.debug("Request to get all videos where VideoStream is null");
    return StreamSupport
      .stream(videoRepository.findAll().spliterator(), false)
      .filter(video -> video.getVideoStream() == null)
      .map(videoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<VideoDTO> findOne(Long id) {
    log.debug("Request to get Video : {}", id);
    return videoRepository.findById(id).map(videoMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete Video : {}", id);
    videoRepository.deleteById(id);
    videoSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<VideoDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of Videos for query {}", query);
    return videoSearchRepository.search(queryStringQuery(query), pageable).map(videoMapper::toDto);
  }
}
