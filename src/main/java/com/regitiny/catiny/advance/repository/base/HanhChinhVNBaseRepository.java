package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.HanhChinhVN;
import com.regitiny.catiny.repository.HanhChinhVNRepository;
import io.vavr.control.Option;

import java.util.UUID;

/**
 * Spring Data SQL repository for the {@link HanhChinhVN} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.HanhChinhVNAdvanceRepository}
 */
public interface HanhChinhVNBaseRepository extends BaseRepository<HanhChinhVN>, CommonRepository<HanhChinhVN>, HanhChinhVNRepository
{
  @Override
  default Option<HanhChinhVN> findOneByUuid(UUID uuid)
  {
    return Option.none();
  }
}
