package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.tools.generate.GenerateEntityAdvanceUtils;
import com.squareup.javapoet.JavaFile;
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
import java.util.Optional;

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

      var entityCanGenerate = new HashSet<String>();
      Optional.of(jsonJhipsterRoot)
        .filter(jsonObject -> jsonObject.has("generator-jhipster"))
        .map(jsonObject -> jsonObject.getJSONObject("generator-jhipster"))
        .filter(jsonObject -> jsonObject.has("entities"))
        .map(jsonObject -> jsonObject.getJSONArray("entities"))
        .ifPresent(objects ->
        {
          for (int i = 0; i < objects.length(); i++)
            entityCanGenerate.add(objects.getString(i));
        });
      Optional.of(jsonGenRoot)
        .filter(jsonObject -> jsonObject.has("entities") && !entityCanGenerate.isEmpty())
        .map(jsonObject -> jsonObject.getJSONObject("entities"))
        .ifPresent(objects ->
        {
          var entities = objects.keys();
          while (entities.hasNext())
          {
            var entity = entities.next();
            entitiesGenerated.put(entity);
            entityCanGenerate.remove(entity);
          }
        });
      entityCanGenerate.forEach(s -> log.debug("entity has generate : {}", s));
      entityCanGenerate.stream().forEach(s ->
      {
        var result = GenerateEntityAdvanceUtils.Generate(s);
        var entities = jsonGenRoot.getJSONObject("entities");
        var json = entities.has(s)
          ? jsonGenRoot.getJSONObject("entities").getJSONObject(s)
          : new JSONObject();

        var generated = json.has("generated")
          ? json.getJSONObject("generated")
          : new JSONObject();
        assert result != null;
        result._1().stream().map(JavaFile::toJavaFileObject)
          .forEach(javaFileObject -> generated.put(javaFileObject.getName(), true));
        result._2().stream().map(JavaFile::toJavaFileObject)
          .forEach(javaFileObject -> generated.put(javaFileObject.getName(), false));

        json.put("generated", generated);
        entities.put(s, json);
        jsonGenRoot.put("entities", entities);
      });
      var fgWriter = new FileOutputStream(projectPath + "/.generate/generate-advance.json");
      fgWriter.write(jsonGenRoot.toString().getBytes());
      fgWriter.flush();
      fgWriter.close();
    }
    catch (IOException e)
    {
      log.warn("err IOException", e);
    }
  }
}
