package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.service.MessageContentAdvanceService;
import com.regitiny.catiny.advance.service.MessageGroupAdvanceService;
import com.regitiny.catiny.tools.generate.GenerateEntityAdvanceUtils;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;

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
    GenerateEntityAdvanceUtils.genRepoAdvance("MeoStatus");
    GenerateEntityAdvanceUtils.genSearchRepoAdvance("MeoStatus");
    GenerateEntityAdvanceUtils.genService("MeoStatus");
    GenerateEntityAdvanceUtils.genMapper("MeoMay");
    GenerateEntityAdvanceUtils.genModel("MessageContent");
    var projectPath = "C:/Users/yuvytung/IdeaProjects/catiny";
    try (
      var fg = new FileInputStream(projectPath + "/.generate/generate-advance.json");
      var fj = new FileInputStream(projectPath + "/.yo-rc.json"))
    {
      var allBytesGeneratedInfo = fg.readAllBytes();
      var allBytesJhipster = fj.readAllBytes();
      var jsonJhipsterRoot = new JSONObject(new String(allBytesJhipster));
      var jsonGenRoot = new JSONObject(new String(allBytesGeneratedInfo));
      log.debug(jsonJhipsterRoot.getJSONObject("generator-jhipster").get("entities").toString());
      log.debug(jsonGenRoot.get("entities").toString());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    var a = messageGroupAdvanceService.localService().findOne(1L);

//    applicationContext.getBean("").getClass().getMethod("",String.class).invoke();
    log.debug("run {}", a.get());
    log.debug("run {}", messageContentAdvanceService.localService().findOne(1L).get());

    log.debug("run {}", a.get());

  }
}
