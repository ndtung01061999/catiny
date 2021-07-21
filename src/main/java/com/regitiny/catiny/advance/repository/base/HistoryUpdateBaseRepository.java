package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.HistoryUpdate;
import com.regitiny.catiny.repository.HistoryUpdateRepository;
import io.vavr.collection.List;
import io.vavr.control.Option;

/**
 * Spring Data SQL repository for the {@link com.regitiny.catiny.domain.HistoryUpdate} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.HistoryUpdateAdvanceRepository}
 */
public interface HistoryUpdateBaseRepository extends BaseRepository, CommonRepository, HistoryUpdateRepository
{
  List<HistoryUpdate> findAllByBaseInfo(BaseInfo baseInfo);


  Option<Integer> countAllByBaseInfo(BaseInfo baseInfo);
}
