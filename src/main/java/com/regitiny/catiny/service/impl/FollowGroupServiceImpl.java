package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.FollowGroup;
import com.regitiny.catiny.repository.FollowGroupRepository;
import com.regitiny.catiny.repository.search.FollowGroupSearchRepository;
import com.regitiny.catiny.service.FollowGroupService;
import com.regitiny.catiny.service.dto.FollowGroupDTO;
import com.regitiny.catiny.service.mapper.FollowGroupMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FollowGroup}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class FollowGroupServiceImpl implements FollowGroupService {

  private final Logger log = LoggerFactory.getLogger(FollowGroupServiceImpl.class);

  private final FollowGroupRepository followGroupRepository;

  private final FollowGroupMapper followGroupMapper;

  private final FollowGroupSearchRepository followGroupSearchRepository;

  public FollowGroupServiceImpl(
    FollowGroupRepository followGroupRepository,
    FollowGroupMapper followGroupMapper,
    FollowGroupSearchRepository followGroupSearchRepository
  ) {
    this.followGroupRepository = followGroupRepository;
    this.followGroupMapper = followGroupMapper;
    this.followGroupSearchRepository = followGroupSearchRepository;
  }

  @Override
  public FollowGroupDTO save(FollowGroupDTO followGroupDTO) {
    log.debug("Request to save FollowGroup : {}", followGroupDTO);
    FollowGroup followGroup = followGroupMapper.toEntity(followGroupDTO);
    followGroup = followGroupRepository.save(followGroup);
    FollowGroupDTO result = followGroupMapper.toDto(followGroup);
    followGroupSearchRepository.save(followGroup);
    return result;
  }

  @Override
  public Optional<FollowGroupDTO> partialUpdate(FollowGroupDTO followGroupDTO) {
    log.debug("Request to partially update FollowGroup : {}", followGroupDTO);

    return followGroupRepository
      .findById(followGroupDTO.getId())
      .map(
        existingFollowGroup -> {
          followGroupMapper.partialUpdate(existingFollowGroup, followGroupDTO);

          return existingFollowGroup;
        }
      )
      .map(followGroupRepository::save)
      .map(
        savedFollowGroup -> {
          followGroupSearchRepository.save(savedFollowGroup);

          return savedFollowGroup;
        }
      )
      .map(followGroupMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<FollowGroupDTO> findAll(Pageable pageable) {
    log.debug("Request to get all FollowGroups");
    return followGroupRepository.findAll(pageable).map(followGroupMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<FollowGroupDTO> findOne(Long id) {
    log.debug("Request to get FollowGroup : {}", id);
    return followGroupRepository.findById(id).map(followGroupMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete FollowGroup : {}", id);
    followGroupRepository.deleteById(id);
    followGroupSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<FollowGroupDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of FollowGroups for query {}", query);
    return followGroupSearchRepository.search(queryStringQuery(query), pageable).map(followGroupMapper::toDto);
  }
}
