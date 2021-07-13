package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Friend;
import com.regitiny.catiny.repository.FriendRepository;
import com.regitiny.catiny.repository.search.FriendSearchRepository;
import com.regitiny.catiny.service.FriendService;
import com.regitiny.catiny.service.dto.FriendDTO;
import com.regitiny.catiny.service.mapper.FriendMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Friend}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class FriendServiceImpl implements FriendService {

  private final Logger log = LoggerFactory.getLogger(FriendServiceImpl.class);

  private final FriendRepository friendRepository;

  private final FriendMapper friendMapper;

  private final FriendSearchRepository friendSearchRepository;

  public FriendServiceImpl(FriendRepository friendRepository, FriendMapper friendMapper, FriendSearchRepository friendSearchRepository) {
    this.friendRepository = friendRepository;
    this.friendMapper = friendMapper;
    this.friendSearchRepository = friendSearchRepository;
  }

  @Override
  public FriendDTO save(FriendDTO friendDTO) {
    log.debug("Request to save Friend : {}", friendDTO);
    Friend friend = friendMapper.toEntity(friendDTO);
    friend = friendRepository.save(friend);
    FriendDTO result = friendMapper.toDto(friend);
    friendSearchRepository.save(friend);
    return result;
  }

  @Override
  public Optional<FriendDTO> partialUpdate(FriendDTO friendDTO) {
    log.debug("Request to partially update Friend : {}", friendDTO);

    return friendRepository
      .findById(friendDTO.getId())
      .map(
        existingFriend -> {
          friendMapper.partialUpdate(existingFriend, friendDTO);

          return existingFriend;
        }
      )
      .map(friendRepository::save)
      .map(
        savedFriend -> {
          friendSearchRepository.save(savedFriend);

          return savedFriend;
        }
      )
      .map(friendMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<FriendDTO> findAll(Pageable pageable) {
    log.debug("Request to get all Friends");
    return friendRepository.findAll(pageable).map(friendMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<FriendDTO> findOne(Long id) {
    log.debug("Request to get Friend : {}", id);
    return friendRepository.findById(id).map(friendMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete Friend : {}", id);
    friendRepository.deleteById(id);
    friendSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<FriendDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of Friends for query {}", query);
    return friendSearchRepository.search(queryStringQuery(query), pageable).map(friendMapper::toDto);
  }
}
