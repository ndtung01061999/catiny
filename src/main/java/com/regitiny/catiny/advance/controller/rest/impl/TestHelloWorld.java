package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.service.MessageContentAdvanceService;
import com.regitiny.catiny.advance.service.MessageGroupAdvanceService;
import com.regitiny.catiny.tools.generate.GenerateEntityAdvance;
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
  private final MessageContentAdvanceService messageContentAdvanceService;

  public TestHelloWorld(MessageGroupAdvanceService messageGroupAdvanceService, MessageContentAdvanceService messageContentAdvanceService)
  {
    this.messageGroupAdvanceService = messageGroupAdvanceService;
    this.messageContentAdvanceService = messageContentAdvanceService;
  }

  @GetMapping("/test/hello")
  public void helloWorld()
  {
    GenerateEntityAdvance.genRepoAdvance("MeoStatus");
    GenerateEntityAdvance.genSearchRepoAdvance("MeoStatus");
    GenerateEntityAdvance.genService("MeoStatus");

    var a = messageGroupAdvanceService.LocalService().findOne(1L);

//    applicationContext.getBean("").getClass().getMethod("",String.class).invoke();
    log.debug("run {}", a.get());
    log.debug("run {}", messageContentAdvanceService.LocalService().findOne(1093L).get());
  }
}
