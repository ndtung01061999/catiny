package com.regitiny.catiny.advance.service.mapper;

import java.util.List;


/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <M> - Model type parameter.
 * @param <D> - DTO type parameter.
 */

public interface EntityAdvanceMapper<M, D>
{
  D modelToDto(M dto);


  M dtoToModel(D entity);


  List<D> modelToDto(List<M> dtoList);


  List<M> dtoToModel(List<D> entityList);
}
