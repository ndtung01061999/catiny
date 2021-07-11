package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.PostComment;
import com.regitiny.catiny.repository.PostCommentRepository;
import com.regitiny.catiny.repository.search.PostCommentSearchRepository;
import com.regitiny.catiny.service.PostCommentService;
import com.regitiny.catiny.service.dto.PostCommentDTO;
import com.regitiny.catiny.service.mapper.PostCommentMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PostComment}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class PostCommentServiceImpl implements PostCommentService {

  private final Logger log = LoggerFactory.getLogger(PostCommentServiceImpl.class);

  private final PostCommentRepository postCommentRepository;

  private final PostCommentMapper postCommentMapper;

  private final PostCommentSearchRepository postCommentSearchRepository;

  public PostCommentServiceImpl(
    PostCommentRepository postCommentRepository,
    PostCommentMapper postCommentMapper,
    PostCommentSearchRepository postCommentSearchRepository
  ) {
    this.postCommentRepository = postCommentRepository;
    this.postCommentMapper = postCommentMapper;
    this.postCommentSearchRepository = postCommentSearchRepository;
  }

  @Override
  public PostCommentDTO save(PostCommentDTO postCommentDTO) {
    log.debug("Request to save PostComment : {}", postCommentDTO);
    PostComment postComment = postCommentMapper.toEntity(postCommentDTO);
    postComment = postCommentRepository.save(postComment);
    PostCommentDTO result = postCommentMapper.toDto(postComment);
    postCommentSearchRepository.save(postComment);
    return result;
  }

  @Override
  public Optional<PostCommentDTO> partialUpdate(PostCommentDTO postCommentDTO) {
    log.debug("Request to partially update PostComment : {}", postCommentDTO);

    return postCommentRepository
      .findById(postCommentDTO.getId())
      .map(
        existingPostComment -> {
          postCommentMapper.partialUpdate(existingPostComment, postCommentDTO);

          return existingPostComment;
        }
      )
      .map(postCommentRepository::save)
      .map(
        savedPostComment -> {
          postCommentSearchRepository.save(savedPostComment);

          return savedPostComment;
        }
      )
      .map(postCommentMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<PostCommentDTO> findAll(Pageable pageable) {
    log.debug("Request to get all PostComments");
    return postCommentRepository.findAll(pageable).map(postCommentMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<PostCommentDTO> findOne(Long id) {
    log.debug("Request to get PostComment : {}", id);
    return postCommentRepository.findById(id).map(postCommentMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete PostComment : {}", id);
    postCommentRepository.deleteById(id);
    postCommentSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<PostCommentDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of PostComments for query {}", query);
    return postCommentSearchRepository.search(queryStringQuery(query), pageable).map(postCommentMapper::toDto);
  }
}
