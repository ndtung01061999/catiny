package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.util.MasterUserUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api")
public class TestHelloWorld
{

  public TestHelloWorld()
  {
  }

  @GetMapping("/test/hello")
  public void helloWorld()
  {
    MasterUserUtil.getCurrentMasterUser();
  }
}
