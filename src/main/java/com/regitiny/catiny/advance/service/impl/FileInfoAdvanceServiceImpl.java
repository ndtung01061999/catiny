package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.FileInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.FileInfoAdvanceSearch;
import com.regitiny.catiny.advance.service.FileInfoAdvanceService;
import com.regitiny.catiny.advance.service.mapper.FileInfoAdvanceMapper;
import com.regitiny.catiny.service.FileInfoQueryService;
import com.regitiny.catiny.service.FileInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class FileInfoAdvanceServiceImpl extends LocalServiceImpl<FileInfoService, FileInfoQueryService> implements FileInfoAdvanceService
{
  private final FileInfoAdvanceRepository fileInfoAdvanceRepository;

  private final FileInfoAdvanceSearch fileInfoAdvanceSearch;

  private final FileInfoAdvanceMapper fileInfoAdvanceMapper;

  public FileInfoAdvanceServiceImpl(FileInfoAdvanceRepository fileInfoAdvanceRepository,
    FileInfoAdvanceSearch fileInfoAdvanceSearch, FileInfoAdvanceMapper fileInfoAdvanceMapper)
  {
    this.fileInfoAdvanceRepository = fileInfoAdvanceRepository;
    this.fileInfoAdvanceSearch = fileInfoAdvanceSearch;
    this.fileInfoAdvanceMapper = fileInfoAdvanceMapper;
  }
}
