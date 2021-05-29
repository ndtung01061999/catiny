package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.service.mapper.EntityMapper;

import java.util.List;


/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - AdvanceDTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityAdvanceMapper<D, E>
{
  E modelToDto(D dto);


  D dtoToModel(E entity);


  List<E> modelToDto(List<D> dtoList);


  List<D> dtoToModel(List<E> entityList);
}
