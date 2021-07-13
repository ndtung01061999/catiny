package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.AlbumAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.AlbumAdvanceSearch;
import com.regitiny.catiny.advance.service.AlbumAdvanceService;
import com.regitiny.catiny.advance.service.mapper.AlbumAdvanceMapper;
import com.regitiny.catiny.service.AlbumQueryService;
import com.regitiny.catiny.service.AlbumService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class AlbumAdvanceServiceImpl extends LocalServiceImpl<AlbumService, AlbumQueryService> implements AlbumAdvanceService
{
  private final AlbumAdvanceRepository albumAdvanceRepository;

  private final AlbumAdvanceSearch albumAdvanceSearch;

  private final AlbumAdvanceMapper albumAdvanceMapper;

  public AlbumAdvanceServiceImpl(AlbumAdvanceRepository albumAdvanceRepository,
    AlbumAdvanceSearch albumAdvanceSearch, AlbumAdvanceMapper albumAdvanceMapper)
  {
    this.albumAdvanceRepository = albumAdvanceRepository;
    this.albumAdvanceSearch = albumAdvanceSearch;
    this.albumAdvanceMapper = albumAdvanceMapper;
  }
}
