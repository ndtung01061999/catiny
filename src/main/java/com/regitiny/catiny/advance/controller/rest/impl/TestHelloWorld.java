package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.service.MessageContentAdvanceService;
import com.regitiny.catiny.advance.service.MessageGroupAdvanceService;
import com.regitiny.catiny.tools.generate.GenerateEntityAdvanceUtils;
import io.vavr.Tuple2;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

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

//    GenerateEntityAdvanceUtils.genRepoAdvance("MeoStatus");
//    GenerateEntityAdvanceUtils.genSearchRepoAdvance("MeoStatus");
//    GenerateEntityAdvanceUtils.genService("MeoStatus");
//    GenerateEntityAdvanceUtils.genMapper("MeoMay");
//    GenerateEntityAdvanceUtils.genModel("MessageContent");
    var projectPath = "C:/Users/yuvytung/IdeaProjects/catiny";
    try (
      var fg = new FileInputStream(projectPath + "/.generate/generate-advance.json");
      var fj = new FileInputStream(projectPath + "/.yo-rc.json"))
    {
      var allBytesGeneratedInfo = fg.readAllBytes();
      var allBytesJhipster = fj.readAllBytes();
      var jsonJhipsterRoot = new JSONObject(new String(allBytesJhipster));
      var jsonGenRoot = new JSONObject(new String(allBytesGeneratedInfo));
      var entitiesGenerated = new JSONArray();

      var entityHasGenerate = new HashSet<String>();
      Optional.of(jsonJhipsterRoot)
        .filter(jsonObject -> jsonObject.has("generator-jhipster"))
        .map(jsonObject -> jsonObject.getJSONObject("generator-jhipster"))
        .filter(jsonObject -> jsonObject.has("entities"))
        .map(jsonObject -> jsonObject.getJSONArray("entities"))
        .ifPresent(objects ->
        {
          for (int i = 0; i < objects.length(); i++)
            entityHasGenerate.add(objects.getString(i));
        });
      Optional.of(jsonGenRoot)
        .filter(jsonObject -> jsonObject.has("entities") && !entityHasGenerate.isEmpty())
        .map(jsonObject -> jsonObject.getJSONArray("entities"))
        .ifPresent(objects ->
        {
          for (int i = 0; i < objects.length(); i++)
          {
            entitiesGenerated.put(objects.getString(i));
            entityHasGenerate.remove(objects.getString(i));
          }
        });
      entityHasGenerate.forEach(s -> log.debug("entity has generate : {}", s));
      entityHasGenerate.stream().map(GenerateEntityAdvanceUtils::Generate)
        .filter(Objects::nonNull)
        .map(Tuple2::_2).forEach(entitiesGenerated::put);

      jsonGenRoot.put("entities", entitiesGenerated);
      var fgWriter = new FileOutputStream(projectPath + "/.generate/generate-advance.json");
      fgWriter.write(jsonGenRoot.toString().getBytes());
      fgWriter.flush();
    }
    catch (IOException e)
    {
      log.warn("err IOException", e);
    }
  }
}
