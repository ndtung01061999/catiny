package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.service.PostQueryService;
import com.regitiny.catiny.service.PostService;

/**
 * Spring Data Elasticsearch advance-repository extends jhipster-search-repository for the {@link com.regitiny.catiny.domain.Post} entityDomain.
 *
 * @see PostService is base repository generate by jhipster
 */
public interface PostAdvanceService extends LocalService<PostService, PostQueryService>
{
}
