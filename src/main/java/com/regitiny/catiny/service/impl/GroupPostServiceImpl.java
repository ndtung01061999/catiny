package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.GroupPost;
import com.regitiny.catiny.repository.GroupPostRepository;
import com.regitiny.catiny.repository.search.GroupPostSearchRepository;
import com.regitiny.catiny.service.GroupPostService;
import com.regitiny.catiny.service.dto.GroupPostDTO;
import com.regitiny.catiny.service.mapper.GroupPostMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link GroupPost}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class GroupPostServiceImpl implements GroupPostService {

  private final Logger log = LoggerFactory.getLogger(GroupPostServiceImpl.class);

  private final GroupPostRepository groupPostRepository;

  private final GroupPostMapper groupPostMapper;

  private final GroupPostSearchRepository groupPostSearchRepository;

  public GroupPostServiceImpl(
    GroupPostRepository groupPostRepository,
    GroupPostMapper groupPostMapper,
    GroupPostSearchRepository groupPostSearchRepository
  ) {
    this.groupPostRepository = groupPostRepository;
    this.groupPostMapper = groupPostMapper;
    this.groupPostSearchRepository = groupPostSearchRepository;
  }

  @Override
  public GroupPostDTO save(GroupPostDTO groupPostDTO) {
    log.debug("Request to save GroupPost : {}", groupPostDTO);
    GroupPost groupPost = groupPostMapper.toEntity(groupPostDTO);
    groupPost = groupPostRepository.save(groupPost);
    GroupPostDTO result = groupPostMapper.toDto(groupPost);
    groupPostSearchRepository.save(groupPost);
    return result;
  }

  @Override
  public Optional<GroupPostDTO> partialUpdate(GroupPostDTO groupPostDTO) {
    log.debug("Request to partially update GroupPost : {}", groupPostDTO);

    return groupPostRepository
      .findById(groupPostDTO.getId())
      .map(
        existingGroupPost -> {
          groupPostMapper.partialUpdate(existingGroupPost, groupPostDTO);

          return existingGroupPost;
        }
      )
      .map(groupPostRepository::save)
      .map(
        savedGroupPost -> {
          groupPostSearchRepository.save(savedGroupPost);

          return savedGroupPost;
        }
      )
      .map(groupPostMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<GroupPostDTO> findAll(Pageable pageable) {
    log.debug("Request to get all GroupPosts");
    return groupPostRepository.findAll(pageable).map(groupPostMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<GroupPostDTO> findOne(Long id) {
    log.debug("Request to get GroupPost : {}", id);
    return groupPostRepository.findById(id).map(groupPostMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete GroupPost : {}", id);
    groupPostRepository.deleteById(id);
    groupPostSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<GroupPostDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of GroupPosts for query {}", query);
    return groupPostSearchRepository.search(queryStringQuery(query), pageable).map(groupPostMapper::toDto);
  }
}
