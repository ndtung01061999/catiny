package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.service.impl.LocalServiceImpl;

/**
 * if you like using EntityService and EntityQueryService of jhipster
 * then you add extends this Interface to EntityAdvancedService
 * and add extends Class LocalServiceImpl to EntityAdvancedServiceImpl
 *
 * @param <S> EntityService
 * @param <Q> EntityQueryService
 * @see LocalServiceImpl
 */
public interface LocalService<S, Q>
{
  Q LocalQueryService();

  S LocalService ();
}
