package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.RankGroup;
import com.regitiny.catiny.repository.RankGroupRepository;
import com.regitiny.catiny.repository.search.RankGroupSearchRepository;
import com.regitiny.catiny.service.RankGroupService;
import com.regitiny.catiny.service.dto.RankGroupDTO;
import com.regitiny.catiny.service.mapper.RankGroupMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RankGroup}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class RankGroupServiceImpl implements RankGroupService {

  private final Logger log = LoggerFactory.getLogger(RankGroupServiceImpl.class);

  private final RankGroupRepository rankGroupRepository;

  private final RankGroupMapper rankGroupMapper;

  private final RankGroupSearchRepository rankGroupSearchRepository;

  public RankGroupServiceImpl(
    RankGroupRepository rankGroupRepository,
    RankGroupMapper rankGroupMapper,
    RankGroupSearchRepository rankGroupSearchRepository
  ) {
    this.rankGroupRepository = rankGroupRepository;
    this.rankGroupMapper = rankGroupMapper;
    this.rankGroupSearchRepository = rankGroupSearchRepository;
  }

  @Override
  public RankGroupDTO save(RankGroupDTO rankGroupDTO) {
    log.debug("Request to save RankGroup : {}", rankGroupDTO);
    RankGroup rankGroup = rankGroupMapper.toEntity(rankGroupDTO);
    rankGroup = rankGroupRepository.save(rankGroup);
    RankGroupDTO result = rankGroupMapper.toDto(rankGroup);
    rankGroupSearchRepository.save(rankGroup);
    return result;
  }

  @Override
  public Optional<RankGroupDTO> partialUpdate(RankGroupDTO rankGroupDTO) {
    log.debug("Request to partially update RankGroup : {}", rankGroupDTO);

    return rankGroupRepository
      .findById(rankGroupDTO.getId())
      .map(
        existingRankGroup -> {
          rankGroupMapper.partialUpdate(existingRankGroup, rankGroupDTO);

          return existingRankGroup;
        }
      )
      .map(rankGroupRepository::save)
      .map(
        savedRankGroup -> {
          rankGroupSearchRepository.save(savedRankGroup);

          return savedRankGroup;
        }
      )
      .map(rankGroupMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<RankGroupDTO> findAll(Pageable pageable) {
    log.debug("Request to get all RankGroups");
    return rankGroupRepository.findAll(pageable).map(rankGroupMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<RankGroupDTO> findOne(Long id) {
    log.debug("Request to get RankGroup : {}", id);
    return rankGroupRepository.findById(id).map(rankGroupMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete RankGroup : {}", id);
    rankGroupRepository.deleteById(id);
    rankGroupSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<RankGroupDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of RankGroups for query {}", query);
    return rankGroupSearchRepository.search(queryStringQuery(query), pageable).map(rankGroupMapper::toDto);
  }
}
