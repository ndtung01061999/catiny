package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.ClassInfo;
import com.regitiny.catiny.repository.ClassInfoRepository;
import com.regitiny.catiny.repository.search.ClassInfoSearchRepository;
import com.regitiny.catiny.service.ClassInfoService;
import com.regitiny.catiny.service.dto.ClassInfoDTO;
import com.regitiny.catiny.service.mapper.ClassInfoMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ClassInfo}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class ClassInfoServiceImpl implements ClassInfoService {

  private final Logger log = LoggerFactory.getLogger(ClassInfoServiceImpl.class);

  private final ClassInfoRepository classInfoRepository;

  private final ClassInfoMapper classInfoMapper;

  private final ClassInfoSearchRepository classInfoSearchRepository;

  public ClassInfoServiceImpl(
    ClassInfoRepository classInfoRepository,
    ClassInfoMapper classInfoMapper,
    ClassInfoSearchRepository classInfoSearchRepository
  ) {
    this.classInfoRepository = classInfoRepository;
    this.classInfoMapper = classInfoMapper;
    this.classInfoSearchRepository = classInfoSearchRepository;
  }

  @Override
  public ClassInfoDTO save(ClassInfoDTO classInfoDTO) {
    log.debug("Request to save ClassInfo : {}", classInfoDTO);
    ClassInfo classInfo = classInfoMapper.toEntity(classInfoDTO);
    classInfo = classInfoRepository.save(classInfo);
    ClassInfoDTO result = classInfoMapper.toDto(classInfo);
    classInfoSearchRepository.save(classInfo);
    return result;
  }

  @Override
  public Optional<ClassInfoDTO> partialUpdate(ClassInfoDTO classInfoDTO) {
    log.debug("Request to partially update ClassInfo : {}", classInfoDTO);

    return classInfoRepository
      .findById(classInfoDTO.getId())
      .map(
        existingClassInfo -> {
          classInfoMapper.partialUpdate(existingClassInfo, classInfoDTO);

          return existingClassInfo;
        }
      )
      .map(classInfoRepository::save)
      .map(
        savedClassInfo -> {
          classInfoSearchRepository.save(savedClassInfo);

          return savedClassInfo;
        }
      )
      .map(classInfoMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<ClassInfoDTO> findAll(Pageable pageable) {
    log.debug("Request to get all ClassInfos");
    return classInfoRepository.findAll(pageable).map(classInfoMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<ClassInfoDTO> findOne(Long id) {
    log.debug("Request to get ClassInfo : {}", id);
    return classInfoRepository.findById(id).map(classInfoMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete ClassInfo : {}", id);
    classInfoRepository.deleteById(id);
    classInfoSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<ClassInfoDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of ClassInfos for query {}", query);
    return classInfoSearchRepository.search(queryStringQuery(query), pageable).map(classInfoMapper::toDto);
  }
}
