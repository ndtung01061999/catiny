package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.PagePost;
import com.regitiny.catiny.repository.PagePostRepository;
import com.regitiny.catiny.repository.search.PagePostSearchRepository;
import com.regitiny.catiny.service.PagePostService;
import com.regitiny.catiny.service.dto.PagePostDTO;
import com.regitiny.catiny.service.mapper.PagePostMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PagePost}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class PagePostServiceImpl implements PagePostService {

  private final Logger log = LoggerFactory.getLogger(PagePostServiceImpl.class);

  private final PagePostRepository pagePostRepository;

  private final PagePostMapper pagePostMapper;

  private final PagePostSearchRepository pagePostSearchRepository;

  public PagePostServiceImpl(
    PagePostRepository pagePostRepository,
    PagePostMapper pagePostMapper,
    PagePostSearchRepository pagePostSearchRepository
  ) {
    this.pagePostRepository = pagePostRepository;
    this.pagePostMapper = pagePostMapper;
    this.pagePostSearchRepository = pagePostSearchRepository;
  }

  @Override
  public PagePostDTO save(PagePostDTO pagePostDTO) {
    log.debug("Request to save PagePost : {}", pagePostDTO);
    PagePost pagePost = pagePostMapper.toEntity(pagePostDTO);
    pagePost = pagePostRepository.save(pagePost);
    PagePostDTO result = pagePostMapper.toDto(pagePost);
    pagePostSearchRepository.save(pagePost);
    return result;
  }

  @Override
  public Optional<PagePostDTO> partialUpdate(PagePostDTO pagePostDTO) {
    log.debug("Request to partially update PagePost : {}", pagePostDTO);

    return pagePostRepository
      .findById(pagePostDTO.getId())
      .map(
        existingPagePost -> {
          pagePostMapper.partialUpdate(existingPagePost, pagePostDTO);

          return existingPagePost;
        }
      )
      .map(pagePostRepository::save)
      .map(
        savedPagePost -> {
          pagePostSearchRepository.save(savedPagePost);

          return savedPagePost;
        }
      )
      .map(pagePostMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<PagePostDTO> findAll(Pageable pageable) {
    log.debug("Request to get all PagePosts");
    return pagePostRepository.findAll(pageable).map(pagePostMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<PagePostDTO> findOne(Long id) {
    log.debug("Request to get PagePost : {}", id);
    return pagePostRepository.findById(id).map(pagePostMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete PagePost : {}", id);
    pagePostRepository.deleteById(id);
    pagePostSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<PagePostDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of PagePosts for query {}", query);
    return pagePostSearchRepository.search(queryStringQuery(query), pageable).map(pagePostMapper::toDto);
  }
}
