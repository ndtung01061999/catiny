package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.FileInfo;
import com.regitiny.catiny.repository.FileInfoRepository;
import com.regitiny.catiny.repository.search.FileInfoSearchRepository;
import com.regitiny.catiny.service.criteria.FileInfoCriteria;
import com.regitiny.catiny.service.dto.FileInfoDTO;
import com.regitiny.catiny.service.mapper.FileInfoMapper;
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
 * Service for executing complex queries for {@link FileInfo} entities in the database.
 * The main input is a {@link FileInfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FileInfoDTO} or a {@link Page} of {@link FileInfoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class FileInfoQueryService extends QueryService<FileInfo> {

  private final Logger log = LoggerFactory.getLogger(FileInfoQueryService.class);

  private final FileInfoRepository fileInfoRepository;

  private final FileInfoMapper fileInfoMapper;

  private final FileInfoSearchRepository fileInfoSearchRepository;

  public FileInfoQueryService(
    FileInfoRepository fileInfoRepository,
    FileInfoMapper fileInfoMapper,
    FileInfoSearchRepository fileInfoSearchRepository
  ) {
    this.fileInfoRepository = fileInfoRepository;
    this.fileInfoMapper = fileInfoMapper;
    this.fileInfoSearchRepository = fileInfoSearchRepository;
  }

  /**
   * Return a {@link List} of {@link FileInfoDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<FileInfoDTO> findByCriteria(FileInfoCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<FileInfo> specification = createSpecification(criteria);
    return fileInfoMapper.toDto(fileInfoRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link FileInfoDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<FileInfoDTO> findByCriteria(FileInfoCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<FileInfo> specification = createSpecification(criteria);
    return fileInfoRepository.findAll(specification, page).map(fileInfoMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(FileInfoCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<FileInfo> specification = createSpecification(criteria);
    return fileInfoRepository.count(specification);
  }

  /**
   * Function to convert {@link FileInfoCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<FileInfo> createSpecification(FileInfoCriteria criteria) {
    Specification<FileInfo> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), FileInfo_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), FileInfo_.uuid));
      }
      if (criteria.getNameFile() != null) {
        specification = specification.and(buildStringSpecification(criteria.getNameFile(), FileInfo_.nameFile));
      }
      if (criteria.getTypeFile() != null) {
        specification = specification.and(buildStringSpecification(criteria.getTypeFile(), FileInfo_.typeFile));
      }
      if (criteria.getPath() != null) {
        specification = specification.and(buildStringSpecification(criteria.getPath(), FileInfo_.path));
      }
      if (criteria.getDataSize() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getDataSize(), FileInfo_.dataSize));
      }
      if (criteria.getBaseInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(FileInfo_.baseInfo, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
    }
    return specification;
  }
}
