package com.regitiny.catiny.advance.repository;

import com.regitiny.catiny.advance.repository.base.HistoryUpdateBaseRepository;
import com.regitiny.catiny.domain.BaseInfo;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the {@link com.regitiny.catiny.domain.HistoryUpdate} entity.
 * <p>
 * here contains complex queries with pure SQL or HQL syntax.
 * if you want to write simple query then you should write to :
 * {@link HistoryUpdateBaseRepository}
 */
@Repository
@SuppressWarnings("unused")
public interface HistoryUpdateAdvanceRepository extends HistoryUpdateBaseRepository
{
  @Query("select max(version) from HistoryUpdate where baseInfo = :baseInfo")
  Option<Integer> lastVersion(BaseInfo baseInfo);
}
