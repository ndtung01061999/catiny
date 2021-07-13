package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.repository.MasterUserRepository;
import com.regitiny.catiny.repository.UserRepository;
import com.regitiny.catiny.repository.search.MasterUserSearchRepository;
import com.regitiny.catiny.service.MasterUserService;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import com.regitiny.catiny.service.mapper.MasterUserMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link MasterUser}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class MasterUserServiceImpl implements MasterUserService {

  private final Logger log = LoggerFactory.getLogger(MasterUserServiceImpl.class);

  private final MasterUserRepository masterUserRepository;

  private final MasterUserMapper masterUserMapper;

  private final MasterUserSearchRepository masterUserSearchRepository;

  private final UserRepository userRepository;

  public MasterUserServiceImpl(
    MasterUserRepository masterUserRepository,
    MasterUserMapper masterUserMapper,
    MasterUserSearchRepository masterUserSearchRepository,
    UserRepository userRepository
  ) {
    this.masterUserRepository = masterUserRepository;
    this.masterUserMapper = masterUserMapper;
    this.masterUserSearchRepository = masterUserSearchRepository;
    this.userRepository = userRepository;
  }

  @Override
  public MasterUserDTO save(MasterUserDTO masterUserDTO) {
    log.debug("Request to save MasterUser : {}", masterUserDTO);
    MasterUser masterUser = masterUserMapper.toEntity(masterUserDTO);
    Long userId = masterUserDTO.getUser().getId();
    userRepository.findById(userId).ifPresent(masterUser::user);
    masterUser = masterUserRepository.save(masterUser);
    MasterUserDTO result = masterUserMapper.toDto(masterUser);
    masterUserSearchRepository.save(masterUser);
    return result;
  }

  @Override
  public Optional<MasterUserDTO> partialUpdate(MasterUserDTO masterUserDTO) {
    log.debug("Request to partially update MasterUser : {}", masterUserDTO);

    return masterUserRepository
      .findById(masterUserDTO.getId())
      .map(
        existingMasterUser -> {
          masterUserMapper.partialUpdate(existingMasterUser, masterUserDTO);

          return existingMasterUser;
        }
      )
      .map(masterUserRepository::save)
      .map(
        savedMasterUser -> {
          masterUserSearchRepository.save(savedMasterUser);

          return savedMasterUser;
        }
      )
      .map(masterUserMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<MasterUserDTO> findAll(Pageable pageable) {
    log.debug("Request to get all MasterUsers");
    return masterUserRepository.findAll(pageable).map(masterUserMapper::toDto);
  }

  public Page<MasterUserDTO> findAllWithEagerRelationships(Pageable pageable) {
    return masterUserRepository.findAllWithEagerRelationships(pageable).map(masterUserMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<MasterUserDTO> findOne(Long id) {
    log.debug("Request to get MasterUser : {}", id);
    return masterUserRepository.findOneWithEagerRelationships(id).map(masterUserMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete MasterUser : {}", id);
    masterUserRepository.deleteById(id);
    masterUserSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<MasterUserDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of MasterUsers for query {}", query);
    return masterUserSearchRepository.search(queryStringQuery(query), pageable).map(masterUserMapper::toDto);
  }
}
