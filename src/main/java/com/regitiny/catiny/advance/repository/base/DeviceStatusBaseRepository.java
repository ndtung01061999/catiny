package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.DeviceStatus;
import com.regitiny.catiny.repository.DeviceStatusRepository;

/**
 * Spring Data SQL repository for the {@link DeviceStatus} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.DeviceStatusAdvanceRepository}
 */
public interface DeviceStatusBaseRepository extends BaseRepository<DeviceStatus>, CommonRepository<DeviceStatus>, DeviceStatusRepository
{
}
