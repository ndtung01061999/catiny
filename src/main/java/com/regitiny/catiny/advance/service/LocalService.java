package com.regitiny.catiny.advance.service;

/**
 * if you like using EntityService and EntityQueryService of jhipster
 * then you add extends this Interface to EntityAdvancedService
 * and add extends Class LocalServiceImpl to EntityAdvancedServiceImpl
 * @see LocalServiceImpl
 * @param <S> EntityService
 * @param <Q> EntityQueryService
 */
public interface LocalService<S,Q>
{
  Q LocalQueryService();

  S LocalService ();
}
