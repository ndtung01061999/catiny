package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.service.UserProfileQueryService;
import com.regitiny.catiny.service.UserProfileService;

/**
 * Spring Data Elasticsearch advance-repository extends jhipster-search-repository for the {@link com.regitiny.catiny.domain.UserProfile} entityDomain.
 *
 * @see UserProfileService is base repository generate by jhipster
 */
public interface UserProfileAdvanceService extends LocalService<UserProfileService, UserProfileQueryService>
{
}
