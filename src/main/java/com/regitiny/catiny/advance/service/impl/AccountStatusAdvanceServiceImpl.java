package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.AccountStatusAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.AccountStatusAdvanceSearch;
import com.regitiny.catiny.advance.service.AccountStatusAdvanceService;
import com.regitiny.catiny.advance.service.mapper.AccountStatusAdvanceMapper;
import com.regitiny.catiny.service.AccountStatusQueryService;
import com.regitiny.catiny.service.AccountStatusService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class AccountStatusAdvanceServiceImpl extends LocalServiceImpl<AccountStatusService, AccountStatusQueryService> implements AccountStatusAdvanceService
{
  private final AccountStatusAdvanceRepository accountStatusAdvanceRepository;

  private final AccountStatusAdvanceSearch accountStatusAdvanceSearch;

  private final AccountStatusAdvanceMapper accountStatusAdvanceMapper;

  public AccountStatusAdvanceServiceImpl(
    AccountStatusAdvanceRepository accountStatusAdvanceRepository,
    AccountStatusAdvanceSearch accountStatusAdvanceSearch,
    AccountStatusAdvanceMapper accountStatusAdvanceMapper)
  {
    this.accountStatusAdvanceRepository = accountStatusAdvanceRepository;
    this.accountStatusAdvanceSearch = accountStatusAdvanceSearch;
    this.accountStatusAdvanceMapper = accountStatusAdvanceMapper;
  }
}
