package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.VideoStream;
import com.regitiny.catiny.repository.VideoStreamRepository;
import com.regitiny.catiny.repository.search.VideoStreamSearchRepository;
import com.regitiny.catiny.service.criteria.VideoStreamCriteria;
import com.regitiny.catiny.service.dto.VideoStreamDTO;
import com.regitiny.catiny.service.mapper.VideoStreamMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link VideoStream} entities in the database.
 * The main input is a {@link VideoStreamCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VideoStreamDTO} or a {@link Page} of {@link VideoStreamDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class VideoStreamQueryService extends QueryService<VideoStream> {

  private final Logger log = LoggerFactory.getLogger(VideoStreamQueryService.class);

  private final VideoStreamRepository videoStreamRepository;

  private final VideoStreamMapper videoStreamMapper;

  private final VideoStreamSearchRepository videoStreamSearchRepository;

  public VideoStreamQueryService(
    VideoStreamRepository videoStreamRepository,
    VideoStreamMapper videoStreamMapper,
    VideoStreamSearchRepository videoStreamSearchRepository
  ) {
    this.videoStreamRepository = videoStreamRepository;
    this.videoStreamMapper = videoStreamMapper;
    this.videoStreamSearchRepository = videoStreamSearchRepository;
  }

  /**
   * Return a {@link List} of {@link VideoStreamDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<VideoStreamDTO> findByCriteria(VideoStreamCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<VideoStream> specification = createSpecification(criteria);
    return videoStreamMapper.toDto(videoStreamRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link VideoStreamDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<VideoStreamDTO> findByCriteria(VideoStreamCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<VideoStream> specification = createSpecification(criteria);
    return videoStreamRepository.findAll(specification, page).map(videoStreamMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(VideoStreamCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<VideoStream> specification = createSpecification(criteria);
    return videoStreamRepository.count(specification);
  }

  /**
   * Function to convert {@link VideoStreamCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<VideoStream> createSpecification(VideoStreamCriteria criteria) {
    Specification<VideoStream> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), VideoStream_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), VideoStream_.uuid));
      }
      if (criteria.getVideoId() != null) {
        specification =
          specification.and(buildSpecification(criteria.getVideoId(), root -> root.join(VideoStream_.video, JoinType.LEFT).get(Video_.id)));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(VideoStream_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getVideoLiveStreamBufferId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getVideoLiveStreamBufferId(),
              root -> root.join(VideoStream_.videoLiveStreamBuffers, JoinType.LEFT).get(VideoLiveStreamBuffer_.id)
            )
          );
      }
    }
    return specification;
  }
}
