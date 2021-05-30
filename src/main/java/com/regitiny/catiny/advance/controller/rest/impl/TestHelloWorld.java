package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.service.MessageGroupAdvanceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api")
public class TestHelloWorld
{
  private final MessageGroupAdvanceService messageGroupAdvanceService;

  public TestHelloWorld(MessageGroupAdvanceService messageGroupAdvanceService)
  {
    this.messageGroupAdvanceService = messageGroupAdvanceService;
  }

  @GetMapping("/test/hello")
  public void helloWorld()
  {
    log.debug(messageGroupAdvanceService);
    var a = messageGroupAdvanceService.LocalService().findOne(1L);

//    applicationContext.getBean("").getClass().getMethod("",String.class).invoke();
    log.debug("run {}", a.get());
  }
}
