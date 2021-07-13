package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.HanhChinhVN;
import com.regitiny.catiny.repository.HanhChinhVNRepository;
import com.regitiny.catiny.repository.search.HanhChinhVNSearchRepository;
import com.regitiny.catiny.service.HanhChinhVNService;
import com.regitiny.catiny.service.dto.HanhChinhVNDTO;
import com.regitiny.catiny.service.mapper.HanhChinhVNMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link HanhChinhVN}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class HanhChinhVNServiceImpl implements HanhChinhVNService {

  private final Logger log = LoggerFactory.getLogger(HanhChinhVNServiceImpl.class);

  private final HanhChinhVNRepository hanhChinhVNRepository;

  private final HanhChinhVNMapper hanhChinhVNMapper;

  private final HanhChinhVNSearchRepository hanhChinhVNSearchRepository;

  public HanhChinhVNServiceImpl(
    HanhChinhVNRepository hanhChinhVNRepository,
    HanhChinhVNMapper hanhChinhVNMapper,
    HanhChinhVNSearchRepository hanhChinhVNSearchRepository
  ) {
    this.hanhChinhVNRepository = hanhChinhVNRepository;
    this.hanhChinhVNMapper = hanhChinhVNMapper;
    this.hanhChinhVNSearchRepository = hanhChinhVNSearchRepository;
  }

  @Override
  public HanhChinhVNDTO save(HanhChinhVNDTO hanhChinhVNDTO) {
    log.debug("Request to save HanhChinhVN : {}", hanhChinhVNDTO);
    HanhChinhVN hanhChinhVN = hanhChinhVNMapper.toEntity(hanhChinhVNDTO);
    hanhChinhVN = hanhChinhVNRepository.save(hanhChinhVN);
    HanhChinhVNDTO result = hanhChinhVNMapper.toDto(hanhChinhVN);
    hanhChinhVNSearchRepository.save(hanhChinhVN);
    return result;
  }

  @Override
  public Optional<HanhChinhVNDTO> partialUpdate(HanhChinhVNDTO hanhChinhVNDTO) {
    log.debug("Request to partially update HanhChinhVN : {}", hanhChinhVNDTO);

    return hanhChinhVNRepository
      .findById(hanhChinhVNDTO.getId())
      .map(
        existingHanhChinhVN -> {
          hanhChinhVNMapper.partialUpdate(existingHanhChinhVN, hanhChinhVNDTO);

          return existingHanhChinhVN;
        }
      )
      .map(hanhChinhVNRepository::save)
      .map(
        savedHanhChinhVN -> {
          hanhChinhVNSearchRepository.save(savedHanhChinhVN);

          return savedHanhChinhVN;
        }
      )
      .map(hanhChinhVNMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<HanhChinhVNDTO> findAll(Pageable pageable) {
    log.debug("Request to get all HanhChinhVNS");
    return hanhChinhVNRepository.findAll(pageable).map(hanhChinhVNMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<HanhChinhVNDTO> findOne(Long id) {
    log.debug("Request to get HanhChinhVN : {}", id);
    return hanhChinhVNRepository.findById(id).map(hanhChinhVNMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete HanhChinhVN : {}", id);
    hanhChinhVNRepository.deleteById(id);
    hanhChinhVNSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<HanhChinhVNDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of HanhChinhVNS for query {}", query);
    return hanhChinhVNSearchRepository.search(queryStringQuery(query), pageable).map(hanhChinhVNMapper::toDto);
  }
}
