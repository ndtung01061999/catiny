package com.regitiny.catiny.advance.repository;

import io.vavr.control.Option;

import java.util.UUID;

public interface CommonRepository<E>
{
  Option<E> findOneByUuid(UUID uuid);
}
