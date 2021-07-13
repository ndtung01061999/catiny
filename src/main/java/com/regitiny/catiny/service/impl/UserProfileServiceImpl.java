package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.UserProfile;
import com.regitiny.catiny.repository.UserProfileRepository;
import com.regitiny.catiny.repository.search.UserProfileSearchRepository;
import com.regitiny.catiny.service.UserProfileService;
import com.regitiny.catiny.service.dto.UserProfileDTO;
import com.regitiny.catiny.service.mapper.UserProfileMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserProfile}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class UserProfileServiceImpl implements UserProfileService {

  private final Logger log = LoggerFactory.getLogger(UserProfileServiceImpl.class);

  private final UserProfileRepository userProfileRepository;

  private final UserProfileMapper userProfileMapper;

  private final UserProfileSearchRepository userProfileSearchRepository;

  public UserProfileServiceImpl(
    UserProfileRepository userProfileRepository,
    UserProfileMapper userProfileMapper,
    UserProfileSearchRepository userProfileSearchRepository
  ) {
    this.userProfileRepository = userProfileRepository;
    this.userProfileMapper = userProfileMapper;
    this.userProfileSearchRepository = userProfileSearchRepository;
  }

  @Override
  public UserProfileDTO save(UserProfileDTO userProfileDTO) {
    log.debug("Request to save UserProfile : {}", userProfileDTO);
    UserProfile userProfile = userProfileMapper.toEntity(userProfileDTO);
    userProfile = userProfileRepository.save(userProfile);
    UserProfileDTO result = userProfileMapper.toDto(userProfile);
    userProfileSearchRepository.save(userProfile);
    return result;
  }

  @Override
  public Optional<UserProfileDTO> partialUpdate(UserProfileDTO userProfileDTO) {
    log.debug("Request to partially update UserProfile : {}", userProfileDTO);

    return userProfileRepository
      .findById(userProfileDTO.getId())
      .map(
        existingUserProfile -> {
          userProfileMapper.partialUpdate(existingUserProfile, userProfileDTO);

          return existingUserProfile;
        }
      )
      .map(userProfileRepository::save)
      .map(
        savedUserProfile -> {
          userProfileSearchRepository.save(savedUserProfile);

          return savedUserProfile;
        }
      )
      .map(userProfileMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<UserProfileDTO> findAll(Pageable pageable) {
    log.debug("Request to get all UserProfiles");
    return userProfileRepository.findAll(pageable).map(userProfileMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<UserProfileDTO> findOne(Long id) {
    log.debug("Request to get UserProfile : {}", id);
    return userProfileRepository.findById(id).map(userProfileMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete UserProfile : {}", id);
    userProfileRepository.deleteById(id);
    userProfileSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<UserProfileDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of UserProfiles for query {}", query);
    return userProfileSearchRepository.search(queryStringQuery(query), pageable).map(userProfileMapper::toDto);
  }
}
