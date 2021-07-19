package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.repository.MasterUserRepository;
import io.vavr.control.Option;

/**
 * Spring Data SQL repository for the {@link com.regitiny.catiny.domain.MasterUser} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.MasterUserAdvanceRepository}
 */
public interface MasterUserBaseRepository extends BaseRepository, CommonRepository, MasterUserRepository
{
  Option<MasterUser> findOneByUserLogin(String login);
}
