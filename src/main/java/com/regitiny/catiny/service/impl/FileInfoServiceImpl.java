package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.FileInfo;
import com.regitiny.catiny.repository.FileInfoRepository;
import com.regitiny.catiny.repository.search.FileInfoSearchRepository;
import com.regitiny.catiny.service.FileInfoService;
import com.regitiny.catiny.service.dto.FileInfoDTO;
import com.regitiny.catiny.service.mapper.FileInfoMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FileInfo}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class FileInfoServiceImpl implements FileInfoService {

  private final Logger log = LoggerFactory.getLogger(FileInfoServiceImpl.class);

  private final FileInfoRepository fileInfoRepository;

  private final FileInfoMapper fileInfoMapper;

  private final FileInfoSearchRepository fileInfoSearchRepository;

  public FileInfoServiceImpl(
    FileInfoRepository fileInfoRepository,
    FileInfoMapper fileInfoMapper,
    FileInfoSearchRepository fileInfoSearchRepository
  ) {
    this.fileInfoRepository = fileInfoRepository;
    this.fileInfoMapper = fileInfoMapper;
    this.fileInfoSearchRepository = fileInfoSearchRepository;
  }

  @Override
  public FileInfoDTO save(FileInfoDTO fileInfoDTO) {
    log.debug("Request to save FileInfo : {}", fileInfoDTO);
    FileInfo fileInfo = fileInfoMapper.toEntity(fileInfoDTO);
    fileInfo = fileInfoRepository.save(fileInfo);
    FileInfoDTO result = fileInfoMapper.toDto(fileInfo);
    fileInfoSearchRepository.save(fileInfo);
    return result;
  }

  @Override
  public Optional<FileInfoDTO> partialUpdate(FileInfoDTO fileInfoDTO) {
    log.debug("Request to partially update FileInfo : {}", fileInfoDTO);

    return fileInfoRepository
      .findById(fileInfoDTO.getId())
      .map(
        existingFileInfo -> {
          fileInfoMapper.partialUpdate(existingFileInfo, fileInfoDTO);

          return existingFileInfo;
        }
      )
      .map(fileInfoRepository::save)
      .map(
        savedFileInfo -> {
          fileInfoSearchRepository.save(savedFileInfo);

          return savedFileInfo;
        }
      )
      .map(fileInfoMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<FileInfoDTO> findAll(Pageable pageable) {
    log.debug("Request to get all FileInfos");
    return fileInfoRepository.findAll(pageable).map(fileInfoMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<FileInfoDTO> findOne(Long id) {
    log.debug("Request to get FileInfo : {}", id);
    return fileInfoRepository.findById(id).map(fileInfoMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete FileInfo : {}", id);
    fileInfoRepository.deleteById(id);
    fileInfoSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<FileInfoDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of FileInfos for query {}", query);
    return fileInfoSearchRepository.search(queryStringQuery(query), pageable).map(fileInfoMapper::toDto);
  }
}
