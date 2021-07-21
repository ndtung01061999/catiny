package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.service.MasterUserQueryService;
import com.regitiny.catiny.service.MasterUserService;
import io.vavr.control.Option;

/**
 * Spring Data Elasticsearch advance-repository extends jhipster-search-repository for the {@link com.regitiny.catiny.domain.MasterUser} entityDomain.
 *
 * @see MasterUserService is base repository generate by jhipster
 */
public interface MasterUserAdvanceService extends LocalService<MasterUserService, MasterUserQueryService>
{
  Option<MasterUser> currentMasterUser();


  Option<MasterUser> anonymousMasterUser();


  Option<MasterUser> createMasterUser(String login, String nickName);
}
