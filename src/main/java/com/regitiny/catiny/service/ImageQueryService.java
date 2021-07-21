package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.Image;
import com.regitiny.catiny.repository.ImageRepository;
import com.regitiny.catiny.repository.search.ImageSearchRepository;
import com.regitiny.catiny.service.criteria.ImageCriteria;
import com.regitiny.catiny.service.dto.ImageDTO;
import com.regitiny.catiny.service.mapper.ImageMapper;
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
 * Service for executing complex queries for {@link Image} entities in the database.
 * The main input is a {@link ImageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ImageDTO} or a {@link Page} of {@link ImageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class ImageQueryService extends QueryService<Image> {

  private final Logger log = LoggerFactory.getLogger(ImageQueryService.class);

  private final ImageRepository imageRepository;

  private final ImageMapper imageMapper;

  private final ImageSearchRepository imageSearchRepository;

  public ImageQueryService(ImageRepository imageRepository, ImageMapper imageMapper, ImageSearchRepository imageSearchRepository) {
    this.imageRepository = imageRepository;
    this.imageMapper = imageMapper;
    this.imageSearchRepository = imageSearchRepository;
  }

  /**
   * Return a {@link List} of {@link ImageDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<ImageDTO> findByCriteria(ImageCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<Image> specification = createSpecification(criteria);
    return imageMapper.toDto(imageRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link ImageDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<ImageDTO> findByCriteria(ImageCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<Image> specification = createSpecification(criteria);
    return imageRepository.findAll(specification, page).map(imageMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(ImageCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<Image> specification = createSpecification(criteria);
    return imageRepository.count(specification);
  }

  /**
   * Function to convert {@link ImageCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<Image> createSpecification(ImageCriteria criteria) {
    Specification<Image> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), Image_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), Image_.uuid));
      }
      if (criteria.getName() != null) {
        specification = specification.and(buildStringSpecification(criteria.getName(), Image_.name));
      }
      if (criteria.getWidth() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getWidth(), Image_.width));
      }
      if (criteria.getHeight() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getHeight(), Image_.height));
      }
      if (criteria.getQuality() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getQuality(), Image_.quality));
      }
      if (criteria.getPixelSize() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getPixelSize(), Image_.pixelSize));
      }
      if (criteria.getPriorityIndex() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getPriorityIndex(), Image_.priorityIndex));
      }
      if (criteria.getDataSize() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getDataSize(), Image_.dataSize));
      }
      if (criteria.getFileInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getFileInfoId(), root -> root.join(Image_.fileInfo, JoinType.LEFT).get(FileInfo_.id))
          );
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(Image_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
      if (criteria.getImageProcessedId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getImageProcessedId(), root -> root.join(Image_.imageProcesseds, JoinType.LEFT).get(Image_.id))
          );
      }
      if (criteria.getImageOriginalId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getImageOriginalId(), root -> root.join(Image_.imageOriginal, JoinType.LEFT).get(Image_.id))
          );
      }
      if (criteria.getAlbumId() != null) {
        specification =
          specification.and(buildSpecification(criteria.getAlbumId(), root -> root.join(Image_.albums, JoinType.LEFT).get(Album_.id)));
      }
    }
    return specification;
  }
}
