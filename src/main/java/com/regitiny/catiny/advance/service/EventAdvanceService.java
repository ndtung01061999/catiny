package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.service.EventQueryService;
import com.regitiny.catiny.service.EventService;

/**
 * Spring Data Elasticsearch advance-repository extends jhipster-search-repository for the {@link com.regitiny.catiny.domain.Event} entityDomain.
 *
 * @see EventService is base repository generate by jhipster
 */
public interface EventAdvanceService extends LocalService<EventService, EventQueryService>
{
}
