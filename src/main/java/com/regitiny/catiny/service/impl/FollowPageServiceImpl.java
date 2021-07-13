package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.FollowPage;
import com.regitiny.catiny.repository.FollowPageRepository;
import com.regitiny.catiny.repository.search.FollowPageSearchRepository;
import com.regitiny.catiny.service.FollowPageService;
import com.regitiny.catiny.service.dto.FollowPageDTO;
import com.regitiny.catiny.service.mapper.FollowPageMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FollowPage}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class FollowPageServiceImpl implements FollowPageService {

  private final Logger log = LoggerFactory.getLogger(FollowPageServiceImpl.class);

  private final FollowPageRepository followPageRepository;

  private final FollowPageMapper followPageMapper;

  private final FollowPageSearchRepository followPageSearchRepository;

  public FollowPageServiceImpl(
    FollowPageRepository followPageRepository,
    FollowPageMapper followPageMapper,
    FollowPageSearchRepository followPageSearchRepository
  ) {
    this.followPageRepository = followPageRepository;
    this.followPageMapper = followPageMapper;
    this.followPageSearchRepository = followPageSearchRepository;
  }

  @Override
  public FollowPageDTO save(FollowPageDTO followPageDTO) {
    log.debug("Request to save FollowPage : {}", followPageDTO);
    FollowPage followPage = followPageMapper.toEntity(followPageDTO);
    followPage = followPageRepository.save(followPage);
    FollowPageDTO result = followPageMapper.toDto(followPage);
    followPageSearchRepository.save(followPage);
    return result;
  }

  @Override
  public Optional<FollowPageDTO> partialUpdate(FollowPageDTO followPageDTO) {
    log.debug("Request to partially update FollowPage : {}", followPageDTO);

    return followPageRepository
      .findById(followPageDTO.getId())
      .map(
        existingFollowPage -> {
          followPageMapper.partialUpdate(existingFollowPage, followPageDTO);

          return existingFollowPage;
        }
      )
      .map(followPageRepository::save)
      .map(
        savedFollowPage -> {
          followPageSearchRepository.save(savedFollowPage);

          return savedFollowPage;
        }
      )
      .map(followPageMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<FollowPageDTO> findAll(Pageable pageable) {
    log.debug("Request to get all FollowPages");
    return followPageRepository.findAll(pageable).map(followPageMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<FollowPageDTO> findOne(Long id) {
    log.debug("Request to get FollowPage : {}", id);
    return followPageRepository.findById(id).map(followPageMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete FollowPage : {}", id);
    followPageRepository.deleteById(id);
    followPageSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<FollowPageDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of FollowPages for query {}", query);
    return followPageSearchRepository.search(queryStringQuery(query), pageable).map(followPageMapper::toDto);
  }
}
