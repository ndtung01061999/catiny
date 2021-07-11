package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.FollowUser;
import com.regitiny.catiny.repository.FollowUserRepository;
import com.regitiny.catiny.repository.search.FollowUserSearchRepository;
import com.regitiny.catiny.service.FollowUserService;
import com.regitiny.catiny.service.dto.FollowUserDTO;
import com.regitiny.catiny.service.mapper.FollowUserMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FollowUser}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class FollowUserServiceImpl implements FollowUserService {

  private final Logger log = LoggerFactory.getLogger(FollowUserServiceImpl.class);

  private final FollowUserRepository followUserRepository;

  private final FollowUserMapper followUserMapper;

  private final FollowUserSearchRepository followUserSearchRepository;

  public FollowUserServiceImpl(
    FollowUserRepository followUserRepository,
    FollowUserMapper followUserMapper,
    FollowUserSearchRepository followUserSearchRepository
  ) {
    this.followUserRepository = followUserRepository;
    this.followUserMapper = followUserMapper;
    this.followUserSearchRepository = followUserSearchRepository;
  }

  @Override
  public FollowUserDTO save(FollowUserDTO followUserDTO) {
    log.debug("Request to save FollowUser : {}", followUserDTO);
    FollowUser followUser = followUserMapper.toEntity(followUserDTO);
    followUser = followUserRepository.save(followUser);
    FollowUserDTO result = followUserMapper.toDto(followUser);
    followUserSearchRepository.save(followUser);
    return result;
  }

  @Override
  public Optional<FollowUserDTO> partialUpdate(FollowUserDTO followUserDTO) {
    log.debug("Request to partially update FollowUser : {}", followUserDTO);

    return followUserRepository
      .findById(followUserDTO.getId())
      .map(
        existingFollowUser -> {
          followUserMapper.partialUpdate(existingFollowUser, followUserDTO);

          return existingFollowUser;
        }
      )
      .map(followUserRepository::save)
      .map(
        savedFollowUser -> {
          followUserSearchRepository.save(savedFollowUser);

          return savedFollowUser;
        }
      )
      .map(followUserMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<FollowUserDTO> findAll(Pageable pageable) {
    log.debug("Request to get all FollowUsers");
    return followUserRepository.findAll(pageable).map(followUserMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<FollowUserDTO> findOne(Long id) {
    log.debug("Request to get FollowUser : {}", id);
    return followUserRepository.findById(id).map(followUserMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete FollowUser : {}", id);
    followUserRepository.deleteById(id);
    followUserSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<FollowUserDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of FollowUsers for query {}", query);
    return followUserSearchRepository.search(queryStringQuery(query), pageable).map(followUserMapper::toDto);
  }
}
