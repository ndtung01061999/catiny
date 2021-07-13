package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.DeviceStatus;
import com.regitiny.catiny.repository.DeviceStatusRepository;
import com.regitiny.catiny.repository.search.DeviceStatusSearchRepository;
import com.regitiny.catiny.service.DeviceStatusService;
import com.regitiny.catiny.service.dto.DeviceStatusDTO;
import com.regitiny.catiny.service.mapper.DeviceStatusMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DeviceStatus}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class DeviceStatusServiceImpl implements DeviceStatusService {

  private final Logger log = LoggerFactory.getLogger(DeviceStatusServiceImpl.class);

  private final DeviceStatusRepository deviceStatusRepository;

  private final DeviceStatusMapper deviceStatusMapper;

  private final DeviceStatusSearchRepository deviceStatusSearchRepository;

  public DeviceStatusServiceImpl(
    DeviceStatusRepository deviceStatusRepository,
    DeviceStatusMapper deviceStatusMapper,
    DeviceStatusSearchRepository deviceStatusSearchRepository
  ) {
    this.deviceStatusRepository = deviceStatusRepository;
    this.deviceStatusMapper = deviceStatusMapper;
    this.deviceStatusSearchRepository = deviceStatusSearchRepository;
  }

  @Override
  public DeviceStatusDTO save(DeviceStatusDTO deviceStatusDTO) {
    log.debug("Request to save DeviceStatus : {}", deviceStatusDTO);
    DeviceStatus deviceStatus = deviceStatusMapper.toEntity(deviceStatusDTO);
    deviceStatus = deviceStatusRepository.save(deviceStatus);
    DeviceStatusDTO result = deviceStatusMapper.toDto(deviceStatus);
    deviceStatusSearchRepository.save(deviceStatus);
    return result;
  }

  @Override
  public Optional<DeviceStatusDTO> partialUpdate(DeviceStatusDTO deviceStatusDTO) {
    log.debug("Request to partially update DeviceStatus : {}", deviceStatusDTO);

    return deviceStatusRepository
      .findById(deviceStatusDTO.getId())
      .map(
        existingDeviceStatus -> {
          deviceStatusMapper.partialUpdate(existingDeviceStatus, deviceStatusDTO);

          return existingDeviceStatus;
        }
      )
      .map(deviceStatusRepository::save)
      .map(
        savedDeviceStatus -> {
          deviceStatusSearchRepository.save(savedDeviceStatus);

          return savedDeviceStatus;
        }
      )
      .map(deviceStatusMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<DeviceStatusDTO> findAll(Pageable pageable) {
    log.debug("Request to get all DeviceStatuses");
    return deviceStatusRepository.findAll(pageable).map(deviceStatusMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<DeviceStatusDTO> findOne(Long id) {
    log.debug("Request to get DeviceStatus : {}", id);
    return deviceStatusRepository.findById(id).map(deviceStatusMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete DeviceStatus : {}", id);
    deviceStatusRepository.deleteById(id);
    deviceStatusSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<DeviceStatusDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of DeviceStatuses for query {}", query);
    return deviceStatusSearchRepository.search(queryStringQuery(query), pageable).map(deviceStatusMapper::toDto);
  }
}
