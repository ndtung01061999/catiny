package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.Video;
import com.regitiny.catiny.repository.VideoRepository;
import com.regitiny.catiny.repository.search.VideoSearchRepository;
import com.regitiny.catiny.service.criteria.VideoCriteria;
import com.regitiny.catiny.service.dto.VideoDTO;
import com.regitiny.catiny.service.mapper.VideoMapper;
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
 * Service for executing complex queries for {@link Video} entities in the database.
 * The main input is a {@link VideoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VideoDTO} or a {@link Page} of {@link VideoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class VideoQueryService extends QueryService<Video> {

  private final Logger log = LoggerFactory.getLogger(VideoQueryService.class);

  private final VideoRepository videoRepository;

  private final VideoMapper videoMapper;

  private final VideoSearchRepository videoSearchRepository;

  public VideoQueryService(VideoRepository videoRepository, VideoMapper videoMapper, VideoSearchRepository videoSearchRepository) {
    this.videoRepository = videoRepository;
    this.videoMapper = videoMapper;
    this.videoSearchRepository = videoSearchRepository;
  }

  /**
   * Return a {@link List} of {@link VideoDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<VideoDTO> findByCriteria(VideoCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<Video> specification = createSpecification(criteria);
    return videoMapper.toDto(videoRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link VideoDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<VideoDTO> findByCriteria(VideoCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<Video> specification = createSpecification(criteria);
    return videoRepository.findAll(specification, page).map(videoMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(VideoCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<Video> specification = createSpecification(criteria);
    return videoRepository.count(specification);
  }

  /**
   * Function to convert {@link VideoCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<Video> createSpecification(VideoCriteria criteria) {
    Specification<Video> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), Video_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), Video_.uuid));
      }
      if (criteria.getName() != null) {
        specification = specification.and(buildStringSpecification(criteria.getName(), Video_.name));
      }
      if (criteria.getWidth() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getWidth(), Video_.width));
      }
      if (criteria.getHeight() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getHeight(), Video_.height));
      }
      if (criteria.getQualityImage() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getQualityImage(), Video_.qualityImage));
      }
      if (criteria.getQualityAudio() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getQualityAudio(), Video_.qualityAudio));
      }
      if (criteria.getQuality() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getQuality(), Video_.quality));
      }
      if (criteria.getPixelSize() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getPixelSize(), Video_.pixelSize));
      }
      if (criteria.getPriorityIndex() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getPriorityIndex(), Video_.priorityIndex));
      }
      if (criteria.getDataSize() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getDataSize(), Video_.dataSize));
      }
      if (criteria.getFileInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getFileInfoId(), root -> root.join(Video_.fileInfo, JoinType.LEFT).get(FileInfo_.id))
          );
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(Video_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getVideoProcessedId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getVideoProcessedId(), root -> root.join(Video_.videoProcesseds, JoinType.LEFT).get(Video_.id))
          );
      }
      if (criteria.getVideoStreamId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getVideoStreamId(), root -> root.join(Video_.videoStream, JoinType.LEFT).get(VideoStream_.id))
          );
      }
      if (criteria.getVideoOriginalId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getVideoOriginalId(), root -> root.join(Video_.videoOriginal, JoinType.LEFT).get(Video_.id))
          );
      }
    }
    return specification;
  }
}
