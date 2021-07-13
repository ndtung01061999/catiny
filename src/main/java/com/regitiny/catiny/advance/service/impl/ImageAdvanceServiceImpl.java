package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.ImageAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.ImageAdvanceSearch;
import com.regitiny.catiny.advance.service.ImageAdvanceService;
import com.regitiny.catiny.advance.service.mapper.ImageAdvanceMapper;
import com.regitiny.catiny.service.ImageQueryService;
import com.regitiny.catiny.service.ImageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class ImageAdvanceServiceImpl extends LocalServiceImpl<ImageService, ImageQueryService> implements ImageAdvanceService
{
  private final ImageAdvanceRepository imageAdvanceRepository;

  private final ImageAdvanceSearch imageAdvanceSearch;

  private final ImageAdvanceMapper imageAdvanceMapper;

  public ImageAdvanceServiceImpl(ImageAdvanceRepository imageAdvanceRepository,
    ImageAdvanceSearch imageAdvanceSearch, ImageAdvanceMapper imageAdvanceMapper)
  {
    this.imageAdvanceRepository = imageAdvanceRepository;
    this.imageAdvanceSearch = imageAdvanceSearch;
    this.imageAdvanceMapper = imageAdvanceMapper;
  }
}
