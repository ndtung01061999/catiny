package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.VideoLiveStreamBuffer;
import com.regitiny.catiny.repository.VideoLiveStreamBufferRepository;
import com.regitiny.catiny.repository.search.VideoLiveStreamBufferSearchRepository;
import com.regitiny.catiny.service.criteria.VideoLiveStreamBufferCriteria;
import com.regitiny.catiny.service.dto.VideoLiveStreamBufferDTO;
import com.regitiny.catiny.service.mapper.VideoLiveStreamBufferMapper;
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
 * Service for executing complex queries for {@link VideoLiveStreamBuffer} entities in the database.
 * The main input is a {@link VideoLiveStreamBufferCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VideoLiveStreamBufferDTO} or a {@link Page} of {@link VideoLiveStreamBufferDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class VideoLiveStreamBufferQueryService extends QueryService<VideoLiveStreamBuffer> {

  private final Logger log = LoggerFactory.getLogger(VideoLiveStreamBufferQueryService.class);

  private final VideoLiveStreamBufferRepository videoLiveStreamBufferRepository;

  private final VideoLiveStreamBufferMapper videoLiveStreamBufferMapper;

  private final VideoLiveStreamBufferSearchRepository videoLiveStreamBufferSearchRepository;

  public VideoLiveStreamBufferQueryService(
    VideoLiveStreamBufferRepository videoLiveStreamBufferRepository,
    VideoLiveStreamBufferMapper videoLiveStreamBufferMapper,
    VideoLiveStreamBufferSearchRepository videoLiveStreamBufferSearchRepository
  ) {
    this.videoLiveStreamBufferRepository = videoLiveStreamBufferRepository;
    this.videoLiveStreamBufferMapper = videoLiveStreamBufferMapper;
    this.videoLiveStreamBufferSearchRepository = videoLiveStreamBufferSearchRepository;
  }

  /**
   * Return a {@link List} of {@link VideoLiveStreamBufferDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<VideoLiveStreamBufferDTO> findByCriteria(VideoLiveStreamBufferCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<VideoLiveStreamBuffer> specification = createSpecification(criteria);
    return videoLiveStreamBufferMapper.toDto(videoLiveStreamBufferRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link VideoLiveStreamBufferDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<VideoLiveStreamBufferDTO> findByCriteria(VideoLiveStreamBufferCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<VideoLiveStreamBuffer> specification = createSpecification(criteria);
    return videoLiveStreamBufferRepository.findAll(specification, page).map(videoLiveStreamBufferMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(VideoLiveStreamBufferCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<VideoLiveStreamBuffer> specification = createSpecification(criteria);
    return videoLiveStreamBufferRepository.count(specification);
  }

  /**
   * Function to convert {@link VideoLiveStreamBufferCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<VideoLiveStreamBuffer> createSpecification(VideoLiveStreamBufferCriteria criteria) {
    Specification<VideoLiveStreamBuffer> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), VideoLiveStreamBuffer_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), VideoLiveStreamBuffer_.uuid));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getBaseInfoId(),
              root -> root.join(VideoLiveStreamBuffer_.baseInfo, JoinType.LEFT).get(BaseInfo_.id)
            )
          );
      }
      if (criteria.getVideoStreamId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getVideoStreamId(),
              root -> root.join(VideoLiveStreamBuffer_.videoStream, JoinType.LEFT).get(VideoStream_.id)
            )
          );
      }
    }
    return specification;
  }
}
