package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.DeviceStatusAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.DeviceStatusAdvanceSearch;
import com.regitiny.catiny.advance.service.DeviceStatusAdvanceService;
import com.regitiny.catiny.advance.service.mapper.DeviceStatusAdvanceMapper;
import com.regitiny.catiny.service.DeviceStatusQueryService;
import com.regitiny.catiny.service.DeviceStatusService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class DeviceStatusAdvanceServiceImpl extends LocalServiceImpl<DeviceStatusService, DeviceStatusQueryService> implements DeviceStatusAdvanceService
{
  private final DeviceStatusAdvanceRepository deviceStatusAdvanceRepository;

  private final DeviceStatusAdvanceSearch deviceStatusAdvanceSearch;

  private final DeviceStatusAdvanceMapper deviceStatusAdvanceMapper;

  public DeviceStatusAdvanceServiceImpl(DeviceStatusAdvanceRepository deviceStatusAdvanceRepository,
    DeviceStatusAdvanceSearch deviceStatusAdvanceSearch,
    DeviceStatusAdvanceMapper deviceStatusAdvanceMapper)
  {
    this.deviceStatusAdvanceRepository = deviceStatusAdvanceRepository;
    this.deviceStatusAdvanceSearch = deviceStatusAdvanceSearch;
    this.deviceStatusAdvanceMapper = deviceStatusAdvanceMapper;
  }
}
