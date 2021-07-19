package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.BaseInfo_;
import com.regitiny.catiny.domain.ClassInfo;
import com.regitiny.catiny.domain.ClassInfo_;
import com.regitiny.catiny.repository.ClassInfoRepository;
import com.regitiny.catiny.repository.search.ClassInfoSearchRepository;
import com.regitiny.catiny.service.criteria.ClassInfoCriteria;
import com.regitiny.catiny.service.dto.ClassInfoDTO;
import com.regitiny.catiny.service.mapper.ClassInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import javax.persistence.criteria.JoinType;
import java.util.List;

/**
 * Service for executing complex queries for {@link ClassInfo} entities in the database.
 * The main input is a {@link ClassInfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ClassInfoDTO} or a {@link Page} of {@link ClassInfoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class ClassInfoQueryService extends QueryService<ClassInfo> {

  private final Logger log = LoggerFactory.getLogger(ClassInfoQueryService.class);

  private final ClassInfoRepository classInfoRepository;

  private final ClassInfoMapper classInfoMapper;

  private final ClassInfoSearchRepository classInfoSearchRepository;

  public ClassInfoQueryService(
    ClassInfoRepository classInfoRepository,
    ClassInfoMapper classInfoMapper,
    ClassInfoSearchRepository classInfoSearchRepository
  ) {
    this.classInfoRepository = classInfoRepository;
    this.classInfoMapper = classInfoMapper;
    this.classInfoSearchRepository = classInfoSearchRepository;
  }

  /**
   * Return a {@link List} of {@link ClassInfoDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<ClassInfoDTO> findByCriteria(ClassInfoCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<ClassInfo> specification = createSpecification(criteria);
    return classInfoMapper.toDto(classInfoRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link ClassInfoDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<ClassInfoDTO> findByCriteria(ClassInfoCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<ClassInfo> specification = createSpecification(criteria);
    return classInfoRepository.findAll(specification, page).map(classInfoMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(ClassInfoCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<ClassInfo> specification = createSpecification(criteria);
    return classInfoRepository.count(specification);
  }

  /**
   * Function to convert {@link ClassInfoCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<ClassInfo> createSpecification(ClassInfoCriteria criteria) {
    Specification<ClassInfo> specification = Specification.where(null);
    if (criteria != null)
    {
      if (criteria.getId() != null)
      {
        specification = specification.and(buildRangeSpecification(criteria.getId(), ClassInfo_.id));
      }
      if (criteria.getUuid() != null)
      {
        specification = specification.and(buildSpecification(criteria.getUuid(), ClassInfo_.uuid));
      }
      if (criteria.getNamePackage() != null)
      {
        specification = specification.and(buildStringSpecification(criteria.getNamePackage(), ClassInfo_.namePackage));
      }
      if (criteria.getFullName() != null)
      {
        specification = specification.and(buildStringSpecification(criteria.getFullName(), ClassInfo_.fullName));
      }
      if (criteria.getClassName() != null)
      {
        specification = specification.and(buildStringSpecification(criteria.getClassName(), ClassInfo_.className));
      }
      if (criteria.getBaseInfoId() != null)
      {
        specification =
          specification.and(
            buildSpecification(criteria.getBaseInfoId(), root -> root.join(ClassInfo_.baseInfos, JoinType.LEFT).get(BaseInfo_.id))
          );
      }
    }
    return specification;
  }
}
