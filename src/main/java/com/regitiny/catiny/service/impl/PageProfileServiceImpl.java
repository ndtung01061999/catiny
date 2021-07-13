package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.PageProfile;
import com.regitiny.catiny.repository.PageProfileRepository;
import com.regitiny.catiny.repository.search.PageProfileSearchRepository;
import com.regitiny.catiny.service.PageProfileService;
import com.regitiny.catiny.service.dto.PageProfileDTO;
import com.regitiny.catiny.service.mapper.PageProfileMapper;
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
 * Service Implementation for managing {@link PageProfile}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class PageProfileServiceImpl implements PageProfileService {

  private final Logger log = LoggerFactory.getLogger(PageProfileServiceImpl.class);

  private final PageProfileRepository pageProfileRepository;

  private final PageProfileMapper pageProfileMapper;

  private final PageProfileSearchRepository pageProfileSearchRepository;

  public PageProfileServiceImpl(
    PageProfileRepository pageProfileRepository,
    PageProfileMapper pageProfileMapper,
    PageProfileSearchRepository pageProfileSearchRepository
  ) {
    this.pageProfileRepository = pageProfileRepository;
    this.pageProfileMapper = pageProfileMapper;
    this.pageProfileSearchRepository = pageProfileSearchRepository;
  }

  @Override
  public PageProfileDTO save(PageProfileDTO pageProfileDTO) {
    log.debug("Request to save PageProfile : {}", pageProfileDTO);
    PageProfile pageProfile = pageProfileMapper.toEntity(pageProfileDTO);
    pageProfile = pageProfileRepository.save(pageProfile);
    PageProfileDTO result = pageProfileMapper.toDto(pageProfile);
    pageProfileSearchRepository.save(pageProfile);
    return result;
  }

  @Override
  public Optional<PageProfileDTO> partialUpdate(PageProfileDTO pageProfileDTO) {
    log.debug("Request to partially update PageProfile : {}", pageProfileDTO);

    return pageProfileRepository
      .findById(pageProfileDTO.getId())
      .map(
        existingPageProfile -> {
          pageProfileMapper.partialUpdate(existingPageProfile, pageProfileDTO);

          return existingPageProfile;
        }
      )
      .map(pageProfileRepository::save)
      .map(
        savedPageProfile -> {
          pageProfileSearchRepository.save(savedPageProfile);

          return savedPageProfile;
        }
      )
      .map(pageProfileMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<PageProfileDTO> findAll(Pageable pageable) {
    log.debug("Request to get all PageProfiles");
    return pageProfileRepository.findAll(pageable).map(pageProfileMapper::toDto);
  }

  /**
   *  Get all the pageProfiles where Page is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<PageProfileDTO> findAllWherePageIsNull() {
    log.debug("Request to get all pageProfiles where Page is null");
    return StreamSupport
      .stream(pageProfileRepository.findAll().spliterator(), false)
      .filter(pageProfile -> pageProfile.getPage() == null)
      .map(pageProfileMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<PageProfileDTO> findOne(Long id) {
    log.debug("Request to get PageProfile : {}", id);
    return pageProfileRepository.findById(id).map(pageProfileMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete PageProfile : {}", id);
    pageProfileRepository.deleteById(id);
    pageProfileSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<PageProfileDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of PageProfiles for query {}", query);
    return pageProfileSearchRepository.search(queryStringQuery(query), pageable).map(pageProfileMapper::toDto);
  }
}
