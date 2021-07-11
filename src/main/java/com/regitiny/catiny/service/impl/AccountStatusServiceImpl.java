package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.AccountStatus;
import com.regitiny.catiny.repository.AccountStatusRepository;
import com.regitiny.catiny.repository.search.AccountStatusSearchRepository;
import com.regitiny.catiny.service.AccountStatusService;
import com.regitiny.catiny.service.dto.AccountStatusDTO;
import com.regitiny.catiny.service.mapper.AccountStatusMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AccountStatus}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class AccountStatusServiceImpl implements AccountStatusService {

  private final Logger log = LoggerFactory.getLogger(AccountStatusServiceImpl.class);

  private final AccountStatusRepository accountStatusRepository;

  private final AccountStatusMapper accountStatusMapper;

  private final AccountStatusSearchRepository accountStatusSearchRepository;

  public AccountStatusServiceImpl(
    AccountStatusRepository accountStatusRepository,
    AccountStatusMapper accountStatusMapper,
    AccountStatusSearchRepository accountStatusSearchRepository
  ) {
    this.accountStatusRepository = accountStatusRepository;
    this.accountStatusMapper = accountStatusMapper;
    this.accountStatusSearchRepository = accountStatusSearchRepository;
  }

  @Override
  public AccountStatusDTO save(AccountStatusDTO accountStatusDTO) {
    log.debug("Request to save AccountStatus : {}", accountStatusDTO);
    AccountStatus accountStatus = accountStatusMapper.toEntity(accountStatusDTO);
    accountStatus = accountStatusRepository.save(accountStatus);
    AccountStatusDTO result = accountStatusMapper.toDto(accountStatus);
    accountStatusSearchRepository.save(accountStatus);
    return result;
  }

  @Override
  public Optional<AccountStatusDTO> partialUpdate(AccountStatusDTO accountStatusDTO) {
    log.debug("Request to partially update AccountStatus : {}", accountStatusDTO);

    return accountStatusRepository
      .findById(accountStatusDTO.getId())
      .map(
        existingAccountStatus -> {
          accountStatusMapper.partialUpdate(existingAccountStatus, accountStatusDTO);

          return existingAccountStatus;
        }
      )
      .map(accountStatusRepository::save)
      .map(
        savedAccountStatus -> {
          accountStatusSearchRepository.save(savedAccountStatus);

          return savedAccountStatus;
        }
      )
      .map(accountStatusMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<AccountStatusDTO> findAll(Pageable pageable) {
    log.debug("Request to get all AccountStatuses");
    return accountStatusRepository.findAll(pageable).map(accountStatusMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<AccountStatusDTO> findOne(Long id) {
    log.debug("Request to get AccountStatus : {}", id);
    return accountStatusRepository.findById(id).map(accountStatusMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete AccountStatus : {}", id);
    accountStatusRepository.deleteById(id);
    accountStatusSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<AccountStatusDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of AccountStatuses for query {}", query);
    return accountStatusSearchRepository.search(queryStringQuery(query), pageable).map(accountStatusMapper::toDto);
  }
}
