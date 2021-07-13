package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.GroupProfile;
import com.regitiny.catiny.repository.GroupProfileRepository;
import com.regitiny.catiny.repository.search.GroupProfileSearchRepository;
import com.regitiny.catiny.service.GroupProfileService;
import com.regitiny.catiny.service.dto.GroupProfileDTO;
import com.regitiny.catiny.service.mapper.GroupProfileMapper;
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
 * Service Implementation for managing {@link GroupProfile}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class GroupProfileServiceImpl implements GroupProfileService {

  private final Logger log = LoggerFactory.getLogger(GroupProfileServiceImpl.class);

  private final GroupProfileRepository groupProfileRepository;

  private final GroupProfileMapper groupProfileMapper;

  private final GroupProfileSearchRepository groupProfileSearchRepository;

  public GroupProfileServiceImpl(
    GroupProfileRepository groupProfileRepository,
    GroupProfileMapper groupProfileMapper,
    GroupProfileSearchRepository groupProfileSearchRepository
  ) {
    this.groupProfileRepository = groupProfileRepository;
    this.groupProfileMapper = groupProfileMapper;
    this.groupProfileSearchRepository = groupProfileSearchRepository;
  }

  @Override
  public GroupProfileDTO save(GroupProfileDTO groupProfileDTO) {
    log.debug("Request to save GroupProfile : {}", groupProfileDTO);
    GroupProfile groupProfile = groupProfileMapper.toEntity(groupProfileDTO);
    groupProfile = groupProfileRepository.save(groupProfile);
    GroupProfileDTO result = groupProfileMapper.toDto(groupProfile);
    groupProfileSearchRepository.save(groupProfile);
    return result;
  }

  @Override
  public Optional<GroupProfileDTO> partialUpdate(GroupProfileDTO groupProfileDTO) {
    log.debug("Request to partially update GroupProfile : {}", groupProfileDTO);

    return groupProfileRepository
      .findById(groupProfileDTO.getId())
      .map(
        existingGroupProfile -> {
          groupProfileMapper.partialUpdate(existingGroupProfile, groupProfileDTO);

          return existingGroupProfile;
        }
      )
      .map(groupProfileRepository::save)
      .map(
        savedGroupProfile -> {
          groupProfileSearchRepository.save(savedGroupProfile);

          return savedGroupProfile;
        }
      )
      .map(groupProfileMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<GroupProfileDTO> findAll(Pageable pageable) {
    log.debug("Request to get all GroupProfiles");
    return groupProfileRepository.findAll(pageable).map(groupProfileMapper::toDto);
  }

  /**
   *  Get all the groupProfiles where Group is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<GroupProfileDTO> findAllWhereGroupIsNull() {
    log.debug("Request to get all groupProfiles where Group is null");
    return StreamSupport
      .stream(groupProfileRepository.findAll().spliterator(), false)
      .filter(groupProfile -> groupProfile.getGroup() == null)
      .map(groupProfileMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<GroupProfileDTO> findOne(Long id) {
    log.debug("Request to get GroupProfile : {}", id);
    return groupProfileRepository.findById(id).map(groupProfileMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete GroupProfile : {}", id);
    groupProfileRepository.deleteById(id);
    groupProfileSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<GroupProfileDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of GroupProfiles for query {}", query);
    return groupProfileSearchRepository.search(queryStringQuery(query), pageable).map(groupProfileMapper::toDto);
  }
}
