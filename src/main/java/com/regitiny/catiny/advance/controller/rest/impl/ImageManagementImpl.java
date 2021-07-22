package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.service.impl.ImageAdvanceServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

@Log4j2
@RestController
@RequestMapping("/api")
public class ImageManagementImpl
{
  @Value("${catiny.server.ffmpeg.data.image.folder-original}")
  private String file;
  @Autowired
  private ImageAdvanceServiceImpl imageAdvanceServiceimpl;

  @GetMapping("image/{name}")
  public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException
  {
    Date date = new Date();
    String filename = file + "/" + date + "-" + name;
    File img = new File(filename);
    return ResponseEntity.ok()
      .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
      .body(Files.readAllBytes(img.toPath()));
  }

  @PostMapping("image")
  public String createImage(@RequestBody MultipartFile image)
  {
    return imageAdvanceServiceimpl.createImage(image);
  }
}
