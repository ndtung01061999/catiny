package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.FileInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.ImageAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.ImageAdvanceSearch;
import com.regitiny.catiny.advance.service.ImageAdvanceService;
import com.regitiny.catiny.advance.service.mapper.ImageAdvanceMapper;
import com.regitiny.catiny.domain.FileInfo;
import com.regitiny.catiny.domain.Image;
import com.regitiny.catiny.service.ImageQueryService;
import com.regitiny.catiny.service.ImageService;
import io.jsonwebtoken.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.UUID;

@Log4j2
@Service
@Transactional
public class ImageAdvanceServiceImpl extends LocalServiceImpl<ImageService, ImageQueryService> implements ImageAdvanceService
{
  @Value("${catiny.server.ffmpeg.data.image.folder-original}")
  private String file;
  private final ImageAdvanceRepository imageAdvanceRepository;

  private final ImageAdvanceSearch imageAdvanceSearch;

  private final ImageAdvanceMapper imageAdvanceMapper;

  private final FileInfoAdvanceRepository fileInfoAdvanceRepository;

  public ImageAdvanceServiceImpl(ImageAdvanceRepository imageAdvanceRepository,
    ImageAdvanceSearch imageAdvanceSearch, ImageAdvanceMapper imageAdvanceMapper, FileInfoAdvanceRepository fileInfoAdvanceRepository)
  {
    this.imageAdvanceRepository = imageAdvanceRepository;
    this.imageAdvanceSearch = imageAdvanceSearch;
    this.imageAdvanceMapper = imageAdvanceMapper;
    this.fileInfoAdvanceRepository = fileInfoAdvanceRepository;
  }


  public String createImage(MultipartFile image){
    Date date = new Date();
    String filename = image.getOriginalFilename();
    try{
      FileCopyUtils.copy(image.getBytes(), new File(file+ "/" + date.getTime()+"-"+filename));
      FileInfo fileInfo =new FileInfo().path(file+ "/" + date.getTime()+"-"+filename).uuid(UUID.randomUUID());
      fileInfoAdvanceRepository.save(fileInfo);
      Image image1= new Image().name(date.getTime()+"-"+filename).uuid(UUID.randomUUID());
      imageAdvanceRepository.save(image1);
      return "Thành công";
    }catch (IOException | java.io.IOException e){
      return "Thất bại";
    }
  }
}
