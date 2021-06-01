package com.regitiny.catiny.tools.generate;

import com.regitiny.catiny.advance.service.LocalService;
import com.regitiny.catiny.advance.service.impl.LocalServiceImpl;
import com.regitiny.catiny.service.mapper.EntityMapper;
import com.squareup.javapoet.*;
import io.vavr.Function2;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.Modifier;

@Log4j2
public class GenerateEntityAdvanceUtils
{
  private static final String BASE_PACKAGE = "com.regitiny.catiny";

  public static void Generate(String entityName)
  {
    genRepoAdvance(entityName);
    genSearchRepoAdvance(entityName);
    genService(entityName);
  }

  private GenerateEntityAdvanceUtils()
  {
    throw new IllegalStateException("GenerateEntityAdvanceUtils Class");
  }

  public static void genRepoAdvance(String entityName)
  {
    String packageAdvanceRepository = BASE_PACKAGE + ".advance.repository";
    String packageRepository = BASE_PACKAGE + ".repository";
    String entityRepository = entityName + "Repository";
    String entityAdvanceRepository = entityName + "AdvanceRepository";

    String javadoc = "Spring Data SQL advance-repository extends jhipster-repository for the Entity.\n" +
      "@see ${entityRepository} is base repository generate by jhipster";
    javadoc = javadoc.replace("${entityRepository}", entityRepository);

    TypeSpec interfaceAdvanceRepository = TypeSpec.interfaceBuilder(entityAdvanceRepository)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addAnnotation(Repository.class)
      .addAnnotation(AnnotationSpec.builder(SuppressWarnings.class).addMember("value", "$S", "unused").build())
      .addSuperinterface(ClassName.get(packageRepository, entityRepository))
      .build();

    var javaFile = JavaFile.builder(packageAdvanceRepository, interfaceAdvanceRepository)
      .build();
    log.info("...AdvanceRepository after generated :  {}", javaFile.toString());
  }

  public static void genSearchRepoAdvance(String entityName)
  {
    String packageAdvanceRepository = BASE_PACKAGE + ".advance.repository.search";
    String packageRepository = BASE_PACKAGE + ".repository.search";
    String entityRepository = entityName + "SearchRepository";
    String entityAdvanceRepository = entityName + "AdvanceSearch";
    String entity = BASE_PACKAGE + ".domain." + entityName;

    String javadoc = " Spring Data Elasticsearch advance-repository extends jhipster-search-repository for the {@link ${entity}} entity.\n" +
      "@see ${searchRepository} is base repository generate by jhipster";
    javadoc = javadoc.replace("${entity}", entity)
      .replace("${searchRepository}", entityRepository);

    TypeSpec interfaceAdvanceRepository = TypeSpec.interfaceBuilder(entityAdvanceRepository)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addSuperinterface(ClassName.get(packageRepository, entityRepository))
      .build();

    var javaFile = JavaFile.builder(packageAdvanceRepository, interfaceAdvanceRepository)
      .build();
    log.info("...AdvanceRepository after generated : {}", javaFile.toString());
  }

  public static void genService(String entityName)
  {
    final String packageInput = BASE_PACKAGE + ".service";
    final String entityInput = entityName + "Service";
    final String packageAdvanceOutput = BASE_PACKAGE + ".advance.service";
    final String entityAdvanceOutput = entityName + "AdvanceService";
    final String entityDomain = BASE_PACKAGE + ".domain." + entityName;

    String javadoc = " Spring Data Elasticsearch advance-repository extends jhipster-search-repository for the {@link ${entityDomain}} entityDomain.\n" +
      "@see ${searchRepository} is base repository generate by jhipster";
    javadoc = javadoc.replace("${entityDomain}", entityDomain)
      .replace("${searchRepository}", entityInput);

    TypeSpec interfaceAdvanceService = TypeSpec.interfaceBuilder(entityAdvanceOutput)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addSuperinterface(ParameterizedTypeName.get(ClassName.get(LocalService.class), TypeVariableName.get(entityInput), TypeVariableName.get(entityName + "QueryService")))
      .build();


    var javaFile = JavaFile.builder(packageAdvanceOutput, interfaceAdvanceService)
      .build();

    log.info("...AdvanceRepository after generated : \n {}", javaFile.toString());

    final var packageImplInput = packageInput + ".impl";
    final var entityImplInput = entityInput + "Impl";
    final var packageAdvanceImplOutput = packageAdvanceOutput + ".impl";
    final var entityAdvanceImplOutput = entityAdvanceOutput + "Impl";

//    ClassImplement

    var advanceServiceImpl = TypeSpec.classBuilder(entityAdvanceImplOutput)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .superclass(ParameterizedTypeName.get(ClassName.get(LocalServiceImpl.class), ClassName.get(packageInput, entityInput), ClassName.get(packageInput, entityName + "QueryService")))
      .addSuperinterface(TypeVariableName.get(entityAdvanceOutput));

    var constructor = MethodSpec.constructorBuilder()
      .addModifiers(Modifier.PUBLIC);

    Function2<String, String, Void> addBaseField = (pName, eName) ->
    {
      advanceServiceImpl.addField(
        ClassName.get(BASE_PACKAGE + pName, entityName + eName), WordUtils.uncapitalize(entityName + eName), Modifier.PRIVATE, Modifier.FINAL);
      constructor.addParameter(ClassName.get(BASE_PACKAGE + pName, entityName + eName), WordUtils.uncapitalize(entityName + eName))
        .addStatement("this.$N = $N", WordUtils.uncapitalize(entityName + eName), WordUtils.uncapitalize(entityName + eName));
      return null;
    };

    addBaseField.apply(".advance.repository", "AdvanceRepository");
    addBaseField.apply(".advance.repository.search", "AdvanceSearch");
    addBaseField.apply(".advance.service.mapper", "AdvanceMapper");

    advanceServiceImpl.addMethod(constructor.build());

    var javaFileImpl = JavaFile.builder(packageAdvanceImplOutput, advanceServiceImpl.build()).build();

    log.info("...AdvanceRepository after generated : \n {}", javaFileImpl.toString());
  }

  public static void genMapper(String entityName)
  {
    final String packageInput = BASE_PACKAGE + ".service.mapper";
    final String entityInput = entityName + "ServiceMapper";
    final String packageAdvanceOutput = BASE_PACKAGE + ".advance.service.mapper";
    final String entityAdvanceOutput = entityName + "AdvanceServiceMapper";
    final String entityDomain = BASE_PACKAGE + ".domain." + entityName;

    var javadoc = "";

    var advanceMapper = TypeSpec.interfaceBuilder(entityAdvanceOutput)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addSuperinterface(
        ParameterizedTypeName.get(
          ClassName.get(EntityMapper.class),
          ClassName.get(BASE_PACKAGE + ".advance.controller.model", entityName + "Model"),
          ClassName.get(BASE_PACKAGE + ".service.dto", entityName + "DTO")));

    var javaFile = JavaFile.builder(packageAdvanceOutput, advanceMapper.build()).build();
    log.info("...Mapper after generated : \n {}", javaFile.toString());
  }

}
