package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.PostLike;
import com.regitiny.catiny.repository.PostLikeRepository;
import com.regitiny.catiny.repository.search.PostLikeSearchRepository;
import com.regitiny.catiny.service.PostLikeService;
import com.regitiny.catiny.service.dto.PostLikeDTO;
import com.regitiny.catiny.service.mapper.PostLikeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PostLike}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class PostLikeServiceImpl implements PostLikeService {

  private final Logger log = LoggerFactory.getLogger(PostLikeServiceImpl.class);

  private final PostLikeRepository postLikeRepository;

  private final PostLikeMapper postLikeMapper;

  private final PostLikeSearchRepository postLikeSearchRepository;

  public PostLikeServiceImpl(
    PostLikeRepository postLikeRepository,
    PostLikeMapper postLikeMapper,
    PostLikeSearchRepository postLikeSearchRepository
  ) {
    this.postLikeRepository = postLikeRepository;
    this.postLikeMapper = postLikeMapper;
    this.postLikeSearchRepository = postLikeSearchRepository;
  }

  @Override
  public PostLikeDTO save(PostLikeDTO postLikeDTO) {
    log.debug("Request to save PostLike : {}", postLikeDTO);
    PostLike postLike = postLikeMapper.toEntity(postLikeDTO);
    postLike = postLikeRepository.save(postLike);
    PostLikeDTO result = postLikeMapper.toDto(postLike);
    postLikeSearchRepository.save(postLike);
    return result;
  }

  @Override
  public Optional<PostLikeDTO> partialUpdate(PostLikeDTO postLikeDTO) {
    log.debug("Request to partially update PostLike : {}", postLikeDTO);

    return postLikeRepository
      .findById(postLikeDTO.getId())
      .map(
        existingPostLike -> {
          postLikeMapper.partialUpdate(existingPostLike, postLikeDTO);

          return existingPostLike;
        }
      )
      .map(postLikeRepository::save)
      .map(
        savedPostLike -> {
          postLikeSearchRepository.save(savedPostLike);

          return savedPostLike;
        }
      )
      .map(postLikeMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<PostLikeDTO> findAll(Pageable pageable) {
    log.debug("Request to get all PostLikes");
    return postLikeRepository.findAll(pageable).map(postLikeMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<PostLikeDTO> findOne(Long id) {
    log.debug("Request to get PostLike : {}", id);
    return postLikeRepository.findById(id).map(postLikeMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete PostLike : {}", id);
    postLikeRepository.deleteById(id);
    postLikeSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<PostLikeDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of PostLikes for query {}", query);
    return postLikeSearchRepository.search(queryStringQuery(query), pageable).map(postLikeMapper::toDto);
  }
}
