package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.HanhChinhVN;
import com.regitiny.catiny.repository.HanhChinhVNRepository;
import com.regitiny.catiny.repository.search.HanhChinhVNSearchRepository;
import com.regitiny.catiny.service.criteria.HanhChinhVNCriteria;
import com.regitiny.catiny.service.dto.HanhChinhVNDTO;
import com.regitiny.catiny.service.mapper.HanhChinhVNMapper;
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
 * Service for executing complex queries for {@link HanhChinhVN} entities in the database.
 * The main input is a {@link HanhChinhVNCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link HanhChinhVNDTO} or a {@link Page} of {@link HanhChinhVNDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class HanhChinhVNQueryService extends QueryService<HanhChinhVN> {

  private final Logger log = LoggerFactory.getLogger(HanhChinhVNQueryService.class);

  private final HanhChinhVNRepository hanhChinhVNRepository;

  private final HanhChinhVNMapper hanhChinhVNMapper;

  private final HanhChinhVNSearchRepository hanhChinhVNSearchRepository;

  public HanhChinhVNQueryService(
    HanhChinhVNRepository hanhChinhVNRepository,
    HanhChinhVNMapper hanhChinhVNMapper,
    HanhChinhVNSearchRepository hanhChinhVNSearchRepository
  ) {
    this.hanhChinhVNRepository = hanhChinhVNRepository;
    this.hanhChinhVNMapper = hanhChinhVNMapper;
    this.hanhChinhVNSearchRepository = hanhChinhVNSearchRepository;
  }

  /**
   * Return a {@link List} of {@link HanhChinhVNDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<HanhChinhVNDTO> findByCriteria(HanhChinhVNCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<HanhChinhVN> specification = createSpecification(criteria);
    return hanhChinhVNMapper.toDto(hanhChinhVNRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link HanhChinhVNDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<HanhChinhVNDTO> findByCriteria(HanhChinhVNCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<HanhChinhVN> specification = createSpecification(criteria);
    return hanhChinhVNRepository.findAll(specification, page).map(hanhChinhVNMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(HanhChinhVNCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<HanhChinhVN> specification = createSpecification(criteria);
    return hanhChinhVNRepository.count(specification);
  }

  /**
   * Function to convert {@link HanhChinhVNCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<HanhChinhVN> createSpecification(HanhChinhVNCriteria criteria) {
    Specification<HanhChinhVN> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), HanhChinhVN_.id));
      }
      if (criteria.getName() != null) {
        specification = specification.and(buildStringSpecification(criteria.getName(), HanhChinhVN_.name));
      }
      if (criteria.getSlug() != null) {
        specification = specification.and(buildStringSpecification(criteria.getSlug(), HanhChinhVN_.slug));
      }
      if (criteria.getType() != null) {
        specification = specification.and(buildStringSpecification(criteria.getType(), HanhChinhVN_.type));
      }
      if (criteria.getNameWithType() != null) {
        specification = specification.and(buildStringSpecification(criteria.getNameWithType(), HanhChinhVN_.nameWithType));
      }
      if (criteria.getCode() != null) {
        specification = specification.and(buildStringSpecification(criteria.getCode(), HanhChinhVN_.code));
      }
      if (criteria.getParentCode() != null) {
        specification = specification.and(buildStringSpecification(criteria.getParentCode(), HanhChinhVN_.parentCode));
      }
      if (criteria.getPath() != null) {
        specification = specification.and(buildStringSpecification(criteria.getPath(), HanhChinhVN_.path));
      }
      if (criteria.getPathWithType() != null) {
        specification = specification.and(buildStringSpecification(criteria.getPathWithType(), HanhChinhVN_.pathWithType));
      }
    }
    return specification;
  }
}
