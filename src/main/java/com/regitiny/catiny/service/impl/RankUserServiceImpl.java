package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.RankUser;
import com.regitiny.catiny.repository.RankUserRepository;
import com.regitiny.catiny.repository.search.RankUserSearchRepository;
import com.regitiny.catiny.service.RankUserService;
import com.regitiny.catiny.service.dto.RankUserDTO;
import com.regitiny.catiny.service.mapper.RankUserMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RankUser}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class RankUserServiceImpl implements RankUserService {

  private final Logger log = LoggerFactory.getLogger(RankUserServiceImpl.class);

  private final RankUserRepository rankUserRepository;

  private final RankUserMapper rankUserMapper;

  private final RankUserSearchRepository rankUserSearchRepository;

  public RankUserServiceImpl(
    RankUserRepository rankUserRepository,
    RankUserMapper rankUserMapper,
    RankUserSearchRepository rankUserSearchRepository
  ) {
    this.rankUserRepository = rankUserRepository;
    this.rankUserMapper = rankUserMapper;
    this.rankUserSearchRepository = rankUserSearchRepository;
  }

  @Override
  public RankUserDTO save(RankUserDTO rankUserDTO) {
    log.debug("Request to save RankUser : {}", rankUserDTO);
    RankUser rankUser = rankUserMapper.toEntity(rankUserDTO);
    rankUser = rankUserRepository.save(rankUser);
    RankUserDTO result = rankUserMapper.toDto(rankUser);
    rankUserSearchRepository.save(rankUser);
    return result;
  }

  @Override
  public Optional<RankUserDTO> partialUpdate(RankUserDTO rankUserDTO) {
    log.debug("Request to partially update RankUser : {}", rankUserDTO);

    return rankUserRepository
      .findById(rankUserDTO.getId())
      .map(
        existingRankUser -> {
          rankUserMapper.partialUpdate(existingRankUser, rankUserDTO);

          return existingRankUser;
        }
      )
      .map(rankUserRepository::save)
      .map(
        savedRankUser -> {
          rankUserSearchRepository.save(savedRankUser);

          return savedRankUser;
        }
      )
      .map(rankUserMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<RankUserDTO> findAll(Pageable pageable) {
    log.debug("Request to get all RankUsers");
    return rankUserRepository.findAll(pageable).map(rankUserMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<RankUserDTO> findOne(Long id) {
    log.debug("Request to get RankUser : {}", id);
    return rankUserRepository.findById(id).map(rankUserMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete RankUser : {}", id);
    rankUserRepository.deleteById(id);
    rankUserSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<RankUserDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of RankUsers for query {}", query);
    return rankUserSearchRepository.search(queryStringQuery(query), pageable).map(rankUserMapper::toDto);
  }
}
